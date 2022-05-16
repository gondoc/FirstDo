package com.eseict.gondo.board2.service;

import com.eseict.gondo.board2.CustomException;
import com.eseict.gondo.board2.ErrorCode;
import com.eseict.gondo.board2.dto.BoardRequestDto;
import com.eseict.gondo.board2.dto.BoardResponseDto;
import com.eseict.gondo.board2.entity.Board;
import com.eseict.gondo.board2.entity.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    // 저장
    @Transactional
    public Long save(final BoardRequestDto boardRequestDto) {
        Board entity = boardRepository.save(boardRequestDto.toEntity());
        return entity.getId();
    }

    // 리스트 조회
    public List<BoardResponseDto> findAll() {
        Sort sort = Sort.by(Sort.Direction.DESC, "id", "boardRegDate");
        List<Board> boardList = boardRepository.findAll(sort);
        return boardList.stream().map(BoardResponseDto::new).collect(Collectors.toList());
    }
    // 조회

    // 수정
    @Transactional
    public Long update(final Long id, final BoardRequestDto boardRequestDto) {
        Board entity = boardRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.POSTS_NOT_FOUND));
        entity.update(boardRequestDto.getBoardTitle(), boardRequestDto.getBoardContent(), boardRequestDto.toEntity().getBoardModifiedDate());
        return id;
    }

    // 삭제
}
