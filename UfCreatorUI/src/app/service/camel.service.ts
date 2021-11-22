import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {tap} from "rxjs/operators";
import {CompositeMetric} from "../model/compositeMetric";
import {RawMetric} from "../model/rawMetric";

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'}),
  params: new HttpParams()
};

@Injectable({
  providedIn: 'root'
})


export class CamelService {

  apiUrl = 'http://localhost:8080/camel-model/';

  constructor(private http: HttpClient) {
  }

  getCamelModelList(): Observable<any> {
      return this.http.get(`${this.apiUrl}`, httpOptions);
    }

  getRawMetricList(resourceName: string): Observable<any> {
    return this.http.get(`${this.apiUrl}/${resourceName}/rawMetrics`, httpOptions);
  }

  getCompositeMetricList(resourceName: string): Observable<any> {
    return this.http.get(`${this.apiUrl}/${resourceName}/compositeMetric`, httpOptions);
  }

  getVariableMetricList(resourceName: string): Observable<any> {
    return this.http.get(`${this.apiUrl}/${resourceName}/variables`, httpOptions);
  }

}
