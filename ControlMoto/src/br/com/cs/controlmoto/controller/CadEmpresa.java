package br.com.cs.controlmoto.controller;

import java.awt.BorderLayout;
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
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.MaskFormatter;
import javax.swing.text.PlainDocument;

import br.com.cs.controlmoto.domain.CadEmpresaDAO;
import br.com.cs.controlmoto.domain.CadEnderecoDAO;
import br.com.cs.controlmoto.domain.CadTelefoneDAO;
import br.com.cs.controlmoto.domain.ConnectionFactory;
import br.com.cs.controlmoto.vo.CadEmpresaVO;
import br.com.cs.controlmoto.vo.CadEnderecoVO;
import br.com.cs.controlmoto.vo.CadTelefoneVO;
/**
 * Company Fictec Cons. Inf.
 * @since 06/09/2012
 * @version 1.0.6
 * @author Clovis
 */

class CadEmpresa extends JInternalFrame implements ActionListener, FocusListener, KeyListener {
    /**
	 * 
	 */
	private static final long serialVersionUID = -6249497882087014735L;

	private JToolBar jtbBarraFerramenta;
	
	private JPanel jpCampos, jpToolBar;
	
	private JLabel  lblCodigo, lblNome, lblCnpj, lblInscricaoestadual, lblEndereco, lblBairro, lblCidade, lblEstado,
    		lblCep, lblTelefoneA, lblTelefoneB, lblFaxA, lblFaxB, lblSite, lblEmail, lblDatacadastro, 
    		lblInfoGerais, lblInfoLocalizacao, lblInfoContato, lblCcm;
    
	private JTextField	txtCodigo, txtNome, txtEndereco, txtBairro, txtCidade, txtSite, txtEmail, txtDataCadastro, txtCcm;//txtEstado,
    
	private JFormattedTextField JtxtCnpj, JtxtInscricaoestadual, JtxtCep, JtxtTelefoneA, JtxtTelefoneB, JtxtFaxA,
    					JtxtFaxB, JtxtDatacadastro, JtxtCcm;
    
	private JButton btoIncluir, btoLocalizar, btoGravar, btoExcluir, btoSair, btoAtualizar, btoCancelar;
    
	private JComboBox jcbEstado;
    
	private JSeparator jsInfoGerais, jsInfoLocalizacao, jsInfoContato;
    
    private ControlMoto telaControlMoto;
    
