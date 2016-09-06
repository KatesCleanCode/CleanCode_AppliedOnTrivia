package com.adaptionsoft.games.testdoubles;

import com.adaptionsoft.games.trivia.news.Printer;

public class PrinterStub implements Printer {

 private StringBuilder messages = new StringBuilder();

 @Override
 public void printMessage(String message) {
  messages.append(message + "\r\n");
 }

 public String getMessages() {
  return messages.toString();
 }

 public void reset() {
  messages = new StringBuilder();
 }
}
