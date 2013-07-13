package br.com.cs.controlmoto.controller;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.MaskFormatter;
import javax.swing.text.PlainDocument;

import org.jdesktop.swingx.JXDatePicker;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import br.com.cs.controlmoto.domain.CadVlCombustivelDAO;
import br.com.cs.controlmoto.domain.ConnectionFactory;
import br.com.cs.controlmoto.utils.FixedLengthDocument;
import br.com.cs.controlmoto.utils.FormataValor;
import br.com.cs.controlmoto.vo.CadVlCombustivelVO;
/**
 * Company Fictec Cons. Inf.
 * @since 17/02/2013
 * @version 1.0.0
 * @author Clovis
 */

class CadVlCombustivel extends JInternalFrame implements ActionListener, FocusListener, KeyListener, MouseListener {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JToolBar jtbBarraFerramenta;
	
	private JPanel jpCampos, jpToolBar, jpTabela;
	
	private JLabel  lblCodigo, lblNome, lblSolicitante, lblValor, lblDataSolicitacao, lblTipoVale, lblStatus, lblDataInicio,
	lblDataTermino;
	
	private JTextField	txtCodigo, txtNome, txtSolicitante, txtValor, txtDataSolicitacao, txtDataInicio, txtDataTermino, txtStatus;
	
	private JFormattedTextField jftValor, jftDataSolicitacao, jftDataInicio, jftDataTermino; 
    
	private JComboBox jcbTipoVale, jcbStatus, jcbSolicitante, jcbOpcao;
    
	private JXDatePicker jxdpDataInicio, jxdpDataTermino;
	
	private JButton btoIncluir, btoLocalizar, btoGravar, btoExcluir, btoSair,btoAtualizar, btoCancelar, btoImprimir;
	
	private JTable jtTabela; private JScrollPane jspTabela;
	private DefaultTableModel modelo;
//    JCheckBox windowResizable = null, windowClosable = null;
    
    JInternalFrame jifTelaCadVlCombustivel;
    
    private ControlMoto telaControlMoto;
    
    String tipoUsuario [] = {"","Adiantamento Salarial","Vale Combustível"};
    String status []= {"","Em Aprovação","Aprovado","Pago"};
    String caminho ="", separadorPath = "";
    Dimension centraliza = Toolkit.getDefaultToolkit().getScreenSize();
    ImageIcon icone;
    
    Statement minhaSTM;
    ResultSet rs;
    Connection minhaConexao;

    //Serve para mover a tela interna
    static int openFrameCount = 10;
    static final int xOffset = 25, yOffset = 15;
    
	    public CadVlCombustivel(String titulo, ControlMoto controlMoto){
	    	super(titulo,false,true,false,true);
	    	initCadVlCombustivel("", telaControlMoto);
	    	this.telaControlMoto = telaControlMoto;
	    	controlMoto.jDesktopPane.add(this);
	    }
   
