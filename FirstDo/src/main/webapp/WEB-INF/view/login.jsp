<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8;">
    <link rel="stylesheet" type="text/css" href="/css/style.css">

    <title>login</title>


    <script>
        function ajax() {

        }
    </script>

    <script type="text/javascript">
        function formCheck() {
            var id_value = document.getElementById('user_id');
            var pw_value = document.getElementById('user_pw').querySelector.toString;
            alert("id_value : " + id_value);
            alert("pw_value : " + id_value);
            if (document.getElementById(user_id) == '') {
                alert('ID는 반드시 입력해야 합니다.');
                $("#user_id").val("");
                $("#user_id").focus();
                return false;
            }
            // if (!pw_value || pw_value.trim() == "<p><br></p>") {
            if (document.getElementById(user_pw) == '') {
                alert('PW는 반드시 입력해야 합니다.');
                $("#user_pw").val("");
                $("#user_pw").focus();
                return false;
            }
            return true;
        }
    </script>
</head>

<body>
<div id="wrapper">
    <div class="container-head">
        <h3>LoginPage</h3>
    </div>
    <div class="home-login">
        <div id="login_1">
            <input type="button" value="메인으로" onclick="location.href='/'" style="width: 200px;"/>
        </div>
    </div>

    <div class="container-body">
        <form action="/main/login" method="post" onsubmit="return formCheck();">
            <div class="container-user">
                <label>ID</label>
                <input type="text" id="user_id" placeholder="ID를 입력하세요" style="width: 308px; margin: 2px;"/>
                <br/> <label>PW</label>
                <input type="password" id="user_pw" placeholder="PW를 입력하세요" style="width: 300px; margin: 2px;"/>
                <br/>
                <input type="submit" value="로그인" style="width: 300px; margin: 2px;"/>
            </div>
        </form>
        <div>
            <input type="button" value="아이디찾기" style="width: 145px; margin: 2px; padding: 2px;"/>
            <input type="button" value="비밀번호찾기" style="width: 145px; margin: 2px; padding: 2px;"/>
            <br/>
            <input type="button" value="회원가입" style="width: 300px; margin: 2px; padding: 2px;"/>
        </div>
    </div>
</div>
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
<link rel="stylesheet" type="text/css" href="/static/js/style.js">
</html>