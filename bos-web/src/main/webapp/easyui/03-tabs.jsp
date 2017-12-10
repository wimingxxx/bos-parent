<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>tabs</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>

</head>
<body class="easyui-layout">
<%--	<!-- 使用div元素描述每个区域 -->
	<div title="XXX管理系统" style="height: 100px" data-options="region:'north'">北部区域</div>
	<div title="系统菜单" style="width: 200px" data-options="region:'west'">
		<!-- 制作accordion折叠面板
			fit:true----自适应(填充父容器)
		-->
		<div class="easyui-accordion" data-options="fit:true">
			<!-- 使用子div表示每个面板 -->
			<div data-options="iconCls:'icon-cut'" title="面板一">1111</div>
			<div title="面板二">2222</div>
			<div title="面板三">3333</div>
		</div>
	</div>
	<div data-options="region:'center'">
		<!-- 制作一个tabs选项卡面板 -->
		<div class="easyui-tabs" data-options="fit:true">
			<!-- 使用子div表示每个面板 -->
			<div data-options="iconCls:'icon-cut'" title="面板一">1111</div>
			<div data-options="closable:true" title="面板二">2222</div>
			<div title="面板三">3333</div>
		</div>
	</div>
	<div style="width: 100px" data-options="region:'east'">东部区域</div>
	<div style="height: 50px" data-options="region:'south'">南部区域</div>--%>


	<!-- 使用div元素描述每个区域 -->
	<div title="系统管理" style="height: 100px" region="north">北部区域</div>

	<!-- 制作accordion折叠面板
			fit:true----自适应(填充父容器)
	-->
	<div title="菜单导航" style="width: 200px" region="west">
		<div class="easyui-accordion" data-options="fit:true">
			<!-- 使用子div表示每个面板 -->
			<div title="面板一" iconCls="icon-search">
				<a id="but1" class="easyui-linkbutton">打开系统管理面板</a>
				<script type="text/javascript">
					$(function(){
						//页面加载完成,为but1添加上点击事件
						$("#but1").click(function(){
							var name = "系统管理";
							//判断系统管理是否存在
							var e = $("#mytabs").tabs("exists",name);
							if(e){
								//存在,选中
								$("#mytabs").tabs("select",name);
							}else{
								//不存在,添加
								$("#mytabs").tabs("add",{
									title:name,
									iconCls:'ion-edit',
									closable:true,
									content:'<iframe frameborder="0" height="100%" width="100%" src="https://www.baidu.com"></iframe>'
								});
							}
						});
					});
				</script>
			</div>
			<div title="面板二">222</div>
			<div title="面板三">333</div>
		</div>
	</div>

    <!-- 制作一个tabs选项卡面板 -->
	<div title="主要部分" region="center">
		<div id="mytabs" class="easyui-tabs" data-options="fit:true">
			<!-- 使用子div表示每个面板 -->
			<div title="面板一" iconCls="icon-search">111</div>
			<div title="面板二">222</div>
			<div title="面板三">333</div>
		</div>
	</div>

	<div title="东部区域" style="width: 100px" region="east">东部区域</div>
	<div title="南部区域" style="height:100px" region="south">南部区域</div>

</body>
</html>