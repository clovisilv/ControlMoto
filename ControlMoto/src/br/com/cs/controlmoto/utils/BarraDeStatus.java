package br.com.cs.controlmoto.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import br.com.cs.controlmoto.controller.ControlMoto;
import br.com.cs.controlmoto.vo.BarraDeStatusVO;


/**
 * Company Fictec Consul. Informática
 * @author Clovis
 * @version 1.0.0
 * @since 26102012
 */
public class BarraDeStatus extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private javax.swing.JPanel jpLabels;
	private javax.swing.JLabel lblData, lblHora, lblNumRegistro, lblUsuario, lblStatus;
	private br.com.cs.controlmoto.vo.BarraDeStatusVO barraDeStatusVO;

	public BarraDeStatus() {
		initBaraDeStatus();		
	}
	
	public JPanel initBaraDeStatus(){
		setSize(100, 100);
		show();
//		setResizable(false);
		//Instacia o JPanel da barra de status
		jpLabels = new JPanel();
		//Determina do layout do JPanel
		jpLabels.setLayout(new GridLayout(1, 5));
		//Instacia os JLabels da barra de status
//		lblData = new JLabel(); lblHora = new JLabel();
		lblNumRegistro = new JLabel("Número de Registros:");
		lblUsuario = new JLabel("Usuário"); lblStatus = new JLabel("Situação:");
		//
		barraDeStatusVO = new BarraDeStatusVO();
		//Instancia o tipo de layout GridBagLayout
		GridBagLayout gridLayout = new GridBagLayout();
		//Seta o layout no painel principal
		jpLabels.setLayout(gridLayout);
		//Instacia o GridBagConstraints 
		GridBagConstraints cons = new GridBagConstraints();
		//Determina o espacamento entre os componentes no painel
		cons.insets =  new Insets(0,0,0,0);
		//Determina o altura e largura entre os componentes no painel
//		cons.weightx = 0.30;cons.weighty = 0.30;
		/**
		 * 
		 */
		cons.anchor = GridBagConstraints.NORTHWEST; cons.fill = GridBagConstraints.HORIZONTAL;
		/*
		 * Insere JLabel de Informações oedem de serviço
		 * Insere JSeparador de Informações ordem de serviço
		 */
		Font font = new Font("Verdana", Font.PLAIN, 10);
//		cons.gridx = 1;cons.gridy = 1;cons.gridwidth = 1; cons.ipadx = 30;
//		lblData.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
//		Font font = new Font("Verdana", Font.PLAIN, 10);
//		lblData.setForeground(Color.BLACK);lblData.setFont(font);
//		jpLabels.add(lblData,cons);
//		
//		cons.gridx = 2;cons.gridy = 1;cons.gridwidth = 1; cons.ipadx = 30;
//		lblHora.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
//		lblHora.setForeground(Color.BLACK);lblHora.setFont(font);
//		jpLabels.add(lblHora,cons);
		
		cons.gridx = 0;cons.gridy = 1;cons.gridwidth = 2; cons.ipadx = 85;
		lblNumRegistro.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		lblNumRegistro.setForeground(Color.BLACK); lblNumRegistro.setFont(font);
		lblNumRegistro.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		lblNumRegistro.setText(lblNumRegistro.getText()+ (!barraDeStatusVO.getLblNumRegistro().isEmpty() ? barraDeStatusVO.getLblNumRegistro() : "" ));
		jpLabels.add(lblNumRegistro, cons);
		
		cons.gridx = 2;cons.gridy = 1;cons.gridwidth = 4; cons.ipadx = 205;
		lblStatus.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		lblStatus.setForeground(Color.BLACK);lblStatus.setFont(font);
		lblStatus.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		lblStatus.setText(lblStatus.getText()+ (barraDeStatusVO.getLblStatus() != null ? barraDeStatusVO.getLblStatus() : "" ));
		jpLabels.add(lblStatus, cons);

		ControlMoto cm = new ControlMoto();
		cons.gridx = 6;cons.gridy = 1;cons.gridwidth = 1; cons.ipadx = 100;
		lblUsuario.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		lblUsuario.setForeground(Color.BLACK);lblUsuario.setFont(font);
		lblUsuario.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
//		lblUsuario.setText(lblUsuario.getText()+cm.)
		jpLabels.add(lblUsuario, cons);
		
//		lblData.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
//		
//		lblHora.setText(new SimpleDateFormat("hh:mm:ss").format(new Date()));
		
		return jpLabels;

	}

}
