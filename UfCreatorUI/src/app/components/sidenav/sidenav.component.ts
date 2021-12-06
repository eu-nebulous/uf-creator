import {Component, Inject, OnInit, ViewChild} from '@angular/core';
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";
import {MAT_DIALOG_DATA} from "@angular/material/dialog";
import {PredefinedFunction} from "../../model/predefinedFunction";
import {MatListOption} from "@angular/material/list";
import {Constant} from "../../model/Constant";


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

  constructor(@Inject(MAT_DIALOG_DATA) public data: any,fb: FormBuilder) {

    this.constants = this.data.selectedOptions.constants;
    this.options = fb.group({
      hideRequired: this.hideRequiredControl,
      floatLabel: this.floatLabelControl,

    });
  }
foo(foo:string){
    console.log(foo);
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
}
