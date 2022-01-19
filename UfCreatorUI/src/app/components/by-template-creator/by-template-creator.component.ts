import {Component} from '@angular/core';
import {FormBuilder, FormControl, Validators} from "@angular/forms";
import {Observable} from "rxjs";
import {StepperOrientation} from "@angular/cdk/stepper";
import {BreakpointObserver} from "@angular/cdk/layout";
import {map} from "rxjs/operators";
import {MatDialog} from "@angular/material/dialog";
import {HttpErrorResponse} from "@angular/common/http";
import {Metric} from "../../model/Metric";
import {CamelService} from "../../service/camel.service";
import {FunctionService} from "../../service/function.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {ByTemplateFunction} from "../../model/ByTemplateFunction";
import {MatListOption} from "@angular/material/list";
import {UtilityShape} from "../../model/UtilityShape";
import {PlottingMetric} from "../../model/PlottingMetric";
import {MatStepper} from "@angular/material/stepper";
import {PredefinedFunctionDialogComponent} from "../predefined-function-dialog/predefined-function-dialog.component";
import {MathplotComponent} from "../mathplot/mathplot.component";

@Component({
  selector: 'app-by-template-creator',
  templateUrl: './by-template-creator.component.html',
  styleUrls: ['./by-template-creator.component.css']
})
export class ByTemplateCreatorComponent {
  shape: string;
  shapes: UtilityShape[] = [new UtilityShape('S-Shaped', "x^2"), new UtilityShape('U-Shaped', "x"), new UtilityShape('Reverse S-Shaped', "x"), new UtilityShape("Reverse U-Shaped", "x"), new UtilityShape('Constant Shaped', "x")];
  camelModelList: Array<string>;
  mode = new FormControl('over');
  forthCtrl = new FormControl();
  variables = new FormControl();
  rawMetrics = new FormControl();
  metrics = new FormControl();
  rawMetricList: Metric[];
  compositeMetrics = new FormControl();
  plottingMetrics = new Array<PlottingMetric>();
  compositeMetricList: Metric[];
  isCamelModelListLoading = true;
  isCompositeMetricsLoading: any;
  isRawMetricsLoading: any;
  selectedCamelModel: string
  metricList: Metric[];
  sliderValues = new Array<number>();
  sum: number;
  byTemplateFunctionList = new Array<ByTemplateFunction>();

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
  isSendingRequest: boolean;
  utilityFunction: any;
  changeText: boolean;
  value: any;

  constructor(private _formBuilder: FormBuilder, breakpointObserver: BreakpointObserver, private functionService: FunctionService,
              private camelModelService: CamelService, public dialog: MatDialog, private _snackBar: MatSnackBar) {
    this.stepperOrientation = breakpointObserver
      .observe('(min-width: 800px)')
      .pipe(map(({matches}) => (matches ? 'horizontal' : 'vertical')));
  }

  ngOnInit(): void {
    this.metricList = [new Metric("Test", "sdasa"), new Metric("Test2", "sdasa")];
    this.camelModelService.getCamelModelList().subscribe(camelModelResponse => {
        this.isCamelModelListLoading = false;
        this._snackBar.open("List of camel models loaded!", "Close", {duration: 5 * 1000,});
        this.camelModelList = camelModelResponse;
      },
      (error: HttpErrorResponse) => {
        this._snackBar.open(error.message, "Close", {duration: 10 * 1000,});
      })
  }

  trackByIndex(index: number, obj: any): any {
    return index;
  }

  fillSliderValues(value: any, index: number) {
    this.sliderValues[index] = value;
  }

  formatLabel(value: number) {
    return value;
  }

  firstStepComplete(firstStepValue: string) {
    this.isCompositeMetricsLoading = true;
    this.isRawMetricsLoading = true;
    this.camelModelService.getCompositeMetricList(firstStepValue).subscribe(
      (response: any) => {
        this.compositeMetricList = response;
        this.isCompositeMetricsLoading = false;
        console.log(response)
        this.camelModelService.getRawMetricList(firstStepValue).subscribe(
          (response: any) => {
            this.rawMetricList = response;
            this.isRawMetricsLoading = false;
            this.metricList = this.rawMetricList.concat(this.compositeMetricList).sort((a, b) => a.name.localeCompare(b.name));
            this.selectedCamelModel = firstStepValue;
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
  }

  validateValuesFromSliders(stepper: any) {
    this.sum = 0;
    this.sliderValues.forEach(x => {
      this.sum += x;
    })

    if (this.sum == 1) {
      this._snackBar.open("In a moment you'll see created utility function", "Close", {duration: 5 * 1000,});
      for (let i = 0; i < this.byTemplateFunctionList.length; i++) {
        this.byTemplateFunctionList[i].weight = this.sliderValues[i];
      }
      this.generateUtilityFunction();
      stepper.next();
    } else {
      this._snackBar.open("Weights should sum up to 1!", "Close", {duration: 5 * 1000,});
    }
  }

  saveToCamelModel(resourceName: any, utilityFunction: any) {
    console.log("Save function request from by template creator component");
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

  saveSelected(selections: MatListOption[]) {
    this.byTemplateFunctionList = new Array<ByTemplateFunction>();
    selections.forEach(x =>
      this.byTemplateFunctionList.push(new ByTemplateFunction(x.value.name,0,0)));
    this.byTemplateFunctionList.forEach(x => this.plottingMetrics.push(new PlottingMetric(x.metricName, "")));
  }

  generateUtilityFunction() {
    console.log('Create function request from by template creator component');

    this.isSendingRequest = true;
    this._snackBar.open("Your function is being created", "Close", {duration: 5 * 1000,});
    this.functionService.createFunction(this.byTemplateFunctionList, "byTemplate").subscribe(value => {
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
}
