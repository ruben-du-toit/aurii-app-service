package za.co.aurii.dto;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QuestionnaireTest {

    @Test
    void testQuestionnaireCreation() {
        // Create a Question
        Questionnaire.Question question = new Questionnaire.Question(
                "q1",
                "How are you feeling today?",
                "Enter your response here",
                "Please be honest about your feelings",
                ""
        );

        // Create Metadata
        Questionnaire.Metadata metadata = new Questionnaire.Metadata(
                "Dr. Smith",
                Arrays.asList("mental health", "daily check-in"),
                "wellness"
        );

        // Create a Questionnaire with the question
        List<Questionnaire.Question> questions = Arrays.asList(question);
        Questionnaire questionnaire = new Questionnaire(
                "quest123",
                "Daily Wellness Check",
                "A questionnaire to track your daily mental wellness",
                "1.0",
                metadata,
                questions
        );

        // Assertions
        assertEquals("quest123", questionnaire.getId());
        assertEquals("Daily Wellness Check", questionnaire.getTitle());
        assertEquals("A questionnaire to track your daily mental wellness", questionnaire.getDescription());
        assertEquals("1.0", questionnaire.getVersion());
        
        // Metadata assertions
        assertEquals("Dr. Smith", questionnaire.getMetadata().getAuthor());
        assertEquals(2, questionnaire.getMetadata().getTags().size());
        assertTrue(questionnaire.getMetadata().getTags().contains("mental health"));
        assertEquals("wellness", questionnaire.getMetadata().getCategory());
        
        // Question assertions
        assertEquals(1, questionnaire.getQuestions().size());
        assertEquals("q1", questionnaire.getQuestions().get(0).getId());
        assertEquals("How are you feeling today?", questionnaire.getQuestions().get(0).getText());
        assertEquals("Enter your response here", questionnaire.getQuestions().get(0).getPlaceholder());
        assertEquals("Please be honest about your feelings", questionnaire.getQuestions().get(0).getPrompt());
        assertEquals("", questionnaire.getQuestions().get(0).getAnswer());
    }

    @Test
    void testQuestionnaireSetters() {
        // Create an empty questionnaire and use setters
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setId("quest456");
        questionnaire.setTitle("Weekly Progress");
        questionnaire.setDescription("Track your weekly fitness progress");
        questionnaire.setVersion("2.1");
        
        // Set metadata
        Questionnaire.Metadata metadata = new Questionnaire.Metadata();
        metadata.setAuthor("Coach Johnson");
        metadata.setTags(Arrays.asList("fitness", "progress"));
        metadata.setCategory("health");
        questionnaire.setMetadata(metadata);
        
        // Set questions
        Questionnaire.Question question = new Questionnaire.Question();
        question.setId("q2");
        question.setText("How many workouts did you complete this week?");
        question.setPlaceholder("Enter number");
        question.setPrompt("Be accurate with your count");
        question.setAnswer("5");
        
        questionnaire.setQuestions(Arrays.asList(question));
        
        // Assertions
        assertEquals("quest456", questionnaire.getId());
        assertEquals("Weekly Progress", questionnaire.getTitle());
        assertEquals("Track your weekly fitness progress", questionnaire.getDescription());
        assertEquals("2.1", questionnaire.getVersion());
        
        // Metadata assertions
        assertEquals("Coach Johnson", questionnaire.getMetadata().getAuthor());
        assertEquals(2, questionnaire.getMetadata().getTags().size());
        assertTrue(questionnaire.getMetadata().getTags().contains("fitness"));
        assertEquals("health", questionnaire.getMetadata().getCategory());
        
        // Question assertions
        assertEquals(1, questionnaire.getQuestions().size());
        assertEquals("q2", questionnaire.getQuestions().get(0).getId());
        assertEquals("How many workouts did you complete this week?", questionnaire.getQuestions().get(0).getText());
        assertEquals("Enter number", questionnaire.getQuestions().get(0).getPlaceholder());
        assertEquals("Be accurate with your count", questionnaire.getQuestions().get(0).getPrompt());
        assertEquals("5", questionnaire.getQuestions().get(0).getAnswer());
    }
}