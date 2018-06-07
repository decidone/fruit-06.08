<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>LogoutProcess</title>
</head>
<body>
<%
	session = request.getSession(false);
	if(session !=null&&session.getAttribute("id")!=null){
		session.invalidate();
		System.out.println("로그아웃 성공");
		RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
		rd.forward(request,response);
	}else{
		System.out.println("로그아웃 실패");
		RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
		rd.forward(request,response);
	}
%>
</body>
</html>