package com.permata.appraisal2.facade.impl;

import com.permata.appraisal2.dao.*;
import com.permata.appraisal2.dto.*;
import com.permata.appraisal2.facade.DataSurveyFacade;
import com.permata.appraisal2.facade.common.FacadeBase;
import com.permata.appraisal2.model.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class DataSurveyFacadeImpl extends FacadeBase<SurveyCommonDto, SurveyCommon> implements DataSurveyFacade {

    @Autowired
    private SurveyCommonDao dao;
    @Autowired
    private DataDetailDao detailDao;
    @Autowired
    private SurveyTanahDao tanahDao;
    @Autowired
    private SurveyBangunanDao bangunanDao;
    @Autowired
    private SurveyLingkunganDao lingkunganDao;
    @Autowired
    private SurveyPembandingDao pembandingDao;
    @Autowired
    private SurveyPointDao pointDao;
    @Autowired
    private ParameterDao paramDao;
    @Autowired
    private SurveyImageDao imageDao;
    @Autowired
    private SurveyKendaraanDao kendaraanDao;
    @Autowired
    private DocumentLpaTypeDao documentLpaTypeDao;
    @Autowired
    private JenisPenilaianDao jenisPenilaianDao;
    @Autowired
    private SurveyItemInspeksiDao itemDao;
    @Autowired
    private SurveyInspeksiDao inspeksiDao;

    @Override
    public SurveyCommonDto get(Long id) {
        return toWrapper(dao.get(id));
    }

    @Override
    public SurveyCommonDto getByDetail(Long detailId, String user) {
        DataDetail detail = detailDao.get(detailId);
        if (detail.getSurvey() == null) {
            return new SurveyCommonDto(detailId, detail.getAssetType().getCategoryType().getCode(), user);
        } else {
            return toWrapper(detail.getSurvey());
        }
    }

    @Override
    public SurveyTanahDto getTanah(Long id) {
        return toTanahWrapper(tanahDao.get(id));
    }

    @Override
    public SurveyItemInspeksiDto getBarang(Long id) {
        return toItemWrapper(itemDao.get(id));
    }

    @Override
    public SurveyBangunanDto getBangunan(Long id) {
        return toBangunanWrapper(bangunanDao.get(id));
    }

    @Override
    public SurveyKendaraanDto getKendaraan(Long id) {
        return toKendaraanWrapper(kendaraanDao.get(id));
    }

    @Override
    @Transactional(readOnly = false)
    public SurveyTanahDto saveDataTanah(SurveyTanahDto dto) {
        return toTanahWrapper(tanahDao.save(toTanahEntity(dto)));
    }

    @Override
    @Transactional(readOnly = false)
    public SurveyItemInspeksiDto saveDataItemInspeksi(SurveyItemInspeksiDto dto) {
        return toItemWrapper(itemDao.save(toItemEntity(dto)));
    }

    @Override
    @Transactional(readOnly = false)
    public SurveyBangunanDto saveDataBangunan(SurveyBangunanDto dto) {
        return toBangunanWrapper(bangunanDao.save(toBangunanEntity(dto)));
    }

    @Override
    @Transactional(readOnly = false)
    public SurveyKendaraanDto saveDataKendaraan(SurveyKendaraanDto dto) {
        return toKendaraanWrapper(kendaraanDao.save(toKendaraanEntity(dto)));
    }

    @Override
    @Transactional(readOnly = false)
    public SurveyCommonDto saveDataCommon(SurveyCommonDto dto, PersonLoginDto personLoginDto) throws Exception {
        DataDetail dataDetail = detailDao.get(dto.getDetailId());
        if(StringUtils.equalsIgnoreCase(dataDetail.getDataCommon().getTaskList(), personLoginDto.getUserId())) {
            SurveyCommon e = dao.save(toEntity(dto));
            return toWrapper(e);
        } else {
            throw new Exception("Gagal : Posisi Order Tidak Pada Tasklist Anda");
        }
    }

    @Override
    public List<SurveyTanahDto> findTanah(SurveyTanahDto filter) {
        List<SurveyTanahDto> result = new ArrayList<SurveyTanahDto>();
        List<SurveyTanah> listData = tanahDao.find(toTanahEntity(filter));
        for (SurveyTanah row : listData) {
            result.add(toTanahWrapper(row));
        }
        return result;
    }

    @Override
    public List<SurveyItemInspeksiDto> findBarang(SurveyItemInspeksiDto filter, String typeGoods) {
//        filter.setType(typeGoods);
        List<SurveyItemInspeksiDto> result = new ArrayList<SurveyItemInspeksiDto>();
        filter.setType(typeGoods);
        List<SurveyItemInspeksi> listData = itemDao.findExact(toItemEntity(filter));
        for (SurveyItemInspeksi row : listData) {
            result.add(toItemWrapper(row));
        }
        return result;
    }

    @Override
    public List<SurveyBangunanDto> findBangunan(SurveyBangunanDto filter) {
        List<SurveyBangunanDto> result = new ArrayList<SurveyBangunanDto>();
        List<SurveyBangunan> listData = bangunanDao.find(toBangunanEntity(filter));
        for (SurveyBangunan row : listData) {
            result.add(toBangunanWrapper(row));
        }
        return result;
    }

    @Override
    public List<SurveyKendaraanDto> findKendaraan(SurveyKendaraanDto filter) {
        List<SurveyKendaraanDto> result = new ArrayList<SurveyKendaraanDto>();
        List<SurveyKendaraan> listData = kendaraanDao.find(toKendaraanEntity(filter));
        for (SurveyKendaraan row : listData) {
            result.add(toKendaraanWrapper(row));
        }
        return result;
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteTanah(Long id) throws Exception {
        tanahDao.delete(id);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteBarang(Long id) throws Exception {
        itemDao.delete(id);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteBangunan(Long id) throws Exception {

        bangunanDao.delete(id);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteKendaraan(Long id) throws Exception {
        kendaraanDao.delete(id);
    }

    @Override
    public SurveyCommonDto toWrapper(SurveyCommon e) {
        SurveyCommonDto w = new SurveyCommonDto();
        BeanUtils.copyProperties(e, w);
        if (e.getDetail() != null) {
            w.setDetailId(e.getDetail().getId());
            w.setCategory(e.getDetail().getAssetType().getCategoryType().getCode());
            w.setActiva(e.getDetail().getAssetType().getName());
        }
        if(e.getJenisPenilaian() != null){
            w.setJenisPenilaian(e.getJenisPenilaian().getId());
            w.setJenisObject(e.getJenisPenilaian().getNama());
        }
        return w;
    }

    @Override
    public SurveyCommon toEntity(SurveyCommonDto w) {
        SurveyCommon e = new SurveyCommon();
        if (w.getId() != null) {
            e = dao.get(w.getId());
        }
        BeanUtils.copyProperties(w, e);
        if(w.getJenisPenilaian() != null){
            e.setJenisPenilaian(jenisPenilaianDao.get(w.getJenisPenilaian()));
        }
        e.setDetail(detailDao.get(w.getDetailId()));
        return e;
    }

    private SurveyTanahDto toTanahWrapper(SurveyTanah e) {
        SurveyTanahDto w = new SurveyTanahDto();
        BeanUtils.copyProperties(e, w);
        w.setDetailId(e.getDetail().getId());
        return w;
    }

    private SurveyItemInspeksiDto toItemWrapper(SurveyItemInspeksi e) {
        SurveyItemInspeksiDto w = new SurveyItemInspeksiDto();
        BeanUtils.copyProperties(e, w);
        w.setSurveyInspeksiId(e.getSurveyInspeksi().getId());
        return w;
    }

    private SurveyKendaraanDto toKendaraanWrapper(SurveyKendaraan e) {
        SurveyKendaraanDto w = new SurveyKendaraanDto();
        BeanUtils.copyProperties(e, w);
        w.setDetailId(e.getDetail().getId());
        w.setCategory(e.getDetail().getAssetType().getCategoryType().getCode());
        return w;
    }

    private SurveyBangunanDto toBangunanWrapper(SurveyBangunan e) {
        SurveyBangunanDto w = new SurveyBangunanDto();
        BeanUtils.copyProperties(e, w);
        Double lantai2 = e.getLantai2()!=null?e.getLantai2().compareTo(new Double("0"))==0?null:e.getLantai2():null;
        Double lantai3 = e.getLantai3()!=null?e.getLantai3().compareTo(new Double("0"))==0?null:e.getLantai3():null;
        Double lantai4 = e.getLantai4()!=null?e.getLantai4().compareTo(new Double("0"))==0?null:e.getLantai4():null;
        Double lantai5 = e.getLantai5()!=null?e.getLantai5().compareTo(new Double("0"))==0?null:e.getLantai5():null;

        w.setLantai2(lantai2);
        w.setLantai3(lantai3);
        w.setLantai4(lantai4);
        w.setLantai5(lantai5);
        w.setDetailId(e.getDetail().getId());
        return w;
    }

    private SurveyTanah toTanahEntity(SurveyTanahDto w) {
        SurveyTanah e = new SurveyTanah();
        if (w.getId() != null) {
            e = tanahDao.get(w.getId());
        }
        BeanUtils.copyProperties(w, e);
        e.setDetail(detailDao.get(w.getDetailId()));
        return e;
    }

    private SurveyItemInspeksi toItemEntity(SurveyItemInspeksiDto w) {
        SurveyItemInspeksi e = new SurveyItemInspeksi();
        if (w.getId() != null) {
            e = itemDao.get(w.getId());
        }
        BeanUtils.copyProperties(w, e);
        e.setSurveyInspeksi(inspeksiDao.get(w.getSurveyInspeksiId()));
        return e;
    }

    private SurveyKendaraan toKendaraanEntity(SurveyKendaraanDto w) {
        SurveyKendaraan e = new SurveyKendaraan();
        if (w.getId() != null) {
            e = kendaraanDao.get(w.getId());
        }
        BeanUtils.copyProperties(w, e);
        e.setDetail(detailDao.get(w.getDetailId()));
        return e;
    }

    private SurveyBangunan toBangunanEntity(SurveyBangunanDto w) {
        SurveyBangunan e = new SurveyBangunan();
        if (w.getId() != null) {
            e = bangunanDao.get(w.getId());
        }
        BeanUtils.copyProperties(w, e);
        Double lantai2 = w.getLantai2()!=null?w.getLantai2().compareTo(new Double("0"))==0?null:w.getLantai2():null;
        Double lantai3 = w.getLantai3()!=null?w.getLantai3().compareTo(new Double("0"))==0?null:w.getLantai3():null;
        Double lantai4 = w.getLantai4()!=null?w.getLantai4().compareTo(new Double("0"))==0?null:w.getLantai4():null;
        Double lantai5 = w.getLantai5()!=null?w.getLantai5().compareTo(new Double("0"))==0?null:w.getLantai5():null;
        e.setLantai2(lantai2);
        e.setLantai3(lantai3);
        e.setLantai4(lantai4);
        e.setLantai5(lantai5);
        e.setDetail(detailDao.get(w.getDetailId()));
        return e;
    }

    @Override
    public SurveyLingkunganDto getLingkunganByDetail(Long detailId) {
        DataDetail detail = detailDao.get(detailId);
        if (detail.getLingkungan() == null) {
            return new SurveyLingkunganDto(detailId);
        } else {
            return toLingkunganWrapper(detail.getLingkungan());
        }
    }

    private SurveyLingkunganDto toLingkunganWrapper(SurveyLingkungan e) {
        SurveyLingkunganDto w = new SurveyLingkunganDto();
        BeanUtils.copyProperties(e, w);
        w.setDetailId(e.getDetail().getId());
        return w;
    }

    @Override
    @Transactional(readOnly = false)
    public SurveyLingkunganDto saveDataLingkungan(SurveyLingkunganDto dto) {
        return toLingkunganWrapper(lingkunganDao.save(toLingkunganEntity(dto)));
    }

    public SurveyLingkungan toLingkunganEntity(SurveyLingkunganDto w) {
        SurveyLingkungan e = new SurveyLingkungan();
        if (w.getId() != null) {
            e = lingkunganDao.get(w.getId());
        }
        BeanUtils.copyProperties(w, e);
        e.setDetail(detailDao.get(w.getDetailId()));
        return e;
    }

    @Override
    public List<SurveyPembandingDto> findPembanding(SurveyPembandingDto filter) {
        List<SurveyPembandingDto> result = new ArrayList<SurveyPembandingDto>();
        List<SurveyPembanding> listData = pembandingDao.find(toPembandingEntity(filter));
        for (SurveyPembanding row : listData) {
            result.add(toPembandingWrapper(row));
        }
        return result;
    }

    private SurveyPembanding toPembandingEntity(SurveyPembandingDto w) {
        SurveyPembanding e = new SurveyPembanding();
        if (w.getId() != null) {
            e = pembandingDao.get(w.getId());
        }
        BeanUtils.copyProperties(w, e);
        e.setDetail(detailDao.get(w.getDetailId()));
        return e;
    }

    private SurveyPembandingDto toPembandingWrapper(SurveyPembanding e) {
        SurveyPembandingDto w = new SurveyPembandingDto();
        BeanUtils.copyProperties(e, w);
        w.setDetailId(e.getDetail().getId());
        return w;
    }


    @Override
    public SurveyPembandingDto getPembanding(Long id) {
        return toPembandingWrapper(pembandingDao.get(id));
    }

    @Override
    @Transactional(readOnly = false)
    public void deletePembanding(Long id) throws Exception {
        pembandingDao.delete(id);
    }

    @Override
    @Transactional(readOnly = false)
    public SurveyPembandingDto saveDataPembanding(SurveyPembandingDto dto) {
        return toPembandingWrapper(pembandingDao.save(toPembandingEntity(dto)));
    }

    @Override
    public SurveyPointDto getPointByDetail(Long detailId) {
        DataDetail detail = detailDao.get(detailId);
        if (detail.getPoint() == null) {
            return new SurveyPointDto(detailId, detail.getAssetType().getCategoryType().getCode());
        } else {
            return toPointWrapper(detail.getPoint());
        }
    }

    private SurveyPointDto toPointWrapper(SurveyPoint e) {
        SurveyPointDto w = new SurveyPointDto();
        BeanUtils.copyProperties(e, w);
        w.setDetailId(e.getDetail().getId());
        w.setCategory(e.getDetail().getAssetType().getCategoryType().getCode());
        if (e.getNegatif() != null && !e.getNegatif().isEmpty()) {
            for (Parameter row : e.getNegatif()) {
                w.getListNegatif().add(row.getId());
            }
        }
        if (e.getPertimbangan() != null && !e.getPertimbangan().isEmpty()) {
            for (Parameter row : e.getPertimbangan()) {
                w.getListPertimbangan().add(row.getId());
            }
        }
        return w;
    }

    @Override
    @Transactional(readOnly = false)
    public SurveyPointDto saveDataPoint(SurveyPointDto dto) {
        return toPointWrapper(pointDao.save(toPointEntity(dto)));
    }

    private SurveyPoint toPointEntity(SurveyPointDto w) {
        SurveyPoint e = new SurveyPoint();
        if (w.getId() != null) {
            e = pointDao.get(w.getId());
        }
        BeanUtils.copyProperties(w, e);
        e.setDetail(detailDao.get(w.getDetailId()));
        if (w.getListNegatif() != null) {
            if (e.getNegatif() == null) {
                e.setNegatif(new ArrayList<Parameter>());
            }
            e.getNegatif().clear();
            for (Long id : w.getListNegatif()) {
                e.getNegatif().add(paramDao.get(id));
            }
        } else {
            e.setNegatif(new ArrayList<Parameter>());
        }
        if (w.getListPertimbangan() != null) {
            if (e.getPertimbangan() == null) {
                e.setPertimbangan(new ArrayList<Parameter>());
            }
            e.getPertimbangan().clear();
            for (Long id : w.getListPertimbangan()) {
                e.getPertimbangan().add(paramDao.get(id));
            }
        } else {
            e.setPertimbangan(new ArrayList<Parameter>());
        }
        return e;
    }

    @Override
    public List<SurveyImageDto> findImage(Long commonId) {
        return toListImageWrapper(imageDao.findBySurvey(commonId));
    }

    @Override
    public SurveyImageDto getImage(Long id) {
        return toImageWrapper(imageDao.get(id));
    }

    @Override
    @Transactional(readOnly = false)
    public SurveyImageDto saveImage(SurveyImageDto dto) {
        return toImageWrapper(imageDao.save(toImageEntity(dto)));
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteImage(Long id) throws Exception {
        try {
            imageDao.delete(id);
        } catch (Exception e) {
            throw e;
        }
    }

    private SurveyImage toImageEntity(SurveyImageDto w) {
        SurveyImage e = new SurveyImage();
        if (w.getId() != null) {
            e = imageDao.get(w.getId());
        }
        BeanUtils.copyProperties(w, e, "image", "lpa");
        e.setCommon(dao.get(w.getCommonId()));
        if (w.getImage() != null && w.getImage().length > 0) {
            e.setImage(w.getImage());
        }
        if (w.getIdDocumentLpa() != null) {
            e.setDocumentLpaType(documentLpaTypeDao.get(w.getIdDocumentLpa()));
        }
        return e;
    }

    private List<SurveyImageDto> toListImageWrapper(List<SurveyImage> listE) {
        List<SurveyImageDto> listW = new ArrayList<SurveyImageDto>();
        for (SurveyImage e : listE) {
            listW.add(toImageWrapper(e));
        }
        return listW;
    }

    private SurveyImageDto toImageWrapper(SurveyImage e) {
        SurveyImageDto w = new SurveyImageDto();
        BeanUtils.copyProperties(e, w);
        w.setCommonId(e.getCommon().getId());
        if (e.getDocumentLpaType() != null) {
            w.setIdDocumentLpa(e.getDocumentLpaType().getId());
            w.setNamaDocumentLpa(e.getDocumentLpaType().getName());
        }
        return w;
    }
}