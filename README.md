### Tic Tac Toe with minimax algorithm
[![CleanShot 2025-05-29 at 13 35 30@2x](https://github.com/user-attachments/assets/b52886f7-45ee-4429-9c05-bcded127c415)](https://www.youtube.com/watch?v=l-hh51ncgDI)
[Minimax](https://www.youtube.com/watch?v=l-hh51ncgDI) is a decision-making algorithm used in turn-based games like Tic-Tac-Toe or Chess. It simulates all possible moves by both players and chooses the move that maximizes the player’s minimum gain — assuming the opponent plays optimally. The algorithm alternates between the maximizing player (who wants the highest score) and the minimizing player (who wants to lower the opponent’s score), evaluating game states recursively to find the best move.

### How to run the application
```bash
./gradlew -q
```


### Usage
This is a very simple CLI game used to implement the minimax algorithm, it takes to commands:
`start` and `exit`

```bash
start <playerOne> <playerTwo>
```
where valid optoins are `[user, easy, medium, hard]`
- `user` ––– Human player, you will be prompt to enter the coordinate `<row> <column>` where valid options are `[1, 2, 3]`
- `easy` ––– The AI place a random moves each time
- `medium` ––– The AI performs static analysis each round, it will go with the winning move first -> blocking move -> fallback (random move)
- `hard` ––– The AI uses the minimax algorithm to always choose the best coordinate each round

```bash
exit
```
Exits the game
