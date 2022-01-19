/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rekammedis;
import javax.swing.JOptionPane;
import java.sql.*;
import javax.swing.JTable;
import java.awt.event.KeyEvent;
import java.util.Calendar;
import java.text.SimpleDateFormat;


/**d
 *
 * @author Iamkka
 */
public class HomeAdmin extends javax.swing.JFrame {
    String user = "root";
    String pwd = "";
    String url = "jdbc:mysql://localhost/rekap_medis";
    Boolean isi = true;
    /**
     * Creates new form HomeAdmin
     */
    public HomeAdmin() {
        initComponents();
        setLocationRelativeTo(null);
    }
    
    void aktif(){
        cbnama_pasien.setEnabled(true);
        txtdiagnosis_pasien.setEnabled(true);
        cbstatus_pasien.setEnabled(true);
        txtnomor_kamar.setEnabled(true);
        btninput_pasien.setEnabled(true);
        btnkeluar_admin.setEnabled(true);
        btncancel_admin.setEnabled(true);
        btnsimpan_pasien.setEnabled(true);
        
        cbgender.setEnabled(false);
        txtalamat_pasien.setEnabled(false);
        txtemail_pasien.setEnabled(false);
        txtnohp_pasien.setEnabled(false);
    }
    
    void nonaktif(){
        cbnama_pasien.setEnabled(false);
        cbgender.setEnabled(false);
        txtalamat_pasien.setEnabled(false);
        txtemail_pasien.setEnabled(false);
        txtnohp_pasien.setEnabled(false);
        txtdiagnosis_pasien.setEnabled(false);
        cbstatus_pasien.setEnabled(false);
        txtnomor_kamar.setEnabled(false);
        btncancel_admin.setEnabled(false);
        btnedit_pasien.setEnabled(false);
        btnhapus_pasien.setEnabled(false);
        btnsimpan_pasien.setEnabled(false);
    }
    
    void bersih(){
        cbnama_pasien.setSelectedIndex(0);
        cbgender.setSelectedIndex(0);
        txtalamat_pasien.setText("");
        txtemail_pasien.setText("");
        txtnohp_pasien.setText("");
        txtdiagnosis_pasien.setText("");
        cbstatus_pasien.setSelectedIndex(0);
        txtnomor_kamar.setText("");
    }
    
    
    private Object[][]getData(){
        Object[][]data_pasien =  null;
        try{
            Connection conn = DriverManager.getConnection(url,user,pwd);
            Statement st = (Statement) conn.createStatement();
            ResultSet rs = st.executeQuery("Select user.nama, user.gender, user.no_hp, med.diagnosis, med.status_pasien, med.no_kamar, med.tgl_input from user, rekam_medis med where (med.id_user = user.id_user) and status = 'Pasien';");
            rs.last();
            int rowCount = rs.getRow();
            rs.beforeFirst();
            data_pasien = new Object [rowCount][7];
            int no =- 1;
            while(rs.next()){
                no = no+1;
                data_pasien[no][0] = rs.getString("nama");
                data_pasien[no][1] = rs.getString("gender");
                data_pasien[no][2] = rs.getString("no_hp");
                data_pasien[no][3] = rs.getString("diagnosis");
                data_pasien[no][4] = rs.getString("status_pasien");
                data_pasien[no][5] = rs.getString("no_kamar");
                data_pasien[no][6] = rs.getString("tgl_input");
            }
        }
        catch(SQLException e){
            System.out.println("Koneksi gagal" + e.toString());
        }
        return data_pasien;
    }
    
    void tampil(){
        String[] columnNames = {"Nama Pasien","Gender","No.HP","Diagnosis","Status","Nomor Kamar","Tanggal"};
        JTable table = new JTable(getData(), columnNames);
        table.setEnabled(false);
        jScrollPane1.setViewportView(table);
    }
    
