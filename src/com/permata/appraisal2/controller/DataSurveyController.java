package com.permata.appraisal2.controller;

import com.permata.appraisal2.common.AppConstans;
import com.permata.appraisal2.common.BaseController;
import com.permata.appraisal2.dto.*;
import com.permata.appraisal2.facade.*;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;

@Controller
@RequestMapping("survey")
@SessionAttributes({"person", "roleActive"})
public class DataSurveyController extends BaseController {

    private final String DIR_VIEW = "survey/";

    @Autowired
    private DataCommonFacade dataCommonFacade;

    @Autowired
    private PersonFacade personFacade;

    @Autowired
    private DataDetailFacade detailFacade;
    @Autowired
    private DataSurveyFacade facade;
    @Autowired
    private ParameterFacade parameterFacade;
    @Autowired
    private DocumentLpaTypeFacade documentLpaTypeFacade;
    @Autowired
    private JenisPenilaianFacade penilaianFacade;

    @Autowired
    private SurveyStockFacade surveyStockFacade;

    @Autowired
    private SurveyInspeksiFacade surveyInspeksiFacade;

    @RequestMapping(value = "show", method = RequestMethod.GET)
    public String show(@RequestParam(value = "detailId") Long detailId,
                       @ModelAttribute("person") PersonLoginDto person, Model model,
                       @RequestParam(value = "inquery", required = false) Boolean inquery) {

        //Bug Fix object person jika habis Claim maka isinya bukan personLogin
        String name = person.getName();
        DataDetailDto  d = detailFacade.get(detailId);
        DataCommonDto commonDto = dataCommonFacade.get(d.getDataCommonId());
        PersonDto personDto = personFacade.get(commonDto.getTaskList());
            if (personDto != null){
                name = personDto.getName();
            }

        SurveyCommonDto formBean = facade.getByDetail(detailId, name);

            if (formBean.getId()==null && inquery != null){
                formBean.setDitinjau(null);
            }

        model.addAttribute("formBean", formBean);
        model.addAttribute("inquery", inquery == null ? false : inquery);
        initReferenceUmum(model, formBean.getCategory(), formBean.getActiva());
        if (formBean.getId() != null) {
            model.addAttribute("listImage", facade.findImage(formBean.getId()));
        }
        return DIR_VIEW + "data-umum";
    }



    @RequestMapping(value = "show-stock", method = RequestMethod.GET)
    public String showStock(@RequestParam(value = "detailId") Long detailId,
                       @ModelAttribute("person") PersonLoginDto person, Model model,
                       @RequestParam(value = "inquery", required = false) Boolean inquery) {
        SurveyStockDto formBean = surveyStockFacade.getByDetail(detailId);
        initCreateSurveyStock(model);
        model.addAttribute("formBean",formBean);
        return DIR_VIEW + "data-stock";
    }

    @RequestMapping(value = "show-inspeksi", method = RequestMethod.GET)
    public String showInspeksi(@RequestParam(value = "detailId") Long detailId,
                            @ModelAttribute("person") PersonLoginDto person, Model model,
                            @RequestParam(value = "inquery", required = false) Boolean inquery) {
        SurveyInspeksiDto formBean = surveyInspeksiFacade.getByDetail(detailId);
        initCreateSurveyInspeksi(model);
        model.addAttribute("formBean",formBean);
        return DIR_VIEW + "data-inspeksi";
    }


    @RequestMapping(value = "save-survey-stock", method = RequestMethod.POST)
    public String saveDataCommon(@ModelAttribute("formBean")  SurveyStockDto dto, BindingResult result,
                                 @ModelAttribute("person") PersonLoginDto person, Model model) {
        initCreateSurveyStock(model);
        boolean edit = dto.getId() != null;
        dto = surveyStockFacade.save(dto);
        if (edit) {
            model.addAttribute("msg", "Data updated successfully!");
        } else {
            model.addAttribute("msg", "Data Saved successfully!");
        }
        model.addAttribute("formBean", dto);
        return DIR_VIEW + "data-stock";
    }

    @RequestMapping(value = "save-survey-inspeksi", method = RequestMethod.POST)
    public String saveDataCommonInspeksi(@ModelAttribute("formBean") SurveyInspeksiDto dto, BindingResult result,
                                         @ModelAttribute("person") PersonLoginDto person, Model model) {
        initCreateSurveyInspeksi(model);
        boolean edit = dto.getId() != null;
        dto = surveyInspeksiFacade.save(dto);
        if (edit) {
            model.addAttribute("msg", "Data updated successfully!");
        } else {
            model.addAttribute("msg", "Data Saved successfully!");
        }
        model.addAttribute("formBean", dto);
        return DIR_VIEW + "data-inspeksi";
    }



