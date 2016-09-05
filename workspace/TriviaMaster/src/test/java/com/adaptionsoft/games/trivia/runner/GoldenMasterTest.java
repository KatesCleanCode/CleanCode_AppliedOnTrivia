package com.adaptionsoft.games.trivia.runner;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.adaptionsoft.games.utils.goldenMaster.GoldenMasterSupplier;

@RunWith(JUnitPlatform.class)
public class GoldenMasterTest {

 @Test
 public void checkSystemAgainstGoldenMaster() throws Exception {
  for (int seed = 0; seed < 10_000; seed++) {
   ByteArrayOutputStream output = new ByteArrayOutputStream();
   System.setOut(new PrintStream(output));
   GameRunner.playGame(new Random(seed));
   String gameOutput = output.toString();

   String goldenMaster = getGoldenMasterForSeed(seed);
   Assert.assertEquals(goldenMaster, gameOutput);
  }
 }

 public static String getGoldenMasterForSeed(int seed)
  throws IOException {
  Path pathToFile = GoldenMasterSupplier.getPathToGoldenMaster(seed);
  byte[] readBytes = Files.readAllBytes(pathToFile);
  return new String(readBytes);
 }
}