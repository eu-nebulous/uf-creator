export class RawMetric {
  name: string
  component: string
  value: string

  constructor(name: string, component: string,value:string) {
    this.name = name;
    this.component = component;
    this.value=value;
  }

}
