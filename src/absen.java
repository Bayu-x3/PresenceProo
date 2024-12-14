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
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author BAYU CANDRA Y
 */
public class absen extends javax.swing.JFrame {

    Connection conn;

    public absen() {
        initComponents();
        connectDatabase();
        loadComboBoxes();
        loadAbsenTable();
    }

    private void connectDatabase() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_presencepro", "root", "");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database connection failed!");
        }
    }
    
     private void loadComboBoxes() {
        loadComboBoxData("pelajaran", "nama_pelajaran", inputJadwal); // Load jadwal from 'pelajaran' table
        loadComboBoxData("siswa", "nama_siswa", inputSiswa);
        loadComboBoxData("kelas", "nama_kelas", inpurKelas);
 // Load kelas from 'kelas' table
        // Load static status options if status is not part of a table
        Vector<String> statusItems = new Vector<>();
        statusItems.add("Hadir");
        statusItems.add("Tidak Hadir");
        statusItems.add("Sakit");
        statusItems.add("Izin");
        jComboBox4.setModel(new DefaultComboBoxModel<>(statusItems));

        // Load the first set of students after loading the kelas data
        filterStudentsByClass();
    }
     
    private void loadAbsenTable() {
    try {
        String query = "SELECT a.id_absensi, p.nama_pelajaran, s.nama_siswa, a.status, a.waktu_absensi "
                     + "FROM absensi a "
                     + "JOIN pelajaran p ON a.id_pelajaran = p.id_pelajaran "
                     + "JOIN siswa s ON a.id_siswa = s.id_siswa "
                     + "JOIN kelas k ON a.id_kelas = k.id_kelas";
        PreparedStatement pstmt = conn.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();

        DefaultTableModel model = (DefaultTableModel) tableAbsen.getModel();
        model.setRowCount(0); // Kosongkan tabel sebelum diisi ulang

        while (rs.next()) {
            model.addRow(new Object[] {
                rs.getInt("id_absensi"),
                rs.getString("nama_pelajaran"),
                rs.getString("nama_siswa"),
                rs.getString("status"),
                rs.getTimestamp("waktu_absensi").toString()
            });
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Terjadi kesalahan saat memuat data tabel!");
    }
}

     
private void tableAbsenMouseClicked(java.awt.event.MouseEvent evt) {                                        
    int selectedRow = tableAbsen.getSelectedRow();
    if (selectedRow != -1) {
        inputJadwal.setSelectedItem(tableAbsen.getValueAt(selectedRow, 1)); // Nama Pelajaran
        inputSiswa.setSelectedItem(tableAbsen.getValueAt(selectedRow, 2));  // Nama Siswa
        jComboBox4.setSelectedItem(tableAbsen.getValueAt(selectedRow, 3));  // Status
    }
}




    private void loadComboBoxData(String tableName, String columnName, JComboBox<String> comboBox) {
        try {
            String query = "SELECT * FROM " + tableName;
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            Vector<String> items = new Vector<>();
            while (rs.next()) {
                items.add(rs.getString(columnName));
            }
            comboBox.setModel(new DefaultComboBoxModel<>(items));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void filterStudentsByClass() {
        try {
            String selectedClass = (String) inpurKelas.getSelectedItem();
            String query = "SELECT nama_siswa FROM siswa WHERE id_kelas = "
               + "(SELECT id_kelas FROM kelas WHERE nama_kelas = ?)";
PreparedStatement pstmt = conn.prepareStatement(query);
pstmt.setString(1, selectedClass);

            ResultSet rs = pstmt.executeQuery();

            Vector<String> studentNames = new Vector<>();
            while (rs.next()) {
                studentNames.add(rs.getString("nama_siswa"));
            }
            inputSiswa.setModel(new DefaultComboBoxModel<>(studentNames));
        } catch (SQLException ex) {
            ex.printStackTrace();
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

        inputJadwal = new javax.swing.JComboBox<>();
        inputSiswa = new javax.swing.JComboBox<>();
        inpurKelas = new javax.swing.JComboBox<>();
        tambahButton = new rojerusan.RSMaterialButtonRectangle();
        jComboBox4 = new javax.swing.JComboBox<>();
        editButton = new rojerusan.RSMaterialButtonRectangle();
        deleteButton = new rojerusan.RSMaterialButtonRectangle();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableAbsen = new javax.swing.JTable();
        rSMaterialButtonRectangle1 = new rojerusan.RSMaterialButtonRectangle();
        inputStatus = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        inputJadwal.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        inputJadwal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputJadwalActionPerformed(evt);
            }
        });
        getContentPane().add(inputJadwal, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, 280, 60));

        inputSiswa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        inputSiswa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputSiswaActionPerformed(evt);
            }
        });
        getContentPane().add(inputSiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 430, 280, 60));

        inpurKelas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        inpurKelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inpurKelasActionPerformed(evt);
            }
        });
        getContentPane().add(inpurKelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 310, 280, 60));

        tambahButton.setBackground(new java.awt.Color(0, 255, 0));
        tambahButton.setBorder(null);
        tambahButton.setText("tambah");
        tambahButton.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 18)); // NOI18N
        tambahButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahButtonActionPerformed(evt);
            }
        });
        getContentPane().add(tambahButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 610, 110, 50));

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        getContentPane().add(jComboBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 550, 290, 50));

        editButton.setBorder(null);
        editButton.setText("edit");
        editButton.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 18)); // NOI18N
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });
        getContentPane().add(editButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 610, 110, 50));

        deleteButton.setBackground(new java.awt.Color(255, 0, 0));
        deleteButton.setBorder(null);
        deleteButton.setText("delete");
        deleteButton.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 18)); // NOI18N
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });
        getContentPane().add(deleteButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 610, 110, 50));

        tableAbsen.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Pelajaran", "Nama Siswa", "Status", "Waktu Absensi"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableAbsen);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 150, 640, 450));

        rSMaterialButtonRectangle1.setBackground(new java.awt.Color(0, 0, 255));
        rSMaterialButtonRectangle1.setText("KEMBALI");
        rSMaterialButtonRectangle1.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 18)); // NOI18N
        rSMaterialButtonRectangle1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonRectangle1ActionPerformed(evt);
            }
        });
        getContentPane().add(rSMaterialButtonRectangle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 670, 120, 60));

        inputStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui_login/Absensi.png"))); // NOI18N
        getContentPane().add(inputStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, -1, 790));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void inputJadwalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputJadwalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputJadwalActionPerformed

    private void tambahButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahButtonActionPerformed
           try {
        String jadwal = (String) inputJadwal.getSelectedItem();
        String siswa = (String) inputSiswa.getSelectedItem();
        String kelas = (String) inpurKelas.getSelectedItem();
        String status = (String) jComboBox4.getSelectedItem();

        String query = "INSERT INTO absensi (id_pelajaran, id_siswa, id_kelas, status) VALUES "
                + "((SELECT id_pelajaran FROM pelajaran WHERE nama_pelajaran = ?), "
                + "(SELECT id_siswa FROM siswa WHERE nama_siswa = ?), "
                + "(SELECT id_kelas FROM kelas WHERE nama_kelas = ?), ?)";

        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, jadwal);
        pstmt.setString(2, siswa);
        pstmt.setString(3, kelas);
        pstmt.setString(4, status);
        pstmt.executeUpdate();

        JOptionPane.showMessageDialog(this, "Data berhasil ditambahkan!");

        loadAbsenTable(); // Perbarui tabel setelah menambahkan data
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Terjadi kesalahan saat menambahkan data!");
    }
    }//GEN-LAST:event_tambahButtonActionPerformed

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        try {
        int selectedRow = tableAbsen.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih baris yang ingin diubah!");
            return;
        }

        int idAbsensi = (int) tableAbsen.getValueAt(selectedRow, 0); // Ambil ID Absensi
        String status = (String) jComboBox4.getSelectedItem();  // Status baru

        String query = "UPDATE absensi SET status = ? WHERE id_absensi = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, status);
        pstmt.setInt(2, idAbsensi);
        pstmt.executeUpdate();

        JOptionPane.showMessageDialog(this, "Data berhasil diubah!");

        loadAbsenTable(); // Perbarui tabel setelah mengedit data
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Terjadi kesalahan saat mengubah data!");
    }
    }//GEN-LAST:event_editButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        try {
        int selectedRow = tableAbsen.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih baris yang ingin dihapus!");
            return;
        }

        int idAbsensi = (int) tableAbsen.getValueAt(selectedRow, 0); // Ambil ID Absensi

        String query = "DELETE FROM absensi WHERE id_absensi = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setInt(1, idAbsensi);
        pstmt.executeUpdate();

        JOptionPane.showMessageDialog(this, "Data berhasil dihapus!");

        loadAbsenTable(); // Perbarui tabel setelah menghapus data
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Terjadi kesalahan saat menghapus data!");
    }
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void inpurKelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inpurKelasActionPerformed
     String selectedClass = (String) inpurKelas.getSelectedItem();
        // Reload students based on the selected class
        filterStudentsByClass();
    }//GEN-LAST:event_inpurKelasActionPerformed

    private void inputSiswaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputSiswaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputSiswaActionPerformed

    private void rSMaterialButtonRectangle1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle1ActionPerformed
        new guru().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_rSMaterialButtonRectangle1ActionPerformed

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
            java.util.logging.Logger.getLogger(absen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(absen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(absen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(absen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new absen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojerusan.RSMaterialButtonRectangle deleteButton;
    private rojerusan.RSMaterialButtonRectangle editButton;
    private javax.swing.JComboBox<String> inpurKelas;
    private javax.swing.JComboBox<String> inputJadwal;
    private javax.swing.JComboBox<String> inputSiswa;
    private javax.swing.JLabel inputStatus;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JScrollPane jScrollPane1;
    private rojerusan.RSMaterialButtonRectangle rSMaterialButtonRectangle1;
    private javax.swing.JTable tableAbsen;
    private rojerusan.RSMaterialButtonRectangle tambahButton;
    // End of variables declaration//GEN-END:variables

}
