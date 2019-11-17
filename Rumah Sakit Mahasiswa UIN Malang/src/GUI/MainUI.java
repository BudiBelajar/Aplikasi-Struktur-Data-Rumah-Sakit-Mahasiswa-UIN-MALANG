/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DataEngine.WriteRead;
import DataEngine.DataHandler;
import DataEngine.Array;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author iLHAM
 */
public class MainUI extends javax.swing.JFrame {

    /**
     * Creates new form MainUI
     */
    private final int COL_NIM = 0;
    private final int COL_NAMA = 1;
    private final int COL_JURUSAN = 2;
    private final int COL_JK = 3;
    private final int COL_HP = 4;
    private final int COL_KAMAR= 5;

    private int searchBy = 0;
    private String where = "";
    private String filterGender = "";
    private String sortBy = "";
    private String order = "";
    private String filterSkillCond="";
    public static boolean saved;
    public static boolean empty;
    public static String[][] result;
    public static DataHandler db;
    public static Array arrayHandler;
    private WriteRead file;

    /**
     * CONSTRUCTOR
     */
    public MainUI() {
        initComponents();
        myInitComponet();
        db = new DataHandler();
        file = new WriteRead();

        radAll.setSelected(true);
        saved = true;

        //MUAT DATA DARI DISK
        try {
            if ((result = file.readFile()) != null) {
                for (int i = 0; i < result.length; i++) {
                    db.insert(result[i][COL_NIM], result[i][COL_NAMA].toUpperCase(), result[i][COL_JK].charAt(0), result[i][COL_JURUSAN].toUpperCase(), result[i][COL_HP], result[i][COL_KAMAR].toUpperCase());
                }
                System.out.println(">memperbarui...");
                filterTable();
            } else {
                System.out.println(">belum ada...");
                empty = true;
            }
        } catch (IOException ex) {
            Logger.getLogger(MainUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //END

    /**
     * UPDATE SEMUA VARIABEL
     */
    public void updateVar() {
        this.where = txtFind.getText().trim().toLowerCase();
        //cek cari dengan apa
        if (optFind.getSelectedIndex() == 0) {
            this.searchBy = 0;
        } else if (optFind.getSelectedIndex() == 1) {
            this.searchBy = 1;
        } else if (optFind.getSelectedIndex() == 2) {
            this.searchBy = 2;
        } else {
            this.searchBy = 4;
        }
        //cek filter jk
        if (radLK.isSelected()) {
            this.filterGender = "L";
        } else if (radPR.isSelected()) {
            this.filterGender = "P";
        } else {
            this.filterGender = "";
        }
        //sortby inisialisasi
        this.sortBy = optSort.getSelectedItem().toString().toLowerCase();
        this.order = optOrder.getSelectedItem().toString();
        result = db.getResult(sortBy, order);;

        empty = (result == null);
        System.out.println(">memperbarui... !");
    }
//    END

    /**
     * METHOD SEARCH
     *
     * @return BOOLEAN HASIL
     */
    public boolean search() {
        if (!where.equals("")) {
            result = db.search(searchBy, where);
            if (result == null) {
                empty = true;
                return false;
            } else {
                return true;
            }
        } else {//do nothing
            db.setSortIdx(searchBy);
            return true;
        }
    }

    /**
     * RESET KOSONG SEMUA FORM
     */
    public void resetForm() {
        txtFind.setText("");
        radAll.setSelected(true);
        radPR.setSelected(false);
        radLK.setSelected(false);
        optSort.setSelectedIndex(0);
        optOrder.setSelectedIndex(0);
    }
//    END

    /**
     * FILTER SEMUA ROW TABEL
     */
    public void filterTable() {
        updateVar();
        updateTableAll();
        if (!empty) {
            
            filterJK();
            if (!search()) {
                System.out.println("hasil pencarian tidak ditemukan!");
                updateTableAll();
            } else {
                updateTableAll();
            }
            showRow();
        }
    }

    //END
    /**
     * TAMPILKAN JUMLAH ROW TABLE
     */
    public void showRow() {
        TableModel dtm = tblMahasiswa.getModel();
        lblResult.setText("Hasil = " + dtm.getRowCount() + " Baris");
    }

    //END
    /**
     * SIMPAN SEMUA VALUE PADA TABLE
     */
    public void retreveTable() {
        TableModel dtm = tblMahasiswa.getModel();
        int nRow = dtm.getRowCount(), nCol = dtm.getColumnCount();
        String[][] tableData = new String[nRow][nCol];

        for (int i = 0; i < nRow; i++) {
            for (int j = 0; j < nCol; j++) {
                tableData[i][j] = dtm.getValueAt(i, j).toString();
            }
        }
        result = tableData;
        System.out.println(">memproses data...!");
    }
    //END

    /**
     * FILTER JENIS KELAMIN
     */
    public void filterJK() {
        DefaultTableModel model = (DefaultTableModel) tblMahasiswa.getModel();
        model.setRowCount(0);

        if (!filterGender.equals("")) {
            for (int i = 0; i < result.length; i++) {
                if (result[i][COL_JK].equals(filterGender)) {
                    Object[] row = {result[i][COL_NIM], result[i][COL_NAMA], result[i][COL_JURUSAN], result[i][COL_JK], result[i][COL_HP], result[i][COL_KAMAR]};
                    model.addRow(row);
                }
            }
            retreveTable(); //SIMPAN HASIL FILTER
        } else {
            return;
        }
        System.out.println(">tentukan jenisnya" + filterGender);
    }

   
    public void updateTableAll() {

        DefaultTableModel model = (DefaultTableModel) tblMahasiswa.getModel();
        model.setRowCount(0);
        if (empty) {
            return;
        }

        for (int i = 0; i < result.length; i++) {
            Object[] row = {result[i][COL_NIM], result[i][COL_NAMA], result[i][COL_JURUSAN], result[i][COL_JK], result[i][COL_HP], result[i][COL_KAMAR]};
            model.addRow(row);
        }
    }
    //END

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtFind = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMahasiswa = new javax.swing.JTable();
        optFind = new javax.swing.JComboBox<>();
        btnFind = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        optSort = new javax.swing.JComboBox<>();
        btnSort = new javax.swing.JButton();
        optOrder = new javax.swing.JComboBox<>();
        radLK = new javax.swing.JRadioButton();
        radPR = new javax.swing.JRadioButton();
        radAll = new javax.swing.JRadioButton();
        lblResult = new javax.swing.JLabel();
        btnFilter = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        menuInsert = new javax.swing.JMenuItem();
        menuDelete = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        menuRefresh = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        menuSave = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        menuExit = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("RUMAH SAKIT MAHASISWA UIN MAULANA MALIK IBARAHIM MALANG");
        setMinimumSize(new java.awt.Dimension(700, 500));

        jPanel1.setLayout(null);

        txtFind.setToolTipText("Insert Nama");
        txtFind.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFindKeyReleased(evt);
            }
        });
        jPanel1.add(txtFind);
        txtFind.setBounds(180, 20, 267, 33);

