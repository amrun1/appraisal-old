<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
    <link href="${ctxPath}/css/main-app.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="${ctxPath}/js/html5shiv.js"></script>
    <script src="${ctxPath}/js/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="container">
    <c:if test="${not empty msg}">
        <div class="alert alert-success">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <strong>${msg}</strong>
        </div>
    </c:if>

    <c:if test="${not empty msgErrorImage}">
        <div class="alert alert-error">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <strong>${msgErrorImage}</strong>
        </div>
    </c:if>

    <f:form method="post" modelAttribute="formBean" action="save-image?${_csrf.parameterName}=${_csrf.token}&activaId=${activaId}&appraisalId=${appraisalId}"
            class="form-horizontal" enctype="multipart/form-data">
        <f:hidden path="id"/>
        <f:hidden path="commonId"/>
        <f:hidden path="lpaId"/>
        <div class="control-group${fn:length(listCategory) == 0 ? " error" : ""}">
            <label class="control-label" for="idDocumentLpa">Kategori</label>
            <div class="controls">
                <f:select path="idDocumentLpa">
                    <f:option value="">-- Select --</f:option>
                    <f:options items="${listCategory}" itemValue="value" itemLabel="label"/>
                </f:select>
                <c:if test="${fn:length(listCategory) == 0}">
                    <div class="help-block error">Data Jenis Dokumen Laporan Kosong, hubungi admin</div>
                </c:if>
                <f:errors path="idDocumentLpa" cssClass="help-block error"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="catatan">Catatan</label>
            <div class="controls">
                <f:textarea path="catatan" rows="3" readonly="true"/>
                <f:errors path="catatan" class="error has-error"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="image">File</label>
            <div class="controls">
                <f:hidden path="contentType"/>
                <span class="btn btn-info btn-file">
                    <span id="info-accept">IMAGE</span> <f:input path="image" type="file" accept="image/*"/>
                </span>
                <span></span>
                <f:errors path="image" class="error has-error"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">&nbsp;</label>
            <div class="controls">
                <button type="submit" id="btnSave" class="btn btn-success"><i class="icon-white icon-ok-circle"></i> Save</button>
                <button type="reset" class="btn btn-danger"><i class="icon-white icon-refresh"></i> Reset</button>
            </div>
        </div>
    </f:form>
