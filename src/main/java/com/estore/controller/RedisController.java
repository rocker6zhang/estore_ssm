package com.estore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.estore.service.RedisService;
import com.estore.utils.JsonMsg;

//标注为控制器,  已经配制了自动扫描
@Controller
@RequestMapping("/redis")
public class RedisController {

	@Autowired
	RedisService redisService;

/*----------------indexElement----------------*/
	
	@RequestMapping(value="/indexElement/updateAll")
	@ResponseBody
	public JsonMsg updateIndexElement() throws Exception {
		JsonMsg update = redisService.updateIndexElementAll();
		return update;
	}
	
	
	
	
	

/*----------------product----------------*/
	
	@RequestMapping(value="/product/updateByKey")
	@ResponseBody
	public JsonMsg updateProduct(@RequestParam("updateKey") String updateKey) throws Exception {
		JsonMsg update = redisService.updateProductByKey(updateKey);
		return update;
	}
	
	
	@RequestMapping(value="/product/updateAll")
	@ResponseBody
	public JsonMsg updateProductAll() throws Exception {
		JsonMsg update = redisService.updateProductAll();
		return update;
	}
	
	/*------------------productCategory-----------------------*/
	

	@RequestMapping(value="/productCategory/updateByKey")
	@ResponseBody
	public JsonMsg updateProductCategory(@RequestParam("categoryId") String categoryId) throws Exception {
		JsonMsg update = redisService.updateProductCategory(categoryId);
		return update;
	}
	
	@RequestMapping(value="/productCategory/updateAll")
	@ResponseBody
	public JsonMsg updateProductCategoryAll() throws Exception {
		JsonMsg update = redisService.updateProductCategoryAll();
		return update;
	}
	




}