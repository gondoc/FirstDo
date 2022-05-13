package com.eseict.gondo.board2.dto;

import com.eseict.gondo.board2.entity.Board;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardRequestDto {

    private String board_title;
    private String board_content;
    private LocalDateTime board_modifiedDate;

    public Board toEntity(){
        return Board.builder()
                .board_title(board_title)
                .board_content(board_content)
                .board_modifiedDate(board_modifiedDate)
                .build();
    }
}
