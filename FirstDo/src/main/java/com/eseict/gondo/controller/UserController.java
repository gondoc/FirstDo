package com.eseict.gondo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

	@PostMapping(value = "/loginAction")
	@ResponseBody
	public ModelAndView login(String user_id, String user_pw, Model model) {
		log.info("UserController-login 호출 : user_id {} , user_pw {} ", user_id, user_pw);
		ModelAndView mv = new ModelAndView();
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
			UserVO userVO = new UserVO();
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

	@PostMapping(value = "/user")
	public ModelAndView insertUser(UserVO userVO) {
		log.info("UserController-insertUser userVO {} : ", userVO);
		ModelAndView mv = new ModelAndView();
		UserVO dbUserVO = null;
		String bcryptUserPassword = "";
		log.info("UserController-insertUser 아이디 검증 userVO.getUser_id() {} : ", userVO.getUser_id());
		if (userVO.getUser_id() == null) {
			log.info("UserController-insertUser 아이디 검증 중 오류 발생");
			mv.addObject("msg", "잘못된 접근입니다.");
			mv.setViewName("/error");
			return mv;
		}
		log.info("UserController-insertUser 이름 검증 userVO.getUser_name() {} : ", userVO.getUser_name());
		if (userVO.getUser_name() == null) {
			mv.addObject("msg", "잘못된 접근입니다.");
			mv.setViewName("/error");
			return mv;
		}
		log.info("UserController-insertUser 비밀번호 검증 userVO.getUser_pw() {} : ", userVO.getUser_pw());
		if (userVO.getUser_pw() == null) {
			mv.addObject("msg", "잘못된 접근입니다.");
			mv.setViewName("/error");
			return mv;
		}
		if (userVO != null) {
			log.info("UserController-insertUser userVO 검증 통과 insertUser Service 호출");
			BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
			bcryptUserPassword = bcrypt.encode(userVO.getUser_pw());
			userVO.setUser_pw(bcryptUserPassword);
			log.info("UserController-insertUser 회원가입 시도 전 유저 비밀번호 암호화(Bcrypt) 완료");
			userService.insertUser(userVO);
			log.info("UserController-insertUser 회원가입 완료 ");
			mv.addObject("msg", "회원가입을 환영합니다.");
			mv.setViewName("/login");
			return mv;
		}
		return mv;
	}

}
