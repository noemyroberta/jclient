/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Building a connection with database "bd_jclientes"
 * 
 * @author noemy
 */

public class MyConnection {
    
    public static Connection CONN = null;
    
    public static Connection getMyConnection() {
        
        String url = "jdbc:mysql://localhost:3306/bd_jclientes";
        String username = "root";
        String password = "";
        
        try {
            CONN = DriverManager.getConnection(url, username, password);
        } catch (SQLException ex) {
            Logger.getLogger(MyConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
   
    return CONN;
    }
    
}
