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

import br.com.cs.controlmoto.utils.ConvertStringToDate;
import br.com.cs.controlmoto.utils.FormataValor;
import br.com.cs.controlmoto.vo.ContasPagar1VO;
import br.com.cs.controlmoto.vo.ContasPagarVO;
import br.com.cs.controlmoto.vo.CredorVO;
import br.com.cs.controlmoto.vo.EspecieDocumentoVO;
import br.com.cs.controlmoto.vo.FormaCobrancaJurosVO;
import br.com.cs.controlmoto.vo.FormaPagtoVO;
import br.com.cs.controlmoto.vo.SituacaoVO;
import br.com.cs.controlmoto.vo.TipoPagtoVO;


/**
 * Company Fictec Consul. Inf.
 * @author Clovis
 * @version 1.0.0
 * @since 15092012
 */
public class CredorDAO {
    
    private java.sql.Connection con;
    private java.sql.PreparedStatement pst;
    private java.sql.ResultSet rs;

    public CredorDAO() {
        try {
			this.con = ConnectionFactory.getConnection();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
    }
    
    public void closeContasPagarDAO() throws SQLException, ClassNotFoundException {
        this.con.close();
    }
    
    public void insertContasPagar(ContasPagarVO cPagarVO) throws SQLException, ParseException{
        
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
    
    public ContasPagarVO controlarContasPagarPk(ContasPagarVO cpVO) throws SQLException, ParseException{
        
        java.lang.String sqlS = "SELECT COUNT(ContasPagarPk) As ContasPagarPk  FROM Tbcontaspagar";
        
        pst = con.prepareStatement(sqlS);
        rs = pst.executeQuery();
        while(rs.next()){
            cpVO.setContasPagarPk(rs.getInt("ContasPagarPk"));
            
        }
        return cpVO;
    }

    public ContasPagar1VO buscarContasPagarByPk(ContasPagar1VO cpVO) throws SQLException, ParseException{
        
        java.lang.String sqlS = "SELECT cp.ContasPagarPk, cp.CredorFk, c.NomeCredor, cp.DtDocumento, cp.EspecieDocumentoFk,"
        + " cp.NumDocumento, cp.TpPagto, cp.FormaPagto, cp.VlDocumento, cp.DtVencimento, cp.CodBarra, cp.VlPagar, cp.TpJuros,"
        + " cp.VlJuros, cp.CodTpCobrarJurosFk, cp.TpMulta, cp.VlMulta, cp.TipoDoTitulo, cp.DtQuitacao, cp.Status "
        + " FROM Tbcontaspagar AS cp JOIN Tbcredor AS c ON cp.CredorFk = c.CredorPk WHERE cp.ContasPagarPk=?";

        pst = con.prepareStatement(sqlS);
        pst.setInt(1, Integer.valueOf(cpVO.getTxtCodContaList()));
        rs = pst.executeQuery();
        while(rs.next()){
            cpVO.setLblCodConta(rs.getString("ContasPagarPk"));
            cpVO.setTxtCredor(rs.getString("CredorFk"));
            cpVO.setNomeCredor(rs.getString("NomeCredor"));
            cpVO.setJftDtDocumento(rs.getString("DtDocumento"));
            cpVO.setJcbEspDocumento( rs.getString("EspecieDocumentoFk"));
            cpVO.setTxtNumDocumento(rs.getString("NumDocumento"));
            cpVO.setJcbTpPagto(rs.getString("TpPagto"));
            cpVO.setJcbFormaPagto(rs.getInt("FormaPagto"));
            cpVO.setTxtVlDocumento(rs.getString("VlDocumento"));
            cpVO.setJftDtVencimento(rs.getString("DtVencimento"));
            cpVO.setTxtCodBarra(rs.getString("CodBarra")) ;
            cpVO.setTxtVlPagar(rs.getString("VlPagar")) ;
            cpVO.setJrbTpTaxaJuros(rs.getString("TpJuros")) ;
            cpVO.setTxtTxJuros(rs.getString("VlJuros")) ;
            cpVO.setJcbTpFormaCobranca(rs.getString("CodTpCobrarJurosFk")) ;
            cpVO.setJrbTpTaxaMulta(rs.getString("TpMulta")) ;
            cpVO.setTxtTxMulta(rs.getString("VlMulta"));
            cpVO.setJrbTpTitulo(rs.getString("TipoDoTitulo"));
            cpVO.setJftDtQuitacao(rs.getString("DtQuitacao")) ;
            cpVO.setJcbSituacao(rs.getString("Status"));
        }
        return cpVO;
    }
    
    public List<CredorVO> buscarAllCredor() throws SQLException, ParseException{
        List<CredorVO> credor = new ArrayList<CredorVO>();
        CredorVO cdVO = null;
        java.lang.String sqlS = "SELECT FornecedorPk, Nome FROM Fornecedor";
        
        pst = con.prepareStatement(sqlS);
        rs = pst.executeQuery();
        while(rs.next()){
            cdVO = new CredorVO();
            cdVO.setCredorPk(rs.getInt("FornecedorPk"));
            cdVO.setNomeCredor(rs.getString("Nome"));
            credor.add(cdVO);
        }
        return (List<CredorVO>) credor;
    }
    
    /*
     * Metodo que carrega informacoes da tabela Forma de Pagto para preencher a combo Forma de Pagto
     */
    public FormaPagtoVO buscarDescricaoFormaPagtoByCodForma(FormaPagtoVO fpVO) throws SQLException, ParseException{

        java.lang.String sqlS = "SELECT Descricao  FROM TbFormaPagto WHERE CodForma=?";

        pst = con.prepareStatement(sqlS);
        pst.setInt(1, fpVO.getCodForma());
        rs = pst.executeQuery();
        while(rs.next()){
            fpVO.setDescricao(rs.getString("Descricao"));
        }
        return fpVO;
    }
    
    /*
     * Medoto que carrage as infomracoes da tabela Tipo Pagto para preencher a combo Tipo Pagto.
     */
    public List<TipoPagtoVO> buscarTipoPagto() throws SQLException, ParseException{
        List<TipoPagtoVO> tipoPagto = new ArrayList<TipoPagtoVO>();
        TipoPagtoVO tpVO = null;
        java.lang.String sqlS = "SELECT TipoPagtoPk, CodTpPagto, Descricao  FROM TbTipoPagto";
        
        pst = con.prepareStatement(sqlS);
        rs = pst.executeQuery();
        while(rs.next()){
            tpVO = new TipoPagtoVO();
            tpVO.setTipoPagtoPk(rs.getInt("TipoPagtoPk"));
            tpVO.setCodTpPagto(rs.getString("CodTpPagto"));
            tpVO.setDescricao(rs.getString("Descricao"));
            
            tipoPagto.add(tpVO);
        }
        return (List<TipoPagtoVO>) tipoPagto;
    }
    
    /*
     * Medoto que carrage as infomracoes da tabela Tipo Pagto para preencher o label lblFormaPagtoDescricao
     */
    public TipoPagtoVO buscarTipoPagtoDescricaoByPk(TipoPagtoVO tpVOs) throws SQLException, ParseException{

        TipoPagtoVO tpVO = null;
        java.lang.String sqlS = "SELECT TipoPagtoPk, CodTpPagto,Descricao FROM TbTipoPagto WHERE CodTpPagto=?";
        
        pst = con.prepareStatement(sqlS);
        pst.setString(1, tpVOs.getCodTpPagto());
        rs = pst.executeQuery();
        while(rs.next()){
            tpVO = new TipoPagtoVO();
            tpVO.setTipoPagtoPk(rs.getInt("TipoPagtoPk"));
            tpVO.setCodTpPagto(rs.getString("CodTpPagto"));
            tpVO.setDescricao(rs.getString("Descricao"));
        }
        return tpVO;
    }

    /*
     * Medoto que carraga informacoes da tabela Especie de Documento
     */
    public List<EspecieDocumentoVO> buscarEspecieDocumento() throws SQLException, ParseException{
        List<EspecieDocumentoVO> espDocumento = new ArrayList<EspecieDocumentoVO>();
        EspecieDocumentoVO edVO = null;
        java.lang.String sqlS = "SELECT EspecieDocumentoPk, CodEspecDocumento, Descricao  FROM TbEspecieDocumento";
        
        pst = con.prepareStatement(sqlS);
        rs = pst.executeQuery();
        while(rs.next()){
            edVO = new EspecieDocumentoVO();
            edVO.setEspecieDocumentoPk(rs.getInt("EspecieDocumentoPk"));
            edVO.setCodEspDocumento(rs.getString("CodEspecDocumento"));
            edVO.setDescricao(rs.getString("Descricao"));
            
            espDocumento.add(edVO);
        }
        return (List<EspecieDocumentoVO>) espDocumento;
    }

    /*
     * Medoto que carraga informacoes da tabela Especie de Documento para preencher o lblEspDocumDescricao
     */
    public EspecieDocumentoVO buscarEspecieDocumentoDescricaoByPk(EspecieDocumentoVO edVOs) throws SQLException, ParseException{

        EspecieDocumentoVO edVO = null;
        java.lang.String sqlS = "SELECT EspecieDocumentoPk, CodEspecDocumento, Descricao  FROM TbEspecieDocumento WHERE CodEspecDocumento=?";
        
        pst = con.prepareStatement(sqlS);
        pst.setString(1, edVOs.getCodEspDocumento());
        rs = pst.executeQuery();
        while(rs.next()){
            edVO = new EspecieDocumentoVO();
            edVO.setEspecieDocumentoPk(rs.getInt("EspecieDocumentoPk"));
            edVO.setCodEspDocumento(rs.getString("CodEspecDocumento"));
            edVO.setDescricao(rs.getString("Descricao"));
        }
        return edVO;
    }

    /*
     * Medoto que carraga informacoes da tabela situacao para preencher a combo situacao.
     */
    public List<SituacaoVO> buscarSituacaoContasPagar() throws SQLException, ParseException{

        List<SituacaoVO> stVOs = new ArrayList<SituacaoVO>();
        SituacaoVO stVO;
        java.lang.String sqlS = "SELECT Descricao FROM TbSituacao WHERE Utilizacao=?";
        
        pst = con.prepareStatement(sqlS);
        pst.setString(1, "CP");
        rs = pst.executeQuery();
        while(rs.next()){
            stVO = new SituacaoVO();
            stVO.setDescricao(rs.getString("Descricao"));
            
            stVOs.add(stVO);
        }
        return stVOs;
    }

    /*
     * Medoto que carraga informacoes da tabela situacao para preencher a combo situacao.
     */
    public List<FormaCobrancaJurosVO> buscarFormaCobrancaJuros() throws SQLException, ParseException{

        List<FormaCobrancaJurosVO> fcjVOs = new ArrayList<FormaCobrancaJurosVO>();
        FormaCobrancaJurosVO fcjVO;
        java.lang.String sqlS = "SELECT FormaCobrancaJurosPk, CodFormaCobrancaJuros, Descricao FROM TbFormaCobrancaJuros";
        
        pst = con.prepareStatement(sqlS);
        rs = pst.executeQuery();
        while(rs.next()){
            fcjVO = new FormaCobrancaJurosVO();
            fcjVO.setFormaCobrancaJurosPk(rs.getInt("FormaCobrancaJurosPk"));
            fcjVO.setCodFormaCobrancaJuros(rs.getString("CodFormaCobrancaJuros"));
            fcjVO.setDescricao(rs.getString("Descricao"));
            
            fcjVOs.add(fcjVO);
        }
        return fcjVOs;
    }
 
        /*
     * Medoto que carraga informacoes da tabela situacao para preencher a combo situacao.
     */
    public FormaCobrancaJurosVO buscarFormaCobrancaJurosByCod(FormaCobrancaJurosVO fcjVOs) throws SQLException, ParseException{

        FormaCobrancaJurosVO fcjVO = null;
        java.lang.String sqlS = "SELECT Descricao FROM TbFormaCobrancaJuros WHERE CodFormaCobrancaJuros=?";
        
        pst = con.prepareStatement(sqlS);
        pst.setString(1, fcjVOs.getCodFormaCobrancaJuros());
        rs = pst.executeQuery();
        while(rs.next()){
            fcjVO = new FormaCobrancaJurosVO();
            fcjVO.setDescricao(rs.getString("Descricao"));
        }
        return fcjVO;
    }
    
    /*
     * Metodo que carrega do JTable com as Contas a Pagar por Codigo da Conta
     */
    public List<ContasPagar1VO> carregaJTableContasPagarByCodigo(java.lang.Integer codigo) throws SQLException{
        
        ArrayList cpVectorVO, cpvAtualVO;
        cpVectorVO = new ArrayList<ContasPagar1VO>();
        ContasPagar1VO cpVO;
        java.lang.String sqlS = "SELECT ContasPagarPk, CredorFk, DtDocumento, NumDocumento, DtVencimento, VlPagar,"
        + " Status FROM Tbcontaspagar WHERE ContasPagarPk=?";
        
        pst = con.prepareStatement(sqlS);
        pst.setInt(1, codigo);
        rs = pst.executeQuery();
        while(rs.next()){
           cpVO = new ContasPagar1VO();
            
           cpVO.setLblContasPagarPk(rs.getInt("ContasPagarPk"));
           cpVO.setTxtCredor(rs.getString("CredorFk"));
           cpVO.setJftDtDocumento(rs.getString("DtDocumento"));
           cpVO.setTxtNumDocumento(rs.getString("NumDocumento"));
           cpVO.setJftDtVencimento(rs.getString("DtVencimento"));
           cpVO.setTxtVlPagar(rs.getString("VlPagar"));
           cpVO.setJcbSituacao(rs.getString("Status"));
           
           cpVectorVO.add(cpVO);
        }
        return cpVectorVO;
    }
    
     /*
     * Metodo que carrega do JTable com as Contas a Pagar por periodo
     */
    public List<ContasPagar1VO> carregaJTableContasPagarByPeriodoVencimento(ContasPagar1VO cpVO) throws SQLException{
        
        ArrayList cpVectorVO, cpvAtualVO;
        cpVectorVO = new ArrayList<ContasPagar1VO>();
        ConvertStringToDate conStringToDate = new ConvertStringToDate();
        java.lang.String sqlS = "SELECT ContasPagarPk, CredorFk, DtDocumento, NumDocumento, DtVencimento, VlPagar,"
        + " Status FROM Tbcontaspagar WHERE DtVencimento BETWEEN ? AND ? ";
        
        pst = con.prepareStatement(sqlS);
        pst.setDate(1,(java.sql.Date) cpVO.getDataInicio());
        pst.setDate(2,(java.sql.Date) cpVO.getDataFim());
        rs = pst.executeQuery();
        while(rs.next()){
           cpVO = new ContasPagar1VO();
            
           cpVO.setLblContasPagarPk(rs.getInt("ContasPagarPk"));
           cpVO.setTxtCredor(rs.getString("CredorFk"));
           cpVO.setJftDtDocumento(rs.getString("DtDocumento"));
           cpVO.setTxtNumDocumento(rs.getString("NumDocumento"));
           cpVO.setJftDtVencimento(rs.getString("DtVencimento"));
           cpVO.setTxtVlPagar(rs.getString("VlPagar"));
           cpVO.setJcbSituacao(rs.getString("Status"));
           
           cpVectorVO.add(cpVO);
        }
        return cpVectorVO;
    }
    
     /*
     * Metodo que carrega do JTable com as Contas a Pagar por periodo e nome do credor
     */
    public List<ContasPagar1VO> carregaJTableContasPagarByVencimentoAndCredor(ContasPagar1VO cpVO) throws SQLException{
        
        ArrayList cpVectorVO, cpvAtualVO;
        cpVectorVO = new ArrayList<ContasPagar1VO>();
        ConvertStringToDate conStringToDate = new ConvertStringToDate();
        java.lang.String sqlS = "SELECT ContasPagarPk, CredorFk, DtDocumento, NumDocumento, DtVencimento, VlPagar,"
        + " Status FROM Tbcontaspagar WHERE CredorFk = ? AND DtVencimento BETWEEN ? AND ? ";
        
        pst = con.prepareStatement(sqlS);
        pst.setString(1, cpVO.getNomeCredor());
        pst.setDate(2,(java.sql.Date) cpVO.getDataInicio());
        pst.setDate(3,(java.sql.Date) cpVO.getDataFim());
        rs = pst.executeQuery();
        while(rs.next()){
           cpVO = new ContasPagar1VO();
            
           cpVO.setLblContasPagarPk(rs.getInt("ContasPagarPk"));
           cpVO.setTxtCredor(rs.getString("CredorFk"));
           cpVO.setJftDtDocumento(rs.getString("DtDocumento"));
           cpVO.setTxtNumDocumento(rs.getString("NumDocumento"));
           cpVO.setJftDtVencimento(rs.getString("DtVencimento"));
           cpVO.setTxtVlPagar(rs.getString("VlPagar"));
           cpVO.setJcbSituacao(rs.getString("Status"));
           
           cpVectorVO.add(cpVO);
        }
        return cpVectorVO;
    }
    
    /* Não esta em uso
     * Metodo que carrega do JTable com as Contas a Pagar por Codigo da Conta
     */
//    public void carregaJTableContasPagar() throws SQLException{
//        
//        Vector cpVectorVO, cpvAtualVO;
//        cpVectorVO = new Vector<Vector>();
//        
//        java.lang.String sqlS = "SELECT ContasPagarPk, Credor, DtDocumento, NumDocumento, DtVencimento, Status FROM TbFormaCobrancaJuros";// WHERE CodFormaCobrancaJuros=?";
//        
//        pst = con.prepareStatement(sqlS);
////        pst.setString(1, fcjVOs.getCodFormaCobrancaJuros());
//        rs = pst.executeQuery();
//        while(rs.next()){
//           Vector registroAtual = new Vector<String>();
//            
//           String cod = rs.getString("ContasPagarPk");
//           String credor = rs.getString("CredorFk");
//           String dtDocumento = rs.getString("DtDocumento");
//           String numDocumento = rs.getString("");
//           String dtVenciemnto = rs.getString("DtVenciemnto");
//           String status = rs.getString("Status");
//           
//           registroAtual.add(cod);
//           registroAtual.add(credor);
//           registroAtual.add(dtDocumento);
//           registroAtual.add(numDocumento);
//           registroAtual.add(dtVenciemnto);
//           registroAtual.add(status);
//           
//           cpVectorVO.add(registroAtual);
//        }
//        
//    }
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
