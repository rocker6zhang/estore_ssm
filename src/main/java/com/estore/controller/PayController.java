package com.estore.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.estore.bean.JsonMsg;
import com.estore.bean.Order;
import com.estore.bean.PageElement;
import com.estore.service.IndexElementService;
import com.estore.service.OrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

//标注为控制器,  已经配制了自动扫描
@Controller
public class PayController {

	@Autowired
	IndexElementService indexElementService;
	@Autowired
	OrderService orderService;


	@RequestMapping(value="/pay/{orderId}")
	public String pay(@PathVariable("orderId") Integer orderId, HttpServletRequest request) throws Exception {

		Order order = orderService.getOrder(orderId);
		request.setAttribute("order", order);
		System.out.println(orderId+"--------------------------order id"); 
		System.out.println(order); 
		//spring mvc 会自动将返回结果 json 化
		return "pay";
	}





}