import {Variable} from "./Variable";
import {Constant} from "./Constant";
import {Metric} from "./Metric";

export class PredefinedFunction {
  name: string
  src: string
  variableList: Variable[]
  constantsList: Constant[]
  metricList: Metric[]
  weight: number

  constructor(name: string, src: string, variableList: Variable[], constantsList: Constant[], metricList: Metric[]) {
    this.name = name;
    this.src = src;
    this.variableList = variableList;
    this.constantsList = constantsList;
    this.metricList = metricList;
  }
}
