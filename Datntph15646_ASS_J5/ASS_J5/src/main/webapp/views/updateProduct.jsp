<!DOCTYPE html>
<html lang="en">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>
        .error {
            color: red;
        }
    </style>
</head>
<body>
<p> assign product:
    <strong>${product.getName()}</strong>  category ?
</p>
<form:form method="POST"
           action="/products/updateProduct/${product.getId()}"
           modelAttribute="product">
    name :
    <form:input type="text"
                value="${product.getName()}"
                placeholder="Enter product's name"
                path="name"
    /><br>
    <form:errors path="name" cssClass="error"/> <br>
    price :
    <form:input type="number"
                value="${product.getPrice()}"
                placeholder="Enter product's price"
                path="price"
    /><br/>
    <form:errors path="price" cssClass="error" /> <br>
   title :
    <form:input type="text"
                value="${product.getTitle()}"
                placeholder="Enter title"
                path="title"
    /><br/>
    <form:errors path="title" cssClass="error"/> <br>
    image :
    <form:input type="text"
                value="${product.getImage()}"
                placeholder="Enter image"
                path="image"
    /><br/>
    <form:errors path="image" cssClass="error"/> <br>
    trang thai :
    <form:input type="number"
                value="${product.getUpdelete()}"
                min="0" max="1"
                path="updelete"
    /><br/>
    <form:errors path="updelete" cssClass="error"/> <br>
    category :
    <form:select path="cate">
        <c:forEach var="category" items="${categories}">
            <form:option value="${category.getId()}">
                ${category.getName()}
            </form:option>
        </c:forEach>
    </form:select>
    <input type="submit" value="Update" />
</form:form>
<form:form
        method="POST"
        action="/products/deleteProduct/${product.getId()}"
        onsubmit="return confirm(' delete this Product?') ? true: false"
>
    <input type="submit" value="Delete"/>
</form:form>
</body>
</html>