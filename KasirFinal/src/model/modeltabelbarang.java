/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.util.List;
import javax.swing.table.*;

/**
 *
 * @author Galang
 */
public class modeltabelbarang extends AbstractTableModel{
    List<barang> br;
    public modeltabelbarang(List<barang>br){
        this.br = br;
    }
    
    @Override
    public int getRowCount() {
       return br.size();
    }

    @Override
    public int getColumnCount() {
       return 5;
    }
    
    @Override
    public String getColumnName(int column){
        switch(column){
            case 0:
                return "ID_BARANG";
            case 1:
                return "NAMA";
            case 2:
                return "HARGA";
            case 3:
                return "QUANTITY";
            case 4:
                return "KADALUARSA";
            default:
                return null;
        }
    }

    @Override
    public Object getValueAt(int row, int column) {
      switch(column){
            case 0:
                return br.get(row).getId_barang();
            case 1:
                return br.get(row).getNama_barang();
            case 2:
                return br.get(row).getHarga_barang();
            case 3:
                return br.get(row).getQuantity();
            case 4:
                return br.get(row).getKadaluarsa();
            default:
                return null;
        }
    }
}
