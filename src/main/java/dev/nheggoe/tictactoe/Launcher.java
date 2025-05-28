package dev.nheggoe.tictactoe;

import java.util.Scanner;

public class Launcher {
  public static void main(String[] args) {
    try (var scanner = new Scanner(System.in)) {
      new TicTacToeGame(scanner).run();
    }
  }
}
