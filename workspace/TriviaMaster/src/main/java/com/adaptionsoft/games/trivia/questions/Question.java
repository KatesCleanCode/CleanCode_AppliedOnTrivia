package com.adaptionsoft.games.trivia.questions;

public enum Question {
 ROCK("Rock", 3),
 SPORTS("Sports", 2),
 SCIENCE("Science", 1),
 POP("Pop", 0);

 private String category;
 private int order;

 Question(String category, int order) {
  this.category = category;
  this.order = order;
 }

 public String getCategory() {
  return category;
 }

 public static Question getQuestion(int location) {
  int questionOrder = location % Question.values().length;
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
