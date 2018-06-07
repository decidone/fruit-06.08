package com.fruit.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.fruit.service.FruitService;

public class SellerLoginController implements Controller{
	public void execute(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException{
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		HttpSession session = request.getSession();
		
		if(id.isEmpty()||pw.isEmpty()) {
			request.setAttribute("result", "모든 항목을 입력해 주시기 바랍니다");
			HttpUtil.forward(request, response, "/seller/fruitSellerLogin.jsp");
			return;
		}
		
		FruitService service = FruitService.getInstance();
		String dbPw = service.fruitSellerLogin(id);
		
		if(dbPw==null) {
			request.setAttribute("result", "아이디 혹은 비밀번호가 일치하지 않습니다.");
			HttpUtil.forward(request,response,"/seller/fruitSellerLogin.jsp");
		}
		
		if(pw.equals(dbPw)) {
			if(session.isNew()||session.getAttribute("id")==null) {
				session.setAttribute("id",id);
				HttpUtil.forward(request,response,"/seller/fruitSellerMenu.jsp");
			}else {
				request.setAttribute("result", "이미 로그인한 상태입니다.");
				HttpUtil.forward(request,response,"/seller/fruitSellerLogin.jsp");
			}
		}else {
			request.setAttribute("result", "아이디 혹은 비밀번호가 일치하지 않습니다.");
			HttpUtil.forward(request,response,"/seller/fruitSellerLogin.jsp");
		}
		
	}

}
