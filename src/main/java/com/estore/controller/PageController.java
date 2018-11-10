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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.estore.bean.Product;
import com.estore.bean.User;
import com.estore.exception.impl.StoreCommonException;
import com.estore.service.DataService;
import com.estore.service.LogFileService;
import com.estore.service.OrderService;
import com.estore.service.ProductService;
import com.estore.utils.JsonMsg;

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
	
	@Autowired
	private LogFileService logFileService;

	@Value("LAN246897")
	String ROOT_PASSWORD;
	

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
