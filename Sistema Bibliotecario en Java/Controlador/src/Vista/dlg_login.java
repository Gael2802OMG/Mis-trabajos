
package Vista;

public class dlg_login extends javax.swing.JDialog {


    public dlg_login() {
        initComponents();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtIngresaUsu = new javax.swing.JTextField();
        txtIngresaContra = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnIngresaIni = new javax.swing.JButton();
        btnRegistini = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        txtIngresaUsu.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 0, 0)));
        getContentPane().add(txtIngresaUsu);
        txtIngresaUsu.setBounds(100, 90, 158, 18);

        txtIngresaContra.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 0, 0)));
        getContentPane().add(txtIngresaContra);
        txtIngresaContra.setBounds(90, 160, 190, 20);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel2.setText("Ingresa tu matricula");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(110, 60, 150, 21);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel3.setText("Ingresa tu contraseña");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(110, 130, 160, 21);

        btnIngresaIni.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnIngresaIni.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/iniciar-sesion.png"))); // NOI18N
        btnIngresaIni.setText("Ingresar");
        btnIngresaIni.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 153)));
        getContentPane().add(btnIngresaIni);
        btnIngresaIni.setBounds(30, 200, 90, 30);

        btnRegistini.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnRegistini.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/nota.png"))); // NOI18N
        btnRegistini.setText("Registrarse");
        btnRegistini.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 153)));
        getContentPane().add(btnRegistini);
        btnRegistini.setBounds(130, 200, 100, 30);

        btnSalir.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/boton.png"))); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 153)));
        getContentPane().add(btnSalir);
        btnSalir.setBounds(250, 200, 100, 30);

        jPanel1.setBackground(new java.awt.Color(204, 0, 0));
        jPanel1.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 255, 204));
        jLabel1.setText("~~~~ INICIO DE SESION ~~~~ ");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(30, 10, 360, 32);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 410, 50);

        jPanel2.setBackground(new java.awt.Color(204, 0, 0));
        getContentPane().add(jPanel2);
        jPanel2.setBounds(0, 260, 400, 40);

        pack();
    }// </editor-fold>//GEN-END:initComponents

 
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
            java.util.logging.Logger.getLogger(dlg_login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(dlg_login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(dlg_login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dlg_login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

      
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnIngresaIni;
    public javax.swing.JButton btnRegistini;
    public javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    public javax.swing.JPasswordField txtIngresaContra;
    public javax.swing.JTextField txtIngresaUsu;
    // End of variables declaration//GEN-END:variables
}
