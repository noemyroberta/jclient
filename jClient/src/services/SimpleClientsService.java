/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import beans.SimpleClients;
import database.MyConnection;
import java.sql.Connection;

import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author noemy
 */
public class SimpleClientsService <T extends SimpleClients> implements 
                                                                   dao.Dao<T> {

    Connection conn = MyConnection.getMyConnection();
    ResultSet rs = null;
    Statement st = null;
    
    @Override
    public void insert(T obj) {

        int lines=0;
        
        String sql = "INSERT INTO tb_clientes (cli_nome, "
                                    + "cli_credito, "
                                    + "cli_quantCompras) "
                    + "VALUES ('"+ obj.getName() +"',"
                            + "'"+ obj.getCredit() +"', "
                            + "'"+ obj.getNumbPurchasesLastMonth() +"')";
        try {
            
            st = conn.createStatement();
            lines = st.executeUpdate(sql);
            
        } catch (SQLException ex) {
            
            System.err.println("An error ocurred trying to read the values.");
            Logger.getLogger(FrequentsClientsService.class.getName()).log(Level.SEVERE, null, ex);
        }
            System.out.println("Register successfully.");
    }

    public ArrayList<SimpleClients> getAll() {
        
        String sql = "SELECT cli_nome, cli_credito,cli_quantCompras "
                    + "FROM tb_clientes "
                    + "WHERE cli_quantCompras > '0'";
        
        ArrayList<SimpleClients> scList = new ArrayList();
        
        try {
            
            Connection conn = MyConnection.getMyConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            
            while (rs.next()) {
                
                String name = rs.getString("cli_nome");
                int credit = rs.getInt("cli_credito");
                int numbPurchasesLastMonth = rs.getInt("cli_quantCompras");
                
                scList.add(new SimpleClients 
                                        (numbPurchasesLastMonth, name, credit));
            }
            
            pst.close();
            conn.close();
            
        } catch (SQLException ex) {
            System.out.println("An error ocurred trying to read data.");
            Logger.getLogger(FrequentsClientsService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return scList;
    }
    
    public ArrayList<SimpleClients> listAll() {
        
        String sql = "SELECT cli_nome, cli_credito, cli_quantCompras "
                    + "FROM tb_clientes "
                    + "WHERE cli_quantCompras > '0'";
        
        ArrayList<SimpleClients> scList = new ArrayList();

        try {
            
            Connection conn = MyConnection.getMyConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            
            while (rs.next()) {
                
                String name = rs.getString("cli_nome");
                int credit = rs.getInt("cli_credito");
                int numbPurchasesLastMonth = rs.getInt("cli_quantCompras");
                
                scList.add(new SimpleClients 
                                        (numbPurchasesLastMonth, name, credit));
            }
            
            pst.close();
            conn.close();
            
            for(int i=0; i<scList.size(); i++) {
                 
                System.out.println("Name: "+ scList.get(i).getName());
                System.out.println("Credit: "+ scList.get(i).getCredit());
                System.out.println("Number of purchases made last month: "
                                   + scList.get(i).getNumbPurchasesLastMonth());
                System.out.println("\n");
                
            }
            
        } catch (SQLException ex) {
            System.out.println("An error ocurred trying to read data.");
            Logger.getLogger(FrequentsClientsService.class.getName()).log(Level.SEVERE, null, ex);
        }
           
            
    return scList;   
    }

    public void addingCredit(int credit, String increaseCName) {

        String sql = "UPDATE tb_clientes SET cli_credito = cli_credito + ? WHERE cli_nome = ?";
      
        try {
            
            PreparedStatement pst = conn.prepareStatement(sql);
             {
                
                pst.setInt(1,credit);
                pst.setString(2,increaseCName);

                pst.executeUpdate();    
            }
            
 
        } catch (SQLException ex) {
            
            Logger.getLogger(SimpleClientsService.class.getName()).log(Level.SEVERE, null, ex);
        }        

    }
    
    public void removingCredit(int credit, String decreaseCName) {
        
        String sql = "UPDATE tb_clientes SET cli_credito = cli_credito - ? WHERE cli_nome = ?";
        
        try {

            PreparedStatement pst = conn.prepareStatement(sql);
            
            pst.setInt(1, credit);
            pst.setString(2, decreaseCName);

            pst.executeUpdate();
            
        } catch (SQLException ex) {
            
            Logger.getLogger(SimpleClientsService.class.getName()).log(Level.SEVERE, null, ex);
        }
  
    }

}
    
    
    
    
    

