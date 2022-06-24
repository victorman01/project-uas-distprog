/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import static model.Reservation.conn;
import static model.Reservation.result;
import static model.Reservation.stat;

/**
 *
 * @author Asus
 */
public class Preorder {

    Reservation reservation;
    Menu menu;
    int amount;

    protected static Connection conn;
    protected static Statement stat;
    protected static ResultSet result;

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Preorder(Reservation reservation, Menu menu, int amount) {
        this.reservation = reservation;
        this.menu = menu;
        this.amount = amount;
        getConnection();
    }

    public Preorder() {
        getConnection();
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

    public String insertData(int reservasiId, int menuId, int amount) {
        try {
            if (!conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) conn.prepareStatement("INSERT INTO preorders"
                        + "(reservasis_id, menus_id, amount) "
                        + "VALUES (?,?,?)");

                sql.setInt(1, reservasiId);
                sql.setInt(2, menuId);
                sql.setInt(3, amount);

                sql.executeUpdate();
                sql.close();
                return "INSERT PREORDER SUCCESS";
            }
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
        return "INSERT PREORDER FAILED!";
    }

    public String updateDataMenu(int reservasiId, int menuId, int amount) {
        try {
            if (!conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) conn.prepareStatement("UPDATE preorders "
                        + "SET amount = amount + ? WHERE  reservasis_id = ? AND menus_id = ?");

                sql.setInt(1, amount);
                sql.setInt(2, reservasiId);
                sql.setInt(3, menuId);

                sql.executeUpdate();
                sql.close();
                return "INSERT PREORDER SUCCESS";
            }
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
        return "INSERT PREORDER FAILED!";
    }

    public void updateData() {
        try {
            if (!conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) conn.prepareStatement("UPDATE preorders "
                        + "amount = ? " + "WHERE reservasis_id = ? and menus_id = ?");

                sql.setInt(1, this.amount);
                sql.setInt(2, this.reservation.getId());
                sql.setInt(3, this.menu.getId());

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
                PreparedStatement sql = (PreparedStatement) conn.prepareStatement("DELETE FROM preorders "
                        + "WHERE reservasis_id = ? and menus_id = ?");
                sql.setInt(1, this.reservation.getId());
                sql.setInt(2, this.menu.getId());
                sql.executeUpdate();
                sql.close();
            }
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
    }
}
