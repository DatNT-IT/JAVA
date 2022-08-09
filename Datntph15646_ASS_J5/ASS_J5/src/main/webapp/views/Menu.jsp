<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page language="java" session="true" contentType="text/html" pageEncoding="UTF-8" %>
<!--begin of menu-->
<nav class="navbar navbar-expand-md navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand" href="../home">Shoes</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault"
                aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse justify-content-end" id="navbarsExampleDefault">
            <ul class="navbar-nav m-auto">

                <c:if test="${sessionScope.acc.admin == 1}">
                    <%--                    có quyền admin--%>
                    <li class="nav-item">
                        <a class="nav-link" href="#"> Account</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="../categories"> Category</a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.acc.sell == 1}">
                    <%--                    có quyền bán--%>
                    <li class="nav-item">
                        <a class="nav-link" href="../editProduct/${sessionScope.acc.id}"> Product</a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.acc != null}">
                    <%--                    có acc hiện user logout--%>

                    <li class="nav-item">
                        <a class="nav-link" href="../logout">Logout</a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.acc == null}">
                    <%--                    ko có user hiện login--%>

                    <li class="nav-item">
                        <a class="nav-link" href="../loginacc">Login</a>
                    </li>
                </c:if>
            </ul>

            <form action="searchtxt" method="post" class="form-inline my-2 my-lg-0">
                <div class="input-group input-group-sm">
                    <%--                    //this ô input--%>
                    <input oninput="searchByName(this)"  name="txt" type="text" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" placeholder="Search..." required>
                    <div class="input-group-append">

                            <i class="fa fa-search"></i>

                    </div>
                </div>
                <a class="btn btn-success btn-sm ml-3" href="loadCart">
                    <i class="fa fa-shopping-cart"></i> Cart
                    <span class="badge badge-light">3</span>
                </a>
            </form>
        </div>
    </div>
</nav>
<section class="jumbotron text-center">
    <div class="container">
        <h1 class="jumbotron-heading">Siêu thị shopping lượng cao</h1>
        <p class="lead text-muted mb-0">Uy tín tạo nên thương hiệu với hơn 100 năm cung cấp các sản phầm nhập từ Việt
            Nam</p>
    </div>
</section>

