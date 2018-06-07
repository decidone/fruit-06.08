package com.fruit.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.fruit.vo.*;
import com.fruit.service.FruitService;

public class FruitBuyController implements Controller{
	public void execute(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException{
		HttpSession session = request.getSession();
		String buyerid = (String)session.getAttribute("id");
		String sellerid = request.getParameter("sellerid");
		String buycount = request.getParameter("buycount");
		
		
		if(sellerid.isEmpty()||buycount.isEmpty()) {
			request.setAttribute("result", "모든 항목을 입력해 주시기 바랍니다");
			HttpUtil.forward(request, response, "/buyer/fruitBuyOutput.jsp");
			return;
		}
		
		int wantbuy = Integer.parseInt(buycount);
		
		if(wantbuy<0) {
			request.setAttribute("result", "구매 갯수가 0보다 작을 수는 없습니다.");
			HttpUtil.forward(request, response, "/buyer/fruitBuyOutput.jsp");
			return;
		}
		FruitService service = FruitService.getInstance();
		FruitSeller fs = service.sellerSearch(sellerid);
		if(fs==null) {
			request.setAttribute("result", "판매자 ID를 올바르게 입력해 주시기 바랍니다.");
			HttpUtil.forward(request, response, "/buyer/fruitBuyOutput.jsp");
			return;
		}
		int sellercount = Integer.parseInt(fs.getCount());
		System.out.println("sellercount : "+sellercount);
		int sellerprice = Integer.parseInt(fs.getPrice());
		int sellermoney = Integer.parseInt(fs.getMoney());
		
		FruitBuyer fb = service.buyerSearch(buyerid);
		int buyercount = Integer.parseInt(fb.getCount());
		System.out.println("buyercount : "+buyercount);
		int buyermoney = Integer.parseInt(fb.getMoney());
		
		sellercount -= wantbuy;
		if(sellercount<0) {
			request.setAttribute("result", "재고가 부족합니다");
			HttpUtil.forward(request, response, "/buyer/fruitBuyOutput.jsp");
			return;
		}
		sellermoney += wantbuy*sellerprice;
		
		buyercount +=wantbuy;
		buyermoney -= wantbuy*sellerprice;
		if(buyermoney<0) {
			request.setAttribute("result", "비용이 부족합니다");
			HttpUtil.forward(request, response, "/buyer/fruitBuyOutput.jsp");
			return;
		}
		
		FruitSeller fs2 = new FruitSeller();
		fs2.setCount(String.valueOf(sellercount));
		fs2.setMoney(String.valueOf(sellermoney));
		fs2.setId(sellerid);
		FruitBuyer fb2 = new FruitBuyer();
		fb2.setCount(String.valueOf(buyercount));
		fb2.setMoney(String.valueOf(buyermoney));
		fb2.setId(buyerid);
		
		service.sellerUpdate(fs2);
		service.buyerUpdate(fb2);
		request.setAttribute("result", "Complete");
		HttpUtil.forward(request, response, "/buyer/fruitBuyOutput.jsp");
	}

}
