package com.adaptionsoft.games.trivia.player;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.equalTo;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(JUnitPlatform.class)
public class PlayerTest {

 private Player player = new Player("Martin");

 @Test
 void whenPlayerIsCreatedThenLocationIsZero() {
  assertThat(player.getLocation(), equalTo(0));
 }

 @Test
 void whenPlayerIsCreatedThenPursusIsZero() {
  assertThat(player.getPursus(), equalTo(0));
 }

 @Test
 void whenPlayerIsCreatedThenHeIsNotInPenaltyBox() {
  assertFalse(player.isInPenaltyBox());
 }

 @Test
 void whenPlayerIsCreatedThenHeIsNotLeavingPenaltyBox() {
  assertFalse(player.isLeavingPenaltyBox());
 }

 @Test
 void whenPlayerIsSentToPenaltyBoxThenHeIsInPenaltyBox() {
  player.sendToPenaltyBox();
  assertTrue(player.isInPenaltyBox());
 }

 @Test
 void whenDicesAreRolledThenLocationIsIncreasedByDieRoll() {
  player.updateLocation(11);
  assertThat(player.getLocation(), equalTo(11));
 }
}
