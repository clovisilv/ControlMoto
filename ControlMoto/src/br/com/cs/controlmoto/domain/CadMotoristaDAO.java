package br.com.cs.controlmoto.domain;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.cs.controlmoto.vo.CadMotoristaVO;

public class CadMotoristaDAO {
	/**
	 * Vari·veis parea conex„o ao banco de dados
	 */
	Statement minhaSTM;
    ResultSet rs;
    Connection minhaConexao;
    /**
	 * MÈtodo que realiza a aÁ„o do bot„o incluir motorista no bd.
	 * @param motoristaDAO
	 * @return
	 */
	public void addMotiristaDAO(CadMotoristaVO motoristaVO) throws IllegalAccessException, InstantiationException, SQLException{
		minhaConexao = ConnectionFactory.getConnection();
		minhaSTM = minhaConexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		String SQL = ("SELECT * FROM Motorista");
		rs = minhaSTM.executeQuery(SQL);
		if(rs.last() == true){
			rs.last();
			motoristaVO.setMotoristaPK(rs.getString("motoristaPk"));
		}else{
			motoristaVO.setMotoristaPK(null);
		}
//		}catch(Exception ex){
//			JOptionPane.showMessageDialog(null,"Digite os dados do motorista e clique em gravar!");
//		}
		rs.close();
		minhaSTM.close();
	}
	/**
	 * MÈtodo que grava o motorista no bd.
	 * @param motoristaDAO
	 * @return
	 */
	public void saveMotoristaDAO(CadMotoristaVO motoristaVO) throws IllegalAccessException, InstantiationException, SQLException{
		minhaConexao = ConnectionFactory.getConnection();
		minhaSTM = minhaConexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		String SQL = "INSERT INTO Motorista (MotoristaPk,EnderecoMFk,TelefoneMFk,Nome,Numero,Rg,Cpf,Habilitacao,Categoria,DataVencimento," +
		"DataNascimento,Banco,Agencia,Conta,Tipo,Comissao,DataCadastro)Values ('"+
		motoristaVO.getMotoristaPK()+ "','"+
		motoristaVO.getEnderecoFk()+ "','"+
		motoristaVO.getTelefoneFk()+ "','"+
		motoristaVO.getNome()+ "','"+
		motoristaVO.getNumero()+ "','"+
		motoristaVO.getRg()+ "','"+
		motoristaVO.getCpf()+ "','"+ 
		motoristaVO.getHabilitacao()+ "','"+
		motoristaVO.getCategoria()+ "','"+
		motoristaVO.getDataVencimentos()+ "','"+
		motoristaVO.getDataNascimentos()+ "','"+
		motoristaVO.getBanco()+"','"+
		motoristaVO.getAgencia()+"','"+
		motoristaVO.getConta()+"','"+
		motoristaVO.getTipo()+"','"+
		motoristaVO.getComissao()+ "','"+
		motoristaVO.getDataCadastros()+ "')"; 
		minhaSTM.executeUpdate(SQL);
//		} catch (SQLException ex) {
//			if (!ex.getMessage().equals("General error")){
//				JOptionPane.showMessageDialog(null,"Motorista gravado com sucesso! \n"+ex);
//			}else{
//				JOptionPane.showMessageDialog(null,"Motorista j√° Cadastrada! \n"+ex);
//			}
//		}
		minhaSTM.close();
	}
	/**
	 * MÈtodo que localiza o motorista no bd.
	 * @param motoristaDAO
	 * @return
	 */
	public CadMotoristaVO readMotoristaByPk(CadMotoristaVO motoristaVO) throws IllegalAccessException, InstantiationException, SQLException{
		minhaConexao = ConnectionFactory.getConnection();
		minhaSTM = minhaConexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		String SQL = ("SELECT * FROM Motorista Where MotoristaPK ='"+motoristaVO.getMotoristaPK()+"'");
		rs = minhaSTM.executeQuery(SQL);
		rs.next();			
		motoristaVO.setMotoristaPK(rs.getString("MotoristaPK"));
		motoristaVO.setEnderecoFk(Integer.parseInt(rs.getString("EnderecoMFK")));
		motoristaVO.setTelefoneFk(Integer.parseInt(rs.getString("TelefoneMFK")));
		motoristaVO.setNome(rs.getString("Nome"));
		motoristaVO.setNumero(rs.getString("Numero"));
		motoristaVO.setRg(rs.getString("Rg"));
		motoristaVO.setCpf(rs.getString("Cpf"));
		motoristaVO.setHabilitacao(rs.getString("Habilitacao"));
		motoristaVO.setCategoria(rs.getString("Categoria"));
		motoristaVO.setDataVencimentos(rs.getDate("DataVencimento"));
		motoristaVO.setDataNascimentos(rs.getDate("DataNascimento"));
		motoristaVO.setBanco(rs.getString("Banco"));
		motoristaVO.setAgencia(rs.getString("Agencia"));
		motoristaVO.setConta(rs.getString("Conta"));
		motoristaVO.setTipo(rs.getString("Tipo"));
		motoristaVO.setComissao(rs.getString("Comissao"));
		motoristaVO.setDataCadastros(rs.getTimestamp("DataCadastro"));		

		rs.close();
		minhaSTM.close();
		
		return motoristaVO;
	}
	/**
	 * MÈtodo que altera o motorista no bd.
	 * @param motoristaDAO
	 * @return
	 */	
	public void alterMotoristaByPk(CadMotoristaVO motoristaVO) throws IllegalAccessException, InstantiationException, SQLException{
		minhaConexao = ConnectionFactory.getConnection();
		minhaSTM = minhaConexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		String SQL = "UPDATE Motorista SET " +
		" MotoristaPK='"+ motoristaVO.getMotoristaPK()+"'," +
		" EnderecoMFk='"+ motoristaVO.getEnderecoFk()+"'," +
		" TelefoneMFk='"+ motoristaVO.getTelefoneFk()+"'," +
		" Nome='"+ motoristaVO.getNome()+"'," +
		" Rg='"+ motoristaVO.getRg()+"'," +
		" Cpf='"+ motoristaVO.getCpf()+"'," +
		" Habilitacao='"+ motoristaVO.getHabilitacao()+"'," +
		" Categoria='"+ motoristaVO.getCategoria()+"'," +
		" DataVencimento='"+ motoristaVO.getDataVencimentos()+"'," +
		" DataNascimento='"+ motoristaVO.getDataNascimentos()+"'," +
		" Banco='"+ motoristaVO.getBanco()+"'," +
		" Agencia='"+ motoristaVO.getAgencia()+"'," +
		" Conta='"+ motoristaVO.getConta()+"'," +
		" Tipo='"+ motoristaVO.getTipo()+"'," +
		" Comissao='"+ motoristaVO.getComissao()+"'," +
		" DataCadastro='"+ motoristaVO.getDataCadastros()+ "'" + 
		" WHERE MotoristaPK='" +motoristaVO.getMotoristaPK()+"'";
		int res = minhaSTM.executeUpdate(SQL);
//		if(res == 1){
//			JOptionPane.showMessageDialog(null,"Atualizac√£o realizada com sucesso!");
//		}else{
//			JOptionPane.showMessageDialog(null,"Motorista n√£o cadastrado.\nPara cadastrar clique incluir.");
//			rs.close();
//		}
//		}catch (SQLException ex) {
//			JOptionPane.showMessageDialog(null,"Erro na atualiza√ß√£o dos dados.\n"+ex);
//		}
		minhaSTM.close();
	}
	/**
	 * MÈtodo que apaga o motorista no bd.
	 * @param motoristaDAO
	 * @return
	 */
	public void deleteMotoristaByPk(CadMotoristaVO motoristaVO) throws IllegalAccessException, InstantiationException, SQLException{	
		minhaConexao = ConnectionFactory.getConnection();
		minhaSTM = minhaConexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		String SQL = ("SELECT MotoristaPK, Nome FROM Motorista Where MotoristaPK='"+motoristaVO.getMotoristaPK()+"'");
		rs = minhaSTM.executeQuery(SQL);
		String nome = "";
		try {
			rs.next();
			nome = "Deseja remover o motorista :\n " + rs.getString("Nome");
		}catch (SQLException ex1){
			JOptionPane.showMessageDialog(null,"Motorista n„o cadastrado.","Moto Express",JOptionPane.INFORMATION_MESSAGE);
			//return;
		}
		int n = JOptionPane.showConfirmDialog(null, nome, "Moto Express",JOptionPane.YES_NO_OPTION);
		if (n == JOptionPane.YES_OPTION){
			SQL = ("DELETE FROM Motorista Where MotoristaPK ='"+motoristaVO.getMotoristaPK()+"'");
			int r = minhaSTM.executeUpdate(SQL);
			if (r == 1){
				JOptionPane.showMessageDialog(null,"Exclus„o realizada com sucesso!","Moto Express",JOptionPane.INFORMATION_MESSAGE);
			}else{
				JOptionPane.showMessageDialog(null,"N„o foi possÌvel excluir o motorista.","Moto Express",JOptionPane.INFORMATION_MESSAGE);
			}
		}
//		}catch (SQLException ex) {
//			JOptionPane.showMessageDialog(null,""+ex,"Moto Express",JOptionPane.INFORMATION_MESSAGE);
//		}
		minhaSTM.close();	
	}
	/**
	 * METODO QUE CARREGA A LISTA DE MOTORISTA PARA COMBOBOX
	 */
	public List<CadMotoristaVO> getMotoristaByCombo() throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {
		CadMotoristaVO cadMotoristaVO; List<CadMotoristaVO> cadMotoristaVOs = new ArrayList<>();
		minhaConexao = ConnectionFactory.getConnection();
		minhaSTM = minhaConexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		java.lang.String sql = ("SELECT MotoristaPk, Nome FROM Motorista");
		rs = minhaSTM.executeQuery(sql);
		while(rs.next()){
			cadMotoristaVO = new CadMotoristaVO();
			cadMotoristaVO.setMotoristaPK(rs.getString("MotoristaPk"));
			cadMotoristaVO.setNome(rs.getString("Nome"));
			
			cadMotoristaVOs.add(cadMotoristaVO);
		}
		
		return cadMotoristaVOs;
	}
}
