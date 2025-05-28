import {Component, OnInit} from '@angular/core';
import {IonButton, IonContent, IonLabel} from "@ionic/angular/standalone";
import {Questionnaire} from "../../../models";
import {QuestionnaireService} from "../../../services";
import {LoggerService} from "../../../core/logger/logger.service";

@Component({
  selector: 'app-wizard',
  templateUrl: './wizard.component.html',
  styleUrls: ['./wizard.component.scss'],
  imports: [
    IonContent,
    IonLabel,
    IonButton
  ]
})
export class WizardComponent implements OnInit {

  log: LoggerService = new LoggerService();
  plans: Questionnaire[] = [];
  showWizard: boolean = false;

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

  buildWizard(plan: any) {
    this.log.info(`Building wizard for plan: ${plan.name}`);
    this.showWizard = !this.showWizard;
  }
}
