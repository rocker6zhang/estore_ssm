<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache,must-revalidate">
    <title>注册-个人用户</title>
    <link type="text/css" rel="stylesheet" href="http://47.104.191.132:8089/css/store/regist.personal.css"/>
    <link type="text/css" rel="stylesheet" href="http://47.104.191.132:8089/css/store/passport.base.css"/>
    <script type="text/javascript" src="http://47.104.191.132:8089/js/jquery-1.6.4.js"></script>
	<script type="text/javascript" src="http://47.104.191.132:8089/js/store/store_common.js"></script>
</head>
<body onload="set_pram()">
<div class="w" id="logo">
    <div>
    	<a href="http://www.estore.com/estore_ssm/" id="index_path">
    		<img src="http://47.104.191.132:8089/image/logo-201305.png" alt="store商城" width="170" height="60"/>
    	</a> <b></b>
    </div>
</div>

<div class="w" id="regist">
    <div class="mt">
        <ul class="tab">
            <li class="curr">个人用户</li>
        </ul>
        <div class="extra">
        <span>我已经注册，现在就&nbsp;
        	<a href="login" class="flk13">登录</a>
        </span>
        </div>
    </div>
    <div class="mc">
        <form id="personRegForm" method="post" onsubmit="return false;">
            <div class="form" onselectstart="return false;">
                <div class="item" id="select-regName">
                    <span class="label"><b class="ftx04">*</b>用户名：</span>

                    <div class="fl item-ifo">
                        <div class="o-intelligent-regName">
                            <input type="text" id="regName" name="username" class="text" tabindex="1" autoComplete="off"
                                   onpaste="return false;"
                                   value=""
                                   onfocus="if(this.value=='') this.value='';this.style.color='#333'"
                                   onblur="if(this.value=='') {this.value='';this.style.color='#999999'}"/>
                            <i class="i-name"></i>
                            <ul id="intelligent-regName" class="hide"></ul>
                            <label id="regName_succeed" class="blank"></label>
                            <label id="regName_error" class="hide"></label>
                        </div>
                    </div>
                </div>
                <div id="o-password">
                    <div class="item">
                        <span class="label"><b class="ftx04">*</b>请设置密码：</span>

                        <div class="fl item-ifo">
                            <input type="password" id="pwd" name="password" class="text" tabindex="2"
                                   style="ime-mode:disabled;"
                                   onpaste="return  false" autocomplete="off"/>
                            <i class="i-pass"></i>
                            <label id="pwd_succeed" class="blank"></label>
                            <label id="pwd_error"></label>
                            <span class="clr"></span>
                        </div>
                    </div>

                    <div class="item">
                        <span class="label"><b class="ftx04">*</b>请确认密码：</span>

                        <div class="fl item-ifo">
                            <input type="password" id="pwdRepeat" name="pwdRepeat" class="text" tabindex="3"
                                   onpaste="return  false" autocomplete="off"/>
                            <i class="i-pass"></i>
                            <label id="pwdRepeat_succeed" class="blank"></label>
                            <label id="pwdRepeat_error"></label>
                        </div>
                    </div>
					<div class="item" id="dphone">
						<span class="label"><b class="ftx04">*</b>验证邮箱：</span>

						<div class="fl item-ifo">
							<input type="text" id="regist_email" maxlength="50" name="email"
								class="text" tabindex="4"
								autocomplete="off" /> <i class="i-phone"></i> <label
								id="phone_succeed" class="blank"></label> <label
								id="phone_error"></label>
						</div>
					</div>
					 
					 <div class="item">
                        <span class="label"><b class="ftx04">*验证码</b>
						</span>
						 <div class="fl item-ifo"><img alt="checkCode" src="" onclick="checkCodeImg()" name="checkCodeImgs" width="120px" height="25px"/>
						</div>
					</div>
					
					 <div class="item">
                        <span class="label"><b class="ftx04">*</b>验证码：</span>

                        <div class="fl item-ifo">
                            <input type="text" id="regist_checkCode" name="checkCode" class="text" tabindex="3"
                                   onpaste="return  false" autocomplete="off"/>
                          <!--   <i class="i-pass"></i>
                            <label id="pwdRepeat_succeed" class="blank"></label>
                            <label id="pwdRepeat_error"></label> -->
                        </div>
                    </div>
					
					</div>
                <div class="item item-new">
                    <span class="label">&nbsp;</span>

                    <div class="fl item-ifo">
                        <input type="checkbox" class="checkbox" checked="checked" id="readme"
                               onclick="agreeonProtocol();">
                        <label for="protocol">我已阅读并同意<a href="#" class="blue" id="protocol">《淘淘用户注册协议》</a></label>
                        <span class="clr"></span>
                        <label id="protocol_error" class="error hide">请接受服务条款</label>
                    </div>
                </div>
                <div class="item">
                    <span class="label">&nbsp;</span>
                    <input type="button" class="btn-img btn-regist" id="registsubmit" value="立即注册" tabindex="8"
                           clstag="regist|keycount|personalreg|07"
                           onclick="REGISTER.reg();"/>
                </div>
            </div>
            <div class="phone">
                <img width="180" height="180" src="http://47.104.191.132:8089/image/313124_300.jpg">
            </div>
            <span class="clr"></span>
        </form>
    </div>
<script type="text/javascript">
	var REGISTER={
		param:{
			//单点登录系统的url
			surl:""+APP_PATH
		},
		inputcheck:function(){
				//不能为空检查
				if ($("#regName").val() == "") {
					alert("用户名不能为空");
					$("#regName").focus();
					return false;
				}
				if ($("#pwd").val() == "") {
					alert("密码不能为空");
					$("#pwd").focus();
					return false;
				}
				if ($("#regist_email").val() == "") {
					alert("email不能为空");
					$("#phone").focus();
					return false;
				}
				if ($("#regist_checkCode").val() == "") {
					alert("checkCode不能为空");
					$("#phone").focus();
					return false;
				}
				//密码检查
				if ($("#pwd").val() != $("#pwdRepeat").val()) {
					alert("确认密码和密码不一致，请重新输入！");
					$("#pwdRepeat").select();
					$("#pwdRepeat").focus();
					return false;
				}
				return true;
		},
		beforeSubmit:function() {
				//检查用户是否已经被占用
				$.ajax({
					type: "POST",//方法类型
					dataType: "json",//预期服务器返回的数据类型
	            	url : APP_PATH + "/user/checkUserName",
	            	data:"username="+$("#regName").val(),
	            	success : function(data) {
	            		if (data.code == 100) {
	            			//检查手机号是否存在
	            			$.ajax({
	            				url : APP_PATH + "/user/checkUserEmail?userEmail="+$("#regist_email").val(),
				            	success : function(data) {
				            		if (data.code == 100) {
					            		REGISTER.doSubmit();
				            		} else {
				            			alert("此邮箱号已经被注册！");
				            			$("#regist_email").select();
				            		}
				            	}
	            			});
	            		} else {
	            			alert("此用户名已经被占用，请选择其他用户名");
	            			$("#regName").select();
	            		}	
	            	}
				});
	            	
		},
		doSubmit:function() {
			$.post(APP_PATH + "/user/register",$("#personRegForm").serialize(), function(data){
				if(data.code == 100){
					alert('用户注册成功，请登录！');
					REGISTER.login();
				} else {
					alert("注册失败！原因是"+data.msg);
				}
			});
		},
		login:function() {
			 location.href = APP_PATH + "/page/login";
			 return false;
		},
		reg:function() {
			if (this.inputcheck()) {
				this.beforeSubmit();
			}
		}
	};
</script>
</body>
</html>
