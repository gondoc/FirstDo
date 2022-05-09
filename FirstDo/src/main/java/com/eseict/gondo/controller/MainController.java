package com.eseict.gondo.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.eseict.gondo.service.BoardService;
import com.eseict.gondo.service.UserService;
import com.eseict.gondo.vo.BoardVO;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;


@Slf4j
@Controller
public class MainController {
    @Autowired
    private BoardService boardService;

    @Autowired
    private UserService userService;


    @GetMapping(value = "/")
    public String main(Model model) {
        log.info("MainController-main 호출");
        List<BoardVO> boardList = boardService.selectBoardList();
        model.addAttribute("boardList", boardList);
        log.info("MainController-selectBoardList boardList {} : ", boardList);
        return "main";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView mv = new ModelAndView();
        log.info("MainController-login 호출");
        // mv.setViewName("login");
        log.info("MainController-login login.jsp 이동");
        return mv;
    }


    @RequestMapping(value = "board/view/{board_idx}", method = RequestMethod.GET)
    public String viewBoard(HttpServletRequest req, Model model, @PathVariable int board_idx) {
        log.info("MainController-viewBoard 호출 : HttpServletRequest req {}, board_idx {} ", req, board_idx);
        BoardVO dbBoardVO = null;
//        int board_idx = 0;
        if(req != null){
//            board_idx = Integer.parseInt(req.getParameter("board_idx"));
            log.info("MainController-viewBoard board_idx {} ", board_idx);
            dbBoardVO = boardService.selectByIdx(board_idx);
            log.info("MainController-viewBoard dbBoardVO {} ", dbBoardVO);
            model.addAttribute("boardVO", dbBoardVO);
            log.info("MainController-viewBoard boardVO key : dbBoardVO value // boardVO {} ", dbBoardVO);
            log.info("MainController-viewBoard viewBoard.jsp 이동");
        }
        return "viewBoard";
    }

    @RequestMapping(value = "/insertBoard", method = RequestMethod.GET)
    public String insertBoard() {
        log.info("MainController-insertBoard 호출");
        log.info("MainController-insertBoard insertBoard.jsp 이동");
        return "insertBoard";
    }

    @RequestMapping(value = "/updateBoard", method = RequestMethod.GET)
    public String updateBoard() {
        log.info("MainController-updateBoard 호출");
        log.info("MainController-updateBoard insertBoard.jsp 이동");
        return "updateBoard";
    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.GET)
    public ModelAndView updateUser() {
        ModelAndView mv = new ModelAndView();
        log.info("MainController-updateUser 호출");
        // mv.setViewName("updateUser");
        log.info("MainController-updateUser updateUser.jsp 이동");
        return mv;
    }


}
