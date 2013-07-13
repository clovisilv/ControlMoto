/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cs.controlmoto.domain;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.cs.controlmoto.vo.CadCredorVO;

/**
 * @author clovis
 * @version 1.0.01
 */
public class CadCredorDAO {
    
    private java.sql.Connection con;
    private java.sql.PreparedStatement pst;
    private java.sql.ResultSet rs;

    public CadCredorDAO() throws SQLException, ClassNotFoundException {
        try {
			this.con = ConnectionFactory.getConnection();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
    }
    
    public void closeCadCredorDAO() throws SQLException, ClassNotFoundException {
        this.con.close();
    }
    
    public CadCredorVO incluirCredor(CadCredorVO cdBean) throws SQLException{
        java.lang.String sql = "SELECT CredorPk FROM tbcredor ORDER BY CredorPk DESC LIMIT 1";
        
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();
        while(rs.next()){
            cdBean.setLblCodigoCampo(rs.getString("CredorPk"));
        }
        return cdBean;
    }
    
    public void salvarCredor(CadCredorVO cdBean) throws SQLException{
        //SELECT CredorPk, NomeCredor FROM tbcredor"
        java.lang.String sql = "INSERT INTO Tbcredor (CredorPk, NomeCredor, Status)"
        + "VALUES (?, ?, ?)";
        
        pst = con.prepareStatement(sql);
        
        pst.setString(1, cdBean.getLblCodigoCampo());
        pst.setString(2, cdBean.getTxtRazaoSocial());
        pst.setString(3, cdBean.getJrbAtivo());
        
        pst.execute();
    }
    
    public void atualizarCredor(CadCredorVO cdBean) throws SQLException{
        java.lang.String sql = "UPDATE TbCredor SET NomeCredor=?, Status=? WHERE CredorPk=?";
        
        pst = con.prepareStatement(sql);
        
        pst.setString(1, cdBean.getTxtRazaoSocial());
        pst.setString(2, cdBean.getJrbAtivo());
        pst.setString(3, cdBean.getLblCodigoCampo());
        
        pst.execute();        
    }

   public CadCredorVO buscarCredorByPk(CadCredorVO crBean) throws SQLException{
        java.lang.String sql = "SELECT CredorPk, NomeCredor, Status FROM Tbcredor WHERE CredorPk=?";
        pst = con.prepareStatement(sql);
        pst.setInt(1, Integer.valueOf(crBean.getLblCodigoCampo()));
        rs = pst.executeQuery();
        while(rs.next()){
            crBean.setLblCodigoCampo(rs.getString("CredorPk"));
            crBean.setTxtRazaoSocial(rs.getString("NomeCredor"));
            crBean.setJrbAtivo(rs.getString("Status"));
        }
        return crBean;
    }
        
    public List<CadCredorVO> listaCredor(CadCredorVO cdBean) throws SQLException{
        CadCredorVO cdBeans=null;
        List<CadCredorVO> cdBeanList = new ArrayList<CadCredorVO>();
        java.lang.String sql = "SELECT CredorPk, NomeCredor, Status FROM tbcredor ORDER BY CredorPk";
        
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();
        while(rs.next()){
            cdBeans = new CadCredorVO();
            
            cdBeans.setLblCodigoCampo(rs.getString("CredorPk"));
            cdBeans.setTxtRazaoSocial(rs.getString("NomeCredor"));
            cdBeans.setJrbAtivo(rs.getString("Status"));
            
            cdBeanList.add(cdBeans);
        }
        return cdBeanList;
    }
    
}
