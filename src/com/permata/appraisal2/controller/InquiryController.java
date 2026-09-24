package com.permata.appraisal2.controller;

import com.permata.appraisal2.common.AppConstans;
import com.permata.appraisal2.common.BaseController;
import com.permata.appraisal2.common.DataTables;
import com.permata.appraisal2.dto.*;
import com.permata.appraisal2.facade.*;
import com.permata.appraisal2.facade.impl.LpaInspeksiFacadeImpl;
import com.permata.appraisal2.model.AssetType;
import com.permata.appraisal2.model.CategoryType;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.*;

@Controller
@RequestMapping("inquiry")
@SessionAttributes({"person", "roleActive"})
public class InquiryController extends BaseController {

    private final String DIR_VIEW = "data/inquiry/";

    @Autowired
    private StatusJaminanFacade statusJaminanFacade;

    @Autowired
    private DataCommonFacade facade;
    @Autowired
    private JenisAppraisalFacade appraisalFacade;
    @Autowired
    private AppraisalBranchFacade appraisalBranchFacade;
    @Autowired
    private BranchFacade branchFacade;
    @Autowired
    private AssetTypeFacade assetTypeFacade;
    @Autowired
    private CategoryTypeFacade categoryTypeFacade;
    @Autowired
    private ServletContext context;
    @Autowired
    // @Qualifier("dataSource")
    private BasicDataSource dataSource;

    @Autowired
    private LpaFacade lpaFacade;

    @Autowired
    private ParameterFacade parameterFacade;
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
    private LpaRabFacade lpaFacadeRab;


    @Autowired
    private LpaKendaraanFacade facadeKendaraan;

    @Autowired
    private LpaMesinFacade lpaFacadeMesin;


    @Autowired
    private LpaStockFacade facadeStock;

    @Autowired
    private LpaInspeksiFacade facadeInspeksi;

    @Autowired
    private LpaBvFacade facadeBv;


    @Autowired
    private LpaRvFacade facadeRv;


    @Autowired
    private LpaKapalFacade lpaFacadeVessel;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(@ModelAttribute("person") PersonLoginDto person, Model model) {
        model.addAttribute("formBean", new DataCommonTableDto());
        model.addAttribute("listJenisAppraisal", appraisalFacade.findLv());
        model.addAttribute("listCabangBisnis", branchFacade.findLv());
        model.addAttribute("listCabangAppraisal", appraisalBranchFacade.findLv(new AppraisalBranchDto()));
        model.addAttribute("listKategoriAktiva", categoryTypeFacade.findLv());
        model.addAttribute("listJenisAktiva", assetTypeFacade.findLvId());
        return DIR_VIEW + "list";
    }

    @RequestMapping(value = "find", method = RequestMethod.GET)
    @ResponseBody
    public DataTables<DataCommonTableDto> find(DataCommonTableDto dto, @ModelAttribute("person") PersonLoginDto person) {
        dto.setSourceId("null_inquiry");
        return facade.findForTables(getPagingInfo(), dto).cloneToDataTable();
    }

    @RequestMapping(value = "view", method = RequestMethod.GET)
    public String view(@RequestParam ("id") Long id, Model model){
        model.addAttribute("formBean", facade.getByInquery(id));
        return DIR_VIEW + "view";
    }

    @RequestMapping(value = {"table-image-other"}, method = RequestMethod.GET)
    public String showImageOther(@RequestParam("lpaId") Long lpaId, Model model) {
        model.addAttribute("listDocOther", lpaFacade.getDocOther(lpaId));
        model.addAttribute("lpaId", lpaId);
        return DIR_VIEW + "table-image-other";
    }

    @RequestMapping(value = {"edit-lpa"}, method = RequestMethod.GET)
    public String editLpa(@RequestParam("id") Long id, Model model) {
        LpaInternalCreateDto formBean = lpaFacade.get(id);
        model.addAttribute("formBean", formBean);
        initCreateLpa(model, formBean);
        return DIR_VIEW + "lpa-lnb-internal";
    }

