/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAObarang;
import java.sql.*;
import java.util.*;
import koneksi.connector;
import model.*;
import DAOImplement.*;
import java.util.logging.*;

/**
 *
 * @author Galang
 */
public class cartDAO implements cartimplement{
    Connection connection;
    
    final String select = "SELECT * FROM `cart`;";
    final String insert = "INSERT INTO `cart` (nama, harga, banyak, total) VALUES (?, ?, ?, ?);";
    final String update = "UPDATE `cart` SET nama = ?, harga = ?, banyak = ?, total = ? WHERE id_cart = ?;";
    final String delete = "DELETE FROM `cart` WHERE `cart`.`id_cart` = ?;";
    final String updateStok = "UPDATE `barang` SET quantity = ? WHERE id_barang = ?;";
    
    public cartDAO(){
        connection = connector.connection();
    }

    @Override
    public void insert(cart c) {
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1,c.getNama());
            statement.setInt(2,c.getHarga());
            statement.setInt(3,c.getBanyak());
            statement.setInt(4,c.getTotal());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            while(rs.next()){
                c.setId_cart(rs.getInt(1));
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            try{
                statement.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void update(cart c) {
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(update);
            statement.setString(1,c.getNama());
            statement.setInt(2,c.getHarga());
            statement.setInt(3,c.getBanyak());
            statement.setInt(4,c.getTotal());
            statement.setInt(5, c.getId_cart());
            statement.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            try{
                statement.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void delete(int id) {
       PreparedStatement statement = null;
       try{
        statement = connection.prepareStatement(delete);
        statement.setInt(1,id);
        statement.executeUpdate();
       }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            try{
                statement.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
    }

    @Override
    public List<cart> getAll() {
        List<cart> cr = null;
        try{
            cr = new ArrayList<cart>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            while(rs.next()){
                cart hp = new cart();
                hp.setId_cart(rs.getInt("id_cart"));
                hp.setNama(rs.getString("nama"));
                hp.setHarga(rs.getInt("harga"));
                hp.setBanyak(rs.getInt("banyak"));
                hp.setTotal(rs.getInt("total"));
                cr.add(hp);
            }
        }catch(SQLException ex){
            Logger.getLogger(cartDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return cr;
    }
    
    @Override
    public void updateStok(barang p) {
       PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(updateStok);
            statement.setInt(1, p.getQuantity()); 
            statement.setInt(2, p.getId_barang());
            statement.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            try{
                statement.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
    }
}
