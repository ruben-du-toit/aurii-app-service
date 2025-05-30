import {Component, input} from '@angular/core';
import {FormGroup, ReactiveFormsModule} from '@angular/forms';
import {QuestionBase} from "../../../models";
import {IonInput, IonItem, IonLabel, IonList, IonSelect, IonSelectOption} from "@ionic/angular/standalone";


@Component({
  selector: 'app-question',
  templateUrl: './dynamic-form-question.component.html',
  styleUrls: ['./dynamic-form-question.component.scss'],
  imports: [ReactiveFormsModule, IonList, IonItem, IonLabel, IonInput, IonSelect, IonSelectOption],
})
export class DynamicFormQuestionComponent {

  question = input.required<QuestionBase<string>>();
  form = input.required<FormGroup>();

  get isValid() {
    return this.form()?.controls[this.question()?.key]?.valid;
  }

}
