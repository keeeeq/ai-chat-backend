package com.example.ai.tools;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.ai.entity.po.BookInfo;
import com.example.ai.entity.query.NovelQuery;
import com.example.ai.service.IBookInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class NovelTools {
    private final IBookInfoService bookInfoService;

    @Tool(name = "getNovelInfo", description = "根据条件查询小说信息")
    public List<BookInfo> getNovelInfo(@ToolParam(description = "查询条件", required = true) NovelQuery novelQuery) {
        try {
            log.info("开始查询小说信息，查询条件：{}", novelQuery);
            if (novelQuery == null) {
                log.warn("查询条件为空");
                return new ArrayList<>();
            }
            QueryChainWrapper<BookInfo> wrapper = bookInfoService.query();
            if (StringUtils.isNotBlank(novelQuery.getBookName())) {
                wrapper.like("book_name", novelQuery.getBookName());
                log.debug("添加书名查询条件：{}", novelQuery.getBookName());
            }
            if (StringUtils.isNotBlank(novelQuery.getAuthorName())) {
                wrapper.eq("author_name", novelQuery.getAuthorName());
                log.debug("添加作者名查询条件：{}", novelQuery.getAuthorName());
            }
            Page<BookInfo> page = new Page<>(1, 10);
            Page<BookInfo> result = wrapper.page(page);
            log.info("查询完成，结果数量：{}", result.getTotal());
            return result.getRecords();
        } catch (Exception e) {
            log.error("查询小说信息时发生错误", e);
            return new ArrayList<>();
        }
    }
}