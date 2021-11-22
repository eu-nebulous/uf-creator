import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, Validators} from "@angular/forms";
import {Observable} from "rxjs";
import {StepperOrientation} from "@angular/cdk/stepper";
import {BreakpointObserver} from "@angular/cdk/layout";
import {map} from "rxjs/operators";
import {MatDialog} from "@angular/material/dialog";
import {CamelService} from "../../service/camel.service";
import {RawMetric} from "../../model/rawMetric";

@Component({
  selector: 'app-by-function-creator',
  templateUrl: './by-function-creator.component.html',
  styleUrls: ['./by-function-creator.component.css']
})
export class ByFunctionCreatorComponent implements OnInit{

  camelModelList: Array<string>
  compositeMetricList: Array<RawMetric>
  rawMetricList: Array<RawMetric>
  selectedCamelModel: ''


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

  mode = new FormControl('over');
  forthCtrl = new FormControl();
  variables = new FormControl();
  variableList: string[] = ['Extra cheese', 'Mushroom', 'Onion', 'Pepperoni', 'Sausage', 'Tomato'];

  rawMetrics = new FormControl();
  compositeMetrics = new FormControl();

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
}
