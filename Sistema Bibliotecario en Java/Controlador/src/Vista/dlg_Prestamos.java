/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package Vista;

/**
 *
 * @author Equipo
 */
public class dlg_Prestamos extends javax.swing.JDialog {

    /**
     * Creates new form Prestamos
     */
    public dlg_Prestamos() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jdFechaini = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        jdFechaFi = new com.toedter.calendar.JDateChooser();
        btnBuscarPre = new javax.swing.JButton();
        btnLimpre = new javax.swing.JButton();
        btnCancelarPre = new javax.swing.JButton();
        btnVolverALi = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnNuevoPre = new javax.swing.JButton();
        btnInserPre = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtCodigoPres = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cmbIdEst = new javax.swing.JComboBox<>();
        cmbIdPers = new javax.swing.JComboBox<>();
        cmbLibrospres = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(204, 255, 255));
        getContentPane().setLayout(null);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel3.setText("Estudiante");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(20, 130, 140, 30);

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel6.setText("Personal");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(20, 190, 112, 30);

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel5.setText("Fecha inicial");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(170, 190, 100, 23);

        jdFechaini.setEnabled(false);
        getContentPane().add(jdFechaini);
        jdFechaini.setBounds(160, 220, 110, 22);

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel7.setText("Fecha final");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(170, 250, 90, 21);

        jdFechaFi.setEnabled(false);
        getContentPane().add(jdFechaFi);
        jdFechaFi.setBounds(160, 280, 110, 22);

        btnBuscarPre.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnBuscarPre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/buscar.png"))); // NOI18N
        btnBuscarPre.setText("Buscar");
        btnBuscarPre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 0)));
        btnBuscarPre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarPreActionPerformed(evt);
            }
        });
        getContentPane().add(btnBuscarPre);
        btnBuscarPre.setBounds(140, 100, 110, 30);

        btnLimpre.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnLimpre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/escoba.png"))); // NOI18N
        btnLimpre.setText("Limpiar");
        btnLimpre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 0)));
        getContentPane().add(btnLimpre);
        btnLimpre.setBounds(30, 320, 90, 30);

        btnCancelarPre.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnCancelarPre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/portapapeles.png"))); // NOI18N
        btnCancelarPre.setText("Cancelar");
        btnCancelarPre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 0)));
        btnCancelarPre.setEnabled(false);
        getContentPane().add(btnCancelarPre);
        btnCancelarPre.setBounds(150, 320, 100, 30);

        btnVolverALi.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnVolverALi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/girar-a-la-izquierda.png"))); // NOI18N
        btnVolverALi.setText("Volver");
        btnVolverALi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 0)));
        getContentPane().add(btnVolverALi);
        btnVolverALi.setBounds(280, 320, 90, 30);

        jPanel1.setBackground(new java.awt.Color(18, 164, 173));
        jPanel1.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("---- PRESTAMOS ----");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(90, 10, 227, 32);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 420, 50);

        jPanel2.setBackground(new java.awt.Color(3, 156, 148));
        getContentPane().add(jPanel2);
        jPanel2.setBounds(0, 360, 420, 40);

        btnNuevoPre.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnNuevoPre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/cliente.png"))); // NOI18N
        btnNuevoPre.setText("nuevo");
        btnNuevoPre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 0)));
        btnNuevoPre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoPreActionPerformed(evt);
            }
        });
        getContentPane().add(btnNuevoPre);
        btnNuevoPre.setBounds(290, 140, 110, 30);

        btnInserPre.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnInserPre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/cerrar-sesion.png"))); // NOI18N
        btnInserPre.setText("Insertar");
        btnInserPre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 0)));
        btnInserPre.setEnabled(false);
        getContentPane().add(btnInserPre);
        btnInserPre.setBounds(290, 180, 110, 30);

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel8.setText("Libro");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(20, 250, 112, 30);
        getContentPane().add(txtCodigoPres);
        txtCodigoPres.setBounds(20, 110, 112, 20);

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel4.setText("Codigo");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(20, 80, 60, 30);

        cmbIdEst.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        cmbIdEst.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID Estudiante" }));
        cmbIdEst.setEnabled(false);
        getContentPane().add(cmbIdEst);
        cmbIdEst.setBounds(20, 160, 140, 27);

        cmbIdPers.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        cmbIdPers.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID Personal", " " }));
        cmbIdPers.setEnabled(false);
        getContentPane().add(cmbIdPers);
        cmbIdPers.setBounds(20, 220, 130, 27);

        cmbLibrospres.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        cmbLibrospres.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID Libro" }));
        cmbLibrospres.setEnabled(false);
        getContentPane().add(cmbLibrospres);
        cmbLibrospres.setBounds(20, 280, 110, 22);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarPreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarPreActionPerformed

    private void btnNuevoPreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoPreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevoPreActionPerformed

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
            java.util.logging.Logger.getLogger(dlg_Prestamos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(dlg_Prestamos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(dlg_Prestamos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dlg_Prestamos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnBuscarPre;
    public javax.swing.JButton btnCancelarPre;
    public javax.swing.JButton btnInserPre;
    public javax.swing.JButton btnLimpre;
    public javax.swing.JButton btnNuevoPre;
    public javax.swing.JButton btnVolverALi;
    public javax.swing.JComboBox<String> cmbIdEst;
    public javax.swing.JComboBox<String> cmbIdPers;
    public javax.swing.JComboBox<String> cmbLibrospres;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    public com.toedter.calendar.JDateChooser jdFechaFi;
    public com.toedter.calendar.JDateChooser jdFechaini;
    public javax.swing.JTextField txtCodigoPres;
    // End of variables declaration//GEN-END:variables
}
