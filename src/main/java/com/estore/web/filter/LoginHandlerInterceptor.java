package com.estore.web.filter;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.estore.service.DataService;
/**
 * 
 * @ClassName: LoginHandlerInterceptor 
 * @Description: TODO 拦截需要登录后才可以访问的url,如果没有登录,引导客户端去登陆
 * @author: zw
 * @date: 2018年8月3日 上午10:17:34
 */
public class LoginHandlerInterceptor implements HandlerInterceptor{
	
	@Autowired
	private DataService dataService;
	
	//注入失败,  = "http://127.0.0.1:8080/store_sso/user/authentication"
	//注入失败处理好了, 是应为配制文件的参数配制到spring了, 而spring 和 spring mvc 没有共用.propertie配制文件,  这里贵spring mvc管 so...
	@Value("${SSO_LOGIN_URL}")
	private String SSO_LOGIN_URL;
	
	// = "127.0.0.1"
	@Value("${APP_IP_PORT}")
	private String APP_IP_PORT;
	//要拦截的url
	private String[] URLS= {"/showOrder.html","userHome.html"};
	

	/**
	 * controller执行前调用此方法
	 * 返回true表示继续执行，返回false中止执行
	 * 这里可以加入登录校验、权限拦截等
	 */
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		boolean flag = true;
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		//httpServletRequest.getRequestURI()
		String uri = httpServletRequest.getRequestURI();
//		String contextPath = httpServletRequest.getContextPath();
//		String path = uri.substring(contextPath.length());
//		System.out.println("path:---"+path);
//		System.out.println("uri:---"+uri);
//		System.out.println("contextPath:---"+contextPath);
//		path:---/index/visitLog/list
//		uri:---/estore_ssm/index/visitLog/list
//		contextPath:---/estore_ssm
		//过滤静态资源
		for(String string : URLS) {
			
			if(uri.contains(string)) {
				System.out.println("访问受控资源"+string+", 需要验证登录");
				//访问受控资源, 需要验证登录
				if(!dataService.isLogin()) {
					flag = false;
					//携带的参数?
					Enumeration<String> attributeNames = request.getParameterNames();
					StringBuilder prm = new StringBuilder();
					
					while(attributeNames.hasMoreElements()) {
						String name = attributeNames.nextElement();
						prm.append("&");
						prm.append(name);
						prm.append("=");
						prm.append(request.getParameter(name));
					}
					
					response.sendRedirect(SSO_LOGIN_URL+"?callback="+APP_IP_PORT+uri+"?"+prm.toString());
					break; 
					
				}
			}
		}
		
		
		return flag;
	}
	/**
	 * controller执行后但未返回视图前调用此方法
	 * 这里可在返回用户前对模型数据进行加工处理，比如这里加入公用信息以便页面显示
	 */
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}
	/**
	 * controller执行后且视图返回后调用此方法
	 * 这里可得到执行controller时的异常信息
	 * 这里可记录操作日志，资源清理等
	 */
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
