package com.estore.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.estore.bean.JsonMsg;
import com.estore.bean.User;
import com.estore.service.UserService;

//标注为控制器,  已经配制了自动扫描
@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	


	@RequestMapping("/register")
	@ResponseBody
	public JsonMsg addUser(@Valid User u,BindingResult result ,HttpSession httpSession ,@RequestParam(value = "checkCode") String checkCode) {
		
		String string = (String)httpSession.getAttribute("checkCode");
		//校验验证码
		if(checkCode == null || !checkCode.equalsIgnoreCase(string)) {
			return JsonMsg.fail("验证码错误"); 
		}
		
		//校验完成后删除
		httpSession.removeAttribute("checkCode");
		
		if(result.hasErrors()){
			//校验失败，应该返回失败，返回校验失败的错误信息
			Map<String, Object> map = new HashMap<String, Object>();
			List<FieldError> errors = result.getFieldErrors();
			
			String msg = "";
			for (FieldError fieldError : errors) {
//				System.out.println("错误的字段名："+fieldError.getField());
//				System.out.println("错误信息："+fieldError.getDefaultMessage());
				//map.put(fieldError.getField(), fieldError.getDefaultMessage());
				msg+=fieldError.getDefaultMessage();
				msg+=";";
			}
			return JsonMsg.fail(msg);
		}

		String msg = userService.regist(u);
		
		//msg不等于null 表示有异常
		if(msg != null) {
			
			return JsonMsg.fail(msg);
		}
		
		//spring mvc 会自动将返回结果 json 化
		return JsonMsg.success();
	}

	
	@RequestMapping("/login")
	@ResponseBody
	public JsonMsg login(@Valid User u,BindingResult result,HttpSession httpSession,@RequestParam(value = "checkCode") String checkCode) {

		String string = (String)httpSession.getAttribute("checkCode");
		//校验验证码
		if(checkCode == null || !checkCode.equalsIgnoreCase(string)) {
			return JsonMsg.fail("验证码错误");
		}
		
		//校验完成后删除
		httpSession.removeAttribute("checkCode");
		
		//校验数据
		if(result.hasErrors()){
			//校验失败，应该返回失败，返回校验失败的错误信息
			Map<String, Object> map = new HashMap<String, Object>();
			List<FieldError> errors = result.getFieldErrors();
			String msg = "";
			for (FieldError fieldError : errors) {
//				System.out.println("错误的字段名："+fieldError.getField());
//				System.out.println("错误信息："+fieldError.getDefaultMessage());
				//map.put(fieldError.getField(), fieldError.getDefaultMessage());
				msg+=fieldError.getDefaultMessage();
				msg+=";";
			}
			return JsonMsg.fail(msg);
		}

		
		
		User user = userService.loginOfSession(u,httpSession);
		
		//如果user为空表示没查到, 登录失败
		if(user == null) {
			return JsonMsg.fail("登录失败:用户名或密码错误");
		}
		//登录成功
		httpSession.setAttribute("user", user);
		
		return JsonMsg.success().addResult("user", user);
	}


	@RequestMapping("/logout")
	@ResponseBody
	public JsonMsg logout(HttpSession httpSession) {
		
		User user = (User) httpSession.getAttribute("user");
		httpSession.removeAttribute("user");
		user = null;
		return JsonMsg.success();
	
	
	}
	
	@RequestMapping("/checkUserName")
	@ResponseBody
	public JsonMsg checkUserName(@RequestParam(value = "username") String username) {

		if (userService.hasName(username)) {
			return JsonMsg.fail("用户名已存在");
		}
		return JsonMsg.success("用户名可以使用");
	}
	
	@RequestMapping("/checkUserEmail")
	@ResponseBody
	public JsonMsg checkUserEmail(@RequestParam(value = "userEmail") String userEmail) {
		
		if (userService.hasEmail(userEmail)) {
			return JsonMsg.fail("email已存在");
		}
		return JsonMsg.success("email可以使用");
	}
	
	
	/**
	 * 
	* @Title: userActive  
	* @Description: TODO 校验用户名是否可用  
	* @param @param activeCode
	* @param @return    设定文件  
	* @return JsonMsg    jsonMsg.code 等于 100表示true  
	* @throws
	 */
	@RequestMapping("/userActive")
	@ResponseBody
	public JsonMsg userActive(@RequestParam(value = "activeCode") String activeCode) {

		String msg = userService.active(activeCode);
		if(msg != null) {
			return JsonMsg.fail(msg);
		}
		
		return JsonMsg.success("用户名可以使用");
	}
	
	@RequestMapping("/token/{token}")
	@ResponseBody
	public JsonMsg getUserByToken(@PathVariable String token, String callback) {
		
		
		return JsonMsg.fail();
	}
	
	
}
