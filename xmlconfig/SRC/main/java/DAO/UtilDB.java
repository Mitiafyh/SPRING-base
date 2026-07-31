package main.java.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UtilDB {
    private String URL;
    private String USER;
    private String PASSWORD;

    public String getURL() {
        return URL;
    }

    public void setURL(String uRL) {
        URL = uRL;
    }

    public String getUSER() {
        return USER;
    }

    public void setUSER(String uSER) {
        USER = uSER;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String pASSWORD) {
        PASSWORD = pASSWORD;
    }

    public static void setConnection(Connection connection) {
        UtilDB.connection = connection;
    }

    private static Connection connection;

    public Connection getConnection() throws SQLException {
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Charge le driver MySQL
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver JDBC MySQL introuvable !", e);
        }

        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(this.URL, this.USER, this.PASSWORD);
        }

        return connection;
    }

    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                connection = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

  
}
