package com.artrium.demo.utils;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
@Slf4j
public class CultureParse {
    private static final Logger log = LoggerFactory.getLogger(CultureParse.class);
    public PreparedStatement pstmt;
    public Statement stat;
    public Connection conn;

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String id;

    @Value("${spring.datasource.password}")
    private String password;

    @PostConstruct
    public void insertCulture() throws IOException, SQLException {
        JdbcConnect();
        getConnection();
        if (!checkQuery()) {
            insertQuery();
        }
    }

    public void JdbcConnect(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            log.info("Connection Success");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
    public void getConnection() throws SQLException {
        conn = DriverManager.getConnection(url, id, password);
        log.info("connection: {}", conn);
    }

    public boolean checkQuery() {
        try {
            stat = conn.createStatement();

            String query = "select count(*) as cnt from culture";
            ResultSet rs = stat.executeQuery(query);

            int count = 0;
            if (rs.next()) {
                count = rs.getInt("cnt");
            }

            return count != 0;
        } catch (SQLException e) {
            log.error("checkQuery error", e);
        }

        return false;
    }

    public void insertQuery() throws IOException {
        String path = System.getProperty("user.dir") + "/culture.csv";
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
        } catch(SQLException e){
            log.error("insertQuery error", e);
        }
    }

}
