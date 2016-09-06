package com.adaptionsoft.games.trivia.printer;

import com.adaptionsoft.games.trivia.news.Printer;

public class ConsolePrinter implements Printer {

 @Override
 public void printMessage(String message) {
  System.out.println(message);
 }
}