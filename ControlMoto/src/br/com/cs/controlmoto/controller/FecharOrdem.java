package br.com.cs.controlmoto.controller;

import java.awt.BorderLayout;
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
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.text.MaskFormatter;

import br.com.cs.controlmoto.domain.CadClienteDAO;
import br.com.cs.controlmoto.domain.CadEnderecoDAO;
import br.com.cs.controlmoto.domain.CadMotoristaDAO;
import br.com.cs.controlmoto.domain.ConnectionFactory;
import br.com.cs.controlmoto.domain.FecharOrdemDAO;
import br.com.cs.controlmoto.utils.BarraDeStatus;
import br.com.cs.controlmoto.utils.ConvertStringToDate;
import br.com.cs.controlmoto.utils.FixedLengthDocument;
import br.com.cs.controlmoto.vo.CadClienteVO;
import br.com.cs.controlmoto.vo.CadEnderecoVO;
import br.com.cs.controlmoto.vo.CadMotoristaVO;
import br.com.cs.controlmoto.vo.FecharOrdemVO;

/**
 * Company Fictec Cons. Inf.
 * 
 * @since 06/09/2012
 * @version 1.0.6
 * @author Clovis
 */
class FecharOrdem extends JInternalFrame implements ActionListener,
		FocusListener, KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1246673835285053730L;

	private JToolBar jtbBarraFerramenta;

	private JPanel jpCampos, jpBotoes;

	private JLabel lblCodigoordem, lblNomecliente, lblEndereco, lblValorhora,
			lblTaxa, lblNomemotorista, lblDatainicio, lblDatatermino,
			lblHorainicio, lblHoratermino, lblTotalhoras, lblTotalcliente,
			lblComissao, lblTotalmotorista, lblObsTermino, lblDatavencimento,
			lblInfoOrdem, lblInfoCliente, lblInfoMotorista;

	private JTextField txtCodigoordem, txtNomecliente, txtEndereco,
			txtValorhora, txtTaxa, txtNomemotorista, txtTotalcliente,
			txtObsTermino, txtComissao, txtTotalmotorista;

	private JTextArea jtaObsTermino;

	private JFormattedTextField JtxtDatainicio, JtxtHorainicio,
			JtxtDatatermino, JtxtHoratermino, JtxtTotalhoras,
			JtxtDatavencimento;

	private JRadioButton jrbAberta, jrbFechada, jrbCancelada;

	private ButtonGroup btogOpcao = null;

	private JButton btoLocalizar, btoEncerrar, btoCancelar, btoDesistir,
			btoLiberar, btoSair, btoImprimir, btoPdf;

	private JScrollPane jspObsTermino;

	private JSeparator jsInfoOrdem, jsInfoCliente, jsInfoMotorista;

	private ControlMoto telaControlMoto;

	private NumberFormat vlrCliente, vlrMotorista, vr;
	private String Status = "", separadorPath = "", caminho;
	private float vlClie, comMotorista, totalClie, vlMot;
	private int posicao = 0;
	private Date hTerm, hIn;
	private DateFormat hTermino, hInicio, formataData;
	private Date data;
	private SimpleDateFormat sdf, sdfTimestamp, sdfHora;
	private Timestamp timestamp;
	private double valor;
	private Statement MinhaSQL;
	private ResultSet rs;
	// Serve para mover a tela
	static int openFrameCount = 6;
	static final int xOffset = 25, yOffset = 8;

	public FecharOrdem(String titulo, ControlMoto controlMoto) {
		super(titulo, false, true, false, true);
		setSize(620, 550);
		setVisible(true);
		setLocation(xOffset * openFrameCount, yOffset * openFrameCount);

		separadorPath = System.getProperty("file.separator");
		caminho = new File("").getAbsolutePath();
		// ImageIcon icone = new
		// ImageIcon("..//ControlMoto/imagens/logo_cs_b.gif");
		// ImageIcon icone = new
		// ImageIcon(".."+separadorPath+separadorPath+"ControlMoto"+separadorPath+"imagens"+separadorPath+"logo_cs_b.gif");
		// setIconImage(icone.getImage());
		this.telaControlMoto = telaControlMoto;
		controlMoto.jDesktopPane.add(this);

		jtbBarraFerramenta = new JToolBar();
		jtbBarraFerramenta.setFloatable(false);
		jtbBarraFerramenta.setOrientation(javax.swing.SwingConstants.VERTICAL);

		jpCampos = new JPanel();
		jpBotoes = new JPanel();

		Container CadSer = getContentPane();
		CadSer.setLayout(new BorderLayout());

		GridBagLayout layout = new GridBagLayout();
		jpCampos.setLayout(layout);

		GridBagConstraints cons = new GridBagConstraints();
		cons.insets = new Insets(3, 5, 3, 5);
		cons.weightx = 0.30;
		cons.weighty = 0.30;

		formataData = new SimpleDateFormat().getDateInstance();
		vr = NumberFormat.getCurrencyInstance().getNumberInstance();
		// vr = NumberFormat.getCurrencyInstance().getNumberInstance(new
		// Locale("pt", "BR"));
		vr.setMaximumFractionDigits(2);
		vr.setMinimumFractionDigits(2);

		vlrCliente = NumberFormat.getCurrencyInstance().getNumberInstance(
				new Locale("", ""));
		vlrCliente.setMaximumFractionDigits(2);
		vlrCliente.setMinimumFractionDigits(2);
		vlrMotorista = NumberFormat.getCurrencyInstance().getNumberInstance(
				new Locale("", ""));
		vlrMotorista.setMaximumFractionDigits(2);
		vlrMotorista.setMinimumFractionDigits(2);

		lblCodigoordem = new JLabel("Código O.S.");
		lblNomecliente = new JLabel("Nome Cliente");
		lblEndereco = new JLabel("Endereço");
		lblValorhora = new JLabel("Valor Hora");
		lblTaxa = new JLabel("Taxa");
		lblNomemotorista = new JLabel("Nome Motorista");
		lblDatainicio = new JLabel("Data Início");
		lblDatatermino = new JLabel("Data Término");
		lblHorainicio = new JLabel("Hora Início");
		lblHoratermino = new JLabel("Hora Término");
		lblTotalhoras = new JLabel("Total Horas");
		lblTotalcliente = new JLabel("Total Cliente");
		lblComissao = new JLabel("Comissão");
		lblTotalmotorista = new JLabel("Total Motorista");
		lblDatavencimento = new JLabel("Data Vencimento");
		lblObsTermino = new JLabel("Observação");
		lblInfoOrdem = new JLabel("Informações Ordem Serviço");
		lblInfoCliente = new JLabel("Informações Cliente");
		lblInfoMotorista = new JLabel("Informações Motorista");

		txtCodigoordem = new JTextField(10);
		txtNomecliente = new JTextField(30);
		txtEndereco = new JTextField(10);
		txtValorhora = new JTextField(10);
		txtTaxa = new JTextField(10);
		txtTaxa.setDocument(new FixedLengthDocument(7));
		txtNomemotorista = new JTextField(80);
		txtTotalcliente = new JTextField(10);
		txtTotalcliente.selectAll();
		txtComissao = new JTextField(20);
		txtObsTermino = new JTextField(20);
		txtObsTermino.setDocument(new FixedLengthDocument(7));
		txtTotalmotorista = new JTextField(20);

		jtaObsTermino = new JTextArea(3, 30);
		jtaObsTermino.setDocument(new FixedLengthDocument(400));

		jspObsTermino = new JScrollPane(jtaObsTermino);

		JtxtDatainicio = new JFormattedTextField(setMascara("##/##/####"));
		JtxtDatatermino = new JFormattedTextField(setMascara("##/##/####"));
		JtxtDatatermino.setDocument(new FixedLengthDocument(10));
		JtxtHorainicio = new JFormattedTextField(setMascara("##:##"));
		JtxtHoratermino = new JFormattedTextField(setMascara("##:##"));
		JtxtHoratermino.setDocument(new FixedLengthDocument(5));
		JtxtTotalhoras = new JFormattedTextField(setMascara("##:##"));
		JtxtDatavencimento = new JFormattedTextField(setMascara("##/##/####"));

		jrbAberta = new JRadioButton("Aberta");
		jrbFechada = new JRadioButton("Fechada");
		jrbCancelada = new JRadioButton("Cancelada");

		sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdfTimestamp = new SimpleDateFormat("yyyy/MM/dd");
		sdfHora = new SimpleDateFormat("HH:MM");
		// dt = new java.sql.Date();
		sdf.setLenient(false);
		sdfTimestamp.setLenient(false);

		btogOpcao = new ButtonGroup();
		btogOpcao.add(jrbAberta);
		btogOpcao.add(jrbFechada);
		btogOpcao.add(jrbCancelada);

		btoLocalizar = new JButton();
		btoLocalizar.setToolTipText("Localizar O.S.");
		btoLocalizar.setMnemonic(KeyEvent.VK_L);
		btoLocalizar
				.setIcon(new ImageIcon(caminho + separadorPath + "imagens"
						+ separadorPath + "icone" + separadorPath
						+ "hd_lupa_32x32.png"));

		btoEncerrar = new JButton();
		btoEncerrar.setToolTipText("Encerra O.S.");
		btoEncerrar.setMnemonic(KeyEvent.VK_E);
		btoEncerrar.setIcon(new ImageIcon(caminho + separadorPath + "imagens"
				+ separadorPath + "icone" + separadorPath
				+ "hd_flopy_32x32.png"));

		// btoAlterar = new JButton();
		// btoAlterar.setToolTipText("Atualizar O.S.");
		// btoAlterar.setMnemonic(KeyEvent.VK_A);
		// btoAlterar.setIcon(new
		// ImageIcon(caminho+separadorPath+"imagens"+separadorPath+"icone"+separadorPath+"hd_atualiza_32x32.png"));

		btoSair = new JButton();
		btoSair.setToolTipText("Sair");
		btoSair.setMnemonic(KeyEvent.VK_S);
		btoSair.setIcon(new ImageIcon(caminho + separadorPath + "imagens"
				+ separadorPath + "icone" + separadorPath
				+ "cancelar_circular_32x32.png"));

		btoCancelar = new JButton();
		btoCancelar.setToolTipText("Cancelar O.S.");
		btoCancelar.setMnemonic(KeyEvent.VK_C);
		btoCancelar.setIcon(new ImageIcon(caminho + separadorPath + "imagens"
				+ separadorPath + "icone" + separadorPath
				+ "hd_cancela_32x32.png"));

		btoDesistir = new JButton();
		btoDesistir.setToolTipText("Desistir de fechar O.S.");
		btoDesistir.setMnemonic(KeyEvent.VK_D);
		btoDesistir.setIcon(new ImageIcon(caminho + separadorPath + "imagens"
						+ separadorPath + "icone" + separadorPath
						+ "pasta_x_32x32.png"));

		btoLiberar = new JButton();
		btoLiberar.setToolTipText("Liberar O.S. fechada.");
		btoLiberar.setMnemonic(KeyEvent.VK_B);
		btoLiberar.setIcon(new ImageIcon(caminho + separadorPath + "imagens"
				+ separadorPath + "icone" + separadorPath
				+ "pasta_seta_32x32.png"));

		btoImprimir = new JButton();
		btoImprimir.setToolTipText("Imprimir O.S.");
		btoImprimir.setMnemonic(KeyEvent.VK_I);
		btoImprimir.setIcon(new ImageIcon(caminho + separadorPath + "imagens"
				+ separadorPath + "icone" + separadorPath
				+ "impressora_empresa_32x32.png"));

		btoPdf = new JButton();
		btoPdf.setToolTipText("Gerar PDF.");
		btoImprimir.setMnemonic(KeyEvent.VK_P);
		btoPdf.setIcon(new ImageIcon(caminho + separadorPath + "imagens"
				+ separadorPath + "icone" + separadorPath + "pdf_32x32.png"));

		jsInfoOrdem = new JSeparator();
		jsInfoCliente = new JSeparator();
		jsInfoMotorista = new JSeparator();
		// Determina os espacos entre os componentes
		cons.anchor = GridBagConstraints.NORTHWEST;
		// cons.fill = GridBagConstraints.HORIZONTAL;
		/*
		 * Insere JLabel de Informações oedem de serviço Insere JSeparador de
		 * Informações ordem de serviço
		 */
		cons.gridx = 1;
		cons.gridy = 1;
		cons.gridwidth = 3;
		cons.ipadx = 80;
		lblInfoOrdem.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		lblInfoOrdem.setForeground(Color.BLUE);
		jpCampos.add(lblInfoOrdem, cons);
		cons.gridx = 1;
		cons.gridy = 2;
		cons.gridwidth = 10;
		cons.ipadx = 500;
		jpCampos.add(jsInfoOrdem, cons);

		cons.gridx = 1;
		cons.gridy = 3;
		cons.gridwidth = 1;
		cons.ipadx = 30;
		jpCampos.add(lblCodigoordem, cons);
		cons.gridx = 2;
		cons.gridy = 3;
		cons.gridwidth = 2;
		cons.ipadx = 80;
		cons.ipady = 1;
		txtCodigoordem
				.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jpCampos.add(txtCodigoordem, cons);
		txtCodigoordem.addActionListener(this);
		// txtCodigoordem.setToolTipText("Digite o cï¿½digo da ordem e precione Enter.");

		cons.gridx = 1;
		cons.gridy = 4;
		cons.gridwidth = 1;
		cons.ipadx = 30;
		jpCampos.add(lblDatainicio, cons);
		cons.gridx = 2;
		cons.gridy = 4;
		cons.gridwidth = 3;
		cons.ipadx = 100;
		JtxtDatainicio.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jpCampos.add(JtxtDatainicio, cons);

		cons.gridx = 6;
		cons.gridy = 4;
		cons.gridwidth = 1;
		cons.ipadx = 30;
		jpCampos.add(lblDatatermino, cons);
		cons.gridx = 7;
		cons.gridy = 4;
		cons.gridwidth = 3;
		cons.ipadx = 100;
		JtxtDatatermino
				.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jpCampos.add(JtxtDatatermino, cons);
		JtxtDatatermino.selectAll();
		JtxtDatatermino.addFocusListener(this);

		cons.gridx = 1;
		cons.gridy = 5;
		cons.gridwidth = 1;
		cons.ipadx = 30;
		jpCampos.add(lblHorainicio, cons);
		cons.gridx = 2;
		cons.gridy = 5;
		cons.gridwidth = 2;
		cons.ipadx = 100;
		JtxtHorainicio.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jpCampos.add(JtxtHorainicio, cons);

		cons.gridx = 6;
		cons.gridy = 5;
		cons.gridwidth = 1;
		cons.ipadx = 30;
		jpCampos.add(lblHoratermino, cons);
		cons.gridx = 7;
		cons.gridy = 5;
		cons.gridwidth = 2;
		cons.ipadx = 100;
		JtxtHoratermino
				.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jpCampos.add(JtxtHoratermino, cons);
		JtxtHoratermino.selectAll();
		JtxtHoratermino.addActionListener(this);

		cons.gridx = 1;
		cons.gridy = 6;
		cons.gridwidth = 1;
		cons.ipadx = 30;
		jpCampos.add(lblTotalhoras, cons);
		cons.gridx = 2;
		cons.gridy = 6;
		cons.gridwidth = 2;
		cons.ipadx = 100;
		JtxtTotalhoras.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jpCampos.add(JtxtTotalhoras, cons);
		JtxtTotalhoras.selectAll();
		JtxtTotalhoras.addActionListener(this);
		JtxtTotalhoras.addFocusListener(this);

		cons.gridx = 1;
		cons.gridy = 7;
		cons.gridwidth = 1;
		cons.ipadx = 30;
		jpCampos.add(lblObsTermino, cons);
		cons.gridx = 2;
		cons.gridy = 7;
		cons.gridwidth = 10;
		cons.ipadx = 380;
		cons.ipady = 30;
		jspObsTermino.setNextFocusableComponent(txtTotalcliente);
		jpCampos.add(jspObsTermino, cons);
		jtaObsTermino.addKeyListener(this);
		// txtObsTermino.selectAll();
		// jtxtaObsTermino.addActionListener(this);txtObsTermino.addFocusListener(this);
		/*
		 * Insere JLabel de Informações do cliente Insere JSeparador de
		 * Informações do cliente
		 */
		cons.gridx = 1;
		cons.gridy = 10;
		cons.gridwidth = 3;
		cons.ipadx = 80;
		cons.ipady = 1;
		lblInfoCliente.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		lblInfoCliente.setForeground(Color.BLUE);
		jpCampos.add(lblInfoCliente, cons);
		cons.gridx = 1;
		cons.gridy = 11;
		cons.gridwidth = 10;
		cons.ipadx = 500;
		jpCampos.add(jsInfoCliente, cons);

		cons.gridx = 1;
		cons.gridy = 12;
		cons.gridwidth = 1;
		cons.ipadx = 30;
		jpCampos.add(lblNomecliente, cons);
		cons.gridx = 2;
		cons.gridy = 12;
		cons.gridwidth = 10;
		cons.ipadx = 380;// cons.ipady = 1;
		jpCampos.add(txtNomecliente, cons);

		cons.gridx = 1;
		cons.gridy = 13;
		cons.gridwidth = 1;
		cons.ipadx = 30;
		jpCampos.add(lblEndereco, cons);
		cons.gridx = 2;
		cons.gridy = 13;
		cons.gridwidth = 10;
		cons.ipadx = 380;// cons.ipady = 2;
		jpCampos.add(txtEndereco, cons);

		cons.gridx = 1;
		cons.gridy = 14;
		cons.gridwidth = 1;
		cons.ipadx = 30;
		jpCampos.add(lblValorhora, cons);
		cons.gridx = 2;
		cons.gridy = 14;
		cons.gridwidth = 3;
		cons.ipadx = 100;// cons.ipady = 2;
		txtValorhora.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jpCampos.add(txtValorhora, cons);

		cons.gridx = 6;
		cons.gridy = 14;
		cons.gridwidth = 1;
		cons.ipadx = 30;
		jpCampos.add(lblTaxa, cons);
		cons.gridx = 7;
		cons.gridy = 14;
		cons.gridwidth = 3;
		cons.ipadx = 100;// cons.ipady = 2;
		txtTaxa.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jpCampos.add(txtTaxa, cons);
		txtTaxa.selectAll();

		cons.gridx = 1;
		cons.gridy = 15;
		cons.gridwidth = 1;
		cons.ipadx = 30;
		jpCampos.add(lblTotalcliente, cons);
		cons.gridx = 2;
		cons.gridy = 15;
		cons.gridwidth = 3;
		cons.ipadx = 100;// cons.ipady = 2;
		txtTotalcliente
				.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jpCampos.add(txtTotalcliente, cons);
		txtTotalcliente.selectAll();
		txtTotalcliente.addFocusListener(this);
		/*
		 * Insere JLabel de Informações motorista Insere JSeparador de
		 * Informações motorista
		 */
		cons.gridx = 1;
		cons.gridy = 16;
		cons.gridwidth = 3;
		cons.ipadx = 80;
		cons.ipady = 1;
		lblInfoMotorista
				.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		lblInfoMotorista.setForeground(Color.BLUE);
		jpCampos.add(lblInfoMotorista, cons);
		cons.gridx = 1;
		cons.gridy = 17;
		cons.gridwidth = 10;
		cons.ipadx = 500;// cons.ipady = 2;
		jpCampos.add(jsInfoMotorista, cons);

		cons.gridx = 1;
		cons.gridy = 18;
		cons.gridwidth = 1;
		cons.ipadx = 30;
		jpCampos.add(lblNomemotorista, cons);
		cons.gridx = 2;
		cons.gridy = 18;
		cons.gridwidth = 10;
		cons.ipadx = 380;// cons.ipady = 2;
		jpCampos.add(txtNomemotorista, cons);

		cons.gridx = 1;
		cons.gridy = 19;
		cons.gridwidth = 1;
		cons.ipadx = 30;
		jpCampos.add(lblComissao, cons);
		cons.gridx = 2;
		cons.gridy = 19;
		cons.gridwidth = 2;
		cons.ipadx = 100;// cons.ipady = 2;
		txtComissao.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jpCampos.add(txtComissao, cons);

		cons.gridx = 6;
		cons.gridy = 19;
		cons.gridwidth = 1;
		cons.ipadx = 30;
		jpCampos.add(lblTotalmotorista, cons);
		cons.gridx = 7;
		cons.gridy = 19;
		cons.gridwidth = 3;
		cons.ipadx = 100;// cons.ipady = 2;
		txtTotalmotorista
				.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jpCampos.add(txtTotalmotorista, cons);
		txtTotalmotorista.selectAll();
		txtTotalmotorista.addFocusListener(this);

		CadSer.add(jpCampos, BorderLayout.CENTER);

		cons.anchor = GridBagConstraints.EAST;
		cons.fill = GridBagConstraints.BOTH;
		cons.gridx = 18;
		cons.gridy = 3;
		cons.gridwidth = 1;
		cons.ipadx = 40;
		cons.ipady = 1;
		jtbBarraFerramenta.add(jrbAberta, cons);
		jrbAberta.addActionListener(this);

		cons.gridx = 18;
		cons.gridy = 4;
		cons.gridwidth = 1;
		cons.ipadx = 40;// cons.gridheight = 2;
		jtbBarraFerramenta.add(jrbFechada, cons);
		jrbFechada.addActionListener(this);

		cons.gridx = 18;
		cons.gridy = 5;
		cons.gridwidth = 1;
		cons.ipadx = 40;// cons.gridheight = 2;
		jtbBarraFerramenta.add(jrbCancelada, cons);
		jrbCancelada.addActionListener(this);

		// cons.anchor = GridBagConstraints.EAST; cons.fill =
		// GridBagConstraints.BOTH;
		cons.gridx = 18;
		cons.gridy = 6;
		cons.gridwidth = 1;
		cons.ipadx = 40;
		jtbBarraFerramenta.add(btoLocalizar, cons);
		btoLocalizar.addActionListener(this);

		// cons.gridx = 18;cons.gridy = 12;cons.gridwidth = 1;cons.ipadx = 40;
		// jtbBarraFerramenta.add(btoAlterar, cons);
		// btoAlterar.addActionListener(this);

		cons.gridx = 18;
		cons.gridy = 8;
		cons.gridwidth = 1;
		cons.ipadx = 40;
		jtbBarraFerramenta.add(btoEncerrar, cons);
		btoEncerrar.addActionListener(this);

		cons.gridx = 18;
		cons.gridy = 11;
		cons.gridwidth = 1;
		cons.ipadx = 40;
		jtbBarraFerramenta.add(btoCancelar, cons);
		btoCancelar.addActionListener(this);

		cons.gridx = 18;
		cons.gridy = 13;
		cons.gridwidth = 1;
		cons.ipadx = 40;
		jtbBarraFerramenta.add(btoLiberar, cons);
		btoLiberar.addActionListener(this);

		cons.gridx = 18;
		cons.gridy = 14;
		cons.gridwidth = 1;
		cons.ipadx = 40;
		jtbBarraFerramenta.add(btoDesistir, cons);
		btoDesistir.addActionListener(this);

		cons.gridx = 18;
		cons.gridy = 9;
		cons.gridwidth = 1;
		cons.ipadx = 40;
		jtbBarraFerramenta.add(btoImprimir, cons);
		btoImprimir.addActionListener(this);

		cons.gridx = 18;
		cons.gridy = 10;
		cons.gridwidth = 1;
		cons.ipadx = 40;
		jtbBarraFerramenta.add(btoPdf, cons);
		btoPdf.addActionListener(this);

		cons.gridx = 18;
		cons.gridy = 15;
		cons.gridwidth = 1;
		cons.ipadx = 40;
		jtbBarraFerramenta.add(btoSair, cons);
		btoSair.addActionListener(this);

		jpBotoes.add(jtbBarraFerramenta);

		CadSer.add(jpBotoes, BorderLayout.EAST);

		btoLocalizar.setEnabled(true);
		btoEncerrar.setEnabled(false);
		btoLiberar.setEnabled(false);
		btoCancelar.setEnabled(false);
		btoImprimir.setEnabled(false);
		btoPdf.setEnabled(false);
		btoDesistir.setEnabled(false);
		btoSair.setVisible(true); // btoAlterar.setEnabled(false);

		// BarraDeStatusVO barraDeStatusVO = new BarraDeStatusVO();
		// try {
		// barraDeStatusVO = new FecharOrdemDAO().getInformacaoBarraDeStatus();
		// } catch (IllegalAccessException | InstantiationException |
		// SQLException e) {
		// e.printStackTrace();
		// }

		CadSer.add(new BarraDeStatus().initBaraDeStatus(), BorderLayout.SOUTH);

	}

	public void focusGained(FocusEvent c) {
		if (c.getSource() == txtCodigoordem) {
			txtCodigoordem.setFocusable(true);
			txtCodigoordem.selectAll();
		}
		if (c.getSource() == txtNomecliente) {
			txtNomecliente.setFocusable(true);
			txtNomecliente.selectAll();
		}
		if (c.getSource() == txtEndereco) {
			txtEndereco.setFocusable(true);
			txtEndereco.selectAll();
		}
		if (c.getSource() == txtValorhora) {
			txtValorhora.setFocusable(true);
			txtValorhora.selectAll();
		}
		if (c.getSource() == jtaObsTermino) {
			jtaObsTermino.setFocusable(true);
			jtaObsTermino.selectAll();
		}
		if (c.getSource() == txtTaxa) {
			txtTaxa.setFocusable(true);
			txtTaxa.selectAll();
		}
		if (c.getSource() == txtNomemotorista) {
			txtNomemotorista.setFocusable(true);
			txtNomemotorista.selectAll();
		}
		if (c.getSource() == txtTotalcliente) {
			txtTotalcliente.setFocusable(true);
			txtTotalcliente.selectAll();
		}
		if (c.getSource() == txtValorhora) {
			txtValorhora.setFocusable(true);
			txtValorhora.selectAll();
		}
		if (c.getSource() == txtTotalcliente) {
			txtTotalcliente.setFocusable(true);
			txtComissao.selectAll();
		}
		if (c.getSource() == txtTotalmotorista) {
			txtTotalmotorista.setFocusable(true);
			txtTotalmotorista.selectAll();
		}
		if (c.getSource() == JtxtDatainicio) {
			JtxtDatainicio.setFocusable(true);
			JtxtDatainicio.selectAll();
		}
		if (c.getSource() == JtxtHorainicio) {
			JtxtHorainicio.setFocusable(true);
			JtxtHorainicio.selectAll();
		}
		if (c.getSource() == JtxtDatatermino) {
			JtxtDatatermino.setFocusable(true);
			JtxtDatatermino.selectAll();
			if (JtxtDatatermino.getText().equals("")) {
				try {
					data = formataData.parse(JtxtDatatermino.getText());
					timestamp = new Timestamp(data.getTime());
				} catch (Exception ex) {
				}
			}
		}
		if (c.getSource() == JtxtHoratermino) {
			JtxtHoratermino.setFocusable(true);
			JtxtHoratermino.selectAll();
		}
		if (c.getSource() == JtxtTotalhoras) {
			JtxtTotalhoras.setFocusable(true);
			JtxtTotalhoras.selectAll();
		}
		if (c.getSource() == JtxtDatavencimento) {
			JtxtDatavencimento.setFocusable(true);
			JtxtDatavencimento.selectAll();
		}

		if (c.getSource() == JtxtTotalhoras) {
			if (JtxtHorainicio.getText() != null
					&& JtxtHoratermino.getValue() != null) {
				JtxtTotalhoras.setText(subitraiHora(JtxtHorainicio.getText(),
						JtxtHoratermino.getText()));
				System.out.println("Hora Inicio " + JtxtHorainicio.getText()
						+ " Hora Termino " + JtxtHoratermino.getText());
			}
		}

		if (c.getSource() == txtTotalcliente) {
			double vHora = Double.parseDouble(txtValorhora.getText());
			String numHora = JtxtTotalhoras.getText();
			int nHora = (Integer.parseInt(numHora.substring(0, 2)) * 60)
					+ Integer.parseInt(numHora.substring(3, 5));
			txtTotalcliente.setText("" + ((vHora / 60) * nHora));// vlrCliente.format
																	// FORMATA
																	// NUMERO
			vlClie = Float.parseFloat(txtTotalcliente.getText());
			txtTotalcliente.setText("" + vlrCliente.format(vlClie));
		}

		if (c.getSource() == txtTotalmotorista) {
			System.out.println(vlClie);
			String txtclie = txtTotalcliente.getText();
			comMotorista = Float.parseFloat(txtComissao.getText());
			// totalClie = Float.parseFloat(txtTotalcliente.getText());
			txtTotalmotorista.setText("" + ((vlClie * comMotorista) / 100));// vlrMotorista.format
																			// FORMATA
																			// NUMERO
			vlMot = Float.parseFloat(txtTotalmotorista.getText());
			txtTotalmotorista.setText("" + vlrMotorista.format(vlMot));
			System.out.println(totalClie);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.FocusListener#focusLost(java.awt.event.FocusEvent)
	 * Todos os eventos referente ao focus dos componentes
	 */
	public void focusLost(FocusEvent c) {
		if (c.getSource() == txtTotalcliente) {
			float vlclie = Float.parseFloat(txtTotalcliente.getText());
			txtTotalcliente.setText("" + vlrCliente.format(vlclie));
		}

		if (c.getSource() == txtTotalmotorista) {
			double vlclie = Double.parseDouble(txtTotalcliente.getText());
			txtTotalcliente.setText("" + vlrCliente.format(vlclie));// vlrCliente.format
																	// FORMATA
																	// NUMERO
			float vlmot = Float.parseFloat(txtTotalmotorista.getText());
			txtTotalmotorista.setText("" + vlrMotorista.format(vlmot));// vlrMotorista.format
																		// FORMATA
																		// NUMERO
		}

		if (c.getSource() == JtxtDatatermino) {
			try {
				data = formataData.parse(JtxtDatatermino.getText());
				timestamp = new Timestamp(data.getTime());
			} catch (Exception ex) {
				System.out.println(ex);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent) Todos
	 * os eventos referente acionamento das teclas
	 */
	// @Override
	public void keyPressed(KeyEvent c) {
		if (c.getSource() == jtaObsTermino) {
			if (c.getKeyCode() == 9)
				txtTotalcliente.requestFocus();
		}
	}

	// @Override
	public void keyReleased(KeyEvent c) {
	}

	// @Override
	public void keyTyped(KeyEvent c) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 * Todos os eventos referente as acoes dos componentes
	 */
	public void actionPerformed(ActionEvent c) {
		if (c.getSource() == btoLocalizar) {
			try {
				FecharOrdemVO fecharOrdemVO = new FecharOrdemVO();
				if (jrbAberta.isSelected() == true) {
					fecharOrdemVO.setStatus("A/P");
				} else if (jrbFechada.isSelected() == true) {
					fecharOrdemVO.setStatus("F");
				}
				if (jrbCancelada.isSelected() == true) {
					fecharOrdemVO.setStatus("C");
				}
				fecharOrdemVO
						.setOrdemPK(Integer.parseInt(JOptionPane
								.showInputDialog(this,
										"Digite o número da ordem de serviço!",
										"ControlMoto",
										JOptionPane.INFORMATION_MESSAGE)));
				FecharOrdemDAO fecharOrdemDAO = new FecharOrdemDAO();
				try {
					fecharOrdemDAO.readFecharOrdemDAO(fecharOrdemVO);
				} catch (InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				} finally {
					try {
						ConnectionFactory.getClosedConnection();
					} catch (IllegalAccessException | InstantiationException e) {
						e.printStackTrace();
					}
				}

				if (fecharOrdemVO.getStatus() == "A/P") {

					txtCodigoordem.setText(String.valueOf(fecharOrdemVO
							.getOrdemPK()));
					JtxtDatainicio.setText(String.valueOf(sdf
							.format(fecharOrdemVO.getDataInicios())));
					JtxtHorainicio.setText(fecharOrdemVO.getHoraInicio());
					txtValorhora.setText(String.valueOf(fecharOrdemVO
							.getValorHora()));
					txtTaxa.setText(String.valueOf(fecharOrdemVO.getTaxa()));

					// new
					// BarraDeStatusVO().setLblStatus(fecharOrdemVO.getStatus());
					// new BarraDeStatus().initBaraDeStatus();

					CadClienteVO clienteVO = new CadClienteVO();
					clienteVO.setClientePk(fecharOrdemVO.getClienteFk());
					CadClienteDAO clienteDAO = new CadClienteDAO();
					try {
						clienteDAO.readClienteByPk(clienteVO);
					} catch (SQLException | InstantiationException
							| IllegalAccessException e) {
						e.printStackTrace();
						JOptionPane
								.showMessageDialog(
										this,
										"Cliente não localizada.\nPara cadastrar clique em incluir.",
										"ControlMoto",
										JOptionPane.INFORMATION_MESSAGE);
						// return;
					} finally {
						try {
							ConnectionFactory.getClosedConnection();
						} catch (IllegalAccessException
								| InstantiationException e) {
							e.printStackTrace();
						}
					}
					txtNomecliente.setText(clienteVO.getNome());

					CadEnderecoVO enderecoVO = new CadEnderecoVO();
					enderecoVO.setEnderecoPk(clienteVO.getEnderecoFk());
					CadEnderecoDAO enderecoDAO = new CadEnderecoDAO();
					try {
						enderecoDAO.readEnderecoByPk(enderecoVO);
					} catch (InstantiationException | IllegalAccessException e) {
						e.printStackTrace();
					} finally {
						try {
							ConnectionFactory.getClosedConnection();
						} catch (IllegalAccessException
								| InstantiationException e) {
							e.printStackTrace();
						}
					}
					txtEndereco.setText(enderecoVO.getEndereco() + ", "
							+ clienteVO.getNumero());

					CadMotoristaVO motoristaVO = new CadMotoristaVO();
					motoristaVO.setMotoristaPK(fecharOrdemVO.getMotoristaFk());
					CadMotoristaDAO motoristaDAO = new CadMotoristaDAO();
					try {
						motoristaDAO.readMotoristaByPk(motoristaVO);
					} catch (SQLException | InstantiationException
							| IllegalAccessException e) {
						e.printStackTrace();
						JOptionPane
								.showMessageDialog(
										this,
										"Motorista não localizada.\nPara cadastrar clique em incluir.",
										"ControlMoto",
										JOptionPane.INFORMATION_MESSAGE);
					} finally {
						try {
							ConnectionFactory.getClosedConnection();
						} catch (IllegalAccessException
								| InstantiationException e) {
							e.printStackTrace();
						}
					}
					txtNomemotorista.setText(motoristaVO.getNome());
					txtComissao.setText(String.valueOf(motoristaVO
							.getComissao()));

					txtCodigoordem.setEnabled(false);
					txtNomecliente.setEnabled(false);
					txtEndereco.setEnabled(false);
					txtValorhora.setEnabled(false);
					txtTaxa.setEnabled(true);
					txtNomemotorista.setEnabled(false);
					JtxtDatainicio.setEnabled(false);
					JtxtHorainicio.setEnabled(false);
					JtxtDatatermino.setEnabled(true);
					JtxtHoratermino.setEnabled(true);
					JtxtTotalhoras.setEnabled(true);
					txtComissao.setEnabled(false);
					txtTotalcliente.setEnabled(true);
					txtTotalmotorista.setEnabled(true);

					JtxtDatatermino.requestFocus(true);
					txtTaxa.selectAll();
					JtxtDatatermino.selectAll();
					JtxtHoratermino.selectAll();
					JtxtTotalhoras.selectAll();
					txtTotalcliente.selectAll();
					txtTotalmotorista.selectAll();

					jrbAberta.setEnabled(false);
					jrbFechada.setEnabled(false);
					jrbCancelada.setEnabled(false);

					btoLocalizar.setEnabled(false);
					btoEncerrar.setEnabled(true);
					btoLiberar.setEnabled(false);
					btoCancelar.setEnabled(true);
					btoImprimir.setEnabled(true);
					btoPdf.setEnabled(false);
					btoDesistir.setEnabled(true);
					btoSair.setVisible(true); // btoAlterar.setEnabled(true);

				} else if (fecharOrdemVO.getStatus() == "F") {
					txtCodigoordem.setText(String.valueOf(fecharOrdemVO
							.getOrdemPK()));
					txtValorhora.setText(String.valueOf(fecharOrdemVO
							.getValorHora()));
					txtTaxa.setText(String.valueOf(fecharOrdemVO.getTaxa()));
					jtaObsTermino.setText(fecharOrdemVO.getObsTermino());
					JtxtDatainicio.setText(String.valueOf(sdf
							.format(fecharOrdemVO.getDataInicios())));
					JtxtHorainicio.setText(fecharOrdemVO.getHoraInicio());
					JtxtDatatermino.setText(String.valueOf(sdf
							.format(fecharOrdemVO.getDataTerminos())));
					JtxtHoratermino.setText(fecharOrdemVO.getHoraTermino());
					JtxtTotalhoras.setText(fecharOrdemVO.getTotalHoras());
					txtTotalcliente.setText(String.valueOf(fecharOrdemVO
							.getTotalCliente()));
					txtTotalmotorista.setText(String.valueOf(fecharOrdemVO
							.getTotalMotorista()));

					CadClienteVO clienteVO = new CadClienteVO();
					clienteVO.setClientePk(fecharOrdemVO.getClienteFk());
					CadClienteDAO clienteDAO = new CadClienteDAO();
					try {
						clienteDAO.readClienteByPk(clienteVO);
					} catch (SQLException | InstantiationException
							| IllegalAccessException e) {
						e.printStackTrace();
						JOptionPane
								.showMessageDialog(
										this,
										"Cliente não localizada.\nPara cadastrar clique em incluir.",
										"ControlMoto",
										JOptionPane.INFORMATION_MESSAGE);
					} finally {
						try {
							ConnectionFactory.getClosedConnection();
						} catch (IllegalAccessException
								| InstantiationException e) {
							e.printStackTrace();
						}
					}
					txtNomecliente.setText(clienteVO.getNome());

					CadEnderecoVO enderecoVO = new CadEnderecoVO();
					enderecoVO.setEnderecoPk(clienteVO.getEnderecoFk());
					CadEnderecoDAO enderecoDAO = new CadEnderecoDAO();
					try {
						enderecoDAO.readEnderecoByPk(enderecoVO);
					} catch (InstantiationException | IllegalAccessException e) {
						e.printStackTrace();
					} finally {
						try {
							ConnectionFactory.getClosedConnection();
						} catch (IllegalAccessException
								| InstantiationException e) {
							e.printStackTrace();
						}
					}
					txtEndereco.setText(enderecoVO.getEndereco() + ", "
							+ clienteVO.getNumero());

					CadMotoristaVO motoristaVO = new CadMotoristaVO();
					motoristaVO.setMotoristaPK(fecharOrdemVO.getMotoristaFk());
					CadMotoristaDAO motoristaDAO = new CadMotoristaDAO();
					try {
						motoristaDAO.readMotoristaByPk(motoristaVO);
					} catch (SQLException | InstantiationException
							| IllegalAccessException e) {
						e.printStackTrace();
						JOptionPane
								.showMessageDialog(
										this,
										"Motorista não localizado.\nPara cadastrar clique em incluir.",
										"ControlMoto",
										JOptionPane.INFORMATION_MESSAGE);
					} finally {
						try {
							ConnectionFactory.getClosedConnection();
						} catch (IllegalAccessException
								| InstantiationException e) {
							e.printStackTrace();
						}
					}
					txtNomemotorista.setText(motoristaVO.getNome());
					txtComissao.setText(String.valueOf(motoristaVO
							.getComissao()));

					txtCodigoordem.setEnabled(false);
					txtNomecliente.setEnabled(false);
					txtEndereco.setEnabled(false);
					txtValorhora.setEnabled(false);
					txtTaxa.setEnabled(false);
					txtNomemotorista.setEnabled(false);
					JtxtDatainicio.setEnabled(false);
					JtxtHorainicio.setEnabled(false);
					JtxtDatatermino.setEnabled(false);
					JtxtHoratermino.setEnabled(false);
					JtxtTotalhoras.setEnabled(false);
					txtComissao.setEnabled(false);
					txtTotalcliente.setEnabled(false);
					txtTotalmotorista.setEnabled(false);
					jtaObsTermino.setEnabled(false);

					jrbAberta.setEnabled(false);
					jrbFechada.setEnabled(false);
					jrbCancelada.setEnabled(false);

					btoLocalizar.setEnabled(false);
					btoEncerrar.setEnabled(false);
					btoLiberar.setEnabled(true);
					btoCancelar.setEnabled(true);
					btoImprimir.setEnabled(true);
					btoPdf.setEnabled(true);
					btoDesistir.setEnabled(true);
					btoSair.setVisible(true); // btoAlterar.setEnabled(false);

				} else if (fecharOrdemVO.getStatus() == "C") {
					txtCodigoordem.setText(String.valueOf(fecharOrdemVO
							.getOrdemPK()));
					// txtNomecliente.setText(fecharOrdemVO.getNomecliente());
					// txtEndereco.setText(fecharOrdemVO.getEndereco());
					txtValorhora.setText(String.valueOf(fecharOrdemVO
							.getValorHora()));
					txtTaxa.setText(String.valueOf(fecharOrdemVO.getTaxa()));
					// txtNomemotorista.setText(fecharOrdemVO.getNomeMotorista());
					try {
						JtxtDatainicio
								.setText(new ConvertStringToDate()
										.convertStringToData(String
												.valueOf(fecharOrdemVO
														.getDataInicios())));
						JtxtDatatermino.setText(new ConvertStringToDate()
								.convertStringToData(String
										.valueOf(fecharOrdemVO
												.getDataTerminos())));
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
					JtxtHorainicio.setText(fecharOrdemVO.getHoraInicio());
					JtxtHoratermino.setText(fecharOrdemVO.getHoraTermino());
					JtxtTotalhoras.setText(fecharOrdemVO.getTotalHoras());
					// txtComissao.setText(String.valueOf(fecharOrdemVO.getComissao()));
					txtTotalcliente.setText(String.valueOf(fecharOrdemVO
							.getTotalCliente()));
					txtTotalmotorista.setText(String.valueOf(fecharOrdemVO
							.getTotalMotorista()));

					CadClienteVO clienteVO = new CadClienteVO();
					clienteVO.setClientePk(fecharOrdemVO.getClienteFk());
					CadClienteDAO clienteDAO = new CadClienteDAO();
					try {
						clienteDAO.readClienteByPk(clienteVO);
					} catch (SQLException | InstantiationException
							| IllegalAccessException e) {
						e.printStackTrace();
						JOptionPane
								.showMessageDialog(
										this,
										"Cliente não localizada.\nPara cadastrar clique em incluir.",
										"ControlMoto",
										JOptionPane.INFORMATION_MESSAGE);
					} finally {
						try {
							ConnectionFactory.getClosedConnection();
						} catch (IllegalAccessException
								| InstantiationException e) {
							e.printStackTrace();
						}
					}
					txtNomecliente.setText(clienteVO.getNome());

					CadEnderecoVO enderecoVO = new CadEnderecoVO();
					enderecoVO.setEnderecoPk(clienteVO.getEnderecoFk());
					CadEnderecoDAO enderecoDAO = new CadEnderecoDAO();
					try {
						enderecoDAO.readEnderecoByPk(enderecoVO);
					} catch (InstantiationException | IllegalAccessException e) {
						e.printStackTrace();
					} finally {
						try {
							ConnectionFactory.getClosedConnection();
						} catch (IllegalAccessException
								| InstantiationException e) {
							e.printStackTrace();
						}
					}
					txtEndereco.setText(enderecoVO.getEndereco() + ", "
							+ clienteVO.getNumero());

					CadMotoristaVO motoristaVO = new CadMotoristaVO();
					motoristaVO.setMotoristaPK(fecharOrdemVO.getMotoristaFk());
					CadMotoristaDAO motoristaDAO = new CadMotoristaDAO();
					try {
						motoristaDAO.readMotoristaByPk(motoristaVO);
					} catch (SQLException | InstantiationException
							| IllegalAccessException e) {
						e.printStackTrace();
						JOptionPane
								.showMessageDialog(
										this,
										"Motorista não localizado.\nPara cadastrar clique em incluir.",
										"ControlMoto",
										JOptionPane.INFORMATION_MESSAGE);
					} finally {
						try {
							ConnectionFactory.getClosedConnection();
						} catch (IllegalAccessException
								| InstantiationException e) {
							e.printStackTrace();
						}
					}
					txtNomemotorista.setText(motoristaVO.getNome());
					txtComissao.setText(String.valueOf(motoristaVO
							.getComissao()));

					txtCodigoordem.setEnabled(false);
					txtNomecliente.setEnabled(false);
					txtEndereco.setEnabled(false);
					txtValorhora.setEnabled(false);
					txtTaxa.setEnabled(false);
					txtNomemotorista.setEnabled(false);
					JtxtDatainicio.setEnabled(false);
					JtxtHorainicio.setEnabled(false);
					JtxtDatatermino.setEnabled(false);
					JtxtHoratermino.setEnabled(false);
					JtxtTotalhoras.setEnabled(false);
					txtComissao.setEnabled(false);
					txtTotalcliente.setEnabled(false);
					txtTotalmotorista.setEnabled(false);

					jrbAberta.setEnabled(false);
					jrbFechada.setEnabled(false);
					jrbCancelada.setEnabled(false);

					btoLocalizar.setEnabled(false);
					btoEncerrar.setEnabled(false);
					btoLiberar.setEnabled(true);
					btoCancelar.setEnabled(false);
					btoImprimir.setEnabled(false);
					btoPdf.setEnabled(true);
					btoDesistir.setEnabled(true);
					btoSair.setEnabled(true); // btoAlterar.setEnabled(false);
				}
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null,
						"Erro ao fechar a Ordem de Serviço", "ControlMoto",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}

		if (c.getSource() == btoEncerrar) {
			try {
				FecharOrdemVO fecharOrdemVO = new FecharOrdemVO();

				fecharOrdemVO.setOrdemPK(Integer.valueOf(txtCodigoordem
						.getText()));

				if (JtxtDatatermino.getValue() == null) {
					JOptionPane.showMessageDialog(this,
							"Data Término deve ser preenchida!", "ControlMoto",
							JOptionPane.INFORMATION_MESSAGE);
					JtxtDatatermino.requestFocus(true);
					return;
				} else {
					fecharOrdemVO.setDataTerminos(timestamp);
				}
				if (JtxtHoratermino.getValue() == null) {
					JOptionPane.showMessageDialog(this,
							"Hora Término deve ser preenchida!", "ControlMoto",
							JOptionPane.INFORMATION_MESSAGE);
					JtxtHoratermino.requestFocus(true);
					return;
				} else {
					fecharOrdemVO.setHoraTermino(JtxtHoratermino.getText());
				}

				fecharOrdemVO.setTotalHoras(JtxtTotalhoras.getText());
				if (fecharOrdemVO.getTotalHoras() == null) {// 07:40 :
					JOptionPane.showMessageDialog(this,
							"Clique no campo total horas!", "ControlMoto",
							JOptionPane.INFORMATION_MESSAGE);
					JtxtTotalhoras.requestFocus(true);
					return;
				}
				fecharOrdemVO
						.setTotalCliente(Double.parseDouble(!txtTotalcliente
								.getText().isEmpty() ? txtTotalcliente
								.getText() : "0"));
				if (fecharOrdemVO.getTotalCliente().equals(0.0)) {
					JOptionPane.showMessageDialog(this,
							"Clique no campo total cliente!", "ControlMoto",
							JOptionPane.INFORMATION_MESSAGE);
					txtTotalcliente.requestFocus(true);
					return;
				}

				fecharOrdemVO
						.setTotalMotorista(Double
								.parseDouble(!txtTotalmotorista.getText()
										.isEmpty() ? txtTotalmotorista
										.getText() : "0"));
				if (fecharOrdemVO.getTotalMotorista().equals(0.0)) {
					JOptionPane.showMessageDialog(this,
							"Clique no campo total motorista!", "ControlMoto",
							JOptionPane.INFORMATION_MESSAGE);
					txtTotalmotorista.requestFocus(true);
					return;
				}

				fecharOrdemVO.setObsTermino(jtaObsTermino.getText());
				fecharOrdemVO.setStatus("F");

				FecharOrdemDAO fecharOrdemDAO = new FecharOrdemDAO();
				try {
					fecharOrdemDAO.saveFecharOrdemDAO(fecharOrdemVO);
				} catch (InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				} finally {
					try {
						ConnectionFactory.getClosedConnection();
					} catch (IllegalAccessException | InstantiationException e) {
						e.printStackTrace();
					}
				}

				JtxtDatatermino.setEnabled(false);
				JtxtHoratermino.setEnabled(false);
				JtxtTotalhoras.setEnabled(false);
				txtTotalcliente.setEnabled(false);
				txtTotalmotorista.setEnabled(false);
				jtaObsTermino.setEnabled(false);
				txtTaxa.setEnabled(false);

				btoLocalizar.setEnabled(false);
				btoEncerrar.setEnabled(false);
				btoLiberar.setEnabled(true);
				btoCancelar.setEnabled(true);
				btoImprimir.setEnabled(true);
				btoPdf.setEnabled(true);
				btoDesistir.setEnabled(true);
				btoSair.setEnabled(true); // btoAlterar.setEnabled(false);

			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this,
						"Erro ao encerrar a Ordem de Serviço", "ControlMoto",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}
		if (c.getSource() == btoImprimir) {
			try {
				FecharOrdemVO fecharOrdemVO = new FecharOrdemVO();
				fecharOrdemVO.setOrdemPK(Integer.valueOf(txtCodigoordem
						.getText()));
				fecharOrdemVO.setStatus("F");

				FecharOrdemDAO fecharOrdemDAO = new FecharOrdemDAO();
				fecharOrdemDAO.printFecharOrdemDAO(fecharOrdemVO);

				jrbAberta.setEnabled(true);
				jrbFechada.setEnabled(true);
				jrbCancelada.setEnabled(true);
				jrbAberta.setSelected(false);
				jrbFechada.setSelected(false);
				jrbCancelada.setSelected(false);

				btoLocalizar.setEnabled(true);
				btoEncerrar.setEnabled(false);
				btoLiberar.setEnabled(false);
				btoCancelar.setEnabled(false);
				btoImprimir.setEnabled(false);
				btoPdf.setEnabled(false);
				btoDesistir.setEnabled(false);
				btoSair.setEnabled(true);
				// btoAlterar.setEnabled(false);

				limpaCampos();

			} catch (Exception e) {
				JOptionPane.showMessageDialog(this,
						"Erro ao imprimir Ordem de Serviço", "ControlMoto",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}
		if (c.getSource() == btoPdf) {
			try {
				FecharOrdemVO fecharOrdemVO = new FecharOrdemVO();
				fecharOrdemVO.setOrdemPK(Integer.valueOf(txtCodigoordem
						.getText()));
				fecharOrdemVO.setStatus("F");

				FecharOrdemDAO fecharOrdemDAO = new FecharOrdemDAO();
				fecharOrdemDAO.printOrdemPdfDAO(fecharOrdemVO);

				jrbAberta.setEnabled(true);
				jrbFechada.setEnabled(true);
				jrbCancelada.setEnabled(true);
				jrbAberta.setSelected(false);
				jrbFechada.setSelected(false);
				jrbCancelada.setSelected(false);

				btoLocalizar.setEnabled(true);
				btoEncerrar.setEnabled(false);
				btoLiberar.setEnabled(false);
				btoCancelar.setEnabled(false);
				btoImprimir.setEnabled(false);
				btoPdf.setEnabled(false);
				btoDesistir.setEnabled(false);
				btoSair.setEnabled(true);
				// btoAlterar.setEnabled(false);

				limpaCampos();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this,
						"Erro ao imprimir Ordem de Serviço", "ControlMoto",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}
		if (c.getSource() == btoLiberar) {
			try {
				FecharOrdemVO fecharOrdemVO = new FecharOrdemVO();
				fecharOrdemVO.setOrdemPK(Integer.valueOf(txtCodigoordem
						.getText()));
				fecharOrdemVO.setDataTermino(null);
				fecharOrdemVO.setHoraTermino("");
				fecharOrdemVO.setObsTermino("");
				fecharOrdemVO.setTotalHoras("");
				fecharOrdemVO.setTotalCliente(0d);
				fecharOrdemVO.setTotalMotorista(0d);
				fecharOrdemVO.setStatus("A/P");

				FecharOrdemDAO fecharOrdemDAO = new FecharOrdemDAO();
				try {
					fecharOrdemDAO.cleanFecharOrdemDAO(fecharOrdemVO);
				} catch (InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				} finally {
					try {
						ConnectionFactory.getClosedConnection();
					} catch (IllegalAccessException | InstantiationException e) {
						e.printStackTrace();
					}
				}

				JtxtDatatermino.setText("");
				JtxtHoratermino.setText("");
				jtaObsTermino.setText("");
				JtxtTotalhoras.setText("");
				txtTotalcliente.setText("");
				txtTotalmotorista.setText("");

				JtxtDatatermino.requestFocus();

				txtTaxa.setEnabled(true);
				JtxtDatatermino.setEnabled(true);
				JtxtHoratermino.setEnabled(true);
				JtxtTotalhoras.setEnabled(true);
				txtTotalcliente.setEnabled(true);
				txtTotalmotorista.setEnabled(true);
				jtaObsTermino.setEnabled(true);

				txtTaxa.selectAll();
				JtxtDatatermino.selectAll();
				JtxtHoratermino.selectAll();
				txtTotalcliente.selectAll();
				txtTotalmotorista.selectAll();

				jrbAberta.setEnabled(false);
				jrbFechada.setEnabled(false);
				jrbCancelada.setEnabled(false);
				jrbAberta.setSelected(true);

				btoLocalizar.setEnabled(false);
				btoEncerrar.setEnabled(true);
				btoLiberar.setEnabled(false);
				btoCancelar.setEnabled(true);
				btoImprimir.setEnabled(false);
				btoPdf.setEnabled(false);
				btoDesistir.setEnabled(true);
				btoSair.setEnabled(true);
				// btoAlterar.setEnabled(false);

			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this,
						"Ordem de Serviço liberada.", "ControlMoto",
						JOptionPane.INFORMATION_MESSAGE);
			}

		}
		if (c.getSource() == btoCancelar) {
			try {
				FecharOrdemVO fecharOrdemVO = new FecharOrdemVO();
				fecharOrdemVO.setOrdemPK(Integer.valueOf(txtCodigoordem
						.getText()));
				fecharOrdemVO.setStatus("C");

				FecharOrdemDAO fecharOrdemDAO = new FecharOrdemDAO();
				try {
					fecharOrdemDAO.cancelFecharOrdemDAO(fecharOrdemVO);
				} catch (InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				} finally {
					try {
						ConnectionFactory.getClosedConnection();
					} catch (IllegalAccessException | InstantiationException e) {
						e.printStackTrace();
					}
				}

				jrbAberta.setEnabled(true);
				jrbFechada.setEnabled(true);
				jrbCancelada.setEnabled(true);
				jrbAberta.setSelected(false);
				jrbFechada.setSelected(false);
				jrbCancelada.setSelected(false);

				btoLocalizar.setEnabled(true);
				btoEncerrar.setEnabled(false);
				btoLiberar.setEnabled(false);
				btoCancelar.setEnabled(false);
				btoImprimir.setEnabled(false);
				btoPdf.setEnabled(false);
				btoDesistir.setEnabled(false);
				btoSair.setEnabled(true);
				// btoAlterar.setEnabled(false);

				limpaCampos();

			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this,
						"Ordem de Serviço cancelada.", "ControlMoto",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}
		if (c.getSource() == btoSair) {
			int resultado = JOptionPane.showConfirmDialog(this,
					"Quer sair de fechar oredem de serviço?", "ControlMoto",
					JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
			if (resultado == 0)
				FecharOrdem.this.dispose();
		}
		if (c.getSource() == btoDesistir) {
			limpaCampos();
			jrbAberta.setEnabled(true);
			jrbFechada.setEnabled(true);
			jrbCancelada.setEnabled(true);

			btoLocalizar.setEnabled(true);
			btoEncerrar.setEnabled(false);
			btoLiberar.setEnabled(false);
			btoCancelar.setEnabled(false);
			btoImprimir.setEnabled(false);
			btoPdf.setEnabled(false);
			btoDesistir.setEnabled(false);
			btoSair.setVisible(true); // btoAlterar.setEnabled(false);

		}
	}

	private MaskFormatter setMascara(String mascara) {
		MaskFormatter mask = null;
		try {
			mask = new MaskFormatter(mascara);
		} catch (java.text.ParseException e) {
		}
		return mask;
	}

	public static String subitraiHora(String hora1, String hora2) {
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
		formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
		long min_1 = getMinutos(hora1, formatter);
		long min_2 = getMinutos(hora2, formatter);
		long result = (min_2 - min_1) * 60 * 1000;
		Date data = new Date(result);
		return formatter.format(data);
	}

	private static long getMinutos(String hora, SimpleDateFormat fomatter) {
		Date data;
		try {
			data = fomatter.parse(hora);
		} catch (ParseException e) {
			return 0;
		}
		long minutos = data.getTime() / 1000 / 60;
		return minutos;
	}

	// private void selectAll() {
	// txtCodigoordem.selectAll();
	// txtNomecliente.selectAll();
	// txtEndereco.selectAll();
	// txtValorhora.selectAll();
	// txtTaxa.selectAll();
	// txtNomemotorista.selectAll();
	// txtTotalcliente.selectAll();
	// txtValorhora.selectAll();
	// txtComissao.selectAll();
	// txtTotalmotorista.selectAll();
	//
	// }

	public void limpaCampos() {
		txtCodigoordem.setText("");
		txtNomecliente.setText("");
		txtEndereco.setText("");
		txtValorhora.setText("");
		jtaObsTermino.setText("");
		txtTaxa.setText("");
		txtNomemotorista.setText("");
		JtxtDatainicio.setText("");
		JtxtHorainicio.setText("");
		JtxtDatatermino.setText("");
		JtxtHoratermino.setText("");
		JtxtTotalhoras.setText("");
		txtComissao.setText("");
		txtTotalcliente.setText("");
		txtTotalmotorista.setText("");
	}

}
