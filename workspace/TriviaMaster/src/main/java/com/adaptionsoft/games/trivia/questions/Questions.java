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

 public String currentCategory(int location) {
  int questionOrder = location % NUMBER_OF_QUESTION_CATEGORIES;
  if (questionOrder == 0) {
   return Question.POP.getCategory();
  }
  if (questionOrder == 1) {
   return Question.SCIENCE.getCategory();
  }
  if (questionOrder == 2) {
   return Question.SPORTS.getCategory();
  }
  return Question.ROCK.getCategory();
 }

 public String askQuestion(int location) {
  String currentCategory = currentCategory(location);
  String askedQuestion = "";
  if (currentCategory == Question.POP.getCategory()) {
   askedQuestion = popQuestions.removeFirst();
  }
  if (currentCategory == Question.SCIENCE.getCategory()) {
   askedQuestion = scienceQuestions.removeFirst();
  }
  if (currentCategory == Question.SPORTS.getCategory()) {
   askedQuestion = sportsQuestions.removeFirst();
  }
  if (currentCategory == Question.ROCK.getCategory()) {
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