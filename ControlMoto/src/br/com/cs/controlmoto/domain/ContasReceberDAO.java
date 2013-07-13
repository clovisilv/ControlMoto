/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cs.controlmoto.domain;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.cs.controlmoto.utils.ConvertStringToDate;
import br.com.cs.controlmoto.utils.FormataValor;
import br.com.cs.controlmoto.vo.ContasReceber1VO;
import br.com.cs.controlmoto.vo.ContasReceberVO;
import br.com.cs.controlmoto.vo.EspecieDocumentoVO;
import br.com.cs.controlmoto.vo.FormaCobrancaJurosVO;
import br.com.cs.controlmoto.vo.FormaPagtoVO;
import br.com.cs.controlmoto.vo.SituacaoVO;
import br.com.cs.controlmoto.vo.TipoPagtoVO;


/**
 * Company Fictec Consul. Inf.
 * @author clovis
 * @version 1.0.1
 * @since 16092012
 */
public class ContasReceberDAO {
    
    private java.sql.Connection con;
    private java.sql.PreparedStatement pst;
    private java.sql.ResultSet rs;

    public ContasReceberDAO() throws SQLException, ClassNotFoundException {
        try {
			this.con = ConnectionFactory.getConnection();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
    }
    
    public void closeContasReceberDAO() throws SQLException, ClassNotFoundException {
        this.con.close();
    }
    
    public void insertContasReceber(ContasReceberVO cReceberVO) throws SQLException, ParseException{
        
        java.lang.String sqlI = "INSERT INTO Tbcontasreceber (DevedorFK, DtDocumento, EspecieDocumentoFk, NumDocumento, TpPagto,"
        + "FormaPagto, VlDocumento, DtVencimento, VlReceber, VlJuros, VlMulta, DtQuitacao, Status) "
        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        pst = con.prepareStatement(sqlI);
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
        
        pst.setInt(1, cReceberVO.getDevedorFk());
        pst.setDate(2, new java.sql.Date(sdf.parse(cReceberVO.getDtDocumento()).getTime()));
        pst.setInt(3, cReceberVO.getEspecieDocumentoFk());
        pst.setInt(4, cReceberVO.getNumDocumento());
        pst.setInt(5, cReceberVO.getTpPagto());
        pst.setInt(6, cReceberVO.getFormaPagto());
        pst.setDouble(7, cReceberVO.getVlDocumento());
        pst.setDate(8, new java.sql.Date(sdf.parse(cReceberVO.getDtVencimento()).getTime()));
        pst.setString(9, cReceberVO.getCodBarra());
        pst.setDouble(10, cReceberVO.getVlReceber());
        pst.setDouble(11, cReceberVO.getVlJuros());
        pst.setDouble(12, cReceberVO.getVlMulta());
        pst.setDate(13, new java.sql.Date(sdf.parse(cReceberVO.getDtQuitacao()).getTime()));
        pst.setString(14, cReceberVO.getStatus());
        //System.out.println(pst.toString());
        pst.execute();
    }
    
    public void salvarContasReceber(ContasReceber1VO cReceberVO) throws SQLException, ParseException{
        
        java.lang.String sqlI = "INSERT INTO Tbcontasreceber (DevedorFk, DtDocumento, EspecieDocumentoFk, NumDocumento,"
        + " TpPagto, FormaPagto, VlDocumento, DtVencimento, CodBarra, VlReceber, TpJuros, VlJuros, CodTpCobrarJurosFk,"
        + " TpMulta, VlMulta, TipoDoTitulo, DtQuitacao, Status) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        pst = con.prepareStatement(sqlI);
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
        
        pst.setString(1, cReceberVO.getTxtCredor());
        if(!"null".equals(cReceberVO.getJftDtDocumento()))
        	pst.setDate(2, new java.sql.Date(sdf.parse(cReceberVO.getJftDtDocumento()).getTime()));
        pst.setString(3, cReceberVO.getJcbEspDocumento());
        pst.setString(4, cReceberVO.getTxtNumDocumento());
        pst.setString(5, cReceberVO.getJcbTpPagto());
        pst.setInt(6, cReceberVO.getJcbFormaPagto());
        pst.setString(7, cReceberVO.getTxtVlDocumento().replace(".", "").replace(",", "."));
        if(!"null".equals(cReceberVO.getJftDtVencimento()))
        	pst.setDate(8, new java.sql.Date(sdf.parse(cReceberVO.getJftDtVencimento()).getTime()));
        pst.setString(9, cReceberVO.getTxtCodBarra());
        pst.setString(10, cReceberVO.getTxtVlReceber().replace(".", "").replace(",", "."));
        pst.setString(11, cReceberVO.getJrbTpTaxaJuros());
        pst.setString(12, cReceberVO.getTxtTxJuros().replace(",", "."));
        pst.setString(13, cReceberVO.getJcbTpFormaCobranca());
        pst.setString(14, cReceberVO.getJrbTpTaxaMulta());
        pst.setString(15, cReceberVO.getTxtTxMulta().replace(",", "."));
        pst.setString(16, cReceberVO.getJrbTpTitulo());
        //if(!"null".equals(cReceberVO.getJftDtQuitacao()))
        pst.setDate(17, "null".equals(cReceberVO.getJftDtQuitacao()) ? null : new java.sql.Date(sdf.parse(cReceberVO.getJftDtQuitacao()).getTime()));
        pst.setString(18, cReceberVO.getJcbSituacao());
        //System.out.println(pst.toString());
        pst.execute();
    }
    
    /*
     * Instrucao para salvar parcelas de pagamento, quando o usuario optar por uma forma maior que 1
     */
    public void salvarContasReceberParcelas(ContasReceber1VO cReceberVO) throws SQLException, ParseException{
       int contador = 1;
       while(Integer.valueOf(cReceberVO.getJcbFormaPagto().toString()) >= contador){ 
            java.lang.String sqlI = "INSERT INTO Tbcontasreceber (DevedorFk, DtDocumento, EspecieDocumentoFk, NumDocumento,"
            + " TpPagto, FormaPagto, VlDocumento, DtVencimento, CodBarra, VlReceber, TpJuros, VlJuros, CodTpCobrarJurosFk,"
            + " TpMulta, VlMulta, TipoDoTitulo, Status) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            pst = con.prepareStatement(sqlI);

            SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
            Calendar cal = Calendar.getInstance();
            
            pst.setString(1, cReceberVO.getTxtCredor());
            if(!"null".equals(cReceberVO.getJftDtDocumento()))
                pst.setDate(2, new java.sql.Date(sdf.parse(cReceberVO.getJftDtDocumento()).getTime()));
            pst.setString(3, cReceberVO.getJcbEspDocumento());
            pst.setString(4, cReceberVO.getTxtNumDocumento()+" 1/"+contador);
            pst.setString(5, cReceberVO.getJcbTpPagto());
            pst.setInt(6, cReceberVO.getJcbFormaPagto());
            pst.setString(7, cReceberVO.getTxtVlDocumento().replace(".", "").replace(",", "."));
            if(!"null".equals(cReceberVO.getJftDtVencimento()))
                cal.setTime(new java.sql.Date(sdf.parse(cReceberVO.getJftDtVencimento()).getTime()));
                cal.add(Calendar.DATE, (30*contador));
                pst.setDate(8, new java.sql.Date(cal.getTimeInMillis()));
            pst.setString(9, cReceberVO.getTxtCodBarra());
            pst.setString(10, cReceberVO.getTxtVlReceber().replace(".", "").replace(",", "."));
            pst.setString(11, cReceberVO.getJrbTpTaxaJuros());
            pst.setString(12, cReceberVO.getTxtTxJuros().replace(",", "."));
            pst.setString(13, cReceberVO.getJcbTpFormaCobranca());
            pst.setString(14, cReceberVO.getJrbTpTaxaMulta());
            pst.setString(15, cReceberVO.getTxtTxMulta().replace(",", "."));
            pst.setString(16, cReceberVO.getJrbTpTitulo());
            pst.setString(17, cReceberVO.getJcbSituacao());
            //System.out.println(pst.toString());
            pst.execute();
            contador ++;
       }
    }
    
    public ContasReceberVO controlarContasReceberPk(ContasReceberVO cpVO) throws SQLException, ParseException{
        //java.lang.String sqlS = "SELECT COUNT(ContasReceberPk) As ContasReceberPk  FROM Tbcontasreceber";
        java.lang.String sqlS = "SELECT ContasReceberPk FROM Tbcontasreceber ORDER BY ContasReceberPk DESC LIMIT 1";
        pst = con.prepareStatement(sqlS);
        rs = pst.executeQuery();
        while(rs.next()){
            cpVO.setContasReceberPk(rs.getInt("ContasReceberPk"));
        }
        return cpVO;
    }

    public ContasReceber1VO buscarContasReceberByPk(ContasReceber1VO crVO) throws SQLException, ParseException{
        
        java.lang.String sqlS = "SELECT cr.ContasReceberPk, cr.DevedorFk, c.Nome, cr.DtDocumento, cr.EspecieDocumentoFk,"
        + " cr.NumDocumento, cr.TpPagto, cr.FormaPagto, cr.VlDocumento, cr.DtVencimento, cr.VlReceber, cr.TpJuros, cr.VlJuros,"
        + " cr.CodTpCobrarJurosFk, cr.TpMulta, cr.VlMulta, cr.TipoDoTitulo, cr.DtQuitacao, cr.Status "
        + "FROM Tbcontasreceber AS cr JOIN Cliente AS c ON cr.DevedorFk = c.ClientePk"
        + " WHERE cr.ContasReceberPk=?";

        pst = con.prepareStatement(sqlS);
        pst.setInt(1, Integer.valueOf(crVO.getTxtCodContaList()));
        rs = pst.executeQuery();
        while(rs.next()){
            crVO.setLblCodConta(rs.getString("ContasReceberPk"));
            crVO.setDevedorFk(rs.getInt("DevedorFk"));
            crVO.setNomeDevedor(rs.getString("Nome"));
            crVO.setJftDtDocumento(rs.getString("DtDocumento"));
            crVO.setJcbEspDocumento( rs.getString("EspecieDocumentoFk"));
            crVO.setTxtNumDocumento(rs.getString("NumDocumento"));
            crVO.setJcbTpPagto(rs.getString("TpPagto"));
            crVO.setJcbFormaPagto(rs.getInt("FormaPagto"));
            crVO.setTxtVlDocumento(rs.getString("VlDocumento"));
            crVO.setJftDtVencimento(rs.getString("DtVencimento"));
//            crVO.setTxtCodBarra(rs.getString("CodBarra"));
            crVO.setTxtVlReceber(rs.getString("VlReceber")) ;
            crVO.setJrbTpTaxaJuros(rs.getString("TpJuros")) ;
            crVO.setTxtTxJuros(rs.getString("VlJuros")) ;
            crVO.setJcbTpFormaCobranca(rs.getString("CodTpCobrarJurosFk")) ;
            crVO.setJrbTpTaxaMulta(rs.getString("TpMulta")) ;
            crVO.setTxtTxMulta(rs.getString("VlMulta"));
            crVO.setJrbTpTitulo(rs.getString("TipoDoTitulo"));
            crVO.setJftDtQuitacao(rs.getString("DtQuitacao")) ;
            crVO.setJcbSituacao(rs.getString("Status"));
        }
        return crVO;
    }
    
    public List<FormaPagtoVO> buscarFormaPagto() throws SQLException, ParseException{
        List<FormaPagtoVO> formasPagto = new ArrayList<FormaPagtoVO>();
        FormaPagtoVO fpVO = null;
        java.lang.String sqlS = "SELECT FormaPagtoPk, CodForma, Descricao FROM TbFormaPagto";
        
        pst = con.prepareStatement(sqlS);
        rs = pst.executeQuery();
        while(rs.next()){
            fpVO = new FormaPagtoVO();
            fpVO.setFormaPagtoPk(rs.getInt("FormaPagtoPk"));
            fpVO.setCodForma(rs.getInt("CodForma"));
            fpVO.setDescricao(rs.getString("Descricao"));
            
            formasPagto.add(fpVO);
        }
        return (List<FormaPagtoVO>) formasPagto;
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
    public List<SituacaoVO> buscarSituacaoContasReceber() throws SQLException, ParseException{

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
     * Metodo que carrega do JTable com as Contas a Receber por Codigo da Conta
     */
    public List<ContasReceber1VO> carregaJTableContasReceberByCodigo(java.lang.Integer codigo) throws SQLException{
        
        ArrayList cpVectorVO, cpvAtualVO;
        cpVectorVO = new ArrayList<ContasReceber1VO>();
        ContasReceber1VO crVO;
        java.lang.String sqlS = "SELECT cr.ContasReceberPk, cr.DevedorFk, c.Nome, cr.DtDocumento, cr.NumDocumento, cr.DtVencimento,"
        + " cr.VlReceber, cr.Status FROM Tbcontasreceber cr JOIN Cliente c ON cr.DevedorFk = c.ClientePk "
        + "WHERE cr.ContasReceberPk=?";
        
        pst = con.prepareStatement(sqlS);
        pst.setInt(1, codigo);
        rs = pst.executeQuery();
        while(rs.next()){
           crVO = new ContasReceber1VO();
            
           crVO.setLblContasReceberPk(rs.getInt("ContasReceberPk"));
           crVO.setNomeDevedor(rs.getString("Nome"));
           crVO.setDevedorFk(rs.getInt("DevedorFk"));
           crVO.setJftDtDocumento(rs.getString("DtDocumento"));
           crVO.setTxtNumDocumento(rs.getString("NumDocumento"));
           crVO.setJftDtVencimento(rs.getString("DtVencimento"));
           crVO.setTxtVlReceber(rs.getString("VlReceber"));
           crVO.setJcbSituacao(rs.getString("Status"));
           
           cpVectorVO.add(crVO);
        }
        return cpVectorVO;
    }
    
    /*
     * Metodo que carrega do JTable com as Contas a Receber por periodo
     */
    public List<ContasReceber1VO> carregaJTableContasReceberByPeriodoVencimento(ContasReceber1VO crVO) throws SQLException{
        
        ArrayList cpVectorVO, cpvAtualVO;
        cpVectorVO = new ArrayList<ContasReceber1VO>();
        ConvertStringToDate conStringToDate = new ConvertStringToDate();
        java.lang.String sqlS = "SELECT cr.ContasReceberPk, cr.DevedorFk, c.Nome, cr.DtDocumento, cr.NumDocumento, cr.DtVencimento,"
        + " cr.VlReceber, cr.Status FROM Tbcontasreceber cr JOIN Cliente c ON cr.DevedorFk = c.ClientePk "
        + " WHERE cr.DtVencimento BETWEEN ? AND ? ";
        
        pst = con.prepareStatement(sqlS);
        pst.setDate(1,(java.sql.Date) crVO.getDataInicio());
        pst.setDate(2,(java.sql.Date) crVO.getDataFim());
        rs = pst.executeQuery();
        while(rs.next()){
           crVO = new ContasReceber1VO();
            
           crVO.setLblContasReceberPk(rs.getInt("ContasReceberPk"));
           crVO.setNomeDevedor(rs.getString("Nome"));
           crVO.setDevedorFk(rs.getInt("DevedorFk"));
           crVO.setJftDtDocumento(rs.getString("DtDocumento"));
           crVO.setTxtNumDocumento(rs.getString("NumDocumento"));
           crVO.setJftDtVencimento(rs.getString("DtVencimento"));
           crVO.setTxtVlReceber(rs.getString("VlReceber"));
           crVO.setJcbSituacao(rs.getString("Status"));
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      
           cpVectorVO.add(crVO);
        }
        return cpVectorVO;
    }
     /*
     * Metodo que carrega do JTable com as Contas a Receber por periodo e nome do credor
     */
    public List<ContasReceber1VO> carregaJTableContasReceberByVencimentoAndCredor(ContasReceber1VO crVO) throws SQLException{
        
        ArrayList cpVectorVO, cpvAtualVO;
        cpVectorVO = new ArrayList<ContasReceber1VO>();
        ConvertStringToDate conStringToDate = new ConvertStringToDate();
        java.lang.String sqlS = "SELECT cr.ContasReceberPk, cr.DevedorFk, c.Nome, cr.DtDocumento, cr.NumDocumento, cr.DtVencimento,"
        + " VlReceber, cr.Status FROM Tbcontasreceber cr JOIN Cliente c ON cr.DevedorFk = d.DevedorPk "
        + " WHERE cr.DevedorFk = ? AND cr.DtVencimento BETWEEN ? AND ? ";
        
        pst = con.prepareStatement(sqlS);
        //pst.setString(1, crVO.getNomeDevedor());
        pst.setInt(1, crVO.getDevedorFk());
        pst.setDate(2,(java.sql.Date) crVO.getDataInicio());
        pst.setDate(3,(java.sql.Date) crVO.getDataFim());
        rs = pst.executeQuery();
        while(rs.next()){
           crVO = new ContasReceber1VO();
            
           crVO.setLblContasReceberPk(rs.getInt("ContasReceberPk"));
           crVO.setNomeDevedor(rs.getString("Nome"));
           crVO.setDevedorFk(rs.getInt("DevedorFk"));
           crVO.setJftDtDocumento(rs.getString("DtDocumento"));
           crVO.setTxtNumDocumento(rs.getString("NumDocumento"));
           crVO.setJftDtVencimento(rs.getString("DtVencimento"));
           crVO.setTxtVlReceber(rs.getString("VlReceber"));
           crVO.setJcbSituacao(rs.getString("Status"));
           
           cpVectorVO.add(crVO);
        }
        System.err.println(pst.toString());
        return cpVectorVO;
    }
    /*
     * Atualiza a contas no banco de dados
     */
    public void updateContasReceber(ContasReceber1VO cReceberVO) throws SQLException, ParseException{
        
        java.lang.String sqlI = "UPDATE Tbcontasreceber SET DevedorFk=?, DtDocumento=?, EspecieDocumentoFk=?, NumDocumento=?,"
        + " TpPagto=?, FormaPagto=?, VlDocumento=?, DtVencimento=?, CodBarra=?, VlReceber=?, TpJuros=?, VlJuros=?, CodTpCobrarJurosFk=?,"
        + " TpMulta=?, VlMulta=?, TipoDoTitulo=?, DtQuitacao=?, Status=? WHERE ContasReceberPk=?";
        pst = con.prepareStatement(sqlI);
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
        FormataValor fv = new FormataValor();
        ConvertStringToDate cv = new ConvertStringToDate();
        
        pst.setString(1, cReceberVO.getTxtCredor());
        pst.setDate(2, new java.sql.Date(sdf.parse(cReceberVO.getJftDtDocumento()).getTime()));
        pst.setString(3, cReceberVO.getJcbEspDocumento());
        pst.setString(4, cReceberVO.getTxtNumDocumento());
        pst.setString(5, cReceberVO.getJcbTpPagto());
        pst.setInt(6, cReceberVO.getJcbFormaPagto());
        pst.setDouble(7, Double.valueOf(cReceberVO.getTxtVlDocumento().length() > 6 ? cReceberVO.getTxtVlDocumento().replace(".","").replace(",",".") : cReceberVO.getTxtVlDocumento().replace(",",".")));
        //pst.setDate(8, new java.sql.Date(sdf.parse(cReceberVO.getJftDtVencimento()).getTime()));
        pst.setDate(8, cv.convertStringToDates(cReceberVO.getJftDtVencimento()));
        pst.setString(9, cReceberVO.getTxtCodBarra());
        pst.setString(10, cReceberVO.getTxtVlReceber().replace(".", "").replace(",", "."));
        pst.setString(11, cReceberVO.getJrbTpTaxaJuros());
        pst.setString(12, cReceberVO.getTxtTxJuros().replace(",", "."));
        pst.setString(13, cReceberVO.getJcbTpFormaCobranca());
        pst.setString(14, cReceberVO.getJrbTpTaxaMulta());
        pst.setString(15, cReceberVO.getTxtTxMulta().replace(",", "."));
        pst.setString(16, cReceberVO.getJrbTpTitulo());
        if(!"  /  /    ".equals(cReceberVO.getJftDtQuitacao())){
            pst.setDate(17, new java.sql.Date(sdf.parse(cReceberVO.getJftDtQuitacao()).getTime()));
        }else{
            pst.setDate(17, null);
        }
        pst.setString(18, cReceberVO.getJcbSituacao());
        pst.setString(19, cReceberVO.getLblCodConta());
        //System.out.println(pst.toString());
        pst.execute();
    }
    /*
     * Apaga a contas no banco de dados
     */
    public void deleteContasReceber(ContasReceber1VO cReceberVO) throws SQLException, ParseException{
        
        java.lang.String sqlI = "DELETE FROM Tbcontasreceber WHERE ContasReceberPk=?";
        pst = con.prepareStatement(sqlI);
        pst.setInt(1, cReceberVO.getLblContasReceberPk());
        pst.execute();
    }
    
}
