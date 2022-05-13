package com.eseict.gondo.board2.dto;

import com.eseict.gondo.board2.entity.Board;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardResponseDto {

    private long board_idx;
    private String board_title;
    private String board_content;
    private LocalDateTime board_regDate;
    private LocalDateTime board_modifiedDate;

    public BoardResponseDto(Board entity){
        this.board_idx = entity.getBoard_idx();
        this.board_title = entity.getBoard_title();
        this.board_content = entity.getBoard_content();
        this.board_regDate = entity.getBoard_regDate();
        this.board_modifiedDate = entity.getBoard_modifiedDate();
    }
}
