export class CompositeMetric {
  name: string
  component: string
  formula: string

  constructor(name: string, component: string, formula: string) {
    this.name = name;
    this.component = component;
    this.formula = formula;
  }
}
