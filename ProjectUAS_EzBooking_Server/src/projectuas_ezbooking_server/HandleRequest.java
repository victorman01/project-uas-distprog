/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectuas_ezbooking_server;

/**
 *
 * @author Muhammad Ikhsan
 */
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Administrator;
import model.Costumer;
import model.Menu;
import model.Preorder;
import model.Restaurant;

public class HandleRequest extends Thread {
    BufferedReader in;
    DataOutputStream out;
    MainServer parent;
    Socket s;
    String pesan;
    Restaurant restoran;
    
    public HandleRequest(MainServer parent, Socket s)
    {
        this.parent=parent;
        this.s=s;
        this.restoran=new Restaurant();
        
        try {
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            out = new DataOutputStream(s.getOutputStream());
            
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
    
}
