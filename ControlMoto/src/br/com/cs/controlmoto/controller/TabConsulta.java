/**
 * Company Fictec Cons. Inf.
 * @since 06/09/2012
 * @version 1.0.6
 * @author Clovis
 */
package br.com.cs.controlmoto.controller;

import java.awt.BorderLayout;
import java.awt.CheckboxGroup;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
import br.com.cs.controlmoto.domain.TabConsultaDAO;
import br.com.cs.controlmoto.utils.FixedLengthDocument;
import br.com.cs.controlmoto.vo.TabConsultaVO;

public class TabConsulta extends JInternalFrame implements ActionListener, FocusListener, KeyListener, MouseListener, ItemListener  {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7314428362478727677L;
	
	private JToolBar jtbBarraFerramenta;
	private JLabel	lblCodigo, lblNome, lblDatainicio, lblDatafinal, lblOrdem, lblPergunta;
	private JTextField	txtCodigo, txtNome, txtOrdem;
	private JFormattedTextField jtxtDatainicio, jtxtDatafinal;
	private JButton	btoCliente, btoMotorista, btoLocalizar, btoCancelar, btoSair;
	private CheckboxGroup ckboxgGrupo;
	private JTable jtTabela;
	private JScrollPane jspPane;
	private JPanel jpBotao, jpPergunta, jpTabela, jpComponentes;
	private JCheckBox jcboxCliente, jcboxMotorista, jcboxFornecedor;
	private ControlMoto telaControlMoto;
	
	static DefaultTableModel aModel;
	
	Container content;
	Connection MinhaConexao;
	PreparedStatement ps;
	Statement MinhaSQL;
	ResultSet rs;
	
	String Controle="", LocControle="", caminho = "", separadorPath="";
	Date datas;
	SimpleDateFormat sdf,sdfTimestamp;
    Timestamp timestamp;
    
	DefaultTableCellRenderer tcrDireta,tcrEsqueda,tcrCentro;
	TableColumnModel colModel;
	DateFormat formatar;
	static Object[] objects = null;

	//Serve para mover a tela
    static int openFrameCount = 2;
    static final int xOffset = 10, yOffset = 60;
	
