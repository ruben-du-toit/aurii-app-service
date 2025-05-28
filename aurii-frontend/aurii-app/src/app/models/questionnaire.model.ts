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
  systemPrompt?: string
  activityStructure?: string
}

export interface Section {
  id: string;
  questions: Question[];
}

export interface Question {
  id: string;
  text: string;
  placeholder: string;
  prompt: string;
  answer: string;
}
