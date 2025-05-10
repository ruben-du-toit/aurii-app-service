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

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Metadata {
        private String author;
        private List<String> tags;
        private String category;
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