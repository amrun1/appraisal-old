<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="isEksternal" value="${fn:containsIgnoreCase(formBean.kategoriJenisAppraisal, 'eksternal')}"/>
<c:set var="isInspeksi" value="${fn:containsIgnoreCase(formBean.jenisObjectPenilain, 'Inspeksi Stock')}"/>
<c:set var="isStock" value="${fn:containsIgnoreCase(formBean.jenisObjectPenilain, 'Stock')}"/>
<c:set var="ctxPath" value="${pageContext.request.contextPath}"/>
<f:hidden path="kategoriJenisAppraisal"/>
<f:hidden path="kategoriObjectPenilaian"/>
<f:hidden path="commonId"/>
<f:hidden path="detailId"/>
<f:hidden path="idLpa"/>
<f:hidden path="id"/>
<f:hidden path="draft"/>
<f:hidden path="jenisAktiva"/>
<f:hidden path="jenisAppraisal"/>
<f:hidden path="surveyId"/>
<f:hidden path="create"/>
<div class="control-group span6">
    <label class="control-label" for="orderNumber">No. Order</label>
    <div class="controls">
        <f:input path="orderNumber" type="text" class="form-control" readonly="true"/>
    </div>
</div>
<div class="control-group span6">
    <label class="control-label" for="lpaNumber">Nomer LPA</label>
    <div class="controls">
        <c:if test="${isEksternal}">
            <f:input path="lpaNumber" type="text" class="form-control"/>
            <f:errors path="lpaNumber"/>
        </c:if>
        <c:if test="${!isEksternal}">
            <f:input path="lpaNumber" type="text" class="form-control" readonly="true"/>
            <f:errors path="lpaNumber"/>
        </c:if>
    </div>
</div>
<div class="control-group span6">
    <label class="control-label" for="contactPerson">CP yang ditemui</label>
    <div class="controls">
        <f:input path="contactPerson" type="text" class="form-control" readonly="true"/>
    </div>
</div>
<div class="control-group span6">
    <label class="control-label" for="namaDebitur">Nama Debitur</label>
    <div class="controls">
        <f:input path="namaDebitur" type="text" class="form-control" readonly="true"/>
    </div>
</div>
<div class="control-group span6">
    <label class="control-label" for="jenisObjectPenilain">Jenis Object Penilaian (Order)</label>
    <div class="controls">
        <f:input path="jenisObjectPenilain" type="text" class="form-control" readonly="true"/>
    </div>
</div>
<div class="control-group span6">
    <label class="control-label" for="jenisObjectPenilaiFisik">Jenis Object Penilaian (Fisik)</label>
    <div class="controls">
        <f:select path="idObjectPenilaiFisik">
            <f:option value="">-- Select --</f:option>
            <f:options items="${listJenisPenilaianFisik}" itemLabel="nama" itemValue="id"/>
        </f:select>
        <f:hidden path="jenisObjectPenilaiFisik"/>
        <f:errors path="jenisObjectPenilaiFisik" cssClass="help-block"/>
    </div>
</div>
<div class="control-group span6">
    <label class="control-label" for="branchId">Lokasi Cabang</label>
    <div class="controls">
        <f:select path="branchId">
            <f:option value="">-- Select --</f:option>
            <f:options items="${listAppraisalCabang}" itemLabel="label" itemValue="value"/>
        </f:select>
        <f:errors path="branchId" cssClass="help-block"/>
    </div>
</div>
<div class="control-group span6" style="margin-bottom: 3px;">
    <c:if test="${isInspeksiStock || isStock}">
        <label class="control-label" for="lokasiFisik">Alamat Tempat Penyimpanan</label>
    </c:if>
    <c:if test="${!isInspeksiStock && !isStock}">
        <label class="control-label" for="lokasiFisik">Lokasi Agunan (Fisik)</label>
    </c:if>
    <div class="controls">
        <f:textarea path="lokasiFisik" rows="3" cols="30" maxlength="255"></f:textarea>
        <f:errors path="lokasiFisik" cssClass="help-block"/>
    </div>
</div>
<div class="control-group span6">
    <label class="control-label" for="namaPerumahan">Nama Perumahan/Apartment</label>
    <div class="controls">
        <f:input path="namaPerumahan" readonly="true"/>
        <f:errors path="namaPerumahan" cssClass="help-block"/>
    </div>
</div>
<div class="control-group span6">
    <label class="control-label" for="namaCluster">Nama Cluster/Tower</label>
    <div class="controls">
        <f:input path="namaCluster" readonly="true"/>
        <f:errors path="namaCluster" cssClass="help-block"/>
    </div>
</div>
<div class="control-group span6">
    <label class="control-label" for="block">Blok/Gang/Lantai</label>
    <div class="controls">
        <f:input path="block" readonly="true"/>
        <f:errors path="block" cssClass="help-block"/>
    </div>
