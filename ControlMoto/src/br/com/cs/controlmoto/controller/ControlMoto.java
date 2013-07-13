package br.com.cs.controlmoto.controller;

import java.awt.BorderLayout;
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
import java.beans.PropertyVetoException;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.help.HelpSet;
import javax.help.JHelp;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;

import br.com.cs.controlmoto.utils.LerArquivo;
import br.com.cs.controlmoto.vo.AutenticacaoVO;
import br.com.cs.controlmoto.vo.CadUsuariosVO;
import br.com.cs.controlmoto.vo.LerArquivoVO;

/**
 * Company Fictec Cons. Inf.
 * @since 06/09/2012
 * @version 1.0.6
 * @author Clovis
 */

public class ControlMoto extends javax.swing.JFrame implements ActionListener, MouseListener {
	
	/**
	 * @
	 */
	private static final long serialVersionUID = -5773897156310280097L;
	
	JToolBar	jtbBarraFeramentas;
	JMenuBar	barraMenu;
	JMenu		jmConfigurar, jmCadastrar, jmOperacional, jmFinanceiro, jmRelatorio, jmAjuda, jmSobre;
	JMenuItem	jmiCadUsuario, jmiRefAcesso, jmiSair, jmiCliente, jmiMotorista, jmiPesquisa, jmiAbrirOrdem, jmiFecharOrdem,
				jmiConsultarOrdem, jmiFaturaOrdem, jmiGerarRpsFatura, jmiRelatTotalCli, jmiRelatTotalMot, jmiAjudas,
				jmiParametros, jmiContasAPagar, jmiContasReceber, jmiCadFornecedor, jmiRelatFinanceiro, jmiCadVlCombustível,
				jmiTabAgenda, jmiCadTarefa, jmiCadTabelaPreco;
	JButton		btoImprimir;
	JFrame		frameHelp;
	JPanel		jpMenus;
	static JDesktopPane jDesktopPane;
	ImageIcon icone;
	static Connection minhaConexao;
	static Statement minhaSTM;
	static ResultSet rs;
	Dimension centraliza = Toolkit.getDefaultToolkit().getScreenSize();
	String SQL, caminho, separadorPath="", logo_tipo, sobre_img, ajuda_arq;
	
	protected java.util.Properties props;
//	private javax.swing.JDesktopPane desktopPane;
	static CadUsuarios	telaCadUsuarios;
	static CadEmpresa	telaCadEmpresa;
	static CadCliente	telaCadCliente;
	static CadMotorista	telaCadMotorista;
	static AbrirOrdens	telaAbrirOrdem;
	static FecharOrdem	telaFecharOrdem;
	static PesqRelatClie telaPesqRelatClie;
	static PesqRelatMot	telaPesqRelatMot;
	static TabConsulta	telaTabConsulta;
	static TabConsultaOrdens telaTabConsultaOrdens;
	static TabFaturaOrdens telaTabFaturaOrdens;
	static TabGerarRpsFatura telaTabGerarRpsFatura;
//    static EspecieDocumento telaRelatorio;
    static RelatFinaceiro jifTelaRelatorio;
    static ContasPagar jifTelaContasPagar;
    static ContasReceber jifTelaContasReceber;
//    static FormaPagto jifTelaFormaPagto;
//    static EspecieDocumento jifTelaEspecieDocumento;
    static CadCredor jifTelaCadCredor;
//    static CadDevedor jifTelaCadDevedor;
    static CadFornecedor jifTelaCadFornecedor;
    static CadVlCombustivel jifTelaCadVlCombustivel;
    static TabAgenda jifTelaTabAgenda;
    static CadAgendamento jifTelaCadAgendamento;
    static CadTarefa jifTelaCadTarefa;
    static CadTabelaPreco jifTelaCadTabelaPreco;
    
	private String recNameControl = "";
	private String recSenhaControl = "";
	private String recStatusControl = "";
	private String recTipoUsuarioControl = "";
	public AutenticacaoVO autenticacaoVO;

