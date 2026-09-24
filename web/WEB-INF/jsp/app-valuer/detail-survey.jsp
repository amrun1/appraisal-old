<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<c:set var="isBv" value="${fn:containsIgnoreCase(formBean.appraisalTypeName, 'bv')}"/>
<c:set var="isRv" value="${fn:containsIgnoreCase(formBean.appraisalTypeName, 'rv')}"/>
<c:set var="isEksternal" value="${fn:containsIgnoreCase(formBean.appraisalTypeCategory, 'eksternal')}"/>
<c:set var="isKendaraan" value="${fn:containsIgnoreCase(formBean.detail.categoryName, 'kendaraan')}"/>
<c:set var="isLnb" value="${fn:containsIgnoreCase(formBean.detail.categoryName, 'Tanah dan Bangunan')}"/>
<c:set var="isMesin" value="${fn:containsIgnoreCase(formBean.detail.categoryName, 'Mesin')}"/>
<c:set var="isInspeksi" value="${fn:containsIgnoreCase(formBean.detail.categoryName, 'Inspeksi')}"/>
<c:set var="isStock" value="${fn:containsIgnoreCase(formBean.detail.categoryName, 'Stock')}"/>
<c:set var="isVessel" value="${fn:containsIgnoreCase(formBean.detail.assetTypeName, 'Vessel')}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <c:set var="ctxPath" value="${pageContext.request.contextPath}"/>
    <title>Appraisal</title>
    <meta http-equiv="Expires" content="-1"/>
    <meta http-equiv="Pragma" content="no-cache"/>
    <meta http-equiv="Cache-Control" content="no-cache"/>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <link href="${ctxPath}/js/bootstrap_2.3.2/css/bootstrap.css" rel="stylesheet">
    <!--[if gte IE 9]><!-->
    <link rel="stylesheet" media="screen" href="${ctxPath}/css/glyphicons/glyphicons.min.css">
    <!--<![endif]-->
    <link href="${ctxPath}/css/main-app.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="page-header">
        <c:if test="${not empty flashMessage}">
            <div class="alert alert-error">
                <button type="button" class="close" data-dismiss="alert">&times;</button>
                <strong>${flashMessage}</strong>
            </div>
        </c:if>
        <div class="absolute-right">
            <div class="btn-group">
                <c:if test="${formBean.surveyBy != null}">
                    <c:choose>
                        <c:when test="${isInspeksi}">
                            <button type="button" class="btn btn-info" onclick="window.open('laporan-survey?PIdDataDetail=${formBean.detail.id}&survey=inspeksi','_blank')">View Survey</button>
                        </c:when>
                        <c:when test="${isKendaraan}">
                            <button type="button" class="btn btn-info" onclick="window.open('laporan-survey?PIdDataDetail=${formBean.detail.id}&survey=kendaraan','_blank')">View Survey</button>
                        </c:when>
                        <c:when test="${isMesin}">
                            <button type="button" class="btn btn-info" onclick="window.open('laporan-survey?PIdDataDetail=${formBean.detail.id}&survey=mesin','_blank')">View Survey</button>
                        </c:when>
                        <c:when test="${isLnb}">
                            <button type="button" class="btn btn-info" onclick="window.open('laporan-survey?PIdDataDetail=${formBean.detail.id}&survey=lnb','_blank')">View Survey</button>
                        </c:when>
                        <%--<c:when test="${isStock}">--%>
                            <%--<button type="button" class="btn btn-info" onclick="window.open('laporan-survey?PIdDataDetail=${formBean.detail.id}&survey=stock','_blank')">View Survey</button>--%>
                        <%--</c:when>--%>
                    </c:choose>
                    <%--<c:if test="${isKendaraan}">--%>
                        <%--<button type="button" class="btn btn-info" onclick="window.open('laporan-survey?PIdDataDetail=${formBean.detail.id}&survey=kendaraan','_blank')">View Survey</button>--%>
                    <%--</c:if>--%>
                    <%--<c:if test="${isLnb}">--%>
                        <%--<button type="button" class="btn btn-info" onclick="window.open('laporan-survey?PIdDataDetail=${formBean.detail.id}&survey=lnb','_blank')">View Survey</button>--%>
                    <%--</c:if>--%>
                    <%--<c:if test="${isMesin}">--%>
                        <%--<button type="button" class="btn btn-info" onclick="window.open('laporan-survey?PIdDataDetail=${formBean.detail.id}&survey=mesin','_blank')">View Survey</button>--%>
                    <%--</c:if>--%>
                </c:if>
            <a class="btn btn-warning" onclick="gotoUrl('view?id=${formBean.id}#header-survey')"><i
                class="icon-white icon-step-backward"></i> Back
            </a>
            <button id="btn-hide-show" title="Hide / Show" type="submit" class="btn btn-info"><i class="icon-white icon-minus"></i> Minimize</button>
            </div>
        </div>
        <h3>Detail Survey</h3>
    </div>
    <f:form method="post" modelAttribute="formBean" action="save-survey" class="form-horizontal" id="data-detail">
        <div style="border:1px solid #ddd; padding: 8px;" class="controls-row">
            <div class="control-group">
                <label class="control-label" for="orderNumber">Order Number</label>
                <div class="controls">
                    <f:input path="orderNumber" type="text" class="form-control" readonly="true"/>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="name">Nama Debitur</label>
                <div class="controls">
                    <f:input path="name" type="text" class="form-control" readonly="true"/>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="contactName">Nama Contact Person</label>
                <div class="controls">
                    <f:input path="contactName" type="text" class="form-control" readonly="true"/>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="contactTelp">Telp. Contact Person</label>
                <div class="controls">
                    <f:input path="contactTelp" type="text" class="form-control" readonly="true"/>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="detail.assetTypeName">Jenis Object Penilaian</label>
                <div class="controls">
                    <f:input path="detail.assetTypeName" class="form-control" readonly="true"/>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="detail.alamat">Lokasi</label>
                <div class="controls">
                    <f:input path="detail.alamat" type="text" class="form-control" readonly="true"/>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="catatan">Catatan dari Bisnis Unit</label>
                <div class="controls">
                    <f:input path="catatan" type="text" class="form-control" readonly="true"/>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="detail.from">Input Data Hasil Survey</label>
                <div class="controls">
                    <f:input path="detail.from" id="detail-from" type="text" class="form-control" readonly="true"/>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="detail.day">Waktu Perjanjian</label>
                <div class="controls">
                    <f:input path="detail.day" id="hari" type="text" class="form-control"/>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="detail.message">Keterangan Status Drop</label>
                <div class="controls">
                    <f:input path="detail.message" type="text" class="form-control"/>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="detail.surveyStatus">Status</label>
                <div class="controls">
                    <f:select path="detail.surveyStatus" id="stats">
                        <f:option value="New">New</f:option>
                        <f:option value="Drop">Drop</f:option>
                        <f:option value="Close">Close</f:option>
                    </f:select>
                </div>
            </div>
        </div>
        <c:if test="${!inquery}">
            <div class="control-group" style="margin-top: 10px; margin-left: 10px;">
                <div class="control-group">
                    <label class="control-label">&nbsp;</label>
                    <div class="controls">
                        <f:hidden path="id"/>
                        <f:hidden path="detail.id"/>
                        <button type="submit" id="simpan" class="btn btn-success"><i class="icon-white icon-ok"></i> Save</button>
                        <c:if test="${formBean.detail.surveyStatus != 'Close'}">
                            <c:if test="${sendToMobile == null}">
                                <a href="#" id="btn-manual" class="btn btn-success"><i class="icon-white icon-ok"></i> Manual</a>
                                <c:choose>
                                    <c:when test="${isBv || isRv}">
                                        <a href="#" id="btn-send-mobile" class="btn btn-info"><i class="icon-white icon-share"></i> Send To Mobile</a>
                                    </c:when>
                                    <c:otherwise>
                                        <a href="#" class="btn btn-info" disabled="disabled" readonly="readonly"><i class="icon-white icon-share"></i> Send To Mobile</a>
                                    </c:otherwise>
                                </c:choose>
                            </c:if>
                            <c:if test="${sendToMobile != null}">
                                <c:if test="${sendToMobile.status != 'COMPLETE'}">
                                    <a href="#" class="btn btn-info disabled"><i class="icon-white icon-download"></i> Ambil Dari Mobile</a>
                                </c:if>
                                <c:if test="${sendToMobile.status == 'COMPLETE'}">
                                    <a href="ambil-dari-mobile?jenisAppraisal=${formBean.appraisalTypeName}&orderNum=${formBean.orderNumber}&commonId=${formBean.id}&detailId=${formBean.detail.id}" id="btn-get-mobile" class="btn btn-info"><i class="icon-white icon-download"></i> Ambil Dari Mobile</a>
                                </c:if>
                            </c:if>
                        </c:if>
                    </div>
                </div>
            </div>
        </c:if>
    </f:form>
    <c:if test="${isVessel}">
        <div id="survey-vessel-body" ></div>
    </c:if>
    <c:if test="${isMesin}">
        <div id="survey-mesin-body" ></div>
    </c:if>
    <c:if test="${sendToMobile.status == 'COMPLETE'}">
        <c:if test="${isBv}">
            <div id="survey-bv-body">Survey BV</div>
        </c:if>
        <c:if test="${isRv}">
            <div id="survey-rv-body">Survey RV</div>
        </c:if>
    </c:if>
    <c:if test="${!isEksternal && !isBv && !isRv && !isVessel && !isMesin}">
        <div style="margin-top: 10px;">
            <ul class="nav nav-tabs">

                <li class="active"><a data-toggle="tab" href="#menu1">Data Umum</a></li>
                <c:if test="${!isInspeksi}">
                    <li><a data-toggle="tab" href="#menu2">Data Point</a></li>
                </c:if>
                <c:choose>
                    <c:when test="${isInspeksi}">
                        <li><a data-toggle="tab" href="#menu8">Data Inspeksi</a></li>
                        <li><a data-toggle="tab" href="#menu11">Barang Jadi</a></li>
                        <li><a data-toggle="tab" href="#menu12">Barang Setengah Jadi</a></li>
                        <li><a data-toggle="tab" href="#menu13">Bahan Baku</a></li>
                    </c:when>
                    <c:when test="${isKendaraan}">
                        <li><a data-toggle="tab" href="#menu3">Data Kendaraan</a></li>
                    </c:when>
                    <c:when test="${isStock}">
                        <li><a data-toggle="tab" href="#menu7">Data Stock</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a data-toggle="tab" href="#menu3">Data Tanah</a></li>
                        <li><a data-toggle="tab" href="#menu4">Data Bangunan</a></li>
                        <li><a data-toggle="tab" href="#menu5">Data Lingkungan</a></li>
                        <li><a data-toggle="tab" href="#menu6">Data Pembanding</a></li>
                    </c:otherwise>
                </c:choose>
                 <%--commented : karena code ini memungkinkan akan muncul 2 menu apabila ada nama yang sama seperti kondisi nya.--%>
                <%--<c:if test="${isLnb}">--%>
                    <%--<li><a data-toggle="tab" href="#menu3">Data Tanah</a></li>--%>
                    <%--<li><a data-toggle="tab" href="#menu4">Data Bangunan</a></li>--%>
                    <%--<li><a data-toggle="tab" href="#menu5">Data Lingkungan</a></li>--%>
                    <%--<li><a data-toggle="tab" href="#menu6">Data Pembanding</a></li>--%>
                <%--</c:if>--%>
                <%--<c:if test="${isKendaraan}">--%>
                    <%--<li><a data-toggle="tab" href="#menu3">Data Kendaraan</a></li>--%>
                <%--</c:if>--%>

                <%--<c:if test="${isStock}">--%>
                    <%--<li><a data-toggle="tab" href="#menu7">Data Stock</a></li>--%>
                <%--</c:if>--%>




            </ul>
            <div class="tab-content">

                <div id="menu1" class="tab-pane fade in active">
                    <iframe src="${ctxPath}/survey/show?detailId=${formBean.detail.id}&inquery=${inquery}" width="99%" height="700px" scrolling="no" frameborder="0"></iframe>
                </div>



                <div id="menu2" class="tab-pane fade">
                    <iframe src="${ctxPath}/survey/show-point?detailId=${formBean.detail.id}&inquery=${inquery}" width="99%" height="900px" scrolling="auto" frameborder="0"></iframe>
                </div>


                <c:if test="${isKendaraan}">
                    <div id="menu3" class="tab-pane fade">
                        <iframe src="${ctxPath}/survey/show-kendaraan?detailId=${formBean.detail.id}&inquery=${inquery}" width="99%" height="700px" scrolling="no" frameborder="0"></iframe>
                    </div>
                </c:if>
                <c:if test="${isLnb}">
                    <div id="menu3" class="tab-pane fade">
                        <iframe src="${ctxPath}/survey/show-tanah?detailId=${formBean.detail.id}&inquery=${inquery}" width="99%" height="700px" scrolling="no" frameborder="0"></iframe>
                    </div>
                    <div id="menu4" class="tab-pane fade">
                        <iframe src="${ctxPath}/survey/show-bangunan?detailId=${formBean.detail.id}&inquery=${inquery}" width="99%" height="700px" scrolling="no" frameborder="0"></iframe>
                    </div>
                    <div id="menu5" class="tab-pane fade">
                        <iframe src="${ctxPath}/survey/show-lingkungan?detailId=${formBean.detail.id}&inquery=${inquery}" width="99%" height="700px" scrolling="no" frameborder="0"></iframe>
                    </div>
                    <div id="menu6" class="tab-pane fade">
                        <iframe src="${ctxPath}/survey/show-pembanding?detailId=${formBean.detail.id}&inquery=${inquery}" width="99%" height="700px" scrolling="no" frameborder="0"></iframe>
                    </div>
                </c:if>

                <c:if test="${isInspeksi}">
                    <div id="menu8" class="tab-pane fade">

                        <iframe src="${ctxPath}/survey/show-inspeksi?detailId=${formBean.detail.id}" width="99%" height="700px" scrolling="no" frameborder="0"></iframe>
                            <%--<iframe src="${ctxPath}/survey/show-kendaraan?detailId=${formBean.detail.id}" width="99%" height="700px" scrolling="no" frameborder="0"></iframe>--%>
                    </div>
                    <div id="menu11" class="tab-pane fade">
                        <iframe src="${ctxPath}/survey/show-barang?detailId=${formBean.detail.id}&typeGoods=Goods" width="99%" height="700px" scrolling="no" frameborder="0"></iframe>
                    </div>
                    <div id="menu12" class="tab-pane fade">
                        <iframe src="${ctxPath}/survey/show-barang?detailId=${formBean.detail.id}&typeGoods=Half_Goods" width="99%" height="700px" scrolling="no" frameborder="0"></iframe>
                    </div>
                    <div id="menu13" class="tab-pane fade">
                        <iframe src="${ctxPath}/survey/show-barang?detailId=${formBean.detail.id}&typeGoods=Raw_Materials" width="99%" height="700px" scrolling="no" frameborder="0"></iframe>
                    </div>
                </c:if>


                <c:if test="${isStock}">
                    <div id="menu7" class="tab-pane fade">

                        <iframe src="${ctxPath}/survey/show-stock?detailId=${formBean.detail.id}" width="99%" height="700px" scrolling="no" frameborder="0"></iframe>
                            <%--<iframe src="${ctxPath}/survey/show-kendaraan?detailId=${formBean.detail.id}" width="99%" height="700px" scrolling="no" frameborder="0"></iframe>--%>
                    </div>
                </c:if>






            </div>
        </div>
    </c:if>
