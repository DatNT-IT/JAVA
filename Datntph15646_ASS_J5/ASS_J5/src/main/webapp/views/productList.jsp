<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product list by CategoryID</title>
    <style>
        table,
        th,
        td {
            border: 1px solid black;
        }
    </style>
</head>
<body>
    <h1>Product list by CategoryID</h1>
    <table>
        <tr>
            <th>nguoi tao</th>
            <th>Product's Name</th>
            <th>Price</th>
            <th>Title</th>
            <th>image</th>
            <th>trang thai</th>
            <th>Actions</th>
          </tr>
          <c:forEach var="product" items="${products}">
              <tr>
                  <td>${product.getAccount()}</td>
                  <td>${product.getName()}</td>
                  <td>${product.getPrice()}</td>
                  <td>${product.getTitle()}</td>
                  <td>${product.getImage()}</td>
                  <td>${product.getUpdelete()}</td>
                  <td>
                    <a href="../../products/changeCategory/${product.getId()}">
                      Update this Product
                    </a>
                </td>
              </tr>
          </c:forEach>
    </table>
    <a href="../../categories">
        Go back to Category Page
    </a>
</body>
</html>