    Dimension centraliza = Toolkit.getDefaultToolkit().getScreenSize();
    private String val = "", tx = "", texto = "", caminho = "", separadorPath = "";
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
    static int openFrameCount = 1;
    static final int xOffset = 10, yOffset = 10;

    
    public CadEmpresa(String titulo, ControlMoto controlMoto){
    	super(titulo,false,true,false,true);
    	setSize(620, 500);//650,400
    	setVisible(true);
    	setLocation(xOffset*openFrameCount, yOffset*openFrameCount);
    	
    	separadorPath = System.getProperty("file.separator");
    	caminho = new File("").getAbsolutePath();
    	
    	this.telaControlMoto = telaControlMoto;
    	controlMoto.jDesktopPane.add(this);
    	
		jpCampos = new JPanel(); jpToolBar = new JPanel();
    					
		GridBagConstraints cons = new GridBagConstraints();
		//Container jpCampos = getContentPane();
		GridBagLayout layout = new GridBagLayout();
		jpCampos.setLayout(layout);
				
		cons.insets = new Insets (3,5,3,5); 
		cons.weightx = 0.30;cons.weighty = 0.30;
		
		jtbBarraFerramenta = new JToolBar();
		jtbBarraFerramenta.setFloatable(false);
		jtbBarraFerramenta.setOrientation(javax.swing.SwingConstants.VERTICAL);
				
		lblCodigo = new JLabel("Código");lblNome = new JLabel("Nome");lblCnpj = new JLabel("C.N.P.J");
		lblInscricaoestadual = new JLabel("Ins. Estadual");lblEndereco = new JLabel("Endereço");
		lblBairro = new JLabel("Bairro");lblCidade = new JLabel("Cidade");lblEstado = new JLabel("Estado");
		lblCep = new JLabel("CEP");lblTelefoneA = new JLabel("Telefone");lblTelefoneB = new JLabel ("Telefone");
		lblFaxA = new JLabel("Fax");lblFaxB = new JLabel("Fax");lblSite = new JLabel("Site");lblEmail = new JLabel("E-mail");
		lblDatacadastro = new JLabel("Data Cadastro");lblInfoGerais = new JLabel("Informações Gerais");
		lblInfoLocalizacao = new JLabel("Informações Localização");lblInfoContato = new JLabel("Informações Contato");
		lblCcm = new JLabel("CCM");
				
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
		JtxtCcm = new JFormattedTextField(setMascara("##.###.###-#"));
				
		btoIncluir = new JButton(); btoIncluir.setMnemonic(KeyEvent.VK_I); btoIncluir.setToolTipText("Incluir");
		btoIncluir.setIcon(new ImageIcon(caminho + separadorPath + "imagens" + separadorPath + "icone" + separadorPath + "hd_folha_novo_32x32.png"));
		
		btoLocalizar = new JButton(); btoLocalizar.setMnemonic(KeyEvent.VK_L); btoLocalizar.setToolTipText("Localizar");
		btoLocalizar.setIcon(new ImageIcon(caminho + separadorPath + "imagens" + separadorPath + "icone" + separadorPath + "hd_lupa_32x32.png"));
		
		btoGravar = new JButton(); btoGravar.setMnemonic(KeyEvent.VK_G); btoGravar.setToolTipText("Gravar");
		btoGravar.setIcon(new ImageIcon(caminho + separadorPath + "imagens"	+ separadorPath + "icone" + separadorPath + "hd_flopy_32x32.png"));
		
		btoExcluir =  new JButton(); btoExcluir.setMnemonic(KeyEvent.VK_E); btoExcluir.setToolTipText("Excluir");
		btoExcluir.setIcon(new ImageIcon(caminho + separadorPath + "imagens" + separadorPath + "icone" + separadorPath + "lixeira_32x32.png"));
		
		btoSair = new JButton(); btoSair.setMnemonic(KeyEvent.VK_S); btoSair.setToolTipText("Sair");
		btoSair.setIcon(new ImageIcon(caminho + separadorPath + "imagens" + separadorPath + "icone" + separadorPath	+ "cancelar_circular_32x32.png"));
		
		btoAtualizar = new JButton(); btoAtualizar.setMnemonic(KeyEvent.VK_A); btoAtualizar.setToolTipText("Atualizar");
		btoAtualizar.setIcon(new ImageIcon(caminho + separadorPath + "imagens" + separadorPath + "icone" + separadorPath	+ "hd_atualiza_32x32.png"));
		
		btoCancelar = new JButton(); btoCancelar.setMnemonic(KeyEvent.VK_C); btoCancelar.setToolTipText("Cancelar");
		btoCancelar.setIcon(new ImageIcon(caminho + separadorPath + "imagens" + separadorPath + "icone" + separadorPath + "hd_cancela_32x32.png"));
	
		jcbEstado = new JComboBox(estados);
		
		jsInfoGerais = new JSeparator(); jsInfoLocalizacao = new JSeparator(); jsInfoContato = new JSeparator();
	
		sdf = new SimpleDateFormat ("dd/MM/yyyy");
		sdfTimestamp = new SimpleDateFormat ("yyyy/MM/dd");
		sdf.setLenient (false);sdfTimestamp.setLenient (false);
		
		cons.anchor = GridBagConstraints.NORTHWEST;
		/*
		 * Insere JLabel de Informações gerais
		 * Insere JSeparador de Informações gerais
		 */
		cons.gridx = 2;cons.gridy = 1;cons.gridwidth = 1;cons.ipadx = 70;cons.ipady = 1;
		lblInfoGerais.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		lblInfoGerais.setForeground(Color.BLUE);
		jpCampos.add(lblInfoGerais,cons);
		cons.gridx = 1;cons.gridy = 2;cons.gridwidth = 10;cons.ipadx = 570;//cons.ipady = 1;
		jpCampos.add(jsInfoGerais,cons);
		
		cons.gridx = 1;cons.gridy = 3;cons.gridwidth = 1;cons.ipadx = 50;
		jpCampos.add(lblCodigo, cons);
		cons.gridx = 2;cons.gridy = 3;cons.gridwidth = 2;cons.ipadx = 80;//cons.ipady = 1;
		jpCampos.add(txtCodigo, cons);txtCodigo.setNextFocusableComponent(JtxtCcm);
		txtCodigo.addActionListener(this);txtCodigo.addFocusListener(this);
		
		cons.gridx = 4;cons.gridy = 3;cons.gridwidth = 1;cons.ipadx = 50;
		jpCampos.add(lblCcm,cons);
		cons.gridx = 6;cons.gridy = 3;cons.gridwidth = 2;cons.ipadx = 80;//cons.ipady = 1;
		JtxtCcm.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jpCampos.add(JtxtCcm,cons);JtxtCcm.setNextFocusableComponent(txtNome);
		JtxtCcm.addActionListener(this);JtxtCcm.addFocusListener(this);
	
		cons.gridx = 1;cons.gridy = 4;cons.gridwidth = 1;cons.ipadx = 50;
		jpCampos.add(lblNome,cons);  
		cons.gridx = 2;cons.gridy = 4;cons.gridwidth = 9;cons.ipadx = 470;
		jpCampos.add(txtNome,cons);txtNome.setNextFocusableComponent(JtxtCnpj);
		txtNome.addActionListener(this);txtNome.addFocusListener(this);txtNome.addKeyListener(this);
				
		cons.gridx = 1;	cons.gridy = 5;cons.gridwidth = 1;cons.ipadx = 50;
		jpCampos.add(lblCnpj,cons);
		cons.gridx = 2;cons.gridy = 5;cons.gridwidth = 3;cons.ipadx = 150;
		JtxtCnpj.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jpCampos.add(JtxtCnpj,cons);JtxtCnpj.setNextFocusableComponent(JtxtInscricaoestadual);
		JtxtCnpj.addActionListener(this);JtxtCnpj.addFocusListener(this);
				
		cons.gridx = 6;cons.gridy = 5;cons.gridwidth = 1;cons.ipadx = 50;
	//	lblInscricaoestadual.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jpCampos.add(lblInscricaoestadual,cons);
		cons.gridx = 7;cons.gridy = 5;cons.gridwidth = 3;cons.ipadx = 150;
		JtxtInscricaoestadual.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jpCampos.add(JtxtInscricaoestadual,cons);JtxtInscricaoestadual.setNextFocusableComponent(JtxtCep);
		JtxtInscricaoestadual.addActionListener(this);JtxtInscricaoestadual.addFocusListener(this);
		/*
		 * Insere JLabel de Informações para localização
		 * Insere JSeparador de Informações para localização
		 */
		cons.gridx = 2;cons.gridy = 6;cons.gridwidth = 1;cons.ipadx = 70;cons.ipady = 1;
		lblInfoLocalizacao.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		lblInfoLocalizacao.setForeground(Color.BLUE);
		jpCampos.add(lblInfoLocalizacao,cons);
		cons.gridx = 1;cons.gridy = 7;cons.gridwidth = 10;cons.ipadx = 570;cons.ipady = 1;
		jpCampos.add(jsInfoLocalizacao,cons);
		
		cons.gridx = 1;cons.gridy = 8;cons.gridwidth = 1;cons.ipadx = 50;
		jpCampos.add(lblCep,cons);
		cons.gridx = 2;cons.gridy = 8;cons.gridwidth = 3;cons.ipadx = 80;
		JtxtCep.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jpCampos.add(JtxtCep,cons);JtxtCep.setNextFocusableComponent(txtEndereco);
		JtxtCep.addActionListener(this);JtxtCep.addFocusListener(this);
		
		cons.gridx = 1;cons.gridy = 9;cons.gridwidth = 1;cons.ipadx = 50;
		jpCampos.add(lblEndereco,cons);
		cons.gridx = 2;cons.gridy = 9;cons.gridwidth = 9;cons.ipadx = 470;
		jpCampos.add(txtEndereco,cons);txtEndereco.setNextFocusableComponent(txtBairro);
		txtEndereco.addActionListener(this);txtEndereco.addFocusListener(this);txtEndereco.addKeyListener(this);
						
		cons.gridx = 1;cons.gridy = 10;cons.gridwidth = 1;cons.ipadx = 50;
		jpCampos.add(lblBairro,cons);
		cons.gridx = 2;cons.gridy = 10;cons.gridwidth = 3;cons.ipadx = 250;
		jpCampos.add(txtBairro,cons);txtBairro.setNextFocusableComponent(txtCidade);
		txtBairro.addActionListener(this);txtBairro.addFocusListener(this);txtBairro.addKeyListener(this);
							
		cons.gridx = 6;cons.gridy = 10;cons.gridwidth = 1;cons.ipadx = 50;
		jpCampos.add(lblCidade,cons);
		cons.gridx = 7;cons.gridy = 10;cons.gridwidth = 3;cons.ipadx = 150;
		jpCampos.add(txtCidade,cons);txtCidade.setNextFocusableComponent(jcbEstado);
		txtCidade.addActionListener(this);txtCidade.addFocusListener(this);txtCidade.addKeyListener(this);
				
		cons.gridx = 1;cons.gridy = 11;cons.gridwidth = 1;cons.ipadx = 50;
		jpCampos.add(lblEstado,cons);
		cons.gridx = 2;cons.gridy = 11;cons.gridwidth = 1;cons.ipadx = 50;
		jpCampos.add(jcbEstado,cons);jcbEstado.setNextFocusableComponent(txtDataCadastro);
	//	txtEstado.addActionListener(this);txtEstado.addFocusListener(this);txtEstado.addKeyListener(this);
		
		cons.gridx = 6;cons.gridy = 11;cons.gridwidth = 1;cons.ipadx = 50;
		jpCampos.add(lblDatacadastro,cons);
		cons.gridx = 7;cons.gridy = 11;cons.gridwidth = 3;cons.ipadx = 110;
		txtDataCadastro.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jpCampos.add(txtDataCadastro,cons);txtDataCadastro.setNextFocusableComponent(JtxtTelefoneA);
		txtDataCadastro.addActionListener(this);txtDataCadastro.addFocusListener(this);
		/*
		 * Insere JLabel de Informações para contato
		 * Insere JSeparador de Informações para contato
		 */
		cons.gridx = 2;cons.gridy = 12;cons.gridwidth = 1;cons.ipadx = 70;cons.ipady = 1;
		lblInfoContato.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		lblInfoContato.setForeground(Color.BLUE);
		jpCampos.add(lblInfoContato,cons);
		cons.gridx = 1;cons.gridy = 13;cons.gridwidth = 10;cons.ipadx = 570;//cons.ipady = 1;
		jpCampos.add(jsInfoContato,cons);
	
		cons.gridx = 1;cons.gridy = 14;cons.gridwidth = 1;cons.ipadx = 50;
		jpCampos.add(lblTelefoneA,cons);
		cons.gridx = 2;cons.gridy = 14;cons.gridwidth = 2;cons.ipadx = 110;
		JtxtTelefoneA.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jpCampos.add(JtxtTelefoneA,cons);JtxtTelefoneA.setNextFocusableComponent(JtxtTelefoneB);
		JtxtTelefoneA.addActionListener(this);JtxtTelefoneA.addFocusListener(this);
			
		cons.gridx = 6;cons.gridy = 14;cons.gridwidth = 1;cons.ipadx = 50;
	//	lblTelefoneB.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jpCampos.add(lblTelefoneB,cons);
		cons.gridx = 7;cons.gridy = 14;cons.gridwidth = 2;cons.ipadx = 110;
		JtxtTelefoneB.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jpCampos.add(JtxtTelefoneB,cons);JtxtTelefoneB.setNextFocusableComponent(JtxtFaxA);
		JtxtTelefoneB.addActionListener(this);JtxtTelefoneB.addFocusListener(this);
				
		cons.gridx = 1;cons.gridy = 15;cons.gridwidth = 1;cons.ipadx = 50;
		jpCampos.add(lblFaxA,cons);
		cons.gridx = 2;cons.gridy = 15;cons.gridwidth = 3;cons.ipadx = 110;
		JtxtFaxA.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jpCampos.add(JtxtFaxA,cons);JtxtFaxA.setNextFocusableComponent(JtxtFaxB);
		JtxtFaxA.addActionListener(this);JtxtFaxA.addFocusListener(this);
		
		cons.gridx = 6;cons.gridy = 15;cons.gridwidth = 1;cons.ipadx = 50;
		jpCampos.add(lblFaxB,cons);
		cons.gridx = 7;cons.gridy = 15;cons.gridwidth = 3;cons.ipadx = 110;
		JtxtFaxB.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jpCampos.add(JtxtFaxB,cons);JtxtFaxB.setNextFocusableComponent(txtSite);
		JtxtFaxB.addActionListener(this);JtxtFaxB.addFocusListener(this);
					
		cons.gridx = 1;cons.gridy = 16;cons.gridwidth = 1;cons.ipadx = 50;
		jpCampos.add(lblSite,cons);
		cons.gridx = 2;cons.gridy = 16;cons.gridwidth = 9;cons.ipadx = 470;
		jpCampos.add(txtSite,cons);txtSite.setNextFocusableComponent(txtEmail);
		txtSite.addActionListener(this);txtSite.addFocusListener(this);
				
		cons.gridx = 1;cons.gridy = 17;	cons.gridwidth = 1;cons.ipadx = 50;
		jpCampos.add(lblEmail,cons);
		cons.gridx = 2;cons.gridy = 17;cons.gridwidth = 9;cons.ipadx = 470;
		jpCampos.add(txtEmail,cons);
		txtEmail.addActionListener(this);txtEmail.addFocusListener(this);

		/* SESSÃO DE BOTÕES */
		cons.anchor = GridBagConstraints.EAST;// cons.fill = GridBagConstraints.BOTH;	
		cons.gridx = 20;cons.gridy = 3;cons.gridwidth = 1;cons.gridheight = 1;cons.ipadx = 50;cons.ipady = 1;
		btoIncluir.setNextFocusableComponent(btoLocalizar);
		jtbBarraFerramenta.add(btoIncluir, cons); btoIncluir.addActionListener(this);

		cons.gridx = 20;cons.gridy = 4;cons.gridwidth = 1;cons.gridheight = 1;cons.ipadx = 60;
		btoGravar.setNextFocusableComponent(btoCancelar);
		jtbBarraFerramenta.add(btoGravar, cons); btoGravar.addActionListener(this);

		cons.gridx = 20;cons.gridy = 5;cons.gridwidth = 1;cons.gridheight = 1;cons.ipadx = 60;
		btoLocalizar.setNextFocusableComponent(btoSair);
		jtbBarraFerramenta.add(btoLocalizar, cons); btoLocalizar.addActionListener(this);

		cons.gridx = 20;cons.gridy = 6;cons.gridwidth = 1;cons.gridheight = 1;cons.ipadx = 60;
		btoAtualizar.setNextFocusableComponent(btoExcluir);
		jtbBarraFerramenta.add(btoAtualizar, cons); btoAtualizar.addActionListener(this);

		cons.gridx = 20;cons.gridy = 7;cons.gridwidth = 1;cons.gridheight = 1;cons.ipadx = 60;
		btoExcluir.setNextFocusableComponent(btoCancelar);
		jtbBarraFerramenta.add(btoExcluir, cons); btoExcluir.addActionListener(this);

		cons.gridx = 20;cons.gridy = 8;cons.gridwidth = 1;cons.gridheight = 1;cons.ipadx = 60;
		jtbBarraFerramenta.add(btoCancelar, cons); btoCancelar.addActionListener(this);

		cons.gridx = 20;cons.gridy = 9;cons.gridwidth = 1;cons.gridheight = 1;cons.ipadx = 60;
		btoSair.setNextFocusableComponent(txtCodigo);
		jtbBarraFerramenta.add(btoSair, cons); btoSair.addActionListener(this);

		btoIncluir.setEnabled(true); btoGravar.setEnabled(false); btoLocalizar.setEnabled(true); btoAtualizar.setEnabled(false);
		btoExcluir.setEnabled(false); btoSair.setEnabled(true); btoCancelar.setEnabled(false);
		
		jpToolBar.add(jtbBarraFerramenta);
		
		getContentPane().add(jpCampos, BorderLayout.CENTER );
		getContentPane().add(jtbBarraFerramenta, BorderLayout.EAST );
	
    }

