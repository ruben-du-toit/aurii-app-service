package za.co.aurii.api;

import za.co.aurii.dto.LLMRequest;
import za.co.aurii.dto.LLMResponse;

public interface LLMClient {
    public LLMResponse sendRequest(String prompt);
}
