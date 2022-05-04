package com.eseict.gondo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
public class MainController {

	@Autowired
	private BoardService boardService;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView selectBoardList(Model model) {
		ModelAndView mv = new ModelAndView();
		log.info("MainController-selectBoardList 호출");
		List<BoardVO> boardList = boardService.selectBoardList();
		log.info("MainController-selectBoardList boardList {} : ", boardList);
		mv.addObject("boardList", boardList);
		mv.setViewName("/home");
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
