package com.estore.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.estore.bean.Product;
import com.estore.bean.User;
import com.estore.exception.impl.StoreCommonException;
import com.estore.service.DataService;
import com.estore.service.OrderService;
import com.estore.service.ProductService;


import net.sf.json.JSONObject;

//标注为控制器,  已经配制了自动扫描
@Controller
@RequestMapping("/page")
public class PageController {
	
	/** Logger available to subclasses */
	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	DataService  dataService;
	
	@Autowired
	private ProductService productService;

	@Value("LAN246897")
	String ROOT_PASSWORD;
	
	@Value("ERROR_LOG_PATH")
	String ERROR_LOG_PATH;
	
	@Value("LOG_PATH")
	String LOG_PATH;


	@RequestMapping("/active")

	public String active(@Valid User u,BindingResult result) {
		return "active";
	}



	@RequestMapping("/showMessage")

	public String showMessage(@Valid User u,BindingResult result) {
		return "showMessage";
	}


	@RequestMapping(value="/showProduct/{id}")

	public String product(@PathVariable("id") Integer id, HttpServletRequest request) throws StoreCommonException {
		try {
			Product product = productService.getProduct(id);
			String p = JSONObject.fromObject(product).toString();
			request.setAttribute("product",p);
		}catch(Exception e) {
			throw StoreCommonException.withPage(500, "日志读取失败 : "+e.getMessage(), "error/error");
		}
		
		return "showProduct";
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
		logger.debug("root Password--"+ROOT_PASSWORD);
		logger.info("user visit by password : "+password);
		
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
	
	@RequestMapping(value="/showErrorLog")
	
	public ModelAndView showErrorLog(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws StoreCommonException {
		PrintWriter writer;
		try {
			//放置乱码
			httpServletResponse.setContentType("text/html; charset = UTF-8"); 
			writer = httpServletResponse.getWriter();
			
			File file = new File(ERROR_LOG_PATH);
			
			BufferedReader bufferedReader = new BufferedReader(new FileReader(file),1024*100);
			String buf = null;
			while((buf = bufferedReader.readLine()) != null) {
				writer.println(buf);
			}
			
			bufferedReader.close();
			
			writer.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw StoreCommonException.withPage(500, "日志读取失败 : "+e.getMessage(), "error/error");
		}
		
		
		
		return new ModelAndView();
	}
	
	@RequestMapping(value="/showLog")
	
	public ModelAndView showLog(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestParam(value="logName" ,defaultValue = "error")String logName) throws StoreCommonException {
		PrintWriter writer;
		try {
			writer = httpServletResponse.getWriter();
			
			
			File file = new File(LOG_PATH + logName);
			
			BufferedReader bufferedReader = new BufferedReader(new FileReader(file),1024*100);
			String buf = null;
			while((buf = bufferedReader.readLine()) != null) {
				writer.println(buf);
			}
			
			bufferedReader.close();
			
			writer.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw StoreCommonException.withPage(500, "日志读取失败 : "+e.getMessage(), "error/error");
		}
		
		
		
		return new ModelAndView();
	}

	@RequestMapping(value="/loginCallback")
	
	public String loginCallback(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestParam(value = "callback", defaultValue = "/") String callback) throws Exception {
		
		
		User user = dataService.getUserByToken();
		if(user == null) {
			logger.debug("验证用户登录信息失败 ");
		}
		
		//httpServletResponse.sendRedirect(callback);
		
		
		//return //重定向方式
		return "redirect:"+callback;
	}
	


}
