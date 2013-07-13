package br.com.cs.controlmoto.domain;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.cs.controlmoto.vo.PesqRelatClieVO;

public class PesqRelatClieDAO {
	/**
	 * 
	 */
	ResultSet rs;
	Statement minhaSTM;
	Connection minhaConexao;
	/**
	 * @param pesqRelatClieVO
	 * @return
	 */
	public final List<PesqRelatClieVO> readClienteByNomeData(PesqRelatClieVO pesqRelatClieVO) throws IllegalAccessException, InstantiationException, SQLException{
		ArrayList<PesqRelatClieVO> obj = new ArrayList<PesqRelatClieVO>();
		PesqRelatClieVO pesqClieVO = null;

		minhaConexao = ConnectionFactory.getConnection();
		minhaSTM = minhaConexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		String SQL = "SELECT DISTINCT Nome FROM Cliente C JOIN Ordem O ON C.ClientePk = O.ClienteOFk WHERE Status='F'";
		rs = minhaSTM.executeQuery(SQL);
		while(rs.next()){
			pesqClieVO = new PesqRelatClieVO();
			pesqClieVO.setNome(rs.getString("Nome"));
			obj.add(pesqClieVO);
		}
//		}catch (SQLException e) {
//			JOptionPane.showMessageDialog(null,"Cliente não foi localizado!","Moto Express",JOptionPane.INFORMATION_MESSAGE);
////			return;
//		}
		rs.close();
		minhaSTM.close();
		
		return (List<PesqRelatClieVO>)obj;
		
	}

}
