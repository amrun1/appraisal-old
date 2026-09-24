package com.permata.appraisal2.process;

import com.permata.appraisal2.common.AppConstans;
import com.permata.appraisal2.dto.IlonDto;
import com.permata.appraisal2.dto.PriorityCategoryDto;
import com.permata.appraisal2.facade.IloanFacade;
import com.permata.appraisal2.facade.PriorityCategoryFacade;
import com.permata.appraisal2.facade.SystemParamFacade;
import com.permata.appraisal2.model.SendToSourceLog;
import com.permata.appraisal2.ws.client.iloan.ntiapr.Ntiapr_bndStub;
import com.permata.appraisal2.ws.client.iloan.ntiapr.Ntiapr_idLocator;
import com.permata.appraisal2.ws.client.iloan.ntiapr.Ntiapr_prt;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Scheduled;

import javax.xml.rpc.holders.StringHolder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * fitur nanti,
 *  1. jika terjadi gagal 3x maka proses reset menjadi sebelumya
 *  2. jika sukses input ke database, file di FTP di rename
 */

@Scope
public class RwaFtpThread {

    Logger logger = Logger.getLogger(RwaFtpThread.class);

    public static int STATUS_IDLE = 0;
    public static int STATUS_REQUEST_PROSES = 1;
    public static int STATUS_REQUEST_SUKSES = 2;
    public static int STATUS_REQUEST_GAGAL = 3;
    public static int STATUS_FTP_PROSES = 4;
    public static int STATUS_FTP_SUKSES = 5;
    public static int STATUS_FTP_GAGAL = 6;
    public static int STATUS_READ_PROSES = 7;
    public static int STATUS_READ_SUKSES = 8;
    public static int STATUS_READ_GAGAL = 9;
    public static int STATUS_SAVE_PROSES = 10;
    public static int STATUS_SAVE_SUKSES = 11;
    public static int STATUS_SAVE_GAGAL = 12;

    int timeout = 1000 * 60 * 1; // mili * second * minute

    private int proses = STATUS_IDLE;

    @Autowired
    private SystemParamFacade facade;
    @Autowired
    private PriorityCategoryFacade priorityCategoryFacade;
    @Autowired
    private IloanFacade iloanFacade;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    SimpleDateFormat sdfDowndate = new SimpleDateFormat("yyyy-MM-dd");

    private void updateStatus(int code, String msg) {
        facade.update(AppConstans.SystemParamCode.RWA_PROSES, String.valueOf(code));
        facade.update(AppConstans.SystemParamCode.RWA_PROSES_MSG, msg);
        facade.update(AppConstans.SystemParamCode.RWA_PROSES_DATE, sdf2.format(new Date()));
        proses = code;
    }

