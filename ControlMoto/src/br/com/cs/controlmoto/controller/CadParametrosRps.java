package br.com.cs.controlmoto.controller;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
/**
 * Company Fictec Cons. Inf.
 * @since 06/09/2012
 * @version 1.0.6
 * @author Clovis
 */

public class CadParametrosRps extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JToolBar jtbBarraFerramenta;
	
	private JPanel jpCampos, jpToolBar;

	private JLabel	lblCaminhoSaida, lblNomeArquivo, lblTipoDeRegistro, lblTipoDoRPS, lblDerieDoRPS, lblNumeroDoRPS, lblSituacaoDoRPS, 
			lblCodigoDoServicoPrestado, lblAliquota, lblIssRetido, lblDiscriminacaoDosServicos;
	
	private JTextField	txtCaminhoSaida, txtNomeArquivo, txtTipoDeRegistro, txtTipoDoRPS, txtDerieDoRPS, txtNumeroDoRPS, txtSituacaoDoRPS, 
				txtCodigoDoServicoPrestado, txtAliquota, txtIssRetido, txtDiscriminacaoDosServicos;
	
	private JButton btoAlterar, btoExcluir, btoIncluir, btoLocalizar, btoSalvar;
	
	private JInternalFrame cadParametrosRps;
	
	private ControlMoto telaControlMoto;
	
	private String caminho ="", separator ="";
	
    //Serve para mover a tela interna
    static int openFrameCount = 3;
    static final int xOffset = 30, yOffset = 26;
    
    public CadParametrosRps(String titulo, ControlMoto controlMoto ) {
    	super(titulo, false, true, false, true);
    	initCadParametrosRps("", controlMoto);
    	this.telaControlMoto = telaControlMoto;
    	controlMoto.jDesktopPane.add(this);
    	
    }

	public void initCadParametrosRps(String titulo, ControlMoto controlMoto ) {
		setSize(400,300);
		setVisible(true);
		
		lblCaminhoSaida = new JLabel(); lblNomeArquivo = new JLabel(); lblTipoDeRegistro = new JLabel(); lblTipoDoRPS = new JLabel();
		lblDerieDoRPS = new JLabel(); lblNumeroDoRPS = new JLabel(); lblSituacaoDoRPS = new JLabel(); 
		lblCodigoDoServicoPrestado = new JLabel(); lblAliquota = new JLabel(); lblIssRetido = new JLabel();
		lblDiscriminacaoDosServicos = new JLabel();

		txtCaminhoSaida = new JTextField(); txtNomeArquivo = new JTextField(); txtTipoDeRegistro = new JTextField();
		txtTipoDoRPS = new JTextField(); txtDerieDoRPS = new JTextField(); txtNumeroDoRPS = new JTextField();
		txtSituacaoDoRPS = new JTextField();txtCodigoDoServicoPrestado = new JTextField(); txtAliquota = new JTextField();
		txtIssRetido = new JTextField(); txtDiscriminacaoDosServicos = new JTextField();
		
		btoAlterar = new JButton(); btoExcluir = new JButton(); btoIncluir = new JButton(); btoLocalizar = new JButton();
		btoSalvar = new JButton();
		
	}

}