    private void initReferenceUmum(Model model, String category, String aktiva) {
//        String jenisObjectCode = AppConstans.GroupParameter.JENIS_OBJECT_PENILAIAN;
        String posisiAktivaCode = AppConstans.GroupParameter.POSISI_LOKASI_AKTIVA;
        String digunakanSebagaiCode = AppConstans.GroupParameter.DIGUNAKAN_SEBAGAI;
        String dihuniOlehCode = AppConstans.GroupParameter.DIHUNI_OLEH;

        if (category != null) {
            if (category.equalsIgnoreCase(AppConstans.JenisKategoriCode.KENDARAAN)) {
                // jenisObjectCode = AppConstans.GroupParameter.JENIS_OBJECT_PENILAIAN_KENDARAAN;
                // posisiAktivaCode = AppConstans.GroupParameter.POSISI_LOKASI_AKTIVA;
                digunakanSebagaiCode = AppConstans.GroupParameter.DIGUNAKAN_SEBAGAI_KENDARAAN;
                // dihuniOlehCode = AppConstans.GroupParameter.DIHUNI_OLEH;
            }
        }
        // model.addAttribute("listJenisObject", parameterFacade.findLvByGroup(jenisObjectCode));
        model.addAttribute("listJenisObject", penilaianFacade.listByKategoriAktiva(category));
        model.addAttribute("listPosisiLokasi", parameterFacade.findLvByGroup(posisiAktivaCode));
        model.addAttribute("listDigunakanSebagai", parameterFacade.findLvByGroup(digunakanSebagaiCode));
        model.addAttribute("listDihuniOleh", parameterFacade.findLvByGroup(dihuniOlehCode));
    }

    @RequestMapping(value = "data-image", method = RequestMethod.GET)
    public String showImage(@RequestParam(value = "commonId") Long commonId, Model model,
                            @RequestParam(value = "id", required = false) Long id,
                            @RequestParam(value = "detailId") Long detailId,
                            @RequestParam(value = "appraisalId", required = false) Long appraisalId) {
        DataDetailDto dataDetail = detailFacade.get(detailId);
        SurveyImageDto formBean = new SurveyImageDto(commonId);
        if (id != null) {
            formBean = facade.getImage(id);
        }
        formBean.setDetailId(detailId);
        model.addAttribute("formBean", formBean);
        initReferenceImage(model, dataDetail.getAssetTypeId(), appraisalId);
        return DIR_VIEW + "data-image";
    }

    private void initReferenceImage(Model model, Long activaId, Long appraisalId) {
        model.addAttribute("listCategory", documentLpaTypeFacade.findLvForSurvey(activaId, appraisalId));
    }


    private void initCreateSurveyStock(Model model){

        model.addAttribute("listPenempatanBarang", parameterFacade.findLvByGroup(AppConstans.GroupParameter.PENEMPATAN_BARANG));
        model.addAttribute("listKemasanBarang", parameterFacade.findLvByGroup(AppConstans.GroupParameter.KEMASAN_BARANG));
        model.addAttribute("listKondisiBarang", parameterFacade.findLvByGroup(AppConstans.GroupParameter.KONDISI_BARANG));
        model.addAttribute("listMetodePemeriksaan", parameterFacade.findLvByGroup(AppConstans.GroupParameter.METODE_PEMERIKSAAN));
        model.addAttribute("listJumlahPersediaan", parameterFacade.findLvByGroup(AppConstans.GroupParameter.JUMLAH_PERSEDIAAN));
        model.addAttribute("listKondisiPenyimpanan", parameterFacade.findLvByGroup(AppConstans.GroupParameter.KONDISI_PENYIMPANAN));
        model.addAttribute("listLingkunganPenyimpanan", parameterFacade.findLvByGroup(AppConstans.GroupParameter.LINGKUNGAN_PENYIMPANAN));
        model.addAttribute("listPeralatanGudang", parameterFacade.findLvByGroup(AppConstans.GroupParameter.PERALATAN_GUDANG));

    }

    private void initCreateSurveyInspeksi(Model model){

        model.addAttribute("listTempatPenyimpanan", parameterFacade.findLvByGroup(AppConstans.GroupParameter.INSPEKSI_JENIS_TEMPAT_SIMPAN));
        model.addAttribute("listInfoAlamatTempat", parameterFacade.findLvByGroup(AppConstans.GroupParameter.INSPEKSI_INFO_ALAMAT_TEMPAT));
        model.addAttribute("listStatusTempat", parameterFacade.findLvByGroup(AppConstans.GroupParameter.INSPEKSI_STATUS_TEMPAT_SIMPAN));
        model.addAttribute("listKondisiTempat", parameterFacade.findLvByGroup(AppConstans.GroupParameter.INSPEKSI_KODISI_TEMPAT_SIMPAN));
        model.addAttribute("listAktifitasKerja", parameterFacade.findLvByGroup(AppConstans.GroupParameter.INSPEKSI_AKTIFITAS_KERJA));
        model.addAttribute("listPeralatanGudang", parameterFacade.findLvByGroup(AppConstans.GroupParameter.INSPEKSI_PERALATAN_GUDANG));
        model.addAttribute("listAlatPemadam", parameterFacade.findLvByGroup(AppConstans.GroupParameter.INSPEKSI_ALAT_PEMADAM));
        model.addAttribute("listKeamanan", parameterFacade.findLvByGroup(AppConstans.GroupParameter.INSPEKSI_KEAMANAN));
        model.addAttribute("listKondisiJalan", parameterFacade.findLvByGroup(AppConstans.GroupParameter.INSPEKSI_KONDISI_JALAN));
        model.addAttribute("listLingkunganSekitar", parameterFacade.findLvByGroup(AppConstans.GroupParameter.INSPEKSI_LINGKUNGAN_SEKITAR));
        model.addAttribute("listMetodePeriksa", parameterFacade.findLvByGroup(AppConstans.GroupParameter.INSPEKSI_METODE_PERIKSA));
        model.addAttribute("listTempatPersediaan", parameterFacade.findLvByGroup(AppConstans.GroupParameter.INSPEKSI_PENEMPATAN_PERSEDIAAN));
        model.addAttribute("listSusunanTempat", parameterFacade.findLvByGroup(AppConstans.GroupParameter.INSPEKSI_SUSUSNAN_PENEMPATAN));
        model.addAttribute("listPacking", parameterFacade.findLvByGroup(AppConstans.GroupParameter.INSPEKSI_PACKING));

    }


