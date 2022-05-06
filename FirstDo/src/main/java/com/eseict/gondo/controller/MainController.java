package com.eseict.gondo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.eseict.gondo.service.BoardService;
import com.eseict.gondo.service.UserService;
import com.eseict.gondo.vo.BoardVO;
import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "/")
public class MainController {

	@Autowired
	private BoardService boardService;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public ModelAndView main() {
		ModelAndView mv = new ModelAndView();
		log.info("MainController-main 호출");
		List<BoardVO> boardList = boardService.selectBoardList();
		log.info("MainController-selectBoardList boardList {} : ", boardList);
		mv.addObject("boardList", boardList);
		mv.setViewName("/main");
		log.info("MainController-selectBoardList mv {} : ", mv);
		return mv;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView();
		log.info("MainController-login 호출");
		mv.setViewName("/login");
		log.info("MainController-login login.jsp 이동");
		return mv;
	}
	
	@PostMapping(value = "/BoardList")
	@ResponseBody
	// public String selectList(@ModelAttribute CommVO commVO, Model model) {
	// POST전송을 받기위한 방법
	public List<BoardVO> selectBoardList() throws JsonProcessingException {
		log.info("MainController-selectBoardList 호출");
		List<BoardVO> boardList = boardService.selectBoardList();
		return boardList;
	}
}
