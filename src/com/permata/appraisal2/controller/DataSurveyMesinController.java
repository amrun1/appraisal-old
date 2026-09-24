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

@Controller
@RequestMapping("survey-mesin")
@SessionAttributes({"person", "roleActive"})
public class DataSurveyMesinController extends BaseController {

    private final String DIR_VIEW = "survey/";

    @Autowired
    private DataCommonFacade dataCommonFacade;

    @Autowired
    private SurveyMesinFacade facade;
    @Autowired
    private ParameterFacade parameterFacade;
    @Autowired
    private DataSurveyFacade surveyFacade;
    @Autowired
    private JenisPenilaianFacade penilaianFacade;
    @Autowired
    private DataDetailFacade detailFacade;
    @Autowired
    private DocumentLpaTypeFacade documentLpaTypeFacade;

    @Autowired
    // @Qualifier("dataSource")
    private BasicDataSource dataSource;

    @Autowired
    private ServletContext context;

    @RequestMapping(value = "show", method = RequestMethod.GET)
    public String show(@RequestParam(value = "detailId") Long detailId,
                       @ModelAttribute("person") PersonLoginDto person, Model model,
                       @RequestParam(value = "inquery", required = false) Boolean inquery) {
        SurveyMesinDto formBean = facade.getByDetail(detailId);
        model.addAttribute("formBean", formBean);
        model.addAttribute("inquery", inquery == null ? false : inquery);
        initReferenceUmum(model, formBean.getCategory(), formBean.getActiva());
        if (formBean.getId() != null) {
            model.addAttribute("listImage", surveyFacade.findImage(formBean.getId()));
        }
        return DIR_VIEW + "data-mesin";
    }

    @RequestMapping(value = "show-mesin-valuer", method = RequestMethod.GET)
    public String showMesinValuer(@RequestParam(value = "detailId") Long detailId,
                       @ModelAttribute("person") PersonLoginDto person, Model model,
                       @RequestParam(value = "inquery", required = false) Boolean inquery) {
        SurveyMesinDto formBean = facade.getByDetail(detailId);
        model.addAttribute("formBean", formBean);
        model.addAttribute("inquery", inquery == null ? false : inquery);
        initReferenceUmum(model, formBean.getCategory(), formBean.getActiva());
        if (formBean.getId() != null) {
            model.addAttribute("listImage", surveyFacade.findImage(formBean.getId()));
        }
        return DIR_VIEW + "data-mesin-valuer";
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(@ModelAttribute("formBean") SurveyMesinDto dto, BindingResult result,
                       @ModelAttribute("person") PersonLoginDto person, Model model,
                       final RedirectAttributes redirectAttributes) {
        initReferenceUmum(model, dto.getCategory(), dto.getActiva());
        boolean edit = dto.getId() != null;
        facade.save(dto);
        if (edit) {
            redirectAttributes.addFlashAttribute("msg", "Data updated successfully!");
        } else {
            redirectAttributes.addFlashAttribute("msg", "Data added successfully!");
        }
        return "redirect:show?detailId="+dto.getDetailId();
    }

    @RequestMapping(value = "show-lampiran", method = RequestMethod.GET)
    public String showLampiran(@RequestParam(value = "idSurveyMesin") Long idSurveyMesin,
                               @ModelAttribute("person") PersonLoginDto person, Model model,
                               @RequestParam(value = "inquery", required = false) Boolean inquery) {
        List<SurveyMesinLampiranDto> listData = facade.findLampiran(idSurveyMesin);
        model.addAttribute("listData", listData);
        model.addAttribute("inquery", inquery == null ? false : inquery);
        return DIR_VIEW + "list-lampiran-mesin";
    }

    @RequestMapping(value = "show-lampiran-valuer", method = RequestMethod.GET)
    public String showLampiranValuer(@RequestParam(value = "idSurveyMesin") Long idSurveyMesin,
                               @ModelAttribute("person") PersonLoginDto person, Model model,
                               @RequestParam(value = "inquery", required = false) Boolean inquery) {
        List<SurveyMesinLampiranDto> listData = facade.findLampiran(idSurveyMesin);
        model.addAttribute("listData", listData);
        model.addAttribute("inquery", inquery == null ? false : inquery);
        return DIR_VIEW + "list-lampiran-mesin-valuer";
    }

    @RequestMapping(value = "form-lampiran", method = RequestMethod.GET)
    public String formLampiran(@RequestParam(value = "id", required = false) Long id,
                               @RequestParam(value = "idSurveyMesin") Long idSurveyMesin,
                               @RequestParam(value = "inquery", required = false) Boolean inquery,
                               Model model) {
        SurveyMesinLampiranDto formBean = new SurveyMesinLampiranDto();
        if (id != null) {
            formBean = facade.getLampiran(id);
        }
        formBean.setIdSurveyMesin(idSurveyMesin);
        model.addAttribute("formBean", formBean);
        model.addAttribute("inquery", inquery == null ? false : inquery);
        initReferenceLampiran(model);
        return DIR_VIEW + "data-lampiran-mesin";
    }

    @RequestMapping(value = "form-lampiran-valuer", method = RequestMethod.GET)
    public String formLampiranValuer(@RequestParam(value = "id", required = false) Long id,
                               @RequestParam(value = "idSurveyMesin") Long idSurveyMesin,
                               @RequestParam(value = "inquery", required = false) Boolean inquery,
                               Model model) {
        SurveyMesinLampiranDto formBean = new SurveyMesinLampiranDto();
        if (id != null) {
            formBean = facade.getLampiran(id);
        }
        formBean.setIdSurveyMesin(idSurveyMesin);
        model.addAttribute("formBean", formBean);
        model.addAttribute("inquery", inquery == null ? false : inquery);
        initReferenceLampiran(model);
        return DIR_VIEW + "data-lampiran-mesin-valuer";
    }


    @RequestMapping(value = "save-lampiran", method = RequestMethod.POST)
    public String saveLampiran(@ModelAttribute("formBean") @Valid SurveyMesinLampiranDto dto, BindingResult result,
                               @ModelAttribute("person") PersonLoginDto person, Model model) {
        if (result.hasErrors()) {
            initReferenceLampiran(model);
            return DIR_VIEW + "data-lampiran-mesin";
        }
        facade.saveLampiran(dto);
        return "success-close-parent-modal";
    }

    @RequestMapping(value = "delete-lampiran", method = RequestMethod.GET)
    public String deleteLampiran(@RequestParam(value = "id") Long id,
                                 @RequestParam(value = "idSurveyMesin") Long idSurveyMesin,
                                 @RequestParam(value = "inquery", required = false) Boolean inquery) {
        try {
            facade.deleteLampiran(id);
        } catch (Exception e) {

        }
        return "redirect:show-lampiran?idSurveyMesin="+idSurveyMesin;
    }

    @RequestMapping(value = "data-image", method = RequestMethod.GET)
    public String showImage(@RequestParam(value = "commonId") Long commonId, Model model,
                            @RequestParam(value = "id", required = false) Long id,
                            @RequestParam(value = "detailId") Long detailId,
                            @RequestParam(value = "appraisalId", required = false) Long appraisalId) {
        DataDetailDto dataDetail = detailFacade.get(detailId);
        SurveyImageDto formBean = new SurveyImageDto(commonId);
        if (id != null) {
            formBean = surveyFacade.getImage(id);
        }
        formBean.setDetailId(detailId);
        model.addAttribute("formBean", formBean);
        initReferenceImage(model, dataDetail.getAssetTypeId(), appraisalId);
        return DIR_VIEW + "data-image";
    }

    @RequestMapping(value = "save-image", method = RequestMethod.POST)
    public String saveDataImage(@ModelAttribute("formBean") @Valid SurveyImageDto dto, BindingResult result,
                                final RedirectAttributes redirectAttributes, Model model,
                                @ModelAttribute("person") PersonLoginDto person) {
        // validator
        if (result.hasErrors()) {
            return DIR_VIEW + "data-image";
        }

        boolean edit = dto.getId() != null;
        // save to db
        if(dataCommonFacade.checkUserTasklist(person.getUserId(), null, dto.getCommonId())) {
            surveyFacade.saveImage(dto);

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
                surveyFacade.deleteImage(id);
                redirectAttributes.addFlashAttribute("msg", "Delete Data success");
            }else{
                return DIR_VIEW + "error-image-auth";
            }
        } catch (Exception e) {
            // do nothing
        }
        return "redirect:show?detailId=" + detailId;
    }