    @RequestMapping(value = "save-image", method = RequestMethod.POST)
    public String saveDataImage(@ModelAttribute("formBean") @Valid SurveyImageDto dto, BindingResult result,
                                final RedirectAttributes redirectAttributes,
                                @ModelAttribute("person") PersonLoginDto person) {
        logger.debug("DTO : " + dto);
        // validator
        if (result.hasErrors()) {
            return DIR_VIEW + "data-image";
        }

        boolean edit = dto.getId() != null;
        // save to db
        if(dataCommonFacade.checkUserTasklist(person.getUserId(), null, dto.getCommonId())) {
            facade.saveImage(dto);
            if (edit) {
                redirectAttributes.addFlashAttribute("msg", "Data updated successfully!");
            } else {
                redirectAttributes.addFlashAttribute("msg", "Data added successfully!");
            }
            return "redirect:show?detailId=" + dto.getDetailId();
        }else{
            return DIR_VIEW + "error-image-auth";
        }
    }

    @RequestMapping(value = "delete-image", method = RequestMethod.GET)
    public String deleteImage(@RequestParam(value = "id") Long id,
                              @RequestParam(value = "detailId") Long detailId,
                              @RequestParam(value = "commonId") Long commonId,
                              final RedirectAttributes redirectAttributes,
                              @ModelAttribute("person") PersonLoginDto person) {
        try {
            if(dataCommonFacade.checkUserTasklist(person.getUserId(), null, commonId)) {
                facade.deleteImage(id);
                redirectAttributes.addFlashAttribute("msg", "Delete Data success");
            }else{
                return DIR_VIEW + "error-image-auth";
            }
        } catch (Exception e) {
            // do nothing
        }
        return "redirect:show?detailId=" + detailId;
    }

    @RequestMapping(value = "preview", method = RequestMethod.GET)
    public String download(@RequestParam("id") Long id, HttpServletResponse response) {
        SurveyImageDto dto = facade.getImage(id);
        OutputStream out = null;
        try {
            response.setHeader("Content-Disposition", "inline;filename=\"" + dto.getNamaDocumentLpa() + ".jpg\"");
            out = response.getOutputStream();
            response.setContentType(dto.getContentType());
            IOUtils.copy(new ByteArrayInputStream(dto.getImage()), out);
            out.flush();
            out.close();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            if(out!=null) {
                IOUtils.closeQuietly(out);
            }
        }
        return null;
    }

    @RequestMapping(value = "save-umum", method = RequestMethod.POST)
    public String saveDataCommon(@ModelAttribute("formBean") @Valid SurveyCommonDto dto, BindingResult result,
                                 @ModelAttribute("person") PersonLoginDto person, Model model) {
        initReferenceUmum(model, dto.getCategory(), dto.getActiva());
        if (result.hasErrors()) {
            return DIR_VIEW + "data-umum";
        }
        boolean edit = dto.getId() != null;
        try {
            dto = facade.saveDataCommon(dto, person);
        } catch (Exception e) {
            model.addAttribute("formBean", dto);
            model.addAttribute("msg", e.getMessage());
            if (dto.getId() != null) {
                model.addAttribute("listImage", facade.findImage(dto.getId()));
            }
            return DIR_VIEW + "data-umum";
        }

        model.addAttribute("formBean", dto);
        if (edit) {
            model.addAttribute("msg", "Data updated successfully!");
        } else {
            model.addAttribute("msg", "Data added successfully!");
        }
        if (dto.getId() != null) {
            model.addAttribute("listImage", facade.findImage(dto.getId()));
        }
        return DIR_VIEW + "data-umum";
    }

    private void initReferenceTanah(Model model) {
        model.addAttribute("listBentukTanah", parameterFacade.findLvByGroup(AppConstans.GroupParameter.BENTUK_TANAH));
        model.addAttribute("listKonturTanah", parameterFacade.findLvByGroup(AppConstans.GroupParameter.KONTUR_TANAH));
        model.addAttribute("listKetinggianTanah", parameterFacade.findLvByGroup(AppConstans.GroupParameter.KETINGGIAN_TANAH));
        model.addAttribute("listBatasTanah", parameterFacade.findLvByGroup(AppConstans.GroupParameter.SESUAI_BATAS_TANAH));
        model.addAttribute("listPosisiTanah", parameterFacade.findLvByGroup(AppConstans.GroupParameter.POSISI_TANAH));
        model.addAttribute("listKondisiTanah", parameterFacade.findLvByGroup(AppConstans.GroupParameter.KONDISI_TANAH));
        model.addAttribute("listJenisTanah", parameterFacade.findLvByGroup(AppConstans.GroupParameter.JENIS_TANAH));
        model.addAttribute("listPeruntukanTanah", parameterFacade.findLvByGroup(AppConstans.GroupParameter.PERUNTUKAN_TANAH));
    }

