package za.co.aurii.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import za.co.aurii.dto.LLMRequest;
import za.co.aurii.dto.LLMResponse;

import jakarta.annotation.PostConstruct;

@Service
public class OllamaClient implements LLMClient {

    @Value("${llm.client:ollama}")
    private String clientType;

    @Value("${llm.apiUrl:http://localhost:11434}")
    private String baseApiUrl;

    @Value("${llm.apiKey:}")
    private String apiKey; // Not used by Ollama /generate but kept for consistency

    @Value("${llm.defaultModel:llama3}")
    private String defaultModel;

    private final WebClient webClient;
    private String ollamaGenerateEndpointUrl;

    public OllamaClient(final WebClient webClient) {
        this.webClient = webClient;
    }

    @PostConstruct
    private void initialize() {
        if (this.baseApiUrl == null || this.baseApiUrl.trim().isEmpty()) {
            System.err.println("Warning: Ollama API base URL is not configured. Using default http://localhost:11434");
            this.baseApiUrl = "http://localhost:11434";
        }

        String sanitizedBaseUrl = this.baseApiUrl.trim();
        if (sanitizedBaseUrl.endsWith("/")) {
            this.ollamaGenerateEndpointUrl = sanitizedBaseUrl + "api/generate";
        } else {
            this.ollamaGenerateEndpointUrl = sanitizedBaseUrl + "/api/generate";
        }

        if (this.defaultModel == null || this.defaultModel.trim().isEmpty()) {
            System.err.println("Warning: Default LLM model is not configured. This might lead to errors if requests don't specify a model.");
        }
        System.out.println("OllamaClient initialized with WebClient. API endpoint: " + this.ollamaGenerateEndpointUrl + ", Default model: " + this.defaultModel);
    }

    @Override
    public LLMResponse sendRequest(String prompt) {
        if (prompt == null || prompt.trim().isEmpty()) {
            throw new IllegalArgumentException("Prompt cannot be null or empty.");
        }
        if (this.defaultModel == null || this.defaultModel.trim().isEmpty()) {
            throw new IllegalStateException("Default model is not configured, cannot send request.");
        }

        LLMRequest request = new LLMRequest();
        request.setModel(this.defaultModel);
        request.setPrompt(prompt);
        request.setStream(false);
        // request.setFormat("json"); // Uncomment if you want to enforce JSON output format

        try {
            String logPrompt = prompt.substring(0, Math.min(prompt.length(), 50)) + (prompt.length() > 50 ? "..." : "");
            System.out.println("Sending request to Ollama (" + this.ollamaGenerateEndpointUrl + ") with model '" + request.getModel() + "' and prompt: '" + logPrompt + "'");

            LLMResponse llmResponse = webClient.post()
                    .uri(this.ollamaGenerateEndpointUrl)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .body(Mono.just(request), LLMRequest.class)
                    .retrieve()
                    .bodyToMono(LLMResponse.class)
                    .block(); // Block to maintain synchronous behavior

            // System.out.println("Received response from Ollama: " + (llmResponse != null ? llmResponse.getResponse() : "null"));
            return llmResponse;

        } catch (WebClientResponseException e) {
            System.err.println("Error sending request to Ollama API (" + this.ollamaGenerateEndpointUrl + ") for model " + request.getModel() + ". Status: " + e.getStatusCode() + ", Body: " + e.getResponseBodyAsString());
            System.err.println("Full WebClientResponseException details: " + e.toString());
            throw new RuntimeException("Failed to communicate with Ollama API: " + e.getStatusCode() + ", " + e.getResponseBodyAsString(), e);
        } catch (Exception e) {
            // This can catch errors from .block() or other unexpected issues
            System.err.println("Unexpected exception during Ollama request for model " + request.getModel() + ": " + e.getMessage());
            throw new RuntimeException("Unexpected error while processing Ollama request: " + e.getMessage(), e);
        }
    }
}