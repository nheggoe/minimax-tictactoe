package dev.nheggoe.tictactoe.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Board {

  private static final String BOARD_TEMPLATE =
      """
      ---------
      | %s %s %s |
      | %s %s %s |
      | %s %s %s |
      ---------""";

  private final List<Cell> cells;

  public Board() {
    this.cells = generateCells();
  }

  public Board(Board other) {
    this.cells = other.cells.stream().map(Cell::new).toList();
  }

  public void setSymbolAtCoordinate(Cell.Symbol symbol, Coordinate coordinate) {
    cells.stream()
        .filter(Cell::isAvailable)
        .filter(cell -> cell.isMatchingCoordinate(coordinate))
        .findFirst()
        .orElseThrow(
            () -> new IllegalArgumentException("This cell is occupied! Choose another one!"))
        .setSymbol(symbol);
  }

  public Board simulateSetSymbolAtCoordinate(Coordinate coordinate, Cell.Symbol symbol) {
    var newBoard = new Board(this);
    newBoard.setSymbolAtCoordinate(symbol, coordinate);
    return newBoard;
  }

  public Cell.Symbol getNextSymbol() {
    var x = countSymbol(Cell.Symbol.X);
    var o = countSymbol(Cell.Symbol.O);
    if (Math.abs(x - o) > 1) {
      throw new IllegalStateException("Invalid game state!\n" + this);
    }
    return x == o ? Cell.Symbol.X : Cell.Symbol.O;
  }

  public List<Coordinate> getEmptyCoordinates() {
    return cells.stream().filter(Cell::isAvailable).map(Cell::getCoordinate).toList();
  }

  public Result computeResult() {
    var xVictory = isWinner(Cell.Symbol.X);
    var oVictory = isWinner(Cell.Symbol.O);
    if (xVictory && oVictory) {
      throw new IllegalStateException("Impossible state.");
    }
    if (xVictory || oVictory) {
      return xVictory ? Result.X_WIN : Result.O_WIN;
    }
    if (cells.stream().map(Cell::getSymbol).anyMatch(symbol -> symbol == Cell.Symbol.EMPTY)) {
      return Result.NOT_FINISHED;
    }
    return Result.DRAW;
  }

  private boolean isWinner(Cell.Symbol symbol) {
    return allMatchGroup(groupByRow(), symbol)
        || allMatchGroup(groupByColumn(), symbol)
        || allMatchGroup(groupByDiagonal(), symbol);
  }

  public Map<Integer, List<Cell>> groupByRow() {
    return cells.stream().collect(Collectors.groupingBy(cell -> cell.getCoordinate().row()));
  }

  public Map<Integer, List<Cell>> groupByColumn() {
    return cells.stream().collect(Collectors.groupingBy(cell -> cell.getCoordinate().column()));
  }

  public Map<Integer, List<Cell>> groupByDiagonal() {
    var pattern = List.of(new Coordinate(1, 3), new Coordinate(2, 2), new Coordinate(3, 1));
    var variantOne = cells.stream().filter(cell -> pattern.contains(cell.getCoordinate())).toList();

    var variantTwo =
        cells.stream()
            .filter(cell -> cell.getCoordinate().row() == cell.getCoordinate().column())
            .toList();

    return Map.of(1, variantOne, 2, variantTwo);
  }

  private boolean allMatchGroup(Map<Integer, List<Cell>> group, Cell.Symbol target) {
    return group.values().stream()
        .anyMatch(e -> e.stream().map(Cell::getSymbol).allMatch(symbol -> symbol == target));
  }

  private long countSymbol(Cell.Symbol target) {
    return cells.stream().map(Cell::getSymbol).filter(symbol -> symbol == target).count();
  }

  @Override
  public String toString() {
    return BOARD_TEMPLATE.formatted(cells.toArray());
  }

  private static List<Cell> generateCells() {
    var cells = new ArrayList<Cell>();
    for (int i = 1; i <= 3; i++) {
      for (int j = 1; j <= 3; j++) {
        cells.add(new Cell(new Coordinate(i, j)));
      }
    }
    return cells;
  }

  public enum Result {
    X_WIN("X wins"),
    O_WIN("O wins"),
    DRAW("Draw"),
    NOT_FINISHED("Not finished");

    private final String output;

    Result(String string) {
      this.output = string;
    }

    @Override
    public String toString() {
      return output;
    }
  }
}
