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


export class UtilityFunctionService {

  apiUrl = 'http://localhost:8080/camel-model';

  constructor(private http: HttpClient) {
  }

  sendUserChoice(form:any): Observable<any> {
    const body=JSON.stringify(form);

    return this.http.post(`${this.apiUrl}/map`,body, httpOptions);
  }


}
