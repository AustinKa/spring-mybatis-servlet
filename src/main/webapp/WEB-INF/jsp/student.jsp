<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
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

<form id="frm" action="/student/getAllStus" method="post">
    性别<input type="text" name="gender" value="${gender}" id="gender">
    地址<input type="text" name="address" value="${address}" id="address">
    <input type="hidden" id="pageNum" name="pageNum">
    <input type="submit" value="查询">

</form>
<a href="/student/exportExcel">下载数据</a>


<table border="1px solid">
    <tr>
        <th>id</th>
        <th>name</th>
        <th>age</th>
        <th>gender</th>
        <th>address</th>
        <%--<th colspan="2">operation</th>--%>
    </tr>


    <c:forEach items="${pageInfo.list}" var="stu">
        <tr>
            <td>${stu.id}</td>
            <td>${stu.sname}</td>
            <td>${stu.age}</td>
            <td>${stu.gender}</td>
            <td>${stu.address}</td>
            <shiro:hasPermission name="student:update">
                <td><a href="/student/toUpdateStu?id=${stu.id}">修改</a></td>
            </shiro:hasPermission>
            <shiro:hasPermission name="student:delete">
                <td><a href="/student/delStu?id=${stu.id}">删除</a></td>
            </shiro:hasPermission>


        </tr>

    </c:forEach>


</table>

<div class="pagination-layout">

    <div class="pagination">
        <ul class="pagination">

        </ul>


    </div>
</div>



<script type="text/javascript">

    window.onload=function(){
        var if_fistime=true;
        $(".pagination").jqPaginator({
            totalPages:${pageInfo.pages},
            visiblePages:3,
            currentPage:${pageInfo.pageNum},
            first: '<li class="first"><a href="javascript:void(0);">第一页</a></li>',
            prev: '<li class="prev"><a href="javascript:void(0);">上一页</a></li>',
            next: '<li class="next"><a href="javascript:void(0);">下一页</a></li>',
            last: '<li class="last"><a href="javascript:void(0);">最后一页</a></li>',
            page: '<li class="page"><a href="javascript:void(0);">{{page}}</a></li>',

            onPageChange:function(num){


                /* alert(num); */
                if(if_fistime){
                    if_fistime=false;
                }else{
                    changePage(num);
                }
            }

        })

    }

    function changePage(num){

        $("#pageNum").val(num);
        $("#frm").submit();



//        location.href="/stuPage?pageNum="+num+"&gender="+$("#gender").val()+"&address="+$("#address").val();



    }

</script>


</body>
