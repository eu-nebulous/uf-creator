import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, Validators} from "@angular/forms";
import {Observable} from "rxjs";
import {StepperOrientation} from "@angular/cdk/stepper";
import {BreakpointObserver} from "@angular/cdk/layout";
import {map} from "rxjs/operators";
import {MatDialog} from "@angular/material/dialog";
import {CamelService} from "../../service/camel.service";
import {Metric} from "../../model/Metric";
import {HttpErrorResponse} from "@angular/common/http";
import {PredefinedFunction} from "../../model/PredefinedFunction";
import {PredefinedFunctionDialogComponent} from "../predefined-function-dialog/predefined-function-dialog.component";
import {MatStepper} from "@angular/material/stepper";
import {Variable} from "../../model/Variable";
import {Constant} from "../../model/Constant";
import {MatListOption} from "@angular/material/list";
import {MatSnackBar} from "@angular/material/snack-bar";
import {FunctionService} from "../../service/function.service";



@Component({
  selector: 'app-by-function-creator',
  templateUrl: './by-function-creator.component.html',
  styleUrls: ['./by-function-creator.component.css']
})

export class ByFunctionCreatorComponent implements OnInit {
  thirdStepCompleted=false;
  secondStepCompleted=false;
  firstStepCompleted=false;
  camelModelList: Array<string>
  compositeMetricList: Array<Metric>
  rawMetricList: Array<Metric>
  variableList: Array<Variable>
  selectedCamelModel: string
  mode = new FormControl('over');
  stepperOrientation: Observable<StepperOrientation>;
  isCamelModelListLoading = true;
  isVariablesLoading: any;
  isCompositeMetricsLoading: any;
  isRawMetricsLoading: any;
  predefinedFunctions: Array<PredefinedFunction>;
  selected = new Array<PredefinedFunction>();
  sliderValues = new Array<number>();
  sum: number;
  isSendingRequest = false;
  utilityFunction: string;
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
  something: string;

  constructor(private _formBuilder: FormBuilder, breakpointObserver: BreakpointObserver, private functionService: FunctionService,
              private camelModelService: CamelService, public dialog: MatDialog, private _snackBar: MatSnackBar) {
    this.stepperOrientation = breakpointObserver
      .observe('(min-width: 800px)')
      .pipe(map(({matches}) => (matches ? 'horizontal' : 'vertical')));
  }

  ngOnInit(): void {
    this.camelModelService.getCamelModelList().subscribe(camelModelResponse => {
        this.isCamelModelListLoading = false;
        this._snackBar.open("List of camel models loaded!", "Close", {duration: 5 * 1000,});
        this.camelModelList = camelModelResponse;
      },
      (error: HttpErrorResponse) => {
        this._snackBar.open(error.message, "Close", {duration: 10 * 1000,});
      })
    this.predefinedFunctions = this.getPredefinedFunctions();
  }

