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
  Random rand = new Random();
  playGame(rand, new ConsolePrinter());
 }

 public static void playGame(Random rand, Printer printer) {
  Game aGame = GameFactory.createGame(printer);
  aGame.addPlayer("Chet");
  aGame.addPlayer("Pat");
  aGame.addPlayer("Sue");

  aGame.play(rand);
 }
}