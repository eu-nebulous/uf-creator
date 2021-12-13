import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {tap} from "rxjs/operators";


const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'}),
  params: new HttpParams()
};

@Injectable({
  providedIn: 'root'
})


export class FunctionService {

  apiUrl = 'http://localhost:8080/utility-function';

  constructor(private http: HttpClient) {
  }

  getAvailableFunctions(variableList:string[]): Observable<any>{
    httpOptions.params.append('variableDto', JSON.stringify(variableList));
    return this.http.get(`${this.apiUrl}/availableFunctions`, httpOptions);
  }
}
