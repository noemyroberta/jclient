/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
*/
package services;

import beans.FrequentsClients;
import database.MyConnection;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 *
 * @author noemy
 */
public class FrequentsClientsService <T extends FrequentsClients> implements
                                                                    dao.Dao<T> {

    Connection conn = MyConnection.getMyConnection();
    ResultSet rs = null;
    Statement st = null;

    @Override
    public void insert(T obj) {
        
        int lines=0;
        
        String sql = "INSERT INTO tb_clientes (cli_nome, "
                                    + "cli_numeroFidelidade, "
                                    + "cli_credito) "
                    + "VALUES ('"+ obj.getName() +"',"
                            + "'"+ obj.getNumber() +"', "
                            + "'"+ obj.getCredit() +"')";
        
        try {
            st = conn.createStatement();
            lines = st.executeUpdate(sql);
        } catch (SQLException ex) {
            System.err.println("An error ocurred trying to read the values.");
            Logger.getLogger(FrequentsClientsService.class.getName()).log(Level.SEVERE, null, ex);
        }
            System.out.println("Register successfully.");
    
    }
    
    public ArrayList<FrequentsClients> getAll() {
        
        String sql = "SELECT cli_nome, cli_credito, cli_numeroFidelidade "
                    + "FROM tb_clientes "
                    + "WHERE cli_numeroFidelidade >= '0'";
        
        ArrayList<FrequentsClients> fcList = new ArrayList();
        
        try {
            
            Connection conn = MyConnection.getMyConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            
            while (rs.next()) {
                
                String name = rs.getString("cli_nome");
                int credit = rs.getInt("cli_credito");
                int number = rs.getInt("cli_numeroFidelidade");
                
                fcList.add(new FrequentsClients (number, name, credit));
            }
            
            pst.close();
            conn.close();
            
        } catch (SQLException ex) {
            System.out.println("An error ocurred trying to read data.");
            Logger.getLogger(FrequentsClientsService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    return fcList;
    }
    
    public ArrayList<FrequentsClients> listAll() {
        
        String sql = "SELECT cli_nome, cli_credito, cli_numeroFidelidade "
                    + "FROM tb_clientes "
                    + "WHERE cli_numeroFidelidade >= '0'";
        
        ArrayList<FrequentsClients> fcList = new ArrayList();

        try {
            Connection conn = MyConnection.getMyConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            
            while (rs.next()) {
                
                String name = rs.getString("cli_nome");
                int credit = rs.getInt("cli_credito");
                int number = rs.getInt("cli_numeroFidelidade");
                
                fcList.add(new FrequentsClients (number, name, credit));
            }
            
            pst.close();
            conn.close();
            
            for(int i=0; i<fcList.size(); i++) {
                
                System.out.println("Name: "+ fcList.get(i).getName());
                System.out.println("Credit: "+ fcList.get(i).getCredit());
                System.out.println("Number: "+ fcList.get(i).getNumber());
                System.out.println("\n");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(FrequentsClientsService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    return fcList;   
    }
    
    public void addingCredit(int credit, String increaseCName) {

        Connection conn = MyConnection.getMyConnection();
        
        String sql = "UPDATE tb_clientes SET cli_credito = cli_credito + ? WHERE cli_nome = ?";
        
        try {

            PreparedStatement pst = conn.prepareStatement(sql);
            
            pst.setInt(1, credit);
            pst.setString(2, increaseCName);

            pst.executeUpdate();
            pst.close();
            
        } catch (SQLException ex) {
            
            Logger.getLogger(SimpleClientsService.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    
    public void removingCredit(int credit, String decreaseCName) {
        
        Connection conn = MyConnection.getMyConnection();
        
        String sql = "UPDATE tb_clientes SET cli_credito = cli_credito - ? WHERE cli_nome = ?";
        
        try {

            PreparedStatement pst = conn.prepareStatement(sql);
            
            pst.setInt(1, credit);
            pst.setString(2, decreaseCName);

            pst.executeUpdate();
            pst.close();
            
        } catch (SQLException ex) {
            
            Logger.getLogger(SimpleClientsService.class.getName()).log(Level.SEVERE, null, ex);
        }
  
    }

}
