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


    <!-- Content Header (Page header) -->


    <div class="content-wrapper" style="min-height: 975.633px;">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                ${flat.title}
                <small>${flat.place}, ${flat.district}</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                <li><a href="#">Flats</a></li>
                <li class="active">nr. ogłoszenia: ${flat.idflat}</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="callout callout-info">
                <h4>Adres ---></h4>
                <a href="${flat.url}">${flat.url}</a>
                <h4><STRONG>Cena za pokój: </STRONG>${flat.price / flat.num_rooms}zł.</h4>
            </div>
            <!-- Default box -->
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">Dane mieszkania</h3>

                    <div class="box-tools pull-right">
                        <button type="button" class="btn btn-box-tool" data-widget="collapse" data-toggle="tooltip" title="" data-original-title="Collapse">
                            <i class="fa fa-minus"></i></button>
                        <button type="button" class="btn btn-box-tool" data-widget="remove" data-toggle="tooltip" title="" data-original-title="Remove">
                            <i class="fa fa-times"></i></button>
                    </div>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="col-sm-12 col-md-6">
<c:if test="${!empty flat.price}">
                            <div class="form-group">
                                <p><strong>Cena: </strong>${flat.price}zł.</p>
                            </div>
</c:if>
<c:if test="${!empty flat.num_rooms}">
                            <div class="form-group">
                                <p><strong>Liczba pokoi: </strong>${flat.num_rooms}</p>
                            </div>
</c:if>
<c:if test="${!empty flat.area}">
                            <div class="form-group">
                                <p><strong>Powierzchnia: </strong>${flat.area}m3</p>
                            </div>
</c:if>
<c:if test="${!empty flat.flatDetail.floor}">
                            <div class="form-group">
                                <p><strong>Piętro: </strong>${flat.flatDetail.floor}</p>
                            </div>
</c:if>
<c:if test="${!empty flat.bail}">
                            <div class="form-group">
                                <p><strong>Kaucja: </strong>${flat.bail}zł.</p>
                            </div><c:if test="${!empty flat.extra_rent}">
</c:if>
    <c:if test="${!empty flat.extra_rent}">
                            <div class="form-group">
                                <p><strong>Czynsz: </strong>${flat.extra_rent}zł.</p>
                            </div></c:if>
</c:if>
<c:if test="${!empty flat.type_advertiser}">
                            <div class="form-group">
                                <p><strong>Ogłoszenie od: </strong>
                                    <c:if test="${flat.type_advertiser==0}"> Prywatne </c:if>
                                    <c:if test="${flat.type_advertiser==1}"> Agencja </c:if>
                                </p>
                            </div>
</c:if>
<c:if test="${!empty flat.flatDetail.available_from}">
                            <div class="form-group">
                                <p><strong>Ogłoszenie od: </strong>${flat.flatDetail.available_from}</p>
                            </div>
</c:if>

                        </div>

                        <div class="col-sm-12 col-md-6">

<c:if test="${!empty flat.datecreate}">
                            <div class="form-group">
                                <p><strong>Data utworzenia: </strong>${flat.datecreate}</p>
                            </div>
</c:if>
<c:if test="${!empty flat.place}">
                            <div class="form-group">
                                <p><strong>Miasto: </strong>${flat.place}</p>
                            </div>
</c:if>
<c:if test="${!empty flat.district}">
                            <div class="form-group">
                                <p><strong>Dzielnia: </strong>${flat.district}</p>
                            </div>
</c:if>
<c:if test="${!empty flat.street}">
                            <div class="form-group">
                                <p><strong>Ulica: </strong>${flat.street}</p>
                            </div>
</c:if>
<c:if test="${!empty flat.flatDetail.type_of_building}">
                            <div class="form-group">
                                <p><strong>Typ budynku: </strong>${flat.flatDetail.type_of_building}</p>
                            </div>
</c:if>
<c:if test="${!empty flat.flatDetail.num_floors}">
                            <div class="form-group">
                                <p><strong>Liczba pięter: </strong>${flat.flatDetail.num_floors}</p>
                            </div>
</c:if>
<c:if test="${!empty flat.flatDetail.building_material}">
                            <div class="form-group">
                                <p><strong>Msteriał budynku: </strong>${flat.flatDetail.building_material}</p>
                            </div>
</c:if>
<c:if test="${!empty flat.flatDetail.windows}">
                            <div class="form-group">
                                <p><strong>Rodzaj okien: </strong>${flat.flatDetail.windows}</p>
                            </div>
</c:if>
<c:if test="${!empty flat.flatDetail.warming}">
                            <div class="form-group">
                                <p><strong>Ogrzewanie: </strong>${flat.flatDetail.warming}</p>
                            </div>
</c:if>
<c:if test="${!empty flat.flatDetail.year_construction}">
                            <div class="form-group">
                                <p><strong>Rok zbudowania: </strong>${flat.flatDetail.year_construction}</p>
                            </div>
</c:if>
<c:if test="${!empty flat.flatDetail.trim_level}">
                            <div class="form-group">
                                <p><strong>Status wykończenia: </strong>${flat.flatDetail.trim_level}</p>
                            </div>
</c:if>
                        </div>
                    </div>
                </div>
                <!-- /.box-body -->
            </div>

            <!-- Default box -->
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">Opis</h3>

                    <div class="box-tools pull-right">
                        <button type="button" class="btn btn-box-tool" data-widget="collapse" data-toggle="tooltip" title="" data-original-title="Collapse">
                            <i class="fa fa-minus"></i></button>
                        <button type="button" class="btn btn-box-tool" data-widget="remove" data-toggle="tooltip" title="" data-original-title="Remove">
                            <i class="fa fa-times"></i></button>
                    </div>
                </div>
                <div class="box-body">
                    ${flat.flatDetail.description}
                </div>
                <!-- /.box-body -->
            </div>

            <!-- /.box -->
            </section>
        <!-- /.content -->
    </div>


<!-- /.content-wrapper -->


<!-- Navigation -->
<jsp:include page="module/footer.jsp" />