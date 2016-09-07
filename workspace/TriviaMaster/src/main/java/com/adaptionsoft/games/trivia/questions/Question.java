package com.adaptionsoft.games.trivia.questions;

public enum Question {
 ROCK("Rock"), SPORTS("Sports");

 private String category;

 Question(String category) {
  this.category = category;
 }

 public String getCategory() {
  return category;
 }
}
