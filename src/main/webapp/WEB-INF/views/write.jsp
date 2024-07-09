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
	
	<h3>Board - Write</h3>
	
	<form action="writeOk" name="writeForm">
	<input type="hidden" name="bid" value="${mDto.mid }" />
	<input type="hidden" name="bname" value="${mDto.mname }" />
		<div id="write_box">
			<p><span>Writer</span><span class="memberInfo">${mDto.mid }</span></p>
			<p><span>Title</span><input type="text" name="btitle" /></p>
			<p><textarea name="bcontent"></textarea></p>
			<p style="text-align:right;"><input type="button" value="Ok" onclick="writeCheck()" wrap="on" /></p>
		</div>
	</form> 
	
	<%@ include file="include/footer.jsp" %>
</body>
</html>