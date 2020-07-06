import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {HttpClientModule} from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UserListComponent } from './UserList/user-list.component';
import { FlowerComponent } from './flower/flower.component';
import { UploadComponent } from './upload/upload.component';

@NgModule({
   declarations: [
      AppComponent,
      UserListComponent,
      FlowerComponent,
      UploadComponent
   ],
   imports: [
      BrowserModule,
      AppRoutingModule,
      HttpClientModule,
      FormsModule
   ],
   providers: [],
   bootstrap: [
      AppComponent
   ]
})
export class AppModule { }