    @RequestMapping (value = "laporan-order-detail", method = RequestMethod.GET)
    public ModelAndView laporanOrderDetail(@RequestParam ("PIdDataDetail") Long PIdDataDetail){
        // TODO: 4/12/2018 conditional report here for inspeksi and others
        Map<String, Object> parameterMap = new HashMap<String, Object>();
        String jasperDir = String.format("%s%s%s%s%s%s", context.getRealPath(""), File.separator, "WEB-INF",
                File.separator, "report", File.separator);
        parameterMap.put("SUBREPORT_DIR", jasperDir);
        parameterMap.put("datasource", dataSource);
        parameterMap.put("PIdDataDetail", PIdDataDetail);
        return new ModelAndView("pdfLaporanOrderDetail", parameterMap);
    }


    @RequestMapping(value = {"table-doc-bangunan"}, method = RequestMethod.GET)
    public String showDokumenBangunan(@RequestParam("idTanahBangunan") Long idTanahBangunan, @RequestParam("idBangunanUsed") Long idBangunanUsed, Model model) {
        List<LpaDokumenBangunanDto> dataList = lpaFacade.listDokumenBangunan(idTanahBangunan);
        model.addAttribute("dataList", dataList);
        model.addAttribute("idBangunanUsed", idBangunanUsed);
        return DIR_VIEW + "table-doc-bangunan";
    }


