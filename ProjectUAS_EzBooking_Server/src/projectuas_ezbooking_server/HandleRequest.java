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

    public HandleRequest(MainServer parent, Socket s) {
        this.parent = parent;
        this.s = s;
        this.restoran = new Restaurant();

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

    public void action(String command, String value) {
        String[] values = value.split(",");
        switch (command) {

            case "REGISTER_RESTO":

                Restaurant resto = new Restaurant(values[0], values[1], Integer.valueOf(values[2]), Boolean.valueOf(values[3]), values[4], values[5], values[6], values[7]);
                resto.insertData();

                this.SendMessage("BERHASIL_REGISTER");
                break;
            case "LOGIN_RESTO":
                System.out.println(values[0]);
                System.out.println(values[1]);
                boolean login = restoran.CheckLogin(values[0], values[1]);
                if (login == true) {
                    this.SendMessage("BERHASIL_LOGIN_RESTAURANT");
                } else {
                    this.SendMessage("GAGAL_LOGIN_RESTAURANT");
                }

                break;
            case "ADD_MENU":
                break;
            case "UPDATE_MEJA":
                break;

        }
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
                }

            } catch (IOException ex) {
                Logger.getLogger(HandleRequest.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

}