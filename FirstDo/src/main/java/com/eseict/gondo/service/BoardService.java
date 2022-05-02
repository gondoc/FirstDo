package com.eseict.gondo.service;

import org.springframework.stereotype.Service;

import com.eseict.gondo.vo.BoardVO;


@Service
public interface BoardService {
	// <!-- 01. insert_글 쓰기 -->
	String insertBoard(BoardVO boardVO, String user_id);

	// <!-- 02. select_글 1개 가져오기 -->
	BoardVO selectByIdx(int board_idx);

	// <!-- 03. update_글 수정하기 -->
	void updateBoard(BoardVO boardVO, String user_id);

	// <!-- 04. delete_글 삭제하기 -->
	void deleteBoard(BoardVO boardVO, String path);
	
}
