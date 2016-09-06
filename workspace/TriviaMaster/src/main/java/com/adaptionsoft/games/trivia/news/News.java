package com.adaptionsoft.games.trivia.news;

import com.adaptionsoft.games.trivia.printer.ConsolePrinter;
import com.adaptionsoft.games.uglytrivia.Game;

public class News {
 private ConsolePrinter printer = new ConsolePrinter();

 public void playersNumber(int playersNumber) {
  printer.printMessage("He/She is player number " + playersNumber);
 }

 public void printPlayerWasAdded(Game game, String playerName) {
  game.printer.printMessage(playerName + " was added");
 }
}