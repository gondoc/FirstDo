package com.eseict.gondo.controller;

import com.eseict.gondo.dao.BoardDAO;
import com.eseict.gondo.dao.UserDAO;
import com.eseict.gondo.service.UserServiceImpl;
import com.eseict.gondo.vo.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.eseict.gondo.service.BoardService;
import com.eseict.gondo.service.UserService;
import com.eseict.gondo.vo.BoardVO;
import com.eseict.gondo.vo.UserVO;
import com.fasterxml.jackson.databind.JsonMappingException;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;


@Slf4j
@RestController
@RequestMapping(value = "/board")
public class BoardController {

    BoardService boardService;

    @Autowired
    public void BoardService(BoardService boardService) {
        this.boardService = boardService;
    }

    @Autowired
    private UserService userService;



    @PostMapping(value = "/board")
    public ResponseEntity<String> Board(@RequestBody BoardVO boardVO){
        int insertCnt = 0;
        log.info("BoardController-Board 호출 : BoardVO {} ", boardVO);
        insertCnt = boardService.insertBoard(boardVO);
        log.info("BoardController-Board insertCnt : {} ", insertCnt);
        return insertCnt == 1 ? new ResponseEntity<String>("success", HttpStatus.OK) :
                new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ApiOperation(value = "글 작성", notes = "유효한 계정이어야 하며, user_idx가 외래키로 지정되어 있습니다.")
    @PostMapping(value = "insertBoard")
    // 글 쓰기
    // 유효한 계정일 것.
    public ResponseEntity<Message> insertBoard(
            @RequestBody(required = false) BoardVO boardVO
    ) {
        log.info("BoardController-insertBoard 호출 : 현 로그인 계정 {}, 작성시도 게시물 {}", boardVO);
        UserVO dbUserVO = null;
        int insertBoardFlag = 1; // 0 성공, 1 실패, 2 계정없음
        if (boardVO != null) {
            if (dbUserVO != null) {
                try {
                    log.info("BoardController-insertBoard 유효한 dbUserVO 확인 {}", dbUserVO);
                    boardService.insertBoard(boardVO);
                    log.info("BoardController-insertBoard insertBoard 성공");
                    log.info("BoardController-insertBoard return flag 1");
                    HashMap<String, String> saveResult = new HashMap<>();
                    return new ResponseEntity(saveResult, HttpStatus.OK);
                } catch (Exception e) {
                    log.info("BoardController-insertBoard insertBoard 중 오류 발생");
                    log.info("BoardController-insertBoard return flag 1");
                    HashMap<String, String> saveResult = new HashMap<>();
                    return new ResponseEntity(saveResult, HttpStatus.OK);
                }
            } else {
                log.info("BoardController-insertBoard 유효하지 않은 계정");
                log.info("BoardController-insertBoard return flag 2");
                HashMap<String, String> saveResult = new HashMap<>();
                return new ResponseEntity(saveResult, HttpStatus.OK);
            }
        } else {
            log.info("BoardController-insertBoard boardVO == null || user_id == null");
            log.info("BoardController-insertBoard return flag 1");
            HashMap<String, String> saveResult = new HashMap<>();
            return new ResponseEntity(saveResult, HttpStatus.OK);
        }
    }

    @ApiOperation(value = "상세보기", notes = "조회하려는 게시물이 유효한 게시물이어야 합니다.")
    @PostMapping(value = "/selectByIdx")
    // 글 상세보기
    // 유효한 boardVO 객체일 것.
    public BoardVO selectByIdx(@RequestBody int board_idx) {
        log.info("BoardController-selectByIdx 호출 : 조회시도 게시물 board_idx() {}", board_idx);
        BoardVO dbBoardVO = null;
        if (board_idx != 0) {
            dbBoardVO = boardService.selectByIdx(board_idx);
        } else {
            log.info("BoardController-selectByIdx 유효하지 않은 게시물");
            return new BoardVO();
        }
        return new BoardVO();
    }

    @ApiOperation(value = "글 수정", notes = "원본 글 작성자와 수정 희망자가 일치해야 정상 동작합니다.")
    @PostMapping(value = "/updateBoard")
    // 글 수정하기
    // 유효한 계정일 것.
    // 글이 존재할 것.
    // 원본 글 작성자와 수정 희망자가 일치할 것.
    public int updateBoard(@RequestBody BoardVO boardVO, @RequestHeader String user_id) throws JsonMappingException {
        log.info("BoardController-updateBoard 호출 : 현 로그인 계정 {}, 수정희망 게시물 {}", user_id, boardVO);
        UserVO dbUserVO = null;
        BoardVO dbBoardVO = null;
        int updateBoardFlag = 0; // 0 실패, 1 성공, 2 수정 전 게시글없음, 3 유효하지않은 계정, 4 계정 불일치
        if (boardVO != null && user_id != null) {
            log.info("BoardController-updateBoard boardVO && user_id not null");
            dbBoardVO = boardService.selectByIdx(boardVO.getBoard_idx());
            log.info("BoardController-updateBoard 수정 전 게시물 dbBoardVO 확인 {}", dbBoardVO);
            if (dbBoardVO == null) {
                log.info("BoardController-updateBoard dbBoardVO null updateBoardFlag = 2");
                return updateBoardFlag = 2;
            }
            dbUserVO = userService.selectByUserId(user_id);
            log.info("BoardController-updateBoard 계정 정보 dbUserVO 확인 {}", dbUserVO);
            if (dbUserVO == null) {
                log.info("BoardController-updateBoard dbUserVO null updateBoardFlag = 3");
                return updateBoardFlag = 3;
            }
            if (boardVO.getUser_idx() != dbUserVO.getUser_idx()) {
                log.info("BoardController-updateBoard 원본 글 작성자와 작성 글 삭제 요청자 불일치 updateBoardFlag = 4");
                return updateBoardFlag = 4;
            } else {
                log.info("BoardController-updateBoard 원본 글 작성자와 작성 글 삭제 요청자 일치 updateBoardFlag = 1");
                boardService.updateBoard(boardVO, user_id);
                log.info("BoardController-updateBoard 수정 성공 Flag 1");
                return updateBoardFlag = 1;
            }
        } else {
            log.info("BoardController-updateBoard 오류 발생 updateBoardFlag = 0 ");
            return updateBoardFlag;
        }
    }

    @ApiOperation(value = "글 삭제", notes = "원본 글 작성자와 삭제 희망자가 일치해야 정상 동작합니다.")
    @PostMapping(value = "/deleteBoard")
    // 글 삭제하기
    // 유효한 계정일 것.
    // 글이 존재할 것.
    // 원본 글 작성자와 작성 글 삭제 요청자가 일치할 것.
    public int deleteBoard(BoardVO boardVO, String user_id) throws JsonMappingException {
        log.info("BoardController-deleteBoard 호출 현 로그인 계정 {}, 삭제 희망 게시물 {}", user_id, boardVO);
        UserVO dbUserVO = null;
        BoardVO dbBoardVO = null;
        int deleteBoardFlag = 0; // 0 실패, 1 성공, 2 삭제 전 게시물 없음, 3 유효하지 않은 계정, 4 불일치
        if (boardVO == null) {
            log.info("BoardController-deleteBoard 오류 발생 boardVO null 값 넘어옴");
            return deleteBoardFlag;
        }
        if (user_id == null) {
            log.info("BoardController-deleteBoard 오류 발생 user_id null 값 넘어옴");
            return deleteBoardFlag;
        }
        dbBoardVO = boardService.selectByIdx(boardVO.getBoard_idx());
        if (dbBoardVO == null) {
            log.info("BoardController-deleteBoard 오류 발생 dbBoardVO null 값");
            return deleteBoardFlag = 2;
        }
        dbUserVO = userService.selectByUserId(user_id);
        if (dbUserVO == null) {
            log.info("BoardController-deleteBoard 오류 발생 dbUserVO null 값");
            return deleteBoardFlag = 3;
        }
        if (boardVO.getUser_idx() != dbUserVO.getUser_idx()) {
            log.info("BoardController-deleteBoard 원본 글 작성자와 작성 글 삭제 요청자 불일치");
            return deleteBoardFlag = 4;
        } else {
            log.info("BoardController-deleteBoard 원본 글 작성자와 작성 글 삭제 요청자 일치");
            boardService.deleteBoard(boardVO, user_id);
            log.info("BoardController-deleteBoard 게시글 삭제 최종 완료 flag = 1");
            return deleteBoardFlag = 1;
        }
    }
}
