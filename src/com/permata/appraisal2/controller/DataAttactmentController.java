package com.permata.appraisal2.controller;

import com.permata.appraisal2.common.BaseController;
import com.permata.appraisal2.common.StatusAjaxResponse;
import com.permata.appraisal2.dao.DataDetailDao;
import com.permata.appraisal2.dao.common.PagingInfo;
import com.permata.appraisal2.dto.DataAttactmentDto;
import com.permata.appraisal2.dto.PersonLoginDto;
import com.permata.appraisal2.facade.DataAttactmentFacade;
import com.permata.appraisal2.model.DataCommon;
import com.permata.appraisal2.model.DataDetail;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("data-attactment")
@SessionAttributes("person")
public class DataAttactmentController extends BaseController {

    private final String DIR_VIEW = "data/attactment/";

    @Autowired
    private DataAttactmentFacade facade;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Model model,
                       @RequestParam(value = "dataDetailId") Long dataDetailId,
                       @RequestParam(value = "assetTypeId") Long assetTypeId,
                       @RequestParam(value = "dataCommonId") Long dataCommonId,
                       @RequestParam(value = "msg", required = false) String msg,
                       @RequestParam(value = "msgErrorValidate", required = false) String msgErrorValidate
    ) {
        model.addAttribute("dataDetailId", dataDetailId);
        model.addAttribute("assetTypeId", assetTypeId);
        model.addAttribute("dataCommonId", dataCommonId);
        model.addAttribute("msg", msg);
        model.addAttribute("msgErrorValidate", msgErrorValidate);
        model.addAttribute("dataList", facade.find(new DataAttactmentDto(dataDetailId), PagingInfo.createToRetrieveAll()).getDataList());
        model.addAllAttributes(facade.findDetailInfo(dataDetailId));
        return DIR_VIEW + "list";
    }

    @RequestMapping(value = {"add", "edit"}, method = RequestMethod.GET)
    public String form(Model model, @RequestParam(value = "id", required = false) Long id,
                       @RequestParam(value = "dataDetailId") Long dataDetailId,
                       @RequestParam(value = "assetTypeId") Long assetTypeId,
                       @RequestParam(value = "dataCommonId") Long dataCommonId,
                       @ModelAttribute("person") PersonLoginDto person,
                       @ModelAttribute("formBean") DataAttactmentDto formBean) {
        logger.debug("id " + id);
        if (id == null) {
            model.addAttribute("formBean", getDefaultFormBean(formBean, dataDetailId, assetTypeId, dataCommonId));
        } else {
            model.addAttribute("formBean", getDefaultFormBean(facade.get(id), dataDetailId, assetTypeId, dataCommonId));
        }
        logger.debug("formBean -> " + model.containsAttribute("formBean"));
        model.addAllAttributes(facade.getReference(assetTypeId));
        return DIR_VIEW + "form";
    }


    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public StatusAjaxResponse delete(@RequestParam("id") Long id) {
        try {
            facade.delete(id);
            return new StatusAjaxResponse(200, "Delete success");
        } catch (Exception e) {
            return new StatusAjaxResponse(500, e.getMessage());
        }
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(@ModelAttribute("formBean") @Valid DataAttactmentDto dto, BindingResult result, Model model,
                       final RedirectAttributes redirectAttributes, @ModelAttribute("person") PersonLoginDto person) {

        // validator
        if (result.hasErrors()) {
            model.addAllAttributes(facade.getReference(dto.getAssetType()));
            model.addAttribute("formBean", getDefaultFormBean(dto, dto.getDataDetailId(), dto.getAssetType(), dto.getDataCommonId()));
            return DIR_VIEW + "form";
        }

        boolean edit = dto.getId() != null;


        //Buat Validasi Namafile yang tidak boleh sama dlm kurun waktu 6 bln trakhir

        if (!edit) {

            if (dto.getFile().length <= 0) {
                model.addAllAttributes(facade.getReference(dto.getAssetType()));
                model.addAttribute("formBean", getDefaultFormBean(dto, dto.getDataDetailId(), dto.getAssetType(), dto.getDataCommonId()));
                model.addAttribute("msgErrorImage", "Lampiran tidak boleh kosong");
                return DIR_VIEW + "form";
            }

            if (dto.getFile().length > 3145728) {
                model.addAllAttributes(facade.getReference(dto.getAssetType()));
                model.addAttribute("formBean", getDefaultFormBean(dto, dto.getDataDetailId(), dto.getAssetType(), dto.getDataCommonId()));
                model.addAttribute("msgErrorImage", "Ukuran File Maksimum 3MB");
                return DIR_VIEW + "form";
            }

            if(StringUtils.isNotBlank(dto.getName())){
                List<DataDetail> dataDetails = facade.getDataUmumByNameFile(dto.getName());
                if (dataDetails.size() > 0) {
                    String nomorOrders = "";
                    for (int i = 0; i < dataDetails.size(); i++) {
                        DataDetail detail = dataDetails.get(i);
                        if (i > 0) nomorOrders += ", ";
                        nomorOrders += detail.getDataCommon().getOrderNumber();
                    }
                    String alert = "Ada Sertifikat dengan Nomor yang Sama pernah dijaminkan oleh debitur lain dalam kurun waktu 6 bulan terakhir. No Order: " + nomorOrders + " No Attachment: " + dto.getName();
                    model.addAttribute("msgErrorValidate", alert);
                }
            }
        }


        // save to db


        facade.save(dto);
        // model.addAttribute("formBean", facade.save(dto));
        model.addAttribute("count", facade.countByDetail(dto.getDataDetailId()));

//        redirectAttributes.addFlashAttribute("css", "success");
//        if (edit) {
//            redirectAttributes.addFlashAttribute("msg", "Data Attactment Updated Successfully!");
//        } else {
//            redirectAttributes.addFlashAttribute("msg", "Data Attactment Added Successfully!");
//        }


        redirectAttributes.addFlashAttribute("css", "success");
        if (edit) {
            model.addAttribute("msg", "Data Attactment Updated Successfully!");
        } else {
            model.addAttribute("msg", "Data Attactment Added Successfully!");
        }

        // return "redirect:/data-umum/edit?id=" + dto.getDataCommonId() + "#anchor-attactment";
        return DIR_VIEW + "success-save";
        // return "redirect:list?dataCommonId="+dto.getDataCommonId()+"&assetTypeId="+dto.getAssetType()+"&dataDetailId="+dto.getDataDetailId();
    }


    private String downloadForIE8(DataAttactmentDto dto, HttpServletResponse response) {
        ServletOutputStream outputStr = null;
        byte[] tiff = null;
        try {
            tiff = dto.getFile();
            response.setHeader("Content-Disposition", "attachment;filename=\"" + dto.getName() + "." + dto.getExtension() + "\"");
            response.setHeader("Cache-Control", "private, max-age=1"); // HTTP 1.1
            response.setHeader("Pragma", "no-cache"); // HTTP 1.0
            response.setDateHeader("Expires", 0);
            response.setContentLength(tiff.length);
            if (tiff.length > 0) {
                outputStr = response.getOutputStream();
                outputStr.write(tiff, 0, tiff.length);
            }

        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            if(outputStr!=null) {
                try {
                    outputStr.flush();
                    outputStr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        }

        return null;
    }

    @RequestMapping(value = "preview", method = RequestMethod.GET)
    public String download(@RequestParam("id") Long id, HttpServletResponse response) {

        DataAttactmentDto dto = facade.get(id);
        byte[] docBytes = null;
        try {
            String[] arrayFormat = new String[]{"jpg", "jpeg", "bitmap", "pdf"};
            //Buat IE8 yg gbsa download PDF & Tiff...
            if (!(Arrays.asList(arrayFormat).contains(dto.getExtension().toLowerCase()))) {
                downloadForIE8(dto, response);
                return null;
            }
            
            docBytes = dto.getFile();
            response.setHeader("Content-Disposition", "attachment;filename=\"" + dto.getName() + "." + dto.getExtension() + "\"");
            response.reset();
            response.getOutputStream().write(docBytes);
//            response.getOutputStream().flush();
//            response.getOutputStream().close();
            response.flushBuffer();

        } catch (IOException e) {
            logger.error(e, e);
        } finally {
            if(docBytes!=null) {
                try {
                    response.getOutputStream().flush();
                    response.getOutputStream().close();
                    response.flushBuffer();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        }

        return null;
    }

    private DataAttactmentDto getDefaultFormBean(DataAttactmentDto formBean, Long detailId, Long assetTypeId, Long dataCommonId) {
        // setter hardcode
        formBean.setDataCommonId(dataCommonId);
        formBean.setDataDetailId(detailId);
        formBean.setAssetType(assetTypeId);
        return formBean;
    }
}