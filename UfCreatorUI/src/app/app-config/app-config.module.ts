import {BrowserModule} from '@angular/platform-browser';
import {APP_INITIALIZER, NgModule} from '@angular/core';
import {AppComponent} from "../app.component";
import {AppRoutingModule} from "../app-routing.module";
import {AppConfigService} from "./service/app-config.service";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {RouterModule} from "@angular/router";
import {MatSnackBarModule} from "@angular/material/snack-bar";
import {HttpClientModule} from "@angular/common/http";


export function initializeApp(appConfigService: AppConfigService) {
  return (): Promise<any> => {
    return appConfigService.loadConfiguration();
  };
}

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    RouterModule,
    MatSnackBarModule,
    HttpClientModule,

  ],

  providers: [
    AppConfigService,
    {provide: APP_INITIALIZER, useFactory: initializeApp, deps: [AppConfigService], multi: true},

  ]
})
export class AppModule { }
