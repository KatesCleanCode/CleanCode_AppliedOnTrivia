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

 public void answerWasIncorrect() {
  printer.printMessage("Question was incorrectly answered");
 }

 public void category(String category) {
  printer.printMessage("The category is " + category);
 }

 public void currentPlayer(String playersName) {
  printer.printMessage(playersName + " is the current player");
 }

 public void printDieRoll(int dieRoll) {
  printer.printMessage("They have rolled a " + dieRoll);
 }
}