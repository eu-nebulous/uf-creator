import {Injectable} from '@angular/core';
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

  apiUrl = 'http://localhost:8080/utility-function/';

  constructor(private http: HttpClient) {

  }

  createFunction(createFunctionRequest: any): Observable<any> {
    console.log(createFunctionRequest);
    const requestUrl = this.apiUrl + 'function';
    const httpDeploymentProcessHeader = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };
    return this.http.post<any>(requestUrl, JSON.stringify(createFunctionRequest), httpDeploymentProcessHeader).pipe(
      tap((response: any) => {
          console.log(`utility function request OK`);
        },
        e => console.log('Error by creating utility function', e)));
    ;
  }
}
