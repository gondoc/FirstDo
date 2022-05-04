package com.eseict.gondo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.eseict.gondo.service.UserService;
import com.eseict.gondo.vo.UserVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping(value = "/login")
	public ModelAndView login(String user_id, String user_pw, Model model) {
		log.info("UserController-login 호출 : user_id {} , user_pw {} ", user_id, user_pw);
		ModelAndView mv = new ModelAndView();
		UserVO userVO = null;
		if (user_id == null) {
			log.info("UserController-login user_id {} : ", user_id);
			mv.addObject("msg", "잘못된 접근입니다.");
			mv.setViewName("/error");
			return mv;
		}
		if (user_pw == null) {
			log.info("UserController-login user_pw {} : ", user_pw);
			mv.addObject("msg", "잘못된 접근입니다.");
			mv.setViewName("/error");
			return mv;
		}
		try {
			userVO.setUser_id(user_id);
			userVO.setUser_pw(user_pw);
			log.info("UserController-login 객체 set userVO {}", userVO);
			userVO = userService.loginUser(userVO);
			log.info("UserController-login 객체 set userVO {}");
			mv.addObject("msg", "정상적으로 로그인 되었습니다.");
			mv.addObject("mv", user_id);
			return mv;
		} catch (Exception e) {
			log.info("UserController-login user_pw {} : ", user_pw);
			mv.addObject("msg", "사용자의 정보가 일치하지 않습니다.");
			mv.setViewName("/login");
			return mv;
		}
	}

}
