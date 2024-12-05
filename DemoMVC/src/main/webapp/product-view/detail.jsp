<%--
  Created by IntelliJ IDEA.
  User: chauq
  Date: 12/2/2024
  Time: 8:52 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Form Detail</title>
</head>
<body>
<form action="http://localhost:8080/products?path=detail">
<h1>Chi Tiêt Sản Phẩm</h1>
    <p name="id"><strong>ID:</strong> ${product.id}</p>
    <p><strong>Tên sản phẩm:</strong> ${product.name}</p>
    <p><strong>Giá:</strong> ${product.price}</p>
    <p><strong>Mô tả:</strong> ${product.image}</p>
    <a href="/products">Back</a>
</form>
</body>
</html>
