import {Component, inject, input, OnInit} from '@angular/core';
import {FormGroup, ReactiveFormsModule} from '@angular/forms';
import {QuestionControlService} from "../../services";
import {DynamicFormQuestionComponent} from "./dynamic-form-question/dynamic-form-question.component";
import {QuestionBase} from "../../models";
import {IonButton, IonContent, IonItem, IonLabel} from "@ionic/angular/standalone";

@Component({
  selector: 'app-dynamic-form',
  templateUrl: './dynamic-form.component.html',
  providers: [QuestionControlService],
  imports: [DynamicFormQuestionComponent, ReactiveFormsModule, IonButton, IonContent, IonItem, IonLabel],
})
export class DynamicFormComponent implements OnInit {
  private readonly qcs = inject(QuestionControlService);
  questions = input<QuestionBase<string>[] | null>([]);

  form: any | FormGroup;
  payLoad = '';

  ngOnInit(): void {
    this.form = this.qcs.toFormGroup(this.questions() as QuestionBase<string>[]);
  }

  onSubmit() {
    this.payLoad = JSON.stringify(this.form.getRawValue());
  }
}
