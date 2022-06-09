/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Muhammad Ikhsan
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static model.Restaurant.conn;
public class Menu {

    public Menu(String nama, int harga, Restaurant restoran) {
        this.nama = nama;
        this.harga = harga;
        this.restoran = restoran;
        this.conn=getConnection();
    }
    public Menu() {
        this.nama = "";
        this.harga = 0;
        this.restoran = null;
        this.conn=getConnection();
    }
    private int id;
    private String nama;
    private int harga;
    private Restaurant restoran;
    protected static Connection conn;
    protected static Statement stat;
    protected static ResultSet result;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Restaurant getRestoran() {
        return restoran;
    }

    public void setRestoran(Restaurant restoran) {
        this.restoran = restoran;
    }
     public Connection getConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/projectdisprog","root","");
        } catch (Exception e) {
            System.out.println("Error di getconnection = "+ e.getMessage());
        }
        return conn;
    }
     public void insertData() {
        try {
            if (!conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) conn.prepareStatement("INSERT INTO menus"
                        + "(nama, harga, restorants_id) "
                        + "VALUES (?,?,?,?)");
                sql.setString(1, this.nama);
                sql.setInt(2, this.harga);
                sql.setInt(3, this.restoran.getId());
                
                
                sql.executeUpdate();
                sql.close();
            }
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
    }
     public void updateData() {
        try {
            if (!conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) conn.prepareStatement("UPDATE menus "
                        + "SET nama = ?, harga = ? "
                        + "WHERE id = ? ");
                sql.setString(1, this.nama);
                sql.setInt(2, this.harga);
                sql.setInt(3, this.id);
                sql.executeUpdate();
                sql.close();
            }
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
    }
     public void deleteData() {
        try {
            if (!conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) conn.prepareStatement("DELETE FROM menus "
                        + "WHERE id = ?");
                sql.setInt(1, this.id);
                sql.executeUpdate();
                sql.close();
            }
        } catch (SQLException e) {
            System.out.println("Error" + e.getMessage());
        }
    }
    
}
