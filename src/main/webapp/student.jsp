<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Bruce陈
  Date: 2019/12/10
  Time: 16:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <table border="1px solid">
        <c:forEach items="${stus}" var="stu">
            <tr>
                <td>${stu.id}</td>
                <td>${stu.sname}</td>
                <td>${stu.age}</td>
                <td>${stu.gender}</td>
                <td>${stu.address}</td>
                <td>${stu.classId}</td>
            </tr>
        </c:forEach>
    </table>



</body>
</html>
