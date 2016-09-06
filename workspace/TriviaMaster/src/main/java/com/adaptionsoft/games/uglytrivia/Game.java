package com.adaptionsoft.games.uglytrivia;

import java.util.Random;

import com.adaptionsoft.games.trivia.news.News;
import com.adaptionsoft.games.trivia.news.Printer;
import com.adaptionsoft.games.trivia.player.PlayersOld;
import com.adaptionsoft.games.trivia.questions.Questions;

public class Game {

 /** The player has the probability 1/(bound-1) for a wrong answer. */
 private static final int BOUND_FOR_WRONG_ANSWER = 9;
 private static final int MAX_DIE_ROLL = 5;
 private static final int MIN_DIE_ROLL = 1;
 private static final int WRONG_ANSWER = 7;
 private static final int WINNING_PURSES = 6;

 private News news;
 private PlayersOld playersOld = new PlayersOld();
 private Questions questions = new Questions();

 public Game(Printer printer) {
  news = new News(printer);
 }

 public void addPlayer(String playerName) {
  playersOld.players.add(playerName);
  news.playerAdded(playerName);
  news.playersNumber(playersOld.getNumberOfPlayers());
 }

 public void roll(int roll) {
  news.currentPlayer(playersOld.getNameOfCurrentPlayer());
  news.rolledDieRoll(roll);
  if (playersOld.isCurrentPlayerInPenaltyBox()) {
   if (isEven(roll)) {
    stayInPenaltyBox();
    return;
   }
   leavePenaltyBox();
  }
  playersOld.updateLocationOfCurrentPlayer(roll);
  news.playersNewLocation(playersOld.getNameOfCurrentPlayer(),
   playersOld.getLocationOfCurrentPlayer());
  news.category(
   questions.currentCategory(playersOld.getLocationOfCurrentPlayer()));
  String askedQuestion =
   questions.askQuestion(playersOld.getLocationOfCurrentPlayer());
  news.question(askedQuestion);
 }

 private boolean isEven(int dieRoll) {
  return dieRoll % 2 == 0;
 }

 private void leavePenaltyBox() {
  playersOld.setLeavePenaltyBox(true);
  news.playerIsLeavingPenaltyBox(playersOld.getNameOfCurrentPlayer());
 }

 private void stayInPenaltyBox() {
  news.playerIsStayingInPenaltyBox(playersOld.getNameOfCurrentPlayer());
  playersOld.setLeavePenaltyBox(false);
 }

 public boolean wasCorrectlyAnswered() {
  if (playersOld.isCurrentPlayerInPenaltyBox()) {
   if (playersOld.isCurrentPlayerStayingInPenaltyBox()) {
    playersOld.switchToNextPlayer();
    return true;
   }
  }
  news.answerWasCorrect();
  playersOld.increasePursesOfCurrentPlayer();
  news.playersPurses(playersOld.getNameOfCurrentPlayer(),
   playersOld.getPursesOfCurrentPlayer());
  boolean winner = didPlayerWin();
  playersOld.switchToNextPlayer();
  return winner;
 }

 public void wrongAnswer() {
  news.answerWasIncorrect();
  news.playerSentToPenaltyBox(playersOld.getNameOfCurrentPlayer());
  playersOld.sendCurrentPlayerToPenaltyBox();
  playersOld.switchToNextPlayer();
 }

 private boolean didPlayerWin() {
  return !(playersOld.getPursesOfCurrentPlayer() == WINNING_PURSES);
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