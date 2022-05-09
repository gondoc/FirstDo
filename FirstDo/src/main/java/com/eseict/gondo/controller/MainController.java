package com.eseict.gondo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.eseict.gondo.service.BoardService;
import com.eseict.gondo.service.UserService;
import com.eseict.gondo.vo.BoardVO;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
public class MainController {
	@Autowired
	private BoardService boardService;

	@Autowired
	private UserService userService;
	@RequestMapping(value = "/", method = {RequestMethod.GET})
	public ModelAndView main() {
		ModelAndView mv = new ModelAndView();
		log.info("MainController-main 호출");
		List<BoardVO> boardList = boardService.selectBoardList();
		log.info("MainController-selectBoardList boardList {} : ", boardList);
		for (BoardVO forBoard : boardList) {
			mv.addObject("vo", boardList.get(0));
		}
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

	@RequestMapping(value = "/insertBoard", method = RequestMethod.GET)
	public ModelAndView insertBoard() {
		ModelAndView mv = new ModelAndView();
		log.info("MainController-insertBoard 호출");
		mv.setViewName("/insertBoard");
		log.info("MainController-insertBoard insertBoard.jsp 이동");
		return mv;
	}
	
	@RequestMapping(value = "/updateBoard", method = RequestMethod.GET)
	public ModelAndView updateBoard() {
		ModelAndView mv = new ModelAndView();
		log.info("MainController-updateBoard 호출");
		mv.setViewName("/updateBoard");
		log.info("MainController-updateBoard insertBoard.jsp 이동");
		return mv;
	}

	@RequestMapping(value = "/updateUser", method = RequestMethod.GET)
	public ModelAndView updateUser() {
		ModelAndView mv = new ModelAndView();
		log.info("MainController-updateUser 호출");
		mv.setViewName("/updateUser");
		log.info("MainController-updateUser updateUser.jsp 이동");
		return mv;
	}

	
}
