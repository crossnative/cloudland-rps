import { Component } from '@angular/core';
import { AppService } from '../app.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.scss'],
})
export class GameComponent {
  id = this.appService.id;

  constructor(private route: ActivatedRoute, private appService: AppService) {}

  ngOnInit() {
    this.route.params.subscribe((params) => {
      const game = params['game'];
      console.error(game);

      


    });
  }
}
