/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package Vista;

/**
 *
 * @author bmfil
 */
public class dlg_registro extends javax.swing.JDialog {

    /**
     * Creates new form registro
     */
    public dlg_registro() {
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

        jLabel2 = new javax.swing.JLabel();
        txtNomRegis = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtElectro = new javax.swing.JTextField();
        txtDirecRegi = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnLimpiar = new javax.swing.JButton();
        btnVolverAu = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtMatricularegis = new javax.swing.JTextField();
        btnRegistrarseRegis = new javax.swing.JButton();
        txtTeleRegi = new javax.swing.JTextField();
        btnBuscarRegis = new javax.swing.JButton();
        btnDeshabilitarRegis = new javax.swing.JButton();
        cmbCargo = new javax.swing.JComboBox<>();
        txtContrRegis = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel2.setText("Nombre");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(20, 100, 70, 16);

        txtNomRegis.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(102, 0, 102)));
        getContentPane().add(txtNomRegis);
        txtNomRegis.setBounds(20, 120, 183, 18);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel3.setText("Correo electronico");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(20, 230, 160, 20);

        txtElectro.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(153, 0, 153)));
        getContentPane().add(txtElectro);
        txtElectro.setBounds(20, 250, 183, 20);

        txtDirecRegi.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(153, 0, 153)));
        txtDirecRegi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDirecRegiActionPerformed(evt);
            }
        });
        getContentPane().add(txtDirecRegi);
        txtDirecRegi.setBounds(20, 350, 183, 20);

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel5.setText("Telefono");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(20, 280, 100, 20);

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel6.setText("Dirección");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(20, 330, 160, 21);

        jPanel1.setBackground(new java.awt.Color(255, 51, 0));
        jPanel1.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText(" °°°° REGISTRO °°°°");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(90, 10, 230, 32);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 400, 50);

        jPanel2.setBackground(new java.awt.Color(255, 51, 0));
        getContentPane().add(jPanel2);
        jPanel2.setBounds(0, 390, 400, 50);

        btnLimpiar.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/escoba.png"))); // NOI18N
        btnLimpiar.setText("Limpiar");
        btnLimpiar.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(51, 0, 255)));
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        getContentPane().add(btnLimpiar);
        btnLimpiar.setBounds(260, 160, 130, 40);

        btnVolverAu.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnVolverAu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/volver.png"))); // NOI18N
        btnVolverAu.setText("Volver");
        btnVolverAu.setBorder(new javax.swing.border.MatteBorder(null));
        btnVolverAu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverAuActionPerformed(evt);
            }
        });
        getContentPane().add(btnVolverAu);
        btnVolverAu.setBounds(290, 300, 100, 30);

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/biblioteca (1).png"))); // NOI18N
        getContentPane().add(jLabel7);
        jLabel7.setBounds(360, 60, 30, 20);

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel8.setText("Matricula");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(20, 50, 140, 20);

        txtMatricularegis.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(153, 0, 153)));
        getContentPane().add(txtMatricularegis);
        txtMatricularegis.setBounds(20, 70, 183, 20);

        btnRegistrarseRegis.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnRegistrarseRegis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/agregar.png"))); // NOI18N
        btnRegistrarseRegis.setText("Registrarse");
        btnRegistrarseRegis.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(51, 0, 255)));
        btnRegistrarseRegis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarseRegisActionPerformed(evt);
            }
        });
        getContentPane().add(btnRegistrarseRegis);
        btnRegistrarseRegis.setBounds(260, 110, 130, 40);

        txtTeleRegi.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(153, 0, 153)));
        txtTeleRegi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTeleRegiActionPerformed(evt);
            }
        });
        getContentPane().add(txtTeleRegi);
        txtTeleRegi.setBounds(20, 300, 183, 20);

        btnBuscarRegis.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnBuscarRegis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/buscar.png"))); // NOI18N
        btnBuscarRegis.setText("Buscar");
        btnBuscarRegis.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(51, 0, 255)));
        btnBuscarRegis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarRegisActionPerformed(evt);
            }
        });
        getContentPane().add(btnBuscarRegis);
        btnBuscarRegis.setBounds(210, 60, 90, 30);

        btnDeshabilitarRegis.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnDeshabilitarRegis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/portapapeles.png"))); // NOI18N
        btnDeshabilitarRegis.setText("Deshabilitar");
        btnDeshabilitarRegis.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(51, 0, 255)));
        btnDeshabilitarRegis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeshabilitarRegisActionPerformed(evt);
            }
        });
        getContentPane().add(btnDeshabilitarRegis);
        btnDeshabilitarRegis.setBounds(260, 210, 130, 40);

        cmbCargo.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        cmbCargo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cargo a Ejercer", "Bibliotecaria", "Asistente" }));
        cmbCargo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 153)));
        getContentPane().add(cmbCargo);
        cmbCargo.setBounds(20, 200, 180, 22);

        txtContrRegis.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(102, 0, 102)));
        getContentPane().add(txtContrRegis);
        txtContrRegis.setBounds(20, 170, 183, 18);

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel4.setText("Contraseña");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(20, 150, 90, 16);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtDirecRegiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDirecRegiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDirecRegiActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnVolverAuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverAuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVolverAuActionPerformed

    private void btnRegistrarseRegisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarseRegisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRegistrarseRegisActionPerformed

    private void txtTeleRegiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTeleRegiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTeleRegiActionPerformed

    private void btnBuscarRegisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarRegisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarRegisActionPerformed

    private void btnDeshabilitarRegisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeshabilitarRegisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDeshabilitarRegisActionPerformed

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
            java.util.logging.Logger.getLogger(dlg_registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(dlg_registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(dlg_registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dlg_registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnBuscarRegis;
    public javax.swing.JButton btnDeshabilitarRegis;
    public javax.swing.JButton btnLimpiar;
    public javax.swing.JButton btnRegistrarseRegis;
    public javax.swing.JButton btnVolverAu;
    public javax.swing.JComboBox<String> cmbCargo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    public javax.swing.JTextField txtContrRegis;
    public javax.swing.JTextField txtDirecRegi;
    public javax.swing.JTextField txtElectro;
    public javax.swing.JTextField txtMatricularegis;
    public javax.swing.JTextField txtNomRegis;
    public javax.swing.JTextField txtTeleRegi;
    // End of variables declaration//GEN-END:variables
}