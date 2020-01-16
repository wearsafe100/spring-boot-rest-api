package com.wearsafe.service.impl;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
 
public class jdbcdemo {
    public static void main(String[] args) {
        Connection connection = null;
        Statement stmt = null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/demojdbc", "root", "root");
             
            stmt = connection.createStatement();
            stmt.execute("INSERT INTO EMPLOYEE (ID,FIRST_NAME,LAST_NAME,STAT_CD) "
                                + "VALUES (1,'Lokesh','Gupta',5)");
        } 
        catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
              //  stmt.close();
               // connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}