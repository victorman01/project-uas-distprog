/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static model.Menu.conn;
import static model.Menu.stat;

/**
 *
 * @author Muhammad Ikhsan
 */
public class Restaurant {

    private int id;
    private String pemilik;
    private String nama;
    private int jumlahMeja;
    private boolean preOrder;
    private String username;
    private String password;
    private String alamat;
    private String no_telepon;
    private float harga_reservasi;
    protected static Connection conn;
    protected static Statement stat;
    protected static ResultSet result;

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

    public float getHarga_reservasi() {
        return harga_reservasi;
    }

    public void setHarga_reservasi(float harga_reservasi) {
        this.harga_reservasi = harga_reservasi;
    }

    public Restaurant(String pemilik, String nama, int jumlahMeja, boolean preOrder, String username, String password, String alamat, String no_telepon, float harga_reservasi) {
        this.pemilik = pemilik;
        this.nama = nama;
        this.jumlahMeja = jumlahMeja;
        this.preOrder = preOrder;
        this.username = username;
        this.password = password;
        this.alamat = alamat;
        this.no_telepon = no_telepon;
        this.harga_reservasi = harga_reservasi;
        this.conn = getConnection();
    }

    public Restaurant(int id, String pemilik, String nama, int jumlahMeja, boolean preOrder, String username, String password, String alamat, String no_telepon, float harga_reservasi) {
        this.id = id;
        this.pemilik = pemilik;
        this.nama = nama;
        this.jumlahMeja = jumlahMeja;
        this.preOrder = preOrder;
        this.username = username;
        this.password = password;
        this.alamat = alamat;
        this.no_telepon = no_telepon;
        this.harga_reservasi = harga_reservasi;
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
        this.harga_reservasi = 0;
        this.conn = getConnection();
    }

