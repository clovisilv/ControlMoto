package br.com.cs.controlmoto.view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.help.HelpSet;
import javax.help.JHelp;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import br.com.cs.controlmoto.utils.LerArquivo;
import br.com.cs.controlmoto.vo.AutenticacaoVO;
import br.com.cs.controlmoto.vo.CadUsuariosVO;
import br.com.cs.controlmoto.vo.LerArquivoVO;

public class ControlMotoView extends JFrame implements ActionListener, MouseListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5773897156310280097L;
	
	JMenuBar	barraMenu;
	JMenu		jmConfigurar,jmCadastrar,jmOperacional,jmRelatorio,jmAjuda,jmSobre;
	JMenuItem	jmiCadUsuario,jmiRefAcesso,jmiSair,jmiCliente,jmiMotorista,jmiPesquisa,jmiAbrirOrdem,jmiFecharOrdem, jmiConsultarOrdem, jmiRelatTotalCli,jmiRelatTotalMot,
				jmiAjudas,jmiParametros;
	
	JFrame frameHelp;
	
	JDesktopPane jDesktopPane = new JDesktopPane();
	
	ImageIcon icone;
	
	static Connection minhaConexao;
	static Statement minhaSTM;
	static ResultSet rs;

	Dimension centraliza = Toolkit.getDefaultToolkit().getScreenSize();

	String SQL, caminho, separadorPath="", logo_tipo, sobre_img, ajuda_arq;
	
	protected java.util.Properties props;
	
	static CadUsuariosView	telaCadUsuarios;
	static CadEmpresaView	telaCadEmpresa;
	static CadClienteView	telaCadCliente;
	static CadMotoristaView	telaCadMotorista;
	static AbrirOrdensView	telaAbrirOrdem;
	static FecharOrdemView	telaFecharOrdem;
	static PesqRelatClieView telaPesqRelatClie;
	static PesqRelatMotView	telaPesqRelatMot;
	static TabConsultaView	telaTabConsulta;
	static TabConsultaOrdensView telaTabConsultaOrdens;

	private String recNameControl = "";
	private String recSenhaControl = "";
	private String recStatusControl = "";
	private String recTipoUsuarioControl = "";
	public AutenticacaoVO autenticacaoVO;

