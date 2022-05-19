package com.ese.gondo.board3.controller;

import com.ese.gondo.board3.Dto.RequestDto;
import com.ese.gondo.board3.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BoardRestController {

    private final BoardService boardService;

    @PostMapping("/board/board")
    public Long insertBoard(@RequestBody RequestDto requestDto){
        return boardService.insertBoard(requestDto);
    }





}
