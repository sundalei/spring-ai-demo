package com.example.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AIProviderConfig {

  @Bean
  ChatClient openAIChatClient(OpenAiChatModel openAiChatModel) {
    return ChatClient.builder(openAiChatModel).build();
  }
}
