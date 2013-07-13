/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cs.controlmoto.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import br.com.cs.controlmoto.domain.CadCredorDAO;
import br.com.cs.controlmoto.vo.CadCredorVO;
/**
 * Company Fictec Cons. Inf.
 * @since 06/09/2012
 * @version 1.0.6
 * @author Clovis
 */

public class CadCredor extends javax.swing.JInternalFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * Creates new form CadCredor
     */
    public CadCredor() {
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

        jpCampos = new javax.swing.JPanel();
        lblCodigo = new javax.swing.JLabel();
        lblRazaoSocial = new javax.swing.JLabel();
        lblCodigoCampo = new javax.swing.JLabel();
        txtRazaoSocial = new javax.swing.JTextField();
        jrbAtivo = new javax.swing.JRadioButton();
        jpBotoes = new javax.swing.JPanel();
        btoIncluir = new javax.swing.JButton();
        btoSalvar = new javax.swing.JButton();
        btoAtualizar = new javax.swing.JButton();
        jpLista = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setClosable(true);
        setMaximizable(true);
        setTitle("Cadastro de Credor");
        setToolTipText("Cadastro de Credor");
        setMaximumSize(new java.awt.Dimension(600, 400));
        setMinimumSize(new java.awt.Dimension(200, 300));
        setPreferredSize(new java.awt.Dimension(500, 300));
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameActivated(evt);
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        lblCodigo.setText("C�digo");

        lblRazaoSocial.setText("Raz�o Social");

        lblCodigoCampo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCodigoCampo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        lblCodigoCampo.setMaximumSize(new java.awt.Dimension(200, 20));
        lblCodigoCampo.setPreferredSize(new java.awt.Dimension(100, 20));

        txtRazaoSocial.setMaximumSize(new java.awt.Dimension(300, 20));

        jrbAtivo.setSelected(true);
        jrbAtivo.setText("Atiivo");
        jrbAtivo.setToolTipText("Se o credor estiver ativo esta opç�o estara selecionada");

        javax.swing.GroupLayout jpCamposLayout = new javax.swing.GroupLayout(jpCampos);
        jpCampos.setLayout(jpCamposLayout);
        jpCamposLayout.setHorizontalGroup(
            jpCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpCamposLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpCamposLayout.createSequentialGroup()
                        .addComponent(lblRazaoSocial)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtRazaoSocial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jpCamposLayout.createSequentialGroup()
                        .addGroup(jpCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jrbAtivo)
                            .addGroup(jpCamposLayout.createSequentialGroup()
                                .addComponent(lblCodigo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblCodigoCampo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 161, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jpCamposLayout.setVerticalGroup(
            jpCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpCamposLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCodigo)
                    .addComponent(lblCodigoCampo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblRazaoSocial)
                    .addComponent(txtRazaoSocial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jrbAtivo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btoIncluir.setText("Incluir");
        btoIncluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btoIncluirActionPerformed(evt);
            }
        });

        btoSalvar.setText("Salvar");
        btoSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btoSalvarActionPerformed(evt);
            }
        });

        btoAtualizar.setText("Atualizar");
        btoAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btoAtualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpBotoesLayout = new javax.swing.GroupLayout(jpBotoes);
        jpBotoes.setLayout(jpBotoesLayout);
        jpBotoesLayout.setHorizontalGroup(
            jpBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpBotoesLayout.createSequentialGroup()
                .addContainerGap(43, Short.MAX_VALUE)
                .addGroup(jpBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btoAtualizar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btoSalvar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btoIncluir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpBotoesLayout.setVerticalGroup(
            jpBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpBotoesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btoIncluir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btoSalvar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btoAtualizar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpLista.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Credores", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(0, 0, 204)));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "C�digo", "Raz�o Social", "Ativo"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getColumn(0).setMinWidth(60);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(100);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(150);
        jTable1.getColumnModel().getColumn(2).setMinWidth(30);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(40);
        jTable1.getColumnModel().getColumn(2).setMaxWidth(50);

        javax.swing.GroupLayout jpListaLayout = new javax.swing.GroupLayout(jpLista);
        jpLista.setLayout(jpListaLayout);
        jpListaLayout.setHorizontalGroup(
            jpListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpListaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpListaLayout.setVerticalGroup(
            jpListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpListaLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jpLista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jpCampos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jpBotoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jpBotoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpCampos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpLista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btoIncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btoIncluirActionPerformed
        CadCredorVO cdBean = null; CadCredorDAO cdDao = null;
        try {
            cdBean = new CadCredorVO();
            cdDao = new CadCredorDAO();
            cdDao.incluirCredor(cdBean);
            String id = String.valueOf(cdBean.getLblCodigoCampo());
            if(!id.isEmpty() && id != "null"){
                lblCodigoCampo.setText(String.valueOf( Integer.valueOf(cdBean.getLblCodigoCampo()) +1));
            }else{
                lblCodigoCampo.setText(String.valueOf(1));
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CadCredor.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                cdDao.closeCadCredorDAO();
                btoIncluir.setEnabled(false);btoSalvar.setEnabled(true);btoAtualizar.setEnabled(false);
            } catch ( SQLException | ClassNotFoundException ex) {
                Logger.getLogger(CadCredor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btoIncluirActionPerformed

    private void btoSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btoSalvarActionPerformed
        CadCredorVO cdBean = null; CadCredorDAO cdDao = null;
        try {
            cdBean = new CadCredorVO(); cdDao = new CadCredorDAO();
            
            cdBean.setLblCodigoCampo(lblCodigoCampo.getText());
            cdBean.setTxtRazaoSocial(txtRazaoSocial.getText());
            if(jrbAtivo.isSelected()){
                cdBean.setJrbAtivo("Sim");
            }else{
                cdBean.setJrbAtivo("N�o");
            }
            cdDao.salvarCredor(cdBean);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CadCredor.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                cdDao.closeCadCredorDAO();
                limpaCampos();
                atuazilaGrid();
                btoIncluir.setEnabled(true);btoSalvar.setEnabled(false);btoAtualizar.setEnabled(false);
            } catch ( SQLException | ClassNotFoundException ex) {
                Logger.getLogger(CadCredor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btoSalvarActionPerformed

    private void btoAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btoAtualizarActionPerformed
        CadCredorVO cdBean = null; CadCredorDAO cdDao = null;
        try {
            cdBean = new CadCredorVO(); cdDao = new CadCredorDAO();
            
            cdBean.setLblCodigoCampo(lblCodigoCampo.getText());
            cdBean.setTxtRazaoSocial(txtRazaoSocial.getText());
            if(jrbAtivo.isSelected()){
                cdBean.setJrbAtivo("Sim");
            }else{
                cdBean.setJrbAtivo("N�o");
            }
            cdDao.atualizarCredor(cdBean);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CadCredor.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                cdDao.closeCadCredorDAO();
                limpaCampos();
                atuazilaGrid();
                btoIncluir.setEnabled(true);btoSalvar.setEnabled(false);btoAtualizar.setEnabled(false);
            } catch ( SQLException | ClassNotFoundException ex) {
                Logger.getLogger(CadCredor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btoAtualizarActionPerformed

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        btoIncluir.setEnabled(true);btoSalvar.setEnabled(false);btoAtualizar.setEnabled(false);
        CadCredorVO cdBean = null; CadCredorDAO cdDao = null;
        List<CadCredorVO> cdList = null;
        DefaultTableModel modelo;
        DefaultTableCellRenderer esquerda=null, direita=null, centro=null;
        try {
            esquerda = new DefaultTableCellRenderer();
            direita = new DefaultTableCellRenderer();
            centro = new DefaultTableCellRenderer();

            esquerda.setHorizontalAlignment(SwingConstants.LEFT);
            direita.setHorizontalAlignment(SwingConstants.RIGHT);
            centro.setHorizontalAlignment(SwingConstants.CENTER);
            
            cdList = new ArrayList<CadCredorVO>();
            cdBean = new CadCredorVO();
            
            cdDao = new CadCredorDAO();
            
            cdList = cdDao.listaCredor(cdBean);
            
            modelo = (DefaultTableModel) this.jTable1.getModel();
            modelo.setNumRows(0);
            for(CadCredorVO cdB : cdList){
               modelo.addRow(new String[]{cdB.getLblCodigoCampo(), cdB.getTxtRazaoSocial(), cdB.getJrbAtivo() });
            }
            
            jTable1.getColumnModel().getColumn(0).setCellRenderer(direita);
            jTable1.getColumnModel().getColumn(1).setCellRenderer(esquerda);
            jTable1.getColumnModel().getColumn(2).setCellRenderer(centro);

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CadCredor.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                cdDao.closeCadCredorDAO();
            } catch ( SQLException | ClassNotFoundException ex) {
                Logger.getLogger(CadCredor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_formInternalFrameActivated

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        CadCredorVO crBean = null; CadCredorDAO crDao=null;
        try {
            int linha = jTable1.getSelectedRow();
            crBean = new CadCredorVO();
            crDao = new CadCredorDAO();
            crBean.setLblCodigoCampo(String.valueOf( jTable1.getValueAt(linha, 0)));

            crBean = crDao.buscarCredorByPk(crBean);
            
            lblCodigoCampo.setText(crBean.getLblCodigoCampo());
            txtRazaoSocial.setText(crBean.getTxtRazaoSocial());
            if("Sim".equals(crBean.getJrbAtivo())){
                jrbAtivo.setSelected(true);
            }else{
                jrbAtivo.setSelected(false);            
            }      
        } catch ( SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ContasPagar.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                crDao.closeCadCredorDAO();
                 btoIncluir.setEnabled(false);btoSalvar.setEnabled(false);btoAtualizar.setEnabled(true);
            } catch ( SQLException | ClassNotFoundException ex) {
                Logger.getLogger(ContasPagar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jTable1MouseClicked

    public void limpaCampos(){
        lblCodigoCampo.setText("");
        txtRazaoSocial.setText("");
        jrbAtivo.setSelected(true);
    }
    
    public void atuazilaGrid(){
        CadCredorVO cdBean = null; CadCredorDAO cdDao = null;
        List<CadCredorVO> cdList = null;
        DefaultTableModel modelo;
        DefaultTableCellRenderer esquerda=null, direita=null, centro=null;
        try {
            esquerda = new DefaultTableCellRenderer();
            direita = new DefaultTableCellRenderer();
            centro = new DefaultTableCellRenderer();

            esquerda.setHorizontalAlignment(SwingConstants.LEFT);
            direita.setHorizontalAlignment(SwingConstants.RIGHT);
            centro.setHorizontalAlignment(SwingConstants.CENTER);
            
            cdList = new ArrayList<CadCredorVO>();
            cdBean = new CadCredorVO();
            
            cdDao = new CadCredorDAO();
            
            cdList = cdDao.listaCredor(cdBean);
            
            modelo = (DefaultTableModel) this.jTable1.getModel();
            modelo.setNumRows(0);
            for(CadCredorVO cdB : cdList){
               modelo.addRow(new String[]{cdB.getLblCodigoCampo(), cdB.getTxtRazaoSocial(), cdB.getJrbAtivo() });
            }
            
            jTable1.getColumnModel().getColumn(0).setCellRenderer(direita);
            jTable1.getColumnModel().getColumn(1).setCellRenderer(esquerda);
            jTable1.getColumnModel().getColumn(2).setCellRenderer(centro);

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CadCredor.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                cdDao.closeCadCredorDAO();
            } catch ( SQLException | ClassNotFoundException ex) {
                Logger.getLogger(CadCredor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btoAtualizar;
    private javax.swing.JButton btoIncluir;
    private javax.swing.JButton btoSalvar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JPanel jpBotoes;
    private javax.swing.JPanel jpCampos;
    private javax.swing.JPanel jpLista;
    private javax.swing.JRadioButton jrbAtivo;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblCodigoCampo;
    private javax.swing.JLabel lblRazaoSocial;
    private javax.swing.JTextField txtRazaoSocial;
    // End of variables declaration//GEN-END:variables
}
