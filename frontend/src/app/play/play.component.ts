import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-play',
  templateUrl: './play.component.html',
  styleUrls: ['./play.component.scss'],
})
export class PlayComponent {
  constructor(private router: Router) {}

  onChoose(choice: string) {
    this.router.navigate(['/choice', choice], {
      skipLocationChange: true,
    });
  }
}
