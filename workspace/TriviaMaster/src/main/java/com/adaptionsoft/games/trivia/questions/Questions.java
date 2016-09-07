package com.adaptionsoft.games.trivia.questions;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Questions {

 private static final int MAX_NUMBER_OF_QUESTIONS = 50;

 private LinkedList<String> popQuestions = new LinkedList<>();
 private LinkedList<String> scienceQuestions = new LinkedList<>();
 private LinkedList<String> sportsQuestions = new LinkedList<>();
 private LinkedList<String> rockQuestions = new LinkedList<>();

 private Map<Question, Integer> lastNumbers = new HashMap<>();

 public Questions() {
  for (Question question : Question.values()) {
   lastNumbers.put(question, 0);
  }
  initializeQuestions();
 }

 public String askQuestion(int location) {
  Question question = Question.getQuestion(location);
  int questionNumber = lastNumbers.get(question);
  question.setNumber(questionNumber);
  lastNumbers.put(question, questionNumber + 1);
  return question.getName();
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