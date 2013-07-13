package br.com.cs.controlmoto.domain;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.cs.controlmoto.vo.PesqRelatMotVO;

public class PesqRelatMotDAO {
	/**
	 * 
	 */
	ResultSet rs;
	Statement minhaSTM;
	Connection minhaConexao;
	/**
	 * @param pesqRelatMotoVO
	 * @return
	 */	
	public final List<PesqRelatMotVO> readMotoristaByNomeData(PesqRelatMotVO pesqRelatMotoVO) throws IllegalAccessException, InstantiationException, SQLException{
		ArrayList<PesqRelatMotVO> obj = new ArrayList<PesqRelatMotVO>();
		PesqRelatMotVO pesqMotVO = null;

		minhaConexao = ConnectionFactory.getConnection();
		minhaSTM = minhaConexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		String SQL = "SELECT DISTINCT Nome FROM Motorista M JOIN Ordem O ON M.MotoristaPk = O.MotoristaOFk WHERE Status='F'";
		rs = minhaSTM.executeQuery(SQL);
		while(rs.next()){
			pesqMotVO = new PesqRelatMotVO();
			pesqMotVO.setNome(rs.getString("Nome"));
			obj.add(pesqMotVO);
		}
//		}catch (SQLException e) {
//			JOptionPane.showMessageDialog(null,"Cliente não foi localizado!","Moto Express",JOptionPane.INFORMATION_MESSAGE);
////			return;
//		}
		rs.close();
		minhaSTM.close();
		
		return (List<PesqRelatMotVO>)obj;
		
	}

}