    @RequestMapping(value = "show-tanah", method = RequestMethod.GET)
    public String showTanah(@RequestParam(value = "detailId") Long detailId,
                            @ModelAttribute("person") PersonLoginDto person, Model model,
                            @RequestParam(value = "inquery", required = false) Boolean inquery) {
        model.addAttribute("dataList", facade.findTanah(new SurveyTanahDto(detailId)));
        model.addAttribute("detailId", detailId);
        model.addAttribute("inquery", inquery == null ? false : inquery);
        return DIR_VIEW + "list-tanah";
    }

    @RequestMapping(value = "show-barang", method = RequestMethod.GET)
    public String showBarang(@RequestParam(value = "detailId") Long detailId, @RequestParam(value = "typeGoods") String typeGoods,
                            @ModelAttribute("person") PersonLoginDto person, Model model,
                            @RequestParam(value = "inquery", required = false) Boolean inquery) {
        SurveyInspeksiDto surveyInspeksi = surveyInspeksiFacade.getByDetail(detailId);
        if(surveyInspeksi.getId()!=null) {
            model.addAttribute("dataList", facade.findBarang(new SurveyItemInspeksiDto(surveyInspeksi.getId()), typeGoods));
        }else {
            model.addAttribute("dataList", null);
        }
        model.addAttribute("detailId", detailId);
        model.addAttribute("inquery", inquery == null ? false : inquery);
        String url;
        if(typeGoods.equalsIgnoreCase("Goods")) {
            url = "list-barang-jadi";
        } else if(typeGoods.equalsIgnoreCase("Half_Goods")) {
            url = "list-barang-setengah-jadi";
        } else {
            url = "list-bahan-baku";
        }
        return DIR_VIEW + url;
    }

    @RequestMapping(value = "form-barang", method = RequestMethod.GET)
    public String formBarang(@RequestParam(value = "id", required = false) Long id,
                            @RequestParam(value = "detailId") Long detailId,
                             @RequestParam(value = "typeGoods") String typeGoods,
                            @ModelAttribute("person") PersonLoginDto person, Model model) {
        SurveyInspeksiDto surveyInspeksi = surveyInspeksiFacade.getByDetail(detailId);
        if(surveyInspeksi.getId()==null){
            model.addAttribute("msg", "Data Survey Inspeksi Harus Dibuat terlebih dahulu!");
            model.addAttribute("dataList", null);
            model.addAttribute("detailId", detailId);
            model.addAttribute("inquery", null);
            return DIR_VIEW + "list-barang-jadi";
        }
        SurveyItemInspeksiDto formBean =new SurveyItemInspeksiDto(surveyInspeksi.getId());
        if (id == null) {
            formBean.setType(typeGoods);
            model.addAttribute("formBean", formBean);
            model.addAttribute("surveyInspeksiId", surveyInspeksi.getId());
        } else {
            formBean =facade.getBarang(id);
            model.addAttribute("formBean", formBean);
        }
        model.addAttribute("detailId", detailId);
        model.addAttribute("labelGoods", typeGoods.equalsIgnoreCase("Goods")?"Barang Jadi":typeGoods.equalsIgnoreCase("Half_Goods")?"Barang Setengah Jadi" : "Bahan Baku");
//        initReferenceTanah(model);
        return DIR_VIEW + "data-barang-jadi";
    }

    @RequestMapping(value = "form-tanah", method = RequestMethod.GET)
    public String formTanah(@RequestParam(value = "id", required = false) Long id,
                            @RequestParam(value = "detailId") Long detailId,
                            @ModelAttribute("person") PersonLoginDto person, Model model) {
        if (id == null) {
            model.addAttribute("formBean", new SurveyTanahDto(detailId));
        } else {
            model.addAttribute("formBean", facade.getTanah(id));
        }
        initReferenceTanah(model);
        return DIR_VIEW + "data-tanah";
    }

