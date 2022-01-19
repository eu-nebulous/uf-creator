import {Component, AfterViewInit, Input, Inject} from '@angular/core';
import * as L from 'leaflet';

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css']
})
export class MapComponent {
  private map: any;
  private markers: any;

  private initMap(): void {
    this.map = L.map('map', {
      center: [51.509865, -0.118092],
      zoom: 4
    });

    const tiles = L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      maxZoom: 18,
      // minZoom: 4,
      attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
    });

    tiles.addTo(this.map);

    const marker1 = L.marker([52.23,21.01 ]);

    const marker2 = L.marker([51.5, -0.11]);
    const latlngs = Array();

    latlngs.push(marker1.getLatLng());

    latlngs.push(marker2.getLatLng());

    const polyline = L.polyline(latlngs, {color: 'red'}).addTo(this.map);

    this.map.fitBounds(polyline.getBounds());
    marker1.addTo(this.map);
    marker2.addTo(this.map);
  }

  constructor() {

  }

  ngAfterViewInit(): void {
    this.initMap();
  }
}
