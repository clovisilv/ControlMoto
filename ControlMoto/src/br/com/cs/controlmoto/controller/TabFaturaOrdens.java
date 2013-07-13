/**
 * Company Fictec Cons. Inf.
 * @since 06/09/2012
 * @version 1.0.6
 * @author Clovis
 */
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
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.text.MaskFormatter;

import org.jdesktop.swingx.JXDatePicker;

import br.com.cs.controlmoto.domain.TabFaturaOrdensDAO;
import br.com.cs.controlmoto.utils.ConvertStringToDate;
import br.com.cs.controlmoto.utils.FixedLengthDocument;
import br.com.cs.controlmoto.utils.FormataValor;
import br.com.cs.controlmoto.utils.ModeloTabela;
import br.com.cs.controlmoto.utils.ValidaData;
import br.com.cs.controlmoto.vo.TabFaturaOrdensVO;
/**
 * Company Fictec Cons. Inf.
 * @since 06/09/2012
 * @version 1.0.6
 * @author Clovis
 * Class para gerar o faturamento das OS
 */

public class TabFaturaOrdens extends JInternalFrame implements ActionListener, FocusListener, MouseListener {
	/*
	 * 
	 */
	private static final long serialVersionUID = -1379654176912208199L;
	
	JToolBar jtbBarraFerramenta;

	JLabel	lblNome, lblDatainicio, lblDatafinal, lblOrdem, lblFaturar, lblValorFaturar;
	
	JTextField 	txtOrdem;
	
	JComboBox jcbNome;
	
	String caminho, separadorPath;
	
//	JFormattedTextField jtxtDatainicio, jtxtDatafinal;
	JXDatePicker jtxtDatainicio, jtxtDatafinal;
	
	JButton	btoOrdem, btoLocalizar, btoCancelar, btoSair, btoFaturar, btoSelecionaTodos, btoDesselecionaTodos;
	
	DefaultTableModel aModel;
	JTable jtTabela;
	JScrollPane jspPane;
//	ListSelectionModel cellSelectionModel;
	DefaultTableCellRenderer tcrDireta, tcrEsqueda, tcrCentro;
	TableModel tcrDireta1;	TableColumnModel colModel; ModeloTabela mt;
	
	JPanel jpBotao, jpTabela, jpComponentes, jpFaturar, jpBtoAcoes;
	JInternalFrame tabConsultaOrdens;
	private ControlMoto	telaControlMoto;
	
	Container content;
	
	JCheckBox windowClosable, faturar;
	
	List<TabFaturaOrdensVO> listFaturaOrdensVO;
	
	boolean	data, mousePressed;
//	Date datas;
	DateFormat formatar;
//	NumberFormat vr;
	SimpleDateFormat sdf,sdfTimestamp;
//	Timestamp timestamp;
//	static String[] linhasSelecionadas=null;
//	static List<TabFaturaOrdensVO> faturaOrdem=null;
	List<TabFaturaOrdensVO> posicao=null;
	static List<TabFaturaOrdensVO> faturarLista=null;
	FormataValor ftValor;

	static boolean selecionado=false;
	//Serve para mover a tela
    static int openFrameCount = 7;
    static final int xOffset = 25, yOffset = 11;
    
