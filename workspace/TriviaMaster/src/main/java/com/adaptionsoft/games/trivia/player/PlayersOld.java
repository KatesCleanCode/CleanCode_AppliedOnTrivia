package com.adaptionsoft.games.trivia.player;

import java.util.ArrayList;

public class PlayersOld {

 private static final int MAX_DIE_ROLL_OF_TWO_DICES = 12;
 private static final int MAX_LOCATION = 11;
 private static final int MAX_NUMBER_OF_PLAYERS = 6;

 private ArrayList<String> players = new ArrayList<>();

 private boolean[] inPenaltyBox = new boolean[MAX_NUMBER_OF_PLAYERS];
 private int[] purses = new int[MAX_NUMBER_OF_PLAYERS];
 private int[] places = new int[MAX_NUMBER_OF_PLAYERS];
 private boolean isGettingOutOfPenaltyBox;
 private int currentPlayer = 0;

 public void add(String playerName) {
  players.add(playerName);
  initializeLocation();
  initializePurses();
  initializePenaltyBox();
 }

 public int getNumberOfPlayers() {
  return players.size();
 }

 public String getNameOfCurrentPlayer() {
  return players.get(currentPlayer);
 }

 private void initializePenaltyBox() {
  inPenaltyBox[getNumberOfPlayers()] = false;
 }

 public void sendCurrentPlayerToPenaltyBox() {
  inPenaltyBox[currentPlayer] = true;
 }

 public boolean isCurrentPlayerInPenaltyBox() {
  return inPenaltyBox[currentPlayer];
 }

 public int getPursesOfCurrentPlayer() {
  return purses[currentPlayer];
 }

 private void initializePurses() {
  purses[getNumberOfPlayers()] = 0;
 }

 public void increasePursesOfCurrentPlayer() {
  purses[currentPlayer]++;
 }

 private void initializeLocation() {
  places[getNumberOfPlayers()] = 0;
 }

 public int getLocationOfCurrentPlayer() {
  return places[currentPlayer];
 }

 private void setLocationOfCurrentPlayer(int location) {
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

 public boolean isCurrentPlayerStayingInPenaltyBox() {
  return !isGettingOutOfPenaltyBox;
 }

 public void switchToNextPlayer() {
  currentPlayer++;
  if (currentPlayer == getNumberOfPlayers()) {
   currentPlayer = 0;
  }
 }
}