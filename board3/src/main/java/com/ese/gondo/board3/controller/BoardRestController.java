package com.ese.gondo.board3.controller;

import com.ese.gondo.board3.Dto.RequestDto;
import com.ese.gondo.board3.Dto.ResponseDto;
import com.ese.gondo.board3.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BoardRestController {

    private final BoardService boardService;

    @PostMapping("/board/board")
    public Long insertBoard(@RequestBody RequestDto requestDto){
        return boardService.insertBoard(requestDto);
    }

    // 상세보기
    // 현재 미사용
    @GetMapping("/board/{id}")
    public ResponseDto findById(@PathVariable final Long id) {
        log.info("Long Id {} ", id);
        return boardService.viewBoard(id);
    }

    // 삭제
    // id 값으로 보드를 찾아 삭제 진행 Long id PK 리턴
    @DeleteMapping("/board/board/{id}")
    public Long delete(@PathVariable final Long id){
        log.info("Long id {}", id);
        return boardService.deleteBoard(id);
    }
}
