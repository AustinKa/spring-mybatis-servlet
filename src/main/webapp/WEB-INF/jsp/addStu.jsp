<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/css/bootstrap.css">
    <script src="/js/jquery-3.2.1.js"></script>
    <script src="/js/jqPaginator.js"></script>
    <script src="/js/bootstrap.js"></script>


</head>
<body>

<form id="frm" action="/student/addStu" method="post">
    姓名<input type="text" name="sname" id="sname">
    年龄<input type="text" name="age" id="age">
    性别<input type="text" name="gender"  id="gender">
    地址<input type="text" name="address" id="address">
    班级<select name="classId">
            <c:forEach items="${classes}" var="c">
                <option  value="${c.classId}">${c.classNo}</option>
            </c:forEach>
        </select>
    <input type="hidden" id="pageNum" name="pageNum">
    <input type="submit" value="新增">


</form>




</body>