    @RequestMapping(value = "save-barang", method = RequestMethod.POST)
    public String saveBarang(@ModelAttribute("formBean") SurveyItemInspeksiDto dto, BindingResult result,
                             @ModelAttribute("person") PersonLoginDto person, Model model,
                             final RedirectAttributes redirectAttributes) {
        logger.debug("DTO : " + dto);

        boolean edit = dto.getId() != null;
        dto = facade.saveDataItemInspeksi(dto);
        SurveyInspeksiDto dtoSurvey = new SurveyInspeksiDto();
        dtoSurvey = surveyInspeksiFacade.get(dto.getSurveyInspeksiId());
        if(dto.getType().equalsIgnoreCase("Goods")) {
            Integer subTotalGoods = Integer.parseInt(dtoSurvey.getSubTotalGoods() != null ? dtoSurvey.getSubTotalGoods() : "0") + Integer.parseInt(dto.getTotalDoc());
            Integer subTotalGoodsFisik = Integer.parseInt(dtoSurvey.getSubTotalGoodsFisik() != null ? dtoSurvey.getSubTotalGoodsFisik() : "0") + Integer.parseInt(dto.getTotalFisik());
            dtoSurvey.setSubTotalGoods(String.valueOf(subTotalGoods));
            dtoSurvey.setSubTotalGoodsFisik(String.valueOf(subTotalGoodsFisik));
        }else if(dto.getType().equalsIgnoreCase("Half_Goods")){
            Integer subTotalHalfGoods = Integer.parseInt(dtoSurvey.getSubTotalHalfGoods() != null ? dtoSurvey.getSubTotalHalfGoods() : "0") + Integer.parseInt(dto.getTotalDoc());
            Integer subTotalHalfGoodsFisik = Integer.parseInt(dtoSurvey.getSubTotalHalfGoodsFisik() != null ? dtoSurvey.getSubTotalHalfGoodsFisik() : "0") + Integer.parseInt(dto.getTotalFisik());
            dtoSurvey.setSubTotalHalfGoods(String.valueOf(subTotalHalfGoods));
            dtoSurvey.setSubTotalHalfGoodsFisik(String.valueOf(subTotalHalfGoodsFisik));
        }else{
            Integer subTotalRawMaterial = Integer.parseInt(dtoSurvey.getSubTotalRawMaterial() != null ? dtoSurvey.getSubTotalRawMaterial() : "0") + Integer.parseInt(dto.getTotalDoc());
            Integer subTotalRawMaterialFisik = Integer.parseInt(dtoSurvey.getSubTotalRawMaterialFisik() != null ? dtoSurvey.getSubTotalRawMaterialFisik() : "0") + Integer.parseInt(dto.getTotalFisik());
            dtoSurvey.setSubTotalRawMaterial(String.valueOf(subTotalRawMaterial));
            dtoSurvey.setSubTotalRawMaterialFisik(String.valueOf(subTotalRawMaterialFisik));
        }
        dtoSurvey = surveyInspeksiFacade.save(dtoSurvey);
        if (edit) {
            redirectAttributes.addFlashAttribute("flashMessage", "Data updated successfully!");
        } else {
            redirectAttributes.addFlashAttribute("flashMessage", "Data added successfully!");
        }
        SurveyInspeksiDto surveyInspeksi = surveyInspeksiFacade.get(dto.getSurveyInspeksiId());
        return "redirect:show-barang?detailId=" + surveyInspeksi.getIdDetail()+"&typeGoods="+dto.getType();
    }

    @RequestMapping(value = "delete-barang", method = RequestMethod.GET)
    public String deleteBarang(@RequestParam(value = "id") Long id,
                              @RequestParam(value = "detailId") Long detailId,@RequestParam(value = "typeGoods") String typeGoods,
                              final RedirectAttributes redirectAttributes) {
        try {
            SurveyItemInspeksiDto dto =new SurveyItemInspeksiDto();
            dto = facade.getBarang(id);
            SurveyInspeksiDto dtoSurvey = new SurveyInspeksiDto();
            dtoSurvey = surveyInspeksiFacade.get(dto.getSurveyInspeksiId());

            if(dto.getType().equalsIgnoreCase("Goods")) {
                Integer subTotalGoods = Integer.parseInt(dtoSurvey.getSubTotalGoods()!=null?dtoSurvey.getSubTotalGoods():"0")- Integer.parseInt(dto.getTotalDoc());
                Integer subTotalGoodsFisik = Integer.parseInt(dtoSurvey.getSubTotalGoodsFisik()!=null?dtoSurvey.getSubTotalGoodsFisik():"0")- Integer.parseInt(dto.getTotalFisik());
                dtoSurvey.setSubTotalGoods(String.valueOf(subTotalGoods<0?0:subTotalGoods));
                dtoSurvey.setSubTotalGoodsFisik(String.valueOf(subTotalGoodsFisik<0?0:subTotalGoodsFisik));
            }else if(dto.getType().equalsIgnoreCase("Half_Goods")){
                Integer subTotalHalfGoods = Integer.parseInt(dtoSurvey.getSubTotalHalfGoods()!=null?dtoSurvey.getSubTotalHalfGoods():"0")- Integer.parseInt(dto.getTotalDoc());
                Integer subTotalHalfGoodsFisik = Integer.parseInt(dtoSurvey.getSubTotalHalfGoodsFisik()!=null?dtoSurvey.getSubTotalHalfGoodsFisik():"0")- Integer.parseInt(dto.getTotalFisik());
                dtoSurvey.setSubTotalHalfGoods(String.valueOf(subTotalHalfGoods<0?0:subTotalHalfGoods));
                dtoSurvey.setSubTotalHalfGoodsFisik(String.valueOf(subTotalHalfGoodsFisik<0?0:subTotalHalfGoodsFisik));
            }else{
                Integer subTotalRawMaterial = Integer.parseInt(dtoSurvey.getSubTotalRawMaterial()!=null?dtoSurvey.getSubTotalRawMaterial():"0")- Integer.parseInt(dto.getTotalDoc());
                Integer subTotalRawMaterialFisik = Integer.parseInt(dtoSurvey.getSubTotalRawMaterialFisik()!=null?dtoSurvey.getSubTotalRawMaterialFisik():"0")- Integer.parseInt(dto.getTotalFisik());
                dtoSurvey.setSubTotalRawMaterial(String.valueOf(subTotalRawMaterial<0?0:subTotalRawMaterial));
                dtoSurvey.setSubTotalRawMaterialFisik(String.valueOf(subTotalRawMaterial<0?0:subTotalRawMaterial));
            }
            dtoSurvey = surveyInspeksiFacade.save(dtoSurvey);

            facade.deleteBarang(id);
            redirectAttributes.addFlashAttribute("flashMessage", "Delete Data success");
        } catch (Exception e) {
            // do nothing
        }
        return "redirect:show-barang?detailId=" + detailId+"&typeGoods="+typeGoods;
    }

