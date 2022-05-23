package com.ese.gondo.board3.Entity;

import com.ese.gondo.board3.Dto.RequestBoardDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface BoardRepository extends JpaRepository<Board, Long> {

    // 강제업데이트 현재 미사용
    // 업데이트는 find 후 save 진행중
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE Board b set b.title = : title, b.content = : content WHERE b.id =: id")
    int updateBoard(RequestBoardDto requestBoardDto, @Param("id") final Long id);
}
