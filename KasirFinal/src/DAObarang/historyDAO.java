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
import java.time.LocalDateTime;
import java.util.logging.*;

/**
 *
 * @author Galang
 */
public class historyDAO implements historyimplement{
    Connection connection;
    
    final String select = "SELECT * FROM `history`;";
    final String insert = "INSERT INTO `history` (tanggal_waktu, nama, harga, banyak, sub_total) VALUES (?, ?, ?, ?, ?);";
    
    public historyDAO(){
        connection = connector.connection();
    }
    
    @Override
    public List<history> getAll() {
        List<history> ht = null;
        try{
            ht = new ArrayList<history>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            while(rs.next()){
                history hp = new history();
                hp.setId_history(rs.getInt("id_history"));
                Timestamp timestamp = rs.getTimestamp("tanggal_waktu");
                LocalDateTime localDateTime = timestamp.toLocalDateTime();
                hp.setTanggal_waktu(localDateTime);
                hp.setNama(rs.getString("nama"));
                hp.setHarga(rs.getInt("harga"));
                hp.setBanyak(rs.getInt("banyak"));
                hp.setSub_total(rs.getInt("sub_total"));
                ht.add(hp);
            }
        }catch(SQLException ex){
            Logger.getLogger(cartDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ht;
    }

    @Override
    public void insert(history h) {
       PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            LocalDateTime tanggalWaktu = h.getTanggal_waktu();
            Timestamp timestamp = Timestamp.valueOf(tanggalWaktu);
            
            statement.setTimestamp(1, timestamp);
            statement.setString(2,h.getNama());
            statement.setInt(3,h.getHarga());
            statement.setInt(4,h.getBanyak());
            statement.setInt(5,h.getSub_total());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            while(rs.next()){
                h.setId_history(rs.getInt(1));
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
}
