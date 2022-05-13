package com.eseict.gondo.board2.entity;

import com.eseict.gondo.board2.dto.BoardDto;
import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Table(name = "BOARD")
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 동일한 패키지 내의 클래스에서만 객체를 생성할 수 있도록 제어.
@Entity(name = "board")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // GenerationType.IDENTITY는 기본 키 생성을 데이터베이스에 위임하는 방식.
    @Column(name = "board_idx")
    private long board_idx;

    @NotBlank
    private String board_title;

    @NotBlank
    private String board_content;

    @NotBlank
    private LocalDateTime board_regDate = LocalDateTime.now();

    @Nullable
    private LocalDateTime board_modifiedDate;

    /* Users 생성자 빌더패턴 적용  */
    private Board(BoardBuilder builder) {
        this.board_idx = builder.board_idx;
        this.board_title = builder.board_title;
        this.board_content = builder.board_content;
        this.board_regDate = builder.board_regDate;
        this.board_modifiedDate = builder.board_modifiedDate;
    }

    /**
     * Users 클래스의 빌더 클래스
     */
    public static class BoardBuilder implements CommonBuilder<Board> {
        private final Long board_idx;
        private final String board_title;
        private final String board_content;
        private final LocalDateTime board_regDate;
        private final LocalDateTime board_modifiedDate;

        /* 생성자 */
        public BoardBuilder(BoardDto boardDto) {
            this.board_idx = boardDto.getBoard_idx();
            this.board_title = boardDto.getBoard_title();
            this.board_content = boardDto.getBoard_content();
            this.board_regDate = boardDto.getBoard_regDate();
            this.board_modifiedDate = boardDto.getBoard_modifiedDate();
        }

        /* build 메소드 호출로 Users 객체 리턴 */
        @Override
        public Board build(){
            return new Board(this);
        }
    }

}
