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
 private static final int WINNING_PURSES = 6;

 public int currentPlayer = 0;

 private News news = new News();
 private Questions questions = new Questions();
 private Players player = new Players();

 public void addPlayer(String playerName) {
  player.addPlayerNameToPlayers(playerName);
  player.initializeLocation();
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
  player.updateLocationOfCurrentPlayer(roll, currentPlayer);
  news.playersNewLocation(
   player.getNameOfCurrentPlayer(currentPlayer),
   player.getLocationOfCurrentPlayer(currentPlayer));
  news.category(questions.currentCategory(
   player.getLocationOfCurrentPlayer(currentPlayer)));
  String askedQuestion = questions
   .askQuestion(player.getLocationOfCurrentPlayer(currentPlayer));
  news.question(askedQuestion);
 }

 private boolean isEven(int dieRoll) {
  return dieRoll % 2 == 0;
 }

 private void leavePenaltyBox() {
  player.setLeavePenaltyBox(true);
  news.playerIsLeavingPenaltyBox(
   player.getNameOfCurrentPlayer(currentPlayer));
 }

 private void stayInPenaltyBox() {
  news.playerIsStayingInPenaltyBox(
   player.getNameOfCurrentPlayer(currentPlayer));
  player.setLeavePenaltyBox(false);
 }

 public boolean wasCorrectlyAnswered() {
  if (player.currentPlayerIsInPenaltyBox(currentPlayer)) {
   if (player.currentPlayerIsNotLeavingPenaltyBox()) {
    player.switchToNextPlayer(this);
    return true;
   }
  }
  news.answerWasCorrect();
  player.increasePursesOfCurrentPlayer(currentPlayer);
  news.playersPurses(player.getNameOfCurrentPlayer(currentPlayer),
   player.getPursesOfCurrentPlayer(currentPlayer));
  boolean winner = didPlayerWin();
  player.switchToNextPlayer(this);
  return winner;
 }

 public void wrongAnswer() {
  news.answerWasIncorrect();
  news.playerSentToPenaltyBox(
   player.getNameOfCurrentPlayer(currentPlayer));
  player.sendCurrentPlayerToPenaltyBox(currentPlayer);
  player.switchToNextPlayer(this);
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