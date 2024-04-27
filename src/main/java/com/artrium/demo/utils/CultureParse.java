package com.artrium.demo.utils;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class CultureParse {
    private static final Logger log = LoggerFactory.getLogger(CultureParse.class);
    public static PreparedStatement pstmt;
    public static Connection conn;

    @PostConstruct
    public void insertCulture() throws IOException, SQLException {
        JdbcConnect();
        getConnection();
        insertQuery();
    }

    public static void JdbcConnect(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("OK!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
    public static void getConnection() throws SQLException {
        String url = "jdbc:h2:mem:test";
        String id = "sa";
        String pass = "";
//        String url = "jdbc:mysql://mydatabase.czogkye88fqc.ap-northeast-2.rds.amazonaws.com/seoul";
//        String id = "artrium";
//        String pass = "12345678";
        conn = DriverManager.getConnection(url,id,pass);
        System.out.println(conn);
    }

    public static void insertQuery() throws IOException {
        String path = "/Users/doyoon/Downloads/서울시 문화행사 정보.csv";
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "EUC-KR"));
        String line = null;

        br.readLine(); // 첫번째 줄 생략

        try {
            String query = "insert into " +
                    "culture (category, gu_name, title, place, org_name, target, fee, homepage, img, " +
                    "start_date, end_date, lot, lat, is_free) " +
                    "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            pstmt = conn.prepareStatement(query);

            while ((line = br.readLine()) != null) {
                String[] tmp = line.split("\",\"");

                String category = tmp[0].replace("\"", "");
                String guName = tmp[1];
                String title = tmp[2];
                String place = tmp[4];
                String orgName = tmp[5];
                String target = tmp[6];
                String fee = tmp[7];
                String homepage = tmp[11];
                String img = tmp[12];
                Date startDate = Date.valueOf(LocalDate.parse(tmp[15].split(" ")[0], DateTimeFormatter.ISO_DATE));
                Date endDate = Date.valueOf(LocalDate.parse(tmp[16].split(" ")[0], DateTimeFormatter.ISO_DATE));

                double lot, lat;
                try {
                    lot = Double.parseDouble(tmp[18]);
                    lat = Double.parseDouble(tmp[19]);
                } catch (Exception e) {
                    continue;
                }

                boolean isFree = tmp[20].equals("무료");

                pstmt.setString(1, category);
                pstmt.setString(2, guName);
                pstmt.setString(3, title);
                pstmt.setString(4, place);
                pstmt.setString(5, orgName);
                pstmt.setString(6, target);
                pstmt.setString(7, fee);
                pstmt.setString(8, homepage);
                pstmt.setString(9, img);
                pstmt.setDate(10, startDate);
                pstmt.setDate(11, endDate);
                pstmt.setDouble(12, lot);
                pstmt.setDouble(13, lat);
                pstmt.setBoolean(14, isFree);

                pstmt.executeUpdate();
            }
        } catch(SQLException ee){
            System.err.println("error = " + ee.toString());
        }
    }

}
