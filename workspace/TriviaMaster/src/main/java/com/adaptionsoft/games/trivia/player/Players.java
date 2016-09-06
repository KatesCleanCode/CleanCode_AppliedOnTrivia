package com.adaptionsoft.games.trivia.player;

import static com.adaptionsoft.games.uglytrivia.Game.MAX_NUMBER_OF_PLAYERS;

import java.util.ArrayList;

public class Players {

 private ArrayList<String> players = new ArrayList<>();
 private boolean[] inPenaltyBox = new boolean[MAX_NUMBER_OF_PLAYERS];
 private int[] purses = new int[MAX_NUMBER_OF_PLAYERS];

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

 public int getPursesOfCurrentPlayer(int currentPlayer) {
  return purses[currentPlayer];
 }

 public void initializePurses() {
  purses[getNumberOfPlayers()] = 0;
 }

 public void increasePursesOfCurrentPlayer(int currentPlayer) {
  purses[currentPlayer]++;
 }

 public void initializeLocation(int[] places) {
  places[getNumberOfPlayers()] = 0;
 }
}