    @RequestMapping(value = "delete-tanah", method = RequestMethod.GET)
    public String deleteTanah(@RequestParam(value = "id") Long id,
                              @RequestParam(value = "detailId") Long detailId,
                              final RedirectAttributes redirectAttributes) {
        try {
            facade.deleteTanah(id);
            redirectAttributes.addFlashAttribute("flashMessage", "Delete Data success");
        } catch (Exception e) {
            // do nothing
        }
        return "redirect:show-tanah?detailId=" + detailId;
    }

    @RequestMapping(value = "save-tanah", method = RequestMethod.POST)
    public String saveDataTanah(@ModelAttribute("formBean") @Valid SurveyTanahDto dto, BindingResult result,
                                @ModelAttribute("person") PersonLoginDto person, Model model,
                                final RedirectAttributes redirectAttributes) {
        logger.debug("DTO : " + dto);
        initReferenceTanah(model);
        if (result.hasErrors()) {
            return DIR_VIEW + "data-tanah";
        }
        boolean edit = dto.getId() != null;
        dto = facade.saveDataTanah(dto);
        if (edit) {
            redirectAttributes.addFlashAttribute("flashMessage", "Data updated successfully!");
        } else {
            redirectAttributes.addFlashAttribute("flashMessage", "Data added successfully!");
        }
        return "redirect:show-tanah?detailId=" + dto.getDetailId();
    }

    @RequestMapping(value = "show-bangunan", method = RequestMethod.GET)
    public String showBangunan(@RequestParam(value = "detailId") Long detailId,
                               @ModelAttribute("person") PersonLoginDto person, Model model,
                               @RequestParam(value = "inquery", required = false) Boolean inquery) {
        model.addAttribute("dataList", facade.findBangunan(new SurveyBangunanDto(detailId)));
        model.addAttribute("detailId", detailId);
        model.addAttribute("inquery", inquery == null ? false : inquery);
        return DIR_VIEW + "list-bangunan";
    }

    @RequestMapping(value = "form-bangunan", method = RequestMethod.GET)
    public String formBangunan(@RequestParam(value = "id", required = false) Long id,
                               @RequestParam(value = "detailId") Long detailId,
                               @ModelAttribute("person") PersonLoginDto person, Model model) {
        if (id == null) {
            model.addAttribute("formBean", SurveyBangunanDto.create(detailId));
        } else {
            model.addAttribute("formBean", facade.getBangunan(id));
        }
        initReferenceBangunan(model);
        return DIR_VIEW + "data-bangunan";
    }

    private void initReferenceBangunan(Model model) {
        model.addAttribute("listKelasBangunan", parameterFacade.findLvByGroup(AppConstans.GroupParameter.KELAS_BANGUNAN));
        model.addAttribute("listPeruntukanBangunan", parameterFacade.findLvByGroup(AppConstans.GroupParameter.PERUNTUKAN_BANGUNAN));
        model.addAttribute("listJenisBangunan", parameterFacade.findLvByGroup(AppConstans.GroupParameter.JENIS_BANGUNAN));
    }

    private void initReferenceKendaraan(Model model) {
        model.addAttribute("listPenggunaan", parameterFacade.findLvByGroup(AppConstans.GroupParameter.PENGGUNAAN_KENDARAAN));
    }

    @RequestMapping(value = "delete-bangunan", method = RequestMethod.GET)
    public String deleteBangunan(@RequestParam(value = "id") Long id,
                                 @RequestParam(value = "detailId") Long detailId,
                                 final RedirectAttributes redirectAttributes) {
        try {
            facade.deleteBangunan(id);
            redirectAttributes.addFlashAttribute("flashMessage", "Delete Data success");
        } catch (Exception e) {
        }
        return "redirect:show-bangunan?detailId=" + detailId;
    }

    @RequestMapping(value = "save-bangunan", method = RequestMethod.POST)
    public String saveDataBangunan(@ModelAttribute("formBean") @Valid SurveyBangunanDto dto, BindingResult result,
                                   @ModelAttribute("person") PersonLoginDto person, Model model,
                                   final RedirectAttributes redirectAttributes) {
        logger.debug("DTO : " + dto);
        initReferenceBangunan(model);
        if (result.hasErrors()) {
            return DIR_VIEW + "data-bangunan";
        }
        boolean edit = dto.getId() != null;
        dto = facade.saveDataBangunan(dto);
        if (edit) {
            redirectAttributes.addFlashAttribute("flashMessage", "Data updated successfully!");
        } else {
            redirectAttributes.addFlashAttribute("flashMessage", "Data added successfully!");
        }
        return "redirect:show-bangunan?detailId=" + dto.getDetailId();
    }

