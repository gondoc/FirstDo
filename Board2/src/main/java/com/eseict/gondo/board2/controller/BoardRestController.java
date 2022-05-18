package com.eseict.gondo.board2.controller;

import com.eseict.gondo.board2.dto.BoardRequestDto;
import com.eseict.gondo.board2.dto.BoardResponseDto;
import com.eseict.gondo.board2.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardRestController {

    private final BoardService boardService;

    // 저장
    @PostMapping("/board")
    public Long save(@RequestBody final BoardRequestDto params) {
        log.info("BoardRestController-save 호출 : params {} ", params);
        return boardService.save(params);
    }

    // 리스트 조회
    @GetMapping("/board")
    public List<BoardResponseDto> findAll(@RequestParam final char boardDeleteYn) {
        return boardService.findAllByDeleteYn(boardDeleteYn);
    }

    // 수정
    @PatchMapping("/board/{id}")
    public Long update(
            @PathVariable final Long id,
            @RequestBody final BoardRequestDto params) {
        return boardService.update(id, params);
    }

    // 상세보기
    @GetMapping("/board/{id}")
    public BoardResponseDto findById(@PathVariable final Long id) {
        return boardService.findById(id);
    }

    // 삭제
    @DeleteMapping("/board/{id}")
    public Long delete(@PathVariable final Long id){
        return boardService.delete(id);
    }
}
