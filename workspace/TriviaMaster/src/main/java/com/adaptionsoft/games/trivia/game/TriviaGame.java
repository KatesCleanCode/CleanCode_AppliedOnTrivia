package com.adaptionsoft.games.trivia.game;

import java.util.Random;

import com.adaptionsoft.games.trivia.news.News;
import com.adaptionsoft.games.trivia.news.Printer;
import com.adaptionsoft.games.trivia.player.Players;
import com.adaptionsoft.games.trivia.questions.Question;
import com.adaptionsoft.games.trivia.questions.Questions;

public class TriviaGame implements Game {

 /** The player has the probability 1/(bound-1) for a wrong answer. */
 private static final int BOUND_FOR_WRONG_ANSWER = 9;
 private static final int MAX_DIE_ROLL = 5;
 private static final int MIN_DIE_ROLL = 1;
 private static final int WRONG_ANSWER = 7;
 private static final int WINNING_PURSES = 6;

 private News news;
 private Questions questions = new Questions();
 private Players players = new Players();

 protected TriviaGame(Printer printer) {
  news = new News(printer);
 }

 @Override
 public void addPlayer(String playerName) {
  players.add(playerName);
  news.playerAdded(playerName);
  news.playersNumber(players.getNumberOfPlayers());
 }

 @Override
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

 public void roll(int roll) {
  news.currentPlayer(players.getCurrentPlayer().getName());
  news.rolledDieRoll(roll);
  if (players.getCurrentPlayer().isInPenaltyBox()) {
   if (isEven(roll)) {
    stayInPenaltyBox();
    return;
   }
   leavePenaltyBox();
  }
  players.getCurrentPlayer().updateLocation(roll);
  news.playersNewLocation(players.getCurrentPlayer().getName(),
   players.getCurrentPlayer().getLocation());
  Question askedQuestion =
   questions.askQuestion(players.getCurrentPlayer().getLocation());
  news.question(askedQuestion);
 }

 private boolean isEven(int dieRoll) {
  return dieRoll % 2 == 0;
 }

 private void leavePenaltyBox() {
  players.getCurrentPlayer().setLeavingPenaltyBox(true);
  news
   .playerIsLeavingPenaltyBox(players.getCurrentPlayer().getName());
 }

 private void stayInPenaltyBox() {
  news
   .playerIsStayingInPenaltyBox(players.getCurrentPlayer().getName());
  players.getCurrentPlayer().setLeavingPenaltyBox(false);
 }

 public boolean wasCorrectlyAnswered() {
  if (players.getCurrentPlayer().isInPenaltyBox()) {
   if (!players.getCurrentPlayer().isLeavingPenaltyBox()) {
    players.switchToNextPlayer();
    return true;
   }
  }
  news.answerWasCorrect();
  players.getCurrentPlayer().increasePursus();
  news.playersPurses(players.getCurrentPlayer().getName(),
   players.getCurrentPlayer().getPurses());
  boolean winner = didPlayerWin();
  players.switchToNextPlayer();
  return winner;
 }

 public void wrongAnswer() {
  news.answerWasIncorrect();
  news.playerSentToPenaltyBox(players.getCurrentPlayer().getName());
  players.getCurrentPlayer().sendToPenaltyBox();
  players.switchToNextPlayer();
 }

 private boolean didPlayerWin() {
  return !(players.getCurrentPlayer().getPurses() == WINNING_PURSES);
 }

 private boolean playerAnsweredWrong(Random random) {
  return random.nextInt(BOUND_FOR_WRONG_ANSWER) == WRONG_ANSWER;
 }

 private int rollDice(Random random) {
  return random.nextInt(MAX_DIE_ROLL) + MIN_DIE_ROLL;
 }
}