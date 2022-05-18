package com.ese.gondo.board3.service;

import com.ese.gondo.board3.Entity.BoardEntity;
import com.ese.gondo.board3.Entity.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public Page<BoardEntity> getBoardList(Pageable pageable) {
        // Pageable의 page는 0 부터 시작한다.
        int page = (pageable.getPageNumber() == 0 ) ? 0 : (pageable.getPageNumber() - 1);
        pageable = PageRequest.of(page, 10);
        return boardRepository.findAll(pageable);
    }

}
