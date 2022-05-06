<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>login</title>
<style type="text/css">
.main-head {
	text-align: center;
	margin: 1em;
	border: 2px solid black;
	font-size: 15pt;
}
</style>
<script type="text/javascript">
	function formCheck() {
		var id_value = $("#user_id");
		var pw_value = $("#user_pw");
		// alert("값 : " + value);
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
	<div class="main-head">
		<h2>LoginPage</h2>
	</div>
	<c:if test="${user==null }">
		<form action="/main/login" method="post" onsubmit="return formCheck();">
			<div>
				<div>
					<label>ID</label>
					<input type="text" id="user_id" placeholder="ID" tabindex="1" />
				</div>
				<div>
					<label>Password</label>
					<input type="password" id="user_pw" placeholder="PW" tabindex="2" />
				</div>
				<div>
					<input type="submit" value="LOG-IN" tabindex="3" />
					<input type="button" value="회원가입" tabindex="4" />
				</div>
				<div>
					<input type="button" value="아이디찾기" tabindex="5" />
					<input type="button" value="비밀번호찾기" tabindex="6" />
				</div>
			</div>
		</form>
	</c:if>
	<c:if test="${user != null}">
		<c:redirect url="/board/boardList" />
	</c:if>


</body>

</html>