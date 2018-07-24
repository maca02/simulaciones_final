package front;

import control.ControladorSimulacion;
import control.VectorEstado;
import front.tablemodel.VectorEstadoTableModel;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Action;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.Position;
import javax.swing.text.Segment;
import model.Configuracion;
import model.VectorEstadoUI;
import objects.Cliente;

public class VentanaPrincipal extends javax.swing.JFrame {

    private ControladorSimulacion controlador;
    private VectorEstadoTableModel model;
    private JTable tabla;
    private PopUpClientes popUpClientes;

    public VentanaPrincipal(ControladorSimulacion controlador) {
        this.controlador = controlador;
        popUpClientes = new PopUpClientes();
        initComponents();
        crearTabla();
        setearModeloDeTextos();
        setear_defecto();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        txt_clientes_atendidos = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        txt_clientes_perdidos = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        txt_clientes_que_volviero = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        btn_simular = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtMinutosASimular = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtMinutoDesde = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtIteracionesAMostrar = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtLlegadaClienteMedia = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtTiempoAtencionDesde = new javax.swing.JTextField();
        txtTiempoAtencionHasta = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jButton1 = new javax.swing.JButton();
        btn_setear = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("FINAL DE SIMULACIONES");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("TOLOZA, Macarena Rocio");

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Resultados estadisticos"));

        txt_clientes_atendidos.setEditable(false);
        txt_clientes_atendidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_clientes_atendidosActionPerformed(evt);
            }
        });

        jLabel25.setText("Cantidad de clientes Atendidos");

        txt_clientes_perdidos.setEditable(false);
        txt_clientes_perdidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_clientes_perdidosActionPerformed(evt);
            }
        });

        jLabel29.setText("Cantidad de clientes perdidos por exceso de fila");

        txt_clientes_que_volviero.setEditable(false);

        jLabel27.setText("Cantidad de clientes perdidos por tardanza en el servicio");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel25)
                .addGap(18, 18, 18)
                .addComponent(txt_clientes_atendidos, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77)
                .addComponent(jLabel29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_clientes_perdidos, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_clientes_que_volviero, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_clientes_atendidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25)
                    .addComponent(jLabel29)
                    .addComponent(txt_clientes_perdidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_clientes_que_volviero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27))
                .addGap(60, 60, 60))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(254, 254, 254))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(143, 143, 143))
        );

        btn_simular.setText("Simular");
        btn_simular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simularActionPerformed(evt);
            }
        });

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setText("Llegan clientes a solicitar un servicio siguiendo una distribución exponencial con una media de 5 min entre llegadas.\nHay una sola cabina de atención a los clientes; el tiempo de atención de cada persona sigue una distribución uniforme entre 7.5 y 10.5  minutos.\nSi la persona lleva más de 20 minutos esperando a ser atendida, saldrá de la fila y regresará al cabo de una hora a intentarlo de nuevo. Además, las personas que se han \nretirado de la fila y han regresado, vuelven con una prioridad de atención, es decir, serán atendidas primero que aquellos clientes que lo intentan por primera vez. \nSi el cliente, después de regresar, vuelve a esperar 20 minutos a ser atendido, se retirará y no volverá. Además, si hay diez personas en espera, los clientes que llegan se \nretirarán inmediatamente.\n\na)\t¿Cuántos clientes atendidos en diez (10) horas?\nb)\t¿Cuántos se pierden por la tardanza en el servicio? (los que vuelven y se van de nuevo)\nc)\t¿Cuántos se pierden por exceso de fila?\n\n");
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1701, Short.MAX_VALUE)
                .addGap(68, 68, 68))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE)
                .addGap(39, 39, 39))
        );

        jTabbedPane1.addTab("Enunciado", jPanel5);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Configuracion inicial", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP));

        jLabel4.setText("Tiempo a simular: (en minutos)");

        txtMinutosASimular.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtMinutosASimular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMinutosASimularActionPerformed(evt);
            }
        });

        jLabel5.setText("Minuto para mostrar vector desde: ");

        jLabel6.setText("Cantidad de iteraciones a mostrar: ");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMinutosASimular, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtMinutoDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtIteracionesAMostrar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtMinutosASimular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtMinutoDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txtIteracionesAMostrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Llegada Cliente"));

        jLabel7.setText("Distribución exponencial");

        jLabel8.setText("Media:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtLlegadaClienteMedia, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtLlegadaClienteMedia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Demora Atencion"));

        jLabel19.setText("Distribución Uniforme");

        jLabel20.setText("Desde:");

        jLabel21.setText("Hasta:");

        txtTiempoAtencionDesde.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        txtTiempoAtencionHasta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTiempoAtencionHastaActionPerformed(evt);
            }
        });

        jLabel22.setText("minutos");

        jLabel23.setText("minutos");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20)
                            .addComponent(jLabel21))
                        .addGap(38, 38, 38)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(txtTiempoAtencionHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel23))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(txtTiempoAtencionDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel22)))))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19)
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txtTiempoAtencionDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22))
                .addGap(30, 30, 30)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(txtTiempoAtencionHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(118, 118, 118)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(702, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(107, 107, 107)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(82, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Configuracion", jPanel3);

        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane3.setViewportBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTabbedPane1.addTab("Vector Estado", null, jScrollPane3, "");

        jButton1.setText("Mostrar tabla clientes");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btn_setear.setText("Setear valores Por Defecto");
        btn_setear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_setearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(btn_setear)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_simular)
                        .addGap(13, 13, 13)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jTabbedPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(btn_simular)
                    .addComponent(btn_setear))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_simularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simularActionPerformed
        try {
            actualizarConfiguracion();
        } catch (InputException ie) {
            JOptionPane.showMessageDialog(null, ie.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (validarMinutoDesdeMayorACantMinutosASimular()) {
            JOptionPane.showMessageDialog(null, "El minuto desde no puede ser mayor a la cantidad de minutos a simular.", "Error", JOptionPane.INFORMATION_MESSAGE);
        } else if (Integer.parseInt(txtIteracionesAMostrar.getText()) <= 0) {
            JOptionPane.showMessageDialog(null, "Cantidad de iteraciones no puede ser 0.", "Error", JOptionPane.INFORMATION_MESSAGE);
        } else {
            model.setDatos(new ArrayList<>());
            controlador.simular();
            calcular_estadisticas();
            SwingUtilities.invokeLater(() -> {
                jTabbedPane1.setSelectedIndex(2);
            });
        }
    }//GEN-LAST:event_btn_simularActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        popUpClientes.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txt_clientes_perdidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_clientes_perdidosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_clientes_perdidosActionPerformed

    private void txt_clientes_atendidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_clientes_atendidosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_clientes_atendidosActionPerformed

    private void btn_setearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_setearActionPerformed
        // TODO add your handling code here:
        
        Configuracion config = Configuracion.getConfiguracionPorDefecto();
        txtMinutosASimular.setText("" + config.getMinutosASimular());
        txtIteracionesAMostrar.setText("" + config.getIteracionesAMostrar());
        txtMinutoDesde.setText("" + config.getMinutoDesde());
        txtTiempoAtencionDesde.setText("" + config.getTiempoAtencionDesde());
        txtTiempoAtencionHasta.setText("" + config.getTiempoAtencionHasta());
        txtLlegadaClienteMedia.setText("" + config.getMediaLlegadaClientes());
    }//GEN-LAST:event_btn_setearActionPerformed

    private void txtTiempoAtencionHastaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTiempoAtencionHastaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTiempoAtencionHastaActionPerformed

    private void txtMinutosASimularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMinutosASimularActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMinutosASimularActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_setear;
    private javax.swing.JButton btn_simular;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField txtIteracionesAMostrar;
    private javax.swing.JTextField txtLlegadaClienteMedia;
    private javax.swing.JTextField txtMinutoDesde;
    private javax.swing.JTextField txtMinutosASimular;
    private javax.swing.JTextField txtTiempoAtencionDesde;
    private javax.swing.JTextField txtTiempoAtencionHasta;
    private javax.swing.JTextField txt_clientes_atendidos;
    private javax.swing.JTextField txt_clientes_perdidos;
    private javax.swing.JTextField txt_clientes_que_volviero;
    // End of variables declaration//GEN-END:variables

    private boolean validarMinutoDesdeMayorACantMinutosASimular() {
        return (Double.parseDouble(txtMinutoDesde.getText()) > Double.parseDouble(txtMinutosASimular.getText()));
    }

    private void crearTabla() {
        /**
         * Crear variable de clase para JTable Crear variable de clase para
         * VectorEstadoTableModel
         */
        model = new VectorEstadoTableModel();
        tabla = new JTable(model);

        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jScrollPane3.setViewportView(tabla);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        model.setColumnsWidth(tabla.getColumnModel());
        //Ask to be notified of selection changes.
        ListSelectionModel rowSM = tabla.getSelectionModel();
        rowSM.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                //Ignore extra messages.
                if (e.getValueIsAdjusting()) {
                    return;
                }

                ListSelectionModel lsm
                        = (ListSelectionModel) e.getSource();

                if (lsm.isSelectionEmpty()) {
                    //...//no rows are selected
                } else {
                    int selectedRow = lsm.getMinSelectionIndex();
                    if (model.getDato(selectedRow) != null) {
                        setClientesModel(model.getDato(selectedRow).getClientes());
                    }

                }
            }

            private void setClientesModel(List<Cliente> clientes) {
                popUpClientes.setClientes(clientes);
            }
        });
    }

    public void setearModelo(List<VectorEstadoUI> modelo) {
        model.setDatos(modelo);
        model.fireTableDataChanged();
    }

    private void setear_defecto() {
        Configuracion config = Configuracion.getConfiguracionPorDefecto();
        txtMinutosASimular.setText("" + config.getMinutosASimular());
        txtIteracionesAMostrar.setText("" + config.getIteracionesAMostrar());
        txtMinutoDesde.setText("" + config.getMinutoDesde());
        txtTiempoAtencionDesde.setText("" + config.getTiempoAtencionDesde());
        txtTiempoAtencionHasta.setText("" + config.getTiempoAtencionHasta());
        txtLlegadaClienteMedia.setText("" + config.getMediaLlegadaClientes());
    }

    private void calcular_estadisticas() {
        DecimalFormat formater = new DecimalFormat("0.0000");

        txt_clientes_atendidos.setText(String.valueOf(formater.format(controlador.getEstadisticas().getClientes_atendidos())));
        txt_clientes_que_volviero.setText(String.valueOf(formater.format(controlador.getEstadisticas().getClientes_que_volvieron())));
        txt_clientes_perdidos.setText(String.valueOf(formater.format(controlador.getEstadisticas().getClientes_perdidos())));
    }

    private void setearModeloDeTextos() {
        DoubleInputVerifier doubleVer = new DoubleInputVerifier();
        txtLlegadaClienteMedia.setInputVerifier(doubleVer);
        txtTiempoAtencionDesde.setInputVerifier(doubleVer);
        txtTiempoAtencionHasta.setInputVerifier(doubleVer);
        IntegerInputVerifier intVer = new IntegerInputVerifier();
        txtIteracionesAMostrar.setInputVerifier(intVer);
        txtMinutoDesde.setInputVerifier(intVer);
        txtMinutosASimular.setInputVerifier(intVer);

    }

    private void actualizarConfiguracion() throws InputException {

        try {
            if (Double.isNaN(Double.parseDouble(txtTiempoAtencionDesde.getText()))
                    || Double.parseDouble(txtTiempoAtencionDesde.getText()) < 0) {
                throw new InputException("Tiempo atención desde invalido");
            }
        } catch (NumberFormatException nfe) {
            throw new InputException("Tiempo atención desde invalido");
        }
        Double tiempoInscripcionDesde = Double.parseDouble(txtTiempoAtencionDesde.getText());

        try {
            if (Double.isNaN(Double.parseDouble(txtTiempoAtencionHasta.getText()))
                    || Double.parseDouble(txtTiempoAtencionHasta.getText()) < 0) {
                throw new InputException("Tiempo atención hasta invalido");
            }
        } catch (NumberFormatException nfe) {
            throw new InputException("Tiempo atención hasta invalido");
        }
        Double tiempoInscripcionHasta = Double.parseDouble(txtTiempoAtencionHasta.getText());

        if (tiempoInscripcionHasta <= tiempoInscripcionDesde) {
            throw new InputException("Tiempo atención el desde debe ser menor al hasta");
        }

        try {
            if (Double.isNaN(Double.parseDouble(txtLlegadaClienteMedia.getText()))
                    || Double.parseDouble(txtLlegadaClienteMedia.getText()) < 0) {
                throw new InputException("Media llegada clientes invalido");
            }
        } catch (NumberFormatException nfe) {
            throw new InputException("Media llegada clientes invalido");
        }
        Double mediaLlegadaAlumnos = Double.parseDouble(txtLlegadaClienteMedia.getText());

        try {
            if (Integer.parseInt(txtMinutosASimular.getText()) < 0) {
                throw new InputException("Minutos a sumular invalidos");
            }
        } catch (NumberFormatException nfe) {
            throw new InputException("Minutos a sumular invalidos");
        }
        int minutosASimular = Integer.parseInt(txtMinutosASimular.getText());

        try {
            if (Integer.parseInt(txtMinutoDesde.getText()) < 0) {
                throw new InputException("Minuto desde invalido");
            }
        } catch (NumberFormatException nfe) {
            throw new InputException("Minuto desde invalido");
        }
        int minutoDesde = Integer.parseInt(txtMinutoDesde.getText());

        try {
            if (Integer.parseInt(txtIteracionesAMostrar.getText()) < 0) {
                throw new InputException("Iteraciones a mostrar invalidos");
            }
        } catch (NumberFormatException nfe) {
            throw new InputException("Iteraciones a mostrar invalidos");
        }
        int iteracionesAMostrar = Integer.parseInt(txtIteracionesAMostrar.getText());

        Configuracion conf = Configuracion.getConfiguracion();
        conf.setIteracionesAMostrar(iteracionesAMostrar);
        conf.setMediaLlegadaClientes(mediaLlegadaAlumnos);
        conf.setMinutoDesde(minutoDesde);
        conf.setMinutosASimular(minutosASimular);
        conf.setTiempoAtencionDesde(tiempoInscripcionDesde);
        conf.setTiempoAtencionHasta(tiempoInscripcionHasta);
        //Successs
    }

    private static class InputException extends Exception {

        public InputException(String msg) {
            super(msg);
        }
    }

    private class DoubleInputVerifier extends InputVerifier {

        @Override
        public boolean verify(JComponent input) {
            if (input instanceof JTextField) {
                JTextField txt = (JTextField) input;
                try {
                    Double val = obtenerValorDeCampo(txt);

                    if (val > 0) {
                        return true;
                    }
                } catch (NumberFormatException nfe) {
                    return false;
                }
            }
            return false;
        }

        @Override
        public boolean shouldYieldFocus(JComponent input) {
            return true;
        }
    }

    private Double obtenerValorDeCampo(JTextField txt) {
        String entrada = txt.getText();
        if (entrada != null && !entrada.isEmpty()) {
            return Double.parseDouble(entrada);
        }
        throw new NumberFormatException(entrada);
    }

    private class IntegerInputVerifier extends InputVerifier {

        @Override
        public boolean verify(JComponent input) {
            if (input instanceof JTextField) {
                JTextField txt = (JTextField) input;
                try {
                    Integer val = obtenerValorDeCampoEnInt(txt);

                    if (val > 0) {
                        return true;
                    }
                } catch (NumberFormatException nfe) {
                    return false;
                }
            }
            return false;
        }

        @Override
        public boolean shouldYieldFocus(JComponent input) {
            return true;
        }
    }

    private Integer obtenerValorDeCampoEnInt(JTextField txt) {
        String entrada = txt.getText();
        if (entrada != null && !entrada.isEmpty()) {
            return Integer.parseInt(entrada);
        }
        throw new NumberFormatException(entrada);
    }
}
