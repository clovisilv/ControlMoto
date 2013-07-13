package br.com.cs.controlmoto.view;

import java.awt.Color;
import java.awt.Container;
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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.MaskFormatter;
import javax.swing.text.PlainDocument;

class CadEmpresaView extends JInternalFrame implements ActionListener, FocusListener, KeyListener {

    /**
	 * 
	 */
	private static final long serialVersionUID = -6249497882087014735L;

	JLabel  lblCodigo, lblNome, lblCnpj, lblInscricaoestadual, lblEndereco, lblBairro, lblCidade, lblEstado,
    		lblCep, lblTelefoneA, lblTelefoneB, lblFaxA, lblFaxB, lblSite, lblEmail, lblDatacadastro, 
    		lblInfoGerais, lblInfoLocalizacao, lblInfoContato;
    
    JTextField	txtCodigo, txtNome, txtEndereco, txtBairro, txtCidade, txtSite, txtEmail, txtDataCadastro;//txtEstado,
    
    JFormattedTextField JtxtCnpj, JtxtInscricaoestadual, JtxtCep, JtxtTelefoneA, JtxtTelefoneB, JtxtFaxA,
    					JtxtFaxB,JtxtDatacadastro;
    
    JButton btoIncluir, btoLocalizar, btoGravar, btoExcluir, btoSair, btoAtualizar, btoCancelar;
    
    JComboBox jcbEstado;
    
    JSeparator jsInfoGerais, jsInfoLocalizacao, jsInfoContato;
    
    private ControlMotoView telaControlMoto;
    
    Dimension centraliza = Toolkit.getDefaultToolkit().getScreenSize();
    String val = "", tx = "", texto = "", separadorPath = "";
    String [] estados ={"","AC","AL","AP","AM","BA","CE","DF","ES","GO","MA","MT","MS","MG","PA","PB","PR",
			"PE","PI","RJ","RN","RS","RO","RR","SC","SP","SE","TO"};
    
    Connection minhaConexao;
    Statement minhaSTM;
    ResultSet rs;
    
    Date data;
    SimpleDateFormat sdf,sdfTimestamp;
    Timestamp timestamp;
    java.sql.Date dt;
    
