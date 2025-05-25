import { Routes } from '@angular/router';
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
        path: '',
        redirectTo: 'home',
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
