package com.eseict.gondo.board2.dto;

import com.eseict.gondo.board2.entity.Board;
import lombok.Getter;

import java.util.Date;

@Getter
public class BoardResponseDto {

    private long id;
    private String boardTitle;
    private String boardContent;
    private Date boardRegDate;
    private Date boardModifiedDate;

    public BoardResponseDto(Board entity){
        this.id = entity.getId();
        this.boardTitle = entity.getBoardTitle();
        this.boardContent = entity.getBoardContent();
        this.boardRegDate = entity.getBoardRegDate();
        this.boardModifiedDate = entity.getBoardModifiedDate();
    }
}
