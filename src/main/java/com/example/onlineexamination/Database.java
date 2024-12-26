package com.example.onlineexamination;

//Online database connection
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


//Online database connection to server
public class Database {
    private static final String host = "mysql-d141468-bhorkadeshubham6-878a.h.aivencloud.com";
    private static final String port = "24436";
    private static final String databaseName = "userauthentication";
    private static final String username = "avnadmin";
    private static final String password = "AVNS_t48cSzLDzi3Uj5iLCBK";

    private static final String url = "jdbc:mysql://" + host + ":" + port + "/" + databaseName + "?sslmode=require";

    public static Connection connectDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, username, password);

        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Internet Connection Required !!","Error",JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
}
