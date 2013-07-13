package br.com.cs.controlmoto.utils;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import br.com.cs.controlmoto.domain.ConnectionFactory;
import br.com.cs.controlmoto.domain.TabConsultaOrdensDAO;
import br.com.cs.controlmoto.vo.TabConsultaOrdensVO;

public class JTableForJComboBox {
	
//	private DefaultTableModel aModel;
//	private JTable jtTabela;
//	private JScrollPane jspPane;	
//	private DefaultTableCellRenderer tcrDireta,tcrEsqueda,tcrCentro;
//	private TableColumnModel colModel;
//	private PreparedStatement ps; Statement MinhaSQL; ResultSet rs; Connection MinhaConexao;
//	
//	public JTableForJComboBox() {
//		// TODO Auto-generated constructor stub
//    	
//    	jtTabela = new JTable();
//    	//TIRA O AUTO-DIMENCIONAR DA CELULA ASSIM APARECEM AS BARRAS DE ROLAGEM
//    	jtTabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//    	
//    	//defaultTCR = new DefaultTableCellRenderer();
//    	
//    	//CUIDADO DO ALINHAMENTO DAS CELULAS    	
//    	tcrDireta = new DefaultTableCellRenderer();tcrDireta.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
//    	tcrEsqueda = new DefaultTableCellRenderer();tcrEsqueda.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
//    	tcrCentro = new DefaultTableCellRenderer();tcrCentro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
//    	
//    	colModel = jtTabela.getColumnModel();
//    	jspPane = new JScrollPane(jtTabela);
//    	//content.add(jspPane, BorderLayout.CENTER);
//	}
//	/**
//	 * @TbTabela nome da tabela no banco de dados que sera utilizada no SELECT
//	 * @TbCampo1 nome do campo na tabela do banco de dados que será os campos da JTable
//	 * @TbCampo2 nome do campo na tabela do banco de dados que será os campos da JTable
//	 */
//	public JTable carregaJComboBoxComJTable(String tbTabela, String tbCampo1, String tbCampo2, String classNameVO, String classNameDAO){
//   	    	try{
//	    		String[] tableColumnsName = {tbCampo1, tbCampo2};
//	    		aModel = (DefaultTableModel)jtTabela.getModel();
//	    		aModel.setColumnIdentifiers(tableColumnsName);
//	    		jtTabela.getColumnModel().getColumn(0).setPreferredWidth(70);colModel.getColumn(0).setCellRenderer(tcrCentro);
//	    		jtTabela.getColumnModel().getColumn(1).setPreferredWidth(200);
//	    	
//	    		/** REALIZA A BUSCA NO BANCO DE DADOS */
//	    		classNameVO = classNameVO+" vo = new "+classNameVO; 
//	    		String instancia1 ="", instancia2 ="", dao ="dao.", metodo = "readOrdemServicoByPk(classNameVO)";
//	    		instancia1 = classNameDAO; instancia2 = " dao"+" = " +" new " + classNameDAO + "()";
//	    		instancia1 = instancia1+instancia2;
//	    		dao = dao + metodo;
//	    		
//	    		/** INSTANCIA AS CLASSES VO e DAO  */
//	    		classNameVO.toString();
//	    		instancia1.toString();
//				try {
//					dao.toString();
//				} catch (InstantiationException | IllegalAccessException e) {
//					e.printStackTrace();
//				}finally{
//					try {
//						ConnectionFactory.getClosedConnection();
//					} catch (IllegalAccessException | InstantiationException e) {
//						e.printStackTrace();
//					}
//				}
//				Object[] objects = new Object[2];
//				int i = 0;
//				for(i = 0; i < classNameVO; i++){
//					objects[i] = classNameVO;
//					objects[i+1] = classNameVO;
//					aModel.addRow(objects);
//				}
//				jtTabela.setModel(aModel);
//				
//    		}catch(SQLException e){
//    			//JOptionPane.showMessageDialog(this, "Erro ao localizar as informações.","",JOptionPane.INFORMATION_MESSAGE);
//    		}    		
//		
//		return null;
//		
//	}
	
}
