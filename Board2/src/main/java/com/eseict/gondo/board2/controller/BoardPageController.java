package com.eseict.gondo.board2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardPageController {

    @GetMapping("/list")
    public String openBoardList() {
        return "/board";
    }

    @GetMapping("/insert")
    public String openBoardInsert() {
        return "insert";
    }
}

