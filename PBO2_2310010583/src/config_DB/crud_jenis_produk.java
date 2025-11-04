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
public class crud_jenis_produk {
    
    private String namaDB = "PBO2_2310010583";
    private String url = "jdbc:mysql://localhost:3306/" + namaDB;
    private String username = "root";
    private String password = "";
    private Connection koneksi;
    public String VAR_JENIS_PRODUK = null;
    public String VAR_UKURAN = null;
    public String VAR_HARGA = null;
    public boolean validasi = false;
    
     public crud_jenis_produk(){
        try {
           Driver mysqldriver = new com.mysql.jdbc.Driver();
           DriverManager.registerDriver(mysqldriver);
           koneksi = DriverManager.getConnection(url,username,password);
           System.out.print("Berhasil dikoneksikan");
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, error.getMessage());
                
        }
    }
    
    
    public void SimpanJenisProduk(Integer ID_Produk, String Jenis_Produk, String Ukuran, String Harga ){
        try {
            String sql = "insert into jenis_produk(ID_Produk, Jenis_produk, Ukuran, Harga) value(?, ?, ?, ?)";
                String cekPrimary = "select * from jenis_produk where ID_Produk= ?";
            
            PreparedStatement check = koneksi.prepareStatement(cekPrimary);
            check.setInt(1, ID_Produk);
            ResultSet data = check.executeQuery();
            if (data.next()){
                JOptionPane.showMessageDialog(null, "ID Produk sudah terdaftar");
                this.VAR_JENIS_PRODUK = data.getString("Jenis_Produk");
                this.VAR_UKURAN = data.getString("Ukuran");
                this.VAR_HARGA = data.getString("Harga");
                this.validasi = true;
            } else {
                this.validasi = false;
                this.VAR_JENIS_PRODUK = null;
                this.VAR_UKURAN = null;
                this.VAR_HARGA = null;
                PreparedStatement perintah = koneksi.prepareStatement(sql);
                perintah.setInt(1, ID_Produk);
                perintah.setString(2, Jenis_Produk);
                perintah.setString(3, Ukuran);
                perintah.setString(4, Harga);
                perintah.executeUpdate();
                JOptionPane.showMessageDialog(null, "berhasil disimpan");
            }
            
    
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            
        }
    }
    
    public void UbahJenisProduk(Integer ID_Produk, String Jenis_Produk, String Ukuran, String Harga){
        try {
            String sql = "update jenis_produk set Jenis_Produk =?,Ukuran =?,Harga =? where ID_Produk =?";
                   
            PreparedStatement perintah = koneksi.prepareStatement(sql);
            perintah.setString(1, Jenis_Produk);
            perintah.setString(2, Ukuran);
            perintah.setString(3, Harga);
            perintah.setInt(4, ID_Produk);
            
            perintah.executeUpdate();
             JOptionPane.showMessageDialog(null, "data berhasil diubah");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            
        }
    }
    
    public void HapusJenisProduk(Integer ID_Produk){
        try {
            String sql = "delete from jenis_produk where ID_Produk = ?";
                   
            PreparedStatement perintah = koneksi.prepareStatement(sql);
            perintah.setInt(1, ID_Produk);
            perintah.executeUpdate();
             JOptionPane.showMessageDialog(null, "data berhasil dihapus");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            
        }
    }
    
}
