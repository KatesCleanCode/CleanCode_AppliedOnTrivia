package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.LinkedList;

public class Game {

 private static final String QUESTION_CATEGORY_ROCK = "Rock";
 private static final String QUESTION_CATEGORY_SPORTS = "Sports";
 private static final String QUESTION_CATEGORY_SCIENCE = "Science";
 private static final String QUESTION_CATEGORY_POP = "Pop";
 private static final int MAX_LOCATION = 11;
 private static final int MAX_DIE_ROLL_OF_TWO_DICES = 12;
 private static final int MAX_NUMBER_OF_PLAYERS = 6;
 private static final int MAX_NUMBER_OF_QUESTIONS = 50;
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

 public Game() {
  for (int i = 0; i < MAX_NUMBER_OF_QUESTIONS; i++) {
   popQuestions.addLast("Pop Question " + i);
   scienceQuestions.addLast("Science Question " + i);
   sportsQuestions.addLast("Sports Question " + i);
   rockQuestions.addLast(createRockQuestion(i));
  }
 }

 public String createRockQuestion(int index) {
  return "Rock Question " + index;
 }

 public boolean add(String playerName) {

  players.add(playerName);
  places[howManyPlayers()] = 0;
  purses[howManyPlayers()] = 0;
  inPenaltyBox[howManyPlayers()] = false;

  printMessage(playerName + " was added");
  printMessage("He/She is player number " + players.size());
  return true;
 }

 public int howManyPlayers() {
  return players.size();
 }

 public void roll(int roll) {
  printMessage(players.get(currentPlayer) + " is the current player");
  printMessage("They have rolled a " + roll);
  if (inPenaltyBox[currentPlayer]) {
   if (roll % 2 != 0) {
    isGettingOutOfPenaltyBox = true;
    printMessage(players.get(currentPlayer)
     + " is getting out of the penalty box");
    updateLocationOfCurrentPlayer(roll);
    printCurrentCategory();
    askQuestion();
   } else {
    printMessage(players.get(currentPlayer)
     + " is not getting out of the penalty box");
    isGettingOutOfPenaltyBox = false;
   }
  } else {
   updateLocationOfCurrentPlayer(roll);
   printCurrentCategory();
   askQuestion();
  }
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
  if (places[currentPlayer] == 0) {
   return QUESTION_CATEGORY_POP;
  }
  if (places[currentPlayer] == 4) {
   return QUESTION_CATEGORY_POP;
  }
  if (places[currentPlayer] == 8) {
   return QUESTION_CATEGORY_POP;
  }
  if (places[currentPlayer] == 1) {
   return QUESTION_CATEGORY_SCIENCE;
  }
  if (places[currentPlayer] == 5) {
   return QUESTION_CATEGORY_SCIENCE;
  }
  if (places[currentPlayer] == 9) {
   return QUESTION_CATEGORY_SCIENCE;
  }
  if (places[currentPlayer] == 2) {
   return QUESTION_CATEGORY_SPORTS;
  }
  if (places[currentPlayer] == 6) {
   return QUESTION_CATEGORY_SPORTS;
  }
  if (places[currentPlayer] == 10) {
   return QUESTION_CATEGORY_SPORTS;
  }
  return QUESTION_CATEGORY_ROCK;
 }

 public boolean wasCorrectlyAnswered() {
  if (inPenaltyBox[currentPlayer]) {
   if (!isGettingOutOfPenaltyBox) {
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

 public boolean wrongAnswer() {
  printMessage("Question was incorrectly answered");
  printMessage(
   players.get(currentPlayer) + " was sent to the penalty box");
  inPenaltyBox[currentPlayer] = true;

  switchToNextPlayer();
  return true;
 }

 private boolean didPlayerWin() {
  return !(purses[currentPlayer] == WINNING_PURSES);
 }
}