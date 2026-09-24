package com.permata.appraisal2.controller;


import com.permata.appraisal2.common.AppConstans;
import com.permata.appraisal2.common.BaseController;
import com.permata.appraisal2.dto.*;
import com.permata.appraisal2.facade.*;
import com.permata.appraisal2.model.SurveyCommon;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
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
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("survey-rv")
@SessionAttributes({"person", "roleActive"})
public class DataSurveyRvController extends BaseController {

    private final String DIR_VIEW = "survey/";

    @Autowired
    private DataCommonFacade commonFacade;
    @Autowired
    private DataDetailFacade detailFacade;
    @Autowired
    private SurveyRvFacade facade;
    @Autowired
    private ParameterFacade parameterFacade;
    @Autowired
    private DocumentLpaTypeFacade documentLpaTypeFacade;
    @Autowired
    private DataSurveyFacade surveyFacade;
    @Autowired
    private PersonFacade personFacade;
    @Autowired
    private DataCommonFacade dataCommonFacade;

    @RequestMapping(value = "show", method = RequestMethod.GET)
    public String show(@RequestParam(value = "detailId") Long detailId,
                       @ModelAttribute("person") PersonLoginDto person, Model model,
                       @RequestParam(value = "inquery", required = false) Boolean inquery) {
        SurveyRvDto formBean = facade.getByDetail(detailId);
        model.addAttribute("formBean", formBean);
        initCreate(model);
        model.addAttribute("inquery", inquery == null ? false : inquery);
        return DIR_VIEW + "data-rv";
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(@ModelAttribute("formBean") SurveyRvDto dto, BindingResult result,
                       @ModelAttribute("person") PersonLoginDto person, Model model,
                       final RedirectAttributes redirectAttributes) {
        boolean edit = dto.getId() != null;
        facade.save(dto);
        if (edit) {
            redirectAttributes.addFlashAttribute("msg", "Data updated successfully!");
        } else {
            redirectAttributes.addFlashAttribute("msg", "Data added successfully!");
        }
        return "redirect:show?detailId=" + dto.getDetailId();
    }

    @RequestMapping(value = {"table-image"}, method = RequestMethod.GET)
    public String showImage(@RequestParam("surveyId") Long surveyId, Model model) {
        List<SurveyImageDto> images;
        if(surveyId !=null) images = surveyFacade.findImage(surveyId);
        else images = new ArrayList<SurveyImageDto>();
        SurveyImageDto foto1 = new SurveyImageDto();
        SurveyImageDto foto2 = new SurveyImageDto();
        SurveyImageDto foto3 = new SurveyImageDto();
        SurveyImageDto foto4 = new SurveyImageDto();
        SurveyImageDto foto5 = new SurveyImageDto();
        SurveyImageDto foto6 = new SurveyImageDto();
        SurveyImageDto foto7 = new SurveyImageDto();
        SurveyImageDto foto8 = new SurveyImageDto();
        SurveyImageDto foto9 = new SurveyImageDto();
        SurveyImageDto foto10 = new SurveyImageDto();
        for (SurveyImageDto row : images) {
            if (StringUtils.isNotBlank(row.getCatatan()) && row.getCatatan().contains("foto")) {
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
        return DIR_VIEW + "view-image";
    }

    @RequestMapping(value = "form-image", method = RequestMethod.GET)
    public String formImage(@RequestParam(value = "id", required = false) Long id,
                            @RequestParam(value = "detailId") Long detailId,
                            @RequestParam(value = "jenisFoto") Integer jenisFoto,
                            @ModelAttribute("person") PersonLoginDto person,
                            Model model) {
        DataDetailDto detailDto = detailFacade.get(detailId);
        DataCommonDto commonDto = commonFacade.get(detailDto.getDataCommonId());
        SurveyCommonDto surveyCommon = surveyFacade.getByDetail(detailId, person.getName());
        SurveyImageDto formBean;
        if (id != null) {
            formBean = surveyFacade.getImage(id);
        } else {
            formBean = new SurveyImageDto();
            formBean.setDetailId(detailId);
            formBean.setCommonId(surveyCommon.getId());
            formBean.setCatatan("foto"+jenisFoto);
        }
        model.addAttribute("formBean", formBean);
        model.addAttribute("jenisFoto", jenisFoto);
        model.addAttribute("tipeData", "rv");
        initImage(model, commonDto.getAppraisalTypeId());
        return DIR_VIEW + "form-image";
    }

    @RequestMapping(value = "save-image", method = RequestMethod.POST)
    public String saveImage(@ModelAttribute("formBean") @Valid SurveyImageDto formBean, BindingResult result,
                            @RequestParam(value = "appraisalId", required = false) Long appraisalId,
                            @RequestParam(value = "jenisFoto", required = false) Integer jenisFoto, Model model,
                            @ModelAttribute("person") PersonLoginDto person) {
        if (result.hasErrors()) {
            model.addAttribute("jenisFoto", jenisFoto);
            initImage(model, appraisalId);
            return DIR_VIEW + "form-image";
        }
        if(dataCommonFacade.checkUserTasklist(person.getUserId(), null, formBean.getCommonId())) {
            surveyFacade.saveImage(formBean);
            return "success-close-parent-modal";
        }else{
            return DIR_VIEW + "error-image-auth";
        }
    }

    @RequestMapping(value = {"delete-image"}, method = RequestMethod.GET)
    public String deleteImage(@RequestParam("id") Long id, @RequestParam("surveyId") Long surveyId,
                              @ModelAttribute("person") PersonLoginDto person) {
        try {
            if(dataCommonFacade.checkUserTasklist(person.getUserId(), null, surveyId)) {
                surveyFacade.deleteImage(id);
            }else{
                return DIR_VIEW + "error-image-auth";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:table-image?surveyId=" + surveyId;
    }

    private void initCreate(Model model) {
        model.addAttribute("listUmur", parameterFacade.findLvByGroup(AppConstans.GroupParameter.RV_LIST_UMUR_APLIKAN));
        model.addAttribute("listHubungan1DenganAplikan", parameterFacade.findLvByGroup(AppConstans.GroupParameter.RV_SI1_HUBUNGAN));
        model.addAttribute("listHubungan2DenganAplikan", parameterFacade.findLvByGroup(AppConstans.GroupParameter.RV_SI2_HUBUNGAN));
        model.addAttribute("listHubungan3DenganAplikan", parameterFacade.findLvByGroup(AppConstans.GroupParameter.RV_SI3_HUBUNGAN));
        model.addAttribute("listDiterimaDi", parameterFacade.findLvByGroup(AppConstans.GroupParameter.RV_DITERIMA_DI));
        model.addAttribute("listPenghuni", parameterFacade.findLvByGroup(AppConstans.GroupParameter.RV_PENGHUNI));
        model.addAttribute("listLingkunganSekitar", parameterFacade.findLvByGroup(AppConstans.GroupParameter.RV_LINGKUNGAN_SEKITAR));
        model.addAttribute("listJenisBangunan", parameterFacade.findLvByGroup(AppConstans.GroupParameter.RV_JENIS_BANGUNAN));
        model.addAttribute("listLuasTanah", parameterFacade.findLvByGroup(AppConstans.GroupParameter.RV_LUAS_TANAH));
        model.addAttribute("listLuasBangunan", parameterFacade.findLvByGroup(AppConstans.GroupParameter.RV_LUAS_BANGUNAN));
        model.addAttribute("listStatusKepemilikan", parameterFacade.findLvByGroup(AppConstans.GroupParameter.RV_STATUS_KEPEMILIKAN));
        model.addAttribute("listLamaPenempatan", parameterFacade.findLvByGroup(AppConstans.GroupParameter.RV_LAMA_PENEMPATAN));
        model.addAttribute("listDidatangiDebtCollector", parameterFacade.findLvByGroup(AppConstans.GroupParameter.RV_DATANG_DEBTCOLLECTOR));
        model.addAttribute("listFotoPada", parameterFacade.findLvByGroup(AppConstans.GroupParameter.RV_FOTO_PADA));
        model.addAttribute("listSurveyor", personFacade.findLvSurveyor());
    }

    private void initImage(Model model, Long appraisalId) {
        model.addAttribute("listCategory", documentLpaTypeFacade.findLvForSurvey(null, appraisalId));
        model.addAttribute("appraisalId", appraisalId);
    }

    @RequestMapping(value = "preview", method = RequestMethod.GET)
    public String download(@RequestParam("id") Long id, HttpServletResponse response) {
        SurveyImageDto dto = surveyFacade.getImage(id);
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
}