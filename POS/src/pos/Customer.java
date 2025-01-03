/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package pos;

import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.regex.Pattern;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Customer extends javax.swing.JPanel {

    public Customer() {
        initComponents();
        loaddata();
    }
    
    
    private void loaddata(){
         try {
                String query = "SELECT * FROM customer";
                PreparedStatement sql = Connections.connect().prepareStatement(query);

                ResultSet result = sql.executeQuery();
                
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.setRowCount(0);

                // Add rows from the database
                while (result.next()) {
                     Object[] row = {
                         result.getInt("cid"),
                         result.getString("cname"),
                         result.getString("ctel"),
                         result.getString("cgender"),
                         result.getString("cemail")
                     };
                   model.addRow(row);              
                }

            } catch (Exception ex) {
                 ex.printStackTrace();
            }

    }
    
    
    public boolean testcust(){
       if(first.getText() == null || first.getText().isEmpty()) {
           JOptionPane.showMessageDialog(null, "Please enter name with initials", "Warning", JOptionPane.WARNING_MESSAGE);
           return false;
       }else if(tel.getText() == null || tel.getText().isEmpty()) {
           JOptionPane.showMessageDialog(null, "Please enter a telephone number", "Warning", JOptionPane.WARNING_MESSAGE);
           return false;
       }else if(mail.getText() == null || mail.getText().isEmpty()) {
           JOptionPane.showMessageDialog(null, "Please enter an email", "Warning", JOptionPane.WARNING_MESSAGE);
           return false;
       }else if(!Pattern.matches("^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$", mail.getText())) {
            JOptionPane.showMessageDialog(null, "Invalid Email", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
       }else if(Pattern.matches("^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$", mail.getText())){
            try {
                    String query = "SELECT cemail FROM customer WHERE cemail = ? ";
                    PreparedStatement sql = Connections.connect().prepareStatement(query);
                
                    sql.setString(1, mail.getText());
                    ResultSet result = sql.executeQuery();

                    if(result.next()){
                       JOptionPane.showMessageDialog(null, "These email is already registered", "Warning", JOptionPane.WARNING_MESSAGE);
                       return false;
                    }
              
            }catch (Exception ex){
                    ex.printStackTrace();
            }
        }else if(Pattern.matches("^[0-9]{10}$", tel.getText())){
            try {
                    String query = "SELECT ctel FROM customer WHERE ctel = ?";
                    PreparedStatement sql = Connections.connect().prepareStatement(query);
                
                    sql.setString(1, tel.getText());
                    ResultSet result = sql.executeQuery();

                    if(result.next()){
                       JOptionPane.showMessageDialog(null, "These telephone number is already registered", "Warning", JOptionPane.WARNING_MESSAGE); 
                       return false;
                    }
              

            }catch (Exception ex){
                    ex.printStackTrace();
            }
        }     
       
       return true;
    }
    
    
    public void clean(){      
        first.setText("");
        tel.setText("");
        gender.setSelectedItem("");
        mail.setText("");
        search.setText("");
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        insert = new javax.swing.JButton();
        update = new javax.swing.JButton();
        gender = new javax.swing.JComboBox<>();
        mail = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tel = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        first = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        search = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();

        table.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Customer Name", "T.P Number", "Gender", "Email"
            }
        ));
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        insert.setBackground(new java.awt.Color(131, 115, 191));
        insert.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        insert.setForeground(new java.awt.Color(255, 255, 255));
        insert.setText("Insert");
        insert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertActionPerformed(evt);
            }
        });

        update.setBackground(new java.awt.Color(131, 115, 191));
        update.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        update.setForeground(new java.awt.Color(255, 255, 255));
        update.setText("Update");
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });

        gender.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        gender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female", "Other", " " }));
        gender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genderActionPerformed(evt);
            }
        });

        mail.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        mail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mailActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Email:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Telephone Number:");

        tel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                telActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Name with initials:");

        first.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Search Telephone:");

        search.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchActionPerformed(evt);
            }
        });
        search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchKeyReleased(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel3.setText("Customer Information");

        jButton1.setText("Search");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Gender:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(389, 389, 389))
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(insert, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(update)
                                .addGap(79, 79, 79))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(first, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(gender, javax.swing.GroupLayout.Alignment.LEADING, 0, 273, Short.MAX_VALUE)
                                        .addComponent(tel, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(mail))
                                    .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 642, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel3)
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(first, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(gender, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(mail, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(insert)
                            .addComponent(update)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void insertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertActionPerformed
        if (!testcust()) {
          return;
        }else{
            try {
             // INSERTING CUSTOMER
             String query = "INSERT INTO customer(cname, ctel, cgender, cemail) VALUES(?, ?, ?, ?)";
             PreparedStatement sql = Connections.connect().prepareStatement(query);

             sql.setString(1, first.getText());
             sql.setString(2, tel.getText());
             sql.setString(3, gender.getSelectedItem().toString());
             sql.setString(4, mail.getText());

             sql.executeUpdate();

             
             
             // INSERTING LOYALTY
             String query2 = "INSERT INTO loyalty(ltotal, lspent, lbalance, learndate, lexpiredate) VALUES(?, ?, ?, ?, ?)";
             PreparedStatement sql2 = Connections.connect().prepareStatement(query2);

             // Define the Sri Lankan timezone
             ZoneId slzone = ZoneId.of("Asia/Colombo");

             // Get the current date in Sri Lanka timezone
             LocalDate nowdate = LocalDate.now(slzone);
             
             LocalDate nxtyear = nowdate.plusYears(1);

             // Format the date as DD/MM/YYYY
             DateTimeFormatter slcurrent = DateTimeFormatter.ofPattern("dd/MM/yyyy");
             String tdydate = nowdate.format(slcurrent);
             String enddate = nxtyear.format(slcurrent);
             
             sql2.setInt(1, 10);
             sql2.setInt(2, 0);
             sql2.setInt(3, 10);
             sql2.setString(4, tdydate);
             sql2.setString(5, enddate);

             sql2.executeUpdate();
             

             // GETTING LOYALTY ID 
             String query1 = "SELECT TOP 1 lid FROM loyalty ORDER BY lid DESC";
             PreparedStatement sql1 = Connections.connect().prepareStatement(query1);

             ResultSet result = sql1.executeQuery();
             String id = "";
             if (result.next()) {
                id = result.getString("lid");
             }

             
             
             // UPDATE THE LOYALTY FOREIGN KEY
             String query3 = "UPDATE customer SET l_id = ? WHERE cid = ? ";
             PreparedStatement sql3 = Connections.connect().prepareStatement(query3);
                
                sql3.setString(1, id);
                sql3.setString(2, id);
                
                sql3.executeUpdate();
                clean();  
                loaddata();
           } catch (Exception ex) {
               ex.printStackTrace();
           }
        }
        
         
    }//GEN-LAST:event_insertActionPerformed

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
       if (search.getText().isEmpty()) {
           JOptionPane.showMessageDialog(null, "Telphone number needed", "Warning", JOptionPane.WARNING_MESSAGE);
       } else {
           if (Pattern.matches("^[0-9]{10}$", tel.getText())) {
               try {
                  String query = "UPDATE customer SET cname = ?, ctel = ?, cgender = ?, cemail = ? WHERE ctel = ?";
                  PreparedStatement sql = Connections.connect().prepareStatement(query);
            
                  sql.setString(1, first.getText());
                  sql.setString(2, tel.getText());
                  sql.setString(3, gender.getSelectedItem().toString());
                  sql.setString(4, mail.getText());
                  sql.setString(5, search.getText());
            
                  sql.executeUpdate();
                  clean();  
                  loaddata();
               } catch (Exception ex) {
                  ex.printStackTrace();
               } 
         } else {
              JOptionPane.showMessageDialog(null, "Invalid telephone number", "Error", JOptionPane.ERROR_MESSAGE);
         }
       }

    }//GEN-LAST:event_updateActionPerformed

    private void telActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_telActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_telActionPerformed

    private void mailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mailActionPerformed

    private void genderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genderActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_genderActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        
    }//GEN-LAST:event_tableMouseClicked

    private void searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchKeyReleased

    }//GEN-LAST:event_searchKeyReleased

    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (search.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Email or telephone number needed", "Warning", JOptionPane.WARNING_MESSAGE);
        }else if(Pattern.matches("^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$", search.getText())){
            try {
                String query2 = "SELECT * FROM customer WHERE cemail = ?";
                PreparedStatement sql2 = Connections.connect().prepareStatement(query2);
                
                sql2.setString(1, search.getText());
                
                ResultSet result = sql2.executeQuery();
                
                while (result.next()) {
                         first.setText(result.getString("cname"));
                         tel.setText(result.getString("ctel"));
                         gender.setSelectedItem(result.getString("cgender"));
                         mail.setText(result.getString("cemail"));             
                }

            } catch (Exception ex) {
                 ex.printStackTrace();
            }     
        }else{
            try {
                String query = "SELECT * FROM customer WHERE ctel = ?";
                PreparedStatement sql = Connections.connect().prepareStatement(query);
                
                sql.setString(1, search.getText());
                
                ResultSet result = sql.executeQuery();

                while (result.next()) {
                         first.setText(result.getString("cname"));
                         tel.setText(result.getString("ctel"));
                         gender.setSelectedItem(result.getString("cgender"));
                         mail.setText(result.getString("cemail"));            
                }

            } catch (Exception ex) {
                 ex.printStackTrace();
            }     
        }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField first;
    private javax.swing.JComboBox<String> gender;
    private javax.swing.JButton insert;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField mail;
    private javax.swing.JTextField search;
    private javax.swing.JTable table;
    private javax.swing.JTextField tel;
    private javax.swing.JButton update;
    // End of variables declaration//GEN-END:variables
}
