package br.com.cs.controlmoto.domain;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.cs.controlmoto.utils.ConvertStringToDate;
import br.com.cs.controlmoto.utils.FormataValor;
import br.com.cs.controlmoto.vo.TabConsultaOrdensVO;
import br.com.cs.controlmoto.vo.TabFaturaOrdensVO;
import br.com.cs.controlmoto.vo.TabGerarRpsFaturaVO;

public class TabGerarRpsFaturaDAO {
	/**
	 * Variaveis para conexao ao banco de dados
	 */
	static Statement minhaSTM;
    static ResultSet rs;
    static Connection minhaConexao;
    static java.sql.PreparedStatement pst;
    /**
	 * Variaveis para conversao de datas
	 */
    SimpleDateFormat sdf;
	/**
	 * Metodo que localiza a ordem de servico no bd pelo Id.
	 * @param TabConsultaOrdensDAO
	 * @return
	 */
	public TabConsultaOrdensVO readOrdemServicoByPk(TabConsultaOrdensVO consultaVO) throws IllegalAccessException, InstantiationException, SQLException{
		minhaConexao = ConnectionFactory.getConnection();
		minhaSTM = minhaConexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		String SQL = ("Select O.OrdemPk, C.Nome, C.ValorHora, M.Nome as NomeMotorista, O.DataInicio, O.HoraInicio, O.Status From Ordem O Inner Join Cliente C On C.ClientePk = O.ClienteOFK Inner Join Motorista M On O.MotoristaOFK = M.MotoristaPk WHERE O.OrdemPk ='"+consultaVO.getOrdemPk()+"'");
		rs = minhaSTM.executeQuery(SQL);
			while(rs.next()){
				consultaVO.setOrdemPk(Integer.valueOf(rs.getString("OrdemPk")));
				consultaVO.setNomeCliente(rs.getString("Nome"));
				consultaVO.setValorHora(rs.getString("ValorHora"));
				consultaVO.setNomeMotorista(rs.getString("NomeMotorista"));
				consultaVO.setDataInicios(rs.getTimestamp("DataInicio"));
				consultaVO.setHoraInicio(rs.getString("HoraInicio"));
				consultaVO.setStatus(rs.getString("Status"));
				consultaVO.setQtde(rs.getRow());
			}
//			}catch(SQLException ex) {
//				JOptionPane.showMessageDialog(null,"Ordem de servico nÃ£o localizada.\nPara cadastrar clique em incluir.","Moto Express",JOptionPane.INFORMATION_MESSAGE);
//				}
		rs.close();
		minhaSTM.close();
		return consultaVO;
	}
	/**
	 * Metodo que localiza a ordem de servico no bd pelo perido.
	 * @param TabConsultaOrdensDAO
	 * @return
	 */
	public List<TabGerarRpsFaturaVO> readRpsByPeriodo(TabGerarRpsFaturaVO geraRpsVO) throws IllegalAccessException, InstantiationException, SQLException, ParseException {
//		java.sql.Timestamp datInicio, java.sql.Timestamp datTermino
		ArrayList<TabGerarRpsFaturaVO> obj = new ArrayList<TabGerarRpsFaturaVO>();
		TabGerarRpsFaturaVO geraRps1VO = null; ConvertStringToDate convStringData = new ConvertStringToDate();
		FormataValor fv = new FormataValor();

		minhaConexao = ConnectionFactory.getConnection();
		minhaSTM = minhaConexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		String sql = ("Select F.FaturaPk, F.DataFaturamento, F.NumeroRps, F.Nome, F.OrdensFaturadas, F.ValorHora, C.Cnpj, C.IE, C.Email, C.Numero, C.Ccm, E.Endereco, E.Bairro," +
		" E.Cidade, E.Estado, E.Cep From Fatura F JOIN Cliente C ON F.Nome = C.Nome JOIN Endereco E ON E.EnderecoPK = C.EnderecoCFk WHERE F.NumeroNotaFiscal is Null AND" +
		" F.DataFaturamento BETWEEN '"+convStringData.formatDateAmerinaca(geraRpsVO.getDataInicioDate())+"' AND '"+convStringData.formatDateAmerinaca(geraRpsVO.getDataFinalDate())+"'");
		rs = minhaSTM.executeQuery(sql);
			while(rs.next()){				
				geraRps1VO = new TabGerarRpsFaturaVO();
				geraRps1VO.setFaturar(false);
				geraRps1VO.setFaturaPk(Integer.valueOf(rs.getString("FaturaPk")));
				geraRps1VO.setDataFaturamentoStr(convStringData.convertStringToData(""+rs.getTimestamp("DataFaturamento")));
				geraRps1VO.setNumeroRps(rs.getInt("NumeroRps"));
				geraRps1VO.setNome(rs.getString("Nome"));
				geraRps1VO.setOrdensFaturadas(rs.getString("OrdensFaturadas"));
				geraRps1VO.setValorStr(fv.formatValor(""+rs.getDouble("ValorHora")));
				geraRps1VO.setCnpj(rs.getString("Cnpj"));
				geraRps1VO.setIe(rs.getString("IE"));
				geraRps1VO.setEmail(rs.getString("Email"));
				geraRps1VO.setNumero(rs.getString("Numero"));
				geraRps1VO.setCcmTomador(rs.getString("Ccm"));
				geraRps1VO.setEndereco(rs.getString("Endereco"));
				geraRps1VO.setBairro(rs.getString("Bairro"));
				geraRps1VO.setCidade(rs.getString("Cidade"));
				geraRps1VO.setEstado(rs.getString("Estado"));
				geraRps1VO.setCep(rs.getString("Cep"));

				obj.add(geraRps1VO);
			}
		rs.close();
		minhaSTM.close();
		
		return (List<TabGerarRpsFaturaVO>) obj;
	}
	/**
	 * Metodo que localiza o ordem de servico no bd pelo periodo.
	 * @param TabConsultaOrdensDAO
	 * @return
	 * @throws ParseException 
	 */
	public List<TabGerarRpsFaturaVO> readRpsByPeriodoAndNome(TabGerarRpsFaturaVO geraRpsVO) throws IllegalAccessException, InstantiationException, SQLException, ParseException {
//		java.sql.Timestamp datInicio, java.sql.Timestamp datTermino
		ArrayList<TabGerarRpsFaturaVO> obj = new ArrayList<TabGerarRpsFaturaVO>();
		TabGerarRpsFaturaVO geraRps1VO = null; ConvertStringToDate convStringData = new ConvertStringToDate();
		FormataValor fv = new FormataValor();

		minhaConexao = ConnectionFactory.getConnection();
		minhaSTM = minhaConexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		String sql = ("Select F.FaturaPk, F.DataFaturamento, F.NumeroRps, F.Nome, F.OrdensFaturadas, F.ValorHora, C.Cnpj, C.IE, C.Email, C.Numero, C.Ccm, E.Endereco, E.Bairro," +
		" E.Cidade, E.Estado, E.Cep From Fatura F JOIN Cliente C ON F.Nome = C.Nome JOIN Endereco E ON E.EnderecoPK = C.EnderecoCFk WHERE F.NumeroNotaFiscal is Null AND" +
		" F.Nome='"+geraRpsVO.getNome()+"' AND F.DataFaturamento BETWEEN '"+convStringData.formatDateAmerinaca(geraRpsVO.getDataInicioDate())+"' AND '"+convStringData.formatDateAmerinaca(geraRpsVO.getDataFinalDate())+"'");
		rs = minhaSTM.executeQuery(sql);
			while(rs.next()){				
				geraRps1VO = new TabGerarRpsFaturaVO();
				geraRps1VO.setFaturar(false);
				geraRps1VO.setFaturaPk(Integer.valueOf(rs.getString("FaturaPk")));
				geraRps1VO.setDataFaturamentoStr(convStringData.convertStringToData(""+rs.getTimestamp("DataFaturamento")));
				geraRps1VO.setNumeroRps(rs.getInt("NumeroRps"));
				geraRps1VO.setNome(rs.getString("Nome"));
				geraRps1VO.setOrdensFaturadas(rs.getString("OrdensFaturadas"));
				geraRps1VO.setValorStr(fv.formatValor(""+rs.getDouble("ValorHora")));
				geraRps1VO.setCnpj(rs.getString("Cnpj"));
				geraRps1VO.setIe(rs.getString("IE"));
				geraRps1VO.setEmail(rs.getString("Email"));
				geraRps1VO.setNumero(rs.getString("Numero"));
				geraRps1VO.setCcmTomador(rs.getString("Ccm"));
				geraRps1VO.setEndereco(rs.getString("Endereco"));
				geraRps1VO.setBairro(rs.getString("Bairro"));
				geraRps1VO.setCidade(rs.getString("Cidade"));
				geraRps1VO.setEstado(rs.getString("Estado"));
				geraRps1VO.setCep(rs.getString("Cep"));

				obj.add(geraRps1VO);
			}
		rs.close();
		minhaSTM.close();
		
		return (List<TabGerarRpsFaturaVO>) obj;
	}
	/**
	 * Metodo que grava as ordens selecionadas pelo usuario para serem faturadas 
	 */
	public void saveOrdensServicoFaturar(List<TabFaturaOrdensVO> listaFatVO) throws IllegalAccessException, InstantiationException, SQLException{
		for(TabFaturaOrdensVO ftVO : listaFatVO){
			//java.lang.String sql = "INSERT INTO Fatura (FaturaPk, ClientePk, Nome, OrdemFaturadas, ValorHora) VALUES(?, ?, ?, ?,?)";
			java.lang.String sql = "INSERT INTO Fatura (NumeroRps, Nome, OrdensFaturadas, ValorHora) VALUES(?, ?, ?, ? )";
			minhaConexao = ConnectionFactory.getConnection();
			pst = minhaConexao.prepareStatement(sql);
			
			//pst.setInt(1, ftVO.get);
			pst.setInt(1, ftVO.getNumeroRps());			
			pst.setString(2, ftVO.getNomeCliente());
			pst.setString(3, ftVO.getOrdensFaturadas());
			pst.setDouble(4, Double.valueOf(ftVO.getTotalCliente()));
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
	public List<TabFaturaOrdensVO> listaFaturas() throws IllegalAccessException, InstantiationException, SQLException {
		
		java.lang.String sql = "SELECT FROM Fatura ORDER BY NumeroRps";
		return null;
	}
	/**
	 * Metodo que carrega o combobox de nomes de clientes
	 */
	public List<TabGerarRpsFaturaVO> carregaComboNome() throws IllegalAccessException, InstantiationException, SQLException {
		ArrayList<TabGerarRpsFaturaVO> listaGeraRpsVO = null; TabGerarRpsFaturaVO geraRpsVO = new TabGerarRpsFaturaVO();
		java.lang.String sql = "SELECT Nome FROM Fatura GROUP BY Nome ORDER BY Nome";
		
		minhaConexao = ConnectionFactory.getConnection();
		pst = minhaConexao.prepareStatement(sql);
		
		rs = pst.executeQuery();
		while(rs.next()){
			listaGeraRpsVO = new ArrayList<TabGerarRpsFaturaVO>();
			
			geraRpsVO.setNome(rs.getString("Nome"));
			
			listaGeraRpsVO.add(geraRpsVO);
		}
		return listaGeraRpsVO;
	}

	}