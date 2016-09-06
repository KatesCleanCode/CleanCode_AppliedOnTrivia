package com.adaptionsoft.games.trivia.player;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.adaptionsoft.games.trivia.exceptions.TooManyPlayersException;

import static org.hamcrest.CoreMatchers.equalTo;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

@RunWith(JUnitPlatform.class)
public class PlayersTest {

 private static final String SECOND_PLAYER = "Brown";
 private static final String FIRST_PLAYER = "Sue";

 private Players players = new Players();

 @Test
 void whenPlayersIsInitializedThenNumberOfPlayersIsZero() {
  assertThat(players.getNumberOfPlayers(), equalTo(0));
 }

 @Test
 void whenOnePlayerIsAddedThenNumberOfPlayersIncreaseByOne() {
  players.add(FIRST_PLAYER);

  assertThat(players.getNumberOfPlayers(), equalTo(1));
 }

 @Test
 void numberOfPlayersIsLimitedToFive() {
  players.add(FIRST_PLAYER);
  players.add(SECOND_PLAYER);
  players.add("Third");
  players.add("Fourth");
  players.add("Fifth");

  try {
   players.add("Sixth");
   fail("There must be maximum 5 players");
  } catch (TooManyPlayersException ex) {
  }
 }

 @Test
 void whenPlayersIsInitializedThenCurrentPlayerIsNullPlayer() {
  Player currentPlayer = players.getCurrentPlayer();
  assertNull(currentPlayer);
 }

 @Test
 void whenOnePlayerIsAddedThenCurrentPlayerIsFirstPlayer() {
  players.add(FIRST_PLAYER);

  Player currentPlayer = players.getCurrentPlayer();
  assertThat(currentPlayer.getName(), equalTo(FIRST_PLAYER));
 }

 @Test
 void
  givenTwoPlayerWhenSwitchToNextPlayerThenCurrentPlayerIsSecondPlayer() {
  players.add(FIRST_PLAYER);
  players.add(SECOND_PLAYER);

  players.switchToNextPlayer();
  Player currentPlayer = players.getCurrentPlayer();
  assertThat(currentPlayer.getName(), equalTo(SECOND_PLAYER));
 }

 @Test
 void
  givenTwoPlayerWhenTwoTimesSwitchToNextPlayerThenCurrentPlayerIsFirstPlayer() {
  players.add(FIRST_PLAYER);
  players.add(SECOND_PLAYER);

  players.switchToNextPlayer();
  players.switchToNextPlayer();
  Player currentPlayer = players.getCurrentPlayer();
  assertThat(currentPlayer.getName(), equalTo(FIRST_PLAYER));
 }

 @Test
 void
  givenZeroPlayerWhenSwitchToNextPlayerThenCurrentPlayerIsNullPlayer() {
  players.switchToNextPlayer();
  Player currentPlayer = players.getCurrentPlayer();
  assertNull(currentPlayer);
 }
}
