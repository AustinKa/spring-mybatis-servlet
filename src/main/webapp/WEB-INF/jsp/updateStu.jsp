<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>

</head>
<body>

<form id="frm" action="/student/updateStu" method="post">
    <input type="hidden" name="id" value="${stu.id}">
    姓名<input type="text" name="sname" id="sname" value="${stu.sname}">
    年龄<input type="text" name="age" id="age" value="${stu.age}">
    性别<input type="text" name="gender"  id="gender" value="${stu.gender}">
    地址<input type="text" name="address" id="address" value="${stu.address}">
    班级<select name="classId">
            <c:forEach items="${classes}" var="c">
                <c:choose>
                    <c:when test="${stu.classId eq c.classId}">
                        <option  value="${c.classId}" selected>${c.classNo}</option>
                    </c:when>
                    <c:otherwise>
                        <option  value="${c.classId}">${c.classNo}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </select>
    <input type="submit" value="修改">


</form>




</body>
