<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>支付页面</title>
<link href="http://47.104.191.132:8089/css/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
<link href="http://47.104.191.132:8089/css/store/style.css" rel="stylesheet" type="text/css">
<link href="http://47.104.191.132:8089/css/store/index.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="http://47.104.191.132:8089/js/jquery-1.12.4.min.js"></script>

<script src="http://47.104.191.132:8089/css/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="http://47.104.191.132:8089/js/store/index.js"></script>
<script type="text/javascript" src="http://47.104.191.132:8089/js/common_utils.js"></script>
<script type="text/javascript" src="http://47.104.191.132:8089/js/store/store_common.js"></script>

</head>
<body onload="init()">






<div id="page_modal">
<!-- Modal 用户反馈 -->
<div class="modal fade" id="proposal_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">反馈建议</h4>
      </div>
      
	  
	  <div class="modal-body">
      	<form id="proposal_form">
		  <div class="form-group">
		    <label for="exampleInputEmail1">标题</label>
		    <input type="email" class="form-control" name="subject" placeholder="标题">
		  </div>
		  
		  <div class="form-group">
		    <label for="exampleInputPassword1">内容</label>
		    <textarea type="password" class="form-control" name="content" placeholder="内容" ></textarea>
		  </div>
		  
		  <div class="form-group">
		    <label for="exampleInputEmail1">你的联系方式(选填)</label>
		    <input type="email" class="form-control" name="proposalPerson" value="选填">
		  </div>
		  
		  
		  
		  <div class="form-group">
		    <label for="exampleInputEmail1">
			check code
			<img alt="checkCode" src="" onclick="checkCodeImg_proposal(this)" id="checkCodeImg_proposal_id" width="120px" height="25px"/>
			</label>
		    <input type="text" class="form-control" name="checkCode" placeholder="验证码">
		    
		  </div>
		  
		
		
		<div class="form-group">
		    <label for="inputPassword3" class="col-sm-2 control-label"></label>
		    <div class="col-sm-10">
		      <p id="proposal_msg"></p>
		    </div>
		</div>
		 
		 
		</form>
      </div>
	  
      
      
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" onclick="proposal()">提交</button>
      </div>
	  
    </div>
  </div>
</div>


 
 
 
</div>

<!-- Modal 用户提示 -->
<div class="modal fade" id="warning" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">warning</h4>
      </div>
      
      <div class="modal-body">
        <p>本网站模仿京东搭建,仅供测试。不提供任何形式的服务。谢谢！</p>
        <p>有任何建议或问题,请点击<a class="btn btn-default" onclick="proposal_page()">反馈</a></p>
      </div>
      
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">确定</button>
      </div>
    </div>
  </div>
</div>

<!-- Modal 用户点击回馈 -->
<div class="modal fade" id="none_link" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">warning</h4>
      </div>
      
      <div class="modal-body">
        <p>该功能暂未开放。谢谢！</p>
      </div>
      
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">确定</button>
      </div>
    </div>
  </div>
</div>

<!-- Modal 用户注册 -->
<div class="modal fade" id="register_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">注册</h4>
      </div>
      
      <div class="modal-body">
      	<form id="register_form">
		  <div class="form-group">
		    <label for="exampleInputEmail1">Email address</label>
		    <input type="email" class="form-control" name="email" placeholder="Email">
		  </div>
		  
		  <div class="form-group">
		    <label for="exampleInputEmail1">User name</label>
		    <input type="email" class="form-control" name="username" placeholder="用户名">
		  </div>
		  
		  <div class="form-group">
		    <label for="exampleInputPassword1">Password</label>
		    <input type="password" class="form-control" name="password" placeholder="密码">
		  </div>
		  
		  
		  
		  <div class="form-group">
		    <label for="exampleInputEmail1">
			check code
			<img alt="checkCode" src="" onclick="checkCodeImg()" name="checkCodeImgs" width="120px" height="25px"/>
			</label>
		    <input type="text" class="form-control" name="checkCode" placeholder="验证码">
		    
		  </div>
		  
		 
		    <div class="form-group">
				<label for="inputPassword3" class="col-sm-2 control-label"></label>
				<div class="col-sm-10">
				  <p id="register_msg"></p>
				</div>
		   </div>
		 
		 
		</form>
      </div>
      
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		<button type="button" class="btn btn-primary" onclick="register()">注册</button>
      </div>
    </div>
  </div>
</div>



