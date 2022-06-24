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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Administrator;
import model.Chat;
import model.Customer;
import model.Menu;
import model.Preorder;
import model.Reservation;
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
    Reservation reservation;
    Chat chat;

    public HandleRequest(MainServer parent, Socket s) {
        this.parent = parent;
        this.s = s;
        this.restoran = new Restaurant();
        this.customer = new Customer();
        this.administrator = new Administrator();
        this.chat = new Chat();

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
        int idResto;
        Administrator admin = new Administrator();
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

            case "TAKE_PROFIT":
                int idProfit = 0;
                String profit = "";
                idProfit = rest.getId();
                Restaurant restProfit = new Restaurant();
                profit = restProfit.Profit(idProfit);
                System.out.println(profit);
                this.SendMessage(profit);
                break;

            case "REGISTER_CUSTOMER":
                Customer cust = new Customer(values[0], values[1], values[2], values[3], values[4], values[5]);
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

            case "INIT_CHAT":
                int idRestoran = 0;
                int idCustomer = 0;
                String help = "";
                if (values[0] == "Restaurant") {
                    help = restoran.getNamaResto();
                    idCustomer = customer.getIdCus(values[1]);
                } else {
                    help = customer.getNamaCus();
                    idRestoran = restoran.getIdRes(values[1]);
                }
                String listmsg = chat.readChat(idRestoran, idCustomer, values[0], values[1]);
                this.SendMessage(help + "//" +listmsg);
                break;

//            case "INSERT_CHAT":
//
//                int idRestoran = 0;
//                int idCustomer = 0;
//                idRestoran = restoran.getIdRes(values[2]);
//                idCustomer = customer.getIdCus(values[3]);
//
//                //int cusID, int resID, String sender, String reviever;
//                String listmsg = chat.readChat(idRestoran, idCustomer, values[0], values[1]);
//                this.SendMessage(listmsg);
//                break;

            case "INIT_RESERVATION":
                System.out.println(value);
                String listDataResto = restoran.viewListData();
                System.out.println(listDataResto);
                this.SendMessage(listDataResto);
                break;

            case "CREATE_RESERVATION":
                //ambil variabel
                idResto = Integer.parseInt(values[0]);
                int numTable = Integer.parseInt(values[1]);
                int numPeople = Integer.parseInt(values[2]);
                restoran = restoran.GetRestoByID(idResto);
                float hargaReservasi = restoran.getHarga_reservasi();

                //Hitung total harga
                float totalPrice = numTable * hargaReservasi + (numPeople * hargaReservasi * (float) 0.2);

                this.SendMessage("CONFIRM_RESERVATION;" + String.valueOf(totalPrice));
                break;

            case "INSERT_RESERVATION":         
                try {
                // Convert date to sqlDate
                String sDate = values[0];
                Date date = new SimpleDateFormat("dd/MM/yyyy").parse(sDate);
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                Reservation insReservation = new Reservation(sqlDate, Integer.parseInt(values[1]), Integer.parseInt(values[2]), values[5], Float.parseFloat(values[6]));
                insReservation.insertData(Integer.parseInt(values[3]), Integer.parseInt(values[4]));
            } catch (ParseException ex) {
                Logger.getLogger(HandleRequest.class.getName()).log(Level.SEVERE, null, ex);
            }
            break;

            case "CHECK_RESTO_TABLES":
                idResto = Integer.parseInt(values[0]);
                int numTables = Integer.parseInt(values[1]);
                restoran = restoran.GetRestoByID(idResto);
                int totalTables = restoran.getJumlahMeja();

                int availableTables = totalTables - numTables;

                if (availableTables >= 0) {
                    //update jumlah meja
                    restoran.updateJumlahMeja(numTables, idResto);
                    this.SendMessage("TABLE_OK");
                } else {
                    this.SendMessage("TABLE_NOT_OK");
                }
                break;

            case "SHOW_LIST_COSTUMER_RESERVATION":
                reservation = new Reservation();
                customer = customer.TakeCustomerById(Integer.parseInt(values[0]));
                String listReservation = reservation.viewListDataByCustomer(customer);
                System.out.println(listReservation);
                this.SendMessage(listReservation);
                break;

            case "SHOW_LIST_RESTAURANT_RESERVATION":
                reservation = new Reservation();
                String listResv = reservation.viewListDataByResto(rest);
                System.out.println(listResv);
                this.SendMessage(listResv);
                break;

            case "SHOW_LIST_PREORDER":
                menu = new Menu();
                resto = new Restaurant();
                int restoIdPreOrder = Integer.parseInt(values[0]);
                resto = resto.DataRestoran(restoIdPreOrder);
                String listPreorder = menu.viewListDataPreOrder(resto);
                System.out.println(listPreorder);
                this.SendMessage(listPreorder);
                break;

            case "SHOW_LIST_MENU":
                System.out.println(values[0]);
                Menu menus = new Menu();
                String listDataMenu = menus.viewListData(rest);
                this.SendMessage(listDataMenu);
                break;

            case "SHOW_LIST_MENU_ADMIN":
                System.out.println(values[0]);
                Menu menuAdmins = new Menu();
                String listDataMenuAdmin = menuAdmins.viewListDataAdmin(rest);
                this.SendMessage(listDataMenuAdmin);
                break;

            case "SHOW_LIST_RESTO":
                System.out.println(values[0]);
                Restaurant restos = new Restaurant();
                String listDataRestoAdmin = restos.viewListDataAdmin();
                this.SendMessage(listDataRestoAdmin);
                break;

            case "UPDATE_MENU":
                Menu menuUpdate = new Menu(Integer.valueOf(values[0]), values[1], Integer.valueOf(values[2]), values[3], rest);
                this.SendMessage(menuUpdate.updateData());
                break;

            case "DELETE_MENU":
                Menu menuDelete = new Menu(Integer.valueOf(values[0]), values[1], Integer.valueOf(values[2]), values[3], rest);
                this.SendMessage(menuDelete.deleteData());
                break;
            case "UPDATE_STATUS":
                String statusBaru = "DONE";
                Reservation updateRsv = new Reservation();
                updateRsv.updateDataStatus(Integer.valueOf(values[0]), statusBaru);
                this.SendMessage("UPDATE_STATUS_BERHASIL");
                break;
            case "DELETE_RESERVATION_RESTO":

                Reservation deleteRsv = new Reservation();
                deleteRsv.deleteDataByID(Integer.valueOf(values[0]));
                this.SendMessage("DELETE_RESERVATION");
                break;

            case "DELETE_RESTO":
                Restaurant restoDelete = new Restaurant(Integer.valueOf(values[0]));
                this.SendMessage(restoDelete.deleteData());
                break;

            case "TAKE_USR_CUSTOMER":
                Customer usrCus = new Customer();
                usrCus = usrCus.TakeCustomer(values[0], values[1]);
                this.SendMessage(usrCus.getId() + "," + usrCus.getUsername() + "," + usrCus.getNama() + "," + usrCus.getAlamat() + "," + usrCus.getEmail() + "," + usrCus.getPassword());
                break;

            case "TAKE_USR_RESTAURANT":
                Restaurant usrRes = new Restaurant();
                usrRes = usrRes.TakeUsr(values[0], values[1]);
                this.SendMessage(usrRes.getId() + "," + usrRes.getPemilik() + "," + usrRes.getNama() + "," + usrRes.getJumlahMeja() + "," + usrRes.isPreOrder() + "," + usrRes.getUsername() + "," + usrRes.getPassword() + "," + usrRes.getAlamat() + "," + usrRes.getNo_telepon() + "," + usrRes.getHarga_reservasi());
                break;

            case "TAKE_USR_ADMIN":
                Administrator usrAd = new Administrator();
                usrAd = usrAd.TakeUsr(values[0], values[1]);
                this.SendMessage(usrAd.getId() + "," + usrAd.getUsername() + "," + usrAd.getPassword() + "," + usrAd.getName());
                break;

            case "TAKE_CUST_ADMIN":
                Restaurant cRest = new Restaurant();
                Customer cCust = new Customer();
                String countRest = cRest.countData();
                String countCust = cCust.countData();
                this.SendMessage("ADMIN-" + countCust + "," + countRest);
                break;

            case "LIST_ADMIN":
                this.SendMessage(admin.viewListAdmin());
                break;

            case "TAKE_ADMIN":
                this.SendMessage(admin.TakeUsr(value).getPassword());
                break;

            case "DELETE_ADMIN":
                this.SendMessage(admin.deleteData(Integer.parseInt(value)));
                break;

            case "UPDATE_ADMIN":
                Administrator admins = new Administrator(Integer.valueOf(values[0]), values[1], values[2], values[3]);
                this.SendMessage(admins.updateData());
                break;

            case "ADD_ADMIN":
                Administrator addAdmin = new Administrator(values[0], values[1], values[2]);
                this.SendMessage(addAdmin.insertData());
                break;

            case "DELETE_CUSTOMER":
                Customer cst = new Customer();
                cst.deleteData(values[2]);
                this.SendMessage("DELETE_SUCCESS");
                break;

            case "SHOW_LIST_CUSTOMER":
                Customer lstCst = new Customer();
                String listCustomer = lstCst.viewListDataCust();
                this.SendMessage(listCustomer);
                System.out.println(listCustomer);
                break;

            case "UPDATE_PROFILE_RESTAURANT":
                boolean preOrder = false;
                System.out.println(value);
                if (values[4].equals("Yes")) {
                    preOrder = true;
                }
//                System.out.println(values[0] + values[1] + values[2] + Integer.parseInt(values[4]) + values[6] +
//                        values[7]+ values[8]+ values[9]+ values[10]);
                Restaurant restUpdate = new Restaurant(Integer.parseInt(values[0]), values[1], values[2], Integer.parseInt(values[3]), preOrder, values[5],
                        values[6], values[7], values[8], Float.parseFloat(values[9]));
                this.SendMessage(restUpdate.updateProfile());
                break;
            case "ADD_PREORDER":
                try {
                reservation = new Reservation();
                int reservationId = reservation.takeLatestId();

                Preorder insPreorder = new Preorder();
                String result = insPreorder.insertData(reservationId, Integer.parseInt(values[0]), Integer.parseInt(values[1]));
                if(!result.equals("INSERT PREORDER SUCCESS")){
                    result = insPreorder.updateDataMenu(reservationId, Integer.parseInt(values[0]), Integer.parseInt(values[1]));
                }
                int totalPriceMenu = Integer.parseInt(values[2]);
                String resultUpdatePrice = reservation.updateTotalPrice(reservationId, totalPriceMenu);
                if (resultUpdatePrice.equals("UPDATE TOTAL PRICE SUCCESS")) {
                    this.SendMessage(result);
                } else {
                    this.SendMessage("UPDATE TOTAL PRICE FAILED");
                }
                System.out.println(result);
            } catch (Exception ex) {
                Logger.getLogger(HandleRequest.class.getName()).log(Level.SEVERE, null, ex);
            }
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
                String[] p = pesan.split(";");
                this.action(p[0], p[1]);
            } catch (IOException ex) {
                Logger.getLogger(HandleRequest.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

}
