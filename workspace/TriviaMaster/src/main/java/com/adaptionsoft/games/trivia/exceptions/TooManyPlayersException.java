package com.adaptionsoft.games.trivia.exceptions;

public class TooManyPlayersException extends RuntimeException {

 private static final long serialVersionUID = 5057418026235332544L;

 public TooManyPlayersException(int maxNumberOfPlayers) {
  super("The number of players is limited to " + maxNumberOfPlayers);
 }
}