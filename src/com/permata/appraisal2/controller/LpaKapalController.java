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

import javax.servlet.ServletContext;
import javax.validation.Valid;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("lpa-kapal")
@SessionAttributes({"person", "roleActive"})
public class LpaKapalController extends BaseController {

    private final String DIR_VIEW = "lpa/kapal/";


    @Autowired
    private LpaKapalFacade lpaFacade;
    @Autowired
    private DataCommonFacade facade;
    @Autowired
    private ParameterFacade parameterFacade;
    @Autowired
    private MataUangFacade mataUangFacade;
    @Autowired
    private PersonFacade personFacade;
    @Autowired
    private JenisPenilaianFacade penilaianFacade;
    @Autowired
    private AppraisalBranchFacade branchFacade;
    @Autowired
    private DocumentTypeFacade documentFacade;
    @Autowired
    private KjppFacade kjppFacade;

    @Autowired
    private StatusJaminanFacade statusJaminanFacade;
    @Autowired
    // @Qualifier("dataSource")
    private BasicDataSource dataSource;

    @Autowired
    private ServletContext context;

    @RequestMapping(value = {"create-lpa"}, method = RequestMethod.GET)
    public String createLpa(@RequestParam("commonId") Long commonId, Model model) {
        LpaKapalDto formBean = lpaFacade.create(commonId);
        model.addAttribute("disableDraft", Boolean.TRUE);
        initCreateLpa(model, formBean);
        return DIR_VIEW + "form";
    }

    @RequestMapping(value = {"draft-lpa"}, method = RequestMethod.POST)
    public String draftLpa(@ModelAttribute("formBean") LpaKapalDto formBean, @ModelAttribute("person") PersonLoginDto person) {
        formBean.setDraft(true);
        try{
            formBean = lpaFacade.save(formBean, person.getUserId());
        } catch (Exception e) {
            return "redirect:/app-valuer/list-error";
        }
        return "redirect:/app-valuer/view?id=" + formBean.getCommonId();
    }

    @RequestMapping(value = {"save-lpa"}, method = RequestMethod.POST)
    public String saveLpa(@ModelAttribute("formBean") @Valid LpaKapalDto formBean,
                          BindingResult result, Model model,
                          @ModelAttribute("person") PersonLoginDto person) {
        boolean next = true;
        if (result.hasErrors()) {
            next = false;
        }
        if (!next) {
            initCreateLpa(model, formBean);
            return DIR_VIEW + "form";
        }
        formBean.setDraft(false);
        try{
            formBean = lpaFacade.save(formBean, person.getUserId());
        } catch (Exception e) {
            return "redirect:/app-valuer/list-error";
        }
        return "redirect:/app-valuer/view?id=" + formBean.getCommonId();
    }

    @RequestMapping(value = {"edit-lpa"}, method = RequestMethod.GET)
    public String editLpa(@RequestParam("id") Long id, Model model) {
        LpaKapalDto formBean = lpaFacade.getByUmum(id);
        model.addAttribute("formBean", formBean);
        model.addAttribute("disableDraft", formBean.getDraft());
        initCreateLpa(model, formBean);
        return DIR_VIEW + "form";
    }

