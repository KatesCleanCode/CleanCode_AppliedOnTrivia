package com.adaptionsoft.games.utils;

import java.util.Random;

import com.adaptionsoft.games.testdoubles.PrinterStub;
import com.adaptionsoft.games.trivia.runner.GameRunner;

public final class SystemTestHelper {

 private SystemTestHelper() {
// Utility class must not be instantiated
 }

 public static String generateGameOutput(int seed) {
  PrinterStub printer = new PrinterStub();
  GameRunner.playGame(new Random(seed), printer);
  return printer.getMessages();
 }
}