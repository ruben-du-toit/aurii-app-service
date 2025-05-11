# Aurii tech breakdown

# CI/CD
- GitHub 
- GitHub Actions for build pipeline

# Infrastructure

- Mobile devices (own phones/tablets)
- Backend hosting (AWS/Azure? K8s)
- Load Balancer
- 

## Modules

### Front End
Mobile Application
Tech Stack: Ionic + Angular
1. Link to Health provider (apple/android)
2. View Calendar
3. View Activity
4. Activity CRUD
5. Generate training plan:
   1. Retrieve & display Questionnaire
   2. Submit Questionnaire
   3. Training plan Summary/preview view**?
   4. Apply to plan calendar approval view?
6. Dashboard view

### Back End
Modulith Application
Tech Stack: Java + Spring Boot. Spring Modulith.

### User Module
- User Profile API
- Health Provider linking data?

#### Activity Module
- Calendar view (i.e. activities date range)
- Activity CRUD API 
- Utilizes the client provided by the [Health Module](#health-module) to retrieve activity data and populate the calendar
- Activity Datastore
- Retrieve Dashboard summary

#### Coach Module
- Workout plan Questionnaire API for retrieving and submitting questionnaires
- Houses the questionnaire configuration objects in resources directory
- Service for building prompts from submitted questionnaires
- Service for parsing AI response into objects for the activity module
- Utilizes the client provided by the [AI Interface Module](#ai-interface-module) to send a prompt and process the response
- Utilizes API provided by the [Activity Module](#activity-module) to populate planned activities to the calendar

#### Health Module
- Provide a single client for retrieving activity data from the user's select health data platform/s
- API for linking the app to a user's health provider


#### AI Interface Module
- Provides a client for interacting with an AI model/s
- Configuration to drive which client is used (e.g. Ollama vs OpenAI vs Anthropic)
