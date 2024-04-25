<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 서버쪽 스크립트를 가져올 때 특정한 클래스를 사용하여 편하게 가져오려고 만들어놓은 태그 라이브러리 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 리스트</title>

<!-- 제이쿼리 사용을 위해 -->
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script>
	$(function() {
		//모든 삭제 버튼에 각각의 이벤트핸들러를 적용해야 하므로 class를 사용하기 때문에 .btn_del
		$(".btn_del").click(function() {
			//클릭된 버튼의 데이터 속성을 사용하여 데이터를 가져오기 위해 jQuery의 data() 메소드를 사용
			// $(this) : 현재 이벤트가 발생하는 jQeury의 객체
			const userid = $(this).data("id");
			/*data("id") -> data-id="${list.userid}" 에서 data-id를 의미. 
			ex) data-name="${list.name}"는 data("name")으로 특정한다.*/
			console.log("userid:" + userid);

			$.ajax({
				type : "POST",
				data : "userid=" + userid,
				url : "delMember.do",
				datatype : "text",
				success : function(result) {
					if (result == "ok") {
						alert(userid + "님의 정보가 삭제되었습니다.");
						location.href = "memberList.do"; //삭제 후 새로고침
					} else {
						console.log(result);
						alert("정보 삭제 실패");
					}
				},
				error : function() {
					alert("에러가 발생하였습니다.");
				}
			});

		});
		$(".btn_name").click(function() {
			//클릭된 버튼의 데이터 속성을 사용하여 데이터를 가져오기 위해 jQuery의 data() 메소드를 사용
			// $(this) : 현재 이벤트가 발생하는 jQeury의 객체
			const name = $(this).data("name");
			console.log("name:" + name);
		});
	});
</script>
</head>
<body>
	<%@ include file="topMenu.jsp"%>

		<tr>
			<td><input type="text" name="find" id="find placeholder="회원검색"></td>
			<button type="button" id="btn_find">찾기</button>
		</tr>
	<table>
		<!-- 표의 헤더 -->
		<thead>
			<tr>
				<th>순번</th>
				<th>ID</th>
				<th>패스워드</th>
				<th>이름</th>
				<th>삭제</th>
			</tr>
		</thead>

		<!-- 표의 몸통 -->
		<tbody>
			<c:forEach items="${memberList}" var="list">
				<tr>
					<td><c:out value="${list.rownum}" /></td>
					<td><c:out value="${list.userid}" /></td>
					<td><c:out value="${list.pass}" /></td>
					<td><c:out value="${list.name}" /></td>

					<td>
						<button type="button" class="btn_del" data-id="${list.userid}">삭제</button>
						<button type="button" class="btn_name" data-name="${list.name}">상세보기</button>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</body>
</html>