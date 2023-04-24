import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AppService } from '../app.service';
import { Game } from '../models';
import { BehaviorSubject } from 'rxjs';

@Component({
  selector: 'app-result',
  templateUrl: './result.component.html',
  styleUrls: ['./result.component.scss'],
})
export class ResultComponent implements OnInit {
  game$ = new BehaviorSubject<Game | undefined>(undefined);
  id = this.appService.id;

  constructor(private route: ActivatedRoute, private appService: AppService) {}

  ngOnInit() {
    this.route.params.subscribe((params) => {
      const choice = params['choice'];
      this.appService
        .onChoose(choice)
        .subscribe((game) => this.game$.next(game));
    });
  }

  ngOnDestroy() {}
}
