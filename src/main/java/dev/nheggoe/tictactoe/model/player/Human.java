package dev.nheggoe.tictactoe.model.player;

import static java.util.Objects.requireNonNull;

import dev.nheggoe.tictactoe.model.Coordinate;
import java.util.Scanner;

public final class Human implements Player {

  private final Scanner scanner;

  public Human(Scanner scanner) {
    this.scanner = requireNonNull(scanner, "Scanner cannot be null!");
  }

  @Override
  public Coordinate nextRound() {
    System.out.print("Enter the coordinates: ");
    var tokens = scanner.nextLine().strip().split("\\s+", 2);
    return new Coordinate(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));
  }
}
