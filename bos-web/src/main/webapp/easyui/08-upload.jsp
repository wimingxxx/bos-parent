<%--
  Created by IntelliJ IDEA.
  User: qiwenming
  Date: 17/12/17
  Time: 上午2:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>一键上传测试</title>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.ocupload-1.1.2.js"></script>
    <script type="text/javascript">
        $(function(){
            //页面加载完成后，调用插件的upload方法，动态修改了HTML页面元素
            $("#myButton").upload({
                action:'xxx.action',
                name:'myFile'
            });
        });
    </script>
</head>
<body>

<iframe name="iframe001" style="display: none"></iframe>

<form action="xxxx.action" method="post" target="iframe001" enctype="multipart/form-data">
    <input type="file" name="myfile">
    <input type="submit">
</form>

<input id="myButton" type="button" value="上传">



</body>
</html>
