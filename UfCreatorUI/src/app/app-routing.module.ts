import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule, Routes} from "@angular/router";
import {PredefinedFunctionDialogModule} from "./components/predefined-function-dialog/predefined-function-dialog.module";
import {ByFunctionCreatorComponent} from "./components/by-function-creator/by-function-creator.component";
import {ByTemplateCreatorComponent} from "./components/by-template-creator/by-template-creator.component";

const routes: Routes = [
  {path: 'app-by-function-creator', component: ByFunctionCreatorComponent},
  {path: 'app-by-template-creator', component: ByTemplateCreatorComponent},
]


@NgModule({

  declarations: [],
  imports: [
    CommonModule,
    PredefinedFunctionDialogModule,
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]

})
export class AppRoutingModule {
}
