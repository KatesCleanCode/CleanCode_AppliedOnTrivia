package com.adaptionsoft.games.trivia.player;

import java.util.ArrayList;

public class Players {
 private ArrayList<String> players = new ArrayList<>();

 public void addPlayerNameToPlayers(String playerName) {
  players.add(playerName);
 }

 public int getNumberOfPlayers() {
  return players.size();
 }

 public String getNameOfCurrentPlayer(int currentPlayer) {
  return players.get(currentPlayer);
 }
}