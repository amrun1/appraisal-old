package com.permata.appraisal2.controller;

import com.permata.appraisal2.common.AppConstans;
import com.permata.appraisal2.common.BaseController;
import com.permata.appraisal2.dto.*;
import com.permata.appraisal2.facade.*;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletContext;
import javax.validation.Valid;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by SB on 4/17/2018.
 */

@Controller
@RequestMapping("lpa-inspeksi")
@SessionAttributes({"person", "roleActive"})
public class LpaInspeksiController extends BaseController {
    private final String DIR_VIEW = "lpa/inspeksi/";

    @Autowired
    private LpaInspeksiFacade lpaInspeksiFacade;
    @Autowired
    private LpaUmumFacade lpaUmumFacade;
    @Autowired
    private ParameterFacade parameterFacade;
    @Autowired
    private StatusJaminanFacade statusJaminanFacade;
    @Autowired
    private AppraisalBranchFacade appraisalBranchFacade;
    @Autowired
    private MataUangFacade mataUangFacade;
    @Autowired
    private JenisPenilaianFacade penilaianFacade;
    @Autowired
    private PersonFacade personFacade;
    @Autowired
    private KjppFacade kjppFacade;
    @Autowired
    private BasicDataSource dataSource;
    @Autowired
    private ServletContext context;


    @RequestMapping(value = {"create-lpa"}, method = RequestMethod.GET)
    public String createLpa(@RequestParam("commonId") Long commonId, Model model) {
        LpaInspeksiDto formBean = lpaInspeksiFacade.create(commonId);
        model.addAttribute("formBean", formBean);
        model.addAttribute("disableDraft", Boolean.TRUE);
        model.addAttribute("surveyStatus", lpaInspeksiFacade.checkSurveyStatusData(commonId));
        initCreateLpa(model, formBean);
        return DIR_VIEW + "form";
    }


    @RequestMapping(value = {"draft-lpa"}, method = RequestMethod.POST)
    public String draftLpa(@ModelAttribute("formBean") LpaInspeksiDto formBean,
                           @ModelAttribute("person") PersonLoginDto person,
                           @RequestParam(value = "surveyStatus") Boolean surveyStatus,
                           final RedirectAttributes redirectAttributes) {
        // TODO: 4/23/2018  need to add validation matching between person login and tasklist in dataUmum
        formBean.setDraft(true);
        try {
            formBean = lpaInspeksiFacade.saveInspeksi(formBean, person.getUserId());
            if(surveyStatus){
                lpaInspeksiFacade.copyItemInspeksiFromSurvey(formBean.getCommonId(),formBean.getId());
            }
        } catch (Exception e) {
            return "redirect:/app-valuer/list-error";
        }

        return "redirect:/app-valuer/view?id=" + formBean.getCommonId();
    }

    @RequestMapping(value = {"save-lpa"}, method = RequestMethod.POST)
    public String saveLpa(@ModelAttribute("formBean") @Valid LpaInspeksiDto formBean,
                          BindingResult result, Model model,
                          @ModelAttribute("person") PersonLoginDto person,
                          @RequestParam(value = "surveyStatus") Boolean surveyStatus,
                          final RedirectAttributes redirectAttributes) {
        // TODO: 4/23/2018  need to add validation matching between person login and tasklist in dataUmum
        boolean next = true;
        // TODO: 4/26/2018 uncomment after validation done
        if (result.hasErrors()) {
            next = false;
        }
//        if(next) {
//            formBean.validate(result);
//            if (result.hasErrors()) {
//                next = false;
//            }
//        }
        if (!next) {
            initCreateLpa(model, formBean);
            return DIR_VIEW + "form";
        }
        formBean.setDraft(false);
        try {
            formBean = lpaInspeksiFacade.saveInspeksi(formBean, person.getUserId());
            if(surveyStatus){
                lpaInspeksiFacade.copyItemInspeksiFromSurvey(formBean.getCommonId(),formBean.getId());
            }
        } catch (Exception e) {
            return "redirect:/app-valuer/list-error";
        }
        return "redirect:/app-valuer/view?id=" + formBean.getCommonId();
    }

