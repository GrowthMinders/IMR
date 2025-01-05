/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package pos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.util.regex.Pattern;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
/**
 *
 * @author Supun
 */
public class Product extends javax.swing.JPanel {

    /**
     * Creates new form Product
     */
    public Product() {
        initComponents();
        loaddata();
    }
    
    private void loaddata() {
      try {

        String query = "SELECT * FROM product";
        PreparedStatement sql = Connections.connect().prepareStatement(query);

        ResultSet result = sql.executeQuery();
        

        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);

        while (result.next()) {
            Object[] row = {
                result.getInt("pid"),
                result.getString("pname"),
                result.getString("pstock"),
                result.getString("price"),
                result.getString("pcategory"),
                result.getString("pbatchno"),
                result.getString("pexpiry"),
                result.getString("psupply"),
            };
            
          
            model.addRow(row);
        }

      } catch (Exception ex) {
        ex.printStackTrace();
      }
      
      
      try{
          String query = "SELECT sname FROM supplier";
          PreparedStatement sql = Connections.connect().prepareStatement(query);

          ResultSet result = sql.executeQuery();
        
        
          DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
          comSupplier.setModel(comboBoxModel);
          
          while (result.next()) {
            comboBoxModel.addElement(result.getString("sname"));
          }  

      }catch (Exception ex) {
        ex.printStackTrace();
      }
}

    
    public boolean testpro(){
       if(txtName.getText() == null || txtName.getText().isEmpty()) {
           JOptionPane.showMessageDialog(null, "Please enter a product name", "Warning", JOptionPane.WARNING_MESSAGE);
           return false;
       }else if(txtQty.getText() == null || txtQty.getText().isEmpty()) {
           JOptionPane.showMessageDialog(null, "Please enter the total quantity", "Warning", JOptionPane.WARNING_MESSAGE);
           return false;
       }else if(txtPrice.getText() == null || txtPrice.getText().isEmpty()) {
           JOptionPane.showMessageDialog(null, "Please enter a product price", "Warning", JOptionPane.WARNING_MESSAGE);
           return false;
       }else if(txtCategory.getText() == null || txtCategory.getText().isEmpty()) {
           JOptionPane.showMessageDialog(null, "Please enter a product category", "Warning", JOptionPane.WARNING_MESSAGE);
           return false;
       }else if(txtBatch.getText() == null || txtBatch.getText().isEmpty()) {
           JOptionPane.showMessageDialog(null, "Please enter a product batch number", "Warning", JOptionPane.WARNING_MESSAGE);
           return false;
       }else if(txtExpiry.getText() == null || txtExpiry.getText().isEmpty()) {
           JOptionPane.showMessageDialog(null, "Please enter product expiry date", "Warning", JOptionPane.WARNING_MESSAGE);
           return false;
       }else if(!Pattern.matches("^[0-9]+$", txtQty.getText())) {
            JOptionPane.showMessageDialog(null, "Invalid quantity", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
       }else if(!Pattern.matches("^[0-9]+$", txtPrice.getText())) {
            JOptionPane.showMessageDialog(null, "Invalid price", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
       }else if(!Pattern.matches("^[A-Za-z\\s]+$", txtCategory.getText())) {
            JOptionPane.showMessageDialog(null, "Invalid category", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
       }else if(!Pattern.matches("^[0-9]{2}/[0-9]{2}/[0-9]{4}$", txtExpiry.getText())) {
            JOptionPane.showMessageDialog(null, "Expiry should be(DD/MM/YYYY)", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
       } 
       
       return true;
    }
    
    
    public void clean(){
       txtName.setText(""); 
       txtQty.setText("");
       txtPrice.setText("");
       txtCategory.setText("");
       txtBatch.setText("");
       txtExpiry.setText("");
       txtSearchID.setText("");
       comSupplier.setSelectedItem("");        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtSearchID = new java.awt.TextField();
        btnEdit = new javax.swing.JButton();
        label3 = new java.awt.Label();
        btnSave = new javax.swing.JButton();
        label4 = new java.awt.Label();
        btnDelete = new javax.swing.JButton();
        label5 = new java.awt.Label();
        btnSearch = new javax.swing.JButton();
        label6 = new java.awt.Label();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        label7 = new java.awt.Label();
        txtBatch = new java.awt.TextField();
        txtPrice = new java.awt.TextField();
        txtQty = new java.awt.TextField();
        txtName = new java.awt.TextField();
        label1 = new java.awt.Label();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtExpiry = new javax.swing.JTextField();
        comSupplier = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        txtCategory = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        txtSearchID.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N

        btnEdit.setLabel("Edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        label3.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        label3.setText("Batch No:");

        btnSave.setText("Insert");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        label4.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        label4.setText("Price:");

        btnDelete.setLabel("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        label5.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        label5.setText("Quantity:");

        btnSearch.setLabel("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        label6.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        label6.setText("Supplier:");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Product Name", "Stock", "Price", "Category", "Batchno", "Expiry", "Supplier"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        label7.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        label7.setText("Search ID");

        txtBatch.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N

        txtPrice.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N

        txtQty.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N

        txtName.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N

        label1.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        label1.setText("Name:");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setText("Product Information");

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel2.setText("Expiry");

        comSupplier.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel3.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel3.setText("Category:");

        jButton1.setText("Re-Order Quantity");
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
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                        .addComponent(txtQty, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(label7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtSearchID, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(label6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(comSupplier, 0, 302, Short.MAX_VALUE)
                            .addComponent(txtExpiry)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtBatch, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
                                .addComponent(txtCategory))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(66, 66, 66)
                                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(46, 46, 46)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 676, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(261, 261, 261)
                .addComponent(jButton1)
                .addGap(55, 55, 55))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(label7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtSearchID, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSearch, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtQty, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtBatch, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtExpiry, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(label6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(comSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(44, 44, 44)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 562, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
       if(txtSearchID.getText() == null || txtSearchID.getText().isEmpty()) {
         JOptionPane.showMessageDialog(null, "ID needed", "Warning", JOptionPane.WARNING_MESSAGE);
       }else{
         if (!testpro()) {
          return;
         }
            try {
                String query = "UPDATE product SET pname = ?, pstock = ?, price = ?, pcategory = ?, pbatchno = ?, pexpiry = ?, psupply = ? WHERE pid = ?";
                PreparedStatement sql = Connections.connect().prepareStatement(query);
                
                sql.setString(1, txtName.getText());
                sql.setString(2, txtQty.getText());
                sql.setString(3, txtPrice.getText());
                sql.setString(4, txtCategory.getText());
                sql.setString(5, txtBatch.getText());
                sql.setString(6, txtExpiry.getText());
                sql.setString(7, comSupplier.getSelectedItem().toString());
                sql.setString(8, txtSearchID.getText());

                
                sql.executeUpdate();
                clean();
                loaddata();
            } catch (Exception ex) {
                 ex.printStackTrace();
            }

         clean();
       }
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        if (!testpro()) {
          return;
        }else{
            try {
                String query = "INSERT INTO product (pname, pstock, price, pcategory, pbatchno, pexpiry, psupply) VALUES(?,?,?,?,?,?,?)";
                PreparedStatement sql = Connections.connect().prepareStatement(query);
                
                sql.setString(1, txtName.getText());
                sql.setString(2, txtQty.getText());
                sql.setString(3, txtPrice.getText());
                sql.setString(4, txtCategory.getText());
                sql.setString(5, txtBatch.getText());
                sql.setString(6, txtExpiry.getText());
                sql.setString(7, comSupplier.getSelectedItem().toString());

                
                sql.executeUpdate();
                clean();
                loaddata();
            } catch (Exception ex) {
                 ex.printStackTrace();
            }
        }    
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        if(txtSearchID.getText() == null || txtSearchID.getText().isEmpty()) {        
          JOptionPane.showMessageDialog(null, "ID needed", "Warning", JOptionPane.WARNING_MESSAGE);
        }else{
          try {
                String query = "DELETE FROM product WHERE pid = ?";
                PreparedStatement sql = Connections.connect().prepareStatement(query);
                
                sql.setString(1, txtSearchID.getText());

                sql.executeUpdate();
                clean();
                loaddata();
            } catch (Exception ex) {
                 ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        try {
                String query = "SELECT * FROM product WHERE pid = ?";
                PreparedStatement sql = Connections.connect().prepareStatement(query);

                sql.setString(1, txtSearchID.getText());
                ResultSet result = sql.executeQuery();

                while (result.next()) {
                         
                         txtName.setText(result.getString("pname"));
                         txtQty.setText(result.getString("pstock"));
                         txtPrice.setText(result.getString("price"));
                         txtCategory.setText(result.getString("pcategory"));
                         txtBatch.setText(result.getString("pbatchno"));
                         txtExpiry.setText(result.getString("pexpiry"));
                         comSupplier.setSelectedItem(result.getString("psupply"));

                }

            } catch (Exception ex) {
                 ex.printStackTrace();
            }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        JFrame frame = new JFrame("Super Mart");

        // Create an instance of your JPanel
        Reorder order = new Reorder();
        
        // Add the JPanel to the JFrame
        frame.add(order);

        // Set size and make it visible
        frame.setSize(627, 747); // Adjust size as needed
        frame.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSearch;
    private javax.swing.JComboBox<String> comSupplier;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private java.awt.Label label1;
    private java.awt.Label label3;
    private java.awt.Label label4;
    private java.awt.Label label5;
    private java.awt.Label label6;
    private java.awt.Label label7;
    private java.awt.TextField txtBatch;
    private javax.swing.JTextField txtCategory;
    private javax.swing.JTextField txtExpiry;
    private java.awt.TextField txtName;
    private java.awt.TextField txtPrice;
    private java.awt.TextField txtQty;
    private java.awt.TextField txtSearchID;
    // End of variables declaration//GEN-END:variables
}
