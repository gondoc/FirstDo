package com.eseict.gondo.service;

import java.util.ArrayList;
import java.util.List;

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
	// user_id가 db 내 유효할 것.
	// insert 중 boardVO에 user_idx 외래키 처리 할 것.
	// flag 처리 할 것.
	public int insertBoard(BoardVO boardVO, String user_id) {
		log.info("BoardService-insertUser 호출 : boardVO {}", boardVO);
		log.info("BoardService-insertUser 호출 : user_id {}", user_id);
		UserVO dbUserVO = null;
		int insertBoardFlag = 0; // 0 실패, 1 성공
		// 작성 게시글 확인, 유저 아이디 확인
		if (boardVO != null && user_id != null) {
			log.info("BoardService-insertUser boardVO, user_id 존재 확인");
			dbUserVO = userDAO.selectByUserId(user_id);
			if (dbUserVO != null) {
				log.info("BoardService-insertUser dbUserVO 유효함 {}", dbUserVO);
				try {
					log.info("BoardService-insertUser 외래키 set dbUserVO.getUser_idx() {}", dbUserVO.getUser_idx());
					boardVO.setUser_idx(dbUserVO.getUser_idx());
					boardDAO.insertBoard(boardVO);
					return insertBoardFlag = 1;
				} catch (Exception e) {
					log.info("BoardService-insertUser insertBoard 실행중 오류발생");
					log.info("BoardService-insertUser insertBoard insertBoardFlag = 0 리턴");
					return insertBoardFlag;
				}
			} else {
				log.info("BoardService-insertUser dbUserVO null 유저가 유효하지 않음");
				log.info("BoardService-insertUser insertBoard insertBoardFlag = 0 리턴");
				return insertBoardFlag;
			}
		} else {
			log.info("BoardService-insertUser boardVO, user_id 미존재");
			log.info("BoardService-insertUser insertBoard insertBoardFlag = 0 리턴");
			return insertBoardFlag;
		}
	}

	@Override
	// 글 상세보기
	// int board_idx != 0
	// 글이 없다면 빈 boardVO 를 리턴할 것.
	public BoardVO selectByIdx(int board_idx) {
		log.info("BoardService-selectByIdx 호출 : int board_idx {}", board_idx);
		BoardVO dbBoardVO = null;
		if (board_idx != 0) {
			dbBoardVO = boardDAO.selectByIdx(board_idx);
			log.info("BoardService-selectByIdx 리턴 : dbBoardVO {}", dbBoardVO);
			return dbBoardVO;
		} else {
			log.info("BoardService-selectByIdx 리턴 : 오류 발생 빈 객체 리턴");
			return dbBoardVO = new BoardVO();
		}
	}

	@Override
	// 글 수정하기
	// 원본 글의 작성자와 현 수정자의 user_idx()가 같을 것.
	// flag 처리 할 것.
	public int updateBoard(BoardVO boardVO, String user_id) {
		log.info("BoardService-updateBoard 호출 : boardVO {}, user_id {}", boardVO, user_id);
		BoardVO dbBoardVO = null;
		UserVO dbUserVO = null;
		int updateBoardFlag = 0; // 0 실패, 1 성공
		if (boardVO != null && user_id != null) {
			dbBoardVO = boardDAO.selectByIdx(boardVO.getBoard_idx());
			log.info("BoardService-updateBoard 수정 전 dbBoardVO 확인 {}", dbBoardVO);
			dbUserVO = userDAO.selectByUserId(user_id);
			log.info("BoardService-updateBoard 계정 확인 dbUserVO {}", dbUserVO);
			if (dbUserVO != null && dbUserVO.getUser_idx() == boardVO.getUser_idx()) {
				try {
					log.info("BoardService-updateBoard 유효 계정 확인, 원본 글 작성자 == 수정 희망자");
					boardDAO.updateBoard(boardVO);
					return updateBoardFlag = 1;
				} catch (Exception e) {
					log.info("BoardService-updateBoard update 중 오류 발생");
					log.info("BoardService-insertUser insertBoard insertBoardFlag = 0 리턴");
					return updateBoardFlag;
				}
			} else {
				log.info("BoardService-updateBoard 유효하지 않은 계정이거나, 원본글 작성자와 수정희망자가 같지 않음.");
				return updateBoardFlag;
			}
		} else {
			log.info("BoardService-insertUser insertBoard boardVO == null || user_id == null 오류 발생");
			return updateBoardFlag;
		}
	}

	@Override
	// 글 삭제하기
	// 원본 글의 작성자와 삭제 희망자가 일치할 것.
	// 삭제 전 원본 글이 유효할 것.
	// flag 활용할 것.
	public int deleteBoard(BoardVO boardVO, String user_id) {
		log.info("BoardService-deleteBoard 호출 : boardVO {}, user_id {}", boardVO, user_id);
		BoardVO dbBoardVO = null;
		UserVO dbUserVO = null;
		int deleteBoardFlag = 0; // 0 실패, 1 성공
		if (boardVO != null && user_id != null) {
			log.info("BoardService-deleteBoard boardVO 존재, user_id 존재 확인");
			dbUserVO = userDAO.selectByUserId(user_id);
			if(dbUserVO != null && boardVO.getUser_idx() == dbUserVO.getUser_idx()) {
				try {
					log.info("BoardService-deleteBoard 유효한 계정 확인, 원본 글 작성자와 삭제 희망자간 일치함 확인");
					boardDAO.deleteBoard(boardVO.getBoard_idx());
					return deleteBoardFlag = 1;
				} catch (Exception e) {
					log.info("BoardService-deleteBoard deleteBoard 실행중 오류 확인");
					log.info("BoardService-deleteBoard deleteBoardFlag = 0 리턴");
					return deleteBoardFlag;
				}
			} else {
				log.info("BoardService-deleteBoard 계정이 유효하지 않거나, 원본 글 작성자와 삭제 희망자간 불일치함");
				return deleteBoardFlag;
			}
		} else {
			log.info("BoardService-deleteBoard boardVO == null || user_id == null deleteBoardFlag = 0");
			return deleteBoardFlag;
		}
	}

	@Override
	// 글 목록 가져오기
	// 기 작성된 글이 없다면 List<BoardVO> 객체를 리턴함. 
	public List<BoardVO> selectBoardList() {
		log.info("boardServiceImpl-selectBoardList 호출");
		List<BoardVO> boardList = boardDAO.selectBoardList();
		if (boardList != null) {
			log.info("boardServiceImpl-selectBoardList 글 목록 리턴");
			return boardList;
		} else {
			log.info("boardServiceImpl-selectBoardList 작성 글 없음. 빈 List<BoardVO> 리턴");
			return boardList = new ArrayList<BoardVO>();
		}
	}
}
