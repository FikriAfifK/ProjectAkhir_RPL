/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOImplement;

import java.util.List;
import model.*;

/**
 *
 * @author HP
 */
public interface cartimplement {
    public void insert(cart c);
    public void update(cart c);
    public void delete(int id);
    public List<cart> getAll();
    public void updateStok(barang p);
}
