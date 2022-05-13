package com.eseict.gondo.board2.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Table(name = "BOARD")
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 동일한 패키지 내의 클래스에서만 객체를 생성할 수 있도록 제어.
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // GenerationType.IDENTITY는 기본 키 생성을 데이터베이스에 위임하는 방식.
    @Column(name = "board_idx")
    private long board_idx;

    @NotBlank
    private String board_title;

    @NotBlank
    private String board_content;

    @Nullable
    private LocalDateTime board_regDate = LocalDateTime.now();

    @Nullable
    private LocalDateTime board_modifiedDate;

    @Builder
    public Board(String board_title, String board_content, LocalDateTime board_regDate, LocalDateTime board_modifiedDate) {
        this.board_title = board_title;
        this.board_content = board_content;
        this.board_regDate = board_regDate;
        this.board_modifiedDate = board_modifiedDate;
    }

    // BoardEntity에 ResponseEntity 리턴으로 설정해도 되는가?
    public void update(String board_title, String board_content, LocalDateTime board_modifiedDate){
        this.board_title = board_title;
        this.board_content = board_content;
        this.board_modifiedDate = LocalDateTime.now();
    }
}
