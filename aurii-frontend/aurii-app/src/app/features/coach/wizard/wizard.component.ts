import {Component, OnInit} from '@angular/core';
import {IonButton, IonContent, IonLabel} from "@ionic/angular/standalone";
import {QuestionBase, QuestionMapper, Questionnaire} from "../../../models";
import {QuestionnaireService} from "../../../services";
import {LoggerService} from "../../../core/logger/logger.service";
import {AsyncPipe} from "@angular/common";
import {DynamicFormComponent} from "../../../shared/dynamic-form/dynamic-form.component";
import {map, Observable, of, startWith} from "rxjs";

@Component({
  selector: 'app-wizard',
  templateUrl: './wizard.component.html',
  styleUrls: ['./wizard.component.scss'],
  providers: [],
  imports: [
    IonContent,
    IonLabel,
    IonButton,
    DynamicFormComponent,
    AsyncPipe
  ]
})
export class WizardComponent implements OnInit {

  log: LoggerService = new LoggerService();
  plans: Questionnaire[] = [];
  showWizard: boolean = false;

  questions$: Observable<QuestionBase<string>[]> = of().pipe(
    startWith([])
  );

  constructor(private service: QuestionnaireService) {
  }

  ngOnInit() {
    this.service.getAllQuestionnaires().subscribe({
      next: (questionnaires: Questionnaire[]) => {
        this.plans = questionnaires;
        this.log.info(`Questionnaire response: ${JSON.stringify(questionnaires)}`);
      },
      error: (error) => {
        this.log.error('Error fetching questionnaires:', error);
      }
    });
  }

  buildWizard(plan: Questionnaire) {
    this.log.info(`Building wizard for plan: ${plan.title}`);
    this.showWizard = !this.showWizard;
    this.questions$ = of(QuestionMapper.mapQuestionsToQuestionBase(plan.sections[0].questions));

  }

}