	public static void main(String [] args) {
		controlMoto();
	}

	public static void controlMoto(){
		JFrame controlMoto = new ControlMoto();
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
				Autenticacao.autenticacao();
			}		
			public void windowClosing(WindowEvent e){
				System.exit(0);}
		}; controlMoto.addWindowListener(x);
	}

	public ControlMoto(){
		separadorPath = System.getProperty("file.separator");
		caminho = new File("").getAbsolutePath();
		jDesktopPane = new JDesktopPane();
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
			//JOptionPane.showMessageDialog(this, e.getMessage()+"\n"+"Erro ao ler informaÃ§Ãµes do sistema","ControlMoto",JOptionPane.INFORMATION_MESSAGE);
		//}

		//JOptionPane.showMessageDialog(null, ""+file +"\n"+ sobre_img );
		LerArquivo lerArquivo = new LerArquivo();
		LerArquivoVO lerArquivoVO = new LerArquivoVO();
		lerArquivoVO = lerArquivo.getInformacao(lerArquivoVO);
		
		jpMenus = new JPanel();
		getContentPane().setLayout(new BorderLayout());
		jpMenus.setLayout(new BorderLayout());
		getContentPane().add(jDesktopPane, BorderLayout.CENTER);
		
		//setSize(centraliza.width,centraliza.height);
		setExtendedState(MAXIMIZED_BOTH);
		setTitle("ControlMoto - Seu sistema de controle !!!");
		//Icone da tela pequeno - Caminho Funciona no Eclipse
		//icone = new ImageIcon(caminho+separadorPath+logo_tipo+"logo_cs_b.gif");
		icone = new ImageIcon(caminho+separadorPath+lerArquivoVO.getLogoTipo()+separadorPath+"lg_fictec_gr.png");//logo_cs_b.gif
		setIconImage(icone.getImage());
		
		btoImprimir = new JButton(); btoImprimir.addActionListener(this); btoImprimir.setToolTipText("Relatório");
		btoImprimir.setIcon(new ImageIcon(caminho+separadorPath+lerArquivoVO.getLogoTipo()+separadorPath+"icone"+separadorPath+"11619_16x16.png"));
		
		jtbBarraFeramentas = new JToolBar();
		jtbBarraFeramentas.setPreferredSize(new Dimension(jDesktopPane.getWidth(), 30));
		jtbBarraFeramentas.setBorderPainted(true); jtbBarraFeramentas.addSeparator();
		jtbBarraFeramentas.add(btoImprimir);
		
		barraMenu = new JMenuBar();

		jmConfigurar = new JMenu("Configurar"); jmCadastrar = new JMenu("Cadastrar"); jmOperacional = new JMenu("Operacional");
		jmRelatorio = new JMenu("Relatórios");jmFinanceiro = new JMenu("Financeiro");jmAjuda = new JMenu("Ajuda");jmSobre = new JMenu("Sobre");

		jmiParametros = new JMenuItem("Parâmetros");jmiCadUsuario = new JMenuItem("Usuário");
		jmiRefAcesso = new JMenuItem("Refazer Acesso");jmiSair = new JMenuItem("Sair");
		jmiCliente = new JMenuItem("Cliente");jmiMotorista = new JMenuItem("Motorista");
		jmiPesquisa = new JMenuItem("Pesquisa");jmiAbrirOrdem = new JMenuItem("Abrir Ordem");
		jmiFecharOrdem = new JMenuItem("Fechar Ordem");jmiConsultarOrdem = new JMenuItem("Consultar Ordem");
		jmiFaturaOrdem = new JMenuItem("Faturar Ordem");jmiRelatTotalCli = new JMenuItem("Total Cliente");
		jmiRelatTotalMot = new JMenuItem("Total Motorista");jmiAjudas = new JMenuItem("Ajuda");
		jmiGerarRpsFatura = new JMenuItem("Gerar RPS"); jmiTabAgenda = new JMenuItem("Agenda");
		jmiContasAPagar = new JMenuItem("Contas a Pagar"); jmiContasReceber = new JMenuItem("Contas a Receber");
		jmiCadFornecedor = new JMenuItem("Fornecedor");jmiRelatFinanceiro = new JMenuItem("Financeiro");
		jmiCadVlCombustível = new JMenuItem("Solicitar Vale"); jmiCadTarefa = new JMenuItem("Tarefas");
		jmiCadTabelaPreco = new JMenuItem("Tabela Preço");
		
		setJMenuBar(barraMenu);
		jpMenus.add(jtbBarraFeramentas, BorderLayout.PAGE_START);
		getContentPane().add(jpMenus, BorderLayout.PAGE_START);
		
//		getContentPane().add(jtbBarraFeramentas, BorderLayout.PAGE_START);
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
		barraMenu.add(jmCadastrar); jmCadastrar.setMnemonic(KeyEvent.VK_D);
		jmCadastrar.add(jmiCadTabelaPreco); jmiCadTabelaPreco.setEnabled(false);
		jmiCadTabelaPreco.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.CTRL_MASK));
		jmCadastrar.addSeparator();
		jmCadastrar.add(jmiCliente); jmiCliente.setEnabled(false);
		jmiCliente.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));		
		jmCadastrar.add(jmiCadFornecedor); jmiCadFornecedor.setEnabled(false);
		jmiCadFornecedor.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		jmCadastrar.add(jmiMotorista); jmiMotorista.setEnabled(false);
		jmiMotorista.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK));		
		jmCadastrar.add(jmiCadUsuario); jmiCadUsuario.setEnabled(false);
		jmiCadUsuario.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, ActionEvent.CTRL_MASK));
		jmCadastrar.addSeparator();
		jmCadastrar.add(jmiCadTarefa); jmiCadTarefa.setEnabled(false);
		jmiCadTarefa.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, ActionEvent.CTRL_MASK));
		jmCadastrar.addSeparator();
		jmCadastrar.add(jmiPesquisa); jmiPesquisa.setEnabled(false);
		jmiPesquisa.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
		/*
		 * Cria o menu Operacional
		 * Insere a opcao jmOperacional seus sub itens, um separador e uma letra de atalho  
		 */
		barraMenu.add(jmOperacional);jmOperacional.setMnemonic(KeyEvent.VK_O);
		jmOperacional.add(jmiTabAgenda); jmiTabAgenda.setEnabled(false);
		jmiTabAgenda.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		jmOperacional.addSeparator();
		jmOperacional.add(jmiAbrirOrdem);jmiAbrirOrdem.setEnabled(false);
		jmiAbrirOrdem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,ActionEvent.CTRL_MASK));
		jmOperacional.add(jmiFecharOrdem);jmiFecharOrdem.setEnabled(false);
		jmiFecharOrdem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,ActionEvent.CTRL_MASK));
		jmOperacional.addSeparator();
		jmOperacional.add(jmiFaturaOrdem);jmiFaturaOrdem.setEnabled(false);
		jmiFaturaOrdem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, ActionEvent.CTRL_MASK));
		jmOperacional.add(jmiGerarRpsFatura);jmiGerarRpsFatura.setEnabled(false);
		jmiGerarRpsFatura.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, ActionEvent.CTRL_MASK));
		jmOperacional.addSeparator();
		jmOperacional.add(jmiCadVlCombustível);jmiCadVlCombustível.setEnabled(false);
		jmiCadVlCombustível.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
		jmOperacional.addSeparator();
		jmOperacional.add(jmiConsultarOrdem);jmiConsultarOrdem.setEnabled(false);
		jmiConsultarOrdem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
		/*
		 * Cria o menu Financeiro
		 * Insere a opcao jmFinanceiro seus sub itens, um separador e uma letra de atalho  
		 */
		barraMenu.add(jmFinanceiro);jmFinanceiro.setMnemonic(KeyEvent.VK_F);
		jmFinanceiro.add(jmiContasAPagar);jmiContasAPagar.setEnabled(true);
		jmiContasAPagar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
		jmFinanceiro.add(jmiContasReceber);jmiContasReceber.setEnabled(true);
		jmiContasReceber.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
		/*
		 * Cria o menu Relatorio
		 * Insere a opcao jmRelatorio seus sub itens, um separador e uma letra de atalho  
		 */
		barraMenu.add(jmRelatorio);jmRelatorio.setMnemonic(KeyEvent.VK_R);
		jmRelatorio.add(jmiRelatTotalCli);jmiRelatTotalCli.setEnabled(false);
		jmiRelatTotalCli.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W,ActionEvent.CTRL_MASK));		
		jmRelatorio.add(jmiRelatTotalMot);jmiRelatTotalMot.setEnabled(false);
		jmiRelatTotalMot.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK));
		jmRelatorio.add(jmiRelatFinanceiro);jmiRelatFinanceiro.setEnabled(false);
		jmiRelatFinanceiro.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
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
		jmiContasAPagar.addActionListener(this);jmiContasReceber.addActionListener(this);jmiSair.addActionListener(this);
		/*
		 * Controla as acoes do sub itens do menu cadastro
		 */
		jmConfigurar.addMouseListener(this);jmCadastrar.addMouseListener(this);jmOperacional.addMouseListener(this);
		jmRelatorio.addMouseListener(this);jmiCliente.addActionListener(this);jmiMotorista.addActionListener(this);
		jmiPesquisa.addActionListener(this);jmiAbrirOrdem.addActionListener(this);jmiFecharOrdem.addActionListener(this);
		jmiConsultarOrdem.addActionListener(this);jmiRelatTotalCli.addActionListener(this);jmiRelatTotalMot.addActionListener(this);
		jmiFaturaOrdem.addActionListener(this);jmiGerarRpsFatura.addActionListener(this);jmiAjudas.addActionListener(this);
		jmiCadFornecedor.addActionListener(this);jmiRelatFinanceiro.addActionListener(this);jmiRelatFinanceiro.addActionListener(this);
		jmAjuda.addMouseListener(this);jmSobre.addActionListener(this);jmSobre.addMouseListener(this);
		jmiCadVlCombustível.addActionListener(this); jmiTabAgenda.addActionListener(this); jmiCadTarefa.addActionListener(this);
		jmiCadTabelaPreco.addActionListener(this); 
		
		//Inicia o servidor do banco de dados
		//HsqlServerDb.startHsqlServer();
		
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
			Sobre.sobre();
		}

		if(e.getSource() == jmConfigurar || e.getSource() == jmCadastrar || e.getSource() == jmOperacional || e.getSource() == jmRelatorio || e.getSource() == jmAjuda || e.getSource() == jmSobre){
			CadUsuariosVO usuariosVO = new CadUsuariosVO();
			Autenticacao autenticacao = new Autenticacao();
			autenticacao.controlaUsuario(usuariosVO);
			usuariosVO.getTipoUsuario();
			usuariosVO.getStatus();
			if(usuariosVO.getTipoUsuarioLogin().equals("ADMINISTRADOR") && usuariosVO.getStatusLogin().equals("HABILITADO")){
				jmiParametros.setVisible(false);jmiParametros.setEnabled(false);jmiParametros.setVisible(false);jmiRefAcesso.setEnabled(true);
				jmiSair.setEnabled(true);
				jmiCliente.setEnabled(true);jmiMotorista.setEnabled(true);jmiCadUsuario.setEnabled(true);jmiCadFornecedor.setEnabled(true);
				jmiPesquisa.setEnabled(true);
				jmiAbrirOrdem.setEnabled(true);jmiFecharOrdem.setEnabled(true);jmiFaturaOrdem.setEnabled(true);jmiConsultarOrdem.setEnabled(true);
				jmiGerarRpsFatura.setEnabled(true);
				jmiRelatTotalCli.setEnabled(true); jmiRelatTotalMot.setEnabled(true);jmiRelatFinanceiro.setEnabled(true);
				jmiAjudas.setEnabled(true);	jmSobre.setEnabled(true); jmiCadVlCombustível.setEnabled(true);
				jmiTabAgenda.setEnabled(true); jmiCadTarefa.setEnabled(true); jmiCadTabelaPreco.setEnabled(true);
			}
			if(usuariosVO.getTipoUsuarioLogin().equals("GERENCIAL") && usuariosVO.getStatusLogin().equals("HABILITADO")){
				jmiParametros.setVisible(false); jmiParametros.setEnabled(false); jmiParametros.setVisible(false);
				jmiRefAcesso.setEnabled(true); jmiSair.setEnabled(true);
				jmiCliente.setEnabled(true); jmiMotorista.setEnabled(true); jmiCadUsuario.setEnabled(false);jmiCadFornecedor.setEnabled(true);
				jmiPesquisa.setEnabled(true);
				jmiAbrirOrdem.setEnabled(true); jmiFecharOrdem.setEnabled(true); jmiFaturaOrdem.setEnabled(true);
				jmiConsultarOrdem.setEnabled(true); jmiGerarRpsFatura.setEnabled(true);
				jmiRelatTotalCli.setEnabled(true); jmiRelatTotalMot.setEnabled(true); jmiRelatFinanceiro.setEnabled(true);
				jmiAjudas.setEnabled(true); jmSobre.setEnabled(true); jmiCadVlCombustível.setEnabled(true);
				jmiTabAgenda.setEnabled(true); jmiCadTarefa.setEnabled(true); jmiCadTabelaPreco.setEnabled(true);
			}
			if(usuariosVO.getTipoUsuarioLogin().equals("USUARIO") && usuariosVO.getStatusLogin().equals("HABILITADO")){
				jmiParametros.setVisible(false); jmiParametros.setEnabled(false); jmiParametros.setVisible(false);
				jmiRefAcesso.setEnabled(true); jmiSair.setEnabled(true);
				jmiCliente.setEnabled(true); jmiMotorista.setEnabled(true); jmiCadUsuario.setEnabled(false); jmiCadFornecedor.setEnabled(true);
				jmiPesquisa.setEnabled(true);
				jmiAbrirOrdem.setEnabled(true); jmiFecharOrdem.setEnabled(true); jmiFaturaOrdem.setEnabled(true);
				jmiConsultarOrdem.setEnabled(true);	jmiGerarRpsFatura.setEnabled(true);
				jmiRelatTotalCli.setEnabled(true); jmiRelatTotalMot.setEnabled(true); jmiRelatFinanceiro.setEnabled(true);
				jmiAjudas.setEnabled(true); jmSobre.setEnabled(true); jmiCadVlCombustível.setEnabled(true);
				jmiTabAgenda.setEnabled(true); jmiCadTarefa.setEnabled(true); jmiCadTabelaPreco.setEnabled(false);
			}
 			if(usuariosVO.getTipoUsuarioLogin().equals("DEVELOPERS") && usuariosVO.getStatusLogin().equals("HABILITADO")){
				jmiParametros.setVisible(true); jmiParametros.setEnabled(true); jmiRefAcesso.setEnabled(true);
				jmiSair.setEnabled(true); jmiCliente.setEnabled(true); jmiMotorista.setEnabled(true); jmiCadFornecedor.setEnabled(true);
				jmiCadUsuario.setEnabled(true); jmiPesquisa.setEnabled(true); jmiAbrirOrdem.setEnabled(true);
				jmiFecharOrdem.setEnabled(true); jmiFaturaOrdem.setEnabled(true); jmiConsultarOrdem.setEnabled(true);
				jmiRelatTotalCli.setEnabled(true); jmiGerarRpsFatura.setEnabled(true); jmiRelatFinanceiro.setEnabled(true);
				jmiRelatTotalMot.setEnabled(true); jmiAjudas.setEnabled(true); jmSobre.setEnabled(true);
				jmiCadVlCombustível.setEnabled(true); jmiTabAgenda.setEnabled(true); jmiCadTarefa.setEnabled(true);
				jmiCadTabelaPreco.setEnabled(true);
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
				ImageIcon icone = new ImageIcon(caminho+separadorPath+lerArquivoVO.getLogoTipo()+separadorPath+"logo_cs_b.gif");
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
				telaCadEmpresa = new CadEmpresa("Cadastro de Empresa", this);
			jDesktopPane.moveToFront(telaCadEmpresa);
			telaCadEmpresa = null;
		}
		if(e.getSource() == jmiCadUsuario){
			if(telaCadUsuarios == null)
				telaCadUsuarios = new CadUsuarios("Cadastro de Usuários", this);
			jDesktopPane.moveToFront(telaCadUsuarios);
			telaCadUsuarios = null;
		}
		if(e.getSource() == jmiRefAcesso){
			Autenticacao.autenticacao();
		}
		if (e.getSource()== jmiCliente){
			if(telaCadCliente == null)
				telaCadCliente = new CadCliente("Cadastro de Cliente", this);
			jDesktopPane.moveToFront(telaCadCliente);
			telaCadCliente = null;
		}
		if (e.getSource()== jmiMotorista){
			if(telaCadMotorista == null)
				telaCadMotorista = new CadMotorista("Cadastro de Motorista", this);
			jDesktopPane.moveToFront(telaCadMotorista);
			telaCadMotorista = null;
		}
		if (e.getSource()== jmiCadFornecedor){
			if(jifTelaCadFornecedor == null)
				jifTelaCadFornecedor = new CadFornecedor("Cadastro de Fornecedor", this);
			jDesktopPane.moveToFront(jifTelaCadFornecedor);
			jifTelaCadFornecedor = null;
		}
		if (e.getSource()== jmiCadTarefa){
			if(jifTelaCadTarefa == null)
				jifTelaCadTarefa = new CadTarefa("Tarefa", this);
			jDesktopPane.moveToFront(jifTelaCadTarefa);
			jifTelaCadTarefa = null;
		}		
		if (e.getSource()== jmiPesquisa){
			if(telaTabConsulta == null)
				telaTabConsulta = new TabConsulta("Tela de Pesquisa", this);
			jDesktopPane.moveToFront(telaTabConsulta);
			telaTabConsulta = null;
		}
		if (e.getSource() == jmiAbrirOrdem){
			if(telaAbrirOrdem == null)
				telaAbrirOrdem = new AbrirOrdens("Abrir Ordem de Serviço", this);
			jDesktopPane.moveToFront(telaAbrirOrdem);
			telaAbrirOrdem = null;
		}
		if (e.getSource()== jmiFecharOrdem){
			if(telaFecharOrdem == null)
				telaFecharOrdem = new FecharOrdem("Fechar Ordem de Serviço", this);
			jDesktopPane.moveToFront(telaFecharOrdem);
			telaFecharOrdem = null;
		}
		if (e.getSource()== jmiFaturaOrdem){
			if(telaTabFaturaOrdens == null)
				telaTabFaturaOrdens = new TabFaturaOrdens("Faturamento", this);
			jDesktopPane.moveToFront(telaTabFaturaOrdens);
			telaTabFaturaOrdens = null;
		}
		if(e.getSource()== jmiGerarRpsFatura){
			if(telaTabGerarRpsFatura == null)
				telaTabGerarRpsFatura = new TabGerarRpsFatura("Gerar Rps", this);
			jDesktopPane.moveToFront(telaTabGerarRpsFatura);
			telaTabGerarRpsFatura = null;
			
		}
		if (e.getSource()== jmiConsultarOrdem){
			if(telaTabConsultaOrdens == null)
				telaTabConsultaOrdens = new TabConsultaOrdens("Consultar Ordem de Serviço", this);
			jDesktopPane.moveToFront(telaTabConsultaOrdens);
			telaTabConsultaOrdens = null;
		}
		if (e.getSource() == jmiRelatTotalCli){
			if(telaPesqRelatClie == null)
				telaPesqRelatClie = new PesqRelatClie("Relatórios de Cliente", this);
			jDesktopPane.moveToFront(telaPesqRelatClie);
			telaPesqRelatClie = null;
		}
		if (e.getSource() == jmiRelatTotalMot){
			if(telaPesqRelatMot == null)
				telaPesqRelatMot = new PesqRelatMot("Relatórios de Motorista", this);
			jDesktopPane.moveToFront(telaPesqRelatMot);
			telaPesqRelatMot = null;
		}
		if (e.getSource() == jmiContasAPagar){
	        if(jifTelaContasPagar==null)
	            jifTelaContasPagar = new ContasPagar("Contas a Pagar", this);
	        jDesktopPane.moveToFront(jifTelaContasPagar);
	        jifTelaContasPagar.setVisible(true);
	        jifTelaContasPagar = null;
		}
		if(e.getSource() == jmiContasReceber){
			if(jifTelaContasReceber == null)
				jifTelaContasReceber = new ContasReceber("Contas a Receber", this);
			jDesktopPane.moveToFront(jifTelaContasReceber);
			jifTelaContasReceber.setVisible(true);
			jifTelaContasReceber = null;			
		}
		if(e.getSource() == jmiRelatFinanceiro){
			if(jifTelaRelatorio == null)
				jifTelaRelatorio = new RelatFinaceiro("Relatórios do Financeiro", this);
			jDesktopPane.moveToFront(jifTelaRelatorio);
			jifTelaRelatorio.setVisible(true);
			jifTelaRelatorio = null;			
		}
		if(e.getSource() == jmiCadVlCombustível){
			if(jifTelaCadVlCombustivel == null )
				jifTelaCadVlCombustivel = new CadVlCombustivel("Solicitar Vale", this);
			jDesktopPane.moveToFront(jifTelaCadVlCombustivel);
			jifTelaCadVlCombustivel.setVisible(true);
			jifTelaCadVlCombustivel = null;
		}
		if(e.getSource() == jmiTabAgenda){
			if(jifTelaTabAgenda == null)
				jifTelaTabAgenda = new TabAgenda("Agenda", this);
			jDesktopPane.moveToFront(jifTelaTabAgenda);
			jifTelaTabAgenda.setVisible(true);
			jifTelaTabAgenda = null;
		}
		if(e.getSource() == jmiCadTabelaPreco){
			if(jifTelaCadTabelaPreco == null)
				jifTelaCadTabelaPreco = new CadTabelaPreco("Tabela de Preço", this);
			jDesktopPane.moveToFront(jifTelaCadTabelaPreco);
			jifTelaCadTabelaPreco.setVisible(true);
			jifTelaCadTabelaPreco = null;
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
		Autenticacao autenticacao = new Autenticacao();
		autenticacao.controlaUsuario(usuariosVO);
		usuariosVO.getTipoUsuario();
		usuariosVO.getStatus();
		if(usuariosVO.getTipoUsuarioLogin().equals("ADMINISTRATOR") && usuariosVO.getStatusLogin().equals("HABILITADO")){
			jmiParametros.setEnabled(false);jmiParametros.setVisible(false);jmiRefAcesso.setEnabled(true);
			jmiSair.setEnabled(true);jmiCliente.setEnabled(true);jmiMotorista.setEnabled(true);
			jmiCadUsuario.setEnabled(true);jmiPesquisa.setEnabled(true);jmiAbrirOrdem.setEnabled(true);
			jmiFecharOrdem.setEnabled(true);jmiFaturaOrdem.setEnabled(true);jmiConsultarOrdem.setEnabled(true);
			jmiRelatTotalCli.setEnabled(true);jmiGerarRpsFatura.setEnabled(true);jmiRelatFinanceiro.setEnabled(true);
			jmiRelatTotalMot.setEnabled(true);jmiAjudas.setEnabled(true);jmSobre.setEnabled(true);
		}
		if(usuariosVO.getTipoUsuarioLogin().equals("GERENCIAL") && usuariosVO.getStatusLogin().equals("HABILITADO")){
			jmiParametros.setEnabled(false);jmiParametros.setVisible(false);jmiRefAcesso.setEnabled(true);
			jmiSair.setEnabled(true);jmiCliente.setEnabled(true);jmiMotorista.setEnabled(true);
			jmiCadUsuario.setEnabled(false);jmiPesquisa.setEnabled(true);jmiAbrirOrdem.setEnabled(true);
			jmiFecharOrdem.setEnabled(true);jmiFaturaOrdem.setEnabled(true);jmiConsultarOrdem.setEnabled(true);
			jmiRelatTotalCli.setEnabled(true);jmiGerarRpsFatura.setEnabled(true);jmiRelatFinanceiro.setEnabled(true);
			jmiRelatTotalMot.setEnabled(true);jmiAjudas.setEnabled(true);jmSobre.setEnabled(true);
		}
		if(usuariosVO.getTipoUsuarioLogin().equals("USUARIO") && usuariosVO.getStatusLogin().equals("HABILITADO")){
			jmiParametros.setEnabled(false);jmiParametros.setVisible(false);jmiRefAcesso.setEnabled(true);
			jmiSair.setEnabled(true);jmiCliente.setEnabled(true);jmiMotorista.setEnabled(true);
			jmiCadUsuario.setEnabled(false);jmiPesquisa.setEnabled(true);jmiAbrirOrdem.setEnabled(true);
			jmiFecharOrdem.setEnabled(true);jmiFaturaOrdem.setEnabled(true);jmiConsultarOrdem.setEnabled(true);
			jmiRelatTotalCli.setEnabled(true);jmiGerarRpsFatura.setEnabled(true);jmiRelatFinanceiro.setEnabled(false);
			jmiRelatTotalMot.setEnabled(true);jmiAjudas.setEnabled(true);jmSobre.setEnabled(true);
		}
		if(usuariosVO.getTipoUsuarioLogin().equals("DEVELOPERS") && usuariosVO.getStatusLogin().equals("HABILITADO")){
			jmiParametros.setEnabled(true);jmiParametros.setVisible(true);jmiRefAcesso.setEnabled(true);
			jmiSair.setEnabled(true);jmiCliente.setEnabled(true);jmiMotorista.setEnabled(true);
			jmiCadUsuario.setEnabled(true);jmiPesquisa.setEnabled(true);jmiAbrirOrdem.setEnabled(true);
			jmiFecharOrdem.setEnabled(true);jmiFaturaOrdem.setEnabled(true);jmiConsultarOrdem.setEnabled(true);
			jmiRelatTotalCli.setEnabled(true);jmiGerarRpsFatura.setEnabled(true);jmiRelatFinanceiro.setEnabled(true);
			jmiRelatTotalMot.setEnabled(true);jmiAjudas.setEnabled(true);jmSobre.setEnabled(true);
		}
	}
	//UTILIZADO PARA ABRIR JINTERNALFRAME DE OUTRO JINTERNALFRAME
	public static void createInternalFrame(JInternalFrame janela, JDesktopPane localCriacao) {
		janela.setVisible(true);
		localCriacao.add(janela);
		localCriacao.moveToFront(janela);
		try {
			janela.setSelected(true);
		}catch (PropertyVetoException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null,"Erro ao criar janela.");
		}
	}
//    public void jbNovoAgendamentoActionPerformed(CadAgendamento cadAgendamento) {                                        
//        if(cadAgendamento == null)
//        	jifTelaCadAgendamento = new CadAgendamento("Agendamento", null);
//		ControlMoto.jifTelaCadAgendamento.jDesktopPane.moveToFront(jifTelaCadAgendamento);
//		jifTelaCadAgendamento.setVisible(true);
//		jifTelaCadAgendamento = null;
//     }
	
}
