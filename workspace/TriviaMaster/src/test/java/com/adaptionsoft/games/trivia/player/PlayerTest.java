package com.adaptionsoft.games.trivia.player;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.equalTo;

import static org.junit.Assert.assertThat;

@RunWith(JUnitPlatform.class)
public class PlayerTest {

 @Test
 void whenPlayerIsCreatedThenLocationIsZero() {
  Player player = new Player("Martin");
  assertThat(player.getLocation(), equalTo(0));
 }

 @Test
 void whenPlayerIsCreatedThenPursusIsZero() {
  Player player = new Player("Martin");
  assertThat(player.getPursus(), equalTo(0));
 }
}
