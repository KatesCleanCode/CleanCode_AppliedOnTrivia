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
  playersOld.players.add(playerName);
  news.playerAdded(playerName);
  news.playersNumber(playersOld.players.getNumberOfPlayers());
 }

 public void roll(int roll) {
  news.currentPlayer(playersOld.players.getCurrentPlayer().getName());
  news.rolledDieRoll(roll);
  if (playersOld.players.getCurrentPlayer().isInPenaltyBox()) {
   if (isEven(roll)) {
    stayInPenaltyBox();
    return;
   }
   leavePenaltyBox();
  }
  playersOld.players.getCurrentPlayer().updateLocation(roll);
  news.playersNewLocation(
   playersOld.players.getCurrentPlayer().getName(),
   playersOld.players.getCurrentPlayer().getLocation());
  news.category(questions.currentCategory(
   playersOld.players.getCurrentPlayer().getLocation()));
  String askedQuestion = questions
   .askQuestion(playersOld.players.getCurrentPlayer().getLocation());
  news.question(askedQuestion);
 }

 private boolean isEven(int dieRoll) {
  return dieRoll % 2 == 0;
 }

 private void leavePenaltyBox() {
  playersOld.players.getCurrentPlayer().setLeavingPenaltyBox(true);
  news.playerIsLeavingPenaltyBox(
   playersOld.players.getCurrentPlayer().getName());
 }

 private void stayInPenaltyBox() {
  news.playerIsStayingInPenaltyBox(
   playersOld.players.getCurrentPlayer().getName());
  playersOld.players.getCurrentPlayer().setLeavingPenaltyBox(false);
 }

 public boolean wasCorrectlyAnswered() {
  if (playersOld.players.getCurrentPlayer().isInPenaltyBox()) {
   if (!playersOld.players.getCurrentPlayer().isLeavingPenaltyBox()) {
    playersOld.players.switchToNextPlayer();
    return true;
   }
  }
  news.answerWasCorrect();
  playersOld.players.getCurrentPlayer().increasePursus();
  news.playersPurses(playersOld.players.getCurrentPlayer().getName(),
   playersOld.players.getCurrentPlayer().getPurses());
  boolean winner = didPlayerWin();
  playersOld.players.switchToNextPlayer();
  return winner;
 }

 public void wrongAnswer() {
  news.answerWasIncorrect();
  news.playerSentToPenaltyBox(
   playersOld.players.getCurrentPlayer().getName());
  playersOld.players.getCurrentPlayer().sendToPenaltyBox();
  playersOld.players.switchToNextPlayer();
 }

 private boolean didPlayerWin() {
  return !(playersOld.players.getCurrentPlayer()
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