import {Component, OnInit} from '@angular/core';
import {IonCard, IonCardContent, IonCardHeader, IonCardTitle, IonDatetime} from "@ionic/angular/standalone";

@Component({
  selector: 'app-calendar',
  templateUrl: './calendar.page.html',
  styleUrls: ['./calendar.page.scss'],
  imports: [
    IonDatetime,
    IonCardContent,
    IonCardTitle,
    IonCardHeader,
    IonCard
  ]
})
export class CalendarPage implements OnInit {

  constructor() { }

  ngOnInit() {}

  // async openDateModal(event: any) {
  //   const selectedDate = event.detail.value;
  //   console.log("Date selected ", selectedDate);
  //   const modal = await this.modalCtrl.create({
  //     component: ViewCalendarModalComponent,
  //     componentProps: { date: selectedDate },
  //     breakpoints: [0, 0.4],
  //     initialBreakpoint: 0.4
  //   });
  //
  //   await modal.present();
  // }


}
