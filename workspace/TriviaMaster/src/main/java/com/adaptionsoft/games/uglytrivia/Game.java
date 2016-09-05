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
 private static final int WINNING_PURSUS = 6;

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

  System.out.println(playerName + " was added");
  System.out.println("He/She is player number " + players.size());
  return true;
 }

 public int howManyPlayers() {
  return players.size();
 }

 public void roll(int roll) {
  System.out
   .println(players.get(currentPlayer) + " is the current player");
  System.out.println("They have rolled a " + roll);

  if (inPenaltyBox[currentPlayer]) {
   if (roll % 2 != 0) {
    isGettingOutOfPenaltyBox = true;

    System.out.println(players.get(currentPlayer)
     + " is getting out of the penalty box");
    places[currentPlayer] = places[currentPlayer] + roll;
    if (places[currentPlayer] > MAX_LOCATION) {
     places[currentPlayer] =
      places[currentPlayer] - MAX_DIE_ROLL_OF_TWO_DICES;
    }

    System.out.println(players.get(currentPlayer)
     + "'s new location is " + places[currentPlayer]);
    System.out.println("The category is " + currentCategory());
    askQuestion();
   } else {
    System.out.println(players.get(currentPlayer)
     + " is not getting out of the penalty box");
    isGettingOutOfPenaltyBox = false;
   }
  } else {
   places[currentPlayer] = places[currentPlayer] + roll;
   if (places[currentPlayer] > MAX_LOCATION) {
    places[currentPlayer] =
     places[currentPlayer] - MAX_DIE_ROLL_OF_TWO_DICES;
   }

   System.out.println(players.get(currentPlayer)
    + "'s new location is " + places[currentPlayer]);
   System.out.println("The category is " + currentCategory());
   askQuestion();
  }

 }

 private void askQuestion() {
  if (currentCategory() == QUESTION_CATEGORY_POP) {
   System.out.println(popQuestions.removeFirst());
  }
  if (currentCategory() == QUESTION_CATEGORY_SCIENCE) {
   System.out.println(scienceQuestions.removeFirst());
  }
  if (currentCategory() == QUESTION_CATEGORY_SPORTS) {
   System.out.println(sportsQuestions.removeFirst());
  }
  if (currentCategory() == QUESTION_CATEGORY_ROCK) {
   System.out.println(rockQuestions.removeFirst());
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
   if (isGettingOutOfPenaltyBox) {
    System.out.println("Answer was correct!!!!");
    purses[currentPlayer]++;
    System.out.println(players.get(currentPlayer) + " now has "
     + purses[currentPlayer] + " Gold Coins.");

    boolean winner = didPlayerWin();
    currentPlayer++;
    if (currentPlayer == players.size()) {
     currentPlayer = 0;
    }

    return winner;
   } else {
    currentPlayer++;
    if (currentPlayer == players.size()) {
     currentPlayer = 0;
    }
    return true;
   }

  } else {

   System.out.println("Answer was correct!!!!");
   purses[currentPlayer]++;
   System.out.println(players.get(currentPlayer) + " now has "
    + purses[currentPlayer] + " Gold Coins.");

   boolean winner = didPlayerWin();
   currentPlayer++;
   if (currentPlayer == players.size()) {
    currentPlayer = 0;
   }

   return winner;
  }
 }

 public boolean wrongAnswer() {
  System.out.println("Question was incorrectly answered");
  System.out.println(
   players.get(currentPlayer) + " was sent to the penalty box");
  inPenaltyBox[currentPlayer] = true;

  currentPlayer++;
  if (currentPlayer == players.size()) {
   currentPlayer = 0;
  }
  return true;
 }

 private boolean didPlayerWin() {
  return !(purses[currentPlayer] == WINNING_PURSUS);
 }
}