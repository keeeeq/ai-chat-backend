package com.example.ai.controller;

import com.example.ai.repository.ChatHistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;

import org.springframework.ai.content.Media;
import org.springframework.util.MimeType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Objects;


import static org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor.CHAT_MEMORY_CONVERSATION_ID_KEY;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ai")
@Slf4j
public class ChatController {

    private final ChatClient chatClient;
    private final ChatMemory chatMemory;
    private final ChatHistoryRepository chatHistoryRepository;
    @RequestMapping(value = "/chat",produces = "text/html;charset=utf-8")
    public Flux<String> chat(String prompt, String chatId, @RequestParam(value = "files", required = false) List<MultipartFile> files){
        //保存会话id
        chatHistoryRepository.save("chat",chatId);
        log.info("接收到的文件数量: {}", files != null ? files.size() : 0);
        if (files != null) {
            files.forEach(file -> log.info("文件名: {}, 大小: {}, 类型: {}",
                    file.getOriginalFilename(),
                    file.getSize(),
                    file.getContentType()));
        }
        //请求模型
        if (files == null || files.isEmpty()) {
            // 没有附件，纯文本聊天

            return textChat(prompt, chatId);
        } else {
            // 有附件，多模态聊天
            return multiModalChat(prompt, chatId, files);
        }

    }
    private Flux<String> multiModalChat(String prompt, String chatId,@RequestParam(value = "files", required = false) List<MultipartFile> files) {

//        // 1.解析多媒体
//        List<Media> medias = files.stream()
//                .map(file -> new Media(
//                                MimeType.valueOf(Objects.requireNonNull(file.getContentType())),
//                                file.getResource()
//                        )
//                )
//                .toList();
//        // 2.请求模型（修复了 media 方法的调用方式）
//        return chatClient.prompt()
//                .user(p -> p.text(prompt).media(medias.toArray(Media[]::new)))
//                .advisors(a -> a.param(CHAT_MEMORY_CONVERSATION_ID_KEY, chatId))
//                .stream()
//                .content();
        return null;
    }

    public Flux<String> textChat(String prompt, String chatId){
        //请求模型
        return chatClient.prompt()
                //创建工厂实例
                .user(prompt)
                .advisors(a->a.param(CHAT_MEMORY_CONVERSATION_ID_KEY,chatId))
                //配置唯一会话id
                .stream()
                .content();

    }

}
