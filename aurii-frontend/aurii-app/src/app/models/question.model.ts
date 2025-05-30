import {QuestionBase} from './question-base';
import { Question } from './questionnaire.model';

export class TextboxQuestion extends QuestionBase<string> {
  override controlType = 'textbox';
}

export class DropdownQuestion extends QuestionBase<string> {
  override controlType = 'dropdown';
}

export class QuestionMapper {
  /**
   * Maps an array of Question interfaces to an array of QuestionBase objects
   * @param questions Array of Question interfaces to map
   * @returns Array of QuestionBase objects
   */
  static mapQuestionsToQuestionBase(questions: Question[]): QuestionBase<any>[] {
    if (!questions || questions.length === 0) {
      return [];
    }

    return questions.map(question => this.mapSingleQuestion(question));
  }

  /**
   * Maps a single Question interface to a QuestionBase object
   * @param question Single Question interface to map
   * @returns QuestionBase object
   */
  static mapSingleQuestion(question: Question): QuestionBase<any> {
    return new QuestionBase({
      key: question.id,
      label: question.label,
      order: question.order,
      controlType: question.controlType,
      options: question.options,
      value: question.answer,
      // You can set additional defaults here if needed
      required: false, // Default since it's not in the Question interface
      type: question.controlType // Using controlType as type, adjust as needed
    });
  }

  /**
   * Maps an array of QuestionBase objects back to Question interfaces
   * @param questionBases Array of QuestionBase objects to map
   * @returns Array of Question interfaces
   */
  static mapQuestionBaseToQuestions(questionBases: QuestionBase<any>[]): Question[] {
    if (!questionBases || questionBases.length === 0) {
      return [];
    }

    return questionBases.map(questionBase => this.mapSingleQuestionBase(questionBase));
  }

  /**
   * Maps a single QuestionBase object to a Question interface
   * @param questionBase Single QuestionBase object to map
   * @returns Question interface
   */
  static mapSingleQuestionBase(questionBase: QuestionBase<any>): Question {
    return {
      id: questionBase.key,
      label: questionBase.label,
      controlType: questionBase.controlType,
      placeholder: '', // Default since it's not in QuestionBase
      prompt: undefined, // Optional field
      answer: questionBase.value?.toString(),
      order: questionBase.order,
      options: questionBase.options
    };
  }
}
