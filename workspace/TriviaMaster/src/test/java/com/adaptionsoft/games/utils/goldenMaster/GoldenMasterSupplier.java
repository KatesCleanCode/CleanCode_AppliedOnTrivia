package com.adaptionsoft.games.utils.goldenMaster;

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
}