	public void keyReleased(KeyEvent e) {

	}

	public void keyTyped(KeyEvent e) {

	}

	public void keyPressed(KeyEvent c) {
      
        }

	public void focusGained(FocusEvent c) {
		if (c.getSource() == txtCodigo) {
			txtCodigo.selectAll();
		}
		if (c.getSource() == txtNome) {
			txtNome.selectAll();
		}
		if (c.getSource() == JtxtCnpj) {
			JtxtCnpj.selectAll();
		}
		if (c.getSource() == JtxtInscricaoestadual) {
			JtxtInscricaoestadual.selectAll();
		}
		if (c.getSource() == txtEndereco) {
			txtEndereco.selectAll();
		}
		if (c.getSource() == txtBairro) {
			txtBairro.selectAll();
		}
		if (c.getSource() == txtCidade) {
			txtCidade.selectAll();
		}
//		if (c.getSource() == txtEstado) {
//		txtEstado.selectAll();
//		}
		if (c.getSource() == JtxtCep) {
			JtxtCep.selectAll();
		}
		if (c.getSource() == JtxtTelefoneA) {
			JtxtTelefoneA.selectAll();
		}
		if (c.getSource() == JtxtTelefoneB) {
			JtxtTelefoneB.selectAll();
		}
		if (c.getSource() == JtxtFaxA) {
			JtxtFaxA.selectAll();
		}
		if (c.getSource() == JtxtFaxB) {
			JtxtFaxB.selectAll();
		}
//		if (c.getSource() == JtxtCelular) {
//		JtxtCelular.selectAll();
//		}
		if (c.getSource() == txtSite) {
			txtSite.selectAll();
        }
		if (c.getSource() == txtEmail) {
			txtEmail.selectAll();
		}
		if (c.getSource() == txtDataCadastro) {
			txtDataCadastro.selectAll();
			if (txtDataCadastro.getText().equals("")) {
				data = new Date();
				txtDataCadastro.setText(""+ sdf.format(data));
				timestamp = new Timestamp(data.getTime());
			}
		}
	
	}

