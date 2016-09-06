package com.adaptionsoft.games.trivia.questions;

import java.util.LinkedList;

import com.adaptionsoft.games.uglytrivia.Game;

public class Questions {

 public static final String QUESTION_CATEGORY_ROCK = "Rock";

 public String currentCategory(int location) {
  int questionOrder = location % Game.NUMBER_OF_QUESTION_CATEGORIES;
  if (questionOrder == 0) {
   return Game.QUESTION_CATEGORY_POP;
  }
  if (questionOrder == 1) {
   return Game.QUESTION_CATEGORY_SCIENCE;
  }
  if (questionOrder == 2) {
   return Game.QUESTION_CATEGORY_SPORTS;
  }
  return Questions.QUESTION_CATEGORY_ROCK;
 }

 public String askQuestion(int location, LinkedList<String> rockQuestions, LinkedList<String> sportsQuestions, LinkedList<String> scienceQuestions, LinkedList<String> popQuestions) {
  String currentCategory = currentCategory(location);
  String askedQuestion = "";
  if (currentCategory == Game.QUESTION_CATEGORY_POP) {
   askedQuestion = popQuestions.removeFirst();
  }
  if (currentCategory == Game.QUESTION_CATEGORY_SCIENCE) {
   askedQuestion = scienceQuestions.removeFirst();
  }
  if (currentCategory == Game.QUESTION_CATEGORY_SPORTS) {
   askedQuestion = sportsQuestions.removeFirst();
  }
  if (currentCategory == Questions.QUESTION_CATEGORY_ROCK) {
   askedQuestion = rockQuestions.removeFirst();
  }
  return askedQuestion;
 }

}
