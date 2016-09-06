package com.adaptionsoft.games.trivia.game;

import java.util.Random;

import com.adaptionsoft.games.trivia.exceptions.TooManyPlayersException;
import com.adaptionsoft.games.trivia.player.Players;

public interface Game {

 /**
  * Adds a player to the game. The number of players is restricted to maximum
  * {@link Players#MAX_NUMBER_OF_PLAYERS}
  *
  * @param playerName
  *         The name of the player to add.
  * @throws TooManyPlayersException
  *          if maximum number of players is exceed.
  */
 void addPlayer(String playerName);

 /**
  * Starts to play the game.
  *
  * @param random
  *         Random for randomized behavior during game execution.
  */
 void play(Random random);
}