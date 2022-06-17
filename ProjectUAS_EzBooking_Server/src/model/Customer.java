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
import static model.Restaurant.conn;
import static model.Restaurant.result;

/**
 *
 * @author Rony H.I
 */
public class Customer {

    int id;
    String username;
    String password;
    String nama;
    String alamat;
    String email;
    Connection connect;

    Statement stat;
    ResultSet result;

    public Customer() {
        this.username = "";
        this.password = "";
        this.nama = "";
        this.alamat = "";
        this.email = "";
        getConnection();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Customer(String username, String password, String nama, String alamat, String email) {
        this.username = username;
        this.password = password;
        this.nama = nama;
        this.alamat = alamat;
        this.email = email;
        getConnection();
    }

    public Customer(int id, String username, String password, String nama, String alamat, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nama = nama;
        this.alamat = alamat;
        this.email = email;
        getConnection();
    }

    public Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            connect = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/projectdisprog", "root", "");
        } catch (Exception e) {
            System.out.println("Error di getconnection = " + e.getMessage());
        }
        return connect;
    }

    public void insertData() {
        try {
            if (!connect.isClosed()) {
                PreparedStatement sql = (PreparedStatement) connect.prepareStatement("INSERT INTO customers"
                        + "(username, password, name, address, email) "
                        + "VALUES (?,?,?,?,?)");
                sql.setString(1, this.username);
                sql.setString(2, this.password);
                sql.setString(3, this.nama);
                sql.setString(4, this.alamat);
                sql.setString(5, this.email);
                sql.executeUpdate();
                sql.close();
            }
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
    }

    public void updateData() {
        try {
            if (!connect.isClosed()) {
                PreparedStatement sql = (PreparedStatement) connect.prepareStatement("UPDATE customers "
                        + "SET username = ?, password = ?, name = ?, address = ?, email = ? "
                        + "WHERE id = ? ");
                sql.setString(1, this.username);
                sql.setString(2, this.password);
                sql.setString(3, this.nama);
                sql.setString(4, this.alamat);
                sql.setString(5, this.email);
                sql.setInt(6, this.id);
                sql.executeUpdate();
                sql.close();
            }
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
    }

    public void deleteData() {
        try {
            if (!connect.isClosed()) {
                PreparedStatement sql = (PreparedStatement) connect.prepareStatement("DELETE FROM customers "
                        + "WHERE id = ?");
                sql.setInt(1, this.id);
                sql.executeUpdate();
                sql.close();
            }
        } catch (SQLException e) {
            System.out.println("Error" + e.getMessage());
        }
    }

    public boolean CheckLogin(String username, String password) {
        try {
            if (!conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) conn.prepareStatement("select * from customers where username=? and password=?");
                sql.setString(1, username);
                sql.setString(2, password);
                result = sql.executeQuery();
                if (result.next()) {
                    return true;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public Customer TakeUsr(String username) {
        Customer usr = null;
        try {

            if (!conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) conn.prepareStatement("select * from customers where username=? and password=?");
                sql.setString(1, username);
                sql.setString(2, password);
                result = sql.executeQuery();
                if (result.next()) {
                    usr = new Customer(result.getString("username"), result.getString("password"), result.getString("name"), result.getString("address"), result.getString("email"));
                    return usr;
                }
            }

        } catch (Exception e) {
            System.out.println("Error, " + e.getMessage());
        }
        return usr;
    }
}
