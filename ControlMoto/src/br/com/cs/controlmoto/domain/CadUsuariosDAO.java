package br.com.cs.controlmoto.domain;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import br.com.cs.controlmoto.vo.AutenticacaoVO;
import br.com.cs.controlmoto.vo.CadUsuariosVO;

public class CadUsuariosDAO {
	/**
	 * Variáveis parea coneção ao banco de dados
	 */
	Statement minhaSTM;
    ResultSet rs;
    Connection minhaConexao;
    /**
	 * Método que realiza a ação do botão incluir usuário no bd.
	 * @param usuariosDAO
	 * @return
	 */
	public void addUsuariosDAO(CadUsuariosVO cadUsuariosVO) throws IllegalAccessException, InstantiationException, SQLException{
		minhaConexao = ConnectionFactory.getConnection();
		minhaSTM = minhaConexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		String SQL = ("SELECT * FROM Usuario");
		rs = minhaSTM.executeQuery(SQL);
		if(rs.last() == true){
			int cod;
			rs.last();
			cadUsuariosVO.setUsuarioPK(cod = Integer.parseInt(rs.getString("usuarioPk"))+1);
		}else{
			cadUsuariosVO.setUsuarioPK(1);
		}
		rs.close();
		minhaSTM.close();
	}
	/**
	 * Método que grava o usuário no bd.
	 * @param usuariosDAO
	 * @return
	 */
	public void saveUsuariosDAO(CadUsuariosVO cadUsuariosVO) throws IllegalAccessException, InstantiationException, SQLException{
		minhaConexao = ConnectionFactory.getConnection();
		minhaSTM = minhaConexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		String SQL = "INSERT INTO Usuario(UsuarioPK,NomeUsuario,Usuarioss,Senhass," +
		"TipoUsuario,Status) Values ('"+
		cadUsuariosVO.getUsuarioPK()+ "','"+
		cadUsuariosVO.getNome()+ "','"+
		cadUsuariosVO.getUsuario()+ "','"+
		cadUsuariosVO.getSenha()+ "','"+ 
		cadUsuariosVO.getTipoUsuario()+"','"+
		cadUsuariosVO.getStatus()+ "')"; 
		minhaSTM.executeUpdate(SQL);
		
		minhaSTM.close();
	}
	/**
	 * Método que localiza o usuário no bd.
	 * @param usuariosDAO
	 * @return
	 */
	public void readUsuarioByPk(CadUsuariosVO cadUsuariosVO) throws IllegalAccessException, InstantiationException, SQLException{
		minhaConexao = ConnectionFactory.getConnection();
		minhaSTM = minhaConexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		String SQL = ("SELECT * FROM Usuario Where UsuarioPK =" +cadUsuariosVO.getUsuarioPK());
		rs = minhaSTM.executeQuery(SQL);
		rs.next();
		cadUsuariosVO.setUsuarioPK(Integer.parseInt((rs.getString("UsuarioPK"))));
		cadUsuariosVO.setNome(rs.getString("NomeUsuario"));
		cadUsuariosVO.setUsuario(rs.getString("Usuarioss"));
		cadUsuariosVO.setSenha(rs.getString("Senhass"));
		cadUsuariosVO.setTipoUsuario(rs.getString("tipoUsuario"));
		cadUsuariosVO.setStatus(rs.getString("Status"));
		
		minhaSTM.close();
	}
	/**
	 * Método que altera o usuário no bd.
	 * @param usuariosDAO
	 * @return
	 */	
	public void alterUsuarioByPk(CadUsuariosVO cadUsuariosVO) throws IllegalAccessException, InstantiationException, SQLException{
		minhaConexao = ConnectionFactory.getConnection();
		minhaSTM = minhaConexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		String SQL = "UPDATE Usuario SET " +
		"UsuarioPK='"+ cadUsuariosVO.getUsuarioPK()+"'," +
		"NomeUsuario='"+ cadUsuariosVO.getNome()+ "'," +
		"Usuarioss='"+ cadUsuariosVO.getUsuario()+ "'," +
		"Senhass='"+ cadUsuariosVO.getSenha()+"'," +
		"TipoUsuario='"+ cadUsuariosVO.getTipoUsuario()+"',"+
		"Status='"+ cadUsuariosVO.getStatus()+ "'" + 
		"WHERE UsuarioPK=" +cadUsuariosVO.getUsuarioPK();
		
		int res = minhaSTM.executeUpdate(SQL);
		if(res == 1){
			JOptionPane.showMessageDialog(null,"Atualizacão realizada com sucesso!");
		}else{
			JOptionPane.showMessageDialog(null,"Usuário não cadastrado.\nPara cadastrar pressione incluir.");
			rs.close();
		}
		
		minhaSTM.close();
	}
	/**
	 * Método que apaga o usuário no bd.
	 * @param usuariosDAO
	 * @return
	 */
	public void deleteUsuarioByPk(CadUsuariosVO cadUsuariosVO) throws IllegalAccessException, InstantiationException, SQLException{
		minhaConexao = ConnectionFactory.getConnection();
		minhaSTM = minhaConexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		String SQL = ("SELECT UsuarioPK, NomeUsuario FROM Usuario Where UsuarioPK=" + cadUsuariosVO.getUsuarioPK());
		rs = minhaSTM.executeQuery(SQL);
		String nome = "";
		try {
			rs.next();
			nome = "Deseja remover usuário :\n " + rs.getString("NomeUsuario");
		}catch (SQLException ex1){
			JOptionPane.showMessageDialog(null,"Usuário não cadastrado.","Moto Express",JOptionPane.INFORMATION_MESSAGE);
			//return;
			}
		int n = JOptionPane.showConfirmDialog(null, nome, "Moto Express",JOptionPane.YES_NO_OPTION);
		if (n == JOptionPane.YES_OPTION){
			SQL = ("DELETE FROM Usuario Where UsuarioPK =" +cadUsuariosVO.getUsuarioPK());
			int r = minhaSTM.executeUpdate(SQL);
			if (r == 1){
				JOptionPane.showMessageDialog(null,"Exclusão realizada com sucesso!","Moto Express",JOptionPane.INFORMATION_MESSAGE);
			}else{
				JOptionPane.showMessageDialog(null,"Não foi possível excluir o usuário.","Moto Express",JOptionPane.INFORMATION_MESSAGE);
			}
		}

		rs.close();
		minhaSTM.close();

	}
	/**
	 * @param autenticacaoVO
	 * @param cadUsuariosVO
	 */
	public void autenticaByUsuario(AutenticacaoVO autenticacaoVO, CadUsuariosVO cadUsuariosVO) throws IllegalAccessException, InstantiationException, SQLException{
		if(autenticacaoVO.getLogin() != null && !autenticacaoVO.getLogin().equals("") && autenticacaoVO.getSenha() != null && !autenticacaoVO.getSenha().equals("")){

			minhaConexao = ConnectionFactory.getConnection();
			minhaSTM = minhaConexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String SQL = "SELECT * FROM Usuario WHERE Usuarioss='"+autenticacaoVO.getLogin()+"' AND Senhass='"+autenticacaoVO.getSenha()+"'"; 
			rs = minhaSTM.executeQuery(SQL);
			if(rs.last() != false){
				autenticacaoVO.setLogin(rs.getString("Usuarioss"));
				autenticacaoVO.setSenha(rs.getString("Senhass"));
				autenticacaoVO.setTipoUsuario(rs.getString("TipoUsuario"));
				autenticacaoVO.setStatus(rs.getString("Status"));
				}
//				if (autenticacaoVO.getLogin().equals(cadUsuariosVO.getNome()) && senhas.equals(cadUsuariosVO.getSenha()) && cadUsuariosVO.getStatus().equals("HABILITADO")){
//					ControlMoto.chamaUsuario(cadUsuariosVO.getNome());
//					dispose();
//				}else{
//					int resul = JOptionPane.showConfirmDialog(this,"Usuário ou senha errado!!! \nDigite o usuário e senha !","Moto Express",JOptionPane.YES_OPTION,JOptionPane.WARNING_MESSAGE);
//					if (resul == 0){
//						txtLogin.setText("");jpwSenha.setText("");txtLogin.requestFocus();
//						return;
//					}else{
//						System.exit(0);
//					}
//				}		
//			}catch(SQLException sqlx){
//				System.out.println(sqlx);
//			}
//		}else{
//			int resul = JOptionPane.showConfirmDialog(this,"Digite usuário e senha !","Moto Express",JOptionPane.YES_OPTION,JOptionPane.WARNING_MESSAGE);
//			if (resul == 0){
//				txtLogin.requestFocus();
//				return;
//			}else{
//				System.exit(0);
//			}
			rs.close();
			minhaSTM.close();
		}
	}
	
	}