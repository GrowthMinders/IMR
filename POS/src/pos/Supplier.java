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

public class Supplier extends javax.swing.JPanel {


    
    public Supplier() {
        initComponents();
        loaddata();
    }
    
    private void loaddata(){
        try {
                String query = "SELECT * FROM supplier sup LEFT JOIN supplier_mobile smob ON sup.sid = smob.s_id";
                PreparedStatement sql = Connections.connect().prepareStatement(query);

                ResultSet result = sql.executeQuery();
                
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.setRowCount(0);

                // Add rows from the database
                while (result.next()) {
                     Object[] row = {
                         result.getInt("sid"),
                         result.getString("sname"),
                         result.getString("semail"),
                         result.getString("slocation"),
                         result.getString("stel"),
                         result.getString("stel1"),
                     };
                   model.addRow(row);
                   
                }

            } catch (Exception ex) {
                 ex.printStackTrace();
            }
    }
    
    
    public boolean testemp(){
        if (first.getText() == null || first.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a name", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }else if(mail.getText() == null || mail.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter an email", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }else if(location.getText() == null || location.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter an address", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }else if(tel1.getText() == null || tel1.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter an address", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }else if(!Pattern.matches("^[A-Za-z\\s]{3,50}$", first.getText())) {
            JOptionPane.showMessageDialog(null, "Invalid name", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }else if(!Pattern.matches("^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$", mail.getText())) {
            JOptionPane.showMessageDialog(null, "Invalid Email", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }else if(Pattern.matches("^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$", mail.getText())){
               try {
                    String query = "SELECT semail FROM supplier WHERE semail = ? ";
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
        }else if(!Pattern.matches("^[0-9a-zA-Z#,\\-.\\s/()]+,\\s*[a-zA-Z\\s]+,\\s*[a-zA-Z\\s]+,\\s*(\\d{5})?$", location.getText())) {
            JOptionPane.showMessageDialog(null, "Invalid Address", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }else if(!Pattern.matches("^[0-9]{10}$", tel1.getText())) {
            JOptionPane.showMessageDialog(null, "Invalid telephone number", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }else if(Pattern.matches("^[0-9]{10}$", tel1.getText())){
                 try {
                    String query = "SELECT stel, stel1 FROM supplier_mobile WHERE stel = ? OR stel1 = ?";
                    PreparedStatement sql = Connections.connect().prepareStatement(query);
                
                    sql.setString(1, tel1.getText());
                    sql.setString(2, tel1.getText());
                    ResultSet result = sql.executeQuery();

                    if(result.next()){
                       JOptionPane.showMessageDialog(null, "These telephone number is already registered", "Warning", JOptionPane.WARNING_MESSAGE);
                       return false;
                    }
              

                 }catch (Exception ex){
                    ex.printStackTrace();
                 }
        }else if(!tel2.getText().isEmpty()){
              if(!Pattern.matches("^[0-9]{10}$", tel2.getText())) {
                 JOptionPane.showMessageDialog(null, "Invalid telephone number", "Warning", JOptionPane.WARNING_MESSAGE);
                 return false;
              }else{
                 try {
                    String query = "SELECT stel, stel1 FROM supplier_mobile WHERE stel = ? OR stel1 = ?";
                    PreparedStatement sql = Connections.connect().prepareStatement(query);
                
                    sql.setString(1, tel2.getText());
                    sql.setString(2, tel2.getText());
                    ResultSet result = sql.executeQuery();

                    if(result.next()){
                       JOptionPane.showMessageDialog(null, "These telephone number is already registered", "Warning", JOptionPane.WARNING_MESSAGE); 
                       return false;
                    }
              

                 }catch (Exception ex){
                    ex.printStackTrace();
                 }
              }
        }
        
        return true;
    }
    
    public void clean(){
       first.setText("");
       mail.setText("");
       location.setText("");
       tel1.setText(""); 
       tel2.setText(""); 
       search.setText(""); 
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        search = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        first = new javax.swing.JTextField();
        tel1 = new javax.swing.JTextField();
        tel2 = new javax.swing.JTextField();
        mail = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        location = new javax.swing.JTextArea();
        insert = new javax.swing.JButton();
        update = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Name", "Email", "Address", "Telephone 1", "Telephone 2"
            }
        ));
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(table);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setText("Supplier Information");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Search Id:");

        search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchKeyReleased(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Name:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Telephone1:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Telephone2:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Email:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Location:");

        first.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                firstActionPerformed(evt);
            }
        });

        mail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mailActionPerformed(evt);
            }
        });

        location.setColumns(20);
        location.setRows(5);
        jScrollPane1.setViewportView(location);

        insert.setBackground(new java.awt.Color(131, 115, 191));
        insert.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        insert.setForeground(new java.awt.Color(255, 255, 255));
        insert.setText("Insert");
        insert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertActionPerformed(evt);
            }
        });

        update.setBackground(new java.awt.Color(131, 115, 191));
        update.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        update.setForeground(new java.awt.Color(255, 255, 255));
        update.setText("Update");
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });

        delete.setBackground(new java.awt.Color(131, 115, 191));
        delete.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        delete.setForeground(new java.awt.Color(255, 255, 255));
        delete.setText("Delete");
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });

        jButton1.setText("Search");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(insert)
                        .addGap(71, 71, 71)
                        .addComponent(delete)
                        .addGap(62, 62, 62)
                        .addComponent(update))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton1))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(tel2)
                                .addComponent(mail)
                                .addComponent(jScrollPane1)
                                .addComponent(tel1, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                                .addComponent(first)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
            .addGroup(layout.createSequentialGroup()
                .addGap(294, 294, 294)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(first, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(mail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(64, 64, 64)
                                .addComponent(jLabel7)
                                .addGap(42, 42, 42))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(tel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(tel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(insert)
                            .addComponent(delete)
                            .addComponent(update)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 456, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(31, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void insertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertActionPerformed
        if (!testemp()) {
          return;
        }else{
        try {
            
                String query = "INSERT INTO supplier(sname, semail, slocation) VALUES(?, ?, ?)";
                PreparedStatement sql = Connections.connect().prepareStatement(query);

                sql.setString(1, first.getText());
                sql.setString(2, mail.getText());
                sql.setString(3, location.getText());
               
                sql.executeUpdate();
                
                //GETTING THE ID TO INSERT TELEPHONE NUMBER
                String query1 = "SELECT sid FROM supplier WHERE semail = ?";
                PreparedStatement sql1 = Connections.connect().prepareStatement(query1);
                String id = ""; 
                sql1.setString(1, mail.getText());
                ResultSet result = sql1.executeQuery();
                    if (result.next()){
                       id = result.getString("sid");
                    }
                
                //INSERTING TELEPHONE NUMBER
                String query2 = "INSERT INTO supplier_mobile(s_id, stel, stel1) VALUES(?, ?, ?)";
                PreparedStatement sql2 = Connections.connect().prepareStatement(query2);

                sql2.setString(1, id);
                sql2.setString(2, tel1.getText());
                sql2.setString(3, tel2.getText());

                sql2.executeUpdate();
                clean();
                loaddata();
            } catch (Exception ex) {
                 ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_insertActionPerformed

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
        if (search.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "ID needed", "Warning", JOptionPane.WARNING_MESSAGE);
        }else{
            if (!testemp()) {
               return;
            }
            try {
            
                String query = "UPDATE supplier SET sname = ?, semail = ?, slocation = ? WHERE sid = ?";
                PreparedStatement sql = Connections.connect().prepareStatement(query);

                sql.setString(1, first.getText());
                sql.setString(2, mail.getText());
                sql.setString(3, location.getText());
                sql.setString(4, search.getText());
               
                sql.executeUpdate();
                
                
                String query1 = "UPDATE supplier_mobile SET s_id = ?, stel = ?, stel1 = ? WHERE s_id = ?";
                PreparedStatement sql1 = Connections.connect().prepareStatement(query1);

                sql1.setString(1, search.getText());
                sql1.setString(2, tel1.getText());
                sql1.setString(3, tel2.getText());
                sql1.setString(4, search.getText());

                sql1.executeUpdate();
                clean();
                loaddata();
            } catch (Exception ex) {
                 ex.printStackTrace();
            }
        }
        
        
    }//GEN-LAST:event_updateActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tableMouseClicked

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        if (search.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "ID needed", "Warning", JOptionPane.WARNING_MESSAGE);
        }else{
            try {
              String query2 = "DELETE FROM supplier_mobile WHERE s_id = ?";
              PreparedStatement sql2 = Connections.connect().prepareStatement(query2);
              sql2.setString(1, search.getText());
              sql2.executeUpdate();

    
              String query1 = "DELETE FROM supplier WHERE sid = ?";
              PreparedStatement sql1 = Connections.connect().prepareStatement(query1);
              sql1.setString(1, search.getText());
              sql1.executeUpdate();
              
               clean();
               loaddata();
            } catch (Exception ex) {
                 ex.printStackTrace();
            }    
        }
    }//GEN-LAST:event_deleteActionPerformed

    private void searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_searchKeyReleased

    private void firstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_firstActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_firstActionPerformed

    private void mailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mailActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       try {
                String query = "SELECT * FROM supplier sup LEFT JOIN supplier_mobile smob ON sup.sid = smob.s_id WHERE sup.sid = ?";
                PreparedStatement sql = Connections.connect().prepareStatement(query);

                sql.setString(1, search.getText());
                ResultSet result = sql.executeQuery();

                while (result.next()) {
                   
                         first.setText(result.getString("sname"));
                         mail.setText(result.getString("semail"));
                         location.setText(result.getString("slocation"));
                         tel1.setText(result.getString("stel"));
                         tel2.setText(result.getString("stel1")); 
              
                }

            } catch (Exception ex) {
                 ex.printStackTrace();
            }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton delete;
    private javax.swing.JTextField first;
    private javax.swing.JButton insert;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea location;
    private javax.swing.JTextField mail;
    private javax.swing.JTextField search;
    private javax.swing.JTable table;
    private javax.swing.JTextField tel1;
    private javax.swing.JTextField tel2;
    private javax.swing.JButton update;
    // End of variables declaration//GEN-END:variables
}
