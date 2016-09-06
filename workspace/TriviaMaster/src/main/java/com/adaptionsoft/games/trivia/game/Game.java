package com.adaptionsoft.games.trivia.game;

import java.util.Random;

public interface Game {

 void addPlayer(String playerName);

 void play(Random random);

}