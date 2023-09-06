package org.biblio.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class Db {
    private  static  Connection  con ;
    public static Connection connect(){
        try {
             con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/biblio", "root",
                    "");
            System.out.println(" connected !!");
        } catch (Exception e) {
            e.getMessage();
        }
        return  con;
    }
}
