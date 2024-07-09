<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CodeWhale - Join</title>
<link rel="stylesheet" href="/resources/css/main.css">
<script type="text/javascript" src="/resources/js/join.js"></script>
</head>
<body>
	<%@ include file="include/header.jsp" %>
	<c:if test="${joinFail == 1}">
		<script type="text/javascript">
			alert("이미 존재하는 아이디입니다. 다른 아이디를 입력해주세요");
			history.go(-1);
		</script>
	</c:if>
	
	<h3>Join Member</h3>
	
	<div id="join_box">
		<p>${mname }(${mid })님 회원가입에 성공하였습니다.</p>
		<p><br/></p>
		<p><input type="button" value="LOGIN" onclick="javascript:window.location.href='login'" /> <input type="button" value="BOARD" onclick="javascript:window.location.href='boardList'" /></p>
	</div>
	
	<%@ include file="include/footer.jsp" %>
</body>
</html>