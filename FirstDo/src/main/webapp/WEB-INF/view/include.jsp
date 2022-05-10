<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    // 여러개의 파일에 공통으로 들어가는 코드는 별도로 만들어 놓고 필요시 포함해서 사용하면 좋다.
// POST전송시 한글깨짐 방지
    request.setCharacterEncoding("UTF-8");
// 현재 페이지 번호 받기
    int currentPage = 1;
    try {
        currentPage = Integer.parseInt(request.getParameter("p"));
    } catch (Exception e) {
        ;
    }
// 페이지 사이즈 받기
    int pageSize = 10;
    try {
        pageSize = Integer.parseInt(request.getParameter("s"));
    } catch (Exception e) {
        ;
    }
// 페이지 개수 받기
    int blockSize = 10;
    try {
        blockSize = Integer.parseInt(request.getParameter("b"));
    } catch (Exception e) {
        ;
    }
// 글번호 받기
    int board_idx = 0;
    try {
        board_idx = Integer.parseInt(request.getParameter("board_idx"));
    } catch (Exception e) {
        ;
    }
// 수정(2)/삭제(3)/저장(1)인지를 구분하는 값 받기
    int mode = 0;
    try {
        mode = Integer.parseInt(request.getParameter("m"));
    } catch (Exception e) {
        ;
    }
    request.setAttribute("p", currentPage);
    request.setAttribute("s", pageSize);
    request.setAttribute("b", blockSize);
    request.setAttribute("board_idx", board_idx);
    request.setAttribute("m", mode);
    request.setAttribute("newLine", "\n");
    request.setAttribute("br", "<br>");
%>