package com.adaptionsoft.games.trivia.questions;

import java.util.LinkedList;

public class Questions {

 static final int NUMBER_OF_QUESTION_CATEGORIES = 4;
 private static final int MAX_NUMBER_OF_QUESTIONS = 50;

 private LinkedList<String> popQuestions = new LinkedList<>();
 private LinkedList<String> scienceQuestions = new LinkedList<>();
 private LinkedList<String> sportsQuestions = new LinkedList<>();
 private LinkedList<String> rockQuestions = new LinkedList<>();

 public Questions() {
  initializeQuestions();
 }

 public String askQuestion(int location) {
  Question currentCategory = Question.currentCategory(location);
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