package za.co.aurii.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Questionnaire {
    private String id;
    private String title;
    private String description;
    private String version;
    private Metadata metadata;
    private List<Question> questions;

    public String generatePrompt() {
        // TODO: Replace with strategy pattern for each answer to allow for richer prompt generation
        StringBuilder consolidatedPrompt = new StringBuilder();
        consolidatedPrompt.append(this.getMetadata().getSystemPrompt());
        consolidatedPrompt.append(this.getMetadata().getActivityStructure());
        consolidatedPrompt.append("\n");

        for (Question question : questions) {
            if (question.getAnswer() != null && !question.getAnswer().trim().isEmpty()) {
                String promptSection = buildPromptSection(question);
                consolidatedPrompt.append(promptSection).append("\n");
            }
        }

        return consolidatedPrompt.toString();
    }

    private String buildPromptSection(Question question) {
        return question.getPrompt().replace("{answer}", question.getAnswer());
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Metadata {
        private String author;
        private List<String> tags;
        private String category;
        private String systemPrompt;
        private Object activityStructure = "{\n" +
                "  \"trainingPlan\": {\n" +
                "    \"metadata\": {\n" +
                "      \"id\": \"unique-identifier\",\n" +
                "      \"name\": \"Plan Name\",\n" +
                "      \"sport\": \"Basketball\",\n" +
                "      \"level\": \"Intermediate\",\n" +
                "      \"description\": \"A comprehensive 12-week program focused on improving strength and agility\",\n" +
                "      \"author\": \"Coach Name\",\n" +
                "      \"createdDate\": \"2025-05-20\",\n" +
                "      \"lastModified\": \"2025-05-20\",\n" +
                "      \"tags\": [\"basketball\", \"agility\", \"strength\"]\n" +
                "    },\n" +
                "    \"athlete\": {\n" +
                "      \"id\": \"athlete-id\",\n" +
                "      \"name\": \"Athlete Name\",\n" +
                "      \"age\": 25,\n" +
                "      \"goals\": [\"Improve vertical jump\", \"Increase shooting accuracy\"],\n" +
                "      \"constraints\": [\n" +
                "        {\n" +
                "          \"type\": \"injury\",\n" +
                "          \"description\": \"Recovering from mild ankle sprain\",\n" +
                "          \"accommodations\": \"Limit high-impact exercises for first 2 weeks\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"baselineMetrics\": {\n" +
                "      }\n" +
                "    },\n" +
                "    \"schedule\": {\n" +
                "      \"duration\": {\n" +
                "        \"value\": 12,\n" +
                "        \"unit\": \"weeks\"\n" +
                "      },\n" +
                "      \"phases\": [\n" +
                "        {\n" +
                "          \"id\": \"phase-1\",\n" +
                "          \"name\": \"Foundation Building\",\n" +
                "          \"description\": \"Focus on fundamental movement patterns and base conditioning\",\n" +
                "          \"duration\": {\n" +
                "            \"value\": 4,\n" +
                "            \"unit\": \"weeks\"\n" +
                "          },\n" +
                "          \"weeks\": [\n" +
                "            {\n" +
                "              \"weekNumber\": 1,\n" +
                "              \"focus\": \"Movement fundamentals\",\n" +
                "              \"days\": [\n" +
                "                {\n" +
                "                  \"dayNumber\": 1,\n" +
                "                  \"date\": \"2025-05-21\",\n" +
                "                  \"type\": \"training\",\n" +
                "                  \"focus\": [\"strength\", \"technique\"],\n" +
                "                  \"workouts\": [\n" +
                "                    {\n" +
                "                      \"id\": \"workout-1-1\",\n" +
                "                      \"name\": \"Morning Strength Session\",\n" +
                "                      \"exercises\": [\n" +
                "                        {\n" +
                "                          \"id\": \"exercise-1\",\n" +
                "                          \"name\": \"Barbell Squat\",\n" +
                "                          \"category\": \"strength\",\n" +
                "                          \"equipment\": [\"barbell\", \"rack\"],\n" +
                "                          \"targetMuscleGroups\": [\"quadriceps\", \"glutes\", \"hamstrings\"],\n" +
                "                          \"sets\": 4,\n" +
                "                          \"reps\": 8,\n" +
                "                          \"weight\": \"70% 1RM\",\n" +
                "                          \"notes\": \"Focus on depth and proper form\"\n" +
                "                        }\n" +
                "                      ]\n" +
                "                    },\n" +
                "                    {\n" +
                "                      \"id\": \"workout-1-2\",\n" +
                "                      \"name\": \"Afternoon Skills Session\",\n" +
                "                      \"drills\": [\n" +
                "                        {\n" +
                "                          \"id\": \"drill-1\",\n" +
                "                          \"name\": \"Shooting Drills\",\n" +
                "                          \"description\": \"Free throw practice followed by catch and shoot\",\n" +
                "                          \"intensity\": \"moderate\",\n" +
                "                          \"metrics\": {\n" +
                "                            \"target\": \"Make 8/10 free throws consecutively\"\n" +
                "                          }\n" +
                "                        }\n" +
                "                      ]\n" +
                "                    }\n" +
                "                  ],\n" +
                "                  \"nutrition\": {\n" +
                "                    \"calorieTarget\": 2800,\n" +
                "                    \"macros\": {\n" +
                "                    },\n" +
                "                    \"supplements\": [\n" +
                "                      {\n" +
                "                        \"name\": \"Protein Shake\",\n" +
                "                        \"timing\": \"Post-workout\",\n" +
                "                        \"amount\": \"25g\"\n" +
                "                      }\n" +
                "                    ]\n" +
                "                  },\n" +
                "                  \"recovery\": {\n" +
                "                    \"techniques\": [\n" +
                "                      {\n" +
                "                        \"name\": \"Foam Rolling\",\n" +
                "                        \"focusAreas\": [\"quads\", \"IT band\"]\n" +
                "                      }\n" +
                "                    ]\n" +
                "                  }\n" +
                "                }\n" +
                "              ]\n" +
                "            }\n" +
                "          ]\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    \"progressTracking\": {\n" +
                "      \"assessmentSchedule\": [\n" +
                "        {\n" +
                "          \"id\": \"assessment-1\",\n" +
                "          \"name\": \"Baseline Assessment\",\n" +
                "          \"timing\": \"Pre-program\",\n" +
                "          \"metrics\": [\"verticalJump\", \"sprintTime\", \"maxBenchPress\"]\n" +
                "        },\n" +
                "        {\n" +
                "          \"id\": \"assessment-2\",\n" +
                "          \"name\": \"Mid-program Assessment\",\n" +
                "          \"timing\": \"Week 6\",\n" +
                "          \"metrics\": [\"verticalJump\", \"sprintTime\", \"maxBenchPress\"]\n" +
                "        }\n" +
                "      ],\n" +
                "      \"results\": [\n" +
                "        {\n" +
                "          \"assessmentId\": \"assessment-1\",\n" +
                "          \"date\": \"2025-05-20\",\n" +
                "          \"metrics\": {\n" +
                "            \"verticalJump\": 24,\n" +
                "            \"sprintTime\": 12.3,\n" +
                "            \"maxBenchPress\": 80\n" +
                "          },\n" +
                "          \"notes\": \"Baseline established\"\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    \"adaptations\": {\n" +
                "      \"rules\": [\n" +
                "        {\n" +
                "          \"trigger\": {\n" +
                "            \"metric\": \"rpe\",\n" +
                "            \"condition\": \"averageOver\",\n" +
                "            \"threshold\": 8\n" +
                "          },\n" +
                "          \"action\": {\n" +
                "            \"type\": \"reduce\",\n" +
                "            \"target\": \"intensity\",\n" +
                "            \"amount\": \"10%\",\n" +
                "            \"duration\": \"nextWorkout\"\n" +
                "          }\n" +
                "        },\n" +
                "        {\n" +
                "          \"trigger\": {\n" +
                "            \"metric\": \"completionRate\",\n" +
                "            \"condition\": \"lessThan\"\n" +
                "          },\n" +
                "          \"action\": {\n" +
                "            \"type\": \"modify\",\n" +
                "            \"target\": \"volume\",\n" +
                "            \"amount\": \"-15%\",\n" +
                "            \"duration\": \"nextWeek\"\n" +
                "          }\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  }\n" +
                "}";
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Question {
        private String id;
        private String text;
        private String placeholder;
        private String prompt;
        private String answer;
    }
    
    
}