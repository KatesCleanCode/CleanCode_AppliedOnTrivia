package com.adaptionsoft.games.trivia.questions;

import java.util.LinkedList;

import com.adaptionsoft.games.uglytrivia.Game;

public class Questions {

 private static final String QUESTION_CATEGORY_ROCK = "Rock";
 private static final String QUESTION_CATEGORY_SPORTS = "Sports";
 private static final String QUESTION_CATEGORY_SCIENCE = "Science";
 private static final String QUESTION_CATEGORY_POP = "Pop";
 private static final int NUMBER_OF_QUESTION_CATEGORIES = 4;

 public String currentCategory(int location) {
  int questionOrder = location % NUMBER_OF_QUESTION_CATEGORIES;
  if (questionOrder == 0) {
   return QUESTION_CATEGORY_POP;
  }
  if (questionOrder == 1) {
   return QUESTION_CATEGORY_SCIENCE;
  }
  if (questionOrder == 2) {
   return QUESTION_CATEGORY_SPORTS;
  }
  return QUESTION_CATEGORY_ROCK;
 }

 public String askQuestion(int location,
  LinkedList<String> rockQuestions,
  LinkedList<String> sportsQuestions,
  LinkedList<String> scienceQuestions,
  LinkedList<String> popQuestions) {
  String currentCategory = currentCategory(location);
  String askedQuestion = "";
  if (currentCategory == QUESTION_CATEGORY_POP) {
   askedQuestion = popQuestions.removeFirst();
  }
  if (currentCategory == QUESTION_CATEGORY_SCIENCE) {
   askedQuestion = scienceQuestions.removeFirst();
  }
  if (currentCategory == QUESTION_CATEGORY_SPORTS) {
   askedQuestion = sportsQuestions.removeFirst();
  }
  if (currentCategory == QUESTION_CATEGORY_ROCK) {
   askedQuestion = rockQuestions.removeFirst();
  }
  return askedQuestion;
 }

 public void initializeQuestions(LinkedList<String> popQuestions, LinkedList<String> scienceQuestions, LinkedList<String> sportsQuestions, LinkedList<String> rockQuestions) {
  for (int i = 0; i < Game.MAX_NUMBER_OF_QUESTIONS; i++) {
   popQuestions.addLast("Pop Question " + i);
   scienceQuestions.addLast("Science Question " + i);
   sportsQuestions.addLast("Sports Question " + i);
   rockQuestions.addLast("Rock Question " + i);
  }
 }

}
