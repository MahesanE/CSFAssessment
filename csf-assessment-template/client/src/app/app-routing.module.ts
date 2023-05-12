import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { View0Component } from './components/view0.component';
import { View1Component } from './components/view1.component';
import { View2Component } from './components/view2.component';

const routes: Routes = [
  { path: 'upload', component: View0Component },
  { path: 'view1/:bundleId', component: View1Component },
  { path: 'bundles', component: View2Component },
  { path: '', redirectTo: 'bundles', pathMatch: 'full' },
  { path: '**', redirectTo: 'bundles' },

  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
