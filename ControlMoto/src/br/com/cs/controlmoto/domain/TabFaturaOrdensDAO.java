package br.com.cs.controlmoto.domain;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.cs.controlmoto.utils.ConvertStringToDate;
import br.com.cs.controlmoto.vo.TabFaturaOrdensVO;

public class TabFaturaOrdensDAO {
	/**
	 * Variaveis para conexao ao banco de dados
	 */
	static Statement minhaSTM;
    static ResultSet rs;
    static Connection minhaConexao;
    static java.sql.PreparedStatement pst;
    ConvertStringToDate convData;
    /**
	 * Variaveis para conversao de datas
	 */
    SimpleDateFormat sdf;
	/**
	 * Metodo que localiza a ordem de servico no bd pelo Id.
	 * @param TabConsultaOrdensDAO
	 * @return
	 */
	public List<TabFaturaOrdensVO> readOrdemServicoByPk(TabFaturaOrdensVO consultaVO) throws IllegalAccessException, InstantiationException, SQLException{
		ArrayList<TabFaturaOrdensVO> obj = new ArrayList<TabFaturaOrdensVO>();
		TabFaturaOrdensVO consultasVO = null;
		convData = new ConvertStringToDate();
		minhaConexao = ConnectionFactory.getConnection();
		minhaSTM = minhaConexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		String SQL = ("Select O.OrdemPk, C.Nome, O.TotalCliente, O.DataInicio From Ordem O Inner Join Cliente C On C.ClientePk = O.ClienteOFK WHERE O.Status='F' AND O.OrdemPk ="+consultaVO.getOrdemPk());
		rs = minhaSTM.executeQuery(SQL);
			while(rs.next()){
				consultasVO = new TabFaturaOrdensVO();
				consultasVO.setOrdemPk(Integer.valueOf(rs.getString("OrdemPk")));
				consultasVO.setNomeCliente(rs.getString("Nome"));
				consultasVO.setTotalCliente(rs.getString("TotalCliente").replace(".", ","));
				try {
					consultasVO.setDataOS(convData.formatDate(rs.getDate("DataInicio")));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
				obj.add(consultasVO);
			}

		rs.close();
		minhaSTM.close();

		return obj;
	}
	/**
	 * Metodo que localiza a ordem de servico no bd pelo nome.
	 * @param TabConsultaOrdensDAO
	 * @return
	 */
	public List<TabFaturaOrdensVO> readOrdemServicoByNome(TabFaturaOrdensVO consultaVO) throws IllegalAccessException, InstantiationException, SQLException{
		ArrayList<TabFaturaOrdensVO> obj = new ArrayList<TabFaturaOrdensVO>();
		TabFaturaOrdensVO consultasVO = null;
		convData = new ConvertStringToDate();
		minhaConexao = ConnectionFactory.getConnection();
		minhaSTM = minhaConexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
//		String SQL = ("Select O.OrdemPk, C.Nome, C.ValorHora, M.Nome as NomeMotorista, O.DataInicio, O.HoraInicio, O.Status From Ordem O Inner Join Cliente C On C.ClientePk = O.ClienteOFK Inner Join Motorista M On O.MotoristaOFK = M.MotoristaPk WHERE C.Nome LIKE '%"+consultasVO.getNomeCliente()+"%'");
//		String SQL = ("Select O.OrdemPk, C.Nome, O.TotalCliente From Ordem O Inner Join Cliente C On C.ClientePk = O.ClienteOFK WHERE O.Status='F' AND O.OrdemPk ="+faturarVO.getOrdemPk());
		String SQL = ("Select C.Nome, O.TotalCliente, O.OrdemPk, O.DataInicio From Cliente C Inner Join Ordem O On C.ClientePk = O.ClienteOFK WHERE O.Status='F' AND C.Nome ='"+consultaVO.getNomeCliente()+"'");
		rs = minhaSTM.executeQuery(SQL);
			while(rs.next()){
				consultasVO = new TabFaturaOrdensVO();
				consultasVO.setOrdemPk(Integer.valueOf(rs.getString("OrdemPk")));
				consultasVO.setNomeCliente(rs.getString("Nome"));
				consultasVO.setTotalCliente(rs.getString("TotalCliente").replace(".", ","));
				try {
					consultasVO.setDataOS(convData.formatDate(rs.getDate("DataInicio")));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
				obj.add(consultasVO);
			}

		rs.close();
		minhaSTM.close();

		return obj;
	}
	/**
	 * Metodo que localiza o ordem de servico no bd pelo periodo.
	 * @param TabConsultaOrdensDAO
	 * @return
	 */
	public List<TabFaturaOrdensVO> readOrdemServicoByPeriodo(java.sql.Timestamp datInicio, java.sql.Timestamp datTermino) throws IllegalAccessException, InstantiationException, SQLException {
		ArrayList<TabFaturaOrdensVO> obj = new ArrayList<TabFaturaOrdensVO>();
		TabFaturaOrdensVO consultaVO = null;
		convData = new ConvertStringToDate();
		minhaConexao = ConnectionFactory.getConnection();
		minhaSTM = minhaConexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		String SQL = ("Select O.OrdemPk, C.Nome, O.TotalCliente, O.DataInicio From Ordem O Inner Join Cliente C On C.ClientePk = O.ClienteOFK WHERE O.Status='F' AND O.DataInicio BETWEEN '"+datInicio+"'AND'"+datTermino+"'");
		rs = minhaSTM.executeQuery(SQL);
		while(rs.next()){
			consultaVO = new TabFaturaOrdensVO();
			consultaVO.setOrdemPk(Integer.valueOf(rs.getString("OrdemPk")));
			consultaVO.setNomeCliente(rs.getString("Nome"));
			consultaVO.setTotalCliente(rs.getString("TotalCliente").replace(".", ","));
			try {
				consultaVO.setDataOS(convData.formatDate(rs.getDate("DataInicio")));
			} catch (ParseException e) {
				e.printStackTrace();
			}

			obj.add(consultaVO);
		}

		rs.close();
		minhaSTM.close();
		
		return obj;
	}
	/**
	 * Metodo que localiza o ordem de servico no bd pelo periodo e nome do cliente.
	 * @param TabConsultaOrdensDAO
	 * @return
	 */
	public List<TabFaturaOrdensVO> readOrdemServicoByPeriodoAndNome(java.sql.Timestamp datInicio, java.sql.Timestamp datTermino, String nome) throws IllegalAccessException, InstantiationException, SQLException {
		ArrayList<TabFaturaOrdensVO> obj = new ArrayList<TabFaturaOrdensVO>();
		TabFaturaOrdensVO consultaVO = null;
		convData = new ConvertStringToDate();
		minhaConexao = ConnectionFactory.getConnection();
		minhaSTM = minhaConexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		String SQL = ("Select O.OrdemPk, C.Nome, O.TotalCliente, O.DataInicio From Ordem O Inner Join Cliente C On C.ClientePk = O.ClienteOFK WHERE O.Status='F' AND Nome='"+nome+"' AND O.DataInicio BETWEEN '"+datInicio+"'AND'"+datTermino+"'");
		rs = minhaSTM.executeQuery(SQL);
		while(rs.next()){
			consultaVO = new TabFaturaOrdensVO();
			consultaVO.setOrdemPk(Integer.valueOf(rs.getString("OrdemPk")));
			consultaVO.setNomeCliente(rs.getString("Nome"));
			consultaVO.setTotalCliente(rs.getString("TotalCliente").replace(".", ","));
			try {
				consultaVO.setDataOS(convData.formatDate(rs.getDate("DataInicio")));
			} catch (ParseException e) {
				e.printStackTrace();
			}

			obj.add(consultaVO);
		}

		rs.close();
		minhaSTM.close();
		
		return obj;
	}
	/**
	 * Metodo que grava as ordens selecionadas pelo usuario para serem faturadas 
	 */
	public void saveOrdensServicoFaturar(List<TabFaturaOrdensVO> listaFatVO) throws IllegalAccessException, InstantiationException, SQLException{
		for(TabFaturaOrdensVO ftVO : listaFatVO){
			//java.lang.String sql = "INSERT INTO Fatura (FaturaPk, ClientePk, Nome, OrdemFaturadas, ValorHora) VALUES(?, ?, ?, ?,?)";
			java.lang.String sql = "INSERT INTO Fatura (DataFaturamento, NumeroRps, Nome, OrdensFaturadas, ValorHora) VALUES(?,?, ?, ?, ? )";
			minhaConexao = ConnectionFactory.getConnection();
			pst = minhaConexao.prepareStatement(sql);
			
			//pst.setInt(1, ftVO.get);
			pst.setDate(1, (Date) ftVO.getDataFaturamento());
			pst.setInt(2, ftVO.getNumeroRps());
			pst.setString(3, ftVO.getNomeCliente());
			pst.setString(4, ftVO.getOrdensFaturadas());
			pst.setDouble(5, Double.valueOf(ftVO.getTotalCliente()));
			System.out.println(pst.toString());
			pst.execute();
		}
	}
	/**
	 * Metodo que grava o número do RPS nas ordens de servicos selecionadas para faturar
	 */
	public void saveNumeroRpsOS(TabFaturaOrdensVO fatVO) throws IllegalAccessException, InstantiationException, SQLException{
		java.lang.String osString = fatVO.getOrdensFaturadas().replaceAll("-", "").trim();
		char [] numOS = osString.toCharArray();
		int [] oS = new int[numOS.length];
		for(int i=0; i < numOS.length; i++){
			java.lang.String sql = "UPDATE Ordem SET NumeroRPS=? WHERE OrdemPK=?";
			minhaConexao = ConnectionFactory.getConnection();
			pst = minhaConexao.prepareStatement(sql);
		
			pst.setInt(1, fatVO.getNumeroRps());
			java.lang.String nOS = String.valueOf(numOS[i]);
			pst.setInt(2, Integer.valueOf(nOS));
			
			System.out.println(pst.toString());
			pst.execute();
		}
	}
	/**
	 * Metodo que cria o id para a tabela fatura
	 */
	public Integer getNumeroRps() throws IllegalAccessException, InstantiationException, SQLException{
		int nRps = 0;
		java.lang.String sql = "SELECT NumeroRps FROM Fatura ORDER BY NumeroRps DESC LIMIT 1";
		minhaConexao = ConnectionFactory.getConnection();
		pst = minhaConexao.prepareStatement(sql);
		rs = pst.executeQuery();
		while(rs.next()){
			nRps = rs.getInt("NumeroRps");
		}
	
		return nRps;
	}
	/**
	 * Metodo que lista os faturamento sem nota fiscal emitida ou com nota fiscal emitida
	 */
	public List<TabFaturaOrdensVO> listaFaturas() throws IllegalAccessException, InstantiationException, SQLException{
		
		java.lang.String sql = "SELECT FROM Fatura ORDER BY NumeroRps";
		return null;
	}
	/**
	 * Metodo que atualiza a situação da OS para FAT=Faturada
	 */
	public void updateSituacaoOS(TabFaturaOrdensVO tabFaturaOrdensVO) throws IllegalAccessException, InstantiationException, SQLException{
		java.lang.String sql = "UPDATE Ordem SET Status=? WHERE OrdemPk=?";
		minhaConexao = ConnectionFactory.getConnection();
		pst = minhaConexao.prepareStatement(sql);
		
		pst.setString(1, "FAT");
		pst.setInt(2, tabFaturaOrdensVO.getOrdemPk());
		
		System.out.println(pst.toString());
		pst.execute();
	}
	/**
	 * Metodo que busca no nome dos clientes conforme letras digitadas na jcombobox da tela de faturamento
	 */
	public List<TabFaturaOrdensVO> searchClienteByCombo() throws IllegalAccessException, InstantiationException, SQLException {
		List<TabFaturaOrdensVO> tabFaturaOrdensVOs = new ArrayList<TabFaturaOrdensVO>();
		TabFaturaOrdensVO tabOrdensVO;
		minhaConexao = ConnectionFactory.getConnection();
		minhaSTM = minhaConexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		
//		java.lang.String sql = ("SELECT nome FROM cliente WHERE nome LIKE %'"+tabFaturaOrdensVO+"'%");
		java.lang.String sql = ("SELECT nome FROM cliente");
		rs = minhaSTM.executeQuery(sql);
		while(rs.next()){
			tabOrdensVO = new TabFaturaOrdensVO();
			tabOrdensVO.setNomeCliente(rs.getString("Nome"));
			
			tabFaturaOrdensVOs.add(tabOrdensVO);
		}
		return tabFaturaOrdensVOs;
	}
	
	}