package com.adaptionsoft.games.trivia.player;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.equalTo;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

@RunWith(JUnitPlatform.class)
public class PlayersTest {

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
}