<!-- Modal 用户登录 -->
<div class="modal fade" id="login_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">登录</h4>
      </div>
      
	  
	  <div class="modal-body">
      	<form id="login_form">
		  <div class="form-group">
		    <label for="exampleInputEmail1">User name</label>
		    <input type="email" class="form-control" name="username" placeholder="用户名">
		  </div>
		  
		  <div class="form-group">
		    <label for="exampleInputPassword1">Password</label>
		    <input type="password" class="form-control" name="password" placeholder="密码">
		  </div>
		  
		  
		  
		  <div class="form-group">
		    <label for="exampleInputEmail1">
			check code
			<img alt="checkCode" src="" onclick="checkCodeImg()" name="checkCodeImgs" width="120px" height="25px"/>
			</label>
		    <input type="text" class="form-control" name="checkCode" placeholder="验证码">
		    
		  </div>
		  
		
		
		<div class="form-group">
		    <label for="inputPassword3" class="col-sm-2 control-label"></label>
		    <div class="col-sm-10">
		      <p id="login_msg"></p>
		    </div>
		</div>
		 
		 
		</form>
      </div>
	  
      
      
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-default" data-dismiss="modal" onclick="register_page()">注册</button>
        <button type="button" class="btn btn-primary" onclick="login_sso()">登录</button>
      </div>
	  
    </div>
  </div>
</div>



<!--header-->
<div class="header">
	<div class="top">
    	<div class="inner">
        	<div class="diqu"><a onclick="none_link()">送至：<span id="user_city">北京</span><i>◇</i></a></div>
            <ul>
            	<li id="login_link"><a onclick="login_page()">你好，请登录<strong class="cheap">免费注册</strong></a></li>
                <li class="line"></li>
                <li><a href="#" id="order_path">我的订单</a></li>
                <li class="line"></li>
                <li class="active_bg" ><a href="#" id="user_home_path">我的Estore<i>◇</i></a></li>
                <li class="line"></li>
                <li><a onclick="none_link()">Estore会员</a></li>
                <li class="line"></li>
                <li><a onclick="none_link()">企业采购</a></li>
                <li class="line"></li>
                <li class="active_bg"><a onclick="none_link()"><span></span>手机Estore<i>◇</i></a></li>
                <li class="line"></li>
                <li class="active_bg"><a onclick="none_link()">关注Estore<i>◇</i></a></li>
                <li class="line"></li>
                <li class="active_bg"><a onclick="none_link()">客户服务<i>◇</i></a></li>
                <li class="line"></li>
                <li class="active_bg"><a onclick="none_link()">网站导航<i>◇</i></a></li>
              </ul>
        </div>
    </div>
    <div class="mid clearfix">
    	<div class="logo"><a href="#" id="index_path"><img src="http://47.104.191.132:8089/image/logo-201305.png"></a></div>
        <div class="search">
        	<form><input type="text" value="" placeholder="家装节4免1" class="txt"><input type="submit" value="搜索" class="sub"></form>
            <div class="tags"><a href="#" class="active">相机节</a><a onclick="none_link()">EstoreE卡</a><a onclick="none_link()">多肉萌宠</a><a onclick="none_link()">耐克5折</a><a onclick="none_link()">每150减50</a><a onclick="none_link()">月饼券</a><a onclick="none_link()">胸针</a><a onclick="none_link()">1元专享</a><a onclick="none_link()">笔记本子</a></div>
        </div>
        <div class="btn" onclick="document.getElementById('cart_path').click();">
        	<a href="#" id="cart_path">我的购物车</a>
            <div class="number" id="page_cart_num">0<i></i></div>
        </div>
    </div>
    <div class="bot clearfix">
            <div class="all">全部商品分类</div>
            <ul>
                <li><a onclick="none_link()">服装城</a></li>
                <li><a onclick="none_link()">美妆馆</a></li>
                <li><a onclick="none_link()">Estore超市</a></li>
                <li><a onclick="none_link()">生鲜</a></li>
                <li><a onclick="none_link()">全球购</a></li>
                <li><a onclick="none_link()">闪购</a></li>
                <li><a onclick="none_link()">团购</a></li>
                <li><a onclick="none_link()">拍卖</a></li>
                <li><a onclick="none_link()">金融</a></li>
            </ul>
       
    </div>
</div>




<!-- =======================================内容========================================== -->
<div class="container-fluid">

	<br/><br/><br/>
	
	<div class="row">
	  <div class="col-md-2"> </div>
	  <div class="col-md-8">
	  	订单号:${order.id} 总价: ${order.price}<br/><hr/>
	  	<strong>支付失败,继续<a href="${home}" style="color:red"> 购物 </a>或查看<a href="${home}/showOrder.html" style="color:red"> 订单 </a></strong>
	  <br/><hr/>
	  
	  <br/><br/><br/>
	  </div>
	  <div class="col-md-2"> </div>
	</div>
</div>











<!-- =======================================内容结束========================================== -->





<!--footer-->
<div class="footer">
	<div class="inner">
		<span class="icon1"></span>
        <span class="icon2"></span> 
        <span class="icon3"></span> 
        <span class="icon4"></span>    	
    </div>
