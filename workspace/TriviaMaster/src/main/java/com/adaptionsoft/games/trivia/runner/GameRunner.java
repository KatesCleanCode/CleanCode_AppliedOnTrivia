package com.adaptionsoft.games.trivia.runner;

import java.util.Random;

import com.adaptionsoft.games.trivia.game.Game;
import com.adaptionsoft.games.trivia.game.GameFactory;
import com.adaptionsoft.games.trivia.news.Printer;
import com.adaptionsoft.games.trivia.printer.ConsolePrinter;

public final class GameRunner {

 private GameRunner() {
// Utility class must not be instantiated
 }

 public static void main(String[] args) {
  playGame(new Random(), new ConsolePrinter());
 }

 public static void playGame(Random random, Printer printer) {
  Game game = GameFactory.createGame(printer);
  game.addPlayer("Chet");
  game.addPlayer("Pat");
  game.addPlayer("Sue");

  game.play(random);
 }
}