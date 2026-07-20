package com.example;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

  private final ChatClient openAiChatClient;
  private final ChatClient deepSeekChatClient;

  public ChatController(
      @Qualifier("openAiChatClient") ChatClient openAiChatClient,
      @Qualifier("deepSeekChatClient") ChatClient deepSeekChatClient) {
    this.openAiChatClient = openAiChatClient;
    this.deepSeekChatClient = deepSeekChatClient;
  }

  @GetMapping("/dad-jokes")
  public String generate(
      @RequestParam(value = "message", defaultValue = "Tell me a dad joke") String message) {
    return openAiChatClient.prompt().user(message).call().content();
  }

  @GetMapping("/dad-jokes/deepseek")
  public String generateDeepSeek(
      @RequestParam(value = "message", defaultValue = "Tell me a dad joke") String message) {
    return deepSeekChatClient.prompt().user(message).call().content();
  }
}
