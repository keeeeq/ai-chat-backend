package com.example.ai.controller;

import com.example.ai.repository.ChatHistoryRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController("/ai/history")
public class ChatHistoryController {
    private final ChatHistoryRepository chatHistoryRepository;
   @GetMapping("/{type}")
    public List<String> getChatIds(@PathVariable String type){
        return chatHistoryRepository.getChatIds(type);
    }
}
