export interface Player {
  id: string;
  choice: string;
}

export interface Result {
  winnerId: string;
  message: string;
}

export interface Game {
  player1: Player;
  player2: Player;
  result: Result;
}
