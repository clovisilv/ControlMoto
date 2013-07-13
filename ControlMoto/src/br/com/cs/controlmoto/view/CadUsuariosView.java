package br.com.cs.controlmoto.view;

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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.MaskFormatter;
import javax.swing.text.PlainDocument;

class CadUsuariosView extends JInternalFrame implements ActionListener, FocusListener, KeyListener, MouseListener {

    /**
	 * 
	 */
	private static final long serialVersionUID = -2722543862301921669L;
	
	JLabel  lblCodigo, lblNome, lblUsuario, lblSenha, lblConfirmaSenha, lblTipoUsuario, lblStatus;
	
    JTextField	txtCodigo, txtNome, txtUsuario;
    
    JPasswordField jpswSenha, jpswConfirmaSenha;
    
    JComboBox jcbTipoUsuario,jcbStatus;
    
    JButton btoIncluir, btoLocalizar, btoGravar, btoExcluir, btoSair,btoAtualizar, btoCancelar;
//    JCheckBox windowResizable = null, windowClosable = null;
    
    JInternalFrame cadUsuarios;
    
    private ControlMotoView telaControlMoto;
    
    String tipoUsuario [] = {"","ADMINISTRADOR","GERENCIAL","USUARIO"};
    String status []= {"","HABILITADO","DESABILITADO"};
    String separadorPath = "";
    Dimension centraliza = Toolkit.getDefaultToolkit().getScreenSize();
    ImageIcon icone;
    
    Statement minhaSTM;
    ResultSet rs;
    Connection minhaConexao;

    //Serve para mover a tela interna
    static int openFrameCount = 3;
    static final int xOffset = 40, yOffset = 35;
    
    public CadUsuariosView(String titulo, ControlMotoView controlMoto){
    	super(titulo,false,true,false,true);
    	initCadUsuarios("", telaControlMoto);
    	this.telaControlMoto = telaControlMoto;
    	controlMoto.jDesktopPane.add(this);
    }
   
