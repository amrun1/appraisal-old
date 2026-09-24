<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctxPath" value="${pageContext.request.contextPath}"/>
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
        <f:select path="faktorMarketCatatan" cssClass="input-xxlarge">
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

<script src="${ctxPath}/js/bootstrap_2.3.2/js/jquery.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $('input[name=faktorMarketability]').change(function () {
            if ($(this).val() == 'Tidak Marketable') {
                $('select[name=faktorMarketCatatan]').prop('disabled', false);
            } else {
                $('select[name=faktorMarketCatatan]').prop('disabled', true);
            }
        });

        // load on main thread
        $('input[name=faktorMarketability]').trigger('change');
    })
</script>