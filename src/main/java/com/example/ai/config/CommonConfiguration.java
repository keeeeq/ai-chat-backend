package com.example.ai.config;

import com.example.ai.constants.SystemConstants;

import com.example.ai.tools.NovelTools;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import lombok.RequiredArgsConstructor;


@Configuration
@RequiredArgsConstructor
public class CommonConfiguration {

    @Bean
    public ChatMemory chatMemory() {
        return new InMemoryChatMemory();
    }
    /**
     配置大模型客户端
     */
    @Bean
    public ChatClient chatClient(OpenAiChatModel openAiChatModel) {
        return ChatClient
                .builder(openAiChatModel)
                .defaultSystem("你是一个热心可爱的猫娘，你每句话都以'喵'结束")
                .defaultAdvisors(new SimpleLoggerAdvisor(),
                        //日志
                        new MessageChatMemoryAdvisor(chatMemory())
                )
                //会话记忆
                .build();
    }
    @Bean
    public ChatClient gameChatClient(OpenAiChatModel openAiChatModel) {
        return ChatClient
                .builder(openAiChatModel)
                .defaultSystem(SystemConstants.GAME_SYSTEM_PROMPT)
                .defaultAdvisors(new SimpleLoggerAdvisor(),
                        //日志
                        new MessageChatMemoryAdvisor(chatMemory()))
                //会话记忆
                .build();
    }
    @Bean
    public ChatClient serviceChatClient(OpenAiChatModel openAiChatModel, ChatMemory chatMemory, NovelTools novelTools) {
        return ChatClient
                .builder(openAiChatModel)
                .defaultSystem(SystemConstants.SERVICE_SYSTEM_PROMPT)
                .defaultAdvisors(
                    new SimpleLoggerAdvisor(),
                    new MessageChatMemoryAdvisor(chatMemory)
                )
                .defaultTools(novelTools)
                .build();
    }

}
