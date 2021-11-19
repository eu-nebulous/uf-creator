import {RawMetric} from "./RawMetric";
import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {CamelModel} from "./CamelModel";
import {CompositeMetric} from "./CompositeMetric";
import {Variable} from "./Variable";
import {environment} from "../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class CamelModelService {
  private apiServerUrl=environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

public getCamelModels(): Observable<CamelModel[]> {
    return this.http.get<CamelModel[]>(`${this.apiServerUrl}/camel-model`);
  }

  public getVariables(resourceName: string): Observable<Variable[]> {
    return this.http.get<Variable[]>(`${this.apiServerUrl}/camel-model/${resourceName}/variables`);
  }
  public getCompositeMetrics(resourceName: string): Observable<CompositeMetric[]> {
    return this.http.get<CompositeMetric[]>(`${this.apiServerUrl}/camel-model/${resourceName}/compositeMetrics`);
  }
  public getRawMetrics(resourceName: string): Observable<RawMetric[]> {
    return this.http.get<RawMetric[]>(`${this.apiServerUrl}/camel-model/${resourceName}/rawMetrics`);
  }


}
