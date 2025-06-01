import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ActivityService {
  private baseUrl = 'http://localhost:8080/api/activities'; // use proxy later

  constructor(private http: HttpClient) {}

  getAllActivities(): Observable<any[]> {
    return this.http.get<any[]>(this.baseUrl);
  }
}
