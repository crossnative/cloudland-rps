import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AppService } from '../app.service';

@Component({
  selector: 'app-play',
  templateUrl: './play.component.html',
})
export class PlayComponent {
  id = this.appService.id;

  backendUrl: string = this.appService.backendRoot;
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

  onNewGameVsComputer() {
    this.appService
      .createAndJoinGameVsComputer()
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

  onBackendUrlUpdate(newBackendUrl: string) {
    this.appService.updateBackendUrl(newBackendUrl);
  }
}
