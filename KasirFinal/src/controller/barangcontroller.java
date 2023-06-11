/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import java.util.List;
import DAObarang.barangDAO;
import DAOImplement.barangimplement;
import model.*;
import kasir.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Galang
 */
public class barangcontroller {
    BarangView frame;
    barangimplement implbarang;
    List<barang> br;
    datecontroller dt = new datecontroller();
    
    public barangcontroller(BarangView frame){
        this.frame = frame;
        implbarang = new barangDAO();
        br = implbarang.getAll();
    }
    
    public void isitabel(){
        br = implbarang.getAll();
        modeltabelbarang np = new modeltabelbarang(br);
        frame.getTabelBarang().setModel(np);
    }
    
    public void insert(){
        barang br = new barang();
        try{
            if (frame.getJTnamabarang().getText().trim().isEmpty()){
                throw new InterruptedException("Input Salah");
            }else{
                br.setNama_barang(frame.getJTnamabarang().getText());
                br.setHarga_barang(Integer.parseInt(frame.getJThargabarang().getText()));
                br.setQuantity(Integer.parseInt(frame.getJTquantity().getText()));  
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/mm/dd");
                Date date = null;
                    date = frame.getJTkadaluarsa().getDate();
                if (dt.compareDate(date) == 1) {
                        throw new InterruptedException("Tanggal Kadalursa Harus Lebih dari Hari ini!");
                }    
                br.setKadaluarsa(date);
                implbarang.insert(br);
                frame.hapusField();
            }
            
        }catch(InterruptedException er){
            String errorMessage = er.getMessage();
            JOptionPane.showMessageDialog(frame, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
        }catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(frame, "Input Salah!", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    public void update(){
        barang br = new barang();
        try{
            if (frame.getJTnamabarang().getText().trim().isEmpty()){
                throw new InterruptedException("Input Salah");
            }else{
                br.setNama_barang(frame.getJTnamabarang().getText());
                br.setHarga_barang(Integer.parseInt(frame.getJThargabarang().getText()));
                br.setQuantity(Integer.parseInt(frame.getJTquantity().getText()));

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/mm/dd");
                Date date = null;
                    date = frame.getJTkadaluarsa().getDate();
                if (dt.compareDate(date) == 1) {
                        throw new InterruptedException("Tanggal Kadalursa Harus Lebih dari Hari ini!");
                } 
                br.setKadaluarsa(date);
                br.setId_barang(Integer.parseInt(frame.getJTidbarang().getText()));
                implbarang.update(br);
                frame.hapusField();
            }
        }catch(InterruptedException er){
            String errorMessage = er.getMessage();
            JOptionPane.showMessageDialog(frame, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
        }catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(frame, "Input Salah!", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    public void delete(){
        try{
            int id = Integer.parseInt(frame.getJTidbarang().getText());
            implbarang.delete(id);
        }catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(frame, "Input Salah!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}