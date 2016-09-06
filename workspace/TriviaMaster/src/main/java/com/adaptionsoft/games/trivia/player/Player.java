package com.adaptionsoft.games.trivia.player;

public class Player {

 private String playerName;
 private int location = 0;
 private int pursus = 0;

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
}