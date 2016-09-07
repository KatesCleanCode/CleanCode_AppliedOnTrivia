package com.adaptionsoft.games.trivia.questions;

public enum Question {
 ROCK("Rock", 3),
 SPORTS("Sports", 2),
 SCIENCE("Science", 1),
 POP("Pop", 0);

 private String category;
 private int number;
 private int order;

 Question(String category, int order) {
  this.category = category;
  this.order = order;
 }

 public String getCategory() {
  return category;
 }

 private int getOrder() {
  return order;
 }

 public static Question getQuestion(int location) {
  Question[] questions = Question.values();
  int order = location % questions.length;

  for (Question question : questions) {
   if (question.getOrder() == order) {
    return question;
   }
  }
  return ROCK;
 }

 public void setNumber(int number) {
  this.number = number;
 }

 public String getName() {
  return getCategory() + " Question " + number;
 }
}