package com.eseict.gondo.service;

import org.springframework.stereotype.Service;

import com.eseict.gondo.vo.UserVO;

@Service
public interface UserService {
// <!-- 로그인 -->
	UserVO loginUser(UserVO userVO);
// <!-- 회원가입 -->
	int insertUser(UserVO userVO);
// <!-- 1개 가져오기 user_idx -->
	UserVO selectByIdx(int user_idx);
// <!-- 1개 가져오기 user_id -->
	UserVO selectByUserId(String user_id);
// <!-- 회원정보수정 -->
	int updateUser(UserVO userVO);
// <!-- 회원탈퇴 -->
	int deleteUser(int user_idx);
}
