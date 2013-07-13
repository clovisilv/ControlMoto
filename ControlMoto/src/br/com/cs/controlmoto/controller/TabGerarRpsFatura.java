package br.com.cs.controlmoto.controller;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.text.MaskFormatter;

import org.jdesktop.swingx.JXDatePicker;

import br.com.cs.controlmoto.domain.TabGerarRpsFaturaDAO;
import br.com.cs.controlmoto.utils.ConvertStringToDate;
import br.com.cs.controlmoto.utils.GerarArquivoRps;
import br.com.cs.controlmoto.utils.ModeloTabelaGerarRps;
import br.com.cs.controlmoto.vo.TabFaturaOrdensVO;
import br.com.cs.controlmoto.vo.TabGerarRpsFaturaVO;

/**
 * Company Fictec Cons. Inf.
 * @since 06/09/2012
 * @version 1.0.6
 * @author Clovis
 */

public class TabGerarRpsFatura extends JInternalFrame implements ActionListener, FocusListener, MouseListener{
	/*
	 * 
	 */
	private static final long serialVersionUID = -1379654176912208199L;
	
	private JToolBar jtbBarraFerramenta;
	private JLabel lblNome, lblDatainicio, lblDatafinal;
	private JComboBox jcbNome;
	private JCheckBox faturar;
	private JXDatePicker jtxtDatainicio, jtxtDatafinal;
	private JButton	btoLocalizar, btoCancelar, btoSair, btoExportar, btoSelecionaTodos, btoDesselecionaTodos;
	private String caminho, separadorPath;
	private JTable jtTabela;
	private JScrollPane jspPane;
	private DefaultTableCellRenderer tcrDireta, tcrEsqueda, tcrCentro, tcrData;
	private TableColumnModel colModel;
	private JPanel jpTabela, jpComponentes, jpFaturar, jpBtoAcoes;
	private JInternalFrame tabConsultaOrdens;
	private boolean	mousePressed;
	private DateFormat formatar;
	private SimpleDateFormat sdf,sdfTimestamp;
	private static List<TabFaturaOrdensVO> faturarLista=null;
	private static List<TabGerarRpsFaturaVO> listaGeraRpsVO = null;
	private static boolean selecionado=false;
	
	private TabGerarRpsFaturaDAO geraRpsDAO;
	private ArrayList<TabGerarRpsFaturaVO> listGeraRps;
		
	private ControlMoto	telaControlMoto;
	private Container content;
	
	//Serve para mover a tela
    static int openFrameCount = 8;
    static final int xOffset = 25, yOffset = 13;
    
