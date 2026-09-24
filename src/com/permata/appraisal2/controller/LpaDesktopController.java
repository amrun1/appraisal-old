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

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

//@Controller
//@RequestMapping("lpa-desktop")
//@SessionAttributes({"person", "roleActive"})
@Deprecated
public class LpaDesktopController extends BaseController {

    private final String DIR_VIEW = "lpa/desktop/";

    @Autowired
    private LpaDesktopFacade lpaFacade;
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

    @RequestMapping(value = {"create-lpa"}, method = RequestMethod.GET)
    public String createLpa(@RequestParam("commonId") Long commonId, Model model) {
        LpaDesktopDto formBean = lpaFacade.create(commonId);
        initCreateLpa(model, formBean);
        return DIR_VIEW + "form";
    }

    @RequestMapping(value = {"draft-lpa"}, method = RequestMethod.POST)
    public String draftLpa(@ModelAttribute("formBean") LpaDesktopDto formBean) {
        formBean.setDraft(true);
        formBean = lpaFacade.save(formBean);
        return "redirect:/app-valuer/view?id=" + formBean.getCommonId();
    }

    @RequestMapping(value = {"save-lpa"}, method = RequestMethod.POST)
    public String saveLpa(@ModelAttribute("formBean") @Valid LpaDesktopDto formBean, BindingResult result, Model model) {
        if (result.hasErrors()) {
            initCreateLpa(model, formBean);
            return DIR_VIEW + "form";
        }
        formBean.setDraft(false);
        formBean = lpaFacade.save(formBean);
        return "redirect:/app-valuer/view?id=" + formBean.getCommonId();
    }

    @RequestMapping(value = {"edit-lpa"}, method = RequestMethod.GET)
    public String editLpa(@RequestParam("id") Long id, Model model) {
        LpaDesktopDto formBean = lpaFacade.getByUmum(id);
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

    @RequestMapping(value = "get-data-param", method = RequestMethod.GET)
    @ResponseBody
    public List<ParameterDto> getDataParam(@RequestParam("jenisBangunan") String jenisBangunan) {
        if (jenisBangunan.equalsIgnoreCase("Apartemen")) {
            return parameterFacade.findParamByGroup(AppConstans.GroupParameter.PROSES_KONSTRUKSI_APARTEMEN);
        } else {
            return parameterFacade.findParamByGroup(AppConstans.GroupParameter.PROSES_KONSTRUKSI);
        }
    }

    private void initCreateLpa(Model model, LpaDesktopDto formBean) {
        model.addAttribute("formBean", formBean);
        model.addAllAttributes(facade.getReference(DataCommonDto.StatusData.COMPLETE, null));
        model.addAttribute("listLokasiAktiva", parameterFacade.findLvByGroup(AppConstans.GroupParameter.POSISI_LOKASI_AKTIVA));
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
        model.addAttribute("listMataUang", mataUangFacade.list(new MataUangDto()));
        model.addAttribute("listJenisDokumen", documentFacade.findByAktiva(formBean.getJenisAktiva()));
        model.addAttribute("listJenisPenilaianFisik", penilaianFacade.listByKategoriAktiva(formBean.getKategoriObjectPenilaian()));
        model.addAttribute("listSurveyor", personFacade.findLvSurveyor());
    }

}