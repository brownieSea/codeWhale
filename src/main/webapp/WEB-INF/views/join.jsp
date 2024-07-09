<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<div id="location"></div>
	
	<h3>Join Member</h3>
	
	<form action="joinOk" method="post" name="joinForm">
		<div id="join_box">
			<p><span>ID</span><input type="text" name="mid" /></p>
			<p><span>Password</span><input type="password" name="mpw" /></p>
			<p><span>Confirm Password</span><input type="password" name="mpwCheck" /></p>
			<p><span>Name</span><input type="text" name="mname" /></p>
			<p><span>Email</span><input type="text" name="memail" /></p>
			<p><input type="button" value="JOIN" onclick="joinCheck()" /></p>
		</div>
	</form> 
	
	<%@ include file="include/footer.jsp" %>
</body>
</html>