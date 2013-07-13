package br.com.cs.controlmoto.domain;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.cs.controlmoto.vo.AutenticacaoVO;

public class AutenticacaoDAO {
	/**
	 * 
	 */
	ResultSet rs;
	Statement minhaSTM;
	Connection minhaConexao;
	/**
	 * @param autenticacaoVO
	 * @throws SQLException
	 */
	public void logarUsuario(AutenticacaoVO autenticacaoVO) throws IllegalAccessException, InstantiationException, SQLException{
		minhaConexao = ConnectionFactory.getConnection();
		minhaSTM = minhaConexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		String SQL = ("SELECT Usuarioss,Senhass,TipoUsuario,Status FROM Usuario WHERE Usuarioss='"+autenticacaoVO.getUsuarioTela()+"'AND Senhass='"+autenticacaoVO.getSenhaTela()+"'"); 
		rs = minhaSTM.executeQuery(SQL);
		rs.next();
		autenticacaoVO.setLogin(rs.getString("Usuarioss"));
		autenticacaoVO.setSenha(rs.getString("Senhass"));
		autenticacaoVO.setTipoUsuario(rs.getString("TipoUsuario"));
		autenticacaoVO.setStatus(rs.getString("Status"));
		
		rs.close();
		minhaSTM.close();
	}

}
