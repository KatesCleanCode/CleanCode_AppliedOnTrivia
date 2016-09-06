package com.adaptionsoft.games.trivia.questions;

import com.adaptionsoft.games.uglytrivia.Game;

public class Questions {

 public String currentCategory(int location) {
  int questionOrder = location % Game.NUMBER_OF_QUESTION_CATEGORIES;
  if (questionOrder == 0) {
   return Game.QUESTION_CATEGORY_POP;
  }
  if (questionOrder == 1) {
   return Game.QUESTION_CATEGORY_SCIENCE;
  }
  if (questionOrder == 2) {
   return Game.QUESTION_CATEGORY_SPORTS;
  }
  return Game.QUESTION_CATEGORY_ROCK;
 }

}
