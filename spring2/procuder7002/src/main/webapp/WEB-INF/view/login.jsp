<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2019/11/23
  Time: 16:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登陆</title>
</head>
<body>
    <form action="http://127.0.0.1:7002/denglu" method="post">
      user:<input name="name" type="text"/>
      pass:<input name="pass" type="text"/>
        <input type="submit" value="登陆"/>
    </form>
</body>
</html>
