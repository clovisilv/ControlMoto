package br.com.cs.controlmoto.domain;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.cs.controlmoto.utils.ConvertStringToDate;
import br.com.cs.controlmoto.vo.CadVlCombustivelVO;

public class CadVlCombustivelDAO {
	/**
	 * Variaveis parea conecao ao banco de dados
	 */
	private java.sql.PreparedStatement pst;
	private java.sql.ResultSet rs;
	private java.sql.Connection minhaConexao;
    /**
	 * Metodo que realiza a acao do botao incluir adinatamento no bd.
	 * @param CadVlCombustivelDAO
	 * @return
	 */
	public void addAdiantementoDAO(CadVlCombustivelVO cadVlCombustivelVO) throws IllegalAccessException, InstantiationException, SQLException{
		minhaConexao = ConnectionFactory.getConnection();
		
		String sql = ("SELECT * FROM Adiantamento");
		pst = minhaConexao.prepareStatement(sql);
		
		rs = pst.executeQuery(sql);
		if(rs.last() == true){
			rs.last();
			cadVlCombustivelVO.setAdiantamentoPk(Integer.parseInt(rs.getString("adiantamentoPk"))+1);
		}else{
			cadVlCombustivelVO.setAdiantamentoPk(1);
		}
		
		rs.close();
		pst.close();
	}
	/**
	 * Metodo que grava o adiantamento no bd.
	 * @param CadVlCombustivelDAO
	 * @return
	 * @throws ParseException 
	 */
	public void saveAdinatamentoDAO(CadVlCombustivelVO cadVlCombustivelVO) throws IllegalAccessException, InstantiationException, SQLException, ParseException{
		minhaConexao = ConnectionFactory.getConnection();
		
		String sql = "INSERT INTO Adiantamento(AdiantamentoPK, CodSolicitante, NomeSolicitante, Valor, TipoAdiantamento," +
		" DataSolicitacao, StatusSolicitacao) Values (?, ?, ?, ?, ?, ?, ?)";
		
		pst = minhaConexao.prepareStatement(sql);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		pst.setInt(1, cadVlCombustivelVO.getAdiantamentoPk());
		pst.setString(2, cadVlCombustivelVO.getCodSolicitante());
		pst.setString(3, cadVlCombustivelVO.getNomeSolicitante());
		pst.setDouble(4, cadVlCombustivelVO.getValor());
		pst.setString(5, cadVlCombustivelVO.getTipoAdiantamento());
		pst.setDate(6, new java.sql.Date(sdf.parse(cadVlCombustivelVO.getDataSolicitacao()).getTime()));
		pst.setString(7, cadVlCombustivelVO.getStatusSolicitacao());
		
		System.out.println(pst.toString());
 
		pst.execute();
		
		pst.close();
	}
	/**
	 * Metodo que localiza o adinatamento no bd.
	 * @param adiantamentoDAO
	 * @return
	 */
	public CadVlCombustivelVO readAdiantamentoByPk(CadVlCombustivelVO cadVlCombustivelVO) throws IllegalAccessException, InstantiationException, SQLException{
		minhaConexao = ConnectionFactory.getConnection();
		
		String sql = ("SELECT * FROM Adiantamento Where AdiantamentoPK = ?");
		
		pst = minhaConexao.prepareStatement(sql);
		pst.setInt(1, cadVlCombustivelVO.getAdiantamentoPk());
		rs = pst.executeQuery();
		
		while(rs.next()){
			cadVlCombustivelVO.setAdiantamentoPk(Integer.parseInt((rs.getString("AdiantamentoPk"))));
			cadVlCombustivelVO.setCodSolicitante(rs.getString("CodSolicitante"));
			cadVlCombustivelVO.setNomeSolicitante(rs.getString("NomeSolicitante"));
			cadVlCombustivelVO.setValor(rs.getDouble("Valor"));
			cadVlCombustivelVO.setTipoAdiantamento(rs.getString("tipoAdiantamento"));
			cadVlCombustivelVO.setDataSolicitacao(String.valueOf( new SimpleDateFormat("dd/MM/yyyy").format(rs.getTimestamp("DataSolicitacao")) ));
			cadVlCombustivelVO.setStatusSolicitacao(rs.getString("StatusSolicitacao"));
		}
		
		pst.close();
		
		return cadVlCombustivelVO;
	}
	/**
	 * Metodo que altera o adinatamento no bd.
	 * @param adinatamentoDAO
	 * @return
	 * @throws ParseException 
	 */	
	public void alterAdinatamentoByPk(CadVlCombustivelVO cadVlCombustivelVO) throws IllegalAccessException, InstantiationException, SQLException, ParseException{
		minhaConexao = ConnectionFactory.getConnection();
		
		String sql = "UPDATE Adiantamento SET AdiantamentoPK=? , CodSolicitante=?, NomeSolicitante=?, Valor=?, TipoAdiantamento=?," +
		" DataSolicitacao=?, StatusSolicitacao=? WHERE AdiantamentoPK=?";
		
		pst = minhaConexao.prepareStatement(sql);
		pst.setInt(1, cadVlCombustivelVO.getAdiantamentoPk());		
		pst.setString(2, cadVlCombustivelVO.getCodSolicitante());
		pst.setString(3, cadVlCombustivelVO.getNomeSolicitante());
		pst.setDouble(4, cadVlCombustivelVO.getValor());
		pst.setString(5, cadVlCombustivelVO.getTipoAdiantamento());
		pst.setDate(6, new ConvertStringToDate().convertStringToDates(cadVlCombustivelVO.getDataSolicitacao()));
		pst.setString(7, cadVlCombustivelVO.getStatusSolicitacao());
		pst.setInt(8, cadVlCombustivelVO.getAdiantamentoPk());
		
		int res = pst.executeUpdate();
		if(res == 1){
			JOptionPane.showMessageDialog(null,"Atualização realizada com sucesso!");
		}else{
			JOptionPane.showMessageDialog(null,"Adiantamento não cadastrado.\nPara cadastrar pressione incluir.");
		}
		
		pst.close();
	}
	/**
	 * Metodo que apaga o adiantamento no bd.
	 * @param adiantamentoDAO
	 * @return
	 */
	public void deleteAdiantamentoByPk(CadVlCombustivelVO cadVlCombustivelVO) throws IllegalAccessException, InstantiationException, SQLException{
		minhaConexao = ConnectionFactory.getConnection();
		
		String sql = "UPDATE Adiantamento SET StatusSolicitacao=?, ObsSolicitacao=? WHERE AdiantamentoPK=?";
				
		pst = minhaConexao.prepareStatement(sql);
		pst.setString(1, cadVlCombustivelVO.getStatusSolicitacao());
		pst.setString(2, cadVlCombustivelVO.getObsSolicitacao());
		pst.setInt(3, cadVlCombustivelVO.getAdiantamentoPk());
		
		int res = pst.executeUpdate();
		if(res == 1)
			JOptionPane.showMessageDialog(null,"Exclusão realizada com sucesso!");
		
		pst.close();

	}
	/**
	 * Metodo que localiza o adinatamento no bd.
	 * @param adiantamentoDAO
	 * @return
	 */
	public List<CadVlCombustivelVO> readAdiantamentoByPeriodo(CadVlCombustivelVO cadVlCombustivelVO) throws IllegalAccessException, InstantiationException, SQLException{
		minhaConexao = ConnectionFactory.getConnection();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		List<CadVlCombustivelVO> cadVlCombustivelVOList = new ArrayList<CadVlCombustivelVO>();
		CadVlCombustivelVO cadVlCombustivelVOs = null;
		
		String sql = ("SELECT * FROM Adiantamento WHERE DataSolicitacao BETWEEN ? AND ?");
		
		pst = minhaConexao.prepareStatement(sql);
		try {
			pst.setDate(1, new java.sql.Date( sdf.parse(cadVlCombustivelVO.getDataInicio()).getTime()));
			pst.setDate(2, new java.sql.Date( sdf.parse(cadVlCombustivelVO.getDataTermino()).getTime()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
				
		rs = pst.executeQuery();
		
		while(rs.next()){
			cadVlCombustivelVOs = new CadVlCombustivelVO();
			cadVlCombustivelVOs.setAdiantamentoPk(Integer.parseInt((rs.getString("AdiantamentoPk"))));
			cadVlCombustivelVOs.setCodSolicitante(rs.getString("CodSolicitante"));
			cadVlCombustivelVOs.setNomeSolicitante(rs.getString("NomeSolicitante"));
			cadVlCombustivelVOs.setValor(rs.getDouble("Valor"));
			cadVlCombustivelVOs.setTipoAdiantamento(rs.getString("TipoAdiantamento"));
			cadVlCombustivelVOs.setDtSolicitacao(rs.getTimestamp("DataSolicitacao"));
			cadVlCombustivelVOs.setStatusSolicitacao(rs.getString("StatusSolicitacao"));
			
			cadVlCombustivelVOList.add(cadVlCombustivelVOs);
		}
		
		pst.close();
		
		return cadVlCombustivelVOList;
	}
	/**
	 * Metodo que localiza usuarios e motoristas para carregar a combo opcao.
	 * @param adiantamentoDAO
	 * @return
	 */
	public List<CadVlCombustivelVO> readSolicitanteAllAdiantamento() throws IllegalAccessException, InstantiationException, SQLException{
		minhaConexao = ConnectionFactory.getConnection();
		List<CadVlCombustivelVO> cadVlCombustivelVOList = new ArrayList<CadVlCombustivelVO>();
		CadVlCombustivelVO cadVlCombustivelVOs = null;
		
		String sql = ("SELECT UsuarioPk, NomeUsuario FROM Usuario UNION SELECT MotoristaPk, Nome FROM Motorista");
		
		pst = minhaConexao.prepareStatement(sql);
						
		rs = pst.executeQuery();
		
		while(rs.next()){
			cadVlCombustivelVOs = new CadVlCombustivelVO();
			cadVlCombustivelVOs.setOpcaoId(rs.getString("UsuarioPk"));
			cadVlCombustivelVOs.setOpcaoNome(rs.getString("NomeUsuario"));
			
			cadVlCombustivelVOList.add(cadVlCombustivelVOs);
		}
		
		pst.close();
		
		return cadVlCombustivelVOList;
	}
	/**
	 * Metodo que altera o adinatamento no bd para atualizar o status.
	 * @param adinatamentoDAO
	 * @return
	 * @throws ParseException 
	 */	
	public void alterStatusAdinatamentoByPk(CadVlCombustivelVO cadVlCombustivelVO) throws IllegalAccessException, InstantiationException, SQLException {
		minhaConexao = ConnectionFactory.getConnection();
		
		String sql = "UPDATE Adiantamento SET StatusSolicitacao=? WHERE AdiantamentoPK=?";
		
		pst = minhaConexao.prepareStatement(sql);
		pst.setString(1, cadVlCombustivelVO.getStatusSolicitacao());
		pst.setInt(2, cadVlCombustivelVO.getAdiantamentoPk());
		
		pst.executeUpdate();		
//		int res = pst.executeUpdate();
//		if(res == 1){
//			JOptionPane.showMessageDialog(null,"Atualização realizada com sucesso!");
//		}else{
//			JOptionPane.showMessageDialog(null,"Adiantamento não cadastrado.\nPara cadastrar pressione incluir.");
//		}
		
		pst.close();
	}
	

	}