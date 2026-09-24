package com.permata.appraisal2.controller;

import com.permata.appraisal2.common.AppConstans;
import com.permata.appraisal2.common.BaseController;
import com.permata.appraisal2.common.DataTables;
import com.permata.appraisal2.common.StatusAjaxResponse;
import com.permata.appraisal2.dto.ZipCodeDto;
import com.permata.appraisal2.facade.SystemParamFacade;
import com.permata.appraisal2.facade.ZipCodeFacade;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("master/zip-code")
public class ZipCodeController extends BaseController {

    private final String DIR_VIEW = "master/zip-code/";

    @Autowired
    private ZipCodeFacade facade;
    @Autowired
    private SystemParamFacade systemParamFacade;

    @RequestMapping(value = "lookup", method = RequestMethod.GET)
    public String lookup(@ModelAttribute("formSearch") ZipCodeDto dto) {
        return DIR_VIEW + "lookup";
    }

    @RequestMapping(value = "upload", method = RequestMethod.GET)
    public String upload(Model model) {
        model.addAttribute("ftpDir",
                String.format("%s:%s%s%s",
                        systemParamFacade.getValue(AppConstans.SystemParamCode.ZIP_CODE_FTP_IP),
                        systemParamFacade.getValue(AppConstans.SystemParamCode.ZIP_CODE_FTP_PORT),
                        systemParamFacade.getValue(AppConstans.SystemParamCode.ZIP_CODE_FTP_DIR),
                        systemParamFacade.getValue(AppConstans.SystemParamCode.ZIP_CODE_FTP_FILE))
        );
        return DIR_VIEW + "upload";
    }

    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public String uploadProses(Model model, RedirectAttributes redirectAttributes) {
        String ftpDir = systemParamFacade.getValue(AppConstans.SystemParamCode.ZIP_CODE_FTP_DIR);
        String ftpFile = systemParamFacade.getValue(AppConstans.SystemParamCode.ZIP_CODE_FTP_FILE);
        String ftpUser = systemParamFacade.getValue(AppConstans.SystemParamCode.ZIP_CODE_FTP_USER);
        String ftpPassword = systemParamFacade.getValue(AppConstans.SystemParamCode.ZIP_CODE_FTP_PASSWORD);
        String ftpIp = systemParamFacade.getValue(AppConstans.SystemParamCode.ZIP_CODE_FTP_IP);
        String ftpPort = systemParamFacade.getValue(AppConstans.SystemParamCode.ZIP_CODE_FTP_PORT);

        try {
            readFile(ftpUser, ftpPassword, ftpFile, ftpIp, ftpPort, ftpDir);
            redirectAttributes.addFlashAttribute("msg", "Upload xx data sukses");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("msg", e.getMessage());

        }
        return "redirect:list";
    }

    private void readFile(String user, String password, String file, String ip, String port, String dir) throws Exception {
        try {
            FTPClient ftpClient = new FTPClient();
            ftpClient.setConnectTimeout(60000);
            ftpClient.connect(ip, Integer.parseInt(port));
            if (!ftpClient.login(user, password)) {
                throw new Exception("Login ke FTP Gagal");
            }
            ftpClient.setDataTimeout(60000);
            ftpClient.changeWorkingDirectory(dir);
            FTPFile[] files = ftpClient.listFiles();
            if (files.length == 0) {
                throw new Exception("File kosong");
            }
            for (FTPFile f : files) {
                if (f.getName().equalsIgnoreCase(file)) {
                    readFile(ftpClient, f);
                    break;
                }
            }
            ftpClient.logout();
            ftpClient.disconnect();
        } catch (MalformedURLException e) {
            throw e;
        } catch (IOException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }
    }


    private void readFile(FTPClient ftpClient, FTPFile ftpFile) throws Exception {
        List<ZipCodeDto> dataList = new ArrayList<ZipCodeDto>();
        InputStream isReader = null;
        BufferedReader reader = null;
        try {
            isReader = ftpClient.retrieveFileStream(ftpFile.getName());
            reader = new BufferedReader(new InputStreamReader(isReader));
            String lineReader;
            while ((lineReader = reader.readLine()) != null) {
                String[] split = lineReader.split(",");
                ZipCodeDto dto = new ZipCodeDto();
                dto.setProvince(split[0].trim());
                dto.setCity(split[1].trim());
                dto.setDistrict(split[2].trim());
                dto.setSubDistrict(split[3].trim());
                dto.setCode(split[4].trim());
                try {
                    dto.setPicName(split[5].trim());
                } catch (Exception e) {

                }
                dataList.add(dto);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                if(isReader!=null) isReader.close();
                if(reader!=null) reader.close();
                System.gc();
                Runtime.getRuntime().freeMemory();
            } catch (IOException ex) {
                logger.error(ex);
            }
        }

        logger.debug("total data to save " + dataList.size());
        facade.save(dataList);
    }

    @RequestMapping(value = "find", method = RequestMethod.GET)
    @ResponseBody
    public DataTables<ZipCodeDto> find(@ModelAttribute("formSearch") ZipCodeDto dto) {
        return facade.find(dto, getPagingInfo()).cloneToDataTable();
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Model model, @ModelAttribute("formBean") ZipCodeDto dto) {
        return DIR_VIEW + "list";
    }


    @RequestMapping(value = {"add", "edit"}, method = RequestMethod.GET)
    public String form(Model model, @RequestParam(value = "id", required = false) Long id) {
        if (id == null) {
            model.addAttribute("formBean", new ZipCodeDto());
        } else {
            model.addAttribute("formBean", facade.get(id));
        }
        return DIR_VIEW + "form";
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(@ModelAttribute("formBean") @Valid ZipCodeDto dto, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return DIR_VIEW + "form";
        }
        boolean edit = dto.getId() != null;
        dto = facade.save(dto);
        if (edit) {
            redirectAttributes.addFlashAttribute("msg", "Data updated successfully!");
        } else {
            redirectAttributes.addFlashAttribute("msg", "Data added successfully!");
        }
        return "redirect:list";
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

    @RequestMapping(value = "updatePic", method = RequestMethod.GET)
    public String updatePic(Model model) {
        model.addAttribute("listPropinsi", facade.findPropinsi());
        model.addAttribute("listPic",facade.findPersonByRole());
        return DIR_VIEW + "updatePic";
    }

    @RequestMapping(value = "updatePic", method = RequestMethod.POST)
    public String updatePic(@RequestParam("province") String province,
                            @RequestParam("city") String city,
                            @RequestParam("district") String district,
                            @RequestParam("picName") String picName,
                            RedirectAttributes redirectAttributes) {
        logger.debug("Nama Pic: "+picName);
        facade.updatePic(province, city, district, picName);
        redirectAttributes.addFlashAttribute("msg", "Update PIC sukses");
        return "redirect:list";
    }

    @RequestMapping(value = "find-kota", method = RequestMethod.POST)
    @ResponseBody
    public List<String> findKota(@RequestParam("propinsi") String propinsi) {
        return facade.findKota(propinsi);
    }

    @RequestMapping(value = "find-kecamatan", method = RequestMethod.POST)
    @ResponseBody
    public List<String> findKecamatan(@RequestParam("propinsi") String propinsi,
                                      @RequestParam("kota") String kota) {
        return facade.findKecamatan(propinsi, kota);
    }

    @RequestMapping(value = "find-pic", method = RequestMethod.GET)
    @ResponseBody
    public List<String> findPic() {
        return facade.findPic();
    }

}