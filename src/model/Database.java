package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Bang on 10/6/16.
 */
public class Database {
    private static List<WaterSourceReport> reportList;
    //declare connection, statement and resultSet objects
    private static Connection connection = null;
    private static PreparedStatement stmt = null;
    private static ResultSet rs = null;
    private static Database database;


    /**
     * Gettter of database.
     * @return
     */
    public static Database getDatabase() {
        return database;
    }


    /**
     * Update information of an account to database.
     * @param fname first name of user.
     * @param lname last name of user.
     * @param email email address of user.
     * @param phone phone number of user.
     * @param address address of user.
     * @param username username of user.
     */
    public static void updateAccount(String fname,
                                     String lname,
                                     String email,
                                     String phone, String address, String username) {

        try {
            String SQL = "UPDATE user SET fname = ?, lname = ?, email = ? , phone = ?, address = ? where username = ?";
            stmt = connection.prepareStatement(SQL);
            stmt.setString (1, fname);
            stmt.setString (2, lname);
            stmt.setString (3, email);
            stmt.setString (4, phone);
            stmt.setString (5, address);
            stmt.setString (6, username);
            stmt.executeUpdate();
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) { } // ignore
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) { } // ignore
            }
        }
    }

    /**
     * Obtain user Account from database
     * @param username of user Account
     * @return account of that username.
     */
    public static Account getAccount(String username) {
        Account account = null;

        try {
            String SQL = "SELECT * FROM user WHERE username= ?";
            stmt = connection.prepareStatement(SQL);
            stmt.setString(1,username);
            rs = stmt.executeQuery();

            while (rs.next())
            {
                String username_ = rs.getString("username");
                String firstName = rs.getString("fname");
                String lastName = rs.getString("lname");
                String email = rs.getString("email");
                String type = rs.getString("type");
                String phone = rs.getString("phone") + "";
                String address = rs.getString("address");

                account = new Account(username, firstName, lastName, email, AccountType.valueOf(type), phone, address);
            }
            stmt.close();

        } catch (SQLException error) {
            System.out.println(error.getMessage());
        }

        return account;
    }

    /**
     * Check if user name matches password in database.
     * @param username to log in
     * @param password of that user.
     * @return if user name matches password in database
     */
    public static boolean login(String username, String password) {

        try {
            if (username != null && password != null) {
                String SQL = "SELECT * FROM user WHERE username= ? AND password= ?";
                stmt = connection.prepareStatement(SQL);
                stmt.setString(1,username);
                stmt.setString(2,password);
                rs = stmt.executeQuery();

                if (rs.next()) {
                    return true;
                }
            }

        } catch (SQLException error) {
            System.out.println(error.getMessage());
        }
        return false;
    }

    /**
     * Validate if username is available to register.
     * @param username to validate
     * @return if username is available.
     */
    public static boolean validateUsername(String username) {

        try {
            String SQL = "SELECT `username` FROM `schema`.user WHERE username = ?";
            stmt = connection.prepareStatement(SQL);
            stmt.setString(1,username);
            rs = stmt.executeQuery();

            if (!rs.next()) {
                return true;
            }
        }
        catch (SQLException ex){
            System.out.println(ex.getErrorCode());
        }
        finally {
            if (rs != null) {
                try {
                  rs.close();
                } catch (SQLException sqlEx) { } // ignore
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) { } // ignore
            }
        }
        return false;
    }

    /**
     * Added a user to user table in data base
     * @param username of user
     * @param password of user
     * @param fname first name.
     * @param lname last name.
     * @param email email address.
     * @param type type of user.
     */
    public static void addUser(String username, String password, String fname
            , String lname, String email, AccountType type) {

        try {
            String query = "INSERT INTO `schema`.`user` (`id`, `username`, `password`" +
                    ", `fname`, `lname`, `email`, `type`)"
                    + " VALUES (null, ?, ?, ?, ?, ?, ?)";

            stmt = connection.prepareStatement(query);
            stmt.setString (1, username);
            stmt.setString (2, password);
            stmt.setString (3, fname);
            stmt.setString (4, lname);
            stmt.setString (5, email);
            stmt.setString (6, type.toString());
            stmt.executeUpdate();
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) { } // ignore
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) { } // ignore
            }
        }
    }

    /**
     * Added water source report to database.
     * @param username user who submit the report.
     * @param latitude of location report
     * @param longitude of location report
     * @param waterType type of water
     * @param waterCondition condition of water
     * @param date date of report
     */
    public static void addWaterSourceReport(String username , String latitude
            , String longitude, WaterType waterType, WaterCondition waterCondition, String date) {

        try {
            String query = "INSERT INTO `schema`.`WaterSourceReport` (`ReportNumber`, `Username`, `Latitude`" +
                    ", `Longitude`, `WaterType`, `WaterCondition`, `Date`)"
                    + " VALUES (null, ?, ?, ?, ?, ?, ?)";

            stmt = connection.prepareStatement(query);
            stmt.setString (1, username);
            stmt.setString (2, latitude);
            stmt.setString (3, longitude);
            stmt.setString (4, waterType.toString());
            stmt.setString (5, waterCondition.toString());
            stmt.setString (6, date);

            stmt.executeUpdate();
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) { } // ignore
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) { } // ignore
            }
        }
    }

    /**
     * Retreive water source report from database.
     * @return list of water source report
     */
    public static List<WaterSourceReport> getWaterSourceReports() {
        String query = "SELECT * FROM WaterSourceReport";
        reportList = new ArrayList<>();

        try {
            stmt = connection.prepareStatement(query);
            rs = stmt.executeQuery();

            while (rs.next())
            {
                int reportNumber = rs.getInt("ReportNumber");
                String username = rs.getString("Username");
                Double latitude = rs.getDouble("Latitude");
                Double longitude = rs.getDouble("Longitude");
                String type = rs.getString("WaterType");
                String condition = rs.getString("WaterCondition");
                String date = rs.getString("Date");
                WaterSourceReport report;
                report = new WaterSourceReport(reportNumber,
                        username, latitude, longitude, WaterType.valueOf(type),
                        WaterCondition.valueOf(condition), date);
                reportList.add(report);
            }
            stmt.close();

        } catch (SQLException error) {
            System.out.println(error.getMessage());
        }

        return reportList;
    }


    /**
     * Connect to database.
     * @return null
     */
    public static Database connectToDatabase(){

        //load jdbc driver for mysql database
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }catch(Exception e) {
            System.out.println("Unable to load Driver");
        }

        //Establish connection using DriverManager
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/schema?" +
                    "user=root&password=pass");
        } catch (SQLException e) {
            System.out.println("Unable to connect to database");
        }
        return null;
    }
}
