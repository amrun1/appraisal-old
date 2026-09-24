package com.permata.appraisal2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;

public class MySqlTest {

    public MySqlTest() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_mob", "root", "");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from survey_bv");
            while (rs.next()) {
                System.out.println("survey_id : " + rs.getString(1));
                System.out.println("ditemukan : " + rs.getBoolean(11));
                System.out.println("SI_1_SEX : " + rs.getString(13));
                System.out.println("foto " + Arrays.toString(rs.getBytes(64)));
                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
                System.out.println();
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        new MySqlTest();
    }
}