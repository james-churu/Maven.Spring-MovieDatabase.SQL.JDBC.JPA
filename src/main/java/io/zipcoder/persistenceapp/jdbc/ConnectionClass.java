package io.zipcoder.persistenceapp.jdbc;

import org.h2.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionClass {

    public static final String URL = "jdbc:mysql://localhost:3306/movie_database_lab";
    public static final String USER = "labs";
    public static final String PASS = "labs";

    public Connection connection;

    public void makeConnection() {

        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Connection Successful");

        } catch (SQLException ex) {
            throw new RuntimeException("Error connecting to the database", ex);
        }
    }
    public Connection getConnection(){
        makeConnection();
        return this.connection;
    }
}
