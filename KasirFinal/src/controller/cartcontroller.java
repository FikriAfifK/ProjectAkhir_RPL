package controller;

import DAOImplement.*;
import java.util.List;
import DAObarang.*;
import koneksi.connector;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.*;
import kasir.*;
import java.util.Date;
import java.time.LocalDateTime;


/**
 *
 * @author Galang
 */
public class cartcontroller {
    CartView frame;
    cartimplement implcart;
    barangimplement implbarang;
    historyimplement implhistory;
    List<cart> cr;
    Connection connection = connector.connection();
    int harga;
    boolean cek = false;
    
    public cartcontroller(CartView frame){
        this.frame = frame;
        implcart = new cartDAO();
        cr = implcart.getAll();
    }
    
    public void isitabel(){
        cr = implcart.getAll();
        modeltabelcart np = new modeltabelcart(cr);
        frame.getTabelCart().setModel(np);
    }
    
    public void insert(){
        cart cr = new cart();
        datecontroller dt = new datecontroller();
        try{
            if (frame.getJTnama().getText().trim().isEmpty()){
                throw new InterruptedException("Input Salah");
            }
            final String select = "SELECT * FROM `barang`;";
            Statement statement = null;
            try{
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(select);
                cr.setNama(frame.getJTnama().getText());
                while(rs.next()){
                    Date tanggal = rs.getDate("kadaluarsa");
                    if(frame.getJTnama().getText().equals(rs.getString("nama_barang"))){
                        if(dt.compareDate(tanggal) == 0){
                            cr.setHarga(Integer.parseInt(rs.getString("harga_barang")));
                            harga = Integer.parseInt(rs.getString("harga_barang"));
                            cr.setBanyak(Integer.parseInt(frame.getJTbanyak().getText())); 
                            cr.setTotal(harga*Integer.parseInt(frame.getJTbanyak().getText()));
                            implcart.insert(cr);
                            if(cekStok(frame.getJTnama().getText(),Integer.parseInt(frame.getJTbanyak().getText())) == 1){
                                throw new InterruptedException("Stok tidak cukup");
                            }
                            frame.hapusField();
                            cek = true;
                        }
                        else{
                            cek = false;
                        }
                    }
                }
                if(cek == false){
                    throw new InterruptedException("Barang Tidak Ada");   
                }
            }catch(SQLException ex){
                Logger.getLogger(cartDAO.class.getName()).log(Level.SEVERE, null, ex);
            }catch(InterruptedException er){
                String errorMessage = er.getMessage();
                JOptionPane.showMessageDialog(frame, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(frame, "Input Salah!", "Error", JOptionPane.ERROR_MESSAGE);
        }catch(InterruptedException er){
            String errorMessage = er.getMessage();
            JOptionPane.showMessageDialog(frame, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
        }   
    }
     
    public void delete(){
        try{
            int id = Integer.parseInt(frame.getJTidcart().getText());
            implcart.delete(id);
        }catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(frame, "Input Salah!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void update(){
        cart cr = new cart();
        try{
            if (frame.getJTnama().getText().trim().isEmpty()){
                throw new InterruptedException("Input Salah");
            }
            try{
                final String select = "SELECT * FROM `barang`;";
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(select);
                cr.setNama(frame.getJTnama().getText());
                while(rs.next()){
                    if(frame.getJTnama().getText().equals(rs.getString("nama_barang"))){
                        cr.setHarga(Integer.parseInt(rs.getString("harga_barang")));
                        harga = Integer.parseInt(rs.getString("harga_barang"));
                        cr.setNama(frame.getJTnama().getText());
                        cr.setBanyak(Integer.parseInt(frame.getJTbanyak().getText()));
                        cr.setTotal(Integer.parseInt(frame.getJTbanyak().getText())*harga);
                        cr.setId_cart(Integer.parseInt(frame.getJTidcart().getText()));
                        implcart.update(cr);
                        if(cekStok(frame.getJTnama().getText(),Integer.parseInt(frame.getJTbanyak().getText())) == 1){
                            throw new InterruptedException("Stok tidak cukup");
                        }
                        frame.hapusField();
                        cek = true;
                    }
                }
                if(cek == false){
                    throw new InterruptedException("Barang Tidak Ada");   
                }
            }catch(SQLException ex){
                Logger.getLogger(cartDAO.class.getName()).log(Level.SEVERE, null, ex);
            }catch(InterruptedException er){
                String errorMessage = er.getMessage();
                JOptionPane.showMessageDialog(frame, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(frame, "Input Salah!", "Error", JOptionPane.ERROR_MESSAGE);
        }catch(InterruptedException er){
            String errorMessage = er.getMessage();
            JOptionPane.showMessageDialog(frame, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
        } 
    }
    
    public int bayar(){
        final String select = "SELECT * FROM `cart`;";
        Statement statement;
        int total = 0;
        implhistory= new historyDAO();
        try{
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            
            while(rs.next()){
                total = total + rs.getInt("total");
            }
            if(total == 0){
                throw new InterruptedException();
            }
        }catch(SQLException ex){
             // Handle the exception here
            Logger.getLogger(cartDAO.class.getName()).log(Level.SEVERE, null, ex);
        }catch(InterruptedException er){
            JOptionPane.showMessageDialog(frame, "Barang masih kosong!", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return total;
    }
    
    public void hapusCart(){
        final String select = "SELECT * FROM `cart`;";
        Statement statement;
        implhistory= new historyDAO();
        try{
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            while(rs.next()){
                int id = rs.getInt("id_cart");
                implcart.delete(id);
            }
            statement = connection.createStatement();
            final String sql = "ALTER TABLE `cart` AUTO_INCREMENT = 1";
            statement.executeUpdate(sql);
        }catch(SQLException ex){
             // Handle the exception here
            Logger.getLogger(cartDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateHistory(){
        final String select = "SELECT * FROM `cart`;";
        Statement statement;
        implhistory= new historyDAO();
        try{
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            updateStok();
            while(rs.next()){
                history ht = new history();
                LocalDateTime now = LocalDateTime.now();
                ht.setTanggal_waktu(now);
                ht.setNama(rs.getString("nama"));
                ht.setHarga(rs.getInt("harga"));
                ht.setBanyak(rs.getInt("banyak"));
                ht.setSub_total(rs.getInt("total"));
                implhistory.insert(ht);
                
            }
        }catch(SQLException ex){
             // Handle the exception here
            Logger.getLogger(cartDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int cekStok(String nama, int banyak){
        datecontroller dt = new datecontroller();
        implbarang = new barangDAO(); 
        int barang = 0; 
        try {
            final String select = "SELECT * FROM `barang`;";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            
            while (rs.next()) {
                Date tanggal = rs.getDate("kadaluarsa");
                if(dt.compareDate(tanggal) == 0){
                    if (rs.getString("nama_barang").equals(nama)) {
                        barang = barang + Integer.parseInt(rs.getString("quantity"));
                    }
                }
            }
            rs.close();
            st.close();
            
            if(barang < banyak){
                hapusKekuranganStok(nama);
                return 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(cartDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Input Salah!", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return 0;
    }
    
    public void hapusKekuranganStok(String nama){
        implbarang = new barangDAO(); 
        try {
            final String select = "SELECT * FROM `cart`;";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            
            while (rs.next()) {
                if (rs.getString("nama").equals(nama)) {
                    int id = rs.getInt("id_cart");
                    implcart.delete(id);
                }
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(cartDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Input Salah!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void updateStok(){
        datecontroller dt = new datecontroller();
        barang br = new barang();
        implbarang = new barangDAO(); 
        int stok = 0;
        int cart = 0;
        try {
            final String select = "SELECT * FROM `cart`;";
            final String selectbr = "SELECT * FROM `barang`;";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);

            while (rs.next()) {
                int barang = 0; 
                Statement st2 = connection.createStatement();
                ResultSet rsbr = st2.executeQuery(selectbr);
                    while (rsbr.next()) {
                        Date tanggal = rsbr.getDate("kadaluarsa");
                        if(dt.compareDate(tanggal) == 0){
                            if (rsbr.getString("nama_barang").equals(rs.getString("nama"))) {
                                if(barang == 0){
                                   cart =  Integer.parseInt(rs.getString("banyak"));  
                                }
                                barang = Integer.parseInt(rsbr.getString("quantity"));

                                stok =  barang - cart;
                                if(stok <= 0)
                                {
                                    int id = Integer.parseInt(rsbr.getString("id_barang"));
                                    implbarang.delete(id);
                                    cart = stok * -1;
                                }
                                else{
                                    br.setQuantity(stok);
                                    br.setId_barang(Integer.parseInt(rsbr.getString("id_barang")));
                                    stok = 0;
                                    implcart.updateStok(br);
                                    break;
                                }
                            }
                        }
                    }
                rsbr.close();
                st2.close();
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(cartDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Input Salah!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}