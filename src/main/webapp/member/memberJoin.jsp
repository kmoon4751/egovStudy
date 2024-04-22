<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>

body {
	font-size: 9pt;
	font-colr: #333333;
	font-family: 맑은 고딕;
}

a {
	text-decoration: none;
}

table {
	width: 600px;
	border-collapse: collapse; /* cell 간격 조정*/
}

th, td {
	border: 1px solid #cccccc;
	padding: 3px;
	line-height: 2;
}

.div_btn {
	width: 600px;
	text-align: center;
	margin-top: 5px;
}

caption {
	font-size: 15px;
	font-weight: bold;
	margin-top: 10px;
	padding-bottom: 5px;
}
</style>


<link rel="stylesheet"
	href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>

<script>
	$(function() {

		//날짜 선택 시 달력 가져오기
		$("#birth").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : 'yy-mm-dd'
		});

		// 클릭 이벤트 생성
		//중복 확인 버튼 클릭시 발생하는 이벤트 생성
		$("#btn_idChk").click(function() {
			var userid = $("#userid").val();
			userid = $.trim(userid);

			//아이디가 공백일 시 팝업창
			if (userid == "") {
				alert("아이디를 입력해주세요");
				$("#userid").focus();
				return false;
			}

			// 제이쿼리 형식으로 송신 : 비동기 전송방식의 기능을 가지고 있는 jquery 함수
			// 중복 유무를 확인하기 위해
			$.ajax({

				//데이터를 전송하기 전 세팅
				type : "POST", // 어떤 타입으로?
				data : "userid=" + userid, // json 전송타입
				url : "idChk.do", // 실제 내가 호출하는 페이지
				datatype : "text", // 리턴 타입

				//데이터 전송 후 반환하는 값을 세팅
				success : function(result) { // 성공했을 때의 처리
					if (result == "ok") { // 서버로부터 ok 응답을 받았을 때
						alert("사용할 수 있는 아이디입니다.");
					} else {
						alert("이미 등록된 아이디입니다.");
					}
				},
				error : function() {
					alert("error가 발생하였습니다.");
				}
			});

		});

		//초기화 버튼 활성
		$("#reset").click(function() {
			$("#frm")[0].reset();
		});

		//저장 버튼 활성
		$("#btn_submit").click(function() {
			//폼 입력값 가져오기
			//jquery를 사용하여 html 요소 중 id가 #userid인 값을 가져온 후 변수에 저장
			var userid = $("#userid").val();
			var pass = $("#pass").val();
			var name = $("#name").val();

			// 앞 뒤 공백 제거 후 변수에 저장
			userid = $.trim(userid);
			pass = $.trim(pass);
			name = $.trim(name);

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

			if (name == "") {
				alert("이름을 입력해주세요");
				$("#name").focus();
				return false;
			}

			//사용자가 실제 입력한 값을 보여주기 위해
			//앞에서 가져온 값을 다시 해당 필드에 설정
			//실제로 제출할 때는 이 값이 사용되지 않음
			$("#userid").val(userid);
			$("#pass").val(pass);
			$("#name").val(name);

			//사용자가 폼에 입력한 데이터 값을 직렬화 하기 위해
			//.serialize();를 사용하여 변수에 저장
			var formData = jQuery("#frm").serialize();
			$.ajax({

				//데이터를 전송하기 전 세팅
				type : "POST", 								// 어떤 방식으로 전송할것인지.
				data : formData, 							// 전송할 데이터
				url : "memberJoinSave.do", 					// 데이터를 전송할 url
				dataType : "text", 							// 서버에서 받을 데이터 타입

				//데이터를 전송하고난 후 세팅
				success : function(result) { 				// 성공했을 때 처리
					if (result == "ok") { 					// 서버로부터 받은 결과가 ok 일 때
						alert("저장 완료하였습니다."); 
						$("#frm")[0].reset(); 				// 폼 초기화
						location("login.do"); 				// 로그인 페이지로 이동
					} else { 
						alert("저장 실패하였습니다");
					}
				},
				error : function() { // 오류 발생 시 처리
					alert("error가 발생하였습니다.");
				}
			});

		});
	});
</script>

<meta charset="UTF-8">
<title>회원 등록</title>
</head>
<body>

	<form name="frm" id="frm">
		<table>
			<caption>회원가입 폼</caption>
			<tr>
				<th><label for="userid">아이디</label></th>
				<td><input type="text" name="userid" id="userid"
					placeholder="아이디">
					<button type="button" id="btn_idChk">중복체크</button></td>
			</tr>

			<tr>
				<th><label for="pass">패스워드</label></th>
				<td><input type="password" name="pass" id="pass"></td>
			</tr>

			<tr>
				<th><label for="name">이름</label>
				<td><input type="text" name="name" id="name" placeholder="이름"></td>
			</tr>

			<tr>
				<th><label for="gender">성별</label></th>
				<td><input type="radio" name="gender" id="gender" value="m">남
					<input type="radio" name="gender" id="gender2" value="f">여</td>
			</tr>

			<tr>
				<th><label for="birth">생년월일</label></th>
				<td><input type="text" name="birth" id="birth" readonly></td>
			</tr>

			<tr>
				<th><label for="tel">연락처</label></th>
				<td><input type="text" name="tel" id="tel"
					placeholder="예)010-0000-0000"></td>
			</tr>

			<tr>
				<th><label for="address">주소</label></th>
				<td><input type="text" name="address" id="address"></td>
			</tr>

		</table>
		<div class="div_btn">
			<button type="button" name="btn_submit" id="btn_submit">저장</button>
			<button type="reset">초기화</button>
		</div>
	</form>

</body>
</html>