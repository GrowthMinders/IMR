/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package pos;

import java.sql.PreparedStatement;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import org.mindrot.jbcrypt.BCrypt;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Supun
 */
public class Employee extends javax.swing.JPanel {

    /**
     * Creates new form Employee
     */
    public Employee() {
        initComponents();
        loaddata();
    }
    
    private void loaddata(){
         try {
                String query = "SELECT * FROM employee emp LEFT JOIN employee_mobile emob ON emp.eid = emob.e_id";
                PreparedStatement sql = Connections.connect().prepareStatement(query);

                ResultSet result = sql.executeQuery();
                
                DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                model.setRowCount(0);

                // Add rows from the database
                while (result.next()) {
                     Object[] row = {
                         result.getInt("eid"),
                         result.getString("efname"),
                         result.getString("elname"),
                         result.getString("epassword"),
                         result.getString("egender"),
                         result.getString("eemail"),
                         result.getString("etype"),
                         result.getString("etel"),
                         result.getString("etel1")
                     };
                   model.addRow(row);              
                }

            } catch (Exception ex) {
                 ex.printStackTrace();
            }

    }
    
    String password = "";
    public boolean testemp(){
        
        char[] passarray = jPasswordField1.getPassword();
        password = new String(passarray);
        
        if (jTextField1.getText() == null || jTextField1.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a first name", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }else if(jTextField2.getText() == null || jTextField2.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a last name", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }else if(password == null || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a password", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }else if(jTextField3.getText() == null || jTextField3.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter an email", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }else if(jTextField5.getText() == null || jTextField5.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a telephone number", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }else if(!Pattern.matches("^[A-Za-z]{3,50}$", jTextField1.getText())) {
            JOptionPane.showMessageDialog(null, "Invalid first name", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }else if(!Pattern.matches("^[A-Za-z]{3,50}$", jTextField2.getText())) {
            JOptionPane.showMessageDialog(null, "Invalid last name", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }else if(!Pattern.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$%^&*_#+=`~{}':;|<>,.])[A-Za-z\\d@$%^&*_#+=`~{}':;|<>,.]{8,24}$", password)) {
            JOptionPane.showMessageDialog(null, "Password must include uppercase, lowercase, digits, special characters, and be 8-24 characters", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }else if(!Pattern.matches("^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$", jTextField3.getText())) {
            JOptionPane.showMessageDialog(null, "Invalid Email", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }else if(Pattern.matches("^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$", jTextField3.getText())){
            try {
                    String query = "SELECT eemail FROM employee WHERE eemail = ? ";
                    PreparedStatement sql = Connections.connect().prepareStatement(query);
                
                    sql.setString(1, jTextField3.getText());
                    ResultSet result = sql.executeQuery();

                    if(result.next()){
                       JOptionPane.showMessageDialog(null, "These email is already registered", "Warning", JOptionPane.WARNING_MESSAGE);
                       return false;
                    }
              
            }catch (Exception ex){
                    ex.printStackTrace();
            }
        }else if(!Pattern.matches("^[0-9]{10}$", jTextField5.getText())) {
            JOptionPane.showMessageDialog(null, "Invalid telephone number", "Warning", JOptionPane.WARNING_MESSAGE);
        }else if(Pattern.matches("^[0-9]{10}$", jTextField5.getText())){
            try {
                    String query = "SELECT etel, etel1 FROM employee_mobile WHERE etel = ? OR etel1 = ?";
                    PreparedStatement sql = Connections.connect().prepareStatement(query);
                
                    sql.setString(1, jTextField5.getText());
                    sql.setString(2, jTextField5.getText());
                    ResultSet result = sql.executeQuery();

                    if(result.next()){
                       JOptionPane.showMessageDialog(null, "These telephone number is already registered", "Warning", JOptionPane.WARNING_MESSAGE); 
                       return false;
                    }
              

            }catch (Exception ex){
                    ex.printStackTrace();
            }
        }else if(!jTextField6.getText().isEmpty()){
              if(!Pattern.matches("^[0-9]{10}$", jTextField6.getText())) {
                 JOptionPane.showMessageDialog(null, "Invalid telephone number.", "Warning", JOptionPane.WARNING_MESSAGE);
              }else{
                 try {
                    String query = "SELECT etel, etel1 FROM employee_mobile WHERE etel = ? OR etel1 = ?";
                    PreparedStatement sql = Connections.connect().prepareStatement(query);
                
                    sql.setString(1, jTextField6.getText());
                    sql.setString(2, jTextField6.getText());
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
        jTextField1.setText("");
        jTextField2.setText("");
        jPasswordField1.setText("");
        jTextField3.setText("");
        jTextField5.setText("");
        jTextField6.setText("");
        jComboBox1.setSelectedItem("");
        jComboBox2.setSelectedItem("");
        search3.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        insert = new javax.swing.JButton();
        update = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        search3 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jPasswordField1 = new javax.swing.JPasswordField();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "First_Name", "Last_Name", "Password", "Gender", "Email", "Role", "Telephone 1", "Telephone 2"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable1MousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cashier", "Administrator" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jLabel9.setText("Telephone Number 2:");

        jLabel1.setText("First Name:");

        jLabel2.setText("Last Name:");

        jLabel3.setText("Password:");

        jLabel4.setText("Gender:");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel5.setText("Email:");

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jLabel6.setText("Role:");

        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jLabel15.setText("Telephone Number 1:");

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

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel7.setText("Employee Information");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Search Id:");

        search3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search3ActionPerformed(evt);
            }
        });
        search3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                search3KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                search3KeyTyped(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female", "Other" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jButton1.setText("Search");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPasswordField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordField1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(30, 30, 30)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel8))
                                .addGap(29, 29, 29)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(search3)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton1))))
                            .addComponent(jLabel5)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(35, 35, 35)
                                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addGap(35, 35, 35)
                                .addComponent(jTextField5))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(61, 61, 61)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(update)
                                .addGap(49, 49, 49)
                                .addComponent(insert)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(delete))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jComboBox1, 0, 228, Short.MAX_VALUE)
                                    .addComponent(jPasswordField1))))
                        .addGap(45, 45, 45)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 719, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(387, 387, 387)
                        .addComponent(jLabel7)))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel7)
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(search3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(jButton1))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(update)
                            .addComponent(insert)
                            .addComponent(delete)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(39, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
     
    }//GEN-LAST:event_jTable1MouseClicked

    private void insertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertActionPerformed
       if (!testemp()) {
          return;
       }else{
        
          try {
            // INSERTING EMPLOYEE DETAILS
            String pass = password;
            String hash = BCrypt.hashpw(pass, BCrypt.gensalt());
            String query = "INSERT INTO employee(efname, elname, epassword, egender, eemail, etype) VALUES(?, ?, ?, ?, ?, ?)";
            PreparedStatement sql = Connections.connect().prepareStatement(query);

            sql.setString(1, jTextField1.getText());
            sql.setString(2, jTextField2.getText());
            sql.setString(3, hash);
            sql.setString(4, jComboBox1.getSelectedItem().toString());
            sql.setString(5, jTextField3.getText());
            sql.setString(6, jComboBox2.getSelectedItem().toString());

            sql.executeUpdate();

            // GETTING THE ID TO INSERT TELEPHONE NUMBER
            String query1 = "SELECT eid FROM employee WHERE eemail = ?";
            PreparedStatement sql1 = Connections.connect().prepareStatement(query1);
            sql1.setString(1, jTextField3.getText());

            ResultSet result = sql1.executeQuery();
            String id = "";
            if (result.next()) {
               id = result.getString("eid");
            }

            // INSERTING TELEPHONE NUMBER
            String query2 = "INSERT INTO employee_mobile(e_id, etel, etel1) VALUES(?, ?, ?)";
            PreparedStatement sql2 = Connections.connect().prepareStatement(query2);

            sql2.setString(1, id);
            sql2.setString(2, jTextField5.getText());
            sql2.setString(3, jTextField6.getText());

            sql2.executeUpdate();
            clean();
            loaddata();
          } catch (Exception ex) {
               ex.printStackTrace();
          }
       }    

           
    }//GEN-LAST:event_insertActionPerformed

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
        if (search3.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "ID needed", "Warning", JOptionPane.WARNING_MESSAGE);
       } else {   
    try {
        String query = "UPDATE employee SET efname = ?, elname = ?, epassword = ?, egender = ?, eemail = ?, etype = ?  WHERE eid = ?";
        PreparedStatement sql = Connections.connect().prepareStatement(query);
        
        sql.setString(1, jTextField1.getText());
        sql.setString(2, jTextField2.getText());
        String pass = jPasswordField1.getText();
        if (!pass.startsWith("$2a$")) { 
            String hash = BCrypt.hashpw(pass, BCrypt.gensalt());
            sql.setString(3, hash);
        } else {
            sql.setString(3, pass);
        }
        sql.setString(4, jComboBox1.getSelectedItem().toString());
        sql.setString(5, jTextField3.getText());
        sql.setString(6, jComboBox2.getSelectedItem().toString());
        sql.setString(7, search3.getText());
        sql.executeUpdate();
        
        String query1 = "UPDATE employee_mobile SET etel = ?, etel1 = ?  WHERE e_id = (SELECT eid FROM employee WHERE eemail = ?)";
        PreparedStatement sql1 = Connections.connect().prepareStatement(query1);
        
        sql1.setString(1, jTextField5.getText());
        sql1.setString(2, jTextField6.getText());
        sql1.setString(3, jTextField3.getText());
        sql1.executeUpdate();
        
        clean();
        loaddata();
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    clean();
}

        
    }//GEN-LAST:event_updateActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        if (search3.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "ID needed", "Warning", JOptionPane.WARNING_MESSAGE);
        }else{
            try {
                
               String query2 = "DELETE FROM employee_mobile WHERE e_id = ?";
               PreparedStatement sql2 = Connections.connect().prepareStatement(query2);
               sql2.setString(1, search3.getText());
               sql2.executeUpdate();
                
                
               String query1 = "DELETE FROM employee WHERE eid = ?";
               PreparedStatement sql1 = Connections.connect().prepareStatement(query1);
               sql1.setString(1, search3.getText());
               sql1.executeUpdate();

               clean();
               loaddata();
            } catch (Exception ex) {
                 ex.printStackTrace();
            }

        }
    }//GEN-LAST:event_deleteActionPerformed

    private void search3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_search3ActionPerformed

    private void search3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_search3KeyReleased

    }//GEN-LAST:event_search3KeyReleased

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void search3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_search3KeyTyped
    
    }//GEN-LAST:event_search3KeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
            try {
                String query = "SELECT * FROM employee emp LEFT JOIN employee_mobile emob ON emp.eid = emob.e_id WHERE emp.eid = ?";
                PreparedStatement sql = Connections.connect().prepareStatement(query);

                sql.setString(1, search3.getText());
                ResultSet result = sql.executeQuery();
                

                // Add rows from the database
                while (result.next()) {
                         
                         jTextField1.setText(result.getString("efname"));
                         jTextField2.setText(result.getString("elname"));
                         jPasswordField1.setText(result.getString("epassword"));
                         jComboBox1.setSelectedItem(result.getString("egender"));
                         jTextField3.setText(result.getString("eemail"));
                         jComboBox2.setSelectedItem(result.getString("etype"));
                         jTextField5.setText(result.getString("etel"));
                         jTextField6.setText(result.getString("etel1")); 
              
                }

            } catch (Exception ex) {
                 ex.printStackTrace();
            }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MousePressed

    private void jPasswordField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordField1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton delete;
    private javax.swing.JButton insert;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField search3;
    private javax.swing.JButton update;
    // End of variables declaration//GEN-END:variables
}
