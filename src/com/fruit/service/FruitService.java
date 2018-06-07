package com.fruit.service;

import java.util.ArrayList;
import com.fruit.vo.*;
import com.fruit.dao.FruitDAO;

public class FruitService {
	
	private static FruitService service = new FruitService();
	public FruitDAO dao = FruitDAO.getInstance();
	private FruitService() {}
	
	public static FruitService getInstance() {
		return service;
	}
	
	public String fruitSellerLogin(String id) {
		return dao.fruitSellerLogin(id);
	}
	
	public void fruitSellerRegister(FruitSeller fs) {
		dao.fruitSellerRegister(fs);
	}
	
	public FruitSeller fruitSellerInformation(String id) {
		FruitSeller fs = dao.fruitSellerInformation(id);
		return fs;
	}
	
	public String fruitBuyerLogin(String id) {
		return dao.fruitBuyerLogin(id);
	}
	
	public void fruitBuyerRegister(FruitBuyer fb) {
		dao.fruitBuyerRegister(fb);
	}
	
	public FruitBuyer fruitBuyerInformation(String id) {
		FruitBuyer fb = dao.fruitBuyerInformation(id);
		return fb;
	}
	
	public ArrayList<FruitSeller>SellerList(){
		ArrayList<FruitSeller>list=dao.SellerList();
		return list;
	}
	
	public FruitSeller sellerSearch(String id) {
		FruitSeller fs = dao.sellerSearch(id);
		return fs;
	}
	
	public FruitBuyer buyerSearch(String id) {
		FruitBuyer fb = dao.buyerSearch(id);
		return fb;
	}
	
	public void sellerUpdate(FruitSeller fs) {
		dao.sellerUpdate(fs);
	}
	
	public void buyerUpdate(FruitBuyer fb) {
		dao.buyerUpdate(fb);
	}
	
	public void sellerJoin(FruitSeller fs) {
		dao.sellerJoin(fs);
	}
	public void buyerJoin(FruitBuyer fb) {
		dao.buyerJoin(fb);
	}

}
