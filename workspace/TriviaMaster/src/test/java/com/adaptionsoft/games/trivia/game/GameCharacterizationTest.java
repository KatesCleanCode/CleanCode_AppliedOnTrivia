package com.adaptionsoft.games.trivia.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.adaptionsoft.games.testdoubles.PrinterStub;

import static org.hamcrest.CoreMatchers.equalTo;

import static org.junit.Assert.assertThat;

@RunWith(JUnitPlatform.class)
public class GameCharacterizationTest {

 private static final int SCIENCE_QUESTION_DIE_ROLL = 5;
 private static final String FIRST_PLAYER = "John";

 private Game game;
 private PrinterStub printer;

 @BeforeEach
 void setUp() {
  printer = new PrinterStub();
  game = GameFactory.createGame(printer);
 }

 @Test
 void whenGameIsCreatedThenNothingIsDisplayed() {
  assertThat(getCurrentOutput(), equalTo(""));
 }

 @Test
 void whenOnePlayerIsAddedThenHisNameAndNumberArePublished() {
  game.addPlayer(FIRST_PLAYER);

  assertThat(getCurrentOutput(), equalTo(
   FIRST_PLAYER + " was added\r\nHe/She is player number 1\r\n"));
 }

 @Test
 void whenSecondPlayerIsAddedThenHisNameAndNumberIsPublished() {
  game.addPlayer(FIRST_PLAYER);
  initializeOutput();
  game.addPlayer("Harry");

  assertThat(getCurrentOutput(),
   equalTo("Harry was added\r\nHe/She is player number 2\r\n"));
 }

 @Test
 void
  whenDicesAreRolledThenCurrentPlayerDataAndQuestionArePublished() {
  game.addPlayer(FIRST_PLAYER);
  initializeOutput();
  game.roll(SCIENCE_QUESTION_DIE_ROLL);

  assertThat(getCurrentOutput(),
   equalTo(FIRST_PLAYER
    + " is the current player\r\nThey have rolled a 5\r\n"
    + FIRST_PLAYER
    + "'s new location is 5\r\nThe category is Science\r\n"
    + "Science Question 0\r\n"));
 }

 @Test
 void whenAnswerIsCorrectThenCoinsAreGivenToPlayer() {
  game.addPlayer(FIRST_PLAYER);
  initializeOutput();
  game.wasCorrectlyAnswered();

  assertThat(getCurrentOutput(), equalTo("Answer was correct!!!!\r\n"
   + FIRST_PLAYER + " now has 1 Gold Coins.\r\n"));
 }

 @Test
 void whenAnswerIsIncorrectThenPlayerIsSentToPenaltyBox() {
  game.addPlayer(FIRST_PLAYER);
  initializeOutput();
  game.wrongAnswer();

  assertThat(getCurrentOutput(),
   equalTo("Question was incorrectly answered\r\n" + FIRST_PLAYER
    + " was sent to the penalty box\r\n"));
 }

 private String getCurrentOutput() {
  return printer.getMessages();
 }

 private void initializeOutput() {
  printer.reset();
 }
}