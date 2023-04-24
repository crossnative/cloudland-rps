import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { v4 as uuidv4 } from 'uuid';
import { Game } from './models';
import { BehaviorSubject, delay } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AppService {
  id: string = uuidv4();

  constructor(private httpClient: HttpClient) {}

  onChoose(choice: string) {
    return this.httpClient.post<Game>('http://localhost:4400/v1/play', {
      player1: {
        id: this.id,
        choice: choice,
      },
    });
  }
}
