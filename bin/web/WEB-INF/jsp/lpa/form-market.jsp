<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctxPath" value="${pageContext.request.contextPath}"/>
<c:set var="isEksternal" value="${fn:containsIgnoreCase(formBean.kategoriJenisAppraisal, 'eksternal')}"/>
<div class="control-group">
    <label class="control-label" for="faktorMarketability">Faktor Marketability</label>
    <div class="controls custom-ui">
        <f:radiobuttons path="faktorMarketability" items="${listFaktorMarket}" itemValue="label"
                        itemLabel="label"/>
        <f:errors path="faktorMarketability" cssClass="help-block"/>
    </div>
</div>
<div class="control-group">
    <label class="control-label" for="faktorMarketCatatan">&nbsp;</label>
    <div class="controls">
        <f:select id="faktor" path="faktorMarketCatatan" cssClass="input-xxlarge">
            <f:option value="">-- Select --</f:option>
            <f:options items="${listFaktorUnMarket}" itemValue="label" itemLabel="label"/>
        </f:select>
        <f:errors path="faktorMarketCatatan" cssClass="help-block"/>
    </div>
</div>
<div class="control-group" style="margin-top: 10px;">
    <label class="control-label" for="catatanPoint">Catatan yang Perlu Diperhatikan</label>
    <div class="controls">
        <f:textarea path="catatanPoint" rows="4" cssClass="input-xxlarge" maxlength="750"/>
        <f:errors path="catatanPoint" cssClass="help-block"/>
    </div>
</div>
<div class="control-group" style="margin-top: 10px; display: none;">
    <label class="control-label" for="positifPoint">Positive Point</label>
    <div class="controls">
        <f:textarea path="positifPoint" rows="4" cssClass="input-xxlarge"/>
        <f:errors path="positifPoint" cssClass="help-block"/>
    </div>
</div>
<div class="control-group" style="margin-top: 10px;">
    <label class="control-label" for="pembanding1">Data Pembanding 1</label>
    <div class="controls">
        <f:input path="pembanding1" cssClass="input-xxlarge"/>
        <f:errors path="pembanding1" cssClass="help-block"/>
    </div>
</div>
<div class="control-group">
    <label class="control-label" for="pembanding2">Data Pembanding 2</label>
    <div class="controls">
        <f:input path="pembanding2" cssClass="input-xxlarge"/>
        <f:errors path="pembanding2" cssClass="help-block"/>
    </div>
</div>
<div class="control-group">
    <label class="control-label" for="pembanding3">Data Pembanding 3</label>
    <div class="controls">
        <f:input path="pembanding3" cssClass="input-xxlarge"/>
        <f:errors path="pembanding3" cssClass="help-block"/>
    </div>
</div>

<c:if test="${isEksternal}">

    <div class="control-group" style="margin-top: 10px;">
        <label class="control-label" for="catatanPoint">Catatan Kewajaran Nilai</label>
        <div class="controls">
            <f:select path="catatanKewajaranNil" cssClass="input-xxlarge">
                <f:option value="">-- Select --</f:option>
                <f:options items="${listCatatanKewajaran}" itemValue="label" itemLabel="label"/>
            </f:select>
            <f:errors path="catatanKewajaranNil" cssClass="help-block"/>
        </div>
    </div>


    <div class="control-group" style="margin-top: 10px;">
        <label class="control-label" for="catatanPoint">Catatan Kesesuaian Laporan dengan dokumen dan Keterangan lainnya</label>
            <div class="controls">
                <f:select path="catatanKesesuaian" cssClass="input-xxlarge">
                    <f:option value="">-- Select --</f:option>
                    <f:options items="${listCatatanKesesuaian}" itemValue="label" itemLabel="label"/>
                </f:select>
                <f:errors path="catatanKesesuaian" cssClass="help-block"/>
            </div>
    </div>

</c:if>
<script src="${ctxPath}/js/bootstrap_2.3.2/js/jquery.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $('input[name=faktorMarketability]').change(function () {
            if ($('input[name=faktorMarketability]:checked').val() == 'Tidak Marketable') {
                $('select[name=faktorMarketCatatan]').prop('disabled', false);
            } else {
                $('select[name=faktorMarketCatatan]').prop('disabled', true);
                $('select[name=faktorMarketCatatan]').prop('selectedIndex', 0);
            }
        });

        // load on main thread
        $('input[name=faktorMarketability]').trigger('change');
    })
</script>