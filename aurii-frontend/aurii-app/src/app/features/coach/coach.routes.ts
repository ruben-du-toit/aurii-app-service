import {Routes} from '@angular/router';
import {CoachPage} from "./coach.page";

export const routes: Routes = [
  {
    path: '',
    component: CoachPage,
    children: [
      {
        path: 'home',
        loadComponent: () => import('./coach-home/coach-home.component').then(m => m.CoachHomeComponent)
      },
      {
        path: 'wizard',
        loadComponent: () => import('./wizard/wizard.component').then(m => m.WizardComponent)
      },
      {
        path: 'summary',
        loadComponent: () => import('./plan-summary/plan-summary.component').then(m => m.PlanSummaryComponent)
      },
      {
        path: '**',
        redirectTo: 'home',
        pathMatch: 'full',
      },
    ]
  },

];
