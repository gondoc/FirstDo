<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>home</title>
<script type="text/javascript">
function boardList(){
	
	$.ajax({
		type: "POST",
// 		data: {
// 			"banner_id" : id
// 		},
		url: "/",
        success: function(res) {
        	alert('삭제완료');
			location.reload();
    	},
    	error : function(){
    		alert('에러!!!');
    	}
		
	})
}
</script>
</head>
<body>
	<h2>MainPage</h2>
	<div id="head">
		<a href="login.jsp">로그인하기</a>
	</div>
	<div>
	<c:forEach var="boardList" items="${boardList }">
	
		${boardList }
	</c:forEach>
	</div>
</body>
</html>