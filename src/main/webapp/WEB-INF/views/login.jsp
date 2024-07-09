<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CodeWhale - Login</title>
<link rel="stylesheet" href="/resources/css/main.css">
<script type="text/javascript" src="/resources/js/login.js"></script>
</head>
<body>
	<%@ include file="include/header.jsp" %>
	<div id="location"></div>
	
	<h3>Login Member</h3>
	
	<form action="loginOk" method="post" name="loginForm">
		<div id="join_box">
			<p><span>ID</span><input type="text" name="mid" /></p>
			<p><span>Password</span><input type="password" name="mpw" /></p>
			<p><input type="button" value="LOGIN" onclick="loginCheck()" /></p>
		</div>
	</form> 
	
	<%@ include file="include/footer.jsp" %>
</body>
</html>