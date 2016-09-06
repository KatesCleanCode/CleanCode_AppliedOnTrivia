package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

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
 private ConsolePrinter printer = new ConsolePrinter();

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
  printPlayerWasAdded(playerName);
  printPlayersNumber();
 }

 private void addPlayerNameToPlayers(String playerName) {
  players.add(playerName);
 }

 private void printPlayersNumber() {
  printMessage("He/She is player number " + players.size());
 }

 private void printPlayerWasAdded(String playerName) {
  printMessage(playerName + " was added");
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
  printCurrentPlayer();
  printDieRoll(roll);
  if (currentPlayerIsInPenaltyBox()) {
   if (isEven(roll)) {
    stayInPenaltyBox();
    return;
   }
   leavePenaltyBox();
  }
  updateLocationOfCurrentPlayer(roll);
  printCurrentCategory();
  askQuestion();
 }

 private boolean isEven(int dieRoll) {
  return dieRoll % 2 == 0;
 }

 private boolean currentPlayerIsInPenaltyBox() {
  return inPenaltyBox[currentPlayer];
 }

 private void printDieRoll(int dieRoll) {
  printMessage("They have rolled a " + dieRoll);
 }

 private void printCurrentPlayer() {
  printMessage(players.get(currentPlayer) + " is the current player");
 }

 private void leavePenaltyBox() {
  isGettingOutOfPenaltyBox = true;
  printIsLeavingPenaltyBox();
 }

 private void stayInPenaltyBox() {
  printIsNotLeavingPenaltyBox();
  isGettingOutOfPenaltyBox = false;
 }

 private void printIsLeavingPenaltyBox() {
  printMessage(
   players.get(currentPlayer) + " is getting out of the penalty box");
 }

 private void printIsNotLeavingPenaltyBox() {
  printMessage(players.get(currentPlayer)
   + " is not getting out of the penalty box");
 }

 private void printCurrentCategory() {
  printMessage("The category is " + currentCategory());
 }

 private void updateLocationOfCurrentPlayer(int roll) {
  places[currentPlayer] = places[currentPlayer] + roll;
  if (places[currentPlayer] > MAX_LOCATION) {
   places[currentPlayer] =
    places[currentPlayer] - MAX_DIE_ROLL_OF_TWO_DICES;
  }
  printMessage(players.get(currentPlayer) + "'s new location is "
   + places[currentPlayer]);
 }

 private void askQuestion() {
  if (currentCategory() == QUESTION_CATEGORY_POP) {
   printMessage(popQuestions.removeFirst());
  }
  if (currentCategory() == QUESTION_CATEGORY_SCIENCE) {
   printMessage(scienceQuestions.removeFirst());
  }
  if (currentCategory() == QUESTION_CATEGORY_SPORTS) {
   printMessage(sportsQuestions.removeFirst());
  }
  if (currentCategory() == QUESTION_CATEGORY_ROCK) {
   printMessage(rockQuestions.removeFirst());
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
  printAnswerWasCorrect();
  increasePursusOfCurrentPlayer();
  boolean winner = didPlayerWin();
  switchToNextPlayer();
  return winner;
 }

 private boolean currentPlayerIsNotLeavingPenaltyBox() {
  return !isGettingOutOfPenaltyBox;
 }

 private void printAnswerWasCorrect() {
  printMessage("Answer was correct!!!!");
 }

 private void increasePursusOfCurrentPlayer() {
  purses[currentPlayer]++;
  printMessage(players.get(currentPlayer) + " now has "
   + purses[currentPlayer] + " Gold Coins.");
 }

 private void switchToNextPlayer() {
  currentPlayer++;
  if (currentPlayer == players.size()) {
   currentPlayer = 0;
  }
 }

 private void printMessage(String message) {
  System.out.println(message);
 }

 public void wrongAnswer() {
  printAnswerWasIncorrect();
  printPlayerWasSentToPenaltyBox();
  sendCurrentPlayerToPenaltyBox();
  switchToNextPlayer();
 }

 private void sendCurrentPlayerToPenaltyBox() {
  inPenaltyBox[currentPlayer] = true;
 }

 private void printPlayerWasSentToPenaltyBox() {
  printMessage(
   players.get(currentPlayer) + " was sent to the penalty box");
 }

 private void printAnswerWasIncorrect() {
  printMessage("Question was incorrectly answered");
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