    public TabFaturaOrdens(String titulo, ControlMoto controlMoto){
    	   	
    	super(titulo,false,true,false,true);
    	setSize(700,430);
    	setVisible(true);
    	setLocation(xOffset*openFrameCount, yOffset*openFrameCount);
//    	ImageIcon icone = new ImageIcon("..//ControlMoto/imagens/logo_cs_b.gif");
//    	ImageIcon icone = new ImageIcon(".."+separadorPath+separadorPath+"ControlMoto"+separadorPath+"imagens"+separadorPath+"logo_cs_b.gif");
//    	setIconImage(icone.getImage());
//    	TabConsultaOrdens.setDefaultLocale(null);
    	
    	//Caminho da aplicação
    	separadorPath = System.getProperty("file.separator");
		caminho = new File("").getAbsolutePath();
		
    	this.telaControlMoto = telaControlMoto;
    	controlMoto.jDesktopPane.add(this);
   
    	content = getContentPane();
    	content.setLayout(new BorderLayout());
    	
    	formatar = new SimpleDateFormat().getDateInstance();
    	
    	tabConsultaOrdens = new JInternalFrame();
    	
    	jtbBarraFerramenta = new JToolBar(); jtbBarraFerramenta.setOrientation(javax.swing.SwingConstants.VERTICAL);
    	jtbBarraFerramenta.setFloatable(false);
    	
    	lblOrdem = new JLabel("Número da O.S.");lblNome = new JLabel("Nome do Cliente");lblDatainicio = new JLabel("Data de Início");
    	lblDatafinal = new JLabel("Data Final");lblFaturar = new JLabel("Valor a Faturar : ");lblValorFaturar = new JLabel();
    	
    	jcbNome = new JComboBox(); jcbNome.setPreferredSize(new Dimension(100, 25)); 
    	txtOrdem = new JTextField(); txtOrdem.setDocument(new FixedLengthDocument(10));

    	jtxtDatainicio = new JXDatePicker();
    	jtxtDatainicio.setMaximumSize(new java.awt.Dimension(150, 22));
    	jtxtDatainicio.setMinimumSize(new java.awt.Dimension(150, 22));
    	jtxtDatainicio.setPreferredSize(new java.awt.Dimension(150, 22));
    	jtxtDatainicio.addActionListener(this);
    	
    	jtxtDatafinal = new JXDatePicker();
    	jtxtDatafinal.setMaximumSize(new java.awt.Dimension(150, 22));
    	jtxtDatafinal.setMinimumSize(new java.awt.Dimension(150, 22));
    	jtxtDatafinal.setPreferredSize(new java.awt.Dimension(150, 22));
    	jtxtDatafinal.addActionListener(this);
    	
		faturar = new JCheckBox();
    	btoLocalizar = new JButton(); btoLocalizar.setToolTipText("Localizar");
    	btoLocalizar.setIcon(new ImageIcon(caminho+separadorPath+"imagens"+separadorPath+"icone"+separadorPath+"hd_lupa_32x32.png"));
    	
    	btoCancelar = new JButton(); btoCancelar.setToolTipText("Cancelar");
    	btoCancelar.setIcon(new ImageIcon(caminho+separadorPath+"imagens"+separadorPath+"icone"+separadorPath+"hd_cancela_32x32.png"));
    	
    	btoSelecionaTodos = new JButton(); btoSelecionaTodos.setToolTipText("Selecionar Todos");
    	btoSelecionaTodos.setIcon(new ImageIcon(caminho+separadorPath+"imagens"+separadorPath+"icone"+separadorPath+"selecionar_32x32.png"));
    	
    	btoDesselecionaTodos = new JButton(); btoDesselecionaTodos.setToolTipText("Desselecionar Todos");
    	btoDesselecionaTodos.setIcon(new ImageIcon(caminho+separadorPath+"imagens"+separadorPath+"icone"+separadorPath+"desseleciona_32x32.png"));
    	
    	btoFaturar = new JButton(); btoFaturar.setToolTipText("Faturar");
    	btoFaturar.setIcon(new ImageIcon(caminho+separadorPath+"imagens"+separadorPath+"icone"+separadorPath+"cofre_32x32.png"));
    	
    	btoSair = new JButton(); btoSair.setToolTipText("Sair");
    	btoSair.setIcon(new ImageIcon(caminho+separadorPath+"imagens"+separadorPath+"icone"+separadorPath+"cancelar_circular_32x32.png"));    	
    	btoSair.requestFocus();
    	
    	jpBotao = new JPanel();jpTabela = new JPanel();jpComponentes = new JPanel();jpFaturar = new JPanel();jpBtoAcoes = new JPanel();
    	
    	jtTabela = new JTable();jtTabela.addMouseListener(this);
    	        
    	//TIRA O AUTO-DIMENCIONAR DA CELULA ASSIM APARECEM AS BARRAS DE ROLAGEM
    	jtTabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    	
     	GridBagConstraints cons = new GridBagConstraints();
    	Container jpComponenntes = getContentPane();
    	GridBagLayout componentes = new GridBagLayout();
    	jpComponentes.setLayout(componentes);
    	jpComponentes.setSize(500,430);
    	cons.insets = new Insets(5,3,5,3);
    	cons.anchor = GridBagConstraints.NORTHWEST;
    	  	
    	cons.gridx = 1;cons.gridy = 3;cons.gridwidth = 2;cons.ipadx = 30;
    	jpComponentes.add(lblOrdem, cons);
    	cons.gridx = 3;cons.gridy = 3;cons.gridwidth = 2;cons.ipadx = 70;
    	txtOrdem.setHorizontalAlignment(SwingConstants.RIGHT);
    	jpComponentes.add(txtOrdem, cons); txtOrdem.addActionListener(this);txtOrdem.addMouseListener(this);
    	txtOrdem.setToolTipText("Lista somente esta ordem de serviço.");
//    	txtOrdem.addKeyListener(new KeyAdapter() {
//    		public void keyReleased(KeyEvent e){
//    			if(e.getKeyCode() != 38 && e.getKeyCode() != 40 && e.getKeyCode() != 10){
//    				JOptionPane.showMessageDialog(null, "Se o número da ordem de serviço for preenchido\nas outras opções de filtros serão ignoradas.", "ControlMoto", JOptionPane.OK_OPTION);
//    			}
//    		}
//		});
    	
    	listFaturaOrdensVO = new ArrayList<TabFaturaOrdensVO>();
		TabFaturaOrdensVO faturaOrdensVO = new TabFaturaOrdensVO();
		try {
			listFaturaOrdensVO = new TabFaturaOrdensDAO().searchClienteByCombo();
		} catch (IllegalAccessException | InstantiationException | SQLException e) {
			e.printStackTrace();
		}
		
    	cons.gridx = 1;cons.gridy = 5;cons.gridwidth = 2;cons.ipadx = 30;
    	jpComponentes.add(lblNome, cons);
    	cons.gridx = 3;cons.gridy = 5;cons.gridwidth = 6;cons.ipadx = 280;
    	jpComponentes.add(jcbNome, cons); jcbNome.setEditable(true); jcbNome.setSelectedItem("");
    	jcbNome.addActionListener(this); jcbNome.addFocusListener(this); jcbNome.addMouseListener(this); 
    	jcbNome.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() != 38 && e.getKeyCode() != 40 && e.getKeyCode() != 10) {
					String a = jcbNome.getEditor().getItem().toString();
					jcbNome.removeAllItems();
					boolean showPopup = false;

					for (TabFaturaOrdensVO ft : listFaturaOrdensVO) {
						if ( ft.getNomeCliente().toString().indexOf(a) != -1) {
							jcbNome.addItem(ft.getNomeCliente());
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
    	    	
    	cons.gridx = 1;cons.gridy = 7;cons.gridwidth = 2;cons.ipadx = 30;
    	jpComponentes.add(lblDatainicio, cons);
    	cons.gridx = 3;cons.gridy = 7;cons.gridwidth = 1;cons.ipadx = 2;
    	jpComponentes.add(jtxtDatainicio, cons);
    	jtxtDatainicio.addActionListener(this);jtxtDatainicio.addFocusListener(this);jtxtDatainicio.addMouseListener(this);
    	
    	cons.gridx = 6;cons.gridy = 7;cons.gridwidth = 2;cons.ipadx = 30;
    	lblDatafinal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    	jpComponentes.add(lblDatafinal, cons);
    	cons.gridx = 8;cons.gridy = 7;cons.gridwidth = 1;cons.ipadx = 2;
    	jpComponentes.add(jtxtDatafinal, cons); 
    	jtxtDatafinal.addActionListener(this);jtxtDatafinal.addFocusListener(this);jtxtDatafinal.addMouseListener(this);
    	
    	/* Determina o layout para os campos */
    	content.add(jpComponentes, BorderLayout.NORTH);
    	/* Barra de feramentas */
    	cons.anchor = GridBagConstraints.EAST; cons.fill = GridBagConstraints.BOTH;
    	cons.gridx = 12;cons.gridy = 3;cons.gridwidth = 1;cons.ipadx = 10;
    	jtbBarraFerramenta.add(btoLocalizar, cons); btoLocalizar.addActionListener(this);
    	
    	cons.gridx = 12;cons.gridy = 5;cons.gridwidth = 1;cons.ipadx = 10;
    	jtbBarraFerramenta.add(btoCancelar, cons); btoCancelar.setEnabled(false); btoCancelar.addActionListener(this);

    	cons.gridx = 16;cons.gridy = 2;cons .gridwidth = 1;cons.ipadx = 10;
    	jtbBarraFerramenta.add(btoSelecionaTodos, cons); btoSelecionaTodos.setEnabled(false); btoSelecionaTodos.addActionListener(this);
    	
    	cons.gridx = 16;cons.gridy = 5;cons.gridwidth = 1;cons.ipadx = 10;
    	jtbBarraFerramenta.add(btoDesselecionaTodos, cons); btoDesselecionaTodos.setEnabled(false); btoDesselecionaTodos.addActionListener(this);
    	
    	cons.gridx = 16;cons.gridy = 8;cons.gridwidth = 1;cons.ipadx = 10;
    	jtbBarraFerramenta.add(btoFaturar, cons); btoFaturar.setEnabled(false); btoFaturar.addActionListener(this);
    	
    	cons.gridx = 12;cons.gridy = 7;cons.gridwidth = 1;cons.ipadx = 10;
    	jtbBarraFerramenta.add(btoSair, cons); btoSair.addActionListener(this);

    	/* Determina o layout do JPanel de botoes para a selecao das colunas de checkbox */
    	jpBtoAcoes.setLayout(componentes);
    	
    	/* Adiciona o componente jtbBarraFerramenta ao jpBtoAcoes */
    	jpBtoAcoes.add(jtbBarraFerramenta);
    	content.add(jpBtoAcoes, BorderLayout.EAST);
    	
    	/* Coloca o campo lblFaturar na parte de baixo de do frame principal */
		cons.anchor = GridBagConstraints.EAST; cons.fill = GridBagConstraints.BOTH;
    	cons.gridx = 12;cons.gridy = 3;cons.gridwidth = 1;cons.ipadx = 10;
    	jpFaturar.add(lblFaturar, cons);
    	
		cons.anchor = GridBagConstraints.EAST; cons.fill = GridBagConstraints.BOTH;
    	cons.gridx = 12;cons.gridy = 3;cons.gridwidth = 1;cons.ipadx = 10;
    	jpFaturar.add(lblValorFaturar, cons);
    	
    	/* Coloca o jpFaturar na parte de baixo de do frame principal */
    	content.add(jpFaturar, BorderLayout.SOUTH);
    	
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
    	//Carrega a combobox com os nomes dos clientes
    	jcbNome.addItem("");
		for(TabFaturaOrdensVO ft : listFaturaOrdensVO){
			jcbNome.addItem(ft.getNomeCliente());
		}
    }
    
    public void focusGained(FocusEvent c){
    }
    public void focusLost(FocusEvent c){
    }
    
    public void mouseClicked(MouseEvent c){
    	//Controla os campos da tela ao clicar no campo txtOrdem
    	if (c.getSource() == txtOrdem){
    		txtOrdem.setEnabled(true);
    		jcbNome.getEditor().setItem(new String("")); jcbNome.setEnabled(false);
    		jtxtDatainicio.setDate(null); jtxtDatainicio.setEnabled(false);
    		jtxtDatafinal.setDate(null); jtxtDatafinal.setEnabled(false);
    	}
    	//Indica que a coluna do checkbox está selecionada
    	if(c.getSource() == jtTabela){
    		if(jtTabela.getRowSelectionAllowed())
    				selecionado = true;
    	}
    }
    public void mouseEntered(MouseEvent c) {
	}
	public void mouseExited(MouseEvent c) {
	}
	public void mousePressed(MouseEvent c) {
	}
	public void mouseReleased(MouseEvent c) {
	}
    /*
     * (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent c){
    //CONTROLE QUE LISTA AS ORDENS DE SERVICO PELO SEU CODIGO    	    
   	    if (c.getSource() == btoLocalizar && txtOrdem.getText() != null && !txtOrdem.getText().equals("")){
   	    	try{
	    		TabFaturaOrdensVO consultasVO = new TabFaturaOrdensVO();
	    		int CodOrdem = Integer.parseInt(txtOrdem.getText());
	    		consultasVO.setOrdemPk(Integer.valueOf(txtOrdem.getText()));

	    		TabFaturaOrdensDAO consultaDAO = new TabFaturaOrdensDAO();
				ArrayList<TabFaturaOrdensVO> o=null;
				faturarLista = new ArrayList<TabFaturaOrdensVO>();
				//Busca as infomrações no banco de dados e coloca no grid
				faturarLista = (List<TabFaturaOrdensVO>) consultaDAO.readOrdemServicoByPk(consultasVO);

	    		double totalFaturar = 0d;
	    		for (TabFaturaOrdensVO ft : faturarLista) {
					totalFaturar = totalFaturar + Double.valueOf(ft.getTotalCliente().replace(",", "."));
				}

				mt = new ModeloTabela(faturarLista);

				jtTabela.setModel(mt);

				jtTabela.getColumnModel().getColumn(0).setPreferredWidth(50);//Combobox para seleção
	    		jtTabela.getColumnModel().getColumn(1).setPreferredWidth(90);colModel.getColumn(1).setCellRenderer(tcrDireta);//Código OS
	    		jtTabela.getColumnModel().getColumn(2).setPreferredWidth(240);colModel.getColumn(2).setCellRenderer(tcrEsqueda);//Cliente
	    		jtTabela.getColumnModel().getColumn(3).setPreferredWidth(90);colModel.getColumn(3).setCellRenderer(tcrDireta);//Valor OS
	    		jtTabela.getColumnModel().getColumn(4).setPreferredWidth(88);colModel.getColumn(4).setCellRenderer(tcrDireta);//Data OS
	    		ftValor = new FormataValor();
	    		lblValorFaturar.setText(ftValor.formatValor(String.valueOf(totalFaturar)));//.replace(".", ","));
	    		
	    		if(!faturarLista.isEmpty()){
	    			btoLocalizar.setEnabled(false); btoCancelar.setEnabled(true); btoSair.setEnabled(true); btoSelecionaTodos.setEnabled(true);
	    			btoDesselecionaTodos.setEnabled(true); btoFaturar.setEnabled(true); txtOrdem.setEnabled(true); 
	    			//jtxtDatafinal.setEnabled(false); jtxtDatafinal.setEnabled(false);
	    		}
     		}catch(SQLException | IllegalAccessException | InstantiationException e){
    			JOptionPane.showMessageDialog(this, "Erro ao localizar as informações.\n"+e.getMessage(),"",JOptionPane.INFORMATION_MESSAGE);
       		}
   		}
   	    //CONTROLE QUE LISTA AS ORDENS DE COLETAS DENTRO DE UM DETERMINADO PERIODO      	    
        if (c.getSource() == btoLocalizar && jtxtDatainicio.getDate() != null && jcbNome.getSelectedItem().toString().isEmpty() && jcbNome.getEditor().getItem().toString().isEmpty()){
        	 try{
	    		TabFaturaOrdensVO consultasVO = new TabFaturaOrdensVO();
	    		java.sql.Timestamp dtaInicio= null, dtaTermino=null;
	    		try{
	    			dtaInicio = new java.sql.Timestamp(jtxtDatainicio.getDate().getTime());
	    			dtaTermino = new java.sql.Timestamp(jtxtDatafinal.getDate().getTime());
	    		}catch(Exception ex){
	    		//	JOptionPane.showMessageDialog(this, "Data inválida !","", JOptionPane.INFORMATION_MESSAGE);
	    		}
	    		TabFaturaOrdensDAO consultaDAO = new TabFaturaOrdensDAO();
				ArrayList<TabFaturaOrdensVO> o=null;
				faturarLista = new ArrayList<TabFaturaOrdensVO>();
				//Busca informacao no banco de dados
				faturarLista = (ArrayList<TabFaturaOrdensVO>) consultaDAO.readOrdemServicoByPeriodo(dtaInicio, dtaTermino);

	    		double totalFaturar = 0d;
	    		for (TabFaturaOrdensVO ft : faturarLista) {
					totalFaturar = totalFaturar + Double.valueOf(ft.getTotalCliente().replace(",", "."));
				}

				mt = new ModeloTabela(faturarLista);
				jtTabela.setModel(mt);
				jtTabela.getColumnModel().getColumn(0).setPreferredWidth(50);//Combobox para seleção
	    		jtTabela.getColumnModel().getColumn(1).setPreferredWidth(90);colModel.getColumn(1).setCellRenderer(tcrDireta);//Código OS
	    		jtTabela.getColumnModel().getColumn(2).setPreferredWidth(240);colModel.getColumn(2).setCellRenderer(tcrEsqueda);//Cliente
	    		jtTabela.getColumnModel().getColumn(3).setPreferredWidth(90);colModel.getColumn(3).setCellRenderer(tcrDireta);//Valor OS
	    		jtTabela.getColumnModel().getColumn(4).setPreferredWidth(88);colModel.getColumn(4).setCellRenderer(tcrDireta);//Data OS
	    		ftValor = new FormataValor();
	    		lblValorFaturar.setText(ftValor.formatValor(String.valueOf(totalFaturar)));//.replace(".", ","));
	      		
	    		if(!faturarLista.isEmpty()){
	     			btoLocalizar.setEnabled(false); btoCancelar.setEnabled(true); btoSair.setEnabled(true); btoSelecionaTodos.setEnabled(true);
	     			btoDesselecionaTodos.setEnabled(true); btoFaturar.setEnabled(true); txtOrdem.setEnabled(false);
	     			//jtxtDatafinal.setEnabled(true); jtxtDatafinal.setEnabled(true);
	     		}
     		}catch(SQLException | IllegalAccessException | InstantiationException e){
    			JOptionPane.showMessageDialog(this, "Erro ao localizar as informações.","",JOptionPane.INFORMATION_MESSAGE);
       		}
    	}
   	    //CONTROLE QUE LISTA AS ORDENS DE COLETAS DENTRO DE UM DETERMINADO PERIODO E NOME      	    
        if (c.getSource() == btoLocalizar && jtxtDatainicio.getDate() != null && !jcbNome.getSelectedItem().toString().isEmpty() && txtOrdem.getText().equals("")){;
        	 try{
	    		TabFaturaOrdensVO consultasVO = new TabFaturaOrdensVO();
	    		java.sql.Timestamp dtaInicio= null, dtaTermino=null; String nome = null;
	    		try{
	    			dtaInicio = new java.sql.Timestamp(jtxtDatainicio.getDate().getTime());
	    			dtaTermino = new java.sql.Timestamp(jtxtDatafinal.getDate().getTime());
	    			nome = jcbNome.getSelectedItem().toString();
	    		}catch(Exception ex){
	    		//	JOptionPane.showMessageDialog(this, "Data inválida !","", JOptionPane.INFORMATION_MESSAGE);
	    		}
	    		TabFaturaOrdensDAO consultaDAO = new TabFaturaOrdensDAO();
				ArrayList<TabFaturaOrdensVO> o=null;
				faturarLista = new ArrayList<TabFaturaOrdensVO>();
				//Busca informacao no banco de dados
				faturarLista = (ArrayList<TabFaturaOrdensVO>) consultaDAO.readOrdemServicoByPeriodoAndNome(dtaInicio, dtaTermino, nome);

	    		double totalFaturar = 0d;
	    		for (TabFaturaOrdensVO ft : faturarLista) {
					totalFaturar = totalFaturar + Double.valueOf(ft.getTotalCliente().replace(",", "."));
				}

				mt = new ModeloTabela(faturarLista);
				jtTabela.setModel(mt);
				jtTabela.getColumnModel().getColumn(0).setPreferredWidth(50);//Combobox para seleção
	    		jtTabela.getColumnModel().getColumn(1).setPreferredWidth(90);colModel.getColumn(1).setCellRenderer(tcrDireta);//Código OS
	    		jtTabela.getColumnModel().getColumn(2).setPreferredWidth(240);colModel.getColumn(2).setCellRenderer(tcrEsqueda);//Cliente
	    		jtTabela.getColumnModel().getColumn(3).setPreferredWidth(90);colModel.getColumn(3).setCellRenderer(tcrDireta);//Valor OS
	    		jtTabela.getColumnModel().getColumn(4).setPreferredWidth(88);colModel.getColumn(4).setCellRenderer(tcrDireta);//Data OS
	    		ftValor = new FormataValor();
	    		lblValorFaturar.setText(ftValor.formatValor(String.valueOf(totalFaturar)));//.replace(".", ","));
	      		
	    		if(!faturarLista.isEmpty()){
	     			btoLocalizar.setEnabled(false); btoCancelar.setEnabled(true); btoSair.setEnabled(true); btoSelecionaTodos.setEnabled(true);
	     			btoDesselecionaTodos.setEnabled(true); btoFaturar.setEnabled(true); txtOrdem.setEnabled(false); 
	     			//jtxtDatafinal.setEnabled(true); jtxtDatafinal.setEnabled(true);
	     		}
     		}catch(SQLException | IllegalAccessException | InstantiationException e){
    			JOptionPane.showMessageDialog(this, "Erro ao localizar as informações.","",JOptionPane.INFORMATION_MESSAGE);
       		}
    	}
        /* Acao do botao de selecionar, esta acao marca todas as checkbox. */
        if(c.getSource() == btoSelecionaTodos){
        	for(int i=0; i < faturarLista.size(); i++){
            	jtTabela.setValueAt(true, i, 0);
            	jtTabela.changeSelection(i, 0, true, true);
        	}
        	selecionado = true;
        }
        /**
         *  Acao do botao de desselecionar, esta acao marca todas as checkbox.  
         */
        if(c.getSource() == btoDesselecionaTodos){
        	for(int i=0; i < faturarLista.size(); i++){
            	jtTabela.setValueAt(false, i, 0);
            	jtTabela.changeSelection(i, 0, false, false);
        	}
        }
        /**
         *  Acao do botao faturar, esta acao gerar o faturamento dos clientes
         */
        if(c.getSource() == btoFaturar){
        	List<TabFaturaOrdensVO> somaFaturas = new ArrayList<TabFaturaOrdensVO>();
        	TabFaturaOrdensVO ftVO = null; TabFaturaOrdensVO tf =null; TabFaturaOrdensDAO ftDAO = null;
        	ConvertStringToDate convStringToDate = null; ValidaData vd = null; int cont= 0;java.util.Date dtf =null; 
        	if(selecionado==true){
		    	convStringToDate = new ConvertStringToDate(); vd = new ValidaData();String dataFat = null;
		    	do{
		    		if(cont == 0){
		    			dataFat = JOptionPane.showInputDialog(this, "Digite a data de faturamento.", "ControlMoto", JOptionPane.OK_CANCEL_OPTION);
		    			cont++;
		    		}else{
		    			dataFat = JOptionPane.showInputDialog(this, "Redigite a data de faturamento.\nData inválida.", "ControlMoto", JOptionPane.OK_CANCEL_OPTION);
		    		}
		    	}
		    	while(vd.validarData(dataFat) == false);
		    	
		    	try {
					dtf = convStringToDate.convertStringToDates(dataFat);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			    for(TabFaturaOrdensVO fatVO : posicao){//faturarLista
			    	if(fatVO.getFaturar() == true){
				    	if(!somaFaturas.isEmpty()){
				    		tf = (TabFaturaOrdensVO) somaFaturas.get(0);
				    	if(tf.getNomeCliente().equals(fatVO.getNomeCliente()))
				    			ftVO = new TabFaturaOrdensVO();
				    			ftVO.setNumeroRps(tf.getNumeroRps());
				    			//ftVO.setClienteFk(tf.getClienteFk());
				    			ftVO.setDataFaturamento(dtf);
				    			ftVO.setNomeCliente(fatVO.getNomeCliente());
					    		ftVO.setOrdemPk(fatVO.getOrdemPk());
					    		ftVO.setTotalCliente(fatVO.getTotalCliente());
					    		ftVO.setOrdensFaturadas( tf.getOrdensFaturadas()+"-"+String.valueOf(fatVO.getOrdemPk()));
					    		ftVO.setTotalCliente( String.valueOf(Double.valueOf(tf.getTotalCliente()) + Double.valueOf(ftVO.getTotalCliente()) ));
					    		somaFaturas.set(0, ftVO);
					    		try {
									ftDAO.updateSituacaoOS(fatVO);
								} catch (IllegalAccessException | InstantiationException | SQLException e) {
									e.printStackTrace();
								}
				    	}else{
				    		ftDAO = new TabFaturaOrdensDAO();
				    		int nRps = 0;
							try {
								nRps = ftDAO.getNumeroRps();
								ftDAO.updateSituacaoOS(fatVO);
							} catch (SQLException | InstantiationException | IllegalAccessException e) {
								e.printStackTrace();
							}
				    		ftVO = new TabFaturaOrdensVO();
			    			ftVO.setNumeroRps(nRps+1);
			    			ftVO.setNomeCliente(fatVO.getNomeCliente());
			    			//ftVO.setClienteFk(fatVO.getClienteFk());
			    			ftVO.setDataFaturamento(dtf);
			    			ftVO.setOrdensFaturadas(String.valueOf(fatVO.getOrdemPk()));
			    			ftVO.setTotalCliente(fatVO.getTotalCliente());
			    			somaFaturas.add(ftVO);		    		
				    	}
				    }
				    try {				    	
				    	ftDAO = new TabFaturaOrdensDAO();
						ftDAO.saveOrdensServicoFaturar(somaFaturas);
						ftDAO.saveNumeroRpsOS(ftVO);
					} catch (SQLException | InstantiationException | IllegalAccessException e) {
						e.printStackTrace();
					}
			    }//termina o for
	        	jtTabela.setModel(aModel = new DefaultTableModel());
        	}
        	else {
        		//carregaListaBotaoSeleciona();
        		JOptionPane.showMessageDialog(this, "Selecione um registro.", "ControlMoto", JOptionPane.OK_OPTION);
        	}        	

        }
        /**
         * Controla acao do botao btoCancelar
         */
	    if (c.getSource() == btoCancelar ){//&& mt != null){
			aModel = new DefaultTableModel();
			jtTabela.setModel(aModel);
			
			jcbNome.getEditor().setItem(new String(""));jcbNome.setEnabled(true);
			
			txtOrdem.setText(""); txtOrdem.setEnabled(true);
			
			jtxtDatainicio.setEnabled(true);jtxtDatafinal.setEnabled(true);
			
    		btoLocalizar.setEnabled(true); btoCancelar.setEnabled(false); btoSair.setEnabled(true); btoSair.setFocusable(true) ;
    		btoSelecionaTodos.setEnabled(false); btoDesselecionaTodos.setEnabled(false); btoFaturar.setEnabled(false);
    		
    		lblValorFaturar.setText("");
	    }
	    /**
	     * Controla acao do botao btoSair
	     */
	    if (c.getSource()== btoSair){
	    	int resultado = JOptionPane.showConfirmDialog(this,"Deseja sair da tabela de faturamento?", "ControlMoto", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
			if(resultado == 0){
				TabFaturaOrdens.this.dispose();
			}else{
				return;
			}
	    }
	    
    }
    /**
     * Metodo que contrala as mascaras nos campos jtxtDatainicio e jtxtDatafinal
     * @param mascara
     * @return
     */
	private MaskFormatter setMascara(String mascara){
		MaskFormatter mask = null;
		try{
			mask = new MaskFormatter(mascara);
		}catch(java.text.ParseException e){
		}
		return mask;
	}
	/**
	 * Metodo que carrega os dados a List com as informacoes do JTable
	 */
	public void carregaListaBotaoSeleciona(){
		posicao = new ArrayList<TabFaturaOrdensVO>(); TabFaturaOrdensVO ftVO1 = null; TabFaturaOrdensDAO ftDAO = null; int y=0;
		for (int i = 0; i < jtTabela.getRowCount(); i++) {
        	if(Boolean.valueOf(String.valueOf(jtTabela.getValueAt(i, 0))) == true){
            	ftVO1 = new TabFaturaOrdensVO();
            	ftVO1.setFaturar(Boolean.valueOf(String.valueOf(jtTabela.getValueAt(i, 0))));
            	ftVO1.setOrdemPk(Integer.valueOf(String.valueOf(jtTabela.getValueAt(i, 1))));
            	ftVO1.setNomeCliente(String.valueOf(jtTabela.getValueAt(i, 2)));
            	ftVO1.setTotalCliente(String.valueOf(jtTabela.getValueAt(i, 3)));
            	for(TabFaturaOrdensVO ftVO : faturarLista){
            		if(posicao.isEmpty()){
	            		if(ftVO.getOrdemPk() == ftVO1.getOrdemPk())
	            			posicao.add(ftVO1);
            		}else{
            			for(TabFaturaOrdensVO ftVOPosicao : posicao){
	            			if( ftVOPosicao.getNomeCliente().equals(ftVO.getNomeCliente()) && !ftVOPosicao.getOrdemPk().equals(ftVO1.getOrdemPk()) ){
		            			ftVO1 = new TabFaturaOrdensVO();
		                    	ftVO1.setFaturar(ftVO.getFaturar());
		                    	ftVO1.setOrdemPk(Integer.valueOf(String.valueOf(jtTabela.getValueAt(i, 1))));
		                    	ftVO1.setNomeCliente(ftVO.getNomeCliente());
		                    	ftVO1.setTotalCliente( String.valueOf(Double.valueOf(String.valueOf(jtTabela.getValueAt(i, 3)))+ Double.valueOf(ftVO.getTotalCliente())) );
		                    	posicao.set(y,ftVO1);
		            		}else{
		            			if(!ftVOPosicao.getNomeCliente().equals(ftVO1.getNomeCliente())){
		            				ftVO1 = new TabFaturaOrdensVO();
		                    		ftVO1.setFaturar(Boolean.valueOf(String.valueOf(jtTabela.getValueAt(i, 0))));
		                    		ftVO1.setOrdemPk(Integer.valueOf(String.valueOf(jtTabela.getValueAt(i, 1))));
		                    		ftVO1.setNomeCliente(String.valueOf(jtTabela.getValueAt(i, 2)));
		                    		ftVO1.setTotalCliente( String.valueOf(jtTabela.getValueAt(i, 3)));
		                    		posicao.add(ftVO1);
		                    		y++;
		            			}
		            		}
            			}
            		}
            	}
        	}
		}
		try {
		   	ftDAO = new TabFaturaOrdensDAO();
			ftDAO.saveOrdensServicoFaturar(posicao); //				ftDAO.saveNumeroRpsOS(ftVO);
		} catch (SQLException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
    }

	}