    @RequestMapping(value = "show-lingkungan", method = RequestMethod.GET)
    public String showLingkungan(@RequestParam(value = "detailId") Long detailId,
                                 @ModelAttribute("person") PersonLoginDto person, Model model,
                                 @RequestParam(value = "inquery", required = false) Boolean inquery) {
        model.addAttribute("formBean", facade.getLingkunganByDetail(detailId));
        initReferenceLingkungan(model);
        model.addAttribute("inquery", inquery == null ? false : inquery);
        return DIR_VIEW + "data-lingkungan";
    }

    private void initReferenceLingkungan(Model model) {
        model.addAttribute("listKepadatanPenduduk", parameterFacade.findLvByGroup(AppConstans.GroupParameter.KEPADATAN_PENDUDUK));
        model.addAttribute("listTingkatKemacetan", parameterFacade.findLvByGroup(AppConstans.GroupParameter.TINGKAT_KEMACETAN));
        model.addAttribute("listLingkunganSekitar", parameterFacade.findLvByGroup(AppConstans.GroupParameter.LINGKUNGAN_SEKITAR));
    }

    @RequestMapping(value = "save-lingkungan", method = RequestMethod.POST)
    public String saveDataLingkungan(@ModelAttribute("formBean") @Valid SurveyLingkunganDto dto, BindingResult result,
                                     Model model) {
        logger.debug("DTO : " + dto);
        initReferenceLingkungan(model);
        if (result.hasErrors()) {
            return DIR_VIEW + "data-lingkungan";
        }
        boolean edit = dto.getId() != null;
        dto = facade.saveDataLingkungan(dto);
        model.addAttribute("formBean", dto);
        if (edit) {
            model.addAttribute("msg", "Data updated successfully!");
        } else {
            model.addAttribute("msg", "Data added successfully!");
        }
        return DIR_VIEW + "data-lingkungan";
    }

    @RequestMapping(value = "show-pembanding", method = RequestMethod.GET)
    public String showPembanding(@RequestParam(value = "detailId") Long detailId, Model model,
                                 @RequestParam(value = "inquery", required = false) Boolean inquery) {
        model.addAttribute("dataList", facade.findPembanding(new SurveyPembandingDto(detailId)));
        model.addAttribute("detailId", detailId);
        model.addAttribute("inquery", inquery == null ? false : inquery);
        return DIR_VIEW + "list-pembanding";
    }

    @RequestMapping(value = "form-pembanding", method = RequestMethod.GET)
    public String formPembanding(@RequestParam(value = "id", required = false) Long id,
                                 @RequestParam(value = "detailId") Long detailId, Model model) {
        if (id == null) {
            model.addAttribute("formBean", new SurveyPembandingDto(detailId));
        } else {
            model.addAttribute("formBean", facade.getPembanding(id));
        }
        initReferencePembanding(model);
        return DIR_VIEW + "data-pembanding";
    }

    private void initReferencePembanding(Model model) {
        model.addAttribute("listJenisAktiva", parameterFacade.findLvByGroup(AppConstans.GroupParameter.JENIS_AKTIVA));
        model.addAttribute("listKondisi", parameterFacade.findLvByGroup(AppConstans.GroupParameter.KONDISI_DENGAN_AKTIVA));
    }

    @RequestMapping(value = "delete-pembanding", method = RequestMethod.GET)
    public String deletePembanding(@RequestParam(value = "id") Long id,
                                   @RequestParam(value = "detailId") Long detailId,
                                   final RedirectAttributes redirectAttributes) {
        try {
            facade.deletePembanding(id);
            redirectAttributes.addFlashAttribute("flashMessage", "Delete Data success");
        } catch (Exception e) {
        }
        return "redirect:show-pembanding?detailId=" + detailId;
    }

    @RequestMapping(value = "save-pembanding", method = RequestMethod.POST)
    public String saveDataPembanding(@ModelAttribute("formBean") @Valid SurveyPembandingDto dto, BindingResult result,
                                     Model model, final RedirectAttributes redirectAttributes) {
        logger.debug("DTO : " + dto);
        if (result.hasErrors()) {
            initReferencePembanding(model);
            return DIR_VIEW + "data-pembanding";
        }
        boolean edit = dto.getId() != null;
        dto = facade.saveDataPembanding(dto);
        if (edit) {
            redirectAttributes.addFlashAttribute("flashMessage", "Data updated successfully!");
        } else {
            redirectAttributes.addFlashAttribute("flashMessage", "Data added successfully!");
        }
        return "redirect:show-pembanding?detailId=" + dto.getDetailId();
    }

    @RequestMapping(value = "show-point", method = RequestMethod.GET)
    public String showPoint(@RequestParam(value = "detailId") Long detailId, Model model,
                            @RequestParam(value = "inquery", required = false) Boolean inquery) {
        SurveyPointDto dto = facade.getPointByDetail(detailId);
        model.addAttribute("formBean", dto);
        model.addAttribute("inquery", inquery == null ? false : inquery);
        initReferencePoint(model, dto.getCategory(), null);
        return DIR_VIEW + "data-point";
    }