  firstStepComplete(firstStepValue: string) {
    this.isCompositeMetricsLoading = true;
    this.isRawMetricsLoading = true;
    this.isVariablesLoading = true;
    this.camelModelService.getVariableMetricList(firstStepValue).subscribe(
      (response: any) => {
        this.isVariablesLoading = false;
        this.variableList = response;
        console.log(response)
        this.camelModelService.getCompositeMetricList(firstStepValue).subscribe(
          (response: any) => {
            this.isCompositeMetricsLoading = false;
            this.compositeMetricList = response;
            console.log(response)
            this.camelModelService.getRawMetricList(firstStepValue).subscribe(
              (response: any) => {
                this.isRawMetricsLoading = false;
                this.rawMetricList = response;
                this.selectedCamelModel = firstStepValue;
                this.firstStepCompleted = true;
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

  getPredefinedFunctions() {
    const predefined = new Array<PredefinedFunction>();
    const constantsListLocality = new Array<Constant>()
    const variableListLocality = [new Variable("latitude of first component", "0","Latitude"), new Variable("longitude of first component", "0","Longitude"), new Variable("latitude of second component", "0","Latitude"), new Variable("longitude of second component", "0","Longitude")];
    const metricsLocality = new Array<Metric>();
    const localityUtility = new PredefinedFunction("LocalityUtility", "../../../assets/img/Ulocality.png", variableListLocality, constantsListLocality, metricsLocality);
    const variableListFinishOnTime=[new Variable("number of worker VMs","","Cardinality"),new Variable("cores per worker","","Cardinality")];
    const metricListFinishOnTime= [new Metric("number of remaining jobs",""),new Metric("upper quantile of job time distribution",""),new Metric("total time spent until now","")];
    const constantsListFinishOnTime = [new Constant("deadline for all jobs", "0"), new Constant("scaling parameter", "0")];
    const finishOnTimeUtility = new PredefinedFunction("FinishOnTime","../../../assets/img/Udeadline.png",variableListFinishOnTime,constantsListFinishOnTime,metricListFinishOnTime);
    predefined.push(localityUtility);
    predefined.push(finishOnTimeUtility)

    return predefined;
  }


  openDialog(selections: MatListOption[], stepper: MatStepper) {
    this.saveSelected(selections);
    this.secondStepCompleted=true;
    const dialogRef = this.dialog.open(PredefinedFunctionDialogComponent, {
      data: {
        selectedOptions: this.selected,
        variables: this.variableList,
        rawMetricList: this.rawMetricList,
        compositeMetricList: this.compositeMetricList
      },
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        stepper.next();
      }
      console.log(`Dialog result: ${result}`);
    });
  }

  formatLabel(value: number) {
    return value;
  }

  trackByIndex(index: number, obj: any): any {
    return index;
  }

  saveSelected(selections: MatListOption[]) {
    this.selected = selections.map(o => o.value);
  }

  fillSliderValues(value: any, index: number) {
    this.sliderValues[index] = value;
  }

  validateValuesFromSliders(stepper: any) {
    this.sum = 0;
    this.sliderValues.forEach(x => {
      this.sum += x;
    })

    if (this.sum == 1) {
      this._snackBar.open("In a moment you'll see created utility function", "Close", {duration: 5 * 1000,});
      for (let i = 0; i < this.selected.length; i++) {
        this.selected[i].weight = this.sliderValues[i];
      }
      this.generateUtilityFunction();
      stepper.next();

    } else {
      this._snackBar.open("Weights should sum up to 1!", "Close", {duration: 5 * 1000,});
    }
  }

  generateUtilityFunction() {
    console.log('Create function request from by template creator component');

    this.isSendingRequest = true;
    this._snackBar.open("Your function is being created", "Close", {duration: 5 * 1000,});
    this.functionService.createFunction(this.selected, "predefined").subscribe(value => {
        this.utilityFunction = value.utilityFunction;
        console.log(`Successful create function request request`);
        this.isSendingRequest = false;
      },
      error1 => {
        console.log(`Error by sending deployment request, message: ${error1.error.message}`);
        this._snackBar.open(`${error1.error.message}`, 'Close', {duration: 1000000});
        this.isSendingRequest = false;
      });
  }

  saveToCamelModel(resourceName: any, utilityFunction: any) {
    console.log("Save function request from predefined creator component");
    this.isSendingRequest = true;
    this._snackBar.open("Your function is being saved", "Close", {duration: 5 * 1000,});
    this.camelModelService.saveUtilityFunction(resourceName, utilityFunction).subscribe(response => {
        console.log(`Successful save utility function to camel model request`);
        this._snackBar.open("Your utility function has been successfully saved into camel model!", 'Close', {duration: 1000000});
      },
      error => {
        console.log(`Error by sending deployment request, message: ${error.error.message}`);
        this._snackBar.open(`${error.error.message}`, 'Close', {duration: 1000000});
      })
  }
}
