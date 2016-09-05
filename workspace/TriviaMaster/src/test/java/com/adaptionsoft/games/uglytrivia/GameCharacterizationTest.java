package com.adaptionsoft.games.uglytrivia;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.equalTo;

import static org.junit.Assert.assertThat;

@RunWith(JUnitPlatform.class)
public class GameCharacterizationTest {

 @Test
 void whenGameIsCreatedThenNothingIsDisplayed() {
  ByteArrayOutputStream output = new ByteArrayOutputStream();
  System.setOut(new PrintStream(output));
  Game game = new Game();

  assertThat(output.toString(), equalTo(""));
 }

 @Test
 void whenOnePlayerIsAddedThenHisNameAndNumberArePublished() {
  ByteArrayOutputStream output = new ByteArrayOutputStream();
  System.setOut(new PrintStream(output));
  Game game = new Game();
  game.add("John");
  // TODO, kknaus Sep 5, 2016: Incorrect spelling should be fixed
  assertThat(output.toString(),
   equalTo("John was added\r\nThey are player number 1\r\n"));
 }
}
