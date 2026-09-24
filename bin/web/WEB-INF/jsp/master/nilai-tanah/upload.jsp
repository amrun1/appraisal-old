<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <c:set var="ctxPath" value="${pageContext.request.contextPath}"/>
    <title>Appraisal</title>
    <meta http-equiv="Expires" content="-1" />
    <meta http-equiv="Pragma" content="no-cache" />
    <meta http-equiv="Cache-Control" content="no-cache" />
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link href="${ctxPath}/js/bootstrap_2.3.2/css/bootstrap.css" rel="stylesheet">
    <link href="${ctxPath}/css/main-app.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="page-header">
        <h1>Data Desktop Nilai Tanah</h1>
    </div>
    <div class="page-header-page">
            <div class="absolute-right btn-group">
            <a href="list" class="btn btn-primary"><i class="icon-white icon-list"></i> List Data</a>
            <a href="javascript:toggleTarget('#xls-info')" title="Show Form Filter" class="btn btn-small btn-success">
                <i class="icon-white icon-inbox"></i> Informasi
            </a>
        </div>
        <h1>Data Desktop Nilai Tanah</h1>
        <div class="panel hide alert alert-info" id="xls-info">
            <strong>
                <p>
                    File upload harus berformat XLS<br>
                    Urutan Kolom pada file adalah sebagai berikut<br>
                <ul>
                    <li>No</li>
                    <li>Nama Perumahan / Cluster / Area</li>
                    <li>Nama Jalan</li>
                    <li>Posisi Tanah</li>
                    <li>Kelurahan</li>
                    <li>Kecamatan</li>
                    <li>Kabupaten/Kota</li>
                    <li>Propinsi</li>
                    <li>Periode</li>
                    <li>Nilai Tanah per m<sup>2</sup></li>
                </ul>
                </p>
            </strong>
        </div>
    </div>
    <c:if test="${not empty msg}">
        <div class="alert alert-${css}">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <strong>${msg}</strong>
        </div>
    </c:if>
    <form action="upload?${_csrf.parameterName}=${_csrf.token}" method="post" enctype="multipart/form-data" class="form-horizontal">
        <div class="control-group">
            <label class="control-label" for="fileUpload">File <sup>XLS</sup></label>
            <div class="controls">
                <input type="file" name="fileUpload" id="fileUpload">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">&nbsp;</label>
            <div class="controls">
                <button type="submit" class="btn btn-success"><i class="icon-white icon-check"></i> Upload</button>
            </div>
        </div>
    </form>
</div>
<script src="${ctxPath}/js/bootstrap_2.3.2/js/jquery.js"></script>
<script src="${ctxPath}/js/bootstrap_2.3.2/js/bootstrap.min.js"></script>
<!--[if lt IE 9]>
<script src="${ctxPath}/js/html5shiv.js"></script>
<script src="${ctxPath}/js/respond.min.js"></script>
<![endif]-->
<script type="text/javascript" src="${ctxPath}/js/main-app.js"></script>
</body>
</html>