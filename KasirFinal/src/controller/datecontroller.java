/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.Date;

/**
 *
 * @author Galang
 */
public class datecontroller {
    public int compareDate(Date date){
        Date dateNow = new Date();
        int comparison = date.compareTo(dateNow);

        if (comparison > 0) {
           return 0; // tanggal kadalursa lebih dari tanggal sekarang
        } else {
           return 1;
        }
    }
    
}
