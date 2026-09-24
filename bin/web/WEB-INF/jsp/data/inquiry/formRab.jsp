<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page import="com.permata.appraisal2.model.PersonLevel" %>
<c:set var="taskPosition" value="<%= PersonLevel.APPRAISAL_VALUER %>"/>
<c:set var="showButton" value="${roleActive.level == taskPosition}"/>
<c:set var="isEksternal" value="${fn:containsIgnoreCase(formBean.kategoriJenisAppraisal, 'eksternal')}"/>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
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
    <link href="${ctxPath}/js/bootstrap-datepicker-1.6.4/css/bootstrap-datepicker.min.css" rel="stylesheet">
    <link href="${ctxPath}/css/main-app.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="${ctxPath}/js/html5shiv.js"></script>
    <script src="${ctxPath}/js/respond.min.js"></script>
    <![endif]-->

    <style type="text/css">
        /*.modal { width:90%; left: 30%;}*/
        .modal-body {
            max-height: 448px;
            padding: 10px;
        }
        .modal.fade.in {
            top: 5%;
        }
        .modal-header {
            padding: 5px 10px;
        }
    </style>
</head>
<body>
<div class="container">
    <f:form id="form-lpa" method="post" modelAttribute="formBean" action="draft-lpa" class="form-horizontal">
        <f:hidden path="faktorMarketability"/>
        <f:hidden path="pembanding1"/>
        <f:hidden path="pembanding2"/>
        <f:hidden path="pembanding3"/>
        <f:hidden path="catatanPoint"/>
        <div class="page-header-page">
            <div class="absolute-right input-append">
                <c:if test="${formBean.id != null}">
                    <div class="btn-group">
                        <button class="btn btn-info dropdown-toggle" data-toggle="dropdown"><i class="icon-white icon-file"></i>Preview Laporan <span class="caret"></span></button>
                        <ul class="dropdown-menu">

                                <li><a href="laporan-lpa-rab?PIdLpaUmum=${formBean.idLpa}&lpa=lpa-rab" target="_blank">LPA</a></li>
                            <li class="divider"></li>
                            <li><a href="laporan-lpa-rab?PIdLpaUmum=${formBean.idLpa}&lpa=img-survey" target="_blank">Image Hasil Survey</a></li>
                            <li><a href="laporan-lpa-rab?PIdLpaUmum=${formBean.idLpa}&lpa=img-peta" target="_blank">Image Peta Wilayah / Lokasi</a></li>
                            <li><a href="#myModalLain" data-toggle="modal">Image Dokumen Lain</a></li>
                        </ul>
                    </div>
                </c:if>
                <c:if test="${showButton}">
                    <%--<button type="submit" id="btn-save-all" class="btn btn-success"><i class="icon-white icon-ok"></i>Save</button>--%>
                    <%--<button type="submit" id="btn-save-draft" class="btn btn-info"><i class="icon-white icon-camera"></i> Draf</button>--%>
                    <%--<button type="reset" class="btn btn-primary"><i class="icon-white icon-refresh"></i> Reset</button>--%>
                    <button type="button" onclick="gotoUrl('${ctxPath}/inquiry/view?id=${formBean.commonId}');" class="btn btn-warning"><i class="icon-white icon-arrow-left"></i> Back</button>
                    <%--<c:if test="${formBean.id != null}">--%>
                        <%--<a href="delete-lpa?id=${formBean.id}&commonId=${formBean.commonId}" id="btn-delete-lpa" class="btn btn-danger"><i class="icon-white icon-remove"></i> Delete</a>--%>
                    <%--</c:if>--%>
                </c:if>
                <c:if test="${!showButton}">
                    <button type="button" onclick="gotoUrl('${ctxPath}/inquiry/view?id=${formBean.commonId}');" class="btn btn-warning"><i class="icon-white icon-arrow-left"></i> Back</button>
                </c:if>
            </div>
            <h1>Generate LPA ${formBean.kategoriJenisAppraisal} ${formBean.jenisAppraisal}</h1>
        </div>
        <div class="clearfix" style="margin-top:60px"></div>
        <spring:hasBindErrors name="formBean">
            <div class="alert alert-error">
                <button type="button" class="close" data-dismiss="alert">&times;</button>
                Terdapat ${fn:length(errors.allErrors)} Error Validasi, Silahkan Check Form Isian Anda
            </div>
        </spring:hasBindErrors>
        <ul class="nav nav-tabs">
            <li class="active"><a data-toggle="tab" href="#menu1">Data Umum <span class="badge badge-warning hide"></span></a></li>
            <li><a data-toggle="tab" href="#menu2">Data RAB <span class="badge badge-warning hide"></span></a></li>
            <%--<li><a data-toggle="tab" href="#menu3">Nilai Agunan <span class="badge badge-warning hide"></span></a></li>--%>
            <%--<li><a data-toggle="tab" href="#menu4">Faktor Marketability & Catatan<span class="badge badge-warning hide"></span></a></li>
            <li><a data-toggle="tab" href="#menu5">Negative List Appraisal <span class="badge badge-warning hide"></span></a></li>--%>
            <li><a data-toggle="tab" href="#menu6">Data Lampiran <span class="badge badge-warning hide"></span></a></li>
        </ul>
        <div class="tab-content">
            <div id="menu1" class="tab-pane fade in active panel controls-row">
                <jsp:include page="../../lpa/form-umum.jsp"/>
            </div>
            <%--<div id="menu4" class="tab-pane fade controls-row panel">
                <jsp:include page="../form-market.jsp"/>
            </div>
            <div id="menu5" class="tab-pane fade controls-row panel">
                <jsp:include page="../form-pertimbangan-dan-negative.jsp"/>
            </div>--%>
            <div id="menu6" class="tab-pane fade controls-row panel">
                <div class="absolute-right">
                    <%--<c:if test="${formBean.id != null && showButton }"><button id="btn-add-image" type="button" class="btn btn-success"><i class="icon-white icon-plus"></i> Add</button></c:if>--%>
                </div>
                <table class="table dataTable" style="margin-top:28px;">
                    <thead><tr><th width="100px">#</th><th>Kategori</th><th>Catatan</th></tr></thead>
                    <tbody id="tbody-image"></tbody>
                </table>
            </div>
            <div id="menu2" class="tab-pane fade controls-row panel">
                <div class="control-group">
                    <label class="control-label" for="biayaDilampirkan">Biaya Yang Dilampirkan</label>
                    <div class="controls">
                        <f:input path="biayaDilampirkan" cssClass="input-medium" data-mask="luas-tanah"/>
                        <f:errors path="biayaDilampirkan" cssClass="help-block"/>
                    </div>
                </div>

                <div class="control-group">
                    <label class="control-label" for="biayaTidakDiperhitungkan">Biaya Yang Tidak Diperhitungkan</label>
                    <div class="controls">
                        <f:input path="biayaTidakDiperhitungkan" cssClass="input-medium" data-mask="luas-tanah" readonly="true"/>
                        <f:errors path="biayaTidakDiperhitungkan" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="tidakDiperhitungkan1txt">&nbsp;</label>
                    <div class="controls">
                        <div class="input-prepend">
                            <span class="btn btn-info">1</span>
                            <f:input path="tidakDiperhitungkan1txt" cssClass="input-medium"/>
                            <f:errors path="tidakDiperhitungkan1txt" cssClass="help-block"/>
                        </div>
                        <div class="input-prepend">
                            <span class="btn btn-info">Rp.</span>
                            <f:input path="tidakDiperhitungkan1" cssClass="input-small" data-mask="luas-tanah"/>
                            <f:errors path="tidakDiperhitungkan1" cssClass="help-block"/>
                        </div>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="tidakDiperhitungkan2txt">&nbsp;</label>
                    <div class="controls">
                        <div class="input-prepend">
                            <span class="btn btn-info">2</span>
                            <f:input path="tidakDiperhitungkan2txt" cssClass="input-medium"/>
                            <f:errors path="tidakDiperhitungkan2txt" cssClass="help-block"/>
                        </div>
                        <div class="input-prepend">
                            <span class="btn btn-info">Rp.</span>
                            <f:input path="tidakDiperhitungkan2" cssClass="input-small" data-mask="luas-tanah"/>
                            <f:errors path="tidakDiperhitungkan2" cssClass="help-block"/>
                        </div>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="tidakDiperhitungkan3txt">&nbsp;</label>
                    <div class="controls">
                        <div class="input-prepend">
                            <span class="btn btn-info">3</span>
                            <f:input path="tidakDiperhitungkan3txt" cssClass="input-medium"/>
                            <f:errors path="tidakDiperhitungkan3txt" cssClass="help-block"/>
                        </div>
                        <div class="input-prepend">
                            <span class="btn btn-info">Rp.</span>
                            <f:input path="tidakDiperhitungkan3" cssClass="input-small" data-mask="luas-tanah"/>
                            <f:errors path="tidakDiperhitungkan3" cssClass="help-block"/>
                        </div>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="tidakDiperhitungkan4txt">&nbsp;</label>
                    <div class="controls">
                        <div class="input-prepend">
                            <span class="btn btn-info">4</span>
                            <f:input path="tidakDiperhitungkan4txt" cssClass="input-medium"/>
                            <f:errors path="tidakDiperhitungkan4txt" cssClass="help-block"/>
                        </div>
                        <div class="input-prepend">
                            <span class="btn btn-info">Rp.</span>
                            <f:input path="tidakDiperhitungkan4" cssClass="input-small" data-mask="luas-tanah"/>
                            <f:errors path="tidakDiperhitungkan4" cssClass="help-block"/>
                        </div>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="tidakDiperhitungkan5txt">&nbsp;</label>
                    <div class="controls">
                        <div class="input-prepend">
                            <span class="btn btn-info">5</span>
                            <f:input path="tidakDiperhitungkan5txt" cssClass="input-medium"/>
                            <f:errors path="tidakDiperhitungkan5txt" cssClass="help-block"/>
                        </div>
                        <div class="input-prepend">
                            <span class="btn btn-info">Rp.</span>
                            <f:input path="tidakDiperhitungkan5" cssClass="input-small" data-mask="luas-tanah"/>
                            <f:errors path="tidakDiperhitungkan5" cssClass="help-block"/>
                        </div>
                    </div>
                </div>

                <div class="control-group">
                    <label class="control-label" for="biayaDiperhitungkan">Biaya Yang Diperhitungkan</label>
                    <div class="controls">
                        <f:input path="biayaDiperhitungkan" cssClass="input-medium" data-mask="luas-tanah" readonly="true"/>
                        <f:errors path="biayaDiperhitungkan" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="luasBangungan">Luas Bangunan Sesuai Gambar Denah</label>
                    <div class="controls">
                        <f:input path="luasBangungan" cssClass="input-small" data-mask="luas-tanah"/>
                        <f:errors path="luasBangungan" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="hargaBangunanRab">Harga Bangunan per-M<sup>2</sup> Sesuai RAB</label>
                    <div class="controls">
                        <f:input path="hargaBangunanRab" cssClass="input-medium" data-mask="luas-tanah" />
                        <f:errors path="hargaBangunanRab" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="hargaBangunanInternal">Harga Bangunan per-M<sup>2</sup> Sesuai Internal</label>
                    <div class="controls">
                        <f:input path="hargaBangunanInternal" cssClass="input-medium" data-mask="luas-tanah"/>
                        <f:errors path="hargaBangunanInternal" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="nilaiReviewCustomer">Nilai Review RAB By Customer</label>
                    <div class="controls">
                        <f:input path="nilaiReviewCustomer" cssClass="input-medium" data-mask="luas-tanah"/>
                        <f:errors path="nilaiReviewCustomer" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="nilaiReviewInternal">Nilai Review RAB By Internal</label>
                    <div class="controls">
                        <f:input path="nilaiReviewInternal" cssClass="input-medium" data-mask="luas-tanah"/>
                        <f:errors path="nilaiReviewInternal" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="kewajaranNilai">Kewajaran Nilai RAB</label>
                    <div class="controls">
                        <f:select path="kewajaranNilai">
                            <f:option value="">-- Select --</f:option>
                            <f:options items="${listKewajaranNilai}" itemLabel="label" itemValue="value"/>
                        </f:select>
                        <f:errors path="kewajaranNilai" cssClass="help-block"/>
                    </div>
                </div>
            </div>
        </div>
    </f:form>
