package com.estore.test;

import junit.framework.TestCase;
import redis.clients.jedis.Jedis;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.junit.Test;

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
public class RedisTest extends TestCase {
	
	@Test
	public void test1() {
		Jedis jedis = new Jedis("localhost", 6379);
		System.out.println(jedis.ping());
		jedis.set("hello", "redis");
		
		System.out.println(new Date());
		System.out.println(jedis.get("hello"));
		System.out.println(jedis.get("12"));
	
	}
	
	@Test
	 public  void testList() { 
	      //Connecting to Redis server on localhost 
	      Jedis jedis = new Jedis("localhost"); 
	      System.out.println("Connection to server sucessfully"); 

	      //store data in redis list 
//	      jedis.lpush("tutorial-list", "Redis"); 
//	      jedis.lpush("tutorial-list", "Mongodb"); 
//	      jedis.lpush("tutorial-list", "Mysql"); 
//	      jedis.lpush("tutorial-list", "Mysql"); 
//	      jedis.lpush("tutorial-list", "Mysql"); 
//	      jedis.lpush("tutorial-list", "Mysql"); 
	      // Get the stored data and print it 
	      List<String> list = jedis.lrange("tutorial-list", 0 ,10); 

	      for(int i = 0; i<list.size(); i++) { 
	         System.out.println("Stored string in redis:: "+list.get(i)); 
	      } 
	   } 
	
	
	@Test
	  public  void testKeys() { 
	      //Connecting to Redis server on localhost 
	      Jedis jedis = new Jedis("localhost"); 
	      System.out.println("Connection to server sucessfully"); 
	      //store data in redis list 
	      // Get the stored data and print it 
	      Set<String> list = jedis.keys("*"); 
	      
	      for(int i = 0; i < 100; i++ ) {
	    	 // jedis.set("test"+i,new Random().toString());
	    	 // jedis.expire("test"+i, 60);
	    	  //jedis.del("test"+i);
			}

	      for(String key : list) { 
	         System.out.println(jedis.type(key)+"-------"+key); 
	      } 
	      
	      jedis.close();
	   } 
	
	
	@Test
	public  void test6() { 
		//Connecting to Redis server on localhost 
		Jedis jedis = new Jedis("localhost"); 
		System.out.println("Connection to server sucessfully"); 
		
		System.out.println(jedis.get("REDIS_USER_SESSION_KEY:dcc017a9-154a-4bc9-a769-10573ba626bf")); 
	} 
	
	
	
}
