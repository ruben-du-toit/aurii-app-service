import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {CommonModule} from '@angular/common';
import {IonicModule} from '@ionic/angular';
import {FormsModule} from "@angular/forms";

@Component({
  standalone: true,
  selector: 'app-activity',
  imports: [
    CommonModule,
    IonicModule,
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

  // ✅ NEW STRUCTURE: matches your backend
  newActivity = {
    userId: '22222222-2222-2222-2222-222222222222',
    title: '',
    description: '',
    category: '',
    type: '',
    status: '',
    scheduledDate: new Date().toISOString(),
    details: {
      schema: 'RunningMetrics',
      durationMinutes: 0,
      elapsedMinutes: 0,
      distanceKm: 0,
      elevationGain: 0,
      avgSpeed: 0,
      caloriesBurned: 0,
      perceivedExertion: 0,
      heartRateAvg: 0,
      cadence: 0,
      strideLength: 0,
      totalSets: null,
      totalReps: null,
      pagesRead: null,
      activityAchieved: false
    }
  };

  // ✅ FETCH ALL ACTIVITIES
  loadActivities() {
    this.loading = true;
    this.http.get<any[]>(`http://localhost:8080/api/activities`).subscribe({
      next: (data) => {
        this.activities = data;
        this.loading = false;
      },
      error: (err) => {
        console.error('Failed to load activities', err);
        this.loading = false;
      }
    });
  }

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    this.isDetail = !!id;

    if (this.isDetail && id) {
      this.loading = true;
      this.http.get(`http://localhost:8080/api/activities/${id}`).subscribe({
        next: (data) => {
          this.activity = data;
          this.loading = false;
        },
        error: (err) => {
          console.error('Failed to load activity detail', err);
          this.loading = false;
        }
      });
    } else {
      this.loadActivities();
    }
  }

  goToDetail(id: string) {
    console.log("goToDetail", id);
    this.router.navigate(['/activities', id]);
  }

  // ✅ POST new activity using the updated schema
  createActivity() {
    this.http.post('http://localhost:8080/api/activities/createActivity', this.newActivity)
      .subscribe({
        next: (createdActivity: any) => {
          this.activities.push(createdActivity);
          this.loadActivities();

          // ✅ Reset form
          this.newActivity = {
            userId: '22222222-2222-2222-2222-222222222222',
            title: '',
            description: '',
            category: '',
            type: '',
            status: '',
            scheduledDate: new Date().toISOString(),
            details: {
              schema: 'RunningMetrics',
              durationMinutes: 0,
              elapsedMinutes: 0,
              distanceKm: 0,
              elevationGain: 0,
              avgSpeed: 0,
              caloriesBurned: 0,
              perceivedExertion: 0,
              heartRateAvg: 0,
              cadence: 0,
              strideLength: 0,
              totalSets: null,
              totalReps: null,
              pagesRead: null,
              activityAchieved: false
            }
          };
        },
        error: (err) => {
          console.error('Failed to create activity', err);
        }
      });
  }
}
