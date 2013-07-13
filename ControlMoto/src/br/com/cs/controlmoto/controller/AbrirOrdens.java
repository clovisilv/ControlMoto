package br.com.cs.controlmoto.controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.MaskFormatter;
import javax.swing.text.PlainDocument;

import br.com.cs.controlmoto.domain.AbrirOrdensDAO;
import br.com.cs.controlmoto.domain.CadClienteDAO;
import br.com.cs.controlmoto.domain.CadEnderecoDAO;
import br.com.cs.controlmoto.domain.CadMotoristaDAO;
import br.com.cs.controlmoto.domain.CadTelefoneDAO;
import br.com.cs.controlmoto.domain.ConnectionFactory;
import br.com.cs.controlmoto.utils.FixedLengthDocument;
import br.com.cs.controlmoto.vo.AbrirOrdensVO;
import br.com.cs.controlmoto.vo.CadClienteVO;
import br.com.cs.controlmoto.vo.CadEnderecoVO;
import br.com.cs.controlmoto.vo.CadMotoristaVO;
import br.com.cs.controlmoto.vo.CadTelefoneVO;

/**
 * Company Fictec Cons. Inf.
 * @since 06/09/2012
 * @version 1.0.6
 * @author Clovis
 */
class AbrirOrdens extends JInternalFrame implements ActionListener, FocusListener, KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -477731764090304783L;
	private JToolBar jtbBarraFerramenta;
	
	private JLabel	lblCodigo, lblCodigocliente, lblNomecliente, lblEndereco, lblBairro, lblCidade, lblTelefone, lblContato,
			lblValorhora, lblTaxa, lblCodigomotorista, lblNomemotorista, lblComissao, lblHorainicio, lblDatainicio,
			lblTelefoneMot, lblObsInicio, lblInfoOrdem, lblInfoCliente, lblInfoMotorista;

	private JTextField	txtCodigo, txtCodigocliente, txtNomecliente, txtEndereco, txtBairro, txtCidade, txtTelefone, txtContato,
				txtValorhora, txtTaxa, txtCodigomotorista, txtNomemotorista, txtComissao, txtCelular;
	
	private JTextArea jtaObsInicio;

	private JFormattedTextField jtxtDatainicio, jtxtHorainicio;

	private JButton btoIncluir, btoLocalizar, btoGravar, btoExcluir, btoSair, btoAtualizar, btoCancelar, btoImprimir;
	
	private JSeparator jsInfoOrdem, jsInfoCliente, jsInfoMotorista;
	
	private JScrollPane jspObsInicio;
	
	private JPanel jpCampos, jpBotoes;
	
	private ControlMoto telaControlMoto;
	
	private java.util.Date data;
	private SimpleDateFormat sdf,sdfTimestamp,sdfHora;
	private Timestamp timestamp;
	private java.sql.Date dts;
    private DateFormat formatarData;
    private NumberFormat vr;
    private Double valor;
    private String caminho, status, separadorPath = "", aberta, fechada, impressa, reimperssa, dtaNova;

    private Statement minhaSTM;
    private ResultSet rs;
	
	//Serve para mover a tela
    static int openFrameCount = 5;
    static final int xOffset = 25, yOffset = 4;
    
	public AbrirOrdens(String titulo, ControlMoto controlMoto){

		super(titulo,false,true,false,true);
		setSize(630,500);
		setVisible(true);
    	setLocation(xOffset*openFrameCount, yOffset*openFrameCount);
//		ImageIcon icone = new ImageIcon("..//ControlMoto/imagens/logo_cs_b.gif");
//		ImageIcon icone = new ImageIcon(".."+separadorPath+separadorPath+"ControlMoto"+separadorPath+"imagens"+separadorPath+"logo_cs_b.gif");
//		setIconImage(icone.getImage());
		/*
		 * Determina o caminho e o tipo de separador para os arquivos conforme o sistema operacional
		 */
		caminho = new File("").getAbsolutePath();
		separadorPath = System.getProperty("file.separator");
		//icone = new ImageIcon(caminho+separadorPath+"imagens"+separadorPath+"logo_cs_b.gif");
		
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		
		this.telaControlMoto = telaControlMoto;
		controlMoto.jDesktopPane.add(this);

		jpCampos = new JPanel(); jpBotoes = new JPanel();
		
		Container CadSer = getContentPane();	
		CadSer.setLayout(new BorderLayout());
		
		GridBagConstraints cons = new GridBagConstraints();
		GridBagLayout layout = new GridBagLayout();
		jpCampos.setLayout(layout);

		//Determina como sera a expnsao dos componentes
		//cons.fill = GridBagConstraints.HORIZONTAL;
		//Determina o espacamento entre os compnentes
		cons.insets = new Insets (2,3,2,3); 
		//Determina como os componentes crescerao quando a tela aumentar de tamalho
		cons.weightx = 0.30;cons.weighty = 0.30;
		
		formatarData = new SimpleDateFormat().getDateInstance();
		
		vr = NumberFormat.getCurrencyInstance().getNumberInstance(new Locale("",""));
		vr.setMaximumFractionDigits(2);vr.setMinimumFractionDigits(2);
		
		jtbBarraFerramenta = new JToolBar(); jtbBarraFerramenta.setFloatable(false); jtbBarraFerramenta.setOrientation(javax.swing.SwingConstants.VERTICAL);

		lblCodigo = new JLabel("Código O.S.");lblCodigocliente = new JLabel("Código Cliente");lblNomecliente = new JLabel("Nome Cliente");
		lblEndereco = new JLabel("Endereço");lblBairro = new JLabel("Bairro");lblCidade = new JLabel("Cidade");lblTelefone = new JLabel("Telefone");
		lblContato = new JLabel("Contato");lblCodigomotorista = new JLabel("Código Motorista");lblComissao = new JLabel("Comissão");
		lblNomemotorista = new JLabel("Nome Motorista");lblDatainicio = new JLabel("Data Início");lblHorainicio = new JLabel("Hora Início");
		lblValorhora = new JLabel("Valor Hora");lblTaxa = new JLabel("Taxa");lblTelefoneMot = new JLabel("Telefone");
		lblObsInicio = new JLabel("Observação");lblInfoOrdem = new JLabel("Informações Ordem de Serviço");
		lblInfoCliente = new JLabel("Informações Cliente");lblInfoMotorista = new JLabel("Informações Motorista");

		txtCodigo = new JTextField(50);txtCodigocliente = new JTextField(10);txtNomecliente = new JTextField(60);txtEndereco = new JTextField(60);
		txtBairro = new JTextField("");txtCidade = new JTextField("");txtTelefone = new JTextField(20);txtContato = new JTextField(30);
		txtValorhora = new JTextField(20);txtTaxa = new JTextField(20);txtCodigomotorista = new JTextField(10);txtCodigomotorista.setDocument(new MeuDocument());
		txtNomemotorista = new JTextField(150);txtComissao = new JTextField(20);txtCelular = new JTextField();
		
		jtaObsInicio = new JTextArea(3,30);jtaObsInicio.setDocument(new FixedLengthDocument(400));
		
		jtxtDatainicio = new JFormattedTextField(setMascara("##/##/####"));jtxtDatainicio.setDocument(new FixedLengthDocument(10));
		jtxtHorainicio = new JFormattedTextField(setMascara("##:##"));jtxtHorainicio.setDocument(new FixedLengthDocument(5));

		btoIncluir = new JButton(); btoIncluir.setMnemonic(KeyEvent.VK_I); btoIncluir.setToolTipText("Novo");
		btoIncluir.setIcon(new ImageIcon(caminho+separadorPath+"imagens"+separadorPath+"icone"+separadorPath+"hd_folha_novo_32x32.png"));
		
		btoLocalizar = new JButton(); btoLocalizar.setMnemonic(KeyEvent.VK_L); btoLocalizar.setToolTipText("Localizar");
		btoLocalizar.setIcon(new ImageIcon(caminho+separadorPath+"imagens"+separadorPath+"icone"+separadorPath+"hd_lupa_32x32.png"));
		
		btoGravar = new JButton(); btoGravar.setMnemonic(KeyEvent.VK_G); btoGravar.setToolTipText("Gravar");
		btoGravar.setIcon(new ImageIcon(caminho+separadorPath+"imagens"+separadorPath+"icone"+separadorPath+"hd_flopy_32x32.png"));
		
		btoExcluir =  new JButton(); btoExcluir.setMnemonic(KeyEvent.VK_E); btoExcluir.setToolTipText("Excluir");
		btoExcluir.setIcon(new ImageIcon(caminho+separadorPath+"imagens"+separadorPath+"icone"+separadorPath+"lixeira_32x32.png"));
		
		btoSair = new JButton(); btoSair.setMnemonic(KeyEvent.VK_S); btoSair.setToolTipText("Sair");
		btoSair.setIcon(new ImageIcon(caminho+separadorPath+"imagens"+separadorPath+"icone"+separadorPath+"cancelar_circular_32x32.png"));
		
		btoAtualizar = new JButton(); btoAtualizar.setMnemonic(KeyEvent.VK_A); btoAtualizar.setToolTipText("Atualizar");
		btoAtualizar.setIcon(new ImageIcon(caminho+separadorPath+"imagens"+separadorPath+"icone"+separadorPath+"hd_atualiza_32x32.png"));
		
		btoCancelar = new JButton(); btoCancelar.setMnemonic(KeyEvent.VK_C); btoCancelar.setToolTipText("Cancelar");
		btoCancelar.setIcon(new ImageIcon(caminho+separadorPath+"imagens"+separadorPath+"icone"+separadorPath+"hd_cancela_32x32.png"));
		
		btoImprimir = new JButton(); btoImprimir.setMnemonic(KeyEvent.VK_P); btoImprimir.setToolTipText("Imprimir");
		btoImprimir.setIcon(new ImageIcon(caminho+separadorPath+"imagens"+separadorPath+"icone"+separadorPath+"impressora_empresa_32x32.png"));
		
				
		jsInfoOrdem = new JSeparator();jsInfoCliente = new JSeparator();jsInfoMotorista = new JSeparator();
		
		jspObsInicio = new JScrollPane(jtaObsInicio);
		
		sdf = new SimpleDateFormat ("dd/MM/yyyy");
		sdfTimestamp = new SimpleDateFormat ("yyyy/MM/dd");
		sdfHora = new SimpleDateFormat("HH:MM");
		sdf.setLenient (false);sdfTimestamp.setLenient (false);
		//Especifica o alinhamento dos componentes
		cons.anchor = GridBagConstraints.NORTHWEST;
		/*
		 * Insere JLabel de Informações ordem
		 * Insere JSeparador de Informações ordem
		 */
		cons.gridx = 1;cons.gridy = 1;cons.gridwidth = 3;cons.ipadx = 100;
		lblInfoOrdem.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		lblInfoOrdem.setForeground(Color.BLUE);
		jpCampos.add(lblInfoOrdem,cons);
		cons.gridx = 1;cons.gridy = 2;cons.gridwidth = 15;cons.ipadx = 750;cons.ipady = 1;
		jpCampos.add(jsInfoOrdem,cons);
		
		cons.gridx = 1;cons.gridy = 3;cons.gridwidth = 1;cons.ipadx = 110;
		jpCampos.add(lblCodigo,cons);
		cons.gridx = 2;cons.gridy = 3;cons.gridwidth = 2;cons.ipadx = 80;//cons.ipady = 1;
		txtCodigo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jpCampos.add(txtCodigo,cons);
		
		cons.gridx = 1;cons.gridy = 4;cons.gridwidth = 1;cons.ipadx = 110;
		jpCampos.add(lblDatainicio,cons);
		cons.gridx = 2;cons.gridy = 4;cons.gridwidth = 3;cons.ipadx = 100;
		jtxtDatainicio.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jtxtDatainicio.setNextFocusableComponent(jtxtHorainicio);
		jpCampos.add(jtxtDatainicio,cons);jtxtDatainicio.addFocusListener(this);

		cons.gridx = 12;cons.gridy = 4;cons.gridwidth = 1;cons.ipadx = 120;
		lblHorainicio.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jpCampos.add(lblHorainicio,cons);
		cons.gridx = 13;cons.gridy = 4;cons.gridwidth = 3;cons.ipadx = 150;
		jtxtHorainicio.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jtxtHorainicio.setNextFocusableComponent(jtaObsInicio);
		jpCampos.add(jtxtHorainicio,cons);jtxtHorainicio.addFocusListener(this);
		
		cons.gridx = 1;	cons.gridy = 5;cons.gridwidth = 1;cons.ipadx = 110;
		jpCampos.add(lblObsInicio,cons);
		cons.gridx = 2;cons.gridy = 5;cons.gridwidth = 16;cons.ipadx = 400;cons.ipady = 30;
		jspObsInicio.setNextFocusableComponent(txtCodigocliente);
		jpCampos.add(jspObsInicio,cons);txtNomecliente.setEnabled(false);
		jtaObsInicio.addKeyListener(this);
		/*
		 * Insere JLabel de Informações do cliente
		 * Insere JSeparador de Informações do cliente
		 */
		cons.gridx = 1;cons.gridy = 6;cons.gridwidth = 3;cons.ipadx = 100;cons.ipady = 1;
		lblInfoCliente.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		lblInfoCliente.setForeground(Color.BLUE);
		jpCampos.add(lblInfoCliente,cons);
		cons.gridx = 1;cons.gridy = 7;cons.gridwidth = 15;cons.ipadx = 750;cons.ipady = 1;
		jpCampos.add(jsInfoCliente,cons);
		
		cons.gridx = 1;cons.gridy = 8;cons.gridwidth = 1;cons.ipadx = 110;
		jpCampos.add(lblCodigocliente,cons);
		cons.gridx = 2;cons.gridy = 8;cons.gridwidth = 2;cons.ipadx = 80;cons.ipady = 1;
		txtCodigocliente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		txtCodigocliente.setNextFocusableComponent(txtCodigomotorista);
		jpCampos.add(txtCodigocliente,cons);
		txtCodigocliente.addActionListener(this);

		cons.gridx = 1;	cons.gridy = 9;cons.gridwidth = 1;cons.ipadx = 110;
		jpCampos.add(lblNomecliente,cons);
		cons.gridx = 2;cons.gridy = 9;cons.gridwidth = 16;cons.ipadx = 450;
		jpCampos.add(txtNomecliente,cons);txtNomecliente.setEnabled(false);

		cons.gridx = 1;cons.gridy = 10;cons.gridwidth = 1;cons.ipadx = 110;
		jpCampos.add(lblEndereco,cons);
		cons.gridx = 2;cons.gridy = 10;cons.gridwidth = 16;cons.ipadx = 450;
		jpCampos.add(txtEndereco,cons);txtEndereco.setEnabled(false);

		cons.gridx = 1;cons.gridy = 11;cons.gridwidth = 1;cons.ipadx = 110;
		jpCampos.add(lblBairro,cons);
		cons.gridx = 2;cons.gridy = 11;cons.gridwidth = 9;cons.ipadx = 310;
		jpCampos.add(txtBairro,cons);txtBairro.setEnabled(false);

		cons.gridx = 12;cons.gridy = 11;cons.gridwidth = 1;cons.ipadx = 110;
		lblCidade.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jpCampos.add(lblCidade,cons);
		cons.gridx = 13;cons.gridy = 11;cons.gridwidth = 9;cons.ipadx = 210;
		jpCampos.add(txtCidade,cons);txtCidade.setEnabled(false);

		cons.gridx = 1;cons.gridy = 12;cons.gridwidth = 1;cons.ipadx = 110;
		jpCampos.add(lblContato,cons);
		cons.gridx = 2;cons.gridy = 12;cons.gridwidth = 4;cons.ipadx = 190;
		jpCampos.add(txtContato,cons);txtContato.setEnabled(false);

		cons.gridx = 12;cons.gridy = 12;cons.gridwidth = 1;cons.ipadx = 110;
		lblTelefone.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jpCampos.add(lblTelefone,cons);
		cons.gridx = 13;cons.gridy = 12;cons.gridwidth = 6;cons.ipadx = 210;
		jpCampos.add(txtTelefone,cons);txtTelefone.setEnabled(false);

		cons.gridx = 1;cons.gridy = 13;cons.gridwidth = 1;cons.ipadx = 110;
		jpCampos.add(lblValorhora,cons);
		cons.gridx = 2;cons.gridy = 13;cons.gridwidth = 3;cons.ipadx = 90;
		txtValorhora.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jpCampos.add(txtValorhora,cons);txtValorhora.setEnabled(false);
		
		cons.gridx = 12;cons.gridy = 13;cons.gridwidth = 1;cons.ipadx = 110;
		lblTaxa.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jpCampos.add(lblTaxa,cons);
		cons.gridx = 13;cons.gridy = 13;cons.gridwidth = 3;cons.ipadx = 210;
		txtTaxa.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jpCampos.add(txtTaxa,cons);txtTaxa.setEnabled(false);
		/*
		 * Insere JLabel de Informações do motorista
		 * Insere JSeparador de Informações do motorista
		 */
		cons.gridx = 1;cons.gridy = 14;	cons.gridwidth = 3;	cons.ipadx = 100;
		lblInfoMotorista.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		lblInfoMotorista.setForeground(Color.BLUE);
		jpCampos.add(lblInfoMotorista,cons);
		cons.gridx = 1;cons.gridy = 15;cons.gridwidth = 15;cons.ipadx = 750;
		jpCampos.add(jsInfoMotorista,cons);
		
		cons.gridx = 1;cons.gridy = 16;	cons.gridwidth = 1;	cons.ipadx = 110;
		jpCampos.add(lblCodigomotorista,cons);
		cons.gridx = 2;cons.gridy = 16;cons.gridwidth = 2;cons.ipadx = 80;
		txtCodigomotorista.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jpCampos.add(txtCodigomotorista,cons);
		txtCodigomotorista.addActionListener(this);

		cons.gridx = 1;cons.gridy = 17;	cons.gridwidth = 1;cons.ipadx = 110;
		jpCampos.add(lblNomemotorista,cons);
		cons.gridx = 2;cons.gridy = 17;cons.gridwidth = 16;cons.ipadx = 450;
		jpCampos.add(txtNomemotorista,cons);txtNomemotorista.setEnabled(false);

		cons.gridx = 1;cons.gridy = 18;	cons.gridwidth = 1;cons.ipadx = 110;
		jpCampos.add(lblComissao,cons);
		cons.gridx = 2;cons.gridy = 18;cons.gridwidth = 3;cons.ipadx = 90;
		txtComissao.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jpCampos.add(txtComissao,cons);txtComissao.setEnabled(false);
		
		cons.gridx = 12;cons.gridy = 18;cons.gridwidth = 1;cons.ipadx = 110;
		lblTelefoneMot.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jpCampos.add(lblTelefoneMot,cons);
		cons.gridx = 13;cons.gridy = 18;cons.gridwidth = 3;cons.ipadx = 210;
		txtCelular.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jpCampos.add(txtCelular,cons);txtCelular.setEnabled(false);
		
		CadSer.add(jpCampos, BorderLayout.CENTER);

		cons.gridx = 26;cons.gridy = 3;cons.gridwidth = 1;cons.ipadx = 120;//cons.gridheight = 2;
		jtbBarraFerramenta.add(btoIncluir,cons);
		btoIncluir.addActionListener(this);

		cons.gridx = 26;cons.gridy = 4;cons.gridwidth = 1;cons.ipadx = 120;//cons.gridheight = 2;
		jtbBarraFerramenta.add(btoLocalizar,cons);
		btoLocalizar.addActionListener(this);

		cons.gridx = 26;cons.gridy = 5;cons.gridwidth = 1;cons.ipadx = 120;//cons.gridheight = 2;
		jtbBarraFerramenta.add(btoGravar,cons);
		btoGravar.addActionListener(this);
		
		cons.gridx = 26;cons.gridy = 6;cons.gridwidth = 1;cons.ipadx = 120;//cons.gridheight = 2;
		jtbBarraFerramenta.add(btoAtualizar,cons);
		btoAtualizar.addActionListener(this);

		cons.gridx = 26;cons.gridy = 7 ;cons.gridwidth = 1;cons.ipadx = 120;//cons.gridheight = 2;
		jtbBarraFerramenta.add(btoExcluir,cons);
		btoExcluir.addActionListener(this);
		
		cons.gridx = 26;cons.gridy = 8;cons.gridwidth = 1;cons.ipadx = 120;//cons.gridheight = 2;
		jtbBarraFerramenta.add(btoCancelar,cons);
		btoCancelar.addActionListener(this);
		
		cons.gridx = 26;cons.gridy = 9;cons.gridwidth = 1;cons.ipadx = 120;//cons.gridheight = 2;
		jtbBarraFerramenta.add(btoImprimir, cons);
		btoImprimir.addActionListener(this);

		cons.gridx = 26;cons.gridy = 10;cons.gridwidth = 1;cons.ipadx = 120;//cons.gridheight = 2;
		jtbBarraFerramenta.add(btoSair,cons);
		btoSair.addActionListener(this);
		//Controle dos botoes ao iniciar a tela
		btoIncluir.setEnabled(true);
		btoLocalizar.setEnabled(true);
		btoGravar.setEnabled(false);
		btoExcluir.setEnabled(false);
		btoSair.setEnabled(true);
		btoAtualizar.setEnabled(false);
		btoCancelar.setEnabled(false);
		btoImprimir.setEnabled(false);
		//
		jpBotoes.add(jtbBarraFerramenta);
		//
		CadSer.add(jpBotoes, BorderLayout.EAST);

	}

	public void focusGained(FocusEvent c) {
		if(c.getSource() == txtCodigo){txtCodigo.selectAll();}
		if(c.getSource() == jtxtDatainicio){
			jtxtDatainicio.selectAll();
			if (jtxtDatainicio.getText().equals("  /  /    ") || jtxtDatainicio.getValue() == null ){
				data = new java.util.Date();
				jtxtDatainicio.setText(""+ sdf.format(data));
				dtaNova = jtxtDatainicio.getText();
				dtaNova = (dtaNova.substring(6,10)+"-"+dtaNova.substring(3,5)+"-"+dtaNova.substring(0,2) );
				timestamp = new Timestamp(data.getTime());
			}
		}
		if(c.getSource() == jtxtHorainicio){
			jtxtHorainicio.selectAll();
			if (jtxtHorainicio.getValue()==null){
				data = new java.util.Date();
				jtxtHorainicio.setText(""+ sdfHora.format(data));
			}
		}
		if(c.getSource() == jtaObsInicio){
			jtaObsInicio.selectAll();
		}   
		if(c.getSource() == txtCodigocliente){txtCodigocliente.selectAll();}
		if(c.getSource() == txtNomecliente){txtNomecliente.selectAll();}
		if(c.getSource() == txtEndereco){txtEndereco.selectAll();}
		if(c.getSource() == txtBairro){txtBairro.selectAll();}
		if(c.getSource() == txtCidade){txtCidade.selectAll();}
		if(c.getSource() == txtTelefone){txtTelefone.selectAll();}
		if(c.getSource() == txtContato){txtContato.selectAll();}
		if(c.getSource() == txtValorhora){txtValorhora.selectAll();}
		if(c.getSource() == txtCodigomotorista){txtCodigomotorista.selectAll();}
		if(c.getSource() == txtNomemotorista){txtNomemotorista.selectAll();}
		if(c.getSource() == txtComissao){txtComissao.selectAll();}
		
	}
	public void focusLost(FocusEvent c) {
	}
	
	public void keyPressed(KeyEvent c) {
		if(c.getSource() == jtaObsInicio){
			if(c.getKeyCode() == 9)
				txtCodigocliente.requestFocus();			
		}
	}
	public void keyReleased(KeyEvent c) {
	}
	public void keyTyped(KeyEvent c) {
	}

	public void actionPerformed(ActionEvent c){
		if (c.getSource()== btoIncluir){
			try{
				AbrirOrdensVO abrirOrdensVO = new AbrirOrdensVO();
		
				AbrirOrdensDAO abrirOrdensDAO = new AbrirOrdensDAO();
				try {
					abrirOrdensDAO.addAbrirOrdemDAO(abrirOrdensVO);
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
	
				txtCodigo.setText(String.valueOf(abrirOrdensVO.getOrdemPk())); txtCodigo.requestFocus();
				txtCodigo.setFont( new Font("ArialBlack", Font.BOLD, 12)); txtCodigo.setEnabled(false);
				jtxtDatainicio.requestFocus();
				//Controla botoes
				btoIncluir.setEnabled(false);
				btoLocalizar.setEnabled(false);
				btoGravar.setEnabled(true);
				btoExcluir.setEnabled(false);
				btoSair.setEnabled(true);
				btoAtualizar.setEnabled(false);
				btoCancelar.setEnabled(true);
				btoImprimir.setEnabled(false);
				
			}catch(SQLException e){
				JOptionPane.showMessageDialog(this, "Erro ao abrir Ordem de Serviço.","ControlMoto",JOptionPane.INFORMATION_MESSAGE);
			}					              
		}
		
		if (c.getSource()== txtCodigocliente){
			try{
				/* * PEGA O CODIGO DO MOTORISTA DIGITADO NO CAMPO DA TELA  */
				AbrirOrdensVO abrirOrdensVO = new AbrirOrdensVO();
				abrirOrdensVO.setClienteFk(Integer.valueOf(txtCodigocliente.getText()));
				/* * ADICIONA O CODIGO NO MOTORISTAVO PARA REALIZAR A BUSCA DEPOIS COLOCA AS INFORMACOES NOS CAMPOS DA TELA  */
				CadClienteVO clienteVO = new CadClienteVO();
				clienteVO.setClientePk(abrirOrdensVO.getClienteFk());
				
				CadClienteDAO clienteDAO = new CadClienteDAO();
//				try {
				try {
					clienteDAO.readClienteByPk(clienteVO);
				} catch (IllegalAccessException | InstantiationException e) {
					e.printStackTrace();
				}finally{
					try {
						ConnectionFactory.getClosedConnection();
					} catch (InstantiationException | IllegalAccessException e) {
						e.printStackTrace();
					}
				}
//				} catch (SQLException e) {
//					JOptionPane.showMessageDialog(null,"Cliente não localizada.\nPara cadastrar clique em incluir.","ControlMoto",JOptionPane.INFORMATION_MESSAGE);
//				}
				
				txtNomecliente.setText(clienteVO.getNome());
				txtContato.setText(clienteVO.getContato());
				txtValorhora.setText(""+ vr.format(valor = Double.parseDouble(clienteVO.getValorHora())));
				txtTaxa.setText(""+ vr.format(valor = Double.parseDouble(clienteVO.getTaxa())));
				txtCodigomotorista.requestFocus();
				/* * PEGA O CODIGO DO ENDERECO NO CLIENTEVO  */
				CadEnderecoVO enderecoVO = new CadEnderecoVO();
				enderecoVO.setEnderecoPk(clienteVO.getEnderecoFk());
				/* * ADICIONA O CODIGO NO ENDERECOVO PARA REALIZAR A BUSCA  DEPOIS COLOCA AS INFORMACOES NOS CAMPOS DA TELA  */
				CadEnderecoDAO enderecoDAO = new CadEnderecoDAO();
				try {
					enderecoDAO.readEnderecoByPk(enderecoVO);
				} catch (IllegalAccessException | InstantiationException e1) {
					e1.printStackTrace();
				}finally{
					try {
						ConnectionFactory.getClosedConnection();
					} catch (InstantiationException | IllegalAccessException e) {
						e.printStackTrace();
					}
				}
				txtEndereco.setText(enderecoVO.getEndereco());
				txtBairro.setText(enderecoVO.getBairro());
				txtCidade.setText(enderecoVO.getCidade());
				/* * PEGA O CODIGO DO ENDERECO NO CLIENTEVO  */
				CadTelefoneVO telefoneVO = new CadTelefoneVO();
				telefoneVO.setTelefonePk(clienteVO.getTelefoneFk());
				/* * ADICIONA O CODIGO NO TELEFONEVO PARA REALIZAR A BUSCA DEPOIS COLOCA AS INFORMACOES NOS CAMPOS DA TELA  */
				CadTelefoneDAO telefoneDAO = new CadTelefoneDAO();
				try {
					telefoneDAO.readTelefoneByPk(telefoneVO);
				} catch (InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				} finally{
					try {
						ConnectionFactory.getClosedConnection();
					} catch (IllegalAccessException | InstantiationException e) {
						e.printStackTrace();
					}
				}
				txtTelefone.setText(telefoneVO.getTelefoneA());
			}catch(SQLException e){
				JOptionPane.showMessageDialog(this, "Cliente não localizado.","ControlMoto",JOptionPane.INFORMATION_MESSAGE);
			}			
		}

		if (c.getSource()== txtCodigomotorista){
			try{
				/* * PEGA O CODIGO DO MOTORISTA DIGITADO NO CAMPO DA TELA  */
				AbrirOrdensVO abrirOrdensVO = new AbrirOrdensVO();
				abrirOrdensVO.setMotoristaFk(txtCodigomotorista.getText());
				/*
				 * ADICIONA O CODIGO NO MOTORISTAVO PARA REALIZAR A BUSCA DEPOIS COLOCA AS INFORMACOES NOS CAMPOS DA TELA  
				 */
				CadMotoristaVO motoristaVO = new CadMotoristaVO();
				motoristaVO.setMotoristaPK(abrirOrdensVO.getMotoristaFk());
				
				CadMotoristaDAO motoristaDAO = new CadMotoristaDAO();
//				try{
				try {
					motoristaDAO.readMotoristaByPk(motoristaVO);
				} catch (IllegalAccessException | InstantiationException e) {
					e.printStackTrace();
				}finally{
					try {
						ConnectionFactory.getClosedConnection();
					} catch (InstantiationException | IllegalAccessException e) {
						e.printStackTrace();
					}
				}
//				} catch (SQLException e) {
//					JOptionPane.showMessageDialog(null,"Motorista não localizada.\nPara cadastrar clique em incluir.","ControlMoto",JOptionPane.INFORMATION_MESSAGE);
//				}
				
				txtNomemotorista.setText(motoristaVO.getNome());
				txtComissao.setText(""+ vr.format(valor = Double.parseDouble(motoristaVO.getComissao())));
				/*
				 * PEGA O CODIGO DA DE CONTATO DO MOTORISTA DIGITADO NO CAMPO DA TELA 
				 */
				CadTelefoneVO telefoneVO = new CadTelefoneVO();
				telefoneVO.setTelefonePk(motoristaVO.getTelefoneFk());
				/*
				 * ADICIONA O CODIGO NO TELEFONEVO PARA REALIZAR A BUSCA DEPOIS COLOCA AS INFORMACOES NOS CAMPOS DA TELA  
				 */
				CadTelefoneDAO telefoneDAO = new CadTelefoneDAO();
				try {
					telefoneDAO.readTelefoneByPk(telefoneVO);
				} catch (IllegalAccessException | InstantiationException e) {
					e.printStackTrace();
				}finally{
					try {
						ConnectionFactory.getClosedConnection();
					} catch (InstantiationException | IllegalAccessException e) {
						e.printStackTrace();
					}
				}				
				txtCelular.setText(telefoneVO.getCelularA());
				/*
				 * COLOCA O FOCO NO CAMPO CODIGO DO MOTORISTA
				 */
				if(btoGravar.isEnabled() == true){
					btoGravar.requestFocus();
				}
			}catch(SQLException e){
				JOptionPane.showMessageDialog(this, "Motorista não localizado.","ControlMoto",JOptionPane.INFORMATION_MESSAGE);
			}
			
		}

		if (c.getSource()== btoGravar){
			try{
				AbrirOrdensVO abrirOrdensVO = new AbrirOrdensVO();
				abrirOrdensVO.setOrdemPk(Integer.valueOf(txtCodigo.getText()));
				abrirOrdensVO.setClienteFk(Integer.valueOf(txtCodigocliente.getText()));
				abrirOrdensVO.setMotoristaFk(txtCodigomotorista.getText());
				abrirOrdensVO.setValorHora(Double.valueOf(txtValorhora.getText()));
				abrirOrdensVO.setTaxa(Double.valueOf(txtTaxa.getText()));
				abrirOrdensVO.setMotoristaFk(txtCodigomotorista.getText());
				abrirOrdensVO.setDataInicios(timestamp);
				//abrirOrdensVO.setDataInicio(dtaNova);
				abrirOrdensVO.setHoraInicio(jtxtHorainicio.getText());
				abrirOrdensVO.setObsInicio(jtaObsInicio.getText());
				abrirOrdensVO.setStatus("A");
				
				AbrirOrdensDAO abrirOrdensDAO = new AbrirOrdensDAO();
				try {
					abrirOrdensDAO.saveAbrirOrdemDAO(abrirOrdensVO);
				} catch (IllegalAccessException | InstantiationException e) {
					e.printStackTrace();
				}finally{
					try {
						ConnectionFactory.getClosedConnection();
					} catch (InstantiationException | IllegalAccessException e) {
						e.printStackTrace();
					}
				}
				//Controla botoes depois do clique no botao gravar
				btoIncluir.setEnabled(true);
				btoLocalizar.setEnabled(true);
				btoGravar.setEnabled(false);
				btoExcluir.setEnabled(false);
				btoSair.setEnabled(true);
				btoAtualizar.setEnabled(false);
				btoCancelar.setEnabled(false);
				btoImprimir.setEnabled(false);
				
				limpaCampos();
			}catch(SQLException e){
				JOptionPane.showMessageDialog(this, "Erro ao gravar Ordem de Serviço","ControlMoto",JOptionPane.INFORMATION_MESSAGE);
			}
		
		}

		if (c.getSource()== btoLocalizar ){
			try{
				AbrirOrdensVO abrirOrdensVO = new AbrirOrdensVO();
				abrirOrdensVO.setOrdemPk(Integer.valueOf(JOptionPane.showInputDialog(this,"Digite o número da ordem de serviço!","ControlMoto",JOptionPane.INFORMATION_MESSAGE)));
				AbrirOrdensDAO abrirOrdensDAO = new AbrirOrdensDAO();
				try {
					abrirOrdensDAO.readAbrirOrdemDAO(abrirOrdensVO);
				} catch (IllegalAccessException | InstantiationException e1) {
					e1.printStackTrace();
				}finally{
					try {
						ConnectionFactory.getClosedConnection();
					} catch (IllegalAccessException | InstantiationException e) {
						e.printStackTrace();
					} 
				}
				
				txtCodigo.setText(String.valueOf(abrirOrdensVO.getOrdemPk()));
				txtCodigocliente.setText(String.valueOf(abrirOrdensVO.getClienteFk()));
				txtValorhora.setText(""+ vr.format(abrirOrdensVO.getValorHora()));
				txtTaxa.setText(""+ vr.format(abrirOrdensVO.getTaxa()));
				txtCodigomotorista.setText(abrirOrdensVO.getMotoristaFk());
				jtxtDatainicio.setText(String.valueOf(sdf.format(abrirOrdensVO.getDataInicios())));
				jtxtHorainicio.setText(abrirOrdensVO.getHoraInicio());
				jtaObsInicio.setText(abrirOrdensVO.getObsInicio());
				
				CadClienteVO clienteVO = new CadClienteVO();
				clienteVO.setClientePk(abrirOrdensVO.getClienteFk());
				CadClienteDAO clienteDAO = new CadClienteDAO();
				try {
					clienteDAO.readClienteByPk(clienteVO);
				} catch (InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null,"Cliente não localizada.\nPara cadastrar clique em incluir.","ControlMoto",JOptionPane.INFORMATION_MESSAGE);
				}finally{
					try {
						ConnectionFactory.getClosedConnection();
					} catch (IllegalAccessException | InstantiationException e) {
						e.printStackTrace();
					} 
				}
				
				txtNomecliente.setText(clienteVO.getNome());
				txtContato.setText(clienteVO.getContato());
				
				CadEnderecoVO enderecoVO = new CadEnderecoVO();
				enderecoVO.setEnderecoPk(clienteVO.getEnderecoFk());
				CadEnderecoDAO enderecoDAO = new CadEnderecoDAO();
				try {
					enderecoDAO.readEnderecoByPk(enderecoVO);
				} catch (InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				}finally{
					try {
						ConnectionFactory.getClosedConnection();
					} catch (IllegalAccessException | InstantiationException e) {
						e.printStackTrace();
					}
				}
				
				txtEndereco.setText(enderecoVO.getEndereco() +", "+ clienteVO.getNumero());
				txtBairro.setText(enderecoVO.getBairro());
				txtCidade.setText(enderecoVO.getCidade());
				
				CadTelefoneVO telefoneVO = new CadTelefoneVO();
				telefoneVO.setTelefonePk(clienteVO.getTelefoneFk());
				CadTelefoneDAO telefoneDAO = new CadTelefoneDAO();
				try {
					telefoneDAO.readTelefoneByPk(telefoneVO);
				} catch (InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				}finally{
					try {
						ConnectionFactory.getClosedConnection();
					} catch (IllegalAccessException | InstantiationException e) {
						e.printStackTrace();
					}
				}
	
				txtTelefone.setText(telefoneVO.getTelefoneA());
				
				CadMotoristaVO motoristaVO = new CadMotoristaVO();
				motoristaVO.setMotoristaPK(abrirOrdensVO.getMotoristaFk());
				CadMotoristaDAO motoristaDAO = new CadMotoristaDAO();
				try {
					motoristaDAO.readMotoristaByPk(motoristaVO);
				} catch (SQLException | InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
					//JOptionPane.showMessageDialog(null,"Motorista não localizada.\nPara cadastrar clique em incluir.","ControlMoto",JOptionPane.INFORMATION_MESSAGE);
				}finally{
					try {
						ConnectionFactory.getClosedConnection();
					} catch (IllegalAccessException | InstantiationException e) {
						e.printStackTrace();
					}
				}
				
				txtNomemotorista.setText(motoristaVO.getNome());
				txtComissao.setText(motoristaVO.getComissao());
				
				telefoneVO.setTelefonePk(motoristaVO.getTelefoneFk());
				try {
					telefoneDAO.readTelefoneByPk(telefoneVO);
				} catch (InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				}finally{
					try {
						ConnectionFactory.getClosedConnection();
					} catch (IllegalAccessException | InstantiationException e) {
						e.printStackTrace();
					}
				}
				txtCelular.setText(telefoneVO.getCelularA());
				//Controla botoes depois do clique no botao localizar
				if(abrirOrdensVO.getStatus().equals("A")){//Status A, ordem de servico ja impressa.
					btoIncluir.setEnabled(false);
					btoLocalizar.setEnabled(false);
					btoAtualizar.setEnabled(true);
					btoGravar.setEnabled(false);
					btoImprimir.setEnabled(true);
					btoExcluir.setEnabled(true);
					btoSair.setEnabled(true);
					btoCancelar.setEnabled(true);
				}else if(abrirOrdensVO.getStatus().equals("A/P")){
					//Controla botoes depois do clique no botao localizar e o status igual a A/P
					btoIncluir.setEnabled(false);
					btoLocalizar.setEnabled(false);
					btoGravar.setEnabled(false);
					btoExcluir.setEnabled(true);
					btoSair.setEnabled(true);
					btoAtualizar.setEnabled(true);
					btoCancelar.setEnabled(true);
					btoImprimir.setEnabled(true);
				}
			}catch(SQLException e){
				JOptionPane.showMessageDialog(this, "Ordem de Serviço não localizada.","ControlMoto",JOptionPane.INFORMATION_MESSAGE);
			}
		}

		if (c.getSource()== btoAtualizar){
			try{
				AbrirOrdensVO abrirOrdensVO = new AbrirOrdensVO();
				abrirOrdensVO.setOrdemPk(Integer.valueOf(txtCodigo.getText()));
				abrirOrdensVO.setClienteFk(Integer.valueOf(txtCodigocliente.getText()));
				abrirOrdensVO.setValorHora(Double.valueOf(txtValorhora.getText()));
				abrirOrdensVO.setTaxa(Double.valueOf(txtTaxa.getText()));
				abrirOrdensVO.setMotoristaFk(txtCodigomotorista.getText());
				try{
					abrirOrdensVO.setDataInicios(new java.sql.Timestamp(sdf.parse(jtxtDatainicio.getText()).getTime()));
				}catch(Exception ex){}
				abrirOrdensVO.setHoraInicio(jtxtHorainicio.getText());
				abrirOrdensVO.setObsInicio(jtaObsInicio.getText());
							
				AbrirOrdensDAO abrirOrdensDAO = new AbrirOrdensDAO();
				try {
					abrirOrdensDAO.alterAbrirOrdemDAO(abrirOrdensVO);
				} catch (InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				}finally{
					try {
						ConnectionFactory.getClosedConnection();
					} catch (IllegalAccessException | InstantiationException e) {
						e.printStackTrace();
					}
				}
				//Controla botoes depois do clique no botao atualizar
				btoIncluir.setEnabled(true);
				btoLocalizar.setEnabled(true);
				btoGravar.setEnabled(false);
				btoExcluir.setEnabled(false);
				btoSair.setEnabled(true);
				btoAtualizar.setEnabled(false);
				btoCancelar.setEnabled(true);
				btoImprimir.setEnabled(false);
				
				limpaCampos();
			}catch(SQLException e){
				JOptionPane.showMessageDialog(this, "Ordem de Serviço atualizada.","ControlMoto",JOptionPane.INFORMATION_MESSAGE);
			}
			
		}

		if (c.getSource()== btoExcluir){
			try{
				AbrirOrdensVO abrirOrdensVO = new AbrirOrdensVO();
				abrirOrdensVO.setOrdemPk(Integer.valueOf(txtCodigo.getText()));
				abrirOrdensVO.setStatus("E");
				
				AbrirOrdensDAO abrirOrdensDAO = new AbrirOrdensDAO();
				try {
					abrirOrdensDAO.deleteAbrirOrdemDAO(abrirOrdensVO);
				} catch (InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				}finally{
					try {
						ConnectionFactory.getClosedConnection();
					} catch (IllegalAccessException | InstantiationException e) {
						e.printStackTrace();
					}
				}
				//Controla botoes depois do clique no botao excluir
				btoIncluir.setEnabled(true);
				btoLocalizar.setEnabled(true);
				btoGravar.setEnabled(false);
				btoExcluir.setEnabled(false);
				btoSair.setEnabled(true);
				btoAtualizar.setEnabled(false);
				btoCancelar.setEnabled(true);
				btoImprimir.setEnabled(false);
				
				limpaCampos();
			}catch(SQLException e){
				JOptionPane.showMessageDialog(this, "Ordem de Serviço excluida.","ControlMoto",JOptionPane.INFORMATION_MESSAGE);
			}			
		}

		if (c.getSource()== btoCancelar){
			JOptionPane.showMessageDialog(this,"Procedimento cancelado!","ControlMoto",JOptionPane.INFORMATION_MESSAGE);
			//Controla botoes depois do clique no botao cancelar
			btoIncluir.setEnabled(true);
			btoLocalizar.setEnabled(true);
			btoGravar.setEnabled(false);
			btoExcluir.setEnabled(false);
			btoSair.setEnabled(true);
			btoAtualizar.setEnabled(false);
			btoCancelar.setEnabled(false);
			btoImprimir.setEnabled(false);
			
			limpaCampos();
			
		}

		if (c.getSource()== btoSair){
			int resultado = JOptionPane.showConfirmDialog(this,"Quer sair de abrir orden de serviço?", "ControlMoto", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE );
			if (resultado == 0)
				AbrirOrdens.this.dispose();
		}

		if (c.getSource() == btoImprimir){
			try{
				AbrirOrdensVO abrirOrdensVO = new AbrirOrdensVO();
				abrirOrdensVO.setOrdemPk(Integer.parseInt(txtCodigo.getText()));
				abrirOrdensVO.setStatus("A/P");
				
				AbrirOrdensDAO abrirFecharDAO = new AbrirOrdensDAO();
				try {
					abrirFecharDAO.printAbrirOrdemDAO(abrirOrdensVO);
				} catch (IllegalAccessException | InstantiationException e) {
					e.printStackTrace();
				}
				//Controla botoes depois do clique no botao imprimir
				btoIncluir.setEnabled(true);
				btoLocalizar.setEnabled(true);
				btoGravar.setEnabled(false);
				btoExcluir.setEnabled(false);
				btoSair.setEnabled(true);
				btoAtualizar.setEnabled(false);
				btoCancelar.setEnabled(true);
				btoImprimir.setEnabled(false);
				
				limpaCampos();
			}catch(SQLException e){
				JOptionPane.showMessageDialog(this, "Erro ao imprimir Ordem de Serviço","ControlMoto",JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	
	class MeuDocument extends PlainDocument{
		public void insertString(int offs, String str, AttributeSet a)throws BadLocationException{
			super.insertString(offs, str.toUpperCase(),a);
		}
	}

	private MaskFormatter setMascara(String mascara){
		MaskFormatter mask = null;
		try
		{ mask = new MaskFormatter(mascara);
		}catch(java.text.ParseException e){}
		return mask;
	}
	
	public void limpaCampos(){
		txtCodigo.setText("");
		txtCodigocliente.setText("");
		txtNomecliente.setText("");
		jtaObsInicio.setText("");
		txtEndereco.setText("");
		txtBairro.setText("");
		txtCidade.setText("");
		txtTelefone.setText("");
		txtCelular.setText("");
		txtContato.setText("");
		txtValorhora.setText("");
		txtTaxa.setText("");
		txtCodigomotorista.setText("");
		txtNomemotorista.setText("");
		txtComissao.setText("");
		jtxtDatainicio.setText("");
		jtxtDatainicio.setValue("");		
		jtxtHorainicio.setText("");
		jtxtHorainicio.setValue("");
	}
	
	public void atualizaCampos(){
		try{
			txtCodigo.setText(rs.getString("Codigo"));
			txtCodigocliente.setText(rs.getString("ClienteFk"));
			txtNomecliente.setText(rs.getString("NomeCliente"));
			jtaObsInicio.setText(rs.getString("ObsInicio"));
			txtEndereco.setText(rs.getString("Endereco"));
			txtBairro.setText(rs.getString("Bairro"));
			txtCidade.setText(rs.getString("CidCli"));
			txtTelefone.setText(rs.getString("Telefone"));
			txtCelular.setText(rs.getString("Celular"));
			txtContato.setText(rs.getString("Contato"));
			txtValorhora.setText(rs.getString("ValorHora"));
			txtTaxa.setText(rs.getString("Taxa"));
			txtCodigomotorista.setText(rs.getString("MotoristaFk"));
			txtNomemotorista.setText(rs.getString("NomeMotorista"));
			txtComissao.setText(rs.getString("ComisMot"));
			jtxtDatainicio.setText(rs.getString("DataInicio"));
			jtxtHorainicio.setText(rs.getString("HoraInicio"));
		}
		catch(SQLException ex)
		{}
	}

}