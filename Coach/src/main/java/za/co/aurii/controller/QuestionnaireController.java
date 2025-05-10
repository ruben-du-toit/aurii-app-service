package za.co.aurii.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.aurii.service.QuestionnaireService;
import za.co.aurii.dto.Questionnaire;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/questionnaire")
@RequiredArgsConstructor
public class QuestionnaireController {

    private final QuestionnaireService questionnaireService;

    @GetMapping
    public ResponseEntity<List<Questionnaire>> getAllQuestionnaires() {
        return ResponseEntity.ok(questionnaireService.getAllQuestionnaires());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Questionnaire> getQuestionnaireById(@PathVariable String id) {
        return ResponseEntity.ok(questionnaireService.getQuestionnaireById(id));
    }

    @PostMapping
    public ResponseEntity<String> submitQuestionnaire(@Valid @RequestBody Questionnaire questionnaire) {
        String prompt = questionnaireService.processSubmittedQuestionnaire(questionnaire);
        return ResponseEntity.ok(prompt);
    }
}