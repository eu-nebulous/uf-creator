export class VariableMetric {
  name: string
  isCurrentConfiguration: string
  formula: string

  constructor(name: string, isCurrentConfiguration: string, formula: string) {
    this.name = name;
    this.isCurrentConfiguration = isCurrentConfiguration;
    this.formula = formula;
  }
}
