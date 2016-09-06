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
  players.add(playerName);
 }

 public int getNumberOfPlayers() {
  return players.getNumberOfPlayers();
 }

 public String getNameOfCurrentPlayer() {
  return players.getCurrentPlayer().getName();
 }

 public void sendCurrentPlayerToPenaltyBox() {
  inPenaltyBox[currentPlayer] = true;
  players.getCurrentPlayer().sendToPenaltyBox();
 }

 public boolean isCurrentPlayerInPenaltyBox() {
  return players.getCurrentPlayer().isInPenaltyBox();
 }

 public int getPursesOfCurrentPlayer() {
  return players.getCurrentPlayer().getPurses();
 }

 public void increasePursesOfCurrentPlayer() {
  purses[currentPlayer]++;
  players.getCurrentPlayer().increasePursus();
 }

 public int getLocationOfCurrentPlayer() {
  return players.getCurrentPlayer().getLocation();
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
  return !players.getCurrentPlayer().isLeavingPenaltyBox();
 }

 public void switchToNextPlayer() {
  players.switchToNextPlayer();
 }
}