package com.eseict.gondo.controller;

import com.eseict.gondo.Message;
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
    public ResponseEntity<String> Board(
            @RequestParam String board_subject,
            @RequestParam String board_content) {
        log.info("BoardController-Board 호출 : board_subject {} ", board_subject);
        log.info("BoardController-Board 호출 : board_content {} ", board_content);
        int insertCnt = 0;
        BoardVO boardVO = new BoardVO();
        boardVO.setBoard_subject(board_subject);
        boardVO.setBoard_content(board_content);
        log.info("BoardController-Board : BoardVO {} ", boardVO);
        insertCnt = boardService.insertBoard(boardVO);
        log.info("BoardController-Board insertCnt : {} ", insertCnt);
        return insertCnt == 1 ? new ResponseEntity<String>("success", HttpStatus.OK) :
                new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @PostMapping(value = "insertBoard")
    // 글 쓰기
    public ResponseEntity<Message> insertBoard(
            BoardVO boardVO) {
        log.info("BoardController-insertBoard 호출 : 작성시도 게시물 {}", boardVO);
        HashMap<String, String> saveResult = new HashMap<>();
        if (boardVO != null) {
            try {
                boardService.insertBoard(boardVO);
                log.info("BoardController-insertBoard insertBoard 성공");
                return new ResponseEntity(saveResult, HttpStatus.OK);
            } catch (Exception e) {
                log.info("BoardController-insertBoard insertBoard 중 오류 발생");
                return new ResponseEntity(saveResult, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity(saveResult, HttpStatus.INTERNAL_SERVER_ERROR);
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
    // 글이 존재할 것.
    public ResponseEntity<String> updateBoard(BoardVO boardVO) throws JsonMappingException {
        log.info("BoardController-updateBoard 호출 : 수정희망 게시물 {}", boardVO);
        HashMap<String, String> saveResult = new HashMap<>();
        Message msg = new Message();
        BoardVO dbBoardVO = null;
        int updateCnt = 0;
        if (boardVO != null) {
            try {
                updateCnt = boardService.updateBoard(boardVO);
//                return new ResponseEntity(saveResult, HttpStatus.OK);
                log.info("BoardController-updateBoard 호출 : updateCnt {}", updateCnt);
                saveResult.put("updateCnt", "updateCnt");
                return updateCnt == 1 ? new ResponseEntity<String>("success", HttpStatus.OK) :
                        new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
            } catch (Exception e) {
//                log.info("BoardController-insertBoard updateBoard 중 오류 발생");
//                msg.setMessage("오류발생");
//                saveResult.put(boardVO, msg);
                return new ResponseEntity(saveResult, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity(saveResult, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ApiOperation(value = "글 삭제", notes = "원본 글 작성자와 삭제 희망자가 일치해야 정상 동작합니다.")
    @PostMapping(value = "/deleteBoard")
    // 글 삭제하기
    // 글이 존재할 것.
    public ResponseEntity<String> deleteBoard(BoardVO boardVO) throws JsonMappingException {
        log.info("BoardController-deleteBoard 호출 삭제 희망 게시물 {}", boardVO);
        HashMap<String, String> saveResult = new HashMap<>();
        BoardVO dbBoardVO = null;
        int deleteCnt = 0;
        if (boardVO == null) {
            log.info("BoardController-deleteBoard 오류 발생 boardVO null 값 넘어옴");
            return new ResponseEntity(saveResult, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        dbBoardVO = boardService.selectByIdx(boardVO.getBoard_idx());
        if (dbBoardVO == null) {
            log.info("BoardController-deleteBoard 오류 발생 boardVO getBoard_idx null 값");
            return new ResponseEntity(saveResult, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        log.info("BoardController-deleteBoard 삭제쿼리 실행");
        deleteCnt = boardService.deleteBoard(boardVO);
        return deleteCnt > 0 ? new ResponseEntity<String>("success", HttpStatus.OK) :
                new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
