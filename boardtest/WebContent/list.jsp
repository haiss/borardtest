<!--작성일 : 2016. 12. 27. -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%  request.setCharacterEncoding("utf-8");%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table width="500" cellpadding="0" border="1">
		<tr>
			<td>번호</td>
			<td>이름</td>
			<td>제목</td>
			<td>날짜</td>
			<td>히트</td>
		</tr>
		
		<c:forEach items="${list}" var="dto">
		
		<tr>
			<td>${dto.id}</td>
			<td>${dto.name}</td>
			<td>
				<c:forEach begin="1" end="${dto.indent}">-</c:forEach>
				<a href="content_view.do?id=${dto.id}">${dto.title}</a></td>
			<td>${dto.date}</td>
			<td>${dto.hit}</td>
		</tr>
		
		</c:forEach>
		
		<tr>
			<td colspan="5"> <a href="write_view.do">글작성</a> </td>
		</tr>
	</table>
</body>
</html>