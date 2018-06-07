package com.fruit.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.fruit.service.FruitService;
import com.fruit.vo.FruitSeller;

public class SellerJoinController implements Controller{
	public void execute(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException{
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		if(id.isEmpty()||pw.isEmpty()) {
			request.setAttribute("result", "id와 비밀번호를 입력해 주시기 바랍니다.");
			HttpUtil.forward(request, response, "/seller/fruitSellerJoin.jsp");
			return;
		}
		
		
		
		
		FruitService service = FruitService.getInstance();
		FruitSeller fs = new FruitSeller();
		
		if(service.sellerSearch(id)!=null) {
			request.setAttribute("result", "이미 가입되어있는 아이디입니다.");
			HttpUtil.forward(request, response, "/seller/fruitSellerJoin.jsp");
			return;
		}
		
		fs.setId(id);
		fs.setPw(pw);
		fs.setCount("0");
		fs.setMoney("0");
		fs.setPrice("0");
		service.sellerJoin(fs);
		
		HttpUtil.forward(request, response, "/seller/fruitSellerJoinOutput.jsp");
	}
}
