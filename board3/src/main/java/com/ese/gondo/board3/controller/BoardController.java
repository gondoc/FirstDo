package com.ese.gondo.board3.controller;

import com.ese.gondo.board3.Entity.BoardEntity;
import com.ese.gondo.board3.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/board")
    public String boardView(@PageableDefault Pageable pageable, Model model){
        Page<BoardEntity> boardList = boardService.getBoardList(pageable);
        model.addAttribute("boardList", boardList);
        log.info("총 element 수 : {}, 전체 page 수 : {}, 페이지에 표시할 element 수 : {}, 현재 페이지 index : {}, 현재 페이지의 element 수 : {}",
                boardList.getTotalElements(), boardList.getTotalPages(), boardList.getSize(),
                boardList.getNumber(), boardList.getNumberOfElements());
        return "/board";
    }
}
