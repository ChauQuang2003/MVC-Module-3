<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: chauq
  Date: 11/30/2024
  Time: 10:51 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
    <link rel="stylesheet" href="<c:url value="/static/css/home.css"/>">
</head>
<body>
<h1>Home page</h1>
<button><a href="http://localhost:8080/products?path=add">Add Product</a></button>
<table border="1">
    <tr>
        <th>#</th>
        <th>ID</th>
        <th>Name</th>
        <th>Price</th>
        <th>Image</th>
        <th colspan="3">Action</th>
    </tr>
    <c:forEach var="item" items="${list}">
        <tr>
            <td>${loop.index + 1}</td>
            <td>${item.id}</td>
            <td>${item.name}</td>
            <td>${item.price}</td>
            <td><img src="${item.image}" alt=""></td>
            <td><a href="http://localhost:8080/products?path=edit&id=${item.id}"><button>Sửa</button></a></td>
            <td><a href="http://localhost:8080/products?path=delete&id=${item.id}"><button>Xóa</button></a></td>
            <td><a href="http://localhost:8080/products?path=delete&detail&id=${item.id}"><button>Chi tiết</button></a></td>
        </tr>

    </c:forEach>

</table>
</body>
</html>
