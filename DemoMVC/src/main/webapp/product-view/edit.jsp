<%--
  Created by IntelliJ IDEA.
  User: chauq
  Date: 12/1/2024
  Time: 1:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Product</title>
</head>
<body>
<h1>Form Edit</h1>
<a href="http://localhost:8080/products?path=home">Back</a>
<form action="http://localhost:8080/products?path=edit">
    <input type="number" name="id" placeholder="Id" value="${product.id}" readonly>
    <br>
    <input type="text" name="name" placeholder="Name" value="${product.name}">
    <br>
    <input type="text" name="price" placeholder="Price" value="${product.price}">
    <br>
    <input type="text" name="image" placeholder="Image" value="${product.image}">
    <br>
    <button>Submit</button>
</form>
</body>
</html>
