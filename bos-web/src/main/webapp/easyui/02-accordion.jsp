<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>accordion</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>

</head>
<body class="easyui-layout">

	<!-- 使用div元素描述每个区域 -->
	<div title="系统管理" style="height: 100px" region="north">北部区域</div>

	<!-- 制作accordion折叠面板
			fit:true----自适应(填充父容器)
	-->
	<div title="菜单导航" style="width: 200px" region="west">
		<div class="easyui-accordion" data-options="fit:true">
			<!-- 使用子div表示每个面板 -->
			<div title="面板一" iconCls="icon-search">111</div>
			<div title="面板二">222</div>
			<div title="面板三">333</div>
		</div>
	</div>

	<div title="主要部分" region="center">中心区域</div>
	<div title="东部区域" style="width: 100px" region="east">东部区域</div>
	<div title="南部区域" style="height:100px" region="south">南部区域</div>

</body>
</html>