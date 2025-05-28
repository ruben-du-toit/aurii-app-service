import {Component, OnInit} from '@angular/core';
import {IonRouterOutlet} from "@ionic/angular/standalone";

@Component({
  selector: 'app-coach',
  templateUrl: './coach.page.html',
  styleUrls: ['./coach.page.scss'],
  imports: [
    IonRouterOutlet,
  ]
})
export class CoachPage implements OnInit {

  constructor() {
  }

  ngOnInit() {
  }

}
