<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="isEksternal" value="${fn:containsIgnoreCase(formBean.kategoriJenisAppraisal, 'eksternal')}"/>
<c:if test="${isEksternal}">
    <div class="control-group">
        <label class="control-label" for="opini">Opini Internal Atas Nilai Eksternal</label>
        <div class="controls">
            <f:select path="opini">
                <f:option value="" label="-- Select --"/>
                <f:options items="${listOpini}" itemValue="value" itemLabel="label"/>
            </f:select>
            <f:errors path="opini" cssClass="help-block"/>
        </div>
    </div>
    <div class="control-group" style="margin-top:10px;">
        <label class="control-label" for="deviasiDoc">Deviasi Nilai Dokumen</label>
        <div class="controls">
            <div class="input-append">
                <f:input path="deviasiDoc" cssClass="input-mini" data-mask="devisiasi"/>
                <button type="button" class="btn btn-info">%</button>
            </div>
            <f:errors path="deviasiDoc" cssClass="help-block"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label" for="deviasiFisik">Deviasi Nilai Fisik</label>
        <div class="controls">
            <div class="input-append">
                <f:input path="deviasiFisik" cssClass="input-mini" data-mask="devisiasi"/>
                <button type="button" class="btn btn-info">%</button>
            </div>
            <f:errors path="deviasiFisik" cssClass="help-block"/>
        </div>
    </div>
</c:if>