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


/**
 * 
 * @ClassName: MapperTest 
 * @Description: TODO 测试mapper 接口
 * @author: zw
 * @date: 2018年4月11日 上午11:19:12
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class MapperTest extends TestCase {
	//com.estore.dao.ProductMapper.java
	//com.estore.dao.ProductMapper
	@Autowired
	ProductMapper productMapper;
	@Autowired
	CartMapper cartMapper;
	@Autowired
	UserMapper userMapper;
	@Autowired
	OrderMapper orderMapper;
	
	
	@Autowired
	SqlSession sqlSession;
	
	@Test
	public void testOrder() {
		System.out.println(new Date());
		List<Order> selectAllInfoByUserId = orderMapper.selectAllInfoByUserId(2);
		System.out.println(selectAllInfoByUserId);
	}
	
	@Test
	public void test2() {
		Order order = new Order();
		order.setCreatedate(new Date());
		//写订单, 返回订单id保存到order对象中
		orderMapper.insert(order);
		System.out.println(order.getId()+"----------");
	}
	
	@Test
	public void test() {
//		System.out.println(productMapper);
//		System.out.println(sqlSession);
//		System.out.println(cartMapper);
		
//		System.out.println(productMapper.selectByPrimaryKey(25));
//		System.out.println(userMapper.selectByPrimaryKey(15));
//		
		//配制在spring 中的批量add session
		ProductMapper mapper = sqlSession.getMapper(ProductMapper.class);
//		Product(String name, Double price, String category, Integer pnum, String description)
		for(int i = 0;i<1000;i++){
			String uid = UUID.randomUUID().toString().substring(0,5)+i;
			//mapper.insertSelective(new Product(uid,i*0.5,null,i,"好看的商品"+i));
		}
		
	}
}
