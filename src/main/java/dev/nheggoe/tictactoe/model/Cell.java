package dev.nheggoe.tictactoe.model;

import static java.util.Objects.requireNonNull;

public class Cell {

  private final Coordinate coordinate;
  private Symbol symbol;

  public Cell(Coordinate coordinate) {
    this.coordinate = requireNonNull(coordinate, "Coordinate cannot be null");
    this.symbol = Symbol.EMPTY;
  }

  public Cell(Cell other) {
    this.coordinate = other.coordinate;
    this.symbol = other.symbol;
  }

  public void setSymbol(Symbol symbol) {
    this.symbol = requireNonNull(symbol, "Cell symbol at " + coordinate + " cannot be null");
  }

  public Symbol getSymbol() {
    return symbol;
  }

  public Coordinate getCoordinate() {
    return coordinate;
  }

  public boolean isAvailable() {
    return symbol == Symbol.EMPTY;
  }

  public boolean isMatchingSymbol(Symbol other) {
    return symbol == other;
  }

  public boolean isMatchingCoordinate(Coordinate other) {
    return this.coordinate.equals(other);
  }

  @Override
  public String toString() {
    return symbol.toString();
  }

  @Override
  public final boolean equals(Object o) {
    if (!(o instanceof Cell cell)) {
      return false;
    }
    return coordinate.equals(cell.coordinate) && symbol == cell.symbol;
  }

  @Override
  public int hashCode() {
    int result = coordinate.hashCode();
    result = 31 * result + symbol.hashCode();
    return result;
  }

  public enum Symbol {
    X("X"),
    O("O"),
    EMPTY(" ");

    private final String output;

    Symbol(String output) {
      this.output = output;
    }

    @Override
    public String toString() {
      return output;
    }
  }
}
