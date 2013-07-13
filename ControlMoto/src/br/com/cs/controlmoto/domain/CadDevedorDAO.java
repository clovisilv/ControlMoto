/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cs.controlmoto.domain;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.cs.controlmoto.vo.CadDevedorVO;

/**
 * @author clovis
 * @version 1.0.01
 */
public class CadDevedorDAO {
    
    private java.sql.Connection con;
    private java.sql.PreparedStatement pst;
    private java.sql.ResultSet rs;

    public CadDevedorDAO() throws SQLException, ClassNotFoundException {
        try {
			this.con = ConnectionFactory.getConnection();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
    }
    
    public void closeCadDevedorDAO() throws SQLException, ClassNotFoundException {
        this.con.close();
    }
    
    public CadDevedorVO incluirDevedor(CadDevedorVO cdBean) throws SQLException{
        java.lang.String sql = "SELECT DevedorPk FROM Tbdevedor ORDER BY DevedorPk DESC LIMIT 1";
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();
        while(rs.next()){
            cdBean.setLblCodigoCampo(rs.getString("DevedorPk"));
        }
        return cdBean;
    }
    
    public void salvarDevedor(CadDevedorVO cdBean) throws SQLException{
        java.lang.String sql = "INSERT INTO Tbdevedor (DevedorPk, NomeDevedor, Status)"
        + "VALUES (?, ?, ?)";
        pst = con.prepareStatement(sql);
 
        pst.setString(1, cdBean.getLblCodigoCampo());
        pst.setString(2, cdBean.getTxtRazaoSocial());
        pst.setString(3, cdBean.getJrbAtivo());
        
        pst.execute();
    }
    
    public void atualizarDevedor(CadDevedorVO cdBean) throws SQLException{
        java.lang.String sql = "UPDATE Tbdevedor SET NomeDevedor=?, Status=? WHERE DevedorPk=?";
        pst = con.prepareStatement(sql);
        
        pst.setString(1, cdBean.getTxtRazaoSocial());
        pst.setString(2, cdBean.getJrbAtivo());
        pst.setInt(3, Integer.valueOf(cdBean.getLblCodigoCampo()));
        //System.out.println(pst.toString());
        pst.execute();        
    }
    
    public CadDevedorVO buscarDevedorByPk(CadDevedorVO dvBean) throws SQLException{
        java.lang.String sql = "SELECT DevedorPk, NomeDevedor, Status FROM Tbdevedor WHERE DevedorPk=?";
        pst = con.prepareStatement(sql);
        pst.setInt(1, Integer.valueOf(dvBean.getLblCodigoCampo()));
        rs = pst.executeQuery();
        while(rs.next()){
            dvBean.setLblCodigoCampo(rs.getString("DevedorPk"));
            dvBean.setTxtRazaoSocial(rs.getString("NomeDevedor"));
            dvBean.setJrbAtivo(rs.getString("Status"));
        }
        return dvBean;
    }
    
    public List<CadDevedorVO> ListaDevedor(CadDevedorVO dvBean) throws SQLException{
        CadDevedorVO dvBeans = null;
        List<CadDevedorVO> cvBeanList = new ArrayList<CadDevedorVO>();
        java.lang.String sql = "SELECT DevedorPk, NomeDevedor, Status FROM Tbdevedor ORDER BY DevedorPk";
        
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();
        while(rs.next()){
            dvBeans = new CadDevedorVO();
            
            dvBeans.setLblCodigoCampo(rs.getString("DevedorPk"));
            dvBeans.setTxtRazaoSocial(rs.getString("NomeDevedor"));
            dvBeans.setJrbAtivo(rs.getString("Status"));                            
            
            cvBeanList.add(dvBeans);
        }
        return cvBeanList;
    }
    
}
