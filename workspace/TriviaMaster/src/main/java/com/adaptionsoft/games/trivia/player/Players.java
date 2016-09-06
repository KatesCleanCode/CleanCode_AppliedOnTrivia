package com.adaptionsoft.games.trivia.player;

import java.util.ArrayList;

public class Players {

 private static final int MAX_DIE_ROLL_OF_TWO_DICES = 12;
 private static final int MAX_LOCATION = 11;
 private static final int MAX_NUMBER_OF_PLAYERS = 6;

 private ArrayList<String> players = new ArrayList<>();
 private boolean[] inPenaltyBox = new boolean[MAX_NUMBER_OF_PLAYERS];
 private int[] purses = new int[MAX_NUMBER_OF_PLAYERS];
 private int[] places = new int[MAX_NUMBER_OF_PLAYERS];
 private boolean isGettingOutOfPenaltyBox;
 private int currentPlayer = 0;

 public void addPlayerNameToPlayers(String playerName) {
  players.add(playerName);
 }

 public int getNumberOfPlayers() {
  return players.size();
 }

 public String getNameOfCurrentPlayer() {
  return players.get(currentPlayer);
 }

 public void initializePenaltyBox() {
  inPenaltyBox[getNumberOfPlayers()] = false;
 }

 public void sendCurrentPlayerToPenaltyBox() {
  inPenaltyBox[currentPlayer] = true;
 }

 public boolean currentPlayerIsInPenaltyBox() {
  return inPenaltyBox[currentPlayer];
 }

 public int getPursesOfCurrentPlayer() {
  return purses[currentPlayer];
 }

 public void initializePurses() {
  purses[getNumberOfPlayers()] = 0;
 }

 public void increasePursesOfCurrentPlayer() {
  purses[currentPlayer]++;
 }

 public void initializeLocation() {
  places[getNumberOfPlayers()] = 0;
 }

 public int getLocationOfCurrentPlayer() {
  return places[currentPlayer];
 }

 public void setLocationOfCurrentPlayer(int location) {
  places[currentPlayer] = location;
 }

 public void updateLocationOfCurrentPlayer(int roll) {
  setLocationOfCurrentPlayer(getLocationOfCurrentPlayer() + roll);
  if (getLocationOfCurrentPlayer() > MAX_LOCATION) {
   setLocationOfCurrentPlayer(
    getLocationOfCurrentPlayer() - MAX_DIE_ROLL_OF_TWO_DICES);
  }
 }

 public void setLeavePenaltyBox(boolean leavePenaltyBox) {
  isGettingOutOfPenaltyBox = leavePenaltyBox;
 }

 public boolean currentPlayerIsNotLeavingPenaltyBox() {
  return !isGettingOutOfPenaltyBox;
 }

 public void switchToNextPlayer() {
  currentPlayer++;
  if (currentPlayer == getNumberOfPlayers()) {
   currentPlayer = 0;
  }
 }
}