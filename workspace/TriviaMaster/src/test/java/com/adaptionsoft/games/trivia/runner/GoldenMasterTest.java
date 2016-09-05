package com.adaptionsoft.games.trivia.runner;

import static com.adaptionsoft.games.utils.SystemTestHelper.generateGameOutput;
import static com.adaptionsoft.games.utils.goldenMaster.GoldenMasterSupplier.getGoldenMasterForSeed;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitPlatform.class)
public class GoldenMasterTest {

 @Test
 public void checkSystemAgainstGoldenMaster() throws Exception {
  for (int seed = 0; seed < 10_000; seed++) {
   String gameOutput = generateGameOutput(seed);
   String goldenMaster = getGoldenMasterForSeed(seed);
   assertEquals(goldenMaster, gameOutput);
  }
 }
}