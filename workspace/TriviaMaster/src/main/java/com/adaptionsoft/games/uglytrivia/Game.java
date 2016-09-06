package com.adaptionsoft.games.uglytrivia;

import java.util.Random;

import com.adaptionsoft.games.trivia.news.News;
import com.adaptionsoft.games.trivia.player.Players;
import com.adaptionsoft.games.trivia.questions.Questions;

public class Game {

 /** The player has the probability 1/(bound-1) for a wrong answer. */
 private static final int BOUND_FOR_WRONG_ANSWER = 9;
 private static final int MAX_DIE_ROLL = 5;
 private static final int MIN_DIE_ROLL = 1;
 private static final int WRONG_ANSWER = 7;
 private static final int MAX_LOCATION = 11;
 private static final int MAX_DIE_ROLL_OF_TWO_DICES = 12;
 public static final int MAX_NUMBER_OF_PLAYERS = 6;
 private static final int WINNING_PURSES = 6;

 private int[] places = new int[MAX_NUMBER_OF_PLAYERS];
 private int currentPlayer = 0;
 private boolean isGettingOutOfPenaltyBox;

 private News news = new News();
 private Questions questions = new Questions();
 private Players player = new Players();

 public void addPlayer(String playerName) {
  player.addPlayerNameToPlayers(playerName);
  player.initializeLocation(places);
  player.initializePurses();
  player.initializePenaltyBox();
  news.playerAdded(playerName);
  news.playersNumber(player.getNumberOfPlayers());
 }

 public void roll(int roll) {
  news.currentPlayer(player.getNameOfCurrentPlayer(currentPlayer));
  news.rolledDieRoll(roll);
  if (player.currentPlayerIsInPenaltyBox(currentPlayer)) {
   if (isEven(roll)) {
    stayInPenaltyBox();
    return;
   }
   leavePenaltyBox();
  }
  updateLocationOfCurrentPlayer(roll);
  news.playersNewLocation(
   player.getNameOfCurrentPlayer(currentPlayer),
   player.getLocationOfCurrentPlayer(places, currentPlayer));
  news.category(questions.currentCategory(
   player.getLocationOfCurrentPlayer(places, currentPlayer)));
  String askedQuestion = questions
   .askQuestion(player.getLocationOfCurrentPlayer(places, currentPlayer));
  news.question(askedQuestion);
 }

 private boolean isEven(int dieRoll) {
  return dieRoll % 2 == 0;
 }

 private void leavePenaltyBox() {
  isGettingOutOfPenaltyBox = true;
  news.playerIsLeavingPenaltyBox(
   player.getNameOfCurrentPlayer(currentPlayer));
 }

 private void stayInPenaltyBox() {
  news.playerIsStayingInPenaltyBox(
   player.getNameOfCurrentPlayer(currentPlayer));
  isGettingOutOfPenaltyBox = false;
 }

 private void updateLocationOfCurrentPlayer(int roll) {
  places[currentPlayer] =
   player.getLocationOfCurrentPlayer(places, currentPlayer) + roll;
  if (player.getLocationOfCurrentPlayer(places,
   currentPlayer) > MAX_LOCATION) {
   places[currentPlayer] =
    player.getLocationOfCurrentPlayer(places, currentPlayer)
     - MAX_DIE_ROLL_OF_TWO_DICES;
  }
 }

 public boolean wasCorrectlyAnswered() {
  if (player.currentPlayerIsInPenaltyBox(currentPlayer)) {
   if (currentPlayerIsNotLeavingPenaltyBox()) {
    switchToNextPlayer();
    return true;
   }
  }
  news.answerWasCorrect();
  player.increasePursesOfCurrentPlayer(currentPlayer);
  news.playersPurses(player.getNameOfCurrentPlayer(currentPlayer),
   player.getPursesOfCurrentPlayer(currentPlayer));
  boolean winner = didPlayerWin();
  switchToNextPlayer();
  return winner;
 }

 private boolean currentPlayerIsNotLeavingPenaltyBox() {
  return !isGettingOutOfPenaltyBox;
 }

 private void switchToNextPlayer() {
  currentPlayer++;
  if (currentPlayer == player.getNumberOfPlayers()) {
   currentPlayer = 0;
  }
 }

 public void wrongAnswer() {
  news.answerWasIncorrect();
  news.playerSentToPenaltyBox(
   player.getNameOfCurrentPlayer(currentPlayer));
  player.sendCurrentPlayerToPenaltyBox(currentPlayer);
  switchToNextPlayer();
 }

 private boolean didPlayerWin() {
  return !(player
   .getPursesOfCurrentPlayer(currentPlayer) == WINNING_PURSES);
 }

 public void play(Random random) {
  boolean notAWinner = true;
  do {
   roll(rollDice(random));
   if (playerAnsweredWrong(random)) {
    wrongAnswer();
   } else {
    notAWinner = wasCorrectlyAnswered();
   }
  } while (notAWinner);
 }

 private boolean playerAnsweredWrong(Random random) {
  return random.nextInt(BOUND_FOR_WRONG_ANSWER) == WRONG_ANSWER;
 }

 private int rollDice(Random random) {
  return random.nextInt(MAX_DIE_ROLL) + MIN_DIE_ROLL;
 }
}