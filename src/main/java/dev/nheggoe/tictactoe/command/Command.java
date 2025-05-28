package dev.nheggoe.tictactoe.command;

import static java.util.Objects.requireNonNull;

import dev.nheggoe.tictactoe.model.player.Player;

public sealed interface Command {

  static Command of(String input) {
    var tokens = input.strip().split("\\s+");
    if (tokens.length != 1 && tokens.length != 3) {
      throw new IllegalArgumentException();
    }
    return switch (tokens[0]) {
      case String s when s.equalsIgnoreCase("start") ->
          new Start(
              Player.Type.valueOf(tokens[1].toUpperCase()),
              Player.Type.valueOf(tokens[2].toUpperCase()));
      case String s when s.equalsIgnoreCase("exit") -> new Exit();
      default -> throw new IllegalArgumentException();
    };
  }

  record Start(Player.Type playerOne, Player.Type playerTwo) implements Command {
    public Start {
      requireNonNull(playerOne);
      requireNonNull(playerTwo);
    }
  }

  record Exit() implements Command {}
}
