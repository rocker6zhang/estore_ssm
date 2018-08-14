<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>user visit list</title>

<% pageContext.setAttribute("APP_PATH", request.getContextPath()); %>




<link href="http://127.0.0.1:8089/css/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="http://127.0.0.1:8089/js/jquery-1.12.4.min.js"></script>
<script src="http://127.0.0.1:8089/css/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="http://127.0.0.1:8089/js/common_utils.js"></script>
<script type="text/javascript" src="http://127.0.0.1:8089/js/store/store_common.js"></script>
<script type="text/javascript" src="http://127.0.0.1:8089/js/store/visitLogList.js"></script>


</head>
<body onload="startPage('${ip}')">



<div class="container">


<!-- 标题 -->
	<div class="row">
	  <div class="col-md-12"><h1>user visit log 列表</h1></div>
	</div>
	<!-- 操作按钮 -->
	<div class="row">
	  <div class="col-md-3"></div>
	  <div class="col-md-3"></div>
	  <div class="col-md-3"></div>
	  <div class="col-md-3">
	  </div>
	</div>
	<!-- 商品列表 -->
	<div class="row">
		<div class="col-md-12">
			<table class="table table-hover">
				<thead>
				<tr>
						<th>date time</th>
						<th>client ip</th>
						<th>visit url</th>
						<th>application</th>
						<th>备注</th>
						<th>操作</th>
				</tr>
				</thead>
				<tbody id="product_list">
				</tbody>
				
			</table>
		</div>
		<div class="row">
			<div class="col-md-4" id="page_info"></div>
			<div class="col-md-2"></div>
			<div class="col-md-6" id="page_nav_area"> </div>
						
		</div>
	</div>
		
</div>

</body>
</html>

