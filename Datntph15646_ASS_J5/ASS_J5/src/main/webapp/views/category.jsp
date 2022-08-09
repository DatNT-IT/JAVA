<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">    
    
    <title>Category list</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet"
          integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <style>
        table,
        th,
        td {
            border: 1px solid black;
        }
    </style>
<%--    <link rel="stylesheet" href="<c:url value='../css'/>">--%>
<%--    <script src="<c:url value='../js'/>"></script>--%>
</head>
<body>

<div class="mx-auto p-1 bg-danger text-white text-center" style="width: 200px;"><h1>Category</h1></div>

    <table class="table table-striped">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Actions</th>
          </tr>
          <c:forEach var="category" items="${categories}">
              <tr>
                  <td>${category.getId()}</td>
                  <td>${category.getName()}</td>
                  <td>
                    <a href="products/${category.getId()}">
                      Show Products
                    </a>
                </td>
              </tr>
          </c:forEach>          
    </table>
    <a href="../product/writeExcel"><button type="button" class="btn btn-primary">Write Excel </button></a>
    <a href="../product/readExcel"><button type="button" class="btn btn-primary">Read Excel </button></a>
    <a href="../home">
        <button type="button" class="btn btn-primary">Back to home</button>
    </a>
</body>
</html>
