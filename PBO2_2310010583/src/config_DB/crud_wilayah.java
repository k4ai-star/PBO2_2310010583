/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config_DB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Driver;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JOptionPane;


/**
 *
 * @author Asus
 */
public class crud_wilayah {
    
    private String namaDB = "PBO2_2310010583";
    private String url = "jdbc:mysql://localhost:3306/" + namaDB;
    private String username = "root";
    private String password = "";
    private Connection koneksi;
    public String VAR_NAMA_WILAYAH = null;
    public String VAR_WILAYAH_PENEMPATAN = null;
    public java.sql.Date VAR_TANGGAL_PEMBENTUKAN = null;
    public java.sql.Date VAR_TANGGAL_REHABILITASI = null;
    public boolean validasi = false;
    
     public crud_wilayah(){
        try {
           Driver mysqldriver = new com.mysql.jdbc.Driver();
           DriverManager.registerDriver(mysqldriver);
           koneksi = DriverManager.getConnection(url,username,password);
           System.out.print("Berhasil dikoneksikan");
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, error.getMessage());
                
        }
    }
    
    
    public void Simpanwilayah(Integer ID_Wilayah, String Nama_Wilayah, String Wilayah_Penempatan, java.sql.Date Tanggal_Pembentukan, java.sql.Date Tanggal_Rehabilitasi ){
        try {
            String sql = "insert into wilayah(ID_Wilayah, Nama_wilayah, Wilayah_penempatan, Tanggal_pembentukan, Tanggal_Rehabilitasi) value(?, ?, ?, ?, ?)";
                String cekPrimary = "select * from wilayah where ID_Wilayah= ?";
            
            PreparedStatement check = koneksi.prepareStatement(cekPrimary);
            check.setInt(1, ID_Wilayah);
            ResultSet data = check.executeQuery();
            if (data.next()){
                JOptionPane.showMessageDialog(null, "ID wilayah sudah terdaftar");
                this.VAR_NAMA_WILAYAH = data.getString("Nama_Wilayah");
                this.VAR_WILAYAH_PENEMPATAN = data.getString("Wilayah_Penempatan");
                this.VAR_TANGGAL_PEMBENTUKAN = data.getDate("Tanggal_Pembentukan");
                this.VAR_TANGGAL_REHABILITASI = data.getDate("Tanggal_Rehabilitasi");
                this.validasi = true;
            } else {
                this.validasi = false;
                this.VAR_NAMA_WILAYAH = null;
                this.VAR_WILAYAH_PENEMPATAN = null;
                this.VAR_TANGGAL_PEMBENTUKAN = null;
                this.VAR_TANGGAL_REHABILITASI = null;
                PreparedStatement perintah = koneksi.prepareStatement(sql);
                perintah.setInt(1, ID_Wilayah);
                perintah.setString(2, Nama_Wilayah);
                perintah.setString(3, Wilayah_Penempatan);
                perintah.setDate(4, Tanggal_Pembentukan);
                perintah.setDate(5, Tanggal_Rehabilitasi);
                perintah.executeUpdate();
                JOptionPane.showMessageDialog(null, "berhasil disimpan");
            }
            
    
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            
        }
    }
    
    public void Ubahwilayah(Integer ID_Wilayah,String Nama_Wilayah, String Wilayah_Penempatan, java.sql.Date Tanggal_Pembentukan, java.sql.Date Tanggal_Rehabilitasi){
        try {
            String sql = "update wilayah set Nama_Wilayah =?,Wilayah_Penempatan =?,Tanggal_Pembentukan =?, Tanggal_Rehabilitasi =? where ID_Wilayah =?";
                   
            PreparedStatement perintah = koneksi.prepareStatement(sql);
            perintah.setString(1, Nama_Wilayah);
            perintah.setString(2, Wilayah_Penempatan);
            perintah.setDate(3, Tanggal_Pembentukan);
            perintah.setDate(4, Tanggal_Rehabilitasi);
            perintah.setInt(5, ID_Wilayah);
            
            perintah.executeUpdate();
             JOptionPane.showMessageDialog(null, "data berhasil diubah");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            
        }
    }
    
    public void Hapuswilayah(Integer ID_Wilayah){
        try {
            String sql = "delete from wilayah where ID_Wilayah = ?";
                   
            PreparedStatement perintah = koneksi.prepareStatement(sql);
            perintah.setInt(1, ID_Wilayah);
            perintah.executeUpdate();
             JOptionPane.showMessageDialog(null, "data berhasil dihapus");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            
        }
    }

    public void ubahwilayah(Integer valueOf, String text, String text0, java.sql.Date valueOf0, java.sql.Date valueOf1) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
