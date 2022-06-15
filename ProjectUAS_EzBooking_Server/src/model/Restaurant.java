/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Muhammad Ikhsan
 */
public class Restaurant {

    public Restaurant(String pemilik, String nama, int jumlahMeja, boolean preOrder, String username, String password, String alamat, String no_telepon) {
        this.pemilik = pemilik;
        this.nama = nama;
        this.jumlahMeja = jumlahMeja;
        this.preOrder = preOrder;
        this.username = username;
        this.password = password;
        this.alamat = alamat;
        this.no_telepon = no_telepon;
        this.conn = getConnection();
    }
    public Restaurant() {
        this.pemilik = null;
        this.nama = "";
        this.jumlahMeja = 0;
        this.preOrder = false;
        this.username = "";
        this.password = "";
        this.alamat = "";
        this.no_telepon = "";
        this.conn = getConnection();
    }

    public String getPemilik() {
        return pemilik;
    }

    public void setPemilik(String pemilik) {
        this.pemilik = pemilik;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getJumlahMeja() {
        return jumlahMeja;
    }

    public void setJumlahMeja(int jumlahMeja) {
        this.jumlahMeja = jumlahMeja;
    }

    public boolean isPreOrder() {
        return preOrder;
    }

    public void setPreOrder(boolean preOrder) {
        this.preOrder = preOrder;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    private int id;
    private String pemilik;
    private String nama;
    private int jumlahMeja;
    private boolean preOrder;
    private String username;
    private String password;
    private String alamat;
    private String no_telepon;
    protected static Connection conn;
    protected static Statement stat;
    protected static ResultSet result;

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNo_telepon() {
        return no_telepon;
    }

    public void setNo_telepon(String no_telepon) {
        this.no_telepon = no_telepon;
    }

    public Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/projectdisprog", "root", "");
        } catch (Exception e) {
            System.out.println("Error di getconnection = " + e.getMessage());
        }
        return conn;
    }

    public boolean CheckLogin(String username, String password) {
        try {
            if (!conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) conn.prepareStatement("select * from restorants where username=? and password=?");
                sql.setString(1, username);
                sql.setString(2, password);
                result = sql.executeQuery();
                if (result.next()) {
                    return true;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(Restaurant.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public void insertData() {
        try {
            if (!conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) conn.prepareStatement("INSERT INTO restaurants"
                        + "(owner, name, number_of_tables, preorder, username, password, address, phone_number) "
                        + "VALUES (?,?,?,?,?,?,?,?)");
                sql.setString(1, this.pemilik);
                sql.setString(2, this.nama);
                sql.setInt(3, this.jumlahMeja);
                sql.setBoolean(4, this.preOrder);
                sql.setString(5, this.username);
                sql.setString(6, this.password);
                sql.setString(7, this.alamat);
                sql.setString(8, this.no_telepon);
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
                PreparedStatement sql = (PreparedStatement) conn.prepareStatement("UPDATE restaurants "
                        + "SET username = ?, password = ? "
                        + "WHERE id = ? ");
                sql.setString(1, this.username);
                sql.setString(2, this.password);
                sql.setInt(3, this.id);
                sql.executeUpdate();
                sql.close();
            }
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
    }

    public void updateMeja(int jumlah, int id) {
        try {
            if (!conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) conn.prepareStatement("UPDATE restaurants "
                        + "SET number_of_tables = ?"
                        + "WHERE id = ? ");
                sql.setInt(1, jumlah);
                sql.setInt(2, id);
                sql.executeUpdate();
                sql.close();
            }
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
    }

    public void updateJumlahMeja(int jumlah, int id) {
        try {
            if (!conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) conn.prepareStatement("UPDATE restaurants "
                        + "SET number_of_tables -= ?"
                        + "WHERE id = ? ");
                sql.setInt(1, jumlah);
                sql.setInt(2, id);
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
                PreparedStatement sql = (PreparedStatement) conn.prepareStatement("DELETE FROM restaurants "
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
