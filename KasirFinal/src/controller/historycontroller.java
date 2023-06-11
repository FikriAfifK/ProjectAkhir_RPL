/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.List;
import DAObarang.*;
import DAOImplement.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.*;
import kasir.*;
import koneksi.connector;

/**
 *
 * @author Galang
 */
public class historycontroller {
    HistoryView frame;
    historyimplement implhistory;
    List<history> ht;
    
    public historycontroller(HistoryView frame){
        this.frame = frame;
        implhistory = new historyDAO();
        ht = implhistory.getAll();
    }
    
    public void isitabel(){
        totalPenghasilan();
        ht = implhistory.getAll();
        modeltabelhistory np = new modeltabelhistory(ht);
        frame.getTabelHistory().setModel(np);
    }
    
    public void totalPenghasilan(){
    Connection connection = connector.connection();
    final String select = "SELECT * FROM `history`;";
        Statement statement;
        
        int total = 0;
        try{
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            while(rs.next()){
                total = total + rs.getInt("sub_total");
            }
            frame.getJLpenghasilan().setText(String.valueOf(total));
        }catch(SQLException ex){
             // Handle the exception here
            Logger.getLogger(cartDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
