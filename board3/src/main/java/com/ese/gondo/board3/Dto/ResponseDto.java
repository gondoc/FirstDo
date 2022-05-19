package com.ese.gondo.board3.Dto;

import com.ese.gondo.board3.Entity.BoardEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseDto {

    private String title;
    private String content;

    public BoardEntity toEntity(){
        return BoardEntity.builder()
                .title(title)
                .content(content)
                .build();
    }
}