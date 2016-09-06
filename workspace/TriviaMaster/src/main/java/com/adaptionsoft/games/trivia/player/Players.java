package com.adaptionsoft.games.trivia.player;

import java.util.ArrayList;
import java.util.List;

import com.adaptionsoft.games.trivia.exceptions.TooManyPlayersException;

public class Players {

 private List<Player> player = new ArrayList<>();
 private int currentPlayer = 0;

 public int getNumberOfPlayers() {
  return player.size();
 }

 public void add(String playerName) {
  if (getNumberOfPlayers() >= 5) {
   throw new TooManyPlayersException();
  }
  player.add(new Player(playerName));
 }

 public Player getCurrentPlayer() {
  if (player.isEmpty()) {
   return null;
  }
  return player.get(currentPlayer);
 }

 public void switchToNextPlayer() {
  if (!player.isEmpty()) {
   currentPlayer++;
   currentPlayer = currentPlayer % player.size();
  }
 }
}