package com.fruit.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.fruit.service.FruitService;
import com.fruit.vo.*;

public class FruitSellerRegisterController implements Controller{
	public void execute(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException{
		
		HttpSession session = request.getSession();
		String count = request.getParameter("count");
		String price = request.getParameter("price");
		String id = (String)session.getAttribute("id");
		
		if(count.isEmpty()||price.isEmpty()) {
			request.setAttribute("result", "모든 항목을 입력해 주시기 바랍니다");
			HttpUtil.forward(request, response, "/seller/fruitSellerRegister.jsp");
			return;
		}
		int c = Integer.parseInt(count);
		int p = Integer.parseInt(price);
		
		FruitSeller fs = new FruitSeller();
		fs.setCount(count);
		fs.setPrice(price);
		fs.setId(id);
		fs.setMoney("0");
		FruitService service = FruitService.getInstance();
		service.fruitSellerRegister(fs);
		
		HttpUtil.forward(request, response, "/seller/fruitSellerRegisterOutput.jsp");
	}
}
