package com.adaptionsoft.games.trivia.player;

public class Player {

 public static final int MAX_LOCATION = 11;
 private static final int MAX_DIE_ROLL_OF_TWO_DICES = 12;

 private String playerName;
 private int location = 0;
 private int purses = 0;
 private boolean isInPenaltyBox = false;
 private boolean leavingPenaltyBox = false;

 public Player(String playerName) {
  this.playerName = playerName;
 }

 public String getName() {
  return playerName;
 }

 public int getLocation() {
  return location;
 }

 public int getPurses() {
  return purses;
 }

 public boolean isInPenaltyBox() {
  return isInPenaltyBox;
 }

 public void sendToPenaltyBox() {
  isInPenaltyBox = true;
 }

 public int updateLocation(int dieRoll) {
  location = (location + dieRoll) % MAX_DIE_ROLL_OF_TWO_DICES;
  return location;
 }

 public void increasePursus() {
  purses++;
 }

 public boolean isLeavingPenaltyBox() {
  return leavingPenaltyBox;
 }

 public void setLeavingPenaltyBox(boolean leavingPenaltyBox) {
  this.leavingPenaltyBox = leavingPenaltyBox;
 }
}