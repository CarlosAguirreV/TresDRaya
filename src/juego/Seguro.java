package juego;

/**
 * Este dialogo es para cuando alguna opcion necesita una confirmacion.
 *
 * @author C.A.V.
 */
public class Seguro extends java.awt.Dialog {

    private boolean respuesta;

    /**
     * Crea una nueva forma e inicializa los campos.
     */
    public Seguro(java.awt.Frame parent, boolean modal, String mensaje) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.etiquetaMensaje.setText(mensaje);
        this.respuesta = false;
    }

    /**
     * Devuelve una respuesta True o False.
     *
     * @return boolean
     */
    public boolean getRespuesta() {
        return this.respuesta;
    }

    /**
     * Cierra esta ventana.
     */
    public void cerrar() {
        this.dispose();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanelInferior5 = new javax.swing.JPanel();
        jPanelInferior6 = new javax.swing.JPanel();
        btnSi = new javax.swing.JButton();
        btnNo = new javax.swing.JButton();
        etiquetaMensaje = new javax.swing.JLabel();

        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));

        jPanel1.setBackground(new java.awt.Color(254, 254, 254));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(190, 190, 190)));
        jPanel1.setForeground(new java.awt.Color(254, 254, 254));

        jPanelInferior5.setBackground(new java.awt.Color(230, 140, 2));
        jPanelInferior5.setForeground(new java.awt.Color(203, 203, 203));

        javax.swing.GroupLayout jPanelInferior5Layout = new javax.swing.GroupLayout(jPanelInferior5);
        jPanelInferior5.setLayout(jPanelInferior5Layout);
        jPanelInferior5Layout.setHorizontalGroup(
            jPanelInferior5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 398, Short.MAX_VALUE)
        );
        jPanelInferior5Layout.setVerticalGroup(
            jPanelInferior5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 34, Short.MAX_VALUE)
        );

        jPanelInferior6.setBackground(new java.awt.Color(70, 150, 250));
        jPanelInferior6.setForeground(new java.awt.Color(203, 203, 203));

        btnSi.setFont(new java.awt.Font("Noto Sans", 1, 18)); // NOI18N
        btnSi.setText("Si");
        btnSi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiActionPerformed(evt);
            }
        });

        btnNo.setFont(new java.awt.Font("Noto Sans", 1, 18)); // NOI18N
        btnNo.setText("No");
        btnNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelInferior6Layout = new javax.swing.GroupLayout(jPanelInferior6);
        jPanelInferior6.setLayout(jPanelInferior6Layout);
        jPanelInferior6Layout.setHorizontalGroup(
            jPanelInferior6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInferior6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnSi, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnNo, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanelInferior6Layout.setVerticalGroup(
            jPanelInferior6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelInferior6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelInferior6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnNo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        etiquetaMensaje.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        etiquetaMensaje.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiquetaMensaje.setText("...");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelInferior5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanelInferior6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(etiquetaMensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanelInferior5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(etiquetaMensaje)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(jPanelInferior6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        etiquetaMensaje.getAccessibleContext().setAccessibleName("Mensaje de texto.");
        etiquetaMensaje.getAccessibleContext().setAccessibleDescription("Mensaje que muestra dos opciones Si o No.");

        add(jPanel1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Closes the dialog
     */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
    }//GEN-LAST:event_closeDialog

    private void btnSiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiActionPerformed
        this.respuesta = true;
        this.setVisible(false);
    }//GEN-LAST:event_btnSiActionPerformed

    private void btnNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNoActionPerformed
        this.respuesta = false;
        this.setVisible(false);
    }//GEN-LAST:event_btnNoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNo;
    private javax.swing.JButton btnSi;
    private javax.swing.JLabel etiquetaMensaje;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelInferior5;
    private javax.swing.JPanel jPanelInferior6;
    // End of variables declaration//GEN-END:variables
}
