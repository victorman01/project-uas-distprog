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

/**
 *
 * @author Asus
 */
public class Administrator {

    int id;
    String username;
    String password;
    String name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Administrator(String username, String password, String nama) {
        this.username = username;
        this.password = password;
        this.name = nama;
        getConnection();
    }

    public Administrator() {
        this.username = "";
        this.password = "";
        this.name = "";
        getConnection();
    }

    public Administrator(int id, String username, String password, String nama) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = nama;
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

    public String insertData() {
        try {
            if (!connect.isClosed()) {
                PreparedStatement sql = (PreparedStatement) connect.prepareStatement("INSERT INTO administrators"
                        + "(username, password, name) "
                        + "VALUES (?,?,?)");
                sql.setString(1, this.username);
                sql.setString(2, this.password);
                sql.setString(3, this.name);
                sql.executeUpdate();
                sql.close();
                return "ADD_ADMIN_SUCCESS";
            }
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
        return "ADD_ADMIN_FAILED";
    }

    public String updateData() {
        try {
            if (!connect.isClosed()) {
                PreparedStatement sql = (PreparedStatement) connect.prepareStatement("UPDATE administrators "
                        + "SET username = ?, password = ?, name = ? "
                        + "WHERE id = ?");
                sql.setString(1, this.username);
                sql.setString(2, this.password);
                sql.setString(3, this.name);
                sql.setInt(4, this.id);
                sql.executeUpdate();
                sql.close();
                return "UPDATE_ADMIN_SUCCESS";
            }
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
        return "UPDATE_ADMIN_FAILED";
    }

    public String deleteData(int idAdmin) {
        try {
            if (!connect.isClosed()) {
                PreparedStatement sql = (PreparedStatement) connect.prepareStatement("DELETE FROM administrators "
                        + "WHERE id = ?");
                sql.setInt(1, idAdmin);
                sql.executeUpdate();
                sql.close();
                return "DELETE_ADMIN_SUCCES";
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return "DELETE_ADMIN_FAILED";
    }

    public boolean CheckLogin(String username, String password) {
        try {
            if (!conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) conn.prepareStatement("select * from administrators where username=? and password=?");
                sql.setString(1, username);
                sql.setString(2, password);
                result = sql.executeQuery();
                if (result.next()) {
                    return true;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(Administrator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public Administrator TakeUsr(String username) {
        Administrator usr = null;
        try {

            if (!conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) conn.prepareStatement("select * from administrators where username=?");
                sql.setString(1, username);
                result = sql.executeQuery();
                if (result.next()) {
                    usr = new Administrator(result.getString("username"), result.getString("password"), result.getString("name"));
                    return usr;
                }
            }

        } catch (Exception e) {
            System.out.println("Error, " + e.getMessage());
        }
        return usr;
    }

    public String viewListAdmin() {
        String listAdmin = "";
        try {
            stat = (java.sql.Statement) conn.createStatement();
            this.result = stat.executeQuery("select * from administrators");
            while (this.result.next()) {

                Administrator admin = new Administrator(this.result.getInt("id"),
                        this.result.getString("username"),
                        this.result.getString("password"),
                        this.result.getString("name"));

                listAdmin += String.valueOf(admin.getId()) + "," + admin.getUsername() + "," + admin.getPassword()
                        + "," + admin.getName() + "/";
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return listAdmin;
    }

    public Administrator TakeUsr(String username, String password) {
        Administrator usr = null;
        try {
            stat = (java.sql.Statement) conn.createStatement();
            this.result = stat.executeQuery("select * from administrators");
            while (this.result.next()) {
                usr = new Administrator(this.result.getInt("id"),
                        this.result.getString("username"),
                        this.result.getString("password"),
                        this.result.getString("name"));
                return usr;
            }
        } catch (Exception e) {
            System.out.println("ERROR, " + e.getMessage());
        }
        return usr;
    }
}
