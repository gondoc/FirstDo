package com.eseict.gondo.board2.controller;

import com.eseict.gondo.board2.dto.BoardResponseDto;
import com.eseict.gondo.board2.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class BoardPageController {

    private final BoardService boardService;

    @GetMapping(value = "/")
    public String home(Model model) {
        return "home";
    }

    @GetMapping(value = "/board")
    public String board(@PageableDefault Pageable pageable, Model model) {
        char deleteYn = 0;
        List<BoardResponseDto> boardList = boardService.findAllByDeleteYn(deleteYn);
        model.addAttribute("boardList", boardList);
        return "board";
    }

//    @GetMapping("/list")
//    public String openBoardList() {
//        return "board";
//    }

    @GetMapping("/board/insert")
    public String openBoardInsert(@RequestParam(required = false) final Long id, Model model) {
        model.addAttribute("id", id);
        return "insert";
    }

    @GetMapping("/board/view/{id}")
    public String openBoardView(@PathVariable Long id, Model model) {
        model.addAttribute("id", id);
        return "view";
    }
}

