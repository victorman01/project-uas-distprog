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
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
public class MainServer {
    ServerSocket server;
    Socket s;
    String pesan;
    ArrayList<HandleRequest>Client;
    
    public MainServer()
    {
        try {
            server=new ServerSocket(3233);
            Client=new ArrayList<HandleRequest>();
            
            while(true)
            {
                s=server.accept();
                HandleRequest hr= new HandleRequest(this, s);
                hr.start();
                Client.add(hr);
            }
        } catch (IOException ex) {
            Logger.getLogger(MainServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
