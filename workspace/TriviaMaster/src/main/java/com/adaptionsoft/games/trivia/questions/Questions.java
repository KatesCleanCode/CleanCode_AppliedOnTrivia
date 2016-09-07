package com.adaptionsoft.games.trivia.questions;

import java.util.HashMap;
import java.util.Map;

public class Questions {

 private Map<Question, Integer> lastNumbers = new HashMap<>();

 public Questions() {
  for (Question question : Question.values()) {
   lastNumbers.put(question, 0);
  }
 }

 public Question askQuestion(int location) {
  Question question = Question.getQuestion(location);
  int questionNumber = lastNumbers.get(question);
  question.setNumber(questionNumber);
  questionNumber++;
  lastNumbers.put(question, questionNumber);
  return question;
 }
}