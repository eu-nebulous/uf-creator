import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {HttpClientModule} from "@angular/common/http";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatNativeDateModule, MatRippleModule} from "@angular/material/core";
import {MatStepperModule} from "@angular/material/stepper";
import {MatInputModule} from "@angular/material/input";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatButtonModule} from '@angular/material/button';
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatSidenavModule} from "@angular/material/sidenav";
import {MatIconModule} from "@angular/material/icon";
import {MatDividerModule} from "@angular/material/divider";
import {MatListModule} from "@angular/material/list";
import {RouterModule} from "@angular/router";
import {PredefinedFunctionDialogComponent} from './components/predefined-function-dialog/predefined-function-dialog.component';
import {ByTemplateCreatorComponent} from './components/by-template-creator/by-template-creator.component';
import {ByFunctionCreatorComponent} from './components/by-function-creator/by-function-creator.component';
import {AppRoutingModule} from "./app-routing.module";
import {MatSelectModule} from "@angular/material/select";
import {MatDialogModule} from "@angular/material/dialog";
import {MatRadioModule} from "@angular/material/radio";
import {MatSlideToggleModule} from "@angular/material/slide-toggle";
import {MatProgressSpinnerModule} from "@angular/material/progress-spinner";
import {MatProgressBarModule} from "@angular/material/progress-bar";
import {MatGridListModule} from "@angular/material/grid-list";
import {MatCardModule} from "@angular/material/card";
import {MatSliderModule} from "@angular/material/slider";
import {MatButtonToggleModule} from "@angular/material/button-toggle";
import {MatSnackBarModule} from "@angular/material/snack-bar";
import {MatPaginatorModule} from "@angular/material/paginator";
import {ByPlottingComponent} from './components/by-plotting/by-plotting.component';
import {MathplotComponent} from "./components/mathplot/mathplot.component";
import {MapComponent} from './components/map/map.component';
import {LeafletModule} from "@asymmetrik/ngx-leaflet";
import {FontAwesomeModule} from "@fortawesome/angular-fontawesome";


@NgModule({
  declarations: [
    AppComponent,
    PredefinedFunctionDialogComponent,
    ByTemplateCreatorComponent,
    ByFunctionCreatorComponent,
    ByPlottingComponent,
    MathplotComponent,
    MapComponent
  ],
  imports: [
    BrowserAnimationsModule,
    BrowserAnimationsModule,
    BrowserModule,
    FormsModule,
    HttpClientModule,
    MatNativeDateModule,
    ReactiveFormsModule,
    MatStepperModule,
    MatInputModule,
    MatFormFieldModule,
    MatButtonModule,
    MatToolbarModule,
    MatSidenavModule,
    MatIconModule,
    MatDividerModule,
    MatListModule,
    RouterModule,
    AppRoutingModule,
    MatIconModule,
    MatSelectModule,
    MatDialogModule,
    MatRadioModule,
    HttpClientModule,
    MatSlideToggleModule,
    MatProgressSpinnerModule,
    MatProgressBarModule,
    MatGridListModule,
    MatCardModule,
    ReactiveFormsModule,
    MatSliderModule,
    MatButtonToggleModule,
    MatSnackBarModule,
    MatPaginatorModule,
    MatRippleModule,
    LeafletModule,
    FontAwesomeModule

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
