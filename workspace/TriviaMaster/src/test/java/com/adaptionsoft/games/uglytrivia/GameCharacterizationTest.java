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

 private static final String FIRST_PLAYER = "John";

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
  game.add(FIRST_PLAYER);
  // TODO, kknaus Sep 5, 2016: Incorrect spelling should be fixed
  assertThat(getCurrentOutput(), equalTo(
   FIRST_PLAYER + " was added\r\nThey are player number 1\r\n"));
 }

 @Test
 void whenSecondPlayerIsAddedThenHisNameAndNumberIsPublished() {
  game.add(FIRST_PLAYER);
  initializeOutput();
  game.add("Harry");

  assertThat(getCurrentOutput(),
   equalTo("Harry was added\r\nThey are player number 2\r\n"));
 }

 @Test
 void whenDicesAreRolledThenSomethingHappens() {
  game.add(FIRST_PLAYER);
  initializeOutput();
  game.roll(5);

  assertThat(getCurrentOutput(), equalTo("something happend"));
 }

 private String getCurrentOutput() {
  return output.toString();
 }

 private void initializeOutput() {
  output = new ByteArrayOutputStream();
  System.setOut(new PrintStream(output));
 }
}
