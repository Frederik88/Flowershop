import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FlowerComponent } from './flower/flower.component';
import { UploadComponent } from './upload/upload.component';
import { NavComponent } from './nav/nav.component';
import { UserComponent } from './user/user.component';
import { TokenInterceptorService } from './_service/TokenInterceptor.service';

@NgModule({
   declarations: [
      AppComponent,
      FlowerComponent,
      UploadComponent,
      NavComponent,
      UserComponent
   ],
   imports: [
      BrowserModule,
      AppRoutingModule,
      HttpClientModule,
      FormsModule
   ],
   providers: [{
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptorService,
      multi: true
   }],
   bootstrap: [
      AppComponent
   ]
})
export class AppModule { }
