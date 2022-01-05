import {Component, Inject} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {MAT_DIALOG_DATA} from "@angular/material/dialog";
import {Constant} from "../../model/Constant";
import {Variable} from "../../model/Variable";
import {Metric} from "../../model/Metric";

@Component({
  selector: 'app-sidenav',
  templateUrl: './predefined-function-dialog.component.html',
  styleUrls: ['./predefined-function-dialog.component.css']
})
export class PredefinedFunctionDialogComponent {
  options: FormGroup;

  hideRequiredControl = new FormControl(false);
  floatLabelControl = new FormControl('auto');
  constants: Constant[];
  variables: Variable[];
  metricList: Metric[]
  selectedValue: string;
  firstName = new FormControl();
  dataFromFunctionCreator: any;
  variablesFromFunctionCreator: any;
  selectFormControl = new FormControl('', Validators.required);

  constructor(@Inject(MAT_DIALOG_DATA) public data: any, fb: FormBuilder) {
    this.variablesFromFunctionCreator = this.data.selectedOptions.variables;
    this.constants = this.data.selectedOptions.constants;
    this.dataFromFunctionCreator = this.data.selectedOptions;
    this.metricList=this.data.rawMetricList+this.data.compositeMetricList;
    console.log(this.dataFromFunctionCreator);
    this.options = fb.group({
      hideRequired: this.hideRequiredControl,
      floatLabel: this.floatLabelControl,
      firstName: this.firstName

    });
  }

  updateConstants(list: any) {
    console.log(list);
  }

  getValue(event: Event): void {
    this.constants.forEach(x => {
      x.value = (event.target as HTMLInputElement).value;
      console.log(x.name, x.value);
    })
  }

  trackByIndex(index: number, obj: any): any {
    return index;
  }
}
