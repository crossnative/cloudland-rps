import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ResultComponent } from './result/result.component';
import { PlayComponent } from './play/play.component';
import { LandingComponent } from './landing/landing.component';
import { GameComponent } from './game/game.component';

const routes: Routes = [
  { path: 'landing', component: LandingComponent },
  { path: 'game/:game', component: GameComponent },
  { path: 'choice/:choice', component: ResultComponent },
  { path: '**', component: PlayComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
