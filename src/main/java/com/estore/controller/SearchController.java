package com.estore.controller;


import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.estore.bean.ProductSearch;
import com.estore.service.ProductSearchService;
import com.estore.utils.JsonMsg;
import com.estore.utils.JsonUtils;

import junit.framework.Test;

//标注为控制器,  已经配制了自动扫描
@Controller
@RequestMapping(value="/search")
public class SearchController {

	@Autowired
	ProductSearchService searchService;

	/**
	 * 
	* @Title: indexSearch  
	* @Description: TODO 首页综合搜索框搜索服务,  
	* @param @param keyWord 搜索关键字
	* @param @throws Exception    设定文件  
	* @return String    返回类型  返回搜索结果显示页面
	* @throws
	 */
	@RequestMapping(value="/indexSearch")
	public String indexSearch(@PathVariable("keyWord") String keyWord,HttpServletRequest httpServletRequest) throws Exception {
		JsonMsg result = productSearchWithJson(keyWord);		
		httpServletRequest.setAttribute("result", JsonUtils.objectToJson(result));
		return "search";
	}
	
	@RequestMapping(value="/productSearch")
	public String productSearch(String keyWord, HttpServletRequest httpServletRequest) throws Exception {
		
		JsonMsg result = productSearchWithJson(keyWord);
		httpServletRequest.setAttribute("result", JsonUtils.objectToJson(result));
		return "search";
	}
	
	@RequestMapping(value="/productSearchWithJson")
	@ResponseBody
	public JsonMsg productSearchWithJson(String keyWord) throws Exception {
		
		//构建搜索条件
		ProductSearch productSearch = new ProductSearch(keyWord,null,null);
		//调用服务
		JsonMsg result = searchService.productSearch(productSearch);
		//回显关键词
		result.addResult("keyWord", keyWord);
		return result;
	}





}