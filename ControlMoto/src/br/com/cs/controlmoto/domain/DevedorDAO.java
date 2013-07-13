/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cs.controlmoto.domain;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.cs.controlmoto.utils.FormataValor;
import br.com.cs.controlmoto.vo.ContasPagar1VO;
import br.com.cs.controlmoto.vo.ContasPagarVO;
import br.com.cs.controlmoto.vo.DevedorVO;


/**
 * Company Fictec Consul. Inf.
 * @author clovis
 * @version 1.0.1
 * @since 15092012
 */
public class DevedorDAO {
    
    private java.sql.Connection con;
    private java.sql.PreparedStatement pst;
    private java.sql.ResultSet rs;

    public DevedorDAO() throws SQLException, ClassNotFoundException {
        try {
			this.con = ConnectionFactory.getConnection();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
    }
    
    public void closeContasPagarDAO() throws SQLException, ClassNotFoundException {
        this.con.close();
    }
    
    public void insertDevedor(ContasPagarVO cPagarVO) throws SQLException, ParseException{
        
        java.lang.String sqlI = "INSERT INTO Tbcontaspagar (CredorFK, DtDocumento, EspecieDocumentoFk, NumDocumento, TpPagto,"
        + "FormaPagto, VlDocumento, DtVencimento, CodBarra, VlPagar, VlJuros, VlMulta, DtQuitacao, Status) "
        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        pst = con.prepareStatement(sqlI);
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
        
        pst.setInt(1, cPagarVO.getCredorFk());
        pst.setDate(2, new java.sql.Date(sdf.parse(cPagarVO.getDtDocumento()).getTime()));
        pst.setInt(3, cPagarVO.getEspecieDocumentoFk());
        pst.setInt(4, cPagarVO.getNumDocumento());
        pst.setInt(5, cPagarVO.getTpPagto());
        pst.setInt(6, cPagarVO.getFormaPagto());
        pst.setDouble(7, cPagarVO.getVlDocumento());
        pst.setDate(8, new java.sql.Date(sdf.parse(cPagarVO.getDtVencimento()).getTime()));
        pst.setString(9, cPagarVO.getCodBarra());
        pst.setDouble(10, cPagarVO.getVlPagar());
        pst.setDouble(11, cPagarVO.getVlJuros());
        pst.setDouble(12, cPagarVO.getVlMulta());
        pst.setDate(13, new java.sql.Date(sdf.parse(cPagarVO.getDtQuitacao()).getTime()));
        pst.setString(14, cPagarVO.getStatus());
        //System.out.println(pst.toString());
        pst.execute();
    }
    
    public void salvarContasPagar(ContasPagar1VO cPagarVO) throws SQLException, ParseException{
        
        java.lang.String sqlI = "INSERT INTO Tbcontaspagar (CredorFk, DtDocumento, EspecieDocumentoFk, NumDocumento,"
        + " TpPagto, FormaPagto, VlDocumento, DtVencimento, CodBarra, VlPagar, TpJuros, VlJuros, CodTpCobrarJurosFk,"
        + " TpMulta, VlMulta, TipoDoTitulo, DtQuitacao, Status) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        pst = con.prepareStatement(sqlI);
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
        
//        pst.setString(1, cPagarVO.getLblCodConta());
        pst.setString(1, cPagarVO.getTxtCredor());
        pst.setDate(2, new java.sql.Date(sdf.parse(cPagarVO.getJftDtDocumento()).getTime()));
        pst.setString(3, cPagarVO.getJcbEspDocumento());
        pst.setString(4, cPagarVO.getTxtNumDocumento());
        pst.setString(5, cPagarVO.getJcbTpPagto());
        pst.setInt(6, cPagarVO.getJcbFormaPagto());
        pst.setString(7, cPagarVO.getTxtVlDocumento().replace(".", "").replace(",", "."));
        pst.setDate(8, new java.sql.Date(sdf.parse(cPagarVO.getJftDtVencimento()).getTime()));
        pst.setString(9, cPagarVO.getTxtCodBarra());
        pst.setString(10, cPagarVO.getTxtVlPagar().replace(".", "").replace(",", "."));
        pst.setString(11, cPagarVO.getJrbTpTaxaJuros());
        pst.setString(12, cPagarVO.getTxtTxJuros().replace(",", "."));
        pst.setString(13, cPagarVO.getJcbTpFormaCobranca());
        pst.setString(14, cPagarVO.getJrbTpTaxaMulta());
        pst.setString(15, cPagarVO.getTxtTxMulta().replace(",", "."));
        pst.setString(16, cPagarVO.getJrbTpTitulo());
        pst.setDate(17, new java.sql.Date(sdf.parse(cPagarVO.getJftDtQuitacao()).getTime()));
        pst.setString(18, cPagarVO.getJcbSituacao());
        //System.out.println(pst.toString());
        pst.execute();
    }

    public DevedorVO buscarDevdorByPk(DevedorVO dvVO) throws SQLException, ParseException{
        
        java.lang.String sqlS = "SELECT SELECT DevdorPk, NomeDevedor FROM TbDevedor WHERE DevedorPk=?";

        pst = con.prepareStatement(sqlS);
        pst.setInt(1, Integer.valueOf(dvVO.getDevedorPk()));
        rs = pst.executeQuery();
        while(rs.next()){
            dvVO.setDevedorPk(rs.getInt("DevedorPk"));
            dvVO.setNomeDevedor(rs.getString("NomeDevedor"));
        }
        return dvVO;
    }
    
    public List<DevedorVO> buscarAllDevedor() throws SQLException, ParseException{
        List<DevedorVO> devedor = new ArrayList<DevedorVO>();
        DevedorVO dvVO = null;
        java.lang.String sqlS = "SELECT ClientePk, Nome FROM Cliente";
        
        pst = con.prepareStatement(sqlS);
        rs = pst.executeQuery();
        while(rs.next()){
            dvVO = new DevedorVO();
            dvVO.setDevedorPk(rs.getInt("ClientePk"));
            dvVO.setNomeDevedor(rs.getString("Nome"));
            
            devedor.add(dvVO);
        }
        return (List<DevedorVO>) devedor;
    }
    
    /*
     * Atualiza a contas no banco de dados
     */
    public void updateContasPagar(ContasPagar1VO cPagarVO) throws SQLException, ParseException{
        
        java.lang.String sqlI = "UPDATE Tbcontaspagar SET CredorFk=?, DtDocumento=?, EspecieDocumentoFk=?, NumDocumento=?,"
        + " TpPagto=?, FormaPagto=?, VlDocumento=?, DtVencimento=?, CodBarra=?, VlPagar=?, TpJuros=?, VlJuros=?, CodTpCobrarJurosFk=?,"
        + " TpMulta=?, VlMulta=?, TipoDoTitulo=?, DtQuitacao=?, Status=? WHERE ContasPagarPk=?";
        pst = con.prepareStatement(sqlI);
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
        FormataValor fv = new FormataValor();
        
        pst.setString(1, cPagarVO.getTxtCredor());
        pst.setDate(2, new java.sql.Date(sdf.parse(cPagarVO.getJftDtDocumento()).getTime()));
        pst.setString(3, cPagarVO.getJcbEspDocumento());
        pst.setString(4, cPagarVO.getTxtNumDocumento());
        pst.setString(5, cPagarVO.getJcbTpPagto());
        pst.setInt(6, cPagarVO.getJcbFormaPagto());
        pst.setDouble(7, Double.valueOf(cPagarVO.getTxtVlDocumento().length() > 6 ? cPagarVO.getTxtVlDocumento().replace(".","").replace(",",".") : cPagarVO.getTxtVlDocumento().replace(",",".")));
        pst.setDate(8, new java.sql.Date(sdf.parse(cPagarVO.getJftDtVencimento()).getTime()));
        pst.setString(9, cPagarVO.getTxtCodBarra());
        pst.setString(10, cPagarVO.getTxtVlPagar().replace(".", "").replace(",", "."));
        pst.setString(11, cPagarVO.getJrbTpTaxaJuros());
        pst.setString(12, cPagarVO.getTxtTxJuros().replace(",", "."));
        pst.setString(13, cPagarVO.getJcbTpFormaCobranca());
        pst.setString(14, cPagarVO.getJrbTpTaxaMulta());
        pst.setString(15, cPagarVO.getTxtTxMulta().replace(",", "."));
        pst.setString(16, cPagarVO.getJrbTpTitulo());
        pst.setDate(17, new java.sql.Date(sdf.parse(cPagarVO.getJftDtQuitacao()).getTime()));
        pst.setString(18, cPagarVO.getJcbSituacao());
        pst.setString(19, cPagarVO.getLblCodConta());
        //System.out.println(pst.toString());
        pst.execute();
    }
    
}
