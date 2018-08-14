package com.estore.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.estore.bean.Cart;
import com.estore.bean.Product;
import com.estore.bean.User;
import com.estore.service.CartService;
import com.estore.service.IndexElementService;
import com.estore.service.ProductService;
import com.estore.utils.JsonMsg;

//标注为控制器,  已经配制了自动扫描
@Controller
//@RequestMapping("")
public class CartController {
	@Autowired
	IndexElementService indexElementService;

	@Autowired
	CartService cartService;

	@Autowired
	ProductService productService;




	@RequestMapping(value="/cart",method=RequestMethod.POST)
	@ResponseBody
	public JsonMsg addElement(Cart cart,HttpSession session) throws Exception {

		//校验cart数据
		if(cart.getProductId() == null ||  cart.getNum() == null) {
			return JsonMsg.fail("商品id和购买数量不能为空");
		}
		
		/*写cart*/
		cartService.addCartOfSession(cart,session);

		//spring mvc 会自动将返回结果 json 化
		return JsonMsg.success();
	}



	/**
	 * 
	 * @Title: deleteElement  
	 * @Description: TODO (删除session和数据库中的carts里面的product id 为 参数id的购物车项)  
	 * @param @param id 商品id
	 * @return JsonMsg    返回json消息  
	 * @throws
	 */

	@RequestMapping(value="/cart",method=RequestMethod.DELETE)
	@ResponseBody
	public JsonMsg deleteElement(@RequestParam(value = "id")Integer id, HttpSession session) {

		if(id == null) {
			return JsonMsg.fail("商品id不能为空");
		}
		
		

		cartService.removeCartWithSession(session,id);
		
		return JsonMsg.success();
	}

	@RequestMapping(value="/cart",method=RequestMethod.PUT)
	@ResponseBody
	public JsonMsg updateElement(Cart cart, HttpSession session) {
		
		//校验cart数据
		if(cart == null || cart.getProductId() == null ||  cart.getNum() == null) {
			return JsonMsg.fail("商品id和购买数量不能为空");
		}
		
		
		

		//可优化
		Product product = productService.getProduct(cart.getProductId());
		
		Map<Product,Integer> carts = (Map<Product,Integer>) session.getAttribute("carts"); 

		if(carts == null || carts.get(product) == null) {
			return JsonMsg.fail("服务器没有要修改的数据");
		}

		carts.put(product, cart.getNum()); 

		//更新 database
		User user = (User) session.getAttribute("user");
		if(user != null) {
			cart.setUserId(user.getId());
			cartService.updateCart(cart);
		}
		
		//spring mvc 会自动将返回结果 json 化
		return JsonMsg.success();

	}

	@RequestMapping(value="/cart",method=RequestMethod.GET)
	@ResponseBody
	public JsonMsg getElement(Integer id) {

		//没有需求
		return JsonMsg.fail();
	}

	@RequestMapping(value="/cart/all")
	@ResponseBody
	public JsonMsg getAllElement(HttpSession session) {

		Map<Product,Integer> carts = (Map<Product,Integer>) session.getAttribute("carts"); 
 
		if(carts == null ) {
			return JsonMsg.fail();
		}
		
		//把map装换成list,键值对封装到Cart
		List<Cart> list = new ArrayList<Cart>();
		for(Product product : carts.keySet()) {
			Cart c = new Cart();
			c.setProduct(product);
			c.setNum(carts.get(product));;
			list.add(c);
		}
		return JsonMsg.success().addResult("carts", list);
	}

	@RequestMapping(value="/cart/list")
	@ResponseBody
	public JsonMsg getAllElement(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, @RequestParam(value = "pageSize", defaultValue = "5")Integer pageSize) {

		return JsonMsg.fail();
	}

	




}