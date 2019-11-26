<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2019/11/23
  Time: 20:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    验证成功！
    <shiro:hasRole name="admin">
        <p>有admin权限</p>
    </shiro:hasRole>
    <shiro:hasRole name="foot">
        <p>有foot权限</p>
    </shiro:hasRole>
</body>
</html>