    @RequestMapping(value = {"delete-lpa"}, method = RequestMethod.GET)
    public String deleteLpa(@RequestParam("id") Long id, @RequestParam("commonId") Long commonId) {
        try {
            lpaFacade.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/app-valuer/view?id=" + commonId;
    }

    private void initCreateLpa(Model model, LpaKapalDto formBean) {
        model.addAttribute("formBean", formBean);
        model.addAttribute("lvStatusJaminan", statusJaminanFacade.listLv());
        model.addAttribute("listLokasiAktiva", parameterFacade.findLvByGroup(AppConstans.GroupParameter.POSISI_LOKASI_AKTIVA));
        model.addAttribute("listFaktorMarket", parameterFacade.findLvByGroup(AppConstans.GroupParameter.FAKTOR_MARKETABILITY));
        model.addAttribute("listNegative", parameterFacade.findLvByGroup(AppConstans.GroupParameter.NEGATIVE_LIST_VESSEL));
        model.addAttribute("listPertimbangan", parameterFacade.findLvByGroup(AppConstans.GroupParameter.PROPERTI_PERTIMBANGAN_VESSEL));
        model.addAttribute("listFaktorUnMarket", parameterFacade.findLvByGroup(AppConstans.GroupParameter.FAKTOR_UNMARKETABILITY));
        model.addAttribute("listAppraisalCabang", branchFacade.findLv(new AppraisalBranchDto()));
        model.addAttribute("listMataUang", mataUangFacade.list(new MataUangDto()));
        model.addAttribute("listJenisDokumen", documentFacade.findByAktiva(formBean.getJenisAktiva()));
        model.addAttribute("listJenisPenilaianFisik", penilaianFacade.listByKategoriAktiva(formBean.getKategoriObjectPenilaian()));
        model.addAttribute("listCatatanKewajaran", parameterFacade.findLvByGroup(AppConstans.GroupParameter.CATATAN_KEWAJARAN));
        model.addAttribute("listCatatanKesesuaian", parameterFacade.findLvByGroup(AppConstans.GroupParameter.CATATAN_KESESUAIAN));
        if (formBean.isEksternal()) {
            model.addAttribute("listOpini", parameterFacade.findLvByGroup(AppConstans.GroupParameter.OPINI_INTERNAL_ATAS_EKSTERNAL));
            model.addAttribute("listKjpp", kjppFacade.findLv());
        } else {
            model.addAttribute("listSurveyor", personFacade.findLvSurveyor());
        }
        model.addAttribute("listKelasKapal", parameterFacade.findLvByGroup(AppConstans.GroupParameter.KELAS_KAPAL_VESSEL));
        model.addAttribute("listStatusAgunanKapal", parameterFacade.findLvByGroup(AppConstans.GroupParameter.STATUS_AGUNAN_VESSEL));
        model.addAttribute("listStatusJaminanKapal", parameterFacade.findLvByGroup(AppConstans.GroupParameter.STATUS_JAMINAN_VESSEL));
        model.addAttribute("listStatusJaminanKapal", parameterFacade.findLvByGroup(AppConstans.GroupParameter.STATUS_JAMINAN_VESSEL));
        model.addAttribute("listKondisiUmum", parameterFacade.findLvByGroup(AppConstans.GroupParameter.KONDISI_UMUM_VESSEL));
        model.addAttribute("listJenisKapal", parameterFacade.findLvByGroup(AppConstans.GroupParameter.JENIS_VESSEL));

    }

    @RequestMapping (value = "laporan-lpa-kapal", method = RequestMethod.GET)
    public ModelAndView laporanLpaKapal(@RequestParam ("PIdLpaUmum") Long PIdLpaUmum, @RequestParam ("lpa") String lpa){
        Map<String, Object> parameterMap = new HashMap<String, Object>();
        String jasperDir = String.format("%s%s%s%s%s%s", context.getRealPath(""), File.separator, "WEB-INF",
                File.separator, "report", File.separator);
        parameterMap.put("SUBREPORT_DIR", jasperDir);
        parameterMap.put("datasource", dataSource);
        parameterMap.put("PIdLpaUmum", PIdLpaUmum);
        if (lpa.equals("lpa-int")) {
            return new ModelAndView("pdfLaporanLpaKapal", parameterMap);
        }else if(lpa.equals("lpa-ext")){
            return new ModelAndView("pdfLaporanLpaKapalExt", parameterMap);
        }else if(lpa.equals("img-survey")){
            return new ModelAndView("pdfLaporanLpaImgSurvey", parameterMap);
        }else if(lpa.equals("img-peta")){
            return new ModelAndView("pdfLaporanLpaImgPeta", parameterMap);
        }else if(lpa.equals("img-other")){
            return new ModelAndView("pdfLaporanLpaImgOther", parameterMap);
        }else{
            return null;
        }
    }


}
