<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>header</title>
</head>
<body>
	<div id="nav_top">
		<div>
			<a href="/" id="nav_logo"><img src="/resources/img/whale.png" alt="CODE Whale" width="100px" /><span style="font-weight:100; margin-left:-50px;">CODE </span><span style="margin-left:5px; color:orange;">Whale</span></a>
		</div>
		<ul class="nav_menu" style="margin-right:110px;">
			<li><a href="index">Profile</a></li>
			<li><a href="list">Board</a></li>
			<li><a href="index">Contact</a></li>
		</ul>
		<div>
			<c:choose>
				<c:when test="${sessionScope.sessionId eq null }">
					<ul class="nav_menu">
					<li><a href="login">Login</a></li>
					<li><a href="join">Join</a></li>
					</ul>
				</c:when>
				<c:otherwise>
					<p><c:out value="${sessionScope.sessionName} (${sessionScope.sessionId}"></c:out>)님</p>
					<ul class="nav_menu" style="margin-top:10px;">
					<li><a href="logout">Logout</a></li>
					<li><a href="modify">Modify</a></li>
					</ul>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</body>
</html>