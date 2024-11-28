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
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author BAYU CANDRA Y
 */
public class jadwalGuru extends javax.swing.JFrame {

    private Connection conn;
    /**
     * Creates new form jadwalGuru
     */
   public jadwalGuru() {
        initComponents();
        connectToDatabase();
        loadDataToTable();
        loadGuruToComboBox();
        loadPelajaranToComboBox();
        loadKelasToComboBox();

        // Pengaturan JSpinner untuk input jam mulai dan selesai
        inputJamMulai.setModel(new javax.swing.SpinnerDateModel());
        inputJamSelesai.setModel(new javax.swing.SpinnerDateModel());

        // Mengatur format tampilan waktu pada JSpinner
        javax.swing.JSpinner.DateEditor timeEditorMulai = new javax.swing.JSpinner.DateEditor(inputJamMulai, "HH:mm");
        javax.swing.JSpinner.DateEditor timeEditorSelesai = new javax.swing.JSpinner.DateEditor(inputJamSelesai, "HH:mm");
        inputJamMulai.setEditor(timeEditorMulai);
        inputJamSelesai.setEditor(timeEditorSelesai);

        // Menambahkan event listener untuk tabel
        tableJadwal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableJadwalMouseClicked(evt);
            }
        });
    }
    
    private void loadGuruToComboBox() {
    try {
        String query = "SELECT id_guru, nama_guru FROM guru";
        PreparedStatement pstmt = conn.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();

        inputGuru.removeAllItems(); // Bersihkan item sebelumnya

        while (rs.next()) {
            String idGuru = rs.getString("id_guru");
            String namaGuru = rs.getString("nama_guru");
            inputGuru.addItem(idGuru + " - " + namaGuru); // Format: ID - Nama
        }
    } catch (Exception e) {
        e.printStackTrace();
        javax.swing.JOptionPane.showMessageDialog(this, "Gagal memuat data guru: " + e.getMessage());
    }
}

    private void loadPelajaranToComboBox() {
    try {
        String query = "SELECT id_pelajaran, nama_pelajaran FROM pelajaran";
        PreparedStatement pstmt = conn.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();

        inputPelajaran.removeAllItems();

        while (rs.next()) {
            String idPelajaran = rs.getString("id_pelajaran");
            String namaPelajaran = rs.getString("nama_pelajaran");
            inputPelajaran.addItem(idPelajaran + " - " + namaPelajaran);
        }
    } catch (Exception e) {
        e.printStackTrace();
        javax.swing.JOptionPane.showMessageDialog(this, "Gagal memuat data pelajaran: " + e.getMessage());
    }
}

    private void loadKelasToComboBox() {
    try {
        String query = "SELECT id_kelas, nama_kelas FROM kelas";
        PreparedStatement pstmt = conn.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();

        inputKelas.removeAllItems();

        while (rs.next()) {
            String idKelas = rs.getString("id_kelas");
            String namaKelas = rs.getString("nama_kelas");
            inputKelas.addItem(idKelas + " - " + namaKelas);
        }
    } catch (Exception e) {
        e.printStackTrace();
        javax.swing.JOptionPane.showMessageDialog(this, "Gagal memuat data kelas: " + e.getMessage());
    }
}

    private String getSelectedId(javax.swing.JComboBox<String> comboBox) {
    String selectedItem = comboBox.getSelectedItem().toString();
    return selectedItem.split(" - ")[0]; // Ambil bagian sebelum " - "
}

    
   private void tableJadwalMouseClicked(java.awt.event.MouseEvent evt) {                                         
    int selectedRow = tableJadwal.getSelectedRow(); // Mendapatkan baris yang diklik
    if (selectedRow != -1) {
        // Ambil data dari kolom-kolom tabel
        String idGuru = tableJadwal.getValueAt(selectedRow, 1).toString();
        String idPelajaran = tableJadwal.getValueAt(selectedRow, 2).toString();
        String idKelas = tableJadwal.getValueAt(selectedRow, 3).toString();
        String hari = tableJadwal.getValueAt(selectedRow, 4).toString();
        String jamMulai = tableJadwal.getValueAt(selectedRow, 5).toString();
        String jamSelesai = tableJadwal.getValueAt(selectedRow, 6).toString();

        // Set nilai dari data tabel ke dalam komponen input
        inputGuru.setSelectedItem(idGuru);
        inputPelajaran.setSelectedItem(idPelajaran);
        inputKelas.setSelectedItem(idKelas);
        inputHari.setText(hari);
        inputJamMulai.setValue(jamMulai);
        inputJamSelesai.setValue(jamSelesai);
    }
}

    
    private void loadDataToTable() {
    DefaultTableModel model = (DefaultTableModel) tableJadwal.getModel();
    model.setRowCount(0);

    try {
        String query = "SELECT j.id_jadwal, g.nama_guru, p.nama_pelajaran, k.nama_kelas, j.hari, j.jam_mulai, j.jam_selesai "
                     + "FROM jadwal j "
                     + "JOIN guru g ON j.id_guru = g.id_guru "
                     + "JOIN pelajaran p ON j.id_pelajaran = p.id_pelajaran "
                     + "JOIN kelas k ON j.id_kelas = k.id_kelas";
        PreparedStatement pstmt = conn.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            int idJadwal = rs.getInt("id_jadwal");
            String guru = rs.getString("nama_guru");
            String pelajaran = rs.getString("nama_pelajaran");
            String kelas = rs.getString("nama_kelas");
            String hari = rs.getString("hari");
            String jamMulai = rs.getString("jam_mulai");
            String jamSelesai = rs.getString("jam_selesai");

            model.addRow(new Object[]{idJadwal, guru, pelajaran, kelas, hari, jamMulai, jamSelesai});
        }
    } catch (Exception e) {
        e.printStackTrace();
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        inputPelajaran = new javax.swing.JComboBox<>();
        inputGuru = new javax.swing.JComboBox<>();
        inputKelas = new javax.swing.JComboBox<>();
        inputHari = new javax.swing.JTextField();
        inputJamSelesai = new javax.swing.JSpinner();
        inputJamMulai = new javax.swing.JSpinner();
        tambahButton = new rojerusan.RSMaterialButtonRectangle();
        editButton = new rojerusan.RSMaterialButtonRectangle();
        deleteButton = new rojerusan.RSMaterialButtonRectangle();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableJadwal = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        inputPelajaran.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        getContentPane().add(inputPelajaran, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 280, 30));

        inputGuru.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        inputGuru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputGuruActionPerformed(evt);
            }
        });
        getContentPane().add(inputGuru, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 280, 30));

        inputKelas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        inputKelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputKelasActionPerformed(evt);
            }
        });
        getContentPane().add(inputKelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, 290, 30));

        inputHari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputHariActionPerformed(evt);
            }
        });
        getContentPane().add(inputHari, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 380, 280, 30));
        getContentPane().add(inputJamSelesai, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 550, 290, 30));
        getContentPane().add(inputJamMulai, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 460, 290, 30));

        tambahButton.setBackground(new java.awt.Color(0, 255, 0));
        tambahButton.setBorder(null);
        tambahButton.setText("tambah");
        tambahButton.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 18)); // NOI18N
        tambahButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahButtonActionPerformed(evt);
            }
        });
        getContentPane().add(tambahButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 600, 90, 50));

        editButton.setBorder(null);
        editButton.setText("edit");
        editButton.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 18)); // NOI18N
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });
        getContentPane().add(editButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 600, 90, 50));

        deleteButton.setBackground(new java.awt.Color(255, 0, 0));
        deleteButton.setBorder(null);
        deleteButton.setText("Delete");
        deleteButton.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 18)); // NOI18N
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });
        getContentPane().add(deleteButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 600, 90, 50));

        tableJadwal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Guru", "Pelajaran", "Kelas", "Hari", "Jam Mulai", "Jam Selesai"
            }
        ));
        jScrollPane1.setViewportView(tableJadwal);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 160, 610, 450));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui_login/JadwalGuru.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1082, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tambahButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahButtonActionPerformed
    insertJadwal();
        loadDataToTable();
    }//GEN-LAST:event_tambahButtonActionPerformed

        private void insertJadwal() {
        try {
            String jamMulai = new SimpleDateFormat("HH:mm").format(inputJamMulai.getValue());
            String jamSelesai = new SimpleDateFormat("HH:mm").format(inputJamSelesai.getValue());

            String query = "INSERT INTO jadwal (id_guru, id_pelajaran, id_kelas, hari, jam_mulai, jam_selesai) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, getSelectedId(inputGuru));
            pstmt.setString(2, getSelectedId(inputPelajaran));
            pstmt.setString(3, getSelectedId(inputKelas));
            pstmt.setString(4, inputHari.getText());
            pstmt.setString(5, jamMulai);
            pstmt.setString(6, jamSelesai);

            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Jadwal berhasil ditambahkan!");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal menambahkan jadwal: " + e.getMessage());
        }
    }
    
    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
         int selectedRow = tableJadwal.getSelectedRow();
    if (selectedRow != -1) {
        int idJadwal = (int) tableJadwal.getValueAt(selectedRow, 0);
int idGuru = Integer.parseInt(getSelectedId(inputGuru));
int idPelajaran = Integer.parseInt(getSelectedId(inputPelajaran));
int idKelas = Integer.parseInt(getSelectedId(inputKelas));

        String hari = inputHari.getText();
        String jamMulai = new SimpleDateFormat("HH:mm").format(inputJamMulai.getValue());
        String jamSelesai = new SimpleDateFormat("HH:mm").format(inputJamSelesai.getValue());

        if (hari.isEmpty() || jamMulai.isEmpty() || jamSelesai.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Semua data harus diisi!");
            return;
        }

        try {
            String query = "UPDATE jadwal SET id_guru = ?, id_kelas = ?, id_pelajaran = ?, hari = ?, jam_mulai = ?, jam_selesai = ? WHERE id_jadwal = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, idGuru);
            pstmt.setInt(2, idKelas);
            pstmt.setInt(3, idPelajaran);
            pstmt.setString(4, hari);
            pstmt.setString(5, jamMulai);
            pstmt.setString(6, jamSelesai);
            pstmt.setInt(7, idJadwal);
            pstmt.executeUpdate();

            loadDataToTable();
            javax.swing.JOptionPane.showMessageDialog(this, "Jadwal berhasil diperbarui!");
        } catch (Exception e) {
            e.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    } else {
        javax.swing.JOptionPane.showMessageDialog(this, "Silakan pilih jadwal yang ingin diubah.");
    }
    }//GEN-LAST:event_editButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        int selectedRow = tableJadwal.getSelectedRow();
    if (selectedRow != -1) {
        int idJadwal = (int) tableJadwal.getValueAt(selectedRow, 0);

        try {
            String query = "DELETE FROM jadwal WHERE id_jadwal = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, idJadwal);
            pstmt.executeUpdate();

            loadDataToTable();
            javax.swing.JOptionPane.showMessageDialog(this, "Jadwal berhasil dihapus!");
        } catch (Exception e) {
            e.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    } else {
        javax.swing.JOptionPane.showMessageDialog(this, "Silakan pilih jadwal yang ingin dihapus.");
    }
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void inputGuruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputGuruActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputGuruActionPerformed

    private void inputKelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputKelasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputKelasActionPerformed

    private void inputHariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputHariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputHariActionPerformed

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
            java.util.logging.Logger.getLogger(jadwalGuru.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(jadwalGuru.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(jadwalGuru.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(jadwalGuru.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new jadwalGuru().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojerusan.RSMaterialButtonRectangle deleteButton;
    private rojerusan.RSMaterialButtonRectangle editButton;
    private javax.swing.JComboBox<String> inputGuru;
    private javax.swing.JTextField inputHari;
    private javax.swing.JSpinner inputJamMulai;
    private javax.swing.JSpinner inputJamSelesai;
    private javax.swing.JComboBox<String> inputKelas;
    private javax.swing.JComboBox<String> inputPelajaran;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableJadwal;
    private rojerusan.RSMaterialButtonRectangle tambahButton;
    // End of variables declaration//GEN-END:variables
}
