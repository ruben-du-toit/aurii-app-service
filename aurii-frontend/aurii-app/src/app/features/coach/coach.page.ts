import {Component, OnInit} from '@angular/core';
import {CoachHomeComponent} from "./coach-home/coach-home.component";
import {WizardComponent} from "./wizard/wizard.component";
import {PlanSummaryComponent} from "./plan-summary/plan-summary.component";

@Component({
  selector: 'app-coach',
  templateUrl: './coach.page.html',
  styleUrls: ['./coach.page.scss'],
  imports: [
    CoachHomeComponent,
    WizardComponent,
    PlanSummaryComponent,
  ]
})
export class CoachPage implements OnInit {
  condition: any = "summary";

  constructor() {
  }

  ngOnInit() {
  }

}
