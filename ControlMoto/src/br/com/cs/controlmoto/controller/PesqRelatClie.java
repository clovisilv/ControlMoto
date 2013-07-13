package br.com.cs.controlmoto.controller;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.Dialog.ModalExclusionType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import org.jdesktop.swingx.JXDatePicker;

import br.com.cs.controlmoto.domain.PesqRelatClieDAO;
import br.com.cs.controlmoto.utils.LerArquivo;
import br.com.cs.controlmoto.vo.LerArquivoVO;
import br.com.cs.controlmoto.vo.PesqRelatClieVO;

/**
 * Company Fictec Cons. Inf.
 * @since 06/09/2012
 * @version 1.0.6
 * @author Clovis
 */
public class PesqRelatClie extends JInternalFrame implements ActionListener, ItemListener{
    	
	   	/**
		 * 
		 */
		private static final long serialVersionUID = -4536573625074858504L;

		JComboBox jcbNomeCliente;
		JRadioButton jrbAberta, jrbFechada,jrbCancelada;
		ButtonGroup bgOrdemServico;
		JLabel lblNome,lblDatainicio,lblDatafinal;
		JXDatePicker jXDPDataInicio, jXDPDataTermino;
		JButton btoAjuda,btoConfirma,btoCancela;
		JPanel jpCalendario, jpCombobox, jpBotoes, jpRadioButon;
		private ControlMoto telaControlMoto;
		Dimension centraliza = Toolkit.getDefaultToolkit().getScreenSize();
		String dataInicio, dataTermino, separadorPath = "", caminho="", status, report, logo_tipo, ajuda_img;
		SimpleDateFormat df;
		protected java.util.Properties props;
		final long mspd = 86400000;
		//Serve para mover a tela
        static int openFrameCount = 8;
        static final int xOffset = 15, yOffset = 20;
        
