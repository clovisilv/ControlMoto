/**
 * Company Fictec Cons. Inf.
 * @since 06/09/2012
 * @version 1.0.6
 * @author Clovis
 */
package br.com.cs.controlmoto.controller;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.text.MaskFormatter;

import br.com.cs.controlmoto.domain.ConnectionFactory;
import br.com.cs.controlmoto.domain.TabConsultaOrdensDAO;
import br.com.cs.controlmoto.utils.FixedLengthDocument;
import br.com.cs.controlmoto.vo.TabConsultaOrdensVO;

public class TabConsultaOrdens extends JInternalFrame implements ActionListener, FocusListener, MouseListener {
	/**
	 */
	private static final long serialVersionUID = -1379654176912208199L;

	private JToolBar jtbBarraFerramenta;

	private JLabel lblCodigo, lblNome, lblDatainicio, lblDatafinal, lblOrdem;
	
	private JTextField txtCodigo, txtNome, txtOrdem;
	
	private JFormattedTextField jtxtDatainicio, jtxtDatafinal;
	
	private JButton btoOrdem, btoLocalizar, btoCancelar, btoSair;
	
	private DefaultTableModel aModel;
	
	private JTable jtTabela;
	
	private JScrollPane jspPane;
	
	private JPanel jpBotao,jpTabela,jpComponentes;
	private JInternalFrame tabConsultaOrdens;
	private JCheckBox windowClosable;
	
	private ControlMoto telaControlMoto;
	
	private DefaultTableCellRenderer tcrDireta,tcrEsqueda,tcrCentro;
	
	private TableColumnModel colModel;
	
	private Container content;
	
	private PreparedStatement ps; Statement MinhaSQL; ResultSet rs; Connection MinhaConexao;
	
	private boolean data;
	
	private String caminho="", separadorPath = "";
	
	private Date datas; DateFormat formatar;  NumberFormat vr;	SimpleDateFormat sdf,sdfTimestamp; Timestamp timestamp;
	
	//Serve para mover a tela
    static int openFrameCount = 9;
    static final int xOffset = 25, yOffset = 14;
    
