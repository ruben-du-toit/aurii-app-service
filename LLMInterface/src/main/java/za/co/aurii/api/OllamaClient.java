package za.co.aurii.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
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
    private String apiKey; // Not used by Ollama /generate but kept for consistency if needed elsewhere

    @Value("${llm.defaultModel:llama3}")
    private String defaultModel;

    private final RestTemplate restTemplate;
    private String ollamaGenerateEndpointUrl;

    public OllamaClient(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostConstruct
    private void initialize() {
        if (this.baseApiUrl == null || this.baseApiUrl.trim().isEmpty()) {
            System.err.println("Warning: Ollama API base URL is not configured. Using default http://localhost:11434");
            // Defaulting baseApiUrl if somehow null after @Value (though @Value should prevent this with defaults)
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
            // Potentially throw an IllegalStateException if defaultModel is critical for all operations
            // throw new IllegalStateException("Default LLM model must be configured.");
        }
        System.out.println("OllamaClient initialized. API endpoint: " + this.ollamaGenerateEndpointUrl + ", Default model: " + this.defaultModel);
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
        // Ensure the request is for a single response, not a stream.
        request.setStream(false); 
        // request.setFormat("json"); // Uncomment if you want to enforce JSON output format from Ollama

        // Other potential default parameters for LLMRequest can be set here if needed, e.g.:
        // request.setOptions(new HashMap<>()); // To set temperature, top_k, etc.
        // request.setSystem("You are a helpful assistant."); // System prompt

        try {
            System.out.println("Sending request to Ollama (" + this.ollamaGenerateEndpointUrl + ") with model '" + request.getModel() + "' and prompt: '" + request.getPrompt().substring(0, Math.min(request.getPrompt().length(), 50)) + "...'");

            LLMResponse llmResponse = restTemplate.postForObject(
                    this.ollamaGenerateEndpointUrl,
                    request,
                    LLMResponse.class
            );

            // System.out.println("Received response from Ollama: " + (llmResponse != null ? llmResponse.getResponse() : "null"));
            return llmResponse;

        } catch (RestClientException e) {
            System.err.println("Error sending request to Ollama API (" + this.ollamaGenerateEndpointUrl + ") for model " + request.getModel() + ": " + e.getMessage());
            throw new RuntimeException("Failed to communicate with Ollama API: " + e.getMessage(), e);
        } catch (Exception e) {
            System.err.println("Unexpected exception during Ollama request for model " + request.getModel() + ": " + e.getMessage());
            throw new RuntimeException("Unexpected error while processing Ollama request", e);
        }
    }
}