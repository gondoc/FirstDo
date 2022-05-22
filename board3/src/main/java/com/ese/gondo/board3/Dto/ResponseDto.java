package com.ese.gondo.board3.Dto;

import com.ese.gondo.board3.Entity.Board;
import lombok.Getter;

@Getter
public class ResponseDto {

    private long id;
    private String title;
    private String content;

    public ResponseDto(Board entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
    }
}