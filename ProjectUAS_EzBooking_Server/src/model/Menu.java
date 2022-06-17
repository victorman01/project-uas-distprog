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
import java.util.ArrayList;
import static model.Restaurant.conn;

public class Menu {

    public Menu(String nama, int harga, String detail, Restaurant restoran) {
        this.nama = nama;
        this.harga = harga;
        this.detail = detail;
        this.restoran = restoran;
        this.conn = getConnection();
    }

    public Menu(String nama, int harga, String detail, int id) {
        this.nama = nama;
        this.harga = harga;
        this.detail = detail;
        //this.restoran = restoran;
        this.conn = getConnection();
    }

    public Menu(int id, String nama, int harga, String detail, Restaurant restoran) {
        this.id = id;
        this.nama = nama;
        this.harga = harga;
        this.detail = detail;
        this.restoran = restoran;
        this.conn = getConnection();
    }

    public Menu() {
        this.nama = "";
        this.harga = 0;
        this.restoran = null;
        this.conn = getConnection();
    }

    private int id;
    private String nama;
    private int harga;

    private String detail;
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

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
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

    public void insertData(int id) {
        try {
            if (!conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) conn.prepareStatement("INSERT INTO menus"
                        + "(name, price, detail , restorants_id) "
                        + "VALUES (?,?,?,?)");
                sql.setString(1, this.nama);
                sql.setInt(2, this.harga);
                sql.setString(3, this.detail);
                sql.setInt(4, this.id);

                sql.executeUpdate();
                sql.close();
            }
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
    }

    public void insertData() {
        try {
            if (!conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) conn.prepareStatement("INSERT INTO menus"
                        + "(name, price, detail , restorants_id) "
                        + "VALUES (?,?,?,?)");
                sql.setString(1, this.nama);
                sql.setInt(2, this.harga);
                sql.setString(3, this.detail);
                sql.setInt(4, this.restoran.getId());

                sql.executeUpdate();
                sql.close();
            }
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
    }

    public String updateData() {
        try {
            if (!conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) conn.prepareStatement("UPDATE menus "
                        + "SET name = ?, price = ?, detail = ? "
                        + "WHERE id = ?");
                sql.setString(1, this.nama);
                sql.setInt(2, this.harga);
                sql.setString(3, this.detail);
                sql.setInt(4, this.id);
                sql.executeUpdate();
                sql.close();
                return "UPDATE_SUCCESS";
            }
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
        return "UPDATE_FAILED";
    }

    public String deleteData() {
        try {
            if (!conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) conn.prepareStatement("DELETE FROM menus "
                        + "WHERE id = ?");
                sql.setInt(1, this.id);
                sql.executeUpdate();
                sql.close();
                return "DELETE_SUCCESS";
            }
        } catch (SQLException e) {
            System.out.println("Error" + e.getMessage());
        }
        return "DELETE_FAILED";
    }

    public String viewListData(Restaurant resto) {
//        ArrayList<Object> collections = new ArrayList<>();
        String listMenu = "";
        try {
            stat = (java.sql.Statement) conn.createStatement();
            this.result = stat.executeQuery("select * from menus where restorants_id=" + resto.getId());
            while (this.result.next()) {

                Menu menu = new Menu(this.result.getInt("id"),
                        this.result.getString("name"),
                        this.result.getInt("price"),
                        this.result.getString("detail"),
                        resto);

                listMenu += String.valueOf(menu.getId()) + "," + menu.getNama() + "," + String.valueOf(menu.getHarga())
                        + "," + menu.getDetail() + "/";
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return listMenu;

    }

}
