package br.com.cs.controlmoto.utils;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.cs.controlmoto.vo.TabFaturaOrdensVO;

public class ModeloTabela  extends AbstractTableModel {
	
    /**
	 * @author clovis
	 */
	private static final long serialVersionUID = 1L;
	
	private static final int COL_CHECK = 0;   
    private static final int COL_CODIGO = 1;   
    private static final int COL_NOME = 2;
    private static final int COL_VALOR = 3;
    private static final int COL_DATAINICIO = 4;
    private static final int NUM_COLUNAS = 5;   
    private static final int NUM_LINHAS = 6;
  
    public List<TabFaturaOrdensVO> faturaOrdens;   
    public List check; //Marca se o faturaOrdens esta ou nao selecionado   
  
  
    public ModeloTabela(List faturaOrdens) {   
        this.faturaOrdens = faturaOrdens;   
        check = new ArrayList();   
        for (int i = 0; i < faturaOrdens.size(); i++)   
           check.add(Boolean.FALSE); //Valor default.   
    }   
  
    public int getColumnCount() {   
        return NUM_COLUNAS;   
    }
    
    public int setColumnCount(int coluna) {   
        return NUM_COLUNAS;   
    }
    
    public int getRowCount() {
        return faturaOrdens.size();   
    }
    
    public int setRowCount(int linha) {
        return NUM_LINHAS;   
    }
  
    public Object getValueAt(int linha, int coluna) {   
        if (coluna == COL_CHECK)   
            return check.get(linha) == Boolean.TRUE;   
        if (coluna == COL_CODIGO)   
            return ( faturaOrdens.get(linha)).getOrdemPk();   
        if (coluna == COL_NOME)   
            return ( faturaOrdens.get(linha)).getNomeCliente();
        if (coluna == COL_VALOR)
            return ( faturaOrdens.get(linha)).getTotalCliente();
        if (coluna == COL_DATAINICIO)
            return ( faturaOrdens.get(linha)).getDataOS();
        return "??";   
    }   
  
    public Class< ? > getColumnClass(int coluna) {   
        if (coluna == COL_CHECK)   
            return Boolean.class;   
        if (coluna == COL_CODIGO || coluna == COL_NOME || coluna == COL_VALOR || coluna == COL_DATAINICIO)   
            return String.class;   
           
        return super.getColumnClass(coluna);
    }   
  
    public boolean isCellEditable(int linha, int coluna) {   
        return (coluna == COL_CHECK);   
    }   
  
    public void setValueAt(Object aValue, int linha, int coluna) {   
        if (coluna == COL_CHECK)   
            check.set(linha, (Boolean) aValue);   
    }   
  
    public String getColumnName(int coluna) {   
        if (coluna == 0)   
            return "Faturar";
        if (coluna == 1)   
            return "Código OS";   
        if (coluna == 2)   
            return "Nome Cliente";
        if (coluna == 3)
            return "Valor OS";
        if (coluna == 4)
            return "Data OS";
        return "???";   
    }   
       
    /**  
     * Esse metodo permitir que voce pergunte ao modelo se um checkbox este ou  
     * nao selecionado.  
     */   
    public boolean isChecked(TabFaturaOrdensVO faturaOrdens) {           
        return check.get(this.faturaOrdens.indexOf(faturaOrdens)) == Boolean.TRUE;   
    }   

}