    @RequestMapping(value = {"edit-lpa-rab"}, method = RequestMethod.GET)
    public String editLpaRab(@RequestParam("id") Long id, Model model) {
        LpaRabDto formBean = lpaFacadeRab.getByUmum(id);
        initCreateLpaRab(model, formBean);
        return DIR_VIEW + "formRab";
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


    @RequestMapping(value = {"edit-lpa-kendaraan-new"}, method = RequestMethod.GET)
    public String editLpaKendaraa(@RequestParam("id") Long id, Model model) {
        LpaKendaraanDto formBean = facadeKendaraan.getByUmum(id);
        model.addAttribute("formBean", formBean);
        initCreateLpaKendraan(model, formBean);
        return DIR_VIEW + "formKendaraan";
    }


    @RequestMapping(value = {"table-kendaraan"}, method = RequestMethod.GET)
    public String showItemKendaraan(@RequestParam("idLpa") Long idLpa, Model model) {
        List<LpaItemKendaraanDto> dataList = facadeKendaraan.listItemKendaraan(idLpa);
        model.addAttribute("dataList", dataList);
        return DIR_VIEW + "table-kendaraan";
    }



    @RequestMapping(value = {"edit-lpa-stock"}, method = RequestMethod.GET)
    public String editLpaStock(@RequestParam("id") Long id, Model model) {
        LpaStockDto formBean = facadeStock.getByUmum(id);
        model.addAttribute("formBean", formBean);
        initCreateLpaStock(model, formBean);
        return DIR_VIEW + "formStock";
    }

    @RequestMapping(value = {"edit-lpa-inspeksi"}, method = RequestMethod.GET)
    public String editLpaInspeksi(@RequestParam("id") Long id, Model model) {
        LpaInspeksiDto formBean = facadeInspeksi.getByUmum(id);
        model.addAttribute("formBean", formBean);
        initCreateLpaInspeksi(model, formBean);
        return DIR_VIEW + "formInspeksi";
    }

    @RequestMapping(value = {"table-item-inspeksi"}, method = RequestMethod.GET)
    public String showItemBarangJadi(@RequestParam("idLpa") Long idLpaInspeksi, @RequestParam("typeGoods") String typeGoods, Model model) {

        List<LpaItemInspeksiDto> dataList = facadeInspeksi.getListItemInspeksiByInspeksiId(idLpaInspeksi, typeGoods);
        String url;
        if(typeGoods.equalsIgnoreCase("Goods")) {
            url = "table-barang-jadi";
        } else if(typeGoods.equalsIgnoreCase("Half_Goods")) {
            url = "table-barang-setengah-jadi";
        } else {
            url = "table-bahan-baku";
        }
        model.addAttribute("dataList", dataList);
        model.addAttribute("countTanahRef", dataList.size());
        return DIR_VIEW + url;
    }

    @RequestMapping (value = "laporan-lpa-inspeksi", method = RequestMethod.GET)
    public ModelAndView laporanLpaInspeksi(@RequestParam ("PIdLpaUmum") Long PIdLpaUmum, @RequestParam ("lpa") String lpa){
        Map<String, Object> parameterMap = new HashMap<String, Object>();
        String jasperDir = String.format("%s%s%s%s%s%s", context.getRealPath(""), File.separator, "WEB-INF",
                File.separator, "report", File.separator);
        parameterMap.put("SUBREPORT_DIR", jasperDir);
        parameterMap.put("datasource", dataSource);
        parameterMap.put("PIdLpaUmum", PIdLpaUmum);

        if(lpa.equals("lpa-inspeksi")) {
            return new ModelAndView("pdfLpaInspeksi", parameterMap);
        }else if(lpa.equals("img-survey")){
            return new ModelAndView("pdfLaporanLpaImgSurveyInspeksi", parameterMap);
        }else if(lpa.equals("detail-barang-jadi")){
            parameterMap.put("PTypeBarang", "Goods");
            return new ModelAndView("pdfLaporanDetailBarang", parameterMap);
        }else if(lpa.equals("detail-barang-setengah-jadi")){
            parameterMap.put("PTypeBarang", "Half_Goods");
            return new ModelAndView("pdfLaporanDetailBarang", parameterMap);
        }else if(lpa.equals("detail-bahan-baku")){
            parameterMap.put("PTypeBarang", "Raw_Materials");
            return new ModelAndView("pdfLaporanDetailBarang", parameterMap);
        }else{
            return null;
        }
    }

    @RequestMapping(value = {"form-item-inspeksi"}, method = RequestMethod.GET)
    public String formBarang(@RequestParam(required = false, value = "id") Long id,
                             @RequestParam(value = "jenisAktiva") Long jenisAktiva,
                             @RequestParam(value = "lpaInspeksiId") Long lpaInspeksiId,
                             @RequestParam(value = "typeGoods") String typeGoods,
                             Model model) {
        LpaItemInspeksiDto formBean = new LpaItemInspeksiDto();
        formBean.setLpaInspeksiId(lpaInspeksiId);
        if (id != null) {
            formBean = facadeInspeksi.getItemInspeksi(id);
        } else {
            formBean.setType(typeGoods);
        }
        model.addAttribute("labelGoods", formBean.getType().equalsIgnoreCase("Goods")?"Barang Jadi":formBean.getType().equalsIgnoreCase("Half_Goods")?"Barang Setengah Jadi" : "Bahan Baku");
        model.addAttribute("formBean", formBean);
        model.addAttribute("jenisAktiva", jenisAktiva);

        return DIR_VIEW + "form-item-inspeksi";
    }

    @RequestMapping(value = {"edit-lpa-bv"}, method = RequestMethod.GET)
    public String editLpaBV(@RequestParam("id") Long id, Model model) {
        LpaBvDto formBean = facadeBv.getByUmum(id);
        model.addAttribute("formBean", formBean);
        initCreateLpaBv(model);
        return DIR_VIEW + "formBv";
    }

    @RequestMapping(value = {"edit-lpa-Rv"}, method = RequestMethod.GET)
    public String editLpaRv(@RequestParam("id") Long id, Model model) {
        LpaRvDto formBean = facadeRv.getByUmum(id);
        model.addAttribute("formBean", formBean);
        initCreateLpaRv(model);
        return DIR_VIEW + "formRv";
    }

    @RequestMapping(value = {"table-image-rv-bv"}, method = RequestMethod.GET)
    public String showImageRvBv(@RequestParam("surveyId") Long surveyId, @RequestParam("lpaId") Long lpaId, Model model) {
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
            if (StringUtils.isNotBlank(row.getCatatan()) && row.getNamaDocumentLpa().contains("Tambahan")) {
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

    @RequestMapping (value = "laporan-lpa-rv", method = RequestMethod.GET)
    public ModelAndView laporanLpaRv(@RequestParam ("PIdLpaUmum") Long PIdLpaUmum, @RequestParam ("lpa") String lpa){
        Map<String, Object> parameterMap = new HashMap<String, Object>();
        String jasperDir = String.format("%s%s%s%s%s%s", context.getRealPath(""), File.separator, "WEB-INF",
                File.separator, "report", File.separator);
        parameterMap.put("SUBREPORT_DIR", jasperDir);
        parameterMap.put("datasource", dataSource);
        parameterMap.put("PIdLpaUmum", PIdLpaUmum);
        if(lpa.equals("lpa-rv")) {
            return new ModelAndView("pdfLaporanLpaRv", parameterMap);
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

    private void initCreateLpaRv(Model model) {
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
        model.addAttribute("listSurveyor", personFacade.findLvSurveyor());
        model.addAttribute("listMataUang", mataUangFacade.list(new MataUangDto()));
        model.addAttribute("listStatusKepemilikan", parameterFacade.findLvByGroup(AppConstans.GroupParameter.RV_STATUS_KEPEMILIKAN));
        model.addAttribute("listLamaPenempatan", parameterFacade.findLvByGroup(AppConstans.GroupParameter.RV_LAMA_PENEMPATAN));
        model.addAttribute("listDidatangiDebtCollector", parameterFacade.findLvByGroup(AppConstans.GroupParameter.RV_DATANG_DEBTCOLLECTOR));
        model.addAttribute("listFotoPada", parameterFacade.findLvByGroup(AppConstans.GroupParameter.RV_FOTO_PADA));
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


    private void initCreateLpaBv(Model model) {
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


    private void initCreateLpaStock(Model model, LpaStockDto formBean) {
        model.addAttribute("listLokasiAktiva", parameterFacade.findLvByGroup(AppConstans.GroupParameter.POSISI_LOKASI_AKTIVA));
        model.addAttribute("lvStatusJaminan", statusJaminanFacade.listLv());
        model.addAttribute("listAppraisalCabang", appraisalBranchFacade.findLv(new AppraisalBranchDto()));
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

    private void initCreateLpaInspeksi(Model model, LpaInspeksiDto formBean) {
        //Lpa Umum
        model.addAttribute("listLokasiAktiva", parameterFacade.findLvByGroup(AppConstans.GroupParameter.POSISI_LOKASI_AKTIVA));
        model.addAttribute("lvStatusJaminan", statusJaminanFacade.listLv());
        model.addAttribute("listAppraisalCabang", appraisalBranchFacade.findLv(new AppraisalBranchDto()));
        model.addAttribute("listMataUang", mataUangFacade.list(new MataUangDto()));
        model.addAttribute("listJenisPenilaianFisik", penilaianFacade.listByKategoriAktiva(formBean.getKategoriObjectPenilaian()));
        //LPA Inspeksi
        model.addAttribute("listTempatPenyimpanan", parameterFacade.findLvByGroup(AppConstans.GroupParameter.INSPEKSI_JENIS_TEMPAT_SIMPAN));
        model.addAttribute("listInfoAlamatTempat", parameterFacade.findLvByGroup(AppConstans.GroupParameter.INSPEKSI_INFO_ALAMAT_TEMPAT));
        model.addAttribute("listStatusTempat", parameterFacade.findLvByGroup(AppConstans.GroupParameter.INSPEKSI_STATUS_TEMPAT_SIMPAN));
        model.addAttribute("listKondisiTempat", parameterFacade.findLvByGroup(AppConstans.GroupParameter.INSPEKSI_KODISI_TEMPAT_SIMPAN));
        model.addAttribute("listAktifitasKerja", parameterFacade.findLvByGroup(AppConstans.GroupParameter.INSPEKSI_AKTIFITAS_KERJA));
        model.addAttribute("listPeralatanGudang", parameterFacade.findLvByGroup(AppConstans.GroupParameter.INSPEKSI_PERALATAN_GUDANG));
        model.addAttribute("listAlatPemadam", parameterFacade.findLvByGroup(AppConstans.GroupParameter.INSPEKSI_ALAT_PEMADAM));
        model.addAttribute("listKeamanan", parameterFacade.findLvByGroup(AppConstans.GroupParameter.INSPEKSI_KEAMANAN));
        model.addAttribute("listKondisiJalan", parameterFacade.findLvByGroup(AppConstans.GroupParameter.INSPEKSI_KONDISI_JALAN));
        model.addAttribute("listLingkunganSekitar", parameterFacade.findLvByGroup(AppConstans.GroupParameter.INSPEKSI_LINGKUNGAN_SEKITAR));
        model.addAttribute("listMetodePeriksa", parameterFacade.findLvByGroup(AppConstans.GroupParameter.INSPEKSI_METODE_PERIKSA));
        model.addAttribute("listTempatPersediaan", parameterFacade.findLvByGroup(AppConstans.GroupParameter.INSPEKSI_PENEMPATAN_PERSEDIAAN));
        model.addAttribute("listSusunanTempat", parameterFacade.findLvByGroup(AppConstans.GroupParameter.INSPEKSI_SUSUSNAN_PENEMPATAN));
        model.addAttribute("listPacking", parameterFacade.findLvByGroup(AppConstans.GroupParameter.INSPEKSI_PACKING));
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

    private void initCreateLpaKendraan(Model model, LpaKendaraanDto formBean) {
        model.addAttribute("listLokasiAktiva", parameterFacade.findLvByGroup(AppConstans.GroupParameter.POSISI_LOKASI_AKTIVA));
        model.addAttribute("lvStatusJaminan", statusJaminanFacade.listLv());
        model.addAttribute("listAppraisalCabang", appraisalBranchFacade.findLv(new AppraisalBranchDto()));
        model.addAttribute("listFaktorMarket", parameterFacade.findLvByGroup(AppConstans.GroupParameter.FAKTOR_MARKETABILITY));
        model.addAttribute("listNegative", parameterFacade.findLvByGroup(AppConstans.GroupParameter.NEGATIVE_LIST_KENDARAAN));
        model.addAttribute("listPertimbangan", parameterFacade.findLvByGroup(AppConstans.GroupParameter.PROPERTI_PERTIMBANGAN_KENDARAAN));
        model.addAttribute("listFaktorUnMarket", parameterFacade.findLvByGroup(AppConstans.GroupParameter.FAKTOR_UNMARKETABILITY_KENDARAAN));
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



    @RequestMapping(value = {"edit-lpa-vessel"}, method = RequestMethod.GET)
    public String editLpaKapal(@RequestParam("id") Long id, Model model) {
        LpaKapalDto formBean = lpaFacadeVessel.getByUmum(id);
        model.addAttribute("formBean", formBean);
        initCreateLpaVessel(model, formBean);
        return DIR_VIEW + "formVessel";
    }


    private void initCreateLpaVessel(Model model, LpaKapalDto formBean) {
        model.addAttribute("formBean", formBean);
        model.addAttribute("lvStatusJaminan", statusJaminanFacade.listLv());
        model.addAttribute("listLokasiAktiva", parameterFacade.findLvByGroup(AppConstans.GroupParameter.POSISI_LOKASI_AKTIVA));
        model.addAttribute("listFaktorMarket", parameterFacade.findLvByGroup(AppConstans.GroupParameter.FAKTOR_MARKETABILITY));
        model.addAttribute("listNegative", parameterFacade.findLvByGroup(AppConstans.GroupParameter.NEGATIVE_LIST_VESSEL));
        model.addAttribute("listPertimbangan", parameterFacade.findLvByGroup(AppConstans.GroupParameter.PROPERTI_PERTIMBANGAN_VESSEL));
        model.addAttribute("listFaktorUnMarket", parameterFacade.findLvByGroup(AppConstans.GroupParameter.FAKTOR_UNMARKETABILITY));
        model.addAttribute("listAppraisalCabang", appraisalBranchFacade.findLv(new AppraisalBranchDto()));
        model.addAttribute("listMataUang", mataUangFacade.list(new MataUangDto()));
        model.addAttribute("listJenisDokumen", documentFacade.findByAktiva(formBean.getJenisAktiva()));
        model.addAttribute("listJenisPenilaianFisik", penilaianFacade.listByKategoriAktiva(formBean.getKategoriObjectPenilaian()));
        if (formBean.isEksternal()) {
            model.addAttribute("listOpini", parameterFacade.findLvByGroup(AppConstans.GroupParameter.OPINI_INTERNAL_ATAS_EKSTERNAL));
            model.addAttribute("listKjpp", kjppFacade.findLv());
        } else {
            model.addAttribute("listSurveyor", personFacade.findLvSurveyor());
        }
        model.addAttribute("listKelasKapal", parameterFacade.findLvByGroup(AppConstans.GroupParameter.KELAS_KAPAL_VESSEL));
        model.addAttribute("listStatusAgunanKapal", parameterFacade.findLvByGroup(AppConstans.GroupParameter.STATUS_AGUNAN_VESSEL));
        model.addAttribute("listStatusJaminanKapal", parameterFacade.findLvByGroup(AppConstans.GroupParameter.STATUS_JAMINAN_VESSEL));
        model.addAttribute("listStatusJaminanKapal", parameterFacade.findLvByGroup(AppConstans.GroupParameter.STATUS_JAMINAN_VESSEL));
        model.addAttribute("listKondisiUmum", parameterFacade.findLvByGroup(AppConstans.GroupParameter.KONDISI_UMUM_VESSEL));
        model.addAttribute("listJenisKapal", parameterFacade.findLvByGroup(AppConstans.GroupParameter.JENIS_VESSEL));

    }

    @RequestMapping (value = "laporan-lpa-kapal", method = RequestMethod.GET)
    public ModelAndView laporanLpaKapal(@RequestParam ("PIdLpaUmum") Long PIdLpaUmum, @RequestParam ("lpa") String lpa){
        Map<String, Object> parameterMap = new HashMap<String, Object>();
        String jasperDir = String.format("%s%s%s%s%s%s", context.getRealPath(""), File.separator, "WEB-INF",
                File.separator, "report", File.separator);
        parameterMap.put("SUBREPORT_DIR", jasperDir);
        parameterMap.put("datasource", dataSource);
        parameterMap.put("PIdLpaUmum", PIdLpaUmum);
        if (lpa.equals("lpa-int")) {
            return new ModelAndView("pdfLaporanLpaKapal", parameterMap);
        }else if(lpa.equals("lpa-ext")){
            return new ModelAndView("pdfLaporanLpaKapalExt", parameterMap);
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

    @RequestMapping (value = "laporan-lpa-kendaraan", method = RequestMethod.GET)
    public ModelAndView laporanLpaKendaraan(@RequestParam ("PIdLpaUmum") Long PIdLpaUmum, @RequestParam ("lpa") String lpa){
        Map<String, Object> parameterMap = new HashMap<String, Object>();
        String jasperDir = String.format("%s%s%s%s%s%s", context.getRealPath(""), File.separator, "WEB-INF",
                File.separator, "report", File.separator);
        parameterMap.put("SUBREPORT_DIR", jasperDir);
        parameterMap.put("datasource", dataSource);
        parameterMap.put("PIdLpaUmum", PIdLpaUmum);
        if(lpa.equals("lpa-int")) {
            return new ModelAndView("pdfLaporanLpaKendaraan", parameterMap);
        }else if(lpa.equals("lpa-ext")){
            return new ModelAndView("pdfLaporanLpaKendaraanExt", parameterMap);
        }else if(lpa.equals("lampiran")){
            return new ModelAndView("pdfLaporanLpaKendaraanLampiran", parameterMap);
        }else if(lpa.equals("img-survey")){
            return new ModelAndView("pdfLaporanLpaImgSurvey", parameterMap);
        }else if(lpa.equals("img-peta")){
            return new ModelAndView("pdfLaporanLpaImgPeta", parameterMap);
        }else if(lpa.equals("lpa-other")){
            return new ModelAndView("pdfLaporanLpaImgOther", parameterMap);
        }else{
            return null;
        }
    }


    private void initCreateLpaRab(Model model, LpaRabDto formBean) {
        model.addAllAttributes(facade.getReference(DataCommonDto.StatusData.COMPLETE, null));
        model.addAttribute("formBean", formBean);
        model.addAttribute("listSurveyor", personFacade.findLvSurveyor());
        model.addAttribute("listMataUang", mataUangFacade.list(new MataUangDto()));
        model.addAttribute("listJenisPenilaianFisik", penilaianFacade.listByKategoriAktiva(formBean.getKategoriObjectPenilaian()));
        model.addAttribute("listAppraisalCabang", appraisalBranchFacade.findLv(new AppraisalBranchDto()));
        model.addAttribute("listLokasiAktiva", parameterFacade.findLvByGroup(AppConstans.GroupParameter.POSISI_LOKASI_AKTIVA));
        model.addAttribute("listKewajaranNilai", parameterFacade.findLvByGroup(AppConstans.GroupParameter.RAB_KEWAJARAN_NILAI));
    }


    @RequestMapping(value = {"edit-lpa-mesin"}, method = RequestMethod.GET)
    public String editLpaMes(@RequestParam("id") Long id, Model model) {
        LpaMesinDto formBean = lpaFacadeMesin.getByUmum(id);
        initCreateLpaMesin(model, formBean);
        return DIR_VIEW + "formMesin";
    }

    private void initCreateLpaMesin(Model model, LpaMesinDto formBean) {
        model.addAllAttributes(facade.getReference(DataCommonDto.StatusData.COMPLETE, null));
        model.addAttribute("formBean", formBean);
        model.addAttribute("listFaktorMarket", parameterFacade.findLvByGroup(AppConstans.GroupParameter.MESIN_FAKTOR_MARKETABILITY));
        model.addAttribute("listNegative", parameterFacade.findLvByGroup(AppConstans.GroupParameter.MESIN_NEGATIVE_LIST));
        model.addAttribute("listPertimbangan", parameterFacade.findLvByGroup(AppConstans.GroupParameter.MESIN_PERTIMBANGAN));
        model.addAttribute("listFaktorUnMarket", parameterFacade.findLvByGroup(AppConstans.GroupParameter.MESIN_FAKTOR_UNMARKETABILITY));
        model.addAttribute("listSurveyor", personFacade.findLvSurveyor());
        model.addAttribute("listMataUang", mataUangFacade.list(new MataUangDto()));
        model.addAttribute("listJenisPenilaianFisik", penilaianFacade.listByKategoriAktiva(formBean.getKategoriObjectPenilaian()));
        model.addAttribute("listAppraisalCabang", appraisalBranchFacade.findLv(new AppraisalBranchDto()));
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


    @RequestMapping(value = {"table-doc-tanah"}, method = RequestMethod.GET)
    public String showDokumenTanah(@RequestParam("idTanahBangunan") Long idTanahBangunan, @RequestParam("idTanahUsed") Long idTanahUsed, Model model) {
        List<LpaDokumenTanahDto> dataList = lpaFacade.listDokumenTanah(idTanahBangunan);
        model.addAttribute("dataList", dataList);
        model.addAttribute("idTanahUsed", idTanahUsed);
        return DIR_VIEW + "table-doc-tanah";
    }


    @RequestMapping(value = {"form-doc-tanah"}, method = RequestMethod.GET)
    public String formDokumenTanah(@RequestParam(required = false, value = "id") Long id,
                                   @RequestParam(value = "jenisAktiva") Long jenisAktiva,
                                   @RequestParam(value = "lpaCommonId") Long lpaCommonId,
                                   Model model) {
        LpaDokumenTanahDto formBean = new LpaDokumenTanahDto();
        formBean.setCommonId(lpaCommonId);
        if (id != null) {
            formBean = lpaFacade.getTanah(id);
            formBean.setLuasTanahStr(formBean.getLuasTanah().toString());
        }
        model.addAttribute("formBean", formBean);
        model.addAttribute("jenisAktiva", jenisAktiva);
        model.addAttribute("listJenisDokumen", documentFacade.findByAktiva(jenisAktiva));
        return DIR_VIEW + "form-doc-tanah";
    }

    @RequestMapping(value = {"preview-image"}, method = RequestMethod.GET)
    public String viewImage(@RequestParam("id") Long id, HttpServletResponse response) {
        LpaImageDto dto = lpaFacade.getImage(id);
        Boolean isWsFile = (dto.getContentType() != null && !dto.getContentType().isEmpty() && dto.getContentType().equalsIgnoreCase("application/vnd.ms-excel"));
//        return previewImage(response, dto.getContentType().concat(".jpg"), dto.getImage(), dto.getContentType());
        Boolean isPdf = (dto.getContentType() != null && !dto.getContentType().isEmpty() && dto.getContentType().equalsIgnoreCase("application/pdf"));
        Boolean isPng = (dto.getContentType() != null && !dto.getContentType().isEmpty() && dto.getContentType().equalsIgnoreCase("image/png"));
        return previewImage(response, dto.getContentType().concat(isWsFile?".xls":isPdf?".pdf":isPng?".png":".jpg"), dto.getImage(), dto.getContentType());
    }


    @RequestMapping(value = {"table-image"}, method = RequestMethod.GET)
    public String showImage(@RequestParam("surveyId") Long surveyId, @RequestParam("lpaId") Long lpaId, Model model) {
        model.addAttribute("dataList", lpaFacade.findImage(surveyId, lpaId));
        model.addAttribute("lpaId", lpaId);
        return DIR_VIEW + "table-image";
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
        }else if (lpa.equals("lpa-int-progress")) {
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
        model.addAttribute("listAppraisalCabang", appraisalBranchFacade.findLv(new AppraisalBranchDto()));
        model.addAttribute("listPeruntukanTanah", parameterFacade.findLvByGroup(AppConstans.GroupParameter.PERUNTUKAN_TANAH));
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

    @RequestMapping(value = {"form-kendaraan"}, method = RequestMethod.GET)
    public String formItemKendaraan(@RequestParam(value = "id", required = false) Long id,
                                    @RequestParam(value = "lpaId") Long lpaId,
                                    Model model) {
        LpaItemKendaraanDto formBean;

            formBean = facadeKendaraan.getItem(id);

        model.addAttribute("formBean", formBean);
        initItemKendaraan(model);
        return DIR_VIEW + "form-kendaraan";
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

    private void initItemKendaraan(Model model) {
        model.addAttribute("listPenggunaan", parameterFacade.findLvByGroup(AppConstans.GroupParameter.PENGGUNAAN_KENDARAAN));
        model.addAttribute("listPemakaian", parameterFacade.findLvByGroup(AppConstans.GroupParameter.PEMAKAIAN_KENDARAAN));
        model.addAttribute("listKondisiBody", parameterFacade.findLvByGroup(AppConstans.GroupParameter.KONDISI_BODY_KENDARAAN));
        model.addAttribute("listKondisiBumper", parameterFacade.findLvByGroup(AppConstans.GroupParameter.KONDISI_BUMPER_KENDARAAN));
        model.addAttribute("listKondisiCat", parameterFacade.findLvByGroup(AppConstans.GroupParameter.KONDISI_CAT_KENDARAAN));
        model.addAttribute("listKondisiInterior", parameterFacade.findLvByGroup(AppConstans.GroupParameter.KONDISI_INTERIOR_KENDARAAN));
        model.addAttribute("listKondisiEksterior", parameterFacade.findLvByGroup(AppConstans.GroupParameter.KONDISI_EKSTERIOR_KENDARAAN));
        model.addAttribute("listKondisiMesin", parameterFacade.findLvByGroup(AppConstans.GroupParameter.KONDISI_MESIN_KENDARAAN));
        model.addAttribute("listKondisiBan", parameterFacade.findLvByGroup(AppConstans.GroupParameter.KONDISI_BAN_KENDARAAN));
    }

}