package com.adaptionsoft.games.trivia.player;

import java.util.ArrayList;

import com.adaptionsoft.games.uglytrivia.Game;

public class Players {
 private ArrayList<String> players = new ArrayList<>();
 private boolean[] inPenaltyBox =
  new boolean[Game.MAX_NUMBER_OF_PLAYERS];

 public void addPlayerNameToPlayers(String playerName) {
  players.add(playerName);
 }

 public int getNumberOfPlayers() {
  return players.size();
 }

 public String getNameOfCurrentPlayer(int currentPlayer) {
  return players.get(currentPlayer);
 }

 public void initializePenaltyBox() {
  inPenaltyBox[getNumberOfPlayers()] = false;
 }

 public void sendCurrentPlayerToPenaltyBox(int currentPlayer) {
  inPenaltyBox[currentPlayer] = true;
 }

 public boolean currentPlayerIsInPenaltyBox(int currentPlayer) {
  return inPenaltyBox[currentPlayer];
 }

 public int getPursesOfCurrentPlayer(int[] purses, int currentPlayer) {
  return purses[currentPlayer];
 }

 public void initializePurses(int[] purses) {
  purses[getNumberOfPlayers()] = 0;
 }

 public void increasePursusOfCurrentPlayer(int[] purses, int currentPlayer) {
  purses[currentPlayer]++;
 }
}