<div class="control-group span6">
    <label class="control-label" for="hargaTanah">Nilai Tanah</label>
    <div class="controls">
        <f:input path="hargaTanah" cssClass="input-medium" data-mask="currency"/>
        <f:errors path="hargaTanah" cssClass="help-block error has-error"/>
    </div>
</div>
<div class="control-group span6">
    <label class="control-label" for="hargaBangunan">Nilai Bangunan</label>
    <div class="controls">
        <f:input path="hargaBangunan" cssClass="input-medium" data-mask="currency"/>
        <f:errors path="hargaBangunan" cssClass="help-block error has-error"/>
    </div>
</div>
<div class="control-group span6">
    <label class="control-label" for="hargaBangunan">&nbsp;</label>
    <div class="controls">
        <f:checkbox path="hitungManual1" label="Hitung Manual"/>
    </div>
</div>
<div style="clear: both"></div>
<table width="100%" class="custom-ui">
    <tr>
        <td></td>
        <td>Uraian</td>
        <td>Nilai Sesuai Dokumen</td>
        <td>Nilai Sesuai Fisik</td>
        <td>Prosentase(%)</td>
    </tr>
    <tr>

        <td>I</td>
        <td>
            <f:select path="uraian1">
                <f:option value="tanah" label="Tanah"/>
                <f:option value="bangunan" label="Bangunan"/>
                <f:option value="unit" label="Per Unit"/>
                <f:option value="tidak" label="Tidak Diisi"/>
            </f:select>
        </td>
        <td>
            <div>
                <f:input path="nilaiDokumen1" cssClass="input-medium" data-mask="currency"/>
            </div>
            <f:radiobutton path="diberiNilaiDokumen1" value="1" label="Diberi Nilai"/>
            <f:radiobutton path="diberiNilaiDokumen1" value="0" label="Tidak Diberi Nilai"/>
        </td>
        <td>
            <div><f:input path="nilaiFisik1" cssClass="input-medium" data-mask="currency"/></div>
            <f:radiobutton path="diberiNilaiFisik1" value="1" label="Diberi Nilai"/>
            <f:radiobutton path="diberiNilaiFisik1" value="0" label="Tidak Diberi Nilai"/>
        </td>
        <td><f:input path="prosentase1" cssClass="input-mini" data-mask="numeric" maxlength="3"/></td>
    </tr>
    <tr>
        <td>&nbsp;</td>
    </tr>
    <tr>
        <td>II</td>
        <td>
            <f:select path="uraian2">
                <f:option value="tanah" label="Tanah"/>
                <f:option value="bangunan" label="Bangunan"/>
                <f:option value="unit" label="Per Unit"/>
                <f:option value="tidak" label="Tidak Diisi"/>
            </f:select>
        </td>
        <td>
            <div><f:input path="nilaiDokumen2" cssClass="input-medium" data-mask="currency"/></div>
            <f:radiobutton path="diberiNilaiDokumen2" value="1" label="Diberi Nilai"/>
            <f:radiobutton path="diberiNilaiDokumen2" value="0" label="Tidak Diberi Nilai"/>
        </td>
        <td>
            <div><f:input path="nilaiFisik2" cssClass="input-medium" data-mask="currency"/></div>
            <f:radiobutton path="diberiNilaiFisik2" value="1" label="Diberi Nilai"/>
            <f:radiobutton path="diberiNilaiFisik2" value="0" label="Tidak Diberi Nilai"/>
        </td>
        <td><f:input path="prosentase2" cssClass="input-mini" data-mask="numeric" maxlength="3"/></td>
    </tr>
    <tr>
        <td></td>
        <td>Total</td>
        <td><input type="text" class="input-medium" name="totalSesuaiDokumen" data-mask="currency"
                   readonly/></td>
        <td><input type="text" class="input-medium" name="totalSesuaiFisik" data-mask="currency"
                   readonly/></td>
    </tr>
    <tr>
        <td>&nbsp;</td>
    </tr>
    <tr>
        <td></td>
        <td>Nilai Likuidasi I</td>
        <td><input type="text" class="input-medium" name="likuidasiDokumen1" data-mask="currency"
                   readonly/></td>
        <td><input type="text" class="input-medium" name="likuidasiFisik1" data-mask="currency"
                   readonly/></td>
    </tr>
    <tr>
        <td></td>
        <td>Nilai Likuidasi II</td>
        <td><input type="text" class="input-medium" name="likuidasiDokumen2" data-mask="currency"
                   readonly/></td>
        <td><input type="text" class="input-medium" name="likuidasiFisik2" data-mask="currency"
                   readonly/></td>
    </tr>
    <tr>
        <td></td>
        <td>Total Likuidasi</td>
        <td><input type="text" class="input-medium" name="totalLikuidasiDokumen" data-mask="currency"
                   readonly/></td>
        <td><input type="text" class="input-medium" name="totalLikuidasiFisik" data-mask="currency"
                   readonly/></td>
    </tr>
    <tr>
        <td>&nbsp;</td>
    </tr>
    <tr>
        <td></td>
        <td>Nilai Teleransi I (20%)</td>
        <td><input type="text" class="input-medium" name="toleransi1" data-mask="currency" readonly/></td>
    </tr>
    <tr>
        <td></td>
        <td>Nilai Teleransi II (30%)</td>
        <td><input type="text" class="input-medium" name="toleransi2" data-mask="currency" readonly/></td>
    </tr>
</table>