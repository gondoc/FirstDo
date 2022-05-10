package com.eseict.gondo.dao;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.eseict.gondo.vo.BoardVO;

@Mapper
public interface BoardDAO {
	// <!-- 01. insert_저장하기(글 쓰기) -->
	int insertBoard(BoardVO boardVO);
	
	// <!-- 02. select_1개 조회하기 -->
	BoardVO selectByIdx(int board_idx);
	
	// <!-- 03. update_수정하기(글 수정) -->
	int updateBoard(BoardVO boardVO);
	
	// <!-- 04. delete_삭제하기(글 삭제) -->
	int deleteBoard(int board_idx);

    // 	<!-- 05. select_글 목록 가져오기 -->
	List<BoardVO> selectList(Connection connection, HashMap<String, Integer> map);

	// <!-- 06. select_글 갯수 가져오기 -->
	int selectCount();

	List<BoardVO> selectBoardList();
}
