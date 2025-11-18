package com.promineotech.projects;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    private static final String HOST = "localhost";
    private static final String SCHEMA = "projects";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    private static final String URL = 
        "jdbc:mysql://" + HOST + ":3306/" + SCHEMA + "?useSSL=false&allowPublicKeyRetrieval=true";

    public static Connection getConnection() {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            return conn;
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }
}



