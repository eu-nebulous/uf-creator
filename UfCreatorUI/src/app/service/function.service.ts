import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {tap} from "rxjs/operators";
import {AppConfigService} from "../app-config/service/app-config.service";


const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'}),
  params: new HttpParams()
};

@Injectable({
  providedIn: 'root'
})

export class FunctionService {

  apiUrl = `${AppConfigService.settings.apiUrl}/utility-function/`;


  constructor(private http: HttpClient) {

  }

  createFunction(createFunctionRequest: any, requestType: string): Observable<any> {
    let requestUrl = this.apiUrl + requestType;
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
  }
}
