package com.example.chat.huggingface;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hf/chat")
public class HuggingFaceChatController {

  private final HuggingFaceService huggingFaceService;

  public HuggingFaceChatController(HuggingFaceService huggingFaceService) {
    this.huggingFaceService = huggingFaceService;
  }

  @PostMapping("/summarize-meeting-notes")
  public String summarizeMeetingNotes(@RequestBody String meetingNotes) {
    return huggingFaceService.chat(meetingNotes);
  }
}
