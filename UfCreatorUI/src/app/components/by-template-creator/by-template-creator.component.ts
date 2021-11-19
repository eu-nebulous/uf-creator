import { Component} from '@angular/core';
import {FormBuilder, FormControl, Validators} from "@angular/forms";
import {Observable} from "rxjs";
import {StepperOrientation} from "@angular/cdk/stepper";
import {BreakpointObserver} from "@angular/cdk/layout";
import {map} from "rxjs/operators";
import {MatDialog} from "@angular/material/dialog";
import {CamelModel} from "../../CamelModel";
import {Variable} from "../../Variable";
import {RawMetric} from "../../RawMetric";
import {CompositeMetric} from "../../CompositeMetric";
import {HttpErrorResponse} from "@angular/common/http";
import {CamelModelService} from "../../camelModel.service";


@Component({
  selector: 'app-by-template-creator',
  templateUrl: './by-template-creator.component.html',
  styleUrls: ['./by-template-creator.component.css']
})
export class ByTemplateCreatorComponent  {

  shape: string;
  shapes: string[] = ['S-Shaped', 'U-Shaped', 'Constant Shaped', 'Reverse S-Shaped', "Reverse U-Shaped", "Linear"];
  camelModels: CamelModel[];
  mode = new FormControl('over');
  forthCtrl = new FormControl();
  variables = new FormControl();
  variableList:Variable[];
  rawMetrics = new FormControl();
  rawMetricList:RawMetric[];
  compositeMetrics = new FormControl();
  compositeMetricList:CompositeMetric[];

  firstFormGroup = this._formBuilder.group({
    firstCtrl: ['', Validators.required],
  });
  secondFormGroup = this._formBuilder.group({
    secondCtrl: ['', Validators.required],
  });
  thirdFormGroup = this._formBuilder.group({
    thirdCtrl: ['', Validators.required],
  });
  forthFormGroup = this._formBuilder.group({
    forthCtrl: ['', Validators.required],
  });

  stepperOrientation: Observable<StepperOrientation>;

  constructor(private camelModelService:CamelModelService, private _formBuilder: FormBuilder, public dialog: MatDialog, breakpointObserver: BreakpointObserver) {
    this.stepperOrientation = breakpointObserver
      .observe('(min-width: 800px)')
      .pipe(map(({matches}) => (matches ? 'horizontal' : 'vertical')));
  }
  ngOnInit() {
    this.getCamelModels();
  }

  firstStepComplete(firstStepValue:string) {
    this.camelModelService.getVariables(firstStepValue).subscribe(
      (response: Variable[]) => {
        this.variableList = response;
        this.camelModelService.getCompositeMetrics(firstStepValue).subscribe(
          (response: CompositeMetric[]) => {
            this.compositeMetricList = response;
            this.camelModelService.getRawMetrics(firstStepValue).subscribe(
              (response: RawMetric[]) => {
                this.rawMetricList = response;
              },
              (error: HttpErrorResponse) => {
                console.log(error);
              }
            );
          },
          (error: HttpErrorResponse) => {
            console.log(error);
          }
        );
      },
      (error: HttpErrorResponse) => {
        console.log(error);
      }
    );


  }

  public getCamelModels(): void {
    this.camelModelService.getCamelModels().subscribe(
      (response: CamelModel[]) => {
        this.camelModels = response;
      },
      (error: HttpErrorResponse) => {
        console.log(error);
      }
    );
  }
}
