<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CodeWhale - Board</title>
<link rel="stylesheet" href="/resources/css/main.css">
<script type="text/javascript" src="/resources/js/write.js"></script>
</head>
<body>
	<%@ include file="include/header.jsp" %>
	<div id="location"></div>
	
	<h3>Board - View - ${sid }</h3>
	
		<div id="write_box">
			<div style="width:100%; text-align:right;"><strong>${mDtoView.mid }</strong> | ${bDto.bhit } | <c:out value="${fn:substring(bDto.bdate, 0, 10) }"></c:out></div>
			<div style="width:97%; background-color:#e1e1e1; padding:10px; margin:10px 0;">${bDto.btitle }</div>
			<div style="width:100%; padding:10px 0 20px 0; border-bottom:1px solid #ccc;">${bDto.bcontent }</div>
			
			<div style="width:100%; display:flex; justify-content:space-between;">
				<p style="text-align:left;"><input type="button" value="LIST" onclick="javascript:window.location.href='list'" /></p>
				<p style="text-align:right;">
					<input type="button" value="MODIFY" onclick="javascript:window.location.href='contentModify?bnum=${bDto.bnum}'" />
					<input type="button" value="DELETE" onclick="javascript:window.location.href='contentDelete?bnum=${bDto.bnum}'" />
				</p>
			</div>
		</div>
	
	<%@ include file="include/footer.jsp" %>
</body>
</html>