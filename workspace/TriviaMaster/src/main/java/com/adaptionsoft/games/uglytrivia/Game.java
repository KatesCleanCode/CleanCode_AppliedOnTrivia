package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
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
 private static final int MAX_NUMBER_OF_PLAYERS = 6;
 private static final int WINNING_PURSES = 6;

 private ArrayList<String> players = new ArrayList<>();
 private int[] places = new int[MAX_NUMBER_OF_PLAYERS];
 private int[] purses = new int[MAX_NUMBER_OF_PLAYERS];
 private boolean[] inPenaltyBox = new boolean[MAX_NUMBER_OF_PLAYERS];
 private int currentPlayer = 0;
 private boolean isGettingOutOfPenaltyBox;

 private News news = new News();
 private Questions questions = new Questions();
 private Players player = new Players();

 public void addPlayer(String playerName) {
  player.addPlayerNameToPlayers(playerName, players);
  initializeLocation();
  initializePurses();
  initializePenaltyBox();
  news.playerAdded(playerName);
  news.playersNumber(player.getNumberOfPlayers(players));
 }

 private void initializePenaltyBox() {
  inPenaltyBox[player.getNumberOfPlayers(players)] = false;
 }

 private void initializePurses() {
  purses[player.getNumberOfPlayers(players)] = 0;
 }

 private void initializeLocation() {
  places[player.getNumberOfPlayers(players)] = 0;
 }

 public void roll(int roll) {
  news.currentPlayer(
   player.getNameOfCurrentPlayer(players, currentPlayer));
  news.rolledDieRoll(roll);
  if (currentPlayerIsInPenaltyBox()) {
   if (isEven(roll)) {
    stayInPenaltyBox();
    return;
   }
   leavePenaltyBox();
  }
  updateLocationOfCurrentPlayer(roll);
  news.playersNewLocation(
   player.getNameOfCurrentPlayer(players, currentPlayer),
   getLocationOfCurrentPlayer());
  news
   .category(questions.currentCategory(getLocationOfCurrentPlayer()));
  String askedQuestion =
   questions.askQuestion(getLocationOfCurrentPlayer());
  news.question(askedQuestion);
 }

 private int getLocationOfCurrentPlayer() {
  return places[currentPlayer];
 }

 private boolean isEven(int dieRoll) {
  return dieRoll % 2 == 0;
 }

 private boolean currentPlayerIsInPenaltyBox() {
  return inPenaltyBox[currentPlayer];
 }

 private void leavePenaltyBox() {
  isGettingOutOfPenaltyBox = true;
  news.playerIsLeavingPenaltyBox(
   player.getNameOfCurrentPlayer(players, currentPlayer));
 }

 private void stayInPenaltyBox() {
  news.playerIsStayingInPenaltyBox(
   player.getNameOfCurrentPlayer(players, currentPlayer));
  isGettingOutOfPenaltyBox = false;
 }

 private void updateLocationOfCurrentPlayer(int roll) {
  places[currentPlayer] = getLocationOfCurrentPlayer() + roll;
  if (getLocationOfCurrentPlayer() > MAX_LOCATION) {
   places[currentPlayer] =
    getLocationOfCurrentPlayer() - MAX_DIE_ROLL_OF_TWO_DICES;
  }
 }

 public boolean wasCorrectlyAnswered() {
  if (currentPlayerIsInPenaltyBox()) {
   if (currentPlayerIsNotLeavingPenaltyBox()) {
    switchToNextPlayer();
    return true;
   }
  }
  news.answerWasCorrect();
  increasePursusOfCurrentPlayer();
  news.playersPurses(
   player.getNameOfCurrentPlayer(players, currentPlayer),
   getPursesOfCurrentPlayer());
  boolean winner = didPlayerWin();
  switchToNextPlayer();
  return winner;
 }

 private boolean currentPlayerIsNotLeavingPenaltyBox() {
  return !isGettingOutOfPenaltyBox;
 }

 private void increasePursusOfCurrentPlayer() {
  purses[currentPlayer]++;
 }

 private void switchToNextPlayer() {
  currentPlayer++;
  if (currentPlayer == player.getNumberOfPlayers(players)) {
   currentPlayer = 0;
  }
 }

 public void wrongAnswer() {
  news.answerWasIncorrect();
  news.playerSentToPenaltyBox(
   player.getNameOfCurrentPlayer(players, currentPlayer));
  sendCurrentPlayerToPenaltyBox();
  switchToNextPlayer();
 }

 private void sendCurrentPlayerToPenaltyBox() {
  inPenaltyBox[currentPlayer] = true;
 }

 private boolean didPlayerWin() {
  return !(getPursesOfCurrentPlayer() == WINNING_PURSES);
 }

 private int getPursesOfCurrentPlayer() {
  return purses[currentPlayer];
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