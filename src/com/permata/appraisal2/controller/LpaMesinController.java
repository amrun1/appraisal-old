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
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("lpa-mesin")
@SessionAttributes({"person", "roleActive"})
public class LpaMesinController extends BaseController {

    private final String DIR_VIEW = "lpa/mesin/";

    @Autowired
    private LpaMesinFacade lpaFacade;
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
    private KjppFacade kjppFacade;

    @Autowired
    // @Qualifier("dataSource")
    private BasicDataSource dataSource;

    @Autowired
    private ServletContext context;

    @RequestMapping(value = {"create-lpa"}, method = RequestMethod.GET)
    public String createLpa(@RequestParam("commonId") Long commonId, Model model) {
        LpaMesinDto formBean = lpaFacade.create(commonId);
        model.addAttribute("disableDraft", Boolean.TRUE);
        initCreateLpa(model, formBean);
        return DIR_VIEW + "form";
    }

    @RequestMapping(value = {"draft-lpa"}, method = RequestMethod.POST)
    public String draftLpa(@ModelAttribute("formBean") LpaMesinDto formBean, @ModelAttribute("person") PersonLoginDto person) {
        formBean.setDraft(true);
        try{
            formBean = lpaFacade.save(formBean, person.getUserId());
        } catch (Exception e) {
            return "redirect:/app-valuer/list-error";
        }
        return "redirect:/app-valuer/view?id=" + formBean.getCommonId();
    }

    @RequestMapping(value = {"save-lpa"}, method = RequestMethod.POST)
    public String saveLpa(@ModelAttribute("formBean") @Valid LpaMesinDto formBean, BindingResult result, Model model,
                          @ModelAttribute("person") PersonLoginDto person) {
        boolean next = true;
        if (result.hasErrors()) {
            next = false;
        }
//        if (next) {
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
        try{
            formBean = lpaFacade.save(formBean,person.getUserId());
        } catch (Exception e) {
            return "redirect:/app-valuer/list-error";
        }
        return "redirect:/app-valuer/view?id=" + formBean.getCommonId();
    }

