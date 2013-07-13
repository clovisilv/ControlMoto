package br.com.cs.controlmoto.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

class FecharOrdemView extends JInternalFrame implements ActionListener, FocusListener, KeyListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1246673835285053730L;
	
	JLabel	lblCodigoordem,lblNomecliente,lblEndereco,lblValorhora,lblTaxa,lblNomemotorista,lblDatainicio,
			lblDatatermino,lblHorainicio,lblHoratermino,lblTotalhoras,lblTotalcliente,lblComissao,lblTotalmotorista,
			lblObsTermino,lblDatavencimento,lblInfoOrdem,lblInfoCliente,lblInfoMotorista;
	
	JTextField	txtCodigoordem,txtNomecliente,txtEndereco,txtValorhora,txtTaxa,txtNomemotorista,txtTotalcliente,
				txtObsTermino,txtComissao,txtTotalmotorista;
	
	JTextArea	jtaObsTermino;
	
	JFormattedTextField	JtxtDatainicio,JtxtHorainicio,JtxtDatatermino,JtxtHoratermino,JtxtTotalhoras,JtxtDatavencimento;
	
	JRadioButton	jrbAberta,jrbFechada,jrbCancelada;
	
	ButtonGroup	 btogOpcao = null;
	
	JButton	btoLocalizar,btoEncerrar,btoAlterar,btoCancelar,btoDesistir,btoLiberar,btoSair,btoImprimir;
	
	JScrollPane jspObsTermino;
	
	JSeparator	jsInfoOrdem,jsInfoCliente,jsInfoMotorista;
	
	private ControlMotoView telaControlMoto;
	
	NumberFormat vlrCliente, vlrMotorista, vr;
	String	Status ="", separadorPath = "";
	private float vHora, vlClie, comMotorista, totalClie, vlMot;
	int posicao = 0;
	Date	hTerm, hIn;
	DateFormat	hTermino, hInicio, formataData;
	Date data;
    SimpleDateFormat sdf,sdfTimestamp,sdfHora;
    Timestamp timestamp;
	double	valor;
	Statement	MinhaSQL;
	ResultSet	rs;
	//Serve para mover a tela
    static int openFrameCount = 6;
    static final int xOffset = 55, yOffset = 50;
	
	public FecharOrdemView(String titulo, ControlMotoView controlMoto){
		super(titulo,false,true,false,true);
		setSize(600,500);
		setVisible(true);
    	setLocation(xOffset*openFrameCount, yOffset*openFrameCount);
		separadorPath = System.getProperty("file.separator");
//		ImageIcon icone = new ImageIcon("..//ControlMoto/imagens/logo_cs_b.gif");
//		ImageIcon icone = new ImageIcon(".."+separadorPath+separadorPath+"ControlMoto"+separadorPath+"imagens"+separadorPath+"logo_cs_b.gif");
//		setIconImage(icone.getImage());
		this.telaControlMoto = telaControlMoto;
		controlMoto.jDesktopPane.add(this);

		Container CadSer = getContentPane();
		GridBagLayout layout = new GridBagLayout();
		CadSer.setLayout(layout);

		GridBagConstraints cons = new GridBagConstraints();
		cons.insets = new Insets (3,5,3,5); 
		cons.weightx = 0.30;cons.weighty = 0.30;
		
		formataData = new SimpleDateFormat().getDateInstance();
		vr = NumberFormat.getCurrencyInstance().getNumberInstance();
//		vr = NumberFormat.getCurrencyInstance().getNumberInstance(new Locale("pt", "BR"));
		vr.setMaximumFractionDigits(2);vr.setMinimumFractionDigits(2);

		vlrCliente = NumberFormat.getCurrencyInstance().getNumberInstance(new Locale("",""));
		vlrCliente.setMaximumFractionDigits(2);vlrCliente.setMinimumFractionDigits(2);
		vlrMotorista = NumberFormat.getCurrencyInstance().getNumberInstance(new Locale("",""));
		vlrMotorista.setMaximumFractionDigits(2);vlrMotorista.setMinimumFractionDigits(2);

		lblCodigoordem = new JLabel("Código O.S.");lblNomecliente = new JLabel("Nome Cliente");lblEndereco = new JLabel("Endereço");
		lblValorhora = new JLabel("Valor Hora");lblTaxa = new JLabel("Taxa");lblNomemotorista = new JLabel("Nome Motorista");
		lblDatainicio = new JLabel("Data Início");lblDatatermino = new JLabel("Data Término");lblHorainicio = new JLabel("Hora Início");
		lblHoratermino = new JLabel("Hora Término");lblTotalhoras = new JLabel("Total Horas");lblTotalcliente = new JLabel("Total Cliente");
		lblComissao = new JLabel("Comissão");lblTotalmotorista = new JLabel("Total Motorista");lblDatavencimento = new JLabel("Data Vencimento");
		lblObsTermino = new JLabel("Observação");lblInfoOrdem = new JLabel("Informações Ordem Serviço");lblInfoCliente = new JLabel("Informações Cliente");lblInfoMotorista = new JLabel("Informações Motorista");

		txtCodigoordem = new JTextField(10);txtNomecliente = new JTextField(30);txtEndereco = new JTextField(10);
		txtValorhora = new JTextField(10);txtTaxa = new JTextField(10);txtNomemotorista = new JTextField(80);
		txtTotalcliente = new JTextField(10);txtTotalcliente.selectAll();txtComissao = new JTextField(20);
		txtObsTermino = new JTextField(20);txtTotalmotorista = new JTextField(20);
				
		jtaObsTermino = new JTextArea(3,30);
		
		jspObsTermino = new JScrollPane(jtaObsTermino);

		JtxtDatainicio = new JFormattedTextField(setMascara("##/##/####"));
		JtxtDatatermino = new JFormattedTextField(setMascara("##/##/####"));
		JtxtHorainicio = new JFormattedTextField(setMascara("##:##"));
		JtxtHoratermino = new JFormattedTextField(setMascara("##:##"));
		JtxtTotalhoras = new JFormattedTextField(setMascara("##:##"));
		JtxtDatavencimento = new JFormattedTextField(setMascara("##/##/####"));

		jrbAberta = new JRadioButton("Aberta");
		jrbFechada = new JRadioButton("Fechada");
		jrbCancelada = new JRadioButton("Cancelada");
		
		sdf = new SimpleDateFormat ("dd/MM/yyyy");
		sdfTimestamp = new SimpleDateFormat ("yyyy/MM/dd");
		sdfHora = new SimpleDateFormat("HH:MM");
//		dt = new java.sql.Date();
		sdf.setLenient (false);sdfTimestamp.setLenient (false);

		btogOpcao = new ButtonGroup();
		btogOpcao.add(jrbAberta);
		btogOpcao.add(jrbFechada);
		btogOpcao.add(jrbCancelada);

		btoLocalizar = new JButton("Localizar");btoLocalizar.setMnemonic(KeyEvent.VK_L);
		btoEncerrar = new JButton("Encerrar");btoEncerrar.setMnemonic(KeyEvent.VK_E);
		btoAlterar = new JButton("Alterar");btoAlterar.setMnemonic(KeyEvent.VK_A);
		btoSair = new JButton("Sair");btoSair.setMnemonic(KeyEvent.VK_S);
		btoCancelar = new JButton("Cancelar");btoCancelar.setMnemonic(KeyEvent.VK_C);
		btoDesistir = new JButton("Desistir");btoDesistir.setMnemonic(KeyEvent.VK_D);
		btoLiberar = new JButton("Liberar");btoLiberar.setMnemonic(KeyEvent.VK_B);
		btoImprimir = new JButton("Imprimir");btoImprimir.setMnemonic(KeyEvent.VK_I);
		
		jsInfoOrdem = new JSeparator();jsInfoCliente = new JSeparator();jsInfoMotorista = new JSeparator();
		//Determina os espacos entre os componentes
		cons.anchor = GridBagConstraints.NORTHWEST;
		//cons.fill = GridBagConstraints.HORIZONTAL;
		/*
		 * Insere JLabel de Informações oedem de serviço
		 * Insere JSeparador de Informações ordem de serviço
		 */
		cons.gridx = 1;cons.gridy = 1;cons.gridwidth = 3; cons.ipadx = 80;
		lblInfoOrdem.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		lblInfoOrdem.setForeground(Color.BLUE);
		CadSer.add(lblInfoOrdem,cons);
		cons.gridx = 1;cons.gridy = 2;cons.gridwidth = 10;cons.ipadx = 500;
		CadSer.add(jsInfoOrdem,cons);
		
		cons.gridx = 1;cons.gridy = 3;cons.gridwidth = 1; cons.ipadx = 30;
		CadSer.add(lblCodigoordem,cons);
		cons.gridx = 2;cons.gridy = 3;cons.gridwidth = 2;cons.ipadx = 80;cons.ipady = 1;
		txtCodigoordem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		CadSer.add(txtCodigoordem,cons);txtCodigoordem.addActionListener(this);
//		txtCodigoordem.setToolTipText("Digite o c�digo da ordem e precione Enter.");
		
		cons.gridx = 1;cons.gridy = 4;cons.gridwidth = 1;cons.ipadx = 30;
		CadSer.add(lblDatainicio,cons);
		cons.gridx = 2;cons.gridy = 4;cons.gridwidth = 3;cons.ipadx = 100;
		JtxtDatainicio.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		CadSer.add(JtxtDatainicio,cons);

		cons.gridx = 6;cons.gridy = 4;cons.gridwidth = 1;cons.ipadx = 30;
		CadSer.add(lblDatatermino,cons);
		cons.gridx = 7;cons.gridy = 4;cons.gridwidth = 3;cons.ipadx = 100;
		JtxtDatatermino.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		CadSer.add(JtxtDatatermino,cons);JtxtDatatermino.selectAll();
		JtxtDatatermino.addFocusListener(this);

		cons.gridx = 1;cons.gridy = 5;cons.gridwidth = 1;cons.ipadx = 30;
		CadSer.add(lblHorainicio,cons);
		cons.gridx = 2;cons.gridy = 5;cons.gridwidth = 2;cons.ipadx = 100;
		JtxtHorainicio.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		CadSer.add(JtxtHorainicio,cons);

		cons.gridx = 6;cons.gridy = 5;cons.gridwidth = 1;cons.ipadx = 30;
		CadSer.add(lblHoratermino,cons);
		cons.gridx = 7;cons.gridy = 5;cons.gridwidth = 2;cons.ipadx = 100;
		JtxtHoratermino.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		CadSer.add(JtxtHoratermino,cons);JtxtHoratermino.selectAll();
		JtxtHoratermino.addActionListener(this);

		cons.gridx = 1;cons.gridy = 6;cons.gridwidth = 1;cons.ipadx = 30;
		CadSer.add(lblTotalhoras,cons);
		cons.gridx = 2;cons.gridy = 6;cons.gridwidth = 2;cons.ipadx = 100;
		JtxtTotalhoras.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		CadSer.add(JtxtTotalhoras,cons);JtxtTotalhoras.selectAll();
		JtxtTotalhoras.addActionListener(this);JtxtTotalhoras.addFocusListener(this);
		
		cons.gridx = 1;cons.gridy = 7;cons.gridwidth = 1;cons.ipadx = 30;
		CadSer.add(lblObsTermino,cons);
		cons.gridx = 2;cons.gridy = 7;cons.gridwidth = 10;cons.ipadx = 380;cons.ipady = 30;
		jspObsTermino.setNextFocusableComponent(txtTotalcliente);
		CadSer.add(jspObsTermino,cons);jtaObsTermino.addKeyListener(this);
		//txtObsTermino.selectAll();
//		jtxtaObsTermino.addActionListener(this);txtObsTermino.addFocusListener(this);
		/*
		 * Insere JLabel de Informações do cliente
		 * Insere JSeparador de Informações do cliente
		 */
		cons.gridx = 1;cons.gridy = 10;cons.gridwidth = 3;cons.ipadx = 80;cons.ipady = 1;
		lblInfoCliente.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		lblInfoCliente.setForeground(Color.BLUE);
		CadSer.add(lblInfoCliente,cons);
		cons.gridx = 1;cons.gridy = 11;cons.gridwidth = 10;cons.ipadx = 500;
		CadSer.add(jsInfoCliente,cons);

		cons.gridx = 1;cons.gridy = 12;cons.gridwidth = 1;cons.ipadx = 30;
		CadSer.add(lblNomecliente,cons);
		cons.gridx = 2;cons.gridy = 12;cons.gridwidth = 10;cons.ipadx = 380;//cons.ipady = 1;
		CadSer.add(txtNomecliente,cons);

		cons.gridx = 1;	cons.gridy = 13;cons.gridwidth = 1;cons.ipadx = 30;
		CadSer.add(lblEndereco,cons);
		cons.gridx = 2;cons.gridy = 13;cons.gridwidth = 10;cons.ipadx = 380;//cons.ipady = 2;
		CadSer.add(txtEndereco,cons);

		cons.gridx = 1;cons.gridy = 14;cons.gridwidth = 1;cons.ipadx = 30;
		CadSer.add(lblValorhora,cons);
		cons.gridx = 2;cons.gridy = 14;cons.gridwidth = 3;cons.ipadx = 100;//cons.ipady = 2;
		txtValorhora.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		CadSer.add(txtValorhora,cons);

		cons.gridx = 6;cons.gridy = 14;cons.gridwidth = 1;cons.ipadx = 30;
		CadSer.add(lblTaxa,cons);
		cons.gridx = 7;cons.gridy = 14;cons.gridwidth = 3;cons.ipadx = 100;//cons.ipady = 2;
		txtTaxa.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		CadSer.add(txtTaxa,cons);txtTaxa.selectAll();

		cons.gridx = 1;cons.gridy = 15;cons.gridwidth = 1;cons.ipadx = 30;
		CadSer.add(lblTotalcliente,cons);
		cons.gridx = 2;cons.gridy = 15;cons.gridwidth = 3;cons.ipadx = 100;//cons.ipady = 2;
		txtTotalcliente.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		CadSer.add(txtTotalcliente,cons);txtTotalcliente.selectAll();
		txtTotalcliente.addFocusListener(this);
		/*
		 * Insere JLabel de Informações motorista
		 * Insere JSeparador de Informações motorista
		 */
		cons.gridx = 1;cons.gridy = 16;cons.gridwidth = 3;cons.ipadx = 80;cons.ipady = 1;
		lblInfoMotorista.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		lblInfoMotorista.setForeground(Color.BLUE);
		CadSer.add(lblInfoMotorista,cons);
		cons.gridx = 1;cons.gridy = 17;cons.gridwidth = 10;cons.ipadx = 500;//cons.ipady = 2;
		CadSer.add(jsInfoMotorista,cons);
		
		cons.gridx = 1;cons.gridy = 18;cons.gridwidth = 1;cons.ipadx = 30;
		CadSer.add(lblNomemotorista,cons);
		cons.gridx = 2;cons.gridy = 18;cons.gridwidth = 10;cons.ipadx = 380;//cons.ipady = 2;
		CadSer.add(txtNomemotorista,cons);

		cons.gridx = 1;cons.gridy = 19;cons.gridwidth = 1;cons.ipadx = 30;
		CadSer.add(lblComissao,cons);
		cons.gridx = 2;cons.gridy = 19;cons.gridwidth = 2;cons.ipadx = 100;//cons.ipady = 2;
		txtComissao.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		CadSer.add(txtComissao,cons);

		cons.gridx = 6;cons.gridy = 19;cons.gridwidth = 1;cons.ipadx = 30;
		CadSer.add(lblTotalmotorista,cons);
		cons.gridx = 7;cons.gridy = 19;cons.gridwidth = 3;cons.ipadx = 100;//cons.ipady = 2;
		txtTotalmotorista.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		CadSer.add(txtTotalmotorista,cons);txtTotalmotorista.selectAll();
		txtTotalmotorista.addFocusListener(this);

		cons.anchor = GridBagConstraints.EAST; cons.fill = GridBagConstraints.BOTH;
		cons.gridx = 18;cons.gridy = 3;cons.gridwidth = 1;cons.ipadx = 40;cons.ipady = 1;
		CadSer.add(jrbAberta,cons);jrbAberta.addActionListener(this);

		cons.gridx = 18;cons.gridy = 4;cons.gridwidth = 1;cons.ipadx = 40;//cons.gridheight = 2;
		CadSer.add(jrbFechada,cons);jrbFechada.addActionListener(this);

		cons.gridx = 18;cons.gridy = 5;cons.gridwidth = 1;cons.ipadx = 40;//cons.gridheight = 2;
		CadSer.add(jrbCancelada,cons);jrbCancelada.addActionListener(this);

//		cons.anchor = GridBagConstraints.EAST; cons.fill = GridBagConstraints.BOTH;
		cons.gridx = 18;cons.gridy = 6;cons.gridwidth = 1;cons.ipadx = 40;//cons.gridheight = 1;
		CadSer.add(btoLocalizar,cons);
		btoLocalizar.addActionListener(this);

		cons.gridx = 18;cons.gridy = 12;cons.gridwidth = 1;cons.ipadx = 40;//cons.gridheight = 1
		btoEncerrar.setToolTipText("Encerra Ordem de Serviço.");
		CadSer.add(btoEncerrar,cons);
		btoEncerrar.addActionListener(this);
		
		cons.gridx = 18;cons.gridy = 12;cons.gridwidth = 1;cons.ipadx = 40;//cons.gridheight = 1
		btoImprimir.setToolTipText("Imprime Ordem de Serviço.");
		CadSer.add(btoImprimir,cons);
		btoImprimir.addActionListener(this);
		
		cons.gridx = 18;cons.gridy = 13;cons.gridwidth = 1;cons.ipadx = 40;//cons.gridheight = 1;
		btoCancelar.setToolTipText("Cancela O.S.");
		CadSer.add(btoCancelar,cons);
		btoCancelar.addActionListener(this);

		cons.gridx = 18;cons.gridy = 13;cons.gridwidth = 1;cons.ipadx = 40;//cons.gridheight = 1;
		CadSer.add(btoAlterar,cons);
		btoAlterar.addActionListener(this);

		cons.gridx = 18;cons.gridy = 14;cons.gridwidth = 1;cons.ipadx = 40;//cons.gridheight = 1;
		CadSer.add(btoLiberar,cons);
		btoLiberar.addActionListener(this);

		cons.gridx = 18;cons.gridy = 19;cons.gridwidth = 1;cons.ipadx = 40;//cons.gridheight = 1;
		btoDesistir.setToolTipText("Desistir de fechar O.S.");
		CadSer.add(btoDesistir,cons);
		btoDesistir.addActionListener(this);

		cons.gridx = 18;cons.gridy = 19;cons.gridwidth = 1;cons.ipadx = 40;//cons.gridheight = 1;
		CadSer.add(btoSair,cons);
		btoSair.addActionListener(this);

		btoLocalizar.setVisible(true);btoEncerrar.setVisible(false);btoEncerrar.setEnabled(false);
		btoImprimir.setVisible(false);btoImprimir.setEnabled(false);btoAlterar.setVisible(false);
		btoCancelar.setVisible(false);btoDesistir.setVisible(false);btoLiberar.setVisible(false);
		btoSair.setVisible(true);
		}
	
	private MaskFormatter setMascara(String mascara){
		MaskFormatter mask = null;
		try{
			mask = new MaskFormatter(mascara);
		}catch(java.text.ParseException e){}
		return mask;
	}

	public static String subitraiHora(String hora1, String hora2){
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
		formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
		long min_1 = getMinutos(hora1, formatter);
		long min_2 = getMinutos(hora2, formatter);
		long result = (min_2 - min_1)* 60 * 1000;
		Date data = new Date(result);
		return formatter.format(data);
	}

	private static long getMinutos(String hora, SimpleDateFormat fomatter){
		Date data;
		try {
			data = fomatter.parse(hora);
		}catch(ParseException e){
			return 0;
		}
		long minutos = data.getTime() / 1000 / 60;
		return minutos;
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
	
}
