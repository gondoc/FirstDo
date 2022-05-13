package com.eseict.gondo.board2.service;

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
        return entity.getBoard_idx();
    }

    // 리스트 조회
    public List<BoardResponseDto> findAll() {
        Sort sort = Sort.by(Sort.Direction.DESC, "board_idx", "board_regDate");
        List<Board> boardList = boardRepository.findAll(sort);
        return boardList.stream().map(BoardResponseDto::new).collect(Collectors.toList());
    }
    // 조회

    // 수정
    @Transactional
    public Long update(final Long board_idx, final BoardRequestDto boardRequestDto){
        Board entity = boardRepository.findById(board_idx).orElseThrow(()-> new NullPointerException());
        entity.update(boardRequestDto.getBoard_title(), boardRequestDto.getBoard_content(), boardRequestDto.getBoard_modifiedDate());
        return board_idx;
    }

    // 삭제
}
