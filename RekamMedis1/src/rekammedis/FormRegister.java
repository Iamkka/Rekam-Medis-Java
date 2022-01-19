/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rekammedis;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;
import java.sql.*;
/**
 *
 * @author gusti
 */
public class FormRegister extends javax.swing.JFrame {
    String user ="root";
    String pwd ="";
    String url="jdbc:mysql://localhost/rekap_medis";
    Boolean isi= true;
    /**
     * Creates new form FormRegister
     */
    public FormRegister() {
        initComponents();
        setLocationRelativeTo(null);
    }
    void aktif(){
        txtnama.setEnabled(true);
        //txtkode.setEnabled(true);
        txtemail.setEnabled(true);
        txtpassword.setEnabled(true);
        txtalamat.setEnabled(true);
        txtnohp.setEnabled(true);
        jcbgender.setEnabled(true);
        jcbstatus.setEnabled(true);
    }
   /* void nonaktif(){
        txtemail.setEnabled(false);
        txtnama.setEnabled(false);
        txtpassword.setEnabled(false);
        txtalamat.setEnabled(false);
        txtnohp.setEnabled(false);
        jcbgender.setEnabled(false);
        btnregister.setEnabled(false);
        btncancel.setEnabled(false);
        jcbstatus.setEnabled(false);
    }
    */
    void bersih(){
        txtemail.setText("");
        txtnama.setText("");
        txtpassword.setText("");
        //txtkode.setText("");
        txtnohp.setText("");
        txtalamat.setText("");
        jcbstatus.setSelectedIndex(0);
        jcbgender.setSelectedIndex(0);
    }
    
    
    void simpan(){
        try{
            Connection conn = DriverManager.getConnection(url,user,pwd);
            Statement st = (Statement) conn.createStatement();
            String sql = "insert into `user`(`nama`, `email`, `password`, `alamat`, `gender`, `status`, `no_hp`) values('"+txtnama.getText()+"','"+txtemail.getText()+"','"+txtpassword.getText()+"','"+txtalamat.getText()+"','"+jcbgender.getSelectedItem()+"','"+jcbstatus.getSelectedItem()+"','"+txtnohp.getText()+"')";
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(this,"User sudah terdaftar","info",JOptionPane.INFORMATION_MESSAGE);
            
        }
        catch(SQLException e){
            System.out.println("Koneksi gagal"+e.toString());
        }
        formWindowActivated(null);
    }
    
    public void close(){
        WindowEvent closeWindow = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closeWindow);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtnohp = new javax.swing.JTextField();
        txtnama = new javax.swing.JTextField();
        txtemail = new javax.swing.JTextField();
        txtalamat = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtpassword = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        jcbstatus = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        btnregister = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        btncancel = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jcbgender = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(1, 151, 202));
        jPanel1.setLayout(null);

        jLabel8.setText("No HP");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(80, 360, 90, 16);

        jLabel6.setText("Password");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(430, 130, 70, 16);
        jPanel1.add(txtnohp);
        txtnohp.setBounds(190, 360, 227, 24);
        jPanel1.add(txtnama);
        txtnama.setBounds(190, 124, 227, 30);
        jPanel1.add(txtemail);
        txtemail.setBounds(190, 160, 227, 30);
        jPanel1.add(txtalamat);
        txtalamat.setBounds(190, 250, 227, 74);

        jLabel9.setIcon(new javax.swing.ImageIcon("D:\\Pradita\\Semester 2\\Pelajaran\\Pemrograman Dasar\\Untitled-1aaa-01.png")); // NOI18N
        jPanel1.add(jLabel9);
        jLabel9.setBounds(480, 240, 179, 139);
        jPanel1.add(txtpassword);
        txtpassword.setBounds(510, 122, 194, 30);

        jLabel1.setText("Nama");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(80, 130, 70, 16);

        jcbstatus.setBackground(new java.awt.Color(240, 240, 240));
        jcbstatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "Pasien" }));
        jcbstatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbstatusActionPerformed(evt);
            }
        });
        jPanel1.add(jcbstatus);
        jcbstatus.setBounds(190, 400, 227, 26);

        jLabel2.setText("E-mail");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(80, 170, 100, 16);

        btnregister.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rekammedis/plus.png"))); // NOI18N
        btnregister.setText("Register");
        btnregister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnregisterActionPerformed(evt);
            }
        });
        jPanel1.add(btnregister);
        btnregister.setBounds(80, 460, 300, 50);

        jLabel3.setText("Gender");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(80, 210, 100, 16);

        btncancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rekammedis/multiply.png"))); // NOI18N
        btncancel.setText("Cancel");
        btncancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelActionPerformed(evt);
            }
        });
        jPanel1.add(btncancel);
        btncancel.setBounds(420, 460, 310, 48);

        jLabel4.setText("Alamat");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(80, 280, 90, 16);

        jcbgender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "L", "P" }));
        jPanel1.add(jcbgender);
        jcbgender.setBounds(190, 200, 50, 26);

        jLabel5.setText("Status");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(80, 400, 100, 16);

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel7.setText("Form Register");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(320, 30, 125, 24);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btncancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btncancelActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
        //nonaktif();
        bersih();
    }//GEN-LAST:event_formWindowActivated

    private void btnregisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnregisterActionPerformed
        // TODO add your handling code here:
        if (isi == true){
            simpan();
            close();
            FormLogin n = new FormLogin();
            n.setVisible(true);
        }
        
        
    }//GEN-LAST:event_btnregisterActionPerformed

    private void jcbstatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbstatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbstatusActionPerformed

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
            java.util.logging.Logger.getLogger(FormRegister.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormRegister.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormRegister.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormRegister.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormRegister().setVisible(true);
            }
        });
    }
    
 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btncancel;
    private javax.swing.JButton btnregister;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JComboBox<String> jcbgender;
    private javax.swing.JComboBox<String> jcbstatus;
    private javax.swing.JTextField txtalamat;
    private javax.swing.JTextField txtemail;
    private javax.swing.JTextField txtnama;
    private javax.swing.JTextField txtnohp;
    private javax.swing.JPasswordField txtpassword;
    // End of variables declaration//GEN-END:variables
}
