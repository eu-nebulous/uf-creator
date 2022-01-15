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


export class CamelService {

  apiUrl = `${AppConfigService.settings.apiUrl}/camel-model/`;


  constructor(private http: HttpClient) {
  }

  getCamelModelList(): Observable<any> {
    return this.http.get(`${this.apiUrl}`, httpOptions);
  }

  getRawMetricList(resourceName: string): Observable<any> {
    return this.http.get(`${this.apiUrl}/${resourceName}/rawMetrics`, httpOptions);
  }

  getCompositeMetricList(resourceName: string): Observable<any> {
    return this.http.get(`${this.apiUrl}/${resourceName}/compositeMetrics`, httpOptions);
  }

  getVariableMetricList(resourceName: string): Observable<any> {
    return this.http.get(`${this.apiUrl}/${resourceName}/variables`, httpOptions);
  }

  saveUtilityFunction(resourceName: any, utilityFunction: string): Observable<any> {
    let requestUrl = this.apiUrl + "/" + resourceName + "/saveUtility/" + utilityFunction;

    return this.http.post<any>(requestUrl, "").pipe(
      tap((response: any) => {
          console.log(`Saving utility function request OK`);
        },
        e => console.log('Error by saving utility function', e)));
  }

}
