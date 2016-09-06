package com.adaptionsoft.games.utils;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;

import com.adaptionsoft.games.trivia.printer.ConsolePrinter;
import com.adaptionsoft.games.trivia.runner.GameRunner;

public final class SystemTestHelper {

 private SystemTestHelper() {
// Utility class must not be instantiated
 }

 public static String generateGameOutput(int seed) {
  ByteArrayOutputStream output = new ByteArrayOutputStream();
  System.setOut(new PrintStream(output));
  GameRunner.playGame(new Random(seed), new ConsolePrinter());
  return output.toString();
 }
}