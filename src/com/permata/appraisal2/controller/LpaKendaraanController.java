package com.permata.appraisal2.controller;

import com.permata.appraisal2.common.AppConstans;
import com.permata.appraisal2.common.BaseController;
import com.permata.appraisal2.dto.*;
import com.permata.appraisal2.facade.*;
import com.permata.appraisal2.facade.impl.LpaUmumFacadeImpl;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.validation.Valid;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("lpa-kendaraan")
@SessionAttributes({"person", "roleActive"})
public class LpaKendaraanController extends BaseController {

    private final String DIR_VIEW = "lpa/kendaraan/";

    @Autowired
    private LpaUmumFacade lpaUmumFacade;
    @Autowired
    private LpaKendaraanFacade facade;
    @Autowired
    private ParameterFacade parameterFacade;
    @Autowired
    private StatusJaminanFacade statusJaminanFacade;
    @Autowired
    private AppraisalBranchFacade branchFacade;
    @Autowired
    private MataUangFacade mataUangFacade;
    @Autowired
    private DocumentTypeFacade documentFacade;
    @Autowired
    private JenisPenilaianFacade penilaianFacade;
    @Autowired
    private KjppFacade kjppFacade;
    @Autowired
    private PersonFacade personFacade;

    @Autowired
    // @Qualifier("dataSource")
    private BasicDataSource dataSource;

    @Autowired
    private ServletContext context;

    @RequestMapping(value = {"create-lpa"}, method = RequestMethod.GET)
    public String createLpa(@RequestParam("commonId") Long commonId, Model model) {
        LpaKendaraanDto formBean = facade.create(commonId);
        model.addAttribute("formBean", formBean);
        model.addAttribute("disableDraft", Boolean.TRUE);
        initCreateLpa(model, formBean);
        return DIR_VIEW + "form";
    }

    @RequestMapping(value = {"draft-lpa"}, method = RequestMethod.POST)
    public String draftLpa(@ModelAttribute("formBean") LpaKendaraanDto formBean, @ModelAttribute("person") PersonLoginDto person) {
        formBean.setDraft(true);
        try{
            formBean = facade.save(formBean, person.getUserId());
        } catch (Exception e) {
            return "redirect:/app-valuer/list-error";
        }
        return "redirect:/app-valuer/view?id=" + formBean.getCommonId();
    }

    @RequestMapping(value = {"save-lpa"}, method = RequestMethod.POST)
    public String saveLpa(@ModelAttribute("formBean") @Valid LpaKendaraanDto formBean, BindingResult result, Model model,
                          @ModelAttribute("person") PersonLoginDto person) {
        boolean next = true;
        if (result.hasErrors()) {
            next = false;
        }
        if(next) {
            formBean.validate(result);
            if (result.hasErrors()) {
                next = false;
            }
        }
        if (!next) {
            initCreateLpa(model, formBean);
            return DIR_VIEW + "form";
        }

        formBean.setDraft(false);
        try{
            formBean = facade.save(formBean,person.getUserId());
        } catch (Exception e) {
            return "redirect:/app-valuer/list-error";
        }
        return "redirect:/app-valuer/view?id=" + formBean.getCommonId();
    }

