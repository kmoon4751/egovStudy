<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 서버쪽 스크립트를 가져올 때 특정한 클래스를 사용하여 편하게 가져오려고 만들어놓은 태그 라이브러리 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 리스트</title>
</head>
<body>
	<%@ include file="topMenu.jsp"%>

	<table>
		<!-- 표의 헤더 -->
		<thead>
			<tr>
				<th>순번</th>
				<th>id</th>
				<th>패스워드</th>
				<th>이름</th>
			</tr>
		</thead>

		<!-- 표의 몸통 -->
		<tbody>
			<c:forEach items="${memberList}" var="list">
				<tr>
					<td><c:out value="${list.rownum}"/></td>
					<td><c:out value="${list.userid}"/></td>
					<td><c:out value="${list.pass}"/></td>
					<td><c:out value="${list.name}"/></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</body>
</html>