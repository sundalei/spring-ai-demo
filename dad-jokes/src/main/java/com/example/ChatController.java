package com.example;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

  private final ChatClient chatClient;

  public ChatController(@Qualifier("openAiChatClient") ChatClient chatClient) {
    this.chatClient = chatClient;
  }

  @GetMapping("/dad-jokes")
  public String generate(
      @RequestParam(value = "message", defaultValue = "Tell me a dad joke") String message) {
    return chatClient.prompt().user(message).call().content();
  }
}