</div>
<!--footer-nav-->
<div class="footer_bot clearfix">
	<dl>
    	<dt>购物指南</dt>
        <dd><a onclick="none_link()">购物流程</a></dd>
        <dd><a onclick="none_link()">会员介绍</a></dd>
        <dd><a onclick="none_link()">生活旅行/团购</a></dd>
        <dd><a onclick="none_link()">常见问题</a></dd>
        <dd><a onclick="none_link()">大家电</a></dd>
        <dd><a onclick="none_link()">联系客服</a></dd>
    </dl>
    <dl>
    	<dt>配送方式</dt>
        <dd><a onclick="none_link()">上门自提</a></dd>
        <dd><a onclick="none_link()">211限时达</a></dd>
        <dd><a onclick="none_link()">配送服务查询</a></dd>
        <dd><a onclick="none_link()">配送费收取标准</a></dd>
        <dd><a onclick="none_link()">海外配送</a></dd>
    </dl>
    <dl>
    	<dt>支付方式</dt>
        <dd><a onclick="none_link()">货到付款</a></dd>
        <dd><a onclick="none_link()">在线支付</a></dd>
        <dd><a onclick="none_link()">分期付款</a></dd>
        <dd><a onclick="none_link()">邮局汇款</a></dd>
        <dd><a onclick="none_link()">公司转账</a></dd>
    </dl>
    <dl>
    	<dt>售后服务</dt>
        <dd><a onclick="none_link()">售后政策</a></dd>
        <dd><a onclick="none_link()">价格保护</a></dd>
        <dd><a onclick="none_link()">退款说明</a></dd>
        <dd><a onclick="none_link()">返修</a>/<a onclick="none_link()">退换货</a></dd>
        <dd><a onclick="none_link()">取消订单</a></dd>
    </dl>
    <dl class="lastdl">
    	<dt>特色服务</dt>
        <dd><a onclick="none_link()">夺宝岛</a></dd>
        <dd><a onclick="none_link()">DIY装机</a></dd>
        <dd><a onclick="none_link()">延保服务</a></dd>
        <dd><a onclick="none_link()">EstoreE卡</a></dd>
        <dd><a onclick="none_link()">Estore通信</a></dd>
        <dd><a onclick="none_link()">EstoreJD+</a></dd>
    </dl>
    <div class="botbox clearfix">
    	<h3>Estore自营覆盖区县</h3>
        <p>Estore已向全国2654个区县提供自营配送服务，支持货到付款、POS机刷卡和售后上门服务。</p>
        <span><a onclick="none_link()">查看详情 ></a></span>
    </div>
    
</div>
<!--copyright-->
<div class="copyright">
	<ul class="copyright_nav">
    	<li><a onclick="none_link()">关于我们</a><span>|</span></li>
        <li><a onclick="none_link()">联系我们</a><span>|</span></li>
        <li><a onclick="none_link()">联系客服</a><span>|</span></li>
        <li><a onclick="none_link()">商家入驻</a><span>|</span></li>
        <li><a onclick="none_link()">营销中心</a><span>|</span></li>
        <li><a onclick="none_link()">手机Estore</a><span>|</span></li>
        <li><a onclick="none_link()">友情链接</a><span>|</span></li>
        <li><a onclick="none_link()">销售联盟</a><span>|</span></li>
        <li><a onclick="none_link()">Estore社区</a><span>|</span></li>
        <li><a onclick="none_link()">风险监测</a><span>|</span></li>
        <li><a onclick="none_link()">Estore公益</a><span>|</span></li>
        <li><a onclick="none_link()">English Site</a><span>|</span></li>
        <li><a onclick="none_link()">Contact Us</a></li>
    </ul>
    
   
   
   
    <div class="list">
    	<a onclick="none_link()"><img src="http://47.104.191.132:8089/image/54b8871eNa9a7067e.png"></a>
        <a onclick="none_link()"><img src="http://47.104.191.132:8089/image/54b8872dNe37a9860.png"></a>
        <a onclick="none_link()"><img src="http://47.104.191.132:8089/image/56a89b8fNfbaade9a.jpg"></a>
        <a onclick="none_link()"><img src="http://47.104.191.132:8089/image/54b8875fNad1e0c4c.png"></a>
        <a onclick="none_link()"><img src="http://47.104.191.132:8089/image/5698dc03N23f2e3b8.jpg"></a>
        <a onclick="none_link()"><img src="http://47.104.191.132:8089/image/5698dc16Nb2ab99df.jpg"></a>
    </div>
</div>

<!--底部导航栏-->
<div class="sidenav">
    <ul class="navtop">
        <li class="icon1"><a href="/estore_ssm/userHome.html">Estore会员</a></li>
        <li class="icon2"><a href="/estore_ssm/showCart.html">购物车</a></li>
        <li class="icon3"><a onclick="none_link()">我的关注</a></li>
        <li class="icon4"><a onclick="none_link()">我的足迹</a></li>
        <li class="icon5"><a onclick="none_link()">我的消息</a></li>
        <li class="icon6"><a onclick="none_link()">咨询JIMI</a></li>
    </ul>
    <ul class="navtop navbot">
        <li class="icon7"><a href="#">顶部</a></li>
        <li class="icon8"><a onclick="proposal_page()">反馈</a></li>
        <li class="ending">有奖调查</li>
    </ul>
</div>




</body>
</html>
