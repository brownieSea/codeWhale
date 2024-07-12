<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CodeWhale - Board</title>
<link rel="stylesheet" href="/resources/css/main.css">
<script type="text/javascript" src="/resources/js/join.js"></script>
</head>
<body>
	<%@ include file="include/header.jsp" %>
	<div id="location"></div>
	
	<h3>Board</h3>
	
	<p style="text-align:center; margin-bottom:10px">"${searchKey }"로 검색된 게시글입니다.</p>
	
	<div class="col2" style="padding:8px 0;">
		<div> Total : ${pageDto.total } &nbsp;&nbsp;|&nbsp;&nbsp; ${currPage } / ${pageDto.realEndPage } pages</span></div>
		<div style="text-align:right; flex-grow:1; ">
			<form action="list2">
				<input type="text" name="searchKey" /> <input type="submit" value="Search" style="margin:0;" />
			</form>
		</div>	
	</div>
	<table>
	<tr>
		<th>No</th>
		<th width="420px">Title</th>
		<th>Writer</th>
		<th>Date</th>
		<th>Hit</th>
	</tr>
	
	<c:forEach items="${bDtos }" var="bDto">
	<tr style="cursor:pointer;" onclick="location.href='viewContent?bnum=${bDto.bnum}'">
		<td>${bDto.bnum }</td>
		<td style="text-align:left;">
		<c:choose>
			<c:when test="${fn:length(bDto.btitle) > 43}">
				<c:out value="${fn:substring(bDto.btitle, 0, 42)}"></c:out>...
			</c:when>
			<c:otherwise>
				${bDto.btitle }
			</c:otherwise>
		</c:choose>
		</td>
		<td>${bDto.bid }</td>
		<td>
			<c:out value="${fn:substring(bDto.bdate, 0, 10) }"></c:out>
		</td>
		<td>${bDto.bhit }</td>
	</tr>
	</c:forEach>
	</table>	

	<p style="width:650px; margin:0 auto; text-align:right;"><input type="button" value="WRITE" onclick="javascript:window.location.href='write'" /></p>
	
	<div class="pageNav">
		<c:if test="${pageDto.prev }">
			<a href="list2?pageNum=1"><span class="bttn1"><< </span></a>
			<a href="list2?pageNum=${pageDto.startPage-10 }&searchKey=${searchKey}"><span class="bttn1">◀ prev</span></a>  
		</c:if> 
		<c:forEach begin="${pageDto.startPage }" end="${pageDto.endPage }" var="pageNumber">
		<!-- 페이지 시작번호와 마지막 번호를 가져온다 -->
			<c:choose>
				<c:when test="${currPage == pageNumber}">
					<span style="border-radius:0.3em; background-color:#45818E; color:#fff; padding:2px 7px;; font-weight:600;">${pageNumber }</span>
				</c:when>
				<c:otherwise>
					<a href="list2?pageNum=${pageNumber }&searchKey=${searchKey}">${pageNumber }</a>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<c:if test="${pageDto.next }">
			<a href="list2?pageNum=${pageDto.startPage+10 }&searchKey=${searchKey}"><span class="bttn1">▶ next</span></a>
			<a href="list2?pageNum=${pageDto.realEndPage }&searchKey=${searchKey}"><span class="bttn1">>></span></a>
		</c:if>
	</div>
	
	<%@ include file="include/footer.jsp" %>
</body>
</html>