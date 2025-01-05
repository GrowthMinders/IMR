/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.SwingUtilities;
import pos.Login.cash;
import java.text.SimpleDateFormat; 
import java.util.Date;            
import java.util.TimeZone; 
import java.sql.SQLException;
import pos.Emails.SupplierContact.mail;
import pos.Product_Selection.frontdata;

/**
 *
 * @author DELL
 */
public class Cashier_Dashboard extends javax.swing.JFrame {
    
    
        public class search{
           public static String sre = "";
           public static String time = "";
           public static String cashier = "";
           public static boolean timeSet = false;
        }
    
        public class billdetails{
           public static String[] prodbatch = new String[100];
           public static int[] prodprice = new int[100];
           public static String[] prodid = new String[100];
           public static int[] proddisc = new int[100];
           public static String date = "";
           public static String etime = "";
        }
        
        
    String id = "";
    String custname = "";
    public Cashier_Dashboard() {
       
        initComponents();
        purchasetable();
        jTextField2.setText(cash.cashierer);
        search.cashier = jTextField2.getText();
        ZoneId slzone = ZoneId.of("Asia/Colombo");

        // Get the current date in Sri Lanka timezone
        LocalDate nowdate = LocalDate.now(slzone);

        // Format the date as DD/MM/YYYY
        DateTimeFormatter slcurrent = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String tdydate = nowdate.format(slcurrent);
        billdetails.date = nowdate.format(slcurrent);
        jTextField3.setText(tdydate);
    }

    
        
