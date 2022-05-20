package com.ese.gondo.board3.controller;

import com.ese.gondo.board3.Dto.ResponseDto;
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
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/boards")
    public String boards(Model model, @PageableDefault final Pageable pageable) {
        Page<BoardEntity> boardList = boardService.getBoardList(pageable);
        model.addAttribute("boardList", boardList);
        log.debug("총 element 수 : {}, 전체 page 수 : {}, 페이지에 표시할 element 수 : {}, 현재 페이지 index : {}, 현재 페이지의 element 수 : {}",
                boardList.getTotalElements(), boardList.getTotalPages(), boardList.getSize(),
                boardList.getNumber(), boardList.getNumberOfElements());
        return "/board";
    }

    // 상세보기
    @GetMapping("board/board/{id}")
    public String boardView(@PathVariable final Long id, Model model) {
        ResponseDto board = boardService.viewBoard(id);
        model.addAttribute("id", id);
        model.addAttribute("board", board);
        return "/view";
    }

    @GetMapping("/board/insert")
    public String insertView() {
        return "/insert";
    }

}
