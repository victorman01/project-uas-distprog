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

/**
 *
 * @author Asus
 */
public class Administrator {

    int id;
    String username;
    String password;
    String nama;
    Connection connect;

    Statement stat;
    ResultSet result;

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

    public Administrator(String username, String password, String nama) {
        this.username = username;
        this.password = password;
        this.nama = nama;
        getConnection();
    }

    public Administrator(int id, String username, String password, String nama) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nama = nama;
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
                PreparedStatement sql = (PreparedStatement) connect.prepareStatement("INSERT INTO administrators"
                        + "(username, password, nama) "
                        + "VALUES (?,?,?)");
                sql.setString(1, this.username);
                sql.setString(2, this.password);
                sql.setString(3, this.nama);
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
                PreparedStatement sql = (PreparedStatement) connect.prepareStatement("UPDATE administrators "
                        + "SET username = ?, password = ?, nama = ? "
                        + "WHERE id = ? ");
                sql.setString(1, this.username);
                sql.setString(2, this.password);
                sql.setString(3, this.nama);
                sql.setInt(4, this.id);
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
                PreparedStatement sql = (PreparedStatement) connect.prepareStatement("DELETE FROM administrators "
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
