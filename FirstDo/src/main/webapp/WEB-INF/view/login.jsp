<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>login</title>
<script src="js/jquery.min.js"></script>

<style type="text/css">
#wrapper {
	height: auto;
	min-height: 100%;
	padding-bottom: 100px;
}

.container-head {
	height: 100px;
	vertical-align: middle;
	text-align: center;
	margin: 15px;
	border: 2px solid black;
	font: bold;
	font-size: 20pt;
}

.container-body {
	text-align: center;
	margin: 15px;
	padding: 15px;
	border: 2px solid black;
}

table {
	vertical-align: middle;
	display: inline-block;
	text-align: center;
}

footer {
	border: 2px solid black;
	vertical-align: middle;
	text-align: center;
	height: 100px;
	position: relative;
	transform: translateY(-100%);
	margin: 15px;
	padding: 10px;
	font-size: 10pt;
}
</style>

<script>
	function ajax() {
		var user_id = userId

	}
</script>


<script type="text/javascript">
	function formCheck() {
		var id_value = $("#user_id");
		var pw_value = $("#user_pw");
		alert("값 : " + id_value);
		if (!id_value || id_value.trim() == "<p><br></p>") {
			alert('ID는 반드시 입력해야 합니다.');
			$("#user_id").val("");
			$("#user_id").focus();
			return false;
		}
		if (!pw_value || pw_value.trim() == "<p><br></p>") {
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
		<div class="container-body">
			<form action="/main/login" method="post" onsubmit="return formCheck();">
				<div class="container-user">
					<label>ID</label>
					<input type="text" id="user_id" placeholder="ID를 입력하세요" style="width: 308px; margin: 2px;" required />
					<br />
					<label>PW</label>
					<input type="password" id="user_pw" placeholder="PW를 입력하세요" style="width: 300px; margin: 2px;" required />
					<br />
					<input type="submit" value="로그인" style="width: 300px; margin: 2px;" />
				</div>
			</form>
			<div>
				<input type="button" value="아이디찾기" style="width: 145px; margin: 2px; padding: 2px;" />
				<input type="button" value="비밀번호찾기" style="width: 145px; margin: 2px; padding: 2px;" />
				<br />
				<input type="button" value="회원가입" style="width: 300px; margin: 2px; padding: 2px;" />
			</div>
		</div>
	</div>
	<footer>
		<div>
			<table>
				<tr>
					<td rowspan="2" style="vertical-align: middle;"><img src="images/eseLogo.png" alt="이에스이로고" title="이에스이로고"></td>
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
</html>