package br.com.cs.controlmoto.view;

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

import br.com.cs.controlmoto.domain.PesqRelatMotDAO;
import br.com.cs.controlmoto.utils.LerArquivo;
import br.com.cs.controlmoto.vo.LerArquivoVO;
import br.com.cs.controlmoto.vo.PesqRelatMotVO;
	/**
	 * @author Clovis
	 */
    class PesqRelatMotView extends JInternalFrame implements ActionListener, ItemListener {
  	/**
	 * 
	 */
	private static final long serialVersionUID = -4160327735033525077L;

		JComboBox jcbNomeMotorista;
		JRadioButton jrbAberta, jrbFechada,jrbCancelada;
		ButtonGroup bgOrdemServico;
        JLabel lblNome, lblDatainicio, lblDatafinal;
        JXDatePicker jXDPDataInicio, jXDPDataFinal;
        JButton btoAjuda, btoConfirma, btoCancela;
        JPanel jpCalendario, jpCombobox, jpBotoes, jpRadioButon;
        private ControlMotoView telaControlMoto;
        Dimension centraliza = Toolkit.getDefaultToolkit().getScreenSize();
        String dataInicio, dataFinal, separadorPath = "", status, logo_tipo, ajuda_img, caminho="";
        SimpleDateFormat df;
		protected java.util.Properties props;
        final long mspd = 86400000;
        
        static int openFrameCount = 9;
        static final int xOffset = 70, yOffset = 65;
        
   /** Creates a new instance of Pesquisa */
    public PesqRelatMotView(String titulo, ControlMotoView controlMoto) {
    	//Titulo. resizable, closable, maximizable, iconifiable
    	super(titulo,false,true,false,true);
    	setSize(490, 180);
    	setVisible(true);
    	setLocation(xOffset*openFrameCount, yOffset*openFrameCount);
    	separadorPath = System.getProperty("file.separator");
    	caminho = new File("").getAbsolutePath();
//    	//CAMINHO CORRETO PARA FUNCIONAR NO JAR
//		File file = new File(caminho+separadorPath+"ControlMoto"+separadorPath+"resources"+separadorPath+"propertys.properties");
//		//CAMINHO CORRETO PARA FUNCIONAR NO ECLIPSE
//    	//File file = new File("./resources/propertys.properties");
//    	props = new java.util.Properties();
//    	FileInputStream fis = null;
//    	try{
//    		fis = new FileInputStream(file);
//    		props.load(fis);
//    		logo_tipo = props.getProperty("logo_tipo");
//    		ajuda_img = props.getProperty("ajuda_img");
//    		fis.close();
//    	}catch (IOException e) {
//			JOptionPane.showMessageDialog(null,"Erro ao ler informações do sistema","ControlMoto",JOptionPane.INFORMATION_MESSAGE);
//		}
   	
    	this.telaControlMoto = telaControlMoto;
    	controlMoto.jDesktopPane.add(this);
    	
    	Container pesq = getContentPane();
        pesq.setLayout(new BorderLayout(5,5));
        
        jpCombobox = new JPanel();jpCombobox.setSize(20,200);jpCombobox.setLayout(new FlowLayout(1,10,5));
        
        jrbAberta = new JRadioButton("O.S. Aberta");jrbAberta.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jrbAberta.addActionListener(this);jrbAberta.addItemListener(this);
        jrbFechada = new JRadioButton("O.S. Fechada");jrbFechada.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jrbFechada.addActionListener(this);jrbFechada.addItemListener(this);
        jrbCancelada = new JRadioButton("O.S. Cancelada");jrbCancelada.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jrbCancelada.addActionListener(this);jrbCancelada.addItemListener(this);
        
        bgOrdemServico = new ButtonGroup();
        bgOrdemServico.add(jrbAberta);bgOrdemServico.add(jrbFechada);bgOrdemServico.add(jrbCancelada);
       
        jpCalendario = new JPanel();jpCalendario.setSize(20,170);jpCalendario.setLayout(new GridLayout(2,2,25,8));
        
        jpBotoes = new JPanel();jpBotoes.setLayout(new FlowLayout(1,80,5));
        
        jpRadioButon = new JPanel();jpRadioButon.setSize(20,30);jpRadioButon.setLayout(new GridLayout(3,1));
        
        lblNome = new JLabel("Nome");
        
        lblDatainicio = new JLabel("Data Inicio");
        lblDatainicio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        
        lblDatafinal = new JLabel("Data Final");
        lblDatafinal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        
        jcbNomeMotorista = new JComboBox();jcbNomeMotorista.addActionListener(this);jcbNomeMotorista.addItemListener(this);
        jcbNomeMotorista.setPreferredSize(new java.awt.Dimension(345, 22));
        
        jXDPDataInicio = new JXDatePicker();
        jXDPDataInicio.setMaximumSize(new java.awt.Dimension(150, 22));
        jXDPDataInicio.setMinimumSize(new java.awt.Dimension(150, 22));
        jXDPDataInicio.setPreferredSize(new java.awt.Dimension(150, 22));
        jXDPDataInicio.addActionListener(this);
        
        jXDPDataFinal = new JXDatePicker();
        jXDPDataFinal.setMaximumSize(new java.awt.Dimension(150, 22));
        jXDPDataFinal.setMinimumSize(new java.awt.Dimension(150, 22));
        jXDPDataFinal.setPreferredSize(new java.awt.Dimension(150, 22));
        jXDPDataFinal.addActionListener(this);
        
        btoAjuda =  new JButton("");btoAjuda.addActionListener(this);
        btoAjuda.getAccessibleContext().setAccessibleName("btoAjuda");
     
        btoConfirma = new JButton("Confirma");btoConfirma.addActionListener(this);
        btoCancela = new JButton("Cancelar");btoCancela.addActionListener(this);
        
        df = new SimpleDateFormat("dd/MM/yyyy");
                
        btoAjuda.setFont(new java.awt.Font("Tahoma", 0, 8));
//        ImageIcon iconeAjuda = new ImageIcon("..//ControlMoto/imagens/20_duvida.png");
        ImageIcon iconeAjuda = new ImageIcon(ajuda_img+"20_duvida.png");
 		btoAjuda.setIcon(iconeAjuda);
        btoAjuda.setToolTipText("Ajuda");btoAjuda.setBorderPainted(false);
        btoAjuda.setContentAreaFilled(false);btoAjuda.setMargin(new java.awt.Insets(1, 1, 1, 1));
        btoAjuda.setName("ajuda");btoAjuda.addActionListener(this);

        jpCombobox.add(lblNome);jpCombobox.add(jcbNomeMotorista);jpCombobox.add(btoAjuda);
        getContentPane().add(jpCombobox, BorderLayout.NORTH);
        
        jpRadioButon.add(jrbAberta);jpRadioButon.add(jrbFechada);jpRadioButon.add(jrbCancelada);
        getContentPane().add(jpRadioButon, BorderLayout.EAST);
        
        jpCalendario.add(lblDatainicio);
        jpCalendario.add(jXDPDataInicio);
        jpCalendario.add(lblDatafinal);
        jpCalendario.add(jXDPDataFinal);
        getContentPane().add(jpCalendario, BorderLayout.CENTER);
        
        jpBotoes.add(btoConfirma);
        jpBotoes.add(btoCancela);
        getContentPane().add(jpBotoes, BorderLayout.SOUTH);
        
        PesqRelatMotVO pesqRelatMotoVO = new PesqRelatMotVO();
        PesqRelatMotDAO pesqRelatMotDAO = new PesqRelatMotDAO();
        List<PesqRelatMotVO> o=null;
		try {
			o = pesqRelatMotDAO.readMotoristaByNomeData(pesqRelatMotoVO);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
        Object[] objs = new Object[o.size()+1];
        objs[0] = ""; 
        int i = 0;
        for(int y=0; y < (o.size()+1); y++){
	        for(PesqRelatMotVO pesqRelMotoVO : o){
	        	objs[i+1] = pesqRelMotoVO.getNome();        	
	        	}
	        jcbNomeMotorista.addItem(objs[y]);
        }
   	}
    
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == jXDPDataFinal ){
			Long dtTerm = jXDPDataFinal.getDateInMillis();
			Long dtIni = jXDPDataInicio.getDateInMillis();
			dataInicio = df.format(dtIni);dataFinal = df.format(dtTerm);
			if(((dtTerm - dtIni)/mspd) > 30 ){
				JOptionPane.showMessageDialog(this, "Periodo invalido", "Moto Express", JOptionPane.CANCEL_OPTION);
				jXDPDataFinal.requestFocus();
			}
		}
//    	if(e.getSource() == jcbNomeMotorista){
//    		if(jrbAberta.getText() == "O.S. Aberta" || jrbCancelada.getText() == "O.S. Cancelada"){
//    			JOptionPane.showMessageDialog(this, "Opcao valida so para ordem de servico fechadas", "Moto Express", JOptionPane.CANCEL_OPTION);
//    			jcbNomeMotorista.setSelectedItem("");jrbAberta.setSelected(false);jrbFechada.setSelected(false);
//    			jrbCancelada.setSelected(false);dataInicio = null;dataFinal = null;
//    		}	
//    	}

	}
    /*
     * (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     * Todos os eventos referente as acoes dos componentes
     */
    public void actionPerformed(ActionEvent e){
    	if(e.getSource() == jcbNomeMotorista && !jcbNomeMotorista.getSelectedItem().toString().isEmpty()){
			if(status != "F" && status != null){
				JOptionPane.showMessageDialog(this, "Opcao valida somente para ordem\nde servico fechadas", "Moto Express", JOptionPane.CANCEL_OPTION);
				jcbNomeMotorista.setSelectedItem("");
				}
    	}
    	if(e.getSource() == jrbAberta){
    		status = "A";
    		if(!jcbNomeMotorista.getSelectedItem().toString().isEmpty()){
    			JOptionPane.showMessageDialog(this, "Opcao valida so para ordem\nde servico fechadas", "Moto Express", JOptionPane.CANCEL_OPTION);
    			jcbNomeMotorista.setSelectedItem("");jrbAberta.setSelected(false);jrbFechada.setSelected(false);
    			jrbCancelada.setSelected(false);
    		}	
    	}
    	if(e.getSource() == jrbFechada){
    		status = "F";
    	}
    	if(e.getSource() == jrbCancelada){
    		status = "C";
    		if(!jcbNomeMotorista.getSelectedItem().toString().isEmpty()){
    			JOptionPane.showMessageDialog(this, "Opcao valida so para ordem\nde servico fechadas", "Moto Express", JOptionPane.CANCEL_OPTION);
    			jcbNomeMotorista.setSelectedItem("");jrbAberta.setSelected(false);jrbFechada.setSelected(false);
    			jrbCancelada.setSelected(false);
    		}
    	}
       	if (e.getSource() == jXDPDataInicio){
    		try{
				Date dtInicio = jXDPDataInicio.getDate();
				dataInicio = df.format(dtInicio);
			}catch(Exception df){
				JOptionPane.showConfirmDialog(this, "Moto Express", "Data nao convertida", JOptionPane.YES_OPTION);
				return;
				}
		}
		if (e.getSource() == jXDPDataFinal){
			try{
				Date dtTermino = jXDPDataFinal.getDate();
				dataFinal = df.format(dtTermino);
				}catch(Exception df){
					JOptionPane.showConfirmDialog(this, "Moto Express", "Data nao convertida", JOptionPane.YES_OPTION);
					return;
				}
		}
        if(e.getSource() == btoConfirma){
        	/*
        	 * Lê arquivo de configuração do projeto
        	 */
        	LerArquivoVO lerArquivoVO = new LerArquivoVO();
        	LerArquivo lerArquivo = new LerArquivo();
        	lerArquivoVO = lerArquivo.getInformacao(lerArquivoVO);
        	
        	Long dtTerm = jXDPDataFinal.getDateInMillis();
    		Long dtIni = jXDPDataInicio.getDateInMillis();
    		dataInicio = df.format(dtIni);dataFinal = df.format(dtTerm);
    		if(((dtTerm - dtIni)/mspd) <= 30 && status != null){ //|| jrbAberta.isSelected() == true && jrbFechada.isSelected() == true && jrbCancelada.isSelected() == true ){// bgOrdemServico.isSelected()){
	        	if(dataInicio.equals(dataInicio) && dataFinal.equals(dataFinal) && jcbNomeMotorista.getSelectedItem().toString().isEmpty() && jrbFechada.isSelected() == true){
	        		try{
	        			RelatClienteView rep = new RelatClienteView();
	        			JasperPrint relat;
	        			relat = rep.gerarRelatTotalMotDataFechada(status, new java.sql.Timestamp(df.parse(dataInicio).getTime()), new java.sql.Timestamp(df.parse(dataFinal).getTime()));
	        			JasperViewer jasperViewer = new JasperViewer(relat, false);
	        			jasperViewer.setModalExclusionType(ModalExclusionType.NO_EXCLUDE);
	        			jasperViewer.setTitle("Control Moto - Relatorio Total Motorista por Periodo");
	        			jasperViewer.setZoomRatio(0.75f);
	        			jasperViewer.setBounds(100,110,750,600);
	        			//ImageIcon icone = new ImageIcon(".."+separadorPath+separadorPath+"ControlMoto"+separadorPath+"imagens"+separadorPath+"logo_cs_b.gif");
	        			ImageIcon icone = new ImageIcon(caminho+separadorPath+lerArquivoVO.getLogoTipo()+separadorPath+"logo_cs_b.gif");
	    				jasperViewer.setIconImage(icone.getImage());
	    				jasperViewer.setAlwaysOnTop(true);
	    				jasperViewer.setVisible(true);
	//        			JasperViewer.viewReport(relat,false);
	         			}catch(Exception ex){
	        				ex.printStackTrace();
	        			}
	        	}
	        	if(dataInicio.equals(dataInicio) && dataFinal.equals(dataFinal) && jcbNomeMotorista.getSelectedItem().toString().isEmpty() && jrbAberta.isSelected() == true){
	        		try{
	        			RelatClienteView rep = new RelatClienteView();
	        			JasperPrint relat;
	        			relat = rep.gerarRelatTotalMotDataAberta(status, new java.sql.Timestamp(df.parse(dataInicio).getTime()), new java.sql.Timestamp(df.parse(dataFinal).getTime()));
	        			JasperViewer jasperViewer = new JasperViewer(relat, false);
	        			jasperViewer.setModalExclusionType(ModalExclusionType.NO_EXCLUDE);
	        			jasperViewer.setTitle("Control Moto - Relatorio Total Motorista por Periodo");
	        			jasperViewer.setZoomRatio(0.75f);
	        			jasperViewer.setBounds(100,110,750,600);
	        			//ImageIcon icone = new ImageIcon(".."+separadorPath+separadorPath+"ControlMoto"+separadorPath+"imagens"+separadorPath+"logo_cs_b.gif");
	        			ImageIcon icone = new ImageIcon(caminho+separadorPath+lerArquivoVO.getLogoTipo()+separadorPath+"logo_cs_b.gif");
	    				jasperViewer.setIconImage(icone.getImage());
	    				jasperViewer.setAlwaysOnTop(true);
	    				jasperViewer.setVisible(true);
	//        			JasperViewer.viewReport(relat,false);
	         			}catch(Exception ex){
	        				ex.printStackTrace();
	        			}
	        	}
	        	
	        	if(dataInicio.equals(dataInicio) && dataFinal.equals(dataFinal) && jcbNomeMotorista.getSelectedItem().toString().isEmpty() && jrbCancelada.isSelected() == true){
	        		try{
	        			RelatClienteView rep = new RelatClienteView();
	        			JasperPrint relat;
	        			relat = rep.gerarRelatTotalMotDataCancelada(status, new java.sql.Timestamp(df.parse(dataInicio).getTime()), new java.sql.Timestamp(df.parse(dataFinal).getTime()));
	        			JasperViewer jasperViewer = new JasperViewer(relat, false);
//	        			jasperViewer.setModalExclusionType(ModalExclusionType.NO_EXCLUDE);
	        			jasperViewer.setTitle("Control Moto - Relatorio Total Motorista por Periodo");
	        			jasperViewer.setZoomRatio(0.75f);
	        			jasperViewer.setBounds(100,110,750,600);
	        			//ImageIcon icone = new ImageIcon(".."+separadorPath+separadorPath+"ControlMoto"+separadorPath+"imagens"+separadorPath+"logo_cs_b.gif");
	        			ImageIcon icone = new ImageIcon(caminho+separadorPath+lerArquivoVO.getLogoTipo()+separadorPath+"logo_cs_b.gif");
	    				jasperViewer.setIconImage(icone.getImage());
	    				jasperViewer.setAlwaysOnTop(true);
	    				jasperViewer.setVisible(true);
	         			}catch(Exception ex){
	        				ex.printStackTrace();
	        			}
	        	}
	        	if(dataInicio.equals(dataInicio) && dataFinal.equals(dataFinal) && !jcbNomeMotorista.getSelectedItem().toString().isEmpty() && jrbFechada.isSelected() == true ){
	           		try{
	        			String nomeMotorista = jcbNomeMotorista.getSelectedItem().toString();
	        			RelatClienteView rep = new RelatClienteView();
	        			JasperPrint relat;
	        			relat = rep.gerarRelatTotalMotDataNome(nomeMotorista, new java.sql.Timestamp(df.parse(dataInicio).getTime()), new java.sql.Timestamp(df.parse(dataFinal).getTime()));
	        			JasperViewer jasperViewer = new JasperViewer(relat, false);
	        			jasperViewer.setTitle("Control Moto - Relatorio Total Motorista por Periodo e Nome");
	        			jasperViewer.setZoomRatio(0.75f);
	        			jasperViewer.setBounds(100,110,750,600);
	        			//ImageIcon icone = new ImageIcon(".."+separadorPath+separadorPath+"ControlMoto"+separadorPath+"imagens"+separadorPath+"logo_cs_b.gif");
	        			ImageIcon icone = new ImageIcon(caminho+separadorPath+lerArquivoVO.getLogoTipo()+separadorPath+"logo_cs_b.gif");
	    				jasperViewer.setIconImage(icone.getImage());
	    				//jasperViewer.setAlwaysOnTop(true);
	    				jasperViewer.setVisible(true);
	           			}catch(Exception ex){
	         	   			ex.printStackTrace();
	         	   		}
	           }
    		}else{
	        	JOptionPane.showMessageDialog(this, "Periodo invalido ou opcao não selecionada", "Moto Express", JOptionPane.CANCEL_OPTION);
				jXDPDataFinal.requestFocus();
    		}
		}
        
        if(e.getSource() == btoCancela){
        	dispose();
        }
    }
    }