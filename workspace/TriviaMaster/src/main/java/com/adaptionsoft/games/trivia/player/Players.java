package com.adaptionsoft.games.trivia.player;

import java.util.ArrayList;
import java.util.List;

import com.adaptionsoft.games.trivia.exceptions.TooManyPlayersException;

public class Players {

 /** Maximum 5 players can attend a game. */
 public static final int MAX_NUMBER_OF_PLAYERS = 5;

 private List<Player> players = new ArrayList<>();
 private int currentPlayer = 0;

 public int getNumberOfPlayers() {
  return players.size();
 }

 public void add(String playerName) {
  if (getNumberOfPlayers() >= MAX_NUMBER_OF_PLAYERS) {
   throw new TooManyPlayersException(MAX_NUMBER_OF_PLAYERS);
  }
  players.add(new Player(playerName));
 }

 public Player getCurrentPlayer() {
  if (players.isEmpty()) {
   return null;
  }
  return players.get(currentPlayer);
 }

 /**
  * Ends the turn of the current player and gives it to next player. <br>
  * Next player can be retrieved with {@link #getCurrentPlayer()} after
  * execution of this method.
  *
  * @see #getCurrentPlayer()
  */
 public void switchToNextPlayer() {
  if (!players.isEmpty()) {
   currentPlayer++;
   currentPlayer = currentPlayer % players.size();
  }
 }
}