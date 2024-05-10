/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package Vista;

/**
 *
 * @author bmfil
 */
public class dlg_Autor extends javax.swing.JDialog {

    /**
     * Creates new form Autor
     */
    public dlg_Autor() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jdtFechaau = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        txtNacionalidadau = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtDescripcionau = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtNombreau = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnNuevoau = new javax.swing.JButton();
        btnLimpiarau = new javax.swing.JButton();
        btnAgregarau = new javax.swing.JButton();
        btnVolverAu = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnBuscarau = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        btnCancelarAu = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(jList1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(204, 255, 255));
        getContentPane().setLayout(null);

        jdtFechaau.setEnabled(false);
        getContentPane().add(jdtFechaau);
        jdtFechaau.setBounds(170, 170, 180, 30);

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel4.setText("Nacionalidad");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(10, 220, 100, 30);

        txtNacionalidadau.setEnabled(false);
        getContentPane().add(txtNacionalidadau);
        txtNacionalidadau.setBounds(110, 230, 156, 20);

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel5.setText("Descripcion breve del autor");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(30, 270, 220, 30);

        txtDescripcionau.setEnabled(false);
        getContentPane().add(txtDescripcionau);
        txtDescripcionau.setBounds(20, 300, 330, 20);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel2.setText("Nombre");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(20, 130, 70, 30);
        getContentPane().add(txtNombreau);
        txtNombreau.setBounds(100, 130, 156, 20);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel3.setText("Fecha de nacimiento");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(10, 170, 150, 30);

        btnNuevoau.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnNuevoau.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/agregar.png"))); // NOI18N
        btnNuevoau.setText("Nuevo");
        btnNuevoau.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(51, 0, 255)));
        btnNuevoau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoauActionPerformed(evt);
            }
        });
        getContentPane().add(btnNuevoau);
        btnNuevoau.setBounds(380, 110, 108, 40);

        btnLimpiarau.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnLimpiarau.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/escoba.png"))); // NOI18N
        btnLimpiarau.setText("LImpiar");
        btnLimpiarau.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 153)));
        btnLimpiarau.setEnabled(false);
        getContentPane().add(btnLimpiarau);
        btnLimpiarau.setBounds(380, 210, 110, 35);

        btnAgregarau.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnAgregarau.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/cliente.png"))); // NOI18N
        btnAgregarau.setText("Agregar ");
        btnAgregarau.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 204)));
        btnAgregarau.setEnabled(false);
        getContentPane().add(btnAgregarau);
        btnAgregarau.setBounds(380, 160, 110, 35);

        btnVolverAu.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnVolverAu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/volver.png"))); // NOI18N
        btnVolverAu.setText("Volver");
        btnVolverAu.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 153)));
        btnVolverAu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverAuActionPerformed(evt);
            }
        });
        getContentPane().add(btnVolverAu);
        btnVolverAu.setBounds(380, 300, 110, 30);

        jPanel1.setBackground(new java.awt.Color(153, 153, 255));
        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("<-----------AUTOR------------> ");
        jLabel1.setToolTipText("");
        jPanel1.add(jLabel1);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 510, 80);

        jPanel3.setBackground(new java.awt.Color(153, 153, 255));
        jPanel3.setForeground(new java.awt.Color(204, 204, 255));
        getContentPane().add(jPanel3);
        jPanel3.setBounds(0, 350, 510, 50);

        btnBuscarau.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnBuscarau.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/buscar.png"))); // NOI18N
        btnBuscarau.setText("Buscar ");
        btnBuscarau.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(51, 0, 255)));
        getContentPane().add(btnBuscarau);
        btnBuscarau.setBounds(260, 130, 90, 20);

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/shakespeare.png"))); // NOI18N
        getContentPane().add(jLabel6);
        jLabel6.setBounds(20, 80, 30, 40);

        btnCancelarAu.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnCancelarAu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/prohibicion.png"))); // NOI18N
        btnCancelarAu.setText("Cancelar");
        btnCancelarAu.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 153)));
        btnCancelarAu.setEnabled(false);
        btnCancelarAu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarAuActionPerformed(evt);
            }
        });
        getContentPane().add(btnCancelarAu);
        btnCancelarAu.setBounds(380, 260, 110, 30);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVolverAuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverAuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVolverAuActionPerformed

    private void btnNuevoauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoauActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevoauActionPerformed

    private void btnCancelarAuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarAuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelarAuActionPerformed

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
            java.util.logging.Logger.getLogger(dlg_Autor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(dlg_Autor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(dlg_Autor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dlg_Autor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnAgregarau;
    public javax.swing.JButton btnBuscarau;
    public javax.swing.JButton btnCancelarAu;
    public javax.swing.JButton btnLimpiarau;
    public javax.swing.JButton btnNuevoau;
    public javax.swing.JButton btnVolverAu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    public com.toedter.calendar.JDateChooser jdtFechaau;
    public javax.swing.JTextField txtDescripcionau;
    public javax.swing.JTextField txtNacionalidadau;
    public javax.swing.JTextField txtNombreau;
    // End of variables declaration//GEN-END:variables
}
