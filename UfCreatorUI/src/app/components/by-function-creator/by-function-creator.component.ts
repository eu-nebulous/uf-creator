import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, Validators} from "@angular/forms";
import {Observable} from "rxjs";
import {StepperOrientation} from "@angular/cdk/stepper";
import {BreakpointObserver} from "@angular/cdk/layout";
import {map} from "rxjs/operators";
import {MatDialog} from "@angular/material/dialog";
import {CamelService} from "../../service/camel.service";
import {RawMetric} from "../../model/rawMetric";
import {HttpErrorResponse} from "@angular/common/http";
import {CompositeMetric} from "../../model/compositeMetric";
import {Variable} from "@angular/compiler/src/render3/r3_ast";
import {VariableMetric} from "../../model/variableMetric";

@Component({
  selector: 'app-by-function-creator',
  templateUrl: './by-function-creator.component.html',
  styleUrls: ['./by-function-creator.component.css']
})
export class ByFunctionCreatorComponent implements OnInit{

  camelModelList: Array<string>
  compositeMetricList: Array<RawMetric>
  rawMetricList: Array<RawMetric>
  variableList: Array<VariableMetric>
  selectedCamelModel: string
  mode = new FormControl('over');
  stepperOrientation: Observable<StepperOrientation>;

  constructor(private _formBuilder: FormBuilder, public dialog: MatDialog, breakpointObserver: BreakpointObserver, private camelModelService: CamelService) {
    this.stepperOrientation = breakpointObserver
      .observe('(min-width: 800px)')
      .pipe(map(({matches}) => (matches ? 'horizontal' : 'vertical')));
  }

  ngOnInit(): void {
      this.camelModelService.getCamelModelList().subscribe(camelModelResponse => {
      this.camelModelList = camelModelResponse;
    })
  }

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

  public getRawMetrics(selectedCamelModel: string) {
    this.camelModelService.getRawMetricList(this.selectedCamelModel).subscribe(rawMetricsResponse => {
      this.rawMetricList = rawMetricsResponse;
    });
  }

  public getCompositeMetrics(selectedCamelModel: string)  {
    this.camelModelService.getCompositeMetricList(this.selectedCamelModel).subscribe(compositeMetricsResponse => {
      this.compositeMetricList = compositeMetricsResponse;
    });
  }

  public getVariableMetrics(selectedCamelModel: string)  {
    this.camelModelService.getCompositeMetricList(this.selectedCamelModel).subscribe(compositeMetricsResponse => {
      this.compositeMetricList = compositeMetricsResponse;
    });
  }

  firstStepComplete(firstStepValue:string) {
    this.camelModelService.getVariableMetricList(firstStepValue).subscribe(
      (response: any) => {
        this.variableList = response;
        console.log(response)
        this.camelModelService.getCompositeMetricList(firstStepValue).subscribe(
          (response: any) => {
            this.compositeMetricList = response;
            console.log(response)
            this.camelModelService.getRawMetricList(firstStepValue).subscribe(
              (response: any) => {
                this.rawMetricList = response;
                console.log(response)
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
}
