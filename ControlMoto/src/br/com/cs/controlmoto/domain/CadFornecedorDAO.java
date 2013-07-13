package br.com.cs.controlmoto.domain;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.cs.controlmoto.vo.CadFornecedorVO;


public class CadFornecedorDAO {
	/**
	 * Vari·veis parea conex„o ao banco de dados
	 */
	Statement minhaSTM;
    ResultSet rs;
    Connection minhaConexao;
    /**
	 * MÈtodo que realiza a aÁ„o do bot„o incluir Fornecedor no bd.
	 * @param CadFornecedorDAO
	 * @return
	 */
	public void addFornecedorDAO(CadFornecedorVO fornecedorVO) throws IllegalAccessException, InstantiationException, SQLException{
		minhaConexao = ConnectionFactory.getConnection();
		minhaSTM = minhaConexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		String SQL = ("SELECT * FROM Fornecedor");
		rs = minhaSTM.executeQuery(SQL);
		if(rs.last() == true){
			rs.last();
			int cod;
			fornecedorVO.setFornecedorPk(cod = Integer.parseInt(rs.getString("FornecedorPk"))+1);
		}else{
			fornecedorVO.setFornecedorPk(Integer.valueOf(1));
		}
//		}catch(Exception ex){
//			JOptionPane.showMessageDialog(null,"Fornecedor gravado com sucesso!");
//		}
		rs.close();
		minhaSTM.close();
	}
	/**
	 * MÈtodo que grava o Fornecedor no bd.
	 * @param CadFornecedorDAO
	 * @return
	 */
	public void saveFornecedorDAO(CadFornecedorVO fornecedorVO) throws IllegalAccessException, InstantiationException, SQLException{
		minhaConexao = ConnectionFactory.getConnection();
		minhaSTM = minhaConexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		String SQL = "INSERT INTO Fornecedor (FornecedorPk, EnderecoFFk, TelefoneFFk, Nome, Numero, Cnpj, IE, Site, Email, Contato," +
		" ValorHora, Taxa, DataCadastro)Values ('"+
		fornecedorVO.getFornecedorPk()+ "','"+
		fornecedorVO.getEnderecoFk()+"','"+
		fornecedorVO.getTelefoneFk()+"','"+
		fornecedorVO.getNome()+ "','"+
		fornecedorVO.getNumero()+ "','"+
		fornecedorVO.getCnpj()+ "','"+
		fornecedorVO.getInscricaoestadual()+ "','"+
		fornecedorVO.getSite()+"','"+
		fornecedorVO.getEmail()+"','"+
		fornecedorVO.getContato()+"','"+
		fornecedorVO.getValorHora()+"','"+
		fornecedorVO.getTaxa()+"','"+
		fornecedorVO.getDataCadastros()+ "')"; 
		minhaSTM.executeUpdate(SQL);
//		} catch (SQLException ex) {
//			if (!ex.getMessage().equals("General error")){
//				JOptionPane.showMessageDialog(null,"Fornecedor gravado com sucesso! \n"+ex);
//			}else{
//				JOptionPane.showMessageDialog(null,"Fornecedor j√° Cadastrada! \n"+ex);
//			}
//		}
		minhaSTM.close();
	}
	/**
	 * MÈtodo que localiza o Fornecedor no bd.
	 * @param CadFornecedorDAO
	 * @return
	 */
	public CadFornecedorVO readFornecedorByPk(CadFornecedorVO fornecedorVO) throws IllegalAccessException, InstantiationException, SQLException{
		minhaConexao = ConnectionFactory.getConnection();
		minhaSTM = minhaConexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		String SQL = ("SELECT * FROM Fornecedor Where FornecedorPK =" +fornecedorVO.getFornecedorPk());
		rs = minhaSTM.executeQuery(SQL);
		rs.next();			
		fornecedorVO.setFornecedorPk(Integer.parseInt((rs.getString("FornecedorPK"))));
		fornecedorVO.setEnderecoFk(Integer.parseInt(rs.getString("EnderecoFFK")));
		fornecedorVO.setTelefoneFk(Integer.parseInt(rs.getString("TelefoneFFK")));
		fornecedorVO.setNome(rs.getString("Nome"));
		fornecedorVO.setNumero(rs.getString("Numero"));
		fornecedorVO.setCnpj(rs.getString("Cnpj"));
		fornecedorVO.setInscricaoestadual(rs.getString("IE"));
		fornecedorVO.setContato(rs.getString("Contato"));
		fornecedorVO.setSite(rs.getString("Site"));
		fornecedorVO.setEmail(rs.getString("Email"));
		fornecedorVO.setValorHora(rs.getString("ValorHora"));
		fornecedorVO.setTaxa(rs.getString("Taxa"));
		fornecedorVO.setDataCadastros(rs.getTimestamp("DataCadastro"));		
		//}catch(SQLException ex) {
			//JOptionPane.showMessageDialog(null,"Fornecedor n√£o localizada.\nPara cadastrar clique em incluir.","Moto Express",JOptionPane.INFORMATION_MESSAGE);
		//}
		rs.close();
		minhaSTM.close();
		
		return fornecedorVO;
	}
	/**
	 * MÈtodo que altera o Fornecedor no bd.
	 * @param CadFornecedorDAO
	 * @return
	 */	
	public Integer alterFornecedorByPk(CadFornecedorVO fornecedorVO) throws IllegalAccessException, InstantiationException, SQLException{
		minhaConexao = ConnectionFactory.getConnection();
		minhaSTM = minhaConexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		String SQL = "UPDATE Fornecedor SET " +
		"FornecedorPK='"+ fornecedorVO.getFornecedorPk()+"'," +
		"Nome='"+ fornecedorVO.getNome()+"'," +
		"Numero='"+ fornecedorVO.getNumero()+"'," +
		"Cnpj='"+ fornecedorVO.getCnpj()+"'," +
		"IE='"+ fornecedorVO.getInscricaoestadual()+"'," +
		"Contato='"+ fornecedorVO.getContato()+"'," +
		"Site='"+ fornecedorVO.getSite()+"'," +
		"Email='"+ fornecedorVO.getEmail()+"'," +
		"ValorHora='"+ fornecedorVO.getValorHora()+"'," +
		"Taxa='"+ fornecedorVO.getTaxa()+"'," +
		"DataCadastro='"+ fornecedorVO.getDataCadastros()+ "'" + 
		" WHERE FornecedorPK=" +fornecedorVO.getFornecedorPk();
		int res = minhaSTM.executeUpdate(SQL);
		//if(res == 1){
			//JOptionPane.showMessageDialog(null,"Atualizac√£o realizada com sucesso!");
		//}else{
			//JOptionPane.showMessageDialog(null,"Clientye n√£o cadastrado.\nPara cadastrar pressione incluir.");
			//rs.close();
		//}
		//}catch (SQLException ex) {
			//JOptionPane.showMessageDialog(null,"Erro na atualiza√ß√£o dos dados.\n"+ex);
		//}
		minhaSTM.close();
		
		return res;
	}
	/**
	 * MÈtodo que apaga o Fornecedor no bd.
	 * @param CadFornecedorDAO
	 * @return
	 */
	public void deleteFornecedorByPk(CadFornecedorVO fornecedorVO) throws IllegalAccessException, InstantiationException, SQLException{
		minhaConexao = ConnectionFactory.getConnection();
		minhaSTM = minhaConexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		String sql = ("SELECT FornecedorPK, Nome FROM Fornecedor Where FornecedorPK=" + fornecedorVO.getFornecedorPk());
		rs = minhaSTM.executeQuery(sql);
		String nome = "";
		try {
			rs.next();
			nome = "Deseja remover o Fornecedor :\n " + rs.getString("Nome");
		}catch (SQLException ex1){
			JOptionPane.showMessageDialog(null,"Fornecedor n„o cadastrado.","Moto Express",JOptionPane.INFORMATION_MESSAGE);
			//return;
		}
		int n = JOptionPane.showConfirmDialog(null, nome, "Moto Express",JOptionPane.YES_NO_OPTION);
		if (n == JOptionPane.YES_OPTION){
			sql = ("DELETE FROM Fornecedor Where FornecedorPK =" +fornecedorVO.getFornecedorPk());
			int r = minhaSTM.executeUpdate(sql);
			if (r == 1){
				JOptionPane.showMessageDialog(null,"Exclus„o realizada com sucesso!","Moto Express",JOptionPane.INFORMATION_MESSAGE);
			}else{
				JOptionPane.showMessageDialog(null,"N„o foi possÌvel excluir o Fornecedor.","Moto Express",JOptionPane.INFORMATION_MESSAGE);
			}
		}
//		}catch (SQLException ex) {
//			JOptionPane.showMessageDialog(null,""+ex,"Moto Express",JOptionPane.INFORMATION_MESSAGE);
//		}
		rs.close();
		minhaSTM.close();
	}
	/**
	 * 
	 */
	public List<CadFornecedorVO> getAllFornecedor() throws IllegalAccessException, InstantiationException, SQLException {
		CadFornecedorVO fornecedorVO = new CadFornecedorVO();
		List<CadFornecedorVO> fornecedorList = new ArrayList<CadFornecedorVO>();
		
		minhaConexao = ConnectionFactory.getConnection();
		minhaSTM = minhaConexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		String sql = "SELECT * FROM Fornecedor";
		rs = minhaSTM.executeQuery(sql);
		while(rs.next()){
			fornecedorVO.setFornecedorPk(rs.getInt("FornecedorPk"));
			fornecedorVO.setNome(rs.getString("Nome"));
			
			fornecedorList.add(fornecedorVO);
		}
		
		return fornecedorList;
	}
}