    private void sendRequestFile() {
        updateStatus(STATUS_REQUEST_PROSES, "Proses send request file");
        try {
            Ntiapr_idLocator locator = new Ntiapr_idLocator();
            Ntiapr_prt service = locator.getntiapr_srv_Port(new URL(facade.getValue(AppConstans.SystemParamCode.RWA_WS_IP_RECV)));
            ((Ntiapr_bndStub) service).setUsername(facade.getValue(AppConstans.SystemParamCode.RWA_WS_USER));
            ((Ntiapr_bndStub) service).setPassword(facade.getValue(AppConstans.SystemParamCode.RWA_WS_PASSWORD));
            ((Ntiapr_bndStub) service).setTimeout(timeout);

            StringHolder desc = new StringHolder();
            StringHolder errc = new StringHolder();

            Calendar now = Calendar.getInstance();
            int intMonth = now.get(Calendar.MONTH)+1;
            String month = intMonth<10?"0"+intMonth: ""+intMonth;
            String ddate = now.get(Calendar.YEAR) + "-" + month + "-" + now.get(Calendar.DAY_OF_MONTH);

            service.ntiapr_opr(
                    facade.getValue(AppConstans.SystemParamCode.RWA_FTP_DIR),
                    ddate,
                    facade.getValue(AppConstans.SystemParamCode.RWA_FTP_IP),
                    facade.getValue(AppConstans.SystemParamCode.RWA_FILE_NAME),
                    facade.getValue(AppConstans.SystemParamCode.RWA_FTP_PASSWORD),
                    facade.getValue(AppConstans.SystemParamCode.RWA_FTP_PORT),
                    facade.getValue(AppConstans.SystemParamCode.RWA_FTP_USER),
                    desc, errc
            );
            logger.debug("desc.value -> " + desc.value);
            logger.debug("errc.value -> " + errc.value);
            if (errc.value.equalsIgnoreCase("01") && desc.value.toLowerCase().contains("error")) {
                updateStatus(STATUS_REQUEST_GAGAL, desc.value);
                System.out.println("error code value = " + errc.value);
                System.out.println("Description Value = " + desc.value);
                return;
            } else if (errc.value.equalsIgnoreCase("00")) {
                updateStatus(STATUS_REQUEST_SUKSES, "x");
                facade.update(AppConstans.SystemParamCode.RWA_DOWN_DATE, ddate);
                downloadFtp();
            } else {
                updateStatus(STATUS_REQUEST_GAGAL, desc.value);
                System.out.println("error code value = " + errc.value);
                System.out.println("Description Value = " + desc.value);
            }


        } catch (javax.xml.rpc.ServiceException ex) {
            updateStatus(STATUS_REQUEST_GAGAL, ex.getMessage());
            System.out.println("Service Exception ");
        } catch (java.rmi.RemoteException ex) {
            updateStatus(STATUS_REQUEST_GAGAL, ex.getMessage());
            System.out.println("RemoteException  ");
        }catch (MalformedURLException e1) {
            e1.printStackTrace();
        }
    }

