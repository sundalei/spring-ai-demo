package com.example.chat.huggingface;

import java.io.IOException;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpHeaders;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

@Service
@Slf4j
public class HuggingFaceService {

  private static final String HUGGINGFACE_BASE_API_URL =
      "https://router.huggingface.co/hf-inference/models";
  private static final String HUGGINGFACE_MODEL = "facebook/bart-large-cnn";

  @Value("${huggingface.api-key}")
  private String huggingfaceApiKey;

  private final ObjectMapper objectMapper;

  public HuggingFaceService(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  public String chat(String prompt) {
    try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
      var request = buildRequest(prompt);
      return httpClient.execute(request, resp -> EntityUtils.toString(resp.getEntity()));
    } catch (IOException e) {
      throw new HuggingFaceException("Could not call HuggingFace API using Java client!", e);
    }
  }

  private HttpPost buildRequest(String prompt) {
    var request = new HttpPost(HUGGINGFACE_BASE_API_URL + "/" + HUGGINGFACE_MODEL);
    request.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");
    request.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + huggingfaceApiKey);

    Map<String, Object> parameters = Map.of("max_new_tokens", 20, "temperature", 0.7);
    Map<String, Object> options = Map.of("wait_for_model", true);
    Map<String, Object> payload =
        Map.of("inputs", prompt, "parameters", parameters, "options", options);

    String requestBody = objectMapper.writeValueAsString(payload);
    request.setEntity(new StringEntity(requestBody));
    return request;
  }
}