</div>
<script src="${ctxPath}/js/bootstrap_2.3.2/js/jquery.js"></script>
<script src="${ctxPath}/js/bootstrap_2.3.2/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctxPath}/js/main-app.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        var imageOnly = true;
        var docPelengkap = false;
        $('#image').change(function(e) {
            var files = e.target.files;
            var file = files[0];
            var type = file.type;
            var size = file.size;
            document.getElementById("btnSave").disabled = false;
            if(type == null || type == "") {
                type = file.name.split('.').pop();
                if(type == "xls") {
                    type = "application/vnd.ms-excel";
                } else if (type == "jpg" || type == "jpeg") {
                    type = "image/jpeg";
                } else if (type == "pdf") {
                    type = "application/pdf";
                }  else if (type == "tif") {
                    type = "image/tiff";
                } else if (type == "bmp") {
                    type = "image/bmp";
                } else if (type == "png") {
                    type = "image/png";
                }
                file.type = type;
            }
            if(imageOnly){
                if(type.search('jpg') == -1 && type.search('png') == -1 && type.search('jpeg') == -1){
                    alert('File yang diperbolehkan IMAGE');
                    document.getElementById("btnSave").disabled = true;
                    return false;
                }


                if(size>1000000){
                    alert("Ukuran File Maksimum 1MB");
                    document.getElementById("btnSave").disabled = true;
                    return false;
                }

            } else if(docPelengkap) {
                if(type.search('pdf') == -1 && type.search('image') == -1){
                    alert('File yang diperbolehkan PDF dan IMAGE');
                    document.getElementById("btnSave").disabled = true;
                    return false;
                }

                if(size>1000000){
                    alert("Ukuran File Maksimum 1MB");
                    document.getElementById("btnSave").disabled = true;
                    return false;
                }
            } else {
                if(type.search('excel') == -1 && type.search('pdf') == -1 && type.search('tif') == -1 && type.search('image') == -1 && type.search('xls') == -1){
                    alert('File yang diperbolehkan XLS, TIF, PDF, dan IMAGE');
                    document.getElementById("btnSave").disabled = true;
                    return false;
                }
                if (type.search('excel') == -1 && type.search('xls') == -1) {
                    if(size>1024000) {
                        alert("Ukuran File Maksimum 1MB");
                        document.getElementById("btnSave").disabled = true;
                        return false;
                    }
                } else if(size>1536000){
                    alert("Ukuran File Maksimum 1.5MB");
                    document.getElementById("btnSave").disabled = true;
                    return false;
                }
            }
            $(this).parent().next().text(file.name);
            $('#contentType').val(type);
        });
        $('#idDocumentLpa').change(function(){
            var value = $(this).find(':selected').text();
            var idDoc = $(this).find(':selected').val();
            //Change to Ajax
            $.ajax({
                type : "GET",
                url : "<s:url value="get-status-doc"/>",
                async: false,
                dataType : "json",
                data : {
                    "id" : idDoc
                },
            }).success(function(data) {
                if(data.jenisData.toLocaleLowerCase().search("worksheet") != -1) {
                    console.log("Data worksheet")
                    $("#catatan").prop('readonly', true);
                    $('#image').prop('accept', ".xls");
                    $('#info-accept').text("XLS");
                    imageOnly = false;
                    docPelengkap = false;
                } else if (data.jenisData.toLocaleLowerCase().search("lain-lain") != -1) {
                    console.log("Data Lain lain")
                    $("#catatan").prop('readonly', false);
                    $('#image').prop('accept', ".pdf, .tif, image/*");
                    $('#info-accept').text("PDF, TIF, IMAGE");
                    imageOnly = false;
                    docPelengkap = false;
                }else if (data.jenisData.toLocaleLowerCase().search("dokumen pelengkap") != -1) {
                    console.log("Data dokumen pelengkap")
                    $("#catatan").prop('readonly', false);
                    $('#image').prop('accept', ".pdf, image/jpeg, image/jpg, image/png");
                    $('#info-accept').text("PDF, IMAGE");
                    imageOnly = false;
                    docPelengkap = true;
                }else{
                    console.log("Data Gambar")
                    $("#catatan").prop('readonly', ${kategoriLpa});
                    $('#image').prop('accept', "image/jpeg, image/jpg, image/png");
                    $('#info-accept').text("IMAGE");
                    imageOnly = true;
                    docPelengkap = false;
                }

            });
//            if(value.toLowerCase().search("worksheet") != -1){
//                $("#catatan").prop('readonly', true);
//                $('#image').prop('accept', ".xls");
//                $('#info-accept').text("XLS");
//                imageOnly = false;
//            } else if(value.toLowerCase().search("lain - lain") != -1){
//                $("#catatan").prop('readonly', false);
//                $('#image').prop('accept', ".pdf, .tif, image/*");
//                $('#info-accept').text("PDF, TIF, IMAGE");
//                imageOnly = false;
//            } else {
//                $("#catatan").prop('readonly', true);
//                $("#catatan").val('');
//                $('#image').prop('accept', "image/*");
//                $('#info-accept').text(" IMAGE ");
//                imageOnly = true;
//            }
        })

        var valueLoad = $(this).find(':selected').text();
        var idDocLoad = $(this).find(':selected').val();
        $.ajax({
            type : "GET",
            url : "<s:url value="get-status-doc"/>",
            async: false,
            dataType : "json",
            data : {
                "id" : idDocLoad
            },
        }).success(function(data) {
        if(data.jenisData.toLocaleLowerCase().search("worksheet") != -1) {
            console.log("Data worksheet")
            $("#catatan").prop('readonly', true);
            $('#image').prop('accept', ".xls");
            $('#info-accept').text("XLS");
            imageOnly = false;
            docPelengkap = false;
        } else if (data.jenisData.toLocaleLowerCase().search("lain-lain") != -1) {
            console.log("Data Lain lain")
            $("#catatan").prop('readonly', false);
            $('#image').prop('accept', ".pdf, .tif, image/*");
            $('#info-accept').text("PDF, TIF, IMAGE");
            imageOnly = false;
            docPelengkap = false;
        }else if (data.jenisData.toLocaleLowerCase().search("dokumen pelengkap") != -1) {
            console.log("Data dokumen pelengkap")
            $("#catatan").prop('readonly', false);
            $('#image').prop('accept', ".pdf, image/jpeg, image/jpg, image/png");
            $('#info-accept').text("PDF, IMAGE");
            imageOnly = false;
            docPelengkap = true;
        }else{
            console.log("Data Gambar")
            $("#catatan").prop('readonly', ${kategoriLpa});
            $('#image').prop('accept', "image/jpeg, image/jpg, image/png");
            $('#info-accept').text("IMAGE");
            imageOnly = true;
            docPelengkap = false;
        }});
    });
</script>
</body>
</html>