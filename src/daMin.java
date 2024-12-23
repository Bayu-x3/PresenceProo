/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;


/**
 *
  @author user
 */
public class daMin extends javax.swing.JFrame {

    private Connection conn;

    public daMin() {
    initComponents();
    connectToDatabase();
    loadDataToTable();

    // Tambahkan ini di constructor daMin
    tableAdmin.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            tableAdminMouseClicked(evt);
        }
    });
}

// Tambahkan metode ini ke class daMin
private void tableAdminMouseClicked(java.awt.event.MouseEvent evt) {
    int selectedRow = tableAdmin.getSelectedRow();
    if (selectedRow != -1) {
        String nama = tableAdmin.getValueAt(selectedRow, 1).toString();
        String username = tableAdmin.getValueAt(selectedRow, 2).toString();
        String password = tableAdmin.getValueAt(selectedRow, 3).toString();
        
        inputNama.setText(nama);
        inputUser.setText(username);
        inputPass.setText(password);
    }
}

      private void connectToDatabase() {
        try {
            String url = "jdbc:mysql://localhost:3306/db_presencepro";
            String user = "root"; // Sesuaikan dengan username database Anda
            String password = ""; // Sesuaikan dengan password database Anda

            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Koneksi ke database berhasil!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Koneksi ke database gagal!");
        }
    }

   private void loadDataToTable() {
    DefaultTableModel model = (DefaultTableModel) tableAdmin.getModel();
    model.setRowCount(0); // Mengosongkan tabel sebelum memuat data baru

    try {
        // Modifikasi query SQL untuk hanya mengambil data dengan hak_akses = 'admin'
        String query = "SELECT id_admin, nama, username, password FROM admin";
        PreparedStatement pstmt = conn.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            int id_admin = rs.getInt("id_admin");
            String nama = rs.getString("nama");
            String username = rs.getString("username");
            String password = rs.getString("password");

            model.addRow(new Object[]{id_admin, nama, username, password});
        }

    } catch (Exception e) {
        e.printStackTrace();
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

        jScrollPane1 = new javax.swing.JScrollPane();
        tableAdmin = new javax.swing.JTable();
        inputNama = new javax.swing.JTextField();
        inputUser = new javax.swing.JTextField();
        inputPass = new javax.swing.JTextField();
        deleteButton = new rojerusan.RSMaterialButtonRectangle();
        tambahButton = new rojerusan.RSMaterialButtonRectangle();
        editButton = new rojerusan.RSMaterialButtonRectangle();
        kembaliButton = new rojerusan.RSMaterialButtonRectangle();
        batalButton = new rojerusan.RSMaterialButtonRectangle();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tableAdmin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "nama", "username", "password"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableAdmin);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 180, 590, 450));

        inputNama.setBackground(new java.awt.Color(51, 52, 55));
        inputNama.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        inputNama.setForeground(new java.awt.Color(255, 255, 255));
        inputNama.setBorder(null);
        inputNama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputNamaActionPerformed(evt);
            }
        });
        getContentPane().add(inputNama, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, 280, 50));

        inputUser.setBackground(new java.awt.Color(51, 52, 55));
        inputUser.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        inputUser.setForeground(new java.awt.Color(255, 255, 255));
        inputUser.setBorder(null);
        getContentPane().add(inputUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 310, 280, 50));

        inputPass.setBackground(new java.awt.Color(51, 52, 55));
        inputPass.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        inputPass.setForeground(new java.awt.Color(255, 255, 255));
        inputPass.setBorder(null);
        getContentPane().add(inputPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 430, 280, 50));

        deleteButton.setBackground(new java.awt.Color(255, 0, 0));
        deleteButton.setBorder(null);
        deleteButton.setText("Delete");
        deleteButton.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 18)); // NOI18N
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });
        getContentPane().add(deleteButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 510, 90, 50));

        tambahButton.setBackground(new java.awt.Color(0, 255, 0));
        tambahButton.setBorder(null);
        tambahButton.setText("tambah");
        tambahButton.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 18)); // NOI18N
        tambahButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahButtonActionPerformed(evt);
            }
        });
        getContentPane().add(tambahButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 510, 90, 50));

        editButton.setBorder(null);
        editButton.setText("edit");
        editButton.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 18)); // NOI18N
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });
        getContentPane().add(editButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 510, 90, 50));

        kembaliButton.setBackground(new java.awt.Color(0, 8, 250));
        kembaliButton.setText("Kembali");
        kembaliButton.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 18)); // NOI18N
        kembaliButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kembaliButtonActionPerformed(evt);
            }
        });
        getContentPane().add(kembaliButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 570, 130, 60));

        batalButton.setBackground(new java.awt.Color(1, 94, 1));
        batalButton.setText("batal");
        batalButton.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 18)); // NOI18N
        batalButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                batalButtonActionPerformed(evt);
            }
        });
        getContentPane().add(batalButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 570, 130, 60));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui_login/DataAdmin.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void inputNamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputNamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputNamaActionPerformed

    private void tambahButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahButtonActionPerformed
    String nama = inputNama.getText();
    String username = inputUser.getText();
    String password = inputPass.getText();
    
    if (nama.isEmpty() || username.isEmpty() || password.isEmpty()) {
        javax.swing.JOptionPane.showMessageDialog(this, "All fields must be filled out.");
        return; // Prevent the operation if validation fails
    }
    
    try {
        String query = "INSERT INTO admin (nama, username, password) VALUES (?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, nama);
        pstmt.setString(2, username);
        pstmt.setString(3, password);
        pstmt.executeUpdate();
        
        loadDataToTable(); 
        inputNama.setText("");
        inputUser.setText("");
        inputPass.setText("");
    } catch (Exception e) {
        e.printStackTrace();
    }
    }//GEN-LAST:event_tambahButtonActionPerformed

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        int selectedRow = tableAdmin.getSelectedRow();
    if (selectedRow != -1) {
        int id_admin = (int) tableAdmin.getValueAt(selectedRow, 0);
        String nama = inputNama.getText();
        String username = inputUser.getText();
        String password = inputPass.getText();
        
        if (nama.isEmpty() || username.isEmpty() || password.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "All fields must be filled out.");
            return; // Prevent the operation if validation fails
        }

        
        try {
            String query = "UPDATE admin SET nama = ?, username = ?, password = ? WHERE id_admin = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, nama);
            pstmt.setString(2, username);
            pstmt.setString(3, password);
            pstmt.setInt(4, id_admin);
            pstmt.executeUpdate();

            loadDataToTable(); // Reload the table data
            inputNama.setText("");
            inputUser.setText("");
            inputPass.setText("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    } else {
        // Handle case when no row is selected
        javax.swing.JOptionPane.showMessageDialog(this, "Please select a row to edit.");
    }
    }//GEN-LAST:event_editButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
         int selectedRow = tableAdmin.getSelectedRow();
    if (selectedRow != -1) {
        int id_admin = (int) tableAdmin.getValueAt(selectedRow, 0);

        try {
            String query = "DELETE FROM admin WHERE id_admin = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id_admin);
            pstmt.executeUpdate();

            loadDataToTable(); // Reload the table data
        } catch (Exception e) {
            e.printStackTrace();
        }
    } else {
        // Handle case when no row is selected
        javax.swing.JOptionPane.showMessageDialog(this, "Please select a row to delete.");
    }
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void batalButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_batalButtonActionPerformed
       inputNama.setText("");
        inputPass.setText("");
        inputUser.setText("");
    }//GEN-LAST:event_batalButtonActionPerformed

    private void kembaliButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kembaliButtonActionPerformed
      new admin().setVisible(true); // Open the admin window
      this.dispose(); // Close the current window
    }//GEN-LAST:event_kembaliButtonActionPerformed


    
    
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
            java.util.logging.Logger.getLogger(daMin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(daMin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(daMin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(daMin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new daMin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojerusan.RSMaterialButtonRectangle batalButton;
    private rojerusan.RSMaterialButtonRectangle deleteButton;
    private rojerusan.RSMaterialButtonRectangle editButton;
    private javax.swing.JTextField inputNama;
    private javax.swing.JTextField inputPass;
    private javax.swing.JTextField inputUser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private rojerusan.RSMaterialButtonRectangle kembaliButton;
    private javax.swing.JTable tableAdmin;
    private rojerusan.RSMaterialButtonRectangle tambahButton;
    // End of variables declaration//GEN-END:variables
}
