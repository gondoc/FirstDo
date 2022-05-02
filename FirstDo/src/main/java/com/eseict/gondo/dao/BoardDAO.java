package com.eseict.gondo.dao;

import org.apache.ibatis.annotations.Mapper;

import com.eseict.gondo.vo.BoardVO;

@Mapper
public interface BoardDAO {
	// <!-- 01. insert_저장하기(글 쓰기) -->
	String insertBoard(BoardVO boardVO);
	
	// <!-- 02. select_1개 조회하기 -->
	BoardVO selectByIdx(int board_idx);
	
	// <!-- 03. update_수정하기(글 수정) -->
	String updateBoard(BoardVO boardVO);
	
	// <!-- 04. delete_삭제하기(글 삭제) -->
	String deleteBoard(int board_idx);
}
