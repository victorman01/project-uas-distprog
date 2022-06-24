/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Date;
import java.util.ArrayList;
import static model.Preorder.conn;
import static model.Preorder.stat;
import static model.Restaurant.conn;

/**
 *
 * @author Rony H.I
 */
public class Reservation {

    int id;
    Date bookingDate;
    int numPeoples;
    int numTables;
    Restaurant restaurant;
    Customer customer;
    String status;
    float totalPrice;

    protected static Connection conn;
    protected static Statement stat;
    protected static ResultSet result;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public int getNumPeoples() {
        return numPeoples;
    }

    public void setNumPeoples(int numPeoples) {
        this.numPeoples = numPeoples;
    }

    public int getNumTables() {
        return numTables;
    }

    public void setNumTables(int numTables) {
        this.numTables = numTables;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Reservation(int id, Date bookingDate, int numPeoples, int numTables, Restaurant restaurant, Customer customer, String status, float totalPrice) {
        this.id = id;
        this.bookingDate = bookingDate;
        this.numPeoples = numPeoples;
        this.numTables = numTables;
        this.restaurant = restaurant;
        this.customer = customer;
        this.status = status;
        this.totalPrice = totalPrice;
        getConnection();
    }

    public Reservation(Date bookingDate, int numPeoples, int numTables, Restaurant restaurant, Customer customer, String status, float totalPrice) {
        this.bookingDate = bookingDate;
        this.numPeoples = numPeoples;
        this.numTables = numTables;
        this.restaurant = restaurant;
        this.customer = customer;
        this.status = status;
        this.totalPrice = totalPrice;
        getConnection();

    }

    public Reservation(Date bookingDate, int numPeoples, int numTables, String status, float totalPrice) {
        this.bookingDate = bookingDate;
        this.numPeoples = numPeoples;
        this.numTables = numTables;
        this.restaurant = restaurant;
        this.customer = customer;
        this.status = status;
        this.totalPrice = totalPrice;
        getConnection();
    }

    public Reservation() {
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

    public void insertData(int restoID, int customerID) {
        try {
            if (!conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) conn.prepareStatement("INSERT INTO reservasis"
                        + "(booking_date, number_of_peoples, number_of_tables, restorants_id, customers_id, status, total_price) "
                        + "VALUES (?,?,?,?,?,?,?)");

                sql.setDate(1, this.bookingDate);
                sql.setInt(2, this.numPeoples);
                sql.setInt(3, this.numTables);
                sql.setInt(4, restoID);
                sql.setInt(5, customerID);
                sql.setString(6, this.status);
                sql.setFloat(7, this.totalPrice);

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
                PreparedStatement sql = (PreparedStatement) conn.prepareStatement("UPDATE reservasis "
                        + "SET booking_date = ?, number_of_peoples = ?, number_of_tables = ?, status = ?, total_price = ?"
                        + "WHERE id = ? ");

                sql.setDate(1, this.bookingDate);
                sql.setInt(2, this.numPeoples);
                sql.setInt(3, this.numTables);
                sql.setString(4, this.status);
                sql.setFloat(5, this.totalPrice);
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
            if (!conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) conn.prepareStatement("DELETE FROM reservasis "
                        + "WHERE id = ?");
                sql.setInt(1, this.id);
                sql.executeUpdate();
                sql.close();
            }
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
    }

    public String viewListDataByResto(Restaurant resto) {
        //ArrayList<Object> collections = new ArrayList<>();
        String listReservasi = "";
        try {
            stat = (java.sql.Statement) conn.createStatement();
            this.result = stat.executeQuery("SELECT reserv.id, reserv.booking_date, "
                    + "reserv.number_of_peoples, reserv.number_of_tables, reserv.status, reserv.total_price, "
                    + "custo.id , custo.username, custo.password, custo.name, custo.address, "
                    + "custo.email FROM customers AS custo INNER JOIN reservasis AS reserv ON reserv.customers_id = custo.id "
                    + "INNER JOIN restaurants AS resto ON reserv.restorants_id = resto.id "
                    + "WHERE resto.id = " + resto.getId());
            while (this.result.next()) {
                int id = this.result.getInt("reserv.id");
                Date bookingDate = this.result.getDate("reserv.booking_date");
                int numOfPeople = this.result.getInt("reserv.number_of_peoples");
                int numOfTable = this.result.getInt("reserv.number_of_tables");
                Customer customer = new Customer(result.getInt("custo.id"),
                        result.getString("custo.username"), result.getString("custo.password"), result.getString("custo.name"), result.getString("custo.address"),
                        result.getString("custo.email"), result.getString("custo.address"));
                String status = this.result.getString("reserv.status");
                float totalPrice = this.result.getFloat("reserv.total_price");
                Reservation reservasi = new Reservation(id, bookingDate, numOfPeople, numOfTable, resto, customer, status, totalPrice);
                listReservasi += String.valueOf(reservasi.getId()) + "," + String.valueOf(reservasi.getBookingDate()) + "," + String.valueOf(reservasi.getNumPeoples()) + "," + String.valueOf(reservasi.getNumTables())
                        + "," + reservasi.restaurant.getNama() + "," + reservasi.customer.getNama() + "," + reservasi.getStatus() + "," + String.valueOf(reservasi.getTotalPrice()) + "/";
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return listReservasi;
    }

    public String viewListDataByCustomer(Customer customer) {
        String listReservasi = "";
        try {
            stat = (java.sql.Statement) conn.createStatement();
            this.result = stat.executeQuery("SELECT reserv.id, reserv.booking_date, "
                    + "reserv.number_of_peoples, reserv.number_of_tables, reserv.status, reserv.total_price, "
                    + "resto.id, resto.owner, resto.name, resto.number_of_tables, "
                    + "resto.preorder, resto.username, resto.password, resto.address, resto.phone_number, "
                    + "resto.price_reservation FROM reservasis AS reserv INNER JOIN "
                    + "restaurants AS resto ON restorants_id = resto.id "
                    + "WHERE customers_id = " + customer.getId());

            while (this.result.next()) {
                int id = this.result.getInt("reserv.id");
                Date bookingDate = this.result.getDate("reserv.booking_date");
                int numOfPeople = this.result.getInt("reserv.number_of_peoples");
                int numOfTable = this.result.getInt("reserv.number_of_tables");

                Restaurant restaurant = new Restaurant(result.getInt("resto.id"),
                        result.getString("resto.owner"), result.getString("resto.name"),
                        result.getInt("resto.number_of_tables"), result.getBoolean("resto.preorder"),
                        result.getString("resto.username"), result.getString("resto.password"),
                        result.getString("resto.address"), result.getString("resto.phone_number"),
                        result.getFloat("resto.price_reservation"));

                String status = this.result.getString("status");
                float totalPrice = this.result.getFloat("total_price");
                Reservation reservasi = new Reservation(id, bookingDate, numOfPeople, numOfTable, restaurant, customer, status, totalPrice);
                listReservasi += String.valueOf(reservasi.getId()) + "," + String.valueOf(reservasi.getBookingDate()) + "," + String.valueOf(reservasi.getNumPeoples()) + "," + String.valueOf(reservasi.getNumTables())
                        + "," + reservasi.restaurant.getNama() + "," + reservasi.customer.getNama() + "," + reservasi.getStatus() + "," + String.valueOf(reservasi.getTotalPrice() + "/");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return listReservasi;
    }

    public int takeLatestId() {
        try {
            if (!conn.isClosed()) {
                stat = (java.sql.Statement) conn.createStatement();
                this.result = stat.executeQuery("SELECT * FROM reservasis ORDER BY id DESC LIMIT 1");
                int id = 0;
                while (this.result.next()) {
                    id = this.result.getInt("id");
                }
                return id;
            }
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
        return 0;
    }
}
