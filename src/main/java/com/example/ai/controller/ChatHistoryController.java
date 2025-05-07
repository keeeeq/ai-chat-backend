package com.example.ai.controller;

import com.example.ai.entity.vo.MessageVO;
import com.example.ai.repository.ChatHistoryRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.messages.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/ai/history")
public class ChatHistoryController {
    private final ChatHistoryRepository chatHistoryRepository;
    private final ChatMemory chatMemory;
   @GetMapping("/{type}")
    public List<String> getChatIds(@PathVariable String type){
        return chatHistoryRepository.getChatIds(type);
    }
    @GetMapping("/{type}/{chatId}")
    public List<MessageVO> getChatHistory(@PathVariable String type, @PathVariable String chatId){
        List<Message> messages = chatMemory.get(chatId, Integer.MAX_VALUE);
        if (messages==null){
            return new ArrayList<>();
        }
        log.info("messages:{}",messages);
        return messages.stream().map(MessageVO::new).toList();
    }
}
