/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Galang
 */
public class modeltabelhistory extends AbstractTableModel{
    List<history> ht;
    public modeltabelhistory(List<history> ht){
        this.ht = ht;
    }
    
    @Override
    public int getRowCount() {
       return ht.size();
    }

    @Override
    public int getColumnCount() {
       return 6;
    }
    
    @Override
    public String getColumnName(int column){
        switch(column){
            case 0:
                return "ID";
            case 1:
                return "TANGGAL/WAKTU";
            case 2:
                return "NAMA";
            case 3:
                return "HARGA";
            case 4:
                return "BANYAK";
            case 5:
                return "SUB TOTAL";
            default:
                return null;
        }
    }

    @Override
    public Object getValueAt(int row, int column) {
      switch(column){
            case 0:
                return ht.get(row).getId_history();
            case 1:
                return ht.get(row).getTanggal_waktu();
            case 2:
                return ht.get(row).getNama();
            case 3:
                return ht.get(row).getHarga();
            case 4:
                return ht.get(row).getBanyak();
            case 5:
                return ht.get(row).getSub_total();
            default:
                return null;
        }
    }
}
