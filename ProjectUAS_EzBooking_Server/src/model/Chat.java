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
import static model.Reservation.conn;

/**
 *
 * @author skyclyve
 */
public class Chat {

    private int id;
    private String chat;
    private Date date;
    private String sender;
    private String reciever;
    private Boolean status;
    private Customer customer;
    private Restaurant restaurant;

    protected static Connection conn;
    protected static Statement stat;
    protected static ResultSet result;

    public Chat() {
        getConnection();
    }

    public Chat(int id, String chat, Date date, String sender, String reciever, Customer cus, Restaurant res) {
        this.id = id;
        this.chat = chat;
        this.date = date;
        this.sender = sender;
        this.reciever = reciever;
        this.customer = cus;
        this.restaurant = res;
        getConnection();
    }

    public Chat(String chat, Date date, String sender, String reciever, Customer cus, Restaurant res) {
        this.chat = chat;
        this.date = date;
        this.sender = sender;
        this.reciever = reciever;
        this.customer = cus;
        this.restaurant = res;
        getConnection();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getChat() {
        return chat;
    }

    public void setChat(String chat) {
        this.chat = chat;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReciever() {
        return reciever;
    }

    public void setReciever(String reciever) {
        this.reciever = reciever;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
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
    public void insertData(int restoID, int customerID, String sender, String reciever) {
        try {
            if (!conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) conn.prepareStatement("INSERT INTO chats"
                        + "(chat, date, sender, reciever, status, customers_id, restaurants_id) "
                        + "VALUES (?,?,?,?,?,?,?)");

                sql.setString(1, this.chat);
                sql.setDate(2, this.date);
                sql.setString(3, sender);
                sql.setString(4, reciever);
                sql.setBoolean(5, status);
                sql.setInt(6, customerID);
                sql.setInt(7, restoID);

                sql.executeUpdate();
                sql.close();
            }
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
    }
    
//    public void updateData() {
//        try {
//            if (!conn.isClosed()) {
//                PreparedStatement sql = (PreparedStatement) conn.prepareStatement("UPDATE reservasis "
//                        + "SET booking_date = ?, number_of_peoples = ?, number_of_tables = ?, status = ?, total_price = ?"
//                        + "WHERE id = ? ");
//
//                sql.setDate(1, this.bookingDate);
//                sql.setInt(2, this.numPeoples);
//                sql.setInt(3, this.numTables);
//                sql.setString(4, this.status);
//                sql.setFloat(5, this.totalPrice);
//                sql.setInt(6, this.id);
//
//                sql.executeUpdate();
//                sql.close();
//            }
//        } catch (Exception e) {
//            System.out.println("Error" + e.getMessage());
//        }
//    }
}