    public TabGerarRpsFatura(String titulo, ControlMoto controlMoto){
    	   	
    	super(titulo,false,true,false,true);
    	setSize(700,430);
    	setVisible(true);
    	setLocation(xOffset*openFrameCount, yOffset*openFrameCount);
//    	ImageIcon icone = new ImageIcon("..//ControlMoto/imagens/logo_cs_b.gif");
//    	ImageIcon icone = new ImageIcon(".."+separadorPath+separadorPath+"ControlMoto"+separadorPath+"imagens"+separadorPath+"logo_cs_b.gif");
//    	setIconImage(icone.getImage());
//    	TabConsultaOrdens.setDefaultLocale(null);
    	this.telaControlMoto = telaControlMoto;
    	controlMoto.jDesktopPane.add(this);
   
    	content = getContentPane();
    	content.setLayout(new BorderLayout());
    	
    	formatar = new SimpleDateFormat().getDateInstance();
    	
    	tabConsultaOrdens = new JInternalFrame();
    	//Caminho da aplicação
    	separadorPath = System.getProperty("file.separator");
		caminho = new File("").getAbsolutePath();
    	 
    	/* CARREGA LISTA PARA COMBO COM OS NOMES DOS CLIENTES COM FATURAMENTO EM ABERTO */
   		geraRpsDAO = new TabGerarRpsFaturaDAO();
    	listGeraRps = new ArrayList<TabGerarRpsFaturaVO>();
    	try {
    		listGeraRps = (ArrayList<TabGerarRpsFaturaVO>) geraRpsDAO.carregaComboNome();
		} catch (IllegalAccessException | InstantiationException | SQLException e) {
			e.printStackTrace();
		}finally{
			//Finaliza a conexão com a base de dados
		}
    	
    	jtbBarraFerramenta = new JToolBar();jtbBarraFerramenta.setOrientation(javax.swing.SwingConstants.VERTICAL);
    	jtbBarraFerramenta.setFloatable(false);
    	
    	lblNome = new JLabel("Nome do Cliente"); lblDatainicio = new JLabel("Data de Início"); lblDatafinal = new JLabel("Data Final");
    	
		jcbNome = new JComboBox(); jcbNome.setEditable(true); jcbNome.setPreferredSize(new Dimension(130, 25));
    	jcbNome.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() != 38 && e.getKeyCode() != 40 && e.getKeyCode() != 10) {
					String a = jcbNome.getEditor().getItem().toString();
					jcbNome.removeAllItems();
					boolean showPopup = false;

					for (TabGerarRpsFaturaVO rps : listGeraRps) {
						if ( rps.getNome().toString().indexOf(a) != -1) {
							jcbNome.addItem(rps.getNome());
							showPopup = true;
						}
					}
					jcbNome.getEditor().setItem(new String(a));
					JTextField textField = (JTextField) e.getSource();
					textField.setCaretPosition(textField.getDocument().getLength());
					jcbNome.hidePopup();
					if (showPopup) {
						jcbNome.showPopup();
					}
				}
			}
		});
    	jcbNome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (jcbNome.getSelectedIndex() == -1) {
					//WHY selection do not work without this line !!!
					jcbNome.setSelectedItem(jcbNome.getItemAt(0));
				}
			}
		});

    	jtxtDatainicio = new JXDatePicker();
    	jtxtDatainicio.setMaximumSize(new java.awt.Dimension(150, 25));
    	jtxtDatainicio.setMinimumSize(new java.awt.Dimension(150, 25));
    	jtxtDatainicio.setPreferredSize(new java.awt.Dimension(150, 25));
    	jtxtDatainicio.addActionListener(this);
    	
    	jtxtDatafinal = new JXDatePicker();
    	jtxtDatafinal.setMaximumSize(new java.awt.Dimension(150, 25));
    	jtxtDatafinal.setMinimumSize(new java.awt.Dimension(150, 25));
    	jtxtDatafinal.setPreferredSize(new java.awt.Dimension(150, 25));
    	jtxtDatafinal.addActionListener(this);
    	
		faturar = new JCheckBox();
    	btoLocalizar = new JButton(); btoLocalizar.setToolTipText("Localizar"); 
    	btoLocalizar.setIcon(new ImageIcon(caminho+separadorPath+"imagens"+separadorPath+"icone"+separadorPath+"hd_lupa_32x32.png"));
 
    	btoCancelar = new JButton(""); btoCancelar.setToolTipText("Cancelar");
    	btoCancelar.setIcon(new ImageIcon(caminho+separadorPath+"imagens"+separadorPath+"icone"+separadorPath+"hd_cancela_32x32.png"));
    	
    	btoSelecionaTodos = new JButton(); btoSelecionaTodos.setToolTipText("Selecionar Todos");
    	btoSelecionaTodos.setIcon(new ImageIcon(caminho+separadorPath+"imagens"+separadorPath+"icone"+separadorPath+"selecionar_32x32.png"));
    	
    	btoDesselecionaTodos = new JButton(); btoDesselecionaTodos.setToolTipText("Desselecionar Todos");
    	btoDesselecionaTodos.setIcon(new ImageIcon(caminho+separadorPath+"imagens"+separadorPath+"icone"+separadorPath+"desseleciona_32x32.png"));
    	
    	btoExportar = new JButton(); btoExportar.setToolTipText("Exportar RPS");
    	btoExportar.setIcon(new ImageIcon(caminho+separadorPath+"imagens"+separadorPath+"icone"+separadorPath+"exportar_32x32.png"));

    	btoSair = new JButton(""); btoSair.setToolTipText("Sair");
    	btoSair.setIcon(new ImageIcon(caminho+separadorPath+"imagens"+separadorPath+"icone"+separadorPath+"cancelar_circular_32x32.png"));    	
    	btoSair.requestFocus();
    	
    	jpTabela = new JPanel();jpComponentes = new JPanel();jpFaturar = new JPanel();jpBtoAcoes = new JPanel();
    	
    	javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jpBtoAcoes);
    	
    	jpBtoAcoes.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 385, Short.MAX_VALUE)
        );
    	
    	jtTabela = new JTable();jtTabela.addMouseListener(this);
    	        
    	//TIRA O AUTO-DIMENCIONAR DA CELULA ASSIM APARECEM AS BARRAS DE ROLAGEM
    	jtTabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    	
     	GridBagConstraints cons = new GridBagConstraints();
    	Container jpComponenntes = getContentPane();
    	GridBagLayout componentes = new GridBagLayout();
    	jpComponentes.setLayout(componentes);
    	jpComponentes.setSize(400,430);
    	cons.insets = new Insets(5,3,5,3);
    	cons.anchor = GridBagConstraints.NORTHWEST;
     	
    	cons.gridx = 1;cons.gridy = 3;cons.gridwidth = 2;cons.ipadx = 30;
    	jpComponentes.add(lblNome, cons);
    	cons.gridx = 3;cons.gridy = 3;cons.gridwidth = 8;cons.ipadx = 260;
    	jpComponentes.add(jcbNome, cons);
    	jcbNome.addActionListener(this);jcbNome.addFocusListener(this);jcbNome.addMouseListener(this);
    	    	
    	cons.gridx = 1;cons.gridy = 4;cons.gridwidth = 2;cons.ipadx = 30;
    	jpComponentes.add(lblDatainicio, cons);
    	cons.gridx = 3;cons.gridy = 4;cons.gridwidth = 2;cons.ipadx = 5;
    	jpComponentes.add(jtxtDatainicio, cons);
    	jtxtDatainicio.addActionListener(this);jtxtDatainicio.addFocusListener(this);jtxtDatainicio.addMouseListener(this);
    	
    	cons.gridx = 5;cons.gridy = 4;cons.gridwidth = 2;cons.ipadx = 30;
    	lblDatafinal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    	jpComponentes.add(lblDatafinal, cons);
    	cons.gridx = 7;cons.gridy = 4;cons.gridwidth = 2;cons.ipadx = 5;
    	jpComponentes.add(jtxtDatafinal, cons);
    	jtxtDatafinal.addActionListener(this);jtxtDatafinal.addFocusListener(this);jtxtDatafinal.addMouseListener(this);
    	
    	content.add(jpComponentes, BorderLayout.NORTH);
    	
    	/* Botoes para a selecao das colunas de checkbox */
    	jpBtoAcoes.setLayout(componentes);
		cons.anchor = GridBagConstraints.EAST; cons.fill = GridBagConstraints.BOTH;
    	cons.gridx = 12;cons.gridy = 3;cons.gridwidth = 1;cons.ipadx = 10;
    	jtbBarraFerramenta.add(btoLocalizar, cons); btoLocalizar.addActionListener(this);
    	
    	cons.gridx = 12;cons.gridy = 4;cons.gridwidth = 1;cons.ipadx = 10;
    	jtbBarraFerramenta.add(btoCancelar, cons); btoCancelar.setEnabled(false); btoCancelar.addActionListener(this);
    
    	cons.gridx = 16;cons.gridy = 5;cons.gridwidth = 1;cons.ipadx = 10;
    	jtbBarraFerramenta.add(btoSelecionaTodos, cons); btoSelecionaTodos.setEnabled(false); btoSelecionaTodos.addActionListener(this);
    	
    	cons.gridx = 16;cons.gridy = 6;cons.gridwidth = 1;cons.ipadx = 10;
    	jtbBarraFerramenta.add(btoDesselecionaTodos, cons); btoDesselecionaTodos.setEnabled(false);	btoDesselecionaTodos.addActionListener(this);
    	
    	cons.gridx = 16;cons.gridy = 7;cons.gridwidth = 1;cons.ipadx = 10;
    	jtbBarraFerramenta.add(btoExportar, cons); btoExportar.setEnabled(false); btoExportar.addActionListener(this);
    	
    	cons.gridx = 12;cons.gridy = 8;cons.gridwidth = 1;cons.ipadx = 10;
    	jtbBarraFerramenta.add(btoSair, cons);
    	btoSair.addActionListener(this);
    	
    	jpBtoAcoes.add(jtbBarraFerramenta);
    	
    	content.add(jpBtoAcoes, BorderLayout.EAST);
    	
    	/* Variaveis para controle de direita, esquerda e centro nas colunas do JTable */
    	tcrDireta = new DefaultTableCellRenderer();tcrDireta.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    	tcrEsqueda = new DefaultTableCellRenderer();tcrEsqueda.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
    	tcrCentro = new DefaultTableCellRenderer();tcrCentro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    	
    	colModel = jtTabela.getColumnModel();
    	jspPane = new JScrollPane(jtTabela);
    	content.add(jspPane, BorderLayout.CENTER);
    	
    	sdf = new SimpleDateFormat("dd/MM/yyyy");
    	sdfTimestamp = new SimpleDateFormat("yyyy/MM/dd");
    	
        jtTabela.addMouseListener(this);  
    	jtTabela.setCellSelectionEnabled(true);
    	jtTabela.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    	
    	faturarLista = new ArrayList<TabFaturaOrdensVO>();
    	
    	/* CARREGA A COMBO COM OS NOMES DOS CLIENTES COM FATURAMENTO EM ABERTO */
		jcbNome.addItem("");
		for(TabGerarRpsFaturaVO geraRpsVO : listGeraRps){
			jcbNome.addItem(geraRpsVO.getNome());
		}
      
    }
    public void focusGained(FocusEvent c){
    }
    public void focusLost(FocusEvent c){
    }
    
    public void mouseClicked(MouseEvent c){    	
    }
    public void mouseEntered(MouseEvent c) {
	}
	public void mouseExited(MouseEvent c) {
	}
	public void mousePressed(MouseEvent c) {	
		mousePressed = true;
	}
	public void mouseReleased(MouseEvent c) {
	}
    /*
     * (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent c){
    //CONTROLE QUE LISTA OS FATURASMENTOS POR UM DETERMINADO PERIODO      	    
        if (c.getSource() == btoLocalizar && jcbNome.getSelectedItem().toString().isEmpty() && jtxtDatainicio.getDate() != null && jtxtDatafinal.getDate() != null){
        	 try{
   	    		TabGerarRpsFaturaVO geraRpsVO = null; 	 TabGerarRpsFaturaDAO geraRpsDAO = null; ConvertStringToDate convDate = null;
	    		java.util.Date dtInicio= null, dtFinal=null; java.lang.String nm=null;
	    		try{
	    			convDate = new ConvertStringToDate();
   	    			dtInicio = new java.sql.Timestamp(jtxtDatainicio.getDate().getTime());
   	    			dtFinal = new java.sql.Timestamp(jtxtDatafinal.getDate().getTime());
	    		}catch(Exception ex){
	    		//	JOptionPane.showMessageDialog(this, "Data inválida !","", JOptionPane.INFORMATION_MESSAGE);
	    		}
	    		geraRpsVO = new TabGerarRpsFaturaVO();
	    		geraRpsVO.setDataInicioDate(dtInicio);
	    		geraRpsVO.setDataFinalDate(dtFinal);
	    		
	    		listaGeraRpsVO = new ArrayList<TabGerarRpsFaturaVO>();
	    		geraRpsDAO = new TabGerarRpsFaturaDAO();

				try {
					listaGeraRpsVO = (ArrayList<TabGerarRpsFaturaVO>) geraRpsDAO.readRpsByPeriodo(geraRpsVO);
				} catch (ParseException | IllegalAccessException | InstantiationException e) {
					e.printStackTrace();
				}

				ModeloTabelaGerarRps mt = new ModeloTabelaGerarRps(listaGeraRpsVO);
				jtTabela.setModel(mt);
				
	    		jtTabela.getColumnModel().getColumn(0).setPreferredWidth(30);//colModel.getColumn(0).setCellRenderer(tcrCentro); Não pode ter este CellRender pois tira a combo de selecao
	    		jtTabela.getColumnModel().getColumn(1).setPreferredWidth(60);colModel.getColumn(1).setCellRenderer(tcrCentro);//Codigo
	    		jtTabela.getColumnModel().getColumn(2).setPreferredWidth(100);colModel.getColumn(2).setCellRenderer(tcrDireta);//Data Faturamento
	    		jtTabela.getColumnModel().getColumn(3).setPreferredWidth(40);colModel.getColumn(3).setCellRenderer(tcrDireta);//Numero RPS
	    		jtTabela.getColumnModel().getColumn(4).setPreferredWidth(200);colModel.getColumn(4).setCellRenderer(tcrEsqueda);//Nome cliente
	    		jtTabela.getColumnModel().getColumn(5).setPreferredWidth(100);colModel.getColumn(5).setCellRenderer(tcrDireta);//Ordens faturadas
	    		jtTabela.getColumnModel().getColumn(6).setPreferredWidth(80);colModel.getColumn(6).setCellRenderer(tcrDireta);//Valor
	    		
	    		if(!listaGeraRpsVO.isEmpty()){
	     			btoLocalizar.setEnabled(false); btoCancelar.setEnabled(true); btoSair.setEnabled(true); btoSelecionaTodos.setEnabled(true);
	     			btoDesselecionaTodos.setEnabled(true); btoExportar.setEnabled(true);
	     		}

     		}catch(SQLException e){
    			JOptionPane.showMessageDialog(this, "Erro ao localizar as informações.","",JOptionPane.INFORMATION_MESSAGE);
       		}
    	}
        //CONTROLE QUE LISTA OS FATURASMENTOS PELO NOME DO CLIENTE E UM DETERMINADO PERIODO      	    
        if (c.getSource() == btoLocalizar && !jcbNome.getSelectedItem().toString().isEmpty() && jtxtDatainicio.getDate() != null && jtxtDatafinal.getDate() != null){
        	 try{
   	    		TabGerarRpsFaturaVO geraRpsVO = null; 	 TabGerarRpsFaturaDAO geraRpsDAO = null; ConvertStringToDate convDate = null;
	    		java.util.Date dtInicio= null, dtFinal=null; java.lang.String nm=null;
	    		try{
	    			convDate = new ConvertStringToDate();
   	    			dtInicio = new java.sql.Timestamp(jtxtDatainicio.getDate().getTime());
   	    			dtFinal = new java.sql.Timestamp(jtxtDatafinal.getDate().getTime());
   	    			nm = jcbNome.getSelectedItem().toString();
	    		}catch(Exception ex){
	    		//	JOptionPane.showMessageDialog(this, "Data inválida !","", JOptionPane.INFORMATION_MESSAGE);
	    		}
	    		geraRpsVO = new TabGerarRpsFaturaVO();
	    		geraRpsVO.setNome(nm);
	    		geraRpsVO.setDataInicioDate(dtInicio);
	    		geraRpsVO.setDataFinalDate(dtFinal);
	    		
	    		listaGeraRpsVO = new ArrayList<TabGerarRpsFaturaVO>();
	    		geraRpsDAO = new TabGerarRpsFaturaDAO();

				try {
					listaGeraRpsVO = (ArrayList<TabGerarRpsFaturaVO>) geraRpsDAO.readRpsByPeriodoAndNome(geraRpsVO);
				} catch (ParseException | IllegalAccessException | InstantiationException e) {
					e.printStackTrace();
				}

				ModeloTabelaGerarRps mt = new ModeloTabelaGerarRps(listaGeraRpsVO);
				jtTabela.setModel(mt);
				
	    		jtTabela.getColumnModel().getColumn(0).setPreferredWidth(30);//colModel.getColumn(0).setCellRenderer(tcrCentro); Não pode ter este CellRender pois tira a combo de selecao
	    		jtTabela.getColumnModel().getColumn(1).setPreferredWidth(60);colModel.getColumn(1).setCellRenderer(tcrCentro);//Codigo
	    		jtTabela.getColumnModel().getColumn(2).setPreferredWidth(100);colModel.getColumn(2).setCellRenderer(tcrDireta);//Data Faturamento
	    		jtTabela.getColumnModel().getColumn(3).setPreferredWidth(40);colModel.getColumn(3).setCellRenderer(tcrDireta);//Numero RPS
	    		jtTabela.getColumnModel().getColumn(4).setPreferredWidth(200);colModel.getColumn(4).setCellRenderer(tcrEsqueda);//Nome cliente
	    		jtTabela.getColumnModel().getColumn(5).setPreferredWidth(100);colModel.getColumn(5).setCellRenderer(tcrDireta);//Ordens faturadas
	    		jtTabela.getColumnModel().getColumn(6).setPreferredWidth(80);colModel.getColumn(6).setCellRenderer(tcrDireta);//Valor
	    		
	    		if(!listaGeraRpsVO.isEmpty()){
	     			btoLocalizar.setEnabled(false); btoCancelar.setEnabled(true); btoSair.setEnabled(true); btoSelecionaTodos.setEnabled(true);
	     			btoDesselecionaTodos.setEnabled(true); btoExportar.setEnabled(true);
	     		}

     		}catch(SQLException e){
    			JOptionPane.showMessageDialog(this, "Erro ao localizar as informações.","",JOptionPane.INFORMATION_MESSAGE);
       		}
    	}
        
        /* Acao do botao de selecionar, esta acao marca todas as checkbox. */
        if(c.getSource() == btoSelecionaTodos){
        	for(int i=0; i < listaGeraRpsVO.size(); i++){
            	jtTabela.setValueAt(true, i, 0);
            	jtTabela.changeSelection(i, 0, true, true);
        	}
        	selecionado = true;
        }
        
        /* Acao do botao de desselecionar, esta acao marca todas as checkbox. */
        if(c.getSource() == btoDesselecionaTodos){
        	for(int i=0; i < listaGeraRpsVO.size(); i++){
            	jtTabela.setValueAt(false, i, 0);
            	jtTabela.changeSelection(i, 0, false, false);
        	}
        }
        
        /* Acao do botao faturar, esta acao gerar o faturamento dos clientes */
        if(c.getSource() == btoExportar){
        	try {
//        	List<TabFaturaOrdensVO> somaFaturas = new ArrayList<TabFaturaOrdensVO>();
//        	TabFaturaOrdensVO ftVO = null; TabFaturaOrdensVO tf =null;
//	    	TabFaturaOrdensDAO ftDAO = null; 
//	    	GerarArquivoRps arqRps = new GerarArquivoRps();
        	jtTabela.getModel(); List<Object> obj = new ArrayList<Object>();
//        	ModeloTabelaGerarRps mtrps = new ModeloTabelaGerarRps(listaGeraRpsVO);
        	ModeloTabelaGerarRps mtrps1 = (ModeloTabelaGerarRps) jtTabela.getModel();
        	obj = mtrps1.check;
	        for (Object o : mtrps1.check){
	        	if(o.equals(true))
	        	new GerarArquivoRps().gerarArquivoRps(listaGeraRpsVO);
	        }
	        
			} catch (IOException | ParseException | IllegalAccessException | InstantiationException | SQLException e) {
				e.printStackTrace();
			}
        }
        
	    if (c.getSource()== btoCancelar){// && aModel != null){
			//Limpa o grid
	    	jtTabela.setModel(new DefaultTableModel());
			//Trata os botoes
			btoLocalizar.setEnabled(true); btoCancelar.setEnabled(false); btoSelecionaTodos.setEnabled(false); btoDesselecionaTodos.setEnabled(false);
			btoExportar.setEnabled(false); btoSair.setEnabled(true);
		}
	    
	    if (c.getSource()== btoSair){
	    	int resultado = JOptionPane.showConfirmDialog(this,"Deseja sair da tabela gerar RPS?", "ControlMoto", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
			if(resultado == 0){
				TabGerarRpsFatura.this.dispose();
			}else{
				return;
			}
	    }
	    
    }
      
	private MaskFormatter setMascara(String mascara){
		MaskFormatter mask = null;
		try{
			mask = new MaskFormatter(mascara);
		}catch(java.text.ParseException e){
		}
		return mask;
	}
    
	}

