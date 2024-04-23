<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 하세요</title>

<!-- 제이쿼리 사용을 위해 -->
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>

<script>
	//저장 버튼 활성
	$(function() {
		$("#btn_submit").click(function() {
			//폼 입력값 가져오기
			//jquery를 사용하여 html 요소 중 id가 #userid인 값을 가져온 후 변수에 저장
			var userid = $("#userid").val();
			var pass = $("#pass").val();

			// 앞 뒤 공백 제거 후 변수에 저장
			userid = $.trim(userid);
			pass = $.trim(pass);

			//입력값이 공백인지 유효성 검사
			if (userid == "") {
				alert("아이디를 입력해주세요");
				$("#userid").focus();
				return false;
			}

			if (pass == "") {
				alert("패스워드를 입력해주세요");
				$("#pass").focus();
				return false;
			}

			//사용자가 실제 입력한 값을 보여주기 위해
			//앞에서 가져온 값을 다시 해당 필드에 설정
			//실제로 제출할 때는 이 값이 사용되지 않음
			$("#userid").val(userid);
			$("#pass").val(pass);

			$.ajax({

				//데이터를 전송하기 전 세팅
				type : "POST", // 어떤 방식으로 전송할것인지.
				data : "userid=" + userid + "&pass=" + pass, // 전송할 데이터
				url : "loginProc.do", // 데이터를 전송할 url
				dataType : "text", // 서버에서 받을 데이터 타입

				//데이터를 전송하고난 후 세팅
				success : function(result) { // 성공했을 때 처리
					if (result == "ok") { // 서버로부터 받은 결과가 ok 일 때
						alert(userid + "님 로그인되셨습니다.");
						$("#frm")[0].reset(); // 폼 초기화
						location.href = "main.do"; // 로그인 후메인 페이지로 이동
					} else if (result == "x") {
						alert(userid + "해당 아이디는 없는 아이디입니다.")
					} else {
						alert("아이디 또는 패스워드를 확인해주세요.");
					}
				},
				error : function() { // 오류 발생 시 처리
					alert("error가 발생하였습니다.");
				}
			});

		});

	});
</script>
</head>
<body>
	<%@ include file="topMenu.jsp"%>

	<form name="frm" id="frm">
		<table>
			<caption>로그인</caption>
			<tr>
				<th><label for="userid">아이디</label></th>
				<td><input type="text" name="userid" id="userid"
					placeholder="아이디"></td>
			</tr>

			<tr>
				<th><label for="pass">패스워드</label></th>
				<td><input type="password" name="pass" id="pass"></td>
			</tr>
		</table>
		<div class="div_btn">
			<button type="button" name="btn_submit" id="btn_submit">로그인</button>
			<button type="reset">취소</button>
		</div>
	</form>
</body>
</html>