//	public static void main(String [] args) {
//		controlMoto();
//	}

	public static void controlMoto(){
		JFrame controlMoto = new ControlMotoView();
		controlMoto.setVisible(true);
		WindowListener x = new WindowAdapter(){
			public void windowActivated(WindowEvent e){
			}
			public void windowClosed(WindowEvent e){
			}
			public void windowDeactivated(WindowEvent e){
			}
			public void windowDeiconified(WindowEvent e){
			}
			public void windowIconified(WindowEvent e){
			}
			public void windowOpened(WindowEvent e){
				AutenticacaoView.autenticacao();
			}		
			public void windowClosing(WindowEvent e){
				System.exit(0);}
		}; controlMoto.addWindowListener(x);
	}

	public ControlMotoView(){
		separadorPath = System.getProperty("file.separator");
		caminho = new File("").getAbsolutePath();
		//CAMINHO CORRETO PARA FUNCIONAR NO JAR		
		//File file = new File(caminho+separadorPath+"ControlMoto"+separadorPath+"resources"+separadorPath+"propertys.properties");
		//CAMINHO CORRETO PARA FUNCIONAR NO ECLIPSE
		//File file = new File(caminho+separadorPath+"resources"+separadorPath+"propertys.properties");		
		//props = new Properties();
		//FileInputStream fis = null;
		//try{
			//fis = new FileInputStream(file);
			//props.load(fis);
			//logo_tipo = props.getProperty("logo_tipo");
			//sobre_img = props.getProperty("sobre_img");
			//ajuda_arq = props.getProperty("ajuda_arq");			
			//fis.close();
		//}catch (IOException e) {
			//JOptionPane.showMessageDialog(this, e.getMessage()+"\n"+"Erro ao ler informações do sistema","ControlMoto",JOptionPane.INFORMATION_MESSAGE);
		//}

		//JOptionPane.showMessageDialog(null, ""+file +"\n"+ sobre_img );
		LerArquivo lerArquivo = new LerArquivo();
		LerArquivoVO lerArquivoVO = new LerArquivoVO();
		lerArquivoVO = lerArquivo.getInformacao(lerArquivoVO);
		
		getContentPane().add(jDesktopPane);
		setSize(centraliza.width,centraliza.height);
		setTitle("ControlMoto - Seu sistema de controle !!!");
		//Icone da tela pequeno - Caminho Funciona no Eclipse
		//icone = new ImageIcon(caminho+separadorPath+logo_tipo+"logo_cs_b.gif");
		icone = new ImageIcon(lerArquivoVO.getLogoTipo()+separadorPath+"logo_cs_b.gif");
		setIconImage(icone.getImage());
		
		barraMenu = new JMenuBar();

		jmConfigurar = new JMenu("Configurar");jmCadastrar = new JMenu("Cadastrar");
		jmOperacional = new JMenu("Operacional");jmRelatorio = new JMenu("Relatários");
		jmAjuda = new JMenu("Ajuda");jmSobre = new JMenu("Sobre");

		jmiParametros = new JMenuItem("Parâmetros");jmiCadUsuario = new JMenuItem("Usuário");
		jmiRefAcesso = new JMenuItem("Refazer Acesso");jmiSair = new JMenuItem("Sair");
		jmiCliente = new JMenuItem("Cliente");jmiMotorista = new JMenuItem("Motorista");
		jmiPesquisa = new JMenuItem("Pesquisa");jmiAbrirOrdem = new JMenuItem("Abrir Ordem");
		jmiFecharOrdem = new JMenuItem("Fechar Ordem");jmiConsultarOrdem = new JMenuItem("Consultar Ordem");
		jmiRelatTotalCli = new JMenuItem("Total Cliente");jmiRelatTotalMot = new JMenuItem("Total Motorista");
		jmiAjudas = new JMenuItem("Ajuda");

		setJMenuBar(barraMenu);
		/*
		 * Cria o menu Configurar
		 * Insere a opcao jmConfigurar seus sub itens, um separador e uma letra de atalho  
		 */
		barraMenu.add(jmConfigurar);
		jmConfigurar.setMnemonic(KeyEvent.VK_O);
		jmConfigurar.add(jmiParametros);jmiParametros.setEnabled(false);
		jmiParametros.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
		jmConfigurar.add(jmiRefAcesso);jmiRefAcesso.setEnabled(false);
		jmiRefAcesso.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
		jmConfigurar.addSeparator();
		jmConfigurar.add(jmiSair);jmiSair.setEnabled(false);
		/*
		 * Cria o menu Cadastrar
		 * Insere a opcao jmCadastrar seus sub itens, um separador e uma letra de atalho  
		 */
		barraMenu.add(jmCadastrar);jmCadastrar.setMnemonic(KeyEvent.VK_D);
		jmCadastrar.add(jmiCliente);jmiCliente.setEnabled(false);
		jmiCliente.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));		
		jmCadastrar.add(jmiMotorista);jmiMotorista.setEnabled(false);
		jmiMotorista.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK));		
		jmCadastrar.add(jmiCadUsuario);jmiCadUsuario.setEnabled(false);
		jmiCadUsuario.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, ActionEvent.CTRL_MASK));
		jmCadastrar.addSeparator();
		jmCadastrar.add(jmiPesquisa);jmiPesquisa.setEnabled(false);
		jmiPesquisa.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
		/*
		 * Cria o menu Operacional
		 * Insere a opcao jmOperacional seus sub itens, um separador e uma letra de atalho  
		 */
		barraMenu.add(jmOperacional);jmOperacional.setMnemonic(KeyEvent.VK_O);
		jmOperacional.add(jmiAbrirOrdem);jmiAbrirOrdem.setEnabled(false);
		jmiAbrirOrdem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,ActionEvent.CTRL_MASK));
		jmOperacional.add(jmiFecharOrdem);jmiFecharOrdem.setEnabled(false);
		jmiFecharOrdem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,ActionEvent.CTRL_MASK));
		jmOperacional.addSeparator();
		jmOperacional.add(jmiConsultarOrdem);jmiConsultarOrdem.setEnabled(false);
		jmiConsultarOrdem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
		/*
		 * Cria o menu Relatorio
		 * Insere a opcao jmRelatorio seus sub itens, um separador e uma letra de atalho  
		 */
		barraMenu.add(jmRelatorio);jmRelatorio.setMnemonic(KeyEvent.VK_R);
		jmRelatorio.add(jmiRelatTotalCli);jmiRelatTotalCli.setEnabled(false);
		jmiRelatTotalCli.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W,ActionEvent.CTRL_MASK));		
		jmRelatorio.add(jmiRelatTotalMot);jmiRelatTotalMot.setEnabled(false);
		jmiRelatTotalMot.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK));
		/*
		 * Cria o menu Ajuda
		 * Insere a opcao jmAjuda seus sub itens, um separador e uma letra de atalho  
		 */
		barraMenu.add(jmAjuda);jmAjuda.setMnemonic(KeyEvent.VK_A);
		jmAjuda.add(jmiAjudas);jmiAjudas.setEnabled(false);
		/*
		 * Cria o menu Sobre  
		 */
		barraMenu.add(jmSobre);jmSobre.setMnemonic(KeyEvent.VK_S);
		/*
		 * Controla as acoes do sub itens do menu jmConfigurar
		 */
		jmiParametros.addActionListener(this);jmiCadUsuario.addActionListener(this);jmiRefAcesso.addActionListener(this);
		jmiSair.addActionListener(this);
		/*
		 * Controla as acoes do sub itens do menu cadastro
		 */
		jmConfigurar.addMouseListener(this);jmCadastrar.addMouseListener(this);jmOperacional.addMouseListener(this);jmRelatorio.addMouseListener(this);
		jmiCliente.addActionListener(this);jmiMotorista.addActionListener(this);jmiPesquisa.addActionListener(this);
		jmiAbrirOrdem.addActionListener(this);jmiFecharOrdem.addActionListener(this);jmiConsultarOrdem.addActionListener(this);
		jmiRelatTotalCli.addActionListener(this);jmiRelatTotalMot.addActionListener(this);
		jmiAjudas.addActionListener(this);jmAjuda.addMouseListener(this);jmSobre.addActionListener(this);jmSobre.addMouseListener(this);

		//Inicia a classe de autenticacao do aplicativo
		autenticacaoVO = new AutenticacaoVO();

	}
	
	public void mouseClicked(MouseEvent e){
	}
	public void mouseEntered(MouseEvent e){
	}
	public void mouseExited(MouseEvent e){
	}
	public void mouseReleased(MouseEvent e){
	}
	public void mousePressed(MouseEvent e){
		if(e.getSource() == jmSobre){
			SobreView.sobre();
		}

		if(e.getSource() == jmConfigurar || e.getSource() == jmCadastrar || e.getSource() == jmOperacional || e.getSource() == jmRelatorio || e.getSource() == jmAjuda || e.getSource() == jmSobre){
			CadUsuariosVO usuariosVO = new CadUsuariosVO();
			AutenticacaoView autenticacao = new AutenticacaoView();
			autenticacao.controlaUsuario(usuariosVO);
			usuariosVO.getTipoUsuario();
			usuariosVO.getStatus();
			if(usuariosVO.getTipoUsuarioLogin().equals("ADMINISTRATOR") && usuariosVO.getStatusLogin().equals("HABILITADO")){
				jmiParametros.setVisible(false);jmiParametros.setEnabled(false);jmiParametros.setVisible(false);jmiRefAcesso.setEnabled(true);
				jmiSair.setEnabled(true);
				jmiCliente.setEnabled(true);jmiMotorista.setEnabled(true);jmiCadUsuario.setEnabled(true);
				jmiPesquisa.setEnabled(true);
				jmiAbrirOrdem.setEnabled(true);jmiFecharOrdem.setEnabled(true);jmiConsultarOrdem.setEnabled(true);
				jmiRelatTotalCli.setEnabled(true);jmiRelatTotalMot.setEnabled(true);
				jmiAjudas.setEnabled(true);
				jmSobre.setEnabled(true);
			}
			if(usuariosVO.getTipoUsuarioLogin().equals("GERENCIAL") && usuariosVO.getStatusLogin().equals("HABILITADO")){
				jmiParametros.setVisible(false);jmiParametros.setEnabled(false);jmiParametros.setVisible(false);jmiRefAcesso.setEnabled(true);
				jmiSair.setEnabled(true);
				jmiCliente.setEnabled(true);jmiMotorista.setEnabled(true);jmiCadUsuario.setEnabled(false);
				jmiPesquisa.setEnabled(true);
				jmiAbrirOrdem.setEnabled(true);jmiFecharOrdem.setEnabled(true);jmiConsultarOrdem.setEnabled(true);
				jmiRelatTotalCli.setEnabled(true);jmiRelatTotalMot.setEnabled(true);
				jmiAjudas.setEnabled(true);
				jmSobre.setEnabled(true);
			}
			if(usuariosVO.getTipoUsuarioLogin().equals("USUARIO") && usuariosVO.getStatusLogin().equals("HABILITADO")){
				jmiParametros.setVisible(false);jmiParametros.setEnabled(false);jmiParametros.setVisible(false);jmiRefAcesso.setEnabled(true);
				jmiSair.setEnabled(true);
				jmiCliente.setEnabled(true);jmiMotorista.setEnabled(true);jmiCadUsuario.setEnabled(false);
				jmiPesquisa.setEnabled(true);
				jmiAbrirOrdem.setEnabled(true);jmiFecharOrdem.setEnabled(true);jmiConsultarOrdem.setEnabled(true);
				jmiRelatTotalCli.setEnabled(true);jmiRelatTotalMot.setEnabled(true);
				jmiAjudas.setEnabled(true);
				jmSobre.setEnabled(true);
			}
 			if(usuariosVO.getTipoUsuarioLogin().equals("DEVELOPERS") && usuariosVO.getStatusLogin().equals("HABILITADO")){
				jmiParametros.setVisible(true);jmiParametros.setEnabled(true);jmiRefAcesso.setEnabled(true);
				jmiSair.setEnabled(true);jmiCliente.setEnabled(true);jmiMotorista.setEnabled(true);
				jmiCadUsuario.setEnabled(true);jmiPesquisa.setEnabled(true);jmiAbrirOrdem.setEnabled(true);
				jmiFecharOrdem.setEnabled(true);jmiConsultarOrdem.setEnabled(true);jmiRelatTotalCli.setEnabled(true);
				jmiRelatTotalMot.setEnabled(true);jmiAjudas.setEnabled(true);jmSobre.setEnabled(true);
			}
		}
	}

	public void actionPerformed(ActionEvent e){
		
		if(e.getSource() == jmiAjudas){
			try {
				LerArquivo lerArquivo = new LerArquivo();
				LerArquivoVO lerArquivoVO = new LerArquivoVO();
				lerArquivoVO = lerArquivo.getInformacao(lerArquivoVO);
				
				//URL url = new URL("file:"+ajuda_arq+"help.hs" );
				URL url = new URL("file:"+caminho+separadorPath+lerArquivoVO.getAjudaArq()+separadorPath+"help.hs" );
				HelpSet hs = new  HelpSet(null, url);			
				JHelp help = new JHelp(hs);
				frameHelp = new JFrame();
				frameHelp.setContentPane(help);
				frameHelp.setTitle("Ajuda - ControlMoto");
				frameHelp.setSize(600,500);
				frameHelp.setVisible(true);
				//Caminho producao
				//ImageIcon icone = new ImageIcon(caminho+separadorPath+logo_tipo+"logo_cs_b.gif");
				ImageIcon icone = new ImageIcon(lerArquivoVO.getLogoTipo()+separadorPath+"logo_cs_b.gif");
				//Caminho local para o Eclipse
				//ImageIcon icone = new ImageIcon(caminho+logo_tipo+"logo_cs_b.gif");
				frameHelp.setIconImage(icone.getImage());
			 } catch  (Exception ex) {
				 JOptionPane.showMessageDialog(null,ex.getMessage()+"\n"+"Erro ao ler informações do sistema","ControlMoto",JOptionPane.INFORMATION_MESSAGE);
//					ex.printStackTrace();
		 		}
		} 
		if (e.getSource() == jmiParametros){
				if(telaCadEmpresa == null)
					telaCadEmpresa = new CadEmpresaView("Cadastro de Empresa", this);
				jDesktopPane.moveToFront(telaCadEmpresa);
				telaCadEmpresa = null;
		}
		if(e.getSource() == jmiCadUsuario){
			if(telaCadUsuarios == null)
				telaCadUsuarios = new CadUsuariosView("Cadastro de Usuários", this);
			jDesktopPane.moveToFront(telaCadUsuarios);
			telaCadUsuarios = null;
		}
		if(e.getSource() == jmiRefAcesso){
			AutenticacaoView.autenticacao();
		}
		if (e.getSource()== jmiCliente){
			if(telaCadCliente == null)
				telaCadCliente = new CadClienteView("Cadastro de Cliente", this);
			jDesktopPane.moveToFront(telaCadCliente);
			telaCadCliente = null;
		}
		if (e.getSource()== jmiMotorista){
			if(telaCadMotorista == null)
				telaCadMotorista = new CadMotoristaView("Cadastro de Motorista", this);
			jDesktopPane.moveToFront(telaCadMotorista);
			telaCadMotorista = null;
		}
		if (e.getSource()== jmiPesquisa){
			if(telaTabConsulta == null)
				telaTabConsulta = new TabConsultaView("Tela de Pesquisa", this);
			jDesktopPane.moveToFront(telaTabConsulta);
			telaTabConsulta = null;
		}
		if (e.getSource() == jmiAbrirOrdem){
			if(telaAbrirOrdem == null)
				telaAbrirOrdem = new AbrirOrdensView("Abrir Ordem de Serviço", this);
			jDesktopPane.moveToFront(telaAbrirOrdem);
			telaAbrirOrdem = null;
		}
		if (e.getSource()== jmiFecharOrdem){
			if(telaFecharOrdem == null)
				telaFecharOrdem = new FecharOrdemView("Fechar Ordem de Serviço", this);
			jDesktopPane.moveToFront(telaFecharOrdem);
			telaFecharOrdem = null;
		}
		if (e.getSource()== jmiConsultarOrdem){
			if(telaTabConsultaOrdens == null)
				telaTabConsultaOrdens = new TabConsultaOrdensView("Consultar Ordem de Serviço", this);
			jDesktopPane.moveToFront(telaTabConsultaOrdens);
			telaTabConsultaOrdens = null;
		}
		if (e.getSource() == jmiRelatTotalCli){
			if(telaPesqRelatClie == null)
				telaPesqRelatClie = new PesqRelatClieView("Relatórios de Cliente", this);
			jDesktopPane.moveToFront(telaPesqRelatClie);
			telaPesqRelatClie = null;
		}
		if (e.getSource() == jmiRelatTotalMot){
			if(telaPesqRelatMot == null)
				telaPesqRelatMot = new PesqRelatMotView("Relatorios de Motorista",this);
			jDesktopPane.moveToFront(telaPesqRelatMot);
			telaPesqRelatMot = null;
		}
		if (e.getSource()== jmiSair){
			System.exit(0);
		}
	}

	public JMenuItem getjmiPesquisa(){
		return jmiPesquisa ;
	}
	public String getRecNameControl() {
		return recNameControl;
	}
	public void setRecNameControl(String recNameControl) {
		this.recNameControl = recNameControl;
	}
	public String getRecSenhaControl() {
		return recSenhaControl;
	}
	public void setRecSenhaControl(String recSenhaControl) {
		this.recSenhaControl = recSenhaControl;
	}
	public String getRecStatusControl() {
		return recStatusControl;
	}
	public void setRecStatusControl(String recStatusControl) {
		this.recStatusControl = recStatusControl;
	}
	public String getRecTipoUsuarioControl() {
		return recTipoUsuarioControl;
	}
	public void setRecTipoUsuarioControl(String recTipoUsuarioControl) {
		this.recTipoUsuarioControl = recTipoUsuarioControl;
	}
	
	public void verificarUser(){
		CadUsuariosVO usuariosVO = new CadUsuariosVO();
		AutenticacaoView autenticacao = new AutenticacaoView();
		autenticacao.controlaUsuario(usuariosVO);
		usuariosVO.getTipoUsuario();
		usuariosVO.getStatus();
		if(usuariosVO.getTipoUsuarioLogin().equals("ADMINISTRATOR") && usuariosVO.getStatusLogin().equals("HABILITADO")){
			jmiParametros.setEnabled(false);jmiParametros.setVisible(false);jmiRefAcesso.setEnabled(true);
			jmiSair.setEnabled(true);jmiCliente.setEnabled(true);jmiMotorista.setEnabled(true);
			jmiCadUsuario.setEnabled(true);jmiPesquisa.setEnabled(true);jmiAbrirOrdem.setEnabled(true);
			jmiFecharOrdem.setEnabled(true);jmiConsultarOrdem.setEnabled(true);jmiRelatTotalCli.setEnabled(true);
			jmiRelatTotalMot.setEnabled(true);jmiAjudas.setEnabled(true);jmSobre.setEnabled(true);
		}
		if(usuariosVO.getTipoUsuarioLogin().equals("GERENCIAL") && usuariosVO.getStatusLogin().equals("HABILITADO")){
			jmiParametros.setEnabled(false);jmiParametros.setVisible(false);jmiRefAcesso.setEnabled(true);
			jmiSair.setEnabled(true);jmiCliente.setEnabled(true);jmiMotorista.setEnabled(true);
			jmiCadUsuario.setEnabled(false);jmiPesquisa.setEnabled(true);jmiAbrirOrdem.setEnabled(true);
			jmiFecharOrdem.setEnabled(true);jmiConsultarOrdem.setEnabled(true);jmiRelatTotalCli.setEnabled(true);
			jmiRelatTotalMot.setEnabled(true);jmiAjudas.setEnabled(true);jmSobre.setEnabled(true);
		}
		if(usuariosVO.getTipoUsuarioLogin().equals("USUARIO") && usuariosVO.getStatusLogin().equals("HABILITADO")){
			jmiParametros.setEnabled(false);jmiParametros.setVisible(false);jmiRefAcesso.setEnabled(true);
			jmiSair.setEnabled(true);jmiCliente.setEnabled(true);jmiMotorista.setEnabled(true);
			jmiCadUsuario.setEnabled(false);jmiPesquisa.setEnabled(true);jmiAbrirOrdem.setEnabled(true);
			jmiFecharOrdem.setEnabled(true);jmiConsultarOrdem.setEnabled(true);jmiRelatTotalCli.setEnabled(true);
			jmiRelatTotalMot.setEnabled(true);jmiAjudas.setEnabled(true);jmSobre.setEnabled(true);
		}
		if(usuariosVO.getTipoUsuarioLogin().equals("DEVELOPERS") && usuariosVO.getStatusLogin().equals("HABILITADO")){
			jmiParametros.setEnabled(true);jmiParametros.setVisible(true);jmiRefAcesso.setEnabled(true);
			jmiSair.setEnabled(true);jmiCliente.setEnabled(true);jmiMotorista.setEnabled(true);
			jmiCadUsuario.setEnabled(true);jmiPesquisa.setEnabled(true);jmiAbrirOrdem.setEnabled(true);
			jmiFecharOrdem.setEnabled(true);jmiConsultarOrdem.setEnabled(true);jmiRelatTotalCli.setEnabled(true);
			jmiRelatTotalMot.setEnabled(true);jmiAjudas.setEnabled(true);jmSobre.setEnabled(true);
		}
	}
	
}
