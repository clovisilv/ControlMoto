package br.com.cs.controlmoto.controller;

import java.awt.BorderLayout;
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
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.MaskFormatter;
import javax.swing.text.PlainDocument;

import br.com.cs.controlmoto.domain.CadUsuariosDAO;
import br.com.cs.controlmoto.domain.ConnectionFactory;
import br.com.cs.controlmoto.utils.FixedLengthDocument;
import br.com.cs.controlmoto.vo.CadUsuariosVO;
/**
 * Company Fictec Cons. Inf.
 * @since 06/09/2012
 * @version 1.0.6
 * @author Clovis
 */

class CadUsuarios extends JInternalFrame implements ActionListener, FocusListener, KeyListener, MouseListener {
    /**
	 * 
	 */
	private static final long serialVersionUID = -2722543862301921669L;
	
	private JToolBar jtbBarraFerramenta;
	
	private JPanel jpCampos, jpToolBar;
	
	private JLabel  lblCodigo, lblNome, lblUsuario, lblSenha, lblConfirmaSenha, lblTipoUsuario, lblStatus;
	
	private JTextField	txtCodigo, txtNome, txtUsuario;
    
	private JPasswordField jpswSenha, jpswConfirmaSenha;
    
	private JComboBox jcbTipoUsuario,jcbStatus;
    
	private JButton btoIncluir, btoLocalizar, btoGravar, btoExcluir, btoSair,btoAtualizar, btoCancelar;
//    JCheckBox windowResizable = null, windowClosable = null;
    
    JInternalFrame cadUsuarios;
    
    private ControlMoto telaControlMoto;
    
    String tipoUsuario [] = {"","ADMINISTRADOR","GERENCIAL","USUARIO"};
    String status []= {"","HABILITADO","DESABILITADO"};
    String caminho ="", separadorPath = "";
    Dimension centraliza = Toolkit.getDefaultToolkit().getScreenSize();
    ImageIcon icone;
    
    Statement minhaSTM;
    ResultSet rs;
    Connection minhaConexao;

    //Serve para mover a tela interna
    static int openFrameCount = 2;
    static final int xOffset = 10, yOffset = 48;
    
    public CadUsuarios(String titulo, ControlMoto controlMoto){
    	super(titulo,false,true,false,true);
    	initCadUsuarios("", telaControlMoto);
    	this.telaControlMoto = telaControlMoto;
    	controlMoto.jDesktopPane.add(this);
    }
   
