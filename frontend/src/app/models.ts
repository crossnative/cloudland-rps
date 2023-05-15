export interface Player {
  id: string;
  name: string;
  choice: string;
}

export interface Result {
  winnerId: string;
  message: string;
}

export interface Game {
  id: string;
  gameState: string;
  player1: Player;
  player2: Player;
  result: Result;
}
