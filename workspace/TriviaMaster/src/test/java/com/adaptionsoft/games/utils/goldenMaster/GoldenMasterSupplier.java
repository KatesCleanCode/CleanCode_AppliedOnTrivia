package com.adaptionsoft.games.utils.goldenMaster;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class GoldenMasterSupplier {

 private GoldenMasterSupplier() {
// Utility class must not be instantiated
 }

 public static Path getPathToGoldenMaster(int seed) {
  return Paths.get("src/test/resources/goldenmasterData",
   seed + ".txt");
 }

 public static String getGoldenMasterForSeed(int seed)
  throws IOException {
  Path pathToFile = getPathToGoldenMaster(seed);
  byte[] readBytes = Files.readAllBytes(pathToFile);
  return new String(readBytes);
 }
}