    public TabConsultaOrdens(String titulo, ControlMoto controlMoto){
    	   	
    	super(titulo,false,true,false,true);
    	setSize(700,430);
    	setVisible(true);
    	setLocation(xOffset*openFrameCount, yOffset*openFrameCount);

    	separadorPath = System.getProperty("file.separator");
    	caminho = new File("").getAbsolutePath();
    	
    	this.telaControlMoto = telaControlMoto;
    	controlMoto.jDesktopPane.add(this);
    	
    	jtbBarraFerramenta = new JToolBar();
		jtbBarraFerramenta.setFloatable(false);
		jtbBarraFerramenta.setOrientation(javax.swing.SwingConstants.VERTICAL);
   
    	content = getContentPane();
    	content.setLayout(new BorderLayout());
    	
    	formatar = new SimpleDateFormat().getDateInstance();
    	
    	tabConsultaOrdens = new JInternalFrame();
    	
    	lblOrdem = new JLabel("Número da O.S.");;lblNome = new JLabel("Nome do Cliente");lblDatainicio = new JLabel("Data de Início");
    	lblDatafinal = new JLabel("Data Final");//lblOrdem = new JLabel("Ordem de Servico N");
    	
    	//txtCodigo = new JTextField();
    	txtNome = new JTextField();txtNome.setDocument(new FixedLengthDocument(50));
    	txtOrdem = new JTextField();txtOrdem.setDocument(new FixedLengthDocument(10));
    	jtxtDatainicio = new JFormattedTextField(setMascara("##/##/####"));jtxtDatainicio.setDocument(new FixedLengthDocument(10));
    	jtxtDatafinal = new JFormattedTextField(setMascara("##/##/####"));jtxtDatafinal.setDocument(new FixedLengthDocument(10));
    	
		btoLocalizar = new JButton(); btoLocalizar.setMnemonic(KeyEvent.VK_L); btoLocalizar.setToolTipText("Localizar");
		btoLocalizar.setIcon(new ImageIcon(caminho + separadorPath + "imagens" + separadorPath + "icone" + separadorPath + "hd_lupa_16x16.png"));
				
		btoSair = new JButton(); btoSair.setMnemonic(KeyEvent.VK_S); btoSair.setToolTipText("Sair"); 
		btoSair.setIcon(new ImageIcon(caminho + separadorPath + "imagens" + separadorPath + "icone" + separadorPath	+ "cancelar_circular_16x16.png"));
		
		btoCancelar = new JButton(); btoCancelar.setMnemonic(KeyEvent.VK_C); btoCancelar.setToolTipText("Cancelar");
		btoCancelar.setIcon(new ImageIcon(caminho + separadorPath + "imagens" + separadorPath + "icone" + separadorPath + "hd_cancela_16x16.png"));
    	
    	jpBotao = new JPanel();jpTabela = new JPanel();jpComponentes = new JPanel();
    	
    	jtTabela = new JTable();
    	//TIRA O AUTO-DIMENCIONAR DA CELULA ASSIM APARECEM AS BARRAS DE ROLAGEM
    	jtTabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    	
//    	defaultTCR = new DefaultTableCellRenderer();
    	
     	GridBagConstraints cons = new GridBagConstraints();
    	Container jpComponenntes = getContentPane();
    	GridBagLayout componentes = new GridBagLayout();
    	jpComponentes.setLayout(componentes);
    	jpComponentes.setSize(500,430);
    	cons.insets = new Insets(5,3,5,3);
    	cons.anchor = GridBagConstraints.NORTHWEST;
    	  	
    	cons.gridx = 1;cons.gridy = 3;cons.gridwidth = 2;cons.ipadx = 60;
    	jpComponentes.add(lblOrdem, cons);
    	cons.gridx = 3;cons.gridy = 3;cons.gridwidth = 2;cons.ipadx = 70;
    	jpComponentes.add(txtOrdem, cons);
    	txtOrdem.addActionListener(this);txtOrdem.addFocusListener(this);txtOrdem.addMouseListener(this);
    	
    	cons.gridx = 1;cons.gridy = 5;cons.gridwidth = 2;cons.ipadx = 60;
    	jpComponentes.add(lblNome, cons);
    	cons.gridx = 3;cons.gridy = 5;cons.gridwidth = 5;cons.ipadx = 280;
    	jpComponentes.add(txtNome, cons);
    	txtNome.addActionListener(this);txtNome.addFocusListener(this);txtNome.addMouseListener(this);
    	    	
    	cons.gridx = 1;cons.gridy = 7;cons.gridwidth = 2;cons.ipadx = 60;
    	jpComponentes.add(lblDatainicio, cons);
    	cons.gridx = 3;cons.gridy = 7;cons.gridwidth = 2;cons.ipadx = 70;
    	jtxtDatainicio.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    	jpComponentes.add(jtxtDatainicio, cons);
    	jtxtDatainicio.addActionListener(this);jtxtDatainicio.addFocusListener(this);jtxtDatainicio.addMouseListener(this);
    	
    	cons.gridx = 6;cons.gridy = 7;cons.gridwidth = 2;cons.ipadx = 70;
    	lblDatafinal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    	jpComponentes.add(lblDatafinal, cons);
    	cons.gridx = 8;cons.gridy = 7;cons.gridwidth = 2;cons.ipadx = 70;
    	jtxtDatafinal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    	jpComponentes.add(jtxtDatafinal, cons);jtxtDatafinal.setEnabled(false);
    	jtxtDatafinal.addActionListener(this);jtxtDatafinal.addFocusListener(this);jtxtDatafinal.addMouseListener(this);
    	
		cons.anchor = GridBagConstraints.EAST; cons.fill = GridBagConstraints.BOTH;
    	jtbBarraFerramenta.add(btoLocalizar);
    	btoLocalizar.addActionListener(this); btoLocalizar.requestFocus();
    	
    	//cons.gridx = 12;cons.gridy = 5;cons.gridwidth = 1;cons.gridheight = 1;cons.ipadx = 10;
    	jtbBarraFerramenta.add(btoCancelar);
    	btoCancelar.addActionListener(this);
    	
    	//cons.gridx = 12;cons.gridy = 7;cons.gridwidth = 1;cons.gridheight = 1;cons.ipadx = 10;
    	jtbBarraFerramenta.add(btoSair);
    	btoSair.addActionListener(this);
    	
    	cons.gridx = 12;cons.gridy = 3;cons.gridwidth = 1;cons.gridheight = 5;cons.ipadx = 5;
    	jpComponentes.add(jtbBarraFerramenta, cons);
    	
    	content.add(jpComponentes, BorderLayout.NORTH);
    	
//    	jpBotao.setLayout(new GridLayout(10,1,10,5));   	
//
//    	jpBotao.add(btoOrdem);btoOrdem.addActionListener(this);
//    	jpBotao.add(btoCancelar);btoCancelar.addActionListener(this);
//    	jpBotao.add(btoSair);btoSair.addActionListener(this);
// 
//    	content.add(jpBotao, BorderLayout.EAST);

    	tcrDireta = new DefaultTableCellRenderer();tcrDireta.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    	tcrEsqueda = new DefaultTableCellRenderer();tcrEsqueda.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
    	tcrCentro = new DefaultTableCellRenderer();tcrCentro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    	
    	colModel = jtTabela.getColumnModel();
    	jspPane = new JScrollPane(jtTabela);
    	content.add(jspPane, BorderLayout.CENTER);
    	
    	sdf = new SimpleDateFormat("dd/MM/yyyy");
    	sdfTimestamp = new SimpleDateFormat("yyyy/MM/dd");

    }
    
