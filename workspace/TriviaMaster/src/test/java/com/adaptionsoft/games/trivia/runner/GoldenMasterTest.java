package com.adaptionsoft.games.trivia.runner;

import static com.adaptionsoft.games.utils.goldenMaster.GoldenMasterSupplier.getGoldenMasterForSeed;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

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
   assertEquals(goldenMaster, gameOutput);
  }
 }
}