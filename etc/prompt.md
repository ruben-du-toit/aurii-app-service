# PRD/Initial prompt
I want to create a service that acts as a single component within a broader application. I want to create it within the current template project I am in which uses Java and Spring Boot.

Leveraging Spring Boot's features, Let's build the project using the following spec:
- The key data model for the service is the Questionnaire.
  - id: string
  - title: string
  - description: string
  - version: string
  - Metadata: object
    - Metadata fields:
      - author
      - tags
      - category
  - Questions: list of objects
  - Each Question should have the following string fields
    - id: string
    - text: string
    - placeholder: string
    - prompt: string
    - answer: string 
- The service should expose a REST API:

- The first endpoint should allow the user to GET a list of questionnaires
- The second endpoint is for the client to retrieve a single structured questionnaire object by id. Questionnaires are to be retrieved from json files in the `resources/questionnaires` directory. Each questionnaire file will follow the naming convention {questionnaire-id}.json.
- Do not create an API for capturing and storing new questionnaire files.
- The third endpoint should allow the client to submit a completed questionnaire to the service. The completed questionnaire should be the same object structure as the questionnaire provided by the first endpoint. The only difference is the Questions answer fields will be populated.
- On submission, the api should call a method on the Questionnaire object to generate a prompt string which will be used in a request to an LLM.
  - When building the prompt:
  - Iterate through all questions
    - if the question has no answer or a blank answer, skip the question
    - if the question has an answer that is not blank, invoke a `buildPromptSection` method. This method should replace the `{answer}` placeholder in the promptTemplate with the provided answer text.
    - Append each prompt section to a consolidated prompt field on the Questionnaire object
    - Between questions, ensure that a newline is appended to the consolidated prompt.
- After the prompt has been built, call the LLM client to submit the prompt to the LLM
- Log the returned prompt response text using 1an slf4j logger
- The LLM Client will need to be configurable:
  - the first type of LLMClient will be an OllamaClient
  - Spring needs to autowire the LLMClient bean based on spring boot configuration pulled from an application.yaml file
- Use a modified vertical slice implementation approach that's suitable for LLM-assisted coding.

With this in mind, I want you to first evaluate the project template and think about a few possible PRD approaches before landing on the best one. Provide reasoning why this would be the best approach. Remember we're using Java and Spring Boot, we may need to include extra libraries to do some of the heavy lifting for us, we don't need to create an enterprise-level app (this is a side project for personal use), and we want to use a vertical slice implementation approach so we can start with basic implementations of features first, and add on complexity from there.

# Plan prompt
From this PRD, create an actionable, step-by-step plan that we can use as a guide for LLM-assisted coding. Before you create the plan, think about a few different plan styles that would be suitable for this project and the implementation style before selecting the best one. Give your reasoning for why you think we should use this plan style. Remember that we will constantly refer to this plan to guide our coding implementation so it should be well structured, concise, and actionable, while still providing enough information to guide the LLM.

# Doc Prompt
Great. Now that we've finished implementing that phase can you please document it in ai/docs according to the predefined conventions. Remember you don't need to repeat info that already exists in project files, rather describe the implementation--and point to the file where the implementation can be found--so that it can be referenced by human


# Revisions for question->template-> prompt conversion
Based on the vertical slice implementation we used to first write the  @PRD.md , refactor the @PRD.md  to accurately represent these new requirements: @prompt.md (lines).

# Revise Plan
From this @PRD, refactor the @plan to accurately represent the changed requirements. Remember that the completed steps must remain in the plan as-is. Only refactor upcoming steps. To change steps that have already been implemented, add steps to perform the required modifications. Remember that this plan needs to be an  actionable, step-by-step plan that we can use as a guide for LLM-assisted coding.

<!-- Note: either as a rule or as the initial prompt, look at the testing approach, especially when using files or mock data. Ensure that there are implementation steps for generating appropriate test data before writing test. Also add a step for generating the testing plan for approval before proceeding -->