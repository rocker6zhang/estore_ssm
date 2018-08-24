package com.estore.test;

import junit.framework.TestCase;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.estore.bean.Order;
import com.estore.dao.CartMapper;
import com.estore.dao.OrderMapper;
import com.estore.dao.ProductMapper;
import com.estore.dao.UserMapper;
import com.estore.service.UserService;
import com.estore.service.impl.UserServiceimpl;
import com.estore.utils.OpenAPI;


/**
 * 
 * @ClassName: MapperTest 
 * @Description: TODO 测试mapper 接口
 * @author: zw
 * @date: 2018年4月11日 上午11:19:12
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class UitlsTest extends TestCase {
	//com.estore.dao.ProductMapper.java
	//com.estore.dao.ProductMapper
//	@Autowired
//	ProductMapper productMapper;
//	@Autowired
//	CartMapper cartMapper;
//	@Autowired
//	UserMapper userMapper;
//	@Autowired
//	OrderMapper orderMapper;
//	
//	
//	@Autowired
//	SqlSession sqlSession;
	
	@Test
	public void testOpenAPI() {
		System.out.println(new Date());
		
		System.out.println(OpenAPI.getCityWithIP("47.104.191.132"));
		System.out.println(OpenAPI.getCityWithIP("192.168.1.1"));
		System.out.println(OpenAPI.getCityWithIP("116.4.97.132"));
		System.out.println(OpenAPI.getInfoWithIP("116.4.97.132"));
	}
	
	@Test
	public void testInstanceof() {
		System.out.println(new Date());
		
		UserServiceimpl userServiceimpl = new UserServiceimpl();
		new Exception();
		System.out.println(userServiceimpl instanceof UserServiceimpl);
		System.out.println(userServiceimpl instanceof UserService);
		System.out.println(userServiceimpl instanceof Object);
		System.out.println(userServiceimpl instanceof CartMapper);
	}
}
