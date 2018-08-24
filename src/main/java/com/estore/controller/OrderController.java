package com.estore.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.estore.bean.Cart;
import com.estore.bean.Order;
import com.estore.bean.User;
import com.estore.service.DataService;
import com.estore.service.OrderService;
import com.estore.utils.JsonMsg;
import com.estore.utils.JsonUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


//标注为控制器,  已经配制了自动扫描
@Controller

public class OrderController {
	@Autowired
	OrderService orderService;
	
	@Autowired
	DataService  dataService;

	@Value("${SSO_LOGIN_URL}")
	private String SSO_LOGIN_URL;
	
	@Value("${APP_IP_PORT}")
	private String APP_IP_PORT;
	
	@Value("${APP_ROOT}")
	private String APP_ROOT;
	

	@RequestMapping(value="/order",method=RequestMethod.POST)
	@ResponseBody
	public JsonMsg addElement(String items, String address,HttpSession session) throws Exception  {

		
		
		
		//检查登录...or权限控制
		User user = null;
		try {
			user = dataService.getUserByToken();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(user == null) {
			
			JsonMsg addResult = JsonMsg.fail(201, "用户未登录");
			addResult.addResult("loginURL", SSO_LOGIN_URL);
			addResult.addResult("callback",  APP_IP_PORT + APP_ROOT+ "/page/loginCallback?callback=" + "http://" + APP_IP_PORT + APP_ROOT+ "/showCart.html");
			
			//把本次请求的参数返回给客户端, 添加到
//			HashMap<String, String> hashMap = new HashMap<String, String>();
//			hashMap.put("items", items);
//			hashMap.put("address", address);
//			
//			addResult.addResult("prm", hashMap);
			return addResult;
		}
		
//		JSONArray jsonArray = JSONArray.fromObject(items);
//		List<Cart> list = JSONArray.toList(jsonArray, Cart.class);
		
		List<Cart> list = JsonUtils.jsonToList(items, Cart.class);   

		//设置订单参数
		Order order = new Order();
		order.setAddress(address);
		order.setUserId(user.getId());
		
		//取得主键
		/*为什么要在这里插入订单? 因为在写订单是要一起写订单项, 
		 *需要订单主键,如果再service层一起插入订单的话,因为在一个事务里面所以无法获得主键*/
		orderService.insertOrder(order);
		//调用业务逻辑
		orderService.addOrder(list,session,order);
		
		
		//返回响应
		return JsonMsg.success().addResult("orderId", order.getId());
	}




	@RequestMapping(value="/order",method=RequestMethod.DELETE)
	@ResponseBody
	public JsonMsg deleteElement(Integer id) {

		System.out.println(id);
		if(id == null) {
			return JsonMsg.fail();
		}
		orderService.removeOrder(id);
		//spring mvc 会自动将返回结果 json 化
		return JsonMsg.success();
	}

//	@RequestMapping(value="/order",method=RequestMethod.PUT)
//	@ResponseBody
//	public JsonMsg updateElement(Order order ) {
//
//		return JsonMsg.success();
//
//	}

	@RequestMapping(value="/order",method=RequestMethod.GET)
	@ResponseBody
	public JsonMsg getElement(Integer  id) {


		Order order = orderService.getOrder(id);
		//spring mvc 会自动将返回结果 json 化
		return JsonMsg.success().addResult("order", order);
	} 

	@RequestMapping(value="/order/all")
	@ResponseBody
	public JsonMsg getAllElement(HttpSession session) {
		User user = (User) session.getAttribute("user");
		if(user == null) {
			return JsonMsg.fail("用户未登录");
		}

		List<Order> list = orderService.getOrderInfo(user);
		System.out.println(list);
		//spring mvc 会自动将返回结果 json 化
		return JsonMsg.success().addResult("list", list);
	}

	@RequestMapping(value="/order/list")  
	@ResponseBody
	public JsonMsg getAllElement(HttpSession session, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, @RequestParam(value = "pageSize", defaultValue = "5")Integer pageSize) {

		User user = (User) session.getAttribute("user");
   
		if(user == null) {    
			return JsonMsg.fail(201,"用户未登录");   
		}
		// 引入PageHelper分页插件
		// 在查询之前只需要调用，传入页码，以及每页的大小
		// PageHelper会在 影响 数据库操作, 在mybatis配制文件中配制了
		PageHelper.startPage(pageNum, pageSize);
		
		List<Order> list = orderService.getOrderInfo(user);
		// pageInfo包装查询后的结果,封装了详细的分页信息,包括有我们查询出来的数据，传入连续显示的页数
		PageInfo<Order> page = new PageInfo<Order>(list, 5);

		//spring mvc 会自动将返回结果 json 化
		return JsonMsg.success().addResult("pageInfo", page);
	}







}