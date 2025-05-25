import { Component, OnInit } from '@angular/core';
import {IonIcon, IonLabel, IonTabBar, IonTabButton, IonTabs} from "@ionic/angular/standalone";
import {addIcons} from "ionicons";
import {addCircle, calendar, home, person} from "ionicons/icons";

@Component({
  selector: 'app-tab-nav-bar',
  templateUrl: './tab-nav-bar.component.html',
  styleUrls: ['./tab-nav-bar.component.scss'],
  imports: [
    IonTabs,
    IonTabBar,
    IonTabButton,
    IonIcon,
    IonLabel
  ]
})
export class TabNavBarComponent  implements OnInit {

  constructor() {
    addIcons({ home, calendar, addCircle , person });
  }

  ngOnInit() {}

}
