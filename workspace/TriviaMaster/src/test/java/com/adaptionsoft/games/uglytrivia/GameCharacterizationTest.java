package com.adaptionsoft.games.uglytrivia;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
public class GameCharacterizationTest {

 @Test
 void whenOnePlayerIsAddedThenHisNameAndNumberArePublished() {
  ByteArrayOutputStream output = new ByteArrayOutputStream();
  System.setOut(new PrintStream(output));
  Game game = new Game();
  game.add("John");

  Assert.assertThat(output.toString(), CoreMatchers
   .equalTo("John was added\r\nThey are player number 1\r\n"));
 }
}
