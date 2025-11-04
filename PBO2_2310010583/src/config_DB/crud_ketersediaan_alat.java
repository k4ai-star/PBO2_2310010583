/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config_DB;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Asus
 */
public class crud_ketersediaan_alat {
    
     private String namaDB = "PBO2_2310010583";
    private String url = "jdbc:mysql://localhost:3306/" + namaDB;
    private String username = "root";
    private String password = "";
    private Connection koneksi;
    public Integer VAR_ID_ALAT = null;
    public String VAR_STATUS = null;
    public java.sql.Date VAR_TANGGAL_PEMASUKAN = null;
    public java.sql.Date VAR_TANGGAL_SELESAI = null;
    public boolean validasi = false;
    
     public crud_ketersediaan_alat(){
        try {
           Driver mysqldriver = new com.mysql.jdbc.Driver();
           DriverManager.registerDriver(mysqldriver);
           koneksi = DriverManager.getConnection(url,username,password);
           System.out.print("Berhasil dikoneksikan");
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, error.getMessage());
                
        }
    }
    
    
    public void SimpanKetersediaanAlat(Integer ID_Status, Integer ID_Alat, String Status, java.sql.Date Tanggal_Pemasukan, java.sql.Date Tanggal_Selesai ){
        try {
            String sql = "insert into ketersediaan_alat(ID_Status, ID_Alat, Status, Tanggal_pemasukan, Tanggal_Selesai) value(?, ?, ?, ?, ?)";
                String cekPrimary = "select * from ketersediaan_alat where ID_Status= ?";
            
            PreparedStatement check = koneksi.prepareStatement(cekPrimary);
            check.setInt(1, ID_Status);
            ResultSet data = check.executeQuery();
            if (data.next()){
                JOptionPane.showMessageDialog(null, "ID Status sudah terdaftar");
                this.VAR_ID_ALAT = data.getInt("ID_alat");
                this.VAR_STATUS = data.getString("Status");
                this.VAR_TANGGAL_PEMASUKAN = data.getDate("Tanggal_Pemasukan");
                this.VAR_TANGGAL_SELESAI = data.getDate("Tanggal_Selesai");
                this.validasi = true;
            } else {
                this.validasi = false;
                this.VAR_ID_ALAT = null;
                this.VAR_STATUS = null;
                this.VAR_TANGGAL_PEMASUKAN = null;
                this.VAR_TANGGAL_SELESAI = null;
                PreparedStatement perintah = koneksi.prepareStatement(sql);
                perintah.setInt(1, ID_Status);
                perintah.setInt(2, ID_Alat);
                perintah.setString(3, Status);
                perintah.setDate(4, Tanggal_Pemasukan);
                perintah.setDate(5, Tanggal_Selesai);
                perintah.executeUpdate();
                JOptionPane.showMessageDialog(null, "berhasil disimpan");
            }
            
    
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            
        }
    }
    
    public void UbahKetersediaanAlat(Integer ID_Status,Integer ID_Alat, String Status, java.sql.Date Tanggal_Pemasukan, java.sql.Date Tanggal_Selesai){
        try {
            String sql = "update ketersediaan_alat set ID_Alat =?,Status =?,Tanggal_Pemasukan =?, Tanggal_Selesai =? where ID_Status =?";
                   
            PreparedStatement perintah = koneksi.prepareStatement(sql);
            perintah.setInt(1, ID_Alat);
            perintah.setString(2, Status); 
            perintah.setDate(3, Tanggal_Pemasukan);
            perintah.setDate(4, Tanggal_Selesai);
            perintah.setInt(5, ID_Status);
            
            perintah.executeUpdate();
             JOptionPane.showMessageDialog(null, "data berhasil diubah");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            
        }
    }
    
    public void HapusKetersediaanAlat(Integer ID_Status){
        try {
            String sql = "delete from ketersediaan_alat where ID_Status = ?";
                   
            PreparedStatement perintah = koneksi.prepareStatement(sql);
            perintah.setInt(1, ID_Status);
            perintah.executeUpdate();
             JOptionPane.showMessageDialog(null, "data berhasil dihapus");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            
        }
    }
}
