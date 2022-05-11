<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    request.setCharacterEncoding("utf-8");

%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8;">
    <title>글 쓰기</title>
</head>
<body>
<div id="wrapper">
    <div class="container-head">
        <h3>InsertBoardPage</h3>
    </div>
    <div class="home-login">
        <div id="login_1">
            <input type="button" value="메인으로" onclick="location.href='/'" style="width: 200px;"/>
        </div>
    </div>

    <form id="insert-form" >
        <label>제목</label>
        <input name="board_subject" type="text" id="board_subject" width="300px" placeholder="글의 제목입니다." required> <br>
        <br/>
        <label>본문</label>
        <textarea name="board_content" id="board_content" rows="5" cols="30" placeholder="글의 본문입니다." required></textarea>
        <br/>
        <input type="button" id="insert-submit" value="글 등록하기" style="width: 300px; height: 80px; margin: 2px;"/>
    </form>

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

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath }/js/comm.js"></script>
<link rel="stylesheet" type="text/css" href="/css/style.css">
<script type="text/javascript">
    $(function () {
        $('#insert-submit').on("click", function () {
            const insert = $("#insert-form").serialize();
            console.log(insert);
            $.ajax({
                cache: false,
                type: "post",
                url: "/board/insertBoard",
                data : insert,
                dataType: 'json',
                success: function (data) {
                    alert("success");
                    console.log(data);
                },
                error: function (request, status, error) {
                    console.log("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);

                }
            });
        });
    });
</script>
</body>
</html>