    private void downloadFtp() {
        updateStatus(STATUS_FTP_PROSES, "Proses download file from FTP");
        URL url = null;
        try {
            FTPClient ftpClient = new FTPClient();
            ftpClient.setConnectTimeout(timeout);
            ftpClient.connect(
                    facade.getValue(AppConstans.SystemParamCode.RWA_FTP_IP),
                    Integer.parseInt(facade.getValue(AppConstans.SystemParamCode.RWA_FTP_PORT)));
            if (!ftpClient.login(facade.getValue(AppConstans.SystemParamCode.RWA_FTP_USER), facade.getValue(AppConstans.SystemParamCode.RWA_FTP_PASSWORD))) {
                updateStatus(STATUS_FTP_GAGAL, "Gagal login FTP");
                return;
            }
            ftpClient.setDataTimeout(timeout); // timeout read file
            ftpClient.changeWorkingDirectory(facade.getValue(AppConstans.SystemParamCode.RWA_FTP_DIR));
            FTPFile[] files = ftpClient.listFiles();
            if (files.length == 0) {
                updateStatus(STATUS_FTP_GAGAL, "File di FTP Kosong");
                return;
            }
            boolean readFile = false;
            String fileName = facade.getValue(AppConstans.SystemParamCode.RWA_FILE_NAME);
            for (FTPFile f : files) {
                if (f.getName().equalsIgnoreCase(fileName)) {
                    readFile = true;
                    updateStatus(STATUS_FTP_SUKSES, "next proses read file");
                    readFile(ftpClient, f);
                    break;
                }
            }
            if (!readFile) {
                updateStatus(STATUS_FTP_GAGAL, "File Untuk dibaca tidak ada");
            }
            ftpClient.logout();
            ftpClient.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            updateStatus(STATUS_FTP_GAGAL, e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            updateStatus(STATUS_FTP_GAGAL, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            updateStatus(STATUS_FTP_GAGAL, e.getMessage());
        }
    }

    @Scheduled(cron = "0 0 22 * * ?")
//    @Scheduled(cron = "0 20 11 1/1 * ?")
    public void downloadFile() {
        //  NOTES
        //  Comment this for Non scheduler Job
        //  Start Comment
        // System.out.println("Runing downfile RWA....");
        // String val = facade.getValue(AppConstans.SystemParamCode.RWA_PROSES);
        // if (StringUtils.isBlank(val)) {
        //     facade.update(AppConstans.SystemParamCode.RWA_PROSES, String.valueOf(STATUS_IDLE));
        //     proses = STATUS_IDLE;
        // } else {
        //     proses = Integer.valueOf(val);
        // }

        // // reset proses jika sudah tanggal reset per bulannya
        // Calendar cal = Calendar.getInstance();
        // String stringDowndate = facade.getValue(AppConstans.SystemParamCode.RWA_DOWN_DATE);
        // Calendar downdate = Calendar.getInstance();
        // if(StringUtils.isNotBlank(stringDowndate)) {
        //     downdate.setTime(toDate(stringDowndate, sdfDowndate));
        //     downdate.add(Calendar.MONTH, 1);
        // }
        // int tanggalReset = Integer.valueOf(facade.getValue(AppConstans.SystemParamCode.RWA_TANGGAL_RESET));
        // if (cal.get(Calendar.DAY_OF_MONTH) == tanggalReset || StringUtils.isBlank(stringDowndate)) {
        //     updateStatus(STATUS_IDLE, "Tanggal " + tanggalReset + " Reset proses");
        // } else if (downdate.compareTo(cal)<=0) {
        //     updateStatus(STATUS_IDLE, "Tanggal " + tanggalReset + " Reset proses");
        // }

        // if (proses == STATUS_IDLE || proses == STATUS_REQUEST_GAGAL) {
        //     sendRequestFile();
        // } else if (proses == STATUS_REQUEST_SUKSES || proses == STATUS_FTP_GAGAL || proses == STATUS_READ_GAGAL || proses == STATUS_SAVE_GAGAL) {
        //     downloadFtp(); // download, read, and save
        // } else {
        //     logger.debug("status == " + proses + ", do nothing");
        // }
        // System.out.println("Finish downloading RWA");
        //  End Comment
    }

    private void readFile(FTPClient ftpClient, FTPFile ftpFile) {
        updateStatus(STATUS_READ_PROSES, "Read file per-line");
        List<PriorityCategoryDto> priorityList = priorityCategoryFacade.list();
        List<IlonDto> listData = new ArrayList<IlonDto>();
        InputStream isReader = null;
        BufferedReader reader = null;
        try {
            isReader = ftpClient.retrieveFileStream(ftpFile.getName());
            reader = new BufferedReader(new InputStreamReader(isReader));
            String lineReader, tmpVar;
            BigDecimal outstanding;
            while ((lineReader = reader.readLine()) != null) {
                IlonDto dto = new IlonDto();
                if (lineReader.length() > 800) {
                    tmpVar = lineReader.substring(0, 10);
                    lineReader = lineReader.substring(10);
                    dto.setDownDate(toDate(tmpVar, sdfDowndate));   //10          DOWN_DATE

                    tmpVar = lineReader.substring(0, 19);
                    lineReader = lineReader.substring(19);
                    dto.setAccountNo(StringUtils.trim(tmpVar));// 19          ACC_NO

                    tmpVar = lineReader.substring(0, 7);
                    lineReader = lineReader.substring(7);
                    dto.setCifJha(StringUtils.trim(tmpVar));// 7          CIF_JHA

                    tmpVar = lineReader.substring(0, 40);
                    lineReader = lineReader.substring(40);
                    dto.setName(StringUtils.trim(tmpVar));//            40          NAME

                    tmpVar = lineReader.substring(0, 19);
                    lineReader = lineReader.substring(19);
                    dto.setPlafond(toBigDecimal(tmpVar));//            19          PLAFOND

                    tmpVar = lineReader.substring(0, 19);
                    lineReader = lineReader.substring(19);
                    dto.setOutstanding(toBigDecimal(tmpVar));//            19          OUTSTANDING

                    tmpVar = lineReader.substring(0, 10);
                    lineReader = lineReader.substring(10);
                    dto.setMaturDate(toDate(tmpVar, sdfDowndate));//            10          MATUR_DATE

                    tmpVar = lineReader.substring(0, 35);
                    lineReader = lineReader.substring(35);
                    dto.setCollateralId(StringUtils.trim(tmpVar));//            35          COLL_ID

                    tmpVar = lineReader.substring(0, 35);
                    lineReader = lineReader.substring(35);
                    dto.setGroupVal(StringUtils.trim(tmpVar));//            35          GROUP_VAL

                    tmpVar = lineReader.substring(0, 3);
                    lineReader = lineReader.substring(3);
                    dto.setCollateralCode(StringUtils.trim(tmpVar));//             3          COLL_CODE

                    tmpVar = lineReader.substring(0, 5);
                    lineReader = lineReader.substring(5);
                    dto.setCollateralCategory(StringUtils.trim(tmpVar));//         5          COLL_CATEGORY

                    tmpVar = lineReader.substring(0, 40);
                    lineReader = lineReader.substring(40);
                    dto.setBuildingOwner(StringUtils.trim(tmpVar));//        40          BUILDING OWNERSHIP

                    tmpVar = lineReader.substring(0, 1);
                    lineReader = lineReader.substring(1);
                    dto.setSertificateStatus(Boolean.valueOf(StringUtils.trim(tmpVar)));//             1          STATUS SERTIPIKAT

                    tmpVar = lineReader.substring(0, 30);
                    lineReader = lineReader.substring(30);
                    dto.setTitleNumber(StringUtils.trim(tmpVar));//         30          TITLE NUMBER

                    tmpVar = lineReader.substring(0, 3);
                    lineReader = lineReader.substring(3);
                    dto.setSertificateType(StringUtils.trim(tmpVar));//         3          JENIS SERTIPIKAT

                    tmpVar = lineReader.substring(0, 25);
                    lineReader = lineReader.substring(25);
                    dto.setPicNumber(StringUtils.trim(tmpVar));//         25          NO GAMBAR SIUTASI

                    tmpVar = lineReader.substring(0, 160);
                    lineReader = lineReader.substring(160);
                    dto.setLocation(StringUtils.trim(tmpVar));//         160          LOKASI

                    tmpVar = lineReader.substring(0, 4);
                    lineReader = lineReader.substring(4);
                    dto.setLocationCode(StringUtils.trim(tmpVar));//        4          SANDI LOKASI

                    tmpVar = lineReader.substring(0, 19);
                    lineReader = lineReader.substring(19);
                    dto.setAprExt(toBigDecimal(tmpVar));//         19          APR EXT

                    tmpVar = lineReader.substring(0, 10);
                    lineReader = lineReader.substring(10);
                    dto.setAprExtDate(toDate(tmpVar));//         10          APR DATE EXT

                    tmpVar = lineReader.substring(0, 10);
                    lineReader = lineReader.substring(10);
                    dto.setExtValCode(StringUtils.trim(tmpVar));//         10          EXT VAL CODE

                    tmpVar = lineReader.substring(0, 19);
                    lineReader = lineReader.substring(19);
                    dto.setAprInt(toBigDecimal(tmpVar));//         19          APR INT

                    tmpVar = lineReader.substring(0, 10);
                    lineReader = lineReader.substring(10);
                    dto.setAprIntDate(toDate(tmpVar));// 10          APR DATE INT

                    tmpVar = lineReader.substring(0, 10);
                    lineReader = lineReader.substring(10);
                    dto.setIntValCode(StringUtils.trim(tmpVar));//10          INT VAL CODE

                    tmpVar = lineReader.substring(0, 19);
                    lineReader = lineReader.substring(19);
                    dto.setExtLandAprDoc(toBigDecimal(tmpVar));//19          EXT LAND APR DOC

                    tmpVar = lineReader.substring(0, 19);
                    lineReader = lineReader.substring(19);
                    dto.setExtBldAprDoc(toBigDecimal(tmpVar));//19          EXT BLD APR DOC

                    tmpVar = lineReader.substring(0, 19);
                    lineReader = lineReader.substring(19);
                    dto.setExtTotDoc(toBigDecimal(tmpVar));//19          EXT TOT DOC

                    tmpVar = lineReader.substring(0, 19);
                    lineReader = lineReader.substring(19);
                    dto.setExtLandAprFisik(toBigDecimal(tmpVar));//19          EXT LAND APR FISIK

                    tmpVar = lineReader.substring(0, 19);
                    lineReader = lineReader.substring(19);
                    dto.setExtBldAprFisik(toBigDecimal(tmpVar));//19          EXT BLD APR FISIK

                    tmpVar = lineReader.substring(0, 19);
                    lineReader = lineReader.substring(19);
                    dto.setExtTotFisik(toBigDecimal(tmpVar));//19          EXT TOT FISIK

                    tmpVar = lineReader.substring(0, 19);
                    lineReader = lineReader.substring(19);
                    dto.setIntLandAprDoc(toBigDecimal(tmpVar));//19          INT LAND APR DOC

                    tmpVar = lineReader.substring(0, 19);
                    lineReader = lineReader.substring(19);
                    dto.setIntBldAprDoc(toBigDecimal(tmpVar));//19          INT BLD APR DOC

                    tmpVar = lineReader.substring(0, 19);
                    lineReader = lineReader.substring(19);
                    dto.setIntTotDoc(toBigDecimal(tmpVar));//19          INT TOT DOC

                    tmpVar = lineReader.substring(0, 19);
                    lineReader = lineReader.substring(19);
                    dto.setIntLandAprFisik(toBigDecimal(tmpVar));//19          INT LAND APR FISIK

                    tmpVar = lineReader.substring(0, 19);
                    lineReader = lineReader.substring(19);
                    dto.setIntBldAprFisik(toBigDecimal(tmpVar));//19          INT BLD APR FISIK

                    tmpVar = lineReader.substring(0, 19);
                    lineReader = lineReader.substring(19);
                    dto.setIntTotFisik(toBigDecimal(tmpVar));//19          INT TOT FISIK

                    tmpVar = lineReader.substring(0, 25);
                    lineReader = lineReader.substring(25);
                    dto.setNoSht(StringUtils.trim(tmpVar));//25          NO SHT

                    tmpVar = lineReader.substring(0, 25);
                    lineReader = lineReader.substring(25);
                    dto.setNoApht(StringUtils.trim(tmpVar));//25          NO APHT

                    tmpVar = lineReader.substring(0, 10);
                    lineReader = lineReader.substring(10);
                    dto.setAphtDate(toDate(tmpVar));//10          APHT DATE

                    tmpVar = lineReader.substring(0, 61);
                    lineReader = lineReader.substring(61);
                    dto.setNoSuratIjin(StringUtils.trim(tmpVar));//30          NO SURAT IJIN

                    tmpVar = lineReader.substring(0, 20);
                    lineReader = lineReader.substring(20);
                    dto.setAppNumber(StringUtils.trim(tmpVar));//30          APPLICATION NUMBER

                    tmpVar = lineReader.substring(0, 3);
                    lineReader = lineReader.substring(3);
                    dto.setFacilityCode(StringUtils.trim(tmpVar));//30          FACILITY CODE

                    tmpVar = lineReader.substring(0, 9);
                    lineReader = lineReader.substring(9);
                    dto.setSeqNumber(toLong(tmpVar));//30          SEQ NO

                    tmpVar = lineReader.substring(0, 20);
                    lineReader = lineReader.substring(20);
                    dto.setFacilityNumber(StringUtils.trim(tmpVar));//30          FAS NO

                    tmpVar = lineReader.substring(0, 20);
                    lineReader = lineReader.substring(20);
                    dto.setMainFacilityAa(StringUtils.trim(tmpVar));//30          MAIN FAC AA

                    tmpVar = lineReader.substring(0, 3);
                    lineReader = lineReader.substring(3);
                    dto.setMainFacilityCode(StringUtils.trim(tmpVar));//30          MAIN FAC COD

                    tmpVar = lineReader.substring(0, 9);
                    lineReader = lineReader.substring(9);
                    dto.setMainFacilitySeq(toLong(tmpVar));//30          MAIN FAC SEQ

                    tmpVar = lineReader.substring(0, 21);
                    dto.setFacilityNo(StringUtils.removeEnd(StringUtils.trim(tmpVar), "^"));//30          FAS NO

                    if (priorityList.size() > 0) {
                        PriorityCategoryDto lastCategory = new PriorityCategoryDto();
                        PriorityCategoryDto category;
                        for (int i = 0; i < priorityList.size(); i++) {
                            if (dto.getOutstanding() != null) {
                                category = priorityList.get(i);
                                outstanding = dto.getOutstanding();
                                if (i == 0) {
                                    if (outstanding.compareTo(category.getNilai()) >= 0) {
                                        dto.setPriority(category.getId());
                                        break;
                                    }
                                } else {
                                    if (outstanding.compareTo(category.getNilai()) >= 0 && outstanding.compareTo(lastCategory.getNilai()) < 0) {
                                        dto.setPriority(category.getId());
                                        break;
                                    }
                                }
                                lastCategory = category;
                            }
                        }
                    } else if (dto.getOutstanding() != null) {
                        outstanding = dto.getOutstanding();
                        if (outstanding.compareTo(new BigDecimal("5000000000")) >= 0) {
                            dto.setPriority(0l);
                        } else if (outstanding.compareTo(new BigDecimal("3000000000")) >= 0 && outstanding.compareTo(new BigDecimal("5000000000")) < 0) {
                            dto.setPriority(1l);
                        } else if (outstanding.compareTo(new BigDecimal("1000000000")) >= 0 && outstanding.compareTo(new BigDecimal("3000000000")) < 0) {
                            dto.setPriority(2l);
                        } else if (outstanding.compareTo(new BigDecimal("500000000")) >= 0 && outstanding.compareTo(new BigDecimal("1000000000")) < 0) {
                            dto.setPriority(3l);
                        } else if (outstanding.compareTo(new BigDecimal("250000000")) >= 0 && outstanding.compareTo(new BigDecimal("500000000")) < 0) {
                            dto.setPriority(4l);
                        } else if (outstanding.compareTo(new BigDecimal("250000000")) < 0) {
                            dto.setPriority(5l);
                        }
                    }

                    dto.setCreatedDate(new Date());
                    listData.add(dto);
                }
            }
            reader.close();
            updateStatus(STATUS_READ_SUKSES, "Sukses read all file");
        } catch (Exception ex) {
            updateStatus(STATUS_READ_GAGAL, ex.getMessage());
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

        if (proses == STATUS_READ_SUKSES) {
            saveData(listData);
        }
    }

    private void saveData(List<IlonDto> listData){
        updateStatus(STATUS_SAVE_PROSES, "Save List Data " + listData.size());
        try {
            iloanFacade.trancate();
            iloanFacade.saveAll(listData);
            updateStatus(STATUS_SAVE_SUKSES, "All proses success");
        } catch (Exception e) {
            updateStatus(STATUS_SAVE_GAGAL, e.getMessage());
        }
    }

    private BigDecimal toBigDecimal(String str) {
        try {
            return BigDecimal.valueOf(Double.valueOf(StringUtils.trim(str)));
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }

    private Long toLong(String str) {
        try {
            return Long.valueOf(StringUtils.trim(str));
        } catch (Exception e) {
            return Long.valueOf(0l);
        }
    }

    private Date toDate(String str) {
        if (StringUtils.isNotBlank(str)) {
            try {
                return sdf.parse(str.trim());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private Date toDate(String str, SimpleDateFormat sdf) {
        if (StringUtils.isNotBlank(str)) {
            try {
                return sdf.parse(str.trim());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}