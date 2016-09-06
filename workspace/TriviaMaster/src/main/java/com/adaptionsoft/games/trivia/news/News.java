package com.adaptionsoft.games.trivia.news;

import com.adaptionsoft.games.trivia.printer.ConsolePrinter;
import com.adaptionsoft.games.uglytrivia.Game;

public class News {
 private ConsolePrinter printer = new ConsolePrinter();

 public void printPlayersNumber(Game game, int playersNumber) {
  game.printer
   .printMessage("He/She is player number " + playersNumber);
 }
}