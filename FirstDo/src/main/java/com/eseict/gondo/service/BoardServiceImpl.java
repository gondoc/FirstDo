package com.eseict.gondo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eseict.gondo.dao.BoardDAO;
import com.eseict.gondo.dao.UserDAO;
import com.eseict.gondo.vo.BoardVO;
import com.eseict.gondo.vo.UserVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("boardService")
public class BoardServiceImpl implements BoardService {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private BoardDAO boardDAO;

	@Override
	// <!-- 01. insert_글 쓰기 -->
	// db 내 유저 아이디 존재시 글쓰기 진행.
	public String insertBoard(BoardVO boardVO, String user_id) {
		log.info("boardService-insertUser 호출 : boardVO {}", boardVO);
		log.info("boardService-insertUser 호출 : user_id {}", user_id);
		UserVO dbUserVO = null;
		// 작성 게시글 확인, 유저 아이디 확인
		if (boardVO != null && user_id != null) {
//			dbUserVO = userDAO.
			if(dbUserVO != null) {
				boardDAO.insertBoard(boardVO);
			}
		} else {
			// 예외 처리
		}
		return "1";
	}

	@Override
	public BoardVO selectByIdx(int board_idx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateBoard(BoardVO boardVO, String user_id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteBoard(BoardVO boardVO, String path) {
		// TODO Auto-generated method stub
		
	}
}
