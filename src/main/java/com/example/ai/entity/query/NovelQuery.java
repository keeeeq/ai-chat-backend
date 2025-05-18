package com.example.ai.entity.query;

import lombok.Data;
import org.springframework.ai.tool.annotation.ToolParam;

import java.util.List;

@Data
public class NovelQuery {

    @ToolParam( description = "主键ID", required = false)
    private String id;

    @ToolParam( description = "书籍ID", required = false)
    private String bookId;

    @ToolParam(description = "小说名", required = false)
    private String bookName;

    @ToolParam( description = "作品方向: 0-男频 1-女频", required = false)
    private Integer workDirection;


    @ToolParam( description = "类别名", required = false)
    private String categoryName;

    @ToolParam( description = "小说封面地址", required = false)
    private String picUrl;

    @ToolParam( description = "作家名", required = false)
    private String authorName;

    @ToolParam(description = "书籍描述", required = false)
    private String bookDesc;

    @ToolParam( description = "评分: 总分10，真实评分 = score/10", required = false)
    private Integer score;

    @ToolParam( description = "书籍状态: 0-连载中 1-已完结", required = false)
    private Integer bookStatus;

    @ToolParam(description = "点击量", required = false)
    private Long visitCount;

    @ToolParam( description = "总字数", required = false)
    private Integer wordCount;


    @ToolParam(description = "最新章节ID", required = false)
    private String lastChapterId;

    @ToolParam( description = "最新章节名", required = false)
    private String lastChapterName;

    @ToolParam(description = "最新章节更新时间", required = false)
    private String lastChapterUpdateTime;

    @ToolParam( description = "是否收费: 1-收费 0-免费", required = false)
    private Integer isVip;

    @ToolParam( description = "更新时间", required = false)
    private String updateTime;

    @ToolParam(description = "排序方式", required = false)
    private List<Sort> Sorts;
    @Data
    public static class Sort {
        @ToolParam( description = "排序字段:默认visitCount", required = false)
        private String field;
        @ToolParam( description = "是否升序:默认false", required = false)
        private Boolean asc;
    }
}
