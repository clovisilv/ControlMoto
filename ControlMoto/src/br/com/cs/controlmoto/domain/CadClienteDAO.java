package br.com.cs.controlmoto.domain;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.cs.controlmoto.vo.CadClienteVO;


public class CadClienteDAO {
	/**
	 * Vari·veis parea conex„o ao banco de dados
	 */
	Statement minhaSTM;
    ResultSet rs;
    Connection minhaConexao;
    /**
	 * MÈtodo que realiza a aÁ„o do bot„o incluir cliente no bd.
	 * @param CadClienteDAO
	 * @return
	 */
	public void addClienteDAO(CadClienteVO clienteVO) throws IllegalAccessException, InstantiationException, SQLException{
		minhaConexao = ConnectionFactory.getConnection();
		minhaSTM = minhaConexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		String SQL = ("SELECT * FROM Cliente");
		rs = minhaSTM.executeQuery(SQL);
		if(rs.last() == true){
			rs.last();
			int cod;
			clienteVO.setClientePk(cod = Integer.parseInt(rs.getString("clientePk"))+1);
		}else{
			clienteVO.setClientePk(Integer.valueOf(1));
		}
//		}catch(Exception ex){
//			JOptionPane.showMessageDialog(null,"Cliente gravado com sucesso!");
//		}
		rs.close();
		minhaSTM.close();
	}
	/**
	 * MÈtodo que grava o cliente no bd.
	 * @param CadClienteDAO
	 * @return
	 */
	public void saveClienteDAO(CadClienteVO clienteVO) throws IllegalAccessException, InstantiationException, SQLException{
		minhaConexao = ConnectionFactory.getConnection();
		minhaSTM = minhaConexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		String SQL = "INSERT INTO Cliente (ClientePk,EnderecoCFk,TelefoneCFk,Nome,Numero,Cnpj,IE,Site,Email,Contato," +
		" ValorHora,Taxa,DataCadastro)Values ('"+
		clienteVO.getClientePk()+ "','"+
		clienteVO.getEnderecoFk()+"','"+
		clienteVO.getTelefoneFk()+"','"+
		clienteVO.getNome()+ "','"+
		clienteVO.getNumero()+ "','"+
		clienteVO.getCnpj()+ "','"+
		clienteVO.getInscricaoestadual()+ "','"+
		clienteVO.getSite()+"','"+
		clienteVO.getEmail()+"','"+
		clienteVO.getContato()+"','"+
		clienteVO.getValorHora()+"','"+
		clienteVO.getTaxa()+"','"+
		clienteVO.getDataCadastros()+ "')"; 
		minhaSTM.executeUpdate(SQL);
//		} catch (SQLException ex) {
//			if (!ex.getMessage().equals("General error")){
//				JOptionPane.showMessageDialog(null,"Cliente gravado com sucesso! \n"+ex);
//			}else{
//				JOptionPane.showMessageDialog(null,"Cliente j√° Cadastrada! \n"+ex);
//			}
//		}
		minhaSTM.close();
	}
	/**
	 * MÈtodo que localiza o cliente no bd.
	 * @param CadClienteDAO
	 * @return
	 */
	public CadClienteVO readClienteByPk(CadClienteVO clienteVO) throws IllegalAccessException, InstantiationException, SQLException{
		minhaConexao = ConnectionFactory.getConnection();
		minhaSTM = minhaConexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		String SQL = ("SELECT * FROM Cliente Where ClientePK =" +clienteVO.getClientePk());
		rs = minhaSTM.executeQuery(SQL);
		rs.next();			
		clienteVO.setClientePk(Integer.parseInt((rs.getString("ClientePK"))));
		clienteVO.setEnderecoFk(Integer.parseInt(rs.getString("EnderecoCFK")));
		clienteVO.setTelefoneFk(Integer.parseInt(rs.getString("TelefoneCFK")));
		clienteVO.setNome(rs.getString("Nome"));
		clienteVO.setNumero(rs.getString("Numero"));
		clienteVO.setCnpj(rs.getString("Cnpj"));
		clienteVO.setInscricaoestadual(rs.getString("IE"));
		clienteVO.setContato(rs.getString("Contato"));
		clienteVO.setSite(rs.getString("Site"));
		clienteVO.setEmail(rs.getString("Email"));
		clienteVO.setValorHora(rs.getString("ValorHora"));
		clienteVO.setTaxa(rs.getString("Taxa"));
		clienteVO.setDataCadastros(rs.getTimestamp("DataCadastro"));		
		//}catch(SQLException ex) {
			//JOptionPane.showMessageDialog(null,"Cliente n√£o localizada.\nPara cadastrar clique em incluir.","Moto Express",JOptionPane.INFORMATION_MESSAGE);
		//}
		rs.close();
		minhaSTM.close();
		
		return clienteVO;
	}
	/**
	 * MÈtodo que altera o cliente no bd.
	 * @param CadClienteDAO
	 * @return
	 */	
	public Integer alterClienteByPk(CadClienteVO clienteVO) throws IllegalAccessException, InstantiationException, SQLException{
		minhaConexao = ConnectionFactory.getConnection();
		minhaSTM = minhaConexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		String SQL = "UPDATE Cliente SET " +
		"ClientePK='"+ clienteVO.getClientePk()+"'," +
		"Nome='"+ clienteVO.getNome()+"'," +
		"Numero='"+ clienteVO.getNumero()+"'," +
		"Cnpj='"+ clienteVO.getCnpj()+"'," +
		"IE='"+ clienteVO.getInscricaoestadual()+"'," +
		"Contato='"+ clienteVO.getContato()+"'," +
		"Site='"+ clienteVO.getSite()+"'," +
		"Email='"+ clienteVO.getEmail()+"'," +
		"ValorHora='"+ clienteVO.getValorHora()+"'," +
		"Taxa='"+ clienteVO.getTaxa()+"'," +
		"DataCadastro='"+ clienteVO.getDataCadastros()+ "'" + 
		" WHERE ClientePK=" +clienteVO.getClientePk();
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
	 * MÈtodo que apaga o cliente no bd.
	 * @param CadClienteDAO
	 * @return
	 */
	public void deleteClienteByPk(CadClienteVO clienteVO) throws IllegalAccessException, InstantiationException, SQLException{
		minhaConexao = ConnectionFactory.getConnection();
		minhaSTM = minhaConexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		String SQL = ("SELECT ClientePK, Nome FROM Cliente Where ClientePK=" + clienteVO.getClientePk());
		rs = minhaSTM.executeQuery(SQL);
		String nome = "";
		try {
			rs.next();
			nome = "Deseja remover o cliente :\n " + rs.getString("Nome");
		}catch (SQLException ex1){
			JOptionPane.showMessageDialog(null,"Cliente n„o cadastrado.","Moto Express",JOptionPane.INFORMATION_MESSAGE);
			//return;
		}
		int n = JOptionPane.showConfirmDialog(null, nome, "Moto Express",JOptionPane.YES_NO_OPTION);
		if (n == JOptionPane.YES_OPTION){
			SQL = ("DELETE FROM Cliente Where ClientePK =" +clienteVO.getClientePk());
			int r = minhaSTM.executeUpdate(SQL);
			if (r == 1){
				JOptionPane.showMessageDialog(null,"Exclus„o realizada com sucesso!","Moto Express",JOptionPane.INFORMATION_MESSAGE);
			}else{
				JOptionPane.showMessageDialog(null,"N„o foi possÌvel excluir o cliente.","Moto Express",JOptionPane.INFORMATION_MESSAGE);
			}
		}
//		}catch (SQLException ex) {
//			JOptionPane.showMessageDialog(null,""+ex,"Moto Express",JOptionPane.INFORMATION_MESSAGE);
//		}
		rs.close();
		minhaSTM.close();
	}
	/**
	 * METODO QUE CARREGA DADOS DO CLEINTE PARA AS COMBOBOX
	 * 
	 */
	public List<CadClienteVO> getClienteByCombo() throws IllegalAccessException, InstantiationException, SQLException {
		CadClienteVO cadClienteVO; List<CadClienteVO> cadClienteVOs = new ArrayList<>();
		
		minhaConexao = ConnectionFactory.getConnection();
		minhaSTM = minhaConexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		java.lang.String sql = ("SELECT ClientePk, Nome FROM CLiente");
		
		rs = minhaSTM.executeQuery(sql);
		while(rs.next()){
			cadClienteVO = new CadClienteVO();
			cadClienteVO.setClientePk(rs.getInt("clientePk"));
			cadClienteVO.setNome(rs.getString("Nome"));
			
			cadClienteVOs.add(cadClienteVO);
		}
		return cadClienteVOs;
	}

}
