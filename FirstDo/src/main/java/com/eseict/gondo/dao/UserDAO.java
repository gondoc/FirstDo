package com.eseict.gondo.dao;

import org.apache.ibatis.annotations.Mapper;

import com.eseict.gondo.vo.UserVO;

@Mapper
public interface UserDAO {
	// <!-- 00. select_로그인하기 -->
	UserVO loginUser(UserVO userVO);
	
	// <!-- 01. insert_저장하기(회원가입하기) -->
	int insertUser(UserVO userVO);
	
	// <!-- 02. select_1개 얻기 -->
	UserVO selectByIdx(int user_idx);
	
	// <!-- 03. select_1개 얻기(아이디) -->
	UserVO selectByUserId(String user_id);
	
	// <!-- 04. update_수정하기(회원정보수정하기) -->
	int updateUser(UserVO userVO);
	
	// <!-- 05. delete_삭제하기(회원탈퇴하기) -->
	int deleteUser(int user_idx);
}
