package br.com.cs.controlmoto.domain;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import br.com.cs.controlmoto.controller.RelatCliente;
import br.com.cs.controlmoto.utils.LerArquivo;
import br.com.cs.controlmoto.vo.AbrirOrdensVO;
import br.com.cs.controlmoto.vo.LerArquivoVO;

public class AbrirOrdensDAO {
	/**
	 * 
	 */
	ResultSet rs;
	Statement minhaSTM;
	Connection minhaConexao;
	/**
	 * 
	 */			
	JasperViewer jasperViewer = null;
	/**
	 * 
	 */
	DateFormat formatarData = new SimpleDateFormat().getDateInstance();
	NumberFormat vr = NumberFormat.getCurrencyInstance().getNumberInstance(new Locale("pt","BR"));
	Double valor;
	java.lang.String separadorPath = "", caminho="";
	/**
	 * @param abrirOrdensVO
	 */
	public final void addAbrirOrdemDAO(AbrirOrdensVO abrirOrdensVO) throws IllegalAccessException, InstantiationException, SQLException{
		minhaConexao = ConnectionFactory.getConnection();
		minhaSTM = minhaConexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
		String SQL = ("SELECT * FROM Ordem");
		rs = minhaSTM.executeQuery(SQL);
		if (rs.last() == true) {
			rs.last();
			int cod;
			abrirOrdensVO.setOrdemPk(cod = Integer.parseInt(rs
					.getString("ordemPk")) + 1);
		} else {
			abrirOrdensVO.setOrdemPk(Integer.valueOf(1));
		}
//		} catch (Exception ex) {
//			JOptionPane.showMessageDialog(null, "Cliente gravado com sucesso!");
//		}
		rs.close();
		minhaSTM.close();		
	}
	/**
	 * @param abrirOrdensVO
	 */
	public final void saveAbrirOrdemDAO(AbrirOrdensVO abrirOrdensVO) throws IllegalAccessException, InstantiationException, SQLException {
		minhaConexao = ConnectionFactory.getConnection();
		minhaSTM = minhaConexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		String SQL = "INSERT INTO Ordem(OrdemPK,ClienteOFK,ValorHora,Taxa,MotoristaOFK,DataInicio,HoraInicio,ObsInicio,Status)" +
		"Values ('"+
		abrirOrdensVO.getOrdemPk() +"','"+
		abrirOrdensVO.getClienteFk() + "','"+
		abrirOrdensVO.getValorHora() + "','"+
		abrirOrdensVO.getTaxa() + "','"+
		abrirOrdensVO.getMotoristaFk() + "','"+
		abrirOrdensVO.getDataInicios() + "','"+
		abrirOrdensVO.getHoraInicio() + "','"+
		abrirOrdensVO.getObsInicio() + "','"+
		abrirOrdensVO.getStatus() + "')";
		minhaSTM.executeUpdate(SQL);

		//JOptionPane.showMessageDialog(null,"Grava√ß„o realizada com sucesso!", "ControlMoto",JOptionPane.INFORMATION_MESSAGE);
//		} catch (SQLException ex) {
//			if (ex.getMessage().equals("General error"))
//				JOptionPane.showMessageDialog(null, "Ordem j√° Cadastrada", "ControlMoto", JOptionPane.INFORMATION_MESSAGE);
//			else
//				JOptionPane.showMessageDialog(null, "Dados inv√°lidos", "ControlMoto", JOptionPane.INFORMATION_MESSAGE);
//		}
		minhaSTM.close();		
	}
	/**
	 * @param abrirOrdensVO
	 */
	public final void readAbrirOrdemDAO(AbrirOrdensVO abrirOrdensVO) throws IllegalAccessException, InstantiationException, SQLException{
		minhaConexao = ConnectionFactory.getConnection();
		minhaSTM = minhaConexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		String sql = ("SELECT OrdemPk, ClienteOFk, ValorHora, Taxa, MotoristaOFk, DataInicio, HoraInicio, ObsInicio, Status" +
		" FROM Ordem WHERE OrdemPk="+abrirOrdensVO.getOrdemPk()+" AND Status = 'A' OR Status = 'A/P'");
		rs = minhaSTM.executeQuery(sql);
		rs.next();
		abrirOrdensVO.setOrdemPk(Integer.valueOf((rs.getString("OrdemPk"))));
		abrirOrdensVO.setClienteFk(Integer.valueOf(rs.getString("ClienteOFk")));
		abrirOrdensVO.setValorHora(Double.valueOf(rs.getString("ValorHora")));
		abrirOrdensVO.setTaxa(Double.valueOf(rs.getString("Taxa")));
		abrirOrdensVO.setMotoristaFk(rs.getString("MotoristaOFk"));
		abrirOrdensVO.setDataInicios(rs.getTimestamp("DataInicio"));
		abrirOrdensVO.setHoraInicio(rs.getString("HoraInicio"));
		abrirOrdensVO.setObsInicio(rs.getString("ObsInicio"));
		abrirOrdensVO.setStatus(rs.getString("Status"));
		
		rs.close();
		minhaSTM.close();
	}
	/**
	 * @param abrirOrdensVO
	 */
	public final void alterAbrirOrdemDAO(AbrirOrdensVO abrirOrdensVO) throws IllegalAccessException, InstantiationException, SQLException{
		minhaConexao = ConnectionFactory.getConnection();
		minhaSTM = minhaConexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		String SQL = "Update Ordem SET "+
		"ClienteOFK='"+abrirOrdensVO.getClienteFk()+"',"+
		"ValorHora='"+abrirOrdensVO.getValorHora()+"',"+
		"Taxa='"+abrirOrdensVO.getTaxa()+"',"+
		"MotoristaOFK='"+abrirOrdensVO.getMotoristaFk()+"',"+
		"DataInicio='"+abrirOrdensVO.getDataInicios()+"',"+
		"HoraInicio='"+abrirOrdensVO.getHoraInicio()+"',"+
		"ObsInicio='"+abrirOrdensVO.getObsInicio()+"'"+
		"WHERE OrdemPk="+abrirOrdensVO.getOrdemPk();
		int res = minhaSTM.executeUpdate(SQL);
		if(res == 1){
			JOptionPane.showMessageDialog(null,"AtualizaÁ„o realizada com sucesso","ControlMoto",JOptionPane.INFORMATION_MESSAGE);
		}
		else{
			JOptionPane.showMessageDialog(null,"Ordem n„o cadastrada.\n Para cadastrar pressione incluir","ControlMoto",JOptionPane.INFORMATION_MESSAGE);
		}
		minhaSTM.close();
	}
	/**
	 * @param abrirOrdensVO
	 */
	public final void deleteAbrirOrdemDAO(AbrirOrdensVO abrirOrdensVO) throws IllegalAccessException, InstantiationException, SQLException{
		minhaConexao = ConnectionFactory.getConnection();
		minhaSTM = minhaConexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		String SQL = "SELECT Ordem.OrdemPk, Cliente.Nome FROM Ordem, Cliente Where Ordem.ClienteOFk = Cliente.ClientePk and OrdemPk ="+abrirOrdensVO.getOrdemPk();
		rs = minhaSTM.executeQuery(SQL);
		String nome = "";
		try{
			rs.next();
			nome = "Deseja remover a O.S. n√∫mero : "+rs.getString("OrdemPk");
		}catch(SQLException ex1){
			JOptionPane.showMessageDialog(null,"Ordem n„o cadastrada","ControlMoto",JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		int n = JOptionPane.showConfirmDialog(null,nome,"ControlMoto",JOptionPane.YES_NO_OPTION);
		if(n == JOptionPane.YES_OPTION){
			SQL = "UPDATE Ordem SET Status='"+abrirOrdensVO.getStatus()+"'" +
			" Where OrdemPk ="+abrirOrdensVO.getOrdemPk();
			int r = minhaSTM.executeUpdate(SQL);
			if(r == 1){
				JOptionPane.showMessageDialog(null,"Exclus„o realizada com sucesso","ControlMoto",JOptionPane.INFORMATION_MESSAGE);
			}else{
				JOptionPane.showMessageDialog(null,"N„o foi possÌvel excluir a ordem.","ControlMoto",JOptionPane.INFORMATION_MESSAGE);
			}
		}else
			return;
		
		rs.close();
		minhaSTM.close();
	}
	/** Abre o visualizador do JasperView para impressao da Ordem de Servico aberta
	 * @param abrirOrdensVO
	 */
	public final void printAbrirOrdemDAO(AbrirOrdensVO abrirOrdensVO) throws IllegalAccessException, InstantiationException, SQLException{
		separadorPath = System.getProperty("file.separator");
		caminho =  new File("").getAbsolutePath();
		
		LerArquivoVO lerArquivoVO = new LerArquivoVO();
		LerArquivo lerArquivo = new LerArquivo();
		lerArquivoVO = lerArquivo.getInformacao(lerArquivoVO);
		
		minhaConexao = ConnectionFactory.getConnection();
		minhaSTM = minhaConexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		try{
			RelatCliente rep = new RelatCliente();
			JasperPrint relat;
			relat = rep.gerarRelatoriosAbrirOS(abrirOrdensVO.getOrdemPk());
	    	JasperViewer jasperViewer = new JasperViewer(relat, false);
			jasperViewer.setTitle("ControlMoto  - Ordem de ServiÁo aberta");
			jasperViewer.setZoomRatio(0.75f);
			jasperViewer.setBounds(100,100,750,600);
			ImageIcon icone = new ImageIcon(caminho+separadorPath+lerArquivoVO.getLogoTipo()+separadorPath+"logo_cs_b.gif");
			jasperViewer.setIconImage(icone.getImage());
			jasperViewer.setVisible(true);
			
			String SQL = "UPDATE Ordem SET"+
			" Status='"+abrirOrdensVO.getStatus()+"'"+
			" WHERE OrdemPk="+abrirOrdensVO.getOrdemPk();
			minhaSTM.executeUpdate(SQL);
					
		}catch(Exception ex){
			ex.printStackTrace();
		}

		
		minhaSTM.close();
	}
	
}