    @RequestMapping(value = {"edit-lpa"}, method = RequestMethod.GET)
    public String editLpa(@RequestParam("id") Long id, Model model) {
        // TODO: 4/23/2018  need to add validation matching between person login and tasklist in dataUmum
        LpaInspeksiDto formBean = lpaInspeksiFacade.getByUmum(id);
        model.addAttribute("formBean", formBean);
        model.addAttribute("disableDraft", formBean.getDraft());
        model.addAttribute("surveyStatus", false);
        initCreateLpa(model, formBean);
        return DIR_VIEW + "form";
    }

    @RequestMapping(value = {"delete-lpa"}, method = RequestMethod.GET)
    public String deleteLpa(@RequestParam("id") Long id, @RequestParam("commonId") Long commonId) {
        try {
            lpaUmumFacade.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/app-valuer/view?id=" + commonId;
    }

    private void initCreateLpa(Model model, LpaInspeksiDto formBean) {
        //Lpa Umum
        model.addAttribute("listLokasiAktiva", parameterFacade.findLvByGroup(AppConstans.GroupParameter.POSISI_LOKASI_AKTIVA));
        model.addAttribute("lvStatusJaminan", statusJaminanFacade.listLv());
        model.addAttribute("listAppraisalCabang", appraisalBranchFacade.findLv(new AppraisalBranchDto()));
        model.addAttribute("listMataUang", mataUangFacade.list(new MataUangDto()));
        model.addAttribute("listJenisPenilaianFisik", penilaianFacade.listByKategoriAktiva(formBean.getKategoriObjectPenilaian()));
        //LPA Inspeksi
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
        if (formBean.isEksternal()) {
            model.addAttribute("listKjpp", kjppFacade.findLv());
        } else {
            model.addAttribute("listSurveyor", personFacade.findLvSurveyor());
        }

    }

    @RequestMapping(value = {"form-item-inspeksi"}, method = RequestMethod.GET)
    public String formBarang(@RequestParam(required = false, value = "id") Long id,
                                 @RequestParam(value = "jenisAktiva") Long jenisAktiva,
                                 @RequestParam(value = "lpaInspeksiId") Long lpaInspeksiId,
                                 @RequestParam(value = "typeGoods") String typeGoods,
                                 Model model) {
        LpaItemInspeksiDto formBean = new LpaItemInspeksiDto();
        formBean.setLpaInspeksiId(lpaInspeksiId);
        if (id != null) {
            formBean = lpaInspeksiFacade.getItemInspeksi(id);
        } else {
            formBean.setType(typeGoods);
        }
        model.addAttribute("labelGoods", formBean.getType().equalsIgnoreCase("Goods")?"Barang Jadi":formBean.getType().equalsIgnoreCase("Half_Goods")?"Barang Setengah Jadi" : "Bahan Baku");
        model.addAttribute("formBean", formBean);
        model.addAttribute("jenisAktiva", jenisAktiva);

        return DIR_VIEW + "form-item-inspeksi";
    }

    @RequestMapping(value = {"save-item-inspeksi"}, method = RequestMethod.POST)
    public String saveItemInspeksi(@ModelAttribute("formBean") @Valid LpaItemInspeksiDto formBean, BindingResult result,
                                 @RequestParam(value = "jenisAktiva") Long jenisAktiva, Model model,
                                 @ModelAttribute("person") PersonLoginDto person) {
        boolean next = true;
        if (result.hasErrors()) {
            next = false;
        }
        if (next) {
//            formBean.validate(result); // TODO: 4/19/2018 uncomment after validate
            if (result.hasErrors()) {
                next = false;
            }
        }
        try {
            lpaInspeksiFacade.saveItemInspeksi(formBean, person.getUserId());
        } catch (Exception e) {
            return "error-redirect-list";
        }

        return "success-close-modal";
    }

    @RequestMapping(value = {"table-item-inspeksi"}, method = RequestMethod.GET)
    public String showListItemInspeksi(@RequestParam("idLpaInspeksi") Long idLpaInspeksi, @RequestParam("commonId") Long commonId, @RequestParam("surveyStatus") Boolean surveyStatus, @RequestParam("typeGoods") String typeGoods, Model model) {
        List<LpaItemInspeksiDto> dataList;
//        if(idLpaInspeksi==null && commonId!=null && statusSurvey) {
        if (surveyStatus) {
            dataList = lpaInspeksiFacade.getListSurveyItemInspeksiByCommonId(commonId, typeGoods);
        } else {
            dataList = lpaInspeksiFacade.getListItemInspeksiByInspeksiId(idLpaInspeksi, typeGoods);
        }
        String url;
        if (typeGoods.equalsIgnoreCase("Goods")) {
            url = "table-barang-jadi";
        } else if (typeGoods.equalsIgnoreCase("Half_Goods")) {
            url = "table-barang-setengah-jadi";
        } else {
            url = "table-bahan-baku";
        }
        model.addAttribute("dataList", dataList);
        model.addAttribute("countTanahRef", dataList.size());
        return DIR_VIEW + url;
    }

    @RequestMapping (value = "laporan-lpa-inspeksi", method = RequestMethod.GET)
    public ModelAndView laporanLpaInspeksi(@RequestParam ("PIdLpaUmum") Long PIdLpaUmum, @RequestParam ("lpa") String lpa){
        Map<String, Object> parameterMap = new HashMap<String, Object>();
        String jasperDir = String.format("%s%s%s%s%s%s", context.getRealPath(""), File.separator, "WEB-INF",
                File.separator, "report", File.separator);
        parameterMap.put("SUBREPORT_DIR", jasperDir);
        parameterMap.put("datasource", dataSource);
        parameterMap.put("PIdLpaUmum", PIdLpaUmum);

        if(lpa.equals("lpa-inspeksi")) {
            return new ModelAndView("pdfLpaInspeksi", parameterMap);
        }else if(lpa.equals("img-survey")){
            return new ModelAndView("pdfLaporanLpaImgSurveyInspeksi", parameterMap);
        }else if(lpa.equals("detail-barang-jadi")){
            parameterMap.put("PTypeBarang", "Goods");
            return new ModelAndView("pdfLaporanDetailBarang", parameterMap);
        }else if(lpa.equals("detail-barang-setengah-jadi")){
            parameterMap.put("PTypeBarang", "Half_Goods");
            return new ModelAndView("pdfLaporanDetailBarang", parameterMap);
        }else if(lpa.equals("detail-bahan-baku")){
            parameterMap.put("PTypeBarang", "Raw_Materials");
            return new ModelAndView("pdfLaporanDetailBarang", parameterMap);
        }else{
            return null;
        }
    }

    @RequestMapping(value = {"delete-barang"}, method = RequestMethod.GET)
    public String deleteBarang(@RequestParam("id") Long id, @RequestParam("commonId") Long commonId, @RequestParam("typeGoods") String typeGoods,
                               @ModelAttribute("person") PersonLoginDto person, final RedirectAttributes redirectAttributes, Model model) {
        try {
            lpaInspeksiFacade.deleteItemInspeksi(id, person.getUserId());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("flashMessage", e.getMessage(

            ));
            redirectAttributes.addFlashAttribute("css", "error");
            return "redirect:/app-valuer/list";
        }
//        return "redirect:/table-item-inspeksi?idLpaInspeksi=" + commonId+"&typeGoods="+typeGoods;

//        Model model;

        List<LpaItemInspeksiDto> dataList = lpaInspeksiFacade.getListItemInspeksiByInspeksiId(commonId, typeGoods);
        String url;
        if (typeGoods.equalsIgnoreCase("Goods")) {
            url = "table-barang-jadi";
        } else if (typeGoods.equalsIgnoreCase("Half_Goods")) {
            url = "table-barang-setengah-jadi";
        } else {
            url = "table-bahan-baku";
        }
        model.addAttribute("dataList", dataList);
        model.addAttribute("countTanahRef", dataList.size());
        return DIR_VIEW + url;
    }

}