</div>
<div class="control-group span6">
    <label class="control-label" for="nomor">Nomor</label>
    <div class="controls">
        <f:input path="nomor" readonly="true"/>
        <f:errors path="nomor" cssClass="help-block"/>
    </div>
</div>
<div class="control-group span6">
    <label class="control-label" for="rt">RT</label>
    <div class="controls">
        <f:input path="rt" readonly="true"/>
        <f:errors path="rt" cssClass="help-block"/>
    </div>
</div>
<div class="control-group span6">
    <label class="control-label" for="rw">RW</label>
    <div class="controls">
        <f:input path="rw" readonly="true"/>
        <f:errors path="rw" cssClass="help-block"/>
    </div>
</div>
<div class="control-group span6 ${status.error ? 'help-block' : ''}">
    <label class="control-label" for="posisiLokasiAgunan">Posisi Lokasi Agunan</label>
    <div class="controls">
        <f:select path="posisiLokasiAgunan">
            <f:option value="">-- Select --</f:option>
            <f:options items="${listLokasiAktiva}" itemValue="label" itemLabel="label"/>
        </f:select>
        <f:errors path="posisiLokasiAgunan" cssClass="help-block"/>
    </div>
</div>
<div class="control-group span6">
    <label class="control-label" for="kodePos">Kode Pos</label>
    <div class="controls">
        <f:input path="kodePos" readonly="true"/>
        <f:errors path="kodePos" cssClass="help-block"/>
    </div>
</div>
<div class="control-group span6">
    <label class="control-label" for="propinsi">Propinsi</label>
    <div class="controls">
        <f:input path="propinsi" readonly="true"/>
        <f:errors path="propinsi" cssClass="help-block"/>
    </div>
</div>
<div class="control-group span6">
    <label class="control-label" for="kota">Kabupaten/Kotamadya</label>
    <div class="controls">
        <f:input path="kota" readonly="true"/>
        <f:errors path="kota" cssClass="help-block"/>
    </div>
</div>
<div class="control-group span6">
    <label class="control-label" for="kecamatan">Kecamatan</label>
    <div class="controls">
        <f:input path="kecamatan" readonly="true"/>
        <f:errors path="kecamatan" cssClass="help-block"/>
    </div>
</div>
<div class="control-group span6">
    <label class="control-label" for="kelurahan">Desa/Kelurahan</label>
    <div class="controls">
        <f:input path="kelurahan" readonly="true"/>
        <f:errors path="kelurahan" cssClass="help-block"/>
    </div>
</div>
<div class="control-group span6">
    <label class="control-label" for="statusJaminan">Status Jaminan</label>
    <div class="controls">
        <f:select path="statusJaminan" disabled="true">
            <f:option value="">-- Select --</f:option>
            <f:options items="${lvStatusJaminan}" itemValue="value" itemLabel="label"/>
        </f:select>
        <f:hidden path="statusJaminan"/>
        <f:errors path="statusJaminan" cssClass="help-block"/>
    </div>
</div>
<div class="control-group span6">
    <label class="control-label" for="kategoriSla">Kategori SLA Agunan</label>
    <div class="controls">
        <f:input path="kategoriSla" readonly="true"/>
        <f:errors path="kategoriSla" cssClass="help-block"/>
    </div>
</div>
<div class="control-group span6">
    <label class="control-label" for="sla">SLA</label>
    <div class="controls">
        <f:input path="sla" readonly="true"/>
        <f:errors path="sla" cssClass="help-block"/>
    </div>
</div>
<div class="control-group span6">
    <label class="control-label" for="tanggalOrder">Tanggal Order</label>
    <div class="controls">
        <f:input path="tanggalOrder" readonly="true"/>
        <f:errors path="tanggalOrder" cssClass="help-block"/>
    </div>
</div>
<div class="control-group span6">
    <label class="control-label" for="tanggalSurvey">Tanggal Survey</label>
    <div class="controls">
        <f:input path="tanggalSurvey" data-date-format="dd-mm-yyyy"
                 data-provide="datepicker"  class="tutup-otomatis" placeholder="DD-MM-YYYY" data-date-end-date="0d"/>
        <f:errors path="tanggalSurvey" cssClass="help-block"/>
    </div>
</div>
<c:if test="${isEksternal}">
    <div class="control-group span6">
        <label class="control-label" for="tanggalPenilaian">Tanggal Penilaian</label>
        <div class="controls">
            <f:input path="tanggalPenilaian" data-date-format="dd-mm-yyyy"
                     data-provide="datepicker"  class="tutup-otomatis" placeholder="DD-MM-YYYY" data-date-end-date="0d"/>
        </div>
    </div>
    <div class="control-group span6">
        <label class="control-label" for="tanggalOrderKeEksternal">Tanggal Order Ke Eksternal</label>
        <div class="controls">
            <f:input path="tanggalOrderKeEksternal" data-date-format="dd-mm-yyyy"
                     data-provide="datepicker"  class="tutup-otomatis" placeholder="DD-MM-YYYY" data-date-end-date="0d"/>
        </div>
    </div>
    <div class="control-group span6">
        <label class="control-label" for="tanggalTerimaDariEksternal">Tanggal Terima Dari Eksternal</label>
        <div class="controls">
            <f:input path="tanggalTerimaDariEksternal" data-date-format="dd-mm-yyyy"
                     data-provide="datepicker"  class="tutup-otomatis" placeholder="DD-MM-YYYY" data-date-end-date="0d"/>
        </div>
    </div>