    public void initCadUsuarios(String titulo, ControlMotoView controlMoto){
    	setSize(600,250);
    	setVisible(true);

    	cadUsuarios = new JInternalFrame("Cadastro de usuario");
    	cadUsuarios.setToolTipText("Cadastro de usuario");
    	separadorPath = System.getProperty("file.separator");
    	setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    	
//    	ImageIcon icone = new ImageIcon("..//ControlMoto/imagens/logo_cs_b.gif");
//    	ImageIcon icone = new ImageIcon(".."+separadorPath+separadorPath+"ControlMoto"+separadorPath+"imagens"+separadorPath+"logo_cs_b.gif");
//    	setIconImage(icone.getImage());
    	setLocation((centraliza.width-this.getSize().width)/2,(centraliza.height-this.getSize().height)/3);   

    	GridBagConstraints cons = new GridBagConstraints();
    	Container CadSer = getContentPane();
    	GridBagLayout layout = new GridBagLayout();
    	CadSer.setLayout(layout);

    	cons.insets = new Insets (3,5,3,5); 
    	cons.weightx = 0.30;cons.weighty = 0.30;

    	// Determina os espa�os entre os componentes
    	// cons.anchor = GridBagConstraints.NORTHWEST;
    	// cons.fill = GridBagConstraints.HORIZONTAL;

    	lblCodigo = new JLabel("Código");lblNome = new JLabel("Nome");lblUsuario = new JLabel("Usuário");
    	lblSenha = new JLabel("Senha");lblConfirmaSenha = new JLabel("Confirma Senha");
    	lblTipoUsuario = new JLabel("Tipo Usuário");lblStatus = new JLabel("Status");

    	txtCodigo = new JTextField();txtNome = new JTextField();txtUsuario = new JTextField();
    	
    	jcbTipoUsuario = new JComboBox(tipoUsuario);jcbStatus = new JComboBox(status);

    	jpswSenha = new JPasswordField("");jpswConfirmaSenha = new JPasswordField("");

    	btoIncluir = new JButton("Incluir");btoIncluir.setMnemonic(KeyEvent.VK_I);
    	btoLocalizar = new JButton("Localizar");btoLocalizar.setMnemonic(KeyEvent.VK_L);
    	btoGravar = new JButton("Gravar");btoGravar.setMnemonic(KeyEvent.VK_G);
    	btoExcluir =  new JButton("Excluir");btoExcluir.setMnemonic(KeyEvent.VK_E);
    	btoSair = new JButton("Sair");btoSair.setMnemonic(KeyEvent.VK_S);
    	btoAtualizar = new JButton("Atualizar");btoAtualizar.setMnemonic(KeyEvent.VK_A);
    	btoCancelar = new JButton("Cancelar");btoCancelar.setMnemonic(KeyEvent.VK_C);

    	cons.anchor = GridBagConstraints.NORTHWEST;cons.fill = GridBagConstraints.HORIZONTAL;
    	cons.gridx = 1;cons.gridy = 2;cons.gridwidth = 1;cons.ipadx = 40;
    	CadSer.add(lblCodigo,cons);

    	cons.gridx = 2;cons.gridy = 2;cons.gridwidth = 2;cons.ipadx = 80;
    	CadSer.add(txtCodigo,cons);
    	txtCodigo.addActionListener(this);txtCodigo.addFocusListener(this);

    	cons.gridx = 1;cons.gridy = 3;cons.gridwidth = 1;cons.ipadx = 40;
    	CadSer.add(lblNome,cons);

    	cons.gridx = 2;cons.gridy = 3;cons.gridwidth = 9;cons.ipadx = 450;
    	CadSer.add(txtNome,cons);
    	txtNome.addActionListener(this);txtNome.addFocusListener(this);txtNome.addKeyListener(this);

    	cons.gridx = 1;	cons.gridy = 4;cons.gridwidth = 1;cons.ipadx = 40;
    	CadSer.add(lblUsuario,cons);

    	cons.gridx = 2;cons.gridy = 4;cons.gridwidth = 4;cons.ipadx = 120;
    	CadSer.add(txtUsuario,cons);
    	txtUsuario.addActionListener(this);txtUsuario.addFocusListener(this);

    	cons.gridx = 1;cons.gridy = 5;cons.gridwidth = 1;cons.ipadx = 40;
    	CadSer.add(lblSenha,cons);

    	cons.gridx = 2;cons.gridy = 5;cons.gridwidth = 2;cons.ipadx = 40;
    	jpswSenha.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    	CadSer.add(jpswSenha,cons);
    	jpswSenha.addActionListener(this);jpswSenha.addFocusListener(this);

    	cons.gridx = 4;cons.gridy = 5;cons.gridwidth = 1;cons.ipadx = 40;
    	lblConfirmaSenha.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    	CadSer.add(lblConfirmaSenha,cons);

    	cons.gridx = 5;cons.gridy = 5;cons.gridwidth = 2;cons.ipadx = 40;
    	jpswConfirmaSenha.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    	CadSer.add(jpswConfirmaSenha,cons);
    	jpswConfirmaSenha.addActionListener(this);jpswConfirmaSenha.addFocusListener(this);

    	cons.gridx = 1;cons.gridy = 6;cons.gridwidth = 1;cons.ipadx = 40;
    	CadSer.add(lblTipoUsuario,cons);

    	cons.gridx = 2;cons.gridy = 6;cons.gridwidth = 2;cons.ipadx = 40;
    	CadSer.add(jcbTipoUsuario,cons);
    	jcbTipoUsuario.addActionListener(this);jcbTipoUsuario.addFocusListener(this);jcbTipoUsuario.addKeyListener(this);

    	cons.gridx = 4;cons.gridy = 6;cons.gridwidth = 1;cons.ipadx = 40;
    	lblStatus.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    	CadSer.add(lblStatus,cons);

    	cons.gridx = 5;cons.gridy = 6;cons.gridwidth = 2;cons.ipadx = 40;
    	CadSer.add(jcbStatus,cons);
    	jcbStatus.addActionListener(this);jcbStatus.addFocusListener(this);jcbStatus.addKeyListener(this);

    	cons.fill = GridBagConstraints.HORIZONTAL;
    	cons.gridx = 20;cons.gridy = 2;cons.gridwidth = 1;cons.gridheight = 1;cons.ipadx = 40;
    	CadSer.add(btoIncluir,cons);
    	btoIncluir.addActionListener(this);

    	cons.gridx = 20;cons.gridy = 3;cons.gridwidth = 1;cons.gridheight = 1;cons.ipadx = 40;
    	CadSer.add(btoLocalizar,cons);
    	btoLocalizar.addActionListener(this);

    	cons.gridx = 20;cons.gridy = 3;cons.gridwidth = 1;cons.gridheight = 1;cons.ipadx = 40;
    	CadSer.add(btoAtualizar,cons);
    	btoAtualizar.addActionListener(this);

    	cons.gridx = 20;cons.gridy = 4;cons.gridwidth = 1;cons.gridheight = 1;cons.ipadx = 40;
    	CadSer.add(btoGravar,cons);
    	btoGravar.addActionListener(this);

    	cons.gridx = 20;cons.gridy = 5;cons.gridwidth = 1;cons.gridheight = 1;cons.ipadx = 40;
    	CadSer.add(btoExcluir,cons);
    	btoExcluir.addActionListener(this);

    	cons.gridx = 20;cons.gridy = 12;cons.gridwidth = 1;cons.gridheight = 1;cons.ipadx = 40;
    	CadSer.add(btoSair,cons);
    	btoSair.addActionListener(this);

    	cons.gridx = 20;cons.gridy = 12;cons.gridwidth = 1;cons.gridheight = 1;cons.ipadx = 40;
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

	public void validaSenha(){
		String Senha = (new String (jpswSenha.getPassword()));
		String ConfSenha = (new String(jpswConfirmaSenha.getPassword()));
		if(!ConfSenha.equals(Senha)){
			JOptionPane.showMessageDialog(null,"Senha nao confere !!!");
			jpswConfirmaSenha.requestFocus();
						
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