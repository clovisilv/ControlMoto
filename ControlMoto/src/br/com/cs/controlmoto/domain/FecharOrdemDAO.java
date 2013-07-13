package br.com.cs.controlmoto.domain;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import br.com.cs.controlmoto.controller.RelatCliente;
import br.com.cs.controlmoto.utils.LerArquivo;
import br.com.cs.controlmoto.vo.BarraDeStatusVO;
import br.com.cs.controlmoto.vo.FecharOrdemVO;
import br.com.cs.controlmoto.vo.LerArquivoVO;

public class FecharOrdemDAO {
	/**
	 * 
	 */
	ResultSet rs;
	Statement minhaSTM;
	Connection minhaConexao;
	String separadorPath = "", caminho="";

	/**
	 * @param fecharOrdemVO
	 */
	public final void saveFecharOrdemDAO(FecharOrdemVO fecharOrdemVO) throws IllegalAccessException, InstantiationException, SQLException {
		minhaConexao = ConnectionFactory.getConnection();
		minhaSTM = minhaConexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		String SQL = "Update Ordem SET "+
		"DataTermino='"+fecharOrdemVO.getDataTerminos()+"',"+
		"HoraTermino='"+fecharOrdemVO.getHoraTermino()+"',"+
		"TotalHoras='"+fecharOrdemVO.getTotalHoras()+"',"+
		"TotalCliente='"+fecharOrdemVO.getTotalCliente()+"',"+
		"TotalMotorista='"+fecharOrdemVO.getTotalMotorista()+"',"+
		"ObsTermino='"+fecharOrdemVO.getObsTermino()+"',"+
		"Status='"+ fecharOrdemVO.getStatus() +"'" +
		" WHERE OrdemPK="+fecharOrdemVO.getOrdemPK();
		int res = minhaSTM.executeUpdate(SQL);
			if(res == 1)
				JOptionPane.showMessageDialog(null,"Ordem de coleta encerrada!","ControlMoto",JOptionPane.INFORMATION_MESSAGE);
			else
				JOptionPane.showMessageDialog(null,"Ordem de coleta nao foi aberta!","ControlMoto",JOptionPane.INFORMATION_MESSAGE);
//			}catch(SQLException ex){
//				System.out.println(ex);
//			}
		minhaSTM.close();
	}
	/**
	 * @param fecharOrdemVO
	 */
	public final void readFecharOrdemDAO(FecharOrdemVO fecharOrdemVO) throws IllegalAccessException, InstantiationException, SQLException{
		minhaConexao = ConnectionFactory.getConnection();
		minhaSTM = minhaConexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		String SQL = ("SELECT OrdemPk, ClienteOFk, ValorHora, Taxa, MotoristaOFk, DataInicio, HoraInicio, DataTermino, " +
		" HoraTermino, TotalHoras, TotalCliente, TotalMotorista, ObsInicio, ObsTermino, DataVencimento, Status" +
		" FROM Ordem WHERE Status='"+fecharOrdemVO.getStatus()+"' AND OrdemPk= "+fecharOrdemVO.getOrdemPK());
		rs = minhaSTM.executeQuery(SQL);
		rs.next();
		fecharOrdemVO.setOrdemPK(Integer.valueOf(rs.getString("OrdemPk")));
		fecharOrdemVO.setClienteFk(Integer.valueOf(rs.getString("ClienteOFk")));
		fecharOrdemVO.setValorHora(Double.valueOf(rs.getString("ValorHora")));
		fecharOrdemVO.setTaxa(Double.valueOf(rs.getString("Taxa")));
		fecharOrdemVO.setMotoristaFk(rs.getString("MotoristaOFk"));
		fecharOrdemVO.setDataInicios(rs.getTimestamp("Datainicio"));
		fecharOrdemVO.setHoraInicio(rs.getString("Horainicio"));
		fecharOrdemVO.setDataTerminos(rs.getTimestamp("DataTermino"));
		fecharOrdemVO.setHoraTermino(rs.getString("HoraTermino"));
		fecharOrdemVO.setTotalHoras(rs.getString("TotalHoras"));
		fecharOrdemVO.setTotalCliente(Double.valueOf(rs.getString("TotalCliente") == null ? "0.00" : rs.getString("TotalCliente")));
		fecharOrdemVO.setTotalMotorista(Double.valueOf(rs.getString("TotalMotorista") == null ? "0.00" : rs.getString("TotalMotorista")));
		fecharOrdemVO.setObsInicio(rs.getString("ObsInicio"));
		fecharOrdemVO.setObsTermino(rs.getString("ObsTermino"));
		fecharOrdemVO.setDataVencimentos(rs.getTimestamp("DataVencimento"));
		
		rs.close();
		minhaSTM.close();
	}
	/**
	 * @param fecharOrdemVO
	 */
	public final void cleanFecharOrdemDAO(FecharOrdemVO fecharOrdemVO) throws IllegalAccessException, InstantiationException, SQLException{
		minhaConexao = ConnectionFactory.getConnection();
		minhaSTM = minhaConexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		String SQL = "Update Ordem SET "+
		//"OrdemPK='"+fecharOrdemVO.getOrdemPK()+"',"+
		" DataTermino="+fecharOrdemVO.getDataTermino()+","+
		" HoraTermino='"+fecharOrdemVO.getHoraTermino()+"',"+
		" ObsTermino='"+fecharOrdemVO.getObsTermino()+"',"+
		" TotalHoras='"+fecharOrdemVO.getTotalHoras()+"',"+
		" TotalCliente='"+fecharOrdemVO.getTotalCliente()+"',"+
		" TotalMotorista='"+fecharOrdemVO.getTotalMotorista()+"',"+
		" Status='"+fecharOrdemVO.getStatus()+"'"+
		" WHERE OrdemPK="+fecharOrdemVO.getOrdemPK();
		int res = minhaSTM.executeUpdate(SQL);
		if(res == 1){
			JOptionPane.showMessageDialog(null,"Liberação realizada com sucesso","ControlMoto",JOptionPane.INFORMATION_MESSAGE);
		}else{
			JOptionPane.showMessageDialog(null,"Ordem nao cadastrada.\n Para cadastrar pressione incluir","ControlMoto",JOptionPane.INFORMATION_MESSAGE);
		}

		minhaSTM.close();
	}
	/**
	 * @param fecharOrdemVO
	 */
	public final void cancelFecharOrdemDAO(FecharOrdemVO fecharOrdemVO) throws IllegalAccessException, InstantiationException, SQLException{
		minhaConexao = ConnectionFactory.getConnection();
		minhaSTM = minhaConexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		String SQL = "Update Ordem Set "+
		"Status='" +fecharOrdemVO.getStatus()+ "'"+
		" WHERE Ordempk="+fecharOrdemVO.getOrdemPK();
		int res = minhaSTM.executeUpdate(SQL);
			if (res == 1)
				JOptionPane.showMessageDialog(null,"Ordem cancelada com sucesso!","ControlMoto",JOptionPane.INFORMATION_MESSAGE);
			else                                            
				JOptionPane.showMessageDialog(null,"Ordem nao pode ser cancelada.\n Nao foi impressa.","ControlMoto",JOptionPane.INFORMATION_MESSAGE);
//			}catch(SQLException ex){
//				JOptionPane.showMessageDialog(null,"Ordem nao cancelada.","ControlMoto",JOptionPane.INFORMATION_MESSAGE);
//				return;	
//		}
		minhaSTM.close();
	}
	/**
	 * @param fecharOrdemVO
	 * @throws Exception, SQLException 
	 */
	public final void printFecharOrdemDAO(FecharOrdemVO fecharOrdemVO) throws IllegalAccessException, InstantiationException, Exception, SQLException{
    	/*
    	 * Le arquivo de configuracao do projeto
    	 */
    	LerArquivoVO lerArquivoVO = new LerArquivoVO();
    	LerArquivo lerArquivo = new LerArquivo();
    	lerArquivoVO = lerArquivo.getInformacao(lerArquivoVO);
    	
    	separadorPath = System.getProperty("file.separator");
    	caminho = new File("").getAbsolutePath();

    	minhaConexao = ConnectionFactory.getConnection();
		minhaSTM = minhaConexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		try{
			RelatCliente rep = new RelatCliente();
			JasperPrint relat;
			relat = rep.gerarRelatoriosOSFechada(fecharOrdemVO.getOrdemPK());
//			 net.sf.jasperreports.view.save.JRPdfSaveContributor saveContributor = new JRPdfSaveContributor(locale, resBundle);
			JasperViewer jasperViewer = new JasperViewer(relat,false);
			jasperViewer.setTitle("ControlMoto - Ordem de Serviço Fechada ");
			jasperViewer.setZoomRatio(0.75F);
			jasperViewer.setBounds(100,110,750,600);
			ImageIcon icone = new ImageIcon(caminho+separadorPath+lerArquivoVO.getLogoTipo()+separadorPath+"logo_cs_b.gif");
			//ImageIcon icone = new ImageIcon(".."+separadorPath+separadorPath+"ControlMoto"+separadorPath+"imagens"+separadorPath+"logo_cs_b.gif");
			jasperViewer.setIconImage(icone.getImage());
			jasperViewer.setAlwaysOnTop(true);
			jasperViewer.setVisible(true);
			}catch(SQLException ex){
				ex.printStackTrace();
			}
		String SQL = "UPDATE Ordem SET"+
				" Status='"+fecharOrdemVO.getStatus()+"'"+
				" WHERE OrdemPK="+fecharOrdemVO.getOrdemPK();
		minhaSTM.executeUpdate(SQL);
//			}catch(Exception ex){
//				ex.printStackTrace();
//			}

		minhaSTM.close();
		
	}
	/**
	 * @param fecharOrdemVO
	 * @throws Exception, SQLException 
	 */
	public final void printOrdemPdfDAO(FecharOrdemVO fecharOrdemVO) throws IllegalAccessException, InstantiationException, Exception, SQLException{
    	/*
    	 * Le arquivo de configuracao do projeto
    	 */
    	LerArquivoVO lerArquivoVO = new LerArquivoVO();
    	LerArquivo lerArquivo = new LerArquivo();
    	lerArquivoVO = lerArquivo.getInformacao(lerArquivoVO);
    	
    	separadorPath = System.getProperty("file.separator");
    	caminho = new File("").getAbsolutePath();
    	
    	minhaConexao = ConnectionFactory.getConnection();
		minhaSTM = minhaConexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		try{
			RelatCliente rep = new RelatCliente();
			JasperPrint relat;
			relat = rep.gerarRelatoriosOSFechadaPdf(fecharOrdemVO.getOrdemPK());
			JasperViewer jasperViewer = new JasperViewer(relat,false);
			jasperViewer.setTitle("ControlMoto - Ordem de Serviço Fechada ");
			jasperViewer.setZoomRatio(0.75F);
			jasperViewer.setBounds(100,110,750,600);
			ImageIcon icone = new ImageIcon(caminho+separadorPath+lerArquivoVO.getLogoTipo()+separadorPath+"logo_cs_b.gif");
			//ImageIcon icone = new ImageIcon(".."+separadorPath+separadorPath+"ControlMoto"+separadorPath+"imagens"+separadorPath+"logo_cs_b.gif");
			jasperViewer.setIconImage(icone.getImage());
			jasperViewer.setAlwaysOnTop(true);
			jasperViewer.setVisible(true);
			}catch(SQLException ex){
				ex.printStackTrace();
			}
		String SQL = "UPDATE Ordem SET"+
				" Status='"+fecharOrdemVO.getStatus()+"'"+
				" WHERE OrdemPK="+fecharOrdemVO.getOrdemPK();
		minhaSTM.executeUpdate(SQL);
//			}catch(Exception ex){
//				ex.printStackTrace();
//			}

		minhaSTM.close();
		
	}
	/**
	 * 
	 */
	public BarraDeStatusVO getInformacaoBarraDeStatus() throws SQLException, IllegalAccessException, InstantiationException{
		BarraDeStatusVO barraDeStatusVO = null;
		
		minhaConexao = ConnectionFactory.getConnection();
		minhaSTM = minhaConexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		String SQL = ("SELECT COUNT(OrdemPk) AS OrdemPk FROM Ordem ");
		rs = minhaSTM.executeQuery(SQL);
		while(rs.next()){
			barraDeStatusVO = new BarraDeStatusVO();
			barraDeStatusVO.setLblNumRegistro(rs.getString("OrdemPk"));
		}
		
		return barraDeStatusVO;
	}
	
}
