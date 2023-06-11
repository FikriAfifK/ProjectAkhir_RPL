/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;

/**
 *
 * @author Galang
 */
public class history {
    private int id_history;
    private LocalDateTime tanggal_waktu;
    private String nama;
    private int harga;
    private int banyak;
    private int sub_total;

    public int getId_history() {
        return id_history;
    }

    public void setId_history(int id_history) {
        this.id_history = id_history;
    }

    public LocalDateTime getTanggal_waktu() {
        return tanggal_waktu;
    }

    public void setTanggal_waktu(LocalDateTime tanggal_waktu) {
        this.tanggal_waktu = tanggal_waktu;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public int getBanyak() {
        return banyak;
    }

    public void setBanyak(int banyak) {
        this.banyak = banyak;
    }

    public int getSub_total() {
        return sub_total;
    }

    public void setSub_total(int sub_total) {
        this.sub_total = sub_total;
    }
    
    
}
