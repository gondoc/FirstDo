package com.eseict.gondo.service;

import com.eseict.gondo.JDBCUtil;
import com.eseict.gondo.vo.PagingVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.eseict.gondo.dao.BoardDAO;
import com.eseict.gondo.dao.UserDAO;
import com.eseict.gondo.vo.BoardVO;
import com.eseict.gondo.vo.UserVO;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Service("boardService")
public class BoardServiceImpl implements BoardService {

    //	@Autowired
    private UserDAO userDAO;

    //	@Autowired
    private BoardDAO boardDAO;

    public BoardServiceImpl(UserDAO userDAO, BoardDAO boardDAO) {
        super();
        this.userDAO = userDAO;
        this.boardDAO = boardDAO;
    }

    @Override
    // <!-- 01. insert_글 쓰기 -->
    // db 내 유저 아이디 존재시 글쓰기 진행.
    // user_id가 db 내 유효할 것.
    // insert 중 boardVO에 user_idx 외래키 처리 할 것.
    // flag 처리 할 것.
    public int insertBoard(BoardVO boardVO) {
        log.info("BoardServiceImpl-insertBoard 호출 : boardVO {}", boardVO);
        int insertBoardFlag = 0; // 0 실패, 1 성공
        // 작성 게시글 확인, 유저 아이디 확인
        if (boardVO != null) {
            try {
                log.info("BoardService-insertBoard boardVO insert 시도 {}", boardVO);
                int rs = boardDAO.insertBoard(boardVO);
                log.info("BoardService-insertBoard boardVO rs {}", rs);
                // 삼항연산
                log.info("BoardService-insertBoard 글 쓰기 성공 insertBoardFlag = 1 리턴");
                return insertBoardFlag = 1;
            } catch (Exception e) {
                log.info("BoardServiceImpl-insertBoard insertBoard 실행중 오류발생");
                log.info("BoardServiceImpl-insertBoard insertBoard insertBoardFlag = 0 리턴");
                e.printStackTrace();
                return insertBoardFlag;
            }
        } else {
            log.info("BoardServiceImpl-insertBoard dbUserVO null 유저가 유효하지 않음");
            log.info("BoardServiceImpl-insertBoard insertBoard insertBoardFlag = 0 리턴");
            return insertBoardFlag;
        }

    }

    @Override
    // 글 상세보기
    // int board_idx != 0
    // 글이 없다면 빈 boardVO 를 리턴할 것.
    public BoardVO selectByIdx(int board_idx) {
        log.info("BoardServiceImpl-selectByIdx 호출 : int board_idx {}", board_idx);
        BoardVO dbBoardVO = null;
        if (board_idx != 0) {
            dbBoardVO = boardDAO.selectByIdx(board_idx);
            log.info("BoardServiceImpl-selectByIdx 리턴 : dbBoardVO {}", dbBoardVO);
            return dbBoardVO;
        } else {
            log.info("BoardServiceImpl-selectByIdx 리턴 : 오류 발생 빈 객체 리턴");
            return dbBoardVO = new BoardVO();
        }
    }

    @Override
    // 글 수정하기
    // flag 처리 할 것.
    public int updateBoard(BoardVO boardVO) {
        log.info("BoardServiceImpl-updateBoard 호출 : boardVO {} ", boardVO);
        int rs = 0;
        if (boardVO != null) {
            rs = boardDAO.updateBoard(boardVO);
        }
        return rs;
    }

    @Override
    // 글 삭제하기
    // 원본 글의 작성자와 삭제 희망자가 일치할 것.
    // 삭제 전 원본 글이 유효할 것.
    // flag 활용할 것.
    public int deleteBoard(BoardVO boardVO) {
        log.info("BoardServiceImpl-deleteBoard 호출 : boardVO {}", boardVO);
        BoardVO dbBoardVO = null;
        int deleteCnt = 1;
        if (boardVO != null) {
            log.info("BoardServiceImpl-deleteBoard boardVO 존재, user_id 존재 확인");
            try {
                log.info("BoardServiceImpl-deleteBoard 삭제 쿼리 실행");
                deleteCnt = boardDAO.deleteBoard(boardVO.getBoard_idx());
                return deleteCnt;
            } catch (Exception e) {
                log.info("BoardServiceImpl-deleteBoard deleteBoard 실행중 오류 확인");
                return deleteCnt;
            }
        }
        return deleteCnt;
    }

    @Override
    public int selectCount() {
        int totalCount = boardDAO.selectCount();
        return totalCount;
    }

    @Override
    public List<BoardVO> selectBoardList() {
        List<BoardVO> list = boardDAO.selectBoardList();
        return list;
    }

    @Override
    public PagingVO<BoardVO> selectList(int currentPage, int pageSize, int blockSize) throws SQLException {
        log.info("BoardServiceImpl-selectList 호출 : currentPage {}, pageSize {}, blockSize {} ", currentPage, pageSize, blockSize);
        int totalCount = boardDAO.selectCount();
        PagingVO<BoardVO> pagingVO = new PagingVO<BoardVO>(totalCount, currentPage, pageSize, blockSize);

        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("startNo", pagingVO.getStartNo());
        map.put("pageSize", pagingVO.getPageSize());

        List<BoardVO> boardList = boardDAO.selectList(map);
        log.info("BoardServiceImpl-selectBoardList : boardList {} ", boardList);
        pagingVO.setList(boardList);
        log.info("BoardServiceImpl-selectBoardList : pagingVO {} ", pagingVO);
        return pagingVO;
    }
}
