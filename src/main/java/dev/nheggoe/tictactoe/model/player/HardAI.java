package dev.nheggoe.tictactoe.model.player;

import dev.nheggoe.tictactoe.model.Board;
import dev.nheggoe.tictactoe.model.Cell;
import dev.nheggoe.tictactoe.model.Coordinate;

public final class HardAI extends AI {

  private Cell.Symbol ai;
  private Cell.Symbol opp;

  public HardAI(Board board) {
    super(board);
  }

  @Override
  public Coordinate nextRound() {
    System.out.println("Making move level \"hard\"");
    this.ai = getBoard().getNextSymbol();
    this.opp = (ai == Cell.Symbol.X) ? Cell.Symbol.O : Cell.Symbol.X;
    var empty = getBoard().getEmptyCoordinates();
    var board = getBoard();
    var maxList =
        empty.stream()
            .map(coordinate -> minimax(board.simulateSetSymbolAtCoordinate(coordinate, ai), true))
            .toList();
    var maxIndex = maxList.indexOf(maxList.stream().reduce(Integer::max).orElseThrow());
    return empty.get(maxIndex);
  }

  private Integer minimax(Board board, boolean isMaxing) {
    var result = board.computeResult();
    if (result != Board.Result.NOT_FINISHED) {
      return switch (result) {
        case X_WIN -> ai == Cell.Symbol.X ? +1 : -1;
        case O_WIN -> ai == Cell.Symbol.O ? +1 : -1;
        case DRAW -> 0;
        default -> throw new IllegalStateException("All case should be covered");
      };
    }
    var empty = board.getEmptyCoordinates();
    if (isMaxing) {
      return empty.stream()
          .map(coordinate -> minimax(board.simulateSetSymbolAtCoordinate(coordinate, opp), false))
          .reduce(Integer::min)
          .orElseThrow();
    } else {
      return empty.stream()
          .map(coordinate -> minimax(board.simulateSetSymbolAtCoordinate(coordinate, ai), true))
          .reduce(Integer::max)
          .orElseThrow();
    }
  }
}
