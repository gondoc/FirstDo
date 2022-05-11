package com.eseict.gondo.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.eseict.gondo.vo.PagingVO;
import org.springframework.stereotype.Service;

import com.eseict.gondo.vo.BoardVO;

@Service
public interface BoardService {
	// <!-- 01. insert_글 쓰기 -->
	int insertBoard(BoardVO boardVO);

	// <!-- 02. select_글 1개 가져오기 -->
	BoardVO selectByIdx(int board_idx);

	// <!-- 03. update_글 수정하기 -->
	int updateBoard(BoardVO boardVO, String user_id);

	// <!-- 04. delete_글 삭제하기 -->
	int deleteBoard(BoardVO boardVO, String user_id);
	
	// <!-- 05. select_목록 가져오기 -->
	PagingVO<BoardVO> selectList(int currentPage, int pageSize, int blockSize) throws SQLException;

	// <!-- 06. select_글 갯수 가져오기 -->
	int selectCount();

	List<BoardVO> selectBoardList();
}
