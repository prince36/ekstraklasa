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
<jsp:include page="../module/navi.jsp" />


<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->

    <section class="content-header">
        <h1>

            <small></small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
            <li><a href="#">Tables</a></li>
            <li class="active">Powiadomienia</li>
        </ol>
    </section>

    <section class="content">
        <div class="raw">
            <div class="col-xs-12">
                <div class="box">
                    <div class="box-header">
                        <h3 class="box-title">Lista powiadomień</h3>
                    </div>


                    <!-- Deletion Alert Messages -->
                    <c:if test="${deletion=='unsuccess'}">
                    <div class="alert alert-danger alert-dismissible" role="alert">
                            <strong>Error!</strong> Usuwanie zakończyło się niepowodzeniem.
                        </div>
                    </div>
                    </c:if>

                    <c:if test="${deletion=='success'}">
                    <div>
                        <div class="alert alert-success alert-dismissible" role="alert">
                            <strong>Success!</strong> Usuwanie zakończyło się sukcesem.
                        </div>
                    </div>
                    </c:if>

                    <!-- /.box-header -->
                    <div class="box-body">
                        <div class="dataTables_wrapper form-inline dt-bootstrap"><div class="row"><div class="col-sm-6"></div><div class="col-sm-6"></div></div><div class="row"><div class="col-sm-12"><table class="table table-bordered table-hover dataTable" aria-describedby="example2_info">
                            <thead>
                            <tr>
                                <th>Meil</th>
                                <th class="sorting_asc">Miasto</th>
                                <th>Dzielnica</th>
                                <th>Liczba Pokoi</th>
                                <th>Cena od</th>
                                <th>Cena do</th>
                                <th>Licznik</th>
                                <th>Data</th>
                                <th></th>
                            </tr>
                            </thead>
                            <c:forEach items="${orders}" var="order">
                                <tbody>
                                <tr>
                                    <td>${order.email} </td>
                                    <td>${order.location}</td>
                                    <td><c:if test="${empty order.district}"> - - - </c:if>
                                            ${order.district}</td>
                                    <td>${order.numrooms}</td>
                                    <td>${order.price_from}</td>                                                                         </td>
                                    <td>${order.price_to}</td>
                                    <td>TODO</td>
                                    <td>TODO</td>
                                    <td>
                                        <a href="${contextPath}/order/${order.idorder}/edit" class="btn-sm btn-warning">Edytuj</a>
                                        <a href="${contextPath}/order/${order.idorder}/delete" class="btn-sm btn-danger">Usuń</a>
                                    </td>
                                </tr>
                                </tbody>
                            </c:forEach>
                        </table>

                            <!-- /.box-body Paginacja
                            <div class="row">
                                <div class="col-sm-5"><div class="dataTables_info" id="example2_info" role="status" aria-live="polite">Showing ${page*20+1} to <c:if test="${page*20+20>=allNumFlats}">${allNumFlats}</c:if> <c:if test="${page*20+20<allNumFlats}">${page*20+20}</c:if> of ${allNumFlats} flats</div></div>
                                <div class="col-sm-7"><div class="dataTables_paginate paging_simple_numbers" id="example2_paginate">
                                    <ul class="pagination">
                                        <c:if test="${page>3}"><li class="paginate_button previous" id="example2_previous"><a href="${contextPath}/flats${city}?p=${1}" aria-controls="example2" data-dt-idx="0" tabindex="0">Pierwsza</a></li></c:if>
                                        <c:if test="${page-2>0}"><li class="paginate_button "><a href="${contextPath}/flats${city}?p=${page-2}" aria-controls="example2" data-dt-idx="${page-2}" tabindex="0">${page-2}</a></li></c:if>
                                        <c:if test="${page-1>0}"><li class="paginate_button "><a href="${contextPath}/flats${city}?p=${page-1}" aria-controls="example2" data-dt-idx="${page-1}" tabindex="0">${page-1}</a></li></c:if>
                                        <li class="paginate_button active"><a href="${contextPath}/flats${city}?p=${page}" aria-controls="example2" data-dt-idx="${page}" tabindex="0">${page}</a></li>
                                        <c:if test="${page+1<lastPage}"><li class="paginate_button "><a href="${contextPath}/flats${city}?p=${page+1}" aria-controls="example2" data-dt-idx="${page+1}" tabindex="0">${page+1}</a></li></c:if>
                                        <c:if test="${page+2<lastPage}"><li class="paginate_button "><a href="${contextPath}/flats${city}?p=${page+2}" aria-controls="example2" data-dt-idx="${page+2}" tabindex="0">${page+2}</a></li></c:if>
                                        <c:if test="${page+3<lastPage}"><li class="paginate_button "><a href="${contextPath}/flats${city}?p=${page+3}" aria-controls="example2" data-dt-idx="${page+3}" tabindex="0">${page+3}</a></li></c:if>
                                        <c:if test="${page!=lastPage}"><li class="paginate_button next" id="example2_next"><a href="${contextPath}/flats${city}?p=${lastPage}" aria-controls="example2" data-dt-idx="${page+4}" tabindex="0">Ostatnia</a></li></c:if>
                                    </ul></div></div>
                            </div>
                            -->
                        </div>
                        </div>
                            <!-- /.box -->
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>

</div>
<!-- /.content-wrapper -->


<!-- Navigation -->
<jsp:include page="../module/footer.jsp" />