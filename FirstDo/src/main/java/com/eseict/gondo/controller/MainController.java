package com.eseict.gondo.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.eseict.gondo.JDBCUtil;
import com.eseict.gondo.vo.PagingVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.eseict.gondo.service.BoardService;
import com.eseict.gondo.service.UserService;
import com.eseict.gondo.vo.BoardVO;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Slf4j
@Controller
public class MainController {

    @Autowired
    private BoardService boardService;

//    @Autowired
//    private UserService userService;


    @RequestMapping(value = "/")
    public String main(HttpServletRequest request) {
        log.info("MainController-main 호출");
        int currentPage = 1;
        int pageSize = 10;
        int blockSize = 10;

//        List<BoardVO> boardList = boardService.selectBoardList();
//        request.setAttribute("boardList", boardList);
        PagingVO<BoardVO> pagingVO = null;
        try {
            pagingVO = boardService.selectList(currentPage, pageSize, blockSize);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("pv", pagingVO);
//        log.info("MainController-selectBoardList boardList {} : ", boardList);
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


    @RequestMapping(value = "board/view", method = RequestMethod.GET)
    public String viewBoard(
            @RequestParam("idx") int board_idx, Model model) {
        log.info("MainController-viewBoard 호출 : board_idx {} ", board_idx);
        BoardVO dbBoardVO = null;
        dbBoardVO = boardService.selectByIdx(board_idx);
        log.info("MainController-viewBoard dbBoardVO {} ", dbBoardVO);
        model.addAttribute("view", dbBoardVO);
        log.info("MainController-viewBoard view key : dbBoardVO value // view {} ", dbBoardVO);
        log.info("MainController-viewBoard viewBoard.jsp 이동");
        return "viewBoard";
    }

    @RequestMapping(value = "/insertBoard", method = RequestMethod.GET)
    public String insertBoard() {
        log.info("MainController-insertBoard 호출");
        log.info("MainController-insertBoard insertBoard.jsp 이동");
        return "insertBoard";
    }

    @RequestMapping(value = "/updateBoard", method = RequestMethod.GET)
    public String updateBoard(
            @RequestParam("idx") int board_idx, Model model) {
        log.info("MainController-updateBoard 호출");
        BoardVO dbBoardVO = null;
        dbBoardVO = boardService.selectByIdx(board_idx);
        log.info("MainController-viewBoard dbBoardVO {} ", dbBoardVO);
        model.addAttribute("view", dbBoardVO);
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
