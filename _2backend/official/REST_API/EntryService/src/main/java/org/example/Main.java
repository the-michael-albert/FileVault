package org.example;

import java.sql.*;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mariadb://localhost:3306/vault";
        String user = "root";
        String password = "password";

        String resourceID = genResID();
        int width = 0;
        int height = 0;
        int duration = 0;

        String filename = "file.mp3";

        int year = 2023;
        int month = 4;
        int date = 14;

        int filetype = 12;

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            // create a prepared statement to insert data into the metadata table
            String metadataSql = "INSERT INTO metadata (width, height, duration, resource_id) VALUES (?, ?, ?, ?)";
            PreparedStatement metadataStmt = conn.prepareStatement(metadataSql, Statement.RETURN_GENERATED_KEYS);
            metadataStmt.setInt(1, width);
            metadataStmt.setInt(2, height);
            metadataStmt.setLong(3, duration);
            metadataStmt.setString(4, resourceID);

            // execute the prepared statement to insert the metadata row
            int metadataRowsAffected = metadataStmt.executeUpdate();
            if (metadataRowsAffected == 1) {
                // if the metadata insert was successful, retrieve the generated id
                ResultSet generatedKeys = metadataStmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    long metadataId = generatedKeys.getLong(1);

                    // create a prepared statement to insert data into the detail table
                    String detailSql = "INSERT INTO detail (id, resource_id, file_name) VALUES (?, ?, ?)";
                    PreparedStatement detailStmt = conn.prepareStatement(detailSql);
                    detailStmt.setLong(1, metadataId);
                    detailStmt.setString(2, resourceID);
                    detailStmt.setString(3, filename);

                    // execute the prepared statement to insert the detail row
                    int detailRowsAffected = detailStmt.executeUpdate();
                    if (detailRowsAffected == 1) {
                        // if the detail insert was successful, create a prepared statement to insert data into the file table
                        String fileSql = "INSERT INTO file (id, resource_id, date_year, date_month, date_day, file_type) VALUES (?, ?, ?, ?, ?, ?)";
                        PreparedStatement fileStmt = conn.prepareStatement(fileSql);
                        fileStmt.setLong(1, metadataId);
                        fileStmt.setString(2, resourceID);
                        fileStmt.setInt(3, year);
                        fileStmt.setInt(4, month);
                        fileStmt.setInt(5, date);
                        fileStmt.setInt(6, filetype);

                        // execute the prepared statement to insert the file row
                        int fileRowsAffected = fileStmt.executeUpdate();
                        if (fileRowsAffected == 1) {
                            System.out.println("Data inserted successfully.");
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String genResID(){
        String out = "";
        String alpha = "abcdefghijklmnopqrstuvwzyx";
        for (int i = 0; i < 40; i++) {
            out += alpha.charAt(new Random().nextInt(alpha.length()));
        }
        return out;
    }
}
