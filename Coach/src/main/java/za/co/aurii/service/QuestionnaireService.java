package za.co.aurii.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import za.co.aurii.api.OllamaClient;
import za.co.aurii.dto.Questionnaire;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class QuestionnaireService {

    private final OllamaClient ollamaClient;

    public QuestionnaireService(final OllamaClient ollamaClient) {
        this.ollamaClient = ollamaClient;
    }

    public List<Questionnaire> getAllQuestionnaires() {
        try {
            Resource questionnairesDir = new ClassPathResource("questionnaires");
            File dir = questionnairesDir.getFile();
            ObjectMapper objectMapper = new ObjectMapper();
            File[] files = dir.listFiles();
            if (files == null) {
                log.error("Unable to list files in questionnaires directory");
                throw new RuntimeException("Unable to list questionnaires");
            }
            return Arrays.stream(files)
                    .filter(file -> file.isFile() && file.getName().endsWith(".json"))
                    .map(file -> {
                        try {
                            return objectMapper.readValue(file, Questionnaire.class);
                        } catch (IOException e) {
                            log.error("Error reading questionnaire file: {}", file.getName(), e);
                            throw new RuntimeException("Error reading questionnaire file: " + file.getName(), e);
                        }
                    })
                    .collect(Collectors.toList());
        } catch (IOException e) {
            log.error("Error accessing questionnaires directory", e);
            throw new RuntimeException("Error reading questionnaires directory", e);
        } catch (Exception e) {
            log.error("Unexpected error while reading questionnaires", e);
            throw new RuntimeException("Unexpected error while reading questionnaires", e);
        }
    }

    public Questionnaire getQuestionnaireById(String id) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Resource resource = new ClassPathResource("questionnaires/" + id + ".json");
            return objectMapper.readValue(resource.getInputStream(), Questionnaire.class);
        } catch (IOException e) {
            throw new RuntimeException("Error reading questionnaire file: " + id, e);
        }
    }

    public String processSubmittedQuestionnaire(Questionnaire questionnaire) {
        // TODO: enhance prompt builder to include before/after sections to the prompt as well as a section for asking the LLM to return
        //  the response in a format that can be serialized into JSON for the calendar app
        String prompt = questionnaire.generatePrompt();
        log.info("Consolidated prompt: {}", prompt);
        String response = ollamaClient.sendRequest(prompt).getResponse();
        log.info("LLM Response: {}", response);
        return response;
    }
}
