package com.eseict.gondo.service;

import org.springframework.stereotype.Service;

import com.eseict.gondo.vo.UserVO;

@Service
public interface UserService {
// <!-- 로그인 -->
	String loginUser(String user_id, String user_pw);
// <!-- 회원가입 -->
	String insertUser(UserVO userVO);
// <!-- 1개 가져오기 user_idx -->
	UserVO selectByIdx(int user_idx);
// <!-- 1개 가져오기 user_id -->
	UserVO selectByUserId(String user_id);
// <!-- 회원정보수정 -->
	String updateUser(UserVO userVO);
// <!-- 회원탈퇴 -->
	String deleteUser(int user_idx);
}
