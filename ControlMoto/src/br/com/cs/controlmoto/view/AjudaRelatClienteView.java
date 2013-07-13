package br.com.cs.controlmoto.view;

import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import br.com.cs.controlmoto.utils.LerArquivo;
import br.com.cs.controlmoto.vo.LerArquivoVO;

public class AjudaRelatClienteView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7017148200591347217L;

	JTextArea jtaAjuda;
	String separadorPath = "", caminho="";
	ImageIcon icone;

	/**
	 * @param args
	 */
	public void ajudaRelatCliente() {
		JFrame ajudaRelatCliente = new AjudaRelatClienteView();
		ajudaRelatCliente.setVisible(true);
	}

	public AjudaRelatClienteView() {
		setTitle("Ajuda");
		setSize(300, 400);
		setResizable(false);
		caminho = new File(".").getAbsolutePath();
		separadorPath = System.getProperty("file.separator");
		/*
		 * Lê arquivo de configuração do projeto
		 */
		LerArquivoVO lerArquivoVO = new LerArquivoVO();
		LerArquivo lerArquivo = new LerArquivo();
		lerArquivoVO = lerArquivo.getInformacao(lerArquivoVO);
		
		//icone = new ImageIcon(caminho+separadorPath+separadorPath+"ControlMoto"+separadorPath+"imagens"+separadorPath+"logo_cs_b.gif");
		icone = new ImageIcon(lerArquivoVO.getLogoTipo()+separadorPath+"logo_cs_b.gif");
		JOptionPane.showMessageDialog(null, ""+lerArquivoVO.getSobreImg()+separadorPath+"logo_cs_c.gif");
		setIconImage(icone.getImage());
		jtaAjuda = new JTextArea();
		getContentPane().add(jtaAjuda);
	}

}
