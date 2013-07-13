package br.com.cs.controlmoto.domain;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.cs.controlmoto.vo.CadTabelaPrecoVO;

public class CadTabelaPrecoDAO {
	
	private java.sql.PreparedStatement pst;
	private java.sql.Connection con;
	private java.sql.ResultSet rs;
	
	/**
	 * Metodo Contrutor que pega a conecao 
	 */
	public CadTabelaPrecoDAO() throws IllegalAccessException, InstantiationException, SQLException {
		this.con = ConnectionFactory.getConnection();
	}
	/**
	 * Metodo que fecha a conecao
	 */
	public void setClosedConnection() throws IllegalAccessException, InstantiationException, SQLException {
		this.con.close();
	}
	/**
	 * 
	 */
	public CadTabelaPrecoVO getNovoTabPreco() throws IllegalAccessException, InstantiationException, SQLException {
		java.lang.String sql = "SELECT PrecoPk FROM TbPreco ORDER BY PrecoPk DESC LIMIT 1";
		CadTabelaPrecoVO cadTabelaPrecoVO = new CadTabelaPrecoVO();
		pst = con.prepareStatement(sql);
		rs = pst.executeQuery();
		while(rs.next()){
			cadTabelaPrecoVO.setIdTabelaPreco(rs.getInt("PrecoPk")+1);
		}
		if(cadTabelaPrecoVO.equals(null))
			cadTabelaPrecoVO.setIdTabelaPreco(1);
		
		return cadTabelaPrecoVO;		
	}
	/**
	 * 
	 */
	public void salvarTabPreco(CadTabelaPrecoVO cadTabelaPrecoVO) throws IllegalAccessException, InstantiationException, SQLException {
		java.lang.String sql = "INSERT INTO TbPreco (PrecoPk, CodigoTbPreco, DescricaoTbPreco, ItemDescricaoPreco, SequenciaPreco," +
		" ValorPreco, DataCriacao, Situacao ) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
		//UsuarioCriacaoFk,
		pst = con.prepareStatement(sql);
		
		pst.setInt(1, cadTabelaPrecoVO.getIdTabelaPreco());
		pst.setString(2, cadTabelaPrecoVO.getCodigoTabela());
		pst.setString(3, cadTabelaPrecoVO.getDescricaoTabPreco());
		pst.setString(4, cadTabelaPrecoVO.getDescricaoItem());
		pst.setInt(5, cadTabelaPrecoVO.getSequencia());
		pst.setDouble(6, cadTabelaPrecoVO.getPreco());
		//pst.setInt(7, cadTabelaPrecoVO.getUsuarioCriacaoFk());
		pst.setDate(7, cadTabelaPrecoVO.getDataCriacao());
		pst.setString(8, cadTabelaPrecoVO.getSituacao());

		pst.execute();		

	}
	/**
	 * 
	 */
	public List<CadTabelaPrecoVO> localizarAllTabPreco(CadTabelaPrecoVO cadTabelaPrecoVO) throws IllegalAccessException, InstantiationException, SQLException {
		
		List<CadTabelaPrecoVO> cadTabelaPrecoVOs = new ArrayList<>();
		CadTabelaPrecoVO cadTabelaPrecoVO2 = null;
		
		java.lang.String sql = "SELECT * FROM TbPreco WHERE CodigoTbPreco=?";
		pst.setString(1, cadTabelaPrecoVO.getCodigoTabela());
		
		pst = con.prepareStatement(sql);
		rs = pst.executeQuery();
		while(rs.next()){
			cadTabelaPrecoVO2 = new CadTabelaPrecoVO();
			
			cadTabelaPrecoVO2.setIdTabelaPreco(rs.getInt("PrecoPk"));
			cadTabelaPrecoVO2.setCodigoTabela(rs.getString("CodigoTbPreco"));
			cadTabelaPrecoVO2.setDescricaoTabPreco(rs.getString("DescricaoTbPreco"));
			cadTabelaPrecoVO2.setDescricaoItem(rs.getString("ItemDescricaoPreco"));
			cadTabelaPrecoVO2.setSequencia(rs.getInt("SequenciaPreco"));
			cadTabelaPrecoVO2.setPreco(rs.getDouble("ValorPreco"));
			cadTabelaPrecoVO2.setUsuarioCriacaoFk(rs.getInt("UsuarioAlteracaoFk"));
			cadTabelaPrecoVO2.setDataCriacao(rs.getDate("DataCriacao"));
			cadTabelaPrecoVO2.setUsuarioAlteracaoFk(rs.getInt("UsuarioAlteracaoFk"));
			cadTabelaPrecoVO2.setDataAlteracao(rs.getDate("DataAlteracao"));
			//cadTabelaPrecoVO2.setSituacao(rs.getString("Situacao"));
		
			cadTabelaPrecoVOs.add(cadTabelaPrecoVO2);
		}
		
		return cadTabelaPrecoVOs;		
	}
	/**
	 * 
	 */
	public List<CadTabelaPrecoVO> localizarAllTabPrecoByCombo() throws IllegalAccessException, InstantiationException, SQLException {
		
		List<CadTabelaPrecoVO> cadTabelaPrecoVOs = new ArrayList<>();
		CadTabelaPrecoVO cadTabelaPrecoVO2 = null;
		
		java.lang.String sql = "SELECT * FROM TbPreco  Group By CodigoTbPreco";
		//pst.setString(1, cadTabelaPrecoVO.getCodigoTabela());
		//pst.setString(2, cadTabelaPrecoVO.getCodigoTabela());
		
		pst = con.prepareStatement(sql);
		rs = pst.executeQuery();
		while(rs.next()){
			cadTabelaPrecoVO2 = new CadTabelaPrecoVO();
			
			cadTabelaPrecoVO2.setIdTabelaPreco(rs.getInt("PrecoPk"));
			cadTabelaPrecoVO2.setCodigoTabela(rs.getString("CodigoTbPreco"));
			cadTabelaPrecoVO2.setDescricaoTabPreco(rs.getString("DescricaoTbPreco"));
			cadTabelaPrecoVO2.setDescricaoItem(rs.getString("ItemDescricaoPreco"));
			cadTabelaPrecoVO2.setSequencia(rs.getInt("SequenciaPreco"));
			cadTabelaPrecoVO2.setPreco(rs.getDouble("ValorPreco"));
			cadTabelaPrecoVO2.setUsuarioCriacaoFk(rs.getInt("UsuarioAlteracaoFk"));
			cadTabelaPrecoVO2.setDataCriacao(rs.getDate("DataCriacao"));
			cadTabelaPrecoVO2.setUsuarioAlteracaoFk(rs.getInt("UsuarioAlteracaoFk"));
			cadTabelaPrecoVO2.setDataAlteracao(rs.getDate("DataAlteracao"));
			//cadTabelaPrecoVO2.setSituacao(rs.getString("Situacao"));
		
			cadTabelaPrecoVOs.add(cadTabelaPrecoVO2);
		}
		
		return cadTabelaPrecoVOs;		
	}	
	/**
	 * 
	 */
	public CadTabelaPrecoVO localizarTabPrecoByCodigoAndSequencia(CadTabelaPrecoVO cadTabelaPrecoVO) throws IllegalAccessException, InstantiationException, SQLException {
		
		CadTabelaPrecoVO cadTabelaPrecoVO2 = null;
		
		java.lang.String sql = "SELECT * FROM TbPreco WHERE CodigoTbPreco=? AND SequenciaPreco=?";
		pst = con.prepareStatement(sql);
		
		pst.setString(1, cadTabelaPrecoVO.getCodigoTabela());
		pst.setInt(2, cadTabelaPrecoVO.getSequencia());
		
		rs = pst.executeQuery();
		while(rs.next()){
			cadTabelaPrecoVO2 = new CadTabelaPrecoVO();
			
			cadTabelaPrecoVO2.setIdTabelaPreco(rs.getInt("PrecoPk"));
			cadTabelaPrecoVO2.setCodigoTabela(rs.getString("CodigoTbPreco"));
			cadTabelaPrecoVO2.setDescricaoTabPreco(rs.getString("DescricaoTbPreco"));
			cadTabelaPrecoVO2.setDescricaoItem(rs.getString("ItemDescricaoPreco"));
			cadTabelaPrecoVO2.setSequencia(rs.getInt("SequenciaPreco"));
			cadTabelaPrecoVO2.setPreco(rs.getDouble("ValorPreco"));
			cadTabelaPrecoVO2.setUsuarioCriacaoFk(rs.getInt("UsuarioAlteracaoFk"));
			cadTabelaPrecoVO2.setDataCriacao(rs.getDate("DataCriacao"));
			cadTabelaPrecoVO2.setUsuarioAlteracaoFk(rs.getInt("UsuarioAlteracaoFk"));
			cadTabelaPrecoVO2.setDataAlteracao(rs.getDate("DataAlteracao"));
			//cadTabelaPrecoVO2.setSituacao(rs.getString("Situacao"));		
		}
		
		return cadTabelaPrecoVO2;
	}
	/**
	 * 
	 */
	public List<CadTabelaPrecoVO> localizarTabPrecoByCodigo(CadTabelaPrecoVO cadTabelaPrecoVO) throws IllegalAccessException, InstantiationException, SQLException {
		
		CadTabelaPrecoVO cadTabelaPrecoVO2 = null; List<CadTabelaPrecoVO> cadTabelaPrecoVOs = new ArrayList<>(); 
		
		java.lang.String sql = "SELECT * FROM TbPreco WHERE CodigoTbPreco = ? ";
		pst = con.prepareStatement(sql);
		pst.setString(1, cadTabelaPrecoVO.getCodigoTabela().toString());
		
		rs = pst.executeQuery();
		while(rs.next()){
			cadTabelaPrecoVO2 = new CadTabelaPrecoVO();
			
			cadTabelaPrecoVO2.setIdTabelaPreco(rs.getInt("PrecoPk"));
			cadTabelaPrecoVO2.setCodigoTabela(rs.getString("CodigoTbPreco"));
			cadTabelaPrecoVO2.setDescricaoTabPreco(rs.getString("DescricaoTbPreco"));
			cadTabelaPrecoVO2.setDescricaoItem(rs.getString("ItemDescricaoPreco"));
			cadTabelaPrecoVO2.setSequencia(rs.getInt("SequenciaPreco"));
			cadTabelaPrecoVO2.setPreco(rs.getDouble("ValorPreco"));
			cadTabelaPrecoVO2.setUsuarioCriacaoFk(rs.getInt("UsuarioAlteracaoFk"));
			cadTabelaPrecoVO2.setDataCriacao(rs.getDate("DataCriacao"));
			cadTabelaPrecoVO2.setUsuarioAlteracaoFk(rs.getInt("UsuarioAlteracaoFk"));
			cadTabelaPrecoVO2.setDataAlteracao(rs.getDate("DataAlteracao"));
			//cadTabelaPrecoVO2.setSituacao(rs.getString("Situacao"));
			
			cadTabelaPrecoVOs.add(cadTabelaPrecoVO2);
		}
		
		return cadTabelaPrecoVOs;
	}	
	/**
	 * 
	 */
	public void atualizarTabPrecoByCodigoAndSequencia(CadTabelaPrecoVO cadTabelaPrecoVO) throws IllegalAccessException, InstantiationException, SQLException {
		
		java.lang.String sql = "UPDATE TbPreco SET CodigoTbPreco=?, DescricaoTbPreco=?, ItemDescricaoPreco=?," +
		" SequenciaPreco=?, ValorPreco=?, DataCriacao=? WHERE PrecoPk=?";
				
		pst = con.prepareStatement(sql);
		
		pst.setString(1, cadTabelaPrecoVO.getCodigoTabela());
		pst.setString(2, cadTabelaPrecoVO.getDescricaoTabPreco());
		pst.setString(3, cadTabelaPrecoVO.getDescricaoItem());
		pst.setInt(4, cadTabelaPrecoVO.getSequencia());
		pst.setDouble(5, cadTabelaPrecoVO.getPreco());
		pst.setDate(6, cadTabelaPrecoVO.getDataCriacao());
		//pst.setInt(6, cadTabelaPrecoVO.getUsuarioAlteracaoFk());
		//pst.setDate(7, cadTabelaPrecoVO.getDataAlteracao());
		pst.setInt(7, cadTabelaPrecoVO.getIdTabelaPreco());

		pst.execute();
		
	}	
	/**
	 * 
	 */
	public void cancelarTabPrecoByCodigoAndSequencia(CadTabelaPrecoVO cadTabelaPrecoVO) throws IllegalAccessException, InstantiationException, SQLException {
		
		java.lang.String sql = "UPDATE TbPreco SET UsuarioAlteracaoFk=?, DataAlteração=?, Situacao=? WHERE PrecoPk=?";
				
		pst = con.prepareStatement(sql);
		
		pst.setInt(1, cadTabelaPrecoVO.getUsuarioAlteracaoFk());
		pst.setDate(2, cadTabelaPrecoVO.getDataAlteracao());
		pst.setString(3, cadTabelaPrecoVO.getSituacao());
		pst.setInt(4, cadTabelaPrecoVO.getIdTabelaPreco());

		pst.execute();
		
	}
	/**
	 * 
	 */
	public void excluirTabPrecoByCodigoAndSequencia(CadTabelaPrecoVO cadTabelaPrecoVO) throws IllegalAccessException, InstantiationException, SQLException {
		
	}
	/**
	 * 
	 */
	public CadTabelaPrecoVO getTabPrecoByCliente(CadTabelaPrecoVO cadTabelaPrecoVO) throws IllegalAccessException, InstantiationError, SQLException {
		
		java.lang.String sql = "SELECT CodigoTbPreco, DescricaoTbPreco, Situacao FROM TbPreco WHERE CodigoTbPreco=? GROUP BY CodigoTbPreco";
		
		pst = con.prepareStatement(sql);
		pst.setString(1, cadTabelaPrecoVO.getCodigoTabela() );
		
		rs = pst.executeQuery();
		while( rs.next() ){
			cadTabelaPrecoVO.setCodigoTabela( rs.getString("CodigoTbPreco") );
			cadTabelaPrecoVO.setDescricaoTabPreco( rs.getString("DescricaoTbPreco") );
			cadTabelaPrecoVO.setSituacao( rs.getString("Situacao") );
		}
		
		return cadTabelaPrecoVO;
	}
	
}