    @RequestMapping(value = {"edit-lpa"}, method = RequestMethod.GET)
    public String editLpa(@RequestParam("id") Long id, Model model) {
        LpaKendaraanDto formBean = facade.getByUmum(id);
        model.addAttribute("formBean", formBean);
        model.addAttribute("disableDraft", formBean.getDraft());
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

    @RequestMapping(value = {"table-kendaraan"}, method = RequestMethod.GET)
    public String showItemKendaraan(@RequestParam("idLpa") Long idLpa, Model model) {
        List<LpaItemKendaraanDto> dataList = facade.listItemKendaraan(idLpa);
        model.addAttribute("dataList", dataList);
        return DIR_VIEW + "table-kendaraan";
    }

    @RequestMapping(value = {"form-kendaraan"}, method = RequestMethod.GET)
    public String formItemKendaraan(@RequestParam(value = "id", required = false) Long id,
                                    @RequestParam(value = "lpaId") Long lpaId,
                                    Model model) {
        LpaItemKendaraanDto formBean;
        if (id == null) {
            formBean = new LpaItemKendaraanDto(lpaId);
        } else {
            formBean = facade.getItem(id);
        }
        model.addAttribute("formBean", formBean);
        initItemKendaraan(model);
        return DIR_VIEW + "form-kendaraan";
    }

    @RequestMapping(value = {"save-kendaraan"}, method = RequestMethod.POST)
    public String saveKendaraan(@ModelAttribute("formBean") @Valid LpaItemKendaraanDto formBean, BindingResult result, Model model,
                                @ModelAttribute("person") PersonLoginDto person) {
        if (result.hasErrors()) {
            initItemKendaraan(model);
            return DIR_VIEW + "form-kendaraan";
        }
        try{
            facade.saveKendaraan(formBean, person.getUserId());
        } catch (Exception e) {
            return "error-redirect-parent-list";
        }
        return "success-close-parent-modal";
    }

    @RequestMapping(value = {"delete-kendaraan"}, method = RequestMethod.GET)
    public String deleteDokumenBangunan(@RequestParam("id") Long id, @RequestParam("idLpa") Long idLpa) {
        try {
            facade.deleteKendaraan(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:table-kendaraan?idLpa=" + idLpa;
    }

    private void initItemKendaraan(Model model) {
        model.addAttribute("listPenggunaan", parameterFacade.findLvByGroup(AppConstans.GroupParameter.PENGGUNAAN_KENDARAAN));
        model.addAttribute("listPemakaian", parameterFacade.findLvByGroup(AppConstans.GroupParameter.PEMAKAIAN_KENDARAAN));
        model.addAttribute("listKondisiBody", parameterFacade.findLvByGroup(AppConstans.GroupParameter.KONDISI_BODY_KENDARAAN));
        model.addAttribute("listKondisiBumper", parameterFacade.findLvByGroup(AppConstans.GroupParameter.KONDISI_BUMPER_KENDARAAN));
        model.addAttribute("listKondisiCat", parameterFacade.findLvByGroup(AppConstans.GroupParameter.KONDISI_CAT_KENDARAAN));
        model.addAttribute("listKondisiInterior", parameterFacade.findLvByGroup(AppConstans.GroupParameter.KONDISI_INTERIOR_KENDARAAN));
        model.addAttribute("listKondisiEksterior", parameterFacade.findLvByGroup(AppConstans.GroupParameter.KONDISI_EKSTERIOR_KENDARAAN));
        model.addAttribute("listKondisiMesin", parameterFacade.findLvByGroup(AppConstans.GroupParameter.KONDISI_MESIN_KENDARAAN));
        model.addAttribute("listKondisiBan", parameterFacade.findLvByGroup(AppConstans.GroupParameter.KONDISI_BAN_KENDARAAN));
    }

    private void initCreateLpa(Model model, LpaKendaraanDto formBean) {
        model.addAttribute("listLokasiAktiva", parameterFacade.findLvByGroup(AppConstans.GroupParameter.POSISI_LOKASI_AKTIVA));
        model.addAttribute("lvStatusJaminan", statusJaminanFacade.listLv());
        model.addAttribute("listAppraisalCabang", branchFacade.findLv(new AppraisalBranchDto()));
        model.addAttribute("listFaktorMarket", parameterFacade.findLvByGroup(AppConstans.GroupParameter.FAKTOR_MARKETABILITY));
        model.addAttribute("listNegative", parameterFacade.findLvByGroup(AppConstans.GroupParameter.NEGATIVE_LIST_KENDARAAN));
        model.addAttribute("listPertimbangan", parameterFacade.findLvByGroup(AppConstans.GroupParameter.PROPERTI_PERTIMBANGAN_KENDARAAN));
        model.addAttribute("listFaktorUnMarket", parameterFacade.findLvByGroup(AppConstans.GroupParameter.FAKTOR_UNMARKETABILITY_KENDARAAN));
        model.addAttribute("listMataUang", mataUangFacade.list(new MataUangDto()));
        model.addAttribute("listOpini", parameterFacade.findLvByGroup(AppConstans.GroupParameter.OPINI_INTERNAL_ATAS_EKSTERNAL));
        model.addAttribute("listMataUang", mataUangFacade.list(new MataUangDto()));
        model.addAttribute("listJenisDokumen", documentFacade.findByAktiva(formBean.getJenisAktiva()));
        model.addAttribute("listJenisPenilaianFisik", penilaianFacade.listByKategoriAktiva(formBean.getKategoriObjectPenilaian()));
        model.addAttribute("listCatatanKewajaran", parameterFacade.findLvByGroup(AppConstans.GroupParameter.CATATAN_KEWAJARAN));
        model.addAttribute("listCatatanKesesuaian", parameterFacade.findLvByGroup(AppConstans.GroupParameter.CATATAN_KESESUAIAN));
        if (formBean.isEksternal()) {
            model.addAttribute("listKjpp", kjppFacade.findLv());
        } else {
            model.addAttribute("listSurveyor", personFacade.findLvSurveyor());
        }
    }


    @RequestMapping (value = "laporan-lpa-kendaraan", method = RequestMethod.GET)
    public ModelAndView laporanLpaKendaraan(@RequestParam ("PIdLpaUmum") Long PIdLpaUmum, @RequestParam ("lpa") String lpa){
        Map<String, Object> parameterMap = new HashMap<String, Object>();
        String jasperDir = String.format("%s%s%s%s%s%s", context.getRealPath(""), File.separator, "WEB-INF",
                File.separator, "report", File.separator);
        parameterMap.put("SUBREPORT_DIR", jasperDir);
        parameterMap.put("datasource", dataSource);
        parameterMap.put("PIdLpaUmum", PIdLpaUmum);
        if(lpa.equals("lpa-int")) {
            return new ModelAndView("pdfLaporanLpaKendaraan", parameterMap);
        }else if(lpa.equals("lpa-ext")){
            return new ModelAndView("pdfLaporanLpaKendaraanExt", parameterMap);
        }else if(lpa.equals("lampiran")){
            return new ModelAndView("pdfLaporanLpaKendaraanLampiran", parameterMap);
        }else if(lpa.equals("img-survey")){
            return new ModelAndView("pdfLaporanLpaImgSurvey", parameterMap);
        }else if(lpa.equals("img-peta")){
            return new ModelAndView("pdfLaporanLpaImgPeta", parameterMap);
        }else if(lpa.equals("lpa-other")){
            return new ModelAndView("pdfLaporanLpaImgOther", parameterMap);
        }else{
            return null;
        }
    }
}