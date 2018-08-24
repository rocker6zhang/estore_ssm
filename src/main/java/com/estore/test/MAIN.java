package com.estore.test;

import java.util.Date;

import com.estore.utils.OpenAPI;

public class MAIN {
public static void main(String[] args) {
	new MAIN().testOpenAPI();
}
public void testOpenAPI() {
	System.out.println(new Date());
	
	System.out.println(OpenAPI.getCityWithIP("47.104.191.132"));
	System.out.println(OpenAPI.getCityWithIP("192.168.1.1"));
	System.out.println(OpenAPI.getCityWithIP("116.4.97.132"));
	System.out.println(OpenAPI.getInfoWithIP("116.4.97.132"));
	System.out.println(OpenAPI.getInfoWithIP(""));
//	内网IP
//	内网IP
//	东莞
//	{"code":0,"data":{"ip":"116.4.97.132","country":"中国","area":"","region":"广东","city":"东莞","county":"XX","isp":"电信","country_id":"CN","area_id":"","region_id":"440000","city_id":"441900","county_id":"xx","isp_id":"100017"}}

}
}