    private int enderecoPk,telefonePk;
    //Serve para mover a tela interna
    static int openFrameCount = 0;
    static final int xOffset = 20, yOffset = 20;

    
    public CadEmpresaView(String titulo, ControlMotoView controlMoto){
    	super(titulo,false,true,false,true);
    	setSize(660,500);//650,400
    	setVisible(true);
    	setLocation(xOffset*openFrameCount, yOffset*openFrameCount);
//    	separadorPath = System.getProperty("file.separator");
//    	setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
//    	setTitle("ControlMoto - Cadastro de Empresa");
    	
    	this.telaControlMoto = telaControlMoto;
    	controlMoto.jDesktopPane.add(this);
    	
//    	setResizable(false);
//    	ImageIcon icone = new ImageIcon("..//Controlmoto/imagens/logo_cs_b.gif");
//    	ImageIcon icone = new ImageIcon(".."+separadorPath+separadorPath+"ControlMoto"+separadorPath+"imagens"+separadorPath+"logo_cs_b.gif");
//    	setIconImage(icone.getImage());
    	setLocation((centraliza.width-this.getSize().width)/2,(centraliza.height-this.getSize().height)/3);   
				
	GridBagConstraints cons = new GridBagConstraints();
	Container CadSer = getContentPane();
	GridBagLayout layout = new GridBagLayout();
	CadSer.setLayout(layout);
			
	cons.insets = new Insets (3,5,3,5); 
	cons.weightx = 0.30;cons.weighty = 0.30;
			
	lblCodigo = new JLabel("Código");lblNome = new JLabel("Nome");lblCnpj = new JLabel("C.N.P.J");
	lblInscricaoestadual = new JLabel("Ins. Estadual");lblEndereco = new JLabel("Endereço");
	lblBairro = new JLabel("Bairro");lblCidade = new JLabel("Cidade");lblEstado = new JLabel("Estado");
	lblCep = new JLabel("CEP");lblTelefoneA = new JLabel("Telefone");lblTelefoneB = new JLabel ("Telefone");
	lblFaxA = new JLabel("Fax");lblFaxB = new JLabel("Fax");lblSite = new JLabel("Site");lblEmail = new JLabel("E-mail");
	lblDatacadastro = new JLabel("Data Cadastro");lblInfoGerais = new JLabel("Informações Gerais");
	lblInfoLocalizacao = new JLabel("Informações Localização");lblInfoContato = new JLabel("Informações Contato");
			
	txtCodigo = new JTextField();txtNome = new JTextField();txtNome.setDocument(new MeuDocument());
	txtEndereco = new JTextField();txtEndereco.setDocument(new MeuDocument());
	txtBairro = new JTextField();txtBairro.setDocument(new MeuDocument());
	txtCidade = new JTextField();txtCidade.setDocument(new MeuDocument());
//	txtEstado = new JTextField();txtEstado.setDocument(new MeuDocument());
	txtSite = new JTextField();//txtSite.setDocument(new MeuDocument());
	txtEmail = new JTextField();//txtEmail.setDocument(new MeuDocument());
	txtDataCadastro = new JTextField();txtDataCadastro.setDocument(new MeuDocument());
							
	JtxtCnpj = new JFormattedTextField(setMascara("##.###.###/####-##"));
	JtxtInscricaoestadual = new JFormattedTextField(setMascara("###.###.###.###"));
	JtxtCep = new JFormattedTextField(setMascara("#####-###"));
	JtxtTelefoneA = new JFormattedTextField(setMascara("(##) ####-####"));
	JtxtTelefoneB = new JFormattedTextField(setMascara("(##) ####-####"));
	JtxtFaxA = new JFormattedTextField(setMascara("(##) ####-####"));
	JtxtFaxB = new JFormattedTextField(setMascara("(##) ####-####"));
	JtxtDatacadastro = new JFormattedTextField(setMascara("##/##/####"));
			
	btoIncluir = new JButton("Incluir");btoIncluir.setMnemonic(KeyEvent.VK_I);
	btoLocalizar = new JButton("Localizar");btoLocalizar.setMnemonic(KeyEvent.VK_L);
	btoGravar = new JButton("Gravar");btoGravar.setMnemonic(KeyEvent.VK_G);
	btoExcluir =  new JButton("Excluir");btoExcluir.setMnemonic(KeyEvent.VK_E);
	btoSair = new JButton("Sair");btoSair.setMnemonic(KeyEvent.VK_S);
	btoAtualizar = new JButton("Atualizar");btoAtualizar.setMnemonic(KeyEvent.VK_A);
	btoCancelar = new JButton("Cancelar");btoCancelar.setMnemonic(KeyEvent.VK_C);
	
	jcbEstado = new JComboBox(estados);
	
	jsInfoGerais = new JSeparator(); jsInfoLocalizacao = new JSeparator(); jsInfoContato = new JSeparator();

	sdf = new SimpleDateFormat ("dd/MM/yyyy");
	sdfTimestamp = new SimpleDateFormat ("yyyy/MM/dd");
//	dt = new java.sql.Date();
	sdf.setLenient (false);sdfTimestamp.setLenient (false);
		
//	timestamp = new Timestamp (timeInMillis);
	
	cons.anchor = GridBagConstraints.NORTHWEST;
	
	/*
	 * Insere JLabel de Informações gerais
	 * Insere JSeparador de Informações gerais
	 */
	cons.gridx = 2;cons.gridy = 1;cons.gridwidth = 1;cons.ipadx = 70;cons.ipady = 1;
	lblInfoGerais.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
	lblInfoGerais.setForeground(Color.BLUE);
	CadSer.add(lblInfoGerais,cons);
	cons.gridx = 1;cons.gridy = 2;cons.gridwidth = 10;cons.ipadx = 570;//cons.ipady = 1;
	CadSer.add(jsInfoGerais,cons);
	
	cons.gridx = 1;cons.gridy = 3;cons.gridwidth = 1;cons.ipadx = 50;
	CadSer.add(lblCodigo,cons);
	cons.gridx = 2;cons.gridy = 3;cons.gridwidth = 2;cons.ipadx = 80;//cons.ipady = 1;
	CadSer.add(txtCodigo,cons);txtCodigo.setNextFocusableComponent(txtNome);
	txtCodigo.addActionListener(this);txtCodigo.addFocusListener(this);
		
	cons.gridx = 1;cons.gridy = 4;cons.gridwidth = 1;cons.ipadx = 50;
	CadSer.add(lblNome,cons);  
	cons.gridx = 2;cons.gridy = 4;cons.gridwidth = 9;cons.ipadx = 470;
	CadSer.add(txtNome,cons);txtNome.setNextFocusableComponent(JtxtCnpj);
	txtNome.addActionListener(this);txtNome.addFocusListener(this);txtNome.addKeyListener(this);
			
	cons.gridx = 1;	cons.gridy = 5;cons.gridwidth = 1;cons.ipadx = 50;
	CadSer.add(lblCnpj,cons);
	cons.gridx = 2;cons.gridy = 5;cons.gridwidth = 3;cons.ipadx = 150;
	JtxtCnpj.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
	CadSer.add(JtxtCnpj,cons);JtxtCnpj.setNextFocusableComponent(JtxtInscricaoestadual);
	JtxtCnpj.addActionListener(this);JtxtCnpj.addFocusListener(this);
			
	cons.gridx = 6;cons.gridy = 5;cons.gridwidth = 1;cons.ipadx = 50;
//	lblInscricaoestadual.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
	CadSer.add(lblInscricaoestadual,cons);
	cons.gridx = 7;cons.gridy = 5;cons.gridwidth = 3;cons.ipadx = 150;
	JtxtInscricaoestadual.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
	CadSer.add(JtxtInscricaoestadual,cons);JtxtInscricaoestadual.setNextFocusableComponent(JtxtCep);
	JtxtInscricaoestadual.addActionListener(this);JtxtInscricaoestadual.addFocusListener(this);
	
	/*
	 * Insere JLabel de Informações para localização
	 * Insere JSeparador de Informações para localização
	 */
	cons.gridx = 2;cons.gridy = 6;cons.gridwidth = 1;cons.ipadx = 70;cons.ipady = 1;
	lblInfoLocalizacao.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
	lblInfoLocalizacao.setForeground(Color.BLUE);
	CadSer.add(lblInfoLocalizacao,cons);
	cons.gridx = 1;cons.gridy = 7;cons.gridwidth = 10;cons.ipadx = 570;cons.ipady = 1;
	CadSer.add(jsInfoLocalizacao,cons);
	
	cons.gridx = 1;cons.gridy = 8;cons.gridwidth = 1;cons.ipadx = 50;
	CadSer.add(lblCep,cons);
	cons.gridx = 2;cons.gridy = 8;cons.gridwidth = 3;cons.ipadx = 80;
	JtxtCep.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
	CadSer.add(JtxtCep,cons);JtxtCep.setNextFocusableComponent(txtEndereco);
	JtxtCep.addActionListener(this);JtxtCep.addFocusListener(this);
	
	cons.gridx = 1;cons.gridy = 9;cons.gridwidth = 1;cons.ipadx = 50;
	CadSer.add(lblEndereco,cons);
	cons.gridx = 2;cons.gridy = 9;cons.gridwidth = 9;cons.ipadx = 470;
	CadSer.add(txtEndereco,cons);txtEndereco.setNextFocusableComponent(txtBairro);
	txtEndereco.addActionListener(this);txtEndereco.addFocusListener(this);txtEndereco.addKeyListener(this);
						
	cons.gridx = 1;cons.gridy = 10;cons.gridwidth = 1;cons.ipadx = 50;
	CadSer.add(lblBairro,cons);
	cons.gridx = 2;cons.gridy = 10;cons.gridwidth = 3;cons.ipadx = 250;
	CadSer.add(txtBairro,cons);txtBairro.setNextFocusableComponent(txtCidade);
	txtBairro.addActionListener(this);txtBairro.addFocusListener(this);txtBairro.addKeyListener(this);
						
	cons.gridx = 6;cons.gridy = 10;cons.gridwidth = 1;cons.ipadx = 50;
	CadSer.add(lblCidade,cons);
	cons.gridx = 7;cons.gridy = 10;cons.gridwidth = 3;cons.ipadx = 150;
	CadSer.add(txtCidade,cons);txtCidade.setNextFocusableComponent(jcbEstado);
	txtCidade.addActionListener(this);txtCidade.addFocusListener(this);txtCidade.addKeyListener(this);
			
	cons.gridx = 1;cons.gridy = 11;cons.gridwidth = 1;cons.ipadx = 50;
	CadSer.add(lblEstado,cons);
	cons.gridx = 2;cons.gridy = 11;cons.gridwidth = 1;cons.ipadx = 50;
	CadSer.add(jcbEstado,cons);jcbEstado.setNextFocusableComponent(txtDataCadastro);
//	txtEstado.addActionListener(this);txtEstado.addFocusListener(this);txtEstado.addKeyListener(this);
	
	cons.gridx = 6;cons.gridy = 11;cons.gridwidth = 1;cons.ipadx = 50;
	CadSer.add(lblDatacadastro,cons);
	cons.gridx = 7;cons.gridy = 11;cons.gridwidth = 3;cons.ipadx = 110;
	txtDataCadastro.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
	CadSer.add(txtDataCadastro,cons);txtDataCadastro.setNextFocusableComponent(JtxtTelefoneA);
	txtDataCadastro.addActionListener(this);txtDataCadastro.addFocusListener(this);

	/*
	 * Insere JLabel de Informações para contato
	 * Insere JSeparador de Informações para contato
	 */
	cons.gridx = 2;cons.gridy = 12;cons.gridwidth = 1;cons.ipadx = 70;cons.ipady = 1;
	lblInfoContato.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
	lblInfoContato.setForeground(Color.BLUE);
	CadSer.add(lblInfoContato,cons);
	cons.gridx = 1;cons.gridy = 13;cons.gridwidth = 10;cons.ipadx = 570;//cons.ipady = 1;
	CadSer.add(jsInfoContato,cons);
	
	cons.gridx = 1;cons.gridy = 14;cons.gridwidth = 1;cons.ipadx = 50;
	CadSer.add(lblTelefoneA,cons);
	cons.gridx = 2;cons.gridy = 14;cons.gridwidth = 2;cons.ipadx = 110;
	JtxtTelefoneA.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
	CadSer.add(JtxtTelefoneA,cons);JtxtTelefoneA.setNextFocusableComponent(JtxtTelefoneB);
	JtxtTelefoneA.addActionListener(this);JtxtTelefoneA.addFocusListener(this);
		
	cons.gridx = 6;cons.gridy = 14;cons.gridwidth = 1;cons.ipadx = 50;
//	lblTelefoneB.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
	CadSer.add(lblTelefoneB,cons);
	cons.gridx = 7;cons.gridy = 14;cons.gridwidth = 2;cons.ipadx = 110;
	JtxtTelefoneB.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
	CadSer.add(JtxtTelefoneB,cons);JtxtTelefoneB.setNextFocusableComponent(JtxtFaxA);
	JtxtTelefoneB.addActionListener(this);JtxtTelefoneB.addFocusListener(this);
			
	cons.gridx = 1;cons.gridy = 15;cons.gridwidth = 1;cons.ipadx = 50;
	CadSer.add(lblFaxA,cons);
	cons.gridx = 2;cons.gridy = 15;cons.gridwidth = 3;cons.ipadx = 110;
	JtxtFaxA.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
	CadSer.add(JtxtFaxA,cons);JtxtFaxA.setNextFocusableComponent(JtxtFaxB);
	JtxtFaxA.addActionListener(this);JtxtFaxA.addFocusListener(this);
	
	cons.gridx = 6;cons.gridy = 15;cons.gridwidth = 1;cons.ipadx = 50;
	CadSer.add(lblFaxB,cons);
	cons.gridx = 7;cons.gridy = 15;cons.gridwidth = 3;cons.ipadx = 110;
	JtxtFaxB.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
	CadSer.add(JtxtFaxB,cons);JtxtFaxB.setNextFocusableComponent(txtSite);
	JtxtFaxB.addActionListener(this);JtxtFaxB.addFocusListener(this);
				
	cons.gridx = 1;cons.gridy = 16;cons.gridwidth = 1;cons.ipadx = 50;
	CadSer.add(lblSite,cons);
	cons.gridx = 2;cons.gridy = 16;cons.gridwidth = 9;cons.ipadx = 470;
	CadSer.add(txtSite,cons);txtSite.setNextFocusableComponent(txtEmail);
	txtSite.addActionListener(this);txtSite.addFocusListener(this);
			
	cons.gridx = 1;cons.gridy = 17;	cons.gridwidth = 1;cons.ipadx = 50;
	CadSer.add(lblEmail,cons);
	cons.gridx = 2;cons.gridy = 17;cons.gridwidth = 9;cons.ipadx = 470;
	CadSer.add(txtEmail,cons);
	txtEmail.addActionListener(this);txtEmail.addFocusListener(this);
	
	/*
	 * SESSÃO DE BOTÕES
	 */
	cons.anchor = GridBagConstraints.EAST;// cons.fill = GridBagConstraints.BOTH;
	cons.gridx = 20;cons.gridy = 3;cons.gridwidth = 1;cons.gridheight = 1;cons.ipadx = 50;cons.ipady = 1;
	CadSer.add(btoIncluir,cons);
	btoIncluir.addActionListener(this);
						
	cons.gridx = 20;cons.gridy = 4;cons.gridwidth = 1;cons.gridheight = 1;cons.ipadx = 50;
	CadSer.add(btoLocalizar,cons);
	btoLocalizar.addActionListener(this);
	
    cons.gridx = 20;cons.gridy = 4;cons.gridwidth = 1;cons.gridheight = 1;cons.ipadx = 50;
	CadSer.add(btoAtualizar,cons);
	btoAtualizar.addActionListener(this);
	
	cons.gridx = 20;cons.gridy = 5;cons.gridwidth = 1;cons.gridheight = 1;cons.ipadx = 50;
	CadSer.add(btoGravar,cons);
	btoGravar.addActionListener(this);
			
	cons.gridx = 20;cons.gridy = 6;cons.gridwidth = 1;cons.gridheight = 1;cons.ipadx = 50;cons.ipady = 1;
	CadSer.add(btoExcluir,cons);
	btoExcluir.addActionListener(this);
		
	cons.gridx = 20;cons.gridy = 17;cons.gridwidth = 1;cons.gridheight = 1;cons.ipadx = 50;
	CadSer.add(btoSair,cons);
	btoSair.addActionListener(this);
			
	cons.gridx = 20;cons.gridy = 17;cons.gridwidth = 1;cons.gridheight = 1;cons.ipadx = 50;
	CadSer.add(btoCancelar,cons);
	btoCancelar.addActionListener(this);
			
	btoIncluir.setEnabled(true);
	btoLocalizar.setEnabled(true);
	btoGravar.setEnabled(false);
	btoExcluir.setEnabled(false);
	btoSair.setEnabled(true);
	btoCancelar.setVisible(false);
	
    }

	private MaskFormatter setMascara(String mascara) {
		MaskFormatter mask = null;
		try {
			mask = new MaskFormatter(mascara);
			} catch (java.text.ParseException e) {}
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