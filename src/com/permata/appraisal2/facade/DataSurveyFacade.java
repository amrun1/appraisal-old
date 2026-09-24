package com.permata.appraisal2.facade;

import com.permata.appraisal2.dto.*;
import com.permata.appraisal2.model.SurveyKendaraan;

import java.util.List;

public interface DataSurveyFacade {
    public SurveyCommonDto get(Long id);

    public SurveyCommonDto getByDetail(Long detailId, String user);

    public SurveyCommonDto saveDataCommon(SurveyCommonDto dto, PersonLoginDto personLoginDto) throws Exception;

    public List<SurveyTanahDto> findTanah(SurveyTanahDto filter);

    public List<SurveyItemInspeksiDto> findBarang(SurveyItemInspeksiDto filter, String typeGoods);

    public List<SurveyBangunanDto> findBangunan(SurveyBangunanDto filter);

    public List<SurveyKendaraanDto> findKendaraan(SurveyKendaraanDto filter);

    public SurveyTanahDto getTanah(Long id);

    public SurveyItemInspeksiDto getBarang(Long id);

    public SurveyTanahDto saveDataTanah(SurveyTanahDto dto);

    public SurveyItemInspeksiDto saveDataItemInspeksi(SurveyItemInspeksiDto dto);

    public SurveyBangunanDto getBangunan(Long id);

    public SurveyKendaraanDto getKendaraan(Long id);

    public SurveyBangunanDto saveDataBangunan(SurveyBangunanDto dto);

    public SurveyKendaraanDto saveDataKendaraan(SurveyKendaraanDto dto);

    public void deleteTanah(Long id) throws Exception;

    public void deleteBarang(Long id) throws Exception;

    public void deleteBangunan(Long id) throws Exception;

    public void deleteKendaraan(Long id) throws Exception;

    public SurveyLingkunganDto getLingkunganByDetail(Long detailId);

    public SurveyLingkunganDto saveDataLingkungan(SurveyLingkunganDto dto);

    public List<SurveyPembandingDto> findPembanding(SurveyPembandingDto filter);

    public SurveyPembandingDto getPembanding(Long id);

    public void deletePembanding(Long id) throws Exception;

    public SurveyPembandingDto saveDataPembanding(SurveyPembandingDto dto);

    public SurveyPointDto getPointByDetail(Long detailId);

    public SurveyPointDto saveDataPoint(SurveyPointDto dto);

    public List<SurveyImageDto> findImage(Long commonId);

    public SurveyImageDto getImage(Long id);

    public SurveyImageDto saveImage(SurveyImageDto dto);

    public void deleteImage(Long id) throws Exception;
}