</div>
<div id="modal-view" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="modal-view-label"
        aria-hidden="true" data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
        <h3 id="modal-view-label">Data Dokumen Tanah</h3>
    </div>
    <div class="modal-body"></div>
</div>
<div id="loading-image" class="loading2" />
<script src="${ctxPath}/js/bootstrap_2.3.2/js/jquery.js"></script>
<script src="${ctxPath}/js/bootstrap_2.3.2/js/bootstrap.min.js"></script>
<!--[if lt IE 9]>
<script src="${ctxPath}/js/html5shiv.js"></script>
<script src="${ctxPath}/js/respond.min.js"></script>
<![endif]-->
<script type="text/javascript" src="${ctxPath}/js/main-app.js"></script>
<script type="text/javascript">
    function callbackModal(){
        window.location.reload();
    }
    $(document).ready(function () {

        $.active = false;
        $('body').bind('click keypress', function() { $.active = true; });
        var logout = '${ctxPath}/logoutExpired';
        var heartBeat = '${ctxPath}/heartbeat';
        checkActivity(${maxInterval}, 60000, 0,logout,heartBeat);

        // function
        //callbackModal = function(){
            // $('#modal-view').modal('hide');
        //}
        <c:if test="${isBv}">
            $.ajax({
                type: 'get',
                cache: false,
                dataType: "html",
                url: "${ctxPath}/survey-bv/show",
                data: 'detailId=${formBean.detail.id}&person=${person.userId}&inquery=',
                complete: function (data) {
                    $('#survey-bv-body').html(data.responseText);
                }
            });
        </c:if>
        <c:if test="${isRv}">
        $.ajax({
            type: 'get',
            cache: false,
            dataType: "html",
            url: "${ctxPath}/survey-rv/show",
            data: 'detailId=${formBean.detail.id}&person=${person.userId}&inquery=',
            complete: function (data) {
                $('#survey-rv-body').html(data.responseText);
            }
        });
        </c:if>
        <c:if test="${isVessel}">
        $.ajax({
            type: 'get',
            cache: false,
            dataType: "html",
            url: "${ctxPath}/survey-vessel/show-vessel-valuer",
            data: 'detailId=${formBean.detail.id}&person=${person.userId}&inquery=',
            complete: function (data) {
                $('#survey-vessel-body').html(data.responseText);
            }
        });
        </c:if>
        <c:if test="${isMesin}">
        $.ajax({
            type: 'get',
            cache: false,
            dataType: "html",
            url: "${ctxPath}/survey-mesin/show-mesin-valuer",
            data: 'detailId=${formBean.detail.id}&person=${person.userId}&inquery=',
            complete: function (data) {
                $('#survey-mesin-body').html(data.responseText);
            }
        });
        </c:if>

        function initFormModal(){
            $('#form-send-to-mobile').submit(function(){
                var surveyorId = $(this).find('select[name=surveyorId]').val();
                if(surveyorId == null || surveyorId == undefined || surveyorId == ''){
                    alert('Surveyor harus diisi');
                    return false;
                }
                $.ajax({
                    type: $(this).attr('method'),
                    cache: false,
                    dataType: "html",
                    url: $(this).attr('action'),
                    data: $(this).serialize(),
                    complete: function (data) {
                        $('#modal-view').find(".modal-body").html(data.responseText);
                        initFormModal();
                    }
                });
                return false;
            });
        }

        // component event
        $('#btn-manual').click(function () {
            $("#detail-from").val("MANUAL");
            return false;
        });
        $("#btn-hide-show").click(function () {
            $("#data-detail").toggle('slow');
        });
        $('#btn-send-mobile').click(function(){
            $('#modal-view-label').text('Form Send To Mobile');
            $('#modal-view').find(".modal-body").load("form-send-to-mobile?commondId=${formBean.id}&orderNumber=${formBean.orderNumber}", function(){
                $('#modal-view').modal('show');
                initFormModal();
            });
            return false;
        });
        $('#btn-get-mobile').click(function(){
            $('#loading-image').html('<img src="${ctxPath}/images/loader.gif" style="width: 200px; height: 200px;">').show();
        });

        $('#simpan').click(function(){
            detail = $('#hari').val();
            hasilData = $('#detail-from').val();
            status = $('#stats').val();
            <%--<f:select path="detail.surveyStatus">--%>
            <%--<f:option value="New">New</f:option>--%>
            <%--<f:option value="Drop">Drop</f:option>--%>
            <%--<f:option value="Close">Close</f:option>--%>
            <%--</f:select>--%>

           if (status == 'Close'){

               if (detail == null || $.trim(detail) == ''){
                   alert('Waktu Perjanjian Harus di isi');
                   return false;
               }

               if (hasilData == null || $.trim(hasilData) == ''){
                   alert('Hasil Survey Harus di isi');
                   return false;
               }
           }



        });


    });
</script>
</body>
</html>