package com.estore.controller.pageData;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.estore.bean.PageElement;
import com.estore.service.IndexElementService;
import com.estore.utils.JsonMsg;
import com.estore.utils.UploadUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

//标注为控制器,  已经配制了自动扫描
@Controller
@RequestMapping("/pageData/index")
public class IndexDataController {

	@Autowired
	IndexElementService indexElementService;


	
	@RequestMapping("/getDataByCategoryId")
	@ResponseBody
	public JsonMsg getDataByCategoryId(Integer  categoryId) {
		
//		System.out.println(indexElementService  == null);
		if(categoryId  == null) {
			return JsonMsg.fail("没有收到 category Id");
		}
		List<PageElement> pageElementsByCategory = indexElementService.getPageElementsByCategory(categoryId,0);
		//spring mvc 会自动将返回结果 json 化
		return JsonMsg.success().addResult("list", pageElementsByCategory);
	}
	
	@RequestMapping(value="/element",method=RequestMethod.PUT)
	@ResponseBody
	public JsonMsg updateElement(PageElement element ) {

		element.setUpdateTime(new Date());
		
		indexElementService.updatePageElement(element);
		//spring mvc 会自动将返回结果 json 化
		return JsonMsg.success();
	}
	
	@RequestMapping(value="/element",method=RequestMethod.GET)
	@ResponseBody
	public JsonMsg getElement(Integer  id) {

		
		PageElement element = indexElementService.getPageElement(id);
		//spring mvc 会自动将返回结果 json 化
		return JsonMsg.success().addResult("pojo", element);
	}
	
	@RequestMapping(value="/element/all")
	@ResponseBody
	public JsonMsg getAllElement() {

		
		List<PageElement> list = indexElementService.getPageElements();
		//spring mvc 会自动将返回结果 json 化
		return JsonMsg.success().addResult("list", list);
	}
	
	@RequestMapping(value="/element/list")
	@ResponseBody
	public JsonMsg getAllElement(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, @RequestParam(value = "pageSize", defaultValue = "5")Integer pageSize) {

		// 引入PageHelper分页插件
		// 在查询之前只需要调用，传入页码，以及每页的大小
		// PageHelper会在 影响 数据库操作, 在mybatis配制文件中配制了
		PageHelper.startPage(pageNum, pageSize);
		List<PageElement> list = indexElementService.getPageElements();
		// pageInfo包装查询后的结果,封装了详细的分页信息,包括有我们查询出来的数据，传入连续显示的页数
		PageInfo page = new PageInfo(list, 5);

		//spring mvc 会自动将返回结果 json 化
		return JsonMsg.success().addResult("pageInfo", page);
	}
	
	@RequestMapping(value="/element/category")
	@ResponseBody
	public JsonMsg getElementsBycategory(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, @RequestParam(value = "pageSize", defaultValue = "5")Integer pageSize) {

		// 引入PageHelper分页插件
		// 在查询之前只需要调用，传入页码，以及每页的大小
		// PageHelper会在 影响 数据库操作, 在mybatis配制文件中配制了
		PageHelper.startPage(pageNum, pageSize);
		List<PageElement> list = indexElementService.getPageElements();
		// pageInfo包装查询后的结果,封装了详细的分页信息,包括有我们查询出来的数据，传入连续显示的页数
		PageInfo page = new PageInfo(list, 5);

		//spring mvc 会自动将返回结果 json 化
		return JsonMsg.success().addResult("pageInfo", page);
	}
	
	@RequestMapping("/getDataByCategoryIdWithPage")
	@ResponseBody
	public JsonMsg getDataByCategoryIdWithPage(Integer  categoryId,@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, @RequestParam(value = "pageSize", defaultValue = "5")Integer pageSize) {
		
//		System.out.println(indexElementService  == null);
		if(categoryId  == null) {
			return JsonMsg.fail("没有收到 category Id");
		}
		PageHelper.startPage(pageNum, pageSize);
		List<PageElement> pageElementsByCategory = indexElementService.getPageElementsByCategory(categoryId,pageNum);
		// pageInfo包装查询后的结果,封装了详细的分页信息,包括有我们查询出来的数据，传入连续显示的页数
		PageInfo page = new PageInfo(pageElementsByCategory, pageSize);
		
		//spring mvc 会自动将返回结果 json 化
		return JsonMsg.success().addResult("pageInfo", page);
	}
	
	
	




}