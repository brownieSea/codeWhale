<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CodeWhale - Modify Information</title>
<link rel="stylesheet" href="/resources/css/main.css">
<script type="text/javascript" src="/resources/js/join.js"></script>
</head>
<body>
	<%@ include file="include/header.jsp" %>
	<div id="location"></div>
	
	<h3>Modify Infomation</h3>
	
		<div id="join_box">
			<p><span>ID</span><span class="memberInfo">${mDto.mid }</span></p>
			<p><span>Name</span><span class="memberInfo">${mDto.mname }</span></p>
			<p><span>Email</span><span class="memberInfo">${mDto.memail }</span></p>
			<p><input type="button" value="Modify" onclick="javascript:window.location.href='modify'" /> <input type="button" value="Logout" onclick="javascript:window.location.href='logout'"></p>
		</div>
	
	<%@ include file="include/footer.jsp" %>
</body>
</html>