    @RequestMapping(value = "save-point", method = RequestMethod.POST)
    public String saveDataPoint(@ModelAttribute("formBean") @Valid SurveyPointDto dto, BindingResult result,
                                Model model) {
        logger.debug("DTO : " + dto);
        initReferencePoint(model, dto.getCategory(), null);
        if (result.hasErrors()) {
            return DIR_VIEW + "data-point";
        }
        boolean edit = dto.getId() != null;
        dto = facade.saveDataPoint(dto);
        model.addAttribute("formBean", dto);
        if (edit) {
            model.addAttribute("msg", "Data updated successfully!");
        } else {
            model.addAttribute("msg", "Data added successfully!");
        }
        return DIR_VIEW + "data-point";
    }

    private void initReferencePoint(Model model, String jenisKategori, String jenisAktiva) {
        String negativeCode = AppConstans.GroupParameter.NEGATIVE_LIST;
        String pertimbanganCode = AppConstans.GroupParameter.PROPERTI_PERTIMBANGAN;
        if (jenisKategori != null) {
            if (jenisKategori.equalsIgnoreCase(AppConstans.JenisKategoriCode.KENDARAAN)) {
                negativeCode = AppConstans.GroupParameter.NEGATIVE_LIST_KENDARAAN;
                pertimbanganCode = AppConstans.GroupParameter.PROPERTI_PERTIMBANGAN_KENDARAAN;
            }
        }
        model.addAttribute("listLegative", parameterFacade.findLvByGroup(negativeCode));
        model.addAttribute("listPertimbangan", parameterFacade.findLvByGroup(pertimbanganCode));
    }

    @RequestMapping(value = "preview-pembanding", method = RequestMethod.GET)
    public String downloadPembanding(@RequestParam("id") Long id, HttpServletResponse response) {
        SurveyPembandingDto dto = facade.getPembanding(id);
        OutputStream out = null;
        try {
            response.setHeader("Content-Disposition", "inline;filename=\"image-pembanding.jpg\"");
            out = response.getOutputStream();
            IOUtils.copy(new ByteArrayInputStream(dto.getImage()), out);
            out.flush();
            out.close();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            if(out!=null) {
                IOUtils.closeQuietly(out);
            }
        }
        return null;
    }

    @RequestMapping(value = "show-kendaraan", method = RequestMethod.GET)
    public String showKendaraan(@RequestParam(value = "detailId") Long detailId,
                                @ModelAttribute("person") PersonLoginDto person, Model model,
                                @RequestParam(value = "inquery", required = false) Boolean inquery) {
        model.addAttribute("dataList", facade.findKendaraan(new SurveyKendaraanDto(detailId)));
        model.addAttribute("detailId", detailId);
        model.addAttribute("inquery", inquery == null ? false : inquery);
        return DIR_VIEW + "list-kendaraan";
    }

    @RequestMapping(value = "form-kendaraan", method = RequestMethod.GET)
    public String formKendaraan(@RequestParam(value = "id", required = false) Long id,
                                @RequestParam(value = "detailId") Long detailId,
                                @ModelAttribute("person") PersonLoginDto person, Model model,
                                @RequestParam(value = "inquery", required = false) Boolean inquery) {
        if (id == null) {
            model.addAttribute("formBean", new SurveyKendaraanDto(detailId));
        } else {
            model.addAttribute("formBean", facade.getKendaraan(id));
        }
        model.addAttribute("inquery", inquery == null ? false : inquery);
        initReferenceKendaraan(model);
        return DIR_VIEW + "data-kendaraan";
    }

    @RequestMapping(value = "save-kendaraan", method = RequestMethod.POST)
    public String saveDataKendaraan(@ModelAttribute("formBean") @Valid SurveyKendaraanDto dto, BindingResult result,
                                    @ModelAttribute("person") PersonLoginDto person, Model model,
                                    final RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            initReferenceKendaraan(model);
            return DIR_VIEW + "data-kendaraan";
        }
        boolean edit = dto.getId() != null;
        dto = facade.saveDataKendaraan(dto);
        if (edit) {
            redirectAttributes.addFlashAttribute("flashMessage", "Data updated successfully!");
        } else {
            redirectAttributes.addFlashAttribute("flashMessage", "Data added successfully!");
        }
        return "redirect:show-kendaraan?detailId=" + dto.getDetailId();
    }

    @RequestMapping(value = "delete-kendaraan", method = RequestMethod.GET)
    public String deleteKendaraan(@RequestParam(value = "id") Long id,
                                  @RequestParam(value = "detailId") Long detailId,
                                  final RedirectAttributes redirectAttributes) {
        try {
            facade.deleteKendaraan(id);
            redirectAttributes.addFlashAttribute("flashMessage", "Delete Data success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("flashMessage", e.getMessage());
        }
        return "redirect:show-kendaraan?detailId=" + detailId;
    }

    @RequestMapping(value = "form-vessel", method = RequestMethod.GET)
    public String formVessel(@RequestParam(value = "id", required = false) Long id,
                                @RequestParam(value = "detailId") Long detailId,
                                @ModelAttribute("person") PersonLoginDto person, Model model,
                                @RequestParam(value = "inquery", required = false) Boolean inquery) {
        if (id == null) {
            model.addAttribute("formBean", new SurveyVesselDto(detailId));
        } else {
            model.addAttribute("formBean", facade.getKendaraan(id));
        }
        model.addAttribute("inquery", inquery == null ? false : inquery);
        initReferenceVessel(model);
        return DIR_VIEW + "data-vessel";
    }

    private void initReferenceVessel(Model model){

    }
}