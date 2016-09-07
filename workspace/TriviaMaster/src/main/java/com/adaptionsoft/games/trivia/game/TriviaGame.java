package com.adaptionsoft.games.trivia.game;

import static com.adaptionsoft.games.trivia.utils.NumberUtils.isEven;

import java.util.Random;

import com.adaptionsoft.games.trivia.news.News;
import com.adaptionsoft.games.trivia.news.Printer;
import com.adaptionsoft.games.trivia.player.Player;
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
  news.playerAdded(playerName, players.getNumberOfPlayers());
 }

 @Override
 public void play(Random random) {
  boolean isNotGameOver = true;
  do {
   int dieRoll = rollDice(random);
   askQuestion(dieRoll);
   if (playerAnsweredWrong(random)) {
    processWrongAnswer();
   } else {
    processCorrectAnswer();
    isNotGameOver = isNotGameOver();
   }
   players.switchToNextPlayer();
  } while (isNotGameOver);
 }

 public void askQuestion(int dieRoll) {
  news.currentPlayer(getCurrentPlayer().getName());
  news.rolledDieRoll(dieRoll);
  if (getCurrentPlayer().isInPenaltyBox()) {
   if (isEven(dieRoll)) {
    stayInPenaltyBox();
    return;
   }
   leavePenaltyBox();
  }
  getCurrentPlayer().updateLocation(dieRoll);
  news.playersNewLocation(getCurrentPlayer().getName(),
   getCurrentPlayer().getLocation());
  Question askedQuestion =
   questions.askQuestion(getCurrentPlayer().getLocation());
  news.askedQuestion(askedQuestion);
 }

 private void leavePenaltyBox() {
  getCurrentPlayer().setLeavingPenaltyBox(true);
  news.playerIsLeavingPenaltyBox(getCurrentPlayer().getName());
 }

 private void stayInPenaltyBox() {
  news.playerIsStayingInPenaltyBox(getCurrentPlayer().getName());
  getCurrentPlayer().setLeavingPenaltyBox(false);
 }

 public void processCorrectAnswer() {
  Player player = getCurrentPlayer();
  if (!player.isInPenaltyBox() || player.isLeavingPenaltyBox()) {
   news.answerWasCorrect();
   player.increasePursus();
   news.playersPurses(player.getName(), player.getPurses());
  }
 }

 private Player getCurrentPlayer() {
  return players.getCurrentPlayer();
 }

 public void processWrongAnswer() {
  news.answerWasIncorrect();
  news.playerSentToPenaltyBox(getCurrentPlayer().getName());
  getCurrentPlayer().sendToPenaltyBox();

 }

 private boolean isNotGameOver() {
  return getCurrentPlayer().getPurses() != WINNING_PURSES;
 }

 private boolean playerAnsweredWrong(Random random) {
  return random.nextInt(BOUND_FOR_WRONG_ANSWER) == WRONG_ANSWER;
 }

 private int rollDice(Random random) {
  return random.nextInt(MAX_DIE_ROLL) + MIN_DIE_ROLL;
 }
}