import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {HttpClient, HttpClientModule} from "@angular/common/http";
import {CommonModule} from '@angular/common';
import {IonicModule} from '@ionic/angular';
import {FormsModule} from "@angular/forms";

@Component({
  standalone: true,
  selector: 'app-activity',
  imports: [
    CommonModule,
    IonicModule,
    HttpClientModule,
    FormsModule,
  ],
  templateUrl: './activity.component.html',
  styleUrls: ['./activity.component.scss'],
})
export class ActivityComponent implements OnInit {
  activities: any[] = [];
  activity: any = null;
  loading = true;
  isDetail = false;

  constructor(
    private route: ActivatedRoute,
    private http: HttpClient,
    private router: Router
  ) {}

  newActivity = {
    userId: '22222222-2222-2222-2222-222222222222',
    type: '',
    duration: 0,
    distance: 0,
    calories: 0,
    loggedAt: new Date().toISOString()
  };

  loadActivities() {
    this.http.get<any[]>(`http://localhost:8080/api/activities`).subscribe(data => {
      this.activities = data;
      this.loading = false;
    });
  }


  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    this.isDetail = !!id;

    if (this.isDetail) {
      this.http.get(`http://localhost:8080/api/activities/${id}`).subscribe(data => {
        this.activity = data;
        this.loading = false;
      });
    } else {
      this.http.get<any[]>(`http://localhost:8080/api/activities`).subscribe(data => {
        this.activities = data;
        this.loading = false;
      });
    }
  }

  goToDetail(id: number) {
    console.log("goToDetail", id);
    this.router.navigate(['/activities', id]);
  }

  // Here’s your new method to call the create API:
  createActivity() {
    this.http.post('http://localhost:8080/api/activities/createActivity', this.newActivity)
      .subscribe({
        next: (createdActivity: any) => {
          this.activities.push(createdActivity);
          this.loadActivities();  // refresh after successful creation
          this.newActivity.type = '';
          this.newActivity.duration = 0;
          this.newActivity.distance = 0;
          this.newActivity.calories = 0;
          this.newActivity.loggedAt = new Date().toISOString();
        },
        error: err => {
          console.error('Failed to create activity', err);
        }
      });
  }

}
