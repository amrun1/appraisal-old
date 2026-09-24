<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<div class="control-group" style="margin-top: 10px;">
    <label class="control-label" for="listNegative">Negative List</label>
    <div class="controls">
        <f:checkboxes path="listNegative" items="${listNegative}" itemLabel="label" itemValue="value"/>
        <f:errors path="listNegative" cssClass="help-block"/>
    </div>
</div>
<div class="control-group" style="margin-top: 10px;">
    <label class="control-label" for="listPertimbangan">Pertimbangan Khusus</label>
    <div class="controls">
        <f:checkboxes path="listPertimbangan" items="${listPertimbangan}" itemLabel="label" itemValue="value"/>
        <f:errors path="listPertimbangan" cssClass="help-block"/>
    </div>
</div>