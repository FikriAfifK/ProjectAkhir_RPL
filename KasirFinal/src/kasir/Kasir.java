/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package kasir;

/**
 *
 * @author Galang
 */
public class Kasir {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        MainView m = new MainView();
        m.setVisible(true);
        m.setLocationRelativeTo(null);
        
        BarangView b = new BarangView();
//        b.setVisible(true);
//        b.setLocationRelativeTo(null);
        
        CartView c = new CartView();
//        v.setVisible(true);
//        v.setLocationRelativeTo(null);

        HistoryView h = new HistoryView();
    }
    
}