package com.example.ai.repository;

import java.util.List;

public interface ChatHistoryRepository {
    /**
     * 保存会话记录
     * @param type
     * @param userId
     */
    void save(String type,String userId);
    List<String> getChatIds(String type);
}
