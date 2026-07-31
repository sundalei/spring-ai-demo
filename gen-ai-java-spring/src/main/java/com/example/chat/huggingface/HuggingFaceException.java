package com.example.chat.huggingface;

public class HuggingFaceException extends RuntimeException {
  public HuggingFaceException(String message) {
    super(message);
  }

  public HuggingFaceException(String message, Throwable throwable) {
    super(message, throwable);
  }
}
