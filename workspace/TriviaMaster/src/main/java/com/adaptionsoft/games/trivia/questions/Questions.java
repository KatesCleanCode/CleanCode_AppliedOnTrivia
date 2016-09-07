package com.adaptionsoft.games.trivia.questions;

import java.util.LinkedList;

public class Questions {

 private static final int NUMBER_OF_QUESTION_CATEGORIES = 4;
 private static final int MAX_NUMBER_OF_QUESTIONS = 50;

 private LinkedList<String> popQuestions = new LinkedList<>();
 private LinkedList<String> scienceQuestions = new LinkedList<>();
 private LinkedList<String> sportsQuestions = new LinkedList<>();
 private LinkedList<String> rockQuestions = new LinkedList<>();

 public Questions() {
  initializeQuestions();
 }

 public static Question currentCategory(int location) {
  int questionOrder = location % NUMBER_OF_QUESTION_CATEGORIES;
  if (questionOrder == 0) {
   return Question.POP;
  }
  if (questionOrder == 1) {
   return Question.SCIENCE;
  }
  if (questionOrder == 2) {
   return Question.SPORTS;
  }
  return Question.ROCK;
 }

 public String askQuestion(int location) {
  Question currentCategory = currentCategory(location);
  String askedQuestion = "";
  if (currentCategory == Question.POP) {
   askedQuestion = popQuestions.removeFirst();
  }
  if (currentCategory == Question.SCIENCE) {
   askedQuestion = scienceQuestions.removeFirst();
  }
  if (currentCategory == Question.SPORTS) {
   askedQuestion = sportsQuestions.removeFirst();
  }
  if (currentCategory == Question.ROCK) {
   askedQuestion = rockQuestions.removeFirst();
  }
  return askedQuestion;
 }

 private void initializeQuestions() {
  for (int i = 0; i < MAX_NUMBER_OF_QUESTIONS; i++) {
   popQuestions.addLast("Pop Question " + i);
   scienceQuestions.addLast("Science Question " + i);
   sportsQuestions.addLast("Sports Question " + i);
   rockQuestions.addLast("Rock Question " + i);
  }
 }
}