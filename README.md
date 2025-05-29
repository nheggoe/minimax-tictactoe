### Tic Tac Toe with Minimax Algorithm

[![CleanShot 2025-05-29 at 13:35:30@2x](https://github.com/user-attachments/assets/b52886f7-45ee-4429-9c05-bcded127c415)](https://www.youtube.com/watch?v=l-hh51ncgDI)

The [Minimax algorithm](https://www.youtube.com/watch?v=l-hh51ncgDI) is a decision-making technique used in turn-based games like Tic-Tac-Toe and Chess. It simulates all possible moves by both players and selects the move that maximizes the minimum gain for the player — assuming the opponent plays optimally. The algorithm alternates between:
- **Maximizing player** – tries to get the highest score.
- **Minimizing player** – tries to minimize the maximizing player’s score.

### How to Run

```bash
./gradlew -q
```

### Usage

This is a simple CLI game demonstrating the minimax algorithm. It accepts two commands:

- **Start a game**
  
  ```bash
  start <playerOne (X)> <playerTwo (O)>
  ```
  where valid options for each player are: `user`, `easy`, `medium`, `hard`.

  - `user` — A human player. You’ll be prompted to enter coordinates in the format `<row> <column>` with valid inputs being 1–3.
  - `easy` — AI makes random moves.
  - `medium` — AI performs static analysis:
    - Prioritizes a winning move.
    - Then tries to block the opponent.
    - Otherwise, picks a random move.
  - `hard` — AI uses the **Minimax algorithm** for optimal play.

- **Exit the game**
  
  ```bash
  exit
  ```
