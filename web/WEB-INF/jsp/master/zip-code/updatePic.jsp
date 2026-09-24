<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
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
    <link href="${ctxPath}/js/datatables.css" rel="stylesheet">
    <link href="${ctxPath}/css/main-app.css" rel="stylesheet">

</head>
<body>
<div class="container">
    <div class="page-header">
        <h1>Update PIC Per Kota/Kecamatan</h1>
    </div>
    <form method="post" action="updatePic?${_csrf.parameterName}=${_csrf.token}" class="form-horizontal">
        <div class="control-group">
            <label class="control-label" for="province">Propinsi</label>
            <div class="controls">
                <select id="province" name="province">
                    <option value="">-- Select --</option>
                    <c:forEach items="${listPropinsi}" var="row">
                        <option value="${row}">${row}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="city">Kota / Kabupaten</label>
            <div class="controls">
                <select id="city" name="city">
                    <option value="">-- Select --</option>
                </select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="district">Kecamatan</label>
            <div class="controls">
                <select id="district" name="district">
                    <option value="">-- Select --</option>
                </select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="picName">Nama PIC</label>
            <div class="controls">
                <select id="picName" name="picName">
                    <option value="">-- Select --</option>
                    <c:forEach items="${listPic}" var="row">
                        <option value="${row}">${row}</option>
                    </c:forEach>
                </select>


            </div>
        </div>
        <div class="control-group">
            <label class="control-label">&nbsp;</label>
            <div class="controls">
                <button type="submit" class="btn btn-small btn-success"><i class="icon-white icon-ok"></i> Save</button>
                <button type="button" class="btn btn-small btn-warning" onclick="gotoUrl('list')"><i
                        class="icon-white icon-step-backward"></i> Back
                </button>
            </div>
        </div>
        <div class="alert alert-success">
            <strong>Kosongkan Kecamatan jika ingin update PIC per-kota terpilih</strong>
        </div>
    </form>
</div>
<script src="${ctxPath}/js/bootstrap_2.3.2/js/jquery.js"></script>
<script src="${ctxPath}/js/bootstrap_2.3.2/js/bootstrap.min.js"></script>
<!--[if lt IE 9]>
    <!--<script src="${ctxPath}/js/html5shiv.js"></script>-->
<!--<script src="${ctxPath}/js/respond.min.js"></script>-->
<![endif]-->
<script type="text/javascript" src="${ctxPath}/js/main-app.js"></script>
<script type="text/javascript">
    $(document).ready(function () {

        $.active = false;
        $('body').bind('click keypress', function() { $.active = true; });
        var logout = '${ctxPath}/logoutExpired';
        var heartBeat = '${ctxPath}/heartbeat';
        checkActivity(${maxInterval}, 60000, 0,logout,heartBeat);

        $('#province').change(function(){
            var propinsi = $(this).val();
            $.ajax({
                type : "POST",
                url : "<s:url value="find-kota"/>",
                dataType : "json",
                data : {
                    "propinsi" : propinsi,
                    "${_csrf.parameterName}": "${_csrf.token}"
                }
            }).success(function(data) {
                var oCity = $('#city');
                oCity.empty();
                oCity.append($('<option>', {value:"", text:"-- Select --"}));
                for(var i=0 ; i<data.length ; i++){
                    oCity.append($('<option>', {value:data[i], text:data[i]}));
                }
                var oDistrict = $('#district');
                oDistrict.empty();
                oDistrict.append($('<option>', {value:"", text:"-- Select --"}));
            });
        })
        $('#city').change(function(){
            var propinsi = $('#province').val();
            var kota = $(this).val();
            $.ajax({
                type : "POST",
                url : "<s:url value="find-kecamatan"/>",
                dataType : "json",
                data : {
                    "propinsi" : propinsi,
                    "kota" : kota,
                    "${_csrf.parameterName}": "${_csrf.token}"
                }
            }).success(function(data) {
                var oDistrict = $('#district');
                oDistrict.empty();
                oDistrict.append($('<option>', {value:"", text:"-- Select --"}));
                for(var i=0 ; i<data.length ; i++){
                    oDistrict.append($('<option>', {value:data[i], text:data[i]}));
                }
            });
        })
    });
</script>
</body>
</html>