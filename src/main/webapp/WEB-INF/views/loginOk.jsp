<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	<c:if test="${loginFail == 1}">
		<script type="text/javascript">
			alert("아이디 혹은 비밀번호 정보가 일치하지 않습니다. 다시 확인해주세요");
			history.go(-1);
		</script>
	</c:if>
	
	<div id="location"></div>
	
	<h3>Login Member</h3>
	
	<form action="loginOk" method="post" name="loginForm">
		<div id="join_box">
			<p>${mDto.mname }님이 로그인하셨습니다.</p>
			<p><input type="button" value="LOGOUT" onclick="javascript:window.location.href='logout'" /></p>
		</div>
	</form> 
	
	<%@ include file="include/footer.jsp" %>
</body>
</html>