        private void purchasetable() {
            
            jTextField1.setText("");

            // Check if jTextField4 is empty and set time only once
            if (!search.timeSet && jTextField4.getText().isEmpty()) {
                TimeZone sriLankaTimeZone = TimeZone.getTimeZone("Asia/Colombo");
                SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
                sdf.setTimeZone(sriLankaTimeZone);
                Date now = new Date();
                search.time = sdf.format(now);
                jTextField4.setText(search.time);
                search.timeSet = true;
            }

           
          for(int i = 0; i < 100; i++){
               String product = frontdata.arr[i];
           if(frontdata.arr[i] != null){  
            try {
              // Query to fetch product details
              String query = "SELECT pid, pname, pbatchno, price FROM product WHERE pname = ?";
              PreparedStatement sql = Connections.connect().prepareStatement(query);
              sql.setString(1, product);
              ResultSet result = sql.executeQuery();

              DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
             if(i==0){ 
                model.setRowCount(0);
             } 

              String products = "";
              String batch = "";
              int price = 0;

              while (result.next()) {
                 billdetails.prodid[i] = result.getString("pid"); 
                 products = result.getString("pname");
                 batch = result.getString("pbatchno");
                 price = result.getInt("price");
              }
              
              // Query to fetch discount details
              String query1 = "SELECT dvalue, dcusteli FROM discount WHERE dproduct = ?";
              PreparedStatement sql1 = Connections.connect().prepareStatement(query1);
              sql1.setString(1, products);
              ResultSet result1 = sql1.executeQuery();

              while (result1.next()) {
                 billdetails.proddisc[i] = result1.getInt("dvalue");
                 Object[] row = {
                    products,
                    billdetails.prodbatch[i] = batch,
                    billdetails.prodprice[i] = price - result1.getInt("dvalue"),
                    frontdata.selctedqty[i],
                    frontdata.selctedqty[i] * (price - result1.getInt("dvalue"))
                 };
                 model.addRow(row);
                 
              }  
                 
                if(billdetails.proddisc[i] == 0){
                    Object[] row = {
                       products,
                       billdetails.prodbatch[i] = batch,
                       billdetails.prodprice[i] = price,
                       frontdata.selctedqty[i],
                       frontdata.selctedqty[i] * price
                    };
                    model.addRow(row);
                }  
          
             // Update UI in Swing's Event Dispatch Thread
             SwingUtilities.invokeLater(() -> {
             jTable1.revalidate();
             jTable1.repaint();
             model.fireTableDataChanged();
              });

    } catch (Exception ex) {
        ex.printStackTrace();
    }
    }else{
      break;         
    }        
   }         
}

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
        });

        jLabel1.setText("Product Name:");

        jButton2.setBackground(new java.awt.Color(131, 115, 191));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setText("Customers");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setText("Support");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton11.setBackground(new java.awt.Color(131, 115, 191));
        jButton11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton11.setText("Log Out");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Product Name", "Batch No", "Price", "Quantity", "Subtotal"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButton4.setText("Checkout");
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
        });
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel2.setText("Cashier:");

        jLabel3.setText("Date:");

        jLabel4.setText("Time:");

        jLabel5.setText("Telephone Number:");

        jTextField5.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jTextField5.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jButton5.setText("Get  Loyalty");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton3.setText("Get Product");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(144, 144, 144)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(54, 54, 54)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel5))))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE))
                                .addGap(43, 43, 43)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(68, 68, 68)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(68, 68, 68)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(66, 66, 66)
                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        JFrame frame = new JFrame("Super Mart");

        // Create an instance of your JPanel
        Customer cust = new Customer();
        
        // Add the JPanel to the JFrame
        frame.add(cust);

        // Set size and make it visible
        frame.setSize(1170, 522); // Adjust size as needed

        frame.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        JFrame frame = new JFrame("Super Mart");

        // Create an instance of your JPanel
        Login log = new Login();
        
        // Add the JPanel to the JFrame
        frame.add(log);

        // Set size and make it visible
        frame.setSize(672, 430); // Adjust size as needed

        frame.setVisible(true);
        cash.cashierer = "";
        this.dispose();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JFrame frame = new JFrame("Super Mart");

        // Create an instance of your JPanel
        Support sup = new Support();
        
        // Add the JPanel to the JFrame
        frame.add(sup);

        // Set size and make it visible
        frame.setSize(598, 230); // Adjust size as needed
        frame.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    
    
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        if(jTextField5.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Please enter a telephone number", "Warning", JOptionPane.WARNING_MESSAGE);
        }else{
            try {
                    String query = "SELECT cid, cname FROM customer WHERE ctel = ?";
                    PreparedStatement sql = Connections.connect().prepareStatement(query);
                
                    sql.setString(1, jTextField5.getText());
                    ResultSet result = sql.executeQuery();

                    if(result.next()){
                        jLabel5.setText("Loyalty:");
                        jTextField5.setText("");
                        id = result.getString("cid");
                        custname = result.getString("cname");
                        jTextField5.setText(id +" - "+ custname);
                    }else{
                        int check = JOptionPane.showConfirmDialog(null, "Acoount not found, do you want to create a loyalty ?");
                        if(check == 0){
                           JFrame frame = new JFrame("Super Mart");
                           // Create an instance of your JPanel
                           Customer cust = new Customer();
                           // Add the JPanel to the JFrame
                           frame.add(cust);
                           // Set size and make it visible
                           frame.setSize(1170, 522); // Adjust size as needed
                           frame.setVisible(true); 
                        }
                    }
              

            }catch (Exception ex){
                    ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped
       
    }//GEN-LAST:event_jTextField1KeyTyped

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
     //
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseClicked
            
         DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
         model.setRowCount(0);
        
             TimeZone sriLankaTimeZone = TimeZone.getTimeZone("Asia/Colombo");
             SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
             sdf.setTimeZone(sriLankaTimeZone);
             Date now = new Date();
             billdetails.etime = sdf.format(now);

  for (int i = 0; i < 100; i++) { 
    if (frontdata.arr[i] != null) {
        try {
            String query = "INSERT INTO customerbuy (p_id, pquantity) VALUES (?, ?)";
            PreparedStatement sql = Connections.connect().prepareStatement(query);

            sql.setString(1, billdetails.prodid[i]);
            sql.setInt(2, frontdata.selctedqty[i]);
            sql.executeUpdate();  // Corrected to executeUpdate for INSERT

            sql.close(); // Closing statement to prevent leaks
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        try {
            String trigger = "CALL purchase_amount()";
            PreparedStatement triggerSql = Connections.connect().prepareStatement(trigger);
            triggerSql.execute(); // Corrected to execute for CALL
            triggerSql.close(); // Closing statement to prevent leaks
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
       
        
        
        try {
            String callProcedure = "{CALL stock_check(?)}";
            PreparedStatement sql = Connections.connect().prepareStatement(callProcedure);
            sql.setString(1, frontdata.arr[i]);
            
            ResultSet result = sql.executeQuery();
            if(result.next()){
                int stock = Integer.parseInt(result.getString("pstock"));
                String supplier = result.getString("psupply");
                if(stock <= 5){
                    try {
                       String query1 = "SELECT semail FROM supplier WHERE sname ?";
                       PreparedStatement sql1 = Connections.connect().prepareStatement(query1);
                       sql1.setString(1, supplier);
                       ResultSet result1 = sql.executeQuery();
                       while (result.next()) {
                          mail.email = result.getString("semail");
                          mail.product = frontdata.arr[i];
                       }
    
                   } catch (Exception ex) {
                      ex.printStackTrace();
                   }

                    pos.Emails.SupplierContact supply = new pos.Emails.SupplierContact();
                    supply.sendEmail();
                }            
            }
            
            sql.execute(); // Corrected to execute for CALL
            sql.close(); // Closing statement to prevent leaks
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        
        try {
            ZoneId slzone = ZoneId.of("Asia/Colombo");
            // Get the current date in Sri Lanka timezone
            LocalDate nowdate = LocalDate.now(slzone);
            // Format the date as DD/MM/YYYY
            DateTimeFormatter slcurrent = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String tdydate = nowdate.format(slcurrent);
            
            String callProcedure = "{CALL punchase_history_insert(?, ?, ?, ?, ?, ?, ?, ?)}";
            PreparedStatement sql = Connections.connect().prepareStatement(callProcedure);

            sql.setString(1, search.time);
            sql.setString(2, billdetails.etime);
            sql.setString(3, search.cashier);
            sql.setString(4, frontdata.arr[i]);
            sql.setInt(5, frontdata.selctedqty[i]);
            sql.setInt(6, billdetails.prodprice[i]);
            sql.setInt(7, billdetails.proddisc[i]);
            sql.setString(8, tdydate);
            sql.execute(); // Corrected to execute for CALL
            sql.close(); // Closing statement to prevent leaks
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        
    } else {
        break;
    }
}
                try {
                  String trigger = "CALL logging()";
                  PreparedStatement triggerSql = Connections.connect().prepareStatement(trigger);
                  triggerSql.execute(); // Corrected to execute for CALL
                  triggerSql.close(); // Closing statement to prevent leaks
               } catch (SQLException ex) {
                  ex.printStackTrace();
               }   
                    
  
                    JFrame frame = new JFrame("Super Mart");

                    // Create an instance of your JPanel
                    Emailgrab email = new Emailgrab();
        
                    // Add the JPanel to the JFrame
                    frame.add(email);

                    // Set size and make it visible
                    frame.setSize(489, 266); // Adjust size as needed
                    frame.setVisible(true);

    }//GEN-LAST:event_jButton4MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
         try {
          String inputText = jTextField1.getText();
    
        String query = "SELECT pname FROM product WHERE pname LIKE ?";
        PreparedStatement sql = Connections.connect().prepareStatement(query);

        sql.setString(1, inputText + "%");
        ResultSet result = sql.executeQuery();

        
        while (result.next()) {
            search.sre = result.getString("pname"); 
        }

        if (!search.sre.isEmpty()) {
            this.dispose();
            JFrame frame = new JFrame("Super Mart");

            // Create an instance of your JPanel
            Product_Selection prosel = new Product_Selection();

            // Add the JPanel to the JFrame
            frame.add(prosel);

            // Set size and make it visible
            frame.setSize(598, 230); // Adjust size as needed
            frame.setVisible(true);
        }
    
         } catch (Exception ex) {
             ex.printStackTrace();
         }

    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Cashier_Dashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    // End of variables declaration//GEN-END:variables
}
