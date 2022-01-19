import {AfterViewInit, Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import functionPlot from "function-plot";


@Component({
  selector: 'app-mathplot',
  templateUrl: './mathplot.component.html',
  styleUrls: ['./mathplot.component.css']
})
export class MathplotComponent implements OnChanges{
  @Input() functionToBePlotted = "";
  @Input() a:number;
  @Input() b:number;
  @Input() id = "";

  func(): any {
    functionPlot({
      yAxis: { domain: [-0.5, 1.5] },
      xAxis: { domain: [-0.5,5] },
      target: this.id,
      grid: true,
      disableZoom: false,
      title: 'Plotted function',
      data: [
        {
          fn: this.functionToBePlotted,
          graphType: "polyline",
          scope: {b:Number(this.b),e:Math.E,a:Number(this.a)}

          // fn: this.functionToBePlotted,
          // graphType: "polyline"
          // attr: { "stroke-width": 5 }
          // scope: {a: this.a,b: this.b,e:Math.E}
          },
        // {
        //   attr: { "stroke-width": 5 },
        //   fnType: 'points',
        //   graphType: 'polyline'
        // }

      ],
    });
  }

  ngOnChanges(changes: SimpleChanges): void {
    this.func()
  }
}
