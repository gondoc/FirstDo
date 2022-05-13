package com.eseict.gondo.board2.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardDto {
    private long board_idx;
    private String board_title;
    private String board_content;
    private LocalDateTime board_regDate;
    private LocalDateTime board_modifiedDate;

}
