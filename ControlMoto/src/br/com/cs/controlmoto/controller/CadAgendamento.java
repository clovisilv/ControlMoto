/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cs.controlmoto.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import br.com.cs.controlmoto.domain.AgendamentoDAO;
import br.com.cs.controlmoto.domain.CadClienteDAO;
import br.com.cs.controlmoto.domain.CadMotoristaDAO;
import br.com.cs.controlmoto.domain.TarefaDAO;
import br.com.cs.controlmoto.utils.ConvertStringToDate;
import br.com.cs.controlmoto.vo.AgendamentoVO;
import br.com.cs.controlmoto.vo.CadClienteVO;
import br.com.cs.controlmoto.vo.CadMotoristaVO;
import br.com.cs.controlmoto.vo.TarefaVO;

/**
 *
 * @author fictec
 */
public class CadAgendamento extends javax.swing.JInternalFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
     * Creates new form CadAgendamento
     */
    public CadAgendamento() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        lblCodigo = new javax.swing.JLabel();
        lblValorCodigo = new javax.swing.JLabel();
        lblDataAgendamento = new javax.swing.JLabel();
        jftDataAgendamento = new javax.swing.JFormattedTextField();
        lblHora = new javax.swing.JLabel();
        jftHora = new javax.swing.JFormattedTextField();
        lblCliente = new javax.swing.JLabel();
        jcbCliente = new javax.swing.JComboBox();
        lblTarefa = new javax.swing.JLabel();
        jcbTarefa = new javax.swing.JComboBox();
        lblStatus = new javax.swing.JLabel();
        lblValorStatus = new javax.swing.JLabel();
        lblMotorista = new javax.swing.JLabel();
        jcbMotorista = new javax.swing.JComboBox();
        lblNumeroOS = new javax.swing.JLabel();
        lblValorNumeroOS = new javax.swing.JLabel();
        jtbBarraFerramentas = new javax.swing.JToolBar();
        jbSalvar = new javax.swing.JButton();
        jbAtualizar = new javax.swing.JButton();
        jbImprimir = new javax.swing.JButton();
//        jDesktopPane = new JDesktopPane();

        setClosable(true);
        setIconifiable(true);
        setResizable(false);
       	setTitle("Agendamento");
        setToolTipText("Agendamento");
        setMaximumSize(new java.awt.Dimension(640, 350));
        setMinimumSize(new java.awt.Dimension(200, 200));
        setPreferredSize(new java.awt.Dimension(580, 260));//largura e altura
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameActivated(evt);
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });
        
        setLocation(xOffset*openFrameCount, yOffset*openFrameCount);

        lblCodigo.setText("C�digo");

        lblValorCodigo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblValorCodigo.setToolTipText("C�digo Agendamento");
        lblValorCodigo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(0, 0, 153)));
        lblValorCodigo.setMaximumSize(new java.awt.Dimension(14, 20));
        lblValorCodigo.setMinimumSize(new java.awt.Dimension(14, 20));
        lblValorCodigo.setPreferredSize(new java.awt.Dimension(14, 20));

        lblDataAgendamento.setText("Data");

        try {
            jftDataAgendamento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jftDataAgendamento.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jftDataAgendamento.setMaximumSize(new java.awt.Dimension(6, 20));

        lblHora.setText("Hora");

        try {
            jftHora.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jftHora.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        lblCliente.setText("Cliente");

//        jcbCliente.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jcbCliente.setMaximumSize(new java.awt.Dimension(56, 20));
        lblTarefa.setText("Tarefa");

//        jcbTarefa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lblStatus.setText("Situa��o");

        lblValorStatus.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(0, 0, 153)));
        lblValorStatus.setMaximumSize(new java.awt.Dimension(34, 20));
        lblValorStatus.setMinimumSize(new java.awt.Dimension(34, 20));
        lblValorStatus.setName("");
        lblValorStatus.setPreferredSize(new java.awt.Dimension(34, 20));

        lblMotorista.setText("Motorista");

