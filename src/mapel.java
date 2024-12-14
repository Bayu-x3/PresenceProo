/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

import java.awt.event.MouseEvent;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author BAYU CANDRA Y
 */
public class mapel extends javax.swing.JFrame {

    private Connection conn;
    /**
     * Creates new form mapel
     */
    public mapel() {
    initComponents();
    connectToDatabase();
    loadDataToTable();
    
   // Listener untuk klik tabel
        tableMapel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMapelMouseClicked(evt);
            }

        private void tableMapelMouseClicked(MouseEvent evt) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
        });
    }
    
    private void tableMapelMouseClicked(java.awt.event.MouseEvent evt) {
        int selectedRow = tableMapel.getSelectedRow();
        if (selectedRow != -1) {
            String nama_pelajaran = tableMapel.getValueAt(selectedRow, 1).toString();
            inputMapel.setText(nama_pelajaran);
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
    DefaultTableModel model = (DefaultTableModel) tableMapel.getModel();
    model.setRowCount(0); // Mengosongkan tabel sebelum memuat data baru

    try {
        // Modifikasi query SQL untuk hanya mengambil data dengan hak_akses = 'admin'
        String query = "SELECT id_pelajaran, nama_pelajaran FROM pelajaran";
        PreparedStatement pstmt = conn.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            int id_pelajaran = rs.getInt("id_pelajaran");
            String nama_pelajaran = rs.getString("nama_pelajaran");

            model.addRow(new Object[]{id_pelajaran, nama_pelajaran});
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

        inputMapel = new javax.swing.JTextField();
        backButton = new rojerusan.RSMaterialButtonRectangle();
        editButton = new rojerusan.RSMaterialButtonRectangle();
        deleteButton = new rojerusan.RSMaterialButtonRectangle();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableMapel = new javax.swing.JTable();
        tambahButton1 = new rojerusan.RSMaterialButtonRectangle();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        inputMapel.setBackground(new java.awt.Color(51, 52, 55));
        inputMapel.setBorder(null);
        inputMapel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputMapelActionPerformed(evt);
            }
        });
        getContentPane().add(inputMapel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, 300, 100));

        backButton.setBackground(new java.awt.Color(0, 51, 255));
        backButton.setBorder(null);
        backButton.setText("KEMBALI");
        backButton.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 18)); // NOI18N
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });
        getContentPane().add(backButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 570, 110, 50));

        editButton.setBorder(null);
        editButton.setText("edit");
        editButton.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 18)); // NOI18N
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });
        getContentPane().add(editButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 510, 110, 50));

        deleteButton.setBackground(new java.awt.Color(255, 0, 0));
        deleteButton.setBorder(null);
        deleteButton.setText("Delete");
        deleteButton.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 18)); // NOI18N
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });
        getContentPane().add(deleteButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 510, 110, 50));

        tableMapel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "ID", "NAMA PELAJARAN"
            }
        ));
        jScrollPane1.setViewportView(tableMapel);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 150, 600, 450));

        tambahButton1.setBackground(new java.awt.Color(0, 255, 0));
        tambahButton1.setBorder(null);
        tambahButton1.setText("tambah");
        tambahButton1.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 18)); // NOI18N
        tambahButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(tambahButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 510, 110, 50));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui_login/MataPelajaran.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        new admin().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backButtonActionPerformed

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        int selectedRow = tableMapel.getSelectedRow();
        if (selectedRow != -1) {
            int id_pelajaran = (int) tableMapel.getValueAt(selectedRow, 0);
            String nama_pelajaran = inputMapel.getText();

            if (nama_pelajaran.isEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(this, "All fields must be filled out.");
                return; // Prevent the operation if validation fails
            }

            try {
                String query = "UPDATE pelajaran SET nama_pelajaran = ? WHERE id_pelajaran = ?";
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setString(1, nama_pelajaran);
                pstmt.setInt(2, id_pelajaran);
                pstmt.executeUpdate();

                loadDataToTable(); // Reload the table data
                inputMapel.setText("");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            // Handle case when no row is selected
            javax.swing.JOptionPane.showMessageDialog(this, "Please select a row to edit.");
        }
    }//GEN-LAST:event_editButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        int selectedRow = tableMapel.getSelectedRow();
        if (selectedRow != -1) {
            int id_pelajaran = (int) tableMapel.getValueAt(selectedRow, 0);

            try {
                String query = "DELETE FROM pelajaran WHERE id_pelajaran = ?";
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setInt(1, id_pelajaran);
                pstmt.executeUpdate();

                loadDataToTable();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "Pilih baris yang ingin dihapus.");
        }
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void inputMapelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputMapelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputMapelActionPerformed

    private void tambahButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahButton1ActionPerformed
         String nama_pelajaran = inputMapel.getText();

        if (nama_pelajaran.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "All fields must be filled out.");
            return; // Prevent the operation if validation fails
        }

        try {
            String query = "INSERT INTO pelajaran (nama_pelajaran) VALUES (?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, nama_pelajaran);
            pstmt.executeUpdate();

            loadDataToTable();
            inputMapel.setText("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_tambahButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(mapel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mapel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mapel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mapel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mapel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojerusan.RSMaterialButtonRectangle backButton;
    private rojerusan.RSMaterialButtonRectangle deleteButton;
    private rojerusan.RSMaterialButtonRectangle editButton;
    private javax.swing.JTextField inputMapel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableMapel;
    private rojerusan.RSMaterialButtonRectangle tambahButton1;
    // End of variables declaration//GEN-END:variables
}
