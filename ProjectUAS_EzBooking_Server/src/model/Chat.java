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
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private List<String> listChat;

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
        this.listChat = new ArrayList<String>();
        getConnection();
    }

    public Chat(String chat, Date date, String sender, String reciever, Customer cus, Restaurant res) {
        this.chat = chat;
        this.date = date;
        this.sender = sender;
        this.reciever = reciever;
        this.customer = cus;
        this.restaurant = res;
        this.listChat = new ArrayList<String>();
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

    public List<String> getListChat() {
        return listChat;
    }

    public void setListChat(List<String> listChat) {
        this.listChat = listChat;
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

    public String readChat(int cusID, int resID, String sender, String reviever) {
        String string = "";
        try {
            String help = "";
            stat = (java.sql.Statement) conn.createStatement();
            this.result = stat.executeQuery("SELECT c.chat, c.date, c.sender, c.reciever, c.status, cus.id, cus.username, cus.password "
                    + "cus.name, cus.address, cus.email, res.id, res.owner, res.name, res.number_of_table, res.preorder, res.username, res.password, res.address, res.phone_number , res.price_reservation "
                    + "FROM CHAT AS c INNER JOIN customers as cus ON c.customer_id = cus.id INNER JOIN restaurant AS res ON c.restaurant_id = res.id "
                    + "WHERE cus.id = '" + cusID + "', res.id = '" + resID + "', c.sender = '" + sender + "', c.reciever = '" + reviever);
            while (result.next()) {
                String chats = result.getString("c.chat");
                Date date = result.getDate("c.date");
                Customer cus = new Customer(result.getString("cus.id"), result.getString("cus.username"), result.getString("cus.password"), result.getString("cus.name"), result.getString("cus.address"), result.getString("cus.email"));
                Restaurant res = new Restaurant(result.getInt("res.id"), result.getString("res.owner"), result.getString("res.name"), result.getInt("res.number_of_table"), result.getBoolean("res.preorder"), result.getString("res.username"), result.getString("res.password"), result.getString("res.address"), result.getString("res.phone_number"), result.getFloat("res.price_reservation"));
                if (sender == "Restaurant") {
                    help = res.getNama();
                } else if (sender == "Customer") {
                    help = cus.getNama();
                }
                string = date + " " + help + " : " + chats + ",";
            }
        } catch (SQLException ex) {
            Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
        }
        return string;
    }

}
