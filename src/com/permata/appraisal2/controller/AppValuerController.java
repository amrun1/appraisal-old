package com.permata.appraisal2.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.permata.appraisal2.dto.*;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.permata.appraisal2.common.AppConstans;
import com.permata.appraisal2.common.BaseController;
import com.permata.appraisal2.common.DataTables;
import com.permata.appraisal2.common.StatusAjaxResponse;
import com.permata.appraisal2.facade.AppraisalBranchFacade;
import com.permata.appraisal2.facade.DataCommonFacade;
import com.permata.appraisal2.facade.DesktopNilaiTanahFacade;
import com.permata.appraisal2.facade.DocumentLpaTypeFacade;
import com.permata.appraisal2.facade.DocumentTypeFacade;
import com.permata.appraisal2.facade.FlowListFacade;
import com.permata.appraisal2.facade.JenisPenilaianFacade;
import com.permata.appraisal2.facade.KjppFacade;
import com.permata.appraisal2.facade.LpaFacade;
import com.permata.appraisal2.facade.LpaUmumFacade;
import com.permata.appraisal2.facade.MataUangFacade;
import com.permata.appraisal2.facade.ParameterFacade;
import com.permata.appraisal2.facade.PersonFacade;
import com.permata.appraisal2.facade.SurveyBvFacade;
import com.permata.appraisal2.facade.SurveyRvFacade;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

@Controller
@RequestMapping("app-valuer")
@SessionAttributes({"person", "roleActive"})
public class AppValuerController extends BaseController {

    private String POSISI = AppConstans.TaskPosition.APP_VALUER;
    private final String DIR_VIEW = "app-valuer/";

    @Autowired
    private LpaUmumFacade lpaUmumFacade;


    @Autowired
    private ServletContext context;
    @Autowired
    private DataCommonFacade facade;
    @Autowired
    private FlowListFacade flowListFacade;
    @Autowired
    private ParameterFacade parameterFacade;
    @Autowired
    private AppraisalBranchFacade branchFacade;
    @Autowired
    private DocumentTypeFacade documentFacade;
    @Autowired
    private LpaFacade lpaFacade;
    @Autowired
    private MataUangFacade mataUangFacade;
    @Autowired
    private DocumentLpaTypeFacade documentLpaTypeFacade;
    @Autowired
    private KjppFacade kjppFacade;
    @Autowired
    private JenisPenilaianFacade penilaianFacade;
    @Autowired
    private PersonFacade personFacade;
    @Autowired
    private SurveyBvFacade surveyBvFacade;
    @Autowired
    private SurveyRvFacade surveyRvFacade;

    @Autowired
    private DesktopNilaiTanahFacade facadeNilaiTanah;

    @Autowired
    // @Qualifier("dataSource")
    private BasicDataSource dataSource;

    @Autowired
    ServletContext servletContext;


    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(@RequestParam(value = "task", required = false) String task,
                       @ModelAttribute("person") PersonLoginDto person, Model model) {
        model.addAttribute("task", StringUtils.isEmpty(task) ? person.getUserId() : task);
        model.addAttribute("formBean", new DataCommonTableDto());
        return DIR_VIEW + "list";
    }

    @RequestMapping(value = "list-error", method = RequestMethod.GET)
    public String errorSavingBackToList(@RequestParam(value = "task", required = false) String task,
                       @ModelAttribute("person") PersonLoginDto person, Model model) {
        model.addAttribute("flashMessage", "Gagal : Posisi Order Tidak Pada Tasklist Anda");
        model.addAttribute("css", "error");
        model.addAttribute("task", StringUtils.isEmpty(task) ? person.getUserId() : task);
        model.addAttribute("formBean", new DataCommonTableDto());
        return DIR_VIEW + "list";
    }

    @RequestMapping(value = "find", method = RequestMethod.GET)
    @ResponseBody
    public DataTables<DataCommonDto> find(DataCommonDto dto, @ModelAttribute("person") PersonLoginDto person,
                                          @ModelAttribute("roleActive") PersonRoleDto role,
                                          @RequestParam(value = "task") String task) {
        dto.setPosition(POSISI);
        dto.setTaskList(StringUtils.isEmpty(task) ? person.getUserId() : task);
        dto.setAppBranchId(role.getAppBranchId());

        dto.setCreatedBy(person.getUserId());
        dto.setAdminBy(person.getUserId());


        return facade.findForValuerAndApprove(dto, getPagingInfo(), getOrder(),person.getUserId()).cloneToDataTable();
    }

    @RequestMapping(value = "detail-survey", method = RequestMethod.GET)
    public String survey(Model model, @RequestParam(value = "id") Long id,
                         @RequestParam(value = "comminId") Long comminId,
                         @RequestParam(value = "inquery", required = false) Boolean inquery) {
        model.addAttribute("comminId", comminId);
        model.addAttribute("inquery", inquery == null ? false : inquery);
        AppValuerDataCommonDto formBean = facade.getByValuer(comminId);
        model.addAttribute("formBean", formBean);
        model.addAttribute("sendToMobile", facade.getSendToMoble(formBean.getOrderNumber()));
        return DIR_VIEW + "detail-survey";
    }

    @RequestMapping(value = "save-survey", method = RequestMethod.POST)
    public String addSurvey(@ModelAttribute("formBean") AppValuerDataCommonDto formBean,
                            @ModelAttribute("person") PersonLoginDto person,
                            final RedirectAttributes redirectAttributes) {
        try {
            facade.saveSurvey(formBean, person);
            return "redirect:view?id=" + formBean.getId() + "#header-survey";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("flashMessage", e.getMessage());
            return "redirect:detail-survey?id="+formBean.getDetail().getId()+"&comminId=" + formBean.getId();
        }
    }

    @RequestMapping(value = {"view"}, method = RequestMethod.GET)
    public String view(Model model, @RequestParam(value = "id") Long id,
                       @ModelAttribute("person") PersonLoginDto person,
                       @ModelAttribute("formBean") AppValuerDataCommonDto formBean) {
        formBean = facade.getByValuer(id);
        logger.debug("Jenis nya kategori nya: "+formBean.getDetail().getCategoryName());
        model.addAttribute("formBean", formBean);
        model.addAttribute("categoryReject", parameterFacade.findLvByGroup(AppConstans.GroupParameter.REJECT_CATEGORY));
        if (formBean.getTaskList().equals(person.getUserId())) {
            FlowListDto flowListDto = new FlowListDto();
            flowListDto.setFrom(POSISI);
            model.addAttribute("listFlowList", flowListFacade.list(flowListDto));
        }
        return DIR_VIEW + "view";
    }