    public void initCadUsuarios(String titulo, ControlMoto controlMoto){
    	setSize(600,250);
    	setVisible(true);

    	cadUsuarios = new JInternalFrame("Cadastro de usuário");
    	cadUsuarios.setToolTipText("Cadastro de usuário");
    	
    	separadorPath = System.getProperty("file.separator");
    	caminho = new File("").getAbsolutePath();
    	
    	setLocation(xOffset*openFrameCount, yOffset*openFrameCount);
    	setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    	
		jpCampos = new JPanel(); jpToolBar = new JPanel();

    	GridBagConstraints cons = new GridBagConstraints();
    	//Container jpCampos = getContentPane();
    	GridBagLayout layout = new GridBagLayout();
    	jpCampos.setLayout(layout);
    	
		jtbBarraFerramenta = new JToolBar();
		jtbBarraFerramenta.setFloatable(false);
		jtbBarraFerramenta.setOrientation(javax.swing.SwingConstants.HORIZONTAL);

    	cons.insets = new Insets (3,5,3,5); 
    	cons.weightx = 0.30;cons.weighty = 0.30;

    	// Determina os espacos entre os componentes
    	// cons.anchor = GridBagConstraints.NORTHWEST; cons.fill = GridBagConstraints.HORIZONTAL;

    	lblCodigo = new JLabel("Código");lblNome = new JLabel("Nome");lblUsuario = new JLabel("Usuário");
    	lblSenha = new JLabel("Senha");lblConfirmaSenha = new JLabel("Confirma Senha");
    	lblTipoUsuario = new JLabel("Tipo Usuário");lblStatus = new JLabel("Status");

    	txtCodigo = new JTextField();
    	txtNome = new JTextField();txtNome.setDocument(new FixedLengthDocument(30));
    	txtUsuario = new JTextField();txtUsuario.setDocument(new FixedLengthDocument(6));
    	
    	jcbTipoUsuario = new JComboBox(tipoUsuario);jcbStatus = new JComboBox(status);

    	jpswSenha = new JPasswordField("");jpswSenha.setDocument(new FixedLengthDocument(6));
    	jpswConfirmaSenha = new JPasswordField("");jpswConfirmaSenha.setDocument(new FixedLengthDocument(6));

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

    	cons.anchor = GridBagConstraints.NORTHWEST;cons.fill = GridBagConstraints.HORIZONTAL;
    	cons.gridx = 1;cons.gridy = 2;cons.gridwidth = 1;cons.ipadx = 40;
    	jpCampos.add(lblCodigo,cons);

    	cons.gridx = 2;cons.gridy = 2;cons.gridwidth = 2;cons.ipadx = 80;
    	jpCampos.add(txtCodigo,cons);
    	txtCodigo.addActionListener(this);txtCodigo.addFocusListener(this);

    	cons.gridx = 1;cons.gridy = 3;cons.gridwidth = 1;cons.ipadx = 40;
    	jpCampos.add(lblNome,cons);

    	cons.gridx = 2;cons.gridy = 3;cons.gridwidth = 9;cons.ipadx = 450;
    	jpCampos.add(txtNome,cons);
    	txtNome.addActionListener(this);txtNome.addFocusListener(this);txtNome.addKeyListener(this);

    	cons.gridx = 1;	cons.gridy = 4;cons.gridwidth = 1;cons.ipadx = 40;
    	jpCampos.add(lblUsuario,cons);

    	cons.gridx = 2;cons.gridy = 4;cons.gridwidth = 4;cons.ipadx = 120;
    	jpCampos.add(txtUsuario,cons);
    	txtUsuario.addActionListener(this);txtUsuario.addFocusListener(this);

    	cons.gridx = 1;cons.gridy = 5;cons.gridwidth = 1;cons.ipadx = 40;
    	jpCampos.add(lblSenha,cons);

    	cons.gridx = 2;cons.gridy = 5;cons.gridwidth = 2;cons.ipadx = 40;
    	jpswSenha.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    	jpCampos.add(jpswSenha,cons);
    	jpswSenha.addActionListener(this);jpswSenha.addFocusListener(this);

    	cons.gridx = 4;cons.gridy = 5;cons.gridwidth = 1;cons.ipadx = 40;
    	lblConfirmaSenha.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    	jpCampos.add(lblConfirmaSenha,cons);

    	cons.gridx = 5;cons.gridy = 5;cons.gridwidth = 2;cons.ipadx = 40;
    	jpswConfirmaSenha.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    	jpCampos.add(jpswConfirmaSenha,cons);
    	jpswConfirmaSenha.addActionListener(this);jpswConfirmaSenha.addFocusListener(this);

    	cons.gridx = 1;cons.gridy = 6;cons.gridwidth = 1;cons.ipadx = 40;
    	jpCampos.add(lblTipoUsuario,cons);

    	cons.gridx = 2;cons.gridy = 6;cons.gridwidth = 2;cons.ipadx = 40;
    	jpCampos.add(jcbTipoUsuario,cons);
    	jcbTipoUsuario.addActionListener(this);jcbTipoUsuario.addFocusListener(this);jcbTipoUsuario.addKeyListener(this);

    	cons.gridx = 4;cons.gridy = 6;cons.gridwidth = 1;cons.ipadx = 40;
    	lblStatus.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    	jpCampos.add(lblStatus,cons);

    	cons.gridx = 5;cons.gridy = 6;cons.gridwidth = 2;cons.ipadx = 40;
    	jpCampos.add(jcbStatus,cons);
    	jcbStatus.addActionListener(this);jcbStatus.addFocusListener(this);jcbStatus.addKeyListener(this);

    	cons.fill = GridBagConstraints.HORIZONTAL;
    	
    	cons.gridx = 20;cons.gridy = 2;cons.gridwidth = 1;cons.gridheight = 1;cons.ipadx = 40;
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
		getContentPane().add(jtbBarraFerramenta, BorderLayout.NORTH );
    }
    
	public void keyReleased(KeyEvent c) {
	}
	public void keyTyped(KeyEvent c) {
	}
	public void keyPressed(KeyEvent c) {
	}
	public void focusGained(FocusEvent c) {
		if(c.getSource()==txtCodigo ){txtCodigo.selectAll();}
		if(c.getSource()==txtNome ){txtNome.selectAll();}
		if(c.getSource() == txtUsuario){txtUsuario.selectAll();}
		if(c.getSource()==jpswSenha){jpswSenha.selectAll();}
		if(c.getSource()== jpswConfirmaSenha){jpswConfirmaSenha.selectAll();}
	}
	
