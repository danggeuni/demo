package com.example.demo.controller.dto.board;

import com.example.demo.domain.BoardEntity;
import lombok.Data;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Setter
public class ArticlesResponse {
    private final Long id;
    private final String name;
    private final String title;
    private String content;
    private final Long groupId;
    private final int sortKey;
    private final int depth;
    private final LocalDateTime createDate;
    private final LocalDateTime updateDate;

    public ArticlesResponse(BoardEntity entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.groupId = entity.getGroupId();
        this.sortKey = entity.getSortKey();
        this.depth = entity.getDepth();
        this.createDate = entity.getCreateDate();
        this.updateDate = entity.getUpdateDate();
    }
}
