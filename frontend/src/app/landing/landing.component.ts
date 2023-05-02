import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-landing',
  templateUrl: './landing.component.html',
  styleUrls: ['./landing.component.scss'],
})
export class LandingComponent {
  constructor(private router: Router) {}

  onNewGame() {
    // POST an Backend
    // { 
    //    player1 = {
       //        id: myId,
     //       }      
    //  }
    this.router.navigate(['/game', 'new-id']);
  }

  onJoinGame() {
    this.router.navigate(['/game', 'existing-id']);
  }
}
