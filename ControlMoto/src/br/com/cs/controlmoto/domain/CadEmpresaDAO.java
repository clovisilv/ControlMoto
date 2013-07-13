package br.com.cs.controlmoto.domain;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import br.com.cs.controlmoto.vo.CadEmpresaVO;

public class CadEmpresaDAO {
	/**
	 * Variaveis parea conecao ao banco de dados
	 */
	Statement minhaSTM;
    ResultSet rs;
    Connection minhaConexao;
    /**
	 * Metodo que realiza a acao do botao incluir Empresa no bd.
	 * @param EmpresaDAO
	 * @return
	 */
	public void addEmpresaDAO(CadEmpresaVO empresaVO) throws IllegalAccessException, InstantiationException, SQLException{
		minhaConexao = ConnectionFactory.getConnection();
		minhaSTM = minhaConexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		String SQL = ("SELECT * FROM Empresa");
		rs = minhaSTM.executeQuery(SQL);
		if(rs.last() == true){
			rs.last();
			int cod;
			empresaVO.setEmpresaPK(cod = Integer.parseInt(rs.getString("EmpresaPK"))+1);
		}else{
			empresaVO.setEmpresaPK(Integer.valueOf(1));
		}
//		}catch(Exception ex){
//			JOptionPane.showMessageDialog(null,"Cliente gravado com sucesso!");
//		}
		rs.close();
		minhaSTM.close();
	}
	/**
	 * Metodo que grava o Empresa no bd.
	 * @param EmpresaDAO
	 * @return
	 */
	public void saveEmpresaDAO(CadEmpresaVO empresaVO) throws IllegalAccessException, InstantiationException, SQLException{
		minhaConexao = ConnectionFactory.getConnection();
		minhaSTM = minhaConexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		String empresaSQL = "INSERT INTO Empresa (EmpresaPK,EnderecoEFk,TelefoneEFk,NomeEmpresa,Cnpj,Ccm,IE,Site,Email,DataCadastro)Values ('"+
		empresaVO.getEmpresaPK()+ "','"+
		empresaVO.getEnderecoFk()+"','"+
		empresaVO.getTelefoneFk()+"','"+
		empresaVO.getNome()+ "','"+
		empresaVO.getCnpj()+ "','"+
		empresaVO.getCcm()+ "','"+
		empresaVO.getInscricaoEstadual()+ "','"+ 
		empresaVO.getSite()+"','"+
		empresaVO.getEmail()+"','"+
		empresaVO.getDataCadastros() + "')"; 
		minhaSTM.executeUpdate(empresaSQL);
		
		minhaSTM.close();

	}
	/**
	 * Metodo que localiza o Empresa no bd.
	 * @param EmpresaDAO
	 * @return 
	 * @return
	 */
	public CadEmpresaVO readEmpresaByPk(CadEmpresaVO empresaVO) throws IllegalAccessException, InstantiationException, SQLException{
		minhaConexao = ConnectionFactory.getConnection();
		minhaSTM = minhaConexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		String SQL = ("SELECT * FROM Empresa Where EmpresaPK =" +empresaVO.getEmpresaPK());
		rs = minhaSTM.executeQuery(SQL);
		rs.next();			
		empresaVO.setEmpresaPK(Integer.parseInt((rs.getString("EmpresaPK"))));
		empresaVO.setEnderecoFk(Integer.parseInt(rs.getString("EnderecoEFk")));
		empresaVO.setTelefoneFk(Integer.parseInt(rs.getString("TelefoneEFk")));
		empresaVO.setNome(rs.getString("NomeEmpresa"));
		empresaVO.setCnpj(rs.getString("Cnpj"));
		empresaVO.setCcm(rs.getString("Ccm"));
		empresaVO.setInscricaoEstadual(rs.getString("IE"));
		empresaVO.setSite(rs.getString("Site"));
		empresaVO.setEmail(rs.getString("Email"));
		empresaVO.setDataCadastros(rs.getTimestamp("DataCadastro"));
		
		rs.close();
		minhaSTM.close();

		return empresaVO;
	}
	/**
	 * Metodo que altera o Empresa no bd.
	 * @param EmpresaDAO
	 * @return
	 */	
	public void alterEmpresaByPk(CadEmpresaVO empresaVO) throws IllegalAccessException, InstantiationException, SQLException{
		minhaConexao = ConnectionFactory.getConnection();
		minhaSTM = minhaConexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		String SQL = "UPDATE Empresa SET " +
		"EmpresaPK='"+ empresaVO.getEmpresaPK()+"'," +
		"NomeEmpresa='"+ empresaVO.getNome()+"'," +
		"Cnpj='"+ empresaVO.getCnpj()+"'," +
		"Ccm='"+ empresaVO.getCcm()+"'," +
		"IE='"+ empresaVO.getInscricaoEstadual()+"'," +
		"Site='"+ empresaVO.getSite()+"'," +
		"Email='"+ empresaVO.getEmail()+"'," +
		"DataCadastro='"+ empresaVO.getDataCadastros()+ "'" + 
		"WHERE EmpresaPK=" +empresaVO.getEmpresaPK();
		int res = minhaSTM.executeUpdate(SQL);
//		if(res == 1){
//			JOptionPane.showMessageDialog(null,"Atualizac√£o realizada com sucesso!");
//		}else{
//			JOptionPane.showMessageDialog(null,"Empresa n√£o cadastrado.\nPara cadastrar pressione incluir.");
//			rs.close();
//		}
		minhaSTM.close();
	}
	/**
	 * Metodo que apaga o Empresa no bd.
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
			JOptionPane.showMessageDialog(null,"Empresa n„o cadastrado.","Moto Express",JOptionPane.INFORMATION_MESSAGE);
			//return;
			}
		int n = JOptionPane.showConfirmDialog(null, nome, "Moto Express",JOptionPane.YES_NO_OPTION);
		if (n == JOptionPane.YES_OPTION){
			SQL = ("DELETE FROM Empresa Where EmpresaPK =" +empresaVO.getEmpresaPK());
			int r = minhaSTM.executeUpdate(SQL);
			if (r == 1){
				JOptionPane.showMessageDialog(null,"Exclus„o realizada com sucesso!","Moto Express",JOptionPane.INFORMATION_MESSAGE);
			}else{
				JOptionPane.showMessageDialog(null,"N„o foi possÌvel excluir a empresa.","Moto Express",JOptionPane.INFORMATION_MESSAGE);
			}
		}
		minhaSTM.close();
	}
	/**
	 * Metodo que pega o CCM Empresa no bd.
	 * @param EmpresaDAO
	 * @return
	 */
	public CadEmpresaVO getCcmRps() throws IllegalAccessException, InstantiationException, SQLException{
		
		CadEmpresaVO cadEmp = new CadEmpresaVO();
		
		minhaConexao = ConnectionFactory.getConnection();
		minhaSTM = minhaConexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		
		String SQL = ("SELECT Ccm FROM Empresa Where EmpresaPK="+"1");
		rs = minhaSTM.executeQuery(SQL);
		while(rs.next()){
			 cadEmp.setCcm(rs.getString("Ccm"));
		}
		minhaSTM.close();
		
		return cadEmp;
	}
}
