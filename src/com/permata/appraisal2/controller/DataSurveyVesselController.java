package com.permata.appraisal2.controller;

import com.permata.appraisal2.common.AppConstans;
import com.permata.appraisal2.common.BaseController;
import com.permata.appraisal2.dto.*;
import com.permata.appraisal2.facade.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("survey-vessel")
@SessionAttributes({"person", "roleActive"})
public class DataSurveyVesselController extends BaseController {

    private final String DIR_VIEW = "survey/";

    @Autowired
    private SurveyVesselFacade facade;
    @Autowired
    private DataSurveyFacade surveyFacade;
    @Autowired
    private ParameterFacade parameterFacade;
    @Autowired
    private JenisPenilaianFacade penilaianFacade;

    @Autowired
    private DataDetailFacade detailFacade;

    @Autowired
    private DataCommonFacade dataCommonFacade;

    @Autowired
    private PersonFacade personFacade;


    @Autowired
    private DocumentLpaTypeFacade documentLpaTypeFacade;

    @RequestMapping(value = "show", method = RequestMethod.GET)
    public String show(@RequestParam(value = "detailId") Long detailId,
                       @ModelAttribute("person") PersonLoginDto person, Model model,
                       @RequestParam(value = "inquery", required = false) Boolean inquery) {

        DataDetailDto d = detailFacade.get(detailId);
        DataCommonDto commonDto = dataCommonFacade.get(d.getDataCommonId());
        PersonDto personDto = personFacade.get(commonDto.getTaskList());

        SurveyVesselDto formBean = facade.getByDetail(detailId);
        formBean.setDitinjau(personDto.getName());

        model.addAttribute("formBean", formBean);
        model.addAttribute("inquery", inquery == null ? false : inquery);
        initReferenceUmum(model, formBean.getCategory(), formBean.getActiva());
        if (formBean.getId() != null) {
            model.addAttribute("listImage", surveyFacade.findImage(formBean.getId()));
        }
        return DIR_VIEW + "data-vessel";
    }


    @RequestMapping(value = "show-vessel-valuer", method = RequestMethod.GET)
    public String showVal(@RequestParam(value = "detailId") Long detailId,
                       @ModelAttribute("person") PersonLoginDto person, Model model,
                       @RequestParam(value = "inquery", required = false) Boolean inquery) {

        DataDetailDto d = detailFacade.get(detailId);
        DataCommonDto commonDto = dataCommonFacade.get(d.getDataCommonId());
        PersonDto personDto = personFacade.get(commonDto.getTaskList());

        SurveyVesselDto formBean = facade.getByDetail(detailId);
        formBean.setDitinjau(personDto.getName());

        model.addAttribute("formBean", formBean);
        model.addAttribute("inquery", inquery == null ? false : inquery);
        initReferenceUmum(model, formBean.getCategory(), formBean.getActiva());
        if (formBean.getId() != null) {
            model.addAttribute("listImage", surveyFacade.findImage(formBean.getId()));
        }
        return DIR_VIEW + "data-vessel-valuer";
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
        return DIR_VIEW + "data-image-vessel";
    }

    private void initReferenceImage(Model model, Long activaId, Long appraisalId) {
        model.addAttribute("listCategory", documentLpaTypeFacade.findLvForSurvey(activaId, appraisalId));
//        model.addAttribute("category", "mesin");
    }

    @RequestMapping(value = "save-image", method = RequestMethod.POST)
    public String saveDataImage(@ModelAttribute("formBean") @Valid SurveyImageDto dto, BindingResult result,
                                final RedirectAttributes redirectAttributes, Model model,
                                @ModelAttribute("person") PersonLoginDto person) {
        // validator
        if (result.hasErrors()) {
            return DIR_VIEW + "data-image-vessel";
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


    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(@ModelAttribute("formBean") SurveyVesselDto dto, BindingResult result,
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
        model.addAttribute("listLegative", parameterFacade.findLvByGroup(AppConstans.GroupParameter.NEGATIVE_LIST_VESSEL));
        model.addAttribute("listPertimbangan", parameterFacade.findLvByGroup(AppConstans.GroupParameter.PROPERTI_PERTIMBANGAN_VESSEL));
        model.addAttribute("listKelasKapal", parameterFacade.findLvByGroup(AppConstans.GroupParameter.KELAS_KAPAL_VESSEL));
        model.addAttribute("listStatusAgunanKapal", parameterFacade.findLvByGroup(AppConstans.GroupParameter.STATUS_AGUNAN_VESSEL));
        model.addAttribute("listStatusJaminanKapal", parameterFacade.findLvByGroup(AppConstans.GroupParameter.STATUS_JAMINAN_VESSEL));
        model.addAttribute("listKondisiUmum", parameterFacade.findLvByGroup(AppConstans.GroupParameter.KONDISI_UMUM_VESSEL));
        model.addAttribute("listJenisKapal", parameterFacade.findLvByGroup(AppConstans.GroupParameter.JENIS_VESSEL));

    }
}