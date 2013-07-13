package br.com.cs.controlmoto.domain;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import br.com.cs.controlmoto.vo.CadEmpresaVO;
import br.com.cs.controlmoto.vo.CadTelefoneVO;

public class CadTelefoneDAO {
	/**
	 * Variáveis parea coneção ao banco de dados
	 */
	Statement minhaSTM;
    ResultSet rs;
    Connection minhaConexao;
    /**
	 * Método que realiza a ação do botão incluir nas telas de cadastros.
	 * @param TelefoneDAO
	 * @return
	 */
	public void addTelefoneDAO(CadTelefoneVO telefoneVO) throws IllegalAccessException, InstantiationException, SQLException{
		minhaConexao = ConnectionFactory.getConnection();
		minhaSTM = minhaConexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		String SQL = ("SELECT * FROM Empresa");
		rs = minhaSTM.executeQuery(SQL);
		if(rs.last() == true){
			rs.last();
			int cod;
			telefoneVO.setTelefonePk(cod = Integer.parseInt(rs.getString("TelefonePK"))+1);
		}else{
			telefoneVO.setTelefonePk(Integer.valueOf(1));
		}
		rs.close();
		minhaSTM.close();
	}
	/**
	 * Método que grava o telefone no bd.
	 * @param TelefoneDAO
	 * @return 
	 */
	public CadTelefoneVO saveTelefoneDAO(CadTelefoneVO telefoneVO) throws IllegalAccessException, InstantiationException, SQLException{
		minhaConexao = ConnectionFactory.getConnection();
		minhaSTM = minhaConexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		String telefoneSQL = "INSERT INTO Telefone (TelefoneA,TelefoneB,CelularA,CelularB,FaxA,FaxB)Values ('"+
		telefoneVO.getTelefoneA()+"','"+
		telefoneVO.getTelefoneB()+"','"+
		telefoneVO.getCelularA()+"','"+
		telefoneVO.getCelularB()+"','"+
		telefoneVO.getFaxA()+"','"+
		telefoneVO.getFaxB()+ "')";
		minhaSTM.executeUpdate(telefoneSQL);
		
		String SQL = ("SELECT TelefonePk FROM Telefone");
		rs = minhaSTM.executeQuery(SQL);
		if(rs.last() == true){
			rs.last();
			telefoneVO.setTelefonePk(Integer.valueOf(rs.getString("TelefonePK")));
		}

		minhaSTM.close();
		
		return telefoneVO;
	}
	/**
	 * Método que localiza o telefone no bd.
	 * @param TelefoneDAO
	 * @return 
	 */
	public CadTelefoneVO readTelefoneByPk(CadTelefoneVO telefoneVO) throws IllegalAccessException, InstantiationException, SQLException{
		minhaConexao = ConnectionFactory.getConnection();
		minhaSTM = minhaConexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		String SQL = ("SELECT * FROM Telefone Where TelefonePK =" +telefoneVO.getTelefonePk());
		rs = minhaSTM.executeQuery(SQL);
		rs.next();			
		telefoneVO.setTelefoneA(rs.getString("TelefoneA"));
		telefoneVO.setTelefoneB(rs.getString("TelefoneB"));
		telefoneVO.setCelularA(rs.getString("CelularA"));
		telefoneVO.setCelularB(rs.getString("CelularB"));
		telefoneVO.setFaxA(rs.getString("FaxA"));
		telefoneVO.setFaxB(rs.getString("FaxB"));

		rs.close();
		minhaSTM.close();
		
		return telefoneVO;
	}
	/**
	 * Método que altera o telefone no bd.
	 * @param TelefoneDAO
	 * @return
	 */	
	public void alterTelefoneByPk(CadTelefoneVO telefoneVO) throws IllegalAccessException, InstantiationException, SQLException{
		minhaConexao = ConnectionFactory.getConnection();
		minhaSTM = minhaConexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		String SQL = "UPDATE Telefone SET " +
		"TelefoneA='"+ telefoneVO.getTelefoneA()+"', " +
		"TelefoneB='"+ telefoneVO.getTelefoneB()+ "', " +
		"CelularA='"+ telefoneVO.getCelularA()+"', " +
		"CelularB='"+ telefoneVO.getCelularB()+"', "+ 
		"FaxA='"+ telefoneVO.getFaxA()+"', " +
		"FaxB='"+ telefoneVO.getFaxB()+"'" +
		" WHERE TelefonePK=" +telefoneVO.getTelefonePk();
		int res = minhaSTM.executeUpdate(SQL);
		//if(res == 1){
			//JOptionPane.showMessageDialog(null,"Atualizacão realizada com sucesso!");
		//}else{
			//JOptionPane.showMessageDialog(null,"Telefone não cadastrado.\nPara cadastrar pressione incluir.");
			//rs.close();
		//}
	//}catch (SQLException ex) {
		//JOptionPane.showMessageDialog(null,"Erro na atualização dos dados.\n"+ex);
	//}
		minhaSTM.close();
	}
	/**
	 * Método que apaga o Empresa no bd.
	 * @param EmpresaDAO
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
			JOptionPane.showMessageDialog(null,"Empresa não cadastrado.","Moto Express",JOptionPane.INFORMATION_MESSAGE);
			//return;
			}
		
		int n = JOptionPane.showConfirmDialog(null, nome, "Moto Express",JOptionPane.YES_NO_OPTION);
		if (n == JOptionPane.YES_OPTION){
			SQL = ("DELETE FROM Empresa Where EmpresaPK =" +empresaVO.getEmpresaPK());
			int r = minhaSTM.executeUpdate(SQL);
			if (r == 1){
				JOptionPane.showMessageDialog(null,"Exclusão realizada com sucesso!","Moto Express",JOptionPane.INFORMATION_MESSAGE);
			}else{
				JOptionPane.showMessageDialog(null,"Não foi possível excluir a empresa.","Moto Express",JOptionPane.INFORMATION_MESSAGE);
			}
		}
		rs.close();
		minhaSTM.close();
	}

}