</c:if>
<div class="control-group span6">
    <label class="control-label" for="ditujuke">Penilaian ditujukan ke</label>
    <div class="controls">
        <f:input path="ditujuke"/>
        <f:errors path="ditujuke" cssClass="help-block"/>
    </div>
</div>
<c:if test="${!isEksternal}">
<div class="control-group span6">
    <label class="control-label" for="ditinjau">Ditinjau Oleh</label>
    <div class="controls">
        <f:select path="ditinjau">
            <f:options items="${listSurveyor}" itemValue="label" itemLabel="label"/>
        </f:select>
        <f:errors path="ditinjau" cssClass="help-block"/>
    </div>
</div>
</c:if>
<c:if test="${isEksternal}">
    <div class="control-group span6">
        <label class="control-label" for="idKjpp">KJPP</label>
        <div class="controls">
            <f:select path="idKjpp">
                <f:option value="" label="-- Select --"/>
                <f:options items="${listKjpp}" itemValue="value" itemLabel="label"/>
            </f:select>
            <f:errors path="idKjpp" cssClass="help-block"/>
            <f:hidden path="ditinjau"/>
        </div>
    </div>
</c:if>
<div class="control-group span6">
    <label class="control-label" for="ditemui">Diantar/Ditemui Oleh</label>
    <div class="controls">
        <f:input path="ditemui"/>
        <f:errors path="ditemui" cssClass="help-block"/>
    </div>
</div>
<div class="control-group span6">
    <label class="control-label" for="catatanSurvey">Catatan Hasil Survey</label>
    <div class="controls">
        <f:input path="catatanSurvey"/>
        <f:errors path="catatanSurvey" cssClass="help-block"/>
    </div>
</div>
<div class="control-group span12">&nbsp;</div>
<div class="control-group span6">
    <label class="control-label" for="mataUang">Mata Uang</label>
    <div class="controls">
        <f:select path="mataUang">
            <f:option value="" label="-- Select --"/>
            <f:options items="${listMataUang}" itemValue="code" itemLabel="code"/>
        </f:select>
        <f:errors path="mataUang"/>
    </div>
</div>
<div class="control-group span6">
    <label class="control-label" for="tanggalRate">Tanggal Rate</label>
    <div class="controls">
        <f:input path="tanggalRate" data-date-format="dd-mm-yyyy"
                 data-provide="datepicker"  class="tutup-otomatis" placeholder="DD-MM-YYYY" data-date-end-date="0d"/>
        <f:errors path="tanggalRate"/>
    </div>
</div>
<div class="control-group span6">
    <label class="control-label" for="nilaiRate">Nilai Rate</label>
    <div class="controls">
        <f:input path="nilaiRate" data-mask="luas-tanah"/>
        <f:errors path="nilaiRate"/>
    </div>
</div>
<script src="${ctxPath}/js/bootstrap_2.3.2/js/jquery.js"></script>
<script type="text/javascript">
    $(document).ready(function () {

        $('#tanggalSurvey').datepicker({
            autoclose: true
        });


        $('select[name=idObjectPenilaiFisik]').change(function(){
            var value = $(this).val();
            var text = $(this).find('option:selected').text();
            if (text=="-- Select --"){
                $('#jenisObjectPenilaiFisik').val("");
            }else {
                $('#jenisObjectPenilaiFisik').val(text);
            }
            for(var i=0 ; i<listJenisPenilaian.length ; i++){
                var item = listJenisPenilaian[i];
                if(item.id == value){
                    $('#kategoriSla').val(item.kategori);
                    $('#sla').val(item.sla);
                    return;
                }
            }
            $('#kategoriSla').val("");
            $('#sla').val(0);
        })
        <c:if test="${isEksternal}">
            $('#idKjpp').change(function () {
                $('#ditinjau').val($(this).find('option:selected').text());
            })
        </c:if>
        var listJenisPenilaian = new Array();
        <c:forEach items="${listJenisPenilaianFisik}" var="row" varStatus="status">
            listJenisPenilaian[${status.index}] = {
                "id" : ${row.id},
                "kategori" : "${row.kategori}",
                "sla" : ${row.sla}
            };
        </c:forEach>


        $('select[name=idObjectPenilaiFisik]').trigger('change');

    });
</script>