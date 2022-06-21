/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Customer;

import MainForm.FormRegisterRestaurant;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.ComboItem;

/**
 *
 * @author Alvin Fernando
 */
public class FormReservation extends javax.swing.JFrame {

    /**
     * Creates new form FormReservation
     */
    Socket s;
    BufferedReader in;
    DataOutputStream out;
    String message;
    String restoName;
    int restoId = 0;
    int customerId = 0;

    public FormReservation(int customerID) {
        initComponents();
        try {
            s = new Socket("localhost", 3233);
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            out = new DataOutputStream(s.getOutputStream());
            customerId = customerID;
            out.writeBytes("INIT_RESERVATION; \n");
            message = in.readLine();
            this.setLocationRelativeTo(null);

            //Masukin Resto Ke combobox
            String[] valueResto = message.split(",");

            for (int i = 0; i < valueResto.length; i++) {
                String[] value = valueResto[i].split("/");
                cbRestaurant.addItem(new ComboItem(value[0], value[1]));
            }
            btnFoodOrder.setEnabled(false);

        } catch (IOException ex) {
            Logger.getLogger(FormReservation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnFoodOrder = new javax.swing.JButton();
        btnBook = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        cbRestaurant = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        numPeople = new javax.swing.JSpinner();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        dateBooking = new com.toedter.calendar.JDateChooser();
        numTable = new javax.swing.JSpinner();
        jLabel8 = new javax.swing.JLabel();
        btnCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 202, 3));
        jPanel1.setForeground(new java.awt.Color(255, 202, 3));

        btnFoodOrder.setBackground(new java.awt.Color(255, 255, 255));
        btnFoodOrder.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnFoodOrder.setText("FOODS ORDER");
        btnFoodOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFoodOrderActionPerformed(evt);
            }
        });

        btnBook.setBackground(new java.awt.Color(255, 255, 255));
        btnBook.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnBook.setText("BOOKING");
        btnBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBookActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel5.setText("Restaurant:");
        jLabel5.setToolTipText("");

        cbRestaurant.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        cbRestaurant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbRestaurantActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel6.setText("Booking Date:");
        jLabel6.setToolTipText("");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel7.setText("Number of Peoples:");
        jLabel7.setToolTipText("");

        numPeople.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        numPeople.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        numPeople.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                numPeopleStateChanged(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("FORM RESERVATION");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(76, 76, 76))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        numTable.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        numTable.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        numTable.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        numTable.setEnabled(false);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel8.setText("Number of tables:");
        jLabel8.setToolTipText("");

        btnCancel.setBackground(java.awt.Color.red);
        btnCancel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnCancel.setForeground(java.awt.Color.white);
        btnCancel.setText("CANCEL");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jLabel8)))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(numTable)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(cbRestaurant, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 3, Short.MAX_VALUE))
                            .addComponent(dateBooking, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnFoodOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBook, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnCancel))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(12, 12, 12)
                                .addComponent(numPeople)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbRestaurant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(dateBooking, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(numPeople, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(numTable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBook)
                    .addComponent(btnFoodOrder)
                    .addComponent(btnCancel))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnFoodOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFoodOrderActionPerformed
        FormPreOrder form = new FormPreOrder(restoId);
        form.setVisible(true);
    }//GEN-LAST:event_btnFoodOrderActionPerformed

    private void btnBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBookActionPerformed
        try {
            if (Integer.parseInt(numTable.getValue().toString()) > 0 && dateBooking.getDate() != null) {
                Format dateFormatter = new SimpleDateFormat("d MMM YYYY");
                Format dateFormatServer = new SimpleDateFormat("dd/MM/yyyy");
                Date bookingDate = dateBooking.getDate();
                String stringDate = dateFormatter.format(bookingDate);
                String stringDateServer = dateFormatServer.format(bookingDate);

                //Buat reservasi & kalkulasi total pricenya di server
                out.writeBytes("CREATE_RESERVATION;" + restoId + "," + numTable.getValue().toString() + "," + numPeople.getValue().toString() + "\n");
                message = in.readLine();

                String[] messages = message.split(";");

                //Detail reservasi ditampilin di confirm dialog
                if (message.contains("CONFIRM_RESERVATION;")) {
                    int answer = JOptionPane.showConfirmDialog(this,
                            "Restaurant = " + restoName + "\n"
                            + "Booking Date = " + stringDate + "\n"
                            + "Number of Tables = " + numTable.getValue() + " Table(s)\n"
                            + "Number of People = " + numPeople.getValue() + " Person(s)\n"
                            + "Total Prices = Rp." + messages[1] + "\n\n"
                            + "Are you sure want to confirm this reservation ?", "Confirm Reservation",
                            JOptionPane.YES_NO_OPTION);
                    if (answer == JOptionPane.YES_OPTION) {
                        //Cek ketersediaaan tables di resto
                        out.writeBytes("CHECK_RESTO_TABLES;" + restoId + "," + numTable.getValue().toString() + "\n");
                        message = in.readLine();

                        if (message.contains("TABLE_OK")) {
                            //insert data kalo tablenya tersedia
                            out.writeBytes("INSERT_RESERVATION;" + stringDateServer + "," + numPeople.getValue().toString() + "," + numTable.getValue().toString() + "," + restoId + "," + customerId + ",pending," + messages[1] + "\n");
                            JOptionPane.showMessageDialog(this, "Reservation is successfully created!");
                        } else {
                            JOptionPane.showMessageDialog(this, "Sorry, the table(s) in this restaurant is running out");
                        }
                        btnFoodOrder.setEnabled(true);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Input the date and number of person(s) first");
            }

        } catch (IOException ex) {
            Logger.getLogger(FormReservation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnBookActionPerformed

    private void cbRestaurantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbRestaurantActionPerformed
        // Ngambil value dari combobox
        ComboItem selectedItem = (ComboItem) cbRestaurant.getSelectedItem();
        if (selectedItem != null) {
            int selectedRestoID = Integer.parseInt(selectedItem.getValue());
            restoId = selectedRestoID;
            restoName = selectedItem.getLabel();
        }

    }//GEN-LAST:event_cbRestaurantActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void numPeopleStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_numPeopleStateChanged
        // TODO add your handling code here:
        int numOfPeople = Integer.parseInt(numPeople.getValue().toString());
        int numOfTables = (int) Math.ceil(numOfPeople / 4.0);
        numTable.setValue(numOfTables);
    }//GEN-LAST:event_numPeopleStateChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormReservation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormReservation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormReservation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormReservation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormReservation(0).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBook;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnFoodOrder;
    private javax.swing.JComboBox<Object> cbRestaurant;
    private com.toedter.calendar.JDateChooser dateBooking;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSpinner numPeople;
    private javax.swing.JSpinner numTable;
    // End of variables declaration//GEN-END:variables
}
