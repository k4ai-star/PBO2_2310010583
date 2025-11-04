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
public class crud_produk {
    
    private String namaDB = "PBO2_2310010583";
    private String url = "jdbc:mysql://localhost:3306/" + namaDB;
    private String username = "root";
    private String password = "";
    private Connection koneksi;
    public Integer VAR_ID_WILAYAH = null;
    public java.sql.Date VAR_TANGGAL_PRODUKSI = null;
    public Integer VAR_JUMLAH_PRODUKSI = null;
    public String VAR_NAMA_PRODUK = null;
    public boolean validasi = false;
    
     public crud_produk(){
        try {
           Driver mysqldriver = new com.mysql.jdbc.Driver();
           DriverManager.registerDriver(mysqldriver);
           koneksi = DriverManager.getConnection(url,username,password);
           System.out.print("Berhasil dikoneksikan");
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, error.getMessage());
                
        }
    }
    
    
    public void SimpanProduk(Integer ID_Produk, java.sql.Date Tanggal_Produksi, Integer ID_Wilayah, Integer Jumlah_Produksi, String Nama_Produk ){
        try {
            String sql = "insert into produk(ID_Produk, Tanggal_Produksi, ID_Wilayah, Jumlah_Produksi, Nama_Produk) value(?, ?, ?, ?, ?)";
                String cekPrimary = "select * from produk where ID_produk= ?";
            
            PreparedStatement check = koneksi.prepareStatement(cekPrimary);
            check.setInt(1, ID_Produk);
            ResultSet data = check.executeQuery();
            if (data.next()){
                JOptionPane.showMessageDialog(null, "ID produk sudah terdaftar");
                this.VAR_TANGGAL_PRODUKSI = data.getDate("Tanggal_Produksi");
                this.VAR_ID_WILAYAH = data.getInt("ID_Wilayah");
                this.VAR_JUMLAH_PRODUKSI = data.getInt("Jumlah_produksi");
                this.VAR_NAMA_PRODUK = data.getString("Nama_Produk");
                this.validasi = true;
            } else {
                this.validasi = false;
                this.VAR_TANGGAL_PRODUKSI = null;
                this.VAR_ID_WILAYAH = null;
                this.VAR_JUMLAH_PRODUKSI = null;
                this.VAR_NAMA_PRODUK = null;
                PreparedStatement perintah = koneksi.prepareStatement(sql);
                perintah.setInt(1, ID_Produk);
                perintah.setDate(2, Tanggal_Produksi);
                perintah.setInt(3, ID_Wilayah);
                perintah.setInt(4, Jumlah_Produksi);
                perintah.setString(5,Nama_Produk);
                perintah.executeUpdate();
                JOptionPane.showMessageDialog(null, "berhasil disimpan");
            }
            
    
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            
        }
    }
    
    public void UbahProduk(Integer ID_Produk, java.sql.Date Tanggal_Produksi, Integer ID_Wilayah, Integer Jumlah_Produksi, String Nama_Produk){
        try {
            String sql = "update produk set Tanggal_Produksi =?,ID_Wilayah =?,Jumlah_Produksi =?, Nama_Produk =? where ID_Produk =?";
                   
            PreparedStatement perintah = koneksi.prepareStatement(sql);
            perintah.setDate(1, Tanggal_Produksi);
            perintah.setInt(2, ID_Wilayah);
            perintah.setInt(3, Jumlah_Produksi);
            perintah.setString(4, Nama_Produk);
            perintah.setInt(5, ID_Produk);
            
            perintah.executeUpdate();
             JOptionPane.showMessageDialog(null, "data berhasil diubah");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            
        }
    }
    
    public void HapusProduk(Integer ID_Produk){
        try {
            String sql = "delete from Produk where ID_Produk = ?";
                   
            PreparedStatement perintah = koneksi.prepareStatement(sql);
            perintah.setInt(1, ID_Produk);
            perintah.executeUpdate();
             JOptionPane.showMessageDialog(null, "data berhasil dihapus");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            
        }
    }
}