   /*
    * Creates a new instance of Pesquisa
    **/
    public PesqRelatClie(String titulo,ControlMoto controlMoto) {
    	super(titulo,false,true,false,true);
    	setSize(490, 180);
    	setVisible(true);
    	setLocation(xOffset*openFrameCount, yOffset*openFrameCount);
    	setToolTipText("Relatório de Cliente");
    	separadorPath = System.getProperty("file.separator");
    	caminho = new File("").getAbsolutePath();
//    	//CAMINHO CORRETO PARA FUNCIONAR NO JAR
//		File file = new File(caminho+separadorPath+"ControlMoto"+separadorPath+"resources"+separadorPath+"propertys.properties");
//		//CAMINHO CORRETO PARA FUNCIONAR NO ECLIPSE
//    	//File file = new File("./resources/propertys.properties");
//    	props = new java.util.Properties();
//     	FileInputStream fis = null;
//    	try{
//    		fis = new FileInputStream(file);
//    		props.load(fis);
//    		logo_tipo = props.getProperty("logo_tipo");
//    		ajuda_img = props.getProperty("ajuda_img");
//    		fis.close();
//    	}catch (IOException e) {
//			JOptionPane.showMessageDialog(null,"Erro ao ler informaçÃµes do sistema","ControlMoto",JOptionPane.INFORMATION_MESSAGE);
//		}
   	
    	this.telaControlMoto = telaControlMoto;
    	controlMoto.jDesktopPane.add(this);
        
        Container pesq = getContentPane();
        pesq.setLayout(new BorderLayout(5,5));
        
        jpCombobox = new JPanel();jpCombobox.setSize(100,200);jpCombobox.setLayout(new FlowLayout(1,10,5));
        
        jrbAberta = new JRadioButton("O.S. Aberta");jrbAberta.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jrbAberta.addActionListener(this);
        jrbFechada = new JRadioButton("O.S. Fechada");jrbFechada.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jrbFechada.addActionListener(this);
        jrbCancelada = new JRadioButton("O.S. Cancelada");jrbCancelada.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jrbCancelada.addActionListener(this);
        
        bgOrdemServico = new ButtonGroup();
        bgOrdemServico.add(jrbAberta);bgOrdemServico.add(jrbFechada);bgOrdemServico.add(jrbCancelada);
       
        jpCalendario = new JPanel();jpCalendario.setSize(20,170);jpCalendario.setLayout(new GridLayout(2,2,40,8));
        
        jpBotoes = new JPanel();jpBotoes.setLayout(new FlowLayout(1,80,5));
        
        jpRadioButon = new JPanel();jpRadioButon.setSize(20,30);jpRadioButon.setLayout(new GridLayout(3,1));
        
        lblNome = new JLabel("Nome");
        
        lblDatainicio = new JLabel("Data Inicio");
        lblDatainicio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        
        lblDatafinal = new JLabel("Data Final");
        lblDatafinal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        
        jcbNomeCliente = new JComboBox();jcbNomeCliente.setEnabled(false);
        jcbNomeCliente.addActionListener(this);
        jcbNomeCliente.setPreferredSize(new java.awt.Dimension(345, 22));
        
        jXDPDataInicio = new JXDatePicker();
        jXDPDataInicio.setMaximumSize(new java.awt.Dimension(150, 22));
        jXDPDataInicio.setMinimumSize(new java.awt.Dimension(150, 22));
        jXDPDataInicio.setPreferredSize(new java.awt.Dimension(150, 22));
        jXDPDataInicio.addActionListener(this);
        
        jXDPDataTermino = new JXDatePicker();
        jXDPDataTermino.setMaximumSize(new java.awt.Dimension(150, 22));
        jXDPDataTermino.setMinimumSize(new java.awt.Dimension(150, 22));
        jXDPDataTermino.setPreferredSize(new java.awt.Dimension(150, 22));
        jXDPDataTermino.addActionListener(this);
        
        btoAjuda =  new JButton("");btoAjuda.addActionListener(this);
        btoAjuda.getAccessibleContext().setAccessibleName("btoAjuda");
        
        btoConfirma = new JButton("Confirma");btoConfirma.addActionListener(this);
        btoCancela = new JButton("Cancelar");btoCancela.addActionListener(this);
        
        df = new SimpleDateFormat("dd/MM/yyyy");
                
        btoAjuda.setFont(new java.awt.Font("Tahoma", 0, 8));
        ImageIcon iconeAjuda = new ImageIcon(ajuda_img+"20_duvida.png");
 		btoAjuda.setIcon(iconeAjuda);
        btoAjuda.setToolTipText("Ajuda");
        btoAjuda.setBorderPainted(false);
        btoAjuda.setContentAreaFilled(false);
        btoAjuda.setMargin(new java.awt.Insets(1, 1, 1, 1));
        btoAjuda.setName("ajuda");
        btoAjuda.addActionListener(this);

        jpCombobox.add(lblNome);jpCombobox.add(jcbNomeCliente);jpCombobox.add(btoAjuda);
        pesq.add(jpCombobox, BorderLayout.NORTH);
        
        jpRadioButon.add(jrbAberta);jpRadioButon.add(jrbFechada);jpRadioButon.add(jrbCancelada);
        getContentPane().add(jpRadioButon, BorderLayout.EAST);
        
        jpCalendario.add(lblDatainicio);
        jpCalendario.add(jXDPDataInicio);
        jpCalendario.add(lblDatafinal);
        jpCalendario.add(jXDPDataTermino);
        getContentPane().add(jpCalendario, BorderLayout.CENTER);
        
        jpBotoes.add(btoConfirma);
        jpBotoes.add(btoCancela);
        pesq.add(jpBotoes, BorderLayout.SOUTH);
        
        PesqRelatClieVO pesqRelatClieVO = new PesqRelatClieVO();
        PesqRelatClieDAO pesqRelatClieDAO = new PesqRelatClieDAO();
        List<PesqRelatClieVO> o = null;
		try {
			o = pesqRelatClieDAO.readClienteByNomeData(pesqRelatClieVO);
		} catch (SQLException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
        
        Object[] objs = new Object[o.size()+1];
        objs[0] = ""; 
        int i = 1;
        for(PesqRelatClieVO pesqRelClieVO : o){
        	objs[i] = pesqRelClieVO.getNome();
        	i++;
       	}
        for(int y=0; y < objs.length; y++){
	        jcbNomeCliente.addItem(objs[y]);        	
        }
  	}
    
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == jXDPDataTermino ){
			Long dtTerm = jXDPDataTermino.getDateInMillis();
			Long dtIni = jXDPDataInicio.getDateInMillis();
			dataInicio = df.format(dtIni);dataTermino = df.format(dtTerm);
			if(((dtTerm - dtIni)/mspd) > 30 ){
				JOptionPane.showMessageDialog(this, "Periodo inválido", "ControlMoto", JOptionPane.CANCEL_OPTION);
				jXDPDataTermino.requestFocus();
			}
		}
	
	}
    
    public void actionPerformed(ActionEvent e){
    	if(e.getSource() == jcbNomeCliente && !jcbNomeCliente.getSelectedItem().toString().isEmpty()){
			if(status != "F" && status != ""){
				JOptionPane.showMessageDialog(this, "Nome selecionado.\nOpção válida só para ordem de servico fechada", "ControlMoto", JOptionPane.CANCEL_OPTION);
				jcbNomeCliente.setSelectedItem("");
				}
    	}
    	if(e.getSource() == jrbAberta){
    		status = "A/P";
    		if(!jcbNomeCliente.getSelectedItem().toString().isEmpty()){
    			JOptionPane.showMessageDialog(this, "Nome selecionado.\nOpção válida só para ordem de serviço fechada", "ControlMoto", JOptionPane.CANCEL_OPTION);
    			jcbNomeCliente.setSelectedItem("");jcbNomeCliente.setEnabled(false);jrbAberta.setSelected(false);jrbFechada.setSelected(false);
    			jrbCancelada.setSelected(false);
    		}
    		jcbNomeCliente.setEnabled(false);jrbFechada.setSelected(false);jrbCancelada.setSelected(false);
    	}
    	if(e.getSource() == jrbFechada){
    		status = "F";
    		jcbNomeCliente.setEnabled(true);
    	}
    	if(e.getSource() == jrbCancelada){
    		status = "C";
    		if(!jcbNomeCliente.getSelectedItem().toString().isEmpty()){
    			JOptionPane.showMessageDialog(this, "Nome selecionado.\nOpção válida só para ordem de serviço fechada", "ControlMoto", JOptionPane.CANCEL_OPTION);
    			jcbNomeCliente.setSelectedItem("");jcbNomeCliente.setEnabled(false);jrbAberta.setSelected(false);jrbFechada.setSelected(false);
    			jrbCancelada.setSelected(false);
    		}
    		jcbNomeCliente.setEnabled(false);jrbAberta.setSelected(false);jrbFechada.setSelected(false);
    	}
    	
    	if (e.getSource() == jXDPDataInicio){
    		try{
				Date dtInicio = jXDPDataInicio.getDate();
				dataInicio = df.format(dtInicio);
    			}catch(Exception df){
    				JOptionPane.showConfirmDialog(this, "ControlMoto", "Data não convertida", JOptionPane.YES_OPTION);
    				return;
				}
			}
    	
		if (e.getSource() == jXDPDataTermino){
			try{
				Date dtTermino = jXDPDataTermino.getDate();
				dataTermino = df.format(dtTermino);
				Long dtTerm = jXDPDataTermino.getDateInMillis();
				Long dtIni = jXDPDataInicio.getDateInMillis();
				dataInicio = df.format(dtIni);dataTermino = df.format(dtTerm);
				if(((dtTerm - dtIni)/mspd) > 30 ){
					JOptionPane.showMessageDialog(this, "Periodo inválido", "ControlMoto", JOptionPane.CANCEL_OPTION);
					jXDPDataTermino.requestFocus();
					}
				}catch(Exception df){
					JOptionPane.showConfirmDialog(this, "ControlMoto", "Data não convertida", JOptionPane.YES_OPTION);
					return;
				}
		}
		  	
        if(e.getSource() == btoConfirma){
        	/* Lá arquivo de configuração do projeto */
        	LerArquivoVO lerArquivoVO = new LerArquivoVO();
        	LerArquivo lerArquivo = new LerArquivo();
        	lerArquivoVO = lerArquivo.getInformacao(lerArquivoVO);
        	
        	Long dtTerm = jXDPDataTermino.getDateInMillis();
    		Long dtIni = jXDPDataInicio.getDateInMillis();
    		dataInicio = df.format(dtIni);dataTermino = df.format(dtTerm);
    		if(((dtTerm - dtIni)/mspd) <= 30 && status != null){
	        	if(dataInicio.equals(dataInicio) && dataTermino.equals(dataTermino) && jcbNomeCliente.getSelectedItem().toString().isEmpty() && jrbFechada.isSelected() == true ){
	        		try{
	        			RelatCliente rep = new RelatCliente();
	        			JasperPrint relat;
	        			relat = rep.gerarRelatTotalCliDataFechada(status, new java.sql.Timestamp(df.parse(dataInicio).getTime()), new java.sql.Timestamp(df.parse(dataTermino).getTime()));
	        			JasperViewer jasperViewer = new JasperViewer(relat, false);
	        			jasperViewer.setModalExclusionType(ModalExclusionType.NO_EXCLUDE);
	        			jasperViewer.setTitle("Control Moto - Relatório Cliente");
	        			jasperViewer.setZoomRatio(0.75f);
	        			jasperViewer.setBounds(100,110,750,600);
	        			//caminho para producao
	        			//ImageIcon icone = new ImageIcon(caminho+separadorPath+logo_tipo+"logo_cs_b.gif");
	        			//caminho para eclipse
	        			//ImageIcon icone = new ImageIcon(logo_tipo+"logo_cs_b.gif");
	        			ImageIcon icone = new ImageIcon(caminho+separadorPath+lerArquivoVO.getLogoTipo()+separadorPath+"logo_cs_b.gif");
	    				jasperViewer.setIconImage(icone.getImage());
	    				jasperViewer.setAlwaysOnTop(true);
	    				jasperViewer.setVisible(true);
         			}catch(Exception ex){
        				ex.printStackTrace();
         			}
	        	}
	        	if(dataInicio.equals(dataInicio) && dataTermino.equals(dataTermino) && jcbNomeCliente.getSelectedItem().toString().isEmpty() && jrbAberta.isSelected() == true ){
	        		try{
	        			RelatCliente rep = new RelatCliente();
	        			JasperPrint relat;
	        			relat = rep.gerarRelatTotalCliDataAberta(status, new java.sql.Timestamp(df.parse(dataInicio).getTime()), new java.sql.Timestamp(df.parse(dataTermino).getTime()));
	        			JasperViewer jasperViewer = new JasperViewer(relat, false);
	        			jasperViewer.setModalExclusionType(ModalExclusionType.NO_EXCLUDE);
	        			jasperViewer.setTitle("Control Moto - Relatório Cliente");
	        			jasperViewer.setZoomRatio(0.75f);
	        			jasperViewer.setBounds(100,110,750,600);
	        			//caminho para producao
	        			//ImageIcon icone = new ImageIcon(caminho+separadorPath+logo_tipo+"logo_cs_b.gif");
	        			//caminho para eclipse
	        			//ImageIcon icone = new ImageIcon(logo_tipo+"logo_cs_b.gif");
	        			ImageIcon icone = new ImageIcon(caminho+separadorPath+lerArquivoVO.getLogoTipo()+separadorPath+"logo_cs_b.gif");
	    				jasperViewer.setIconImage(icone.getImage());
	    				jasperViewer.setAlwaysOnTop(true);
	    				jasperViewer.setVisible(true);
         			}catch(Exception ex){
        				ex.printStackTrace();
        			}
	        	}
	        	if(dataInicio.equals(dataInicio) && dataTermino.equals(dataTermino) && jcbNomeCliente.getSelectedItem().toString().isEmpty() && jrbCancelada.isSelected() == true ){
	        		try{
	        			RelatCliente rep = new RelatCliente();
	        			JasperPrint relat;
	        			relat = rep.gerarRelatTotalCliDataCancelada(status, new java.sql.Timestamp(df.parse(dataInicio).getTime()), new java.sql.Timestamp(df.parse(dataTermino).getTime()));
	        			JasperViewer jasperViewer = new JasperViewer(relat, false);
	        			jasperViewer.setModalExclusionType(ModalExclusionType.NO_EXCLUDE);
	        			jasperViewer.setTitle("Control Moto - Relatório Cliente");
	        			jasperViewer.setZoomRatio(0.75f);
	        			jasperViewer.setBounds(100,110,750,600);
	        			//caminho para producao
	        			//ImageIcon icone = new ImageIcon(caminho+separadorPath+logo_tipo+"logo_cs_b.gif");
	        			//caminho para eclipse
	        			//ImageIcon icone = new ImageIcon(logo_tipo+"logo_cs_b.gif");
	        			ImageIcon icone = new ImageIcon(caminho+separadorPath+lerArquivoVO.getLogoTipo()+separadorPath+"logo_cs_b.gif");
	    				jasperViewer.setIconImage(icone.getImage());
	    				jasperViewer.setAlwaysOnTop(true);
	    				jasperViewer.setVisible(true);
         			}catch(Exception ex){
        				ex.printStackTrace();
        			}
	        	}
	        	if(dataInicio.equals(dataInicio) && dataTermino.equals(dataTermino) && !jcbNomeCliente.getSelectedItem().toString().isEmpty() && jrbFechada.isSelected() == true ){
	           		try{
	        			String nomeCliente = jcbNomeCliente.getSelectedItem().toString();
	        			RelatCliente rep = new RelatCliente();
	        			JasperPrint relat;
	        			relat = rep.gerarRelatTotalCliNomeData(nomeCliente, new java.sql.Timestamp(df.parse(dataInicio).getTime()), new java.sql.Timestamp(df.parse(dataTermino).getTime()));
	        			JasperViewer jasperViewer = new JasperViewer(relat, false);
	        			jasperViewer.setTitle("Control Moto - Relatório Total Cliente por Período e Nome");
	        			jasperViewer.setZoomRatio(0.75f);
	        			jasperViewer.setBounds(100,110,750,600);
	        			//caminho para producao
	        			//ImageIcon icone = new ImageIcon(caminho+separadorPath+logo_tipo+"logo_cs_b.gif");
	        			//caminho para eclipse
	        			//ImageIcon icone = new ImageIcon(logo_tipo+"logo_cs_b.gif");
	        			ImageIcon icone = new ImageIcon(caminho+separadorPath+lerArquivoVO.getLogoTipo()+separadorPath+"logo_cs_b.gif");
	    				jasperViewer.setIconImage(icone.getImage());
	    				jasperViewer.setVisible(true);
        			}catch(Exception ex){
         	   			ex.printStackTrace();
         	   		}
         	   }
	        	
    		}else{
	        	JOptionPane.showMessageDialog(this, "Periodo inválido ou opção não selecionada", "ControlMoto", JOptionPane.CANCEL_OPTION);
				jXDPDataTermino.requestFocus();
    		}
		}
        if(e.getSource() == btoAjuda){
        	AjudaRelatCliente ajudaRelatCliente = new AjudaRelatCliente();
        	ajudaRelatCliente.ajudaRelatCliente();
        }        
        if(e.getSource() == btoCancela){
        	dispose();
        }
        
    	}

    }
