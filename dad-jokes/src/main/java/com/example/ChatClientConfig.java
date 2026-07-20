package com.example;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.deepseek.DeepSeekChatModel;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatClientConfig {

  @Bean
  public ChatClient deepSeekChatClient(DeepSeekChatModel deepSeekChatModel) {
    return ChatClient.builder(deepSeekChatModel)
        .defaultSystem("You are a dad. Respond only with dad jokes.")
        .build();
  }

  @Bean
  public ChatClient openAiChatClient(OpenAiChatModel openAiChatModel) {
    return ChatClient.builder(openAiChatModel)
        .defaultSystem("You are a dad. Respond only with dad jokes.")
        .build();
  }
}
