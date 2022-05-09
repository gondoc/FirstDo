<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8;">
    <title>home</title>


    <script type="text/javascript">
        // $(function(){
        // 	$("#listCount").change(function(){
        // 		var pageSize = $(this).val();
        // 		//alert(pageSize);
        // 		SendPost("/qna/qnaList", {"p":${cv.currentPage},"s" : pageSize ,"b":${cv.blockSize}});
        // 	});
        // });
    </script>
    <script type="text/javascript">

    </script>
</head>
<body>
<div id="wrapper">
    <div class="main-head">
        <h2>InsertBoardPage</h2>
    </div>
    <div class="home-login">
        <div id="login_1">
            <input type="button" value="메인으로" onclick="location.href='/main'" style="width: 200px;"/>
        </div>
    </div>
    <div>
        <c:forEach var="boardList" items="${boardList }">
            ${boardList }
        </c:forEach>
    </div>
    <div>
        p : ${cv.p}, s: ${cv.s } ,b: ${cv.b },<br/>currentPage: ${cv.currentPage }, pageSize: ${cv.pageSize },
        blockSize: ${cv.blockSize }
    </div>
    <table id="content">
        <tr>
            <td colspan="5" class="title">게시판</td>
        </tr>
        <tr>
            <td colspan="5" class="info">
                ${pv.pageInfo }

                <select id="listCount">
                    <option value="5" ${cv.pageSize==5 ? " selected='selected' " : "" }>5개</option>
                    <option value="10" ${cv.pageSize==10 ? " selected='selected' " : "" }>10개</option>
                    <option value="20" ${cv.pageSize==20 ? " selected='selected' " : "" }>20개</option>
                    <option value="30" ${cv.pageSize==30 ? " selected='selected' " : "" }>30개</option>
                    <option value="40" ${cv.pageSize==40 ? " selected='selected' " : "" }>40개</option>
                </select>
                씩 보기
            </td>
        </tr>
        <tr>
            <th width="5%">No</th>
            <th width="5%">이름</th>
            <th width="40%">제목</th>
            <th width="10%">작성일</th>
            <th width="8%">작성자</th>
        </tr>
        <c:if test="${pv.totalCount==0 }">
            <tr>
                <td colspan="5" class="info2">등록된 글이 없습니다.</td>
            </tr>
        </c:if>
        <c:if test="${not empty boardList}">
            <c:forEach var="vo" items="${pv.list }" varStatus="vs">
                <tr align="center">
                    <td>${vo.board_idx }</td>
                    <td>
                        <c:forEach var="nme" items="${pv.namelist }" varStatus="vs">
                            <c:if test="${nme.back_qna_idx == vo.back_Qna_Idx }">
                                ${nme.user_name }
                            </c:if>
                        </c:forEach>
                    </td>
                    <td align="left">
                        <a href="#"
                           onclick='SendPost("${pageContext.request.contextPath }/qna/qnaView" ,{"p":${pv.currentPage },"s":${pv.pageSize },"b":${pv.blockSize },"idx":${vo.back_Qna_Idx },"m":"view","h":"true"},"post")'><c:out
                                value="${vo.back_Qna_Name }"></c:out></a>
                    </td>
                    <td>
                        <fmt:formatDate value="${vo.board_regDate }" type="date" dateStyle="short"/>
                    </td>
                    <td></td>
                </tr>
            </c:forEach>
            <tr>
                <td style="border: none; text-align: center;" colspan="5">${pv.pageList }</td>
            </tr>
        </c:if>
        <tr>
            <td class="info" colspan="5">
                <button type="button" class="btn btn-outline-success btn-sm"
                        onclick='SendPost("${pageContext.request.contextPath }/qna/qnaInsertForm",{"p":${pv.currentPage },"s":${pv.pageSize },"b":${pv.blockSize }},"post")'>
                    글쓰기
                </button>
            </td>
        </tr>
    </table>
</div>

</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="${pageContext.request.contextPath }/static/js/comm.js"></script>
<link rel="stylesheet" href="/static/js/style.js">
</html>