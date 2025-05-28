package dev.nheggoe.tictactoe.model.player;

import static java.util.Objects.requireNonNull;

import dev.nheggoe.tictactoe.model.Board;
import dev.nheggoe.tictactoe.model.Cell;
import dev.nheggoe.tictactoe.model.Coordinate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public abstract sealed class AI implements Player permits EasyAI, HardAI, MediumAI {

  private final Board board;

  protected AI(Board board) {
    this.board = requireNonNull(board, "Board cannot be null!");
  }

  /**
   * If the AI has two in a row and can win with one more move, it takes that move.
   *
   * @return the coordinate that will result in winning of the game
   */
  protected Optional<Coordinate> winningMove() {
    var ai = board.getNextSymbol();
    return findWinningMove(ai);
  }

  /**
   * If the opponent has two in a row and can win with one more move, the AI blocks it.
   *
   * @return the coordinate that blocks the opponent's winning coordinate
   */
  protected Optional<Coordinate> blockingMove() {
    var opponent = board.getNextSymbol() == Cell.Symbol.X ? Cell.Symbol.O : Cell.Symbol.X;
    return findWinningMove(opponent);
  }

  /**
   * Makes a random move
   *
   * @return a random available coordinate on the board
   */
  protected Coordinate fallBackMove() {
    var coordinates = new ArrayList<>(board.getEmptyCoordinates());
    Collections.shuffle(coordinates);
    return coordinates.getFirst();
  }

  private Optional<Coordinate> findWinningMove(Cell.Symbol target) {
    return Stream.of(
            getPossibleWinningCell(board.groupByRow(), target),
            getPossibleWinningCell(board.groupByColumn(), target),
            getPossibleWinningCell(board.groupByDiagonal(), target))
        .flatMap(Collection::stream)
        .map(Cell::getCoordinate)
        .findAny();
  }

  protected List<Cell> getPossibleWinningCell(Map<Integer, List<Cell>> group, Cell.Symbol target) {
    return group.values().stream()
        .filter(
            groupedElement ->
                containsEmptyCell(groupedElement) && countSymbol(groupedElement, target) == 2)
        .flatMap(Collection::stream)
        .filter(Cell::isAvailable)
        .toList();
  }

  private static boolean containsEmptyCell(List<Cell> groupedElement) {
    return groupedElement.stream().anyMatch(Cell::isAvailable);
  }

  private static long countSymbol(List<Cell> groupedElement, Cell.Symbol target) {
    return groupedElement.stream().filter(cell -> cell.isMatchingSymbol(target)).count();
  }

  protected Board getBoard() {
    return board;
  }
}
