package za.co.aurii.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LLMResponse {

    /**
     * The model name that generated the response.
     */
    private String model;

    /**
     * Timestamp of when the response was created.
     */
    @JsonProperty("created_at")
    private String createdAt;

    /**
     * The response content.
     * In streaming mode, this will be a chunk of the response.
     * In non-streaming mode, or for the final streaming object, this might be the full response
     * or empty if the response was fully streamed.
     */
    private String response;

    /**
     * Indicates if the request is complete.
     * For streaming responses, this is true for the last message in the stream.
     */
    private Boolean done;

    /**
     * (Optional) The reason the generation finished (e.g., "stop").
     * Typically present in the final response object.
     */
    @JsonProperty("done_reason")
    private String doneReason;

    /**
     * (Optional) An encoding of the conversation used in this response.
     * This can be sent in the next request to keep a conversational memory.
     * Typically present in the final response object.
     */
    private List<Integer> context;

    /**
     * (Optional) Time spent generating the response (nanoseconds).
     * Typically present in the final response object.
     */
    @JsonProperty("total_duration")
    private Long totalDuration;

    /**
     * (Optional) Time spent loading the model (nanoseconds).
     * Typically present in the final response object.
     */
    @JsonProperty("load_duration")
    private Long loadDuration;

    /**
     * (Optional) Number of tokens in the prompt.
     * Typically present in the final response object.
     */
    @JsonProperty("prompt_eval_count")
    private Integer promptEvalCount;

    /**
     * (Optional) Time spent evaluating the prompt (nanoseconds).
     * Typically present in the final response object.
     */
    @JsonProperty("prompt_eval_duration")
    private Long promptEvalDuration;

    /**
     * (Optional) Number of tokens in the response.
     * Typically present in the final response object.
     */
    @JsonProperty("eval_count")
    private Integer evalCount;

    /**
     * (Optional) Time spent generating the response (nanoseconds).
     * Typically present in the final response object.
     */
    @JsonProperty("eval_duration")
    private Long evalDuration;
}