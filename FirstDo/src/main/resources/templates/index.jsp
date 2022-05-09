<!DOCTYPE html>
<html xmlns xmlns:th="http://www.w3.org/1999/xhtml" : th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>thymeleaf</title>
</head>
<body>
<table id="content">
    <tr>
    </tr>
    <thead>

    </thead>

    <tbody>
    <!-- CONTENTS !-->
    <tr th:each="boardVO : ${boardList}">
        <td th:text="${boardVO.board_idx}"></td>
        <td><a th:href="'/board/post/'+${boardVO.board_idx}" th:text="${boardVO.board_subject}"></a></td>
        <td th:text="${boardVO.board_regDate}"></td>
    </tr>

    </tbody>
</table>
</body>
</html>