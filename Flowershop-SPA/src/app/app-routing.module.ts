import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { FlowerComponent } from './flower/flower.component';
import { UserComponent } from './user/user.component';
import { UploadComponent } from './upload/upload.component';


const routes: Routes = [
  { path:  'user', component:  UserComponent},
  { path:  'flower', component:  FlowerComponent},
  { path:  'upload', component:  UploadComponent},
  { path: '', redirectTo: '/user', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
