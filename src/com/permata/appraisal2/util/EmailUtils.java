package com.permata.appraisal2.util;


import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class EmailUtils {

    private String from;
    private String[] to;
    private String subject;
    private String text;
    private String smptpAddr;
    private String port;

    public EmailUtils(String from, String[] to, String subject, String text, String smptpAddr, String port) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.text = text;
        this.smptpAddr = smptpAddr;
        this.port = port;
    }


    public void send() throws MessagingException {
//        String smtpAddr = "10.89.2.169";//SMTP Server Exchange
        Properties props = new Properties();
        //props.put("mail.smtp.host", "10.89.1.161");//SMTP Server LoNo
        props.put("mail.smtp.host", smptpAddr);
        props.put("mail.smtp.port", port);

        System.out.println("smtpAddr : " + smptpAddr);

        Session mailSession = Session.getDefaultInstance(props);
        Message simpleMessage = new MimeMessage(mailSession);
        InternetAddress fromAddress = null;
        InternetAddress[] toAddress = new InternetAddress[to.length];

        try {
            fromAddress = new InternetAddress(from);
            for (int i = 0; i < to.length; i++) {
                toAddress[i] = new InternetAddress(to[i]);
            }
        } catch (AddressException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        simpleMessage.setFrom(fromAddress);
        simpleMessage.setRecipients(RecipientType.TO, toAddress);
        simpleMessage.setSubject(subject);
        //simpleMessage.setText(text);
        simpleMessage.setContent(text, "text/html");
        simpleMessage.setSentDate(new Date());
        Transport.send(simpleMessage, toAddress);

    }

    public void sendNotificationReject(String order, String reffNo, String debitur, String alamat, String alasan, String rejector, String group, String email) {
//        this.from = "cpjkt@permatabank.co.id";
//        this.subject = "Notifikasi Reject Order Appraisal - " + debitur;
        this.text = "<table style=\"font-family:Verdana;font-size:10px;\"><tr><td colspan='5'>Salam Permata,<br><br></td><tr>"
                + "<tr><td colspan='5'>Berikut kami sampaikan bahwa,<br></td><tr>"
                + "<tr><td></td> <td>Nomor Order </td>" + "<td> : </td><td colspan=\"3\">" + order + "</td></tr>"
//                + "<tr><td></td> <td>Nomor Reff </td>" + "<td> : </td><td colspan=\"3\">" + reffNo + "</td></tr>"
                + "<tr><td></td> <td>Nama debitur</td>" + "<td> : </td><td colspan=\"3\">" + debitur + "</td></tr>"
                + "<tr><td></td> <td>Alamat</td>" + "<td> : </td><td colspan=\"3\" rowspan='2'>" + alamat + " </td></tr>"
                + "<tr><td><td/><td></td><td colspan=\"3\"></td></tr>"
                + "<tr><td></td><td>Alasan</td>" + "<td> : </td><td  colspan=\"3\"><b>"+ alasan +"</b></td></tr>"
                + "<tr><td><td/><td></td><td colspan=\"3\"></td></tr>"
                + "<tr><td colspan='5'>Kami kembalikan terlebih dahulu untuk segera dilengkapi kekurangannya dan mohon submit kembali.<br><br></td><tr>"
                + "<tr><td colspan='5'>Terima Kasih.<br><br></td><tr>"
                + "<tr><td colspan='5'>"+ rejector +"<br></td><tr>"
                + "<tr><td colspan='5'>Collateral Valuation PermataBank - " + group + "<br></td><tr>"
                + "<tr><td colspan='5'>"+ email + "</font><br></td><tr></table>";




        try {
            this.send();
        } catch (MessagingException ex) {
            Logger.getLogger(EmailUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*
     * public static void main(String[] args) { String data = "Subject : Request
     * Copy Sales Slip\n" + "Card No : 4541790025223266\n" + "Trx Date : 12 Jan
     * 2010\n" + "Merchant : Pacific Computer\n" + "City : Bandung\n" + "MID :
     * 241000116\n" + "Mohon Sales Slip Segera dikirim via fax 62-62-7453023,
     * atau via email ke PIC Chargeback & Retrieval\n";
     *
     * SendMail sm = new SendMail("Gengki Septi<gadi@permatabank.co.id>", new
     * String[]{"gadi@permatabank.co.id"}, "REMINDER REQUEST COPY SALES SLIP",
     * data); try { sm.send(); } catch (MessagingException ex) {
     * Logger.getLogger(SendMail.class.getName()).log(Level.SEVERE, null, ex); }
     * }
     */
}
