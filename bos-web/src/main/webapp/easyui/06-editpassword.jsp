<%--
  Created by IntelliJ IDEA.
  User: qiwenming
  Date: 17/12/13
  Time: 下午11:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改密码界面测试</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript">
        $(function(){
            $("#btnCancel").click(function(){
                $('#editPwdWindow').window('close');
            });

            //为确定按钮绑定事件
            $("#btnEp").click(function(){
                //进行表单校验
                var v = $("#editPasswordForm").form("validate");
                if(v){
                    //表单校验通过，手动校验两次输入是否一致
                    var v1 = $("#txtNewPass").val();
                    var v2 = $("#txtRePass").val();
                    if(v1 == v2){
                        //两次输入一致，发送ajax请求
                        $.post("userAction_editPassword.action",{"password":v1},function(data){
                            if(data == '1'){
                                //修改成功，关闭修改密码窗口
                                $("#editPwdWindow").window("close");
                                $.messager.alert("提示信息","密码修成功！","info");
                            }else{
                                //修改密码失败，弹出提示
                                $.messager.alert("提示信息","密码修改失败！","error");
                            }
                        });
                    }else{
                        //两次输入不一致，弹出错误提示
                        $.messager.alert("提示信息","两次密码输入不一致！","warning");
                    }
                }
            });
        });
        function editPassword(){
            $('#editPwdWindow').window('open');
        }
    </script>
</head>
<body>

<a href="javascript:void(0);" data-options="menu:'#layout_north_kzmbMenu',iconCls:'icon-help'" class="easyui-menubutton">控制面板</a>

<div id="layout_north_kzmbMenu" style="width: 100px; display: none;">
    <div onclick="editPassword();">修改密码</div>
    <div >联系管理员</div>
    <div class="menu-sep"></div>
    <div >退出系统</div>
</div>

<div id="editPwdWindow" class="easyui-window" title="修改密码" collapsible="false" minimizable="false" modal="true" closed="true" resizable="false"
     maximizable="false" icon="icon-save"  style="width: 300px; height: 160px; padding: 5px;
        background: #fafafa">
    <div class="easyui-layout" fit="true">
        <div region="center" border="false" style="padding: 10px; background: #fff; border: 1px solid #ccc;">
            <form id="editPasswordForm">
                <table cellpadding=3>
                    <tr>
                        <td>新密码：</td>
                        <td><input  required="true" data-options="validType:'length[4,6]'" id="txtNewPass" type="Password" class="txt01 easyui-validatebox" /></td>
                    </tr>
                    <tr>
                        <td>确认密码：</td>
                        <td><input required="true" data-options="validType:'length[4,6]'" id="txtRePass" type="Password" class="txt01 easyui-validatebox" /></td>
                    </tr>
                </table>
            </form>
        </div>
        <div region="south" border="false" style="text-align: right; height: 30px; line-height: 30px;">
            <a id="btnEp" class="easyui-linkbutton" icon="icon-ok" href="javascript:void(0)" >确定</a>
            <a id="btnCancel" class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0)">取消</a>
        </div>
    </div>

</div>


</body>
</html>
