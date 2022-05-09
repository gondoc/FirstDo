package com.eseict.gondo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
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
	public ModelAndView insertUser(@RequestPart UserVO userVO) {
		log.info("UserController-insertUser userVO {} : ", userVO);
		ModelAndView mv = new ModelAndView();
		UserVO dbUserVO = null;
		String bcryptUserPassword = "";
		if (userVO != null) {
			log.info("UserController-insertUser userVO 검증 통과 insertUser Service 호출");
			BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
			bcryptUserPassword = bcrypt.encode(userVO.getUser_pw());
			userVO.setUser_pw(bcryptUserPassword);
			log.info("UserController-insertUser 회원가입 시도 전 유저 비밀번호 암호화(Bcrypt) 완료");
			userService.insertUser(userVO);
			log.info("UserController-insertUser 회원가입 완료 ");
			dbUserVO = userService.selectByUserId(userVO.getUser_id());
			if (dbUserVO == null) {
				mv.addObject("flag", 0);
				mv.addObject("msg", "DB 저장 실패, 에러페이지로 이동합니다.");
				mv.setViewName("/error");
				return mv;
			} else {
				mv.addObject("flag", 1);
				mv.addObject("msg", "DB 저장 성공, 로그인페이지로 이동합니다.");
				mv.setViewName("/login");
				return mv;
			}
		}
		mv.setViewName("/main");
		return mv;
	}

	@PutMapping(value = "/user")
	public int updateUser(UserVO userVO) {
		ModelAndView mv = new ModelAndView();
		int updateUserFlag = 0;
		UserVO dbUserVO = userService.selectByIdx(userVO.getUser_idx());
		if (dbUserVO == userVO) {
			mv.addObject("msg", "수정된 회원정보가 없습니다.");
			mv.setViewName("/main");

		}
		if (userVO != null) {
			userService.updateUser(userVO);
			mv.addObject("msg", "회원정보가 정상적으로 수정되었습니다.");
			mv.setViewName("/main");
			updateUserFlag = 1;
		}
		return updateUserFlag;
	}

	@DeleteMapping(value = "/user")
	public int deleteUser(UserVO userVO) {
		int deleteUserFlag = 0; // 0 실패, 1 성공
		return deleteUserFlag;
	}

}