    public void focusGained(FocusEvent c){
    }
    
    public void focusLost(FocusEvent c){
    }
    public void mouseClicked(MouseEvent c){
    	if (c.getSource()== txtOrdem){
    		//btoLocalizar.setVisible(true);
    		txtOrdem.setEnabled(true);txtNome.setEnabled(false);
    		jtxtDatainicio.setEnabled(false);jtxtDatafinal.setEnabled(false);
    	}
    	
    	if (c.getSource()== txtNome){
    		//btoLocalizar.setVisible(true);
    		txtOrdem.setEnabled(false);jtxtDatainicio.setEnabled(false);
    		jtxtDatafinal.setEnabled(false);
    	}
    	
    	if (c.getSource()== jtxtDatainicio){
    		//btoLocalizar.setVisible(true);
    		txtOrdem.setEnabled(false);txtNome.setEnabled(false);
    		jtxtDatafinal.setEnabled(true);
    		data = true;
    	}
    	
    }
    
    public void mouseEntered(MouseEvent c) {
	}

	public void mouseExited(MouseEvent c) {
	}

	public void mousePressed(MouseEvent c) {
	}

	public void mouseReleased(MouseEvent c) {
	}
    /*
     * (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent c){
    //CONTROLE QUE LISTA AS ORDENS DE SERVICO PELO SEU CODIGO    	    
   	    if (c.getSource() == btoLocalizar && txtOrdem.getText() != null && !txtOrdem.getText().equals("")){
   	    	try{
	            int CodOrdem = Integer.parseInt(txtOrdem.getText());
	    		String[] tableColumnsName = {"Cod.O.S.","Cliente","Vr Hora","Motorista","Data Início","Hora Inicio","Status"};
	    		aModel = (DefaultTableModel)jtTabela.getModel();
	    		aModel.setColumnIdentifiers(tableColumnsName);
	    		jtTabela.getColumnModel().getColumn(0).setPreferredWidth(70);colModel.getColumn(0).setCellRenderer(tcrCentro);
	    		jtTabela.getColumnModel().getColumn(1).setPreferredWidth(200);
	    		jtTabela.getColumnModel().getColumn(2).setPreferredWidth(70);colModel.getColumn(2).setCellRenderer(tcrDireta);
	    		jtTabela.getColumnModel().getColumn(3).setPreferredWidth(200);
	    		jtTabela.getColumnModel().getColumn(4).setPreferredWidth(100);colModel.getColumn(4).setCellRenderer(tcrCentro);
	    		jtTabela.getColumnModel().getColumn(5).setPreferredWidth(70);colModel.getColumn(5).setCellRenderer(tcrCentro);    		
	    		jtTabela.getColumnModel().getColumn(6).setPreferredWidth(50);colModel.getColumn(6).setCellRenderer(tcrCentro);
	    		
	    		TabConsultaOrdensVO consultaVO = new TabConsultaOrdensVO();
	       		consultaVO.setOrdemPk(Integer.valueOf(txtOrdem.getText()));
	       		TabConsultaOrdensDAO consultaDAO = new TabConsultaOrdensDAO();
				try {
					consultaDAO.readOrdemServicoByPk(consultaVO);
				} catch (InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				}finally{
					try {
						ConnectionFactory.getClosedConnection();
					} catch (IllegalAccessException | InstantiationException e) {
						e.printStackTrace();
					}
				}
				Object[] objects = new Object[7];
				int i = 0;
				for(i = 0; i < consultaVO.getQtde(); i++){
					objects[i] = consultaVO.getOrdemPk();
					objects[i+1] = consultaVO.getNomeCliente();
					objects[i+2] = consultaVO.getValorHora();
					objects[i+3] = consultaVO.getNomeMotorista();
					objects[i+4] = String.valueOf(sdf.format(consultaVO.getDataInicios()));
					objects[i+5] = consultaVO.getHoraInicio();
					objects[i+6] = consultaVO.getStatus();
					aModel.addRow(objects);
				}
				jtTabela.setModel(aModel);
				txtOrdem.setText("");
    		}catch(SQLException e){
    			JOptionPane.showMessageDialog(this, "Erro ao localizar as informações.","",JOptionPane.INFORMATION_MESSAGE);
    		}    		
   		}
   	    //CONTROLE QUE LISTA AS ORDENS DE COLETAS PELO NOME DO CLENTE    
   	    if (c.getSource() == btoLocalizar && txtNome.getText() != null && !txtNome.getText().equals("")){
   	    	try{
	    		String[] tableColumnsName = {"Cod.O.S.","Cliente","Vr Hora","Motorista","Data Inicio","Hora Inicio","Status"};
	    		aModel = (DefaultTableModel)jtTabela.getModel();
	    		aModel.setColumnIdentifiers(tableColumnsName);
	    		jtTabela.getColumnModel().getColumn(0).setPreferredWidth(70);colModel.getColumn(0).setCellRenderer(tcrCentro);
	    		jtTabela.getColumnModel().getColumn(1).setPreferredWidth(200);
	    		jtTabela.getColumnModel().getColumn(2).setPreferredWidth(70);colModel.getColumn(2).setCellRenderer(tcrDireta);
	    		jtTabela.getColumnModel().getColumn(3).setPreferredWidth(200);
	    		jtTabela.getColumnModel().getColumn(4).setPreferredWidth(100);colModel.getColumn(4).setCellRenderer(tcrCentro);
	    		jtTabela.getColumnModel().getColumn(5).setPreferredWidth(70);colModel.getColumn(5).setCellRenderer(tcrCentro);    		
	    		jtTabela.getColumnModel().getColumn(6).setPreferredWidth(50);colModel.getColumn(6).setCellRenderer(tcrCentro);
	    		
	    		TabConsultaOrdensVO consultasVO = new TabConsultaOrdensVO();
	       		consultasVO.setNomeCliente(txtNome.getText().toUpperCase());
	       		TabConsultaOrdensDAO consultaDAO = new TabConsultaOrdensDAO();
	       		List<TabConsultaOrdensVO> o=null;
				try {
					o = consultaDAO.readOrdemServicoByNome(consultasVO);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InstantiationException e) {
					e.printStackTrace();
				}finally{
					try {
						ConnectionFactory.getClosedConnection();
					} catch (InstantiationException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}
				Object[] objects = new Object[7];
				int i = 0;
				for(TabConsultaOrdensVO consultaVO : o ){
					objects = new Object[7];
					objects[i] = consultaVO.getOrdemPk();
					objects[i+1] = consultaVO.getNomeCliente();
					objects[i+2] = consultaVO.getValorHora();
					objects[i+3] = consultaVO.getNomeMotorista();
					objects[i+4] = String.valueOf(sdf.format(consultaVO.getDataInicios()));
					objects[i+5] = consultaVO.getHoraInicio();
					objects[i+6] = consultaVO.getStatus();
					aModel.addRow(objects);
				}
				jtTabela.setModel(aModel);
				txtOrdem.setText("");
    		}catch(SQLException e){
    			JOptionPane.showMessageDialog(this, "Erro ao localizar as informações.","",JOptionPane.INFORMATION_MESSAGE);
    		} 		
   		}
   	    //CONTROLE QUE LISTA AS ORDENS DE COLETAS DENTRO DE UM DETERMINADO PERIODO      	    
         if (c.getSource() == btoLocalizar && jtxtDatainicio.getValue() != null){
        	 try{
	    		String[] tableColumnsName = {"Código O.S.","Cliente","Vr Hora","Motorista","Data Início","Hora Início","Status"};
	    		aModel = (DefaultTableModel)jtTabela.getModel();
	    		aModel.setColumnIdentifiers(tableColumnsName);
	    		jtTabela.getColumnModel().getColumn(0).setPreferredWidth(70);colModel.getColumn(0).setCellRenderer(tcrCentro);
	    		jtTabela.getColumnModel().getColumn(1).setPreferredWidth(200);
	    		jtTabela.getColumnModel().getColumn(2).setPreferredWidth(70);colModel.getColumn(2).setCellRenderer(tcrDireta);
	    		jtTabela.getColumnModel().getColumn(3).setPreferredWidth(200);
	    		jtTabela.getColumnModel().getColumn(4).setPreferredWidth(100);colModel.getColumn(4).setCellRenderer(tcrCentro);
	    		jtTabela.getColumnModel().getColumn(5).setPreferredWidth(70);colModel.getColumn(5).setCellRenderer(tcrCentro);    		
	    		jtTabela.getColumnModel().getColumn(6).setPreferredWidth(50);colModel.getColumn(6).setCellRenderer(tcrCentro);
	    		
	    		TabConsultaOrdensVO consultasVO = new TabConsultaOrdensVO();
	    		try{
		       		consultasVO.setDataInicios(new java.sql.Timestamp(sdf.parse(jtxtDatainicio.getText()).getTime()));
		       		consultasVO.setDataTerminos(new java.sql.Timestamp(sdf.parse(jtxtDatafinal.getText()).getTime()));
	    		}catch(Exception ex){}
	       		TabConsultaOrdensDAO consultaDAO = new TabConsultaOrdensDAO();
				List<TabConsultaOrdensVO> o=null;
				try {
					o = consultaDAO.readOrdemServicoByPeriodo(consultasVO);
				} catch (InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				} finally{
					try {
						ConnectionFactory.getClosedConnection();
					} catch (IllegalAccessException | InstantiationException e) {
						e.printStackTrace();
					}
				}
				Object[] objects = new Object[7];
				int i = 0;
				for(TabConsultaOrdensVO consultaVO : o){
					objects[i] = consultaVO.getOrdemPk();
					objects[i+1] = consultaVO.getNomeCliente();
					objects[i+2] = consultaVO.getValorHora();
					objects[i+3] = consultaVO.getNomeMotorista();
					objects[i+4] = String.valueOf(sdf.format(consultaVO.getDataInicios()));
					objects[i+5] = consultaVO.getHoraInicio();
					objects[i+6] = consultaVO.getStatus();
					aModel.addRow(objects);
				}
				jtTabela.setModel(aModel);
				jtxtDatainicio.setText("");jtxtDatafinal.setText("");
     		}catch(SQLException e){
    			JOptionPane.showMessageDialog(this, "Erro ao localizar as informações.","",JOptionPane.INFORMATION_MESSAGE);
    		}
    	}
         
	    if (c.getSource()== btoCancelar && aModel != null){
			limpaCampos();
			setTitle("Tabela de Consultas");
    		aModel.setRowCount(0);
    		aModel.setColumnCount(0);
    		txtOrdem.setEnabled(true);txtNome.setEnabled(true);
    		jtxtDatainicio.setEnabled(true);jtxtDatafinal.setEnabled(false);
	    }else{
	    	txtOrdem.setEnabled(true);txtNome.setEnabled(true);
	    	jtxtDatainicio.setEnabled(true);jtxtDatafinal.setEnabled(false);
		}
	    
	    if (c.getSource()== btoSair){
	    	int resultado = JOptionPane.showConfirmDialog(this,"Deseja sair da tabela de consultas?","ControlMoto",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
			if(resultado == 0){
			//tabConsultaOrdens.setClosable(windowClosable.isSelected());
				TabConsultaOrdens.this.dispose();
			//System.exit(0);
			}else{
				return;
			}
	    }    
    }
	private MaskFormatter setMascara(String mascara){
		MaskFormatter mask = null;
		try{
			mask = new MaskFormatter(mascara);
		}catch(java.text.ParseException e){
		}
		return mask;
	}
	
	public void limpaCampos(){
		txtNome.setText("");
		txtOrdem.setText("");
		jtxtDatainicio.setText("");
		jtxtDatafinal.setText("");
	}
	
	class AlinharRenderer extends DefaultTableCellRenderer{
		public AlinharRenderer(){
			setHorizontalAlignment(CENTER);
		}	
	}		
		
    }