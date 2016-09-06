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

 public void playerSentToPenaltyBox(String playersName) {
  printer.printMessage(playersName + " was sent to the penalty box");
 }

 public void category(String category) {
  printer.printMessage("The category is " + category);
 }

 public void currentPlayer(String playersName) {
  printer.printMessage(playersName + " is the current player");
 }

 public void rolledDieRoll(int dieRoll) {
  printer.printMessage("They have rolled a " + dieRoll);
 }

 public void playerIsLeavingPenaltyBox(String playersName) {
  printer
   .printMessage(playersName + " is getting out of the penalty box");
 }

 public void playerIsStayingInPenaltyBox(String playersName) {
  printer.printMessage(
   playersName + " is not getting out of the penalty box");
 }

 public void question(String question) {
  printer.printMessage(question);
 }

 public void playersNewLocation(String playersName, int location) {
  printer
   .printMessage(playersName + "'s new location is " + location);
 }

 public void playersPurses(String playersName, int purses) {
  printer
   .printMessage(playersName + " now has " + purses + " Gold Coins.");
 }
}