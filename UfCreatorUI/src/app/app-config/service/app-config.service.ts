import {Injectable} from '@angular/core';
import {AppConfig} from '../model/app-config';
import {HttpBackend, HttpClient} from '@angular/common/http';
import {environment} from "../../../environments/environment.prod";

@Injectable({
  providedIn: 'root'
})
export class AppConfigService {

  static settings: AppConfig;
  private http: HttpClient;

  constructor(handler: HttpBackend) {
    this.http = new HttpClient(handler);
  }

  loadConfiguration() {
    const jsonFile = `${environment.href}assets/appConfig.json`;

    return new Promise<void>((resolve, reject) => {
      if (environment.useRuntimeConfig) {
        console.log('Loading configuration from appConfig.json');
        this.http.get(jsonFile).toPromise().then((response: any) => {
          AppConfigService.settings = <AppConfig> response;
          console.log('Config loaded: ', AppConfigService.settings);
          resolve();
        }).catch((response: any) => {
          console.log('Config not loaded', response);
          reject('Problem by loading config file');
        });
      } else {
        console.log('Load configuration from environment file');
        AppConfigService.settings = new class implements AppConfig {
          apiUrl = environment.apiUrl;
        }();
        resolve();
      }
    });
  }
}
