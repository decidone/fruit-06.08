package com.fruit.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class FrontController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	String charset = null;
	HashMap<String, Controller>list=null;
	
	@Override
	public void init(ServletConfig sc) throws ServletException{
		charset = sc.getInitParameter("charset");
		list = new HashMap<String, Controller>();
		
		list.put("/seller/sellerJoin.do", new SellerJoinController());
		list.put("/seller/sellerLogin.do", new SellerLoginController());
		list.put("/seller/fruitSellerRegister.do", new FruitSellerRegisterController());
		list.put("/seller/fruitSellerInformation.do", new FruitSellerInformationController());
		
		list.put("/buyer/buyerJoin.do", new BuyerJoinController());
		list.put("/buyer/buyerLogin.do", new BuyerLoginController());
		list.put("/buyer/fruitBuyerRegister.do", new FruitBuyerRegisterController());
		list.put("/buyer/fruitBuyList.do", new FruitBuyListController());
		list.put("/buyer/fruitBuy.do", new FruitBuyController());
		list.put("/buyer/fruitBuyerInformation.do", new FruitBuyerInformationController());
		
	}
	
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException{
		request.setCharacterEncoding(charset);
		String url=request.getRequestURI();
		String contextPath = request.getContextPath();
		String path = url.substring(contextPath.length());
		Controller subController = list.get(path);
		subController.execute(request,response);
	}

}
