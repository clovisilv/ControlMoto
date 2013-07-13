package br.com.cs.controlmoto.utils;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.cs.controlmoto.vo.TabGerarRpsFaturaVO;

public class ModeloTabelaGerarRps  extends AbstractTableModel {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//FaturaPk, DataFaturamento, NumeroRps, Nome, OrdensFaturadas, ValorHora
	private static final int COL_CHECK = 0;   
    private static final int COL_CODIGO = 1;   
    private static final int COL_DATAFATURAMENTO = 2;
    private static final int COL_NUMERORPS = 3;
    private static final int COL_NOME = 4;
    private static final int COL_ORDENSFATURADAS = 5;
    private static final int COL_VALOR = 6;    
    private static final int NUM_COLUNAS = 7;
  
    public List<TabGerarRpsFaturaVO> geraRps;   
    public List check; //Marca se o faturaOrdens esta ou nao selecionado   
  
  
    public ModeloTabelaGerarRps(List geraRps) {   
        this.geraRps = geraRps;   
        check = new ArrayList();   
        for (int i = 0; i < geraRps.size(); i++)   
           check.add(Boolean.FALSE); //Valor default.   
    }   
  
    public int getColumnCount() {   
        return NUM_COLUNAS;   
    }   
  
    public int getRowCount() {   
        return geraRps.size();   
    }   
  
    public Object getValueAt(int linha, int coluna) {   
        if (coluna == COL_CHECK)   
            return check.get(linha) == Boolean.TRUE;   
        if (coluna == COL_CODIGO)   
            return ( geraRps.get(linha)).getFaturaPk();   
        if (coluna == COL_DATAFATURAMENTO)   
            return ( geraRps.get(linha)).getDataFaturamentoStr();
        if (coluna == COL_NUMERORPS)   
            return ( geraRps.get(linha)).getNumeroRps();
        if (coluna == COL_NOME)   
            return ( geraRps.get(linha)).getNome();
        if (coluna == COL_ORDENSFATURADAS)   
            return ( geraRps.get(linha)).getOrdensFaturadas();
        if (coluna == COL_VALOR)   
            return ( geraRps.get(linha)).getValorStr();
        return "??";   
    }   
  
    public Class< ? > getColumnClass(int coluna) {   
        if (coluna == COL_CHECK)   
            return Boolean.class;   
        if (coluna == COL_CODIGO || coluna == COL_DATAFATURAMENTO || coluna == COL_NUMERORPS || coluna == COL_NOME || coluna == COL_ORDENSFATURADAS || coluna == COL_VALOR)
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
            return "";
        if (coluna == 1)   
            return "Código";   
        if (coluna == 2)   
            return "Data Faturar";
        if (coluna == 3)   
            return "RPS";
        if (coluna == 4)   
            return "Nome Cliente";
        if (coluna == 5)   
            return "O.S. Faturada";
        if (coluna == 6)   
            return "Valor";
        return "???";   
    }   
       
    /**  
     * Esse metodo permitir que voce pergunte ao modelo se um checkbox esta ou  
     * nao selecionado.  
     */   
    public boolean isChecked(TabGerarRpsFaturaVO geraRps) {           
        return check.get(this.geraRps.indexOf(geraRps.getFaturar())) == Boolean.TRUE;   
    }   

}
