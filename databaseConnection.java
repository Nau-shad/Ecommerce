package com.example.ecommerce;

import java.sql.*;

public class databaseConnection {

    Connection con = null;
    String SQLURL="jdbc:mysql://localhost:3306/ecommerce? useSSL=false";
    String USERNAME = "root";
    String password = "sqlpassword#";


    databaseConnection() throws SQLException {


        con = DriverManager.getConnection(SQLURL,USERNAME,password);
    if(con!=null){
    System.out.println("our connection is establish with the database");
                 }
    }
// for sql commands execution

    public ResultSet execute(String query) throws SQLException {
        ResultSet result=null;
        try {
            Statement statement = con.createStatement();
            result = statement.executeQuery(query); // ResultSet is sql cmd for store all table or iterate  in a table

            return result;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public int executeUpdate ( String query) {
        int row=0;
        try {
            Statement statement = con.createStatement();
            row = statement.executeUpdate(query);
            return row;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return row;

    }


}
