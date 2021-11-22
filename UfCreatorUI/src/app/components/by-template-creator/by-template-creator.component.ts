import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, Validators} from "@angular/forms";
import {Observable} from "rxjs";
import {StepperOrientation} from "@angular/cdk/stepper";
import {BreakpointObserver} from "@angular/cdk/layout";
import {map} from "rxjs/operators";
import {MatDialog} from "@angular/material/dialog";

@Component({
  selector: 'app-by-template-creator',
  templateUrl: './by-template-creator.component.html',
  styleUrls: ['./by-template-creator.component.css']
})
export class ByTemplateCreatorComponent  {

  shape: string;
  shapes: string[] = ['S-Shaped', 'U-Shaped', 'Constant Shaped', 'Reverse S-Shaped', "Reverse U-Shaped", "Linear"];

  mode = new FormControl('over');

  variables = new FormControl();
  variableList: string[] = ['Extra cheese', 'Mushroom', 'Onion', 'Pepperoni', 'Sausage', 'Tomato'];

  rawMetrics = new FormControl();
  rawMetricList: string[] = ['Extra cheese', 'Mushroom', 'Onion', 'Pepperoni', 'Sausage', 'Tomato'];

  compositeMetrics = new FormControl();
  compositeMetricList: string[] = ['Extra cheese', 'Mushroom', 'Onion', 'Pepperoni', 'Sausage', 'Tomato'];

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

  constructor(private _formBuilder: FormBuilder, public dialog: MatDialog, breakpointObserver: BreakpointObserver) {
    this.stepperOrientation = breakpointObserver
      .observe('(min-width: 800px)')
      .pipe(map(({matches}) => (matches ? 'horizontal' : 'vertical')));
  }
}
