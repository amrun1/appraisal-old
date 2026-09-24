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
@RequestMapping("lpa-rab")
@SessionAttributes({"person", "roleActive"})
public class LpaRabController extends BaseController {

    private final String DIR_VIEW = "lpa/rab/";


    @Autowired
    private LpaUmumFacade lpaUmumFacade;

    @Autowired
    private LpaRabFacade lpaFacade;
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
    // @Qualifier("dataSource")
    private BasicDataSource dataSource;

    @Autowired
    private ServletContext context;

    @RequestMapping(value = {"create-lpa"}, method = RequestMethod.GET)
    public String createLpa(@RequestParam("commonId") Long commonId, Model model) {
        LpaRabDto formBean = lpaFacade.create(commonId);
        initCreateLpa(model, formBean);
        return DIR_VIEW + "form";
    }

    @RequestMapping(value = {"draft-lpa"}, method = RequestMethod.POST)
    public String draftLpa(@ModelAttribute("formBean") LpaRabDto formBean, @ModelAttribute("person") PersonLoginDto person) {
        formBean.setDraft(true);
        try{
            formBean = lpaFacade.save(formBean, person.getUserId());
        } catch (Exception e) {
            return "redirect:/app-valuer/list-error";
        }
        return "redirect:/app-valuer/view?id=" + formBean.getCommonId();
    }

    @RequestMapping(value = {"save-lpa"}, method = RequestMethod.POST)
    public String saveLpa(@ModelAttribute("formBean") @Valid LpaRabDto formBean, BindingResult result, Model model,
                          @ModelAttribute("person") PersonLoginDto person) {
        if (result.hasErrors()) {
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
        LpaRabDto formBean = lpaFacade.getByUmum(id);
        initCreateLpa(model, formBean);
        return DIR_VIEW + "form";
    }

    @RequestMapping(value = {"delete-lpa"}, method = RequestMethod.GET)
    public String deleteLpa(@RequestParam("id") Long id, @RequestParam("commonId") Long commonId) {
        try {
            lpaUmumFacade.delete(id);

//            lpaFacade.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/app-valuer/view?id=" + commonId;
    }

    private void initCreateLpa(Model model, LpaRabDto formBean) {
        model.addAllAttributes(facade.getReference(DataCommonDto.StatusData.COMPLETE, null));
        model.addAttribute("formBean", formBean);
        model.addAttribute("listSurveyor", personFacade.findLvSurveyor());
        model.addAttribute("listMataUang", mataUangFacade.list(new MataUangDto()));
        model.addAttribute("listJenisPenilaianFisik", penilaianFacade.listByKategoriAktiva(formBean.getKategoriObjectPenilaian()));
        model.addAttribute("listAppraisalCabang", branchFacade.findLv(new AppraisalBranchDto()));
        model.addAttribute("listLokasiAktiva", parameterFacade.findLvByGroup(AppConstans.GroupParameter.POSISI_LOKASI_AKTIVA));
        model.addAttribute("listKewajaranNilai", parameterFacade.findLvByGroup(AppConstans.GroupParameter.RAB_KEWAJARAN_NILAI));
    }

    @RequestMapping (value = "laporan-lpa-rab", method = RequestMethod.GET)
    public ModelAndView laporanLpaRab(@RequestParam ("PIdLpaUmum") Long PIdLpaUmum, @RequestParam ("lpa") String lpa){
        Map<String, Object> parameterMap = new HashMap<String, Object>();
        String jasperDir = String.format("%s%s%s%s%s%s", context.getRealPath(""), File.separator, "WEB-INF",
                File.separator, "report", File.separator);
        parameterMap.put("SUBREPORT_DIR", jasperDir);
        parameterMap.put("datasource", dataSource);
        parameterMap.put("PIdLpaUmum", PIdLpaUmum);
        if(lpa.equals("lpa-rab")) {
            return new ModelAndView("pdfLaporanLpaRab", parameterMap);
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