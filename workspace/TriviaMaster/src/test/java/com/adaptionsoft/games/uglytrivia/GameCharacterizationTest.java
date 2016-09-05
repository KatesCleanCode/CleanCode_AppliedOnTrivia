package com.adaptionsoft.games.uglytrivia;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.equalTo;

import static org.junit.Assert.assertThat;

@RunWith(JUnitPlatform.class)
public class GameCharacterizationTest {

 private ByteArrayOutputStream output;
 private Game game;

 @BeforeEach
 void setUp() {
  initializeOutput();
  game = new Game();
 }

 @Test
 void whenGameIsCreatedThenNothingIsDisplayed() {
  assertThat(getCurrentOutput(), equalTo(""));
 }

 @Test
 void whenOnePlayerIsAddedThenHisNameAndNumberArePublished() {
  game.add("John");
  // TODO, kknaus Sep 5, 2016: Incorrect spelling should be fixed
  assertThat(getCurrentOutput(),
   equalTo("John was added\r\nThey are player number 1\r\n"));
 }

 @Test
 void
  whenSecondPlayerIsAddedThenFirstAndSecondPlayerNamesAndNumbersArePublished() {
  game.add("John");
  initializeOutput();
  game.add("Harry");

  assertThat(getCurrentOutput(),
   equalTo("Harry was added\r\nThey are player number 2\r\n"));
 }

 private String getCurrentOutput() {
  return output.toString();
 }

 private void initializeOutput() {
  output = new ByteArrayOutputStream();
  System.setOut(new PrintStream(output));
 }
}
