package com.adaptionsoft.games.trivia.utils;

public final class NumberUtils {

 private NumberUtils() {
// Utility classes must not be instantiated
 }

 @SuppressWarnings("checkstyle:magicnumber")
 public static boolean isEven(int dieRoll) {
  return dieRoll % 2 == 0;
 }
}
