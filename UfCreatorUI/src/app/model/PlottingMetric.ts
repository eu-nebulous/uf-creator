import {Metric} from "./Metric";

export class PlottingMetric{
  metric: string
  value: string

  constructor(metric:string,value: string) {
    this.metric = metric;
    this.value = value;
  }
}
