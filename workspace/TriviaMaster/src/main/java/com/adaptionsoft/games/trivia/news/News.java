package com.adaptionsoft.games.trivia.news;

import com.adaptionsoft.games.trivia.printer.ConsolePrinter;
import com.adaptionsoft.games.uglytrivia.Game;

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

 public void printCurrentCategory(Game game, String category) {
  game.printer.printMessage("The category is " + category);
 }
}