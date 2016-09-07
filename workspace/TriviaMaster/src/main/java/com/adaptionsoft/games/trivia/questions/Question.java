package com.adaptionsoft.games.trivia.questions;

public enum Question {
 ROCK("Rock"), SPORTS("Sports"), SCIENCE("Science"), POP("Pop");

 private String category;

 Question(String category) {
  this.category = category;
 }

 public String getCategory() {
  return category;
 }

 public static Question getQuestion(int location) {
  int questionOrder = location % Questions.NUMBER_OF_QUESTION_CATEGORIES;
  if (questionOrder == 0) {
   return POP;
  }
  if (questionOrder == 1) {
   return SCIENCE;
  }
  if (questionOrder == 2) {
   return SPORTS;
  }
  return ROCK;
 }
}
