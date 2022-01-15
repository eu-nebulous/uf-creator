export class ByTemplateFunction {
  metricName: string;
  a: number | undefined;
  b: number | undefined;
  weight: number | undefined;
  shape: string | undefined;

  constructor(metricName: string, a?: number, b?: number, weight?: number, shape?: string) {
    this.metricName = metricName;
    this.a = a;
    this.b = b;
    this.weight = weight;
    this.shape = shape;
  }
}
