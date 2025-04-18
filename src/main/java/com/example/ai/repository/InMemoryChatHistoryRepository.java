package com.example.ai.repository;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Component

public class InMemoryChatHistoryRepository implements ChatHistoryRepository
{
    private final Map<String, List<String>> chatHistory = new HashMap<>();

    @Override
    public void save(String type, String userId) {
//        if(!chatHistory.containsKey(type)){
//            chatHistory.put(type,new ArrayList<>());
//        }
//        List<String> chatIds = chatHistory.get(type);
        List<String> chatIds = chatHistory.computeIfAbsent(type, k -> new ArrayList<>());
        if (!chatIds.contains(userId)) {
            chatIds.add(userId);
        }


    }

    @Override
    public List<String> getChatIds(String type) {
        if (chatHistory.containsKey(type)) {
            return chatHistory.get(type);
        }
        return new ArrayList<>();
    }
}
