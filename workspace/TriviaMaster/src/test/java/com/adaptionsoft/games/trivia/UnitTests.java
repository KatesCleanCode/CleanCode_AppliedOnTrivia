package com.adaptionsoft.games.trivia;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.runner.SelectClasses;
import org.junit.runner.RunWith;

import com.adaptionsoft.games.trivia.player.PlayerTest;
import com.adaptionsoft.games.trivia.player.PlayersTest;

@RunWith(JUnitPlatform.class)
@SelectClasses({ PlayersTest.class, PlayerTest.class })
public class UnitTests {
// executes all unit tests
}