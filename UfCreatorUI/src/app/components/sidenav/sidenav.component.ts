import {Component, Inject, OnInit, ViewChild} from '@angular/core';
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";
import {MAT_DIALOG_DATA} from "@angular/material/dialog";
import {PredefinedFunction} from "../../model/predefinedFunction";
import {MatListOption} from "@angular/material/list";
import {Constant} from "../../model/Constant";
import {Variable} from "../../model/Variable";
import {Validators} from '@angular/forms';
import {ReactiveFormsModule} from '@angular/forms';


interface Animal {
  name: string;
  sound: string;
}
@Component({
  selector: 'app-sidenav',
  templateUrl: './sidenav.component.html',
  styleUrls: ['./sidenav.component.css']
})
export class SidenavComponent{
  options: FormGroup;
  hideRequiredControl = new FormControl(false);
  floatLabelControl = new FormControl('auto');
  constants: Constant[];
  variables: Variable[];
  selectedVariables: Variable[]
  selectedValue: string;
  firstName=new FormControl();
  dataFromFunctionCreator:any;
  variablesFromFunctionCreator:any;


  constructor(@Inject(MAT_DIALOG_DATA) public data: any,fb: FormBuilder) {

    this.constants = this.data.selectedOptions.constants;
    this.dataFromFunctionCreator=this.data.selectedOptions;
    console.log(this.dataFromFunctionCreator);
    this.options = fb.group({
      hideRequired: this.hideRequiredControl,
      floatLabel: this.floatLabelControl,
      firstName: this.firstName

    });
  }
  animalControl = new FormControl('', Validators.required);
  selectFormControl = new FormControl('', Validators.required);
foo(foo:any,foo1:any,foo2:any,foo3:any,foo4:any,foo5:any,foo6:any){
    console.log(foo);
    console.log(foo1);
  console.log(foo2);
  console.log(foo3);
console.log(foo4);
}
updateConstants(list: any){
   console.log(list);
  }
  getValue(event: Event): void {
    this.constants.forEach(x=>{
      x.value=(event.target as HTMLInputElement).value;
      console.log(x.name,x.value);
    })
  }
  trackByIndex(index: number, obj: any): any {
    return index;
  }
}
