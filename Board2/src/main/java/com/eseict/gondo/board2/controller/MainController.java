package com.eseict.gondo.board2.controller;

import com.eseict.gondo.board2.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class MainController {

    private final BoardService boardService;

//    @GetMapping(value = "/")
//    public String home(Model model) {
//        return "home";
//    }
//
//    @GetMapping(value = "/board")
//    public String board(Model model) {
//        char deleteYn = 0;
//        List<BoardResponseDto> boardList = boardService.findAllByDeleteYn(deleteYn);
//        model.addAttribute("boardList", boardList);
//        return "board";
//    }


}
