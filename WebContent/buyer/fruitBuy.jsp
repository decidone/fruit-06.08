<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.fruit.vo.*" %>
<%@page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>과일 구매</title>
</head>
<body>
<%
	ArrayList<FruitSeller> list = (ArrayList<FruitSeller>)request.getAttribute("list");
	if(!list.isEmpty()){
%>
<table border = "1">
	<tr><th>SellerID</th><th>ApplePrice</th><th>AppleCount</th></tr>
	<%
	for(int i=0;i<list.size();i++){
		FruitSeller fs = list.get(i);%>
	<tr><td><%=fs.getId() %></td>
		<td><%=fs.getPrice() %></td>
		<td><%=fs.getCount() %></td>
	</tr>
		<%
		}%>
</table>
<form action="fruitBuy.do" method="post">
Seller ID : <input type="text" name="sellerid"><br>
AppleCount : <input type="number" name="buycount"><br>
<input type="submit" value="Buy"><br>
</form>
		<%
	}else{
		out.println("<h3> 판매중인 판매자가 없습니다.</h3>");
	}
		%>

<%@include file="back.jsp" %>
</body>
</html>