import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AppService } from '../app.service';
import { Game } from '../models';
import { BehaviorSubject, Subject } from 'rxjs';

@Component({
  selector: 'app-result',
  templateUrl: './result.component.html',
})
export class ResultComponent implements OnInit, OnDestroy {
  private readonly unsubscribe$: Subject<void> = new Subject();

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

  ngOnDestroy() {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
  }
}
