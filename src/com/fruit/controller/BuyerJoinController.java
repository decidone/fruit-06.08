package com.fruit.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.fruit.service.FruitService;
import com.fruit.vo.FruitBuyer;
import com.fruit.vo.FruitSeller;

public class BuyerJoinController implements Controller{
	public void execute(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException{
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		if(id.isEmpty()||pw.isEmpty()) {
			request.setAttribute("result", "id와 비밀번호를 입력해주시기 바랍니다.");
			HttpUtil.forward(request, response, "/buyer/fruitBuyerJoin.jsp");
			return;
		}
		
		
		FruitService service = FruitService.getInstance();
		FruitBuyer fb = new FruitBuyer();
		
		if(service.buyerSearch(id)!=null) {
			request.setAttribute("result", "이미 가입되어있는 아이디입니다.");
			HttpUtil.forward(request, response, "/buyer/fruitBuyerJoin.jsp");
			return;
		}
		
		fb.setId(id);
		fb.setPw(pw);
		fb.setCount("0");
		fb.setMoney("0");
		service.buyerJoin(fb);
		
		HttpUtil.forward(request, response, "/buyer/fruitBuyerJoinOutput.jsp");
	}
}
