package com.adaptionsoft.games.trivia.game;

import com.adaptionsoft.games.trivia.news.Printer;
import com.adaptionsoft.games.uglytrivia.Game;

public final class GameFactory {

 private GameFactory() {
// utility class cannot be instantiated
 }

 public static Game createGame(Printer printer) {
  return new Game(printer);
 }
}
