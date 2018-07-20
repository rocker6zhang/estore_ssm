/**
 * 商品列表页面 js
 */

//项目路径

//验证码图片节点
var checkImgs;
//轮播图div id
var big_pic_id;
pic_page_num = (pic_page_num + 1) % pic_page_total;
var pic_page_num = 0;
var news_page_num = 0;
var top_page_num = 0;
var like_page_num = 0;
var pic_page_size= 4;
var news_page_size = 5;
var top_page_size = 4;
var like_page_size = 6;
var pic_page_total;
var news_page_total;
var top_page_total;
var like_page_total;





/**
 * 初始化js环境
 * */
function init(){
	//初始化全局参数
	set_pram();
	//初始化页面链接
	set_link();
	//初始化页面用户信息
	build_user_info()
	
	
	//轮播图div id
	big_pic_id = "myCarousel";

	
//	请求后台参数,构建页面
	getData();
	
	/*初始化完成,启动固定程序*/
	 
    
//	初始化完成后启动
	//启动warning模态框
	$('#warning').modal();
	
}
//将图片集合中的图片元素填充到 bootstrap 轮播图中
var build_pic =  function (result){
	
	var imgs;
	if(result.code == 100){
		imgs = result.extend.pageInfo.list;
		//初始化参数
		//总页数
		pic_page_total = result.extend.pageInfo.pages;
		//console.log(imgs);
	}else{
		return;
	}
	
	//初始化
	//轮播图容器
	var carousel = document.getElementById("myCarousel_ol");
	var img_div = document.getElementById("myCarousel_img_div");
	for(var i = 0; i < imgs.length; i++){
		
		//导航条node
		var li = document.createElement("li");
		li.setAttribute("data-target","#myCarousel");
		li.setAttribute("data-slide-to",""+i);
		if(i == 0){
			li.className="active";
		}
		
		//图片node
		//图片容器
		var div = document.createElement("div");
		if(i == 0){
			div.className="item active";
		}else{
			div.className="item";
		}
		var  a = document.createElement("a");
		a.setAttribute("href",APP_PATH + imgs[i].url);
		
		var img = document.createElement("img"); 
		img.setAttribute("src",STATIC_PATH+imgs[i].picPath);
		img.setAttribute("alt",""+i);
		img.setAttribute("width","730px");
		img.setAttribute("height","454px");
		
		//组合元素
		a.appendChild(img);
		div.appendChild(a);
		
		carousel.appendChild(document.createTextNode(' '));
		carousel.appendChild(li);
		img_div.appendChild(div);
	}
	
	
	//初始化完成
	
	//启动轮播图
    $("#myCarousel").carousel({  
    	//每张图片停留时间
        interval :5000,  
    });  
}

var build_news = function (result){
	
	var list;
	if(result.code == 100){
		list = result.extend.pageInfo.list;
		//初始化参数
		//总页数
		news_page_total = result.extend.pageInfo.pages;
		//console.log(list);
	}else{
		return;
	}
	
	//初始化
	//news 容器
	var news = document.getElementById("store_news");
	var str = "";
	for(var i = 0; i < list.length && i < 5; i++){
	    str += "<li><a href='" + APP_PATH + list[i].url + "'><span>[特惠]</span>"+list[i].title+"</a></li>";
	}
	
	news.innerHTML = str;
	
	
	//初始化完成

}

var build_top = function (result){
	
	var list;
	if(result.code == 100){
		list = result.extend.pageInfo.list;
		//初始化参数
		//总页数
		top_page_total = result.extend.pageInfo.pages;
//		console.log(list);
	}else{
		return;
	}
	
	//初始化
	//news 容器
	var elem = document.getElementById("top_product");


	var prefix = "<li class='left'><i><</i></li>";
	var Suffix = "<li class='right'><i>></i></li>";
	var str = "";
	for(var i = 0; i < list.length && i < 4; i++){
	    str +=  "<li><a href=" + APP_PATH + list[i].url+"><img src=" + STATIC_PATH + list[i].picPath + "  width='250px' height='164px'></a></li>";
	}
	
	console.log(str);
	elem.innerHTML = prefix + str + Suffix;
	
	
	//初始化完成

}

var build_like = function (result){
	
	var list;
	if(result.code == 100){
		list = result.extend.pageInfo.list;
		//初始化参数
		//总页数
		like_page_total = result.extend.pageInfo.pages;
//		console.log(list);
	}else{
		return;
	}
	
	//初始化
	//news 容器
	var elem = document.getElementById("like");
	var str = "";
	for(var i = 0; i < list.length && i < 6; i++){
	    str += "<li><a href='" + APP_PATH + list[i].url+"' class='pic'><img src='"+STATIC_PATH + list[i].picPath +"' width='130px' height='130px'></a><h5><a href='"+list[i].url+"'>"+list[i].title+"</a></h5><p><span>¥</span>48.00</p></li><li class='line'></li>";         
	}
	
	
	elem.innerHTML = str.substring(0,str.lastIndexOf("<li class='line'></li>"));
	
	
	//初始化完成

}


//初始化,请求服务器参数
function getData(){
	//轮播图片
	//请求轮播图参数
	getIndexElement(1,build_pic,"",pic_page_size);
	
	//estore news
	getIndexElement(2,build_news,"",news_page_size);
	
	//推荐
	getIndexElement(4,build_top,"",top_page_size);
	
	//猜你喜欢
	getIndexElement(3,build_like,"",like_page_size);
	

	
	
}

function replace(id){
	switch(id)
	{
	case 1:
		pic_page_num = (pic_page_num + 1) % pic_page_total;
		
		getIndexElement(1,build_pic,pic_page_num + 1,pic_page_size);
		break;
	case 2:
		news_page_num = (pic_page_num + 1) % news_page_total;
		
		getIndexElement(2,build_news,news_page_num+1,news_page_size);
		break;
	case 3:
		like_page_num = (like_page_num + 1) % like_page_total;
		
		getIndexElement(3,build_like,like_page_num+1,like_page_size);
		break;
	case 4:
		top_page_num = (top_page_num + 1) % top_page_total;
		
		getIndexElement(4,build_top,top_page_num+1,top_page_size);
		break;
	default:
		return;
	}
}

/**
 * 请求页面元素资源
 * @param category_id 分类id
 * @param fun 回调函数
 * @returns
 */
function getIndexElement(category_id,fun,page_num,pageSize){
	$.ajax({
	     //几个参数需要注意一下
	         type: "POST",//方法类型
	         dataType: "json",//预期服务器返回的数据类型
	         url: APP_PATH+"/pageData/index/getDataByCategoryIdWithPage" ,//url
	         data: "categoryId="+category_id+"&pageNum="+page_num+"&pageSize="+pageSize,
	         success: fun,
	         error : function() {
	        	 console.log("getIndexElement 服务器请求 失败");
	         }
	     });
	
}









