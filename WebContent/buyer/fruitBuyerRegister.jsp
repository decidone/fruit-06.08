<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>금액 등록</title>
</head>
<body>
${result}
<form action="fruitBuyerRegister.do" method="post">
Money : <input type="number" name="money"><br>
<input type="submit" value="register"><br>
</form>
<%@include file="back.jsp" %>
</body>
</html>