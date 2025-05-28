import { Component, OnInit } from '@angular/core';
import {Router, RouterLink} from "@angular/router";
import {LoggerService} from "../../../core/logger/logger.service";
import {IonButton, IonContent, IonLabel} from "@ionic/angular/standalone";

@Component({
  selector: 'coach-home',
  templateUrl: './coach-home.component.html',
  styleUrls: ['./coach-home.component.scss'],
  imports: [
    IonContent,
    IonLabel,
    IonButton
  ]
})
export class CoachHomeComponent implements OnInit {

  log: LoggerService = new LoggerService();

  constructor(private router: Router) { }

  ngOnInit() {}

  onPlanTypeSelected(planType: string) {
    this.log.info(`Selected plan type: ${planType}`);
    this.router.navigate(['/coach/wizard']);
  }

}