/* Acao do botao faturar, esta acao gerar o faturamento dos clientes */
//if(c.getSource() == btoExportar){
//	List<TabFaturaOrdensVO> somaFaturas = new ArrayList<TabFaturaOrdensVO>();
//	TabFaturaOrdensVO ftVO = null; TabFaturaOrdensVO tf =null;
//	TabFaturaOrdensDAO ftDAO = null;
//	if(selecionado==false){
//	    for(TabFaturaOrdensVO fatVO : posicao){//faturarLista
//	    	if(fatVO.getFaturar() == true){
//		    	if(!somaFaturas.isEmpty()){
//		    		tf = (TabFaturaOrdensVO) somaFaturas.get(0);
//		    	if(tf.getNomeCliente().equals(fatVO.getNomeCliente()))
//		    			ftVO = new TabFaturaOrdensVO();
//		    			ftVO.setNumeroRps(tf.getNumeroRps());
//		    			//ftVO.setClienteFk(tf.getClienteFk());
//		    			ftVO.setNomeCliente(fatVO.getNomeCliente());
//			    		ftVO.setOrdemPk(fatVO.getOrdemPk());
//			    		ftVO.setTotalCliente(fatVO.getTotalCliente());
//			    		ftVO.setOrdensFaturadas( tf.getOrdensFaturadas()+"-"+String.valueOf(fatVO.getOrdemPk()));
//			    		ftVO.setTotalCliente( String.valueOf(Double.valueOf(tf.getTotalCliente()) + Double.valueOf(ftVO.getTotalCliente()) ));
//			    		somaFaturas.set(0, ftVO);
//		    	}else{
//		    		ftDAO = new TabFaturaOrdensDAO();
//		    		int nRps = 0;
//					try {
//						nRps = ftDAO.getNumeroRps();
//					} catch (SQLException | InstantiationException | IllegalAccessException e) {
//						e.printStackTrace();
//					}
//		    		ftVO = new TabFaturaOrdensVO();
//	    			ftVO.setNumeroRps(nRps+1);
//	    			ftVO.setNomeCliente(fatVO.getNomeCliente());
//	    			//ftVO.setClienteFk(fatVO.getClienteFk());
//	    			ftVO.setOrdensFaturadas(String.valueOf(fatVO.getOrdemPk()));
//	    			ftVO.setTotalCliente(fatVO.getTotalCliente());
//	    			somaFaturas.add(ftVO);		    		
//		    	}
//		    }
//		    try {
//		    	ftDAO = new TabFaturaOrdensDAO();
//				ftDAO.saveOrdensServicoFaturar(somaFaturas);
//				ftDAO.saveNumeroRpsOS(ftVO);
//			} catch (SQLException | InstantiationException | IllegalAccessException e) {
//				e.printStackTrace();
//			}
//	    }
//	}
