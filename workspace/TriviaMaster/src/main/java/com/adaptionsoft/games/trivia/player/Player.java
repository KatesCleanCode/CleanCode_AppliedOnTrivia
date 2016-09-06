package com.adaptionsoft.games.trivia.player;

public class Player {

 private String playerName;
 private int location = 0;
 private int pursus = 0;
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

 public int getPursus() {
  return pursus;
 }

 public boolean isInPenaltyBox() {
  return isInPenaltyBox;
 }

 public void sendToPenaltyBox() {
  isInPenaltyBox = true;
 }

 public int updateLocation(int dieRoll) {
  location = (location + dieRoll) % 12;
  return location;
 }

 public boolean isLeavingPenaltyBox() {
  return leavingPenaltyBox;
 }

}