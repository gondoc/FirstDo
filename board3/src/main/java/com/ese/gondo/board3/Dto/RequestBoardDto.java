package com.ese.gondo.board3.Dto;

import com.ese.gondo.board3.Entity.Board;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RequestBoardDto {

    private String title;
    private String content;

    public Board toEntity(){
        return Board.builder()
                .title(title)
                .content(content)
                .build();
    }
}