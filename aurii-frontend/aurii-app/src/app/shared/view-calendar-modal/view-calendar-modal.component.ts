import {Component, ViewChild} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {IonButton, IonButtons, IonContent, IonHeader, IonModal, IonTitle, IonToolbar,} from '@ionic/angular/standalone';
import {OverlayEventDetail} from '@ionic/core/components';

@Component({
  selector: 'view-calendar-modal-app',
  templateUrl: 'view-calendar-modal.component.html',
  styleUrls: ['view-calendar-modal.component.scss'],
  imports: [
    FormsModule,
    IonButton,
    IonButtons,
    IonContent,
    IonHeader,
    IonModal,
    IonTitle,
    IonToolbar,
  ],
})
export class ViewCalendarModalComponent {
  @ViewChild(IonModal) modal!: IonModal;

  message = 'This modal example uses triggers to automatically open a modal when the button is clicked.';
  name!: string;

  cancel() {
    this.modal.dismiss(null, 'cancel');
  }

  confirm() {
    this.modal.dismiss(this.name, 'confirm');
  }

  onWillDismiss(event: CustomEvent<OverlayEventDetail>) {
    if (event.detail.role === 'confirm') {
      this.message = `Hello, ${event.detail.data}!`;
    }
  }
}
