package dev.nheggoe.tictactoe;

import static java.util.Objects.requireNonNull;

import dev.nheggoe.tictactoe.command.Command;
import dev.nheggoe.tictactoe.model.Board;
import dev.nheggoe.tictactoe.model.player.EasyAI;
import dev.nheggoe.tictactoe.model.player.HardAI;
import dev.nheggoe.tictactoe.model.player.Human;
import dev.nheggoe.tictactoe.model.player.MediumAI;
import dev.nheggoe.tictactoe.model.player.Player;
import java.util.Scanner;

public class TicTacToeGame {

  private final Scanner scanner;
  private TurnManager turnManager;
  private Board board;

  public TicTacToeGame(Scanner scanner) {
    this.scanner = requireNonNull(scanner, "Scanner cannot be null!");
    this.board = new Board();
    this.turnManager = new TurnManager(new Human(scanner), new EasyAI(board));
  }

  public void run() {
    boolean running = true;
    while (running) {
      var command = parseCommand();
      switch (command) {
        case Command.Start startCommand -> {
          initialize(startCommand);
          engine();
        }
        case Command.Exit ignored -> running = false;
      }
    }
  }

  public void initialize(Command.Start startCommand) {
    this.board = new Board();
    this.turnManager =
        new TurnManager(
            createPlayer(startCommand.playerOne()), createPlayer(startCommand.playerTwo()));
  }

  private Command parseCommand() {
    while (true) {
      try {
        System.out.print("Input command: ");
        return Command.of(scanner.nextLine());
      } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
        System.out.println("Bad parameters!");
      }
    }
  }

  private void engine() {
    System.out.println(board);
    var player = turnManager.getNextPlayer();
    boolean validInput = false;
    boolean isEnded = false;
    while (!validInput || !isEnded) {
      try {
        board.setSymbolAtCoordinate(board.getNextSymbol(), player.nextRound());
        validInput = true;
        isEnded = board.computeResult() != Board.Result.NOT_FINISHED;
        player = turnManager.getNextPlayer();
        System.out.println(board);
      } catch (NumberFormatException e) {
        System.out.println("You should enter numbers!");
      } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage());
      }
    }
    System.out.println(board.computeResult());
  }

  private Player createPlayer(Player.Type type) {
    return switch (type) {
      case USER -> new Human(scanner);
      case EASY -> new EasyAI(board);
      case MEDIUM -> new MediumAI(board);
      case HARD -> new HardAI(board);
    };
  }
}
