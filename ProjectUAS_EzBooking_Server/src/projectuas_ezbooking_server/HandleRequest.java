/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectuas_ezbooking_server;

/**
 *
 * @author Muhammad Ikhsan
 */
import java.beans.Customizer;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Administrator;
import model.Customer;
import model.Menu;
import model.Preorder;
import model.Restaurant;

public class HandleRequest extends Thread {
    
    BufferedReader in;
    DataOutputStream out;
    MainServer parent;
    Socket s;
    String pesan;
    public static Restaurant restoran;
    public static Restaurant rest;
    Customer customer;
    Administrator administrator;
    
    public HandleRequest(MainServer parent, Socket s) {
        this.parent = parent;
        this.s = s;
        this.restoran = new Restaurant();
        this.customer = new Customer();
        this.administrator = new Administrator();
        
        try {
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            out = new DataOutputStream(s.getOutputStream());

//            inObj = new ObjectInputStream(s.getInputStream());
//            outObj = new ObjectOutputStream(s.getOutputStream());
        } catch (Exception e) {
            Logger.getLogger(HandleRequest.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public void SendMessage(String s) {
        try {
            out.writeBytes(s + "\n");
        } catch (IOException ex) {
            Logger.getLogger(HandleRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void action(String command, String value) {
//        try {
        String[] values = value.split(",");
        int idresto;
        switch (command) {
            
            case "REGISTER_RESTO":
                
                Restaurant resto = new Restaurant(values[0], values[1], Integer.valueOf(values[2]), Boolean.valueOf(values[3]), values[4], values[5], values[6], values[7], Float.parseFloat(values[8]));
                resto.insertData();
                
                this.SendMessage("BERHASIL_REGISTER");
                break;
            case "LOGIN_RESTO":
                System.out.println(values[0]);
                System.out.println(values[1]);
                restoran = restoran.CheckLogin(values[0], values[1]);
                rest = restoran;
                
                if (!(restoran == null)) {
                    this.SendMessage("BERHASIL_LOGIN_RESTAURANT" + "," + restoran.getId());
                } else {
                    this.SendMessage("GAGAL_LOGIN_RESTAURANT");
                }
                
                break;
            case "REGISTER_CUSTOMER":
                
                Customer cust = new Customer(values[0], values[1], values[2], values[3], values[4]);
                cust.insertData();
                
                this.SendMessage("BERHASIL_REGISTER");
                break;
            case "LOGIN_CUSTOMER":
                System.out.println(values[0]);
                System.out.println(values[1]);
                boolean loginCustomer = customer.CheckLogin(values[0], values[1]);
                if (loginCustomer == true) {
                    this.SendMessage("BERHASIL_LOGIN_CUSTOMER");
                } else {
                    this.SendMessage("GAGAL_LOGIN_CUSTOMER");
                }
                
                break;
            case "LOGIN_ADMIN":
                System.out.println(values[0]);
                System.out.println(values[1]);
                boolean loginAdmin = administrator.CheckLogin(values[0], values[1]);
                if (loginAdmin == true) {
                    this.SendMessage("BERHASIL_LOGIN_ADMIN");
                } else {
                    this.SendMessage("GAGAL_LOGIN_ADMIN");
                }
                
                break;
            case "ADD_MENU":
                System.out.println(values[0]);
                System.out.println(values[1]);
                System.out.println(values[2]);
                System.out.println(values[3]);
                int id = Integer.parseInt(values[3]);
                System.out.println("ID YG LOGIN ADALAH" + id);
                System.out.println(rest.getId());
                Menu menu = new Menu(values[0], Integer.valueOf(values[1]), values[2], rest);
                menu.insertData();
                
                this.SendMessage("BERHASIL_ADD_MENU");
                
                break;
            case "UPDATE_MEJA":
                break;
            case "INIT_RESERVATION":
                System.out.println(values[0]);
                String listDataResto = restoran.viewListData();
                this.SendMessage(listDataResto);
                break;
            
            case "SHOW_LIST_MENU":
                System.out.println(values[0]);
                Menu menus = new Menu();
                String listDataMenu = menus.viewListData(rest);
                this.SendMessage(listDataMenu);
                break;
            
            case "UPDATE_MENU":
                Menu menuUpdate = new Menu(Integer.valueOf(values[0]), values[1], Integer.valueOf(values[2]), values[3], rest);
                this.SendMessage(menuUpdate.updateData());
                break;
            
            case "DELETE_MENU":
                Menu menuDelete = new Menu(Integer.valueOf(values[0]), values[1], Integer.valueOf(values[2]), values[3], rest);
                this.SendMessage(menuDelete.deleteData());
                break;
            
            case "TAKE_USR_CUSTOMER":
                Customer usr = new Customer();
                usr = usr.TakeUsr(values[0]);
                this.SendMessage(usr.getUsername() + "," + usr.getNama() + "," + usr.getAlamat() + "," + usr.getEmail());
                break;
        }
//        } catch (IOException ex) {
//            Logger.getLogger(HandleRequest.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
    
    @Override
    public void run() {
        super.run();
        while (true) {
            try {
                pesan = in.readLine();
                System.out.println(pesan);
                if (pesan.contains("REGISTER_RESTO;")) {
                    String[] p = pesan.split(";");
                    
                    this.action(p[0], p[1]);
                    
                } else if (pesan.contains("LOGIN_RESTO;")) {
                    String[] p = pesan.split(";");
                    
                    this.action(p[0], p[1]);
                    
                } else if (pesan.contains("REGISTER_CUSTOMER;")) {
                    String[] p = pesan.split(";");
                    
                    this.action(p[0], p[1]);
                    
                } else if (pesan.contains("LOGIN_CUSTOMER;")) {
                    String[] p = pesan.split(";");
                    
                    this.action(p[0], p[1]);
                    
                } else if (pesan.contains("LOGIN_ADMIN;")) {
                    String[] p = pesan.split(";");
                    
                    this.action(p[0], p[1]);
                } else if (pesan.contains("ADD_MENU;")) {
                    String[] p = pesan.split(";");
                    
                    this.action(p[0], p[1]);
                } else if (pesan.contains("INIT_RESERVATION;")) {
                    String[] p = pesan.split(";");
                    this.action(p[0], p[1]);
                } else if (pesan.contains("SHOW_LIST_MENU;")) {
                    String[] p = pesan.split(";");
                    this.action(p[0], p[1]);
                } else if (pesan.contains("UPDATE_MENU;")) {
                    String[] p = pesan.split(";");
                    this.action(p[0], p[1]);
                } else if (pesan.contains("DELETE_MENU;")) {
                    String[] p = pesan.split(";");
                    this.action(p[0], p[1]);
                } else if(pesan.contains("TAKE_USR_CUSTOMER;,")){
                    String[] p = pesan.split(";");
                    this.action(p[0],p[1]);
                }
            } catch (IOException ex) {
                Logger.getLogger(HandleRequest.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
    }
    
}
