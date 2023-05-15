import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Game, Player } from '../models';
import { switchMap } from 'rxjs';
import { AppService } from '../app.service';

@Component({
  selector: 'app-landing',
  templateUrl: './landing.component.html',
  styleUrls: ['./landing.component.scss'],
})
export class LandingComponent {
  id = this.appService.id;

  gameId: string = '';

  constructor(
    private router: Router,
    private httpClient: HttpClient,
    private appService: AppService
  ) {}

  onNewGame() {
    this.appService
      .createAndJoinGame()
      .subscribe((game) => this.router.navigate(['/game', game.id]));
  }

  onJoinGame() {
    if (!this.gameId) {
      return;
    }

    this.appService
      .joinGame(this.gameId)
      .subscribe((game) => this.router.navigate(['/game', game.id]));
  }
}
