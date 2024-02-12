package com.example.demo.controller.dto;

import com.example.demo.domain.BoardEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
public class BoardListResponse {
    private final Long id;
    private final String name;
    private final String title;
    private final String contents;
    private final Long groupId;
    private final Integer sortKey;
    private final Integer depth;
    private final LocalDateTime writeDate;
    private final LocalDateTime updateDate;

    public BoardListResponse(BoardEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.title = entity.getTitle();
        this.contents = entity.getContent();
        this.groupId = entity.getGroup();
        this.sortKey = entity.getSortKey();
        this.depth = entity.getDepth();
        this.writeDate = entity.getCreated_at();
        this.updateDate = entity.getModified_at();
    }
}
