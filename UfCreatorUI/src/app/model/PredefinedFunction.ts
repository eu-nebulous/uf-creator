import {Variable} from "./Variable";
import {Constant} from "./Constant";
import {Metric} from "./Metric";

export class PredefinedFunction {
  name: string
  src: string
  variableList: Variable[]
  constantsList: Constant[]
  rawMetricList: Metric[]
  compositeMetricList: Metric[]
  weight: number

  constructor(name: string, src: string, variableList: Variable[], constantsList: Constant[], rawMetricList: Metric[], compositeMetricList: Metric[]) {
    this.name = name;
    this.src = src;
    this.variableList = variableList;
    this.constantsList = constantsList;
    this.rawMetricList = rawMetricList;
    this.compositeMetricList = compositeMetricList;
  }
}
