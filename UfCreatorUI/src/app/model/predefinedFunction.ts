import {Variable} from "./Variable";
import {Constant} from "./Constant";
import {RawMetric} from "./rawMetric";
import {CompositeMetric} from "./compositeMetric";

export class PredefinedFunction {
  name: string
  src: string
  variableList: Variable[]
  constantsList: Constant[]
  rawMetricList: RawMetric[]
  compositeMetricList: CompositeMetric[]
  weight: number

  constructor(name: string, src: string, variableList: Variable[], constantsList: Constant[], rawMetricList: RawMetric[],compositeMetricList:CompositeMetric[]) {
    this.name = name;
    this.src = src;
    this.variableList = variableList;
    this.constantsList = constantsList;
    this.rawMetricList = rawMetricList;
    this.compositeMetricList = compositeMetricList;
  }
}
