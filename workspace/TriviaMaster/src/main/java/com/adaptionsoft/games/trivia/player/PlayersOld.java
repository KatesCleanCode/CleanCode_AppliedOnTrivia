package com.adaptionsoft.games.trivia.player;

import java.util.ArrayList;

@Deprecated
public class PlayersOld {

 private static final int MAX_DIE_ROLL_OF_TWO_DICES = 12;
 private static final int MAX_NUMBER_OF_PLAYERS = 6;

 private ArrayList<String> playersOld = new ArrayList<>();
 private Players players = new Players();

 private boolean[] inPenaltyBox = new boolean[MAX_NUMBER_OF_PLAYERS];
 private int[] purses = new int[MAX_NUMBER_OF_PLAYERS];
 private int[] places = new int[MAX_NUMBER_OF_PLAYERS];
 private boolean isGettingOutOfPenaltyBox;
 private int currentPlayer = 0;

 public void add(String playerName) {
  playersOld.add(playerName);
  places[getNumberOfPlayers()] = 0;
  purses[getNumberOfPlayers()] = 0;
  inPenaltyBox[getNumberOfPlayers()] = false;
  players.add(playerName);
 }

 public int getNumberOfPlayers() {
  return playersOld.size();
 }

 public String getNameOfCurrentPlayer() {
  return playersOld.get(currentPlayer);
 }

 public void sendCurrentPlayerToPenaltyBox() {
  inPenaltyBox[currentPlayer] = true;
  players.getCurrentPlayer().sendToPenaltyBox();
 }

 public boolean isCurrentPlayerInPenaltyBox() {
  return inPenaltyBox[currentPlayer];
 }

 public int getPursesOfCurrentPlayer() {
  return purses[currentPlayer];
 }

 public void increasePursesOfCurrentPlayer() {
  purses[currentPlayer]++;
  players.getCurrentPlayer().increasePursus();
 }

 public int getLocationOfCurrentPlayer() {
  return places[currentPlayer];
 }

 public void updateLocationOfCurrentPlayer(int roll) {
  places[currentPlayer] = getLocationOfCurrentPlayer() + roll;
  if (getLocationOfCurrentPlayer() > Player.MAX_LOCATION) {
   places[currentPlayer] =
    getLocationOfCurrentPlayer() - MAX_DIE_ROLL_OF_TWO_DICES;
  }
  players.getCurrentPlayer().updateLocation(roll);
 }

 public void setLeavePenaltyBox(boolean leavePenaltyBox) {
  isGettingOutOfPenaltyBox = leavePenaltyBox;
  players.getCurrentPlayer().setLeavingPenaltyBox(leavePenaltyBox);
 }

 public boolean isCurrentPlayerStayingInPenaltyBox() {
  return !isGettingOutOfPenaltyBox;
 }

 public void switchToNextPlayer() {
  currentPlayer++;
  if (currentPlayer == getNumberOfPlayers()) {
   currentPlayer = 0;
  }
  players.switchToNextPlayer();
 }
}