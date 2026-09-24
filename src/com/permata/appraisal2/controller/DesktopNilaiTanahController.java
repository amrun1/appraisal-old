package com.permata.appraisal2.controller;

import com.permata.appraisal2.common.BaseController;
import com.permata.appraisal2.common.DataTables;
import com.permata.appraisal2.dto.DesktopNilaiTanahDto;
import com.permata.appraisal2.dto.DesktopNilaiTanahXls;
import com.permata.appraisal2.facade.DesktopNilaiTanahFacade;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("master/nilai-tanah")
public class DesktopNilaiTanahController extends BaseController {

    private final String DIR_VIEW = "master/nilai-tanah/";

    @Autowired
    private DesktopNilaiTanahFacade facade;

    @RequestMapping("list")
    public String list(@ModelAttribute("formSearch") DesktopNilaiTanahDto dto) {
        return DIR_VIEW + "list";
    }

    @RequestMapping("lookup")
    public String lookup(@ModelAttribute("formSearch") DesktopNilaiTanahDto dto) {
        return "master/nilai-tanah/lookup";
    }

    @RequestMapping(value = "find", method = RequestMethod.GET)
    @ResponseBody
    public DataTables<DesktopNilaiTanahDto> find(@ModelAttribute("formSearch") DesktopNilaiTanahDto dto) {
        return facade.find(dto, getPagingInfo()).cloneToDataTable();
    }

    @RequestMapping(value = "upload", method = RequestMethod.GET)
    public String uploadForm() {
        return DIR_VIEW + "upload";
    }

    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public String upload(byte[] fileUpload, Model model) {
        model.addAttribute("css", "error");
        ByteArrayInputStream bis = null;
        if (fileUpload == null) {
            model.addAttribute("msg", "file upload can't empty");
        }
        try {
            //Get the workbook instance for XLS file
            bis = new ByteArrayInputStream(fileUpload);
            HSSFWorkbook workbook = new HSSFWorkbook(bis);
            HSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            int ir = 1;
            List<DesktopNilaiTanahXls> listXls = new ArrayList<DesktopNilaiTanahXls>();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                logger.debug("index row " + ir);
                if (ir > 4) {
                    DesktopNilaiTanahXls xls = new DesktopNilaiTanahXls();
                    Iterator<Cell> cellIterator = row.cellIterator();
                    int ic = 1;
                    boolean emptyRow = false;
                    while (cellIterator.hasNext()) {
                        Cell cell = cellIterator.next();
                        if(emptyRow)break;
                        switch (ic) {
                            case 1:
                                // skip nomer
//                                emptyRow = StringUtils.isBlank(String.valueOf(cell.getNumericCellValue()));
                                break;
                            case 2:
                                xls.setNama(cell.getStringCellValue());
                                break;
                            case 3:
                                xls.setJalan(cell.getStringCellValue());
                                break;
                            case 4:
                                xls.setPosisi(cell.getStringCellValue());
                                break;
                            case 5:
                                xls.setKelurahan(cell.getStringCellValue());
                                break;
                            case 6:
                                xls.setKecamatan(cell.getStringCellValue());
                                break;
                            case 7:
                                xls.setKota(cell.getStringCellValue());
                                break;
                            case 8:
                                xls.setPropinsi(cell.getStringCellValue());
                                break;
                            case 9:
                                xls.setPeriode(cell.getStringCellValue());
                                break;
                            case 10:
                                xls.setHarga(BigDecimal.valueOf(cell.getNumericCellValue()));
                                break;
                            default:
                                break;
                        }
                        ic++;
                    }
                    if(xls.getHarga()!=null) listXls.add(xls);
                }
                ir++;
            }
            if (!listXls.isEmpty()) {
                model.addAttribute("css", "success");
                model.addAttribute("msg", facade.upload(listXls));
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("msg", e.getMessage() + " Periksa Data Anda format nilai apakah sudah numeric(tidak ada huruf), pastikan field Nama Perumahan dan Nama Jalan tidak lebih dari 100 karakter");
        } finally {
            if(bis!=null) IOUtils.closeQuietly(bis);
        }

        return DIR_VIEW + "upload";
    }

}