</div>
<div id="modal-image" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="modal-image-label" aria-hidden="true" data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="modal-image-label">Data Lampiran</h3>
    </div>
    <div class="modal-body" style="height: 650px;">
        <iframe id="iframe-image" src="#" width="100%" height="100%" frameborder="0"></iframe>
    </div>
</div>
<div id="myModalLain" id="modal-lain" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
        <h3 id="myModalLabel">Dokumen Lain - lain</h3>
    </div>
    <div class="modal-body">
        <table class="table table-bordered dataTable">
            <thead>
            <tr>
                <th>No</th>
                <th>Konten</th>
                <th>Keterangan</th>
                <th>View</th>
            </tr>
            </thead>
            <tbody id="tbody-image-other">
            <tr class="odd"><td colspan="3" align="center"><i>Tidak Ada data</i></td></tr>
            </tbody>
        </table>
    </div>
</div>
<script src="${ctxPath}/js/bootstrap_2.3.2/js/jquery.js"></script>
<script src="${ctxPath}/js/bootstrap_2.3.2/js/bootstrap.min.js"></script>
<script src="${ctxPath}/js/bootstrap-datepicker-1.6.4/js/bootstrap-datepicker.min.js"></script>
<script src="${ctxPath}/js/jquery.inputmask.bundle.min.js"></script>
<script type="text/javascript" src="${ctxPath}/js/main-app.js"></script>
<script type="text/javascript">
    var callbackModal;
    var refreshTableImage;
    var refreshTableImageOther;
    $(document).ready(function () {

        $.active = false;
        $('body').bind('click keypress', function() { $.active = true; });
        var logout = '${ctxPath}/logoutExpired';
        var heartBeat = '${ctxPath}/heartbeat';
        checkActivity(${maxInterval}, 60000, 0,logout,heartBeat);

        callbackModal = function(){
            refreshTableImage();
            $('#modal-image').modal('hide');
        }
        refreshTableImage = function(){
            $.ajax({
                type: "GET",
                cache: false,
                dataType: "html",
                url: '${ctxPath}/inquiry/table-image?surveyId=${formBean.surveyId}&lpaId=${formBean.idLpa}',
                success: function (html) {
                    fillTbodyImage(html);
                    $('#modal-image').modal('hide');
                    refreshTableImageOther();
                }
            })
        }
        function fillTbodyImage(html){
            $('#tbody-image').html(html);
            unbindAndbindImage();
        }
        refreshTableImageOther = function(){
            $.ajax({
                type: "GET",
                cache: false,
                dataType: "html",
                url: '${ctxPath}/inquiry/table-image-other?lpaId=${formBean.idLpa}',
                success: function (html) {
                    fillTbodyImageOther(html);
                    $('#myModalLain').modal('hide');
                }
            })
        }
        function fillTbodyImageOther(html){
            $('#tbody-image-other').html(html);
        }
        function unbindAndbindImage() {
            $(".image-edit").unbind('click');
            $(".image-edit").bind('click', function () {
                var id = $(this).data('id');
                $('#iframe-image').prop('src', "${ctxPath}/inquiry/form-image?id="+id+"&lpaId=${formBean.idLpa}&activaId=${formBean.jenisAktiva}");
                $('#modal-image').modal('show');
                refreshTableImageOther();
            });
            $(".image-delete").unbind('click');
            $(".image-delete").bind('click', function () {
                if (confirm('Anda yakin hapus data')) {
                    var id = $(this).data('id');
                    $.ajax({
                        type: "GET",
                        cache: false,
                        dataType: "html",
                        url: '${ctxPath}/inquiry/delete-image?id=' + id + '&surveyId=${formBean.surveyId}&lpaId=${formBean.idLpa}',
                        success: function (data) {
                            fillTbodyImage(data);
                            refreshTableImageOther();
                        }
                    })
                }
            });
        }



        function calcHargaBarang(){
            console.log('menghitung Harga Barang');
           var biayaDiper =   parseToFloat($('#biayaDiperhitungkan').inputmask('unmaskedvalue'));
           var luasBangun =  $('#luasBangungan').val();

            $('#hargaBangunanRab').val(biayaDiper/luasBangun);
        }

        function calc(){
            console.log('menghitung');
            var biayaDilampirkan = parseToFloat($('#biayaDilampirkan').inputmask('unmaskedvalue'));
            // var biayaTidakDiperhitungkan = parseToFloat($('#biayaTidakDiperhitungkan').inputmask('unmaskedvalue'));
            // var biayaDiperhitungkan = parseToFloat($('#biayaDiperhitungkan').inputmask('unmaskedvalue'));
            var tidakDiperhitungkan1 = parseToFloat($('#tidakDiperhitungkan1').inputmask('unmaskedvalue'));
            var tidakDiperhitungkan2 = parseToFloat($('#tidakDiperhitungkan2').inputmask('unmaskedvalue'));
            var tidakDiperhitungkan3 = parseToFloat($('#tidakDiperhitungkan3').inputmask('unmaskedvalue'));
            var tidakDiperhitungkan4 = parseToFloat($('#tidakDiperhitungkan4').inputmask('unmaskedvalue'));
            var tidakDiperhitungkan5 = parseToFloat($('#tidakDiperhitungkan5').inputmask('unmaskedvalue'));
            var totalTidakDiperhitungkan = tidakDiperhitungkan1 + tidakDiperhitungkan2 + tidakDiperhitungkan3 + tidakDiperhitungkan4 + tidakDiperhitungkan5;
            $('#biayaTidakDiperhitungkan').val(totalTidakDiperhitungkan);
            $('#biayaDiperhitungkan').val(biayaDilampirkan - totalTidakDiperhitungkan);
        }
        $('#btn-add-image').click(function () {
            $('#modal-image-label').text('Form Lampiran');
            $('#iframe-image').prop('src', "${ctxPath}/inquiry/form-image?lpaId=${formBean.idLpa}&activaId=${formBean.jenisAktiva}");
            $('#modal-image').modal('show');
        });
        $('#biayaDilampirkan, input[name=beriNilaiDoc], input[name^="tidakDiperhitungkan"]').change(function () {
            calc();
        });


        $('#luasBangungan').change(function () {
            calcHargaBarang();
        });

        $('#btn-save-all').click(function () {
            $('#draft').val(false);
            $('#form-lpa').attr('action', 'save-lpa');
            $('select').prop('disabled', false);
        })
        // main app thread
        <c:if test="${formBean.id != null}">
            refreshTableImage();
        refreshTableImageOther();
        </c:if>

        <spring:hasBindErrors name="formBean">
        function showErrorLpa(field){
            var menuId = $('[name="'+field+'"]').parents('.tab-pane').attr('id');
            var _url = $('[href="#' + menuId + '"]');
            _url.addClass('error');
            var text_ = _url.find('span').text();
            if (text_ == "") text_ = 0;
            _url.find('span').text(parseInt(text_) + 1);
            _url.find('span').show();
            $($('[name="'+field+'"]').parents('div.control-group')).addClass('error');
        }
        <c:forEach var="error" items="${errors.allErrors}">
        showErrorLpa("${error.field}");
        </c:forEach>
        </spring:hasBindErrors>
        $('#form-lpa').find('input, textarea, select').prop('disabled', true);
    });
</script>
</body>
</html>