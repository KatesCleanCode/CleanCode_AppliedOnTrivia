package com.adaptionsoft.games.trivia.runner;

import java.util.Random;

import com.adaptionsoft.games.trivia.printer.ConsolePrinter;
import com.adaptionsoft.games.uglytrivia.Game;

public final class GameRunner {

 private GameRunner() {
// Utility class must not be instantiated
 }

 public static void main(String[] args) {
  Random rand = new Random();
  playGame(rand);
 }

 public static void playGame(Random rand) {
  Game aGame = new Game(new ConsolePrinter());
  aGame.addPlayer("Chet");
  aGame.addPlayer("Pat");
  aGame.addPlayer("Sue");
  aGame.play(rand);
 }
}