<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<jsp:include page="../common/menubar.jsp"/>
	<h1 align="center">회원가입</h1>
	
	<form action="insert.me" method="post" encType="multipart/form-data">
		<table align="center">
			<tr>
				<td>* 아이디</td>
				<td><input type="text" name="userId" id="userId"></td>
				<td><button onclick="return duplicationCheck()">중복확인</button></td>
				<!-- return 없으면 폼 전송해버림 -->
			</tr>
			<tr>
				<td>* 비밀번호</td>
				<td><input type="password" name="userPwd"></td>
			</tr>
			<tr>
				<td>* 비밀번호확인</td>
				<td><input type="password" name="userPwd2"></td>
			</tr>
			<tr>
				<td>* 이름</td>
				<td><input type="text" name="userName"></td>
			</tr>
			<tr>
				<td>이메일</td>
				<td><input type="email" name="email"></td>
			</tr>
			<tr>
				<td>주민등록번호</td>
				<td>
					<input type="text" name="birthDay" size="6" maxlength="6">-
					<input type="text" name="gender" size="1" maxlength="1">******
				</td>
			</tr>
			<tr>
				<td>전화번호</td>
				<td>
					<input type="tel" name="phone">
				</td>
			</tr>
			<tr>
				<td>주소</td>
				<td><input type="text" name="address"></td>
			</tr>
			
			<tr>
				<td>프로필사진</td>
				<td>
					<input type="file" name="photo">
				</td>
			</tr>
		</table>
		
		<div align="center">
			<button type="reset">취소</button>
			<button type="submit">가입</button>
		</div>
		
	</form>

	<script>
		function duplicationCheck(){
			var userId = $("#userId").val();
			console.log(userId);
			
			$.ajax({
				url  : "duplicationCheck.me",
				type : "post",
				data : {userId : userId},
				success : function(data){
					// alert(data);
					// console.log(data);
					console.log(data.userId);
				},
				error : function(status){
					console.log(status);
				}
			});
			
			return false;
		}
	</script>
</body>
</html>

