package com.ese.gondo.board3.service;

import com.ese.gondo.board3.Dto.RequestDto;
import com.ese.gondo.board3.Dto.ResponseDto;
import com.ese.gondo.board3.Entity.BoardEntity;
import com.ese.gondo.board3.Entity.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public Page<BoardEntity> getBoardList(Pageable pageable) {
        // Pageable의 page는 0 부터 시작한다.
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
        pageable = PageRequest.of(page, 10, Sort.Direction.DESC, "id");
        return boardRepository.findAll(pageable);
    }

    // 저장
    // requestDto를 받아서 BoardEntity의 @GeneratedValue 된 PK 값을 받을 수 있다.
    @Transactional
    public Long insertBoard(final RequestDto requestDto) {
        BoardEntity entity = boardRepository.save(requestDto.toEntity());
        return entity.getId();
    }

    // 조회
    @Transactional
    public ResponseDto viewBoard(final Long id) {
        BoardEntity entity = boardRepository.findById(id).orElseThrow(() -> new NullPointerException());
        return new ResponseDto(entity);
    }

}
