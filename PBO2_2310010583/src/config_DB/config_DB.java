/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config_DB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Driver;
/**
 *
 * @author Asus
 */
public class config_DB {
    private String namaDB = "PBO2_2310010583";
    private String url = "jdbc:mysql://localhost:3306/" + namaDB;
    private String username = "root";
    private String password = "";
    private Connection koneksi;
}
