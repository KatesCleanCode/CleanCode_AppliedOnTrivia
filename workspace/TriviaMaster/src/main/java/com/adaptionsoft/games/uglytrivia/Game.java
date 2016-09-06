package com.adaptionsoft.games.uglytrivia;

import java.util.Random;

import com.adaptionsoft.games.trivia.news.News;
import com.adaptionsoft.games.trivia.news.Printer;
import com.adaptionsoft.games.trivia.player.Players;
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
 private Players players = new Players();

 public Game(Printer printer) {
  news = new News(printer);
 }

 public void addPlayer(String playerName) {
  getPlayers().add(playerName);
  news.playerAdded(playerName);
  news.playersNumber(getPlayers().getNumberOfPlayers());
 }

 private Players getPlayers() {
  return players;
 }

 public void roll(int roll) {
  news.currentPlayer(getPlayers().getCurrentPlayer().getName());
  news.rolledDieRoll(roll);
  if (getPlayers().getCurrentPlayer().isInPenaltyBox()) {
   if (isEven(roll)) {
    stayInPenaltyBox();
    return;
   }
   leavePenaltyBox();
  }
  getPlayers().getCurrentPlayer().updateLocation(roll);
  news.playersNewLocation(getPlayers().getCurrentPlayer().getName(),
   getPlayers().getCurrentPlayer().getLocation());
  news.category(questions
   .currentCategory(getPlayers().getCurrentPlayer().getLocation()));
  String askedQuestion = questions
   .askQuestion(getPlayers().getCurrentPlayer().getLocation());
  news.question(askedQuestion);
 }

 private boolean isEven(int dieRoll) {
  return dieRoll % 2 == 0;
 }

 private void leavePenaltyBox() {
  getPlayers().getCurrentPlayer().setLeavingPenaltyBox(true);
  news.playerIsLeavingPenaltyBox(
   getPlayers().getCurrentPlayer().getName());
 }

 private void stayInPenaltyBox() {
  news.playerIsStayingInPenaltyBox(
   getPlayers().getCurrentPlayer().getName());
  getPlayers().getCurrentPlayer().setLeavingPenaltyBox(false);
 }

 public boolean wasCorrectlyAnswered() {
  if (getPlayers().getCurrentPlayer().isInPenaltyBox()) {
   if (!getPlayers().getCurrentPlayer().isLeavingPenaltyBox()) {
    getPlayers().switchToNextPlayer();
    return true;
   }
  }
  news.answerWasCorrect();
  getPlayers().getCurrentPlayer().increasePursus();
  news.playersPurses(getPlayers().getCurrentPlayer().getName(),
   getPlayers().getCurrentPlayer().getPurses());
  boolean winner = didPlayerWin();
  getPlayers().switchToNextPlayer();
  return winner;
 }

 public void wrongAnswer() {
  news.answerWasIncorrect();
  news
   .playerSentToPenaltyBox(getPlayers().getCurrentPlayer().getName());
  getPlayers().getCurrentPlayer().sendToPenaltyBox();
  getPlayers().switchToNextPlayer();
 }

 private boolean didPlayerWin() {
  return !(getPlayers().getCurrentPlayer()
   .getPurses() == WINNING_PURSES);
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