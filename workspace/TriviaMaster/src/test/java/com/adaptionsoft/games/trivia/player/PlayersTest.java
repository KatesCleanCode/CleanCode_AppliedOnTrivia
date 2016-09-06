package com.adaptionsoft.games.trivia.player;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.equalTo;

import static org.junit.Assert.assertThat;

@RunWith(JUnitPlatform.class)
public class PlayersTest {

 @Test
 void whenPlayersIsInitializedThenNumberOfPlayersIsZero() {
  Players players = new Players();
  assertThat(players.getNumberOfPlayers(), equalTo(0));
 }

 @Test
 void whenOnePlayerIsAddedThenNumberOfPlayersIncreaseByOne() {
  Players players = new Players();
  players.add("Sue");

  assertThat(players.getNumberOfPlayers(), equalTo(1));
 }
}
