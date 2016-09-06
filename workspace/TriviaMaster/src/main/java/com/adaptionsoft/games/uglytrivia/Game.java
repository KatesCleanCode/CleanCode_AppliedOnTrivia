package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import com.adaptionsoft.games.trivia.news.News;
import com.adaptionsoft.games.trivia.printer.ConsolePrinter;

public class Game {

 /** The player has the probability 1/(bound-1) for a wrong answer. */
 private static final int BOUND_FOR_WRONG_ANSWER = 9;
 private static final int MAX_DIE_ROLL = 5;
 private static final int MIN_DIE_ROLL = 1;
 private static final int WRONG_ANSWER = 7;
 private static final String QUESTION_CATEGORY_ROCK = "Rock";
 private static final String QUESTION_CATEGORY_SPORTS = "Sports";
 private static final String QUESTION_CATEGORY_SCIENCE = "Science";
 private static final String QUESTION_CATEGORY_POP = "Pop";
 private static final int MAX_LOCATION = 11;
 private static final int MAX_DIE_ROLL_OF_TWO_DICES = 12;
 private static final int MAX_NUMBER_OF_PLAYERS = 6;
 private static final int MAX_NUMBER_OF_QUESTIONS = 50;
 private static final int NUMBER_OF_QUESTION_CATEGORIES = 4;
 private static final int WINNING_PURSES = 6;

 private ArrayList<String> players = new ArrayList<>();
 private int[] places = new int[MAX_NUMBER_OF_PLAYERS];
 private int[] purses = new int[MAX_NUMBER_OF_PLAYERS];
 private boolean[] inPenaltyBox = new boolean[MAX_NUMBER_OF_PLAYERS];

 private LinkedList<String> popQuestions = new LinkedList<>();
 private LinkedList<String> scienceQuestions = new LinkedList<>();
 private LinkedList<String> sportsQuestions = new LinkedList<>();
 private LinkedList<String> rockQuestions = new LinkedList<>();

 private int currentPlayer = 0;
 private boolean isGettingOutOfPenaltyBox;
 public ConsolePrinter printer = new ConsolePrinter();
 private News news = new News();

 public Game() {
  initializeQuestions();
 }

 private void initializeQuestions() {
  for (int i = 0; i < MAX_NUMBER_OF_QUESTIONS; i++) {
   popQuestions.addLast("Pop Question " + i);
   scienceQuestions.addLast("Science Question " + i);
   sportsQuestions.addLast("Sports Question " + i);
   rockQuestions.addLast("Rock Question " + i);
  }
 }

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
  news.currentPlayer(players.get(currentPlayer));
  news.rolledDieRoll(roll);
  if (currentPlayerIsInPenaltyBox()) {
   if (isEven(roll)) {
    stayInPenaltyBox();
    return;
   }
   leavePenaltyBox();
  }
  updateLocationOfCurrentPlayer(roll);
  news.category(currentCategory());
  askQuestion();
 }

 private boolean isEven(int dieRoll) {
  return dieRoll % 2 == 0;
 }

 private boolean currentPlayerIsInPenaltyBox() {
  return inPenaltyBox[currentPlayer];
 }

 private void leavePenaltyBox() {
  isGettingOutOfPenaltyBox = true;
  news.printIsLeavingPenaltyBox(this, players.get(currentPlayer));
 }

 private void stayInPenaltyBox() {
  printIsNotLeavingPenaltyBox();
  isGettingOutOfPenaltyBox = false;
 }

 private void printIsNotLeavingPenaltyBox() {
  printer.printMessage(players.get(currentPlayer)
   + " is not getting out of the penalty box");
 }

 private void updateLocationOfCurrentPlayer(int roll) {
  places[currentPlayer] = places[currentPlayer] + roll;
  if (places[currentPlayer] > MAX_LOCATION) {
   places[currentPlayer] =
    places[currentPlayer] - MAX_DIE_ROLL_OF_TWO_DICES;
  }
  printer.printMessage(players.get(currentPlayer)
   + "'s new location is " + places[currentPlayer]);
 }

 private void askQuestion() {
  if (currentCategory() == QUESTION_CATEGORY_POP) {
   printer.printMessage(popQuestions.removeFirst());
  }
  if (currentCategory() == QUESTION_CATEGORY_SCIENCE) {
   printer.printMessage(scienceQuestions.removeFirst());
  }
  if (currentCategory() == QUESTION_CATEGORY_SPORTS) {
   printer.printMessage(sportsQuestions.removeFirst());
  }
  if (currentCategory() == QUESTION_CATEGORY_ROCK) {
   printer.printMessage(rockQuestions.removeFirst());
  }
 }

 private String currentCategory() {
  int location = places[currentPlayer];
  if (location % NUMBER_OF_QUESTION_CATEGORIES == 0) {
   return QUESTION_CATEGORY_POP;
  }
  if (location % NUMBER_OF_QUESTION_CATEGORIES == 1) {
   return QUESTION_CATEGORY_SCIENCE;
  }
  if (location % NUMBER_OF_QUESTION_CATEGORIES == 2) {
   return QUESTION_CATEGORY_SPORTS;
  }
  return QUESTION_CATEGORY_ROCK;
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
  printer.printMessage(players.get(currentPlayer) + " now has "
   + purses[currentPlayer] + " Gold Coins.");
 }

 private void switchToNextPlayer() {
  currentPlayer++;
  if (currentPlayer == players.size()) {
   currentPlayer = 0;
  }
 }

 public void wrongAnswer() {
  news.answerWasIncorrect();
  printPlayerWasSentToPenaltyBox();
  sendCurrentPlayerToPenaltyBox();
  switchToNextPlayer();
 }

 private void sendCurrentPlayerToPenaltyBox() {
  inPenaltyBox[currentPlayer] = true;
 }

 private void printPlayerWasSentToPenaltyBox() {
  printer.printMessage(
   players.get(currentPlayer) + " was sent to the penalty box");
 }

 private boolean didPlayerWin() {
  return !(purses[currentPlayer] == WINNING_PURSES);
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