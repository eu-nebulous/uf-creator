export class Variable{
  name:string;
  value:string;
  type: string;
  componentName: string;


  constructor(name: string, value: string,type:string) {
    this.name = name;
    this.value = value;
    this.type = type;
  }

}
