package com.example.ai.controller;

import com.example.ai.repository.ChatHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import static org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor.CHAT_MEMORY_CONVERSATION_ID_KEY;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ai")
public class ChatController {

    private final ChatClient chatClient;
    private final ChatMemory chatMemory;
    private final ChatHistoryRepository chatHistoryRepository;
    @RequestMapping(value = "/chat",produces = "text/html;charset=utf-8")
    public Flux<String> chat(String prompt, String chatId){
        chatHistoryRepository.save("chat",chatId);
        return chatClient.prompt()
                .user(prompt)
                .advisors(a->a.param(CHAT_MEMORY_CONVERSATION_ID_KEY,chatId))
                .stream()
                .content();

    }

}
