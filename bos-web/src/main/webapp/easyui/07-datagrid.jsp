<%--
  Created by IntelliJ IDEA.
  User: qiwenming
  Date: 17/12/16
  Time: 下午4:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>datagrid测试</title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"></script>
</head>
<body>


<%--1.1	将静态HTML渲染为datagrid样式--%>
<table class="easyui-datagrid">
    <thead>
    <tr>
        <th data-options="field:'name'">姓名</th>
        <th data-options="field:'sex'">性别</th>
        <th data-options="field:'age'">年龄</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>xiaoming</td>
        <td>男</td>
        <td>20</td>
    </tr>
    <tr>
        <td>wiming</td>
        <td>男</td>
        <td>20</td>
    </tr>
    <tr>
        <td>qiwenming</td>
        <td>男</td>
        <td>20</td>
    </tr>
    </tbody>
</table>

<BR>


<%--2.2发送ajax请求获取json数据创建datagrid--%>

<table class="easyui-datagrid" data-options="url:'${pageContext.request.contextPath}/json/datagridtest.json'">
    <thead>
    <tr>
        <th data-options="field:'name'">姓名</th>
        <th data-options="field:'sex'">性别</th>
        <th data-options="field:'age'">年龄</th>
    </tr>
    </thead>
    <tbody>
</table>

<BR>

<%--使用easyUI提供的API创建datagrid--%>

<table id="mytable" class="easyui-datagrid"/>

<script type="text/javascript">
    $(function(){
        //页面加载完成后，创建数据表格datagrid
        $("#mytable").datagrid({
            //定义标题行所有的列
            columns:[[
                {title:'编号',field:'id',checkbox:true},
                {title:'姓名',field:'name'},
                {title:'性别',field:'sex'},
                {title:'年龄',field:'age'}
            ]],
            //指定数据表格发送ajax请求的地址
            url:'${pageContext.request.contextPath }/json/datagridtest2.json',
            rownumbers:true,
            singleSelect:true,
            //定义工具栏
            toolbar:[
                {text:'添加',iconCls:'icon-add',
                    //为按钮绑定单击事件
                    handler:function(){
                        alert('add...');
                    }
                },
                {text:'删除',iconCls:'icon-remove'},
                {text:'修改',iconCls:'icon-edit'},
                {text:'查询',iconCls:'icon-search'}
            ],
            pagination:true
        });
    });
</script>


</body>
</html>
