package com.adaptionsoft.games.trivia.player;

import java.util.ArrayList;
import java.util.List;

import com.adaptionsoft.games.trivia.exceptions.TooManyPlayersException;

public class Players {

 /** Maximum 5 players can attend a game. */
 public static final int MAX_NUMBER_OF_PLAYERS = 5;

 private List<Player> player = new ArrayList<>();
 private int currentPlayer = 0;

 public int getNumberOfPlayers() {
  return player.size();
 }

 public void add(String playerName) {
  if (getNumberOfPlayers() >= MAX_NUMBER_OF_PLAYERS) {
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