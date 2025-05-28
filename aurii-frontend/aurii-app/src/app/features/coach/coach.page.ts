import {Component, OnInit} from '@angular/core';
import {RouterOutlet} from "@angular/router";

@Component({
  selector: 'app-coach',
  templateUrl: './coach.page.html',
  styleUrls: ['./coach.page.scss'],
  imports: [
    RouterOutlet,
  ]
})
export class CoachPage implements OnInit {

  constructor() {
  }

  ngOnInit() {
  }

}
