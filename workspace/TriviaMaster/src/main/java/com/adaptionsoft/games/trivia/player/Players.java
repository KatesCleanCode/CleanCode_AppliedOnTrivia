package com.adaptionsoft.games.trivia.player;

import java.util.ArrayList;
import java.util.List;

public class Players {

 private List<Player> player = new ArrayList<>();

 public int getNumberOfPlayers() {
  return player.size();
 }

 public void add(String playerName) {
  player.add(new Player());
 }

 public Player getCurrentPlayer() {
  if (player.isEmpty()) {
   return null;
  }
  return new Player();
 }
}