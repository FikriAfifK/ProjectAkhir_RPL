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
public class barangDAO implements barangimplement{
    Connection connection;
    
    final String select = "SELECT * FROM `barang`;";
    final String insert = "INSERT INTO `barang` (nama_barang, harga_barang, quantity, kadaluarsa) VALUES (?, ?, ?, ?);";
    final String update = "UPDATE `barang` SET nama_barang = ?, harga_barang = ?, quantity = ?, kadaluarsa = ? WHERE id_barang = ?;";
    final String delete = "DELETE FROM `barang` WHERE `barang`.`id_barang` = ?;";
    
    public barangDAO(){
        connection = connector.connection();
    }

    @Override
    public void insert(barang b) {
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1,b.getNama_barang());
            statement.setInt(2,b.getHarga_barang());
            statement.setInt(3,b.getQuantity());
            statement.setDate(4, new java.sql.Date(b.getKadaluarsa().getTime()));
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            while(rs.next()){
                b.setId_barang(rs.getInt(1));
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
    public void update(barang b) {
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(update);
            statement.setString(1,b.getNama_barang());
            statement.setInt(2,b.getHarga_barang());
            statement.setInt(3,b.getQuantity());
            statement.setDate(4, new java.sql.Date(b.getKadaluarsa().getTime()));
            statement.setInt(5, b.getId_barang());
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
    public List<barang> getAll() {
        List<barang> br = null;
        try{
            br = new ArrayList<barang>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            while(rs.next()){
                barang hp = new barang();
                hp.setId_barang(rs.getInt("id_barang"));
                hp.setNama_barang(rs.getString("nama_barang"));
                hp.setHarga_barang(rs.getInt("harga_barang"));
                hp.setQuantity(rs.getInt("quantity"));
                hp.setKadaluarsa(rs.getDate("kadaluarsa"));
                br.add(hp);
            }
        }catch(SQLException ex){
            Logger.getLogger(barangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return br;
    }

    
}
