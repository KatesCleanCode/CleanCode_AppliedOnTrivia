package com.adaptionsoft.games.trivia.news;

import com.adaptionsoft.games.trivia.questions.Question;

public class News {

 private Printer printer;

 public News(Printer printer) {
  this.printer = printer;
 }

 public void playerAdded(String playerName, int playersNumber) {
  printer.printMessage(playerName + " was added");
  printer.printMessage("He/She is player number " + playersNumber);
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

 public void askedQuestion(Question question) {
  printer.printMessage("The category is " + question.getCategory());
  printer.printMessage(question.getName());
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