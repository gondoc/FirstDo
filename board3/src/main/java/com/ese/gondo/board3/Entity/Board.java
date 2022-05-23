package com.ese.gondo.board3.Entity;

import com.ese.gondo.board3.Dto.RequestBoardDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
@NoArgsConstructor
@Entity
@Table(name = "BOARD")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;

    @Builder
    public Board(String title, String content){
        this.title = title;
        this.content = content;
    }

    public void update(RequestBoardDto requestBoardDto){
        this.title = requestBoardDto.getTitle();
        this.content = requestBoardDto.getContent();
    }
}
