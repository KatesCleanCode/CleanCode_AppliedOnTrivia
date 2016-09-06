package com.adaptionsoft.games.trivia.player;

@Deprecated
public class PlayersOld {

 private Players players = new Players();

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
  players.getCurrentPlayer().sendToPenaltyBox();
 }

 public boolean isCurrentPlayerInPenaltyBox() {
  return players.getCurrentPlayer().isInPenaltyBox();
 }

 public int getPursesOfCurrentPlayer() {
  return players.getCurrentPlayer().getPurses();
 }

 public void increasePursesOfCurrentPlayer() {
  players.getCurrentPlayer().increasePursus();
 }

 public int getLocationOfCurrentPlayer() {
  return players.getCurrentPlayer().getLocation();
 }

 public void updateLocationOfCurrentPlayer(int roll) {
  players.getCurrentPlayer().updateLocation(roll);
 }

 public void setLeavePenaltyBox(boolean leavePenaltyBox) {
  players.getCurrentPlayer().setLeavingPenaltyBox(leavePenaltyBox);
 }

 public boolean isCurrentPlayerStayingInPenaltyBox() {
  return !players.getCurrentPlayer().isLeavingPenaltyBox();
 }

 public void switchToNextPlayer() {
  players.switchToNextPlayer();
 }
}