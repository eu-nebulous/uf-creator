export class CompositeMetric {
  name: string
  component: string
  formula: string
  value: string

  constructor(name: string, component: string, formula: string, value: string) {
    this.name = name;
    this.component = component;
    this.formula = formula;
    this.value = value;
  }
}
