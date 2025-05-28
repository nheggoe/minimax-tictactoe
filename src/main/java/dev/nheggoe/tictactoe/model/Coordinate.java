package dev.nheggoe.tictactoe.model;

public record Coordinate(int row, int column) {
  public Coordinate {
    if (isCoordinateOutOfBound(row) || isCoordinateOutOfBound(column)) {
      throw new IllegalArgumentException("Coordinates should be from 1 to 3!");
    }
  }

  private boolean isCoordinateOutOfBound(int cord) {
    return cord < 1 || 3 < cord;
  }

  @Override
  public String toString() {
    return "(%d, %d)".formatted(row, column);
  }
}
