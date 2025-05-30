export interface Questionnaire {
  id : string;
  title : string;
  description : string;
  version : string;
  metadata: MetaData;
  sections: Section[];
}

export interface MetaData {
  author: string
  tags: string[]
  category: string
}

export interface Section {
  id: string;
  questions: Question[];
}

export interface Question {
  id: string;
  label: string;
  controlType: string;
  placeholder: string;
  prompt?: string;
  answer?: string;
  order: number;
  options: {key: string; value: string}[];
}
