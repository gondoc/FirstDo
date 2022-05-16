package com.eseict.gondo.board2.controller;

import com.eseict.gondo.board2.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    BoardService boardService;

    public void BoardService(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping(value = "/")
    public String home() {
        return "home";
    }

    @GetMapping(value = "/board")
    public String board() {
        return "board";
    }


}