    @RequestMapping(value = {"edit-lpa"}, method = RequestMethod.GET)
    public String editLpa(@RequestParam("id") Long id, Model model) {
        LpaMesinDto formBean = lpaFacade.getByUmum(id);
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

    @RequestMapping(value = "show-lampiran", method = RequestMethod.GET)
    public String showLampiran(@RequestParam(value = "idLpaMesin") Long idLpaMesin,
                               @ModelAttribute("person") PersonLoginDto person, Model model,
                               @RequestParam(value = "inquery", required = false) Boolean inquery) {
        List<LpaMesinLampiranDto> listData = lpaFacade.findLampiran(idLpaMesin);
        model.addAttribute("listData", listData);
        model.addAttribute("inquery", inquery == null ? false : inquery);
        return DIR_VIEW + "list-lampiran";
    }


    @RequestMapping(value = "form-lampiran", method = RequestMethod.GET)
    public String formLampiran(@RequestParam(value = "id", required = false) Long id,
                               @RequestParam(value = "lpaId") Long lpaId,
                               @RequestParam(value = "inquery", required = false) Boolean inquery,
                               Model model) {
        LpaMesinLampiranDto formBean = new LpaMesinLampiranDto();
        if (id != null) {
            formBean = lpaFacade.getLampiran(id);
        }
        formBean.setIdLpaMesin(lpaId);
        model.addAttribute("formBean", formBean);
        model.addAttribute("inquery", inquery == null ? false : inquery);
        initReferenceLampiran(model);
        return DIR_VIEW + "form-lampiran";
    }

    @RequestMapping(value = {"save-lampiran"}, method = RequestMethod.POST)
    public String saveKendaraan(@ModelAttribute("formBean") @Valid LpaMesinLampiranDto formBean, BindingResult result, Model model,
                                @ModelAttribute("person") PersonLoginDto person) {
        if (result.hasErrors()) {
            initReferenceLampiran(model);
            return DIR_VIEW + "form-lampiran";
        }
        try{
            lpaFacade.saveLampiran(formBean,person.getUserId());
        } catch (Exception e) {
            return "error-redirect-parent-list";
        }
        return "success-close-parent-modal";
    }

    @RequestMapping(value = {"delete-lampiran-mesin"}, method = RequestMethod.GET)
    public String deleteLamMesin(@RequestParam("id") Long id, @RequestParam("idLpaMesin") Long idLpaMesin) {
        try {

            logger.debug("IdLampiran nyeeeeee: "+id);

            lpaFacade.deleteLampiran(id);
        } catch (Exception e) {
            logger.debug("Exception IdLampiran: "+e.getMessage());
            e.printStackTrace();
        }
        return "redirect:show-lampiran?idLpaMesin=" + idLpaMesin;
    }




    private void initCreateLpa(Model model, LpaMesinDto formBean) {
        model.addAllAttributes(facade.getReference(DataCommonDto.StatusData.COMPLETE, null));
        model.addAttribute("formBean", formBean);
        model.addAttribute("listFaktorMarket", parameterFacade.findLvByGroup(AppConstans.GroupParameter.MESIN_FAKTOR_MARKETABILITY));
        model.addAttribute("listNegative", parameterFacade.findLvByGroup(AppConstans.GroupParameter.MESIN_NEGATIVE_LIST));
        model.addAttribute("listPertimbangan", parameterFacade.findLvByGroup(AppConstans.GroupParameter.MESIN_PERTIMBANGAN));
        model.addAttribute("listFaktorUnMarket", parameterFacade.findLvByGroup(AppConstans.GroupParameter.MESIN_FAKTOR_UNMARKETABILITY));
        model.addAttribute("listSurveyor", personFacade.findLvSurveyor());
        model.addAttribute("listMataUang", mataUangFacade.list(new MataUangDto()));
        model.addAttribute("listJenisPenilaianFisik", penilaianFacade.listByKategoriAktiva(formBean.getKategoriObjectPenilaian()));
        model.addAttribute("listAppraisalCabang", branchFacade.findLv(new AppraisalBranchDto()));
        model.addAttribute("listTempat", parameterFacade.findLvByGroup(AppConstans.GroupParameter.MESIN_TEMPAT));
        model.addAttribute("listKondisi", parameterFacade.findLvByGroup(AppConstans.GroupParameter.MESIN_KONDISI_TEMPAT));
        model.addAttribute("listLingkungan", parameterFacade.findLvByGroup(AppConstans.GroupParameter.MESIN_LINGKUNGAN_SEKITAR));
        model.addAttribute("listTeknisi", parameterFacade.findLvByGroup(AppConstans.GroupParameter.MESIN_TEKNISI));
        model.addAttribute("listCukuCadang", parameterFacade.findLvByGroup(AppConstans.GroupParameter.MESIN_SUKU_CADANG));
        model.addAttribute("listLokasiAktiva", parameterFacade.findLvByGroup(AppConstans.GroupParameter.POSISI_LOKASI_AKTIVA));
        model.addAttribute("listCatatanKewajaran", parameterFacade.findLvByGroup(AppConstans.GroupParameter.CATATAN_KEWAJARAN));
        model.addAttribute("listCatatanKesesuaian", parameterFacade.findLvByGroup(AppConstans.GroupParameter.CATATAN_KESESUAIAN));
        if (formBean.isEksternal()) {
            model.addAttribute("listOpini", parameterFacade.findLvByGroup(AppConstans.GroupParameter.OPINI_INTERNAL_ATAS_EKSTERNAL));
            model.addAttribute("listKjpp", kjppFacade.findLv());
        } else {
            model.addAttribute("listSurveyor", personFacade.findLvSurveyor());
        }
    }

    private void initReferenceLampiran(Model model){
        model.addAttribute("listKondisi", parameterFacade.findLvByGroup(AppConstans.GroupParameter.MESIN_KONDISI));
        model.addAttribute("listRangkaian", parameterFacade.findLvByGroup(AppConstans.GroupParameter.MESIN_RANGKAIAN));
        model.addAttribute("listPenggunaan", parameterFacade.findLvByGroup(AppConstans.GroupParameter.MESIN_PENGGUNAAN));
    }

    @RequestMapping (value = "laporan-lpa-mesin", method = RequestMethod.GET)
    public ModelAndView laporanLpaMesin(@RequestParam ("PIdLpaUmum") Long PIdLpaUmum, @RequestParam ("lpa") String lpa){
        Map<String, Object> parameterMap = new HashMap<String, Object>();
        String jasperDir = String.format("%s%s%s%s%s%s", context.getRealPath(""), File.separator, "WEB-INF",
                File.separator, "report", File.separator);
        parameterMap.put("SUBREPORT_DIR", jasperDir);
        parameterMap.put("datasource", dataSource);
        parameterMap.put("PIdLpaUmum", PIdLpaUmum);
        if(lpa.equals("lpa-int")) {
            return new ModelAndView("pdfLaporanLpaMesin", parameterMap);
        }else if(lpa.equals("lpa-ext")){
            return new ModelAndView("pdfLaporanLpaMesinExt", parameterMap);
        }else if(lpa.equals("lampiran")){
            return new ModelAndView("pdfLaporanLpaMesinLampiran", parameterMap);
        }else if(lpa.equals("img-survey")){
            return new ModelAndView("pdfLaporanLpaImgSurvey", parameterMap);
        }else if(lpa.equals("img-peta")){
            return new ModelAndView("pdfLaporanLpaImgPeta", parameterMap);
        }else if(lpa.equals("img-other")){
            return new ModelAndView("pdfLaporanLpaMesinLampiran", parameterMap);
        }else{
            return null;
        }
    }


}