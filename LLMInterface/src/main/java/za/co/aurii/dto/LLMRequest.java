package za.co.aurii.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LLMRequest {
    /**
     * (Required) The model name.
     * Model names follow a `model:tag` format.
     */
    private String model;

    /**
     * The prompt to generate a response for.
     */
    private String prompt;

    /**
     * The text that comes after the model's response.
     */
    private String suffix;

    /**
     * (Optional) A list of base64-encoded images (for multimodal models).
     */
    private List<String> images;

    /**
     * (Optional) The format to return a response in. Can be "json" or a JSON schema object.
     */
    private Object format;

    /**
     * (Optional) Additional model parameters like temperature, etc.
     */
    private Map<String, Object> options;

    /**
     * (Optional) System message to (overrides what is defined in the Modelfile).
     */
    private String system;

    /**
     * (Optional) The prompt template to use (overrides what is defined in the Modelfile).
     */
    private String template;

    /**
     * (Optional) If false, the response will be returned as a single response object,
     * rather than a stream of objects. Defaults to true.
     */
    private Boolean stream;

    /**
     * (Optional) If true, no formatting will be applied to the prompt.
     */
    private Boolean raw;

    /**
     * (Optional) Controls how long the model will stay loaded into memory following the request (e.g., "5m").
     * Default: "5m".
     */
    private String keep_alive;

    /**
     * (Deprecated) The context parameter returned from a previous request to /generate,
     * this can be used to keep a short conversational memory.
     */
    private List<Integer> context;
}