	public void focusLost(FocusEvent c) {
        if (c.getSource() == txtNome) {
		String nome = txtNome.getText();
		}
        if(c.getSource() == jpswConfirmaSenha){
			validaSenha();
		}
    }

	public void actionPerformed(ActionEvent c) {
		if (c.getSource() == btoIncluir) {
			try{
				CadUsuariosVO usuariosVO = new CadUsuariosVO();
				
				CadUsuariosDAO usuariosDAO = new CadUsuariosDAO();
				try {
					usuariosDAO.addUsuariosDAO(usuariosVO);
				} catch (InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				}
				
				txtCodigo.setText(String.valueOf(usuariosVO.getUsuarioPK()));
				txtNome.requestFocus();
				
				btoIncluir.setEnabled(false); btoLocalizar.setEnabled(false); btoAtualizar.setVisible(false);
				btoGravar.setEnabled(true);	btoCancelar.setVisible(true); btoSair.setVisible(false);
			}catch(SQLException e){
				JOptionPane.showMessageDialog(this, "Erro ao incluir usuário.","ControlMoto",JOptionPane.INFORMATION_MESSAGE);
			}
		}
		
		if (c.getSource() == btoGravar) {
			try{
				CadUsuariosVO usuariosVO = new CadUsuariosVO();
				usuariosVO.setUsuarioPK(Integer.valueOf(txtCodigo.getText()));
				usuariosVO.setNome(txtNome.getText());
				usuariosVO.setUsuario(txtUsuario.getText());
				usuariosVO.setSenha(jpswSenha.getText());
				usuariosVO.setTipoUsuario(""+ jcbTipoUsuario.getSelectedItem());
				usuariosVO.setStatus(""+ jcbStatus.getSelectedItem());
				
				CadUsuariosDAO usuariosDAO = new CadUsuariosDAO();
				try {
					usuariosDAO.saveUsuariosDAO(usuariosVO);
				} catch (InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				}finally{
					try {
						ConnectionFactory.getClosedConnection();
					} catch (IllegalAccessException | InstantiationException e) {
						e.printStackTrace();
					}
				}
				btoIncluir.setEnabled(true); btoGravar.setEnabled(false); btoLocalizar.setEnabled(true);
	            btoSair.setVisible(true);
	            
	            limpaCampos();
			}catch(SQLException e){
				JOptionPane.showMessageDialog(this, "Erro ao gravar usuário.","ControlMoto",JOptionPane.INFORMATION_MESSAGE);
			}
		}
		
		if (c.getSource() == btoAtualizar) {
			try{
				CadUsuariosVO usuariosVO = new CadUsuariosVO();
				usuariosVO.setUsuarioPK(Integer.valueOf(txtCodigo.getText()));
				usuariosVO.setNome(txtNome.getText());
				usuariosVO.setUsuario(txtUsuario.getText());
				usuariosVO.setSenha(""+ jpswSenha.getPassword());
				usuariosVO.setTipoUsuario(""+ jcbTipoUsuario.getSelectedItem());
				usuariosVO.setStatus(""+ jcbStatus.getSelectedItem());
				
				CadUsuariosDAO usuariosDAO = new CadUsuariosDAO();
				try {
					usuariosDAO.alterUsuarioByPk(usuariosVO);
				} catch (InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				}finally{
					try {
						ConnectionFactory.getClosedConnection();
					} catch (IllegalAccessException | InstantiationException e) {
						e.printStackTrace();
					}
				}
				btoLocalizar.setVisible(true); btoIncluir.setEnabled(true); btoExcluir.setEnabled(false);
	            btoSair.setVisible(true); btoCancelar.setVisible(false);
	            
				limpaCampos();
			}catch(SQLException e){
				JOptionPane.showMessageDialog(this, "Erro ao atualizar usuário.","ControlMoto",JOptionPane.INFORMATION_MESSAGE);
			}
		}
		
		if (c.getSource() == btoExcluir) {
			try{
				CadUsuariosVO usuariosVO = new CadUsuariosVO();
				usuariosVO.setUsuarioPK(Integer.valueOf(txtCodigo.getText()));
				
				CadUsuariosDAO usuariosDAO = new CadUsuariosDAO();
				try {
					usuariosDAO.deleteUsuarioByPk(usuariosVO);
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
				btoAtualizar.setEnabled(false); btoGravar.setEnabled(false); btoGravar.setVisible(true);
				btoExcluir.setEnabled(false); btoExcluir.setVisible(true); btoCancelar.setEnabled(false); btoCancelar.setVisible(false);
				btoSair.setEnabled(true); btoSair.setVisible(true);
				
				limpaCampos();
			}catch(SQLException e){
				JOptionPane.showMessageDialog(this, "Erro ao excluir usuário.","ControlMoto",JOptionPane.INFORMATION_MESSAGE);
			}
		}
		
		if (c.getSource() == btoLocalizar) {
			try{
				CadUsuariosVO usuariosVO = new CadUsuariosVO();
				usuariosVO.setUsuarioPK(Integer.parseInt(JOptionPane.showInputDialog(this,"Digite o codigo do usuário a ser procurado!","Moto Expres",JOptionPane.QUESTION_MESSAGE)));
							
				CadUsuariosDAO usuariosDAO = new CadUsuariosDAO();
				try {
					usuariosDAO.readUsuarioByPk(usuariosVO);
				} catch (InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				}finally{
					try {
						ConnectionFactory.getClosedConnection();
					} catch (IllegalAccessException | InstantiationException e) {
						e.printStackTrace();
					}
				}					
				txtCodigo.setText(String.valueOf(usuariosVO.getUsuarioPK()));
				txtNome.setText(usuariosVO.getNome());
				txtUsuario.setText(usuariosVO.getUsuario());
				jpswSenha.setText(usuariosVO.getSenha());
				jcbTipoUsuario.setSelectedItem(usuariosVO.getTipoUsuario());
				jcbStatus.setSelectedItem(usuariosVO.getStatus());
					
				btoAtualizar.setVisible(true); btoLocalizar.setVisible(false); btoIncluir.setEnabled(false);
				btoExcluir.setEnabled(true); btoCancelar.setVisible(true); btoSair.setVisible(false);
			}catch(SQLException e){
				JOptionPane.showMessageDialog(this, "Erro ao localizar usuário.","ControlMoto",JOptionPane.INFORMATION_MESSAGE);
			}
		}
		
		if (c.getSource() == btoCancelar) {
			JOptionPane.showMessageDialog(this,"Procedimento cancelado!","ControlMoto",JOptionPane.INFORMATION_MESSAGE);
			
			btoIncluir.setEnabled(true); btoLocalizar.setVisible(true); btoLocalizar.setEnabled(true); btoAtualizar.setVisible(false);
			btoGravar.setEnabled(false); btoExcluir.setEnabled(false); btoCancelar.setVisible(false); btoSair.setVisible(true);
			limpaCampos();
		}
		
		if (c.getSource() == btoSair) {
			int resultado = JOptionPane.showConfirmDialog(this,"Deseja sair do cadastro de usuário?", "ControlMoto",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE);
			if (resultado == 0){
				CadUsuarios.this.dispose();
				ControlMoto.telaCadUsuarios = null;
			}else{
				return;
			}
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

	class MeuDocument extends PlainDocument {
		public void insertString(int offs, String str, AttributeSet a)
				throws BadLocationException {
			super.insertString(offs, str.toUpperCase(), a);
		}
	}

	public void limpaCampos() {
		txtCodigo.setText("");
		txtNome.setText("");
		txtUsuario.setText("");
		jpswSenha.setText("");
		jpswConfirmaSenha.setText("");
		jcbTipoUsuario.setSelectedItem("");
		jcbStatus.setSelectedItem("");
	}

	public void atualizaCampos() {
		try {
			txtCodigo.setText(rs.getString("Codigo"));
			txtNome.setText(rs.getString("NomeUsuario"));
			txtUsuario.setText(rs.getString("Usuario"));
			jpswSenha.setText(rs.getString("Senha"));
			jcbStatus.setSelectedItem(rs.getString("Status"));
		} catch (SQLException ex) {
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

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
		if(e.getSource() == cadUsuarios)
		ControlMoto.telaCadUsuarios.setToolTipText("CADASTRO DE USUARIO");
		JOptionPane.showMessageDialog(null, "Cadastro de Usuario");
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

    }