package model;

import java.util.List;
import javax.swing.table.*;

/**
 *
 * @author HP
 */
public class modeltabelcart extends AbstractTableModel{
    List<cart> cr;
    public modeltabelcart(List<cart>cr){
        this.cr = cr;
    }
    
    @Override
    public int getRowCount() {
       return cr.size();
    }

    @Override
    public int getColumnCount() {
       return 5;
    }
    
    @Override
    public String getColumnName(int column){
        switch(column){
            case 0:
                return "NOMER";
            case 1:
                return "NAMA";
            case 2:
                return "HARGA";
            case 3:
                return "BANYAK";
            case 4:
                return "SUB TOTAL";
            default:
                return null;
        }
    }

    @Override
    public Object getValueAt(int row, int column) {
      switch(column){
            case 0:
                return cr.get(row).getId_cart();
            case 1:
                return cr.get(row).getNama();
            case 2:
                return cr.get(row).getHarga();
            case 3:
                return cr.get(row).getBanyak();
            case 4:
                return cr.get(row).getTotal();
            default:
                return null;
        }
    }
}
