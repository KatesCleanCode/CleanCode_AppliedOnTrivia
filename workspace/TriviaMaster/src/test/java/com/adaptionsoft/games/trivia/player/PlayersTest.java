package com.adaptionsoft.games.trivia.player;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.equalTo;

@RunWith(JUnitPlatform.class)
public class PlayersTest {

 @Test
 void whenPlayersIsInitializedThenNumberOfPlayersIsZero() {
  Players players = new Players();
  assertThat(players.getNumberOfPlayers(), equalTo(0));
 }
}
