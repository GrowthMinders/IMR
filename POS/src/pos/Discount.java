/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package pos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Supun
 */
public class Discount extends javax.swing.JPanel {

    /**
     * Creates new form disc
     */
    public Discount() {
        initComponents();
        loaddata();
        loadproducts();
    }

    private void loaddata(){
         try {
                String query = "SELECT * FROM discount";
                PreparedStatement sql = Connections.connect().prepareStatement(query);

                ResultSet result = sql.executeQuery();
                
                DefaultTableModel model = (DefaultTableModel) dtable.getModel();
                model.setRowCount(0);

                // Add rows from the database
                while (result.next()) {
                     Object[] row = {
                         result.getInt("did"),
                         result.getString("dname"),
                         result.getString("dstartdate"),
                         result.getString("denddate"),
                         result.getString("dproduct"),
                         result.getString("dcusteli"),
                         result.getString("dvalue")
                     };
                   model.addRow(row);
                   
                }

            } catch (Exception ex) {
                 ex.printStackTrace();
            }
    }
    
    private void loadproducts(){
        try {
                String query = "SELECT pid,pname FROM product";
                PreparedStatement sql = Connections.connect().prepareStatement(query);

                ResultSet result = sql.executeQuery();
                
                DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                model.setRowCount(0);

                // Add rows from the database
                while (result.next()) {
                     Object[] row = {
                         result.getInt("pid"),
                         result.getString("pname")
                     };
                   model.addRow(row);
                   
                }

            } catch (Exception ex) {
                 ex.printStackTrace();
            }
    }
   
    
    public boolean disctester(){
       if(name.getText() == null || name.getText().isEmpty()){
          JOptionPane.showMessageDialog(null, "Please enter a product name", "Warning", JOptionPane.WARNING_MESSAGE);
          return false;
       }else if(sdate.getText() == null || sdate.getText().isEmpty()){
          JOptionPane.showMessageDialog(null, "Please enter a start date for the discount", "Warning", JOptionPane.WARNING_MESSAGE);
          return false;
       }else if(edate.getText() == null || edate.getText().isEmpty()){
          JOptionPane.showMessageDialog(null, "Please enter an end date for the discount", "Warning", JOptionPane.WARNING_MESSAGE);
          return false;
       }else if(aproduct.getText() == null || aproduct.getText().isEmpty()){
          JOptionPane.showMessageDialog(null, "Please enter a product ID to apply the discount", "Warning", JOptionPane.WARNING_MESSAGE);
          return false;
       }else if(amount.getText() == null || amount.getText().isEmpty()){
          JOptionPane.showMessageDialog(null, "Please enter a discount amount", "Warning", JOptionPane.WARNING_MESSAGE);
          return false;
       }else if(!Pattern.matches("^[A-Za-z\\s]+$", name.getText())) {
          JOptionPane.showMessageDialog(null, "Invalid discount name", "Warning", JOptionPane.WARNING_MESSAGE);
          return false;
       }else if(!Pattern.matches("^[0-9]{2}/[0-9]{2}/[0-9]{4}$", sdate.getText())) {
          JOptionPane.showMessageDialog(null, "Invalid discount start date, should be(DD/MM/YYYY)", "Warning", JOptionPane.WARNING_MESSAGE);
          return false;
       }else if(!Pattern.matches("^[0-9]{2}/[0-9]{2}/[0-9]{4}$", edate.getText())) {
          JOptionPane.showMessageDialog(null, "Invalid discount end date, should be(DD/MM/YYYY)", "Warning", JOptionPane.WARNING_MESSAGE);
          return false;
       }else if(!Pattern.matches("^(?=.*[A-Za-z])[A-Za-z0-9\\s]+$", aproduct.getText())){
          JOptionPane.showMessageDialog(null, "Enter a valid product ID referring to the table to retrieve product name", "Warning", JOptionPane.WARNING_MESSAGE);
          return false;
       }else if(!Pattern.matches("^[0-9]+$", amount.getText())) {  // Allowing multiple digits
          JOptionPane.showMessageDialog(null, "Invalid discount amount, should be a numeric value", "Warning", JOptionPane.WARNING_MESSAGE);
          return false;
      }else{
        // Checking if the product ID exists in the database
        try {
            String query = "SELECT pname FROM product WHERE pname = ?";
            PreparedStatement sql = Connections.connect().prepareStatement(query);
            sql.setString(1, aproduct.getText());  // Compare with entered product ID

            ResultSet result = sql.executeQuery();
            
            // Check if any result was found for the product name
            if (!result.next()) {
                JOptionPane.showMessageDialog(null, "Product name not found", "Warning", JOptionPane.WARNING_MESSAGE);
                return false;
            }

            result.close();
            sql.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
      }

    return true;
}
    public void clean(){
        name.setText("");
        sdate.setText("");
        edate.setText("");
        aproduct.setText("");
        amount.setText("");
        searchbox1.setText("");
    }
   
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        dtable = new javax.swing.JTable();
        addbtn = new javax.swing.JButton();
        updatebtn = new javax.swing.JButton();
        deletebtn = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        name = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        amount = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        sdate = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        edate = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        aproduct = new javax.swing.JTextField();
        searchbox1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jComboBox1 = new javax.swing.JComboBox<>();

        dtable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Name", "Start Date", "End Date", "Applicable Products", "Eligible Customers", "Amount"
            }
        ));
        dtable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                dtableMouseReleased(evt);
            }
        });
        dtable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                dtableKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(dtable);

        addbtn.setBackground(new java.awt.Color(131, 115, 191));
        addbtn.setText("Insert");
        addbtn.setMinimumSize(new java.awt.Dimension(75, 28));
        addbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addbtnActionPerformed(evt);
            }
        });

        updatebtn.setBackground(new java.awt.Color(131, 115, 191));
        updatebtn.setText("Update");
        updatebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatebtnActionPerformed(evt);
            }
        });

        deletebtn.setBackground(new java.awt.Color(131, 115, 191));
        deletebtn.setText("Delete");
        deletebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletebtnActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Name :");

        name.setToolTipText("Enter Discount Name Here");
        name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Amount :");

        amount.setToolTipText("Enter Discount Amount Here");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Start date :");

        sdate.setToolTipText("Enter Starting Date Of Discount Here");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("  End date : ");

        edate.setToolTipText("Enter Closing Date Of Discount Here");
        edate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edateActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("Eligible customers :");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Applicable product id :");
        jLabel9.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jLabel9AncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        aproduct.setToolTipText("Search Products");
        aproduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aproductActionPerformed(evt);
            }
        });
        aproduct.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                aproductKeyReleased(evt);
            }
        });

        searchbox1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchbox1KeyReleased(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Search ID :");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel6.setText("Discounts Information");

        jButton1.setText("Search");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Get Product");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "ID", "Product"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Loylty", "Non-Loyalty" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(277, 277, 277)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(8, 8, 8)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel12)
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(layout.createSequentialGroup()
                                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                            .addGap(131, 131, 131)
                                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                .addGroup(layout.createSequentialGroup()
                                                                    .addComponent(searchbox1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(sdate, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(edate, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGroup(layout.createSequentialGroup()
                                                                    .addComponent(aproduct, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                    .addComponent(jButton2))))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(amount, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 487, Short.MAX_VALUE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(82, 82, 82)
                                .addComponent(addbtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(86, 86, 86)
                                .addComponent(updatebtn)
                                .addGap(97, 97, 97)
                                .addComponent(deletebtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 423, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(13, 13, 13))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(searchbox1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(sdate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(edate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(aproduct, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(amount, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(addbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(updatebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(deletebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void dtableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dtableMouseReleased

    }//GEN-LAST:event_dtableMouseReleased

    private void dtableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dtableKeyReleased

    }//GEN-LAST:event_dtableKeyReleased

    private void addbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addbtnActionPerformed
      if (!disctester()) {
          return;
      }else{
            try {
            
                String query = "INSERT INTO discount(dname, dstartdate, denddate, dproduct, dcusteli, dvalue) VALUES(?, ?, ?, ?, ?, ?)";
                PreparedStatement sql = Connections.connect().prepareStatement(query);

                sql.setString(1, name.getText());
                sql.setString(2, sdate.getText());
                sql.setString(3, edate.getText());
                sql.setString(4, aproduct.getText());
                sql.setString(5, jComboBox1.getSelectedItem().toString());
                sql.setString(6, amount.getText());
               
                sql.executeUpdate();
                clean();
                loaddata();
            } catch (Exception ex) {
                 ex.printStackTrace();
            }
      }
    }//GEN-LAST:event_addbtnActionPerformed

    private void updatebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updatebtnActionPerformed
      if(searchbox1.getText().isEmpty()){
        JOptionPane.showMessageDialog(null, "ID needed", "Warning", JOptionPane.WARNING_MESSAGE);  
      }else{
        if (!disctester()) {
          return;
        }
        try {
                String query = "UPDATE discount SET dname = ?, dstartdate = ?, denddate = ?, dproduct = ?, dcusteli = ?, dvalue = ? WHERE did = ?";
                PreparedStatement sql = Connections.connect().prepareStatement(query);

                sql.setString(1, name.getText());
                sql.setString(2, sdate.getText());
                sql.setString(3, edate.getText());
                sql.setString(4, aproduct.getText());
                sql.setString(5, jComboBox1.getSelectedItem().toString());
                sql.setString(6, amount.getText());
                sql.setString(7, searchbox1.getText());
               
                sql.executeUpdate();
                
                clean();
                loaddata();
            } catch (Exception ex) {
                 ex.printStackTrace();
            }
      }
    }//GEN-LAST:event_updatebtnActionPerformed

    private void deletebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletebtnActionPerformed
      if(searchbox1.getText().isEmpty()){
        JOptionPane.showMessageDialog(null, "ID needed", "Warning", JOptionPane.WARNING_MESSAGE);  
      }else{
        try {
                String query = "DELETE FROM discount WHERE did = ?";
                PreparedStatement sql = Connections.connect().prepareStatement(query);

                sql.setString(1, searchbox1.getText());
               
                sql.executeUpdate();
                
                clean();
                loaddata();
            } catch (Exception ex) {
                 ex.printStackTrace();
            }     
      }
    }//GEN-LAST:event_deletebtnActionPerformed

    private void nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameActionPerformed

    }//GEN-LAST:event_nameActionPerformed

    private void aproductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aproductActionPerformed

    }//GEN-LAST:event_aproductActionPerformed

    private void aproductKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_aproductKeyReleased

    }//GEN-LAST:event_aproductKeyReleased

    private void searchbox1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchbox1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_searchbox1KeyReleased

    private void edateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edateActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            
                String query = "SELECT * FROM  discount WHERE did = ?";
                PreparedStatement sql = Connections.connect().prepareStatement(query);

                sql.setString(1, searchbox1.getText());
                ResultSet result = sql.executeQuery();

                while (result.next()) {
                         name.setText(result.getString("dname"));
                         sdate.setText(result.getString("dstartdate"));
                         edate.setText(result.getString("denddate"));
                         aproduct.setText(result.getString("dproduct"));
                         jComboBox1.setSelectedItem(result.getString("dcusteli")); 
                         amount.setText(result.getString("dvalue")); 
              
                }
                
            } catch (Exception ex) {
                 ex.printStackTrace();
            }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(aproduct.getText() == null || aproduct.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Please enter a product ID", "Warning", JOptionPane.WARNING_MESSAGE);
        }else if(!Pattern.matches("^[0-9]$", aproduct.getText())) {
            JOptionPane.showMessageDialog(null, "Invalid product ID", "Warning", JOptionPane.WARNING_MESSAGE);
        }else{
            try {
            
                String query = "SELECT pname FROM  product WHERE pid = ?";
                PreparedStatement sql = Connections.connect().prepareStatement(query);

                sql.setString(1, aproduct.getText());
                ResultSet result = sql.executeQuery();

                while (result.next()) {
                    aproduct.setText("");
                    jLabel9.setText("Applicable product:");
                    aproduct.setText(result.getString("pname"));
                }
                
            } catch (Exception ex) {
                 ex.printStackTrace();
            }
        }    
    }//GEN-LAST:event_jButton2ActionPerformed


    private void jLabel9AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jLabel9AncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel9AncestorAdded


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addbtn;
    private javax.swing.JTextField amount;
    private javax.swing.JTextField aproduct;
    private javax.swing.JButton deletebtn;
    private javax.swing.JTable dtable;
    private javax.swing.JTextField edate;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField name;
    private javax.swing.JTextField sdate;
    private javax.swing.JTextField searchbox1;
    private javax.swing.JButton updatebtn;
    // End of variables declaration//GEN-END:variables
}
