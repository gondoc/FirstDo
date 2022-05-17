package com.eseict.gondo.board2.entity;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    // 게시글 리스트 조회
    List<Board> findAllByBoardDeleteYn(final char boardDeleteYn, final Sort sort);
}