//        jcbMotorista.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jcbMotorista.setMaximumSize(new java.awt.Dimension(56, 20));        

        lblNumeroOS.setText("N�mero OS");

        lblValorNumeroOS.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblValorNumeroOS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(0, 0, 153)));
        lblValorNumeroOS.setMaximumSize(new java.awt.Dimension(38, 20));
        lblValorNumeroOS.setMinimumSize(new java.awt.Dimension(38, 20));
        lblValorNumeroOS.setPreferredSize(new java.awt.Dimension(38, 20));

        jtbBarraFerramentas.setFloatable(false);
        jtbBarraFerramentas.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jtbBarraFerramentas.setRollover(true);
        jtbBarraFerramentas.setToolTipText("Barra de Ferramentas");

        jbSalvar.setText("Salvar");
        jbSalvar.setFocusable(false);
        jbSalvar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbSalvar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSalvarActionPerformed(evt);
            }
        });
        jtbBarraFerramentas.add(jbSalvar);

        jbAtualizar.setText("Atualizar");
        jbAtualizar.setFocusable(false);
        jbAtualizar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbAtualizar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbAtualizar.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt){
        		jbAtualizarActionPerformed(evt);
        	}
        });  
        jtbBarraFerramentas.add(jbAtualizar);

        jbImprimir.setText("Imprimir");
        jbImprimir.setFocusable(false);
        jbImprimir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbImprimir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbImprimirActionPerformed(evt);
            }
        });
        jtbBarraFerramentas.add(jbImprimir);
      
        TabAgenda_Old_New tabAgenda = new TabAgenda_Old_New(null, null);
        if(tabAgenda.botaoClicado == "btoNovo"){
        	jbSalvar.setEnabled(true); jbAtualizar.setEnabled(false); jbImprimir.setEnabled(false);
        	//tabAgenda.botaoClicado = "";
        }else if(TabAgenda_Old_New.botaoClicado == "btoAlterar"){
        	jbSalvar.setEnabled(false); jbAtualizar.setEnabled(true); jbImprimir.setEnabled(true);
        	//tabAgenda.botaoClicado = "";
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(lblDataAgendamento)
                            .addGap(28, 28, 28)
                            .addComponent(jftDataAgendamento, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(lblHora)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jftHora, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(lblNumeroOS)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblValorNumeroOS, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lblCliente)
                            .addGap(18, 18, 18)
                            .addComponent(jcbCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lblTarefa)
                            .addGap(18, 18, 18)
                            .addComponent(jcbTarefa, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(14, 14, 14)
                            .addComponent(lblStatus)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblValorStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lblMotorista)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jcbMotorista, 400, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblCodigo)
                        .addGap(18, 18, 18)
                        .addComponent(lblValorCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jtbBarraFerramentas, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblValorCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCodigo))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDataAgendamento)
                    .addComponent(jftDataAgendamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblHora)
                    .addComponent(jftHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNumeroOS)
                    .addComponent(lblValorNumeroOS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCliente)
                    .addComponent(jcbCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblTarefa)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jcbTarefa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblStatus)
                        .addComponent(lblValorStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblMotorista)
                    .addComponent(jcbMotorista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
            .addComponent(jtbBarraFerramentas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getAccessibleContext().setAccessibleParent(this);

        pack();
    }// </editor-fold>
    //
    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
    	TabAgenda_Old_New tabAgenda = new TabAgenda_Old_New(null, null);
	        try {
	        	//CARREGA A DATA E HORA NA TELA QUANDO O CLIQUE VEM DO BOTAO NOVO
	        	//QUANDO O CLIQUE FOR NO BOTAO ALTERAR VAI CARREGAR A DATA E HORA DO MEDOTO GETAGENDABYID
	        	if(tabAgenda.botaoClicado != "btoAlterar"){
		        	//CARREGA O ID da AGENDA
		        	if(lblValorCodigo.getText() == ""){
		        		java.lang.String id = String.valueOf(new AgendamentoDAO().getUltimoId());
		        		lblValorCodigo.setText(String.valueOf( id.equals("null") ? 1 : (Integer.valueOf(id)+1) ));
		        	}
		        	if(jftDataAgendamento.getValue() == null){
		        		jftDataAgendamento.setText(new SimpleDateFormat("dd/MM/yyyy").format(new java.util.Date()) );
		        	}
		        	if(jftHora.getValue() == null){
		        		jftHora.setText(new SimpleDateFormat("HH:mm").format(new java.util.Date()));
		        	}
	        	}
	        	//CARREGA A COMBOBOX DE CLIENTE
	        	if(jcbCliente.getItemCount() == 0){
	        		jcbCliente.addItem("");
	        		for(CadClienteVO cadClienteVO : new CadClienteDAO().getClienteByCombo()){
					//jcbCliente.setModel(new javax.swing.DefaultComboBoxModel(new String[] { cadClienteVO.getClientePk()+" - "+cadClienteVO.getNome() }));
	        			jcbCliente.addItem(cadClienteVO.getClientePk()+" - "+cadClienteVO.getNome());
	        		}
	        	}
	        	//CARREGA A COMBOBOX DE TAREFAS
	        	if(jcbTarefa.getItemCount() == 0){
	        		jcbTarefa.addItem("");
	        		for(TarefaVO tarefaVO : new TarefaDAO().getAllTarefa() ){
	        			jcbTarefa.addItem(tarefaVO.getTarefaPk()+" - "+tarefaVO.getDescricaoTarefa());
	        		}
	        	}
	        	//CARREGA A COMBOBOX DE MOTORISTA
	        	if(jcbMotorista.getItemCount() == 0){
	        		jcbMotorista.addItem("");
	        		for(CadMotoristaVO cadMotoristaVO : new CadMotoristaDAO().getMotoristaByCombo()){
	        			jcbMotorista.addItem(cadMotoristaVO.getMotoristaPK()+" - "+cadMotoristaVO.getNome());
	        		}
	        		lblValorStatus.setText("Previsto");
	        	}
	        	if(tabAgenda.botaoClicado == "btoAlterar"){
	        		getAgendaById(tabAgenda.idAgenda);
	        	}
			} catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
				e.printStackTrace();
			}
    }
    //
    private void jbSalvarActionPerformed(java.awt.event.ActionEvent evt) {                                         
        AgendamentoVO agendamentoVO = new AgendamentoVO();
        agendamentoVO.setAgendaPk(Integer.valueOf(lblValorCodigo.getText()));
        if(jftDataAgendamento.getText() != "  /  /    "){
        	//ConvertStringToDate convStringToDate = new ConvertStringToDate()
        	//java.util.Date dt = new java.util.Date(new SimpleDateFormat("dd/MM/yyyy").format(jftDataAgendamento.getText()));
        	try {
				agendamentoVO.setDataAgendada(new ConvertStringToDate().convertStringToDates(jftDataAgendamento.getText()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        if(jftHora.getText() != "  :  ")
			try {
				agendamentoVO.setHoraAgendada(new java.sql.Time(new SimpleDateFormat("HH:mm").parse(jftHora.getText()).getTime()));
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
        agendamentoVO.setClienteAFk(Integer.valueOf(jcbCliente.getSelectedItem().toString().substring(0, jcbCliente.getSelectedItem().toString().indexOf("-")).trim()) );
        agendamentoVO.setNomeCliente(jcbCliente.getSelectedItem().toString().substring(jcbCliente.getSelectedItem().toString().indexOf("-")).replace("-", "").trim() );
        agendamentoVO.setTarefaAFk(Integer.valueOf(jcbTarefa.getSelectedItem().toString().substring(0, jcbTarefa.getSelectedItem().toString().indexOf("-")).trim()) );
        agendamentoVO.setNomeTarefa(jcbTarefa.getSelectedItem().toString().substring(jcbTarefa.getSelectedItem().toString().indexOf("-")).replace("-", "").trim() );
        agendamentoVO.setStatusAgenda(lblValorStatus.getText());
        if(jcbMotorista.getSelectedIndex() > 0){
        	agendamentoVO.setMotoristaAFk(jcbMotorista.getSelectedItem().toString().substring(0, jcbMotorista.getSelectedItem().toString().indexOf("-")).trim());
        	agendamentoVO.setNomeMotorista(jcbMotorista.getSelectedItem().toString().substring(jcbMotorista.getSelectedItem().toString().indexOf("-")).trim() );
        }
        
        try {
			new AgendamentoDAO().insertAgendamento(agendamentoVO);
		} catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e){ 
			e.printStackTrace();
		}finally{
			try {
				new AgendamentoDAO().getConnectionClose();
				limpaCampos();
				CadAgendamento.this.dispose();
				
				TabAgenda_Old_New tabAgenda = new TabAgenda_Old_New(null, null);
				tabAgenda.atualizaGridAgenda();
			} catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
				e.printStackTrace();
			}
		}        
    }
    //
    private void jbAtualizarActionPerformed(java.awt.event.ActionEvent evt) {                                           
        AgendamentoVO agendamentoVO = new AgendamentoVO();
        agendamentoVO.setAgendaPk(Integer.valueOf(lblValorCodigo.getText()));
        if(jftDataAgendamento.getText() != "  /  /    "){
        	try {
				agendamentoVO.setDataAgendada(new ConvertStringToDate().convertStringToDates(jftDataAgendamento.getText()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        if(jftHora.getText() != "  :  ")
			try {
				agendamentoVO.setHoraAgendada(new java.sql.Time(new SimpleDateFormat("HH:mm").parse(jftHora.getText()).getTime()));
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
        agendamentoVO.setClienteAFk(Integer.valueOf(jcbCliente.getSelectedItem().toString().substring(0, jcbCliente.getSelectedItem().toString().indexOf("-")).trim()) );
        agendamentoVO.setNomeCliente(jcbCliente.getSelectedItem().toString().substring(jcbCliente.getSelectedItem().toString().indexOf("-")).replace("-", "").trim() );
        agendamentoVO.setTarefaAFk(Integer.valueOf(jcbTarefa.getSelectedItem().toString().substring(0, jcbTarefa.getSelectedItem().toString().indexOf("-")).trim()) );
        agendamentoVO.setNomeTarefa(jcbTarefa.getSelectedItem().toString().substring(jcbTarefa.getSelectedItem().toString().indexOf("-")).replace("-", "").trim() );
        agendamentoVO.setStatusAgenda(lblValorStatus.getText());
        if(jcbMotorista.getSelectedIndex() > 0){
        	agendamentoVO.setMotoristaAFk(jcbMotorista.getSelectedItem().toString().substring(0, jcbMotorista.getSelectedItem().toString().indexOf("-")).trim());
        	agendamentoVO.setNomeMotorista(jcbMotorista.getSelectedItem().toString().substring(jcbMotorista.getSelectedItem().toString().indexOf("-")).trim() );
        }
        
        try {
			new AgendamentoDAO().updateAgendamento(agendamentoVO);
		} catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e){ 
			e.printStackTrace();
		}finally{
			try {
				new AgendamentoDAO().getConnectionClose();
				//limpaCampos();
				//CadAgendamento.this.dispose();
				
				TabAgenda_Old_New tabAgenda = new TabAgenda_Old_New(null, null);
//				tabAgenda.atualizaGridAgenda();
//				tabAgenda.modeloAgenda.addRow(arg0)
				AgendamentoVO agendamentVO = new AgendamentoVO();
		        try {
		        	agendamentVO.setAno(Integer.valueOf(jftDataAgendamento.getText().substring(6)));
		            List<AgendamentoVO> agendamentVOs = new ArrayList<>();

		            agendamentVOs = new AgendamentoDAO().getAllAgendamentoByAno(agendamentVO);		
		            tabAgenda.modeloAgenda = (DefaultTableModel) tabAgenda.jtAgenda.getModel();
		            tabAgenda.modeloAgenda.setNumRows(0);
		            for(AgendamentoVO agVO : agendamentVOs)
		            	tabAgenda.modeloAgenda.addRow(new String[]{String.valueOf(agVO.getAgendaPk()), new SimpleDateFormat("dd/MM/yyyy").format(agVO.getDataAgendada()), agVO.getHrAgendada(), agVO.getNomeCliente(), agVO.getNomeTarefa(), agVO.getStatusAgenda() });
		                       
				} catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
					e.printStackTrace();
				}
				
			} catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
				e.printStackTrace();
			}
		}

    }     
    //
    private void jbImprimirActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
    }    
    //
    public void getAgendaById(Integer idAgenda){
    	try {
    		AgendamentoVO agendamentoVO = new AgendamentoVO();
    		agendamentoVO.setAgendaPk(idAgenda);
			agendamentoVO = new AgendamentoDAO().getAgendamentoById(agendamentoVO);
			
			lblValorCodigo.setText(String.valueOf(agendamentoVO.getAgendaPk()));
			jftDataAgendamento.setValue(String.valueOf(new SimpleDateFormat("dd/MM/yyyy").format(agendamentoVO.getDataAgendada())));
			jftHora.setText(String.valueOf(new SimpleDateFormat("HH:mm").format(agendamentoVO.getHoraAgendada())));
			lblValorNumeroOS.setText(String.valueOf(agendamentoVO.getNumeroOS()));
			jcbCliente.setSelectedItem(agendamentoVO.getClienteAFk()+" - "+agendamentoVO.getNomeCliente());
			jcbTarefa.setSelectedItem(agendamentoVO.getTarefaAFk()+" - "+agendamentoVO.getNomeTarefa());
			lblValorStatus.setText(agendamentoVO.getStatusAgenda());
			jcbMotorista.setSelectedItem(agendamentoVO.getMotoristaAFk()+" - "+agendamentoVO.getNomeMotorista());

		} catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
			e.printStackTrace();
		}
    }
    
    //METODO PARA LIMPAR OS CAMPOS APOS SALVAR
    public void limpaCampos(){
        lblValorCodigo.setText("");
        jftDataAgendamento.setText("  /  /    ");
        lblHora.setText("  :  ");
        lblValorNumeroOS.setText("");
        jcbCliente.setSelectedItem("");
        jcbTarefa.setSelectedItem("");
        lblValorStatus.setText("");
        jcbMotorista.setSelectedItem("");
    }

    static int openFrameCount = 5;
    static final int xOffset = 25, yOffset = 14;

    // Variables declaration - do not modify
    private javax.swing.JButton jbAtualizar, jbImprimir, jbSalvar;
    private javax.swing.JComboBox jcbCliente, jcbMotorista, jcbTarefa;
    private javax.swing.JFormattedTextField jftDataAgendamento, jftHora;
    private javax.swing.JToolBar jtbBarraFerramentas;
    private javax.swing.JLabel lblCliente, lblCodigo, lblDataAgendamento, lblHora, lblMotorista, lblNumeroOS, lblStatus,
    lblTarefa, lblValorCodigo, lblValorNumeroOS, lblValorStatus;
    // End of variables declaration
}