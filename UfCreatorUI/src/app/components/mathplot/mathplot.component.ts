import {Component, Directive, ElementRef, HostListener, Inject, Input, OnInit, ViewChild} from '@angular/core';
import functionPlot from "function-plot";


@Component({
  selector: 'app-mathplot',
  templateUrl: './mathplot.component.html',
  styleUrls: ['./mathplot.component.css']
})
export class MathplotComponent {
  @Input() functionToBePlotted = "";
  @Input() a:number;
  @Input() b:number;
  @Input() id = "";

  func(): any {

    functionPlot({
      target: this.id,
      grid: false,
      disableZoom: false,
      title: 'Plotted function',
      data: [
        {
          fn: this.functionToBePlotted,
        },
      ],
    });
  }
}
