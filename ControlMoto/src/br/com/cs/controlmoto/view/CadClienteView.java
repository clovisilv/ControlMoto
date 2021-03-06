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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.MaskFormatter;
import javax.swing.text.PlainDocument;

import br.com.cs.controlmoto.domain.CadClienteDAO;
import br.com.cs.controlmoto.domain.CadEnderecoDAO;
import br.com.cs.controlmoto.domain.CadTelefoneDAO;
import br.com.cs.controlmoto.vo.CadClienteVO;
import br.com.cs.controlmoto.vo.CadEnderecoVO;
import br.com.cs.controlmoto.vo.CadTelefoneVO;

class CadClienteView extends JInternalFrame implements ActionListener, FocusListener, KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6964238294734865667L;

	JLabel	lblCodigo, lblNome, lblCnpj, lblInscricaoestadual, lblEndereco, lblNumero, lblBairro, lblCidade, lblEstado, lblCep, lblContato, lblTelefone, lblFax, lblCelular, lblSite, lblEmail, lblValorhora,
			lblTaxa, lblDatacadastro, lblInfoGerias, lblInfoEndereco, lblInfoContato;

	JTextField	txtCodigo, txtNome, txtEndereco, txtNumero, txtBairro, txtCidade, txtEstado, txtContato, txtSite, txtEmail, txtValorhora, txtTaxa, txtDataCadastro;

	JFormattedTextField	JtxtCnpj, JtxtInscricaoestadual, JtxtCep, JtxtTelefoneA, JtxtFaxA, JtxtCelularA, JtxtDatacadastro;
	
	JComboBox jcbEstado;
	
	JButton	btoIncluir, btoLocalizar, btoGravar, btoExcluir, btoSair, btoAtualizar, btoCancelar;
	
	JLayeredPane jlpInfoGerais, jlpInfoEndereco, jlpInfoContato;
	
	JSeparator jsInfoGerais, jsInfoEmdereco, jsInfoContato;
	
	GridBagConstraints cons;
	
	private ControlMotoView telaControlMoto;
	String [] estados ={"","AC","AL","AP","AM","BA","CE","DF","ES","GO","MA","MT","MS","MG","PA","PB","PR", "PE","PI","RJ","RN","RS","RO","RR","SC","SP","SE","TO"};
	String	val = "", tx = "", separadorPath = "";
	double	valor = 0, taxa = 0;
	Connection	minhaConexao;
	Statement	minhaSTM;
	ResultSet	rs;
	NumberFormat valorH, taxaC;
	int enderecoPk, telefonePk;
	
	Date data;
    SimpleDateFormat sdf,sdfTimestamp;
    Timestamp timestamp;
    java.sql.Date dt;
    //Serve para mover a tela interna
    static int openFrameCount = 1;
    static final int xOffset = 30, yOffset = 25;

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	//@SuppressWarnings("unchecked")
	public CadClienteView(String titulo, ControlMotoView controlMoto){
		super(titulo,false,true,false,true);
		setSize(700,540);
		setVisible(true);
    	setLocation(xOffset*openFrameCount, yOffset*openFrameCount);
		separadorPath = System.getProperty("file.separator");
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
	
		this.telaControlMoto = telaControlMoto;
		controlMoto.jDesktopPane.add(this);

		GridBagConstraints cons = new GridBagConstraints();
		Container CadSer = getContentPane();
		GridBagLayout layout = new GridBagLayout();
		CadSer.setLayout(layout);

		cons.insets = new Insets (3,5,3,5); 
		cons.weightx = 0.30;cons.weighty = 0.30;

		valorH = NumberFormat.getCurrencyInstance().getNumberInstance(new Locale("",""));
		valorH.setMaximumFractionDigits(2);valorH.setMinimumFractionDigits(2);
		taxaC = NumberFormat.getCurrencyInstance().getNumberInstance(new Locale("",""));
		taxaC.setMaximumFractionDigits(2);taxaC.setMinimumFractionDigits(2);

		lblCodigo = new JLabel("Código");lblNome = new JLabel("Nome");lblCnpj = new JLabel("C.N.P.J");
		lblInscricaoestadual = new JLabel("Ins. Estadual");lblEndereco = new JLabel("Endereço");
		lblNumero = new JLabel("Número");lblBairro = new JLabel("Bairro");lblCidade = new JLabel("Cidade");
		lblEstado = new JLabel("Estado");lblCep = new JLabel("CEP");lblContato = new JLabel("Contato");
		lblTelefone = new JLabel ("Telefone");lblFax = new JLabel("Fax");lblCelular = new JLabel("Celular");
		lblSite = new JLabel("Site");lblEmail = new JLabel("E-mail");lblValorhora = new JLabel("Valor por Horas");
		lblTaxa = new JLabel("Taxas");lblDatacadastro = new JLabel("Data do Cadastro");
		lblInfoGerias = new JLabel("Informações Gerais");lblInfoEndereco = new JLabel("Informações de Localização");
		lblInfoContato = new JLabel("Informações de Contato");

		txtCodigo = new JTextField();txtCodigo.requestFocus();txtCodigo.setDocument(new MeuDocument());
		txtNome = new JTextField();txtNome.setDocument(new MeuDocument());
		txtEndereco = new JTextField();txtEndereco.setDocument(new MeuDocument());
		txtNumero = new JTextField();txtNumero.setDocument(new MeuDocument());
		txtBairro = new JTextField();txtBairro.setDocument(new MeuDocument());
		txtCidade = new JTextField();txtCidade.setDocument(new MeuDocument());
		txtEstado = new JTextField();txtEstado.setDocument(new MeuDocument());
		txtContato = new JTextField();txtContato.setDocument(new MeuDocument());
		txtSite = new JTextField();txtEmail = new JTextField();
		txtValorhora = new JTextField();txtTaxa = new JTextField();
		txtDataCadastro = new JTextField();

		JtxtCnpj = new JFormattedTextField(setMascara("##.###.###/####-##"));
		JtxtInscricaoestadual = new JFormattedTextField(setMascara("###.###.###.###"));
		JtxtCep = new JFormattedTextField(setMascara("#####-###"));
		JtxtTelefoneA = new JFormattedTextField(setMascara("(##) ####-####"));
		JtxtFaxA = new JFormattedTextField(setMascara("(##) ####-####"));
		JtxtCelularA = new JFormattedTextField(setMascara("(##) ####-####"));
		JtxtDatacadastro = new JFormattedTextField(setMascara("##/##/####"));

		jcbEstado = new JComboBox(estados);

		btoIncluir = new JButton("Incluir");btoIncluir.setMnemonic(KeyEvent.VK_I);
		btoLocalizar = new JButton("Localizar");btoLocalizar.setMnemonic(KeyEvent.VK_L);
		btoGravar = new JButton("Gravar");btoGravar.setMnemonic(KeyEvent.VK_G);
		btoExcluir =  new JButton("Excluir");btoExcluir.setMnemonic(KeyEvent.VK_E);
		btoSair = new JButton("Sair");btoSair.setMnemonic(KeyEvent.VK_S);
		btoAtualizar = new JButton("Atualizar");btoAtualizar.setMnemonic(KeyEvent.VK_A);
		btoCancelar = new JButton("Cancelar");btoCancelar.setMnemonic(KeyEvent.VK_C);

		jsInfoGerais = new JSeparator();jsInfoGerais.setSize(550, 1);
		jsInfoEmdereco = new JSeparator();jsInfoEmdereco.setSize(550, 1);
		jsInfoContato = new JSeparator();jsInfoContato.setSize(550, 1);
		
		sdf = new SimpleDateFormat ("dd/MM/yyyy");
		sdfTimestamp = new SimpleDateFormat ("yyyy/MM/dd");
		sdf.setLenient (false);sdfTimestamp.setLenient (false);
		
		cons.anchor = GridBagConstraints.NORTHWEST;
		/*
		 * Insere JLabel de Informações gerais
		 * Insere JSeparador de Informações gerais
		 */
		cons.gridx = 1;cons.gridy = 1;cons.gridwidth = 3;cons.ipadx = 70;
		lblInfoGerias.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		lblInfoGerias.setForeground(Color.BLUE);
		CadSer.add(lblInfoGerias, cons);
		cons.gridx = 1;cons.gridy = 2;cons.gridwidth = 10;cons.ipadx = 550;
		CadSer.add(jsInfoGerais, cons);
		
		cons.gridx = 1;cons.gridy = 3;cons.gridwidth = 1;cons.ipadx = 50;
		CadSer.add(lblCodigo,cons);
		cons.gridx = 2;cons.gridy = 3;cons.gridwidth = 2;cons.ipadx = 80;cons.ipady = 1;
		CadSer.add(txtCodigo,cons);txtCodigo.setNextFocusableComponent(txtNome);
		txtCodigo.addActionListener(this);txtCodigo.addFocusListener(this);

		cons.gridx = 1;cons.gridy = 4;cons.gridwidth = 1;cons.ipadx = 50;
		CadSer.add(lblNome,cons);
		cons.gridx = 2;cons.gridy = 4;cons.gridwidth = 9;cons.ipadx = 450;
		CadSer.add(txtNome,cons);txtNome.setNextFocusableComponent(JtxtCnpj);
		txtNome.addActionListener(this);txtNome.addFocusListener(this);txtNome.addKeyListener(this);

		cons.gridx = 1;	cons.gridy = 5;cons.gridwidth = 1;cons.ipadx = 50;
		CadSer.add(lblCnpj,cons);
		cons.gridx = 2;cons.gridy = 5;cons.gridwidth = 2;cons.ipadx = 180;
		JtxtCnpj.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		CadSer.add(JtxtCnpj,cons);JtxtCnpj.setNextFocusableComponent(JtxtInscricaoestadual);
		JtxtCnpj.addActionListener(this);JtxtCnpj.addFocusListener(this);

		cons.gridx = 6;cons.gridy = 5;cons.gridwidth = 1;cons.ipadx = 50;
		lblInscricaoestadual.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		CadSer.add(lblInscricaoestadual,cons);
		cons.gridx = 7;cons.gridy = 5;cons.gridwidth = 3;cons.ipadx = 140;
		JtxtInscricaoestadual.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		CadSer.add(JtxtInscricaoestadual,cons);JtxtInscricaoestadual.setNextFocusableComponent(txtSite);
		JtxtInscricaoestadual.addActionListener(this);JtxtInscricaoestadual.addFocusListener(this);
		
		cons.gridx = 1;cons.gridy = 6;cons.gridwidth = 1;cons.ipadx = 50;
		CadSer.add(lblSite,cons);
		cons.gridx = 2;cons.gridy = 6;cons.gridwidth = 9;cons.ipadx = 450;
		CadSer.add(txtSite,cons);txtSite.setNextFocusableComponent(txtValorhora);
		txtSite.addActionListener(this);txtSite.addFocusListener(this);
		
		cons.gridx = 1;cons.gridy = 7;cons.gridwidth = 1;cons.ipadx = 50;
		CadSer.add(lblValorhora,cons);
		cons.gridx = 2;cons.gridy = 7;cons.gridwidth = 1;cons.ipadx = 110;
		txtValorhora.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		CadSer.add(txtValorhora,cons);txtValorhora.setNextFocusableComponent(txtTaxa);
		txtValorhora.addActionListener(this);txtValorhora.addFocusListener(this);
		txtValorhora.addKeyListener(this);txtValorhora.addFocusListener(this);

		cons.gridx = 6;cons.gridy = 7;cons.gridwidth = 1;cons.ipadx = 50;
		lblTaxa.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		CadSer.add(lblTaxa,cons);
		cons.gridx = 7;cons.gridy = 7;cons.gridwidth = 1;cons.ipadx = 130;
		txtTaxa.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
		CadSer.add(txtTaxa,cons);txtTaxa.setNextFocusableComponent(txtDataCadastro);
		txtTaxa.addActionListener(this);txtTaxa.addFocusListener(this);txtTaxa.addKeyListener(this);

		cons.gridx = 1;cons.gridy = 8;cons.gridwidth = 1;cons.ipadx = 50;
		CadSer.add(lblDatacadastro,cons);
		cons.gridx = 2;cons.gridy = 8;cons.gridwidth = 3;cons.ipadx = 110;
		txtDataCadastro.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		CadSer.add(txtDataCadastro,cons);txtDataCadastro.setNextFocusableComponent(JtxtCep);
		txtDataCadastro.addActionListener(this);txtDataCadastro.addFocusListener(this);
		/*
		 * Insere JLabel de Informações endereço
		 * Insere JSeparador de Informações endereço 
		 */
		cons.gridx = 1;cons.gridy = 9;cons.gridwidth = 3;cons.ipadx = 70;
		lblInfoEndereco.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		lblInfoEndereco.setForeground(Color.BLUE);
		CadSer.add(lblInfoEndereco, cons);
		cons.gridx = 1;cons.gridy = 10;cons.gridwidth = 30;cons.ipadx = 550;
		CadSer.add(jsInfoEmdereco, cons);
		
		cons.gridx = 1;cons.gridy = 11;cons.gridwidth = 1;cons.ipadx = 50;
		CadSer.add(lblCep,cons);
		cons.gridx = 2;cons.gridy = 11;cons.gridwidth = 3;cons.ipadx = 100;
		JtxtCep.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		CadSer.add(JtxtCep,cons);JtxtCep.setNextFocusableComponent(txtEndereco);
		JtxtCep.addActionListener(this);JtxtCep.addFocusListener(this);
		
		cons.gridx = 1;cons.gridy = 12;cons.gridwidth = 1;cons.ipadx = 50;
		CadSer.add(lblEndereco,cons);
		cons.gridx = 2;cons.gridy = 12;cons.gridwidth = 9;cons.ipadx = 450;
		CadSer.add(txtEndereco,cons);txtEndereco.setNextFocusableComponent(txtNumero);
		txtEndereco.addActionListener(this);txtEndereco.addFocusListener(this);txtEndereco.addKeyListener(this);

		cons.gridx = 1;cons.gridy = 13;cons.gridwidth = 1;cons.ipadx = 50;
		CadSer.add(lblNumero,cons);
		cons.gridx = 2;cons.gridy = 13;cons.gridwidth = 1;cons.ipadx = 40;
		CadSer.add(txtNumero,cons);txtNumero.setNextFocusableComponent(txtBairro);
		txtNumero.addActionListener(this);txtNumero.addFocusListener(this);txtNumero.addKeyListener(this);
		
		cons.gridx = 4;cons.gridy = 13;cons.gridwidth = 1;cons.ipadx = 50;
		CadSer.add(lblBairro,cons);
		cons.gridx = 5;cons.gridy = 13;cons.gridwidth = 3;cons.ipadx = 300;
		CadSer.add(txtBairro,cons);txtBairro.setNextFocusableComponent(txtCidade);
		txtBairro.addActionListener(this);txtBairro.addFocusListener(this);txtBairro.addKeyListener(this);

		cons.gridx = 1;cons.gridy = 14;cons.gridwidth = 1;cons.ipadx = 50;
		CadSer.add(lblCidade,cons);
		cons.gridx = 2;cons.gridy = 14;cons.gridwidth = 3;cons.ipadx = 150;
		CadSer.add(txtCidade,cons);txtCidade.setNextFocusableComponent(jcbEstado);
		txtCidade.addActionListener(this);txtCidade.addFocusListener(this);txtCidade.addKeyListener(this);

		cons.gridx = 6;cons.gridy = 14;cons.gridwidth = 1;cons.ipadx = 50;
		lblEstado.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		CadSer.add(lblEstado,cons);
		cons.gridx = 7;cons.gridy = 14;cons.gridwidth = 1;cons.ipadx = 110;
		CadSer.add(jcbEstado,cons);jcbEstado.setNextFocusableComponent(txtContato);
		jcbEstado.addActionListener(this);jcbEstado.addKeyListener(this);
		/*
		 * Insere JLabel de Informações contato
		 * Insere JSeparador de Informações contato
		 */
		cons.gridx = 1;cons.gridy = 15;cons.gridwidth = 3;cons.ipadx = 70;
		lblInfoContato.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		lblInfoContato.setForeground(Color.BLUE);
		CadSer.add(lblInfoContato, cons);
		cons.gridx = 1;cons.gridy = 16;cons.gridwidth = 30;cons.ipadx = 550;
		CadSer.add(jsInfoContato, cons);
		
		cons.gridx = 1;cons.gridy = 17;cons.gridwidth = 1;cons.ipadx = 50;
		CadSer.add(lblContato,cons);
		cons.gridx = 2;cons.gridy = 17;cons.gridwidth = 3;cons.ipadx = 150;
		CadSer.add(txtContato,cons);txtContato.setNextFocusableComponent(JtxtTelefoneA);
		txtContato.addActionListener(this);txtContato.addFocusListener(this);

		cons.gridx = 6;cons.gridy = 17;cons.gridwidth = 1;cons.ipadx = 50;
		lblTelefone.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		CadSer.add(lblTelefone,cons);
		cons.gridx = 7;cons.gridy = 17;cons.gridwidth = 3;cons.ipadx = 130;
		JtxtTelefoneA.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		CadSer.add(JtxtTelefoneA,cons);JtxtTelefoneA.setNextFocusableComponent(JtxtFaxA);
		JtxtTelefoneA.addActionListener(this);JtxtTelefoneA.addFocusListener(this);

		cons.gridx = 1;cons.gridy = 18;cons.gridwidth = 1;cons.ipadx = 50;
		CadSer.add(lblFax,cons);
		cons.gridx = 2;cons.gridy = 18;cons.gridwidth = 3;cons.ipadx = 130;
		JtxtFaxA.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		CadSer.add(JtxtFaxA,cons);JtxtFaxA.setNextFocusableComponent(JtxtCelularA);
		JtxtFaxA.addActionListener(this);JtxtFaxA.addFocusListener(this);

		cons.gridx = 6;cons.gridy = 18;cons.gridwidth = 1;cons.ipadx = 50;
		lblCelular.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		CadSer.add(lblCelular,cons);
		cons.gridx = 7;cons.gridy = 18;cons.gridwidth = 3;cons.ipadx = 130;
		JtxtCelularA.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		CadSer.add(JtxtCelularA,cons);JtxtCelularA.setNextFocusableComponent(txtEmail);
		JtxtCelularA.addActionListener(this);JtxtCelularA.addFocusListener(this);

		cons.gridx = 1;cons.gridy = 19;	cons.gridwidth = 1;cons.ipadx = 50;
		CadSer.add(lblEmail,cons);
		cons.gridx = 2;cons.gridy = 19;cons.gridwidth = 9;cons.ipadx = 450;
		CadSer.add(txtEmail,cons);txtEmail.setNextFocusableComponent(btoGravar);
		txtEmail.addActionListener(this);txtEmail.addFocusListener(this);

		cons.anchor = GridBagConstraints.EAST; cons.fill = GridBagConstraints.BOTH;
		cons.gridx = 20;cons.gridy = 3;cons.gridwidth = 1;cons.gridheight = 1;cons.ipadx = 60;
		CadSer.add(btoIncluir,cons);btoIncluir.setNextFocusableComponent(btoLocalizar);
		btoIncluir.addActionListener(this);

		cons.gridx = 20;cons.gridy = 4;cons.gridwidth = 1;cons.gridheight = 1;cons.ipadx = 60;
		CadSer.add(btoLocalizar,cons);btoLocalizar.setNextFocusableComponent(btoSair);
		btoLocalizar.addActionListener(this);

		cons.gridx = 20;cons.gridy = 4;cons.gridwidth = 1;cons.gridheight = 1;cons.ipadx = 60;
		CadSer.add(btoAtualizar,cons);btoAtualizar.setNextFocusableComponent(btoExcluir);
		btoAtualizar.addActionListener(this);

		cons.gridx = 20;cons.gridy = 5;cons.gridwidth = 1;cons.gridheight = 1;cons.ipadx = 60;
		CadSer.add(btoGravar,cons);btoGravar.setNextFocusableComponent(btoCancelar);
		btoGravar.addActionListener(this);

		cons.gridx = 20;cons.gridy = 6;cons.gridwidth = 1;cons.gridheight = 1;cons.ipadx = 60;
		CadSer.add(btoExcluir,cons);btoExcluir.setNextFocusableComponent(btoCancelar);
		btoExcluir.addActionListener(this);

		cons.gridx = 20;cons.gridy = 19;cons.gridwidth = 1;cons.gridheight = 1;cons.ipadx = 60;
		CadSer.add(btoSair,cons);btoSair.setNextFocusableComponent(txtCodigo);
		btoSair.addActionListener(this);

		cons.gridx = 20;cons.gridy = 19;cons.gridwidth = 1;cons.gridheight = 1;cons.ipadx = 60;
		CadSer.add(btoCancelar,cons);
		btoCancelar.addActionListener(this);

		btoIncluir.setEnabled(true);btoLocalizar.setEnabled(true);btoGravar.setEnabled(false);
		btoExcluir.setEnabled(false);btoSair.setEnabled(true);btoCancelar.setVisible(false);
		
	}


	private MaskFormatter setMascara(String mascara) {
		MaskFormatter mask = null;
		try {
			mask = new MaskFormatter(mascara);
		} catch (java.text.ParseException e) {
		}
		return mask;
	}

	class MeuDocument extends PlainDocument {
		public void insertString(int offs, String str, AttributeSet a)
		throws BadLocationException {
			super.insertString(offs, str.toUpperCase(), a);
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