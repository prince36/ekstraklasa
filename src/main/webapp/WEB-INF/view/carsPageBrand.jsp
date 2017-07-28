<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Ja
  Date: 17.07.2017
  Time: 17:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SB Admin - Bootstrap Admin Template</title>

    <!-- Bootstrap Core CSS -->
    <link href="/resources/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="/resources/css/sb-admin.css" rel="stylesheet">

    <!-- Morris Charts CSS -->
    <link href="/resources/css/plugins/morris.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="/resources/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

<div id="wrapper">

    <!-- Navigation -->
    <jsp:include page="module/navigation.jsp" />

    <div id="page-wrapper">

        <div class="container-fluid">

            <jsp:include page="module/statcars.jsp" />

            <!-- Page Heading -->
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">
                        Ogłoszenia
                        <small>Marka: ${brand} | Liczba ogłoszeń: ${countcarsb} </small>
                    </h1>
                    <ol class="breadcrumb">
                        <li>
                            <i class="fa fa-dashboard"></i>  <a href="${contextPath}/cars/home">Dashboard</a>
                        </li>
                        <li class="active">
                            <i class="fa fa-file"></i> Marka: ${brand}
                        </li>
                    </ol>
                </div>
            </div>
            <!-- /.row -->

            <div class="row">
                <div class="col-lg-12">
                    <h2>Wszystkie ogłoszenia</h2>
                    <div class="table-responsive">
                        <table class="table table-hover table-striped">
                            <thead>
                            <tr>
                                <th>Model</th>
                                <th>Wersja</th>
                                <th>Rok produkcji</th>
                                <th>Przebieg</th>
                                <th>Cena</th>
                                <th>Silnik</th>
                                <th>Paliwo</th>
                            </tr>
                            </thead>
                            <c:forEach items="${cars}" var="car">
                                <tbody>
                                <tr>
                                    <td><a href="${contextPage}/cars/${car.idcars}">${car.model}</a></td>
                                    <td><c:if test="${empty car.model_version}"> - - - </c:if>
                                            ${car.model_version}</td>
                                    <td>${car.year_production}</td>
                                    <td>${car.course} km</td>
                                    <td>${car.price} zł</td>
                                    <td>${car.engine_cap} cm3</td>
                                    <td>${car.fuel}</td>
                                </tr>
                                </tbody>
                            </c:forEach>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <!-- /.container-fluid -->

    </div>
    <!-- /#page-wrapper -->

</div>
<!-- /#wrapper -->

<!-- jQuery -->
<script src="/resources/js/jquery.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="/resources/js/bootstrap.min.js"></script>

</body>

</html>
