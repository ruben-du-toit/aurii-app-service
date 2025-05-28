import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {environment} from "../../../environments/environment";
import {Questionnaire} from "../../models";

@Injectable({
  providedIn: 'root'
})
export class QuestionnaireService {

  constructor(private http: HttpClient) {
  }

  private readonly base_url: string = environment.coachServiceUrl + '/questionnaire';

  private readonly httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  getAllQuestionnaires(): Observable<Questionnaire[]> {
    return this.http.get<Questionnaire[]>(this.base_url);
  }

  getQuestionnaireById(id: string): Observable<Questionnaire> {
    return this.http.get<Questionnaire>(`${this.base_url}/${id}`);
  }

  submitQuestionnaire(questionnaire: Questionnaire): Observable<string> {
    return this.http.post<string>(this.base_url, questionnaire, this.httpOptions);
  }
}