    public TabConsulta(String titulo, ControlMoto controlMoto){
    	super(titulo,false,true,false,true);
    	setSize(700,500);
    	setVisible(true);
    	setLocation(xOffset*openFrameCount, yOffset*openFrameCount);
    	
    	caminho = new File("").getAbsolutePath();
    	separadorPath = System.getProperty("file.separator");

    	this.telaControlMoto = telaControlMoto;
    	controlMoto.jDesktopPane.add(this);
    	
    	jtbBarraFerramenta = new JToolBar();
		jtbBarraFerramenta.setFloatable(false);
		jtbBarraFerramenta.setOrientation(javax.swing.SwingConstants.VERTICAL);
   
    	content = getContentPane();
    	content.setLayout(new BorderLayout());
    	
    	formatar = new SimpleDateFormat().getDateInstance();
    	    	
    	lblCodigo = new JLabel("Código");lblNome = new JLabel("Nome");lblDatainicio = new JLabel("Data de Início");
    	lblDatafinal = new JLabel("Data Final");lblOrdem = new JLabel("Ordem de Serviço N");
    	lblPergunta = new JLabel("Escolha o que você deseja pesquisar!");
    	
    	txtCodigo = new JTextField();txtCodigo.setDocument(new FixedLengthDocument(10));
    	txtNome = new JTextField();txtNome.setDocument(new FixedLengthDocument(50));
    	txtOrdem = new JTextField();txtOrdem.setDocument(new FixedLengthDocument(10));
    	jtxtDatainicio = new JFormattedTextField(setMascara("##/##/####"));jtxtDatainicio.setDocument(new FixedLengthDocument(10));
    	jtxtDatafinal = new JFormattedTextField(setMascara("##/##/####"));jtxtDatafinal.setDocument(new FixedLengthDocument(10));
    	
    	btoCliente = new JButton("Cliente");btoMotorista = new JButton("Motorista");
    	
		btoLocalizar = new JButton(); btoLocalizar.setMnemonic(KeyEvent.VK_L); btoLocalizar.setToolTipText("Localizar");
		btoLocalizar.setIcon(new ImageIcon(caminho + separadorPath + "imagens" + separadorPath + "icone" + separadorPath + "hd_lupa_16x16.png"));
				
		btoSair = new JButton(); btoSair.setMnemonic(KeyEvent.VK_S); btoSair.setToolTipText("Sair"); 
		btoSair.setIcon(new ImageIcon(caminho + separadorPath + "imagens" + separadorPath + "icone" + separadorPath	+ "cancelar_circular_16x16.png"));
		
		btoCancelar = new JButton(); btoCancelar.setMnemonic(KeyEvent.VK_C); btoCancelar.setToolTipText("Cancelar");
		btoCancelar.setIcon(new ImageIcon(caminho + separadorPath + "imagens" + separadorPath + "icone" + separadorPath + "hd_cancela_16x16.png"));
    	
    	jpBotao = new JPanel();jpPergunta = new JPanel();
    	jpTabela = new JPanel();jpComponentes = new JPanel();
    	jtTabela = new JTable();jtTabela.addMouseListener(this);
    	//TIRA O AUTO-DIMENCIONAR DA CELULA ASSIM APARECEM AS BARRAS DE ROLAGEM
    	jtTabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    	
    	tcrEsqueda = new DefaultTableCellRenderer();tcrEsqueda.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
    	tcrDireta = new DefaultTableCellRenderer();tcrDireta.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    	tcrCentro = new DefaultTableCellRenderer();tcrCentro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    	ckboxgGrupo = new CheckboxGroup();
    	
    	jcboxCliente = new JCheckBox("Cliente");jcboxCliente.addItemListener(this);
    	jcboxMotorista = new JCheckBox("Motorista");jcboxMotorista.addItemListener(this);
    	jcboxFornecedor = new JCheckBox("Fornecedor");jcboxFornecedor.addItemListener(this);
    	
     	GridBagConstraints cons = new GridBagConstraints();
    	Container jpComponenntes = getContentPane();
    	GridBagLayout componentes = new GridBagLayout();
    	jpComponentes.setLayout(componentes);
    	jpComponentes.setSize(500,430);
    	cons.insets = new Insets(5,3,5,3);
    	//cons.weightx = 0.30;cons.weighty = 0.30;
    	cons.anchor = GridBagConstraints.NORTHWEST;
    	
    	ControlMoto cm = new ControlMoto();

    	cons.gridx = 3;cons.gridy = 1;cons.gridwidth = 5;cons.ipadx = 20;
    	jpComponentes.add(lblPergunta, cons);
    	cons.gridx = 3;cons.gridy = 2;cons.gridwidth = 1;cons.ipadx = 20;
    	jpComponentes.add(jcboxCliente, cons);
    	cons.gridx = 5;cons.gridy = 2;cons.gridwidth = 1;cons.ipadx = 20;
    	jpComponentes.add(jcboxMotorista, cons);
    	cons.gridx = 7;cons.gridy = 2;cons.gridwidth = 1;cons.ipadx = 20;
    	jpComponentes.add(jcboxFornecedor, cons);
    	
    	cons.gridx = 1;cons.gridy = 3;cons.gridwidth = 2;cons.gridheight = 1;cons.ipadx = 60;
    	jpComponentes.add(lblCodigo, cons);
    	cons.gridx = 3;cons.gridy = 3;cons.gridwidth = 2;cons.gridheight = 1;cons.ipadx = 70;
    	jpComponentes.add(txtCodigo, cons);
    	txtCodigo.addFocusListener(this);
    	
    	cons.gridx = 6;cons.gridy = 3;cons.gridwidth = 2;cons.gridheight = 1;cons.ipadx = 10;
    	jpComponentes.add(lblOrdem, cons);
    	cons.gridx = 8;cons.gridy = 3;cons.gridwidth = 2;cons.gridheight = 1;cons.ipadx = 70;
    	jpComponentes.add(txtOrdem, cons);
    	txtOrdem.addFocusListener(this);
    	
    	cons.gridx = 1;cons.gridy = 5;cons.gridwidth = 2;cons.gridheight = 1;cons.ipadx = 60;
    	jpComponentes.add(lblNome, cons);
    	cons.gridx = 3;cons.gridy = 5;cons.gridwidth = 5;cons.gridheight = 1;cons.ipadx = 280;
    	jpComponentes.add(txtNome, cons);
    	txtNome.addFocusListener(this);
    	    	
    	cons.gridx = 1;cons.gridy = 7;cons.gridwidth = 2;cons.gridheight = 1;cons.ipadx = 60;
    	jpComponentes.add(lblDatainicio, cons);
    	cons.gridx = 3;cons.gridy = 7;cons.gridwidth = 2;cons.gridheight = 1;cons.ipadx = 70;
    	jtxtDatainicio.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    	jpComponentes.add(jtxtDatainicio, cons);
    	jtxtDatainicio.addFocusListener(this);
    	
    	cons.gridx = 6;cons.gridy = 7;cons.gridwidth = 2;cons.gridheight = 1;cons.ipadx = 70;
    	jpComponentes.add(lblDatafinal, cons);
    	cons.gridx = 8;cons.gridy = 7;cons.gridwidth = 2;cons.gridheight = 1;cons.ipadx = 70;
    	jtxtDatafinal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    	jpComponentes.add(jtxtDatafinal, cons);jtxtDatafinal.setEnabled(false);
    	jtxtDatafinal.addFocusListener(this);
    	
    	cons.anchor = GridBagConstraints.EAST; cons.fill = GridBagConstraints.BOTH;
    	
    	//cons.gridx = 12;cons.gridy = 3;cons.gridwidth = 1;cons.gridheight = 1;cons.ipadx = 10;
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
    	  	
    	colModel = jtTabela.getColumnModel();
    	jspPane = new JScrollPane(jtTabela);
    	jspPane.setBounds(05, 05, 400, 100);
//    	jpTabela.setSize(700,300);
//    	jspPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//    	jpTabela.add(jspPane);
//    	jspPane.setViewportView(jtTabela);
//    	jspPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
    	content.add(jspPane, BorderLayout.CENTER);

    	lblCodigo.setVisible(false);txtCodigo.setVisible(false);lblOrdem.setVisible(false);txtOrdem.setVisible(false);
    	lblNome.setVisible(false);txtNome.setVisible(false);lblDatainicio.setVisible(false);jtxtDatainicio.setVisible(false);
    	lblDatafinal.setVisible(false);jtxtDatafinal.setVisible(false);btoLocalizar.setVisible(false);btoCliente.setEnabled(false);
    	btoMotorista.setEnabled(false);btoCancelar.setVisible(false);btoSair.setVisible(false);//btoSair.setEnabled(false);
    	
    	sdf = new SimpleDateFormat("dd/MM/yyyy");
    	sdfTimestamp = new SimpleDateFormat("yyyy/MM/dd");

    }
    /*
     * (non-Javadoc)
     * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
     */
    public void keyReleased(KeyEvent c){
    }
    public void keyTyped(KeyEvent c){
    }
    public void keyPressed(KeyEvent c){
    }
    /*
     * (non-Javadoc)
     * @see java.awt.event.FocusListener#focusGained(java.awt.event.FocusEvent)
     */
    public void focusGained(FocusEvent c){
    	if (c.getSource()== txtCodigo){
    		LocControle = "txtCodigo";
    		btoLocalizar.setVisible(true);
    		txtNome.setEnabled(false);jtxtDatainicio.setEnabled(false);
    		txtOrdem.setEnabled(false);jtxtDatafinal.setEnabled(false);
    	}
    	if (c.getSource()== txtNome){
    		LocControle = "txtNome";
    		btoLocalizar.setVisible(true);
    		txtCodigo.setEnabled(false);jtxtDatainicio.setEnabled(false);
    		txtOrdem.setEnabled(false);jtxtDatafinal.setEnabled(false);
    		System.out.println(LocControle);
    	}
    	if (c.getSource()== txtOrdem){
    		LocControle = "txtOrdem";
    		btoLocalizar.setVisible(true);
    		txtCodigo.setEnabled(false);txtNome.setEnabled(false);txtOrdem.setEnabled(true);
    		jtxtDatainicio.setEnabled(false);jtxtDatafinal.setEnabled(false);
    	}
    	if (c.getSource()== jtxtDatainicio){
    		LocControle = "jtxtDatainicio";
    		btoLocalizar.setVisible(true);
    		txtCodigo.setEnabled(false);txtNome.setEnabled(false);
    		txtOrdem.setEnabled(false);jtxtDatafinal.setEnabled(true);
    	}
     }
    public void focusLost(FocusEvent c){
    }
    /*
     * (non-Javadoc)
     * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
     */
    public void mouseClicked(MouseEvent c){

    }
    public void mousePressed(MouseEvent c){
    	String cod = jtTabela.getValueAt(0, 0).toString();  	
    }
    public void mouseReleased(MouseEvent c){
    }
    public void mouseEntered(MouseEvent c) {	
	}
    public void mouseExited(MouseEvent c) {
	}
    /*
     * (non-Javadoc)
     * @see java.awt.event.ItemListener#itemStateChanged(java.awt.event.ItemEvent)
     */
    public void itemStateChanged(ItemEvent c){
    	if (c.getItem()== jcboxCliente ){
    		Controle = "C";
    		lblPergunta.setVisible(false);
    		jcboxCliente.setVisible(false);
    		jcboxMotorista.setVisible(false);
    		jcboxFornecedor.setVisible(false);
    		lblCodigo.setVisible(true);txtCodigo.setVisible(true);
    		lblOrdem.setVisible(false);txtOrdem.setVisible(false);
    		lblNome.setVisible(true);txtNome.setVisible(true);
    		lblDatainicio.setVisible(true);jtxtDatainicio.setVisible(true);//jtxtDatainicio.setEnabled(true);
    		lblDatafinal.setVisible(true);jtxtDatafinal.setVisible(true);//jtxtDatafinal.setEnabled(true);
    		btoLocalizar.setVisible(true);
    		btoCancelar.setVisible(true);
    		btoSair.setVisible(true);
    		setTitle("Consulta de Cliente");
    	}
    	/*
    	 */
    	if (c.getItem()== jcboxMotorista ){
    		Controle = "M";
    		lblPergunta.setVisible(false);
    		jcboxCliente.setVisible(false);
    		jcboxMotorista.setVisible(false);
    		jcboxFornecedor.setVisible(false);
    		lblCodigo.setVisible(true);txtCodigo.setVisible(true);
    		lblOrdem.setVisible(false);txtOrdem.setVisible(false);
    		lblNome.setVisible(true);txtNome.setVisible(true);
    		lblDatainicio.setVisible(true);jtxtDatainicio.setVisible(true);
    		lblDatafinal.setVisible(true);jtxtDatafinal.setVisible(true);
    		btoLocalizar.setVisible(true);
    		btoCancelar.setVisible(true);
    		btoSair.setVisible(true);    		
    		setTitle("Consulta de Motorista");	
    	}
    	/*
    	 */
    	if (c.getItem()== jcboxFornecedor ){
    		Controle = "F";
    		lblPergunta.setVisible(false);
    		jcboxCliente.setVisible(false);
    		jcboxMotorista.setVisible(false);
    		jcboxFornecedor.setVisible(false);
    		lblCodigo.setVisible(true);txtCodigo.setVisible(true);
    		lblOrdem.setVisible(false);txtOrdem.setVisible(false);
    		lblNome.setVisible(true);txtNome.setVisible(true);
    		lblDatainicio.setVisible(true);jtxtDatainicio.setVisible(true);
    		lblDatafinal.setVisible(true);jtxtDatafinal.setVisible(true);
    		btoLocalizar.setVisible(true);
    		btoCancelar.setVisible(true);
    		btoSair.setVisible(true);    		
    		setTitle("Consulta de Fornecedor");	
    	}
 
     }
    /*
     * (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent c){
    	//CONTROLE PARA LISTAR SOMENTE REGISTROS DO CLIENTE PELO CODIGO
    	if (c.getSource() == btoLocalizar && LocControle == "txtCodigo" && Controle == "C"){
    		try{
	   			int CodCli = Integer.parseInt(txtCodigo.getText());
	   			String [] tableColumnsName = {"Código", "Nome", "Endereco", "Cidade", "Estado", "Telefone", "Celular"};
	   			aModel = (DefaultTableModel)jtTabela.getModel();
	   			aModel.setColumnIdentifiers(tableColumnsName);
	   			jtTabela.getColumnModel().getColumn(0).setPreferredWidth(50);colModel.getColumn(0).setCellRenderer(tcrCentro);
	   			jtTabela.getColumnModel().getColumn(1).setPreferredWidth(150);
	   			jtTabela.getColumnModel().getColumn(2).setPreferredWidth(200);
	   			jtTabela.getColumnModel().getColumn(3).setPreferredWidth(100);
	       		jtTabela.getColumnModel().getColumn(4).setPreferredWidth(50);colModel.getColumn(4).setCellRenderer(tcrCentro);
	       		jtTabela.getColumnModel().getColumn(5).setPreferredWidth(110);colModel.getColumn(5).setCellRenderer(tcrDireta);
	       		jtTabela.getColumnModel().getColumn(6).setPreferredWidth(110);colModel.getColumn(6).setCellRenderer(tcrDireta);
	        		
	       		TabConsultaVO consultaVO = new TabConsultaVO();
	       		consultaVO.setClientePk(Integer.valueOf(txtCodigo.getText()));
	       		TabConsultaDAO consultaDAO = new TabConsultaDAO();
				try {
					consultaDAO.readDadosClienteByPk(consultaVO);
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
					objects[i] = consultaVO.getClientePk();
					objects[i+1] = consultaVO.getNome();
					objects[i+2] = consultaVO.getEndereco();
					objects[i+3] = consultaVO.getCidade();
					objects[i+4] = consultaVO.getEstado();
					objects[i+5] = consultaVO.getTelefoneA();
					objects[i+6] = consultaVO.getCelularA();
				}
				aModel.addRow(objects);
				jtTabela.setModel(aModel);
				
				txtCodigo.setText("");
    		}catch(SQLException e){
    			JOptionPane.showMessageDialog(this, "Erro ao localizar as informações.","",JOptionPane.INFORMATION_MESSAGE);
    		}		
    	}
    	//CONTROLE PARA LISTAR O CLIENTE PELO NOME           
    	if(c.getSource() == btoLocalizar && LocControle == "txtNome" && Controle == "C"){
    		try{
	    		String[] tableColumnsName = {"Codigo","Nome","Endereco","Cidade","Estado","Telefone","Celular"};
	    		aModel = (DefaultTableModel)jtTabela.getModel();
	    		aModel.setColumnIdentifiers(tableColumnsName);
	    		jtTabela.getColumnModel().getColumn(0).setPreferredWidth(50);colModel.getColumn(0).setCellRenderer(tcrCentro);
	    		jtTabela.getColumnModel().getColumn(1).setPreferredWidth(150);
	    		jtTabela.getColumnModel().getColumn(2).setPreferredWidth(200);
	    		jtTabela.getColumnModel().getColumn(3).setPreferredWidth(100);
	    		jtTabela.getColumnModel().getColumn(4).setPreferredWidth(50);colModel.getColumn(4).setCellRenderer(tcrCentro);
	    		jtTabela.getColumnModel().getColumn(5).setPreferredWidth(110);colModel.getColumn(5).setCellRenderer(tcrDireta);
	    		jtTabela.getColumnModel().getColumn(6).setPreferredWidth(110);colModel.getColumn(6).setCellRenderer(tcrDireta);
	    		
	    		TabConsultaVO consultasVO = new TabConsultaVO();
	       		consultasVO.setNome(txtNome.getText().toUpperCase());
	       		TabConsultaDAO consultaDAO = new TabConsultaDAO();
				List<TabConsultaVO> o=null;
				try {
					o = consultaDAO.readDadosClienteByNome(consultasVO);
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
				for(TabConsultaVO consultaVO : o ){
					objects[i] = consultaVO.getClientePk();
					objects[i+1] = consultaVO.getNome();
					objects[i+2] = consultaVO.getEndereco();
					objects[i+3] = consultaVO.getCidade();
					objects[i+4] = consultaVO.getEstado();
					objects[i+5] = consultaVO.getTelefoneA();
					objects[i+6] = consultaVO.getCelularA();
					aModel.addRow(objects);
				}
				jtTabela.setModel(aModel);
				txtNome.setText("");
    		}catch(SQLException e){
    			JOptionPane.showMessageDialog(this, "Erro ao localizar as informações.","ControlMoto XX",JOptionPane.INFORMATION_MESSAGE);
    		}
    	}
    	//CONTROLE QUE LISTA OS CLIENTE DE UM DETERMINADO PERIODO  	    
    	if (c.getSource() == btoLocalizar && LocControle == "jtxtDatainicio" && Controle == "C"){
    		try{
	    		String [] tableColumnsName = {"Codigo","Nome","Endereco","Cidade","Estado","Telefone","Celular","Data Cadastro"};
	    		aModel = (DefaultTableModel)jtTabela.getModel();
	    		aModel.setColumnIdentifiers(tableColumnsName);
	    		jtTabela.getColumnModel().getColumn(0).setPreferredWidth(50);colModel.getColumn(0).setCellRenderer(tcrCentro);
	    		jtTabela.getColumnModel().getColumn(1).setPreferredWidth(150);
	    		jtTabela.getColumnModel().getColumn(2).setPreferredWidth(200);
	    		jtTabela.getColumnModel().getColumn(3).setPreferredWidth(100);
	    		jtTabela.getColumnModel().getColumn(4).setPreferredWidth(50);colModel.getColumn(4).setCellRenderer(tcrCentro);
	    		jtTabela.getColumnModel().getColumn(5).setPreferredWidth(100);colModel.getColumn(5).setCellRenderer(tcrDireta);
	    		jtTabela.getColumnModel().getColumn(6).setPreferredWidth(110);colModel.getColumn(6).setCellRenderer(tcrDireta);
	    		jtTabela.getColumnModel().getColumn(7).setPreferredWidth(110);colModel.getColumn(7).setCellRenderer(tcrCentro);
	    		
	    		TabConsultaVO consultasVO = new TabConsultaVO();
	    		try{
	    			consultasVO.setDataInicios(new java.sql.Timestamp(sdf.parse(jtxtDatainicio.getText()).getTime()));
	    			consultasVO.setDataFinals(new java.sql.Timestamp(sdf.parse(jtxtDatafinal.getText()).getTime()));
	    		}catch(Exception ex){}
	    		
	       		TabConsultaDAO consultaDAO = new TabConsultaDAO();
				List<TabConsultaVO> o=null;
				try {
					o = consultaDAO.readDadosClienteByPeriodo(consultasVO);
				} catch (InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				}finally{
					try {
						ConnectionFactory.getClosedConnection();
					} catch (IllegalAccessException | InstantiationException e) {
						e.printStackTrace();
					}
				}
	
				Object[] objects = new Object[8];
				int i = 0;
				for(TabConsultaVO consultaVO : o){
					objects[i] = consultaVO.getClientePk();
					objects[i+1] = consultaVO.getNome();
					objects[i+2] = consultaVO.getEndereco();
					objects[i+3] = consultaVO.getCidade();
					objects[i+4] = consultaVO.getEstado();
					objects[i+5] = consultaVO.getTelefoneA();
					objects[i+6] = consultaVO.getCelularA();
					objects[i+7] = (String.valueOf(sdf.format(consultaVO.getDataCadastros())));
					aModel.addRow(objects);
					}
				jtTabela.setModel(aModel);
				jtxtDatainicio.setText("");jtxtDatafinal.setText("");
    		}catch(SQLException e){
    			JOptionPane.showMessageDialog(this, "Erro ao localizar as informações.","",JOptionPane.INFORMATION_MESSAGE);
    		}
    	}
    	//CONTROLE QUE LISTA O MOTORISTA PELO SEU CODIGO    	
    	if (c.getSource() == btoLocalizar && LocControle == "txtCodigo" && Controle == "M"){
    		try{
	    		String [] tableColumnsName = {"Codigo", "Nome", "Endereco", "Cidade", "Estado", "Telefone", "Celular"};
	    		aModel = (DefaultTableModel)jtTabela.getModel();
	    		aModel.setColumnIdentifiers(tableColumnsName);
	    		jtTabela.getColumnModel().getColumn(0).setPreferredWidth(70);colModel.getColumn(0).setCellRenderer(tcrCentro);
	    		jtTabela.getColumnModel().getColumn(1).setPreferredWidth(150);
	    		jtTabela.getColumnModel().getColumn(2).setPreferredWidth(200);
	    		jtTabela.getColumnModel().getColumn(3).setPreferredWidth(100);
	    		jtTabela.getColumnModel().getColumn(4).setPreferredWidth(50);colModel.getColumn(4).setCellRenderer(tcrCentro);
	    		jtTabela.getColumnModel().getColumn(5).setPreferredWidth(110);colModel.getColumn(5).setCellRenderer(tcrDireta);
	    		jtTabela.getColumnModel().getColumn(6).setPreferredWidth(110);colModel.getColumn(6).setCellRenderer(tcrDireta);
	
	       		TabConsultaVO consultaVO = new TabConsultaVO();
	       		consultaVO.setMotoristaPk(txtCodigo.getText().toUpperCase());
	       		TabConsultaDAO consultaDAO = new TabConsultaDAO();
				try {
					consultaDAO.readDadosMotoristaByPk(consultaVO);
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
					objects[i] = consultaVO.getMotoristaPk();
					objects[i+1] = consultaVO.getNome();
					objects[i+2] = consultaVO.getEndereco();
					objects[i+3] = consultaVO.getCidade();
					objects[i+4] = consultaVO.getEstado();
					objects[i+5] = consultaVO.getTelefoneA();
					objects[i+6] = consultaVO.getCelularA();
				}
				aModel.addRow(objects);
				jtTabela.setModel(aModel);
				
				txtCodigo.setText("");
    		}catch(SQLException e){
    			JOptionPane.showMessageDialog(this, "Erro ao localizar as informações.","",JOptionPane.INFORMATION_MESSAGE);
    		}
    	}
    	//CONTROLE QUE LISTA O MOTORISTA PELO SEU NOME
    	if(c.getSource() == btoLocalizar && LocControle == "txtNome" && Controle == "M"){
    		try{
	    		String[] tableColumnsName = {"Codigo","Nome","Endereco","Cidade","Estado","Telefone","Celular"};
	    		aModel = (DefaultTableModel)jtTabela.getModel();
	    		aModel.setColumnIdentifiers(tableColumnsName);
	    		jtTabela.getColumnModel().getColumn(0).setPreferredWidth(70);colModel.getColumn(0).setCellRenderer(tcrCentro);
	    		jtTabela.getColumnModel().getColumn(1).setPreferredWidth(150);
	    		jtTabela.getColumnModel().getColumn(2).setPreferredWidth(200);
	    		jtTabela.getColumnModel().getColumn(3).setPreferredWidth(100);
	    		jtTabela.getColumnModel().getColumn(4).setPreferredWidth(50);colModel.getColumn(4).setCellRenderer(tcrCentro);
	    		jtTabela.getColumnModel().getColumn(5).setPreferredWidth(110);colModel.getColumn(5).setCellRenderer(tcrDireta);
	    		jtTabela.getColumnModel().getColumn(6).setPreferredWidth(110);colModel.getColumn(6).setCellRenderer(tcrDireta);
	    		
	    		TabConsultaVO consultasVO = new TabConsultaVO();
	       		consultasVO.setNome(txtNome.getText().toUpperCase());
	       		TabConsultaDAO consultaDAO = new TabConsultaDAO();
				List<TabConsultaVO> o=null;
				try {
					o = consultaDAO.readDadosMotoristaByNome(consultasVO);
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
				for(TabConsultaVO consultaVO : o){
					objects[i] = consultaVO.getMotoristaPk();
					objects[i+1] = consultaVO.getNome();
					objects[i+2] = consultaVO.getEndereco();
					objects[i+3] = consultaVO.getCidade();
					objects[i+4] = consultaVO.getEstado();
					objects[i+5] = consultaVO.getTelefoneA();
					objects[i+6] = consultaVO.getCelularA();
					
					aModel.addRow(objects);
					}
				jtTabela.setModel(aModel);
				txtNome.setText("");
    		}catch(SQLException e){
    			JOptionPane.showMessageDialog(this, "Erro ao localizar as informações.","",JOptionPane.INFORMATION_MESSAGE);
    		}
    	}
    	//CONTROLE QUE LISTA OS MOTORISTAS CADASTRADOS EM UM DETERMINADO PERIODO    	    
    	if (c.getSource() == btoLocalizar && LocControle == "jtxtDatainicio" && Controle == "M"){
    		try{
	    		String [] tableColumnsName = {"Codigo","Nome","Endereco","Cidade","Estado","Telefone","Celular","Data Cadastro"};
	    		aModel = (DefaultTableModel)jtTabela.getModel();
	    		aModel.setColumnIdentifiers(tableColumnsName);
	    		jtTabela.getColumnModel().getColumn(0).setPreferredWidth(70);colModel.getColumn(0).setCellRenderer(tcrCentro);
	    		jtTabela.getColumnModel().getColumn(1).setPreferredWidth(150);
	    		jtTabela.getColumnModel().getColumn(2).setPreferredWidth(200);
	    		jtTabela.getColumnModel().getColumn(3).setPreferredWidth(100);
	    		jtTabela.getColumnModel().getColumn(4).setPreferredWidth(50);colModel.getColumn(4).setCellRenderer(tcrCentro);
	    		jtTabela.getColumnModel().getColumn(5).setPreferredWidth(110);colModel.getColumn(5).setCellRenderer(tcrDireta);
	    		jtTabela.getColumnModel().getColumn(6).setPreferredWidth(110);colModel.getColumn(6).setCellRenderer(tcrDireta);
	    		jtTabela.getColumnModel().getColumn(7).setPreferredWidth(100);colModel.getColumn(7).setCellRenderer(tcrCentro);
	    		
	    		TabConsultaVO consultasVO = new TabConsultaVO();
	    		try{
	       		consultasVO.setDataInicios(new java.sql.Timestamp(sdf.parse(jtxtDatainicio.getText()).getTime()));
	       		consultasVO.setDataFinals(new java.sql.Timestamp(sdf.parse(jtxtDatafinal.getText()).getTime()));
	    		}catch(Exception ex){}
	       		TabConsultaDAO consultaDAO = new TabConsultaDAO();
				List<TabConsultaVO> o=null;
				try {
					o = consultaDAO.readDadosMotoristaByPeriodo(consultasVO);
				} catch (InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				} finally{
					try {
						ConnectionFactory.getClosedConnection();
					} catch (IllegalAccessException | InstantiationException e) {
						e.printStackTrace();
					}
				}
	
				Object[] objects = new Object[8];
				int i = 0;
				for(TabConsultaVO consultaVO : o){
					objects[i] = consultaVO.getMotoristaPk();
					objects[i+1] = consultaVO.getNome();
					objects[i+2] = consultaVO.getEndereco();
					objects[i+3] = consultaVO.getCidade();
					objects[i+4] = consultaVO.getEstado();
					objects[i+5] = consultaVO.getTelefoneA();
					objects[i+6] = consultaVO.getCelularA();
					objects[i+7] = (String.valueOf(sdf.format(consultaVO.getDataCadastros())));
					
					aModel.addRow(objects);
					}
				jtTabela.setModel(aModel);
				jtxtDatainicio.setText("");jtxtDatafinal.setText("");
    		}catch(SQLException e){
    			JOptionPane.showMessageDialog(this, "Erro ao localizar as informações.","",JOptionPane.INFORMATION_MESSAGE);
    		}
    	
    	}
    	//FORNECEDOR
    	//CONTROLE QUE LISTA FORNECEDOR PELO SEU CODIGO    	
    	if (c.getSource() == btoLocalizar && LocControle == "txtCodigo" && Controle == "F"){
    		try{
	    		String [] tableColumnsName = {"Codigo", "Nome", "Endereco", "Cidade", "Estado", "Telefone", "Celular"};
	    		aModel = (DefaultTableModel)jtTabela.getModel();
	    		aModel.setColumnIdentifiers(tableColumnsName);
	    		jtTabela.getColumnModel().getColumn(0).setPreferredWidth(70);colModel.getColumn(0).setCellRenderer(tcrCentro);
	    		jtTabela.getColumnModel().getColumn(1).setPreferredWidth(150);
	    		jtTabela.getColumnModel().getColumn(2).setPreferredWidth(200);
	    		jtTabela.getColumnModel().getColumn(3).setPreferredWidth(100);
	    		jtTabela.getColumnModel().getColumn(4).setPreferredWidth(50);colModel.getColumn(4).setCellRenderer(tcrCentro);
	    		jtTabela.getColumnModel().getColumn(5).setPreferredWidth(110);colModel.getColumn(5).setCellRenderer(tcrDireta);
	    		jtTabela.getColumnModel().getColumn(6).setPreferredWidth(110);colModel.getColumn(6).setCellRenderer(tcrDireta);
	
	       		TabConsultaVO consultaVO = new TabConsultaVO();
	       		consultaVO.setFornecedorPk(Integer.valueOf(txtCodigo.getText()));
	       		TabConsultaDAO consultaDAO = new TabConsultaDAO();
				try {
					consultaDAO.readDadosFornecedorByPk(consultaVO);
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
					objects[i] = consultaVO.getFornecedorPk();
					objects[i+1] = consultaVO.getNome();
					objects[i+2] = consultaVO.getEndereco();
					objects[i+3] = consultaVO.getCidade();
					objects[i+4] = consultaVO.getEstado();
					objects[i+5] = consultaVO.getTelefoneA();
					objects[i+6] = consultaVO.getCelularA();
				}
				aModel.addRow(objects);
				jtTabela.setModel(aModel);
				
				txtCodigo.setText("");
    		}catch(SQLException e){
    			JOptionPane.showMessageDialog(this, "Erro ao localizar as informações.","",JOptionPane.INFORMATION_MESSAGE);
    		}
    	}
    	//CONTROLE QUE LISTA O FORNECEDOR PELO SEU NOME
    	if(c.getSource() == btoLocalizar && LocControle == "txtNome" && Controle == "F"){
    		try{
	    		String[] tableColumnsName = {"Codigo","Nome","Endereco","Cidade","Estado","Telefone","Celular"};
	    		aModel = (DefaultTableModel)jtTabela.getModel();
	    		aModel.setColumnIdentifiers(tableColumnsName);
	    		jtTabela.getColumnModel().getColumn(0).setPreferredWidth(70);colModel.getColumn(0).setCellRenderer(tcrCentro);
	    		jtTabela.getColumnModel().getColumn(1).setPreferredWidth(150);
	    		jtTabela.getColumnModel().getColumn(2).setPreferredWidth(200);
	    		jtTabela.getColumnModel().getColumn(3).setPreferredWidth(100);
	    		jtTabela.getColumnModel().getColumn(4).setPreferredWidth(50);colModel.getColumn(4).setCellRenderer(tcrCentro);
	    		jtTabela.getColumnModel().getColumn(5).setPreferredWidth(110);colModel.getColumn(5).setCellRenderer(tcrDireta);
	    		jtTabela.getColumnModel().getColumn(6).setPreferredWidth(110);colModel.getColumn(6).setCellRenderer(tcrDireta);
	    		
	    		TabConsultaVO consultasVO = new TabConsultaVO();
	       		consultasVO.setNome(txtNome.getText().toUpperCase());
	       		TabConsultaDAO consultaDAO = new TabConsultaDAO();
				List<TabConsultaVO> o=null;
				try {
					o = consultaDAO.readDadosFornecedorByNome(consultasVO);
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
				for(TabConsultaVO consultaVO : o){
					objects[i] = consultaVO.getFornecedorPk();
					objects[i+1] = consultaVO.getNome();
					objects[i+2] = consultaVO.getEndereco();
					objects[i+3] = consultaVO.getCidade();
					objects[i+4] = consultaVO.getEstado();
					objects[i+5] = consultaVO.getTelefoneA();
					objects[i+6] = consultaVO.getCelularA();
					
					aModel.addRow(objects);
					}
				jtTabela.setModel(aModel);
				txtNome.setText("");
    		}catch(SQLException e){
    			JOptionPane.showMessageDialog(this, "Erro ao localizar as informações.","",JOptionPane.INFORMATION_MESSAGE);
    		}
    	}
    	//CONTROLE QUE LISTA OS FORNECEDOR CADASTRADOS EM UM DETERMINADO PERIODO    	    
    	if (c.getSource() == btoLocalizar && LocControle == "jtxtDatainicio" && Controle == "F"){
    		try{
	    		String [] tableColumnsName = {"Codigo","Nome","Endereco","Cidade","Estado","Telefone","Celular","Data Cadastro"};
	    		aModel = (DefaultTableModel)jtTabela.getModel();
	    		aModel.setColumnIdentifiers(tableColumnsName);
	    		jtTabela.getColumnModel().getColumn(0).setPreferredWidth(70);colModel.getColumn(0).setCellRenderer(tcrCentro);
	    		jtTabela.getColumnModel().getColumn(1).setPreferredWidth(150);
	    		jtTabela.getColumnModel().getColumn(2).setPreferredWidth(200);
	    		jtTabela.getColumnModel().getColumn(3).setPreferredWidth(100);
	    		jtTabela.getColumnModel().getColumn(4).setPreferredWidth(50);colModel.getColumn(4).setCellRenderer(tcrCentro);
	    		jtTabela.getColumnModel().getColumn(5).setPreferredWidth(110);colModel.getColumn(5).setCellRenderer(tcrDireta);
	    		jtTabela.getColumnModel().getColumn(6).setPreferredWidth(110);colModel.getColumn(6).setCellRenderer(tcrDireta);
	    		jtTabela.getColumnModel().getColumn(7).setPreferredWidth(100);colModel.getColumn(7).setCellRenderer(tcrCentro);
	    		
	    		TabConsultaVO consultasVO = new TabConsultaVO();
	    		try{
	    			consultasVO.setDataInicios(new java.sql.Timestamp(sdf.parse(jtxtDatainicio.getText()).getTime()));
	    			consultasVO.setDataFinals(new java.sql.Timestamp(sdf.parse(jtxtDatafinal.getText()).getTime()));
	    		}catch(Exception ex){}
	       		TabConsultaDAO consultaDAO = new TabConsultaDAO();
				List<TabConsultaVO> o=null;
				try {
					o = consultaDAO.readDadosFornecedorByPeriodo(consultasVO);
				} catch (InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				} finally{
					try {
						ConnectionFactory.getClosedConnection();
					} catch (IllegalAccessException | InstantiationException e) {
						e.printStackTrace();
					}
				}
	
				Object[] objects = new Object[8];
				int i = 0;
				for(TabConsultaVO consultaVO : o){
					objects[i] = consultaVO.getFornecedorPk();
					objects[i+1] = consultaVO.getNome();
					objects[i+2] = consultaVO.getEndereco();
					objects[i+3] = consultaVO.getCidade();
					objects[i+4] = consultaVO.getEstado();
					objects[i+5] = consultaVO.getTelefoneA();
					objects[i+6] = consultaVO.getCelularA();
					objects[i+7] = (String.valueOf(sdf.format(consultaVO.getDataCadastros())));
					
					aModel.addRow(objects);
					}
				jtTabela.setModel(aModel);
				jtxtDatainicio.setText("");jtxtDatafinal.setText("");
    		}catch(SQLException e){
    			JOptionPane.showMessageDialog(this, "Erro ao localizar as informações.","",JOptionPane.INFORMATION_MESSAGE);
    		}
    	}
    	
    	if (c.getSource()== btoCancelar){
  			limpaCampos();
  			setTitle("Tabela de Consultas");
  			aModel.setRowCount(0);
  			aModel.setColumnCount(0);
  			txtCodigo.setEnabled(true);txtNome.setEnabled(true);
    		jtxtDatainicio.setEnabled(true);btoCancelar.requestFocus(true);
    	}
    	if (c.getSource()== btoSair){
    		int resultado = JOptionPane.showConfirmDialog(this,"Deseja sair da tabela de consultas?","ControlMoto",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
    			if(resultado == 0){
    				TabConsulta.this.dispose();
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
		txtCodigo.setText("");
		txtNome.setText("");
		txtOrdem.setText("");
		jtxtDatainicio.setText("");
		jtxtDatafinal.setText("");
	}
    }