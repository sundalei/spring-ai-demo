package com.example.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.deepseek.DeepSeekChatModel;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AIProviderConfig {

  @Bean
  @ConditionalOnProperty(prefix = "app.ai", name = "provider", havingValue = "openai")
  ChatClient openAIChatClient(OpenAiChatModel openAiChatModel) {
    return ChatClient.builder(openAiChatModel).build();
  }

  @Bean
  @ConditionalOnProperty(prefix = "app.ai", name = "provider", havingValue = "deepseek")
  ChatClient deepSeekChatClient(DeepSeekChatModel deepSeekChatModel) {
    return ChatClient.builder(deepSeekChatModel).build();
  }
}
