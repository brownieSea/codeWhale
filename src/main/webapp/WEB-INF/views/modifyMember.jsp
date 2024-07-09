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
	
	<form action="modifyOk" method="post" name="joinForm">
	<input type="hidden" name="mid" value="${mDto.mid }" />
		<div id="join_box">
			<p><span>ID</span><span class="memberInfo">${mDto.mid }</span></p>
			<p><span>Password</span><input type="password" name="mpw" value="${mDto.mpw }" /></p>
			<p><span>Confirm Password</span><input type="password" name="mpwCheck" value="${mDto.mpw }" /></p>
			<p><span>Name</span><input type="text" name="mname" value="${mDto.mname }"/></p>
			<p><span>Email</span><input type="text" name="memail" value="${mDto.memail }" /></p>
			<p><input type="button" value="Ok" onclick="joinCheck()" /> <input type="button" value="Cancel" onclick="javascript:history.go(-1)"></p>
		</div>
	</form> 	
	<%@ include file="include/footer.jsp" %>
</body>
</html>