package com.adaptionsoft.games.trivia.player;

import java.util.ArrayList;

public class Players {

 public void addPlayerNameToPlayers(String playerName, ArrayList<String> players) {
  players.add(playerName);
 }

 public int getNumberOfPlayers(ArrayList<String> players) {
  return players.size();
 }

}
