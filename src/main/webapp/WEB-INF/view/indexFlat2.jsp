<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Ja
  Date: 29.07.2017
  Time: 02:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- Navigation -->
<jsp:include page="module/navi.jsp" />


<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Data Tables
            <small>advanced tables</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
            <li><a href="#">Tables</a></li>
            <li class="active">Data tables</li>
        </ol>
    </section>




    <!-- Main content -->
    <section class="content">
        <div class="row">
            <div class="col-xs-12">

                <div class="box">
                    <div class="box-header">
                        <h3 class="box-title">Hover Data Table</h3>
                    </div>
                    <!-- /.box-header -->
                    <div class="box-body">
                        <table id="example2" class="table table-bordered table-hover">
                            <thead>
                            <tr>
                                <th>Miasto</th>
                                <th>Dzielnica</th>
                                <th>Liczba Pokoi</th>
                                <th>od kogo</th>
                                <th>Cena</th>
                                <th>Czynsz</th>
                                <th>Kaucja</th>
                            </tr>
                            </thead>
                            <c:forEach items="${flats}" var="flat">
                                <tbody>
                                <tr>
                                    <td><a href="${contextPage}/flats/${flat.idflat}">${flat.place}</a></td>
                                    <td><c:if test="${empty flat.district}"> - - - </c:if>
                                            ${flat.district}</td>
                                    <td>${flat.num_rooms}</td>
                                    <td>
                                        <c:if test="${flat.type_advertiser==0}"> Prywatne </c:if>
                                        <c:if test="${flat.type_advertiser==1}"> Agencja </c:if>
                                    </td>
                                    <td>
                                        <c:if test="${empty flat.price}"> - - - </c:if>
                                        <c:if test="${!empty flat.price}"> ${flat.price} zł </c:if>
                                    </td>
                                    <td>
                                        <c:if test="${empty flat.extra_rent}"> - - - </c:if>
                                        <c:if test="${!empty flat.extra_rent}"> ${flat.extra_rent} zł </c:if>
                                    </td>
                                    <td>
                                        <c:if test="${empty flat.bail}"> - - - </c:if>
                                        <c:if test="${!empty flat.bail}"> ${flat.bail} zł </c:if>
                                    </td>
                                </tr>
                                </tbody>
                            </c:forEach>
                            <tfoot>
                            <tr>
                                <th>Miasto</th>
                                <th>Dzielnica</th>
                                <th>Liczba Pokoi</th>
                                <th>od kogo</th>
                                <th>Cena</th>
                                <th>Czynsz</th>
                                <th>Kaucja</th>
                            </tr>
                            </tfoot>
                        </table>
                    </div>
                    <!-- /.box-body -->
                </div>
                <!-- /.box -->

            </div>
            <!-- /.col -->
        </div>
        <!-- /.row -->
    </section>
    <!-- /.content -->
</div>
<!-- /.content-wrapper -->


<!-- Navigation -->
<jsp:include page="module/footer.jsp" />
