import {Routes} from '@angular/router';
import {TabNavBarComponent} from "./shared/tab-nav-bar/tab-nav-bar.component";

export const routes: Routes = [
  {
    path: 'home',
    component: TabNavBarComponent,
    children: [
      {
        path: 'dashboard',
        loadComponent: () => import('./features/dashboard/dashboard.page').then(m => m.DashboardPage)
      },
      {
        path: 'calendar',
        loadComponent: () => import('./features/calendar/calendar.page').then(m => m.CalendarPage)
      },
      {
        path: 'coach',
        loadChildren: () => import('./features/coach/coach.routes').then(m => m.routes)
      },
      {
        path: 'profile',
        loadComponent: () => import('./features/profile/profile.page').then(m => m.ProfilePage)
      },
      {
        path: '',
        redirectTo: 'dashboard',
        pathMatch: 'full',
      },
    ]
  },
  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full',
  }
];
