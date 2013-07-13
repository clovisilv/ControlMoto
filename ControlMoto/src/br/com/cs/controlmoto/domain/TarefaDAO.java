/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cs.controlmoto.domain;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.cs.controlmoto.vo.TarefaVO;

/**
 * @author Fictec
 */
public class TarefaDAO {
    
    private java.sql.PreparedStatement pst;
    private java.sql.Connection con;
    private java.sql.ResultSet rs;
    
    public TarefaDAO() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        this.con = ConnectionFactory.getConnection();
    }
    
    public void getClosedConnection() throws SQLException, ClassNotFoundException {
        this.con.close();
    }
    
    public List<TarefaVO> getAllTarefa() throws SQLException{
        List<TarefaVO> tarefaVOs = new ArrayList<TarefaVO>(); 
        TarefaVO tarefaVO = null;
                
        java.lang.String sql = "SELECT TarefaPk, DescricaoTarefa FROM TbTarefas";
        
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();
        while(rs.next()){
            tarefaVO = new TarefaVO();
            tarefaVO.setTarefaPk(rs.getInt("TarefaPk"));
            tarefaVO.setDescricaoTarefa(rs.getString("DescricaoTarefa"));
            
            tarefaVOs.add(tarefaVO);
        }
        return tarefaVOs;
    }
    
}

