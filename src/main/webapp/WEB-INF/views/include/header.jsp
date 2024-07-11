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
			<a href="/" id="nav_logo"><img src="/resources/img/whale.png" alt="CODE Whale" width="100px" /><span style="font-weight:100;">CODE </span><span style="margin-left:5px; color:orange;">Whale</span></a>
		</div>
		<ul id="nav_menu">
			<c:choose>
				<c:when test="${sessionScope.sessionId eq null }">
				<li><a href="login">Login</a></li>
				<li><a href="join">Join</a></li>
				</c:when>
				<c:otherwise>
				<li><c:out value="${sessionScope.sessionName}"></c:out> (<c:out value="${sessionScope.sessionId}"></c:out>)님</li>
				<li><a href="logout">Logout</a></li>
				<li><a href="modify">Modify</a></li>
				</c:otherwise>
			</c:choose>
			<li><a href="index">Profile</a></li>
			<li><a href="list">Board</a></li>
			<li><a href="index">Contact</a></li>
		</ul>
	</div>
</body>
</html>