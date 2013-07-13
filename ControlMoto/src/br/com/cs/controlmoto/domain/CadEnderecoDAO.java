package br.com.cs.controlmoto.domain;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import br.com.cs.controlmoto.vo.CadEmpresaVO;
import br.com.cs.controlmoto.vo.CadEnderecoVO;

public class CadEnderecoDAO {
	/**
	 * Variaveis parea conecao ao banco de dados
	 */
	Statement minhaSTM;
    ResultSet rs;
    Connection minhaConexao;
    /**
	 * Método que realiza a acao do botao incluir nas telas de cadastros.
	 * @param CadEnderecoDAO
	 * @return
	 */
	public void addEnderecoDAO(CadEnderecoVO enderecoVO) throws IllegalAccessException, InstantiationException, SQLException{
		minhaConexao = ConnectionFactory.getConnection();
		minhaSTM = minhaConexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		String SQL = ("SELECT * FROM Empresa");
		rs = minhaSTM.executeQuery(SQL);
		if(rs.last() == true){
			rs.last();
			int cod;
			enderecoVO.setEnderecoPk(cod = Integer.parseInt(rs.getString("EmpresaPK"))+1);
		}else{
			enderecoVO.setEnderecoPk(Integer.valueOf(1));
		}
		
		rs.close();
		minhaSTM.close();
	}
	/**
	 * Método que grava o endereco no bd.
	 * @param EnderecoDAO
	 * @return 
	 */
	public CadEnderecoVO saveEnderecoDAO(CadEnderecoVO enderecoVO) throws IllegalAccessException, InstantiationException, SQLException{
		minhaConexao = ConnectionFactory.getConnection();
		minhaSTM = minhaConexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		String enderecoSQL = "INSERT INTO Endereco(Cep,Endereco,Bairro,Cidade,Estado)Values ('"+
		enderecoVO.getCep()+"','"+
		enderecoVO.getEndereco()+"','"+
		enderecoVO.getBairro()+"','"+
		enderecoVO.getCidade()+"','"+
		enderecoVO.getEstado()+ "')"; 
		minhaSTM.executeUpdate(enderecoSQL);
		
		String SQL = ("SELECT EnderecoPk FROM Endereco");
		rs = minhaSTM.executeQuery(SQL);
		if(rs.last() == true){
			rs.last();
			enderecoVO.setEnderecoPk(Integer.valueOf(rs.getString("EnderecoPK")));
		}
		
		rs.close();
		minhaSTM.close();
		
		return enderecoVO;
	}
	/**
	 * Método que localiza o endereco no bd.
	 * @param EnderecoDAO
	 * @return 
	 */
	public CadEnderecoVO readEnderecoByPk(CadEnderecoVO enderecoVO) throws IllegalAccessException, InstantiationException, SQLException{
		minhaConexao = ConnectionFactory.getConnection();
		minhaSTM = minhaConexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		String SQL = ("SELECT * FROM Endereco Where EnderecoPK =" +enderecoVO.getEnderecoPk());
		rs = minhaSTM.executeQuery(SQL);
		rs.next();			
		enderecoVO.setCep(rs.getString("Cep"));			
		enderecoVO.setEndereco(rs.getString("Endereco"));
		enderecoVO.setBairro(rs.getString("Bairro"));
		enderecoVO.setCidade(rs.getString("Cidade"));
		enderecoVO.setEstado(rs.getString("Estado"));
		
		rs.close();
		minhaSTM.close();

		return enderecoVO;
	}
	/**
	 * Método que localiza o endereco pelo cep no bd.
	 * @param EnderecoDAO
	 * @return 
	 */
	public CadEnderecoVO readEnderecoByCep(CadEnderecoVO enderecoVO) throws IllegalAccessException, InstantiationException, SQLException{
		minhaConexao = ConnectionFactory.getConnection();
		minhaSTM = minhaConexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		String SQL = ("SELECT * FROM Endereco Where Cep ='" +enderecoVO.getCep()+"'");
		rs = minhaSTM.executeQuery(SQL);
		rs.next();
		enderecoVO.setEnderecoPk(rs.getInt("EnderecoPk"));
		enderecoVO.setEndereco(rs.getString("Endereco"));
		enderecoVO.setBairro(rs.getString("Bairro"));
		enderecoVO.setCidade(rs.getString("Cidade"));
		enderecoVO.setEstado(rs.getString("Estado"));
		
		rs.close();
		minhaSTM.close();

		return enderecoVO;
	}
	/**
	 * Método que altera o endereco no bd.
	 * @param EnderecoDAO
	 * @return
	 */	
	public void alterEnderecoByPk(CadEnderecoVO enderecoVO) throws IllegalAccessException, InstantiationException, SQLException{
		minhaConexao = ConnectionFactory.getConnection();
		minhaSTM = minhaConexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		String SQL = "UPDATE Endereco SET " +
		"EnderecoPk='"+ enderecoVO.getEnderecoPk()+"'," + 
		"Cep='"+ enderecoVO.getCep()+"'," +
		"Endereco='"+ enderecoVO.getEndereco()+"'," +
		"Bairro='"+ enderecoVO.getBairro()+"'," +
		"Cidade='"+ enderecoVO.getCidade()+"'," +
		"Estado='"+ enderecoVO.getEstado()+"'" +
		" WHERE EnderecoPK=" +enderecoVO.getEnderecoPk();
		int res = minhaSTM.executeUpdate(SQL);
//		if(res == 1){
//			JOptionPane.showMessageDialog(null,"Atualizacao realizada com sucesso!");
//		}else{
//			JOptionPane.showMessageDialog(null,"Endereco nao cadastrado.\nPara cadastrar pressione incluir.");
//			rs.close();
//		}
		minhaSTM.close();
	}
	/**
	 * Método que apaga o endereco no bd.
	 * @param EnderecoDAO
	 * @return
	 */
	public void deleteEmpresaByPk(CadEmpresaVO empresaVO) throws IllegalAccessException, InstantiationException, SQLException{
		minhaConexao = ConnectionFactory.getConnection();
		minhaSTM = minhaConexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		String SQL = ("SELECT EmpresaPK, NomeEmpresa FROM Empresa Where EmpresaPK=" + empresaVO.getEmpresaPK());
		rs = minhaSTM.executeQuery(SQL);
		String nome = "";
		try {
			rs.next();
			nome = "Deseja remover o empresa :\n " + rs.getString("NomeEmpresa");
		}catch (SQLException ex1){
			JOptionPane.showMessageDialog(null,"Empresa nao cadastrado.","Moto Express",JOptionPane.INFORMATION_MESSAGE);
			//return;
		}
		
		int n = JOptionPane.showConfirmDialog(null, nome, "Moto Express",JOptionPane.YES_NO_OPTION);
		if (n == JOptionPane.YES_OPTION){
			SQL = ("DELETE FROM Empresa Where EmpresaPK =" +empresaVO.getEmpresaPK());
			int r = minhaSTM.executeUpdate(SQL);
			if (r == 1){
				JOptionPane.showMessageDialog(null,"Exclusao realizada com sucesso!","Moto Express",JOptionPane.INFORMATION_MESSAGE);
			}else{
				JOptionPane.showMessageDialog(null,"Nao foi possivel excluir a empresa.","Moto Express",JOptionPane.INFORMATION_MESSAGE);
			}
		}
		rs.close();
		minhaSTM.close();
	}
	
}
