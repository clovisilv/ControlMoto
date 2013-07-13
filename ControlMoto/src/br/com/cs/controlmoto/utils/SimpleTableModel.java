package br.com.cs.controlmoto.utils;

import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;

public class SimpleTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ArrayList linhas = null;  
    private String [] colunas = null;  
    public String[] getColunas() {return colunas;}  
    public ArrayList getLinhas() {return linhas;}  
    public void setColunas(String[] strings) {colunas = strings;}  
//    public void setLinhas(ArrayList list) {linhas = list;}
    private boolean [] colsEdicao;  
	
	/** 
	 * Retorna o numero de colunas no modelo 
	 * @see javax.swing.table.TableModel#getColumnCount() 
	 */  
	public int getColumnCount() {return getColunas().length;}  
	  
	/** 
	 * Retorna o numero de linhas existentes no modelo 
	 * @see javax.swing.table.TableModel#getRowCount() 
	 */  
	public int getRowCount() {return getLinhas().size();}  
	  
	/** 
	 * Obtem o valor na linha e coluna 
	 * @see javax.swing.table.TableModel#getValueAt(int, int) 
	 */  
	public Object getValueAt(int rowIndex, int columnIndex) {  
	    // Obtem a linha, que é uma String []  
	    String [] linha = (String [])getLinhas().get(rowIndex);  
	    // Retorna o objeto que esta na coluna  
	    return linha[columnIndex];  
	} 
	
//    public SimpleTableModel(ArrayList dados, String[] colunas){  
//        setLinhas(dados);  
//        setColunas(colunas);  
//    }
    
    /** 
     * Cria a JTable 
     * @return 
     */  
//    public JTable createJTable(ArrayList dados, String[] colunas) {  
//      
//        ArrayList dados = new ArrayList();  
//        String[] colunas = new String[] { "Estado", "Cidade" };  
//      
//        // Alimenta as linhas de dados  
//        dados.add(new String[] { "SP", "São Paulo" });  
//        dados.add(new String[] { "RJ", "Rio de Janeiro" });  
//        dados.add(new String[] { "RN", "Rio Grande do Norte" });  
//        dados.add(new String[] { "ES", "Espirito Santo" });  
//      
//        boolean [] edicao = {false, true}; 
//		SimpleTableModel modelo = new SimpleTableModel(dados, colunas);  
//        JTable jtable = new JTable(modelo);  
//      jtable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  
//      
//        return jtable;  
//
//    }

    /** 
     * Seta o valor na linha e coluna 
     * @see javax.swing.table.TableModel#setValueAt(java.lang.Object, int, int) 
     */  
    public void setValueAt(Object value, int row, int col){  
        // Obtem a linha, que é uma String []  
        String [] linha = (String [])getLinhas().get(row);  
        // Altera o conteudo no indice da coluna passado  
        linha[col] = (String)value;  
        // dispara o evento de celula alterada  
        fireTableCellUpdated(row,col);  
    }
    
    
    public boolean isCellEditable(int row, int col){  
        return colsEdicao[col];  
    }  
	

}