    @RequestMapping(value = {"claim"}, method = RequestMethod.POST)
    public String claim(@ModelAttribute("person") PersonLoginDto person,
                        @ModelAttribute("formBean") AppValuerDataCommonDto formBean,
                        final RedirectAttributes redirectAttributes) {
        logger.debug("formBean : " + formBean);
        logger.debug("posisi sebelumnya " + formBean.getLastMessage().getPosition());
        try {
            facade.claimByValuer(formBean.getId(), person.getUserId());
            redirectAttributes.addFlashAttribute("flashMessage", "Claim data success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("css", "error");
            redirectAttributes.addFlashAttribute("flashMessage", e.getMessage());
        }
        return "redirect:list";
    }

    @RequestMapping(value = {"submit"}, method = RequestMethod.POST)
    public String submit(@ModelAttribute("formBean") DataCommonDto formBean,
                         @RequestParam("sendTo") String sendTo,
                         final RedirectAttributes redirectAttributes,
                         @ModelAttribute("person") PersonLoginDto person) {
        try {
            facade.submitToApproval(formBean, formBean.getMessage(), person.getUserId(), sendTo);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("flashMessage", e.getMessage());
            redirectAttributes.addFlashAttribute("css", "error");
            return "redirect:list";
        }
        redirectAttributes.addFlashAttribute("flashMessage", "Submit data success");
        return "redirect:list";
    }

    @RequestMapping(value = {"reject"}, method = RequestMethod.POST)
    public String reject(@ModelAttribute("formBean") DataCommonDto formBean,
                         @RequestParam("rejectTo") String rejectTo,
                         @RequestParam("rejectCategory") String rejectCategory,
                         final RedirectAttributes redirectAttributes,
                         @ModelAttribute("person") PersonLoginDto person) {
        Boolean status = facade.rejectFromValuer(formBean.getId(), rejectTo, rejectCategory.concat(" - ").concat(formBean.getMessage()), person.getUserId());
        if(status) {
            redirectAttributes.addFlashAttribute("flashMessage", "Reject data success");
        } else {
            redirectAttributes.addFlashAttribute("flashMessage", "Reject data success, Gagal Kirim ke Sumber(PLUS/ACL)");
            redirectAttributes.addFlashAttribute("css", "error");
        }
        return "redirect:list";
    }

    @RequestMapping(value = {"create-lpa"}, method = RequestMethod.GET)
    public String createLpa(@RequestParam("commonId") Long commonId, Model model) {
        LpaInternalCreateDto formBean = lpaFacade.createTanahBangungan(commonId);
        model.addAttribute("formBean", formBean);
        model.addAttribute("disableDraft", Boolean.TRUE);
        initCreateLpa(model, formBean);
        return DIR_VIEW + "lpa-lnb-internal";
    }

    @RequestMapping(value = {"edit-lpa"}, method = RequestMethod.GET)
    public String editLpa(@RequestParam("id") Long id, Model model) {

        LpaInternalCreateDto formBean = lpaFacade.get(id);


//        for (int i =0; i<formBean.getListDokumenTanah().size();i++) {
//            logger.info("tanggal berakhirnya " + ParserUtils.parseToCalendar(formBean.getListDokumenTanah().get(i).getTanggalBerakhirHak()).getTime());
//        }
        model.addAttribute("formBean", formBean);
        model.addAttribute("disableDraft", formBean.getDraft());
        initCreateLpa(model, formBean);
        return DIR_VIEW + "lpa-lnb-internal";
    }


    private String checkMandatoryImage(Map<Long,String> source,List<String> target){
        StringBuilder builder = new StringBuilder();
        List<Long> listDel = new ArrayList<Long>();
        if (source != null && !source.isEmpty()){
            for (Map.Entry<Long, String> entry : source.entrySet()) {
                String nama = entry.getValue();
                Long key = entry.getKey();
                for (String nameTarget: target){
                    if (nama.equalsIgnoreCase(nameTarget)){
                        listDel.add(key);
                    }
                }
            }
        }
        if (listDel != null && !listDel.isEmpty()){
            for (Long i : listDel){
                source.remove(i);
            }
        }

        if (source!=null && !source.isEmpty()){
            for (Map.Entry<Long, String> entry : source.entrySet()) {
                builder.append(entry.getValue().concat(", "));
            }
        }
        return builder.toString();
    }

    @RequestMapping(value = {"save-lpa"}, method = RequestMethod.POST)
    public String saveLpa(@ModelAttribute("formBean") @Valid LpaInternalCreateDto formBean, BindingResult result, Model model,
                          @ModelAttribute("person") PersonLoginDto person) {
        boolean next = true;

        //chek validasi gambar
        Map<Long,String> source = documentLpaTypeFacade.findLvForLpaMandatory(formBean.getJenisAktiva(), null);

        List<LpaImageDto> listDto = lpaFacade.findImage(null,formBean.getIdLpa());


        List<String> target = new ArrayList<String>();


        if (listDto != null && !listDto.isEmpty()){
            for (LpaImageDto img : listDto){

                 target.add(img.getNamaDocumentLpa());

            }
        }

        String hasil = checkMandatoryImage(source, target);

        if (!hasil.equals("")){
            logger.debug("lewat sini"+hasil);
            initCreateLpa(model, formBean);
            model.addAttribute("errorImage",hasil);
            return DIR_VIEW + "lpa-lnb-internal";
        }

        formBean.validate(result);

        if (result.hasErrors()) {
            next = false;
        }
        if (next) {
            formBean.validate(result);
            if (result.hasErrors()) {
                next = false;
            }
        }

        if (!next) {
            initCreateLpa(model, formBean);
            return DIR_VIEW + "lpa-lnb-internal";
        }
//        if(facade.checkUserTasklist(person.getUserId(), formBean.getIdLpa(), null)) {

            formBean.setDraft(false);
            Double luastanah = formBean.getLuasTanahSesuaiTataKota() == null ? 0d : formBean.getLuasTanahSesuaiTataKota();
            Double prosesKontruksi = formBean.getProsesKonstruksi() == null ? 0d : formBean.getProsesKonstruksi();
            formBean.setProsesKonstruksi(prosesKontruksi);
            formBean.setLuasTanahSesuaiTataKota(luastanah);
            try {
//                if (facade.checkUserTasklist(person.getUserId(), formBean.getIdLpa(), null))
                    formBean = lpaFacade.saveTanahBangunan(formBean, person.getUserId());
            } catch (Exception e) {
                return "redirect:/app-valuer/list-error";
            }
//        }else{
//            return "redirect:/app-valuer/list-error";
//        }

        return "redirect:view?id=" + formBean.getCommonId();
    }

    @RequestMapping(value = {"draft-lpa"}, method = RequestMethod.POST)
    public String draftLpa(@ModelAttribute("formBean") LpaInternalCreateDto formBean, @ModelAttribute("person") PersonLoginDto person, BindingResult result, Model model) {
        formBean.setDraft(true);
//        for (int i =0; i<formBean.getListDokumenTanah().size();i++) {
//            logger.info("tanggal berakhirnya " + ParserUtils.parseToCalendar(formBean.getListDokumenTanah().get(0).getTanggalBerakhirHak()));
//        }
        try{
            formBean = lpaFacade.saveTanahBangunan(formBean, person.getUserId());
        } catch (Exception e) {
            return "redirect:/app-valuer/list-error";
        }
        logger.debug("formBean : " + formBean);
        return "redirect:view?id=" + formBean.getCommonId();
    }

    @RequestMapping(value = {"form-doc-tanah"}, method = RequestMethod.GET)
    public String formDokumenTanah(@RequestParam(required = false, value = "id") Long id,
                                   @RequestParam(value = "jenisAktiva") Long jenisAktiva,
                                   @RequestParam(value = "lpaCommonId") Long lpaCommonId,
                                   @RequestParam(value = "commonId") Long commonId,
                                   Model model) {
        LpaDokumenTanahDto formBean = new LpaDokumenTanahDto();
        formBean.setCommonId(lpaCommonId);
        if (id != null) {
            formBean = lpaFacade.getTanah(id);
            formBean.setLuasTanahStr(formBean.getLuasTanah().toString());
        }
        model.addAttribute("formBean", formBean);
        model.addAttribute("jenisAktiva", jenisAktiva);
        model.addAttribute("idDataUmum", commonId);
        model.addAttribute("listJenisDokumen", documentFacade.findByAktiva(jenisAktiva));

        List<ReadableDocDto> readableDocTanah = new ArrayList<ReadableDocDto>();
        ReadableDocDto j = new ReadableDocDto("Jelas","Jelas");
        ReadableDocDto j1 = new ReadableDocDto("Tidak Jelas","Tidak Jelas");
        readableDocTanah.add(j);
        readableDocTanah.add(j1);
        model.addAttribute("listReadDocTAnah", readableDocTanah);


        return "lpa/tanah-bangunan/form-doc-tanah";
    }

    @RequestMapping(value = {"save-doc-tanah"}, method = RequestMethod.POST)
    public String saveDokumenTanah(@ModelAttribute("formBean") @Valid LpaDokumenTanahDto formBean, BindingResult result,
                                   @RequestParam(value = "jenisAktiva") Long jenisAktiva,
                                   @RequestParam(value = "idDataUmum") Long idDataUmum,
                                    @ModelAttribute("person") PersonLoginDto person, Model model) {
        String docStr="0";
        if (StringUtils.isNotBlank(formBean.getLuasTanahStr())){
             docStr = formBean.getLuasTanahStr().replace(",","");
        }
        Double s = Double.parseDouble(docStr);
        formBean.setLuasTanah(s);


        boolean next = true;
        if (result.hasErrors()) {
            next = false;
        }
        if (next) {
            formBean.validate(result);
            if (result.hasErrors()) {
                next = false;
            }
        }

        if(facade.checkUserTasklistByCommonId(person.getUserId(), idDataUmum)) {
            if (!next) {
                model.addAttribute("listJenisDokumen", documentFacade.findByAktiva(jenisAktiva));
                model.addAttribute("jenisAktiva", jenisAktiva);
                List<ReadableDocDto> readableDocTanah = new ArrayList<ReadableDocDto>();
                ReadableDocDto jelas = new ReadableDocDto("Jelas", "Jelas");
                ReadableDocDto tidakJelas = new ReadableDocDto("Tidak Jelas", "Tidak Jelas");
                readableDocTanah.add(jelas);
                readableDocTanah.add(tidakJelas);
                model.addAttribute("listReadDocTAnah", readableDocTanah);

                return "lpa/tanah-bangunan/form-doc-tanah";
            }
                lpaFacade.saveDokumenTanah(formBean);
            return "success-close-modal";
        }else{
            return "error-redirect-list";
        }
    }

    @RequestMapping(value = {"save-doc-bangunan"}, method = RequestMethod.POST)
    @ResponseBody
    public StatusAjaxResponse saveDokumenBangunan(@ModelAttribute("formBean") LpaDokumenBangunanDto formBean,
                                                  @RequestParam(value = "idDataUmum") Long idDataUmum, @ModelAttribute("person") PersonLoginDto person) {
        String docStr="0";
        if (StringUtils.isNotBlank(formBean.getLuasBangunanStr())){
            docStr = formBean.getLuasBangunanStr().replace(",","");
        }
        Double s = Double.parseDouble(docStr);
        formBean.setLuasBangunan(s);

        if(facade.checkUserTasklistByCommonId(person.getUserId(), idDataUmum)) {
            formBean = lpaFacade.saveDokumenBangunan(formBean);
            return new StatusAjaxResponse(200, formBean.getId().toString());
        }else{
            return new StatusAjaxResponse(500, "Order Sudah Tidak ada di-TaskList Anda");
        }
    }

    @RequestMapping(value = {"delete-doc-tanah"}, method = RequestMethod.GET)
    public String deleteDokumenTanah(@RequestParam("id") Long id, @RequestParam("commonId") Long commonId, @RequestParam("idTanahUsed") Long idTanahUsed) {
        try {
            lpaFacade.deleteDokumenTanah(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:table-doc-tanah?idTanahBangunan=" + commonId + "&idTanahUsed=" + idTanahUsed;
    }

    @RequestMapping(value = {"delete-lpa"}, method = RequestMethod.GET)
    public String deleteLpa(@RequestParam("id") Long id, @RequestParam("commonId") Long commonId) {
        try {
           // lpaFacade.deleteLpa(id);
            lpaUmumFacade.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:view?id=" + commonId;
    }

    @RequestMapping(value = {"delete-doc-bangunan"}, method = RequestMethod.GET)
    public String deleteDokumenBangunan(@RequestParam("id") Long id, @RequestParam("commonId") Long commonId, @RequestParam("idBangunanUsed") Long idBangunanUsed) {
        try {
            lpaFacade.deleteDokumenBangunan(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:table-doc-bangunan?idTanahBangunan="+ commonId +"&idBangunanUsed="+ idBangunanUsed;
    }

    @RequestMapping(value = {"table-doc-tanah"}, method = RequestMethod.GET)
    public String showDokumenTanah(@RequestParam("idTanahBangunan") Long idTanahBangunan, @RequestParam("idTanahUsed") Long idTanahUsed, Model model) {
        List<LpaDokumenTanahDto> dataList = lpaFacade.listDokumenTanah(idTanahBangunan);
        logger.info("COUNT DOC TANAH "+dataList.size());
        model.addAttribute("dataList", dataList);
        model.addAttribute("countTanahRef", dataList.size());
        model.addAttribute("idTanahUsed", idTanahUsed);
        return DIR_VIEW + "table-doc-tanah";
    }

    @RequestMapping(value = {"table-doc-bangunan"}, method = RequestMethod.GET)
    public String showDokumenBangunan(@RequestParam("idTanahBangunan") Long idTanahBangunan, @RequestParam("idBangunanUsed") Long idBangunanUsed, Model model) {
        List<LpaDokumenBangunanDto> dataList = lpaFacade.listDokumenBangunan(idTanahBangunan);
        model.addAttribute("dataList", dataList);
        model.addAttribute("countBgnRef", dataList.size());
        model.addAttribute("idBangunanUsed", idBangunanUsed);
        return DIR_VIEW + "table-doc-bangunan";
    }

    private void initCreateLpa(Model model, LpaInternalCreateDto formBean) {
        model.addAllAttributes(facade.getReference(DataCommonDto.StatusData.COMPLETE, null));
        // model.addAttribute("listJenisPenilaianFisik", parameterFacade.findLvByGroup(AppConstans.GroupParameter.JENIS_PENILAIAN_FISIK));
        model.addAttribute("listLokasiAktiva", parameterFacade.findLvByGroup(AppConstans.GroupParameter.POSISI_LOKASI_AKTIVA));
//        model.addAttribute("listCategorySlaAgunan", parameterFacade.findLvByGroup(AppConstans.GroupParameter.CATEGORY_SLA_AGUNAN));
        model.addAttribute("listBentukTanah", parameterFacade.findLvByGroup(AppConstans.GroupParameter.BENTUK_TANAH));
        model.addAttribute("listKonturTanah", parameterFacade.findLvByGroup(AppConstans.GroupParameter.KONTUR_TANAH));
        model.addAttribute("listKetinggianTanah", parameterFacade.findLvByGroup(AppConstans.GroupParameter.KETINGGIAN_TANAH));
        model.addAttribute("listPeruntukanBangunan", parameterFacade.findLvByGroup(AppConstans.GroupParameter.PERUNTUKAN_BANGUNAN));
        model.addAttribute("listBatasTanah", parameterFacade.findLvByGroup(AppConstans.GroupParameter.SESUAI_BATAS_TANAH));
        model.addAttribute("listPosisiTanah", parameterFacade.findLvByGroup(AppConstans.GroupParameter.POSISI_TANAH));
        model.addAttribute("listKondisiTanah", parameterFacade.findLvByGroup(AppConstans.GroupParameter.KONDISI_TANAH));
        model.addAttribute("listJenisTanah", parameterFacade.findLvByGroup(AppConstans.GroupParameter.JENIS_TANAH));
        model.addAttribute("listIjinSesuaiImb", parameterFacade.findLvByGroup(AppConstans.GroupParameter.IJIN_SESUAI_IMB));
        model.addAttribute("listStatusAgunan", parameterFacade.findLvByGroup(AppConstans.GroupParameter.STATUS_AGUNAN_DISEWAKAN));
        model.addAttribute("listJenisBangunan", parameterFacade.findLvByGroup(AppConstans.GroupParameter.JENIS_BANGUNAN));
        model.addAttribute("listDipergunakan", parameterFacade.findLvByGroup(AppConstans.GroupParameter.DIGUNAKAN_SEBAGAI));
        model.addAttribute("listDihuni", parameterFacade.findLvByGroup(AppConstans.GroupParameter.DIHUNI_OLEH));
        model.addAttribute("listKelasBangunan", parameterFacade.findLvByGroup(AppConstans.GroupParameter.KELAS_BANGUNAN));
        if (!Objects.equals(formBean.getTypeReport(), AppConstans.JenisLaporan.PROGRESS)) {
            model.addAttribute("listProsesPembangunan", parameterFacade.findParamByGroup(AppConstans.GroupParameter.PROSES_PEMBANGUNAN));
        } else {
            if (Objects.equals(formBean.getJenisBangunan(), AppConstans.JenisBangunan.APARTEMEN)) {
                model.addAttribute("listProsesPembangunan", parameterFacade.findParamByGroup(AppConstans.GroupParameter.PROSES_KONSTRUKSI_APARTEMEN));
            } else {
                model.addAttribute("listProsesPembangunan", parameterFacade.findParamByGroup(AppConstans.GroupParameter.PROSES_KONSTRUKSI));
            }
        }
        model.addAttribute("listStatusPembangunan", parameterFacade.findLvByGroup(AppConstans.GroupParameter.STATUS_BANGUNAN));
        model.addAttribute("listLingkunganSekitar", parameterFacade.findLvByGroup(AppConstans.GroupParameter.LINGKUNGAN_SEKITAR));
        model.addAttribute("listTingkatKepadatan", parameterFacade.findLvByGroup(AppConstans.GroupParameter.KEPADATAN_PENDUDUK));
        model.addAttribute("listTingkatKemacetan", parameterFacade.findLvByGroup(AppConstans.GroupParameter.TINGKAT_KEMACETAN));
        model.addAttribute("listFaktorMarket", parameterFacade.findLvByGroup(AppConstans.GroupParameter.FAKTOR_MARKETABILITY));
        model.addAttribute("listNegative", parameterFacade.findLvByGroup(AppConstans.GroupParameter.NEGATIVE_LIST));
        model.addAttribute("listPertimbangan", parameterFacade.findLvByGroup(AppConstans.GroupParameter.PROPERTI_PERTIMBANGAN));
        model.addAttribute("listFaktorUnMarket", parameterFacade.findLvByGroup(AppConstans.GroupParameter.FAKTOR_UNMARKETABILITY));
        model.addAttribute("listAppraisalCabang", branchFacade.findLv(new AppraisalBranchDto()));
        model.addAttribute("listPeruntukanTanah", parameterFacade.findLvByGroup(AppConstans.GroupParameter.PERUNTUKAN_TANAH));
        model.addAttribute("listOpini", parameterFacade.findLvByGroup(AppConstans.GroupParameter.OPINI_INTERNAL_ATAS_EKSTERNAL));
        model.addAttribute("listCatatanKewajaran", parameterFacade.findLvByGroup(AppConstans.GroupParameter.CATATAN_KEWAJARAN));
        model.addAttribute("listCatatanKesesuaian", parameterFacade.findLvByGroup(AppConstans.GroupParameter.CATATAN_KESESUAIAN));
        model.addAttribute("listMataUang", mataUangFacade.list(new MataUangDto()));
        model.addAttribute("listJenisDokumen", documentFacade.findByAktiva(formBean.getJenisAktiva()));
        model.addAttribute("listJenisPenilaianFisik", penilaianFacade.listByKategoriAktiva(formBean.getKategoriObjectPenilaian()));
        if (formBean.isEksternal()) {
            model.addAttribute("listKjpp", kjppFacade.findLv());
        } else {
            model.addAttribute("listSurveyor", personFacade.findLvSurveyor());
        }

        List<ReadableDocDto> readableDocTanah = new ArrayList<ReadableDocDto>();
        ReadableDocDto j = new ReadableDocDto("Jelas","Jelas");
        ReadableDocDto j1 = new ReadableDocDto("Tidak Jelas","Tidak Jelas");
        ReadableDocDto j2 = new ReadableDocDto("Tidak Ada","Tidak Ada");
        readableDocTanah.add(j);
        readableDocTanah.add(j1);
        readableDocTanah.add(j2);

        model.addAttribute("listReadDocTAnah", readableDocTanah);

        List<LpaDokumenTanahDto> dataListTnh = lpaFacade.listDokumenTanah(formBean.getId());
        model.addAttribute("countTanah", dataListTnh.size());

        List<LpaDokumenBangunanDto> dataListBgn = lpaFacade.listDokumenBangunan(formBean.getId());
        model.addAttribute("countBangunan", dataListBgn.size());

    }

    @RequestMapping(value = {"preview-image"}, method = RequestMethod.GET)
    public String viewImage(@RequestParam("id") Long id, HttpServletResponse response) {
        LpaImageDto dto = lpaFacade.getImage(id);

        Boolean isWsFile = (!dto.getContentType().isEmpty() && dto.getContentType().equalsIgnoreCase("application/vnd.ms-excel"));
        Boolean isPdf = (!dto.getContentType().isEmpty() && dto.getContentType().equalsIgnoreCase("application/pdf"));
        Boolean isPng = (!dto.getContentType().isEmpty() && dto.getContentType().equalsIgnoreCase("image/png"));
        String fileName = StringUtils.isNotBlank(dto.getNamaDocumentLpa())?dto.getNamaDocumentLpa():dto.getContentType();
        return previewImage(response, fileName.concat(isWsFile?".xls":isPdf?".pdf":isPng?".png":".jpg"), dto.getImage(), dto.getContentType());
    }

    @RequestMapping(value = {"table-image"}, method = RequestMethod.GET)
    public String showImage(@RequestParam("surveyId") Long surveyId, @RequestParam("lpaId") Long lpaId, Model model) {
        model.addAttribute("dataList", lpaFacade.findImage(surveyId, lpaId));
        model.addAttribute("lpaId", lpaId);
        return DIR_VIEW + "table-image";
    }

    @RequestMapping(value = {"table-image-other"}, method = RequestMethod.GET)
    public String showImageOther(@RequestParam("lpaId") Long lpaId, Model model) {
        model.addAttribute("listDocOther", lpaFacade.getDocOther(lpaId));
        model.addAttribute("lpaId", lpaId);
        return DIR_VIEW + "table-image-other";
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

    @RequestMapping(value = "form-image", method = RequestMethod.GET)
    public String formImage(@RequestParam(value = "id", required = false) Long id,
                            @RequestParam(value = "lpaId") Long lpaId,
                            @RequestParam(value = "activaId", required = false) Long activaId,
                            @RequestParam(value = "appraisalId", required = false) Long appraisalId,
                            Model model) {
        LpaImageDto formBean;
        if (id != null) {
            formBean = lpaFacade.getImage(id);
        } else {
            formBean = new LpaImageDto();
        }
        formBean.setLpaId(lpaId);
        model.addAttribute("formBean", formBean);
        initImage(model, activaId, appraisalId);
        return DIR_VIEW + "form-image";
    }


    @RequestMapping("lookup-tanah")
    public String lookup(@ModelAttribute("formSearch") DesktopNilaiTanahDto dto) {
        return DIR_VIEW + "lookup-tanah";
    }

    @RequestMapping(value = "find-tanah", method = RequestMethod.GET)
    @ResponseBody
    public DataTables<DesktopNilaiTanahDto> find(@ModelAttribute("formSearch") DesktopNilaiTanahDto dto) {
        return facadeNilaiTanah.find(dto, getPagingInfo()).cloneToDataTable();
    }



    @RequestMapping(value = "save-image", method = RequestMethod.POST)
    public String saveImage(@ModelAttribute("formBean") @Valid LpaImageDto formBean, BindingResult result,
                            @ModelAttribute("person") PersonLoginDto person,
                            @RequestParam(value = "activaId", required = false) Long activaId,
                            @RequestParam(value = "appraisalId", required = false) Long appraisalId, Model model) {

        if (formBean.getImage().length <= 0) {
            initImage(model, activaId, appraisalId);
            model.addAttribute("msgErrorImage", "Lampiran tidak boleh kosong");
            return DIR_VIEW + "form-image";
        }


        if (result.hasErrors()) {
            initImage(model, activaId, appraisalId);
            return DIR_VIEW + "form-image";
        }

        if(facade.checkUserTasklist(person.getUserId(), formBean.getLpaId(), formBean.getCommonId())) {
            LpaImageDto saved = lpaFacade.saveImage(formBean);
            DataCommonDto dataUmum = facade.getByLpaId(formBean.getLpaId());
            DataDetailDto dataDetail = dataUmum.getDetail();

            logger.debug("dataUmum.getAppraisalTypeName: " + dataUmum.getAppraisalTypeName());
            if (saved.getReadFileYn() != null && saved.getReadFileYn() && dataUmum.getAppraisalTypeName().equalsIgnoreCase(AppConstans.JenisAppraisalName.APPRAISAL)) {
                boolean isLnb = false;
                boolean isKendaraan = false;
                boolean isMesin = false;
                ObjectMapper mapper = new ObjectMapper();
                model.addAttribute("wsStatus", true);
                try {
                    if (dataDetail.getCategoryCode().equals(AppConstans.JenisKategoriCode.LNB)) {
                        isLnb = true;
                        if (dataDetail.getAssetTypeName().toLowerCase().contains("apartemen") || dataDetail.getAssetTypeName().toLowerCase().contains("kios")) {
                            model.addAttribute("wsDto", mapper.writeValueAsString(readWorksheetApartemenOrKios(saved.getImage())));
                        } else {
                            model.addAttribute("wsDto", mapper.writeValueAsString(readWorksheetLnb(saved.getImage())));
                        }
                    } else if (dataDetail.getCategoryCode().equals(AppConstans.JenisKategoriCode.KENDARAAN)) {
                        isKendaraan = true;
                        model.addAttribute("wsDto", mapper.writeValueAsString(readWorksheetKendaraan(saved.getImage())));
                    } else if (dataDetail.getCategoryCode().equals(AppConstans.JenisKategoriCode.MESIN)) {
                        isMesin = true;
                        model.addAttribute("wsDto", mapper.writeValueAsString(readWorksheetMesin(saved.getImage())));
                    } else if (dataDetail.getCategoryCode().equals(AppConstans.JenisKategoriCode.VESEL)) {
                        model.addAttribute("wsDto", mapper.writeValueAsString(readWorksheetVessel(saved.getImage())));
                    }
                } catch (IOException e) {
                    model.addAttribute("wsDto", (isLnb ? new WsLnbDto() : isKendaraan ? new WsKendaraanDto() : isMesin ? new WsMesinDto() : new WsVesselDto()));
                }
            } else {
                model.addAttribute("wsStatus", false);
                model.addAttribute("wsDto", new WsLnbDto());

            }


            return DIR_VIEW + "success-image";
        } else {
            return DIR_VIEW + "error-image-auth";
        }
    }

    private WsLnbDto readWorksheetApartemenOrKios(byte[] fileUpload) throws IOException {
        WsLnbDto dto = new WsLnbDto();
        Workbook workbook = null;
        ByteArrayInputStream bis = null;
        try {
            //Get the workbook instance for XLS file
            bis = new ByteArrayInputStream(fileUpload);
            workbook = Workbook.getWorkbook(bis);
            Sheet sheet = workbook.getSheet(0);

            Cell cell = sheet.getCell(10,2);
            dto.setDocTanah(new BigDecimal(cell.getContents()));
            cell = sheet.getCell(17,2);
            dto.setFisikTanah(new BigDecimal(cell.getContents()));
            cell = sheet.getCell(10,6);
            dto.setLikuidasiDocTanah(new BigDecimal(cell.getContents()));
            cell = sheet.getCell(17,6);
            dto.setLikuidasiFisikTanah(new BigDecimal(cell.getContents()));
            dto.setTotalDoc(dto.getDocTanah());
            dto.setTotalFisik(dto.getFisikTanah());
            dto.setTotalLikuidasiDoc(dto.getLikuidasiDocTanah());
            dto.setTotalLikuidasiFisik(dto.getLikuidasiFisikTanah());
            cell = sheet.getCell(10,9);
            dto.setDataPembanding1(cell.getContents());
            cell = sheet.getCell(10,10);
            dto.setDataPembanding2(cell.getContents());
            cell = sheet.getCell(10,11);
            dto.setDataPembanding3(cell.getContents());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(bis!=null)bis.close();
            if(workbook!=null)workbook.close();
        }
        return dto;
    }


    private WsMesinDto readWorksheetMesin(byte[] fileUpload) throws IOException {
        WsMesinDto dto = new WsMesinDto();
        Workbook workbook = null;
        ByteArrayInputStream bis = null;
        try {
            //Get the workbook instance for XLS file
            bis = new ByteArrayInputStream(fileUpload);
            workbook = Workbook.getWorkbook(bis);
            Sheet sheet = workbook.getSheet(0);

            Cell cell = sheet.getCell(4,2);
            dto.setNilaiDokPasar(new BigDecimal(cell.getContents()));
            cell = sheet.getCell(9,2);
            dto.setNilaiFisikPasar(new BigDecimal(cell.getContents()));

            cell = sheet.getCell(4,6);
            dto.setNilaiDokLikuidasi(new BigDecimal(cell.getContents()));
            cell = sheet.getCell(9,6);
            dto.setNilaiFisikLikuidasi(new BigDecimal(cell.getContents()));


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(bis!=null)bis.close();
            if(workbook!=null)workbook.close();
        }
        return dto;
    }



    private WsVesselDto readWorksheetVessel(byte[] fileUpload) throws IOException {
        WsVesselDto dto = new WsVesselDto();
        Workbook workbook = null;
        ByteArrayInputStream bis = null;
        try {
            //Get the workbook instance for XLS file
            bis = new ByteArrayInputStream(fileUpload);
            workbook = Workbook.getWorkbook(bis);
            Sheet sheet = workbook.getSheet(0);

            Cell cell = sheet.getCell(4,2);
            dto.setNilaiUnitSesuaiDok(new BigDecimal(cell.getContents()));
            cell = sheet.getCell(9,2);
            dto.setNilaiUnitSesuaiFisik(new BigDecimal(cell.getContents()));

            cell = sheet.getCell(4,6);
            dto.setNilaiLikuidasiSesuaiDok(new BigDecimal(cell.getContents()));
            cell = sheet.getCell(9,6);
            dto.setNilaiLikuidasiSesuaiFisik(new BigDecimal(cell.getContents()));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(bis!=null)bis.close();
            if(workbook!=null)workbook.close();
        }
        return dto;
    }


    private WsKendaraanDto readWorksheetKendaraan(byte[] fileUpload) throws IOException {
        WsKendaraanDto dto = new WsKendaraanDto();
        Workbook workbook = null;
        ByteArrayInputStream bis = null;
        try {
            //Get the workbook instance for XLS file
            bis = new ByteArrayInputStream(fileUpload);
            workbook = Workbook.getWorkbook(bis);
            Sheet sheet = workbook.getSheet(0);

            Cell cell = sheet.getCell(4,2);
            dto.setNilaiDokPasar(new BigDecimal(cell.getContents()));
            cell = sheet.getCell(9,2);
            dto.setNilaiFisikPasar(new BigDecimal(cell.getContents()));

            cell = sheet.getCell(4,6);
            dto.setNilaiDokLikuidasi(new BigDecimal(cell.getContents()));
            cell = sheet.getCell(9,6);
            dto.setNilaiFisikLikuidasi(new BigDecimal(cell.getContents()));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(bis!=null)bis.close();
            if(workbook!=null)workbook.close();
        }
        return dto;
    }

    private WsLnbDto readWorksheetLnb(byte[] fileUpload) throws IOException {
        WsLnbDto dto = new WsLnbDto();
        Workbook workbook = null;
        ByteArrayInputStream bis = null;
        try {
            bis = new ByteArrayInputStream(fileUpload);
            workbook = Workbook.getWorkbook(bis);
            Sheet sheet = workbook.getSheet(0);

            Cell cell = sheet.getCell(10, 2);
            dto.setDocTanah(new BigDecimal(cell.getContents()));
            cell = sheet.getCell(17, 2);
            dto.setFisikTanah(new BigDecimal(cell.getContents()));

            cell = sheet.getCell(10, 3);
            dto.setDocBangunan(new BigDecimal(cell.getContents()));
            cell = sheet.getCell(17, 3);
            dto.setFisikBangunan(new BigDecimal(cell.getContents()));

            cell = sheet.getCell(10, 4);
            dto.setTotalDoc(new BigDecimal(cell.getContents()));
            cell = sheet.getCell(17, 4);
            dto.setTotalFisik(new BigDecimal(cell.getContents()));

            cell = sheet.getCell(10, 6);
            dto.setLikuidasiDocTanah(new BigDecimal(cell.getContents()));
            cell = sheet.getCell(17, 6);
            dto.setLikuidasiFisikTanah(new BigDecimal(cell.getContents()));

            cell = sheet.getCell(10, 7);
            dto.setLikuidasiDocBangunan(new BigDecimal(cell.getContents()));
            cell = sheet.getCell(17, 7);
            dto.setLikuidasiFisikBangunan(new BigDecimal(cell.getContents()));

            cell = sheet.getCell(10, 8);
            dto.setTotalLikuidasiDoc(new BigDecimal(cell.getContents()));
            cell = sheet.getCell(17, 8);
            dto.setTotalLikuidasiFisik(new BigDecimal(cell.getContents()));

//            HSSFSheet sheet2 = workbook.getSheetAt(1);

            cell = sheet.getCell(10, 35);
            dto.setDataPembanding1(cell.getContents());
            cell = sheet.getCell(10, 36);
            dto.setDataPembanding2(cell.getContents());
            cell = sheet.getCell(10, 37);
            dto.setDataPembanding3(cell.getContents());
//            bis.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(bis!=null)bis.close();
            if(workbook!=null)workbook.close();
        }
        return dto;
    }
//
//    private WsLnbDto readWorksheetLnbOld(byte[] fileUpload) {
//        WsLnbDto dto = new WsLnbDto();
//        try {
//            //Get the workbook instance for XLS file
//            ByteArrayInputStream bis = new ByteArrayInputStream(fileUpload);
//            HSSFWorkbook workbook = new HSSFWorkbook(bis);
//            HSSFSheet sheet = workbook.getSheetAt(0);
//
//            HSSFRow row = sheet.getRow(2);
//            HSSFCell cell = row.getCell(10);
//            dto.setDocTanah(BigDecimal.valueOf(cell.getNumericCellValue()));
//            cell = row.getCell(17);
//            dto.setFisikTanah(BigDecimal.valueOf(cell.getNumericCellValue()));
//
//            row = sheet.getRow(3);
//            cell = row.getCell(10);
//            dto.setDocBangunan(BigDecimal.valueOf(cell.getNumericCellValue()));
//            cell = row.getCell(17);
//            dto.setFisikBangunan(BigDecimal.valueOf(cell.getNumericCellValue()));
//
//            row = sheet.getRow(4);
//            cell = row.getCell(10);
//            dto.setTotalDoc(BigDecimal.valueOf(cell.getNumericCellValue()));
//            cell = row.getCell(17);
//            dto.setTotalFisik(BigDecimal.valueOf(cell.getNumericCellValue()));
//
//            row = sheet.getRow(6);
//            cell = row.getCell(10);
//            dto.setLikuidasiDocTanah(BigDecimal.valueOf(cell.getNumericCellValue()));
//            cell = row.getCell(17);
//            dto.setLikuidasiFisikTanah(BigDecimal.valueOf(cell.getNumericCellValue()));
//
//            row = sheet.getRow(7);
//            cell = row.getCell(10);
//            dto.setLikuidasiDocBangunan(BigDecimal.valueOf(cell.getNumericCellValue()));
//            cell = row.getCell(17);
//            dto.setLikuidasiFisikBangunan(BigDecimal.valueOf(cell.getNumericCellValue()));
//
//            row = sheet.getRow(8);
//            cell = row.getCell(10);
//            dto.setTotalLikuidasiDoc(BigDecimal.valueOf(cell.getNumericCellValue()));
//            cell = row.getCell(17);
//            dto.setTotalLikuidasiFisik(BigDecimal.valueOf(cell.getNumericCellValue()));
//
////            HSSFSheet sheet2 = workbook.getSheetAt(1);
//
//            row = sheet.getRow(35);
//            cell = row.getCell(10);
//            dto.setDataPembanding1(cell.getStringCellValue());
//            row = sheet.getRow(36);
//            cell = row.getCell(10);
//            dto.setDataPembanding2(cell.getStringCellValue());
//            row = sheet.getRow(37);
//            cell = row.getCell(10);
//            dto.setDataPembanding3(cell.getStringCellValue());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return dto;
//    }


    @RequestMapping(value = {"get-status-doc"}, method = RequestMethod.GET)
    @ResponseBody
    public DocumentLpaTypeDto stsDoc(@ModelAttribute("id") String id) {
        DocumentLpaTypeDto dto = documentLpaTypeFacade.get(Long.parseLong(id));
        if (dto.getJenisData()==null || dto.getJenisData().isEmpty()){
            dto.setJenisData("Lain-Lain");
        }
       return dto;

    }


    private void initImage(Model model, Long activaId, Long appraisalId) {
        model.addAttribute("listCategory", documentLpaTypeFacade.findLvForLpa(activaId, appraisalId));
        model.addAttribute("kategoriLpa", !StringUtils.equalsIgnoreCase(documentLpaTypeFacade.getKategoriLpa(activaId), AppConstans.JenisKategoriCode.INSPEKSI));
        model.addAttribute("activaId", activaId);
        model.addAttribute("appraisalId", appraisalId);
    }

    /**
     * @param model
     * @param commondId Data Umum ID
     * @return
     */
    @RequestMapping(value = "form-send-to-mobile", method = RequestMethod.GET)
    public String formSendToMobile(Model model, @RequestParam("commondId") Long commondId,
                                   @RequestParam("orderNumber") String orderNumber) {
        model.addAttribute("sendToMobileObj", facade.getSendToMoble(orderNumber));
        model.addAttribute("commondId", commondId);
        model.addAttribute("listSurveyor", personFacade.findLvSurveyor());
        return DIR_VIEW + "send-to-mobile";
    }

    @RequestMapping(value = "form-send-to-mobile", method = RequestMethod.POST)
    public String sendToMobile(Model model,
                               @RequestParam("commondId") Long commondId,
                               @RequestParam("surveyorId") String surveyorId, @RequestParam("valuerId") String valuerId) {
        facade.sendToMobile(commondId, surveyorId, valuerId);
        return "success-close-modal";
    }

    @RequestMapping(value = "ambil-dari-mobile", method = RequestMethod.GET)
    public String ambilDariMobile(Model model,
                                  @RequestParam("jenisAppraisal") String jenisAppraisal,
                                  @RequestParam("orderNum") String orderNum,
                                  @RequestParam("commonId") Long commonId,
                                  @RequestParam("detailId") Long detailId) {
        // TODO: 5/5/2017 send or ambil aru bv rv
        if (jenisAppraisal.equalsIgnoreCase("bv")) {
            surveyBvFacade.ambilDariMobile(commonId, detailId, orderNum);
        } else if (jenisAppraisal.equalsIgnoreCase("rv")) {
            surveyRvFacade.ambilDariMobile(commonId, detailId, orderNum);
        }
        return "redirect:detail-survey?id=" + detailId + "&comminId=" + commonId;
    }

    @RequestMapping(value = "get-data-param", method = RequestMethod.GET)
    @ResponseBody
    public List<ParameterDto> getDataParam(@RequestParam("jenisBangunan") String jenisBangunan) {
        if (jenisBangunan.equalsIgnoreCase("Apartemen")) {
            return parameterFacade.findParamByGroup(AppConstans.GroupParameter.PROSES_KONSTRUKSI_APARTEMEN);
        } else {
            return parameterFacade.findParamByGroup(AppConstans.GroupParameter.PROSES_KONSTRUKSI);
        }
    }

    @RequestMapping(value = "laporan-lpa", method = RequestMethod.GET)
    public ModelAndView laporanLpa(@RequestParam("PIdLpaUmum") Long PIdLpaUmum, @RequestParam("lpa") String lpa) {
        Map<String, Object> parameterMap = new HashMap<String, Object>();
        String jasperDir = String.format("%s%s%s%s%s%s", context.getRealPath(""), File.separator, "WEB-INF",
                File.separator, "report", File.separator);
        parameterMap.put("SUBREPORT_DIR", jasperDir);
        parameterMap.put("datasource", dataSource);
        parameterMap.put("PIdLpaUmum", PIdLpaUmum);

        if (lpa.equals("lpa-int")) {
            return new ModelAndView("pdfLaporanLpa", parameterMap);
        } else if (lpa.equals("lpa-int-progress")) {
            return new ModelAndView("pdfLaporanLpaProgress", parameterMap);
        } else if (lpa.equals("lpa-ext")) {
            return new ModelAndView("pdfLaporanLpaExt", parameterMap);
        } else if (lpa.equals("lpa-image-survey")) {
            return new ModelAndView("pdfLaporanLpaImgSurvey", parameterMap);
        } else if (lpa.equals("lpa-image-peta")) {
            return new ModelAndView("pdfLaporanLpaImgPeta", parameterMap);
        } else if (lpa.equals("lpa-image-other")) {
            return new ModelAndView("pdfLaporanLpaImgOther", parameterMap);
        } else if (lpa.equals("lpa-desktop")) {
            return new ModelAndView("pdfLaporanLpaDesktop", parameterMap);
        } else if (lpa.equals("lampiran-tanah")) {
            return new ModelAndView("pdfLaporanLpaIntTanah", parameterMap);
        } else if (lpa.equals("lampiran-bangunan")) {
            return new ModelAndView("pdfLaporanLpaIntBangunan", parameterMap);
        } else if (lpa.equals("lampiran-nilai")) {
            return new ModelAndView("pdfLaporanNilai", parameterMap);
        } else {
            return null;
        }
    }

    @RequestMapping(value = "laporan-order-detail", method = RequestMethod.GET)
    public ModelAndView laporanOrderDetail(@RequestParam("PIdDataDetail") Long PIdDataDetail) {
        // TODO: 4/12/2018 conditional report here for inspeksi and others
        Map<String, Object> parameterMap = new HashMap<String, Object>();
        String jasperDir = String.format("%s%s%s%s%s%s", context.getRealPath(""), File.separator, "WEB-INF",
                File.separator, "report", File.separator);
        parameterMap.put("SUBREPORT_DIR", jasperDir);
        parameterMap.put("datasource", dataSource);
        parameterMap.put("PIdDataDetail", PIdDataDetail);

        return new ModelAndView("pdfLaporanOrderDetail", parameterMap);
    }

    @RequestMapping (value = "laporan-survey", method = RequestMethod.GET)
    public ModelAndView laporanSurvey(@RequestParam ("PIdDataDetail") Long PIdDataDetail, @RequestParam ("survey") String survey){
        Map<String, Object> parameterMap = new HashMap<String, Object>();
        String jasperDir = String.format("%s%s%s%s%s%s", context.getRealPath(""), File.separator, "WEB-INF",
                File.separator, "report", File.separator);
        parameterMap.put("SUBREPORT_DIR", jasperDir);
        parameterMap.put("datasource", dataSource);
        parameterMap.put("PIdDataDetail", PIdDataDetail);
        if(survey.equalsIgnoreCase("lnb")) {
            return new ModelAndView("pdfLaporanSurvey", parameterMap);
        }else if (survey.equalsIgnoreCase("kendaraan")){
            return new ModelAndView("pdfLaporanSurveyKendaraan", parameterMap);
        }else if(survey.equalsIgnoreCase("mesin")){
            return new ModelAndView("pdfLaporanSurveyMesin", parameterMap);
        }else if(survey.equalsIgnoreCase("inspeksi")){
            return new ModelAndView("pdfLaporanSurveyInspeksi", parameterMap);
        }else{
            return null;
        }
    }
}