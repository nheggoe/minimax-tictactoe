package dev.nheggoe.tictactoe.model.player;

import dev.nheggoe.tictactoe.model.Board;
import dev.nheggoe.tictactoe.model.Coordinate;

public final class MediumAI extends AI {

  public MediumAI(Board board) {
    super(board);
  }

  @Override
  public Coordinate nextRound() {
    System.out.println("Making move level \"medium\"");
    return winningMove().orElseGet(() -> blockingMove().orElseGet(this::fallBackMove));
  }
}
