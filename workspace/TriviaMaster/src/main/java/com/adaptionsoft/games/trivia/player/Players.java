package com.adaptionsoft.games.trivia.player;

import java.util.ArrayList;
import java.util.List;

public class Players {

 private List<String> playerNames = new ArrayList<>();

 public int getNumberOfPlayers() {
  return playerNames.size();
 }

 public void add(String string) {
  playerNames.add(string);
 }
}