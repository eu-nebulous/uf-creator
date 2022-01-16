import {Component, Input} from '@angular/core';
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
      grid: true,
      disableZoom: false,
      title: 'Plotted function',
      data: [
        {
          fn: 'acos(x)',
          },
        {
          points: [
            [this.a,Math.acos(this.a)],
          ],
          attr: { "stroke-width": 5 },
          fnType: 'points',
          graphType: 'polyline'
        }
      ],
    });
  }
}
