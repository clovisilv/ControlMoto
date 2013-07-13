package br.com.cs.controlmoto.view;

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
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.MaskFormatter;
import javax.swing.text.PlainDocument;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import br.com.cs.controlmoto.domain.AbrirOrdensDAO;
import br.com.cs.controlmoto.domain.CadClienteDAO;
import br.com.cs.controlmoto.domain.CadEnderecoDAO;
import br.com.cs.controlmoto.domain.CadMotoristaDAO;
import br.com.cs.controlmoto.domain.CadTelefoneDAO;
import br.com.cs.controlmoto.vo.AbrirOrdensVO;
import br.com.cs.controlmoto.vo.CadClienteVO;
import br.com.cs.controlmoto.vo.CadEnderecoVO;
import br.com.cs.controlmoto.vo.CadMotoristaVO;
import br.com.cs.controlmoto.vo.CadTelefoneVO;

class AbrirOrdensView extends JInternalFrame implements ActionListener, FocusListener, KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -477731764090304783L;

	JLabel	lblCodigo,lblCodigocliente,lblNomecliente,lblEndereco,lblBairro,lblCidade,lblTelefone,lblContato,
	lblValorhora,lblTaxa,lblCodigomotorista,lblNomemotorista,lblComissao,lblHorainicio,lblDatainicio,lblTelefoneMot,
	lblObsInicio,lblInfoOrdem,lblInfoCliente,lblInfoMotorista;

	JTextField	txtCodigo,txtCodigocliente,txtNomecliente,txtEndereco,txtBairro,txtCidade,txtTelefone,txtContato,
	txtValorhora,txtTaxa,txtCodigomotorista,txtNomemotorista,txtComissao,txtCelular;
	
	JTextArea jtaObsInicio;

	JFormattedTextField jtxtDatainicio,jtxtHorainicio;

	JButton btoIncluir,btoLocalizar,btoGravar,btoExcluir,btoSair,btoAtualizar,btoCancelar,btoImprimir;
	
	JSeparator jsInfoOrdem, jsInfoCliente, jsInfoMotorista;
	
	JScrollPane jspObsInicio;
	
	ImageIcon icone;
	
	private ControlMotoView telaControlMoto;
	
	java.util.Date data;
    SimpleDateFormat sdf,sdfTimestamp,sdfHora;
    Timestamp timestamp;
    java.sql.Date dts;
	DateFormat formatarData;
	NumberFormat vr;
	Double valor;
	String caminho, status, separadorPath = "", aberta, fechada, impressa, reimperssa;

	Statement minhaSTM;
	ResultSet rs;
	
	//Serve para mover a tela
    static int openFrameCount = 5;
    static final int xOffset = 50, yOffset = 45;
    
	public AbrirOrdensView(String titulo, ControlMotoView controlMoto){

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
//		caminho = new File("").getAbsolutePath();
		separadorPath = System.getProperty("file.separator");
		//icone = new ImageIcon(caminho+separadorPath+"imagens"+separadorPath+"logo_cs_b.gif");
		
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		
		this.telaControlMoto = telaControlMoto;
		controlMoto.jDesktopPane.add(this);

		GridBagConstraints cons = new GridBagConstraints();
		Container CadSer = getContentPane();
		GridBagLayout layout = new GridBagLayout();
		CadSer.setLayout(layout);
		//Determina como sera a expnsao dos componentes
		//cons.fill = GridBagConstraints.HORIZONTAL;
		//Determina o espacamento entre os compnentes
		cons.insets = new Insets (2,3,2,3); 
		//Determina como os componentes crescerao quando a tela aumentar de tamalho
		cons.weightx = 0.30;cons.weighty = 0.30;
		
		formatarData = new SimpleDateFormat().getDateInstance();
		
		vr = NumberFormat.getCurrencyInstance().getNumberInstance(new Locale("",""));
		vr.setMaximumFractionDigits(2);vr.setMinimumFractionDigits(2);

		lblCodigo = new JLabel("Código O.S.");lblCodigocliente = new JLabel("Código Cliente");lblNomecliente = new JLabel("Nome Cliente");
		lblEndereco = new JLabel("Endereço");lblBairro = new JLabel("Bairro");lblCidade = new JLabel("Cidade");lblTelefone = new JLabel("Telefone");
		lblContato = new JLabel("Contato");lblCodigomotorista = new JLabel("Código Motorista");lblComissao = new JLabel("Comissão");
		lblNomemotorista = new JLabel("Nome Motorista");lblDatainicio = new JLabel("Data Início");lblHorainicio = new JLabel("Hora Início");
		lblValorhora = new JLabel("Valor Hora");lblTaxa = new JLabel("Taxa");lblTelefoneMot = new JLabel("Telefone");
		lblObsInicio = new JLabel("Observação");lblInfoOrdem = new JLabel("Informações Ordem de Serviço");
		lblInfoCliente = new JLabel("Informações Cliente");lblInfoMotorista = new JLabel("Informações Motorista");

		txtCodigo = new JTextField(50);txtCodigocliente = new JTextField(10);txtNomecliente = new JTextField(60);
		txtEndereco = new JTextField(60);txtBairro = new JTextField("");txtCidade = new JTextField("");
		txtTelefone = new JTextField(20);txtContato = new JTextField(30);txtValorhora = new JTextField(20);
		txtTaxa = new JTextField(20);txtCodigomotorista = new JTextField(10);txtCodigomotorista.setDocument(new MeuDocument());
		txtNomemotorista = new JTextField(150);txtComissao = new JTextField(20);txtCelular = new JTextField();
		
		jtaObsInicio = new JTextArea(3,30);
		
		jtxtDatainicio = new JFormattedTextField(setMascara("##/##/####"));
		jtxtHorainicio = new JFormattedTextField(setMascara("##:##"));

		btoIncluir = new JButton("Incluir");btoIncluir.setMnemonic(KeyEvent.VK_I);
		btoLocalizar = new JButton("Localizar");btoLocalizar.setMnemonic(KeyEvent.VK_L);
		btoGravar = new JButton("Gravar");btoGravar.setMnemonic(KeyEvent.VK_G);
		btoExcluir =  new JButton("Excluir");btoExcluir.setMnemonic(KeyEvent.VK_E);
		btoSair = new JButton("Sair");btoSair.setMnemonic(KeyEvent.VK_S);
		btoAtualizar = new JButton("Atualizar");btoAtualizar.setMnemonic(KeyEvent.VK_A);
		btoCancelar = new JButton("Cancelar");btoCancelar.setMnemonic(KeyEvent.VK_C);
		btoImprimir = new JButton("Imprimir");btoImprimir.setMnemonic(KeyEvent.VK_P);
		
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
		CadSer.add(lblInfoOrdem,cons);
		cons.gridx = 1;cons.gridy = 2;cons.gridwidth = 15;cons.ipadx = 750;cons.ipady = 1;
		CadSer.add(jsInfoOrdem,cons);
		
		cons.gridx = 1;cons.gridy = 3;cons.gridwidth = 1;cons.ipadx = 110;
		CadSer.add(lblCodigo,cons);
		cons.gridx = 2;cons.gridy = 3;cons.gridwidth = 2;cons.ipadx = 80;//cons.ipady = 1;
		txtCodigo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		CadSer.add(txtCodigo,cons);
		
		cons.gridx = 1;cons.gridy = 4;cons.gridwidth = 1;cons.ipadx = 110;
		CadSer.add(lblDatainicio,cons);
		cons.gridx = 2;cons.gridy = 4;cons.gridwidth = 3;cons.ipadx = 100;
		jtxtDatainicio.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jtxtDatainicio.setNextFocusableComponent(jtxtHorainicio);
		CadSer.add(jtxtDatainicio,cons);jtxtDatainicio.addFocusListener(this);

		cons.gridx = 12;cons.gridy = 4;cons.gridwidth = 1;cons.ipadx = 120;
		lblHorainicio.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		CadSer.add(lblHorainicio,cons);
		cons.gridx = 13;cons.gridy = 4;cons.gridwidth = 3;cons.ipadx = 150;
		jtxtHorainicio.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jtxtHorainicio.setNextFocusableComponent(jtaObsInicio);
		CadSer.add(jtxtHorainicio,cons);jtxtHorainicio.addFocusListener(this);
		
		cons.gridx = 1;	cons.gridy = 5;cons.gridwidth = 1;cons.ipadx = 110;
		CadSer.add(lblObsInicio,cons);
		cons.gridx = 2;cons.gridy = 5;cons.gridwidth = 16;cons.ipadx = 400;cons.ipady = 30;
		jspObsInicio.setNextFocusableComponent(txtCodigocliente);
		CadSer.add(jspObsInicio,cons);txtNomecliente.setEnabled(false);
		jtaObsInicio.addKeyListener(this);
		/*
		 * Insere JLabel de Informações do cliente
		 * Insere JSeparador de Informações do cliente
		 */
		cons.gridx = 1;cons.gridy = 6;cons.gridwidth = 3;cons.ipadx = 100;cons.ipady = 1;
		lblInfoCliente.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		lblInfoCliente.setForeground(Color.BLUE);
		CadSer.add(lblInfoCliente,cons);
		cons.gridx = 1;cons.gridy = 7;cons.gridwidth = 15;cons.ipadx = 750;cons.ipady = 1;
		CadSer.add(jsInfoCliente,cons);
		
		cons.gridx = 1;cons.gridy = 8;cons.gridwidth = 1;cons.ipadx = 110;
		CadSer.add(lblCodigocliente,cons);
		cons.gridx = 2;cons.gridy = 8;cons.gridwidth = 2;cons.ipadx = 80;cons.ipady = 1;
		txtCodigocliente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		txtCodigocliente.setNextFocusableComponent(txtCodigomotorista);
		CadSer.add(txtCodigocliente,cons);
		txtCodigocliente.addActionListener(this);

		cons.gridx = 1;	cons.gridy = 9;cons.gridwidth = 1;cons.ipadx = 110;
		CadSer.add(lblNomecliente,cons);
		cons.gridx = 2;cons.gridy = 9;cons.gridwidth = 16;cons.ipadx = 450;
		CadSer.add(txtNomecliente,cons);txtNomecliente.setEnabled(false);

		cons.gridx = 1;cons.gridy = 10;cons.gridwidth = 1;cons.ipadx = 110;
		CadSer.add(lblEndereco,cons);
		cons.gridx = 2;cons.gridy = 10;cons.gridwidth = 16;cons.ipadx = 450;
		CadSer.add(txtEndereco,cons);txtEndereco.setEnabled(false);

		cons.gridx = 1;cons.gridy = 11;cons.gridwidth = 1;cons.ipadx = 110;
		CadSer.add(lblBairro,cons);
		cons.gridx = 2;cons.gridy = 11;cons.gridwidth = 9;cons.ipadx = 310;
		CadSer.add(txtBairro,cons);txtBairro.setEnabled(false);

		cons.gridx = 12;cons.gridy = 11;cons.gridwidth = 1;cons.ipadx = 110;
		lblCidade.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		CadSer.add(lblCidade,cons);
		cons.gridx = 13;cons.gridy = 11;cons.gridwidth = 9;cons.ipadx = 210;
		CadSer.add(txtCidade,cons);txtCidade.setEnabled(false);

		cons.gridx = 1;cons.gridy = 12;cons.gridwidth = 1;cons.ipadx = 110;
		CadSer.add(lblContato,cons);
		cons.gridx = 2;cons.gridy = 12;cons.gridwidth = 4;cons.ipadx = 190;
		CadSer.add(txtContato,cons);txtContato.setEnabled(false);

		cons.gridx = 12;cons.gridy = 12;cons.gridwidth = 1;cons.ipadx = 110;
		lblTelefone.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		CadSer.add(lblTelefone,cons);
		cons.gridx = 13;cons.gridy = 12;cons.gridwidth = 6;cons.ipadx = 210;
		CadSer.add(txtTelefone,cons);txtTelefone.setEnabled(false);

		cons.gridx = 1;cons.gridy = 13;cons.gridwidth = 1;cons.ipadx = 110;
		CadSer.add(lblValorhora,cons);
		cons.gridx = 2;cons.gridy = 13;cons.gridwidth = 3;cons.ipadx = 90;
		txtValorhora.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		CadSer.add(txtValorhora,cons);txtValorhora.setEnabled(false);
		
		cons.gridx = 12;cons.gridy = 13;cons.gridwidth = 1;cons.ipadx = 110;
		lblTaxa.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		CadSer.add(lblTaxa,cons);
		cons.gridx = 13;cons.gridy = 13;cons.gridwidth = 3;cons.ipadx = 210;
		txtTaxa.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		CadSer.add(txtTaxa,cons);txtTaxa.setEnabled(false);
		/*
		 * Insere JLabel de Informações do motorista
		 * Insere JSeparador de Informações do motorista
		 */
		cons.gridx = 1;cons.gridy = 14;	cons.gridwidth = 3;	cons.ipadx = 100;
		lblInfoMotorista.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		lblInfoMotorista.setForeground(Color.BLUE);
		CadSer.add(lblInfoMotorista,cons);
		cons.gridx = 1;cons.gridy = 15;cons.gridwidth = 15;cons.ipadx = 750;
		CadSer.add(jsInfoMotorista,cons);
		
		cons.gridx = 1;cons.gridy = 16;	cons.gridwidth = 1;	cons.ipadx = 110;
		CadSer.add(lblCodigomotorista,cons);
		cons.gridx = 2;cons.gridy = 16;cons.gridwidth = 2;cons.ipadx = 80;
		txtCodigomotorista.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		CadSer.add(txtCodigomotorista,cons);
		txtCodigomotorista.addActionListener(this);

		cons.gridx = 1;cons.gridy = 17;	cons.gridwidth = 1;cons.ipadx = 110;
		CadSer.add(lblNomemotorista,cons);
		cons.gridx = 2;cons.gridy = 17;cons.gridwidth = 16;cons.ipadx = 450;
		CadSer.add(txtNomemotorista,cons);txtNomemotorista.setEnabled(false);

		cons.gridx = 1;cons.gridy = 18;	cons.gridwidth = 1;cons.ipadx = 110;
		CadSer.add(lblComissao,cons);
		cons.gridx = 2;cons.gridy = 18;cons.gridwidth = 3;cons.ipadx = 90;
		txtComissao.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		CadSer.add(txtComissao,cons);txtComissao.setEnabled(false);
		
		cons.gridx = 12;cons.gridy = 18;cons.gridwidth = 1;cons.ipadx = 110;
		lblTelefoneMot.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		CadSer.add(lblTelefoneMot,cons);
		cons.gridx = 13;cons.gridy = 18;cons.gridwidth = 3;cons.ipadx = 210;
		txtCelular.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		CadSer.add(txtCelular,cons);txtCelular.setEnabled(false);

		cons.gridx = 26;cons.gridy = 3;cons.gridwidth = 1;cons.ipadx = 120;//cons.gridheight = 2;
		CadSer.add(btoIncluir,cons);
		btoIncluir.addActionListener(this);

		cons.gridx = 26;cons.gridy = 4;cons.gridwidth = 1;cons.ipadx = 120;//cons.gridheight = 2;
		CadSer.add(btoLocalizar,cons);
		btoLocalizar.addActionListener(this);

		cons.gridx = 26;cons.gridy = 4;cons.gridwidth = 1;cons.ipadx = 120;//cons.gridheight = 2;
		CadSer.add(btoAtualizar,cons);
		btoAtualizar.addActionListener(this);

		cons.gridx = 26;cons.gridy = 5;cons.gridwidth = 1;cons.ipadx = 120;//cons.gridheight = 2;
		CadSer.add(btoGravar,cons);
		btoGravar.addActionListener(this);

		cons.gridx = 26;cons.gridy = 5;cons.gridwidth = 1;cons.ipadx = 120;//cons.gridheight = 2;
		CadSer.add(btoImprimir, cons);
		btoImprimir.addActionListener(this);

		cons.gridx = 26;cons.gridy = 6  ;cons.gridwidth = 1;cons.ipadx = 120;//cons.gridheight = 2;
		CadSer.add(btoExcluir,cons);
		btoExcluir.addActionListener(this);

		cons.gridx = 26;cons.gridy = 18;cons.gridwidth = 1;cons.ipadx = 120;//cons.gridheight = 2;
		CadSer.add(btoSair,cons);
		btoSair.addActionListener(this);

		cons.gridx = 26;cons.gridy = 18;cons.gridwidth = 1;cons.ipadx = 120;//cons.gridheight = 2;
		CadSer.add(btoCancelar,cons);
		btoCancelar.addActionListener(this);
		//Atualizar.addActionListener(this);
		btoIncluir.setEnabled(true);
		btoLocalizar.setEnabled(true);
		btoGravar.setEnabled(false);
		btoImprimir.setEnabled(false);
		btoExcluir.setEnabled(false);
		btoSair.setEnabled(true);

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