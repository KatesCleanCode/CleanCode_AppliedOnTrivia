package com.adaptionsoft.games.trivia.player;

import java.util.ArrayList;

import com.adaptionsoft.games.uglytrivia.Game;

public class Players {

 private static final int MAX_DIE_ROLL_OF_TWO_DICES = 12;
 private static final int MAX_LOCATION = 11;
 private static final int MAX_NUMBER_OF_PLAYERS = 6;

 private ArrayList<String> players = new ArrayList<>();
 private boolean[] inPenaltyBox = new boolean[MAX_NUMBER_OF_PLAYERS];
 private int[] purses = new int[MAX_NUMBER_OF_PLAYERS];
 private int[] places = new int[MAX_NUMBER_OF_PLAYERS];
 private boolean isGettingOutOfPenaltyBox;

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

 public void initializeLocation() {
  places[getNumberOfPlayers()] = 0;
 }

 public int getLocationOfCurrentPlayer(int currentPlayer) {
  return places[currentPlayer];
 }

 public void setLocationOfCurrentPlayer(int currentPlayer,
  int location) {
  places[currentPlayer] = location;
 }

 public void updateLocationOfCurrentPlayer(int roll,
  int currentPlayer) {
  setLocationOfCurrentPlayer(currentPlayer,
   getLocationOfCurrentPlayer(currentPlayer) + roll);
  if (getLocationOfCurrentPlayer(currentPlayer) > MAX_LOCATION) {
   setLocationOfCurrentPlayer(currentPlayer,
    getLocationOfCurrentPlayer(currentPlayer)
     - MAX_DIE_ROLL_OF_TWO_DICES);
  }
 }

 public void setLeavePenaltyBox(Game game, boolean leavePenaltyBox) {
  game.isGettingOutOfPenaltyBox = leavePenaltyBox;
 }

 public boolean currentPlayerIsNotLeavingPenaltyBox(Game game) {
  return !game.isGettingOutOfPenaltyBox;
 }
}