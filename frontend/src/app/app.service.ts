import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { v4 as uuidv4 } from 'uuid';
import { Game, Player } from './models';
import {
  BehaviorSubject,
  repeat,
  switchMap,
  takeWhile,
  tap,
} from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AppService {
  backendRoot = 'http://localhost:8080/api/v1';

  id: string = uuidv4();

  private state = new BehaviorSubject<
    | {
        choice?: string;
        game: Game;
      }
    | undefined
  >(undefined);
  state$ = this.state.asObservable();

  constructor(private httpClient: HttpClient) {
    const storedPlayerId = localStorage.getItem('rps-playerId');
    if (storedPlayerId) {
      this.id = storedPlayerId;
    } else {
      localStorage.setItem('rps-playerId', this.id);
    }
  }

  choose(choice: string) {
    const state = this.state.getValue();
    return this.httpClient
      .patch<Game>(
        `${this.backendRoot}/games/${state?.game.id}/players/${this.id}`,
        {
          id: this.id,
          choice: choice,
        } as Player
      )
      .pipe(tap((game) => this.nextState(game, choice)));
  }

  createAndJoinGame() {
    return this.httpClient.post<Game>(`${this.backendRoot}/games`, null).pipe(
      switchMap((game) =>
        this.httpClient.post<Game>(
          `${this.backendRoot}/games/${game.id}/players`,
          {
            id: this.id,
            name: this.id,
          } as Player
        )
      ),
      tap((game) => this.nextState(game))
    );
  }

  createAndJoinGameVsComputer() {
    return this.httpClient
      .post<Game>(`${this.backendRoot}/games?mode=PlayerVsComputer`, null)
      .pipe(
        switchMap((game) =>
          this.httpClient.post<Game>(
            `${this.backendRoot}/games/${game.id}/players`,
            {
              id: this.id,
              name: this.id,
            } as Player
          )
        ),
        tap((game) => this.nextState(game))
      );
  }
  joinGame(gameId: string) {
    return this.httpClient
      .post<Game>(`${this.backendRoot}/games/${gameId}/players`, {
        id: this.id,
        name: this.id,
      } as Player)
      .pipe(tap((game) => this.nextState(game)));
  }

  get(gameId: string) {
    this.state.next(undefined);
    return this.httpClient
      .get<Game>(`${this.backendRoot}/games/${gameId}`)
      .pipe(
        repeat({ delay: 1000 }),
        takeWhile((game) => game.gameState !== 'DONE', true),
        tap((game) => this.nextState(game))
      );
  }

  onChoose(choice: string) {
    return this.httpClient.post<Game>(`${this.backendRoot}/play`, {
      player1: {
        id: this.id,
        choice: choice,
      },
    });
  }

  updateBackendUrl (newBackendUrl: string) {
    this.backendRoot = newBackendUrl;
  }

  private nextState(game: Game, choice?: string) {
    let newState: {
      choice?: string;
      game: Game;
    };
    if (this.state.getValue()) {
      newState = {
        ...this.state.getValue(),
        game,
      };
      if (choice) {
        newState.choice = choice;
      }
    } else {
      newState = {
        game,
        choice,
      };
    }

    this.state.next(newState);
  }
}
