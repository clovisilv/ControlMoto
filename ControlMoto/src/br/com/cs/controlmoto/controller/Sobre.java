/**
 * Company Fictec Cons. Inf.
 * @since 06/09/2012
 * @version 1.0.6
 * @author Clovis
 */
package br.com.cs.controlmoto.controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import br.com.cs.controlmoto.utils.LerArquivo;
import br.com.cs.controlmoto.vo.LerArquivoVO;

class Sobre extends JDialog implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5302479176506860511L;
	
	JLabel lblMsg1,lblMsg2,lblMsg3,lblMsg4,lblMsg5,lblMsg6,lblMsg7,lblMsg8,lblMsg9,lblMsg10,lblMsg11,lblImagem;
	JPanel jpBotao,jpImagem,jpTexto,jpTitulo;
	JButton btoOK;
	ImageIcon icone,icone_c;
	String caminho, caminhoR, separadorPath = "", sobre_img;
	java.util.Properties props;
	
	
	public Sobre(javax.swing.JFrame parent, boolean modal){
		super(parent, modal);
		initSobre();
		}
	
	public static void sobre(){
		JDialog Sobre = new Sobre(new javax.swing.JFrame(), true);
		Sobre.setVisible(true);
		}
	
	public void initSobre(){
		separadorPath = System.getProperty("file.separator");
		caminho = new File("").getAbsolutePath();
		/*Esta linha est√° funcionando localmente*/
		//File file = new File(caminho+separadorPath+"ControlMoto"+separadorPath+"resources"+separadorPath+"propertys.properties"); Antigo
		//CAMINHO CORRETO PARA FUNCIONAR NO JAR		
		//File file = new File(caminho+separadorPath+"ControlMoto"+separadorPath+"resources"+separadorPath+"propertys.properties");
		//CAMINHO CORRETO PARA FUNCIONAR NO ECLIPSE
//		File file = new File(caminho+separadorPath+"resources"+separadorPath+"propertys.properties");
//		File file = new File("./resources/propertys.properties");
//		props = new Properties();
//		FileInputStream fis = null;
//		try{
//			fis = new FileInputStream(file);
//			props.load(fis);
//			sobre_img = props.getProperty("sobre_img");
//			fis.close();			
//		}catch (IOException e) {
//			JOptionPane.showMessageDialog(null,"Erro ao ler informa√ß√µes do sistema","ControlMoto",JOptionPane.INFORMATION_MESSAGE);	
//		}
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("ControlMoto - Sobre");
		setSize(380,300);
		setResizable(false);
		setLocationRelativeTo(null);
		/*
		 * Le arquivo de configuracao do projeto 
		 */
		LerArquivoVO lerArquivoVO = new LerArquivoVO();
		LerArquivo lerArquivo = new LerArquivo();
		lerArquivoVO = lerArquivo.getInformacao(lerArquivoVO);
		
		//icone = new ImageIcon(sobre_img+"logo_cs_b.gif");
		icone = new ImageIcon(lerArquivoVO.getLogoTipo()+"lg_fictec_gr.png");//logo_cs_b.gif
		setIconImage(icone.getImage());
		//Imagem de dentro da tela
		//icone_c = new ImageIcon(sobre_img+"logo_cs_c.gif");
		icone_c = new ImageIcon(lerArquivoVO.getSobreImg()+separadorPath+"lg_fictec_gr.png");//logo_cs_c.gif
		Container sob = getContentPane();
		sob.setLayout(new BorderLayout(5,20));
				
		lblMsg1 = new JLabel("ControlMoto !");
		lblMsg1.setFont(new Font("Arial",Font.BOLD,20));lblMsg1.setForeground(Color.BLUE);
		lblMsg2 = new JLabel("… um programa para controle");
		lblMsg2.setFont(new Font("Arial",Font.HANGING_BASELINE,12));
		lblMsg3 = new JLabel("das rotinas de uma empresa");
		lblMsg3.setFont(new Font("Arial", Font.HANGING_BASELINE, 12));
		lblMsg4 = new JLabel("prestadora de serviÁo de motoboy.");
		lblMsg4.setFont(new Font("Arial",Font.HANGING_BASELINE, 12));
		lblMsg5 = new JLabel();lblMsg6 = new JLabel();lblMsg7 = new JLabel();
		lblMsg8 = new JLabel("------------------------------------",JLabel.RIGHT);
		lblMsg9 = new JLabel("Desenvolvido por:");
		lblMsg10 = new JLabel("Fictec Consultoria em Inform·tica");
		//lblMsg10 = new JLabel("Clovis Silverio");		
		lblMsg11 = new JLabel("Versao : 1.0.9 - 2013");
		lblImagem = new JLabel(icone_c);
		
		btoOK = new JButton("OK");
		btoOK.addActionListener(this);
		
		jpBotao = new JPanel();
		jpImagem = new JPanel();
		jpTexto = new JPanel();
		jpTitulo = new JPanel();
		
    	jpTitulo.add(lblMsg1);
    	jpTexto.add(lblMsg2);jpTexto.add(lblMsg3);jpTexto.add(lblMsg4);jpTexto.add(lblMsg5);
    	jpTexto.add(lblMsg6);jpTexto.add(lblMsg7);jpTexto.add(lblMsg8);jpTexto.add(lblMsg9);
    	jpTexto.add(lblMsg10);jpTexto.add(lblMsg11);
    	
    	jpImagem.add(lblImagem);
    	jpBotao.add(btoOK);
    	
    	sob.add(jpTitulo, BorderLayout.NORTH);
    	sob.add(jpTexto, BorderLayout.CENTER);
    	sob.add(jpImagem, BorderLayout.WEST);
    	sob.add(jpBotao, BorderLayout.SOUTH);
		
	}

	public void actionPerformed(ActionEvent c){
		if(c.getSource() == btoOK){
			dispose();
			}
		
	}

}
