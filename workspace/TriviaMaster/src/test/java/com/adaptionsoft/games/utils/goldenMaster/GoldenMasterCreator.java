package com.adaptionsoft.games.utils.goldenMaster;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.adaptionsoft.games.trivia.runner.GameRunner;

@RunWith(JUnitPlatform.class)
public class GoldenMasterCreator {

 @Test
 @Disabled
 public void createGoldenMaster() throws IOException {
  Path path = Paths.get("src/test/resources/goldenmasterData");
  if (Files.notExists(path)) {
   Files.createDirectories(path);
  }
  for (int seed = 0; seed < 10_000; seed++) {
   ByteArrayOutputStream output = new ByteArrayOutputStream();
   System.setOut(new PrintStream(output));
   GameRunner.playGame(new Random(seed));

   String gameOutput = output.toString();
   Path pathToGoldenMaster =
    Paths.get("src/test/resources/goldenmasterData", seed + ".txt");
   Path newFile = Files.createFile(pathToGoldenMaster);
   Files.write(newFile, gameOutput.getBytes());
  }
 }
}