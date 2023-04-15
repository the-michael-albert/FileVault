package database;

import commons.FileObject;

import java.sql.*;

public class DatabaseConnector {

    DBConfig config = new DBConfig();

    public boolean insertEntry(FileObject file){

        int width = file.getWidth();
        int height = file.getHeight();
        int duration = file.getDuration();

        int year = file.getYear();
        int month = file.getMonth();
        int date = file.getDate();

        String filename = file.getName();
        String resourceID = file.getResourceID();
        int filetype = file.getFiletype();

        try (Connection conn = DriverManager.getConnection(config.getUrl(), config.getUser(), config.getPassword())) {
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
                    } else {return false;}
                }
            } else {return false;}
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