	    public void initCadVlCombustivel(String titulo, ControlMoto controlMoto){
	    	setSize(500, 500);
	    	setVisible(true);
	
	    	jifTelaCadVlCombustivel = new JInternalFrame("Solicitação de Vale");
	    	jifTelaCadVlCombustivel.setToolTipText("Solicitação de Vale");
	    	
	    	separadorPath = System.getProperty("file.separator");
	    	caminho = new File("").getAbsolutePath();
	    	
	    	setLocation(xOffset*openFrameCount, yOffset*openFrameCount);
	    	setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
	    	
			jpCampos = new JPanel(); jpToolBar = new JPanel(); 
	
	    	GridBagConstraints cons = new GridBagConstraints();
	    	GridBagLayout layout = new GridBagLayout();
	    	jpCampos.setLayout(layout);
    	
			jtbBarraFerramenta = new JToolBar();
			jtbBarraFerramenta.setFloatable(false);
			jtbBarraFerramenta.setOrientation(javax.swing.SwingConstants.HORIZONTAL);
	
	    	cons.insets = new Insets (3,5,3,5); //cons.weightx = 0.010;cons.weighty = 0.010;
	    	jspTabela = new JScrollPane();jspTabela.setPreferredSize(new Dimension(480, 230));
	    	jtTabela = new JTable();

			jpTabela = new JPanel(); jpTabela.setPreferredSize(new Dimension(490, 280));//jpTabela.setSize(javax.swing.GroupLayout.PREFERRED_SIZE);//Size(490, 150);width, height;//
			jpTabela.setBorder(javax.swing.BorderFactory.createTitledBorder(null,"Vizualisar Vales", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Agency FB", 0, 12), new java.awt.Color(0, 0, 204)));			
	    	// Determina os espacos entre os componentes
	    	// cons.anchor = GridBagConstraints.NORTHWEST; cons.fill = GridBagConstraints.HORIZONTAL;
	    	lblCodigo = new JLabel("Código Vale"); lblNome = new JLabel("Nome"); lblSolicitante = new JLabel("Cod. Solicitante");
	    	lblValor = new JLabel("Valor"); lblDataSolicitacao = new JLabel("Data"); lblDataInicio = new JLabel("Data Início");
	    	lblDataTermino = new JLabel("Data Término"); lblTipoVale = new JLabel("Tipo Vale"); lblStatus = new JLabel("Situação");
	
	    	txtCodigo = new JTextField(); txtCodigo.setEnabled(false); txtValor = new JTextField(); txtStatus = new JTextField();
	    	txtStatus.setEnabled(false); jftDataSolicitacao = new JFormattedTextField(setMascara("##/##/####"));
	    	jftDataSolicitacao.setHorizontalAlignment(SwingConstants.RIGHT); jxdpDataInicio = new JXDatePicker(); //new JFormattedTextField(setMascara("##/##/####"));
	    	jxdpDataInicio.setPreferredSize(new Dimension(150, 20)); //jxdpDataInicio.setHorizontalAlignment(SwingConstants.RIGHT);
	    	jxdpDataTermino = new JXDatePicker(); //jxdpDataTermino.setDateInMillis(new java.util.Date().getTime()+30 ); // JFormattedTextField(setMascara("##/##/####")); 
	    	jxdpDataTermino.setPreferredSize(new Dimension(150, 20)); //jxdpDataTermino.setHorizontalAlignment(SwingConstants.RIGHT); 
	    	txtNome = new JTextField(); txtNome.setDocument(new FixedLengthDocument(30));
	    	txtSolicitante = new JTextField(); txtSolicitante.setDocument(new FixedLengthDocument(10));
	    	
	    	jcbTipoVale = new JComboBox(tipoUsuario); jcbStatus = new JComboBox(status); jcbSolicitante = new JComboBox(); jcbOpcao = new JComboBox();
	
			btoIncluir = new JButton(); btoIncluir.setMnemonic(KeyEvent.VK_I); btoIncluir.setToolTipText("Incluir");
			btoIncluir.setIcon(new ImageIcon(caminho + separadorPath + "imagens" + separadorPath + "icone" + separadorPath + "hd_folha_novo_32x32.png"));
			
			btoLocalizar = new JButton(); btoLocalizar.setMnemonic(KeyEvent.VK_L); btoLocalizar.setToolTipText("Localizar");
			btoLocalizar.setIcon(new ImageIcon(caminho + separadorPath + "imagens" + separadorPath + "icone" + separadorPath + "hd_lupa_32x32.png"));
			
			btoGravar = new JButton(); btoGravar.setMnemonic(KeyEvent.VK_G); btoGravar.setToolTipText("Gravar");
			btoGravar.setIcon(new ImageIcon(caminho + separadorPath + "imagens"	+ separadorPath + "icone" + separadorPath + "hd_flopy_32x32.png"));
			
			btoExcluir =  new JButton(); btoExcluir.setMnemonic(KeyEvent.VK_E); btoExcluir.setToolTipText("Excluir");
			btoExcluir.setIcon(new ImageIcon(caminho + separadorPath + "imagens" + separadorPath + "icone" + separadorPath + "lixeira_32x32.png"));
			
			btoSair = new JButton(); btoSair.setMnemonic(KeyEvent.VK_S); btoSair.setToolTipText("Sair");
			btoSair.setIcon(new ImageIcon(caminho + separadorPath + "imagens" + separadorPath + "icone" + separadorPath	+ "cancelar_circular_32x32.png"));
			
			btoAtualizar = new JButton(); btoAtualizar.setMnemonic(KeyEvent.VK_A); btoAtualizar.setToolTipText("Atualizar");
			btoAtualizar.setIcon(new ImageIcon(caminho + separadorPath + "imagens" + separadorPath + "icone" + separadorPath	+ "hd_atualiza_32x32.png"));
			
			btoCancelar = new JButton(); btoCancelar.setMnemonic(KeyEvent.VK_C); btoCancelar.setToolTipText("Cancelar");
			btoCancelar.setIcon(new ImageIcon(caminho + separadorPath + "imagens" + separadorPath + "icone" + separadorPath + "hd_cancela_32x32.png"));
			
			btoImprimir = new JButton(); btoImprimir.setMnemonic(KeyEvent.VK_I); btoImprimir.setToolTipText("Imprimir");
			btoImprimir.setIcon(new ImageIcon(caminho + separadorPath + "imagens" + separadorPath + "icone" + separadorPath + "impressora_empresa_32x32.png"));
	
	    	cons.anchor = GridBagConstraints.NORTHWEST;cons.fill = GridBagConstraints.HORIZONTAL;
	    	cons.gridx = 1; cons.gridy = 3; cons.gridwidth = 1; cons.ipadx = 10;
	    	jpCampos.add(lblCodigo,cons);
	
	    	cons.gridx = 2; cons.gridy = 3; cons.gridwidth = 2; cons.ipadx = 50;
	    	txtCodigo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
	    	jpCampos.add(txtCodigo, cons);
	    	
	    	cons.gridx = 12; cons.gridy = 3; cons.gridwidth = 1; cons.ipadx = 10;
	    	lblStatus.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
	    	jpCampos.add(lblStatus, cons);
	    	
	    	cons.gridx = 14; cons.gridy = 3; cons.gridwidth = 4; cons.ipadx = 120;
	    	jpCampos.add(txtStatus, cons);
	    	
	    	cons.gridx = 2; cons.gridy = 4; cons.gridwidth = 18; cons.ipadx = 140;
	    	txtCodigo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
	    	jcbOpcao.setPreferredSize(new Dimension(140,20));
	    	jpCampos.add(jcbOpcao, cons);
	    	jcbOpcao.addActionListener(this); jcbOpcao.addFocusListener(this);
	    	
	    	cons.gridx = 1; cons.gridy = 5; cons.gridwidth = 1; cons.ipadx = 10;
	    	jpCampos.add(lblSolicitante,cons);
	
	    	cons.gridx = 2; cons.gridy = 5; cons.gridwidth = 2; cons.ipadx = 50;
	    	txtSolicitante.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
	    	jpCampos.add(txtSolicitante, cons);
	    	txtSolicitante.addActionListener(this); txtSolicitante.addFocusListener(this);
	    	
	    	cons.gridx = 12; cons.gridy = 5; cons.gridwidth = 1; cons.ipadx = 10;
	    	lblValor.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
	    	jpCampos.add(lblValor, cons);
	
	    	cons.gridx = 14; cons.gridy = 5; cons.gridwidth = 4; cons.ipadx = 120;
	    	txtValor.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
	    	jpCampos.add(txtValor,cons);
	    	txtValor.addActionListener(this);txtValor.addFocusListener(this);    	
	    	    	
	    	cons.gridx = 1;cons.gridy = 6;cons.gridwidth = 1;cons.ipadx = 10;
	    	jpCampos.add(lblNome,cons);
	
	    	cons.gridx = 2; cons.gridy = 6; cons.gridwidth = 18;cons.ipadx = 140;
	    	jpCampos.add(txtNome,cons);
	    	txtNome.addActionListener(this);txtNome.addFocusListener(this);txtNome.addKeyListener(this);
	
	    	cons.gridx = 1; cons.gridy = 7; cons.gridwidth = 1; cons.ipadx = 10;
	    	jpCampos.add(lblTipoVale,cons);
	
	    	cons.gridx = 2; cons.gridy = 7; cons.gridwidth = 2; //cons.ipadx = 60;
	    	jpCampos.add(jcbTipoVale,cons);
	    	jcbTipoVale.addActionListener(this);jcbTipoVale.addFocusListener(this);jcbTipoVale.addKeyListener(this);
	    	
	    	cons.gridx = 12;cons.gridy = 7;cons.gridwidth = 1;cons.ipadx = 40;
	    	lblDataSolicitacao.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
	    	jpCampos.add(lblDataSolicitacao,cons);
	
	    	cons.gridx = 14;cons.gridy = 7;cons.gridwidth = 4;cons.ipadx = 120;
	    	jpCampos.add(jftDataSolicitacao,cons);
	    	jftDataSolicitacao.addActionListener(this); jftDataSolicitacao.addFocusListener(this); jftDataSolicitacao.addKeyListener(this);
	
	    	//Seta os campos de datas para a busca doas vales
	    	//cons.gridx = 2;cons.gridy = 2;cons.gridwidth = 1;cons.ipadx = 20;
	    	jpTabela.add(lblDataInicio);//, cons);
	
	    	//cons.gridx = 3;cons.gridy = 2;cons.gridwidth = 4;cons.ipadx = 220;
	    	jpTabela.add(jxdpDataInicio);//, cons);
	    	
	    	//cons.gridx = 5;cons.gridy = 2;cons.gridwidth = 1;cons.ipadx = 20;
	    	jpTabela.add(lblDataTermino);//, cons);
	
	    	//cons.gridx = 6;cons.gridy = 2;cons.gridwidth = 4;cons.ipadx = 220;
	    	jpTabela.add(jxdpDataTermino);//, cons);
	    	
			jtTabela.setModel(new javax.swing.table.DefaultTableModel(
		            new Object [][] {
		            },
		            new String [] {"Código", "Solicitante", "Valor", "Data"}
		    ));
			jtTabela.addMouseListener(this);
			jspTabela.add(jtTabela);jspTabela.setViewportView(jtTabela);
			jpTabela.add(jspTabela);
	    	
	//    	cons.gridx = 4;cons.gridy = 6;cons.gridwidth = 1;cons.ipadx = 40;
	//    	lblStatus.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
	//    	jpCampos.add(lblStatus,cons);
	//
	//    	cons.gridx = 5;cons.gridy = 6;cons.gridwidth = 2;cons.ipadx = 40;
	//    	jpCampos.add(jcbStatus,cons);
	//    	jcbStatus.addActionListener(this);jcbStatus.addFocusListener(this);jcbStatus.addKeyListener(this);
	
	    	cons.fill = GridBagConstraints.HORIZONTAL;
	    	
	    	cons.gridx = 20;cons.gridy = 2;cons.gridwidth = 1;cons.gridheight = 1;cons.ipadx = 40;
			btoIncluir.setNextFocusableComponent(btoLocalizar);
			jtbBarraFerramenta.add(btoIncluir, cons); btoIncluir.addActionListener(this);
	
			cons.gridx = 20;cons.gridy = 4;cons.gridwidth = 1;cons.gridheight = 1;cons.ipadx = 60;
			btoGravar.setNextFocusableComponent(btoCancelar);
			jtbBarraFerramenta.add(btoGravar, cons); btoGravar.addActionListener(this);
	
			cons.gridx = 20;cons.gridy = 5;cons.gridwidth = 1;cons.gridheight = 1;cons.ipadx = 60;
			btoLocalizar.setNextFocusableComponent(btoSair);
			jtbBarraFerramenta.add(btoLocalizar, cons); btoLocalizar.addActionListener(this);
	
			cons.gridx = 20;cons.gridy = 6;cons.gridwidth = 1;cons.gridheight = 1;cons.ipadx = 60;
			btoAtualizar.setNextFocusableComponent(btoExcluir);
			jtbBarraFerramenta.add(btoAtualizar, cons); btoAtualizar.addActionListener(this);
			
			cons.gridx = 20;cons.gridy = 7;cons.gridwidth = 1;cons.gridheight = 1;cons.ipadx = 60;
			jtbBarraFerramenta.add(btoImprimir); btoImprimir.addActionListener(this);
	
			cons.gridx = 20;cons.gridy = 8;cons.gridwidth = 1;cons.gridheight = 1;cons.ipadx = 60;
			btoExcluir.setNextFocusableComponent(btoCancelar);
			jtbBarraFerramenta.add(btoExcluir, cons); btoExcluir.addActionListener(this);
	
			cons.gridx = 20;cons.gridy = 9;cons.gridwidth = 1;cons.gridheight = 1;cons.ipadx = 60;
			jtbBarraFerramenta.add(btoCancelar, cons); btoCancelar.addActionListener(this);
	
			cons.gridx = 20;cons.gridy = 10;cons.gridwidth = 1;cons.gridheight = 1;cons.ipadx = 60;
			btoSair.setNextFocusableComponent(txtCodigo);
			jtbBarraFerramenta.add(btoSair, cons); btoSair.addActionListener(this);
	
			btoIncluir.setEnabled(true); btoGravar.setEnabled(false); btoLocalizar.setEnabled(true); btoAtualizar.setEnabled(false);
			btoExcluir.setEnabled(false); btoSair.setEnabled(true); btoCancelar.setEnabled(false); btoImprimir.setEnabled(false);
			
			jpToolBar.add(jtbBarraFerramenta);
			
			getContentPane().add(jpCampos, BorderLayout.CENTER );
			getContentPane().add(jtbBarraFerramenta, BorderLayout.NORTH );
			getContentPane().add(jpTabela, BorderLayout.SOUTH);
	    }
	    
		public void keyReleased(KeyEvent c) {
		}
		public void keyTyped(KeyEvent c) {
		}
		public void keyPressed(KeyEvent c) {
		}
		public void focusGained(FocusEvent c) {
		}
		public void focusLost(FocusEvent c) {
	    }

		public void actionPerformed(ActionEvent c) {
			//ACAO DO BOTAO INCLUIR
			if (c.getSource() == btoIncluir) {
				try{
					//INSTACIA OS CLASSES VO E DAO
					CadVlCombustivelVO cadVlCombustivelVO = new CadVlCombustivelVO();
					CadVlCombustivelDAO cadVlCombustivelDAO = new CadVlCombustivelDAO();
					//CHAMA O METODO QUE CONTROLA O NUMERACAO DO ID DO REGISTRO
					cadVlCombustivelDAO.addAdiantementoDAO(cadVlCombustivelVO);
					//CARREGA A COMBO BOX OPCAO
					if(jcbOpcao.getItemCount() < 1){
						jcbOpcao.addItem("");
						for(CadVlCombustivelVO cadVl : cadVlCombustivelDAO.readSolicitanteAllAdiantamento())
							jcbOpcao.addItem(cadVl.getOpcaoNome()+" - "+cadVl.getOpcaoId());
					}
					txtCodigo.setText(String.valueOf(cadVlCombustivelVO.getAdiantamentoPk()));
					txtSolicitante.requestFocus();
					
					jftDataSolicitacao.setText(String.valueOf(new SimpleDateFormat("dd/MM/yyyy").format(new java.util.Date())));
					
					btoIncluir.setEnabled(false); btoGravar.setEnabled(true); btoLocalizar.setEnabled(false); btoAtualizar.setEnabled(false);
					btoImprimir.setEnabled(false); btoExcluir.setEnabled(false); btoCancelar.setEnabled(true); btoSair.setEnabled(true); 
				}catch(SQLException | InstantiationException | IllegalAccessException e){
					JOptionPane.showMessageDialog(this, "Erro ao incluir adiantamento.","ControlMoto",JOptionPane.INFORMATION_MESSAGE);
				}
			}
			//ACAO DO BOTAO GRAVAR
			if (c.getSource() == btoGravar) {
				try{
					CadVlCombustivelVO cadVlCombustivelVO = new CadVlCombustivelVO();
					
					cadVlCombustivelVO.setAdiantamentoPk(Integer.valueOf(txtCodigo.getText()));
					cadVlCombustivelVO.setCodSolicitante(txtSolicitante.getText());
					cadVlCombustivelVO.setNomeSolicitante(txtNome.getText());
					cadVlCombustivelVO.setValor(Double.valueOf(txtValor.getText().replace(",", ".")));
					cadVlCombustivelVO.setTipoAdiantamento(jcbTipoVale.getSelectedItem().toString());
					cadVlCombustivelVO.setDataSolicitacao(jftDataSolicitacao.getText());
					cadVlCombustivelVO.setStatusSolicitacao("Aberto");
					
					CadVlCombustivelDAO cadVlCombustivelDAO = new CadVlCombustivelDAO();
					try {
						cadVlCombustivelDAO.saveAdinatamentoDAO(cadVlCombustivelVO);
					} catch (InstantiationException | IllegalAccessException e) {
						e.printStackTrace();
					}finally{
						try {
							ConnectionFactory.getClosedConnection();
						} catch (IllegalAccessException | InstantiationException e) {
							e.printStackTrace();
						}
					}
					btoImprimir.setEnabled(true);
					btoImprimir.doClick();
					
					btoIncluir.setEnabled(true); btoGravar.setEnabled(false); btoLocalizar.setEnabled(true); btoAtualizar.setEnabled(false);
					btoImprimir.setEnabled(false); btoExcluir.setEnabled(false); btoCancelar.setEnabled(true); btoSair.setEnabled(true); 
		            
		            limpaCampos();
				}catch(SQLException | ParseException e){
					JOptionPane.showMessageDialog(this, "Erro ao gravar vale.","ControlMoto",JOptionPane.INFORMATION_MESSAGE);
				}
			}
			//CONTROLA ACAO DO BOTAO LOCALIZAR
			if (c.getSource() == btoLocalizar) {
				try{
					DefaultTableCellRenderer esquerda = new DefaultTableCellRenderer();
					esquerda.setHorizontalAlignment(SwingConstants.LEFT);
					DefaultTableCellRenderer centraliza = new DefaultTableCellRenderer();
					centraliza.setHorizontalAlignment(SwingConstants.CENTER);
					DefaultTableCellRenderer direita = new DefaultTableCellRenderer();
					direita.setHorizontalAlignment(SwingConstants.RIGHT);
					
					List<CadVlCombustivelVO> cadVlCombustivelVOs = new ArrayList<CadVlCombustivelVO>();
					CadVlCombustivelVO cadVlCombustivelVO = new CadVlCombustivelVO();
					
					cadVlCombustivelVO.setDataInicio(new SimpleDateFormat("dd/MM/yyyy").format(jxdpDataInicio.getDateInMillis()));
					cadVlCombustivelVO.setDataTermino(new SimpleDateFormat("dd/MM/yyyy").format(jxdpDataTermino.getDateInMillis()));
					
					CadVlCombustivelDAO cadVlCombustivelDAO = new CadVlCombustivelDAO();
					modelo = (DefaultTableModel) this.jtTabela.getModel();
					modelo.setNumRows(0);
					try {
						cadVlCombustivelVOs = cadVlCombustivelDAO.readAdiantamentoByPeriodo(cadVlCombustivelVO);
					} catch (InstantiationException | IllegalAccessException e) {
						e.printStackTrace();
					}finally{
						try {
							ConnectionFactory.getClosedConnection();
						} catch (IllegalAccessException | InstantiationException e) {
							e.printStackTrace();
						}
					}					
					for(CadVlCombustivelVO cadVl : cadVlCombustivelVOs){
						modelo.addRow(new String[]{String.valueOf(cadVl.getAdiantamentoPk()), cadVl.getNomeSolicitante(), new FormataValor().decimalFormat(String.valueOf(cadVl.getValor())), String.valueOf(new SimpleDateFormat("dd/MM/yyy").format(cadVl.getDtSolicitacao()))} ); 
					}
					jtTabela.getColumnModel().getColumn(0).setCellRenderer(direita);
					jtTabela.getColumnModel().getColumn(0).setPreferredWidth(10);
					jtTabela.getColumnModel().getColumn(1).setCellRenderer(esquerda);
					jtTabela.getColumnModel().getColumn(1).setPreferredWidth(180);
					jtTabela.getColumnModel().getColumn(2).setCellRenderer(direita);
					jtTabela.getColumnModel().getColumn(2).setPreferredWidth(20);
					jtTabela.getColumnModel().getColumn(3).setCellRenderer(direita);
					jtTabela.getColumnModel().getColumn(3).setPreferredWidth(15);
					
					jtTabela.setModel(modelo);
					
					btoIncluir.setEnabled(false); btoGravar.setEnabled(false); btoLocalizar.setEnabled(false); btoAtualizar.setEnabled(false);  
					btoExcluir.setEnabled(false); btoCancelar.setEnabled(true); btoSair.setEnabled(true); btoImprimir.setEnabled(false);
				}catch(SQLException e){
					JOptionPane.showMessageDialog(this, "Erro ao localizar vales.","ControlMoto",JOptionPane.INFORMATION_MESSAGE);
				}
			}
			//ACAO DO BOTAO ATUALIZAR
			if (c.getSource() == btoAtualizar) {
				try{
					CadVlCombustivelVO cadVlCombustivelVO = new CadVlCombustivelVO();
					
					cadVlCombustivelVO.setAdiantamentoPk(Integer.valueOf(txtCodigo.getText()));
					cadVlCombustivelVO.setCodSolicitante(txtSolicitante.getText());
					cadVlCombustivelVO.setNomeSolicitante(txtNome.getText());
					cadVlCombustivelVO.setValor(Double.valueOf( txtValor.getText().replace(".", "").replace(",", ".")));
					cadVlCombustivelVO.setTipoAdiantamento(jcbTipoVale.getSelectedItem().toString());
					cadVlCombustivelVO.setDataSolicitacao( jftDataSolicitacao.getText());
					cadVlCombustivelVO.setStatusSolicitacao("Atualizado");
					
					CadVlCombustivelDAO cadVlCombustivelDAO = new CadVlCombustivelDAO();
					try {
						cadVlCombustivelDAO.alterAdinatamentoByPk(cadVlCombustivelVO);
					} catch (InstantiationException | IllegalAccessException e) {
						e.printStackTrace();
					}finally{
						try {
							ConnectionFactory.getClosedConnection();
						} catch (IllegalAccessException | InstantiationException e) {
							e.printStackTrace();
						}
					}
					btoLocalizar.doClick();
					
					btoIncluir.setEnabled(true); btoGravar.setEnabled(false); btoLocalizar.setEnabled(true); btoAtualizar.setEnabled(false);  
					btoExcluir.setEnabled(false); btoCancelar.setEnabled(true); btoSair.setEnabled(true); btoImprimir.setEnabled(false);
		            
					limpaCampos();
				}catch(SQLException | ParseException e){
					JOptionPane.showMessageDialog(this, "Erro ao atualizar vale.","ControlMoto",JOptionPane.INFORMATION_MESSAGE);
				}
			}
			//ACAO DO BOTAO EXCLUIR
			if (c.getSource() == btoExcluir) {
				try{
					CadVlCombustivelVO cadVlCombustivelVO = new CadVlCombustivelVO();
					
					cadVlCombustivelVO.setAdiantamentoPk(Integer.valueOf(txtCodigo.getText()));
					cadVlCombustivelVO.setStatusSolicitacao("Excluido");
					do{
						cadVlCombustivelVO.setObsSolicitacao(JOptionPane.showInputDialog(null,"Digite o motivo da exclusão","ControlMoto",JOptionPane.OK_OPTION));
					}while (cadVlCombustivelVO.getObsSolicitacao() == null);
						
					CadVlCombustivelDAO cadVlCombustivelDAO = new CadVlCombustivelDAO();
					try {
						cadVlCombustivelDAO.deleteAdiantamentoByPk(cadVlCombustivelVO);
					} catch (InstantiationException | IllegalAccessException e) {
						e.printStackTrace();
					}finally{
						try {
							ConnectionFactory.getClosedConnection();
						} catch (IllegalAccessException | InstantiationException e) {
							e.printStackTrace();
						}
					}
					btoIncluir.setEnabled(true); btoGravar.setEnabled(false); btoLocalizar.setEnabled(true); btoAtualizar.setEnabled(false);  
					btoExcluir.setEnabled(false); btoCancelar.setEnabled(true); btoSair.setEnabled(true); btoImprimir.setEnabled(false);
					
					limpaCampos();
				}catch(SQLException e){
					JOptionPane.showMessageDialog(this, "Erro ao excluir adiantamento.","ControlMoto",JOptionPane.INFORMATION_MESSAGE);
				}
			}
			//ACAO DO BOTAO IMPRIMIR
			if(c.getSource() == btoImprimir){
				try {
					CadVlCombustivelVO cadVlCombustivelVO = new CadVlCombustivelVO();
					cadVlCombustivelVO.setAdiantamentoPk( Integer.valueOf(txtCodigo.getText()));
					cadVlCombustivelVO.setStatusSolicitacao("Impresso");
					if(txtStatus.getText() == "Excluido")
						new CadVlCombustivelDAO().alterStatusAdinatamentoByPk(cadVlCombustivelVO);
	                
					//Pega caminho da aplicação
					caminho = new File("").getAbsolutePath();
					//Pega o caracter de separação de pastas do sistema
					separadorPath = System.getProperty("file.separator");
					//Variavel que recebe as informacoes de conexao com o banco de dados, nome do arquivo e mapa de parametros
					JasperPrint relat = null;
					//Pega a conexão com banco de dados
					minhaConexao = ConnectionFactory.getConnection();
					//Cria um mapa com o parametro do relatório
					HashMap map = new HashMap();
					map.put("adiantamentoPk", cadVlCombustivelVO.getAdiantamentoPk());
					//Variavel que recebe o nome do relatorio jasper
					String arquivoJasper = "rpt_adiantamento.jasper";
					if(caminho.contains("workspace")){
	    				//CAMINHO DE PRODUCAO
	    				relat = JasperFillManager.fillReport(caminho+separadorPath+"reports"+separadorPath+arquivoJasper, map, minhaConexao);
					}else{
						//CAMINHO DE ECLIPSE
	    				relat = JasperFillManager.fillReport(caminho+separadorPath+"reports"+separadorPath+arquivoJasper, map, minhaConexao);
					}
	                JasperViewer jasperViewer = new JasperViewer(relat, false);
	                jasperViewer.setModalExclusionType(Dialog.ModalExclusionType.NO_EXCLUDE);
	                jasperViewer.setTitle("ControlMoto - Adiantamento");
	                jasperViewer.setZoomRatio(0.75f);
	                jasperViewer.setBounds(100,110,750,600);
	                //caminho para producao
	                //ImageIcon icone = new ImageIcon(caminho+separadorPath+logo_tipo+"logo_cs_b.gif");
	                //caminho para eclipse
	                //ImageIcon icone = new ImageIcon(logo_tipo+"logo_cs_b.gif");
	                //ImageIcon icone = new ImageIcon(caminho+separadorPath+lerArquivoVO.getLogoTipo()+separadorPath+"logo_cs_b.gif");
	                //jasperViewer.setIconImage(icone.getImage());
	                jasperViewer.setAlwaysOnTop(true);
	                jasperViewer.setVisible(true);
	                					
				} catch (InstantiationException | IllegalAccessException | JRException | SQLException e) {
					e.printStackTrace();
				}
				
			}
			//ACAO DO BOTAO CANCELAR
			if (c.getSource() == btoCancelar) {
				JOptionPane.showMessageDialog(this,"Procedimento cancelado!","ControlMoto",JOptionPane.INFORMATION_MESSAGE);
				
				limpaCampos();
				
				btoIncluir.setEnabled(true); btoGravar.setEnabled(false); btoLocalizar.setEnabled(true); btoAtualizar.setEnabled(false);
				btoExcluir.setEnabled(false); btoCancelar.setEnabled(false); btoSair.setVisible(true);  btoImprimir.setEnabled(false);

			}
			//ACAO DO BOTAO SAIR
			if (c.getSource() == btoSair) {
				int resultado = JOptionPane.showConfirmDialog(this,"Deseja sair do cadastro de usuário?", "ControlMoto",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE);
				if (resultado == 0){
					CadVlCombustivel.this.dispose();
					ControlMoto.telaCadUsuarios = null;
				}else{
					return;
				}
			}
			//ACAO DO COMBOBOX OPCAO
			if(c.getSource() == jcbOpcao){
				if(jcbOpcao.getSelectedItem().toString() != "" && !jcbOpcao.getSelectedItem().toString().equals("") ){
					txtSolicitante.setText(jcbOpcao.getSelectedItem().toString().substring(jcbOpcao.getSelectedItem().toString().indexOf("-")).replace("-", "").trim());
					txtNome.setText(jcbOpcao.getSelectedItem().toString().substring(0 , jcbOpcao.getSelectedItem().toString().indexOf("-")).trim());
					txtValor.requestFocus();
				}
			}
		}
		//CONTROLA OS CLIQUES NO GRID DA TABELA PARA ATUALIZAR OS VALES
		public void mouseClicked(MouseEvent e) {
			if(e.getSource() == jtTabela){
				CadVlCombustivelVO cadVlCombustivelVO = new CadVlCombustivelVO();
				CadVlCombustivelDAO cadVlCombustivelDAO = new CadVlCombustivelDAO();
				
				int linha = jtTabela.getSelectedRow();
				cadVlCombustivelVO.setAdiantamentoPk(Integer.valueOf(jtTabela.getValueAt(linha, 0).toString()));
				
				try {
					cadVlCombustivelVO =  cadVlCombustivelDAO.readAdiantamentoByPk(cadVlCombustivelVO);
				} catch (IllegalAccessException | InstantiationException | SQLException e1) {
					e1.printStackTrace();
				}
				txtCodigo.setText(cadVlCombustivelVO.getAdiantamentoPk().toString());
				txtStatus.setText(cadVlCombustivelVO.getStatusSolicitacao());
				txtNome.setText(cadVlCombustivelVO.getNomeSolicitante());
				txtSolicitante.setText(cadVlCombustivelVO.getCodSolicitante());
				jcbTipoVale.setSelectedItem(cadVlCombustivelVO.getTipoAdiantamento());
				jftDataSolicitacao.setText(cadVlCombustivelVO.getDataSolicitacao());
				txtValor.setText(new FormataValor().decimalFormat(cadVlCombustivelVO.getValor().toString()));
				
				if( !cadVlCombustivelVO.getStatusSolicitacao().equals("Excluido") ){
					btoIncluir.setEnabled(false); btoGravar.setEnabled(false); btoLocalizar.setEnabled(false); btoAtualizar.setEnabled(true);
					btoExcluir.setEnabled(true); btoCancelar.setEnabled(true); btoSair.setEnabled(true); btoImprimir.setEnabled(true);
				}else{
					btoIncluir.setEnabled(false); btoGravar.setEnabled(false); btoLocalizar.setEnabled(false); btoAtualizar.setEnabled(false);
					btoExcluir.setEnabled(false); btoCancelar.setEnabled(true); btoSair.setEnabled(true); btoImprimir.setEnabled(true);
					
					JOptionPane.showMessageDialog(this, "Este vale está com situação excluido e não pode ser alterado.", "ControlMoto", JOptionPane.OK_OPTION);
				}
			}
		}
		
		public void mouseEntered(MouseEvent e) {
			//if(e.getSource() == jifTelaCadVlCombustivel)
			//ControlMoto.jifTelaCadVlCombustivel.setToolTipText("SOLICITAÇÃO DE VALE");
			//JOptionPane.showMessageDialog(null, "Solicitação de Vale");
		}
		public void mouseExited(MouseEvent e) {
		}
		public void mousePressed(MouseEvent e) {
		}
		public void mouseReleased(MouseEvent e) {
		}
		//METODO QUE CRIA AS MASCARAS DO CAMPOS
		private MaskFormatter setMascara(String mascara) {
			MaskFormatter mask = null;
			try {
				mask = new MaskFormatter(mascara);
			} catch (java.text.ParseException e) {
			}
			return mask;
		}
		//
		class MeuDocument extends PlainDocument {
			public void insertString(int offs, String str, AttributeSet a)
					throws BadLocationException {
				super.insertString(offs, str.toUpperCase(), a);
			}
		}
		//METODO QUE ATUALIZA OS COMPOS DA TELA APOS AS ACOES DOS BOTOES
		public void atualizaCampos() {
			try {
				txtCodigo.setText(rs.getString("Codigo"));
				txtNome.setText(rs.getString("NomeUsuario"));
				txtSolicitante.setText(rs.getString("Usuario"));
				jcbStatus.setSelectedItem(rs.getString("Status"));
			} catch (SQLException ex) {
			}
		}
		//METODO QUE LIMPA OS COMPAS DA TELA APOS AS ACOES DOS BOTOES
		public void limpaCampos() {
			txtCodigo.setText("");
			txtNome.setText("");
			txtSolicitante.setText("");
			jcbOpcao.setSelectedItem("");
			jcbTipoVale.setSelectedItem("");
			jcbStatus.setSelectedItem("");
			txtStatus.setText("");
			jftDataSolicitacao.setText("");
			txtValor.setText("");
			//jcbOpcao.removeAllItems();
		}
		
    }