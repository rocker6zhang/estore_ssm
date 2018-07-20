package com.estore.controller;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.estore.utils.CheckCode;

//标注为控制器,  已经配制了自动扫描
@Controller
@RequestMapping("/checkCodeImg")
public class CheckCodeController {



	@RequestMapping("/simpleCode")
	public void simpleCheckCode(HttpServletResponse response, HttpServletRequest request, HttpSession session) {
		
		
		response.setContentType("image/jpeg");
		response.setHeader("Pragma", "no-cache"); 
		response.setHeader("CacheControl", "no-cache");
		response.setHeader("Expires", "-1");
		
		String code = null;
		OutputStream outputStream = null;
		try {
			outputStream = response.getOutputStream();
			code = CheckCode.getCheckImg(outputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(outputStream != null) {
			
			session.setAttribute("checkCode",code);
			
			try {
				outputStream.flush();
				outputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
}
