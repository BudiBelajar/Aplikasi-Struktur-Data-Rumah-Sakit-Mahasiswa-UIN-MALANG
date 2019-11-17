/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DataEngine.Array;
import javax.swing.JOptionPane;

/**
 *
 * @author ilham
 */
public class Add extends javax.swing.JFrame {

    private String NIM;
    private String Nama;
    private char JK;
    private String Jurusan;
    private String Penyakit;
    private String Kamar;

    /**
     * Creates new form Add
     */
    public Add() {
        initComponents();

        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtNama = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtNIM = new javax.swing.JTextField();
        txtPenyakit = new javax.swing.JTextField();
        jJurusan = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jKamar = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        comboJK = new javax.swing.JComboBox<String>();
        jLabel8 = new javax.swing.JLabel();
        btnADD = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("TAMBAH MAHASISWA");
        setResizable(false);

        jPanel1.setLayout(null);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(240, 240, 240));
        jLabel3.setText("NIM :");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(66, 20, 30, 14);

        txtNama.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jPanel1.add(txtNama);
        txtNama.setBounds(100, 50, 210, 20);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(240, 240, 240));
        jLabel5.setText("JURUSAN :");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(35, 80, 58, 14);

        txtNIM.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jPanel1.add(txtNIM);
        txtNIM.setBounds(100, 20, 210, 20);
        jPanel1.add(txtPenyakit);
        txtPenyakit.setBounds(100, 110, 210, 20);

        jJurusan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pendidikan Agama Islam", "Pendidikan Ilmu Pengetahuan Sosial", "Pendidikan Bahasa Arab", "Pendidikan Islam Anak Usia Dini", "Manajemen Pendidikan Islam", "Tadris Bahasa Inggris", "Tadris Matematika", "al-Ahwal al-Syakhshiyyah", "Hukum Bisnis Syaria'ah", "Hukum Tata Negara", "Al-qur'an dan Ilmu Tafsir", "Bahasa dan Sastra Arab", "Sastra Inggris", "Psikologi", "Managemen", "Akuntansi", "Perbankan Syariah", "Matematika", "Biologi", "Fisika", "Kimia", "Teknik Informatika", "Teknik Arsitektur", "Perpustakaan dan Ilmu informasi", "Farmasi", "Pendidikan Dokter", "Profesi Dokter", " ", " ", " " }));
        jJurusan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jJurusanActionPerformed(evt);
            }
        });
        jPanel1.add(jJurusan);
        jJurusan.setBounds(100, 80, 196, 20);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(240, 240, 240));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("PENYAKIT :");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(30, 110, 61, 14);

        jKamar.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1 A", "1 B", "1 C", "2 A", "2 B", "2 C", "3 A", "3 B", "3 C", "4 A", "4 B", "4 C", "5 A", "5 B", "5 C", "6 A", "6 B", "6 C", "7 A", "7 B", "7 C", "8 A", "8 B", "8 C", "9 A", "9 B", "9 C", "10 A", "10 B", "10 C", "11 A", "11 B", "11 C", "12 A", "12 B", "12 C", "13 A", "13 B", "13 C", "BEDAH 1 A", "BEDAH 1 B", "BEDAH 1 C", "BEDAH 2 A", "BEDAH 2 B", "BEDAH 2 C", "ISOLASI 1", "ISOLASI 2 ", "ISOLASI 3", "ISOLASI 4" }));
        jKamar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jKamarActionPerformed(evt);
            }
        });
        jPanel1.add(jKamar);
        jKamar.setBounds(100, 170, 77, 20);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(240, 240, 240));
        jLabel7.setText("KAMAR :");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(42, 170, 50, 14);

        comboJK.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Laki-laki", "Perempuan" }));
        jPanel1.add(comboJK);
        comboJK.setBounds(100, 140, 127, 20);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(240, 240, 240));
        jLabel8.setText("JENIS KELAMIN :");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(10, 140, 90, 14);

        btnADD.setText("ADD");
        btnADD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnADDActionPerformed(evt);
            }
        });
        jPanel1.add(btnADD);
        btnADD.setBounds(200, 220, 121, 39);

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancel);
        btnCancel.setBounds(50, 220, 121, 39);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(240, 240, 240));
        jLabel4.setText("NAMA :");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(56, 50, 40, 14);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/home.jpg"))); // NOI18N
        jPanel1.add(jLabel2);
        jLabel2.setBounds(0, 0, 340, 310);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private boolean checkInt(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("NOT NUMBER!");
        }
        return false;
    }

    private void btnADDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnADDActionPerformed
        // TODO add your handling code here:
        //cek kosong
        if ("".equals(txtNIM.getText()) || "".equals(txtNama.getText()) || "".equals(jJurusan.getSelectedItem()) 
                || "".equals(jKamar.getSelectedItem())||"".equals(txtPenyakit.getText())){
            JOptionPane.showMessageDialog(null, "Semua form harus diisi!");
            return;
        }
        //cek input salah
        if (!checkInt(txtNIM.getText())) {
            JOptionPane.showMessageDialog(null, "Form NIM harus diisi !");
            return;
        }


        this.NIM = txtNIM.getText().trim();

        if (!MainUI.empty) {
            //pencarian
            //jika ada nim sama
            if (MainUI.db.find(0, NIM) != null) {
                JOptionPane.showMessageDialog(null, "NIM sudah ada!");
                return;
            }
        }
        
        this.Jurusan = jJurusan.getSelectedItem().toString().trim().toUpperCase();
        txtNIM.setText("");
        jJurusan.setSelectedItem("");
        this.Nama = txtNama.getText().trim().toUpperCase();
        txtNama.setText("");
        this.Penyakit = txtPenyakit.getText().trim();
        txtPenyakit.setText("");
        this.JK = comboJK.getSelectedItem().toString().charAt(0);
        this.Kamar = jKamar.getSelectedItem().toString().trim().toUpperCase();
        if (!MainUI.empty) {
            if (MainUI.db. find(0, Kamar)!=null) {
                JOptionPane.showMessageDialog(null, "Kamar sudah ada!");
                return;
            }
        }
       

        MainUI.db.insert(NIM, Nama, JK, Jurusan, Penyakit, Kamar);
        MainUI.saved = false;
        System.out.println("data inserted");
        JOptionPane.showMessageDialog(null, "Data berhasil dimasukkan!");
    }//GEN-LAST:event_btnADDActionPerformed


    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_btnCancelActionPerformed

    private void jJurusanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jJurusanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jJurusanActionPerformed

    private void jKamarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jKamarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jKamarActionPerformed

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Add.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Add.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Add.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Add.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Add().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnADD;
    private javax.swing.JButton btnCancel;
    private javax.swing.JComboBox<String> comboJK;
    private javax.swing.JComboBox jJurusan;
    private javax.swing.JComboBox jKamar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtNIM;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtPenyakit;
    // End of variables declaration//GEN-END:variables
}