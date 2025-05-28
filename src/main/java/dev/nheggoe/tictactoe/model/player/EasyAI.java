package dev.nheggoe.tictactoe.model.player;

import dev.nheggoe.tictactoe.model.Board;
import dev.nheggoe.tictactoe.model.Coordinate;

public final class EasyAI extends AI {

  public EasyAI(Board board) {
    super(board);
  }

  @Override
  public Coordinate nextRound() {
    System.out.println("Making move level \"easy\"");
    return fallBackMove();
  }
}
