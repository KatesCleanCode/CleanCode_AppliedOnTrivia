package com.adaptionsoft.games.trivia.questions;

import java.util.LinkedList;

public class Questions {

 private static final String QUESTION_CATEGORY_ROCK =
  Question.ROCK.getCategory();
 private static final String QUESTION_CATEGORY_SPORTS =
  Question.SPORTS.getCategory();
 private static final String QUESTION_CATEGORY_SCIENCE =
  Question.SCIENCE.getCategory();
 private static final String QUESTION_CATEGORY_POP = "Pop";

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

 public String askQuestion(int location) {
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

 private void initializeQuestions() {
  for (int i = 0; i < MAX_NUMBER_OF_QUESTIONS; i++) {
   popQuestions.addLast("Pop Question " + i);
   scienceQuestions.addLast("Science Question " + i);
   sportsQuestions.addLast("Sports Question " + i);
   rockQuestions.addLast("Rock Question " + i);
  }
 }
}