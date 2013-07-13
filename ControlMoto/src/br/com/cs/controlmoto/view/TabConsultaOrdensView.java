package br.com.cs.controlmoto.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.NumberFormat;
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

public class TabConsultaOrdensView extends JInternalFrame implements ActionListener, FocusListener, MouseListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1379654176912208199L;

	JLabel lblCodigo,lblNome,lblDatainicio,lblDatafinal,lblOrdem;
	
	JTextField txtCodigo,txtNome,txtOrdem;
	
	JFormattedTextField jtxtDatainicio,jtxtDatafinal;
	
	JButton btoOrdem,btoLocalizar,btoCancelar,btoSair;
	
	DefaultTableModel aModel;
	
	JTable jtTabela;
	
	JScrollPane jspPane;
	
	JPanel jpBotao,jpTabela,jpComponentes;
	JInternalFrame tabConsultaOrdens;
	JCheckBox windowClosable;
	
	private ControlMotoView telaControlMoto;
	
	DefaultTableCellRenderer tcrDireta,tcrEsqueda,tcrCentro;
	
	TableColumnModel colModel;
	
	Container content;
	
	PreparedStatement ps; Statement MinhaSQL; ResultSet rs; Connection MinhaConexao;
	
	boolean data;
	
	String separadorPath = "";
	
	Date datas; DateFormat formatar;  NumberFormat vr;	SimpleDateFormat sdf,sdfTimestamp; Timestamp timestamp;
	
	//Serve para mover a tela
    static int openFrameCount = 7;
    static final int xOffset = 60, yOffset = 55;
    
    public TabConsultaOrdensView(String titulo, ControlMotoView controlMoto){
    	   	
    	super(titulo,false,true,false,true);
    	setSize(700,430);
    	setVisible(true);
    	setLocation(xOffset*openFrameCount, yOffset*openFrameCount);
//    	ImageIcon icone = new ImageIcon("..//ControlMoto/imagens/logo_cs_b.gif");
//    	ImageIcon icone = new ImageIcon(".."+separadorPath+separadorPath+"ControlMoto"+separadorPath+"imagens"+separadorPath+"logo_cs_b.gif");
//    	setIconImage(icone.getImage());
//    	TabConsultaOrdens.setDefaultLocale(null);

    	this.telaControlMoto = telaControlMoto;
    	controlMoto.jDesktopPane.add(this);
   
    	content = getContentPane();
    	content.setLayout(new BorderLayout());
    	
    	formatar = new SimpleDateFormat().getDateInstance();
    	
    	tabConsultaOrdens = new JInternalFrame();
    	
    	lblOrdem = new JLabel("Numero da O.S.");;lblNome = new JLabel("Nome do Cliente");lblDatainicio = new JLabel("Data de Inicio");
    	lblDatafinal = new JLabel("Data Final");//lblOrdem = new JLabel("Ordem de Servico N");
    	
    	//txtCodigo = new JTextField();
    	txtNome = new JTextField();txtOrdem = new JTextField();
    	jtxtDatainicio = new JFormattedTextField(setMascara("##/##/####"));
    	jtxtDatafinal = new JFormattedTextField(setMascara("##/##/####"));
    	
    	btoOrdem = new JButton("Ordens");btoLocalizar = new JButton("Localizar");
    	btoCancelar = new JButton("Cancelar");btoSair = new JButton("Sair");btoSair.requestFocus();
    	
    	jpBotao = new JPanel();jpTabela = new JPanel();jpComponentes = new JPanel();
    	
    	jtTabela = new JTable();
//    	TIRA O AUTO-DIMENCIONAR DA CELULA ASSIM APARECEM AS BARRAS DE ROLAGEM
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
    	cons.gridx = 12;cons.gridy = 3;cons.gridwidth = 1;cons.ipadx = 10;
    	jpComponentes.add(btoLocalizar, cons);
    	btoLocalizar.addActionListener(this);
    	
    	cons.gridx = 12;cons.gridy = 5;cons.gridwidth = 1;cons.ipadx = 10;
    	jpComponentes.add(btoCancelar, cons);
    	btoCancelar.addActionListener(this);
    	
    	cons.gridx = 12;cons.gridy = 7;cons.gridwidth = 1;cons.ipadx = 10;
    	jpComponentes.add(btoSair, cons);
    	btoSair.addActionListener(this);
    	
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
		
    }