package com.estore.test;


import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.internal.runners.TestClass;

import com.estore.service.impl.LogFileServiceImpl;
import com.estore.utils.JsonMsg;
import com.estore.utils.JsonUtils;
import com.estore.utils.OpenAPI;

import redis.clients.jedis.Jedis;
/*
public class MAIN {
	public static void main(String[] args) {
		//new MAIN().testOpenAPI();
//		new MAIN().logFiletest();
		testRedis();
	}
	public void logFiletest() {
		JsonMsg appLogFileList = new LogFileServiceImpl().getAppLogFileList("E:\\upload");
		System.out.println(JsonUtils.objectToJson(appLogFileList));
	}
	public void testOpenAPI() {
	
		System.out.println(new Date());
		
		System.out.println(OpenAPI.getCityWithIP("127.0.0.1"));
		System.out.println(OpenAPI.getCityWithIP("192.168.1.1"));
		System.out.println(OpenAPI.getCityWithIP("116.4.97.132"));
		System.out.println(OpenAPI.getInfoWithIP("116.4.97.132"));
		System.out.println(OpenAPI.getInfoWithIP(""));
	//	内网IP
	//	内网IP
	//	东莞
	//	{"code":0,"data":{"ip":"116.4.97.132","country":"中国","area":"","region":"广东","city":"东莞","county":"XX","isp":"电信","country_id":"CN","area_id":"","region_id":"440000","city_id":"441900","county_id":"xx","isp_id":"100017"}}
	
	}



	 	public static void testRedis() {
			Jedis jedis = new Jedis("localhost", 6379);
			System.out.println(jedis.ping());
			jedis.set("hello", "redis");
			
			System.out.println(new Date());
			System.out.println(jedis.get("hello"));
			System.out.println(jedis.get("12"));
		
		}
	 	
	 
		public void testFile() throws Exception {
			File file  = new File("E:\\upload");
			File[] listFiles = file.listFiles();
			File[] listRoots = file.listRoots();
			System.out.println(Arrays.toString(listFiles));
			System.out.println(Arrays.toString(listRoots));
			
			for(File file2:listFiles) {
				System.out.println("getName-----------"+file2.getName());
				System.out.println("getAbsolutePath---"+file2.getAbsolutePath());
				System.out.println("getPath----------"+file2.getPath());
			}
		}
		

		
}

*/

import java.io.File;
import java.util.Arrays;
import java.util.Date;
import com.estore.service.impl.LogFileServiceImpl;
import com.estore.utils.JsonMsg;
import com.estore.utils.JsonUtils;
import com.estore.utils.OpenAPI;

import redis.clients.jedis.Jedis;

public class MAIN {
	public static void main(String[] args) {
		//new MAIN().testOpenAPI();
//		new MAIN().logFiletest();
		//testRedis();
		//testClass();
		testReplase();
	}
	
	private static void testReplase() {
		String string = "C:\\Users\\Administrator.PC-20150913CHKY\\Desktop\\pdf";
		System.out.println(string.replaceAll("\\\\", "/"));
	}

 	private static void testClass() {
		// TODO Auto-generated method stub
 		List list = new ArrayList();
 		list.add("L1");
		a a1 = new a();
		a a2 = new a();
		a1.setList(list);
		a2.setList(list);
		System.out.println(a1);
		System.out.println(a2);
		a1.setNum(2);
		a1.setStr("2");
		list.add("L2");
		list = null;
		System.out.println(a1);
		System.out.println(a2);
		
		
//		A [s=0 , num=1 , str=1 , LIST=[L1]]
//		A [s=0 , num=1 , str=1 , LIST=[L1]]
//		A [s=0 , num=2 , str=2 , LIST=[L1, L2]]
//		A [s=0 , num=2 , str=2 , LIST=[L1, L2]]

	}

	public static void testRedis() {
		
		
 		System.out.println(new Date());
		Jedis jedis = new Jedis("localhost", 6379);
		System.out.println(jedis.ping());
		jedis.set("hello", "redis");
		
		System.out.println(jedis.get("hello"));
		System.out.println(jedis.get("12"));
	
	}
	 	
	 
		
}

class a{
	static int num;
	
	static String str;
	
	int s;
	List list;
	
	
	

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	{
		num = 1;
		str = "1";
	}

	public static int getNum() {
		return num;
	}

	public static void setNum(int num) {
		a.num = num;
	}

	public static String getStr() {
		return str;
	}

	public static void setStr(String str) {
		a.str = str;
	}

	public int getS() {
		return s;
	}

	public void setS(int s) {
		this.s = s;
	}

	@Override
	public String toString() {
		return "A [s=" + s + " , num=" + num + " , str=" +  str + " , LIST=" +  list + "]";
	}
	
	
	
}