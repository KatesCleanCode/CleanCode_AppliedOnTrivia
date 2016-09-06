package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.Random;

import com.adaptionsoft.games.trivia.news.News;
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

 public void addPlayer(String playerName) {
  addPlayerNameToPlayers(playerName);
  initializeLocation();
  initializePurses();
  initializePenaltyBox();
  news.playerAdded(playerName);
  news.playersNumber(players.size());
 }

 private void addPlayerNameToPlayers(String playerName) {
  players.add(playerName);
 }

 private void initializePenaltyBox() {
  inPenaltyBox[howManyPlayers()] = false;
 }

 private void initializePurses() {
  purses[howManyPlayers()] = 0;
 }

 private void initializeLocation() {
  places[howManyPlayers()] = 0;
 }

 public int howManyPlayers() {
  return players.size();
 }

 public void roll(int roll) {
  news.currentPlayer(getNameOfCurrentPlayer());
  news.rolledDieRoll(roll);
  if (currentPlayerIsInPenaltyBox()) {
   if (isEven(roll)) {
    stayInPenaltyBox();
    return;
   }
   leavePenaltyBox();
  }
  updateLocationOfCurrentPlayer(roll);
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
  news.playerIsLeavingPenaltyBox(getNameOfCurrentPlayer());
 }

 private String getNameOfCurrentPlayer() {
  return players.get(currentPlayer);
 }

 private void stayInPenaltyBox() {
  news.playerIsStayingInPenaltyBox(getNameOfCurrentPlayer());
  isGettingOutOfPenaltyBox = false;
 }

 private void updateLocationOfCurrentPlayer(int roll) {
  places[currentPlayer] = getLocationOfCurrentPlayer() + roll;
  if (getLocationOfCurrentPlayer() > MAX_LOCATION) {
   places[currentPlayer] =
    getLocationOfCurrentPlayer() - MAX_DIE_ROLL_OF_TWO_DICES;
  }
  news.playersNewLocation(getNameOfCurrentPlayer(),
   getLocationOfCurrentPlayer());
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
  boolean winner = didPlayerWin();
  switchToNextPlayer();
  return winner;
 }

 private boolean currentPlayerIsNotLeavingPenaltyBox() {
  return !isGettingOutOfPenaltyBox;
 }

 private void increasePursusOfCurrentPlayer() {
  purses[currentPlayer]++;
  news.playersPurses(getNameOfCurrentPlayer(),
   getPursesOfCurrentPlayer());
 }

 private void switchToNextPlayer() {
  currentPlayer++;
  if (currentPlayer == players.size()) {
   currentPlayer = 0;
  }
 }

 public void wrongAnswer() {
  news.answerWasIncorrect();
  news.playerSentToPenaltyBox(getNameOfCurrentPlayer());
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