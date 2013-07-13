/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cs.controlmoto.domain;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.cs.controlmoto.vo.AgendamentoVO;

/**
 * @author Fictec
 */
public class AgendamentoDAO {
    
    private java.sql.PreparedStatement pst;
    private java.sql.Connection con;
    private java.sql.ResultSet rs;
    
    public AgendamentoDAO() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException{
        this.con = ConnectionFactory.getConnection();
    }
    
    public void getConnectionClose() throws SQLException, ClassNotFoundException{
        this.con.close();
    }
    /**
     * REALIZA BUSCA DE TODOS AGENDAMENTO CONFORME ANO E MES DO CALENDARIO
     * @return
     * @throws SQLException 
     */
    public List<AgendamentoVO> getAllAgendamentoByAno(AgendamentoVO agendamentoVO) throws SQLException {
        List<AgendamentoVO> agendamentoVOs = new ArrayList<>();
        AgendamentoVO agBean = null;
        //AgendaPk, 
        java.lang.String sql = "SELECT AgendaPk, DataAgendada, HoraAgendada, ClienteAFk, NomeCliente, TarefaAFk,"
        + " NomeTarefa, StatusAgenda, MotoristaAFk, NomeMotorista, NumeroOS FROM tbagendamento WHERE "
        + "YEAR(DataAgendada)=? ORDER BY DataAgendada, HoraAgendada";
        
        pst = con.prepareStatement(sql);
        pst.setInt(1, agendamentoVO.getAno());
        rs = pst.executeQuery();
        while(rs.next()){
            agBean = new AgendamentoVO();
            agBean.setAgendaPk(rs.getInt("AgendaPk"));
            agBean.setDtAgendada(""+rs.getDate("DataAgendada"));
            agBean.setDataAgendada(rs.getDate("DataAgendada"));
            agBean.setHrAgendada(rs.getString("HoraAgendada"));
            agBean.setClienteAFk(rs.getInt("ClienteAFk"));
            agBean.setNomeCliente(rs.getString("NomeCliente"));
            agBean.setTarefaAFk(rs.getInt("TarefaAFk"));
            agBean.setNomeTarefa(rs.getString("NomeTarefa"));
            agBean.setStatusAgenda(rs.getString("StatusAgenda"));
            agBean.setMotoristaAFk(rs.getString("MotoristaAFk"));
            agBean.setNomeMotorista(rs.getString("NomeMotorista"));
            agBean.setNumeroOS(rs.getInt("NumeroOS"));

            agendamentoVOs.add(agBean);
   
        }
        return agendamentoVOs;
    }    
    /**
     * REALIZA BUSCA DE TODOS AGENDAMENTO CONFORME ANO E MES DO CALENDARIO
     * @return
     * @throws SQLException 
     */
    public List<AgendamentoVO> getAllAgendamentoByAnoMes(AgendamentoVO agendamentoVO) throws SQLException {
        List<AgendamentoVO> agendamentoVOs = new ArrayList<>();
        AgendamentoVO agBean = null;
        //AgendaPk, 
        java.lang.String sql = "SELECT AgendaPk, DataAgendada, HoraAgendada, ClienteAFk, NomeCliente, TarefaAFk,"
        + " NomeTarefa, StatusAgenda, MotoristaAFk, NomeMotorista, NumeroOS FROM tbagendamento WHERE "
        + "MONTH(DataAgendada)=? AND YEAR(DataAgendada)=? ORDER BY DataAgendada, HoraAgendada";
        
        pst = con.prepareStatement(sql);
        pst.setInt(1, agendamentoVO.getMes());
        pst.setInt(2, agendamentoVO.getAno());
        rs = pst.executeQuery();
        while(rs.next()){
            agBean = new AgendamentoVO();
            agBean.setAgendaPk(rs.getInt("AgendaPk"));
            agBean.setDtAgendada(""+rs.getDate("DataAgendada"));
            agBean.setDataAgendada(rs.getDate("DataAgendada"));
            agBean.setHrAgendada(rs.getString("HoraAgendada"));
            agBean.setClienteAFk(rs.getInt("ClienteAFk"));
            agBean.setNomeCliente(rs.getString("NomeCliente"));
            agBean.setTarefaAFk(rs.getInt("TarefaAFk"));
            agBean.setNomeTarefa(rs.getString("NomeTarefa"));
            agBean.setStatusAgenda(rs.getString("StatusAgenda"));
            agBean.setMotoristaAFk(rs.getString("MotoristaAFk"));
            agBean.setNomeMotorista(rs.getString("NomeMotorista"));
            agBean.setNumeroOS(rs.getInt("NumeroOS"));

            agendamentoVOs.add(agBean);
   
        }
        return agendamentoVOs;
    }
    /**
     * REALIZA BUSCA DE TODOS AGENDAMENTO CONFORME MES
     * @return
     * @throws SQLException 
     */
    public List<AgendamentoVO> getAllAgendamentoByMes(AgendamentoVO agendamentoVO) throws SQLException {
        List<AgendamentoVO> agendamentoVOs = new ArrayList<>();
        AgendamentoVO agendaBean = null;

        java.lang.String sql = "SELECT AgendaPk, DataAgendada, HoraAgendada, ClienteAFk, NomeCliente, TarefaAFk,"
        + " NomeTarefa, StatusAgenda, MotoristaAFk, NomeMotorista, NumeroOS FROM tbagendamento WHERE "
        + " MONTH(DataAgendada) = ? AND YEAR(DataAgendada)=? ORDER BY DataAgendada, HoraAgendada";
        
        pst = con.prepareStatement(sql);
        pst.setInt(1, agendamentoVO.getMes());
        pst.setInt(2, agendamentoVO.getAno());
        rs = pst.executeQuery();
        while(rs.next()){
            agendaBean = new AgendamentoVO();
            agendaBean.setAgendaPk(rs.getInt("AgendaPk"));
            agendaBean.setDtAgendada(""+rs.getDate("DataAgendada"));
            agendaBean.setDataAgendada(rs.getDate("DataAgendada"));
            agendaBean.setHrAgendada(rs.getString("HoraAgendada"));
            agendaBean.setClienteAFk(rs.getInt("ClienteAFk"));
            agendaBean.setNomeCliente(rs.getString("NomeCliente"));
            agendaBean.setTarefaAFk(rs.getInt("TarefaAFk"));
            agendaBean.setNomeTarefa(rs.getString("NomeTarefa"));
            agendaBean.setStatusAgenda(rs.getString("StatusAgenda"));
            agendaBean.setMotoristaAFk(rs.getString("MotoristaAFk"));
            agendaBean.setNomeMotorista(rs.getString("NomeMotorista"));
            agendaBean.setNumeroOS(rs.getInt("NumeroOS"));

            agendamentoVOs.add(agendaBean);
            
        }
        return agendamentoVOs;
    }
        /**
     * REALIZA BUSCA DE TODOS AGENDAMENTO CONFORME MES
     * @return
     * @throws SQLException 
     */
    public List<AgendamentoVO> getAllAgendamentoByDia(AgendamentoVO agVO) throws SQLException {
        List<AgendamentoVO> agendamentoVOs = new ArrayList<>();
        AgendamentoVO agendamentoVO = null;
        //AgendaPk, 
        java.lang.String sql = "SELECT AgendaPk, DataAgendada, HoraAgendada, ClienteAFk, NomeCliente, TarefaAFk,"
        + " NomeTarefa, StatusAgenda, MotoristaAFk, NomeMotorista, NumeroOS FROM tbagendamento WHERE DataAgendada "
        + " BETWEEN ? AND ? ORDER BY DataAgendada, HoraAgendada";
        
        pst = con.prepareStatement(sql);
        pst.setDate(1, new java.sql.Date(agVO.getDataAgendada().getTime()));
        pst.setDate(2, new java.sql.Date(agVO.getDataAgendada().getTime()));
        rs = pst.executeQuery();
        while(rs.next()){
            agendamentoVO = new AgendamentoVO();
            agendamentoVO.setAgendaPk(rs.getInt("AgendaPk"));
            agendamentoVO.setDtAgendada(""+rs.getDate("DataAgendada"));
            agendamentoVO.setDataAgendada(rs.getDate("DataAgendada"));
            agendamentoVO.setHrAgendada(rs.getString("HoraAgendada"));
            agendamentoVO.setClienteAFk(rs.getInt("ClienteAFk"));
            agendamentoVO.setNomeCliente(rs.getString("NomeCliente"));
            agendamentoVO.setTarefaAFk(rs.getInt("TarefaAFk"));
            agendamentoVO.setNomeTarefa(rs.getString("NomeTarefa"));
            agendamentoVO.setStatusAgenda(rs.getString("StatusAgenda"));
            agendamentoVO.setMotoristaAFk(rs.getString("MotoristaAFk"));
            agendamentoVO.setNomeMotorista(rs.getString("NomeMotorista"));
            agendamentoVO.setNumeroOS(rs.getInt("NumeroOS"));

            agendamentoVOs.add(agendamentoVO);
            
        }   
        return agendamentoVOs;
    }
        /**
         * 
         */
        public void insertAgendamento(AgendamentoVO agendamentoVO) throws SQLException {
            java.lang.String sql = "INSERT INTO tbagendamento(AgendaPk, DataAgendada, HoraAgendada, ClienteAFk,"
            + " NomeCliente, TarefaAFk, NomeTarefa, StatusAgenda, MotoristaAFk, NomeMotorista)"
            + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            
            pst = con.prepareStatement(sql);
            pst.setInt(1, agendamentoVO.getAgendaPk());
            pst.setDate(2, agendamentoVO.getDataAgendada());
            pst.setTime(3, agendamentoVO.getHoraAgendada());
            pst.setInt(4, agendamentoVO.getClienteAFk());
            pst.setString(5, agendamentoVO.getNomeCliente());
            pst.setInt(6, agendamentoVO.getTarefaAFk());
            pst.setString(7, agendamentoVO.getNomeTarefa());
            pst.setString(8, agendamentoVO.getStatusAgenda());
            pst.setString(9, agendamentoVO.getMotoristaAFk());
            pst.setString(10, agendamentoVO.getNomeMotorista());
            
            pst.execute();
            
        }
        /**
         * ATUALIZA AGENDAMENTO
         */
        public void updateAgendamento(AgendamentoVO agendamentoVO) throws SQLException {
            java.lang.String sql = "UPDATE tbagendamento SET DataAgendada=?, HoraAgendada=?, ClienteAFk=?,"
            + " NomeCliente=?, TarefaAFk=?, NomeTarefa=?, StatusAgenda=?, MotoristaAFk=?, NomeMotorista=?"
            + " WHERE AgendaPk=?";
            
            pst = con.prepareStatement(sql);
            
            pst.setDate(1, (Date) agendamentoVO.getDataAgendada());
            pst.setTime(2, agendamentoVO.getHoraAgendada());
            pst.setInt(3, agendamentoVO.getClienteAFk());
            pst.setString(4, agendamentoVO.getNomeCliente());
            pst.setInt(5, agendamentoVO.getTarefaAFk());
            pst.setString(6, agendamentoVO.getNomeTarefa());
            pst.setString(7, agendamentoVO.getStatusAgenda());
            pst.setString(8, agendamentoVO.getMotoristaAFk());
            pst.setString(9, agendamentoVO.getNomeMotorista());
            pst.setInt(10, agendamentoVO.getAgendaPk());
            
            pst.execute();
        }
        /**
         * CONTROLA ID DO AGENDAMENTO
         */
        public Integer getUltimoId() throws SQLException, InstantiationException, IllegalAccessException {
        	java.lang.Integer id = null;
        	java.lang.String sql = "SELECT AgendaPK FROM tbagendamento ORDER BY AgendaPK DESC LIMIT 1";
        	
        	pst = con.prepareStatement(sql) ;
        	rs = pst.executeQuery(sql);
        	while(rs.next()){

        		id = rs.getInt("AgendaPK");
        		
        	}
        	
        	return id;
        }
        /**
         * REALIZA BUSCA DO AGENDAMENTO CONFORME ID
         * @return
         * @throws SQLException 
         */
        public AgendamentoVO getAgendamentoById(AgendamentoVO agendamentoVO) throws SQLException {
            java.lang.String sql = "SELECT AgendaPk, DataAgendada, HoraAgendada, ClienteAFk, NomeCliente, TarefaAFk,"
            + " NomeTarefa, StatusAgenda, MotoristaAFk, NomeMotorista, NumeroOS FROM tbagendamento WHERE AgendaPk=?";
            
            pst = con.prepareStatement(sql);
            pst.setInt(1, agendamentoVO.getAgendaPk());
            rs = pst.executeQuery();
            while(rs.next()){
            	agendamentoVO = new AgendamentoVO();
            	agendamentoVO.setAgendaPk(rs.getInt("AgendaPk"));
            	agendamentoVO.setDtAgendada(""+rs.getDate("DataAgendada"));
            	agendamentoVO.setDataAgendada(rs.getDate("DataAgendada"));
            	agendamentoVO.setHrAgendada(rs.getString("HoraAgendada"));
            	agendamentoVO.setHoraAgendada(rs.getTime("HoraAgendada"));
            	agendamentoVO.setClienteAFk(rs.getInt("ClienteAFk"));
            	agendamentoVO.setNomeCliente(rs.getString("NomeCliente"));
            	agendamentoVO.setTarefaAFk(rs.getInt("TarefaAFk"));
            	agendamentoVO.setNomeTarefa(rs.getString("NomeTarefa"));
            	agendamentoVO.setStatusAgenda(rs.getString("StatusAgenda"));
            	agendamentoVO.setMotoristaAFk(rs.getString("MotoristaAFk"));
            	agendamentoVO.setNomeMotorista(rs.getString("NomeMotorista"));
            	agendamentoVO.setNumeroOS(rs.getInt("NumeroOS"));
       
            }
            return agendamentoVO;
        }
        /**
         * REALIZA O CANCELAMENTO DO AGENDAMENTO CONFORME ID
         */
        public void getCancelaAgendamentoById(AgendamentoVO agendamentoVO) throws SQLException, IllegalAccessException, InstantiationException {
        	java.lang.String sql = "UPDATE tbagendamento SET StatusAgenda=? WHERE AgendaPk=?";
        	
        	pst = con.prepareStatement(sql);
        	
        	pst.setString(1, agendamentoVO.getStatusAgenda());
        	pst.setInt(2, agendamentoVO.getAgendaPk());
        	
        	pst.execute();
        	 
        }
        
}
