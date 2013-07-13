package br.com.cs.controlmoto.domain;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.cs.controlmoto.vo.TabConsultaVO;

public class TabConsultaDAO {
	/**
	 * Variaveis para conecao ao banco de dados
	 */
	static Statement minhaSTM;
    static ResultSet rs;
    static Connection minhaConexao;
    
    SimpleDateFormat sdf;
    
//TRECHO DE CODIGO PARA REALIZAR A BUSCA DAS INFOMACOES DO MOTORISTA
	/**
	 * Metodo que localiza o cliente, endereco e telefone no bd pelo Id.
	 * @param usuariosDAO
	 * @return
	 */
	public TabConsultaVO readDadosClienteByPk(TabConsultaVO consultaVO) throws IllegalAccessException, InstantiationException, SQLException{
		minhaConexao = ConnectionFactory.getConnection();
		minhaSTM = minhaConexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		String SQL = ("Select C.ClientePk, C.Nome, E.Endereco, C.Numero, E.Cidade, E.Estado, T.TelefoneA, T.CelularA From Cliente C Inner Join Endereco E On E.EnderecoPk = C.EnderecoCFK Inner Join Telefone T On T.TelefonePk = C.TelefoneCFk WHERE C.ClientePk ="+ consultaVO.getClientePk());
		rs = minhaSTM.executeQuery(SQL);
			while(rs.next()){
				consultaVO.setClientePk(Integer.valueOf(rs.getString("FornecedorPk")));
				consultaVO.setNome(rs.getString("Nome"));
				consultaVO.setEndereco(rs.getString("Endereco") +" ,"+ rs.getString("Numero"));
				consultaVO.setCidade(rs.getString("Cidade"));
				consultaVO.setEstado(rs.getString("Estado"));
				consultaVO.setTelefoneA(rs.getString("TelefoneA"));
				consultaVO.setCelularA(rs.getString("CelularA"));
				consultaVO.setQtde(rs.getRow());
			}
//			}catch(SQLException ex) {
//				JOptionPane.showMessageDialog(null,"Cliente não localizada.\nPara cadastrar clique em incluir.","Moto Express",JOptionPane.INFORMATION_MESSAGE);
//				}
		rs.close();
		minhaSTM.close();
	
		return consultaVO;
	}
	/**
	 * Metodo que localiza o cliente, endereco e telefone no bd pelo nome.
	 * @param usuariosDAO
	 * @return
	 */
	public List<TabConsultaVO> readDadosClienteByNome(TabConsultaVO consultasVO) throws IllegalAccessException, InstantiationException, SQLException{
		ArrayList<TabConsultaVO> obj = new ArrayList<TabConsultaVO>();
		TabConsultaVO consultaVO = null;

		minhaConexao = ConnectionFactory.getConnection();
		minhaSTM = minhaConexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		String SQL = ("Select C.ClientePk, C.Nome, E.Endereco, C.Numero, E.Cidade, E.Estado, T.TelefoneA, T.CelularA From Cliente C Inner Join Endereco E On E.EnderecoPk = C.EnderecoCFK Inner Join Telefone T On T.TelefonePk = C.TelefoneCFk WHERE C.Nome LIKE'%"+consultasVO.getNome()+"%'");
		rs = minhaSTM.executeQuery(SQL);
			while(rs.next()){
				consultaVO = new TabConsultaVO();
				consultaVO.setClientePk(Integer.valueOf(rs.getString("ClientePk")));
				consultaVO.setNome(rs.getString("Nome"));
				consultaVO.setEndereco(rs.getString("Endereco") +" ,"+ rs.getString("Numero"));
				consultaVO.setCidade(rs.getString("Cidade"));
				consultaVO.setEstado(rs.getString("Estado"));
				consultaVO.setTelefoneA(rs.getString("TelefoneA"));
				consultaVO.setCelularA(rs.getString("CelularA"));
				consultaVO.setQtde(rs.getRow());
				
				obj.add(consultaVO);
			}
//			}catch(SQLException ex) {
//				JOptionPane.showMessageDialog(null,"Cliente não localizada.\nPara cadastrar clique em incluir.","Moto Express",JOptionPane.INFORMATION_MESSAGE);
//			}
		rs.close();
		minhaSTM.close();
		
		return (List<TabConsultaVO>)obj;
	}
	/**
	 * Metodo que localiza o cliente, endereco e telefone no bd pelo nome.
	 * @param usuariosDAO
	 * @return
	 */
	public List<TabConsultaVO> readDadosClienteByPeriodo(TabConsultaVO consultasVO) throws IllegalAccessException, InstantiationException, SQLException{
		ArrayList<TabConsultaVO> obj = new ArrayList<TabConsultaVO>();
		TabConsultaVO consultaVO = null;

		minhaConexao = ConnectionFactory.getConnection();
		minhaSTM = minhaConexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		String SQL = ("Select C.ClientePk, C.Nome, E.Endereco, C.Numero, E.Cidade, E.Estado, T.TelefoneA, T.CelularA, C.DataCadastro From Cliente C Inner Join Endereco E On E.EnderecoPk = C.EnderecoCFK Inner Join Telefone T On T.TelefonePk = C.TelefoneCFk WHERE C.DataCadastro BETWEEN '"+consultasVO.getDataInicios()+"'AND'"+consultasVO.getDataFinals()+"'");
		rs = minhaSTM.executeQuery(SQL);
			while(rs.next()){
				consultaVO = new TabConsultaVO();
				consultaVO.setClientePk(Integer.valueOf(rs.getString("ClientePk")));
				consultaVO.setNome(rs.getString("Nome"));
				consultaVO.setEndereco(rs.getString("Endereco") +" ,"+ rs.getString("Numero"));
				consultaVO.setCidade(rs.getString("Cidade"));
				consultaVO.setEstado(rs.getString("Estado"));
				consultaVO.setTelefoneA(rs.getString("TelefoneA"));
				consultaVO.setCelularA(rs.getString("CelularA"));
				consultaVO.setDataCadastros(rs.getTimestamp("DataCadastro"));
				consultaVO.setQtde(rs.getRow());
				
				obj.add(consultaVO);
			}
//			}catch(SQLException ex) {
//				JOptionPane.showMessageDialog(null,"Cliente não localizada.\nPara cadastrar clique em incluir.","Moto Express",JOptionPane.INFORMATION_MESSAGE);
//				}
		rs.close();
		minhaSTM.close();
		
		return (List<TabConsultaVO>)obj;
	}

//TRECHO DE CODIGO PARA REALIZAR A BUSCA DAS INFOMACOES DO FORNECEDOR
	/**
	 * Metodo que localiza o fornecedor, endereco e telefone no bd pelo Id.
	 * @param usuariosDAO
	 * @return
	 */
	public TabConsultaVO readDadosFornecedorByPk(TabConsultaVO consultaVO) throws IllegalAccessException, InstantiationException, SQLException{
		minhaConexao =  ConnectionFactory.getConnection();
		minhaSTM = minhaConexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		String SQL = ("Select F.FornecedorPk, F.Nome, E.Endereco, F.Numero, E.Cidade, E.Estado, T.TelefoneA, T.CelularA FROM Fornecedor F Inner Join Endereco E On E.EnderecoPk = F.EnderecoFFK Inner Join Telefone T On T.TelefonePk = F.TelefoneFFk WHERE F.FornecedorPk ='"+consultaVO.getFornecedorPk()+"'");
		rs = minhaSTM.executeQuery(SQL);
			while(rs.next()){
				consultaVO.setFornecedorPk(rs.getInt("FornecedorPk"));
				consultaVO.setNome(rs.getString("Nome"));
				consultaVO.setEndereco(rs.getString("Endereco") +" ,"+ rs.getString("Numero"));
				consultaVO.setCidade(rs.getString("Cidade"));
				consultaVO.setEstado(rs.getString("Estado"));
				consultaVO.setTelefoneA(rs.getString("TelefoneA"));
				consultaVO.setCelularA(rs.getString("CelularA"));
				consultaVO.setQtde(rs.getRow());
			}
//			}catch(SQLException ex) {
//				JOptionPane.showMessageDialog(null,"Motorista não localizada.\nPara cadastrar clique em incluir.","Moto Express",JOptionPane.INFORMATION_MESSAGE);
//			}
		rs.close();
		minhaSTM.close();

		return consultaVO;
	}
	/**
	 * Metodo que localiza o motorista, endereco e telefone no bd pelo nome.
	 * @param usuariosDAO
	 * @return
	 */
	public List<TabConsultaVO> readDadosFornecedorByNome(TabConsultaVO consultasVO) throws IllegalAccessException, InstantiationException, SQLException{
		ArrayList<TabConsultaVO> obj = new ArrayList<TabConsultaVO>();
		TabConsultaVO consultaVO = null;

		minhaConexao = ConnectionFactory.getConnection();
		minhaSTM = minhaConexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		String SQL = ("Select F.FornecedorPk, F.Nome, E.Endereco, F.Numero, E.Cidade, E.Estado, T.TelefoneA, T.CelularA FROM Fornecedor F Inner Join ENDERECO E On E.EnderecoPk = F.EnderecoFFK Inner Join TELEFONE T On T.TelefonePk = F.TelefoneFFk WHERE F.Nome LIKE'%"+consultasVO.getNome()+"%'");
		rs = minhaSTM.executeQuery(SQL);
			while(rs.next()){
				consultaVO = new TabConsultaVO();
				consultaVO.setFornecedorPk(rs.getInt("FornecedorPk"));
				consultaVO.setNome(rs.getString("Nome"));
				consultaVO.setEndereco(rs.getString("Endereco") +" ,"+ rs.getString("Numero"));
				consultaVO.setCidade(rs.getString("Cidade"));
				consultaVO.setEstado(rs.getString("Estado"));
				consultaVO.setTelefoneA(rs.getString("TelefoneA"));
				consultaVO.setCelularA(rs.getString("CelularA"));
				consultaVO.setQtde(rs.getRow());
				
				obj.add(consultaVO);
			}
//			}catch(SQLException ex) {
//				JOptionPane.showMessageDialog(null,"Motorista não localizada.\nPara cadastrar clique em incluir.","Moto Express",JOptionPane.INFORMATION_MESSAGE);
//			}
		rs.close();
		minhaSTM.close();
			
		return (List<TabConsultaVO>)obj;
	}
	/**
	 * Metodo que localiza o motorista, endereco e telefone no bd por periodo.
	 * @param usuariosDAO
	 * @return
	 */
	public List<TabConsultaVO> readDadosFornecedorByPeriodo(TabConsultaVO consultasVO) throws IllegalAccessException, InstantiationException, SQLException{
		ArrayList<TabConsultaVO> obj = new ArrayList<TabConsultaVO>();
		TabConsultaVO consultaVO = null;

		minhaConexao = ConnectionFactory.getConnection();
		minhaSTM = minhaConexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
//		String SQL = ("Select M.MotoristaPk, M.Nome, E.Endereco, M.Numero, E.Cidade, E.Estado, T.TelefoneA, T.CelularA, M.DataCadastro From Motorista M Inner Join Endereco E On E.EnderecoPk = M.EnderecoMFK Inner Join Telefone T On T.TelefonePk = M.TelefoneMFk WHERE M.DataCadastro BETWEEN '"+consultasVO.getDataInicios()+"'AND'"+consultasVO.getDataFinals()+"'");
		String SQL = ("Select F.FornecedorPk, F.Nome, E.Endereco, F.Numero, E.Cidade, E.Estado, T.TelefoneA, T.CelularA, F.DataCadastro FROM Fornecedor F Inner Join Endereco E On E.EnderecoPk = F.EnderecoFFK Inner Join Telefone T On T.TelefonePk = F.TelefoneFFk WHERE F.DataCadastro BETWEEN '"+consultasVO.getDataInicios()+"'AND'"+consultasVO.getDataFinals()+"'");		
		rs = minhaSTM.executeQuery(SQL);
			while(rs.next()){
				consultaVO = new TabConsultaVO();
				consultaVO.setFornecedorPk(rs.getInt("FornecedorPk"));
				consultaVO.setNome(rs.getString("Nome"));
				consultaVO.setEndereco(rs.getString("Endereco") +" ,"+ rs.getString("Numero"));
				consultaVO.setCidade(rs.getString("Cidade"));
				consultaVO.setEstado(rs.getString("Estado"));
				consultaVO.setTelefoneA(rs.getString("TelefoneA"));
				consultaVO.setCelularA(rs.getString("CelularA"));
				consultaVO.setDataCadastros(rs.getTimestamp("DataCadastro"));
				consultaVO.setQtde(rs.getRow());
				
				obj.add(consultaVO);
			}
//			}catch(SQLException ex) {
//				JOptionPane.showMessageDialog(null,"Motorista não localizada.\nPara cadastrar clique em incluir.","Moto Express",JOptionPane.INFORMATION_MESSAGE);
//			}
		rs.close();
		minhaSTM.close();
		
		return (List<TabConsultaVO>) obj;
	}
	//TRECHO DE CODIGO PARA REALIZAR A BUSCA DAS INFOMACOES DO MOTORISTA
		/**
		 * Metodo que localiza o motorista, endereco e telefone no bd pelo Id.
		 * @param usuariosDAO
		 * @return
		 */
		public TabConsultaVO readDadosMotoristaByPk(TabConsultaVO consultaVO) throws IllegalAccessException, InstantiationException, SQLException{
			minhaConexao =  ConnectionFactory.getConnection();
			minhaSTM = minhaConexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String SQL = ("Select M.MotoristaPk, M.Nome, E.Endereco, M.Numero, E.Cidade, E.Estado, T.TelefoneA, T.CelularA FROM Motorista M Inner Join Endereco E On E.EnderecoPk = M.EnderecoMFK Inner Join Telefone T On T.TelefonePk = M.TelefoneMFk WHERE M.MotoristaPk ='"+consultaVO.getMotoristaPk()+"'");
			rs = minhaSTM.executeQuery(SQL);
				while(rs.next()){
					consultaVO.setMotoristaPk(rs.getString("MotoristaPk"));
					consultaVO.setNome(rs.getString("Nome"));
					consultaVO.setEndereco(rs.getString("Endereco") +" ,"+ rs.getString("Numero"));
					consultaVO.setCidade(rs.getString("Cidade"));
					consultaVO.setEstado(rs.getString("Estado"));
					consultaVO.setTelefoneA(rs.getString("TelefoneA"));
					consultaVO.setCelularA(rs.getString("CelularA"));
					consultaVO.setQtde(rs.getRow());
				}
//				}catch(SQLException ex) {
//					JOptionPane.showMessageDialog(null,"Motorista não localizada.\nPara cadastrar clique em incluir.","Moto Express",JOptionPane.INFORMATION_MESSAGE);
//				}
			rs.close();
			minhaSTM.close();

			return consultaVO;
		}
		/**
		 * Metodo que localiza o motorista, endereco e telefone no bd pelo nome.
		 * @param usuariosDAO
		 * @return
		 */
		public List<TabConsultaVO> readDadosMotoristaByNome(TabConsultaVO consultasVO) throws IllegalAccessException, InstantiationException, SQLException{
			ArrayList<TabConsultaVO> obj = new ArrayList<TabConsultaVO>();
			TabConsultaVO consultaVO = null;

			minhaConexao = ConnectionFactory.getConnection();
			minhaSTM = minhaConexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String SQL = ("Select M.MotoristaPk, M.Nome, E.Endereco, M.Numero, E.Cidade, E.Estado, T.TelefoneA, T.CelularA From Motorista M Inner Join Endereco E On E.EnderecoPk = M.EnderecoMFK Inner Join Telefone T On T.TelefonePk = M.TelefoneMFk WHERE M.Nome LIKE'%"+consultasVO.getNome()+"%'");
			rs = minhaSTM.executeQuery(SQL);
				while(rs.next()){
					consultaVO = new TabConsultaVO();
					consultaVO.setMotoristaPk(rs.getString("MotoristaPk"));
					consultaVO.setNome(rs.getString("Nome"));
					consultaVO.setEndereco(rs.getString("Endereco") +" ,"+ rs.getString("Numero"));
					consultaVO.setCidade(rs.getString("Cidade"));
					consultaVO.setEstado(rs.getString("Estado"));
					consultaVO.setTelefoneA(rs.getString("TelefoneA"));
					consultaVO.setCelularA(rs.getString("CelularA"));
					consultaVO.setQtde(rs.getRow());
					
					obj.add(consultaVO);
				}
//				}catch(SQLException ex) {
//					JOptionPane.showMessageDialog(null,"Motorista não localizada.\nPara cadastrar clique em incluir.","Moto Express",JOptionPane.INFORMATION_MESSAGE);
//				}
			rs.close();
			minhaSTM.close();
				
			return (List<TabConsultaVO>)obj;
		}
		/**
		 * Metodo que localiza o motorista, endereco e telefone no bd por periodo.
		 * @param usuariosDAO
		 * @return
		 */
		public List<TabConsultaVO> readDadosMotoristaByPeriodo(TabConsultaVO consultasVO) throws IllegalAccessException, InstantiationException, SQLException{
			ArrayList<TabConsultaVO> obj = new ArrayList<TabConsultaVO>();
			TabConsultaVO consultaVO = null;

			minhaConexao = ConnectionFactory.getConnection();
			minhaSTM = minhaConexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String SQL = ("Select M.MotoristaPk, M.Nome, E.Endereco, M.Numero, E.Cidade, E.Estado, T.TelefoneA, T.CelularA, M.DataCadastro From Motorista M Inner Join Endereco E On E.EnderecoPk = M.EnderecoMFK Inner Join Telefone T On T.TelefonePk = M.TelefoneMFk WHERE M.DataCadastro BETWEEN '"+consultasVO.getDataInicios()+"'AND'"+consultasVO.getDataFinals()+"'");
			rs = minhaSTM.executeQuery(SQL);
				while(rs.next()){
					consultaVO = new TabConsultaVO();
					consultaVO.setMotoristaPk(rs.getString("MotoristaPk"));
					consultaVO.setNome(rs.getString("Nome"));
					consultaVO.setEndereco(rs.getString("Endereco") +" ,"+ rs.getString("Numero"));
					consultaVO.setCidade(rs.getString("Cidade"));
					consultaVO.setEstado(rs.getString("Estado"));
					consultaVO.setTelefoneA(rs.getString("TelefoneA"));
					consultaVO.setCelularA(rs.getString("CelularA"));
					consultaVO.setDataCadastros(rs.getTimestamp("DataCadastro"));
					consultaVO.setQtde(rs.getRow());
					
					obj.add(consultaVO);
				}
//				}catch(SQLException ex) {
//					JOptionPane.showMessageDialog(null,"Motorista não localizada.\nPara cadastrar clique em incluir.","Moto Express",JOptionPane.INFORMATION_MESSAGE);
//				}
			rs.close();
			minhaSTM.close();
			
			return (List<TabConsultaVO>) obj;
		}
}