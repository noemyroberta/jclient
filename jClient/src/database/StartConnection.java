/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;

/**
 * 
 * @author noemy
 */
public class StartConnection {
    
    // This class start a connection with database  
    public static void main(String[] args) {
   
       Connection CONN = MyConnection.getMyConnection();
       
    } 
   
}
