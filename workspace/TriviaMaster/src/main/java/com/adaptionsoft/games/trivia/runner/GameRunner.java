
package com.adaptionsoft.games.trivia.runner;

import java.util.Random;

import com.adaptionsoft.games.uglytrivia.Game;

public final class GameRunner {

 private static final int WRONG_ANSWER = 7;
 private static boolean notAWinner;

 private GameRunner() {
// Utility class must not be instantiated
 }

 public static void main(String[] args) {
  Random rand = new Random();
  playGame(rand);
 }

 public static void playGame(Random rand) {
  Game aGame = new Game();
  aGame.add("Chet");
  aGame.add("Pat");
  aGame.add("Sue");

  do {
   aGame.roll(rand.nextInt(5) + 1);
   if (rand.nextInt(9) == WRONG_ANSWER) {
    notAWinner = aGame.wrongAnswer();
   } else {
    notAWinner = aGame.wasCorrectlyAnswered();
   }
  } while (notAWinner);
 }
}