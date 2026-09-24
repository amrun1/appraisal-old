package com.permata.appraisal2.controller;

import com.permata.appraisal2.common.AppConstans;
import com.permata.appraisal2.common.BaseController;
import com.permata.appraisal2.dto.LpaBvDto;
import com.permata.appraisal2.dto.LpaImageDto;
import com.permata.appraisal2.dto.MataUangDto;
import com.permata.appraisal2.dto.PersonLoginDto;
import com.permata.appraisal2.facade.*;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.validation.Valid;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("lpa-bv")
@SessionAttributes({"person", "roleActive"})
public class LpaBvController extends BaseController {

    private final String DIR_VIEW = "lpa/bv/";
    @Autowired
    private LpaBvFacade facade;
    @Autowired
    private ParameterFacade parameterFacade;
    @Autowired
    private AppraisalBranchFacade branchFacade;
    @Autowired
    private MataUangFacade mataUangFacade;
    @Autowired
    private PersonFacade personFacade;
    @Autowired
    private LpaUmumFacade lpaUmumFacade;
    @Autowired
    private LpaFacade lpaFacade;
    @Autowired
    private DocumentLpaTypeFacade documentLpaTypeFacade;

    @Autowired
    // @Qualifier("dataSource")
    private BasicDataSource dataSource;
    @Autowired
    private DataCommonFacade dataCommonFacade;

    @Autowired
    private ServletContext context;

    @RequestMapping(value = {"create-lpa"}, method = RequestMethod.GET)
    public String createLpa(@RequestParam("commonId") Long commonId, Model model) {
        LpaBvDto formBean = facade.create(commonId);
        if (formBean.getLokasi_objek_jam()!=null) {
            String[] jam;
            if(StringUtils.split(formBean.getLokasi_objek_jam(), ":").length<2) {
                jam = StringUtils.split(formBean.getLokasi_objek_jam(), ".");
            } else {
                jam = StringUtils.split(formBean.getLokasi_objek_jam(), ":");
            }
            if(jam!=null && jam.length==2) {
                formBean.setJam(jam[0]);
                formBean.setMenit(jam[1]);
            }
        }
        model.addAttribute("formBean", formBean);
        model.addAttribute("disableDraft", Boolean.TRUE);
        List<String> hour = new ArrayList<String>();
        List<String> minute = new ArrayList<String>();
        for (int i = 0; i <= 23; i++) {
            String jamp = Integer.valueOf(i).toString();
            hour.add(jamp.length() < 2 ? "0".concat(jamp) : jamp);
        }
        for (int i = 0; i < 60; i++) {
            if (i % 15 == 0) {
                String menitp = Integer.valueOf(i).toString();
                minute.add(menitp.length() < 2 ? "0".concat(menitp) : menitp);
            }

        }

        model.addAttribute("listJam", hour);
        model.addAttribute("listMenit", minute);
        initCreateLpa(model);
        return DIR_VIEW + "form";
    }

    @RequestMapping(value = {"draft-lpa"}, method = RequestMethod.POST)
    public String draftLpa(@ModelAttribute("formBean") LpaBvDto formBean, @ModelAttribute("person") PersonLoginDto person) {
        formBean.setDraft(true);
        formBean.setLokasi_objek_jam(formBean.getJam()+":"+formBean.getMenit());
        try{
            formBean = facade.save(formBean, person.getUserId());
        } catch (Exception e) {
            return "redirect:/app-valuer/list-error";
        }
        return "redirect:/app-valuer/view?id=" + formBean.getCommonId();
    }

