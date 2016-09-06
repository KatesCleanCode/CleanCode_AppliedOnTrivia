package com.adaptionsoft.games.trivia.player;

import java.util.ArrayList;
import java.util.List;

public class Players {

 private List<Player> player = new ArrayList<>();
 private int currentPlayer = 0;

 public int getNumberOfPlayers() {
  return player.size();
 }

 public void add(String playerName) {
  player.add(new Player(playerName));
 }

 public Player getCurrentPlayer() {
  if (player.isEmpty()) {
   return null;
  }
  return player.get(currentPlayer);
 }

 public void switchToNextPlayer() {
  currentPlayer++;
 }
}