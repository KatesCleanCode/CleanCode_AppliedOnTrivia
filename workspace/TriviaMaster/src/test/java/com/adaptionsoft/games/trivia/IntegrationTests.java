package com.adaptionsoft.games.trivia;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.runner.SelectClasses;
import org.junit.runner.RunWith;

import com.adaptionsoft.games.trivia.game.TriviaGameCharacterizationTest;

@RunWith(JUnitPlatform.class)
@SelectClasses(TriviaGameCharacterizationTest.class)
public class IntegrationTests {
// executes all integration tests
}