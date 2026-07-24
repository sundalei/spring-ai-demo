package com.example.chat.openai;

public class OpenAIChatException extends RuntimeException {
  public OpenAIChatException(String message) {
    super(message);
  }

  public OpenAIChatException(String message, Throwable throwable) {
    super(message, throwable);
  }
}
