package com.ese.gondo.board3.service;

import com.ese.gondo.board3.Dto.RequestBoardDto;
import com.ese.gondo.board3.Dto.ResponseBoardDto;
import com.ese.gondo.board3.Entity.Board;
import com.ese.gondo.board3.Entity.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public Page<Board> getBoardList(Pageable pageable) {
        // Pageable의 page는 0 부터 시작한다.
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
        pageable = PageRequest.of(page, 10, Sort.Direction.DESC, "id");
        return boardRepository.findAll(pageable);
    }

    // 저장
    // requestDto를 받아서 BoardEntity의 @GeneratedValue 된 PK 값을 받을 수 있다.
    @Transactional
    public Long insertBoard(final RequestBoardDto requestBoardDto) {
        Board entity = boardRepository.save(requestBoardDto.toEntity());
        return entity.getId();
    }

    // 조회
    @Transactional
    public ResponseBoardDto viewBoard(final Long id) {
        Board entity = boardRepository.findById(id).orElseThrow(() -> new NullPointerException());
        return new ResponseBoardDto(entity);
    }


    // 삭제
    // controller에서 전달받은 id 값으로 delete 진행
    @Transactional
    public Long deleteBoard(final Long id) {
        boardRepository.deleteById(id);
        return id;
    }


    @Transactional
    public Long updateBoard(RequestBoardDto requestBoardDto, final Long id) {
        Board entity = boardRepository.findById(id).orElseThrow(() -> new NullPointerException());
        entity.update(requestBoardDto);
        log.info("Req {} , id {} ", requestBoardDto, id);
        return id;
    }
}
