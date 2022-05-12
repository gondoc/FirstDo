<%@ page import="com.eseict.gondo.vo.BoardVO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    request.setCharacterEncoding("utf-8");
    BoardVO vo = (BoardVO) request.getAttribute("view");
    String subject = vo.getBoard_subject();
    String content = vo.getBoard_content();
    content = content.replaceAll("<", "&lt"); // 태그 무시
    content = content.replaceAll("\n", "<br>");
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8;">
    <title>상세보기</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

    <script type="text/javascript">

    </script>
</head>
<body>
<div id="wrapper">
    <div class="container-head">
        <h3>ViewBoardPage</h3>
    </div>
    <div class="home-login">
        <div id="login_1">
            <input type="button" value="메인으로" onclick="location.href='/'" style="width: 200px;"/>
        </div>
    </div>
    <form id="board-form">
        <div>
            <input type="hidden" name="board_idx" id="board_idx" value="<%=vo.getBoard_idx()%>">
        </div>
        <div class="container-body">
            <label>글 제목</label><br>
            <input type="text" name="board_subject" id="board_subject" value="<%=vo.getBoard_subject()%>" readonly>
        </div>
        <div class="container-body">
            <label>글 본문</label><br>
            <textarea name="board_content" id="board_content" readonly><%=vo.getBoard_content()%></textarea>
        </div>
    </form>
    <div style="text-align: center">
        <input type="button" value="수정하기" id="update-do" style="display: inline-block"/>
        <input type="button" value="수정하기" id="update-submit" style="display: none"/>
        <input type="button" value="취소하기" id="cancel-button"
               style="display: none;" onclick="document.location.reload()"/>
        <input type="button" value="삭제하기" id="delete-do" style="display: inline-block"/>
    </div>
</div>
<br>
<footer>
    <div>
        <table class="footer">
            <tr>
                <td rowspan="2" style="vertical-align: middle;">
                    <img src="http://www.eseict.com/Nsco/images/ese_logo_ft.png" alt="이에스이로고" title="이에스이로고">
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
<script type="text/javascript">
    $(function () {
        $("input#update-do").on("click", function () {
            if (confirm("수정하시겠습니까?") == true) {
                // board-form id를 update-form 으로 변경
                $("#board-form").attr('id', 'update-form');
                // board_subject, board_content readonly 속성 해제
                $("input[name='board_subject']").attr("readonly", false);
                $("textarea[name='board_content']").attr("readonly", false);
                // cancel-button display on
                $("input#cancel-button").css("display", "");
                // update-do display none
                $("input#update-do").css("display", "none");
                // update-submit display on
                $("input#update-submit").css("display", "");
                // delete button display none
                $("input#delete-do").css("display", "none");
            } else {
                $("input[name='board_subject']").attr("readonly", true);
                $("textarea[name='board_content']").attr("readonly", true);
                $("input[name='cancel-button']").css("display", "none");
                return;
            }
            $(function () {
                $("#update-submit").on("click", function () {
                    const update = $("#update-form").serialize();
                    if (confirm("작성한 내용으로 수정하시겠습니까?") == true) {
                        $.ajax({
                            cache: false,
                            type: "POST",
                            url: "/board/updateBoard",
                            data: update,
                            dataType: 'text',
                            success: function (data) {
                                alert("update success");
                                console.log(data)
                                window.location.replace("/board/view?idx=<%=vo.getBoard_idx()%>")
                            },
                            error: function (request, status, error) {
                                console.log("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
                            }
                        });
                    } else {
                        $("input[name='board_subject']").attr("readonly", false);
                        $("textarea[name='board_content']").attr("readonly", false);
                        $("input[name='cancel-button']").css("display", "");
                        return;
                    }
                });
            });
        });
        $("#delete-do").on("click", function () {
            if (confirm("삭제하시겠습니까?") == true) {
                const del = $("#board-form").serialize();
                console.log(del);
                $.ajax({
                    cache: false,
                    type: "POST",
                    url: "/board/deleteBoard",
                    data: del,
                    dataType: 'text',
                    success: function (data) {
                        alert("delete success");
                        window.location.href = "/"
                        console.log(data);
                    },
                    error: function (request, status, error) {
                        console.log("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
                    }
                });
            } else {
                return;
            }
        });
    });
</script>
<script src="${pageContext.request.contextPath }/js/comm.js"></script>
<link rel="stylesheet" type="text/css" href="/css/style.css">
</html>