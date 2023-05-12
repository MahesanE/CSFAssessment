import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { View0Component } from './components/view0.component';

const routes: Routes = [
  { path: '', component: View0Component},

  {path: '**', redirectTo: ''}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
