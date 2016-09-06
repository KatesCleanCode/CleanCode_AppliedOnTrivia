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

 public void initializePenaltyBox(boolean[] inPenaltyBox) {
  inPenaltyBox[getNumberOfPlayers()] = false;
 }

 public void sendCurrentPlayerToPenaltyBox(boolean[] inPenaltyBox, int currentPlayer) {
  inPenaltyBox[currentPlayer] = true;
 }

 public boolean currentPlayerIsInPenaltyBox(boolean[] inPenaltyBox, int currentPlayer) {
  return inPenaltyBox[currentPlayer];
 }
}