package br.com.cs.controlmoto.domain;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.cs.controlmoto.vo.TabConsultaOrdensVO;

public class TabConsultaOrdensDAO<ConsultasVO> {
	/**
	 * Variáveis para coneção ao banco de dados
	 */
	static Statement minhaSTM;
    static ResultSet rs;
    static Connection minhaConexao;
    /**
	 * Variáveis para conversão de datas
	 */
    SimpleDateFormat sdf;
	/**
	 * Metodo que localiza a ordem de servico no bd pelo Id.
	 * @param TabConsultaOrdensDAO
	 * @return
	 */
	public TabConsultaOrdensVO readOrdemServicoByPk(TabConsultaOrdensVO consultaVO) throws IllegalAccessException, InstantiationException, SQLException{
		minhaConexao = ConnectionFactory.getConnection();
		minhaSTM = minhaConexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		String SQL = ("Select O.OrdemPk, C.Nome, C.ValorHora, M.Nome as NomeMotorista, O.DataInicio, O.HoraInicio, O.Status From Ordem O Inner Join Cliente C On C.ClientePk = O.ClienteOFK Inner Join Motorista M On O.MotoristaOFK = M.MotoristaPk WHERE O.OrdemPk ='"+consultaVO.getOrdemPk()+"'");
		rs = minhaSTM.executeQuery(SQL);
			while(rs.next()){
				consultaVO.setOrdemPk(Integer.valueOf(rs.getString("OrdemPk")));
				consultaVO.setNomeCliente(rs.getString("Nome"));
				consultaVO.setValorHora(rs.getString("ValorHora"));
				consultaVO.setNomeMotorista(rs.getString("NomeMotorista"));
				consultaVO.setDataInicios(rs.getTimestamp("DataInicio"));
				consultaVO.setHoraInicio(rs.getString("HoraInicio"));
				consultaVO.setStatus(rs.getString("Status"));
				consultaVO.setQtde(rs.getRow());
			}
//			}catch(SQLException ex) {
//				JOptionPane.showMessageDialog(null,"Ordem de servico não localizada.\nPara cadastrar clique em incluir.","Moto Express",JOptionPane.INFORMATION_MESSAGE);
//				}
		rs.close();
		minhaSTM.close();
		return consultaVO;
	}
	/**
	 * Metodo que localiza a ordem de servico no bd pelo nome.
	 * @param TabConsultaOrdensDAO
	 * @return
	 */
	public List<TabConsultaOrdensVO> readOrdemServicoByNome(TabConsultaOrdensVO consultasVO) throws IllegalAccessException, InstantiationException, SQLException{
		ArrayList<TabConsultaOrdensVO> obj = new ArrayList<TabConsultaOrdensVO>();
		TabConsultaOrdensVO consultaVO = null;

		minhaConexao = ConnectionFactory.getConnection();
		minhaSTM = minhaConexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		String SQL = ("Select O.OrdemPk, C.Nome, C.ValorHora, M.Nome as NomeMotorista, O.DataInicio, O.HoraInicio, O.Status From Ordem O Inner Join Cliente C On C.ClientePk = O.ClienteOFK Inner Join Motorista M On O.MotoristaOFK = M.MotoristaPk WHERE C.Nome LIKE '%"+consultasVO.getNomeCliente()+"%'");
		rs = minhaSTM.executeQuery(SQL);
		while(rs.next()){
			consultaVO = new TabConsultaOrdensVO();
			consultaVO.setOrdemPk(Integer.valueOf(rs.getString("OrdemPk")));
			consultaVO.setNomeCliente(rs.getString("Nome"));
			consultaVO.setValorHora(rs.getString("ValorHora"));
			consultaVO.setNomeMotorista(rs.getString("NomeMotorista"));
			consultaVO.setDataInicios(rs.getTimestamp("DataInicio"));
			consultaVO.setHoraInicio(rs.getString("HoraInicio"));
			consultaVO.setStatus(rs.getString("Status"));
			consultaVO.setQtde(rs.getRow());
		
			obj.add(consultaVO);
		}
//			}catch(SQLException ex) {
//				JOptionPane.showMessageDialog(null,"Ordem de Servico nao localizada.\nPara cadastrar clique em incluir.","Moto Express",JOptionPane.INFORMATION_MESSAGE);
//				}
		rs.close();
		minhaSTM.close();
		
		return (List<TabConsultaOrdensVO>)obj;
	}
	/**
	 * Metodo que localiza o ordem de servico no bd pelo período.
	 * @param TabConsultaOrdensDAO
	 * @return
	 */
	public List<TabConsultaOrdensVO> readOrdemServicoByPeriodo(TabConsultaOrdensVO consultasVO) throws IllegalAccessException, InstantiationException, SQLException {
		ArrayList<TabConsultaOrdensVO> obj = new ArrayList<TabConsultaOrdensVO>();
		TabConsultaOrdensVO consultaVO = null;

		minhaConexao = ConnectionFactory.getConnection();
		minhaSTM = minhaConexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		String SQL = ("Select O.OrdemPk, C.Nome, C.ValorHora, M.Nome as NomeMotorista, O.DataInicio, O.HoraInicio, O.Status From Ordem O Inner Join Cliente C On C.ClientePk = O.ClienteOFK Inner Join Motorista M On O.MotoristaOFK = M.MotoristaPk WHERE O.DataInicio BETWEEN '"+consultasVO.getDataInicios()+"'AND'"+consultasVO.getDataTerminos()+"'");
		rs = minhaSTM.executeQuery(SQL);
			while(rs.next()){
				consultaVO = new TabConsultaOrdensVO();
				consultaVO.setOrdemPk(Integer.valueOf(rs.getString("OrdemPk")));
				consultaVO.setNomeCliente(rs.getString("Nome"));
				consultaVO.setValorHora(rs.getString("ValorHora"));
				consultaVO.setNomeMotorista(rs.getString("NomeMotorista"));
				consultaVO.setDataInicios(rs.getTimestamp("DataInicio"));
				consultaVO.setHoraInicio(rs.getString("HoraInicio"));
				consultaVO.setStatus(rs.getString("Status"));
				consultaVO.setQtde(rs.getRow());
				
				obj.add(consultaVO);
			}

//			}catch(SQLException ex) {
//				JOptionPane.showMessageDialog(null,"Ordem de Servico nao localizada.\nPara cadastrar clique em incluir.","Moto Express",JOptionPane.INFORMATION_MESSAGE);
//			}
		rs.close();
		minhaSTM.close();
		
		return (List<TabConsultaOrdensVO>) obj;
	}

}
