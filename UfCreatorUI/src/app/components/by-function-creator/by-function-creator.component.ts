import {Component, OnInit} from '@angular/core';
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
import {VariableMetric} from "../../model/variableMetric";
import {PredefinedFunction} from "../../model/predefinedFunction";
import {SidenavComponent} from "../sidenav/sidenav.component";
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

  camelModelList: Array<string>
  compositeMetricList: Array<RawMetric>
  rawMetricList: Array<RawMetric>
  variableList: Array<VariableMetric>
  selectedCamelModel: string
  mode = new FormControl('over');
  stepperOrientation: Observable<StepperOrientation>;
  isCamelModelListLoading = true;
  isVariablesLoading = true;
  isCompositeMetricsLoading = true;
  isRawMetricsLoading = true;
  predefinedFunctions: Array<PredefinedFunction>;
  selectedOptions: Array<PredefinedFunction>;
  selected = new Array<PredefinedFunction>();
  sliderValues = new Array<number>();
  sum: number;
  isSendingRequest = false;
  utilityFunction: any;
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
    console.log(this.selectedOptions);
  }

  public getRawMetrics(selectedCamelModel: string) {
    this.camelModelService.getRawMetricList(this.selectedCamelModel).subscribe(rawMetricsResponse => {
      this.rawMetricList = rawMetricsResponse;
    });
  }

  public getCompositeMetrics(selectedCamelModel: string) {
    this.camelModelService.getCompositeMetricList(this.selectedCamelModel).subscribe(compositeMetricsResponse => {
      this.compositeMetricList = compositeMetricsResponse;
    });
  }

  public getVariableMetrics(selectedCamelModel: string) {
    this.camelModelService.getCompositeMetricList(this.selectedCamelModel).subscribe(compositeMetricsResponse => {
      this.compositeMetricList = compositeMetricsResponse;
    });
  }

  firstStepComplete(firstStepValue: string) {
    this.camelModelService.getVariableMetricList(firstStepValue).subscribe(
      (response: any) => {
        this.isVariablesLoading = false;
        this.isCompositeMetricsLoading = true;
        this.isRawMetricsLoading = true;
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
    var predefined = new Array<PredefinedFunction>();
    var simulationOnTimeVariables = [new Variable("number of instances", "", "Cardinality"), new Variable("number of cores", "", "Cost")];
    var simulationOnTimeVariables2 = [new Variable("number of instances", "", "Cardinality"), new Variable("number of cores", "", "Cost")];
    var constantsList = [new Constant("expected response time T^", "0"), new Constant("maximum acceptable response time T+", "0"), new Constant("default timeout time Td", "0")];
    var constantsList1 = [new Constant("expected response time T^", "0"), new Constant("maximum acceptable response time T+", "0"), new Constant("default timeout time Td", "0")];
    var rawMetricsList = [new RawMetric("Minimum cores number", "", ""), new RawMetric("Simulations left", "", "")];
    var rawMetricsList2 = [new RawMetric("Minimum cores number", "", ""), new RawMetric("Simulations left", "", "")];
    var compositeMetricsList = [new CompositeMetric("Estimated Remaining Time", "", "", "")];
    var compositeMetricsList2 = [new CompositeMetric("Estimated Remaining Time", "", "", "")];

    var simulationOnTime = new PredefinedFunction("FinishSimulationOnTime", "../../../assets/img/Udeadline.png", simulationOnTimeVariables, constantsList, rawMetricsList, compositeMetricsList);
    var secondFunction = new PredefinedFunction("ExpectedResponseTime", "../../../assets/img/Udeadline.png", simulationOnTimeVariables2, constantsList1, rawMetricsList2, compositeMetricsList2);
    // var ramUsage=new PredefinedFunction("RAM usage function","../../../assets/img/Uram.png");
    // var locality=new PredefinedFunction("locality utility function","../../../assets/img/Ulocality.png");
    // var costPerUser=new PredefinedFunction("Cost per user function","../../../assets/img/Ucostuser.png");
    // var cpuUsage=new PredefinedFunction("CPU usage function","../../../assets/img/Ucpu.png");

    predefined.push(simulationOnTime);
    predefined.push(secondFunction);
    // predefined.push(locality);
    // predefined.push(costPerUser);
    // predefined.push(cpuUsage);

    return predefined;
  }


  openDialog(selections: MatListOption[], stepper: MatStepper) {
    this.saveSelected(selections);
    const dialogRef = this.dialog.open(SidenavComponent, {
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

  foo2(foo: any) {
    console.log(foo);
  }

  trackByIndex(index: number, obj: any): any {
    return index;
  }

  saveSelected(selections: MatListOption[]) {
    selections.forEach(x => {
      this.selected.push(x.value);
    });
    console.log(this.selected);
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
      stepper.next();

    } else {
      this._snackBar.open("Weights should sum up to 1!", "Close", {duration: 5 * 1000,});
    }
  }

  onCreateFunctionClick(predefinedFunctions: any) {
    console.log('Create function request from by function creator component');
    this.isSendingRequest = true;
    this._snackBar.open("Your function is being created", "Close", {duration: 5 * 1000,});
    this.functionService.createFunction(this.predefinedFunctions).subscribe(value => {
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
