import { Component } from '@angular/core';
import { AppService } from '../app.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
})
export class GameComponent {
  id = this.appService.id;

  state$ = this.appService.state$;

  constructor(private route: ActivatedRoute, private appService: AppService) {}

  ngOnInit() {
    this.route.params.subscribe((params) => {
      const gameId = params['gameId'];
      this.appService.get(gameId).subscribe();
    });
  }

  onChoose(choice: string) {
    this.appService.choose(choice).subscribe();
  }
}