    @RequestMapping(value = {"save-lpa"}, method = RequestMethod.POST)
    public String saveLpa(@ModelAttribute("formBean") @Valid LpaBvDto formBean, BindingResult result, Model model,
                          @ModelAttribute("person") PersonLoginDto person) {
        boolean next = true;
        if (result.hasErrors()) {
            next = false;
            List<String> hour = new ArrayList<String>();
            List<String> minute = new ArrayList<String>();
            for (int i = 0; i <= 23; i++) {
                String jamp = Integer.valueOf(i).toString();
                hour.add(jamp.length() < 2 ? "0".concat(jamp) : jamp);
            }
            for (int i = 0; i < 60; i++) {
                if (i % 15 == 0) {
                    String menitp = Integer.valueOf(i).toString();
                    minute.add(menitp.length() < 2 ? "0".concat(menitp) : menitp);
                }

            }

            model.addAttribute("listJam", hour);
            model.addAttribute("listMenit", minute);
        }
//        if(next) {
//            formBean.validate(result);
//            if (result.hasErrors()) {
//                next = false;
//            }
//        }
        if (!next) {
            initCreateLpa(model);
            return DIR_VIEW + "form";
        }

        formBean.setDraft(false);
        formBean.setLokasi_objek_jam(formBean.getJam()+":"+formBean.getMenit());
        try{
            formBean = facade.save(formBean, person.getUserId());
        } catch (Exception e) {
            return "redirect:/app-valuer/list-error";
        }
        return "redirect:/app-valuer/view?id=" + formBean.getCommonId();
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

    @RequestMapping(value = {"edit-lpa"}, method = RequestMethod.GET)
    public String editLpa(@RequestParam("id") Long id, Model model) {
        LpaBvDto formBean = facade.getByUmum(id);
        if (formBean.getLokasi_objek_jam()!=null) {
            String[] jam = formBean.getLokasi_objek_jam().split(":");
            formBean.setJam(jam[0]);
            formBean.setMenit(jam[1]);
        }
        model.addAttribute("formBean", formBean);
        model.addAttribute("disableDraft", formBean.getDraft());
        List<String> hour = new ArrayList<String>();
        List<String> minute = new ArrayList<String>();
        for (int i = 0; i <= 23; i++) {
            String jamp = Integer.valueOf(i).toString();
            hour.add(jamp.length() < 2 ? "0".concat(jamp) : jamp);
        }
        for (int i = 0; i < 60; i++) {
            if (i % 15 == 0) {
                String menitp = Integer.valueOf(i).toString();
                minute.add(menitp.length() < 2 ? "0".concat(menitp) : menitp);
            }

        }

        model.addAttribute("listJam", hour);
        model.addAttribute("listMenit", minute);
        initCreateLpa(model);
        return DIR_VIEW + "form";
    }

    @RequestMapping(value = "form-image", method = RequestMethod.GET)
    public String formImage(@RequestParam(value = "id", required = false) Long id,
                            @RequestParam(value = "lpaId") Long lpaId,
                            @RequestParam(value = "appraisalId", required = false) Long appraisalId,
                            @RequestParam(value = "jenisFoto", required = false) Integer jenisFoto,
                            Model model) {
        LpaImageDto formBean;
        if (id != null) {
            formBean = lpaFacade.getImage(id);
        } else {
            formBean = new LpaImageDto();
        }
        formBean.setLpaId(lpaId);
        model.addAttribute("formBean", formBean);
        model.addAttribute("jenisFoto", jenisFoto);
        initImage(model, appraisalId);
        return DIR_VIEW + "form-image";
    }

    @RequestMapping(value = "save-image", method = RequestMethod.POST)
    public String saveImage(@ModelAttribute("formBean") @Valid LpaImageDto formBean, BindingResult result,
                            @RequestParam(value = "appraisalId", required = false) Long appraisalId,
                            @RequestParam(value = "jenisFoto", required = false) Integer jenisFoto,
                            Model model,
                            @ModelAttribute("person") PersonLoginDto person) {
        if (result.hasErrors()) {
            model.addAttribute("jenisFoto", jenisFoto);
            initImage(model, appraisalId);
            return DIR_VIEW + "form-image";
        }
        if (formBean.getImage().length <= 0) {
            initImage(model, appraisalId);
            model.addAttribute("msgErrorImage", "Lampiran tidak boleh kosong");
            return DIR_VIEW + "form-image";
        }
        if (StringUtils.isBlank(formBean.getCatatan())) {
            initImage(model, appraisalId);
            model.addAttribute("msgErrorImage", "Catatan Harus di isi");
            return DIR_VIEW + "form-image";
        }
        if(dataCommonFacade.checkUserTasklist(person.getUserId(), formBean.getLpaId(), formBean.getCommonId())) {
            lpaFacade.saveImage(formBean);
            return "success-close-parent-modal";
        }else{
            return DIR_VIEW + "error-image-auth";
        }
    }

    @RequestMapping(value = {"table-image"}, method = RequestMethod.GET)
    public String showImage(@RequestParam("surveyId") Long surveyId, @RequestParam("lpaId") Long lpaId, Model model) {
        List<LpaImageDto> images = lpaFacade.findImage(surveyId, lpaId);
        List<LpaImageDto> tambahan = new ArrayList<LpaImageDto>();
        LpaImageDto foto1 = new LpaImageDto();
        LpaImageDto foto2 = new LpaImageDto();
        LpaImageDto foto3 = new LpaImageDto();
        LpaImageDto foto4 = new LpaImageDto();
        LpaImageDto foto5 = new LpaImageDto();
        LpaImageDto foto6 = new LpaImageDto();
        LpaImageDto foto7 = new LpaImageDto();
        LpaImageDto foto8 = new LpaImageDto();
        LpaImageDto foto9 = new LpaImageDto();
        LpaImageDto foto10 = new LpaImageDto();
        for (LpaImageDto row : images) {
            if (row.getNamaDocumentLpa().contains("Tambahan")) {
                tambahan.add(row);
            } else if (StringUtils.isNotBlank(row.getCatatan()) && row.getCatatan().contains("foto")) {
                if (row.getCatatan().equalsIgnoreCase("foto1")) {
                    foto1 = row;
                } else if (row.getCatatan().equalsIgnoreCase("foto2")) {
                    foto2 = row;
                } else if (row.getCatatan().equalsIgnoreCase("foto3")) {
                    foto3 = row;
                } else if (row.getCatatan().equalsIgnoreCase("foto4")) {
                    foto4 = row;
                } else if (row.getCatatan().equalsIgnoreCase("foto5")) {
                    foto5 = row;
                } else if (row.getCatatan().equalsIgnoreCase("foto6")) {
                    foto6 = row;
                } else if (row.getCatatan().equalsIgnoreCase("foto7")) {
                    foto7 = row;
                } else if (row.getCatatan().equalsIgnoreCase("foto8")) {
                    foto8 = row;
                } else if (row.getCatatan().equalsIgnoreCase("foto9")) {
                    foto9 = row;
                } else if (row.getCatatan().equalsIgnoreCase("foto10")) {
                    foto10 = row;
                }
            }
        }
        model.addAttribute("foto1", foto1);
        model.addAttribute("foto2", foto2);
        model.addAttribute("foto3", foto3);
        model.addAttribute("foto4", foto4);
        model.addAttribute("foto5", foto5);
        model.addAttribute("foto6", foto6);
        model.addAttribute("foto7", foto7);
        model.addAttribute("foto8", foto8);
        model.addAttribute("foto9", foto9);
        model.addAttribute("foto10", foto10);
        model.addAttribute("dataList", tambahan);
        model.addAttribute("lpaId", lpaId);
        return DIR_VIEW + "view-image";
    }

    @RequestMapping(value = {"delete-image"}, method = RequestMethod.GET)
    public String deleteImage(@RequestParam("id") Long id,
                              @RequestParam("surveyId") Long surveyId,
                              @RequestParam("lpaId") Long lpaId) {
        try {
            lpaFacade.deleteImage(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:table-image?surveyId=" + (surveyId == null ? "" : surveyId) + "&lpaId=" + lpaId;
    }

    private void initCreateLpa(Model model) {
        model.addAttribute("listHubungan1DenganAplikan", parameterFacade.findLvByGroup(AppConstans.GroupParameter.BV_SI1_HUBUNGAN));
        model.addAttribute("listHubungan2DenganAplikan", parameterFacade.findLvByGroup(AppConstans.GroupParameter.BV_SI2_HUBUNGAN));
        model.addAttribute("listHubungan3DenganAplikan", parameterFacade.findLvByGroup(AppConstans.GroupParameter.BV_SI3_HUBUNGAN));
        model.addAttribute("listKegiatanUsaha", parameterFacade.findLvByGroup(AppConstans.GroupParameter.BV_KEGIATAN_USAHA));
        model.addAttribute("listSurveyor", personFacade.findLvSurveyor());
        model.addAttribute("listMataUang", mataUangFacade.list(new MataUangDto()));
        model.addAttribute("listStatusUsaha", parameterFacade.findLvByGroup(AppConstans.GroupParameter.BV_STATUS_KEPEMILIKAN_USAHA));
        model.addAttribute("listStatusLokasi", parameterFacade.findLvByGroup(AppConstans.GroupParameter.BV_STATUS_LOKASI_USAHA));
        model.addAttribute("listFasilitas", parameterFacade.findLvByGroup(AppConstans.GroupParameter.BV_FASILITAS));
        model.addAttribute("listJenisBangunan", parameterFacade.findLvByGroup(AppConstans.GroupParameter.BV_JENIS_BANGUNAN));
        model.addAttribute("listLuasTanah", parameterFacade.findLvByGroup(AppConstans.GroupParameter.BV_LUAS_TANAH));
        model.addAttribute("listLuasBangunan", parameterFacade.findLvByGroup(AppConstans.GroupParameter.BV_LUAS_BANGUNAN));
        model.addAttribute("listJalanTempatUsaha", parameterFacade.findLvByGroup(AppConstans.GroupParameter.BV_JALAN_TEMPAT_USAHA));
        model.addAttribute("listLingkunganSekitar", parameterFacade.findLvByGroup(AppConstans.GroupParameter.BV_LINGKUNGAN_SEKITAR));
        model.addAttribute("listLamaPenempatan", parameterFacade.findLvByGroup(AppConstans.GroupParameter.BV_LAMA_PENEMPATAN));
        model.addAttribute("listJenisUsaha", parameterFacade.findLvByGroup(AppConstans.GroupParameter.BV_JENIS_USAHA));
        model.addAttribute("listOmzetPenjualan", parameterFacade.findLvByGroup(AppConstans.GroupParameter.BV_OMZET_PENJUALAN));
        model.addAttribute("listJumlahKaryawan", parameterFacade.findLvByGroup(AppConstans.GroupParameter.BV_JUMLAH_KARYAWAN));
        model.addAttribute("listDiterimaDi", parameterFacade.findLvByGroup(AppConstans.GroupParameter.BV_DITERIMA_DI));
        model.addAttribute("listFotoPada", parameterFacade.findLvByGroup(AppConstans.GroupParameter.BV_FOTO_PADA));
    }


    private void initImage(Model model, Long appraisalId) {
        model.addAttribute("listCategory", documentLpaTypeFacade.findLvForLpa(null, appraisalId));
        model.addAttribute("appraisalId", appraisalId);
    }

    @RequestMapping (value = "laporan-lpa-bv", method = RequestMethod.GET)
    public ModelAndView laporanLpaBv(@RequestParam ("PIdLpaUmum") Long PIdLpaUmum, @RequestParam ("lpa") String lpa){
        Map<String, Object> parameterMap = new HashMap<String, Object>();
        String jasperDir = String.format("%s%s%s%s%s%s", context.getRealPath(""), File.separator, "WEB-INF",
                File.separator, "report", File.separator);
        parameterMap.put("SUBREPORT_DIR", jasperDir);
        parameterMap.put("datasource", dataSource);
        parameterMap.put("PIdLpaUmum", PIdLpaUmum);
        if(lpa.equals("lpa-bv")) {
            return new ModelAndView("pdfLaporanLpaBv", parameterMap);
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