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
import {CommonModule} from "@angular/common";
import {MatSnackBar, MatSnackBarModule} from "@angular/material/snack-bar";
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
        this.isCompositeMetricsLoading = false;
        this.isRawMetricsLoading = false;
        this.variableList = response;
        this.compositeMetricList = response;
        this.rawMetricList = response;
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

    var simulationOnTime = new PredefinedFunction("Finish simulation on time function", "../../../assets/img/Udeadline.png", simulationOnTimeVariables, constantsList, rawMetricsList, compositeMetricsList);
    var secondFunction = new PredefinedFunction("Second function", "../../../assets/img/Udeadline.png", simulationOnTimeVariables2, constantsList1, rawMetricsList2, compositeMetricsList2);
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
}