    private void initReferenceUmum(Model model, String category, String aktiva) {
        model.addAttribute("listJenisObject", penilaianFacade.listByKategoriAktiva(category));
        model.addAttribute("listLegative", parameterFacade.findLvByGroup(AppConstans.GroupParameter.MESIN_NEGATIVE_LIST));
        model.addAttribute("listPertimbangan", parameterFacade.findLvByGroup(AppConstans.GroupParameter.MESIN_PERTIMBANGAN));
        model.addAttribute("listTempat", parameterFacade.findLvByGroup(AppConstans.GroupParameter.MESIN_TEMPAT));
        model.addAttribute("listKondisi", parameterFacade.findLvByGroup(AppConstans.GroupParameter.MESIN_KONDISI_TEMPAT));
        model.addAttribute("listLingkungan", parameterFacade.findLvByGroup(AppConstans.GroupParameter.MESIN_LINGKUNGAN_SEKITAR));
        model.addAttribute("listTeknisi", parameterFacade.findLvByGroup(AppConstans.GroupParameter.MESIN_TEKNISI));
        model.addAttribute("listCukuCadang", parameterFacade.findLvByGroup(AppConstans.GroupParameter.MESIN_SUKU_CADANG));
    }

    private void initReferenceLampiran(Model model) {
        model.addAttribute("listKondisi", parameterFacade.findLvByGroup(AppConstans.GroupParameter.MESIN_KONDISI));
        model.addAttribute("listRangkaian", parameterFacade.findLvByGroup(AppConstans.GroupParameter.MESIN_RANGKAIAN));
        model.addAttribute("listPenggunaan", parameterFacade.findLvByGroup(AppConstans.GroupParameter.MESIN_PENGGUNAAN));

    }

    private void initReferenceImage(Model model, Long activaId, Long appraisalId) {
        model.addAttribute("listCategory", documentLpaTypeFacade.findLvForSurvey(activaId, appraisalId));
        model.addAttribute("category", "mesin");
    }

    @RequestMapping (value = "laporan-survey-mesin", method = RequestMethod.GET)
    public ModelAndView laporanSurveyMesin(@RequestParam ("PIdDataDetail") Long PIdDataDetail){
        Map<String, Object> parameterMap = new HashMap<String, Object>();
        String jasperDir = String.format("%s%s%s%s%s%s", context.getRealPath(""), File.separator, "WEB-INF",
                File.separator, "report", File.separator);
        parameterMap.put("SUBREPORT_DIR", jasperDir);
        parameterMap.put("datasource", dataSource);
        parameterMap.put("PIdDataDetail", PIdDataDetail);
        return new ModelAndView("pdfLaporanSurveyMesin", parameterMap);
    }

}