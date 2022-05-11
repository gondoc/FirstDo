<%@ page import="com.eseict.gondo.vo.BoardVO" %>
<%@ page import="com.eseict.gondo.vo.PagingVO" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ include file="include.jsp" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    request.setCharacterEncoding("utf-8");
//  List<BoardVO> boardList = (List<BoardVO>)request.getAttribute("boardList");
    PagingVO<BoardVO> boardList = (PagingVO<BoardVO>)request.getAttribute("pv");
    request.setAttribute("pv", boardList);
    request.setAttribute("paging", boardList.getPageList());
%>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8;">
    <link rel="stylesheet" type="text/css" href="/css/style.css">
    <title>main</title>
</head>

<body>
<div id="wrapper">
    <div class="container-head">
        <h3>MainPage</h3>
    </div>
    <tr>
        <td class="subTitle">${pv.pageInfo}</td>
    </tr>
    <table>
        <thead>
        <tr>
            <td>no</td>
            <td>제목</td>
            <td>작성일</td>
        </tr>
        </thead>
        <%-- 글 1개당 1개의 행 --%>
        <%
            if (boardList.getTotalCount() == 0) {
        %>
        <tr>
            <td colspan="3">
                등록된 내용이 없습니다.
            </td>
        </tr>
        <%
        } else {
            // 글 번호는 계산해서 출력해보자
            for (BoardVO vo : boardList.getList()) {
                int no = boardList.getTotalCount() - (boardList.getCurrentPage() - 1) * boardList.getPageSize();
                int idx = vo.getBoard_idx();
                String board_subject = vo.getBoard_subject();
                Date regDate = vo.getBoard_regDate();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 MM분");
                String board_regDate = simpleDateFormat.format(regDate);
        %>

        <tbody>
        <tr style="border: 1px solid black;">
            <%-- 글 넘버 --%>
            <td>
                <%=idx%>
            </td>
            <%-- 제목 --%>
            <td>
                <a href="board/view?idx=<%=idx%>">
                    <%=board_subject%>
                </a>

            </td>
            <%-- 작성일 --%>
            <td>
                <%=board_regDate%>
            </td>
        </tr>
            <%
                }
            }
        %>
    </table>
    <div class="home-login">
        <div id="login_1">
            <input type="button" value="글쓰기" onclick="location.href='insertBoard'" style="width: 200px;"/>
        </div>
    </div>
    <tr>
        <td colspan="5" class="sub_title" style="text-align: center;">
            <%=boardList.getPageList()%>
        </td>
    </tr>
</div>
<br>

<footer>
    <div>
        <table class="footer">
            <tr>
                <td rowspan="2" style="vertical-align: middle;">
                    <img src="images/eseLogo.png" alt="이에스이로고" title="이에스이로고">
                </td>
                <td>경기도 성남시 분당구 판교로228번길 15, 1단지 3동 501호(삼평동)</td>
            </tr>
            <tr>
                <td>TEL: 070.5079.1111&nbsp;FAX : 070.5079.1101</td>
            </tr>
            <tr>
                <td colspan="3" style="color: graytext;">Copyright © ESE Co.,Ltd. All Right Reserved.</td>
            </tr>
        </table>
    </div>
</footer>
</body>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" ></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="${pageContext.request.contextPath }/js/comm.js"></script>
<link rel="stylesheet" type="text/css" href="/css/style.css">
</html>
