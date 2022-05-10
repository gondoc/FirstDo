<%@ page import="java.util.List" %>
<%@ page import="com.eseict.gondo.vo.BoardVO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.eseict.gondo.vo.PagingVO" %>
<%@ include file="include.jsp"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    request.setCharacterEncoding("utf-8");
    List<BoardVO> boardList = (List<BoardVO>)request.getAttribute("boardList");
//    PagingVO<BoardVO> list = (PagingVO<BoardVO>)request.getAttribute("pv");
%>
<html>

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
    <table id="content">
        <tr>
        </tr>
        <thead>
        <tr>
            <th width="5%">No</th>
            <th width="40%">제목</th>
            <th width="10%">작성일</th>
        </tr>
        </thead>

        <tbody class="boardList">
        <!-- CONTENTS !-->
        <thead>

        </thead>

        <%=boardList%>


        </tbody>

        <br>

    </table>

    <div class="home-login">
        <div id="login_1">
            <input type="button" value="로그인하기" onclick="location.href='login'" style="width: 200px;"/>
        </div>
    </div>
    <input type="button" value="글 쓰기" onclick="location.href='insertBoard'" style="width: 200px; margin-right: 15px;"/>
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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="${pageContext.request.contextPath }/static/js/comm.js"></script>
</html>
