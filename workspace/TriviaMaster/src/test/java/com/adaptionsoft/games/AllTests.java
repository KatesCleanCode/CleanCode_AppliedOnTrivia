package com.adaptionsoft.games;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.adaptionsoft.games.trivia.IntegrationTests;
import com.adaptionsoft.games.trivia.SystemTests;
import com.adaptionsoft.games.trivia.UnitTests;

@RunWith(Suite.class)
@SuiteClasses({ IntegrationTests.class, SystemTests.class,
 UnitTests.class })
public class AllTests {
}