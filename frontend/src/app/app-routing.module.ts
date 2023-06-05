import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ResultComponent } from './result/result.component';
import { PlayComponent } from './play/play.component';
import { GameComponent } from './game/game.component';

const routes: Routes = [
  { path: 'game/:gameId', component: GameComponent },
  { path: 'choice/:choice', component: ResultComponent },
  { path: '**', component: PlayComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
