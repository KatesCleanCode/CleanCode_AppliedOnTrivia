package com.adaptionsoft.games.trivia.game;

import com.adaptionsoft.games.trivia.news.News;
import com.adaptionsoft.games.trivia.news.Printer;

public final class GameFactory {

 private GameFactory() {
// utility class cannot be instantiated
 }

 /**
  * Creates an instance of a game.
  *
  * @param printer
  *         {@link Printer} to print {@link News}
  * @return instance of a {@link TriviaGame}
  */
 public static Game createGame(Printer printer) {
  return new TriviaGame(printer);
 }
}
