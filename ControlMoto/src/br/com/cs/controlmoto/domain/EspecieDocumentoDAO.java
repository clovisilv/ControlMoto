/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cs.controlmoto.domain;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.cs.controlmoto.vo.EspecieDocumentoVO;

/**
 * Company Fictec Const. Inf.
 * @author Clovis
 * @version 1.0.1
 * @since 15092012
 */
public class EspecieDocumentoDAO {
    
    private java.sql.PreparedStatement pst;
    private java.sql.Connection con;
    private java.sql.ResultSet rs;
    
    public EspecieDocumentoDAO() throws SQLException, ClassNotFoundException{
        try {
			this.con = ConnectionFactory.getConnection();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
    }
    
    public void closeEspDocumentoConexao() throws SQLException, ClassNotFoundException{
        this.con.close();
    }
    
    public void atualizaEspDocumento(EspecieDocumentoVO espDocVO) throws SQLException{
        java.lang.String sql = "UPDATE Tbespeciedocumento SET Descricao=?, CodEspecDocumento=?, Status=?"
        + " WHERE EspecieDocumentoPk=?";
        
        pst = con.prepareStatement(sql);
        
        pst.setString(1, espDocVO.getDescricao());
        pst.setString(2, espDocVO.getCodEspDocumento());
        pst.setString(3, espDocVO.getStatus());
        pst.setInt(4, espDocVO.getEspecieDocumentoPk());
        
        pst.execute();;
    }
    
     public EspecieDocumentoVO buscarEspecDocumentoByPk(EspecieDocumentoVO epdVO) throws SQLException{
        java.lang.String sql = "SELECT EspecieDocumentoPk, Descricao, CodEspecDocumento, Status FROM Tbespeciedocumento"
        + " WHERE EspecieDocumentoPk=?";
        pst = con.prepareStatement(sql);
        pst.setInt(1, Integer.valueOf(epdVO.getEspecieDocumentoPk()));
        rs = pst.executeQuery();
        while(rs.next()){
            epdVO.setEspecieDocumentoPk(rs.getInt("EspecieDocumentoPk"));
            epdVO.setCodEspDocumento(rs.getString("CodEspecDocumento"));
            epdVO.setDescricao(rs.getString("Descricao"));
            epdVO.setStatus(rs.getString("Status"));
        }
        return epdVO;
    }
    
    public EspecieDocumentoVO incluirEspDocumento(EspecieDocumentoVO espDocVO) throws SQLException{
        java.lang.String sql = "SELECT EspecieDocumentoPk FROM Tbespeciedocumento ORDER BY EspecieDocumentoPk DESC LIMIT 1";
        
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();
        while(rs.next()){
            espDocVO.setEspecieDocumentoPk(rs.getInt("EspecieDocumentoPk"));
        }
        return espDocVO;
    }
    
    public void salvaEspDocumento(EspecieDocumentoVO espDocVO) throws SQLException{
        java.lang.String sql = "INSERT INTO Tbespeciedocumento(EspecieDocumentoPk, Descricao, CodEspecDocumento, Status)"
        + "VALUES(?, ?, ?, ?)";
        
        pst = con.prepareStatement(sql);
        
        pst.setInt(1, espDocVO.getEspecieDocumentoPk());
        pst.setString(2, espDocVO.getDescricao());
        pst.setString(3, espDocVO.getCodEspDocumento());
        pst.setString(4, espDocVO.getStatus());
        
        pst.execute();
        
    }
    
    public List<EspecieDocumentoVO> listaEspecieDocumento(EspecieDocumentoVO espDocVO) throws SQLException{
        EspecieDocumentoVO espDocVOs = null; List<EspecieDocumentoVO> espDocVOList = new ArrayList<EspecieDocumentoVO>(); 
        java.lang.String sql = "SELECT EspecieDocumentoPk, Descricao, CodEspecDocumento, Status FROM Tbespeciedocumento"
        + " ORDER BY EspecieDocumentoPk";
        
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();
        while(rs.next()){
            espDocVOs = new EspecieDocumentoVO();
            
            espDocVOs.setEspecieDocumentoPk(rs.getInt("EspecieDocumentoPk"));
            espDocVOs.setDescricao(rs.getString("Descricao"));
            espDocVOs.setCodEspDocumento(rs.getString("CodEspecDocumento"));
            espDocVOs.setStatus(rs.getString("Status"));
            
            espDocVOList.add(espDocVOs);
        }
        return espDocVOList;
    }
    
}
