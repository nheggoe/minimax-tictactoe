package dev.nheggoe.tictactoe.model.player;

import dev.nheggoe.tictactoe.model.Coordinate;

public sealed interface Player permits AI, Human {

  Coordinate nextRound();

  enum Type {
    USER,
    EASY,
    MEDIUM,
    HARD
  }
}
