package com.adaptionsoft.games.trivia.news;

import com.adaptionsoft.games.trivia.printer.ConsolePrinter;

public class News {
 private ConsolePrinter printer = new ConsolePrinter();

 public void playersNumber(int playersNumber) {
  printer.printMessage("He/She is player number " + playersNumber);
 }

 public void playerAdded(String playerName) {
  printer.printMessage(playerName + " was added");
 }

 public void answerWasCorrect() {
  printer.printMessage("Answer was correct!!!!");
 }

 public void printAnswerWasIncorrect() {
  printer.printMessage("Question was incorrectly answered");
 }
}