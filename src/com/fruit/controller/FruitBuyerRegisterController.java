package com.fruit.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.fruit.service.FruitService;
import com.fruit.vo.*;

public class FruitBuyerRegisterController implements Controller{
	public void execute(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException{
		
		HttpSession session = request.getSession();
		String money = request.getParameter("money");
		String id = (String)session.getAttribute("id");
		
		if(money.isEmpty()) {
			request.setAttribute("result", "금액을 입력해주시기 바랍니다.");
			HttpUtil.forward(request, response, "/buyer/fruitBuyerRegister.jsp");
			return;
		}
		
		FruitBuyer fb = new FruitBuyer();
		fb.setId(id);
		fb.setMoney(money);
		FruitService service = FruitService.getInstance();
		service.fruitBuyerRegister(fb);
		
		HttpUtil.forward(request, response, "/buyer/fruitBuyerRegisterOutput.jsp");
	}
}
