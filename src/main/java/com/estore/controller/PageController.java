package com.estore.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.estore.bean.Product;
import com.estore.bean.User;
import com.estore.service.ProductService;


import net.sf.json.JSONObject;

//标注为控制器,  已经配制了自动扫描
@Controller
@RequestMapping("/page")
public class PageController {

	@Autowired
	private ProductService productService;

	@Value("LAN246897")
	String ROOT_PASSWORD;

	@RequestMapping("/home")
	public String getHome(@Valid User u,BindingResult result) {
		return "home";
	}

	@RequestMapping("/active")

	public String active(@Valid User u,BindingResult result) {
		return "active";
	}

	@RequestMapping("/login")

	public String login(String goBack, HttpServletRequest httpServletRequest) {
		httpServletRequest.setAttribute("redirect",goBack);
		return "login";
	}

	@RequestMapping("/productList")

	public String productList(@Valid User u,BindingResult result) {
		return "productList";
	}

	@RequestMapping("/addOrder")

	public String addOrder(@Valid User u,BindingResult result) {
		return "addOrder";
	}

	@RequestMapping("/register")

	public String regist(@Valid User u,BindingResult result) {
		return "regist";
	}

	@RequestMapping("/showCart")

	public String showCart(@Valid User u,BindingResult result) {
		return "showCart";
	}

	@RequestMapping("/showMessage")

	public String showMessage(@Valid User u,BindingResult result) {
		return "showMessage";
	}

	@RequestMapping("/OrderList")

	public String OrderList(@Valid User u,BindingResult result) {
		return "OrderList";
	}
	





	@RequestMapping("/addProduct")

	public String addProduct(@Valid User u,BindingResult result) {
		return "addProduct";
	}

	@RequestMapping(value="/showProduct/{id}")

	public String product(@PathVariable("id") Integer id, HttpServletRequest request) {
		Product product = productService.getProduct(id);
		String p = JSONObject.fromObject(product).toString();
		request.setAttribute("product",p);
		
		//new testBean().printHello("maven test");
		return "showProduct";
	}
	
	@RequestMapping(value="/showProduct2/{id}")
	
	public String product2(@PathVariable("id") Integer id, HttpServletRequest request) {
		request.setAttribute("product", productService.getProduct(id));
		return "item";
	}
	
	@RequestMapping(value="/showProposal")
	
	public String proposal(HttpServletRequest httpServletRequest, @RequestParam("password") String password) {
		if(password != null && password.equals(ROOT_PASSWORD)) {
			return "ProposalList";
		}else {
			httpServletRequest.setAttribute("jumpURL",httpServletRequest.getContextPath()+"/index.html");
			httpServletRequest.setAttribute("title","错误");
			httpServletRequest.setAttribute("message","密码错误");
			return "showMessage";
		}
		
		
	}
	
	
	@RequestMapping(value="/showVisitLog")
	
	public String visitLog(HttpServletRequest httpServletRequest,@RequestParam("password") String password) {
		System.out.println("rootPassword--"+ROOT_PASSWORD);
		System.out.println("password--"+password);
		System.out.println(password.equals(ROOT_PASSWORD));
		
		if(password != null && password.equals(ROOT_PASSWORD)) {
			return "visitLogList";
		}else {
			httpServletRequest.setAttribute("title","错误");
			httpServletRequest.setAttribute("message","密码错误");
			return "showMessageNotJump";
		}
		
		
	}
	
	
	
	@RequestMapping(value="/showUserVisit")
	
	public String showUserVisit(String ip,HttpServletRequest httpServletRequest) {
		if(ip != null) {
			httpServletRequest.setAttribute("ip", ip);
			return "showUserVisit";
		}else {
			httpServletRequest.setAttribute("jumpURL",httpServletRequest.getContextPath()+"/page/showVisitLog");
			httpServletRequest.setAttribute("title","错误");
			httpServletRequest.setAttribute("message","参数 ip 不能为空");
			return "showMessage";
		}
		
		
	}



}
