
package com.adaptionsoft.games.trivia.runner;

import java.util.Random;

import com.adaptionsoft.games.uglytrivia.Game;

public final class GameRunner {

 /** The player has the probability 1/(bound-1) for a wrong answer. */
 private static final int BOUND_FOR_WRONG_ANSWER = 9;
 private static final int MAX_DIE_ROLL = 5;
 private static final int MIN_DIE_ROLL = 1;
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
  aGame.addPlayer("Chet");
  aGame.addPlayer("Pat");
  aGame.addPlayer("Sue");

  do {
   aGame.roll(rand.nextInt(MAX_DIE_ROLL) + MIN_DIE_ROLL);
   if (rand.nextInt(BOUND_FOR_WRONG_ANSWER) == WRONG_ANSWER) {
    notAWinner = aGame.wrongAnswer();
   } else {
    notAWinner = aGame.wasCorrectlyAnswered();
   }
  } while (notAWinner);
 }
}