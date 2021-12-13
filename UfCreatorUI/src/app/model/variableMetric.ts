export class VariableMetric {
  name: string
  isCurrentConfiguration: string
  formula: string
  type: string;
  componentName: string;

  constructor(name: string, isCurrentConfiguration: string, formula: string,type: string,componentName:string) {
    this.name = name;
    this.isCurrentConfiguration = isCurrentConfiguration;
    this.formula = formula;
    this.type = type;
    this.componentName=componentName;
  }
}
