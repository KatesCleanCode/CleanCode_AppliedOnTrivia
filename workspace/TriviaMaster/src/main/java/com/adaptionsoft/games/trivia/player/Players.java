package com.adaptionsoft.games.trivia.player;

import java.util.ArrayList;
import java.util.List;

public class Players {

 private List<String> playerNames = new ArrayList<>();
 private List<Player> player = new ArrayList<>();

 public int getNumberOfPlayers() {
  return playerNames.size();
 }

 public void add(String playerName) {
  playerNames.add(playerName);
 }

 public Player getCurrentPlayer() {
  if (playerNames.isEmpty()) {
   return null;
  }
  return new Player();
 }
}