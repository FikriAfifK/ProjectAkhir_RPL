/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAOImplement;
import model.*;
import java.util.List;

/**
 *
 * @author Galang
 */
public interface barangimplement {
    public void insert(barang b);
    public void update(barang b);
    public void delete(int id);
    public List<barang> getAll();
}
