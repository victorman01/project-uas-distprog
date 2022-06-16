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
import static model.Restaurant.conn;

/**
 *
 * @author Rony H.I
 */
public class Reservasi {
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

    public Reservasi(int id, Date bookingDate, int numPeoples, int numTables, Restaurant restaurant, Customer customer, String status, float totalPrice) {
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
    
    public Reservasi(Date bookingDate, int numPeoples, int numTables, Restaurant restaurant, Customer customer, String status, float totalPrice) {
        this.bookingDate = bookingDate;
        this.numPeoples = numPeoples;
        this.numTables = numTables;
        this.restaurant = restaurant;
        this.customer = customer;
        this.status = status;
        this.totalPrice = totalPrice;
        getConnection();

    }
        
    public Reservasi() {
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
     
    public void insertData() {
        try {
            if (!conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) conn.prepareStatement("INSERT INTO reservasis"
                        + "(booking_date, number_of_peoples, number_of_tables, restorants, customres, status, total_price) "
                        + "VALUES (?,?,?,?,?,?,?)");

                sql.setDate(1, this.bookingDate);
                sql.setInt(2, this.numPeoples);
                sql.setInt(3, this.numTables);
                sql.setInt(4, this.restaurant.getId());
                sql.setInt(5, this.customer.getId());
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
     public ArrayList<Object> viewListData(Restaurant resto){
        ArrayList<Object> collections = new ArrayList<>();
        try{
            stat=(java.sql.Statement)conn.createStatement();
            this.result=stat.executeQuery("SELECT * FROM reservasis WHERE restaurants_id= "+ resto.getId());
            while (this.result.next())
            {
                
                Reservasi reservasi = new Reservasi();
                        
//                        this.result.getInt("id"),
//                        this.result.getDate("booking_date"),
//                        this.result.getInt("number_of_peoples"),
//                        this.result.getInt("number_of_tables"),
//                        this.result.getObject("restorants_id", restaurant),
//                        this.result.getInt("customers_id"),
//                        this.result.getInt("harga"),
//                        resto);
                        
                       
                collections.add(reservasi);
            }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return collections;

    }
 
    
}