    void isiNamaPasien(){
        try{
            Connection conn = DriverManager.getConnection(url,user,pwd);
            Statement st = (Statement) conn.createStatement(); 
            String sql = "Select*from user where status = 'Pasien'";
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                cbnama_pasien.addItem(rs.getString("nama"));
            }
        }
        catch(Exception e){
            System.out.println("Koneksi gagal" + e.toString());
        }
    }
    
    void simpan(){
        try{
            Connection conn = DriverManager.getConnection(url,user,pwd);
            Statement st = (Statement) conn.createStatement(); 
            String sql = "insert into rekam_medis (id_user,diagnosis,status_pasien,no_kamar,tgl_input) values ('"+txtid.getText()+"','"+txtdiagnosis_pasien.getText()+"','"+cbstatus_pasien.getSelectedItem()+"','"+txtnomor_kamar.getText()+"','"+txttanggal.getText()+"')";
            st.executeUpdate(sql);
            
            JOptionPane.showMessageDialog(this, "Data berhasil disimpan", "info", JOptionPane.INFORMATION_MESSAGE);
        }
        catch(SQLException e){
            System.out.println("Koneksi gagal" + e.toString());
        }
        formWindowActivated(null);
    }
    
    void cari(){
        try{
            Connection conn = DriverManager.getConnection(url,user,pwd);
            Statement st = (Statement) conn.createStatement(); 
            Statement st2 = (Statement) conn.createStatement(); 
            ResultSet rs = st.executeQuery("Select * from user u, rekam_medis r where u.nama = '"+txtcari_pasien.getText()+"' and r.tgl_input = '"+txtcari_tgl.getText()+"'");
            ResultSet rs2 = st2.executeQuery("Select r.id, r.diagnosis, r.status_pasien, r.no_kamar, r.tgl_input from rekam_medis r, user u where r.id_user = u.id_user and (u.nama = '"+txtcari_pasien.getText()+"' and r.tgl_input = '"+txtcari_tgl.getText()+"')");
//            ResultSet rs = st.executeQuery("Select u.id_user, u.nama, u.alamat, u.email, u.no_hp, r.diagnosis, r.status_pasien, r.no_kamar, r.tgl_input from user u, rekam_medis r where r.id_user = u.id_user and nama = '"+txtcari_pasien.getText()+"'");
            if(rs.next() && rs2.next()){
                cbnama_pasien.setSelectedItem(rs.getString("nama"));
                cbgender.setSelectedItem(rs.getString("gender"));
                txtalamat_pasien.setText(rs.getString("alamat"));
                txtemail_pasien.setText(rs.getString("email"));
                txtnohp_pasien.setText(rs.getString("no_hp"));
                txtid.setText(rs.getString("id_user"));
                txtdiagnosis_pasien.setText(rs2.getString("diagnosis"));
                cbstatus_pasien.setSelectedItem(rs2.getString("status_pasien"));
                txtnomor_kamar.setText(rs2.getString("no_kamar"));
                txtid_medis.setText(rs2.getString("id"));
            }
            else{
                JOptionPane.showMessageDialog(this, "Data tidak ditemukan", "info", JOptionPane.INFORMATION_MESSAGE);
                txtcari_pasien.setText("");
            }
        }
        catch(SQLException e){
            System.out.println("Koneksi gagal" + e.toString());
        }
    }
    
    void update(){
        try{
            Connection conn = DriverManager.getConnection(url,user,pwd);
            Statement st = (Statement) conn.createStatement(); 
            String sql = "update rekam_medis set diagnosis = '"+txtdiagnosis_pasien.getText()+"', status_pasien = '"+cbstatus_pasien.getSelectedItem()+"', no_kamar = '"+txtnomor_kamar.getText()+"'"+"where id = '"+txtid_medis.getText()+"'";
            st.executeUpdate(sql);
            
            JOptionPane.showMessageDialog(this, "Data berhasil diupdate","info", JOptionPane.INFORMATION_MESSAGE);
        }
        catch(SQLException e){
            System.out.println("Koneksi gagal" + e.toString());
        }
        formWindowActivated(null);
    }
    
    void hapus(){
        try{
            Connection conn = DriverManager.getConnection(url,user,pwd);
            Statement st = (Statement) conn.createStatement(); 
            String sql = "delete from rekam_medis where id = '"+txtid_medis.getText()+"'";
            st.executeUpdate(sql);
            
            JOptionPane.showMessageDialog(this, "Data berhasil dihapus", "info", JOptionPane.INFORMATION_MESSAGE);
        }
        catch(SQLException e){
            System.out.println("Koneksi gagal" + e.toString());
        }
        formWindowActivated(null);
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtalamat_pasien = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtemail_pasien = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtcari_pasien = new javax.swing.JTextField();
        btncari_pasien = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtnohp_pasien = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtdiagnosis_pasien = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        cbstatus_pasien = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        txtnomor_kamar = new javax.swing.JTextField();
        btninput_pasien = new javax.swing.JButton();
        btnsimpan_pasien = new javax.swing.JButton();
        btnedit_pasien = new javax.swing.JButton();
        btncancel_admin = new javax.swing.JButton();
        btnkeluar_admin = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_admin = new javax.swing.JTable();
        btnhapus_pasien = new javax.swing.JButton();
        cbgender = new javax.swing.JComboBox<>();
        cbnama_pasien = new javax.swing.JComboBox<>();
        txtid = new javax.swing.JTextField();
        txttanggal = new javax.swing.JTextField();
        txtid_medis = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtcari_tgl = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(194, 248, 255));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(1, 151, 202));
        jPanel1.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("Admin Panel");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jLabel1);
        jLabel1.setBounds(530, 60, 111, 39);

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setText("Input Rekam Medis Pasien :");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(30, 180, 193, 19);

        jLabel3.setText("Nama Pasien");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(30, 230, 90, 16);

        jLabel4.setText("Gender");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(30, 270, 50, 16);

        jLabel5.setText("Alamat");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(30, 330, 50, 16);

        txtalamat_pasien.setEditable(false);
        jPanel1.add(txtalamat_pasien);
        txtalamat_pasien.setBounds(200, 310, 360, 58);

        jLabel6.setText("Email");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(30, 400, 60, 16);

        txtemail_pasien.setEditable(false);
        jPanel1.add(txtemail_pasien);
        txtemail_pasien.setBounds(200, 390, 360, 24);

        jLabel7.setText("Cari");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(30, 140, 23, 16);
        jPanel1.add(txtcari_pasien);
        txtcari_pasien.setBounds(120, 140, 450, 24);

        btncari_pasien.setText("Cari");
        btncari_pasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncari_pasienActionPerformed(evt);
            }
        });
        jPanel1.add(btncari_pasien);
        btncari_pasien.setBounds(1030, 140, 73, 32);

        jLabel8.setText("No HP");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(650, 230, 40, 16);

        txtnohp_pasien.setEditable(false);
        jPanel1.add(txtnohp_pasien);
        txtnohp_pasien.setBounds(750, 220, 360, 24);

        jLabel9.setText("Diagnosis");
        jPanel1.add(jLabel9);
        jLabel9.setBounds(650, 270, 60, 16);
        jPanel1.add(txtdiagnosis_pasien);
        txtdiagnosis_pasien.setBounds(750, 260, 360, 24);

        jLabel10.setText("Status");
        jPanel1.add(jLabel10);
        jLabel10.setBounds(650, 310, 40, 16);

        cbstatus_pasien.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Rawat Jalan", "Rawat Inap" }));
        jPanel1.add(cbstatus_pasien);
        cbstatus_pasien.setBounds(750, 310, 360, 26);

        jLabel11.setText("Nomor Kamar");
        jPanel1.add(jLabel11);
        jLabel11.setBounds(650, 350, 80, 16);
        jPanel1.add(txtnomor_kamar);
        txtnomor_kamar.setBounds(750, 350, 360, 24);

        btninput_pasien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rekammedis/plus.png"))); // NOI18N
        btninput_pasien.setText("Input");
        btninput_pasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btninput_pasienActionPerformed(evt);
            }
        });
        jPanel1.add(btninput_pasien);
        btninput_pasien.setBounds(30, 460, 170, 46);

        btnsimpan_pasien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rekammedis/save-file.png"))); // NOI18N
        btnsimpan_pasien.setText("Simpan");
        btnsimpan_pasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsimpan_pasienActionPerformed(evt);
            }
        });
        jPanel1.add(btnsimpan_pasien);
        btnsimpan_pasien.setBounds(210, 460, 170, 48);

        btnedit_pasien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rekammedis/edit.png"))); // NOI18N
        btnedit_pasien.setText("Edit");
        btnedit_pasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnedit_pasienActionPerformed(evt);
            }
        });
        jPanel1.add(btnedit_pasien);
        btnedit_pasien.setBounds(390, 460, 170, 48);

        btncancel_admin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rekammedis/multiply.png"))); // NOI18N
        btncancel_admin.setText("Cancel");
        btncancel_admin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancel_adminActionPerformed(evt);
            }
        });
        jPanel1.add(btncancel_admin);
        btncancel_admin.setBounds(760, 460, 170, 48);

        btnkeluar_admin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rekammedis/logout.png"))); // NOI18N
        btnkeluar_admin.setText("Keluar");
        btnkeluar_admin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnkeluar_adminActionPerformed(evt);
            }
        });
        jPanel1.add(btnkeluar_admin);
        btnkeluar_admin.setBounds(940, 460, 170, 48);

        table_admin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Nama Pasien", "Gender", "No. HP", "Diagnosis", "Status", "Nomor Kamar"
            }
        ));
        jScrollPane1.setViewportView(table_admin);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(30, 550, 1087, 145);

        btnhapus_pasien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rekammedis/trash.png"))); // NOI18N
        btnhapus_pasien.setText("Hapus");
        btnhapus_pasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhapus_pasienActionPerformed(evt);
            }
        });
        jPanel1.add(btnhapus_pasien);
        btnhapus_pasien.setBounds(580, 460, 169, 48);

        cbgender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "L", "P" }));
        jPanel1.add(cbgender);
        cbgender.setBounds(200, 270, 360, 26);

        cbnama_pasien.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih nama pasien..." }));
        cbnama_pasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbnama_pasienActionPerformed(evt);
            }
        });
        jPanel1.add(cbnama_pasien);
        cbnama_pasien.setBounds(200, 220, 360, 26);

        txtid.setEditable(false);
        txtid.setEnabled(false);
        jPanel1.add(txtid);
        txtid.setBounds(30, 20, 78, 24);
        jPanel1.add(txttanggal);
        txttanggal.setBounds(930, 60, 169, 24);

        txtid_medis.setEditable(false);
        txtid_medis.setEnabled(false);
        jPanel1.add(txtid_medis);
        txtid_medis.setBounds(120, 20, 78, 24);

        jLabel12.setText("Nama pasien");
        jPanel1.add(jLabel12);
        jLabel12.setBounds(130, 610, 74, 16);

        jLabel13.setText("Tanggal");
        jPanel1.add(jLabel13);
        jLabel13.setBounds(510, 610, 45, 16);

        txtcari_tgl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcari_tglActionPerformed(evt);
            }
        });
        jPanel1.add(txtcari_tgl);
        txtcari_tgl.setBounds(590, 140, 420, 24);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1150, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 769, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btncari_pasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncari_pasienActionPerformed
        cari();
        btnsimpan_pasien.setEnabled(true);
        btnhapus_pasien.setEnabled(true);
        btninput_pasien.setEnabled(false);
        btncancel_admin.setEnabled(true);
        btnkeluar_admin.setEnabled(true);
        btnsimpan_pasien.setEnabled(true);
        btnedit_pasien.setEnabled(true);
        
        txtcari_pasien.setText("");
        txtcari_tgl.setText("");
    }//GEN-LAST:event_btncari_pasienActionPerformed

    private void btninput_pasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btninput_pasienActionPerformed
        aktif();
        btninput_pasien.grabFocus();
    }//GEN-LAST:event_btninput_pasienActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        nonaktif();
        tampil();
        bersih();
        isiNamaPasien();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        txttanggal.setText(sdf.format(cal.getTime()));
        btninput_pasien.setEnabled(true);
        
        txtid.setVisible(false);
        txtid_medis.setVisible(false);
    }//GEN-LAST:event_formWindowActivated

    private void btnkeluar_adminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnkeluar_adminActionPerformed
        dispose();
    }//GEN-LAST:event_btnkeluar_adminActionPerformed

    private void btncancel_adminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancel_adminActionPerformed
        bersih();
    }//GEN-LAST:event_btncancel_adminActionPerformed

    private void cbnama_pasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbnama_pasienActionPerformed
        try{
            Connection conn = DriverManager.getConnection(url,user,pwd);
            Statement st = (Statement) conn.createStatement(); 
            String sql = "Select*from user where nama = '"+cbnama_pasien.getSelectedItem()+"'";
            ResultSet rs = st.executeQuery(sql); 
            if(rs.next()){
                cbgender.setSelectedItem(rs.getString("gender"));
                txtalamat_pasien.setText(rs.getString("alamat"));
                txtemail_pasien.setText(rs.getString("email"));
                txtnohp_pasien.setText(rs.getString("no_hp"));
                txtid.setText(rs.getString("id_user"));
            }
        }
        catch(Exception e){
            System.out.println("Koneksi gagal" + e.toString());
        }
    }//GEN-LAST:event_cbnama_pasienActionPerformed

    private void btnsimpan_pasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpan_pasienActionPerformed
        if(isi == true){
            simpan();
        }
        else{
            update();
            isi=true;
        }
        btninput_pasien.setEnabled(true);
        btnsimpan_pasien.setEnabled(true);
    }//GEN-LAST:event_btnsimpan_pasienActionPerformed

    private void btnedit_pasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnedit_pasienActionPerformed
        isi=false;
        aktif();
        btnedit_pasien.setEnabled(false);
    }//GEN-LAST:event_btnedit_pasienActionPerformed

    private void btnhapus_pasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhapus_pasienActionPerformed
        int ok = JOptionPane.showConfirmDialog(this, "Yakin ingin hapus data?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        
        if(ok==0){
            hapus();
            formWindowActivated(null);
        }
        else{
            nonaktif();
            isi=true;
        }
    }//GEN-LAST:event_btnhapus_pasienActionPerformed

    private void txtcari_tglActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcari_tglActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcari_tglActionPerformed

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
            java.util.logging.Logger.getLogger(HomeAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomeAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomeAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomeAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomeAdmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btncancel_admin;
    private javax.swing.JButton btncari_pasien;
    private javax.swing.JButton btnedit_pasien;
    private javax.swing.JButton btnhapus_pasien;
    private javax.swing.JButton btninput_pasien;
    private javax.swing.JButton btnkeluar_admin;
    private javax.swing.JButton btnsimpan_pasien;
    private javax.swing.JComboBox<String> cbgender;
    private javax.swing.JComboBox<String> cbnama_pasien;
    private javax.swing.JComboBox<String> cbstatus_pasien;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table_admin;
    private javax.swing.JTextField txtalamat_pasien;
    private javax.swing.JTextField txtcari_pasien;
    private javax.swing.JTextField txtcari_tgl;
    private javax.swing.JTextField txtdiagnosis_pasien;
    private javax.swing.JTextField txtemail_pasien;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txtid_medis;
    private javax.swing.JTextField txtnohp_pasien;
    private javax.swing.JTextField txtnomor_kamar;
    private javax.swing.JTextField txttanggal;
    // End of variables declaration//GEN-END:variables
}
