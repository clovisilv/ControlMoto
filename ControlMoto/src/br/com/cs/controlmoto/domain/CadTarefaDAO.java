/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cs.controlmoto.domain;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.cs.controlmoto.vo.CadTarefaVO;

/**
 * @author clovis
 * @version 1.0.1
 */
public class CadTarefaDAO {
    
    private java.sql.PreparedStatement pst;
    private java.sql.Connection con;
    private java.sql.ResultSet rs;
    
    public CadTarefaDAO() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException{
        this.con = ConnectionFactory.getConnection();
    }
    
    public void closeTarefaConexao() throws SQLException, ClassNotFoundException{
        this.con.close();
    }
    
    public void atualizaTarefaByPk(CadTarefaVO cadTarefaVO) throws SQLException{
        java.lang.String sql = "UPDATE TbTarefas SET DescricaoTarefa=?, StatusTarefa=?"
        + " WHERE TarefaPk=?";
        
        pst = con.prepareStatement(sql);
        
        pst.setString(1, cadTarefaVO.getDescricaoTarefa());
        pst.setString(2, cadTarefaVO.getStatusTarefa());
        pst.setInt(3, cadTarefaVO.getCodigoTarefa());
        
        pst.execute();;
    }
    
     public CadTarefaVO buscaTarefaByPk(CadTarefaVO cadTarefaVO) throws SQLException{
        java.lang.String sql = "SELECT TarefaPk, DescricaoTarefa, StatusTarefa FROM TbTarefas"
        + " WHERE TarefaPk=?";
        pst = con.prepareStatement(sql);
        pst.setInt(1, Integer.valueOf(cadTarefaVO.getCodigoTarefa()));
        rs = pst.executeQuery();
        while(rs.next()){
        	cadTarefaVO.setCodigoTarefa(rs.getInt("TarefaPk"));
        	cadTarefaVO.setDescricaoTarefa(rs.getString("DescricaoTarefa"));
        	cadTarefaVO.setStatusTarefa(rs.getString("StatusTarefa"));
        }
        return cadTarefaVO;
    }
    
	public CadTarefaVO controlaPkTarefa() throws SQLException{
		CadTarefaVO cadTarefaVO = null;
	     java.lang.String sql = "SELECT COUNT(TarefaPk) AS TarefaPk FROM TbTarefas";
	     
	     pst = con.prepareStatement(sql);

	     rs = pst.executeQuery();
	     while(rs.next()){
	    	 cadTarefaVO = new CadTarefaVO();
	    	 cadTarefaVO.setCodigoTarefa(rs.getInt("TarefaPk"));
	     }
	     return cadTarefaVO;
	}
     
    public void salvaTarefa(CadTarefaVO cadTarefaVO) throws SQLException{
        java.lang.String sql = "INSERT INTO TbTarefas(TarefaPk, DescricaoTarefa, StatusTarefa)"
        + "VALUES(?, ?, ?)";
        
        pst = con.prepareStatement(sql);
        
        pst.setInt(1, cadTarefaVO.getCodigoTarefa());
        pst.setString(2, cadTarefaVO.getDescricaoTarefa());
        pst.setString(3, cadTarefaVO.getStatusTarefa());
        
        pst.execute();
        
    }
    
    public List<CadTarefaVO> listaTarefas(CadTarefaVO cadTarefaVO) throws SQLException{
        CadTarefaVO cadTarefaVOs = null; List<CadTarefaVO> cadTarefaVOList = new ArrayList<CadTarefaVO>(); 
        java.lang.String sql = "SELECT TarefaPk, DescricaoTarefa, StatusTarefa FROM TbTarefas"
        + " ORDER BY TarefaPk";
        
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();
        while(rs.next()){
            cadTarefaVOs = new CadTarefaVO();
            
            cadTarefaVOs.setCodigoTarefa(rs.getInt("TarefaPk"));
            cadTarefaVOs.setDescricaoTarefa(rs.getString("DescricaoTarefa"));
            cadTarefaVOs.setStatusTarefa(rs.getString("StatusTarefa"));
            
            cadTarefaVOList.add(cadTarefaVOs);
        }
        return cadTarefaVOList;
    }
    
}
