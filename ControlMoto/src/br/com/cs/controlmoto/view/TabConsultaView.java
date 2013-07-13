package br.com.cs.controlmoto.view;

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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.text.MaskFormatter;

public class TabConsultaView extends JInternalFrame implements ActionListener, FocusListener, KeyListener, MouseListener, ItemListener  {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7314428362478727677L;
	
	JLabel lblCodigo,lblNome,lblDatainicio,lblDatafinal,lblOrdem,lblPergunta;
	JTextField txtCodigo,txtNome,txtOrdem;
	JFormattedTextField jtxtDatainicio,jtxtDatafinal;
	JButton btoCliente,btoMotorista,btoLocalizar,btoCancelar,btoSair;
	CheckboxGroup ckboxgGrupo;
	static DefaultTableModel aModel;
	JTable jtTabela;
	JScrollPane jspPane;
	JPanel jpBotao,jpPergunta,jpTabela,jpComponentes;
	JCheckBox jcboxCliente,jcboxMotorista;
	
	private ControlMotoView telaControlMoto;
	
	Container content;
	Connection MinhaConexao;
	PreparedStatement ps;
	Statement MinhaSQL;
	ResultSet rs;
	
	String Controle="", LocControle="", separadorPath="";
	Date datas;
	SimpleDateFormat sdf,sdfTimestamp;
    Timestamp timestamp;
    
	DefaultTableCellRenderer tcrDireta,tcrEsqueda,tcrCentro;
	TableColumnModel colModel;
	DateFormat formatar;
	static Object[] objects = null;

	//Serve para mover a tela
    static int openFrameCount = 4;
    static final int xOffset = 45, yOffset = 40;
	
    public TabConsultaView(String titulo, ControlMotoView controlMoto){
    	super(titulo,false,true,false,true);
    	setSize(700,500);
    	setVisible(true);
    	setLocation(xOffset*openFrameCount, yOffset*openFrameCount);
    	separadorPath = System.getProperty("file.separator");
    	//ImageIcon icone = new ImageIcon("..//ControlMoto/imagens/logo_cs_b.gif");
    	//ImageIcon icone = new ImageIcon(".."+separadorPath+separadorPath+"ControlMoto"+separadorPath+"imagens"+separadorPath+"logo_cs_b.gif");
    	//setIconImage(icone.getImage());
    	this.telaControlMoto = telaControlMoto;
    	controlMoto.jDesktopPane.add(this);
   
    	content = getContentPane();
    	content.setLayout(new BorderLayout());
    	
    	formatar = new SimpleDateFormat().getDateInstance();
    	    	
    	lblCodigo = new JLabel("Codigo");lblNome = new JLabel("Nome");lblDatainicio = new JLabel("Data de Inicio");
    	lblDatafinal = new JLabel("Data Final");lblOrdem = new JLabel("Ordem de Servico N");
    	lblPergunta = new JLabel("Escolha o que voce deseja pesquisar!");
    	
    	txtCodigo = new JTextField();txtNome = new JTextField();txtOrdem = new JTextField();
    	jtxtDatainicio = new JFormattedTextField(setMascara("##/##/####"));
    	jtxtDatafinal = new JFormattedTextField(setMascara("##/##/####"));
    	
    	btoCliente = new JButton("Cliente");btoMotorista = new JButton("Motorista");
    	btoLocalizar = new JButton("Localizar");btoCancelar = new JButton("Cancelar");
    	btoSair = new JButton("Sair");//btoOrdem = new JButton("Ordens");
    	
    	jpBotao = new JPanel();jpPergunta = new JPanel();
    	jpTabela = new JPanel();jpComponentes = new JPanel();
    	jtTabela = new JTable();
    	//TIRA O AUTO-DIMENCIONAR DA CELULA ASSIM APARECEM AS BARRAS DE ROLAGEM
    	jtTabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    	
    	tcrEsqueda = new DefaultTableCellRenderer();tcrEsqueda.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
    	tcrDireta = new DefaultTableCellRenderer();tcrDireta.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    	tcrCentro = new DefaultTableCellRenderer();tcrCentro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    	ckboxgGrupo = new CheckboxGroup();
    	
    	jcboxCliente = new JCheckBox("Cliente");jcboxCliente.addItemListener(this);
    	jcboxMotorista = new JCheckBox("Motorista");jcboxMotorista.addItemListener(this);
    	
     	GridBagConstraints cons = new GridBagConstraints();
    	Container jpComponenntes = getContentPane();
    	GridBagLayout componentes = new GridBagLayout();
    	jpComponentes.setLayout(componentes);
    	jpComponentes.setSize(500,430);
    	cons.insets = new Insets(5,3,5,3);
    	//cons.weightx = 0.30;cons.weighty = 0.30;
    	cons.anchor = GridBagConstraints.NORTHWEST;
    	
    	ControlMotoView cm = new ControlMotoView();

    	cons.gridx = 3;cons.gridy = 1;cons.gridwidth = 5;cons.ipadx = 20;
    	jpComponentes.add(lblPergunta, cons);
    	cons.gridx = 3;cons.gridy = 2;cons.gridwidth = 1;cons.ipadx = 20;
    	jpComponentes.add(jcboxCliente, cons);
    	cons.gridx = 5;cons.gridy = 2;cons.gridwidth = 1;cons.ipadx = 20;
    	jpComponentes.add(jcboxMotorista, cons);
    	
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
    	cons.gridx = 12;cons.gridy = 3;cons.gridwidth = 1;cons.gridheight = 1;cons.ipadx = 10;
    	jpComponentes.add(btoLocalizar, cons);
    	btoLocalizar.addActionListener(this);
    	
    	cons.gridx = 12;cons.gridy = 5;cons.gridwidth = 1;cons.gridheight = 1;cons.ipadx = 10;
    	jpComponentes.add(btoCancelar, cons);
    	btoCancelar.addActionListener(this);
    	
    	cons.gridx = 12;cons.gridy = 7;cons.gridwidth = 1;cons.gridheight = 1;cons.ipadx = 10;
    	jpComponentes.add(btoSair, cons);
    	btoSair.addActionListener(this);
    	
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

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}
    }