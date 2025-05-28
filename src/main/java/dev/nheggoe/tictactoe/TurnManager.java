package dev.nheggoe.tictactoe;

import dev.nheggoe.tictactoe.model.player.Player;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class TurnManager {

  private final List<Player> players;
  private Iterator<Player> iterator;

  public TurnManager(Player... players) {
    this.players = new ArrayList<>();
    this.players.addAll(Arrays.asList(players));
    this.iterator = this.players.iterator();
  }

  public Player getNextPlayer() {
    if (!iterator.hasNext()) {
      iterator = players.iterator();
      if (!iterator.hasNext()) {
        throw new IllegalStateException("No players were registered");
      }
    }
    return iterator.next();
  }
}
