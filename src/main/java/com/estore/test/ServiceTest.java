package com.estore.test;

import junit.framework.TestCase;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.estore.bean.User;
import com.estore.dao.CartMapper;
import com.estore.dao.ProductMapper;
import com.estore.dao.UserMapper;
import com.estore.service.VisitLogService;


/**
 * 
 * @ClassName: MapperTest 
 * @Description: TODO 测试mapper 接口
 * @author: zw
 * @date: 2018年4月11日 上午11:19:12
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class ServiceTest extends TestCase {
	//com.estore.dao.ProductMapper.java
	//com.estore.dao.ProductMapper
	@Autowired
	VisitLogService visitLogService;
	
	
	
	@Test
	public void test() {
		System.out.println(visitLogService.getVisitLog(588).getVisitTime());
	}
}
