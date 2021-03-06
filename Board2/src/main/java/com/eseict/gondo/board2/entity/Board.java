package com.eseict.gondo.board2.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 동일한 패키지 내의 클래스에서만 객체를 생성할 수 있도록 제어.
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // GenerationType.IDENTITY는 기본 키 생성을 데이터베이스에 위임하는 방식.
    @Column(name = "board_idx")
    private long id;

    @NotBlank
    @Column(name = "board_title")
    private String boardTitle;

    @NotBlank
    @Column(name = "board_content")
    private String boardContent;

    @Column(name = "board_deleteYn")
    private char boardDeleteYn;

    @Column(name = "board_regDate")
    private LocalDateTime boardRegDate = LocalDateTime.now();

    @Column(name = "board_modifiedDate")
    private LocalDateTime boardModifiedDate;

    @Builder
    public Board(String boardTitle, String boardContent, char boardDeleteYn) {
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.boardDeleteYn = boardDeleteYn;
    }

    // BoardEntity에 ResponseEntity 리턴으로 설정해도 되는가?
    public void update(String boardTitle, String boardContent) {
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.boardModifiedDate = LocalDateTime.now();
    }

    public void delete() {
        this.boardDeleteYn = 'Y';
    }
}
