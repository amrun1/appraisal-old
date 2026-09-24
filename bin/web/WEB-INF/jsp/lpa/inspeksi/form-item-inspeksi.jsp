<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.permata.appraisal2.model.PersonLevel" %>
<c:set var="taskPosition" value="<%= PersonLevel.APPRAISAL_VALUER %>"/>
<c:set var="showButton" value="${roleActive.level == taskPosition}"/>
<c:set var="ctxPath" value="${pageContext.request.contextPath}"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>
<div class="container">
    <f:form modelAttribute="formBean" action="save-item-inspeksi" id="form-item-inspeksi" class="form-horizontal" method="post">
        <f:hidden path="id"/>
        <f:hidden path="lpaInspeksiId"/>
        <f:hidden path="type" />
        <input type="hidden" name="jenisAktiva" value="${jenisAktiva}">
        <div class="control-group span6">
            <label class="control-label" for="name">${labelGoods}</label>
            <div class="controls">
                <f:input path="name"/>
                <f:errors path="name" cssClass="help-block error"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="totalDoc">Jumlah Sesuai Dokumen</label>
            <div class="controls">
                <f:input path="totalDoc" data-mask="number"/>
                <f:errors path="totalDoc" />
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="totalFisik">Jumlah Sesuai Fisik</label>
            <div class="controls">
                <f:input path="totalFisik" data-mask="number"/>
                <f:errors path="totalFisik" />
            </div>
        </div>
        <c:if test="${showButton}">
            <div class="control-group">
                <label class="control-label">&nbsp;</label>
                <div class="controls">
                        <button id="btn-submit-doc-tanah" type="submit" class="btn btn-success"><i class="icon-white icon-ok"></i> Save</button>
                </div>
            </div>
        </c:if>

    </f:form>
</div>

<script type="text/javascript" src="${ctxPath}/js/main-app.js">

</script>
<script type="text/javascript">
    $(document).ready(function () {
        $('#form-barang-jadi').find('input, textarea, select').prop('disabled', false);
    });
</script>
</body>
</html>
