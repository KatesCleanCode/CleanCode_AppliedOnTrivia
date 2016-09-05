package com.adaptionsoft.games.utils.goldenMaster;

import static com.adaptionsoft.games.utils.SystemTestHelper.generateGameOutput;
import static com.adaptionsoft.games.utils.goldenMaster.GoldenMasterSupplier.getPathToGoldenMaster;
import static com.adaptionsoft.games.utils.goldenMaster.GoldenMasterSupplier.getPathToGoldenMasterDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
public class GoldenMasterCreator {

 public static final int NUMBER_OF_GM_TESTS = 10_000;

 @Test
 @Disabled
 public void createGoldenMaster() throws IOException {
  createGMFileDirectory();

  for (int seed = 0; seed < NUMBER_OF_GM_TESTS; seed++) {
   String gameOutput = generateGameOutput(seed);
   Path pathToGoldenMaster = getPathToGoldenMaster(seed);
   Path newFile = Files.createFile(pathToGoldenMaster);
   Files.write(newFile, gameOutput.getBytes());
  }
 }

 private void createGMFileDirectory() throws IOException {
  Path path = getPathToGoldenMasterDir();
  if (Files.notExists(path)) {
   Files.createDirectories(path);
  }
 }
}