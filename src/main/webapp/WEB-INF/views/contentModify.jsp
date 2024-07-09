<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	
	<h3>Board - Modify</h3>
	
	<form action="contentModifyOk">
	<input type="hidden" name="bnum" value="${bDto.bnum }" />
		<div id="write_box">
			<p><span>Writer</span><span class="memberInfo">${bDto.bid }</span></p>
			<p><span>Title</span><input type="text" name="btitle" value="${bDto.btitle }" /></p>
			<p><textarea name="bcontent">${bDto.bcontent }</textarea></p>
			<div style="display:flex; justify-content:space-between;">
				<p style="text-align:left;"><input type="button" value="LIST" onclick="javascript:window.location.href='list'"></p>
				<p style="text-align:right;"><input type="submit" value="OK" /></p>
			</div>
		</div>
	</form>
	
	<%@ include file="include/footer.jsp" %>
</body>
</html>