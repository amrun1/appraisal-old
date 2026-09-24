package com.permata.appraisal2.controller;

import com.permata.appraisal2.common.AppConstans;
import com.permata.appraisal2.common.BaseController;
import com.permata.appraisal2.dto.AppraisalBranchDto;
import com.permata.appraisal2.dto.LpaStockDto;
import com.permata.appraisal2.dto.MataUangDto;
import com.permata.appraisal2.dto.PersonLoginDto;
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
@RequestMapping("lpa-stock")
@SessionAttributes({"person", "roleActive"})
public class LpaStockController extends BaseController {
    private final String DIR_VIEW = "lpa/stock/";

    @Autowired
    private LpaUmumFacade lpaUmumFacade;
    @Autowired
    private LpaStockFacade facade;
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
    private KjppFacade kjppFacade;
    @Autowired
    private JenisPenilaianFacade penilaianFacade;
    @Autowired
    private PersonFacade personFacade;

    @Autowired
    // @Qualifier("dataSource")
    private BasicDataSource dataSource;

    @Autowired
    private ServletContext context;

    @RequestMapping(value = {"create-lpa"}, method = RequestMethod.GET)
    public String createLpa(@RequestParam("commonId") Long commonId, Model model) {
        LpaStockDto formBean = facade.create(commonId);
        model.addAttribute("formBean", formBean);
        model.addAttribute("disableDraft", Boolean.TRUE);
        initCreateLpa(model, formBean);
        return DIR_VIEW + "form";
    }

    @RequestMapping(value = {"draft-lpa"}, method = RequestMethod.POST)
    public String draftLpa(@ModelAttribute("formBean") LpaStockDto formBean, @ModelAttribute("person") PersonLoginDto person) {
        formBean.setDraft(true);
        try{
            formBean = facade.save(formBean, person.getUserId());
        } catch (Exception e) {
            return "redirect:/app-valuer/list-error";
        }
        return "redirect:/app-valuer/view?id=" + formBean.getCommonId();
    }

    @RequestMapping(value = {"save-lpa"}, method = RequestMethod.POST)
    public String saveLpa(@ModelAttribute("formBean") @Valid LpaStockDto formBean,
                          BindingResult result, Model model,
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
            formBean = facade.save(formBean, person.getUserId());
        } catch (Exception e) {
            return "redirect:/app-valuer/list-error";
        }
        return "redirect:/app-valuer/view?id=" + formBean.getCommonId();
    }

    @RequestMapping(value = {"edit-lpa"}, method = RequestMethod.GET)
    public String editLpa(@RequestParam("id") Long id, Model model) {
        LpaStockDto formBean = facade.getByUmum(id);
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

    private void initCreateLpa(Model model, LpaStockDto formBean) {
        model.addAttribute("listLokasiAktiva", parameterFacade.findLvByGroup(AppConstans.GroupParameter.POSISI_LOKASI_AKTIVA));
        model.addAttribute("lvStatusJaminan", statusJaminanFacade.listLv());
        model.addAttribute("listAppraisalCabang", branchFacade.findLv(new AppraisalBranchDto()));
        model.addAttribute("listFaktorMarket", parameterFacade.findLvByGroup(AppConstans.GroupParameter.FAKTOR_MARKETABILITY));
        model.addAttribute("listNegative", parameterFacade.findLvByGroup(AppConstans.GroupParameter.NEGATIVE_LIST_STOCK));
        model.addAttribute("listPertimbangan", parameterFacade.findLvByGroup(AppConstans.GroupParameter.PROPERTI_PERTIMBANGAN_STOCK));
        model.addAttribute("listFaktorUnMarket", parameterFacade.findLvByGroup(AppConstans.GroupParameter.FAKTOR_UNMARKETABILITY_STOCK));
        model.addAttribute("listPenempatanBarang", parameterFacade.findLvByGroup(AppConstans.GroupParameter.PENEMPATAN_BARANG));
        model.addAttribute("listKemasanBarang", parameterFacade.findLvByGroup(AppConstans.GroupParameter.KEMASAN_BARANG));
        model.addAttribute("listKondisiBarang", parameterFacade.findLvByGroup(AppConstans.GroupParameter.KONDISI_BARANG));
        model.addAttribute("listMetodePemeriksaan", parameterFacade.findLvByGroup(AppConstans.GroupParameter.METODE_PEMERIKSAAN));
        model.addAttribute("listJumlahPersediaan", parameterFacade.findLvByGroup(AppConstans.GroupParameter.JUMLAH_PERSEDIAAN));
        model.addAttribute("listTempatPenyimpanan", parameterFacade.findLvByGroup(AppConstans.GroupParameter.TEMPAT_PENYIMPANAN));
        model.addAttribute("listKondisiPenyimpanan", parameterFacade.findLvByGroup(AppConstans.GroupParameter.KONDISI_PENYIMPANAN));
        model.addAttribute("listLingkunganPenyimpanan", parameterFacade.findLvByGroup(AppConstans.GroupParameter.LINGKUNGAN_PENYIMPANAN));
        model.addAttribute("listPeralatanGudang", parameterFacade.findLvByGroup(AppConstans.GroupParameter.PERALATAN_GUDANG));
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

    @RequestMapping (value = "laporan-lpa-stock", method = RequestMethod.GET)
    public ModelAndView laporanLpaStock(@RequestParam ("PIdLpaUmum") Long PIdLpaUmum, @RequestParam ("lpa") String lpa){
        Map<String, Object> parameterMap = new HashMap<String, Object>();
        String jasperDir = String.format("%s%s%s%s%s%s", context.getRealPath(""), File.separator, "WEB-INF",
                File.separator, "report", File.separator);
        parameterMap.put("SUBREPORT_DIR", jasperDir);
        parameterMap.put("datasource", dataSource);
        parameterMap.put("PIdLpaUmum", PIdLpaUmum);
        if(lpa.equals("lpa-stock")) {
            return new ModelAndView("pdfLaporanLpaStock", parameterMap);
        }else if(lpa.equals("lpa-stock-ext")) {
            return new ModelAndView("pdfLaporanLpaStockExt", parameterMap);
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