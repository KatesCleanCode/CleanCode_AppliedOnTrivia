package com.adaptionsoft.games.trivia.runner;

import static com.adaptionsoft.games.utils.SystemTestHelper.generateGameOutput;
import static com.adaptionsoft.games.utils.goldenMaster.GoldenMasterSupplier.getGoldenMasterForSeed;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitPlatform.class)
public class GoldenMasterTest {

 public static final int NUMBER_OF_GM_TESTS = 10_000;

 @Test
 public void checkSystemAgainstGoldenMaster() throws Exception {
  for (int seed = 0; seed < NUMBER_OF_GM_TESTS; seed++) {
   String gameOutput = generateGameOutput(seed);
   String goldenMaster = getGoldenMasterForSeed(seed);
   assertEquals(goldenMaster, gameOutput);
  }
 }
}