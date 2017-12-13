<%--
  Created by IntelliJ IDEA.
  User: qiwenming
  Date: 17/12/11
  Time: 下午11:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>messager</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"></script>
</head>
<body>

<a id="but1" class="easyui-linkbutton">alert测试</a>
<script type="text/javascript">
    $(function(){
        $("#but1").click(function(){
            $.messager.alert('测试','这个这是测试而已','error',function(){
                alert("点击了 确定");
            });
        });
    });
</script>

<br>

<a id="but2" class="easyui-linkbutton">confirm测试</a>
<script type="text/javascript">
    $(function(){
        $("#but2").click(function(){
            $.messager.confirm("测试","您确定是测试吗?",function(r){
                if(r){
                    alert("点击了 确定");
                }else{
                    alert("点击了 取消");
                }
            });
        });
    });
</script>

<br>
<a id="but3" class="easyui-linkbutton">show测试</a>
<script type="text/javascript">
    $(function(){
        $("#but3").click(function(){
            $.messager.show({
                title:'这是标题',
                msg:'这个消息在2秒后关闭',
                timeout:2000,
                showType:'slide'
            });
        });
    });
</script>

<br>

<a data-options="iconCls:'icon-help',menu:'#mm'" class="easyui-menubutton">控制面板</a>
<div id="mm">
    <div onclick="alert('修改密码')" data-options="iconCls:'icon-edit'">修改密码</div>
    <div class="menu-sep"></div>
    <div>联系管理员</div>
    <div class="menu-sep"></div>
    <div>退出系统</div>
</div>

</body>
</html>
