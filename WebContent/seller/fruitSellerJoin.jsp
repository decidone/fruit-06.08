<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>판매자 회원가입</title>
</head>
<body>
${result}<br>
<form action="sellerJoin.do" method="post">
	ID:<input type="text" name="id"><br>
	PW:<input type="password" name="pw"><br>
	<input type="submit" value="join"><br>
</form>
<a href="http://localhost:8080/Fruit/index.jsp">Go Back</a><br>
</body>
</html>