    public Restaurant(int id) {
        this.id = id;
        this.conn = getConnection();
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

    public Restaurant GetRestoByID(int restoId) {
        try {
            if (!conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) conn.prepareStatement("select * from restaurants where id=?");
                sql.setInt(1, restoId);
                result = sql.executeQuery();
                while (result.next()) {
                    Restaurant resto = new Restaurant(this.result.getInt("id"),
                            this.result.getString("owner"), this.result.getString("name"),
                            this.result.getInt("number_of_tables"), this.result.getBoolean("preorder"),
                            this.result.getString("username"), this.result.getString("password"), this.result.getString("address"),
                            this.result.getString("phone_number"), this.result.getFloat("price_reservation"));
                    return resto;
                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(Restaurant.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    public Restaurant CheckLogin(String username, String password) {
        try {
            if (!conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) conn.prepareStatement("select * from restaurants where username=? and password=?");
                sql.setString(1, username);
                sql.setString(2, password);
                result = sql.executeQuery();
                while (result.next()) {
                    Restaurant resto = new Restaurant(this.result.getInt("id"),
                            this.result.getString("owner"), this.result.getString("name"),
                            this.result.getInt("number_of_tables"), this.result.getBoolean("preorder"),
                            this.result.getString("username"), this.result.getString("password"), this.result.getString("address"),
                            this.result.getString("phone_number"), this.result.getFloat("price_reservation"));
                    return resto;
                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(Restaurant.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    public void insertData() {
        try {
            if (!conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) conn.prepareStatement("INSERT INTO restaurants"
                        + "(owner, name, number_of_tables, preorder, username, password, address, phone_number, price_reservation) "
                        + "VALUES (?,?,?,?,?,?,?,?,?)");
                sql.setString(1, this.pemilik);
                sql.setString(2, this.nama);
                sql.setInt(3, this.jumlahMeja);
                sql.setBoolean(4, this.preOrder);
                sql.setString(5, this.username);
                sql.setString(6, this.password);
                sql.setString(7, this.alamat);
                sql.setString(8, this.no_telepon);
                sql.setFloat(9, this.harga_reservasi);
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

    public String updateProfile() {
        try {
            if (!conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) conn.prepareStatement("UPDATE restaurants "
                        + "SET owner = ?, name = ?, number_of_tables = ?, preorder = ?, username = ?, password = ?, address = ?, "
                        + "phone_number = ?, price_reservation = ? "
                        + "WHERE id = ?");
                sql.setString(1, this.pemilik);
                sql.setString(2, this.nama);
                sql.setInt(3, this.jumlahMeja);
                sql.setBoolean(4, this.preOrder);
                sql.setString(5, this.username);
                sql.setString(6, this.password);
                sql.setString(7, this.alamat);
                sql.setString(8, this.no_telepon);
                sql.setFloat(9, this.harga_reservasi);
                sql.setInt(10, this.id);
                sql.executeUpdate();
                sql.close();
                return "UPDATE_PROFILE_SUCCESS";
            }
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
        return "UPDATE_PROFILE_FAILED";
    }

    public String deleteData() {
        try {
            if (!conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) conn.prepareStatement("DELETE FROM restaurants "
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

    public Restaurant DataRestoran(int id) {
        Restaurant resto = new Restaurant();
        try {
            stat = (java.sql.Statement) conn.createStatement();
            this.result = stat.executeQuery("select *from restaurants where id=" + id);
            while (this.result.next()) {
                resto = new Restaurant(this.result.getInt("id"),
                        this.result.getString("owner"), this.result.getString("name"),
                        this.result.getInt("number_of_tables"), this.result.getBoolean("preorder"),
                        this.result.getString("username"), this.result.getString("password"),
                        this.result.getString("address"), this.result.getString("phone_number"),
                        this.result.getFloat("price_reservation"));

                return resto;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return resto;
    }

    public String viewListData() {
        String listData = "";
        try {
            stat = (java.sql.Statement) conn.createStatement();
            this.result = stat.executeQuery("select * from restaurants");
            while (this.result.next()) {

                Restaurant resto = new Restaurant(this.result.getInt("id"),
                        this.result.getString("owner"), this.result.getString("name"),
                        this.result.getInt("number_of_tables"), this.result.getBoolean("preorder"),
                        this.result.getString("username"), this.result.getString("password"),
                        this.result.getString("address"), this.result.getString("phone_number"),
                        this.result.getFloat("price_reservation"));

                listData += resto.id + "/" + resto.nama + ",";
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return listData;

    }

    public String viewListDataAdmin() {
        String listData = "";
        try {
            stat = (java.sql.Statement) conn.createStatement();
            this.result = stat.executeQuery("select * from restaurants");
            while (this.result.next()) {

                Restaurant resto = new Restaurant(this.result.getInt("id"),
                        this.result.getString("owner"), this.result.getString("name"),
                        this.result.getInt("number_of_tables"), this.result.getBoolean("preorder"),
                        this.result.getString("username"), this.result.getString("password"),
                        this.result.getString("address"), this.result.getString("phone_number"),
                        this.result.getFloat("price_reservation"));

                listData += resto.id + "," + resto.pemilik + "," + resto.nama + "," + resto.jumlahMeja + "," + resto.preOrder
                        + "," + resto.alamat + "," + resto.no_telepon + "," + resto.username + "," + resto.harga_reservasi + "/";
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return listData;
    }

    public Restaurant TakeUsr(String username, String password) {
        Restaurant usr = null;
        try {
            if (!conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) conn.prepareStatement("select * from restaurants where username=? and password=?");
                sql.setString(1, username);
                sql.setString(2, password);
                result = sql.executeQuery();
                if (result.next()) {
                    usr = new Restaurant(this.result.getInt("id"),
                            this.result.getString("owner"), this.result.getString("name"),
                            this.result.getInt("number_of_tables"), this.result.getBoolean("preorder"),
                            this.result.getString("username"), this.result.getString("password"),
                            this.result.getString("address"), this.result.getString("phone_number"),
                            this.result.getFloat("price_reservation"));
                    return usr;
                }
            }

        } catch (Exception e) {
            System.out.println("Error, " + e.getMessage());
        }
        return usr;
    }

    public String countData() {
        String count = "";
        try {
            stat = (java.sql.Statement) conn.createStatement();
            this.result = stat.executeQuery("SELECT COUNT(id) as count FROM restaurants");
            while (this.result.next()) {
                count += this.result.getInt("count");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return count;
    }
}