        tblMahasiswa.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblMahasiswa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NIM", "Nama", "Jurusan", "Jenis Kelamin", "Penyakit", "Kamar"
            }
        ));
        tblMahasiswa.setEnabled(false);
        jScrollPane1.setViewportView(tblMahasiswa);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(110, 80, 680, 400);

        optFind.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NIM", "NAMA", " " }));
        optFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optFindActionPerformed(evt);
            }
        });
        optFind.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                optFindKeyPressed(evt);
            }
        });
        jPanel1.add(optFind);
        optFind.setBounds(450, 20, 80, 40);

        btnFind.setText("CARI");
        btnFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindActionPerformed(evt);
            }
        });
        jPanel1.add(btnFind);
        btnFind.setBounds(540, 20, 88, 37);

        jLabel1.setFont(new java.awt.Font("Roboto Black", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("<html> < Rumah Sakit Mahasiswa<br> UIN Malang <br> </b> </htmll>");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(10, 20, 167, 38);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(240, 240, 240));
        jLabel2.setText("Urutkan");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(10, 70, 50, 14);

        optSort.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NIM", "NAMA", " " }));
        jPanel1.add(optSort);
        optSort.setBounds(10, 90, 70, 20);

        btnSort.setText("Terapkan");
        btnSort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSortActionPerformed(evt);
            }
        });
        jPanel1.add(btnSort);
        btnSort.setBounds(10, 150, 90, 23);

        optOrder.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ASC", "DESC" }));
        optOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optOrderActionPerformed(evt);
            }
        });
        jPanel1.add(optOrder);
        optOrder.setBounds(10, 120, 70, 20);

        radLK.setText("Laki-laki");
        radLK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radLKActionPerformed(evt);
            }
        });
        jPanel1.add(radLK);
        radLK.setBounds(10, 230, 63, 23);

        radPR.setText("Perempuan");
        radPR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radPRActionPerformed(evt);
            }
        });
        jPanel1.add(radPR);
        radPR.setBounds(10, 260, 79, 23);

        radAll.setText("Semua");
        radAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radAllActionPerformed(evt);
            }
        });
        jPanel1.add(radAll);
        radAll.setBounds(10, 200, 60, 23);

        lblResult.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblResult.setForeground(new java.awt.Color(240, 240, 240));
        lblResult.setText("Result = 0 row");
        jPanel1.add(lblResult);
        lblResult.setBounds(0, 360, 110, 70);

        btnFilter.setText("Terapkan");
        btnFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFilterActionPerformed(evt);
            }
        });
        jPanel1.add(btnFilter);
        btnFilter.setBounds(10, 290, 80, 23);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(240, 240, 240));
        jLabel3.setText("Jenis :");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(10, 180, 40, 14);

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/home.jpg"))); // NOI18N
        jPanel1.add(jLabel6);
        jLabel6.setBounds(0, 0, 820, 490);

        fileMenu.setText("File");

        menuInsert.setText("Insert...");
        menuInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuInsertActionPerformed(evt);
            }
        });
        menuInsert.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                menuInsertKeyPressed(evt);
            }
        });
        fileMenu.add(menuInsert);

        menuDelete.setText("Delete...");
        menuDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuDeleteActionPerformed(evt);
            }
        });
        fileMenu.add(menuDelete);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Update...");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        fileMenu.add(jMenuItem1);
        fileMenu.add(jSeparator3);

        menuRefresh.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        menuRefresh.setText("Refresh");
        menuRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuRefreshActionPerformed(evt);
            }
        });
        fileMenu.add(menuRefresh);
        fileMenu.add(jSeparator1);

        menuSave.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        menuSave.setText("Save");
        menuSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSaveActionPerformed(evt);
            }
        });
        fileMenu.add(menuSave);
        fileMenu.add(jSeparator2);

        menuExit.setText("Exit");
        menuExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuExitActionPerformed(evt);
            }
        });
        fileMenu.add(menuExit);

        jMenuBar1.add(fileMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 802, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void radLKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radLKActionPerformed
        // TODO add your handling code here:
        radAll.setSelected(false);
        radPR.setSelected(false);
    }//GEN-LAST:event_radLKActionPerformed

    private void radPRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radPRActionPerformed
        // TODO add your handling code here:
        radLK.setSelected(false);
        radAll.setSelected(false);
    }//GEN-LAST:event_radPRActionPerformed

    private void radAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radAllActionPerformed
        // TODO add your handling code here:
        radLK.setSelected(false);
        radPR.setSelected(false);
    }//GEN-LAST:event_radAllActionPerformed

    private void btnFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindActionPerformed
        // TODO add your handling code here:
        filterTable();
    }//GEN-LAST:event_btnFindActionPerformed

    private void btnSortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSortActionPerformed
        // TODO add your handling code here:
        updateVar();

        if (!txtFind.getText().equals("")) {
            retreveTable();
            arrayHandler = new Array();
            arrayHandler.insert(result);
            int by = 0;
            if (sortBy.equals("nim")) {
                by = 0;
            } else if (sortBy.equals("nama")) {
                by = 1;
            } else {
                by = 2;
            }
            result = arrayHandler.sortArray(by, order);
        }
        updateTableAll();
    }//GEN-LAST:event_btnSortActionPerformed

    private void btnFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFilterActionPerformed
        // TODO add your handling code here:
        filterTable();
    }//GEN-LAST:event_btnFilterActionPerformed

    private void menuInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuInsertActionPerformed
        // TODO add your handling code here:
        new Add().setVisible(true);
    }//GEN-LAST:event_menuInsertActionPerformed

    private void optFindKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_optFindKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            filterTable();
        }
    }//GEN-LAST:event_optFindKeyPressed

    private void menuDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuDeleteActionPerformed
        // TODO add your handling code here:
        new Delete().setVisible(true);
    }//GEN-LAST:event_menuDeleteActionPerformed

    private void menuExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuExitActionPerformed
        // TODO add your handling code here:
        String ObjButtons[] = {"Ya", "Tidak"};
        int PromptResult = JOptionPane.showOptionDialog(null,
                "Apakah anda yakin ingin keluar?", "Rumah Sakit Mahasiswa UIN Malang",
                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null,
                ObjButtons, ObjButtons[1]);
        if (PromptResult == 0) {
            System.exit(0);
        }
    }//GEN-LAST:event_menuExitActionPerformed
    //shortcut


    private void menuInsertKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_menuInsertKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuInsertKeyPressed

    private void save() throws IOException {

        updateVar();
        if (!empty) {
            file.writeFile(result);
        } else {
            file.writeFile(null);
        }
        JOptionPane.showMessageDialog(null, "Data telah disimpan!");

    }

    private void menuSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSaveActionPerformed
        // TODO add your handling code here:
        try {
            save();
        } catch (IOException ex) {
            Logger.getLogger(MainUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        saved = true;
    }//GEN-LAST:event_menuSaveActionPerformed

    private void menuRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuRefreshActionPerformed
        // TODO add your handling code here:
        resetForm();
        filterTable();
    }//GEN-LAST:event_menuRefreshActionPerformed

    private void txtFindKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFindKeyReleased
        // TODO add your handling code here:
        filterTable();
    }//GEN-LAST:event_txtFindKeyReleased

    private void optFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optFindActionPerformed
        // TODO add your handling code here:
        filterTable();
    }//GEN-LAST:event_optFindActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        new Edit().setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void optOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optOrderActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_optOrderActionPerformed

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
            java.util.logging.Logger.getLogger(MainUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainUI().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFilter;
    private javax.swing.JButton btnFind;
    private javax.swing.JButton btnSort;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JLabel lblResult;
    private javax.swing.JMenuItem menuDelete;
    private javax.swing.JMenuItem menuExit;
    private javax.swing.JMenuItem menuInsert;
    private javax.swing.JMenuItem menuRefresh;
    private javax.swing.JMenuItem menuSave;
    private javax.swing.JComboBox<String> optFind;
    private javax.swing.JComboBox<String> optOrder;
    private javax.swing.JComboBox<String> optSort;
    private javax.swing.JRadioButton radAll;
    private javax.swing.JRadioButton radLK;
    private javax.swing.JRadioButton radPR;
    private javax.swing.JTable tblMahasiswa;
    private javax.swing.JTextField txtFind;
    // End of variables declaration//GEN-END:variables

    //menu shortcut
    private void myInitComponet() {
        menuInsert.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_I, ActionEvent.CTRL_MASK));
        menuDelete.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_D, ActionEvent.CTRL_MASK));
        menuExit.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent we) {
                ////saat system exit

                //JIKA BELUM TERSIMPAN
                if (!saved) {
                    String ObjButtons[] = {"Simpan", "Tidak"};
                    int PromptResult = JOptionPane.showOptionDialog(null,
                            "Data belum tersimpan?", "Rumah Sakit Mahasiswa UIN Malang",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null,
                            ObjButtons, ObjButtons[1]);
                    if (PromptResult == 0) {
                        try {
                            save();//simpan data
                            saved = true;
                        } catch (IOException ex) {
                            Logger.getLogger(MainUI.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }

                //TANYA KELUAR
                String ObjButtons[] = {"Ya", "Tidak"};
                int PromptResult = JOptionPane.showOptionDialog(null,
                        "Apakah anda yakin ingin keluar?", "Rumah Sakit Mahasiswa UIN Malang",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null,
                        ObjButtons, ObjButtons[1]);
                if (PromptResult == 0) {
                    System.exit(0);
                }
            }
        });
    }
}