	public void focusLost(FocusEvent c){
		
		if (c.getSource() == txtNome){
			String nome = txtNome.getText();
		}
		
		if(c.getSource() == JtxtCep){
			try{
				if(!JtxtCep.getText().equals("     -   ")){
					CadEnderecoVO enderecoVO = new CadEnderecoVO();
					enderecoVO.setCep(JtxtCep.getText());
					CadEnderecoDAO enderecoDAO = new CadEnderecoDAO();
					try {
						enderecoDAO.readEnderecoByCep(enderecoVO);
					} catch (InstantiationException | IllegalAccessException e) {
						e.printStackTrace();
					} 
					
					txtEndereco.setText(enderecoVO.getEndereco());
					txtBairro.setText(enderecoVO.getBairro());
					txtCidade.setText(enderecoVO.getCidade());
					jcbEstado.setSelectedItem(enderecoVO.getEstado());
					
					if(enderecoVO.getEnderecoPk() == null){
						txtEndereco.requestFocus();
					}else{
						txtDataCadastro.requestFocus();
						}
				}else{
					JOptionPane.showMessageDialog(this,"Campo cep está vázio.\nDigite o cep.","ControlMoto",JOptionPane.INFORMATION_MESSAGE);
					JtxtCep.requestFocus();
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this,"Cliente não localizada.\nPara cadastrar clique em incluir.","ControlMoto",JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

	public void actionPerformed(ActionEvent c) {
		if (c.getSource() == btoIncluir) {
			try{
				CadEmpresaVO empresaVO = new CadEmpresaVO();
				
				CadEmpresaDAO empresaDAO = new CadEmpresaDAO();
				try {
					empresaDAO.addEmpresaDAO(empresaVO);
				} catch (InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				}finally{
					try {
						ConnectionFactory.getClosedConnection();
					} catch (IllegalAccessException | InstantiationException e) {
						e.printStackTrace();
					}
				}				
				txtCodigo.setText(String.valueOf(empresaVO.getEmpresaPK()));
				txtNome.requestFocus();
				
				btoIncluir.setEnabled(false); btoLocalizar.setEnabled(false); btoAtualizar.setVisible(false);
				btoGravar.setEnabled(true); btoCancelar.setVisible(true); btoSair.setVisible(false);
			}catch(SQLException e){
				JOptionPane.showMessageDialog(this, "Erro ao incluir empresa.","ControlMoto",JOptionPane.INFORMATION_MESSAGE);
			}			
		}
		
		if (c.getSource() == btoGravar) {
			try{
				CadEnderecoVO enderecoVO = new CadEnderecoVO();
				enderecoVO.setCep(JtxtCep.getText());
				enderecoVO.setEndereco(txtEndereco.getText());
				enderecoVO.setBairro(txtBairro.getText());
				enderecoVO.setCidade(txtCidade.getText());
				enderecoVO.setEstado(String.valueOf(jcbEstado.getSelectedItem()));
				
				CadEnderecoDAO enderecoDAO = new CadEnderecoDAO();
				try {
					enderecoDAO.saveEnderecoDAO(enderecoVO);
				} catch (InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				}finally{
					try {
						ConnectionFactory.getClosedConnection();
					} catch (IllegalAccessException | InstantiationException e) {
						e.printStackTrace();
					}
				}
				
				CadTelefoneVO telefoneVO = new CadTelefoneVO();
				telefoneVO.setFaxA(JtxtFaxA.getText());
				telefoneVO.setFaxB(JtxtFaxB.getText());
				telefoneVO.setTelefoneA(JtxtTelefoneA.getText());
				telefoneVO.setTelefoneB(JtxtTelefoneB.getText());
				
				CadTelefoneDAO telefoneDAO = new CadTelefoneDAO();
				try {
					telefoneDAO.saveTelefoneDAO(telefoneVO);
				} catch (InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				}finally{
					try {
						ConnectionFactory.getClosedConnection();
					} catch (IllegalAccessException | InstantiationException e) {
						e.printStackTrace();
					}
				}
				
				CadEmpresaVO empresaVO = new CadEmpresaVO();
				empresaVO.setEmpresaPK(Integer.parseInt((txtCodigo.getText())));
				empresaVO.setEnderecoFk(enderecoVO.getEnderecoPk());
				empresaVO.setTelefoneFk(telefoneVO.getTelefonePk());
				empresaVO.setNome(txtNome.getText());
				empresaVO.setCnpj(JtxtCnpj.getText());
				empresaVO.setCcm(JtxtCcm.getText());
				empresaVO.setInscricaoEstadual(JtxtInscricaoestadual.getText());
				empresaVO.setSite(txtSite.getText());
				empresaVO.setEmail(txtEmail.getText());
				empresaVO.setDataCadastros(timestamp);
				
				CadEmpresaDAO empresaDAO = new CadEmpresaDAO();
				try {
					empresaDAO.saveEmpresaDAO(empresaVO);
				} catch (InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				}finally{
					try {
						ConnectionFactory.getClosedConnection();
					} catch (IllegalAccessException | InstantiationException e) {
						e.printStackTrace();
					}
				}
				
				btoIncluir.setEnabled(true); btoGravar.setEnabled(false); btoLocalizar.setEnabled(true); btoSair.setVisible(true);
	
				limpaCampos();
			}catch(SQLException e){
				JOptionPane.showMessageDialog(this, "Erro ao gravar empresa.","ControlMoto",JOptionPane.INFORMATION_MESSAGE);
			}
		}
		
		if (c.getSource() == btoLocalizar) {
			try{
				//POPULA O VO DE EMPRESA PARA REALIZAR A BUSCA DA EMPRESA
				CadEmpresaVO empresaVO = new CadEmpresaVO();
				empresaVO.setEmpresaPK(Integer.parseInt(JOptionPane.showInputDialog(this,"Digite o código do empresa!","Moto Expores",JOptionPane.INFORMATION_MESSAGE)));
				CadEmpresaDAO empresaDAO = new CadEmpresaDAO();
				try {
					empresaDAO.readEmpresaByPk(empresaVO);
				} catch (InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				}finally{
					try {
						ConnectionFactory.getClosedConnection();
					} catch (IllegalAccessException | InstantiationException e) {
						e.printStackTrace();
					}
				}
				enderecoPk = empresaVO.getEnderecoFk();
				telefonePk = empresaVO.getTelefoneFk();
				txtCodigo.setText(String.valueOf(empresaVO.getEmpresaPK()));
				txtNome.setText(empresaVO.getNome());
				JtxtCnpj.setText(empresaVO.getCnpj());
				JtxtCcm.setText(empresaVO.getCcm());
				JtxtInscricaoestadual.setText(empresaVO.getInscricaoEstadual());
				txtSite.setText(empresaVO.getSite());
				txtEmail.setText(empresaVO.getEmail());
				txtDataCadastro.setText(""+ sdf.format(empresaVO.getDataCadastros()));
				//POPULA O VO DE ENDERECO PARA REALIZAR A BUSCA DO ENDERECO
				CadEnderecoVO enderecoVO = new CadEnderecoVO();
				enderecoVO.setEnderecoPk(empresaVO.getEnderecoFk());
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
				JtxtCep.setText(enderecoVO.getCep());
				txtEndereco.setText(enderecoVO.getEndereco());
				txtBairro.setText(enderecoVO.getBairro());
				txtCidade.setText(enderecoVO.getCidade());
				jcbEstado.setSelectedItem(enderecoVO.getEstado());
				//POPULA O VO DE TELEFONE PARA REALIZAR A BUSCA DO TELEFONE
				CadTelefoneVO telefoneVO = new CadTelefoneVO();
				telefoneVO.setTelefonePk(empresaVO.getTelefoneFk());
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
				JtxtTelefoneA.setText(telefoneVO.getTelefoneA());
				JtxtTelefoneB.setText(telefoneVO.getTelefoneB());
				JtxtFaxA.setText(telefoneVO.getFaxA());
				JtxtFaxB.setText(telefoneVO.getFaxB());
				
				btoAtualizar.setVisible(true); btoLocalizar.setVisible(false); btoIncluir.setEnabled(false);
				btoExcluir.setEnabled(true); btoCancelar.setVisible(true); btoSair.setVisible(false);
			}catch(SQLException e){
				JOptionPane.showMessageDialog(this, "Erro ao localizar empresa.","ControlMoto",JOptionPane.INFORMATION_MESSAGE);
			}
		}
		
		if (c.getSource() == btoAtualizar) {
			try{
				CadEmpresaVO empresaVO = new CadEmpresaVO();
				empresaVO.setEmpresaPK(Integer.parseInt(txtCodigo.getText()));
				empresaVO.setNome(txtNome.getText());
				empresaVO.setCnpj(JtxtCnpj.getText());
				empresaVO.setCcm(JtxtCcm.getText());
				empresaVO.setInscricaoEstadual(JtxtInscricaoestadual.getText());
				empresaVO.setSite(txtSite.getText());
				empresaVO.setEmail(txtEmail.getText());
				try{
					empresaVO.setDataCadastros(new java.sql.Timestamp(sdf.parse(txtDataCadastro.getText()).getTime()));
				}catch (Exception e) {}
				
				CadEmpresaDAO empresaDAO = new CadEmpresaDAO();
				try {
					empresaDAO.alterEmpresaByPk(empresaVO);
				} catch (InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				}finally{
					try {
						ConnectionFactory.getClosedConnection();
					} catch (IllegalAccessException | InstantiationException e) {
						e.printStackTrace();
					}
				}
				
				CadEnderecoVO enderecoVO = new CadEnderecoVO();
				enderecoVO.setEnderecoPk(enderecoPk);
				enderecoVO.setCep(JtxtCep.getText());
				enderecoVO.setEndereco(txtEndereco.getText());
				enderecoVO.setBairro(txtBairro.getText());
				enderecoVO.setCidade(txtCidade.getText());
				enderecoVO.setEstado(String.valueOf(jcbEstado.getSelectedItem()));
				
				CadEnderecoDAO enderecoDAO = new CadEnderecoDAO();
				try {
					enderecoDAO.alterEnderecoByPk(enderecoVO);
				} catch (InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				}finally{
					try {
						ConnectionFactory.getClosedConnection();
					} catch (IllegalAccessException | InstantiationException e) {
						e.printStackTrace();
					}
				}
				
				CadTelefoneVO telefoneVO = new CadTelefoneVO();
				telefoneVO.setTelefonePk(telefonePk);
				telefoneVO.setFaxA(JtxtFaxA.getText());
				telefoneVO.setFaxB(JtxtFaxB.getText());
				telefoneVO.setTelefoneA(JtxtTelefoneA.getText());
				telefoneVO.setTelefoneB(JtxtTelefoneB.getText());
				
				CadTelefoneDAO telefoneDAO = new CadTelefoneDAO();
				try {
					telefoneDAO.alterTelefoneByPk(telefoneVO);
				} catch (InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				}finally{
					try {
						ConnectionFactory.getClosedConnection();
					} catch (IllegalAccessException | InstantiationException e) {
						e.printStackTrace();
					}
				}
				
	            btoAtualizar.setVisible(false); btoLocalizar.setVisible(true); btoIncluir.setEnabled(true);
	            btoExcluir.setEnabled(false); btoSair.setVisible(true); btoCancelar.setVisible(false);
				
	            limpaCampos();
			}catch(SQLException e){
				JOptionPane.showMessageDialog(this,"Erro na atualização.","ControlMoto",JOptionPane.INFORMATION_MESSAGE);
			}
			
		}
		
		if (c.getSource() == btoExcluir) {
			try{
				CadEmpresaVO empresaVO = new CadEmpresaVO();
				empresaVO.setEmpresaPK(Integer.valueOf(txtCodigo.getText()));
				
				CadEmpresaDAO empresaDAO = new CadEmpresaDAO();
				try {
					empresaDAO.deleteEmpresaByPk(empresaVO);
				} catch (InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				}finally{
					try {
						ConnectionFactory.getClosedConnection();
					} catch (IllegalAccessException | InstantiationException e) {
						e.printStackTrace();
					}
				}
				
				btoIncluir.setEnabled(true); btoIncluir.setVisible(true); btoLocalizar.setEnabled(true); btoLocalizar.setVisible(true);
				btoAtualizar.setEnabled(false); btoAtualizar.setVisible(false); btoGravar.setEnabled(false); btoExcluir.setEnabled(false);
				btoExcluir.setVisible(true); btoSair.setEnabled(true); btoSair.setVisible(true); btoCancelar.setEnabled(false);
				btoCancelar.setVisible(false);
				
				limpaCampos();
			}catch(SQLException e){
				JOptionPane.showMessageDialog(this, "Erro ao excluir empresa.","ControlMoto",JOptionPane.INFORMATION_MESSAGE);
			}			
		}
		
		if (c.getSource() == btoCancelar) {
			JOptionPane.showMessageDialog(this, "Procedimento cancelado!");
			btoIncluir.setEnabled(true); btoLocalizar.setVisible(true); btoLocalizar.setEnabled(true); btoAtualizar.setVisible(false);
			btoGravar.setEnabled(false); btoExcluir.setEnabled(false); btoCancelar.setVisible(false); btoSair.setVisible(true);
			limpaCampos();
		}
		
		if (c.getSource() == btoSair) {
			int resultado = JOptionPane.showConfirmDialog(this,"Deseja sair do Cadastro de Empresa?", "ControlMoto",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE);
			if (resultado == 0)
				CadEmpresa.this.dispose();
			else
				return;
			}
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

	public void limpaCampos() {
		txtCodigo.setText("");
		txtNome.setText("");
		JtxtCnpj.setText("");
		JtxtCcm.setText("");
		JtxtInscricaoestadual.setText("");
		txtEndereco.setText("");
		txtBairro.setText("");
		txtCidade.setText("");
		jcbEstado.setSelectedItem("");
		JtxtCep.setText("");
		JtxtTelefoneA.setText("");
		JtxtTelefoneB.setText("");
		JtxtFaxA.setText("");
		JtxtFaxB.setText("");
		txtSite.setText("");
		txtEmail.setText("");
		txtDataCadastro.setText("");
	}

	public void atualizaCampos() {
		try {
			txtCodigo.setText(rs.getString("Codigo"));
			txtNome.setText(rs.getString("NomeEmpresa"));
			JtxtCnpj.setText(rs.getString("CnpjEmp"));
			JtxtInscricaoestadual.setText(rs.getString("IEEmp"));
			txtEndereco.setText(rs.getString("EndEmp"));
			txtCidade.setText(rs.getString("CidEmp"));
			jcbEstado.setSelectedItem(rs.getString("EstEmp"));
			JtxtCep.setText(rs.getString("CepEmp"));
			JtxtTelefoneA.setText(rs.getString("TelEmpA"));
			JtxtTelefoneB.setText(rs.getString("TelEmpB"));
			JtxtFaxA.setText(rs.getString("FaxEmp"));
			JtxtFaxB.setText(rs.getString("FaxEmp"));
			txtSite.setText(rs.getString("SiteEmp"));
			txtEmail.setText(rs.getString("EmailEmp"));
			txtDataCadastro.setText(rs.getString("DataCadastro"));
		} catch (SQLException ex) {}

	}
}